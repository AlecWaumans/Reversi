package g58399.ATLIR4.view.javaFX.sceneGame;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlayerPane extends GridPane {
    private final Label name;
    private final Label namePlayer;
    private final Label statusLabel;
    private final Label scoreLabel;
    private final Label titleScore;
    private final Stage stage;


    public PlayerPane(g58399.ATLIR4.model.Color color, Stage stage){
        this.stage = stage;

        this.namePlayer = new Label(color.name());
        this.name = new Label("Player : ");
        this.name.setTextFill(Color.BLACK);

        this.statusLabel = new Label();


        this.scoreLabel = new Label();
        this.scoreLabel.setTextFill(Color.BLACK);

        this.titleScore = new Label("Score :");
        this.titleScore.setTextFill(Color.BLACK);

        setStyle("-fx-background-color: darkred; -fx-border-color: black; -fx-border-width: 10px;"); // Définition de l'arrière-plan rouge foncé et des contours

        add(this.name, 0,0);
        add(this.namePlayer, 0,1);
        add(this.statusLabel, 0, 5);
        add(this.titleScore, 0, 6);
        add(this.scoreLabel, 0,7);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> adjustFontSize(stage));
        stage.heightProperty().addListener((obs, oldVal, newVal) -> adjustFontSize(stage));

        adjustFontSize(stage);
    }
    private void adjustFontSize(Stage stage) {
        String fontPath = "/font/VigranMaroll-2OzWK.otf";
        double width = stage.getWidth();
        double height = stage.getHeight();

        double fontSize20 = Math.min(width, height) / 20;
        double fontSize10 = Math.min(width, height) / 10;
        double fontSize14 = Math.min(width, height) / 30;

        this.name.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), fontSize20));
        this.namePlayer.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), fontSize14));
        this.statusLabel.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), fontSize14));
        this.titleScore.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), fontSize20));
        this.scoreLabel.setFont(Font.loadFont(getClass().getResourceAsStream(fontPath), fontSize10));

    }

    public void setStatusValue(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }
    public void setScoreLabel(int score){
        String newScoreString = String.valueOf(score);
        this.scoreLabel.setText(newScoreString);
    }
}
