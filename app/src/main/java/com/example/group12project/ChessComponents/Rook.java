package com.example.group12project.ChessComponents;

import java.util.*;

public class Rook extends Piece {

    public Rook(String p){
        super(p);
        this.set_type("R");
    }

    public boolean can_move(Board b, Place start, Place end){
        if(!super.can_move(b, start, end)){
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
        List<Place> between = b.places_between(start, end);
        for(Place i : between){
            if(i.get_piece() != null){
                return false;
            }
        }

        return true;
    }
}
