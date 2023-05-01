package com.example.group12project.ChessComponents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AggroChessBot extends ChessBot {

    public AggroChessBot(){
        super();
    }

    @Override
    public List<Coordinates> make_move(Piece[][] board, Coordinates kingPos) {
        List<Coordinates> end_pos = new ArrayList<>();
        List<Coordinates> start_pos = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != null && Objects.equals(board[i][j].get_player(), this.player)) {
                    List<Coordinates> possible = board[i][j].allPossibleMoves(board, new Coordinates(i,j), kingPos);
                    for(Coordinates c : possible){
                        //pretend as if move was made, then check if king is NOW in check?
                        scores.add(get_score(board, c));
                        end_pos.add(new Coordinates(i,j));
                        start_pos.add(c);
                    }
                }
            }
        }

        // filtering for max scores
        int max = Collections.max(scores);
        System.out.println("Max: " + max);
        List<Coordinates> end_pos_final = new ArrayList<>();
        List<Coordinates> start_pos_final = new ArrayList<>();
        for(int i =0; i < scores.size(); i++){
            if(scores.get(i) == max){
                end_pos_final.add(end_pos.get(i));
                start_pos_final.add(start_pos.get(i));
            }
        }

        Random rand = new Random();
        int r = rand.nextInt(end_pos_final.size());
        return Arrays.asList(start_pos_final.get(r), end_pos_final.get(r));
    }

    public int get_score(Piece[][] board, Coordinates c){
        if(board[c.X()][c.Y()] == null){
            // no piece there
            return 0;
        }

        String t = board[c.X()][c.Y()].get_type();
        System.out.println(t);
        switch(t){
            case("P"): return 1;
            case("B"):
            case("N"):
                return 3;
            case("R"): return 5;
            case("Q"): return 9;
            default: return 0;
        }
    }

}
