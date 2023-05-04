package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

public class BishopTest extends TestCase {

    public void testCan_move() {
        Piece[][] board = new Piece[8][8];
        board[1][2] = new Bishop("W");
        assertFalse(board[1][2].can_move(board,new Coordinates(1,2),new Coordinates(3,3)));
        assertTrue(board[1][2].can_move(board,new Coordinates(1,2),new Coordinates(6,7)));
    }
}