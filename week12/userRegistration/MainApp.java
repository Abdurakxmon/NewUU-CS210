package userRegistration;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class MainApp extends Application {
    private Stage primaryStage;
    private final UserStorage userStorage = new UserStorage(Path.of("users.txt"));

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setTitle("Login");
        showLoginScene();
        primaryStage.show();
    }

    private void showLoginScene() {
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #c62828;");

        Button loginButton = new Button("Log In");
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter both username and password.");
                return;
            }

            try {
                if (userStorage.authenticate(username, password)) {
                    messageLabel.setText("");
                    showWelcomeScene(username);
                } else {
                    messageLabel.setText("Incorrect username or password.");
                }
            } catch (IOException e) {
                messageLabel.setText("Could not read users.txt.");
            }
        });

        Hyperlink registerLink = new Hyperlink("Register");
        registerLink.setOnAction(event -> showRegisterScene());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setAlignment(Pos.CENTER);
        form.add(new Label("Username:"), 0, 0);
        form.add(usernameField, 1, 0);
        form.add(new Label("Password:"), 0, 1);
        form.add(passwordField, 1, 1);

        HBox actions = new HBox(10, loginButton, registerLink);
        actions.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, titleLabel, form, messageLabel, actions);
        root.setPadding(new Insets(24));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f7f9fc;");

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 420, 280));
    }

    private void showRegisterScene() {
        Label titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Choose a username");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Create a password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm password");

        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        Button registerButton = new Button("Register");
        registerButton.setDefaultButton(true);
        registerButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            try {
                ValidationResult result = validateRegistration(username, email, password, confirmPassword);
                if (!result.isValid()) {
                    messageLabel.setStyle("-fx-text-fill: #c62828;");
                    messageLabel.setText(result.getMessage());
                    return;
                }

                userStorage.saveUser(new User(username, password, email));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Registration successful. You can now log in.");
                alert.showAndWait();
                showLoginScene();
            } catch (IOException e) {
                messageLabel.setStyle("-fx-text-fill: #c62828;");
                messageLabel.setText("Could not save user data.");
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(event -> showLoginScene());

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setAlignment(Pos.CENTER);
        form.add(new Label("Username:"), 0, 0);
        form.add(usernameField, 1, 0);
        form.add(new Label("Email:"), 0, 1);
        form.add(emailField, 1, 1);
        form.add(new Label("Password:"), 0, 2);
        form.add(passwordField, 1, 2);
        form.add(new Label("Confirm Password:"), 0, 3);
        form.add(confirmPasswordField, 1, 3);

        HBox actions = new HBox(10, registerButton, backButton);
        actions.setAlignment(Pos.CENTER);

        VBox root = new VBox(16, titleLabel, form, messageLabel, actions);
        root.setPadding(new Insets(24));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f7f9fc;");

        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(root, 500, 350));
    }

    private ValidationResult validateRegistration(String username, String email, String password, String confirmPassword) throws IOException {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return ValidationResult.invalid("All fields are required.");
        }

        if (!password.equals(confirmPassword)) {
            return ValidationResult.invalid("Passwords do not match.");
        }

        if (userStorage.usernameExists(username)) {
            return ValidationResult.invalid("That username already exists.");
        }

        if (userStorage.emailExists(email)) {
            return ValidationResult.invalid("That email is already registered.");
        }

        return ValidationResult.valid();
    }

    private void showWelcomeScene(String username) {
        Label titleLabel = new Label("My Application");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-font-size: 18px;");

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(event -> showLoginScene());

        VBox root = new VBox(20, titleLabel, welcomeLabel, logoutButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #eef5ff;");

        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 420, 240));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
