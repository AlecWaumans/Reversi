package test;

import g58399.ATLIR4.model.Board;
import g58399.ATLIR4.model.Position;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = new Board();

    @org.junit.jupiter.api.Test
    void containsTestOutsideRow() {
        Position posOutsideRow = new Position(10, 0);
        boolean valueValuate = board.contains(posOutsideRow);
        assertFalse(valueValuate);
    }
    @org.junit.jupiter.api.Test
    void containsTestOutsideColumn() {
        Position posOutsideColumn = new Position(5, 9);
        boolean valueValuate = board.contains(posOutsideColumn);
        assertFalse(valueValuate);
    }

    @org.junit.jupiter.api.Test
    void containsTestSideOfBoard() {
        Position posOutsideRow = new Position(0, 7);
        boolean valueValuate = board.contains(posOutsideRow);
        assertTrue(valueValuate);
    }
    @org.junit.jupiter.api.Test
    void containsTestSideOfBoardSecond() {
        Position posOutsideRow = new Position(7, 0);
        boolean valueValuate = board.contains(posOutsideRow);
        assertTrue(valueValuate);
    }


}