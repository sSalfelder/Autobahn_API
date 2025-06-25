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

    private Consumer<ArrayList<Roadwork>> onSuccessCallback;

    public void getData(String url, Consumer <ArrayList<Roadwork>> onSuccessCallback) {
        this.onSuccessCallback = onSuccessCallback;
        sendRequest(url);
    }

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
            var roadworkList = parseJson(response.body());

            onSuccessCallback.accept(roadworkList);

        } else {
            System.err.println("API response failed: " + response.statusCode());
        }
    }

    private ArrayList<Roadwork> parseJson( String json) {

        ArrayList<Roadwork> roadworkList = new ArrayList<>();

        try {
            JSONParser parse = new JSONParser();
            JSONObject jsonObject = (JSONObject)parse.parse(json);
            JSONArray roadworks = (JSONArray)jsonObject.get("roadworks");

            for (var roadwork : roadworks) {

                JSONObject road = (JSONObject)roadwork;

                String section = road.get("title").toString();
                String direction = road.get("subtitle").toString();

                JSONArray description = (JSONArray)road.get("description");
                String start = description.get(1).toString();
                String end = description.get(2).toString();

                Roadwork rw = new Roadwork(section, direction, start, end);

                roadworkList.add(rw);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return roadworkList;
    }
}
