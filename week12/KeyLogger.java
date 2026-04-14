import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeyLogger extends Application {

    @Override
    public void start(Stage stage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Type here...");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(5);

        inputField.setOnKeyPressed(e -> {
            logArea.appendText(String.format(
                "PRESSED | Code: %s | Shift: %b | Ctrl: %b%n",
                e.getCode(), e.isShiftDown(), e.isControlDown()
            ));
        });

        inputField.setOnKeyReleased(e -> {
            logArea.appendText(String.format(
                "RELEASED | Code: %s%n",
                e.getCode()
            ));
        });

        inputField.setOnKeyTyped(e -> {
            logArea.appendText(String.format(
                "TYPED | Char: %s%n",
                e.getCharacter()
            ));
        });

        Button clearButton = new Button("Clear Log");
        clearButton.setOnAction(e -> logArea.clear());

        VBox root = new VBox(10, inputField, logArea, clearButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 420, 320);
        stage.setTitle("Key Event Logger");
        stage.setScene(scene);
        stage.show();

        inputField.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
