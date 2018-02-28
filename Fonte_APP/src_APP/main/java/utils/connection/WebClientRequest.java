package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientRequest  extends HTTPRequest {

    private Boolean hasToken = false;

    // -- Getters and Setters -- //
    public Boolean getHasToken() {
        return hasToken;
    }

    public void setHasToken(Boolean hastoken) {
        this.hasToken = hastoken;
    }
}