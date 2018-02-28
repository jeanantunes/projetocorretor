package utils.connection;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


/**
 * Created by sleke on 24/02/2018.
 */

public class HTTPRequester {

    private URL url;
    private static final String SEPARATOR = "---------------------------------------";
    private static final String TAG = "HTTPRequester";
    private Boolean hasToken = false;
    private ConnectorType connectorType;

    private SSLContext sslContext;

    public HTTPRequester() {
    }

    public HTTPRequester(ConnectorType connectorType) {
        this.connectorType = connectorType;
    }
    public WebClientResponse request(HTTPRequest request) throws MalformedURLException, URISyntaxException {

        WebClientResponse response = null;
        URL url = WebClientConstants.BASEURL != null && !WebClientConstants.BASEURL.equals("") ?  WebClientConstants.BASEURL.toURI().resolve(request.getEndpoint()).toURL() : new URI(request.getEndpoint()).toURL();
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Default Parameters
            setDefaultParameters(request, url, connection);
            // HTTP Method
            setHTTPMethod(request, connection);

            // Set Headers
            setHeaders(request, connection);
//            // Pining
//            pinning(connection);

            // Set Body
            setBody(request, connection);

            // API Call
            response = callAPI(connection);

        } catch (Exception e) {
            response = handleException(response, connection, e);

        } finally {
            return response;
        }
    }

    // -- Protected Methods -- //
    protected void setHeaders(HTTPRequest request, HttpURLConnection connection) throws JSONException {

        JSONObject requestJsonObject = request.getHeaders();
        if (requestJsonObject != null) {
            Iterator<String> dados = requestJsonObject.keys();
            while (dados.hasNext()) {
                String chave = dados.next();
                connection.setRequestProperty(chave, requestJsonObject.getString(chave));
            }
        }
    }

    @NonNull
    protected WebClientResponse callAPI(HttpURLConnection connection) throws IOException, JSONException {
        // Call the Endpoint
        int responseCode = connection.getResponseCode();

        WebClientResponse response = new WebClientResponse();
        switch (responseCode) {
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_NO_CONTENT:
            case HttpURLConnection.HTTP_PARTIAL:
            case HttpURLConnection.HTTP_OK:

                if (this.connectorType == null || (this.connectorType != null && !this.connectorType.equals(ConnectorType.REST)))
                    handleToken(connection);

                response.setStatusCode(connection.getResponseCode());
                handleAPIReturn(response, connection);
                break;
            default:
                if (!this.connectorType.equals(ConnectorType.REST))
                    handleToken(connection);

            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                String errorMessageFull = this.readStream(connection.getErrorStream());
                response = new WebClientResponse(responseCode, errorMessageFull);
                break;
        }
        return response;
    }

    protected void setHTTPMethod(HTTPRequest request, HttpURLConnection connection) throws ProtocolException {
        // Set the HTTP Method
        connection.setRequestMethod(request.getHttpMethod().toString());
    }

    protected void setBody(HTTPRequest request, HttpURLConnection connection) throws IOException {

        switch (request.getHttpMethod()) {
            case POST:
            case PUT:
            case DELETE:
                connection.setDoOutput(true);
                break;
            case GET:
            default:
                connection.setDoOutput(false);
                break;
        }


        if (request.getBody() != null) {
            if (request.getBody() instanceof String || request.getBody() instanceof JSONObject) {

                // Validate the body
                if (request.getBody() != null && request.getBody().toString().length() > 0 && !"{}".equals(request.getBody().toString())) {
                    // Prepare the body
                    byte[] postData = null;

                    postData = request.getBody().toString().getBytes();

                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                    wr.write(postData);
                    wr.flush();
                }
            } else {
                throw new IOException("");
            }
        }
    }

    @NonNull
    protected void setDefaultParameters(HTTPRequest request, URL url, HttpURLConnection connection) throws IOException {
        connection.setConnectTimeout(request.getTimeoutInterval());
        connection.setReadTimeout(request.getTimeoutInterval());
        connection.setDoInput(true);
    }

    @NonNull
    protected WebClientResponse handleException(WebClientResponse response, HttpURLConnection connection, Exception e) throws IOException {
        if (e instanceof UnknownHostException) {
            response = new WebClientResponse(504, "Service Unavailable");
        } else if (e instanceof SocketTimeoutException) {
            response = new WebClientResponse(504, "Socket timeout");
        } else if (e instanceof SecurityException) {
            response = new WebClientResponse(-1, e.getMessage());
        } else if (e instanceof JSONException) {
            response = new WebClientResponse(-1, e.getMessage());
        } else {
            response = new WebClientResponse(500, e.getMessage());
        }
        return response;
    }

    protected void debugStart(HTTPRequest request) throws JSONException {

        JSONObject bodyJson = null;
        String bodyString  = null;

        if (request.getBody() instanceof JSONObject) {
            bodyJson = (JSONObject) request.getBody();
        } else if (request.getBody() instanceof String) {
            bodyString = (String) request.getBody();
        }


        JSONObject requestJsonObject = request.getHeaders();
        StringBuilder debugStringBuilder = new StringBuilder();
        debugStringBuilder.append(" " + SEPARATOR + " \n");
        debugStringBuilder.append(" REQUEST \n");
        debugStringBuilder.append(" Http method : "+ request.getHttpMethod() +"\n");
        debugStringBuilder.append(" Endpoint : " + WebClientConstants.BASEURL + request.getEndpoint() + "\n");
        debugStringBuilder.append("Header : { " + "\n");
        Iterator<String> headerKeys = requestJsonObject.keys();
        while (headerKeys.hasNext()) {
            String key = headerKeys.next();
            debugStringBuilder.append("     " + key + " : "+ requestJsonObject.get(key) + "\n");
        }

        String accessToken = WebClientSecurity.getInstance().getToken();
        WebClientRequest hubRequest = (WebClientRequest) request;
        if (accessToken != null && hubRequest.getHasToken()) {
            debugStringBuilder.append("     " + "Authorization" + " : Bearer "+ accessToken + "\n");
        }

        debugStringBuilder.append("} "+ "\n");debugStringBuilder.append(" Body : { " + "\n");
        if (bodyJson != null) {
            Iterator<String> bodyKeys = bodyJson.keys();
            while (bodyKeys.hasNext()) {
                String key = bodyKeys.next();
                debugStringBuilder.append("     " + key + " : " + bodyJson.get(key) + "\n");
            }
        } else {
            debugStringBuilder.append("     " + bodyString + "\n");
        }
        debugStringBuilder.append(" }" + "\n");
        debugStringBuilder.append(" " + SEPARATOR + " \n");
        Log.v(TAG, debugStringBuilder.toString());
    }

    protected void debugEnd(HTTPRequest request, HttpURLConnection connection, WebClientResponse response) throws JSONException, IOException {
        StringBuilder debugStringBuilder = new StringBuilder();
        debugStringBuilder.append(" " + SEPARATOR + " \n");
        debugStringBuilder.append(" RESPONSE \n");
        debugStringBuilder.append("Endpoint : " + WebClientConstants.BASEURL + request.getEndpoint() + "\n");
        debugStringBuilder.append("Status : " + response.getStatusCode() + " - " + response.getMensagem() + "\n");
        debugStringBuilder.append("Header : [ " + "\n");
        for (Map.Entry<String, List<String>> k : connection.getHeaderFields().entrySet()) {
            for (String v : k.getValue()){
                debugStringBuilder.append(k.getKey() + " : " + v + "\n");
            }
        }
        debugStringBuilder.append("] "+ "\n");
        if (response.getStatusCode() == HttpURLConnection.HTTP_CREATED || response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            debugStringBuilder.append("     " + gson.toJson(response.getResult()));
        }
        debugStringBuilder.append(" }" + "\n");
        debugStringBuilder.append(" " + SEPARATOR + " \n");
        Log.v(TAG,debugStringBuilder.toString());
    }

    private String readStream(InputStream stream){
        try{
            BufferedReader br;
            StringBuilder sb = new StringBuilder();
            String line;
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return  sb.toString();
        } catch (IOException e) {
        }

        return null;
    }

    private void handleToken(HttpURLConnection connection) throws UnsupportedEncodingException {
        if (getHasToken() && connection != null &&
                connection.getHeaderFields() != null &&
                connection.getHeaderFields().containsKey("x-uid") &&
                connection.getHeaderFields().containsKey("x-access-token")) {

            final String uid = connection.getHeaderField("x-uid");
            final String xAccessToken = connection.getHeaderField("x-access-token");
            if (uid != null && xAccessToken != null) {
                final String token = Base64.encodeToString((uid + ":" + xAccessToken).getBytes(),Base64.NO_WRAP);
                WebClientSecurity.getInstance().setToken(token);
            }

        } else if (getHasToken() && connection != null &&
                connection.getHeaderFields() != null &&
                connection.getHeaderFields().containsKey("access-token")) {

//            final String accessToken = connection.getHeaderField("access-token");
//            if (accessToken != null) {
//                SantanderSecurity.getInstance().setToken(accessToken);
//            }
        }
    }

    private void handleAPIReturn(WebClientResponse response, HttpURLConnection connection) throws IOException, JSONException {
        // Get headers
        response.setHeaders(connection.getHeaderFields());

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf8"));

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }


        if (sb.length() > 0) {
            String jsonDecrpt;

            jsonDecrpt = sb.toString();


            JSONArray jsonArray;
            JSONObject resultJsonObject = new JSONObject();

            if (!jsonDecrpt.equals("[]")) {
                Object json = new JSONTokener(jsonDecrpt).nextValue();
                if (json instanceof JSONObject) {
                    resultJsonObject = ((JSONObject)json);
                } else if (json instanceof JSONArray) {
                    jsonArray = (JSONArray) json;
                    resultJsonObject = new JSONObject();
                    resultJsonObject.put("data",jsonArray);
                    response.setResult(resultJsonObject);
                }
            }

            response.setResult(resultJsonObject);

        }
    }

    protected Boolean getHasToken() {
        return hasToken;
    }

    protected void setHasToken(Boolean hasToken) {
        this.hasToken = hasToken;
    }


    public static byte[] compress(String string) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(string.getBytes());
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        return compressed;
    }

    public static String decompress(byte[] compressed) throws IOException {
        final int BUFFER_SIZE = 32;
        ByteArrayInputStream is = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
        StringBuilder string = new StringBuilder();
        byte[] data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(data)) != -1) {
            string.append(new String(data, 0, bytesRead));
        }
        gis.close();
        is.close();
        return string.toString();
    }

    private byte[] streamToBytes(InputStream content)
            throws IOException {
        byte[] buffer = new byte[1024];
        int numRead = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            while ((numRead = content.read(buffer)) != -1) {
                baos.write(buffer, 0, numRead);
            }

            content.close();

            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }

    // -- Private Methods -- //
    private void pinning(HttpURLConnection connection) {
        // Verify the Certificate
        if ((WebClientConstants.CERTIFICATE != null)) {
            try {
                if (sslContext == null) {
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    BufferedInputStream caInput = new BufferedInputStream(WebClientConstants.CERTIFICATE);
                    Certificate ca;
                    ca = cf.generateCertificate(caInput);

                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null, null);
                    keyStore.setCertificateEntry("ca", ca);

                    String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                    tmf.init(keyStore);

                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, tmf.getTrustManagers(), null);
                }

                // Set the Connection
                HttpsURLConnection httpsURLConnection = ((HttpsURLConnection) connection);
                httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());

            } catch (Exception e) {

            }
        }
    }

    public ConnectorType getConnectorType() {
        return connectorType;
    }
}
