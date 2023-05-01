package com.example.group12project.ChessComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Knight extends Piece {

    public Knight(String p){
        super(p);
        this.type = "N";
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

        if(!((xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2))){
            return false;
        }

        return super.can_move(board, start, end, kingPos);
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end){
        if(!super.can_move(board, start, end)){
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        return (xdiff == 2 && ydiff == 1) || (xdiff == 1 && ydiff == 2);
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
