import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {

    private TextField minutesField;
    private Label timeLabel;
    private Label errorLabel;
    private Button pauseResumeButton;

    private Timeline timeline;
    private FadeTransition fadeTransition;
    private int remainingSeconds = 0;

    @Override
    public void start(Stage stage) {
        minutesField = new TextField();
        minutesField.setPromptText("Enter minutes");
        minutesField.setMaxWidth(160);

        Button startButton = new Button("Start");
        pauseResumeButton = new Button("Pause");
        Button resetButton = new Button("Reset");

        timeLabel = new Label("00:00");
        timeLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 52));

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        startButton.setOnAction(e -> startCountdown());
        pauseResumeButton.setOnAction(e -> pauseOrResume());
        resetButton.setOnAction(e -> resetTimer());

        VBox root = new VBox(16);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                minutesField,
                timeLabel,
                startButton,
                pauseResumeButton,
                resetButton,
                errorLabel
        );

        Scene scene = new Scene(root, 340, 260);
        stage.setTitle("Countdown Timer");
        stage.setScene(scene);
        stage.show();
    }

    private void startCountdown() {
        String value = minutesField.getText().trim();

        int minutes;
        try {
            minutes = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            showError("Enter a valid integer greater than 0.");
            return;
        }

        if (minutes <= 0) {
            showError("Minutes must be greater than 0.");
            return;
        }

        stopFadeEffect();

        remainingSeconds = minutes * 60;
        updateDisplay();
        errorLabel.setText("");
        timeLabel.setStyle("-fx-text-fill: black;");
        pauseResumeButton.setText("Pause");

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            remainingSeconds--;
            updateDisplay();

            if (remainingSeconds <= 0) {
                timeline.stop();
                timeUpEffect();
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void pauseOrResume() {
        if (timeline == null) {
            return;
        }

        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
            pauseResumeButton.setText("Resume");
        } else {
            timeline.play();
            pauseResumeButton.setText("Pause");
        }
    }

    private void resetTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        stopFadeEffect();

        remainingSeconds = 0;
        timeLabel.setText("00:00");
        timeLabel.setOpacity(1.0);
        timeLabel.setStyle("-fx-text-fill: black;");
        errorLabel.setText("");
        pauseResumeButton.setText("Pause");
    }

    private void updateDisplay() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void timeUpEffect() {
        timeLabel.setStyle("-fx-text-fill: red;");

        fadeTransition = new FadeTransition(Duration.seconds(0.5), timeLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    private void stopFadeEffect() {
        if (fadeTransition != null) {
            fadeTransition.stop();
        }
        timeLabel.setOpacity(1.0);
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
