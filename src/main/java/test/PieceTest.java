package test;

import g58399.ATLIR4.designPattern.command.Command;
import g58399.ATLIR4.designPattern.command.CommandManages;
import g58399.ATLIR4.model.Color;
import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.model.Piece;
import g58399.ATLIR4.model.Position;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PieceTest {
    Stack<Command> undo = new Stack<Command>();
    Stack<Command> redo = new Stack<Command>();
    CommandManages manages = new CommandManages(undo, redo);
    Game game = new Game(this.manages);
    @org.junit.jupiter.api.Test
    void getPossibleMovesSautUneCaseAllDirBlack() {
        Position positionPossible1 = new Position(1,1);
        Position positionPossible2 = new Position(1,3);
        Position positionPossible3 = new Position(1,5);
        Position positionPossible4 = new Position(3,1);
        Position positionPossible5 = new Position(3,5);
        Position positionPossible6 = new Position(5,1);
        Position positionPossible7 = new Position(5,3);
        Position positionPossible8 = new Position(5,5);

        List<Position> possibleMovesCalculate = new ArrayList<>();

        possibleMovesCalculate.add(positionPossible1);
        possibleMovesCalculate.add(positionPossible2);
        possibleMovesCalculate.add(positionPossible3);
        possibleMovesCalculate.add(positionPossible4);
        possibleMovesCalculate.add(positionPossible5);
        possibleMovesCalculate.add(positionPossible6);
        possibleMovesCalculate.add(positionPossible7);
        possibleMovesCalculate.add(positionPossible8);

        List<Position> possibleMovesNormal;
        Position positionPieceBlack = new Position(3,3);
        Piece pawnBlack = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlack,positionPieceBlack);

        Position positionPieceNW = new Position(2,2);
        Piece pawnWhiteNW = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteNW,positionPieceNW);

        Position positionPieceN = new Position(2,3);
        Piece pawnWhiteN= new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteN, positionPieceN);

        Position positionPieceNE = new Position(2,4);
        Piece pawnWhiteNE = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteNE,positionPieceNE);

        Position positionPieceW = new Position(3,2);
        Piece pawnWhiteW = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteW, positionPieceW);

        Position positionPieceE = new Position(3,4);
        Piece pawnWhiteE = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteE, positionPieceE);

        Position positionPieceSW = new Position(4,2);
        Piece pawnWhiteSW = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteSW, positionPieceSW);

        Position positionPieceS = new Position(4,3);
        Piece pawnWhiteS = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteS, positionPieceS);

        Position positionPieceSE = new Position(4,4);
        Piece pawnWhiteSE = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteSE, positionPieceSE);

        possibleMovesNormal = game.getPiece(positionPieceBlack).getPossibleMoves(positionPieceBlack,game.getBoard(), Color.BLACK);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));

    }
    @org.junit.jupiter.api.Test
    void getPossibleMovesSautUneCaseAllDirWhite() {
        Position positionPossible1 = new Position(1,1);
        Position positionPossible2 = new Position(1,3);
        Position positionPossible3 = new Position(1,5);
        Position positionPossible4 = new Position(3,1);
        Position positionPossible5 = new Position(3,5);
        Position positionPossible6 = new Position(5,1);
        Position positionPossible7 = new Position(5,3);
        Position positionPossible8 = new Position(5,5);

        List<Position> possibleMovesCalculate = new ArrayList<>();

        possibleMovesCalculate.add(positionPossible1);
        possibleMovesCalculate.add(positionPossible2);
        possibleMovesCalculate.add(positionPossible3);
        possibleMovesCalculate.add(positionPossible4);
        possibleMovesCalculate.add(positionPossible5);
        possibleMovesCalculate.add(positionPossible6);
        possibleMovesCalculate.add(positionPossible7);
        possibleMovesCalculate.add(positionPossible8);

        List<Position> possibleMovesNormal;
        Position positionPieceWhite = new Position(3,3);
        Piece pawnWhite = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhite,positionPieceWhite);

        Position positionPieceNW = new Position(2,2);
        Piece pawnBlackNW = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackNW,positionPieceNW);

        Position positionPieceN = new Position(2,3);
        Piece pawnBlackN= new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackN, positionPieceN);

        Position positionPieceNE = new Position(2,4);
        Piece pawnBlackNE = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackNE,positionPieceNE);

        Position positionPieceW = new Position(3,2);
        Piece pawnBlackW = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackW, positionPieceW);

        Position positionPieceE = new Position(3,4);
        Piece pawnBlackE = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackE, positionPieceE);

        Position positionPieceSW = new Position(4,2);
        Piece pawnBlackSW = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackSW, positionPieceSW);

        Position positionPieceS = new Position(4,3);
        Piece pawnBlackS = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackS, positionPieceS);

        Position positionPieceSE = new Position(4,4);
        Piece pawnBlackSE = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlackSE, positionPieceSE);

        possibleMovesNormal = game.getPiece(positionPieceWhite).getPossibleMoves(positionPieceWhite,game.getBoard(), Color.WHITE);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautUneCaseFirstSituation() {
        game.start();
        Position positionPossible1 = new Position(2,3);
        Position positionPossible2 = new Position(4,5);
        List<Position> possibleMovesCalculate = new ArrayList<>();

        possibleMovesCalculate.add(positionPossible1);
        possibleMovesCalculate.add(positionPossible2);

        List<Position> possibleMovesNormal;
        Position positionPiece = new Position(4,3);
        possibleMovesNormal = game.getPiece(positionPiece).getPossibleMoves(positionPiece,game.getBoard(), Color.BLACK);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautUneCaseSecondSituation() {
        Position positionPossible1 = new Position(3,1);
        Position positionPossible2 = new Position(5,3);
        List<Position> possibleMovesCalculate = new ArrayList<>();

        possibleMovesCalculate.add(positionPossible1);
        possibleMovesCalculate.add(positionPossible2);

        List<Position> possibleMovesNormal;
        Position positionPieceBlack = new Position(3,3);
        Piece pawnBlack = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(pawnBlack,positionPieceBlack);

        Position positionPieceUnder = new Position(3,2);
        Piece pawnWhiteUnder = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteUnder,positionPieceUnder);

        Position positionPieceWest = new Position(4,3);
        Piece pawnWhiteWest= new Piece(Color.WHITE);
        this.game.getBoard().setPiece(pawnWhiteWest, positionPieceWest);

        possibleMovesNormal = this.game.getPiece(positionPieceBlack).getPossibleMoves(positionPieceBlack,this.game.getBoard(), Color.BLACK);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautDeuxCaseAllDirBlack() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.WHITE);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        for(int i = 0; i < 8; i++){
            this.game.getBoard().dropPiece(new Position(i,0));
            this.game.getBoard().dropPiece(new Position(i,6));
            this.game.getBoard().dropPiece(new Position(i,7));

            this.game.getBoard().dropPiece(new Position(0,i));
            this.game.getBoard().dropPiece(new Position(6,i));
            this.game.getBoard().dropPiece(new Position(7,i));
        }
        Position posCentral = new Position(3,3);
        this.game.getBoard().dropPiece(posCentral);
        Piece piece = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(piece,posCentral);
        Position possibleMoveOne = new Position(0,0);
        Position possibleMoveTwo = new Position(0,3);
        Position possibleMoveTree = new Position(0,6);
        Position possibleMoveFour = new Position(3,6);
        Position possibleMoveFive = new Position(3,0);
        Position possibleMoveSix = new Position(6,0);
        Position possibleMoveSeven = new Position(6,3);
        Position possibleMoveHeight = new Position(6,6);
        List<Position> possibleMovesCalculate = new ArrayList<>();
        List<Position> possibleMovesNormal = this.game.getPiece(posCentral).getPossibleMoves(posCentral, this.game.getBoard(),Color.BLACK);

        possibleMovesCalculate.add(possibleMoveOne);
        possibleMovesCalculate.add(possibleMoveTree);
        possibleMovesCalculate.add(possibleMoveTwo);
        possibleMovesCalculate.add(possibleMoveFour);
        possibleMovesCalculate.add(possibleMoveFive);
        possibleMovesCalculate.add(possibleMoveSix);
        possibleMovesCalculate.add(possibleMoveSeven);
        possibleMovesCalculate.add(possibleMoveHeight);


        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));

    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautDeuxCaseAllDirWhite() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.BLACK);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        for(int i = 0; i < 8; i++){
            this.game.getBoard().dropPiece(new Position(i,0));
            this.game.getBoard().dropPiece(new Position(i,6));
            this.game.getBoard().dropPiece(new Position(i,7));

            this.game.getBoard().dropPiece(new Position(0,i));
            this.game.getBoard().dropPiece(new Position(6,i));
            this.game.getBoard().dropPiece(new Position(7,i));
        }
        Position posCentral = new Position(3,3);
        this.game.getBoard().dropPiece(posCentral);
        Piece piece = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(piece,posCentral);
        Position possibleMoveOne = new Position(0,0);
        Position possibleMoveTwo = new Position(0,3);
        Position possibleMoveTree = new Position(0,6);
        Position possibleMoveFour = new Position(3,6);
        Position possibleMoveFive = new Position(3,0);
        Position possibleMoveSix = new Position(6,0);
        Position possibleMoveSeven = new Position(6,3);
        Position possibleMoveHeight = new Position(6,6);
        List<Position> possibleMovesCalculate = new ArrayList<>();
        List<Position> possibleMovesNormal = this.game.getPiece(posCentral).getPossibleMoves(posCentral, this.game.getBoard(),Color.WHITE);

        possibleMovesCalculate.add(possibleMoveOne);
        possibleMovesCalculate.add(possibleMoveTree);
        possibleMovesCalculate.add(possibleMoveTwo);
        possibleMovesCalculate.add(possibleMoveFour);
        possibleMovesCalculate.add(possibleMoveFive);
        possibleMovesCalculate.add(possibleMoveSix);
        possibleMovesCalculate.add(possibleMoveSeven);
        possibleMovesCalculate.add(possibleMoveHeight);


        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautTreeCaseAllDirBlack() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.WHITE);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        for(int i = 0; i < 8; i++){
            this.game.getBoard().dropPiece(new Position(i,7));
            this.game.getBoard().dropPiece(new Position(7,i));
        }
        Position posCentral = new Position(3,3);
        this.game.getBoard().dropPiece(posCentral);
        Piece piece = new Piece(Color.BLACK);
        this.game.getBoard().setPiece(piece,posCentral);
        Position possibleMoveOne = new Position(3,7);
        Position possibleMoveTwo = new Position(7,7);
        Position possibleMoveTree = new Position(7,3);
        List<Position> possibleMovesCalculate = new ArrayList<>();
        List<Position> possibleMovesNormal = this.game.getPiece(posCentral).getPossibleMoves(posCentral, this.game.getBoard(),Color.BLACK);

        possibleMovesCalculate.add(possibleMoveOne);
        possibleMovesCalculate.add(possibleMoveTree);
        possibleMovesCalculate.add(possibleMoveTwo);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossibleMovesSautTreeCaseAllDirWhite() {
        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                Piece piece = new Piece(Color.BLACK);
                this.game.getBoard().setPiece(piece, new Position(row, column));
            }
        }
        for(int i = 0; i < 8; i++){
            this.game.getBoard().dropPiece(new Position(i,7));
            this.game.getBoard().dropPiece(new Position(7,i));
        }
        Position posCentral = new Position(3,3);
        this.game.getBoard().dropPiece(posCentral);
        Piece piece = new Piece(Color.WHITE);
        this.game.getBoard().setPiece(piece,posCentral);
        Position possibleMoveOne = new Position(3,7);
        Position possibleMoveTwo = new Position(7,7);
        Position possibleMoveTree = new Position(7,3);
        List<Position> possibleMovesCalculate = new ArrayList<>();
        List<Position> possibleMovesNormal = this.game.getPiece(posCentral).getPossibleMoves(posCentral, this.game.getBoard(),Color.WHITE);

        possibleMovesCalculate.add(possibleMoveOne);
        possibleMovesCalculate.add(possibleMoveTree);
        possibleMovesCalculate.add(possibleMoveTwo);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }
    @org.junit.jupiter.api.Test
    void nothingPositionPossible() {
        Piece piece = new Piece(Color.BLACK);
        Position posCentral =new Position(3,3);
        this.game.getBoard().setPiece(piece,posCentral );
        List<Position> possibleMovesCalculate = new ArrayList<>();
        List<Position> possibleMovesNormal = this.game.getPiece(posCentral).getPossibleMoves(posCentral, this.game.getBoard(),Color.BLACK);

        Assertions.assertEquals(possibleMovesCalculate.size(),possibleMovesNormal.size());
        Assertions.assertTrue(possibleMovesCalculate.containsAll(possibleMovesNormal));
    }

    @org.junit.jupiter.api.Test
    void getPossiblesCaptures() {

    }
}