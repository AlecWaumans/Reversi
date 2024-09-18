package g58399.ATLIR4.view.javaFX.sceneStart;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartButton extends StackPane {
    public StartButton(String name) {
        Text text = new Text(name);
        text.setFont(Font.font(20));
        text.setFill(Color.WHITE);

        Rectangle rt = new Rectangle(700, 30);
        rt.setOpacity(0.7);
        rt.setFill(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(rt, text);

        setOnMouseEntered(event -> {
            rt.setFill(Color.GREY);
            text.setFill(Color.BLACK);
        });
        setOnMouseExited(event -> {
            rt.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });

    }


}