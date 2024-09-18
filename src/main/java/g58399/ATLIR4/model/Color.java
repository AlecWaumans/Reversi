package g58399.ATLIR4.model;

/**
 * This enumeration will provide a set of colours for each Object in this
 * project.
 *
 * @author alecw
 */
public enum Color {
    // Here is the list of colours that will never change.
    BLACK,
    WHITE,
    MUR;

    /**
     * This method will allow us to change the colour of the Object to its
     * opposite colour.
     *
     * @return the opposite colour scheme.
     */
    public Color opposite() {
        return (this == BLACK) ? WHITE : BLACK;// otherwise change the colour to black.

    }

}