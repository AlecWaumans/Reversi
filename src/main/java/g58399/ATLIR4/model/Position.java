package g58399.ATLIR4.model;

/**
 * This class will allow us to create the pieces of the board according to
 * a well defined model.
 *
 * @param row    is the attribute that defines the lines.
 * @param column is the attribute that defines the columns.
 * @author alecw
 */
public record Position(int row, int column) {

    /**
     * here is the constructor which will allow us to initialise the pieces of
     * the board.
     *
     * @param row    corresponds to the row in the table where the room is located.
     * @param column corresponds to the column in the table where the room is
     *               located.
     */
    public Position {
    }

    public Position next(Direction dir) {
        return new Position(this.row + dir.getDeltaRow(), this.column + dir.getDeltaColumn());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }
}