package g58399.ATLIR4.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Mur extends Piece{

    public Mur(Color color) {
        super(color);
    }

    public static List<Position> getPossibleAdd(@NotNull Board board){
        List<Position> possibleAdd = new ArrayList<>();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int column = 0; column < Board.BOARD_SIZE; column++) {
                Position position = new Position(row,column);
                if(board.isFree(position)){
                    possibleAdd.add(position);
                }
            }
        }
        return possibleAdd;
    }

    @Override
    public String toString() {
        return "M";
    }


}
