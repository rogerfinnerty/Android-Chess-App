package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

public class QueenTest extends TestCase {

    public void testCan_move() {
        Piece[][] board = new Piece[8][8];
        board[4][4] = new Queen("W");
        assertFalse(board[4][4].can_move(board,new Coordinates(4,4),new Coordinates(3,6)));
        assertTrue(board[4][4].can_move(board,new Coordinates(4,4),new Coordinates(2,6)));
    }
}