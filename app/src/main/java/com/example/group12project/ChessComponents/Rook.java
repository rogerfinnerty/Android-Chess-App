package com.example.group12project.ChessComponents;

import java.util.*;

public class Rook extends Piece {

    public Rook(String p){
        super(p);
        this.type = "R";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }
        // find change in piece placement
        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        // check for non-rook movement or no movement at all
        if((xdiff != 0 && ydiff != 0)){
            return false;
        }

        // check for obstruction
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.X()][i.Y()] != null){
                return false;
            }
        }

        return true;
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
