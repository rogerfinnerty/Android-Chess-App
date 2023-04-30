package com.example.group12project.ChessComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomChessBot extends ChessBot {

    public RandomChessBot(){
        super();
    }

    @Override
    public List<Coordinates> make_move(Piece[][] board, Coordinates kingPos) {
        List<Coordinates> end_pos = new ArrayList<>();
        List<Coordinates> start_pos = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != null && Objects.equals(board[i][j].get_player(), this.player)) {
                    List<Coordinates> possible = board[i][j].allPossibleMoves(board, new Coordinates(i,j), kingPos);
                    for(Coordinates c : possible){
                        //pretend as if move was made, then check if king is NOW in check?

                        end_pos.add(new Coordinates(i,j));
                        start_pos.add(c);
                    }
                }
            }
        }

        Random rand = new Random();
        int choice = rand.nextInt(end_pos.size());
        return Arrays.asList(start_pos.get(choice), end_pos.get(choice));
    }
}
