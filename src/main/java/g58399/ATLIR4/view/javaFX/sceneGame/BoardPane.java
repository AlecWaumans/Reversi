package g58399.ATLIR4.view.javaFX.sceneGame;
import g58399.ATLIR4.controller.ControllerJavaFX;
import g58399.ATLIR4.model.Position;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardPane extends GridPane {

    private final int sizeGrid;
    private Position position;

    public BoardPane(){
        setStyle("-fx-border-color: black; -fx-border-width: 10px;"); // add style of border

        this.sizeGrid = 8;
        this.setStyle("-fx-background-color: darkgreen;"); // background color

        //will traverse the grid and will add a square and will link the size
        // of the squares with the size of the cells in the grid
        for (int row = 0; row < this.sizeGrid; row++) {
            for (int col = 0; col < this.sizeGrid; col++) {
                SquareCase square = new SquareCase(row, col);
                this.add(square, col, row);
                square.prefWidthProperty().bind(this.widthProperty().divide(this.sizeGrid));
                square.prefHeightProperty().bind(this.heightProperty().divide(this.sizeGrid));
            }
        }

        this.setAlignment(Pos.CENTER);
    }

    /**
     *  This function will draw a circle and will link the diameter
     *  of the circle with that of the cells of the board and add
     *  it to the board.
     * @param color of the circle
     * @param row
     * @param column
     */
    public void drawPiece(Color color, int row, int column) {
        Circle piece = new Circle();
        piece.setFill(color);
        piece.radiusProperty().bind(this.widthProperty().divide(this.sizeGrid * 2 + 5));
        this.add(piece, column, row);
        GridPane.setHalignment(piece, Pos.CENTER.getHpos());
    }

    public void drawPossibleMove(Color color, int row, int column){
        Circle possibleMove = new Circle();
        possibleMove.setFill(null);
        possibleMove.setStroke(color);
        possibleMove.setStrokeWidth(2);
        possibleMove.radiusProperty().bind(this.widthProperty().divide(this.sizeGrid * 2 + 5));
        this.add(possibleMove, column, row);
        GridPane.setHalignment(possibleMove, Pos.CENTER.getHpos());
    }

    public void clearBoard() {
        ObservableList<Node> children = this.getChildren();
        children.clear();
    }

    /**
     * This method will allow that if the user clicks
     * on the board, he will calculate the position and
     * will call the controller methods to use this calculated
     * position.
     * @param controllerJavaFX
     */
    public void initialize(ControllerJavaFX controllerJavaFX) {
        this.setOnMouseClicked(event -> {
            double cellWidth = getWidth() / sizeGrid;
            double cellHeight = getHeight() / sizeGrid;

            int columnIndex = (int) (event.getX() / cellWidth);
            int rowIndex = (int) (event.getY() / cellHeight);
            this.position = new Position(rowIndex, columnIndex);
            if (event.getButton() == MouseButton.PRIMARY) {
                controllerJavaFX.updateMove(this.position, "gauche");
            } else if (event.getButton() == MouseButton.SECONDARY) {
                controllerJavaFX.updateMove(this.position, "droite");
            }
            controllerJavaFX.updateGameState();
        });
    }
}
