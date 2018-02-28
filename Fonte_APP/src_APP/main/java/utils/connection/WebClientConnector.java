package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

import android.content.Context;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import utils.WebClient;

public class WebClientConnector {
    private Context mContext;
    private static WebClientConnector instance;

    /** @deprecated */
    public WebClientConnector(Context context) {
        this.mContext = context;
    }

    public static WebClientConnector getInstance(Context context) {
        if(instance == null) {
            instance = new WebClientConnector(context);
        }

        return instance;
    }

    public void request(WebClientRequest request, WebClientCallback callback) throws ExecutionException, InterruptedException {
        WebClientServiceParameters parameters = new WebClientServiceParameters(request, callback);
        (new WebClientService(this.mContext, new WebClientHTTPRequester())).execute(new WebClientServiceParameters[]{parameters});
    }
}