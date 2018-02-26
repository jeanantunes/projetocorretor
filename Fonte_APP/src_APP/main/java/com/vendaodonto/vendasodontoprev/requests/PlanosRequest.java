package com.vendaodonto.vendasodontoprev.requests;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import utils.connection.HTTPMethod;
import utils.connection.WebClientCallback;
import utils.connection.WebClientConnector;
import utils.connection.WebClientRequest;

/**
 * Created by sleke on 24/02/2018.
 */

public class PlanosRequest {
    public void requestPlanos(Context ctx, WebClientCallback cbk) {
//        Properties props = getPropertiesFromContext(ctx);

        JSONObject headerJson = new JSONObject();
        try {
            headerJson.put("X-Application-Key", "appKey");
            headerJson.put("Content-Type", "application/json");
            headerJson.put("channel", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();
//        try {
//            bodyJson.put("Id", Id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody(bodyJson);
        hubRequest.setHttpMethod(HTTPMethod.POST);
        hubRequest.setHasToken(true);
        hubRequest.setEndpoint(Endpoints.PLANOS);

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
