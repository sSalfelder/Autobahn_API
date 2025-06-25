package com.github.ssalfelder.autobahn_api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class APIRequest {

    private Consumer<ArrayList<Roadwork>> onSucessCallback;
    private final String URL = "https://verkehr.autobahn.de/o/autobahn/A1/services/roadworks";

    private void sendRequest( String urlString) {
        try{

            CompletableFuture<HttpResponse<String>> future;

            try(HttpClient client = HttpClient.newHttpClient()) {

                HttpRequest request = HttpRequest.newBuilder(URI.create(urlString))
                        .setHeader("accept", "application/json")
                        .build();

                future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            }

        future.thenAccept(this::handleApiResponse);
        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void handleApiResponse( HttpResponse<String> response) {
        if(response.statusCode()==200) {
            System.out.println(response.body());
            var roadworkList = parseJson(response.body());

            onSucessCallback.accept(roadworkList);
            System.err.println("API response failed: " + response.statusCode());
        }
    }

    private ArrayList<Roadwork> parseJson( String json) {

        ArrayList<Roadwork> roadworkList = new ArrayList<>();

        try {
            JSONParser parse = new JSONParser();
            JSONObject jsonObject = (JSONObject)parse.parse(json);
            JSONArray roadworks = (JSONArray)jsonObject.get("roadworks");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
