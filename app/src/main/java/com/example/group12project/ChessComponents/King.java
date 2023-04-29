package com.example.group12project.ChessComponents;

import com.example.group12project.Chessboard;

public class King extends Piece {

    public King(String p){
        super(p);
        this.type = "K";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end) {
        if (!super.can_move(board, start, end)) {
            return false;
        }

        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        if(!((xdiff==1 && ydiff==0) ||
                (xdiff==0 && ydiff==1) ||
                (xdiff==1 && ydiff==1))){
            return false;
        }

        return !Chessboard.kingInCheck(end);
    }

}