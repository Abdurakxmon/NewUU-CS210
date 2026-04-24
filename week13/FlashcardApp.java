import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class FlashcardApp extends Application {

    private final String FILE_NAME = "cards.txt";

    private ArrayList<String[]> cards = new ArrayList<>();
    private int currentIndex = 0;
    private boolean showingAnswer = false;

    private Label indexLabel;
    private Label cardLabel;

    @Override
    public void start(Stage stage) {
        loadCards();

        VBox root = new VBox(16);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        if (cards.isEmpty()) {
            Label noCardsLabel = new Label("No cards found. Add cards to cards.txt and restart.");
            noCardsLabel.setWrapText(true);
            root.getChildren().add(noCardsLabel);

            Scene scene = new Scene(root, 480, 300);
            stage.setTitle("Flashcard Study App");
            stage.setScene(scene);
            stage.show();
            return;
        }

        indexLabel = new Label();

        cardLabel = new Label();
        cardLabel.setWrapText(true);
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setMaxWidth(400);
        cardLabel.setMinHeight(120);
        cardLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: lightblue;" +
                "-fx-padding: 20;" +
                "-fx-background-radius: 10;"
        );

        Button previousButton = new Button("Previous");
        Button flipButton = new Button("Flip");
        Button nextButton = new Button("Next");

        previousButton.setOnAction(e -> previousCard());
        flipButton.setOnAction(e -> flipCard());
        nextButton.setOnAction(e -> nextCard());

        HBox buttons = new HBox(12, previousButton, flipButton, nextButton);
        buttons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(indexLabel, cardLabel, buttons);

        showQuestion();

        Scene scene = new Scene(root, 480, 300);
        stage.setTitle("Flashcard Study App");
        stage.setScene(scene);
        stage.show();
    }

    private void loadCards() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", 2);

                if (p.length == 2 && !p[0].trim().isEmpty() && !p[1].trim().isEmpty()) {
                    cards.add(new String[]{p[0].trim(), p[1].trim()});
                }
            }
        } catch (IOException ex) {
            cards.clear();
        }
    }

    private void showQuestion() {
        showingAnswer = false;
        indexLabel.setText("Card " + (currentIndex + 1) + " / " + cards.size());
        cardLabel.setText(cards.get(currentIndex)[0]);
        cardLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: lightblue;" +
                "-fx-padding: 20;" +
                "-fx-background-radius: 10;"
        );
    }

    private void showAnswer() {
        showingAnswer = true;
        cardLabel.setText(cards.get(currentIndex)[1]);
        cardLabel.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: lightgreen;" +
                "-fx-padding: 20;" +
                "-fx-background-radius: 10;"
        );
    }

    private void flipCard() {
        if (showingAnswer) {
            showQuestion();
        } else {
            showAnswer();
        }
    }

    private void nextCard() {
        currentIndex = (currentIndex + 1) % cards.size();
        showQuestion();
    }

    private void previousCard() {
        currentIndex = (currentIndex - 1 + cards.size()) % cards.size();
        showQuestion();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
