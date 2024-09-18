package g58399.ATLIR4.designPattern.command;

import g58399.ATLIR4.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandAddMur implements Command{

    private Game game;
    private Position piecePosition;
    private List<Position> capturePossibles;

    public CommandAddMur(@NotNull Game game, @NotNull Position piecePosition){
        this.game = game;
        this.piecePosition = piecePosition;
    }

    @Override
    public void execute() {
        if(this.game.isValidAdd(this.piecePosition)){
            Mur mur = new Mur(Color.MUR);
            this.game.getBoard().setPiece(mur, this.piecePosition);
            this.game.setCurrentPlayer();
        }
    }

    @Override
    public void unexecute() {
        for(Position posCapture : this.capturePossibles){
            this.game.getPiece(posCapture).setColor(this.game.getCurrentPlayer().getColor());
        }
        this.game.undoMovePawn(this.piecePosition);
        this.game.setCurrentPlayer();

    }
}
