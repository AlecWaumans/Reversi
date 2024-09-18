package g58399.ATLIR4.designPattern.command;

import g58399.ATLIR4.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandAddPiece implements Command{
    private Game game;
    private Position piecePosition;
    private List<Position> capturePossibles;

    public CommandAddPiece(@NotNull Game game, @NotNull Position piecePosition){
        this.game = game;
        this.piecePosition = piecePosition;
    }

    @Override
    public void execute() {
        if(this.game.isValidMove(this.piecePosition)){
            Piece piece = new Piece(this.game.getCurrentPlayer().getColor());
            this.game.getBoard().setPiece(piece,this.piecePosition);
            this.capturePossibles = this.game.getPiece(this.piecePosition).getPossiblesCaptures(this.piecePosition,
                    this.game.getBoard(), this.game.getCurrentPlayer());
            this.game.capturePawns(this.piecePosition);
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
