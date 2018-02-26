package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public interface WebClientCallback {
    void onSuccess(WebClientResponse response);
    void onFailure(WebClientResponse response);
}
