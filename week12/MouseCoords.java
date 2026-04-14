import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MouseCoords extends Application {

    @Override
    public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPrefSize(400, 300);
        pane.setStyle("-fx-background-color: lightblue;");

        Label statusLabel = new Label("Move the mouse over the pane");
        statusLabel.setPadding(new Insets(10));

        pane.setOnMouseMoved(e ->
            statusLabel.setText(String.format("X: %.1f Y: %.1f", e.getX(), e.getY()))
        );

        pane.setOnMouseClicked(e -> {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            pane.setStyle(String.format("-fx-background-color: rgb(%d,%d,%d);", r, g, b));
        });

        pane.setOnMouseExited(e ->
            statusLabel.setText("Move the mouse over the pane")
        );

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(statusLabel);
        BorderPane.setAlignment(statusLabel, Pos.CENTER);

        Scene scene = new Scene(root, 420, 360);
        stage.setTitle("Mouse Coordinates");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
