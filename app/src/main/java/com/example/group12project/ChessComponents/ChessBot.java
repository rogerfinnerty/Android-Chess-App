package com.example.group12project.ChessComponents;

import java.util.List;

public abstract class ChessBot {

    String player;

    public ChessBot() {
        player = "W";

    }

    public abstract List<Coordinates> make_move(Piece[][] board, Coordinates kingPos);



}
