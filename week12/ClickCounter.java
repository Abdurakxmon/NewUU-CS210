import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ClickCounter extends Application {
    private int count = 0;

    @Override
    public void start(Stage stage) {
        Label countLabel = new Label("Clicks: 0");
        countLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        Button button = new Button("Click me!");
        button.setOnAction(e -> {
            count++;
            countLabel.setText("Clicks: " + count);
        });

        VBox root = new VBox(20, countLabel, button);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Click Counter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
