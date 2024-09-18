package g58399.ATLIR4.view.console;

import g58399.ATLIR4.model.*;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextView {
    private Game model;
    public TextView(@NotNull Game model){
        this.model = model;
    }
    /**
     * displays a title and a welcome message to players.
     */
    public void displayTitle() {
        System.out.println("                                   ***Othello***");
        System.out.println("                       Hello and welcome to our Othello game!! ");
        System.out.println();
    }
    /**
     * displays the game board.
     *
     */
    public void displayBoard() {
        System.out.println("   ---------------------------------------------------------------------------------");

        for (int lg = 0; lg < Board.BOARD_SIZE; lg++) {
            System.out.print(8 - lg);
            System.out.print("  |");
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Position pos = new Position(lg, col);
                if (model.getPiece(pos) == null && !this.model.getAllPossibleMoves().contains(pos)) {
                    System.out.print("         |");
                }else if(model.getPiece(pos) == null && this.model.getAllPossibleMoves().contains(pos)){
                    System.out.print("    P    |");
                }else{
                    System.out.print("   " + model.getPiece(pos) + "   |");
                }
                if (col == 7) {
                    System.out.println();
                    System.out.print("   ---------------------------------------------------------------------------------");

                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.print("       ");
        for (int i = 0; i < 8; i++) {
            System.out.print(LettreColonne(i));
            System.out.print("         ");
        }
        System.out.println();
        System.out.println();
    }

    private static String LettreColonne(@NotNull int col) {
        String LettreColonne = null;
        switch (col) {
            case 0 -> LettreColonne = "A";
            case 1 -> LettreColonne = "B";
            case 2 -> LettreColonne = "C";
            case 3 -> LettreColonne = "D";
            case 4 -> LettreColonne = "E";
            case 5 -> LettreColonne = "F";
            case 6 -> LettreColonne = "G";
            case 7 -> LettreColonne = "H";
            default -> {
            }
        }
        return LettreColonne;
    }
    public String askMode() {
        System.out.println("Please select a mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Easy");
        System.out.println("3. Human vs Hard");

        String input;
        Pattern pattern = Pattern.compile("^(Human vs Human|Human vs Easy|Human vs Hard)$");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine().trim();

            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter one of the following options: 'Human vs Human', 'Human vs Easy', 'Human vs Hard'.");
            }
        }
    }
    public String askCommand() {
        System.out.println("Do you want to undo move, redo move, or add something? Please write 'u' for undo, 'r' for redo, 'a' for add, or 'n' for none: ");
        String input;
        Pattern pattern = Pattern.compile("^\\s*[urna]\\s*$", Pattern.CASE_INSENSITIVE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine().trim().toLowerCase();

            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter 'u' for undo, 'r' for redo, 'a' for add, or 'n' for none.");
            }
        }
    }
    public Position askPosition() {
        System.out.println("Which position do you want to choose ? like '6D'! " +
                "Your position must correspond with case with the letter P ");
        String input;
        Pattern pattern = Pattern.compile("^\\s*([1-8])\\s*([a-hA-H])\\s*$");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine().trim().toLowerCase();

            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                int row = TrueNumbersLine(Character.getNumericValue(matcher.group(1).charAt(0)));
                int column = TrueNumbersColumn(String.valueOf(matcher.group(2)));
                return new Position(row, column);
            } else {
                System.out.println("Invalid input. Please enter a valid position (e.g., '3c').");
            }
        }
    }
    private static int TrueNumbersLine(@NotNull int line) {
        int Trueline = 1;
        switch (line) {
            case 1 -> Trueline = 7;
            case 2 -> Trueline = 6;
            case 3 -> Trueline = 5;
            case 4 -> Trueline = 4;
            case 5 -> Trueline = 3;
            case 6 -> Trueline = 2;
            case 7 -> Trueline = 1;
            case 8 -> Trueline = 0;
            default -> {
            }
        }
        return Trueline;
    }
    private static int TrueNumbersColumn(@NotNull String col) {
        int TrueColumn = 1;
        switch (col) {
            case "a" -> TrueColumn = 0;
            case "b" -> TrueColumn = 1;
            case "c" -> TrueColumn = 2;
            case "d" -> TrueColumn = 3;
            case "e" -> TrueColumn = 4;
            case "f" -> TrueColumn = 5;
            case "g" -> TrueColumn = 6;
            case "h" -> TrueColumn = 7;
            default -> {
            }
        }
        return TrueColumn;
    }


    /**
     * shows the winning player.
     */
    public void displayWinner(Player player) {
        System.out.println("And the winner is " + player + player.getScore());
    }

    /**
     * displays a message inviting the current player (white or black) to play,
     */
    public void displayPlayer() {
        System.out.println("It's your turn to play " + model.getCurrentPlayer());
    }
    public void displayScore(){
        System.out.println("----------  Score ----------");
        if(this.model.getCurrentPlayer().getColor().equals(Color.BLACK)) {
            System.out.println("BLACK : " + this.model.getCurrentPlayer().getScore());
            System.out.println("WHITE : " + this.model.getOppositePlayer().getScore());
        }else{
            System.out.println("WHITE : " + this.model.getCurrentPlayer().getScore());
            System.out.println("BLACK : " + this.model.getOppositePlayer().getScore());
        }
    }



}
