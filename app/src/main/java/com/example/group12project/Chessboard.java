package com.example.group12project;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.group12project.ChessComponents.AggroChessBot;
import com.example.group12project.ChessComponents.Bishop;
import com.example.group12project.ChessComponents.ChessBot;
import com.example.group12project.ChessComponents.Coordinates;
import com.example.group12project.ChessComponents.King;
import com.example.group12project.ChessComponents.Knight;
import com.example.group12project.ChessComponents.Pawn;
import com.example.group12project.ChessComponents.Piece;
import com.example.group12project.ChessComponents.Queen;
import com.example.group12project.ChessComponents.RandomChessBot;
import com.example.group12project.ChessComponents.Rook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chessboard extends AppCompatActivity implements View.OnClickListener {
    static final int ROWS = 8;
    static final int COLUMNS = 8;

    static Piece[][] chessboard = new Piece[ROWS][COLUMNS];
    static View[][] chessboard_image = new View[ROWS][COLUMNS];
    static View[][] background_tiles = new View[ROWS][COLUMNS];

    boolean gameover = false;
    Coordinates whiteKingCoord;
    boolean whiteThreatMap[][];
    boolean blackThreatMap[][];
    Coordinates blackKingCoord;
    String WhiteName;
    String BlackName;
    boolean WhiteMove = true;   //starting boolean for move
    boolean CPU = false;
    boolean random = true;
    boolean castledR = false;
    boolean castledL = false;

    ChessBot bot;
    MediaPlayer mp;
    boolean sound;
    boolean mode;

    boolean piece_theme;
    boolean board_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessboard);

        start_board(); // create blank board, add pieces in their starting positions
        set_tiles();   // fill out background_tiles to use for highlighting
        blackThreatMap = getThreatMap("B");
        whiteThreatMap = getThreatMap("W");

        // sound effect
        mp = MediaPlayer.create(getApplicationContext(), R.raw.move);
        mp.setVolume(1F, 1F);
        sound = true;
        mode = true;
        piece_theme = true;
        board_theme = true;

        // need to set WhiteName and BlackName before game starts
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            CPU = extras.getBoolean("Bot");
            if(!CPU){
                WhiteName = extras.getString("WhiteName");
            }
            if(CPU){
                if(extras.getBoolean("Random")){
                    System.out.println("Random");
                    random = true;
                }
                else{
                    System.out.println("Aggro");
                    random = false;
                }
            }
            BlackName = extras.getString("BlackName");
        }

        TextView nv1 = (TextView) findViewById(R.id.player1_banner);
        TextView nv2 = (TextView) findViewById(R.id.player2_banner);

        nv1.setText(BlackName);
        nv2.setText(WhiteName);

        // first move with bot
        if(CPU){
            if(random){
                bot = new RandomChessBot();
            }
            else{
                bot = new AggroChessBot();
            }
            List<Coordinates> move = bot.make_move(chessboard, whiteKingCoord);
            move.get(0).display_coord();
            //move.get(1).display_coord();
            player_move(move.get(1), move.get(0));
        }

        ConstraintLayout back = (ConstraintLayout) findViewById(R.id.layout_chess);

        Button homebtn = (Button) findViewById(R.id.home_btn);
        homebtn.setOnClickListener(v -> {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sound_btn = (Switch) findViewById(R.id.sound_btn);
        sound_btn.setChecked(true); // intially on
        sound_btn.setOnClickListener(v -> {
            sound = sound_btn.isChecked();
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch mode_btn = (Switch) findViewById(R.id.mode_btn);
        mode_btn.setChecked(true);
        mode_btn.setOnClickListener(v -> {
            mode = !mode;
            if(mode){   // initially on light blue
                back.setBackgroundColor(0xFF33B5E5);
                sound_btn.setTextColor(Color.DKGRAY);
                mode_btn.setTextColor(Color.DKGRAY);
                nv1.setTextColor(Color.DKGRAY);
                nv2.setTextColor(Color.DKGRAY);
            }
            else{       // change to navy blue
                back.setBackgroundColor(0xFF00008B);
                sound_btn.setTextColor(Color.WHITE);
                mode_btn.setTextColor(Color.WHITE);
                nv1.setTextColor(Color.WHITE);
                nv2.setTextColor(Color.WHITE);
            }
        });

        Switch piece_btn = (Switch) findViewById(R.id.piece_btn);
        piece_btn.setChecked(true);
        piece_btn.setOnClickListener(v -> {
            piece_theme = !piece_theme;
        });
    }

    public void buildPopup(boolean white_win){
        AlertDialog.Builder builder = new AlertDialog.Builder(Chessboard.this);

        builder.setMessage("Good Job. Play again?");
        builder.setTitle("WINNER !");
        builder.setCancelable(false);

        builder.setPositiveButton("Home", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        });
        if(!CPU){
            builder.setNeutralButton("Update Leaderboard", (DialogInterface.OnClickListener) (dialog, which) -> {
                if(!white_win){
                    update_leaderboard(WhiteName, BlackName);
                }
                else{
                    update_leaderboard(BlackName, WhiteName);
                }
            });
        }
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("Play Again", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            this.recreate();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    public void start_board() {
        // start King Coordinates
        whiteKingCoord = new Coordinates(0,4);
        blackKingCoord = new Coordinates(7,4);

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
        SystemClock.sleep(100);
    }

    public void player_move(Coordinates start, Coordinates end) {
        Log.d("Clicked:", "Moving");
        Piece p = chessboard[start.X()][start.Y()];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                if (chessboard[i][j] instanceof Pawn)
                    ((Pawn) chessboard[i][j]).enPassantable = false;
        }

        if(p instanceof King){
            if (end.Y() == 6)
                castledR = true;
            if (end.Y() == 2)
                castledL = true;
            if(Objects.equals(p.get_player(), "W")){
                whiteKingCoord = end;
            }
            if(Objects.equals(p.get_player(), "B")){
                blackKingCoord = end;
            }
            ((King) p).hasMoved = true;
        }
        if (p instanceof Rook)
            ((Rook) p).hasMoved = true;
        if (p instanceof Pawn){
            if (Math.abs(end.X() - start.X()) == 2)
                ((Pawn) p).enPassantable = true;
            if(Objects.equals(p.get_player(), "W") && end.X()==7){
                p = new Queen("W");
            }
            if(Objects.equals(p.get_player(), "B") && end.X()==0){
                p = new Queen("B");
            }
            if (Math.abs(end.Y() - start.Y()) == 1 && chessboard[end.X()][end.Y()] == null) {
                chessboard[start.X()][end.Y()] = null;
                update_piece(null, new Coordinates(start.X(), end.Y()));
            }
        }

        // run hopping animation between start and end
        List<Coordinates> intermediates = Coordinates.places_between(start, end);
        Handler handler = new Handler();
        int COUNT = intermediates.size();
        final int[] curr = {0};
        Piece finalP = p;
        final Coordinates[] lag = {start};
        final Runnable r = new Runnable() {
            public void run() {
                update_piece(null, lag[0]);
                if(sound){
                    mp.start();
                }
                if(curr[0] == COUNT){
                    update_piece(finalP, end);

                    if (castledL) {
                        Piece cRook = chessboard[start.X()][0];
                        chessboard[start.X()][0] = null;
                        chessboard[start.X()][3] = cRook;
                        update_piece(cRook,new Coordinates(start.X(),3));
                        update_piece(null,new Coordinates(start.X(),0));
                        castledL = false;
                    }
                    if (castledR) {
                        Piece cRook = chessboard[start.X()][7];
                        chessboard[start.X()][7] = null;
                        chessboard[start.X()][5] = cRook;
                        update_piece(cRook,new Coordinates(start.X(),5));
                        update_piece(null,new Coordinates(start.X(),7));
                        castledR = false;
                    }
                }
                else {
                    update_piece(finalP, intermediates.get(curr[0]));
                    lag[0] = intermediates.get(curr[0]);
                    curr[0]++;
                    handler.postDelayed(this, 400);
                }
            }
        };
        handler.postDelayed(r, 400);

        chessboard[end.X()][end.Y()] = p;
        //update_piece(null, start); // empty spot
        chessboard[start.X()][start.Y()] = null;

        // update move
        WhiteMove = !WhiteMove;
    }

    boolean haveSelect = false;
    Coordinates startSelect; // selection of piece to move
    Coordinates destSelect;  // selection of destination for piece to move

    @Override
    public void onClick(View v) {
        if(gameover){
            return;
        }
        if(CPU){
            if(WhiteMove){ return; }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Coordinates curr = new Coordinates(i,j);
                if(haveSelect && !(curr.equals(whiteKingCoord) || curr.equals(blackKingCoord))){
                    unhighlight_tile(curr);
                }
            }
        }
        TextView t = (TextView) findViewById(v.getId());
        Coordinates c = Coordinates.get_pos(t.getId());
        if(c == null){
            return;
        }
        Log.d("Clicked:", c.X() + " , " + c.Y());
        Log.d("WhiteMove:", WhiteMove + " ");
        if (!haveSelect && chessboard[c.X()][c.Y()] != null) {  // select a piece
            if((Objects.equals(chessboard[c.X()][c.Y()].get_player(), "W")) != WhiteMove){
                return;
            }
            startSelect = c;
            highlight_tile(c);
            highlight_all_possible(c);
            haveSelect = true;
        }
        else if(!haveSelect && chessboard[c.X()][c.Y()] == null){
            return;
        }
        else if(haveSelect && c == startSelect){        // click on the piece again
            startSelect = null;
            unhighlight_tile(c);
            unhighlight_all_possible(c);
            haveSelect = false;
        }
        else if(haveSelect && chessboard[c.X()][c.Y()] != null && (Objects.equals(chessboard[c.X()][c.Y()].get_player(), "W"))==WhiteMove){
            //condition where we select another piece to move
            unhighlight_all_possible(startSelect);
            startSelect = c;
            highlight_tile(startSelect);
            highlight_all_possible(startSelect);
        }
        else{
            destSelect = c;  // click on another piece
            Coordinates thisKing = (WhiteMove) ? whiteKingCoord : blackKingCoord;
            if(chessboard[startSelect.X()][startSelect.Y()].can_move(chessboard, startSelect, destSelect, thisKing)){
                unhighlight_all_possible(startSelect);
                player_move(startSelect, destSelect);
                if(chessboard[destSelect.X()][destSelect.Y()] instanceof King){
                    unhighlight_tile(startSelect);
                }
                if(Objects.equals(chessboard[destSelect.X()][destSelect.Y()].get_player(), "W")){
                    // assumes it is white's move, and black king is in checkmate
                    if(win(blackKingCoord)){
                        background_tiles[blackKingCoord.X()][blackKingCoord.Y()].setBackgroundColor(Color.RED);
                        gameover = true;
                        buildPopup(WhiteMove);
                        return;
                    }
                    System.out.println("Checking");
                    if(((King)chessboard[whiteKingCoord.X()][whiteKingCoord.Y()]).kingInCheck(chessboard, whiteKingCoord)){
                        background_tiles[whiteKingCoord.X()][whiteKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(whiteKingCoord);
                    }
                    if(((King)chessboard[blackKingCoord.X()][blackKingCoord.Y()]).kingInCheck(chessboard, blackKingCoord)){
                        background_tiles[blackKingCoord.X()][blackKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(blackKingCoord);
                    }
                }
                else{
                    // assumes it is black's move, and white king is in checkmate
                    if(win(whiteKingCoord)){
                        background_tiles[whiteKingCoord.X()][whiteKingCoord.Y()].setBackgroundColor(Color.RED);
                        gameover = true;
                        buildPopup(WhiteMove);
                        return;
                    }
                    if(((King)chessboard[blackKingCoord.X()][blackKingCoord.Y()]).kingInCheck(chessboard, blackKingCoord)){
                        background_tiles[blackKingCoord.X()][blackKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(blackKingCoord);
                    }
                    if(((King)chessboard[whiteKingCoord.X()][whiteKingCoord.Y()]).kingInCheck(chessboard, whiteKingCoord)){
                        background_tiles[whiteKingCoord.X()][whiteKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(whiteKingCoord);
                    }
                }
                startSelect = null;
                destSelect = null;
                haveSelect = false;

                // assume we moved and no endgame yet, so let bot move if bot
                if(CPU){
                    List<Coordinates> move = bot.make_move(chessboard, whiteKingCoord);
                    final Handler handler = new Handler();
                    final Runnable r = () -> player_move(move.get(1), move.get(0));
                    handler.postDelayed(r, 2000);

                    if(win(whiteKingCoord)){
                        background_tiles[whiteKingCoord.X()][whiteKingCoord.Y()].setBackgroundColor(Color.RED);
                        gameover = true;
                        buildPopup(WhiteMove);
                        return;
                    }
                    System.out.println("Checking");
                    if(((King)chessboard[whiteKingCoord.X()][whiteKingCoord.Y()]).kingInCheck(chessboard, whiteKingCoord)){
                        background_tiles[whiteKingCoord.X()][whiteKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(whiteKingCoord);
                    }
                    if(((King)chessboard[blackKingCoord.X()][blackKingCoord.Y()]).kingInCheck(chessboard, blackKingCoord)){
                        background_tiles[blackKingCoord.X()][blackKingCoord.Y()].setBackgroundColor(Color.rgb(255, 165, 57));
                    }
                    else{
                        unhighlight_tile(blackKingCoord);
                    }
                }

            }
            else{
                destSelect = null;
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
        TextView v = (TextView) background_tiles[c.X()][c.Y()];
        v.setBackgroundColor(Color.rgb(153, 255, 255));
    }
    public void highlight_tile_possible(Coordinates c){
        // this method highlights the tile at coordinate c to light blue
        TextView v = (TextView) chessboard_image[c.X()][c.Y()];
        if(chessboard[c.X()][c.Y()] == null){
            v.setBackgroundResource(R.drawable.dot);
        }
        else{
            background_tiles[c.X()][c.Y()].setBackgroundColor(Color.MAGENTA);
        }
    }
    public void unhighlight_tile(Coordinates c){
        // this method removes highlight on any tile by resetting back to its original color
        // which is found through a check of the coordinate passed as a parameter
        TextView v = (TextView) background_tiles[c.X()][c.Y()];
        v.setText("");
        if(c.isWhite()){
            v.setBackgroundColor(Color.WHITE); // white
        }
        else{
            v.setBackgroundColor(Color.rgb(181, 181,181)); // gray
        }
    }

    public void unhighlight_tile_possible(Coordinates c){
        TextView v = (TextView) chessboard_image[c.X()][c.Y()];
        if(chessboard[c.X()][c.Y()] == null){
            v.setBackgroundResource(0);
        }
        else{
            unhighlight_tile(c);
        }
    }

    public void unhighlight_all_possible(Coordinates c){
        Piece p = chessboard[c.X()][c.Y()];
        Coordinates thisKing = (Objects.equals(chessboard[c.X()][c.Y()].get_player(), "W")) ? whiteKingCoord : blackKingCoord;
        List<Coordinates> all_pos = p.allPossibleMoves(chessboard, c, thisKing);
        for(Coordinates t : all_pos){
            if(t != whiteKingCoord && t != blackKingCoord){
                unhighlight_tile_possible(t);
            }
        }
    }

    public void highlight_all_possible(Coordinates c){
        Piece p = chessboard[c.X()][c.Y()];
        Coordinates thisKing = (Objects.equals(chessboard[c.X()][c.Y()].get_player(), "W")) ? whiteKingCoord : blackKingCoord;
        List<Coordinates> all_pos;
        if(chessboard[c.X()][c.Y()] instanceof King){
            all_pos = p.allPossibleMoves(chessboard, c, thisKing);
        }
        else{
            all_pos = p.allPossibleMoves(chessboard, c, thisKing);
        }
        for(Coordinates t : all_pos){
            if(t != whiteKingCoord && t != blackKingCoord){
                highlight_tile_possible(t);
            }
        }
    }

    public static void print_board() {
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


    // need to add win condition in this function
    public boolean win(Coordinates king){
        if(!(chessboard[king.X()][king.Y()] instanceof King)){
            // if another piece is taking spot of king, return true
            return true;
        }


        King kingPiece = (King) chessboard[king.X()][king.Y()];
        if(kingPiece == null){
            throw new IllegalArgumentException();
        }
        // find threatmap
        boolean[][] threat = (Objects.equals(kingPiece.get_player(), "W")) ? getThreatMap("B") : getThreatMap("W");
        Coordinates checker = kingPiece.checkByWho(chessboard, king);
        if(checker == null){ // check if King is in check
            return false;   // not in check
        }

        //can king move? can king move to a spot not in check?
        List<Coordinates> kingMoves = kingPiece.allPossibleMoves(chessboard, king);
        List<Coordinates> kingMoves_pos = new ArrayList<>();
        if(!kingMoves.isEmpty()){
            for(Coordinates c : kingMoves){
                if(!threat[c.X()][c.Y()]){
                    return true;
                }
            }
        }


        // check for possible obstructions to counter check

        List<Coordinates> between = Coordinates.places_between(king, checker);   // find possible obstruction spots
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p = chessboard[i][j];
                if(p != null && Objects.equals(p.get_player(), kingPiece.get_player()) && !(p instanceof King)){
                    List<Coordinates> possibleObstructions = p.allPossibleMoves(chessboard, new Coordinates(i,j), king);
                    for(Coordinates c : possibleObstructions){
                        for(Coordinates x : between){
                            if(c.equals(x) || c.equals(checker)){   // piece can obstruct or take checker
                                return false;
                            }
                        }
                    }
                }
            }
        }

        // can king take checker ?
        if(kingPiece.can_move(chessboard, king, checker)) {
            // without being in check
            return kingPiece.kingInCheck(chessboard, checker);
        }
        else{
            return true;
        }
    }

    public void update_leaderboard(String winner, String loser){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        if(pref.contains(winner)){
            int curr = 0;
            pref.getInt(winner, curr);
            curr++;
            editor.putInt(winner, curr);
        }

        // Send the winner and losers to the leaderboard
        Intent intent = new Intent(this, Leaderboard.class);

        intent.putExtra("Winner", winner);
        intent.putExtra("Loser", loser);

        startActivity(intent);
    }
    public String whoWon(){
        if(WhiteMove){
            update_leaderboard(WhiteName,BlackName);
            return WhiteName;
        }
        else{
            update_leaderboard(BlackName,WhiteName);
            return BlackName;

        }
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("move", WhiteMove);
        savedInstanceState.putString("player1", BlackName);
        savedInstanceState.putString("player2", WhiteName);

        ArrayList<String> row1 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[0][i] != null){
                row1.add(chessboard[0][i].get_type() + chessboard[0][i].get_player());
            }
            else{
                row1.add("NONE");
            }
        }
        ArrayList<String> row2 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[1][i] != null){
                row2.add(chessboard[1][i].get_type() + chessboard[1][i].get_player());
            }
            else{
                row2.add("NONE");
            }
        }
        ArrayList<String> row3 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[2][i] != null){
                row3.add(chessboard[2][i].get_type() + chessboard[2][i].get_player());
            }
            else{
                row3.add("NONE");
            }
        }
        ArrayList<String> row4 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[3][i] != null){
                row4.add(chessboard[3][i].get_type() + chessboard[3][i].get_player());
            }
            else{
                row4.add("NONE");
            }
        }
        ArrayList<String> row5 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[0][i] != null){
                row5.add(chessboard[4][i].get_type() + chessboard[4][i].get_player());}
            else{
                row5.add("NONE");
            }
        }
        ArrayList<String> row6 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[5][i] != null){
                row6.add(chessboard[5][i].get_type() + chessboard[5][i].get_player());}
            else{
                row6.add("NONE");
            }
        }
        ArrayList<String> row7 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[0][i] != null){
                row7.add(chessboard[6][i].get_type() + chessboard[6][i].get_player());}
            else{
                row7.add("NONE");
            }
        }
        ArrayList<String> row8 = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            if(chessboard[7][i] != null){
                row8.add(chessboard[7][i].get_type() + chessboard[7][i].get_player());}
            else{
                row8.add("NONE");
            }
        }

        savedInstanceState.putStringArrayList("r1", row1);
        savedInstanceState.putStringArrayList("r2", row2);
        savedInstanceState.putStringArrayList("r3", row3);
        savedInstanceState.putStringArrayList("r4", row4);
        savedInstanceState.putStringArrayList("r5", row5);
        savedInstanceState.putStringArrayList("r6", row6);
        savedInstanceState.putStringArrayList("r7", row7);
        savedInstanceState.putStringArrayList("r8", row8);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> r1 = savedInstanceState.getStringArrayList("r1");
        ArrayList<String> r2 = savedInstanceState.getStringArrayList("r2");
        ArrayList<String> r3 = savedInstanceState.getStringArrayList("r3");
        ArrayList<String> r4 = savedInstanceState.getStringArrayList("r4");
        ArrayList<String> r5 = savedInstanceState.getStringArrayList("r5");
        ArrayList<String> r6 = savedInstanceState.getStringArrayList("r6");
        ArrayList<String> r7 = savedInstanceState.getStringArrayList("r7");
        ArrayList<String> r8 = savedInstanceState.getStringArrayList("r8");

        WhiteMove = savedInstanceState.getBoolean("move");
        WhiteName = savedInstanceState.getString("player1");
        BlackName = savedInstanceState.getString("player2");

        for(int i = 0; i < 8; i++){
            String s = r1.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[0][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[0][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r2.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[1][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[1][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r3.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[2][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[2][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r4.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[3][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[3][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r5.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[4][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[4][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r6.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[5][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[5][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r7.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[6][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[6][i] = null;
            }
        }
        for(int i = 0; i < 8; i++){
            String s = r8.get(i);
            if(!Objects.equals(s, "NONE")){
                chessboard[7][i] = get_piece(s.substring(0,1), s.substring(1));
            }
            else{
                chessboard[7][i] = null;
            }
        }

        update_board();

    }

    public Piece get_piece(String t, String p){
        switch(t){
            case("K"):{
                if(Objects.equals(p, "W")) return new King("W");
                else return new King("B");
            }
            case("Q"):{
                if(Objects.equals(p, "W")) return new Queen("W");
                else return new Queen("B");
            }
            case("N"):{
                if(Objects.equals(p, "W")) return new Knight("W");
                else return new Knight("B");
            }
            case("B"):{
                if(Objects.equals(p, "W")) return new Bishop("W");
                else return new Bishop("B");
            }
            case("R"):{
                if(Objects.equals(p, "W")) return new Rook("W");
                else return new Rook("B");
            }
            case("P"):{
                if(Objects.equals(p, "W")) return new Pawn("W");
                else return new Pawn("B");
            }
            default:{
                return null;
            }
        }
    }
    */


    public boolean[][] getThreatMap(String p){
        boolean[][] map = new boolean[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(chessboard[i][j] != null && Objects.equals(chessboard[i][j].get_player(), p)){
                    for(Coordinates c : chessboard[i][j].allPossibleMoves(chessboard, new Coordinates(i,j))){
                        map[c.X()][c.Y()] = true;
                    }
                }
            }
        }

        return map;
    }

}
