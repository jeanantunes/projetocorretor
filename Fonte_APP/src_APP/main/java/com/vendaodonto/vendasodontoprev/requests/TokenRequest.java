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

public class TokenRequest {

    private String access_token = "f117c723-1856-3d04-a1f4-d8446745b19c";
    private String scope = "am_application_scope default";
    private String token_type = "Bearer";
    private String expires_in = "130";

    public void requestToken(Context ctx, WebClientCallback cbk) {

        JSONObject headerJson = new JSONObject();
        try {
            headerJson.put("Authorization", "Basic Y3hHZXBoTzFkcERDd3U0VHlfRExWTWxXQ0R3YTp0WlJtSUN1eUJWajJZRVczRjdaNXdWM2E0YVlh");
            headerJson.put("Cache-Control", "no-cache");
            headerJson.put("Content-Type", "application/x-www-form-urlencoded");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody("grant_type=client_credentials");
        hubRequest.setHttpMethod(HTTPMethod.POST);
        hubRequest.setHasToken(false);
        hubRequest.setEndpoint(Endpoints.TOKEN);

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
