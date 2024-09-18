package g58399.ATLIR4.main;
import g58399.ATLIR4.view.javaFX.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OthelloLauncherJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Reversi");
        MainPane mainPane = new MainPane(primaryStage);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(408);
        primaryStage.setMinWidth(700);
        primaryStage.show();
    }
}