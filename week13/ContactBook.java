import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ContactBook extends Application {

    private Stage stage;

    private Scene listScene;
    private Scene addScene;

    private ListView<String> contactListView;
    private Label listErrorLabel;
    private Label addErrorLabel;

    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;

    private final String FILE_NAME = "contacts.txt";

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        createListScene();
        createAddScene();

        loadContacts();

        stage.setTitle("Mini Contact Book");
        stage.setScene(listScene);
        stage.show();
    }

    private void createListScene() {
        contactListView = new ListView<>();

        Button addNewButton = new Button("Add New");
        Button deleteButton = new Button("Delete Selected");
        Button refreshButton = new Button("Refresh");

        listErrorLabel = new Label();
        listErrorLabel.setStyle("-fx-text-fill: red;");

        addNewButton.setOnAction(e -> {
            clearAddForm();
            stage.setScene(addScene);
        });

        deleteButton.setOnAction(e -> deleteSelectedContact());

        refreshButton.setOnAction(e -> loadContacts());

        HBox buttons = new HBox(10, addNewButton, deleteButton, refreshButton);

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                new Label("Contacts"),
                contactListView,
                buttons,
                listErrorLabel
        );

        listScene = new Scene(root, 480, 380);
    }

    private void createAddScene() {
        nameField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Phone:"), 0, 1);
        form.add(phoneField, 1, 1);

        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        addErrorLabel = new Label();
        addErrorLabel.setStyle("-fx-text-fill: red;");

        saveButton.setOnAction(e -> saveContact());

        cancelButton.setOnAction(e -> {
            loadContacts();
            stage.setScene(listScene);
        });

        HBox buttons = new HBox(10, saveButton, cancelButton);

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                new Label("Add Contact"),
                form,
                buttons,
                addErrorLabel
        );

        addScene = new Scene(root, 480, 380);
    }

    private void loadContacts() {
        contactListView.getItems().clear();
        listErrorLabel.setText("");

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);

                if (p.length >= 3) {
                    String name = p[0];
                    String phone = p[1];
                    String email = p[2];

                    contactListView.getItems().add(name + " --- " + phone + "@" + email);
                }
            }
        } catch (IOException ex) {
            listErrorLabel.setText("Could not read contacts: " + ex.getMessage());
        }
    }

    private void saveContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            addErrorLabel.setText("All fields are required.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(name + "|" + phone + "|" + email);
            bw.newLine();

            loadContacts();
            stage.setScene(listScene);
        } catch (IOException ex) {
            addErrorLabel.setText("Could not save contact: " + ex.getMessage());
        }
    }

    private void deleteSelectedContact() {
        String selected = contactListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            listErrorLabel.setText("Select a contact to delete.");
            return;
        }

        ArrayList<String> linesToKeep = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String displayLine = toDisplayLine(line);

                if (!selected.equals(displayLine)) {
                    linesToKeep.add(line);
                }
            }
        } catch (IOException ex) {
            listErrorLabel.setText("Could not read contacts: " + ex.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String line : linesToKeep) {
                bw.write(line);
                bw.newLine();
            }

            loadContacts();
        } catch (IOException ex) {
            listErrorLabel.setText("Could not update contacts: " + ex.getMessage());
        }
    }

    private String toDisplayLine(String line) {
        String[] p = line.split("\\|", -1);

        if (p.length >= 3) {
            return p[0] + " --- " + p[1] + "@" + p[2];
        }

        return line;
    }

    private void clearAddForm() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addErrorLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
