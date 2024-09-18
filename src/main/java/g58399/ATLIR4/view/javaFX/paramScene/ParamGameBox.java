package g58399.ATLIR4.view.javaFX.paramScene;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ParamGameBox extends GridPane {

    private final Label paramGame;
    private final ChoiceBox<String> playerChoice;

    public ParamGameBox(){
        this.paramGame = new Label("Player");

        this.playerChoice = new ChoiceBox<>(FXCollections.observableArrayList(
                "human vs easy computer", "human vs hard computer", "human vs human"
        ));
        //Default selection of the first item on the box
        this.playerChoice.getSelectionModel().selectFirst();

        // Style of the choiceBox
        playerChoice.setPrefSize(200, 30);
        playerChoice.setStyle(
                "-fx-text-fill: Black; " +
                        "-fx-border-color: Black; " +
                        "-fx-border-width: 2px;");

        paramGame.setTextFill(Color.BLACK);
        paramGame.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        //spacing between elements and additions of elements
        setHgap(10);
        add(paramGame, 0, 0);
        add(playerChoice, 1, 0);
    }
    public ChoiceBox<String> getPlayerChoice(){
        return this.playerChoice;
    }
}
