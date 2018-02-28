package com.vendaodonto.vendasodontoprev.requests;

import android.content.Context;

import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelPostRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import utils.connection.HTTPMethod;
import utils.connection.WebClientCallback;
import utils.connection.WebClientConnector;
import utils.connection.WebClientConstants;
import utils.connection.WebClientRequest;

/**
 * Created by sleke on 24/02/2018.
 */

public class ForcaVendaRequest {

    public void requestPostForcaVenda(Context ctx, WebClientCallback cbk, ForcaVendaModelPostRequest forcaVendaModelRequest) {

        JSONObject headerJson = new JSONObject();
        try {
            headerJson.put("Content-Type", "application/json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();
        try {
            bodyJson.put("nome", forcaVendaModelRequest.getNome());
            bodyJson.put("celular", forcaVendaModelRequest.getCelular());
            bodyJson.put("email", forcaVendaModelRequest.getEmail());

            JSONObject corretora = new JSONObject();
            corretora.put("cdCorretora", forcaVendaModelRequest.getCorretora().getCdCorretora());

            bodyJson.put("corretora", corretora);

            bodyJson.put("cpf", forcaVendaModelRequest.getCpf());
            bodyJson.put("ativo", forcaVendaModelRequest.getAtivo());
            bodyJson.put("departamento", forcaVendaModelRequest.getDepartamento());
            bodyJson.put("cargo", forcaVendaModelRequest.getCargo());
            bodyJson.put("dataNascimento", forcaVendaModelRequest.getDataNascimento());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody(bodyJson);
        hubRequest.setHttpMethod(HTTPMethod.POST);
        hubRequest.setHasToken(true);
        hubRequest.setEndpoint("portal-corretor-servico-0.0.1-SNAPSHOT/" + Endpoints.FORCAVENDA);

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void requestGetBuscaCPF(Context ctx, WebClientCallback cbk, ForcaVendaModelRequest forcaVendaModelRequest) {

        JSONObject headerJson = new JSONObject();
        try {
            headerJson.put("Content-Type", "application/x-www-form-urlencoded");
            headerJson.put("Cache-Control", "no-cache");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody(bodyJson);
        hubRequest.setHttpMethod(HTTPMethod.GET);
        hubRequest.setHasToken(false);
        hubRequest.setEndpoint("portal-corretor-servico-0.0.1-SNAPSHOT/" + Endpoints.BUSCACPF + "/" +forcaVendaModelRequest.getCpf());

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void requestGetBuscaForcaVenda(Context ctx, WebClientCallback cbk, ForcaVendaModelRequest forcaVendaModelRequest) {

        JSONObject headerJson = new JSONObject();
        try {
//            headerJson.put("Content-Type", "application/json");
            headerJson.put("Cache-Control", "no-cache");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody(bodyJson);
        hubRequest.setHttpMethod(HTTPMethod.GET);
        hubRequest.setHasToken(false);
        hubRequest.setEndpoint("portal-corretor-servico-0.0.1-SNAPSHOT/" + Endpoints.BUSCAFORCAVENDA + forcaVendaModelRequest.getCpf());

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void requestGetBuscaCNPJ(Context ctx, WebClientCallback cbk, String cdForcaVenda, String cnpj) {

        JSONObject headerJson = new JSONObject();
        try {
            headerJson.put("Content-Type", "application/x-www-form-urlencoded");
            headerJson.put("Cache-Control", "no-cache");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bodyJson = new JSONObject();

        WebClientRequest hubRequest = new WebClientRequest();
        hubRequest.setHeaders(headerJson);
        hubRequest.setBody(bodyJson);
        hubRequest.setHttpMethod(HTTPMethod.GET);
        hubRequest.setHasToken(false);
        hubRequest.setEndpoint("portal-corretor-servico-0.0.1-SNAPSHOT/" + Endpoints.BUSCACNPJ + "/" + cdForcaVenda +  "/" + Endpoints.BUSCACNPJCORRETORA + "/" + cnpj);

        try {
            WebClientConnector.getInstance(ctx).request(hubRequest, cbk);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
