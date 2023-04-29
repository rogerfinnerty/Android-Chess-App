package com.example.group12project.ChessComponents;

import java.util.*;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(String p){
        super(p);
        this.type = "P";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        int xdiff = (end.get_x() - start.get_x());
        int ydiff = (end.get_y() - start.get_y());

        // no horizontal movement
        if(ydiff != 0){
            return false;
        }

        // checking for movement down or up
        if(Objects.equals(this.get_player(), "W")){
            xdiff *= -1; // since going down
        }

        if(!firstMove){
            if(xdiff > 1){return false;}
        }
        else{
            if(xdiff > 2){return false;}
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

    public void madeFirstMove(){
        firstMove = false;
    }
}

