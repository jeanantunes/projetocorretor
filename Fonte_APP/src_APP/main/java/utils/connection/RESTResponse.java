package utils.connection;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by sleke on 24/02/2018.
 */


public class RESTResponse {

    protected Integer statusCode = null;
    protected String mensagem = null;
    protected JSONObject result = null;
    protected Map<String, List<String>> headers = null;

    public RESTResponse() { }

    public RESTResponse(Integer statusCode) {
        this.setStatusCode(statusCode);
    }

    public RESTResponse(Integer statusCode, String mensagem) {
        setStatusCode(statusCode);
        setMensagem(mensagem);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
