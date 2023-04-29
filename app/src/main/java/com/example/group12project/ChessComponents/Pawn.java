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

        int xdiff = (end.X() - start.X());
        int ydiff = (end.Y() - start.Y());

        // no horizontal movement
        if(ydiff != 0){
            return false;
        }

        if(Objects.equals(this.get_player(), "B")){
            if(firstMove){
                if(xdiff < -2 || xdiff > 0){
                    return false;
                }
            }
            else{
                if(xdiff < -1 || xdiff > 0){
                    return false;
                }
            }
        }
        else{
            if(firstMove){
                if(xdiff > 2 || xdiff < 0){
                    return false;
                }
            }
            else{
                if(xdiff > 1 || xdiff < 0){
                    return false;
                }
            }
        }
        /*
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
        */
        // check for obstruction
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.X()][i.Y()] != null){
                return false;
            }
        }
        return true;
    }

    public void madeFirstMove(){
        firstMove = false;
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

