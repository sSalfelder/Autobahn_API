package com.github.ssalfelder.autobahn_api;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class Controller {

    @FXML
    private Button highwayButton;

    public Node getDragNode() {
        return highwayButton;
    }

    @FXML
    protected void onHighwayClicked() {

    }

}
