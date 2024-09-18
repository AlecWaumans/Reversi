package g58399.ATLIR4.model;


import java.util.Objects;

public class Player {

    private Color color; // This attribute defines the colour of the player.
    private int score;
    /**
     * Here the constructor will allow us to initialise the player with the
     * given colour.
     *
     * @param color The colour of the player.
     */
    public Player(Color color) {
        this.color = Objects.requireNonNull(color, "color player is null");;
        this.score = 0;
    }
    /**
     * accessor which will allow us to retrieve the private attribute which
     * happens to be the player's colour.
     *
     * @return The colour of the player.
     */
    public Color getColor() {
        return color;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int newScore){
        this.score = newScore;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.color);
        return hash;
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
        final Player other = (Player) obj;
        return this.color == other.color;
    }

    @Override
    public String toString() {
        return (getColor() == Color.BLACK) ? " Player 1 !!  " + "(BLACK)" : " Player 2 !! " + "(WHITE)";
    }
}