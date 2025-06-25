package com.github.ssalfelder.autobahn_api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button highwayButton;

    @FXML
    private TextArea outputArea;

    private ObservableList<Roadwork> roadworkList  = FXCollections.observableArrayList();
    private final String URL = "https://verkehr.autobahn.de/o/autobahn/A1/services/roadworks";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputArea.setVisible(false);
    }

    public Node getDragNode() {
        return highwayButton;
    }

    @FXML
    protected void onHighwayClicked() {
        if (!outputArea.isVisible()) {
            APIRequest request = new APIRequest();
            outputArea.setVisible(true);
            request.getData(URL, this::handleRoadworkData);
        } else {
            outputArea.setVisible(false);
        }
    }

    private void handleRoadworkData(ArrayList<Roadwork> data){
        roadworkList.clear();
        roadworkList.addAll(data);
        StringBuilder sb = new StringBuilder();
        sb.append("Baustellen: \n\n");

        for (Roadwork rw : roadworkList) {
            sb.append("Abschnitt: " + rw.getSection() + "\n");
            sb.append("Richtung: " + rw.getDirection() + "\n");
            sb.append(rw.getStart() + "\n");
            sb.append(rw.getEnd() + "\n\n");
        }

        String result = sb.toString();
        outputArea.setText(result);
    }


}
