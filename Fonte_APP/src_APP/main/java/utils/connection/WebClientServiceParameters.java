package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientServiceParameters {

    private WebClientRequest webClientRequest;
    private WebClientCallback callback;

    public WebClientServiceParameters(WebClientRequest webClientRequest, WebClientCallback callback) {
        this.webClientRequest = webClientRequest;
        this.callback = callback;
    }

    public WebClientRequest getWebClientRequest() {
        return webClientRequest;
    }

    public WebClientCallback getCallback() {
        return callback;
    }
}
