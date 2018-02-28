package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public class WebClientResponse extends RESTResponse {


    public WebClientResponse() {
    }

    public WebClientResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public WebClientResponse(int statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.setMensagem(mensagem);
    }

}
