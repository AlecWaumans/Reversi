package g58399.ATLIR4.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {
    private Color color; // This attribute will define the colour of the room.

    /**
     * Here the constructor will allow us to initialise the piece with the given
     * given colour.
     *
     * @param color The colour of the piece.
     */
    public Piece(Color color) {
        this.color = Objects.requireNonNull(color, "colorPiece is null");
    }

    /**
     * accessor that will allow us to retrieve the private attribute color which
     * is the colour of the Piece.
     *
     * @return a color of the piece.
     */
    public Color getColor() {
        return this.color;
    }
    public void setColor(@NotNull Color color){
        this.color = color;
    }
    /**
     *
     * @param position of the Piece that we want to know where it can go.
     * @param board of game.
     * @return The list of movements that the piece can make according to the
     * chosen piece.
     */
    public List<Position> getPossibleMoves(@NotNull Position position, @NotNull Board board, @NotNull Color colorCurrentPLayer){
        if(!board.contains(position)){
            throw new IllegalArgumentException("the position is outside the board." + position);
        }
        List<Position> possibleMoves = new ArrayList<>();
        Piece piece = board.getPiece(position);
        Color pieceColor = piece.getColor();
        if(pieceColor != colorCurrentPLayer){
            return possibleMoves;
        }
        for(Direction direction:Direction.values()){
            Position pos = position.next(direction);
            getPossibleMovesDirection(pos,board, direction,possibleMoves,pieceColor);
        }
        return possibleMoves;
    }
    private void getPossibleMovesDirection(@NotNull Position pos, @NotNull Board board, @NotNull Direction dir, @NotNull List<Position> possibleMoves,@NotNull Color colorPiece ){
        while(board.contains(pos) && !board.isFree(pos) && !board.getPiece(pos).getColor().equals(Color.MUR)){
            pos = pos.next(dir);
        }
        Direction oppositeDir = dir.getOppositeDirection();
        Position posPrecedente = pos.next(oppositeDir);
        if(board.getPiece(posPrecedente).getColor() != colorPiece && board.contains(pos)){
            possibleMoves.add(pos);
        }
    }

    public List<Position> getPossiblesCaptures(@NotNull Position position, @NotNull Board board, @NotNull Player currentPlayer){
        if(!board.contains(position)){
            throw new IllegalArgumentException("the position is outside the board." + position);
        }
        List<Position> possiblesCaptures = new ArrayList<>();
        Piece piece = board.getPiece(position);
        Color pieceColor = piece.getColor();
        if(pieceColor != currentPlayer.getColor()){
            return possiblesCaptures;
        }

        for(Direction direction:Direction.values()){
            Position pos = position.next(direction);
            possibleCaptureLine(pos,direction,possiblesCaptures, board,currentPlayer);
        }
        return possiblesCaptures;
    }
    private void possibleCaptureLine(@NotNull Position pos, @NotNull Direction dir, @NotNull List<Position> possiblesCaptures, @NotNull Board board,  @NotNull Player currentPlayer){
        if(board.contains(pos)){
            if(possibleCaptureLineCondition(pos, dir, board, currentPlayer)){
                while(board.contains(pos) && !board.isFree(pos) && board.getPiece(pos).getColor() == currentPlayer.getColor().opposite()){
                    possiblesCaptures.add(pos);
                    pos = pos.next(dir);
                }
            }
        }
    }
    private boolean possibleCaptureLineCondition(@NotNull Position pos, @NotNull Direction dir, @NotNull Board board, @NotNull Player currentPlayer){
        while(board.contains(pos) && !board.isFree(pos) && board.getPiece(pos).getColor() == currentPlayer.getColor().opposite()){
            pos = pos.next(dir);
        }
        return board.contains(pos) && !board.isFree(pos) && board.getPiece(pos).getColor() == currentPlayer.getColor();
    }

    @Override
    public String toString() {
        return (this.color == Color.BLACK)? " B " : " W ";
    }
}
