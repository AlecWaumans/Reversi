package g58399.ATLIR4.designPattern.strategie;

import g58399.ATLIR4.model.Position;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EasyMode implements Mode{

    private final List<Position> possibleMoves;
    private final String nameMode;

    public EasyMode(List<Position> possibleMoves){
        this.possibleMoves = Objects.requireNonNull(possibleMoves, "The list of possibleMoves Cannot be null");
        this.nameMode = "Human vs easy computer";
    }
    /**
     * This methode it's use such as a computeur
     * opponent. The reflexion of this IA is to choose one position
     * into a list of all position move possible whit a random index.
     * And make the move if it's possible.
     */
    @Override
    public Position IaAction(){
        if(!this.possibleMoves.isEmpty()) {
            //Generate a random index to choose a position in the list
            Random random = new Random();
            int randomIndex = random.nextInt(this.possibleMoves.size());
            return this.possibleMoves.get(randomIndex);
        }else{
            return null;
        }
    }

    @Override
    public String getNameMode() {
        return this.nameMode;
    }


}
