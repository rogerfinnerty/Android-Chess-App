package com.example.group12project;

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

    boolean WhiteMove = true;   //starting boolean for move

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessboard);

        startBoard();
        player_move(new Coordinates(1, 1), new Coordinates(4, 1));


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

    public void startBoard() {
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
            chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(0);
            return;
        }
        switch (p.get_type()) {
            case ("K"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.king_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.king_black);
                }
                break;
            case ("Q"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.queen_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.queen_black);
                }
                break;
            case ("B"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.bishop_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.bishop_black);
                }
                break;
            case ("R"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.rook_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.rook_black);
                }
                break;
            case ("P"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.pawn_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.pawn_black);
                }
                break;
            case ("N"):
                if (Objects.equals(p.get_player(), "W")) {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.knight_white);
                } else {
                    chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(R.drawable.knight_black);
                }
                break;
            default:
                chessboard_image[newCo.get_x()][newCo.get_y()].setBackgroundResource(0);
                break;
        }
        Log.d("Update", "complete");
    }

    public void player_move(Coordinates start, Coordinates end) {
        Piece p = chessboard[start.get_x()][start.get_y()];
        update_piece(p, end);
        chessboard[end.get_x()][end.get_y()] = p;
        update_piece(null, start);
        chessboard[start.get_x()][start.get_y()] = null;
    }

    boolean TOGGLE_SELECT = false;
    boolean haveSelect = false;
    Coordinates firstSelect;

    // to hold the current chosen location ?
    // hold current location + location to move to ?
    boolean[][] chosen = new boolean[8][8];

    @Override
    public void onClick(View v) {
        TextView t = (TextView) findViewById(v.getId());
        Coordinates c = Coordinates.get_pos(t.getId());
        if (!haveSelect && chessboard[c.get_x()][c.get_y()] != null) {
            firstSelect = c;
            Log.d("1", "" + c.get_x());
            Log.d("2", "" + c.get_y());
            haveSelect = true;
        }

        // get the ID of the chosen piece
        // save current position on toggle
        // if toggled again, unsave current position
        // update board each toggle for highlighted square
        if (!TOGGLE_SELECT) {
            t.setText("HERE");
            TOGGLE_SELECT = true;
        } else {
            t.setText("");
            TOGGLE_SELECT = false;
        }
    }

    public static boolean kingInCheck(Coordinates king_square){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] != null){
                    List<Coordinates> possibleMoves = chessboard[i][j].allPossibleMoves(chessboard, new Coordinates(i,j));
                    for(Coordinates c : possibleMoves){
                        if(c == king_square){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public void print_board() {
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