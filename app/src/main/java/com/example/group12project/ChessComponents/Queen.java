package com.example.group12project.ChessComponents;

import java.util.*;

public class Queen extends Piece{

    public Queen(String p){
        super(p);
        this.type = "Q";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        // NEED TO ADD CHECKS FOR DIAGONAL OR HORIZONTAL/VERTICAL
        int xdiff = Math.abs(end.get_x() - start.get_x());
        int ydiff = Math.abs(end.get_y() - start.get_y());

        if(xdiff != ydiff){
            if(xdiff != 0 && ydiff != 0){
                return false;
            }
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