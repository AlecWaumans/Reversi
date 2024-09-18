package g58399.ATLIR4.view.javaFX.sceneStart;

import g58399.ATLIR4.view.javaFX.paramScene.ParamGamePane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StartPane extends VBox {

    private final StartButton startButton;
    private  ImageView image;
    private final Label titleLabel;
    private final Stage stage;

    public StartPane(Stage stage) {
        stage.setHeight(408);
        stage.setWidth(700);
        stage.setResizable(false);
        titleLabel = new Label("Reversi");
        this.startButton = new StartButton("Start");
        this.stage = stage;
        try (InputStream is = Files.newInputStream(Paths.get("src/main/resources/image/start.png"))) {
            this.image = new ImageView(new Image(is));
            this.image.setFitWidth(700);
            this.image.setFitHeight(350);
        } catch (IOException e) {
            System.out.println("Couldn't load image");
            this.image = new ImageView();
        }
        this.getChildren().addAll(this.image, this.startButton);
        changeScene();
    }

    private void changeScene(){
        this.startButton.setOnMouseClicked(event -> {
            ParamGamePane paramPane = new ParamGamePane(stage);
            Scene scene = new Scene(paramPane);
            stage.setScene(scene);
        });
    }
}
