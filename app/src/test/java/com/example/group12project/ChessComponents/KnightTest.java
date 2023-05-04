package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

public class KnightTest extends TestCase {

    public void testCan_move() {
        Piece[][] board = new Piece[8][8];
        board[7][7] = new Knight("W");
        assertFalse(board[7][7].can_move(board,new Coordinates(7,7), new Coordinates(6,6)));
        assertTrue(board[7][7].can_move(board,new Coordinates(7,7), new Coordinates(5,6)));
    }
}