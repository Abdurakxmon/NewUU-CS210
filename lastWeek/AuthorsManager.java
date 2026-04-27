package homework;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class AuthorsManager extends Application {
    private TableView<Author> tableView;
    private TextField firstNameField;
    private TextField lastNameField;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();

        TableColumn<Author, Integer> idColumn = new TableColumn<>("AuthorID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("authorID"));

        TableColumn<Author, String> firstNameColumn = new TableColumn<>("FirstName");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Author, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn);

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addAuthor());
        updateButton.setOnAction(e -> updateAuthor());
        deleteButton.setOnAction(e -> deleteAuthor());

        tableView.setOnMouseClicked(e -> {
            Author selected = tableView.getSelectionModel().getSelectedItem();

            if (selected != null) {
                firstNameField.setText(selected.getFirstName());
                lastNameField.setText(selected.getLastName());
            }
        });

        HBox controls = new HBox(10, firstNameField, lastNameField, addButton, updateButton, deleteButton);
        VBox root = new VBox(10, tableView, controls);
        root.setPadding(new Insets(15));

        loadAuthors();

        Scene scene = new Scene(root, 550, 400);
        stage.setTitle("Authors Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void loadAuthors() {
        ObservableList<Author> authors = FXCollections.observableArrayList();

        String sql = "SELECT * FROM Authors";

        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                authors.add(new Author(
                        resultSet.getInt("AuthorID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName")
                ));
            }

            tableView.getItems().setAll(authors);
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void addAuthor() {
        String sql = "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";

        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, firstNameField.getText());
            preparedStatement.setString(2, lastNameField.getText());
            preparedStatement.executeUpdate();

            clearFields();
            loadAuthors();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void updateAuthor() {
        Author selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select an author to update.");
            return;
        }

        String sql = "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";

        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, firstNameField.getText());
            preparedStatement.setString(2, lastNameField.getText());
            preparedStatement.setInt(3, selected.getAuthorID());
            preparedStatement.executeUpdate();

            clearFields();
            loadAuthors();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void deleteAuthor() {
        Author selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showError("Please select an author to delete.");
            return;
        }

        String sql = "DELETE FROM Authors WHERE AuthorID = ?";

        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, selected.getAuthorID());
            preparedStatement.executeUpdate();

            clearFields();
            loadAuthors();
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}