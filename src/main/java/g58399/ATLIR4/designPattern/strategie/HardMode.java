package g58399.ATLIR4.designPattern.strategie;

import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.model.Piece;
import g58399.ATLIR4.model.Position;

import java.util.List;
import java.util.Objects;

public class HardMode implements Mode {
    private final List<Position> possibleMoves;
    private String nameMode;
    private Game game;

    public HardMode(List<Position> possibleMoves, Game game){
        this.possibleMoves = Objects.requireNonNull(possibleMoves, "The list of possibleMoves Cannot be null");
        this.game = game;
        this.nameMode = "Human vs Hard computer";
    }

    @Override
    public Position IaAction() {
        int mostCapturePawn = 0;
        Position bestPosition = null;
        System.out.println(this.possibleMoves);
        System.out.println(this.game);
        for (Position position : this.possibleMoves) {
            if(this.game.isValidMove(position)) {
                System.out.println("je mets une piece");
                this.game.setMovePawn(position);
            }
            Piece piece = this.game.getBoard().getPiece(position);
            System.out.println(piece);
            List<Position> allPositionCaptures = piece.getPossiblesCaptures(position,
                    this.game.getBoard(), this.game.getCurrentPlayer());
            int nbrePositionCaptures = allPositionCaptures.size();
            if(mostCapturePawn < nbrePositionCaptures){
                mostCapturePawn = nbrePositionCaptures;
                bestPosition = position;
            }
            this.game.undoMovePawn(position);
        }
        return bestPosition;
    }

    @Override
    public String getNameMode() {
        return this.nameMode;
    }
}

