package utils.connection;

import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import utils.WebClient;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientService extends AsyncTask<WebClientServiceParameters, Void, WebClientResponse> {

    private WebClientCallback callback;
    private Context appContext;
    private WebClientHTTPRequester httpRequester;

    public WebClientService(Context context, WebClientHTTPRequester httpRequester) {
        this.appContext = context;
        this.httpRequester = httpRequester;
    }

    @Override
    protected WebClientResponse doInBackground(WebClientServiceParameters... parameters) {
        callback = parameters[0].getCallback();
        WebClientResponse response = request(parameters[0].getWebClientRequest());
        return response;
    }

    @Override
    protected void onPostExecute(WebClientResponse webClientResponse) {
        super.onPostExecute(webClientResponse);
        switch (webClientResponse.getStatusCode()) {
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_NO_CONTENT:
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_PARTIAL:
                callback.onSuccess(webClientResponse);
                break;
            default:
                callback.onFailure(webClientResponse);
                break;
        }
    }

    public WebClientResponse request(WebClientRequest request) {
        try {
//            if(request.getEndpoint().contains("dcss/usuario/1.0/cpf/")){
            if(request.getEndpoint().contains("portal-corretor-servico-0.0.1-SNAPSHOT")){
                try {
//                    WebClientConstants.BASEURL = new URL("https://api-it1.odontoprev.com.br:8243");
                    WebClientConstants.BASEURL = new URL("http://www.corretorvendaodonto.com.br:7001");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                WebClientConstants.BASEURL = new URL(Endpoints.BASEURL);
            }
            //WebClientConstants.CERTIFICATE = ;

           // Call the httpRequester method
            return httpRequester.request(request);
        } catch (Exception e) {
          return null;
        }
    }
}