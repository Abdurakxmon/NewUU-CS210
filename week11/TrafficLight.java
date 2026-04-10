package com.example.map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrafficLight extends Application {

    @Override
    public void start(Stage stage) {
        Circle redLight = new Circle(30);
        redLight.setFill(Color.RED);
        redLight.setStroke(Color.DARKGRAY);
        redLight.setStrokeWidth(2);
        redLight.setOpacity(1.0);

        Circle yellowLight = new Circle(30);
        yellowLight.setFill(Color.YELLOW);
        yellowLight.setStroke(Color.DARKGRAY);
        yellowLight.setStrokeWidth(2);
        yellowLight.setOpacity(0.3);

        Circle greenLight = new Circle(30);
        greenLight.setFill(Color.GREEN);
        greenLight.setStroke(Color.DARKGRAY);
        greenLight.setStrokeWidth(2);
        greenLight.setOpacity(0.3);

        Text label = new Text("Stop");
        label.setFill(Color.WHITE);
        label.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox root = new VBox(5);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(redLight, yellowLight, greenLight, label);

        Scene scene = new Scene(root, 200, 320);
        scene.setFill(Color.DARKGRAY);

        stage.setTitle("Traffic Light");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}