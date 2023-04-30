package com.example.group12project.ChessComponents;

import android.util.Log;

import java.util.*;

public abstract class Piece {

    private final String player;
    protected String type;

    public Piece(String pl){
        player = pl;
    }

    public String get_player(){
        return player;
    }

    public String get_type(){
        return type;
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end, Coordinates kingPos){
        Piece s = board[start.X()][start.Y()];      // get start piece
        Piece e = board[end.X()][end.Y()];          // if end isn't null, get piece
        // check first to make sure start and end coordinates are not equal
        if((start.X() == end.X()) && (start.Y() == end.Y())){
            return false;
        }

        if(e == null){
            if(s!=null){
                if(!(this instanceof King)){
                    Coordinates pinChecker = isPinned(board, kingPos, start);
                    if(pinChecker != null) {
                        //if pinned, can only move along axis of checker
                        if (end.equals(pinChecker)) {
                            return true;
                        }
                        List<Coordinates> bet = Coordinates.places_between(kingPos, pinChecker);
                        for (Coordinates b : bet) {
                            if (!b.equals(start) && b.equals(end)) {
                                return true;    // if end pos is inline, then we can move
                            }
                        }
                        return false; // else we are pinned and cannot move to end
                    }
                }
            }
        }
        else if(s == null || Objects.equals(s.get_player(), e.get_player())){
            // check if starting Piece is null, and check if starting player is same as ending player (cannot take own piece)
            return false;
        }
        else{
            //checking if pinned
            if(!(this instanceof King)){
                Coordinates pinChecker = isPinned(board, kingPos, start);
                if(pinChecker != null){
                    //if pinned, can only move along axis of checker
                    if(end.equals(pinChecker)){
                        return true;
                    }
                    List<Coordinates> bet = Coordinates.places_between(kingPos, pinChecker);
                    for(Coordinates b : bet){
                        if(!b.equals(start) && b.equals(end)){
                            return true;    // if end pos is inline, then we can move
                        }
                    }
                    return false; // else we are pinned and cannot move to end
                }
            }

        }
        return true;
    }


    // without checking for pinning
    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        Piece s = board[start.X()][start.Y()];      // get start piece
        Piece e = board[end.X()][end.Y()];          // if end isn't null, get piece
        // check first to make sure start and end coordinates are not equal
        if((start.X() == end.X()) && (start.Y() == end.Y())){
            return false;
        }

        // check if starting Piece is null, and check if starting player is same as ending player (cannot take own piece)
        if(e == null){
            return (s != null);
        }
        else return s != null && !Objects.equals(s.get_player(), e.get_player());
    }

    public abstract List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start, Coordinates kingPos);
    public abstract List<Coordinates> allPossibleMoves(Piece[][] board, Coordinates start);

    public Coordinates isPinned(Piece[][] board, Coordinates kingPos, Coordinates currPos){
        if(board[currPos.X()][currPos.Y()] instanceof King || kingPos.equals(currPos)){
            return null;
        }
        if(board[kingPos.X()][kingPos.Y()] == null || !(board[kingPos.X()][kingPos.Y()] instanceof King)){
            return null;
        }
        board[currPos.X()][currPos.Y()] = null;
        Coordinates checker = ((King)board[kingPos.X()][kingPos.Y()]).checkByWho(board, kingPos);
        board[currPos.X()][currPos.Y()] = this;
        return checker;
    }

    public void print_piece(){
        Log.d("Piece:", get_type() + get_player());
    }
}
