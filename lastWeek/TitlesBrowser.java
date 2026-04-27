package homework;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class TitlesBrowser extends Application {
    private TableView<AuthorBook> tableView;
    private TextField searchField;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();

        TableColumn<AuthorBook, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<AuthorBook, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<AuthorBook, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<AuthorBook, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(230);

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, isbnColumn, titleColumn);

        searchField = new TextField();
        searchField.setPromptText("Last-name prefix");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String prefix = searchField.getText().trim();

            if (prefix.isEmpty()) {
                loadData("%");
            } else {
                loadData(prefix + "%");
            }
        });

        HBox searchBox = new HBox(10, searchField, searchButton);
        VBox root = new VBox(10, searchBox, tableView);
        root.setPadding(new Insets(15));

        loadData("%");

        Scene scene = new Scene(root, 640, 400);
        stage.setTitle("Titles Browser");
        stage.setScene(scene);
        stage.show();
    }

    private void loadData(String pattern) {
        ObservableList<AuthorBook> data = FXCollections.observableArrayList();

        String sql = """
                SELECT a.FirstName, a.LastName, t.ISBN, t.Title
                FROM Authors a
                INNER JOIN AuthorISBN ai
                    ON a.AuthorID = ai.AuthorID
                INNER JOIN Titles t
                    ON ai.ISBN = t.ISBN
                WHERE a.LastName LIKE ?
                ORDER BY a.LastName, a.FirstName
                """;

        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, pattern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    data.add(new AuthorBook(
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("ISBN"),
                            resultSet.getString("Title")
                    ));
                }
            }

            tableView.getItems().setAll(data);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}