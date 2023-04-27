package com.example.group12project.ChessComponents;

import java.util.*;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(String p){
        super(p);
        this.set_type("P");
    }

    public boolean can_move(Board b, Place start, Place end){
        if(!super.can_move(b, start, end)){
            return false;
        }

        int xdiff = (end.get_x() - start.get_x());
        int ydiff = (end.get_y() - start.get_y());

        // no horizontal movement
        if(ydiff != 0){
            return false;
        }

        // checking for movement down or up
        if(this.get_player() == "W"){
            xdiff *= -1; // since going down
        }

        if(!firstMove){
            if(xdiff > 1){
                return false;
            }
        }

        if(firstMove){
            if(xdiff > 2){
                return false;
            }
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

