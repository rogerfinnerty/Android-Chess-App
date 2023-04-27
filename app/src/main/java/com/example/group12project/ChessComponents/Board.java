package com.example.group12project.ChessComponents;

import java.util.*;


public class Board {


    int ROWS = 8;
    int COLUMNS = 8;
    Place[][] chessboard = new Place[8][8];
    public Board(){
        this.make_board();
    }

    public void make_board(){
        // this method will have to also create Places on board as they are made
        System.out.println("Making the board");
        // BLANK PIECES
        for(int i = 2; i < ROWS-2; i++){
            for(int j = 0; j < COLUMNS; j++){
                chessboard[i][j] = new Place(null, i, j);
            }
        }

        // WHITE PIECES
        chessboard[0][0] = new Place(new Rook("W"), 0, 0);
        chessboard[0][1] = new Place(new Knight("W"), 0, 1);
        chessboard[0][2] = new Place(new Bishop("W"), 0, 2);
        chessboard[0][3] = new Place(new Queen("W"), 0, 3);
        chessboard[0][4] = new Place(new King("W"), 0, 4);
        chessboard[0][5] = new Place(new Bishop("W"), 0, 5);
        chessboard[0][6] = new Place(new Knight("W"), 0, 6);
        chessboard[0][7] = new Place(new Rook("W"), 0, 7);

        chessboard[1][0] = new Place(new Pawn("W"), 1, 0);
        chessboard[1][1] = new Place(new Pawn("W"), 1, 1);
        chessboard[1][2] = new Place(new Pawn("W"), 1, 2);
        chessboard[1][3] = new Place(new Pawn("W"), 1, 3);
        chessboard[1][4] = new Place(new Pawn("W"), 1, 4);
        chessboard[1][5] = new Place(new Pawn("W"), 1, 5);
        chessboard[1][6] = new Place(new Pawn("W"), 1, 6);
        chessboard[1][7] = new Place(new Pawn("W"), 1, 7);

        // BLACK PIECES
        chessboard[7][0] = new Place(new Rook("B"), 7, 0);
        chessboard[7][1] = new Place(new Knight("B"), 7, 1);
        chessboard[7][2] = new Place(new Bishop("B"), 7, 2);
        chessboard[7][3] = new Place(new Queen("B"), 7, 3);
        chessboard[7][4] = new Place(new King("B"), 7, 4);
        chessboard[7][5] = new Place(new Bishop("B"), 7, 5);
        chessboard[7][6] = new Place(new Knight("B"), 7, 6);
        chessboard[7][7] = new Place(new Rook("B"), 7, 7);

        chessboard[6][0] = new Place(new Pawn("B"), 6, 0);
        chessboard[6][1] = new Place(new Pawn("B"), 6, 1);
        chessboard[6][2] = new Place(new Pawn("B"), 6, 2);
        chessboard[6][3] = new Place(new Pawn("B"), 6, 3);
        chessboard[6][4] = new Place(new Pawn("B"), 6, 4);
        chessboard[6][5] = new Place(new Pawn("B"), 6, 5);
        chessboard[6][6] = new Place(new Pawn("B"), 6, 6);
        chessboard[6][7] = new Place(new Pawn("B"), 6, 7);
    }

    public Place get_place(int x, int y){
        return this.chessboard[x][y];
    }

    public void move(Place start, Place end){
        // need to add TAKE
        chessboard[end.get_x()][end.get_y()].set_piece(start.get_piece());
        chessboard[start.get_x()][start.get_y()].set_piece(null);
    }

    public void print_board(){
        for (int row = 0; row < ROWS; row++){
            System.out.println("");
            System.out.println("---------------------------------");
            for (int column = 0; column < COLUMNS; column++)
            {
                if(chessboard[row][column].get_piece() != null){
                    System.out.print("| " + chessboard[row][column].get_piece().get_type() + " ");
                }
                else{
                    System.out.print("| " + " " + " ");
                }
            }
            System.out.print("|");
        }
        System.out.println("");
        System.out.println("---------------------------------");
    }

    public List<Place> places_between(Place start, Place end){
        // we already know that start != end
        List<Place> list = new LinkedList<Place>();

        int xdiff = end.get_x() - start.get_x();
        int ydiff = end.get_y() - start.get_y();

        //checking for positive y movement
        if(xdiff == 0 && ydiff > 0){
            for(int i = 1; i < ydiff; i++){
                list.add(chessboard[start.get_x()][start.get_y()+i]);
            }
        }

        //checking for negative y movement
        if(xdiff == 0 && ydiff < 0){
            for(int i = 1; i < Math.abs(ydiff); i++){
                list.add(chessboard[start.get_x()][start.get_y()-i]);
            }
        }

        //checking for positive x movement
        if(ydiff == 0 && xdiff > 0){
            for(int i = 1; i < xdiff; i++){
                list.add(chessboard[start.get_x()+i][start.get_y()]);
            }
        }

        //checking for negative x movement
        if(ydiff == 0 && xdiff < 0){
            for(int i = 1; i < Math.abs(xdiff); i++){
                list.add(chessboard[start.get_x()-i][start.get_y()]);
            }
        }

        //checking for bottom right movement
        if(ydiff > 0 && xdiff > 0 && (Math.abs(xdiff) == Math.abs(ydiff))){
            for(int i = 1; i < Math.abs(xdiff); i++){
                list.add(chessboard[start.get_x()+i][start.get_y()+i]);
            }
        }

        //checking for bottom left movement
        if(ydiff < 0 && xdiff > 0 && (Math.abs(xdiff) == Math.abs(ydiff))){
            for(int i = 1; i < Math.abs(xdiff); i++){
                list.add(chessboard[start.get_x()+i][start.get_y()-i]);
            }
        }

        //checking for top right movement
        if(ydiff > 0 && xdiff < 0 && (Math.abs(xdiff) == Math.abs(ydiff))){
            for(int i = 1; i < Math.abs(xdiff); i++){
                list.add(chessboard[start.get_x()-i][start.get_y()+i]);
            }
        }

        //checking for top left movement
        if(ydiff < 0 && xdiff < 0 && (Math.abs(xdiff) == Math.abs(ydiff))){
            for(int i = 1; i < Math.abs(xdiff); i++){
                list.add(chessboard[start.get_x()-i][start.get_y()-i]);
            }
        }

        return list;
    }

    public static void main(String[] args){
        Board b = new Board();

        b.move(b.get_place(0,1), b.get_place(2, 0));
        b.move(b.get_place(6,4), b.get_place(5, 4));
        //b.move(b.get_place(0,1), b.get_place(2, 0));
        //b.move(b.get_place(0,1), b.get_place(2, 0));
        b.print_board();

        System.out.println(b.get_place(1, 4).get_piece().can_move(b, b.get_place(1, 4), b.get_place(3, 4)));
    }
}

