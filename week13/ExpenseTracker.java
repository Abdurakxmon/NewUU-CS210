import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class ExpenseTracker extends Application {

    private TextField categoryField;
    private TextField amountField;
    private TextField noteField;
    private Label statusLabel;
    private TextArea summaryArea;

    private final String FILE_NAME = "expenses.txt";

    @Override
    public void start(Stage stage) {
        Label categoryLabel = new Label("Category:");
        Label amountLabel = new Label("Amount:");
        Label noteLabel = new Label("Note:");

        categoryField = new TextField();
        categoryField.setPromptText("Food, Transport, etc.");

        amountField = new TextField();
        amountField.setPromptText("12.50");

        noteField = new TextField();
        noteField.setPromptText("Optional description");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));

        form.add(categoryLabel, 0, 0);
        form.add(categoryField, 1, 0);
        form.add(amountLabel, 0, 1);
        form.add(amountField, 1, 1);
        form.add(noteLabel, 0, 2);
        form.add(noteField, 1, 2);

        Button addButton = new Button("Add Expense");
        Button summaryButton = new Button("Show Summary");
        Button clearButton = new Button("Clear Fields");

        statusLabel = new Label();

        summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setWrapText(true);
        summaryArea.setPrefHeight(180);

        addButton.setOnAction(e -> addExpense());
        summaryButton.setOnAction(e -> showSummary());
        clearButton.setOnAction(e -> clearFields());

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                form,
                addButton,
                summaryButton,
                clearButton,
                statusLabel,
                summaryArea
        );

        Scene scene = new Scene(root, 460, 420);
        stage.setTitle("Expense Tracker");
        stage.setScene(scene);
        stage.show();
    }

    private void addExpense() {
        String category = categoryField.getText().trim();
        String amountText = amountField.getText().trim();
        String note = noteField.getText().trim();

        if (category.isEmpty() || amountText.isEmpty()) {
            showError("Category and Amount are required.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount < 0) {
                showError("Amount cannot be negative.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Amount must be a valid number.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(category + "|" + amount + "|" + note);
            bw.newLine();

            statusLabel.setText("Saved!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (IOException ex) {
            showError("Could not save expense: " + ex.getMessage());
        }
    }

    private void showSummary() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            summaryArea.setText("No expenses found yet.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        double total = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);

                if (p.length >= 2) {
                    String category = p[0];
                    double amount = Double.parseDouble(p[1]);
                    String note = p.length >= 3 ? p[2] : "";

                    total += amount;

                    sb.append(String.format("%s --- $%.2f", category, amount));

                    if (!note.isEmpty()) {
                        sb.append(" (").append(note).append(")");
                    }

                    sb.append("\n");
                }
            }

            sb.append(String.format("\nTotal: $%.2f", total));
            summaryArea.setText(sb.toString());

        } catch (IOException ex) {
            summaryArea.setText("Could not read expenses: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            summaryArea.setText("File contains an invalid amount.");
        }
    }

    private void clearFields() {
        categoryField.clear();
        amountField.clear();
        noteField.clear();
        statusLabel.setText("");
        statusLabel.setStyle("");
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
