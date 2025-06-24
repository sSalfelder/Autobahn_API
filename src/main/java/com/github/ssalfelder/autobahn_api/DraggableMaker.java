package com.github.ssalfelder.autobahn_api;

import javafx.scene.Node;
import javafx.stage.Stage;

public class DraggableMaker {

    private static double mouseAnchorX;
    private static double mouseAnchorY;


    public static void makeDraggable(Stage stage, Node node) {

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - mouseAnchorX);
            stage.setY(mouseEvent.getScreenY() - mouseAnchorY);
        });
    }
}
