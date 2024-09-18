package g58399.ATLIR4.view.javaFX;

import g58399.ATLIR4.view.javaFX.sceneStart.StartPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPane extends VBox {
    private final StartPane startPane;

    public MainPane(Stage stage){
        this.startPane = new StartPane(stage);
        this.getChildren().add(startPane);
    }
}
