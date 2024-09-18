package g58399.ATLIR4.model;

public class Square {
    private Piece piece;// the Piece contained on the playing square (which will be equal to null if the square contains no coin).

    /**
     * Here the constructor will allow us to initialise the cell where the Piece
     * is located.
     *
     * @param piece the piece that is on the square.
     */
    public Square(Piece piece) {
        this.piece = piece;
    }

    /**
     * This accessor will allow us to access the private attribute Piece of the
     * square class.
     *
     * @return The Object Piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * this setter will allow us to modify the Piece attribute by another Object
     * of the Piece class.
     *
     * @param piece returns the new attribute which is the new Piece.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * This method will check if the Piece of the board is on the square.
     *
     * @return true if the piece is on the square and false if it is not.
     */
    public boolean isFree() {
        return this.piece == null; // If the Piece attribute is equal to null it returns true and the reverse in the opposite case.
    }
}
