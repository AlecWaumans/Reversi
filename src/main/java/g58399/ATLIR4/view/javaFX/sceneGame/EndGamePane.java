package g58399.ATLIR4.view.javaFX.sceneGame;

import g58399.ATLIR4.model.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndGamePane extends VBox {
    private final Stage stage;
    private final Label congratulation;
    private final Player winner;
    private final CustomButtons buttonFinish;

    public EndGamePane(Stage stage, Player player){
        this.stage = stage;
        this.winner = player;
        this.buttonFinish = new CustomButtons("Finish");
        this.congratulation = new Label("CONGRATULATION !" + this.winner);
        this.congratulation.setAlignment(Pos.CENTER);
        this.buttonFinish.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().addAll(this.congratulation, this.buttonFinish);
        setFinishAction();
    }
    private void setFinishAction(){
        this.buttonFinish.setOnMouseClicked(event ->{
            this.stage.close();
        });
    }

}
