package com.example.group12project.ChessComponents;

public class Knight extends Piece {

    public Knight(String p){
        super(p);
        this.set_type("N");
    }

    public boolean can_move(Board b, Place start, Place end){
        if(!super.can_move(b, start, end)){
            return false;
        }

        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        if((xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2)){
            return true;
        }
        else{
            return false;
        }
    }

}
