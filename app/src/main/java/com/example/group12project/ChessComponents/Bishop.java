package com.example.group12project.ChessComponents;

import java.util.*;

public class Bishop extends Piece {

    public Bishop(String p){
        super(p);
        this.type = "B";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end, Coordinates kingPos){
        Piece s = board[start.X()][start.Y()];      // get start piece
        Piece e = board[end.X()][end.Y()];          // if end isn't null, get piece

        if(s==null){    // if there is no piece
            return false;
        }

        if(e != null && (Objects.equals(s.get_player(), e.get_player()))){
            // if end piece is same as start piece (can't take own piece)
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());
        // check for diagonal movement or no movement
        if((xdiff != ydiff)){
            return false;
        }

        // check for preconditions + pinning
        if(!super.can_move(board, start, end, kingPos)){
            return false;
        }

        // check for obstruction
        // 1. get list of all spaces between start and end
        // 2. check each to see if there is a piece, if so, cannot move to that spot
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.X()][i.Y()] != null){
                return false;
            }
        }
        return true;
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        // check for diagonal movement or no movement
        if(xdiff != ydiff){
            return false;
        }

        // check for obstruction
        // 1. get list of all spaces between start and end
        // 2. check each to see if there is a piece, if so, cannot move to that spot
        List<Coordinates> between = Coordinates.places_between(start, end);
        for(Coordinates i : between){
            if(board[i.X()][i.Y()] != null){
                return false;
            }
        }
        return true;
    }

    public List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start, Coordinates kingPos) {
        List<Coordinates> poss = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Coordinates temp = new Coordinates(i,j);
                if(can_move(board, start, temp, kingPos)){
                    poss.add(temp);
                }
            }
        }
        return poss;
    }

    public List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start) {
        List<Coordinates> poss = new ArrayList<>();
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