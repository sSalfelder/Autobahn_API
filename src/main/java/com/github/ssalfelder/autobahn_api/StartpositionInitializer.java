package com.github.ssalfelder.autobahn_api;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartpositionInitializer {

    public static void initializeStartPosition(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double screenWidth = screenBounds.getWidth();
        double stageWidth = stage.getWidth();

        double right = screenBounds.getMinX() + screenWidth - stageWidth;
        double top = screenBounds.getMinY();

        stage.setX(right);
        stage.setY(top);
    }
}
