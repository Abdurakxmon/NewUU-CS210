package com.example.map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorMixer extends Application {

    @Override
    public void start(Stage stage) {
        double r = 0.90;
        double g = 0.50;
        double b = 0.10;

        Color mixedColor = new Color(r, g, b, 1.0);

        Rectangle preview = new Rectangle(200, 200);
        preview.setFill(mixedColor);

        Text rText = new Text(String.format("R: %.2f", r));
        Text gText = new Text(String.format("G: %.2f", g));
        Text bText = new Text(String.format("B: %.2f", b));

        String hexCode = String.format("#%02X%02X%02X",
                (int) (r * 255),
                (int) (g * 255),
                (int) (b * 255));

        Text hexText = new Text(hexCode);
        hexText.setFont(Font.font("System", FontWeight.BOLD, 16));

        VBox root = new VBox(8);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(preview, rText, gText, bText, hexText);

        Scene scene = new Scene(root, 300, 350);
        stage.setTitle("Color Mixer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}