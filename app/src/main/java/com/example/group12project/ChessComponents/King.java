package com.example.group12project.ChessComponents;

import com.example.group12project.Chessboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {

    public King(String p){
        super(p);
        this.type = "K";
    }

    public boolean can_move(Piece[][] board, Coordinates start, Coordinates end) {
        if (!super.can_move(board, start, end)) {
            return false;
        }

        int xdiff = Math.abs(end.X() - start.X());
        int ydiff = Math.abs(end.Y() - start.Y());

        if(!((xdiff==1 && ydiff==0) ||
                (xdiff==0 && ydiff==1) ||
                (xdiff==1 && ydiff==1))){
            return false;
        }
        Chessboard.print_board();
        return !kingInCheck(board, end);
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


    public boolean kingInCheck(Piece[][] chessboard, Coordinates king_square){
        // checks all spots, all pieces to see if they can attack the King
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] != null) {
                    if (!(Objects.equals(chessboard[i][j].get_type(), this.get_type()) && chessboard[i][j] instanceof King)) {
                        List<Coordinates> possibleMoves = chessboard[i][j].allPossibleMoves(chessboard, new Coordinates(i, j));
                        for (Coordinates c : possibleMoves) {
                            if (c == king_square) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}