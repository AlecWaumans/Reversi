package test;

import g58399.ATLIR4.designPattern.command.Command;
import g58399.ATLIR4.designPattern.command.CommandManages;
import g58399.ATLIR4.model.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Stack<Command> undo = new Stack<Command>();
    Stack<Command> redo = new Stack<Command>();
    CommandManages manages = new CommandManages(undo, redo);

    Game game = new Game(this.manages);

    @Test
    void isGameOverTrue() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.BLACK);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        boolean valueEvaluate = game.isGameOver();
        assertTrue(valueEvaluate);

    }
    @Test
    void isGameOverFalse() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.BLACK);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        this.game.getBoard().dropPiece(new Position(3,3));
        boolean valueEvaluate = game.isGameOver();
        assertFalse(valueEvaluate);

    }
}