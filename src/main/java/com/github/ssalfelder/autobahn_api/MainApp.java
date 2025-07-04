package com.github.ssalfelder.autobahn_api;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("view.fxml")));
        Parent root = loader.load();
        Controller controller = loader.getController();
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm()
        );
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setX(200);
        stage.setY(0);
        stage.show();

        DraggableMaker.makeDraggable(stage, controller.getDragNode());
        StartpositionInitializer.initializeStartPosition(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
