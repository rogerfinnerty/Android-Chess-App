package com.example.group12project.ChessComponents;

import java.util.*;

public class Queen extends Piece{

    public Queen(String p){
        super(p);
        this.set_type("Q");
    }

    public boolean can_move(Board b, Place start, Place end){
        if(!super.can_move(b, start, end)){
            return false;
        }

        // NEED TO ADD CHECKS FOR DIAGONAL OR HORIZONTAL/VERTICAL

        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        // check for obstruction
        List<Place> between = b.places_between(start, end);
        for(Place i : between){
            if(i.get_piece() != null){
                return false;
            }
        }

        return true;
    }

}