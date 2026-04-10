package com.example.map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProfileCard extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Label nameLabel = new Label("Abdurakhmon Mukhibbillayev");
        nameLabel.setStyle("-fx-text-fill: white;");
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

        HBox topBox = new HBox(nameLabel);
        topBox.setPadding(new Insets(12));
        topBox.setStyle("-fx-background-color: #2C3E50;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Department:"), 0, 0);
        grid.add(new Label("Economics and Data Science"), 1, 0);

        grid.add(new Label("Year:"), 0, 1);
        grid.add(new Label("3"), 1, 1);

        grid.add(new Label("GPA:"), 0, 2);
        grid.add(new Label("4"), 1, 2);



        Label bottomLabel = new Label("New Uzbekistan University");
        bottomLabel.setPadding(new Insets(8));
        bottomLabel.setMaxWidth(Double.MAX_VALUE);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setStyle("-fx-background-color: #ECF0F1; -fx-font-size: 13;");

        root.setTop(topBox);
        root.setCenter(grid);
        root.setBottom(bottomLabel);

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("Profile Card");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}