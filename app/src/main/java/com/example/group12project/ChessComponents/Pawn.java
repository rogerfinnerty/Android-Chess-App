package com.example.group12project.ChessComponents;

import java.util.*;
import java.lang.Math;

public class Pawn extends Piece {

    public boolean enPassantable = false;

    public Pawn(String p){
        super(p);
        this.type = "P";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end, Coordinates kingPos){
        if(!super.can_move(board, start, end, kingPos)){
            return false;
        }

        int xdiff = (end.X() - start.X());
        int ydiff = (end.Y() - start.Y());

        // no horizontal movement, unless capturing
        if(ydiff != 0){
            if (Math.abs(ydiff) > 1 || Math.abs(xdiff) != 1)
                return false;
            if (board[end.X()][end.Y()] == null) {
                if (board[start.X()][end.Y()] == null)
                    return false;
                if (Objects.equals(this.get_player(), board[start.X()][end.Y()].get_player()))
                    return false;
                if (!((Pawn) board[start.X()][end.Y()]).enPassantable)
                    return false;
            }
            else if (Objects.equals(this.get_player(), board[end.X()][end.Y()].get_player()))
                    return false;

        }

        if(Objects.equals(this.get_player(), "B")){
            if(start.X() == 6){
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
            if(start.X() == 1){
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

        // cannot capture vertically
        if (board[end.X()][end.Y()] != null && ydiff == 0)
            return false;

        // check for obstruction
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

        int xdiff = (end.X() - start.X());
        int ydiff = (end.Y() - start.Y());

        // no horizontal movement, unless capturing
        if(ydiff != 0){
            if (Math.abs(ydiff) > 1 || Math.abs(xdiff) != 1)
                return false;
            if (board[end.X()][end.Y()] == null)
                return false;
            if (Objects.equals(this.get_player(), board[end.X()][end.Y()].get_player()))
                return false;
            // en passant
        }

        if(Objects.equals(this.get_player(), "B")){
            if(start.X() == 6){
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
            if(start.X() == 1){
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

        // cannot capture vertically
        if (board[end.X()][end.Y()] != null && ydiff == 0)
            return false;

        // check for obstruction
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

