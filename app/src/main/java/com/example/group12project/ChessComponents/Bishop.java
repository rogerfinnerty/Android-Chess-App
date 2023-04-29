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

        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        // check for diagonal movement or no movement
        if(xdiff != ydiff && xdiff != 0){
            return false;
        }

        // check for obstruction
        // 1. get list of all spaces between start and end
        // 2. check each to see if there is a piece, if so, cannot move to that spot
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.get_x()][i.get_y()] != null){
                return false;
            }
        }
        return true;
    }

}