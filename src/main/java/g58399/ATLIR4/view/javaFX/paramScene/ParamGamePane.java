package g58399.ATLIR4.view.javaFX.paramScene;

import g58399.ATLIR4.controller.ControllerJavaFX;
import g58399.ATLIR4.designPattern.command.Command;
import g58399.ATLIR4.designPattern.command.CommandManages;
import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.view.javaFX.sceneGame.GamePane;
import g58399.ATLIR4.view.javaFX.sceneStart.StartButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class ParamGamePane extends StackPane {

    private final StartButton startButton;
    private final ParamGameBox paramGameBox;
    private final Label sentenceOpponent;
    private final Stage stage;
    private ImageView backgroundImage;

    public ParamGamePane(Stage stage) {
        this.stage = stage;
        this.startButton = new StartButton("New Game");
        this.paramGameBox = new ParamGameBox();
        this.sentenceOpponent = new Label("Choose Your Opponent !");
        this.sentenceOpponent.setTextFill(Color.BLACK);
        this.sentenceOpponent.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        try (InputStream is = Files.newInputStream(Paths.get("src/main/resources/image/menuPlayer.png"))) {
            this.backgroundImage = new ImageView(new Image(is));
            this.backgroundImage.setFitWidth(700);
            this.backgroundImage.setFitHeight(408);
        } catch (IOException e) {
            System.out.println("Couldn't load image");
            this.backgroundImage = new ImageView();
        }

        // Apply blur effect to the background image
        BoxBlur blur = new BoxBlur(5, 5, 2);
        this.backgroundImage.setEffect(blur);

        StackPane.setAlignment(backgroundImage, Pos.CENTER);
        this.getChildren().add(backgroundImage);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5));

        borderPane.setTop(sentenceOpponent);
        BorderPane.setAlignment(sentenceOpponent, Pos.CENTER);
        BorderPane.setMargin(sentenceOpponent, new Insets(10));

        borderPane.setCenter(paramGameBox);
        BorderPane.setAlignment(paramGameBox, Pos.CENTER_RIGHT);
        BorderPane.setMargin(paramGameBox, new Insets(100));

        this.startButton.setAlignment(Pos.BOTTOM_CENTER);
        borderPane.setBottom(startButton);
        BorderPane.setAlignment(startButton, Pos.BOTTOM_CENTER);

        this.getChildren().add(borderPane);

        changeScene();
    }

    /**
     * @return the string of the choiceBox.
     */
    public String playerMode(){
        return this.paramGameBox.getPlayerChoice().getValue();
    }

    /**
     * This function will initialize the game and create
     * the game interface and will initialize the game.
     */
    public void changeScene() {
        this.startButton.setOnMouseClicked(event -> {
            String mode = playerMode();
            Stack<Command> undo = new Stack<Command>();
            Stack<Command> redo = new Stack<Command>();
            CommandManages commandManages = new CommandManages(undo, redo);
            Game model = new Game(commandManages);
            GamePane gamePane = new GamePane(this.stage);
            ControllerJavaFX controller = new ControllerJavaFX(model, gamePane, mode);
            controller.initialize();
            Scene scene = new Scene(gamePane);
            stage.setScene(scene);
            controller.play();
        });
    }
}
