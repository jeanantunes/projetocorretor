package utils.connection;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientHTTPRequester extends HTTPRequester {

    private SSLContext sslContext;

    public WebClientResponse request(WebClientRequest request) throws MalformedURLException {

        setHasToken(request.getHasToken());

        WebClientResponse response = null;

        URL url = new URL(WebClientConstants.BASEURL, request.getEndpoint());

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Default Parameter
            setDefaultParameters(request, url, connection);
            // HTTP Method
            setHTTPMethod(request, connection);
            // Set Headers
            setHeaders(request, connection);

            // Add Authorization
            String accessToken = WebClientSecurity.getInstance().getToken();
            if (accessToken != null && request.getHasToken()) {
                connection.setRequestProperty("Authorization","Bearer " + accessToken);
            }

            // Body
            setBody(request, connection);

            // Call the API
            response = callAPI(connection);

        } catch (Exception e) {
            response = handleException(response, connection, e);

        } finally {
            return response;
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
}

