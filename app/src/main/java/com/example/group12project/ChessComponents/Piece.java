package com.example.group12project.ChessComponents;

import java.util.*;

public abstract class Piece {

    private final String player;
    protected String type;

    public Piece(String pl){
        player = pl;
    }

    public String get_player(){
        return player;
    }

    public String get_type(){
        return type;
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        Piece s = board[start.X()][start.Y()];      // get start piece
        Piece e = board[end.X()][end.Y()];          // if end isn't null, get piece
        // check first to make sure start and end coordinates are not equal
        if((start.X() == end.X()) && (start.Y() == end.Y())){
            return false;
        }
        // check if starting Piece is null, and check if starting player is same as ending player (cannot take own piece)
        if(e == null){
            return (s != null);
        }
        else return s != null && !Objects.equals(s.get_player(), e.get_player());
    }

    public abstract List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start);
}
