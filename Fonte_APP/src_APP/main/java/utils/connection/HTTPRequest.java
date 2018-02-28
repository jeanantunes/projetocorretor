package utils.connection;


import org.json.JSONObject;


/**
 * Created by sleke on 24/02/2018.
 */


public class HTTPRequest {

    protected String endpoint = null;
    protected HTTPMethod httpMethod = HTTPMethod.GET;
    protected JSONObject headers = new JSONObject();
    protected Object body;
    protected Integer timeoutInterval = 60000;
    private Boolean hasToken = false;

    // -- Public Methods -- //
    public void validate() throws Exception {
        if (endpoint == null) {
            throw new Exception("endPoint is required");
        }
    }

    // -- Getters and Setters -- //
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public HTTPMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HTTPMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public JSONObject getHeaders() {
        return headers;
    }

    public void setHeaders(JSONObject headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getTimeoutInterval() {
        return timeoutInterval;
    }

    public void setTimeoutInterval(Integer timeoutInterval) {
        this.timeoutInterval = timeoutInterval;
    }

    public Boolean getHasToken() {
        return hasToken;
    }

    public void setHasToken(Boolean hastoken) {
        this.hasToken = hastoken;
    }
}