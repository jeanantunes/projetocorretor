package utils.connection;

/**
 * Created by sleke on 24/02/2018.
 */

public enum HTTPMethod {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT");

    private final String httpType;

    private HTTPMethod(final String type) {
        this.httpType = type;
    }

    @Override
    public String toString() {
        return httpType;
    }
}
