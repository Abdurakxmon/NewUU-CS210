import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleCalc extends Application {

    @Override
    public void start(Stage stage) {
        Label label1 = new Label("Number 1:");
        Label label2 = new Label("Number 2:");

        TextField number1Field = new TextField();
        TextField number2Field = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        grid.add(label1, 0, 0);
        grid.add(number1Field, 1, 0);
        grid.add(label2, 0, 1);
        grid.add(number2Field, 1, 1);

        Button calculateButton = new Button("Calculate");
        Button clearButton = new Button("Clear");

        calculateButton.setOnAction(e -> {
            try {
                double n1 = Double.parseDouble(number1Field.getText());
                double n2 = Double.parseDouble(number2Field.getText());
                resultField.setText(String.format("Sum: %.2f", n1 + n2));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid input!");
            }
        });

        clearButton.setOnAction(e -> {
            number1Field.clear();
            number2Field.clear();
            resultField.clear();
        });

        HBox buttons = new HBox(10, calculateButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, grid, buttons, resultField);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 380, 230);
        stage.setTitle("Simple Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
