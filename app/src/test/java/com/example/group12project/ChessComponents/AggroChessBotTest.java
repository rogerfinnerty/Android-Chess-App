package com.example.group12project.ChessComponents;

import junit.framework.TestCase;

public class AggroChessBotTest extends TestCase {

    public void testGet_score() {
        Piece[][] board = new Piece[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = null;
            }
        }
        board[0][0] = new Queen("W");
        board[0][1] = new Rook("W");
        board[0][2] = new Knight("W");
        board[0][3] = new Bishop("W");
        board[0][4] = new Pawn("W");
        board[0][5] = new King("W");

        AggroChessBot aggrochess = new AggroChessBot();

        // testing all possibilities, including null spot
        assertEquals(9, aggrochess.get_score(board, new Coordinates(0,0)));
        assertEquals(5, aggrochess.get_score(board, new Coordinates(0,1)));
        assertEquals(3, aggrochess.get_score(board, new Coordinates(0,2)));
        assertEquals(3, aggrochess.get_score(board, new Coordinates(0,3)));
        assertEquals(1, aggrochess.get_score(board, new Coordinates(0,4)));
        assertEquals(0, aggrochess.get_score(board, new Coordinates(0,5)));
        assertEquals(0, aggrochess.get_score(board, new Coordinates(0,6)));
    }
}