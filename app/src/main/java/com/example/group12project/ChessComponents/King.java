package com.example.group12project.ChessComponents;

import android.util.Log;

import com.example.group12project.Chessboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {

    public boolean hasMoved = false;

    public King(String p){
        super(p);
        this.type = "K";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end, Coordinates kingPos) {
        if (!super.can_move(board, start, end, kingPos)) {
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        // cannot move more than one square unless castling
        if (xdiff > 1 || ydiff > 1) {
            if (hasMoved)
                return false;
            if (end.X() != start.X())
                return false;
            if (ydiff != 2)
                return false;
            if (end.Y() == 6) {
                Piece p = board[start.X()][7];
                if (!(p instanceof Rook))
                    return false;
                if (((Rook) p).hasMoved)
                    return false;
            }
            if (end.Y() == 2) {
                Piece p = board[start.X()][0];
                if (!(p instanceof Rook))
                    return false;
                if (((Rook) p).hasMoved)
                    return false;
                if (board[start.X()][1] != null)
                    return false;
            }
        }


        return !this.kingInCheck(board, end);
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end) {
        if (!super.can_move(board, start, end)) {
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        // cannot move more than one square unless castling
        if (xdiff > 1 || ydiff > 1) {
            if (hasMoved)
                return false;
            if (end.X() != start.X())
                return false;
            if (ydiff != 2)
                return false;
            if (end.Y() == 6) {
                Piece p = board[start.X()][7];
                if (!(p instanceof Rook))
                    return false;
                if (((Rook) p).hasMoved)
                    return false;
            }
            if (end.Y() == 2) {
                Piece p = board[start.X()][0];
                if (!(p instanceof Rook))
                    return false;
                if (((Rook) p).hasMoved)
                    return false;
                if (board[start.X()][1] != null)
                    return false;
            }
        }

        //pretend like king is not there, check if in checkmate?

        return !this.kingInCheck(board, end);
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


    public boolean kingInCheck(Piece[][] chessboard, Coordinates king_square){
        // checks all spots, all pieces to see if they can attack the King
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] != null && (!Objects.equals(chessboard[i][j].get_player(), this.get_player())) && !Objects.equals(chessboard[i][j].get_type(), "K")) { // check if piece is null, then check if its color is same as king
                    if(chessboard[i][j].can_move(chessboard, new Coordinates(i, j), king_square)){
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public Coordinates checkByWho(Piece[][] chessboard, Coordinates king_square){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] != null && (!Objects.equals(chessboard[i][j].get_player(), chessboard[king_square.X()][king_square.Y()].get_player())) && !Objects.equals(chessboard[i][j].get_type(), "K")) { // check if piece is null, then check if its color is same as king
                    //get list of possible moves of non-same color piece at i,j
                    //chessboard[i][j].print_piece();
                    if(chessboard[i][j].can_move(chessboard, new Coordinates(i, j), king_square)){
                        return new Coordinates(i,j);
                    }
                }
            }
        }

        return null;
    }
}
