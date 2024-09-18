package g58399.ATLIR4.model;

import org.jetbrains.annotations.NotNull;

public class Board {
    private final Square[][] board; //attribute of board which is a two dimensional array.
    public static final int BOARD_SIZE = 8;

    /**
     * The builder initializes a new 8 x 8 square board (containing no pieces).
     * containing no pieces).
     */
    public Board() {
        this.board = new Square[BOARD_SIZE][BOARD_SIZE]; // creation of a two dimensional table with 8 rows and 8 columns.
        for (int row = 0; row < board.length; row++) { // go through the table line by line.
            for (int column = 0; column < board.length; column++) { // go through the table column by column.
                this.board[row][column] = new Square(null);// and at each square create an Object square containing no Pieces.
            }
        }
    }
    /**
     *
     * This method will allow us to know if the position is in the board or
     * not.
     *
     * @param pos The position in the tab.
     * @return returns true if the position is on the board and false otherwise
     */
    public boolean contains( @NotNull Position pos) {
        return (pos.column() >= 0
                && pos.column() < BOARD_SIZE
                && pos.row() >= 0
                && pos.row() < BOARD_SIZE);
    }
    /**
     * This method will place the piece passed in parameter on the board.
     * Makes an exception if the position in parameter is not on the board.
     * @param piece The piece you want to place on the board.
     * @param position The position in which you want to put the Piece.
     */
    public void setPiece(@NotNull Piece piece, @NotNull Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("The position is outside the board." + position);
        }
        board[position.row()][position.column()].setPiece(piece);
        //here we add the Piece as a parameter to the setpiece method
        //in the position that we get thanks to the methods getRom and getColumn.
    }
    /**
     * removes the part from the cell whose position is passed in parameter.
     *
     * @param position The position where you want to delete the Piece. Makes an
     * exception if the position in parameter is not on the board.
     */
    public void dropPiece(@NotNull Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("The position is outside the board." + position);

        }
        board[position.row()][position.column()].setPiece(null);
        // we delete by replacing it with a new square that does not contain a piece at the
        // position set in parameter that we get thanks to the methods getRom and getColumn.
    }
    /**
     *
     * @param position The position that we want to have the Piece.
     * @return the part located on the square whose position is passed in
     * parameter. Makes an exception if the position in parameter is not on the
     * board.
     */
    public Piece getPiece(@NotNull Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("The position is outside the board." + position);
        }
        return board[position.row()][position.column()].getPiece();
        //here thanks to the method getpiece we return the Piece in the position put
        //in parameter that we get thanks to the methods getRom and getColumn.
    }
    /**
     * @param position where you want to know if there is a Piece or not;
     * @return true if the given position cell is free and false otherwise.
     * Makes an exception if the position in parameter is not on the board.
     */
    public boolean isFree(@NotNull Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("It's outside of board : " + position);
        }
        return this.board[position.row()][position.column()].isFree();
        // Here thanks to the method isFree of the class Square we can check if there is a Piece or not at
        //the position set as a parameter that we get thanks to the methods getRom and getColumn.
    }

}
