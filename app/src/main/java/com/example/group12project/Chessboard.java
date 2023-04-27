package com.example.group12project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TextView;


import com.example.group12project.ChessComponents.*;

import java.util.Objects;

public class Chessboard extends AppCompatActivity implements View.OnClickListener {
    final int ROWS = 8;
    final int COLUMNS = 8;

    Place chessboard[][] = new Place[ROWS][COLUMNS];
    View chessboard_image[][] = new View[ROWS][COLUMNS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessboard);

        startBoard();

        move(0,1,0,3);
        update_board();
    }

    public void startBoard(){
        Queen wQueen = new Queen("W");
        King wKing = new King("W");
        Rook wRook1 = new Rook("W");
        Rook wRook2 = new Rook("W");
        Bishop wBish1 = new Bishop("W");
        Bishop wBish2 = new Bishop("W");
        Knight wKni1 = new Knight("W");
        Knight wKni2 = new Knight("W");
        Pawn wPawn1 = new Pawn("W");
        Pawn wPawn2 = new Pawn("W");
        Pawn wPawn3 = new Pawn("W");
        Pawn wPawn4 = new Pawn("W");
        Pawn wPawn5 = new Pawn("W");
        Pawn wPawn6 = new Pawn("W");
        Pawn wPawn7 = new Pawn("W");
        Pawn wPawn8 = new Pawn("W");

        Queen bQueen = new Queen("B");
        King bKing = new King("B");
        Rook bRook1 = new Rook("B");
        Rook bRook2 = new Rook("B");
        Bishop bBish1 = new Bishop("B");
        Bishop bBish2 = new Bishop("B");
        Knight bKni1 = new Knight("B");
        Knight bKni2 = new Knight("B");
        Pawn bPawn1 = new Pawn("B");
        Pawn bPawn2 = new Pawn("B");
        Pawn bPawn3 = new Pawn("B");
        Pawn bPawn4 = new Pawn("B");
        Pawn bPawn5 = new Pawn("B");
        Pawn bPawn6 = new Pawn("B");
        Pawn bPawn7 = new Pawn("B");
        Pawn bPawn8 = new Pawn("B");

        // WHITE PIECES
        chessboard[0][0] = new Place(wRook1, 0, 0);
        chessboard_image[0][0] = (TextView) findViewById(R.id.i00);
        chessboard[0][1] = new Place(wKni1, 0, 1);
        chessboard_image[0][1] = (TextView) findViewById(R.id.i01);
        chessboard[0][2] = new Place(wBish1, 0, 2);
        chessboard_image[0][2] = (TextView) findViewById(R.id.i02);
        chessboard[0][3] = new Place(wQueen, 0, 3);
        chessboard_image[0][3] = (TextView) findViewById(R.id.i03);
        chessboard[0][4] = new Place(wKing, 0, 4);
        chessboard_image[0][4] = (TextView) findViewById(R.id.i04);
        chessboard[0][5] = new Place(wBish2, 0, 5);
        chessboard_image[0][5] = (TextView) findViewById(R.id.i05);
        chessboard[0][6] = new Place(wKni2, 0, 6);
        chessboard_image[0][6] = (TextView) findViewById(R.id.i06);
        chessboard[0][7] = new Place(wRook2, 0, 7);
        chessboard_image[0][7] = (TextView) findViewById(R.id.i07);

        chessboard[1][0] = new Place(wPawn1, 1, 0);
        chessboard_image[1][0] = (TextView) findViewById(R.id.i10);
        chessboard[1][1] = new Place(wPawn2, 1, 1);
        chessboard_image[1][1] = (TextView) findViewById(R.id.i11);
        chessboard[1][2] = new Place(wPawn3, 1, 2);
        chessboard_image[1][2] = (TextView) findViewById(R.id.i12);
        chessboard[1][3] = new Place(wPawn4, 1, 3);
        chessboard_image[1][3] = (TextView) findViewById(R.id.i13);
        chessboard[1][4] = new Place(wPawn5, 1, 4);
        chessboard_image[1][4] = (TextView) findViewById(R.id.i14);
        chessboard[1][5] = new Place(wPawn6, 1, 5);
        chessboard_image[1][5] = (TextView) findViewById(R.id.i15);
        chessboard[1][6] = new Place(wPawn7, 1, 6);
        chessboard_image[1][6] = (TextView) findViewById(R.id.i16);
        chessboard[1][7] = new Place(wPawn8, 1, 7);
        chessboard_image[1][7] = (TextView) findViewById(R.id.i17);

        // BLACK PIECES
        chessboard[7][0] = new Place(bRook1, 7, 0);
        chessboard_image[7][0] = (TextView) findViewById(R.id.i70);
        chessboard[7][1] = new Place(bKni1, 7, 1);
        chessboard_image[7][1] = (TextView) findViewById(R.id.i71);
        chessboard[7][2] = new Place(bBish1, 7, 2);
        chessboard_image[7][2] = (TextView) findViewById(R.id.i72);
        chessboard[7][3] = new Place(bQueen, 7, 3);
        chessboard_image[7][3] = (TextView) findViewById(R.id.i73);
        chessboard[7][4] = new Place(bKing, 7, 4);
        chessboard_image[7][4] = (TextView) findViewById(R.id.i74);
        chessboard[7][5] = new Place(bBish2, 7, 5);
        chessboard_image[7][5] = (TextView) findViewById(R.id.i75);
        chessboard[7][6] = new Place(bKni2, 7, 6);
        chessboard_image[7][6] = (TextView) findViewById(R.id.i76);
        chessboard[7][7] = new Place(bRook2, 7, 7);
        chessboard_image[7][7] = (TextView) findViewById(R.id.i77);

        chessboard[6][0] = new Place(bPawn1, 6, 0);
        chessboard_image[6][0] = (TextView) findViewById(R.id.i60);
        chessboard[6][1] = new Place(bPawn2, 6, 1);
        chessboard_image[6][1] = (TextView) findViewById(R.id.i61);
        chessboard[6][2] = new Place(bPawn3, 6, 2);
        chessboard_image[6][2] = (TextView) findViewById(R.id.i62);
        chessboard[6][3] = new Place(bPawn4, 6, 3);
        chessboard_image[6][3] = (TextView) findViewById(R.id.i63);
        chessboard[6][4] = new Place(bPawn5, 6, 4);
        chessboard_image[6][4] = (TextView) findViewById(R.id.i64);
        chessboard[6][5] = new Place(bPawn6, 6, 5);
        chessboard_image[6][5] = (TextView) findViewById(R.id.i65);
        chessboard[6][6] = new Place(bPawn7, 6, 6);
        chessboard_image[6][6] = (TextView) findViewById(R.id.i66);
        chessboard[6][7] = new Place(bPawn8, 6, 7);
        chessboard_image[6][7] = (TextView) findViewById(R.id.i67);

        // filling blanks
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                chessboard[i][j] = new Place(null, i, j);
            }
        }

        // initial updating
        update_board();
    }

    public void update_board(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p = chessboard[i][j].get_piece();
                if(p == null){
                    break;
                }
                switch(p.get_type()) {
                    case("K"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.king_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.king_black);
                        }
                        break;
                    case("Q"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.queen_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.queen_black);
                        }
                        break;
                    case("B"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.bishop_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.bishop_black);
                        }
                        break;
                    case("R"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.rook_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.rook_black);
                        }
                        break;
                    case("P"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.pawn_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.pawn_black);
                        }
                        break;
                    case("N"):
                        if(Objects.equals(p.get_player(), "W")){
                            chessboard_image[i][j].setBackgroundResource(R.drawable.knight_white);
                        }
                        else{
                            chessboard_image[i][j].setBackgroundResource(R.drawable.knight_black);
                        }
                        break;
                    default:
                        chessboard_image[i][j].setBackgroundResource(0);
                        break;
                }
            }
        }
    }

    public boolean move(int xstart, int ystart, int xend, int yend){
        Place pl = chessboard[xstart][ystart];
        chessboard[xend][yend] = pl;
        pl.set_location(xend, yend);
        chessboard[xstart][ystart] = new Place(null, xstart, ystart);

        return true;
    }

    boolean TOGGLE_SELECT = false;

    // to hold the current chosen location ?
    // hold current location + location to move to ?
    boolean chosen[][] = new boolean[8][8];

    // position of selection
    int[] selection = {-1, -1};


    @Override
    public void onClick(View v){
        TextView t = (TextView) findViewById(v.getId());
        Coordinates c = Coordinates.get_pos(t.getId());
        Log.d("1", "" + c.get_x());
        Log.d("2", "" + c.get_y());
        // get the ID of the chosen piece
        // save current position on toggle
        // if toggled again, unsave current position
        // update board each toggle for highlighted square
        if(!TOGGLE_SELECT){
            t.setText("HERE");
            TOGGLE_SELECT = true;
        }
        else{
            t.setText("");
            TOGGLE_SELECT = false;
        }
    }
}