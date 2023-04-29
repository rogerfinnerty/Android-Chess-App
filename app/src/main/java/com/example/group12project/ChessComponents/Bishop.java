package com.example.group12project.ChessComponents;

import java.util.*;

public class Bishop extends Piece {

    public Bishop(String p){
        super(p);
        this.type = "B";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        // check for diagonal movement or no movement
        if(xdiff != ydiff && xdiff != 0){
            return false;
        }

        // check for obstruction
        // 1. get list of all spaces between start and end
        // 2. check each to see if there is a piece, if so, cannot move to that spot
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