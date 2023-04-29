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
        Piece s = board[start.get_x()][start.get_y()];      // get start piece
        Piece e = board[end.get_x()][end.get_y()];          // if end isn't null, get piece
        // check first to make sure start and end coordinates are not equal
        if((start.get_x() == end.get_x()) && (start.get_y() == end.get_y())){
            return false;
        }
        // check if starting Piece is null, and check if starting player is same as ending player (cannot take own piece)
        else return s == null || !Objects.equals(s.get_player(), e.get_player());
    }

    public List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start) {
        List<Coordinates> poss = new ArrayList<Coordinates>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Coordinates temp = new Coordinates(i,j);
                if(can_move(board, start, temp)){
                    poss.add(temp);
                }
            }
        }
        return poss;
    }

}
