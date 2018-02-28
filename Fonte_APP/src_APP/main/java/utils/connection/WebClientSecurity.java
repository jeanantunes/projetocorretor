package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientSecurity {

    private static WebClientSecurity instance;
    private String token;


    public static WebClientSecurity getInstance() {
        if(instance==null)
            instance = new WebClientSecurity();
        return instance;
    }

    private WebClientSecurity(){

    }

    public static void setInstance(WebClientSecurity instance) {
        WebClientSecurity.instance = instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
