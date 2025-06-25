package com.github.ssalfelder.autobahn_api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Button highwayButton;

    private ObservableList<Roadwork> roadworkList  = FXCollections.observableArrayList();
    private final String URL = "https://verkehr.autobahn.de/o/autobahn/A1/services/roadworks";

    public Node getDragNode() {
        return highwayButton;
    }

    @FXML
    protected void onHighwayClicked() {
        APIRequest request = new APIRequest();
        request.getData(URL, this::handleRoadworkData);
    }

    private void handleRoadworkData(ArrayList<Roadwork> data){
        roadworkList.clear();
        roadworkList.addAll(data);

        for (Roadwork rw : roadworkList) {
            System.out.println("-------------------------------------------");
            System.out.printf("%s ", rw.getSection());
            System.out.printf("%s ", rw.getDirection());
            System.out.printf("%s ", rw.getStart());
            System.out.printf("%s %n", rw.getEnd());
            System.out.println("-------------------------------------------");
        }

    }

}
