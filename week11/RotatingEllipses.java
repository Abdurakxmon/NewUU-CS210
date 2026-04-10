package com.example.map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class RotatingEllipses extends Application {

    @Override
    public void start(Stage stage) {
        EllipsePane root = new EllipsePane();

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Rotating Ellipses");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class EllipsePane extends Pane {

    public EllipsePane() {
        paintEllipses();
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintEllipses();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintEllipses();
    }

    private void paintEllipses() {
        getChildren().clear();

        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double radiusX = centerX - 50;
        double radiusY = centerY - 50;

        for (int i = 0; i < 16; i++) {
            Ellipse ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
            ellipse.setFill(Color.WHITE);
            ellipse.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
            ellipse.setRotate(i * 180.0 / 16);

            getChildren().add(ellipse);
        }
    }
}
