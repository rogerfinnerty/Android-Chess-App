package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

public class RookTest extends TestCase {

    public void testCan_move() {
        Piece[][] board = new Piece[8][8];
        board[7][7] = new Rook("W");
        assertFalse(board[7][7].can_move(board,new Coordinates(7,7), new Coordinates(1,1)));
        assertTrue(board[7][7].can_move(board,new Coordinates(7,7), new Coordinates(7,3)));
    }
}