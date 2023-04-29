package com.example.group12project.ChessComponents;

import com.example.group12project.Chessboard;

import java.util.ArrayList;
import java.util.List;

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