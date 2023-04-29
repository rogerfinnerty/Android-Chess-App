package com.example.group12project;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.group12project.ChessComponents.*;

import java.util.List;
import java.util.Objects;

public class Chessboard extends AppCompatActivity implements View.OnClickListener {
    static final int ROWS = 8;
    static final int COLUMNS = 8;

    static Piece[][] chessboard = new Piece[ROWS][COLUMNS];
    static View[][] chessboard_image = new View[ROWS][COLUMNS];
    static View[][] background_tiles = new View[ROWS][COLUMNS];

    boolean WhiteMove = true;   //starting boolean for move

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessboard);

        start_board(); // create blank board, add pieces in their starting positions
        set_tiles();   // fill out background_tiles to use for highlighting
        player_move(new Coordinates(1, 1), new Coordinates(4, 1));
        player_move(new Coordinates(1, 2), new Coordinates(4, 2));
        player_move(new Coordinates(1, 0), new Coordinates(4, 0));
        player_move(new Coordinates(1, 3), new Coordinates(4, 3));


        Button home_btn = (Button) findViewById(R.id.home_btn);
        Button settings_btn = (Button) findViewById(R.id.settings_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_settings);
            }
        });
    }

    public void start_board() {
        // WHITE PIECES
        chessboard[0][0] = new Rook("W");
        chessboard_image[0][0] = (TextView) findViewById(R.id.i00);
        chessboard[0][1] = new Knight("W");
        chessboard_image[0][1] = (TextView) findViewById(R.id.i01);
        chessboard[0][2] = new Bishop("W");
        chessboard_image[0][2] = (TextView) findViewById(R.id.i02);
        chessboard[0][3] = new Queen("W");
        chessboard_image[0][3] = (TextView) findViewById(R.id.i03);
        chessboard[0][4] = new King("W");
        chessboard_image[0][4] = (TextView) findViewById(R.id.i04);
        chessboard[0][5] = new Bishop("W");
        chessboard_image[0][5] = (TextView) findViewById(R.id.i05);
        chessboard[0][6] = new Knight("W");
        chessboard_image[0][6] = (TextView) findViewById(R.id.i06);
        chessboard[0][7] = new Rook("W");
        chessboard_image[0][7] = (TextView) findViewById(R.id.i07);

        chessboard[1][0] = new Pawn("W");
        chessboard_image[1][0] = (TextView) findViewById(R.id.i10);
        chessboard[1][1] = new Pawn("W");
        chessboard_image[1][1] = (TextView) findViewById(R.id.i11);
        chessboard[1][2] = new Pawn("W");
        chessboard_image[1][2] = (TextView) findViewById(R.id.i12);
        chessboard[1][3] = new Pawn("W");
        chessboard_image[1][3] = (TextView) findViewById(R.id.i13);
        chessboard[1][4] = new Pawn("W");
        chessboard_image[1][4] = (TextView) findViewById(R.id.i14);
        chessboard[1][5] = new Pawn("W");
        chessboard_image[1][5] = (TextView) findViewById(R.id.i15);
        chessboard[1][6] = new Pawn("W");
        chessboard_image[1][6] = (TextView) findViewById(R.id.i16);
        chessboard[1][7] = new Pawn("W");
        chessboard_image[1][7] = (TextView) findViewById(R.id.i17);

        // BLACK PIECES
        chessboard[7][0] = new Rook("B");
        chessboard_image[7][0] = (TextView) findViewById(R.id.i70);
        chessboard[7][1] = new Knight("B");
        chessboard_image[7][1] = (TextView) findViewById(R.id.i71);
        chessboard[7][2] = new Bishop("B");
        chessboard_image[7][2] = (TextView) findViewById(R.id.i72);
        chessboard[7][3] = new Queen("B");
        chessboard_image[7][3] = (TextView) findViewById(R.id.i73);
        chessboard[7][4] = new King("B");
        chessboard_image[7][4] = (TextView) findViewById(R.id.i74);
        chessboard[7][5] = new Bishop("B");
        chessboard_image[7][5] = (TextView) findViewById(R.id.i75);
        chessboard[7][6] = new Knight("B");
        chessboard_image[7][6] = (TextView) findViewById(R.id.i76);
        chessboard[7][7] = new Rook("B");
        chessboard_image[7][7] = (TextView) findViewById(R.id.i77);

        chessboard[6][0] = new Pawn("B");
        chessboard_image[6][0] = (TextView) findViewById(R.id.i60);
        chessboard[6][1] = new Pawn("B");
        chessboard_image[6][1] = (TextView) findViewById(R.id.i61);
        chessboard[6][2] = new Pawn("B");
        chessboard_image[6][2] = (TextView) findViewById(R.id.i62);
        chessboard[6][3] = new Pawn("B");
        chessboard_image[6][3] = (TextView) findViewById(R.id.i63);
        chessboard[6][4] = new Pawn("B");
        chessboard_image[6][4] = (TextView) findViewById(R.id.i64);
        chessboard[6][5] = new Pawn("B");
        chessboard_image[6][5] = (TextView) findViewById(R.id.i65);
        chessboard[6][6] = new Pawn("B");
        chessboard_image[6][6] = (TextView) findViewById(R.id.i66);
        chessboard[6][7] = new Pawn("B");
        chessboard_image[6][7] = (TextView) findViewById(R.id.i67);

        // filling blanks
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = null;
            }
        }

        chessboard_image[2][0] = (TextView) findViewById(R.id.i20);
        chessboard_image[2][1] = (TextView) findViewById(R.id.i21);
        chessboard_image[2][2] = (TextView) findViewById(R.id.i22);
        chessboard_image[2][3] = (TextView) findViewById(R.id.i23);
        chessboard_image[2][4] = (TextView) findViewById(R.id.i24);
        chessboard_image[2][5] = (TextView) findViewById(R.id.i25);
        chessboard_image[2][6] = (TextView) findViewById(R.id.i26);
        chessboard_image[2][7] = (TextView) findViewById(R.id.i27);
        chessboard_image[3][0] = (TextView) findViewById(R.id.i30);
        chessboard_image[3][1] = (TextView) findViewById(R.id.i31);
        chessboard_image[3][2] = (TextView) findViewById(R.id.i32);
        chessboard_image[3][3] = (TextView) findViewById(R.id.i33);
        chessboard_image[3][4] = (TextView) findViewById(R.id.i34);
        chessboard_image[3][5] = (TextView) findViewById(R.id.i35);
        chessboard_image[3][6] = (TextView) findViewById(R.id.i36);
        chessboard_image[3][7] = (TextView) findViewById(R.id.i37);
        chessboard_image[4][0] = (TextView) findViewById(R.id.i40);
        chessboard_image[4][1] = (TextView) findViewById(R.id.i41);
        chessboard_image[4][2] = (TextView) findViewById(R.id.i42);
        chessboard_image[4][3] = (TextView) findViewById(R.id.i43);
        chessboard_image[4][4] = (TextView) findViewById(R.id.i44);
        chessboard_image[4][5] = (TextView) findViewById(R.id.i45);
        chessboard_image[4][6] = (TextView) findViewById(R.id.i46);
        chessboard_image[4][7] = (TextView) findViewById(R.id.i47);
        chessboard_image[5][0] = (TextView) findViewById(R.id.i50);
        chessboard_image[5][1] = (TextView) findViewById(R.id.i51);
        chessboard_image[5][2] = (TextView) findViewById(R.id.i52);
        chessboard_image[5][3] = (TextView) findViewById(R.id.i53);
        chessboard_image[5][4] = (TextView) findViewById(R.id.i54);
        chessboard_image[5][5] = (TextView) findViewById(R.id.i55);
        chessboard_image[5][6] = (TextView) findViewById(R.id.i56);
        chessboard_image[5][7] = (TextView) findViewById(R.id.i57);

        // initial updating
        update_board();
    }

    public void update_board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = chessboard[i][j];
                if (p == null) {
                    chessboard_image[i][j].setBackgroundResource(0);
                    break;
                }
                switch (p.get_type()) {
                    case ("K"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.king_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.king_black);
                        }
                        break;
                    case ("Q"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.queen_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.queen_black);
                        }
                        break;
                    case ("B"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.bishop_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.bishop_black);
                        }
                        break;
                    case ("R"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.rook_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.rook_black);
                        }
                        break;
                    case ("P"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.pawn_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.pawn_black);
                        }
                        break;
                    case ("N"):
                        if (Objects.equals(p.get_player(), "W")) {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.knight_white);
                        } else {
                            chessboard_image[i][j].setBackgroundResource(R.drawable.knight_black);
                        }
                        break;
                    default:
                        chessboard_image[i][j].setBackgroundResource(0);
                        break;
                }
            }
        }
        Log.d("Update", "complete");
    }

    public void update_piece(Piece p, Coordinates newCo) {
        if (p == null) {
            chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(0);
            return;
        }
        switch (p.get_type()) {
            case ("K"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.king_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.king_black);
                }
                break;
            case ("Q"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.queen_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.queen_black);
                }
                break;
            case ("B"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.bishop_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.bishop_black);
                }
                break;
            case ("R"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.rook_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.rook_black);
                }
                break;
            case ("P"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.pawn_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.pawn_black);
                }
                break;
            case ("N"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.knight_white);
                } else {
                    chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(R.drawable.knight_black);
                }
                break;
            default:
                chessboard_image[newCo.X()][newCo.Y()].setBackgroundResource(0);
                break;
        }
        Log.d("Update", "complete");
    }

    public void player_move(Coordinates start, Coordinates end) {
        Piece p = chessboard[start.X()][start.Y()];
        update_piece(p, end);
        chessboard[end.X()][end.Y()] = p;
        update_piece(null, start);
        chessboard[start.X()][start.Y()] = null;
    }

    boolean TOGGLE_SELECT = false;
    boolean haveSelect = false;
    Coordinates startSelect; // selection of piece to move
    Coordinates destSelect;  // selection of destination for piece to move

    // to hold the current chosen location ?
    // hold current location + location to move to ?
    boolean[][] chosen = new boolean[8][8];

    @Override
    public void onClick(View v) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                unhighlight_tile(new Coordinates(i,j));
            }
        }
        TextView t = (TextView) findViewById(v.getId());
        Coordinates c = Coordinates.get_pos(t.getId());
        if (!haveSelect && chessboard[c.X()][c.Y()] != null) {
            startSelect = c;
            highlight_tile(c);
            highlight_all_possible(c);
            haveSelect = true;
        }
        else{
            destSelect = c;
            if(chessboard[startSelect.X()][startSelect.Y()].can_move(chessboard, startSelect, destSelect)){
                player_move(startSelect, destSelect);
                startSelect = null;
                destSelect = null;
                haveSelect = false;
            }
        }

    }



    public void set_tiles() {
        background_tiles[0][0] = (TextView) findViewById(R.id.r00);
        background_tiles[0][1] = (TextView) findViewById(R.id.r01);
        background_tiles[0][2] = (TextView) findViewById(R.id.r02);
        background_tiles[0][3] = (TextView) findViewById(R.id.r03);
        background_tiles[0][4] = (TextView) findViewById(R.id.r04);
        background_tiles[0][5] = (TextView) findViewById(R.id.r05);
        background_tiles[0][6] = (TextView) findViewById(R.id.r06);
        background_tiles[0][7] = (TextView) findViewById(R.id.r07);
        background_tiles[1][0] = (TextView) findViewById(R.id.r10);
        background_tiles[1][1] = (TextView) findViewById(R.id.r11);
        background_tiles[1][2] = (TextView) findViewById(R.id.r12);
        background_tiles[1][3] = (TextView) findViewById(R.id.r13);
        background_tiles[1][4] = (TextView) findViewById(R.id.r14);
        background_tiles[1][5] = (TextView) findViewById(R.id.r15);
        background_tiles[1][6] = (TextView) findViewById(R.id.r16);
        background_tiles[1][7] = (TextView) findViewById(R.id.r17);
        background_tiles[2][0] = (TextView) findViewById(R.id.r20);
        background_tiles[2][1] = (TextView) findViewById(R.id.r21);
        background_tiles[2][2] = (TextView) findViewById(R.id.r22);
        background_tiles[2][3] = (TextView) findViewById(R.id.r23);
        background_tiles[2][4] = (TextView) findViewById(R.id.r24);
        background_tiles[2][5] = (TextView) findViewById(R.id.r25);
        background_tiles[2][6] = (TextView) findViewById(R.id.r26);
        background_tiles[2][7] = (TextView) findViewById(R.id.r27);
        background_tiles[3][0] = (TextView) findViewById(R.id.r30);
        background_tiles[3][1] = (TextView) findViewById(R.id.r31);
        background_tiles[3][2] = (TextView) findViewById(R.id.r32);
        background_tiles[3][3] = (TextView) findViewById(R.id.r33);
        background_tiles[3][4] = (TextView) findViewById(R.id.r34);
        background_tiles[3][5] = (TextView) findViewById(R.id.r35);
        background_tiles[3][6] = (TextView) findViewById(R.id.r36);
        background_tiles[3][7] = (TextView) findViewById(R.id.r37);
        background_tiles[4][0] = (TextView) findViewById(R.id.r40);
        background_tiles[4][1] = (TextView) findViewById(R.id.r41);
        background_tiles[4][2] = (TextView) findViewById(R.id.r42);
        background_tiles[4][3] = (TextView) findViewById(R.id.r43);
        background_tiles[4][4] = (TextView) findViewById(R.id.r44);
        background_tiles[4][5] = (TextView) findViewById(R.id.r45);
        background_tiles[4][6] = (TextView) findViewById(R.id.r46);
        background_tiles[4][7] = (TextView) findViewById(R.id.r47);
        background_tiles[5][0] = (TextView) findViewById(R.id.r50);
        background_tiles[5][1] = (TextView) findViewById(R.id.r51);
        background_tiles[5][2] = (TextView) findViewById(R.id.r52);
        background_tiles[5][3] = (TextView) findViewById(R.id.r53);
        background_tiles[5][4] = (TextView) findViewById(R.id.r54);
        background_tiles[5][5] = (TextView) findViewById(R.id.r55);
        background_tiles[5][6] = (TextView) findViewById(R.id.r56);
        background_tiles[5][7] = (TextView) findViewById(R.id.r57);
        background_tiles[6][0] = (TextView) findViewById(R.id.r60);
        background_tiles[6][1] = (TextView) findViewById(R.id.r61);
        background_tiles[6][2] = (TextView) findViewById(R.id.r62);
        background_tiles[6][3] = (TextView) findViewById(R.id.r63);
        background_tiles[6][4] = (TextView) findViewById(R.id.r64);
        background_tiles[6][5] = (TextView) findViewById(R.id.r65);
        background_tiles[6][6] = (TextView) findViewById(R.id.r66);
        background_tiles[6][7] = (TextView) findViewById(R.id.r67);
        background_tiles[7][0] = (TextView) findViewById(R.id.r70);
        background_tiles[7][1] = (TextView) findViewById(R.id.r71);
        background_tiles[7][2] = (TextView) findViewById(R.id.r72);
        background_tiles[7][3] = (TextView) findViewById(R.id.r73);
        background_tiles[7][4] = (TextView) findViewById(R.id.r74);
        background_tiles[7][5] = (TextView) findViewById(R.id.r75);
        background_tiles[7][6] = (TextView) findViewById(R.id.r76);
        background_tiles[7][7] = (TextView) findViewById(R.id.r77);
    }
    public void highlight_tile(Coordinates c){
        // this method highlights the tile at coordinate c to light blue
        View v = (TextView) background_tiles[c.X()][c.Y()];
        v.setBackgroundColor(Color.rgb(153, 255, 255));
    }
    public void highlight_tile_possible(Coordinates c){
        // this method highlights the tile at coordinate c to light blue
        View v = (TextView) background_tiles[c.X()][c.Y()];
        v.setBackgroundColor(Color.rgb(202, 252, 169));
    }
    public void unhighlight_tile(Coordinates c){
        // this method removes highlight on any tile by resetting back to its original color
        // which is found through a check of the coordinate passed as a parameter
        View v = (TextView) background_tiles[c.X()][c.Y()];
        if(c.isWhite()){
            v.setBackgroundColor(Color.WHITE); // white
        }
        else{
            v.setBackgroundColor(Color.rgb(181, 181,181)); // gray
        }
    }

    public void highlight_all_possible(Coordinates c){
        Piece p = chessboard[c.X()][c.Y()];
        List<Coordinates> all_pos = p.allPossibleMoves(chessboard, c);
        for(Coordinates t : all_pos){
            highlight_tile_possible(t);
        }
    }

    public void print_board() {
        // helper method to print board, used to see backend logic
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = chessboard[i][j];
                if (p != null) {
                    System.out.print(p.get_type() + " | ");
                } else {
                    System.out.print("  | ");
                }

            }
            System.out.println("--------------------------");
        }
    }
}