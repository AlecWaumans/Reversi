package g58399.ATLIR4.controller;

import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.model.Position;
import g58399.ATLIR4.view.console.TextView;
import g58399.ATLIR4.model.GameState;
import org.jetbrains.annotations.NotNull;

public class ControllerConsole {

    private Game model;
    private TextView view;

    public ControllerConsole(@NotNull Game model, @NotNull TextView view) {
        this.model = model;
        this.view = view;
    }

    public void play() {
        this.view.displayTitle();
        this.model.start();
        this.model.updateScorePlayer(this.model.getCurrentPlayer(), this.model.getOppositePlayer());
        String mode = this.view.askMode();
        switch (mode) {
            case "Human vs Human" -> {
                System.out.println("Starting Human vs Human game...");
                while (this.model.getState() == GameState.PLAY) {
                    this.model.updateScorePlayer(this.model.getCurrentPlayer(),
                            this.model.getOppositePlayer());
                    this.view.displayScore();
                    if (!this.model.noMovePlayer()) {
                        this.view.displayBoard();
                        this.view.displayPlayer();
                        String command = this.view.askCommand();
                        switch (command) {
                            case "r" -> this.model.getComManages().redo();
                            case "u" -> this.model.getComManages().undo();
                            case "a" -> {
                                Position positionSelected;
                                do {
                                    positionSelected = this.view.askPosition();
                                } while (!this.model.isValidMove(positionSelected));
                                this.model.setMovePawn(positionSelected);
                            }
                        }
                    }
                    this.model.updateScorePlayer(this.model.getCurrentPlayer(),
                            this.model.getOppositePlayer());
                    if (this.model.isGameOver()) {
                        this.model.setState(GameState.GameOver);
                    }
                }
                this.view.displayWinner(this.model.isWinner());
            }
            case "Human vs Easy" -> {
                System.out.println("Starting Human vs Easy game...");
                while (this.model.getState() == GameState.PLAY) {
                    this.model.updateScorePlayer(this.model.getCurrentPlayer(),
                            this.model.getOppositePlayer());
                    this.view.displayScore();
                    if (!this.model.noMovePlayer()) {
                        this.view.displayBoard();
                        this.view.displayPlayer();
                        Position positionSelected;
                        do {
                            positionSelected = this.view.askPosition();
                        } while (!this.model.isValidMove(positionSelected));
                        this.model.setMovePawn(positionSelected);
                        this.model.capturePawns(positionSelected);
                    }
                    this.model.setCurrentPlayer();
                    this.model.IASimpleAction();
                    if (this.model.isGameOver()) {
                        this.model.setState(GameState.GameOver);
                    }
                }
                this.view.displayWinner(this.model.isWinner());
            }
            case "Human vs Hard" -> {
                System.out.println("Starting Human vs Hard game...");
                while (this.model.getState() == GameState.PLAY) {
                    this.model.updateScorePlayer(this.model.getCurrentPlayer(),
                            this.model.getOppositePlayer());
                    this.view.displayScore();
                    if (!this.model.noMovePlayer()) {
                        this.view.displayBoard();
                        this.view.displayPlayer();
                        Position positionSelected;
                        do {
                            positionSelected = this.view.askPosition();
                        } while (!this.model.isValidMove(positionSelected));
                        this.model.setMovePawn(positionSelected);
                        this.model.capturePawns(positionSelected);
                    }
                    this.model.setCurrentPlayer();
                    this.model.IAHardAction();
                    this.model.setCurrentPlayer();
                    if (this.model.isGameOver()) {
                        this.model.setState(GameState.GameOver);
                    }
                }
                this.view.displayWinner(this.model.isWinner());
            }
            default -> System.out.println("Invalid mode.");
        }
    }
}
