package com.example.group12project.ChessComponents;

public class Knight extends Piece {

    public Knight(String p){
        super(p);
        this.type = "N";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        return (xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2);
    }

}
