import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class KeyboardMove extends Application {

    @Override
    public void start(Stage stage) {
        Pane playArea = new Pane();
        playArea.setPrefSize(500, 400);

        Rectangle rect = new Rectangle(60, 40, Color.BLUE);
        rect.setX(220);
        rect.setY(180);

        TextField positionField = new TextField("X: 220 Y: 180");
        positionField.setEditable(false);

        playArea.getChildren().add(rect);

        BorderPane root = new BorderPane();
        root.setCenter(playArea);
        root.setBottom(positionField);
        root.setFocusTraversable(true);

        Scene scene = new Scene(root, 500, 430);

        scene.setOnKeyPressed(e -> {
            double x = rect.getX();
            double y = rect.getY();

            if (e.getCode() == KeyCode.UP) {
                y -= 10;
            } else if (e.getCode() == KeyCode.DOWN) {
                y += 10;
            } else if (e.getCode() == KeyCode.LEFT) {
                x -= 10;
            } else if (e.getCode() == KeyCode.RIGHT) {
                x += 10;
            }

            x = Math.max(0, Math.min(x, 500 - 60));
            y = Math.max(0, Math.min(y, 400 - 40));

            rect.setX(x);
            rect.setY(y);
            positionField.setText(String.format("X: %.0f Y: %.0f", x, y));
        });

        playArea.setOnMouseClicked(e -> root.requestFocus());

        stage.setTitle("Keyboard Move");
        stage.setScene(scene);
        stage.show();

        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
