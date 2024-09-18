package g58399.ATLIR4.view.javaFX.sceneGame;

import g58399.ATLIR4.controller.ControllerJavaFX;
import g58399.ATLIR4.model.*;
import g58399.ATLIR4.view.javaFX.sceneStart.StartButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class GamePane extends BorderPane implements PropertyChangeListener {
    private final StartButton menuButton;
    private final BoardPane boardPane;
    private final PlayerPane playerBlackInfo;
    private final PlayerPane playerWhiteInfo;
    private final Stage stage;


    public GamePane(Stage stage){
        this.stage = stage;
        this.stage.setResizable(true);
        Insets paneInsets = new Insets(10);

        this.menuButton = new StartButton("Menu");
        setBottom(this.menuButton);

        this.boardPane = new BoardPane();
        setCenter(this.boardPane);
        BorderPane.setMargin(this.boardPane, paneInsets);

        this.playerBlackInfo = new PlayerPane(Color.BLACK, this.stage);
        setLeft(this.playerBlackInfo);
        BorderPane.setMargin(this.playerBlackInfo, paneInsets);


        this.playerWhiteInfo = new PlayerPane(Color.WHITE, this.stage);
        setRight(this.playerWhiteInfo);
        BorderPane.setMargin(this.playerWhiteInfo, paneInsets);

        this.playerWhiteInfo.prefWidthProperty().bind(this.boardPane.widthProperty().divide(4));
        this.playerBlackInfo.prefWidthProperty().bind(this.boardPane.widthProperty().divide(4));

        menuButtonAction();
    }

    /**
     * Button which will redirect the stage to the menu with the menu buttons.
     */
    private void menuButtonAction(){
        this.menuButton.setOnMouseClicked(event -> {
            this.stage.setFullScreen(false);
            GamePane previousGame = (GamePane) this.stage.getScene().getRoot();
            // Create the new scene to display (PauseButtonsPane)
            PauseButtonsPane pausePane = new PauseButtonsPane(this.stage,previousGame);
            this.stage.getScene().setRoot(pausePane);

        });
    }
    public PlayerPane getPlayerWhiteInfo() {
        return playerWhiteInfo;
    }
    public PlayerPane getPlayerBlackInfo() {
        return playerBlackInfo;
    }

    /**
     * This method will draw the pieces or circles of possible
     * moves on the board by going through it looking for each
     * cell what is there and drawing a circle depending on the
     * element that is in the cell.
     * @param board
     * @param game
     */

    public void drawPieces(Board board, Game game) {
        boardPane.clearBoard();
        // Walk around the game board and draw the pieces accordingly
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int column = 0; column < Board.BOARD_SIZE; column++) {
                Position pos = new Position(row, column);
                Piece piece = board.getPiece(pos);
                if (piece != null) {
                    javafx.scene.paint.Color color;
                    if (piece.getColor().equals(g58399.ATLIR4.model.Color.WHITE)) {
                        color = javafx.scene.paint.Color.WHITE;
                    } else if(piece.getColor().equals(Color.BLACK)){
                        color = javafx.scene.paint.Color.BLACK;
                    }else{
                        color = javafx.scene.paint.Color.GREY;
                    }
                    // Draw a circle of the color of the piece on the game board
                    boardPane.drawPiece(color, row, column);
                }else{
                    boardPane.drawPiece(javafx.scene.paint.Color.GREEN, row,column);
                }
            }
        }

        // Go through the board again to draw the possible moves
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int column = 0; column < Board.BOARD_SIZE; column++) {
                Position pos = new Position(row, column);
                Piece piece = board.getPiece(pos);
                if(piece == null && game.getAllPossibleAddMur().contains(pos)) {
                    //this.boardPane.drawPossibleMove(javafx.scene.paint.Color.GREY, row, column);
                }
                if (piece == null && game.getAllPossibleMoves().contains(pos)) {
                    this.boardPane.drawPossibleMove(javafx.scene.paint.Color.BLACK, row, column);
                }

            }
        }


    }

    /**
     * Method which will check if it is the end of the game and if this is
     * the case displayed in the congratulations window.
     * @param game
     */
    public void updateEndGame(Game game){
        if (game.isGameOver()) {
            Stage popupStageEnd = new Stage();
            popupStageEnd.setTitle("End Of the Game");

            Pane popupEnd = new Pane();
            popupEnd.getStyleClass().add("root");

            // Ajouter le message de félicitations
            Label congratsLabel = new Label("Félicitations\n" + game.isWinner() + "!");
            congratsLabel.getStyleClass().add("label");
            congratsLabel.setAlignment(Pos.CENTER);
            popupEnd.getChildren().add(congratsLabel);

            // Ajouter des confettis
            for (int i = 0; i < 100; i++) {
                Circle confetti = new Circle(5, javafx.scene.paint.Color.color(Math.random(), Math.random(), Math.random()));
                confetti.setLayoutX(Math.random() * 400);
                confetti.setLayoutY(Math.random() * 400);
                popupEnd.getChildren().add(confetti);

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                    confetti.setLayoutY(confetti.getLayoutY() + Math.random() * 10);
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }

            Scene scenePopEnd = new Scene(popupEnd, 400, 300);
            scenePopEnd.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
            popupStageEnd.setScene(scenePopEnd);
            popupStageEnd.showAndWait();
        }

    }

    /**
     * Updates player information. Like their statuses, their scores.
     * @param game
     */

    public void updatePlayer(Game game){
        game.updateScorePlayer(game.getCurrentPlayer(),game.getOppositePlayer());
        if(game.getCurrentPlayer().getColor() == Color.BLACK){
            int scoreBlack = game.getCurrentPlayer().getScore();
            int scoreWhite = game.getOppositePlayer().getScore();
            getPlayerBlackInfo().setScoreLabel(scoreBlack);
            getPlayerWhiteInfo().setScoreLabel(scoreWhite);
            getPlayerBlackInfo().setStatusValue("Play");
            getPlayerWhiteInfo().setStatusValue("Wait");
        }else{
            int scoreWhite = game.getCurrentPlayer().getScore();
            int scoreBlack = game.getOppositePlayer().getScore();
            getPlayerWhiteInfo().setScoreLabel(scoreWhite);
            getPlayerBlackInfo().setScoreLabel(scoreBlack);
            getPlayerBlackInfo().setStatusValue("Wait");
            getPlayerWhiteInfo().setStatusValue("Play");
        }
    }

    public void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information !");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This function is call when they are notify if the board in the game change for update the view.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("gameStarted")
                || evt.getPropertyName().equals("boardChanged")) {
            Game game = (Game) evt.getSource();
            drawPieces(game.getBoard(), game);
            updatePlayer(game);
            updateEndGame(game);
        }
    }

    public void initialize(ControllerJavaFX controllerJavaFX) {
        this.boardPane.initialize(controllerJavaFX);
    }

}
