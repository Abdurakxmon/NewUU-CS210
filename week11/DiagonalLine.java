package com.example.map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DiagonalLine extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        Line diagonal = new Line();
        diagonal.setStartX(0);
        diagonal.setStartY(0);
        diagonal.endXProperty().bind(root.widthProperty());
        diagonal.endYProperty().bind(root.heightProperty());
        diagonal.setStroke(Color.GREEN);
        diagonal.setStrokeWidth(3);

        root.getChildren().add(diagonal);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Resizable Diagonal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}