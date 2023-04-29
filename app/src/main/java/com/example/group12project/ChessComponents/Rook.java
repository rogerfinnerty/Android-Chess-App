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
        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        // check for non-rook movement or no movement at all
        if((xdiff != 0 && ydiff != 0)){
            return false;
        }

        // check for obstruction
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.get_x()][i.get_y()] != null){
                return false;
            }
        }

        return true;
    }
}
