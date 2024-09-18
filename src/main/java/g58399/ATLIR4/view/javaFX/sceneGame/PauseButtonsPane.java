package g58399.ATLIR4.view.javaFX.sceneGame;

import g58399.ATLIR4.view.javaFX.MainPane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseButtonsPane extends VBox {
    private final CustomButtons continueGame;
    private final CustomButtons leaveGame;
    private final CustomButtons exit;
    private final GamePane previousGame;

    private final Stage stage;
    public PauseButtonsPane(Stage stage, GamePane previousGame){
        this.stage = stage;
        this.previousGame = previousGame;
        this.continueGame = new CustomButtons("Continue");
        this.leaveGame = new CustomButtons("leave");
        this.exit = new CustomButtons("Exit");

        this.getChildren().addAll(this.continueGame, this.leaveGame, this.exit);
        this.setAlignment(Pos.CENTER);

        setExitAction();
        setContinueGameAction();
        setLeaveGameAction();
    }
    private void setContinueGameAction(){
        this.continueGame.setOnMouseClicked(event ->{
            Scene scene = new Scene(this.previousGame);
            this.stage.setScene(scene);
        });
    }
    private void setLeaveGameAction(){
        this.leaveGame.setOnMouseClicked(event -> {
            MainPane mainPane = new MainPane(this.stage);
            Scene scene = new Scene(mainPane);
            stage.setScene(scene);
        });
    }
    private void setExitAction(){
        this.exit.setOnMouseClicked(event ->{
            Platform.exit();
        });
    }
}
