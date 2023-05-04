package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

import java.util.List;

public class KingTest extends TestCase {
    public void testCan_move() {
        Piece[][] board = new Piece[8][8];
        board[2][3] = new King("W");
        assertFalse(board[2][3].can_move(board,new Coordinates(2,3), new Coordinates(3,5)));
        assertTrue(board[2][3].can_move(board,new Coordinates(2,3), new Coordinates(2,4)));
    }
}