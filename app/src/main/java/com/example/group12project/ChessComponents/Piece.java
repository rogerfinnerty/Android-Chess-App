package com.example.group12project.ChessComponents;

import java.util.*;

public abstract class Piece {

    private String player;
    private String type;
    private boolean dead = false;

    public Piece(String pl){
        player = pl;
    }

    public boolean is_dead(){
        return dead;
    }

    public String get_player(){
        return player;
    }

    public String get_type(){
        return type;
    }

    public boolean set_type(String t){
        if(t != "K" && t != "Q" && t != "B" && t != "R" && t != "P" && t != "N"){
            return false;
        }
        else{
            type = t;
            return true;
        }
    }

    public boolean can_move(Board b, Place start, Place end){
        if((start.get_x() == end.get_x()) && (start.get_y() == end.get_y())){
            return false;
        }
        if(end.get_piece() != null && start.get_piece().get_player() == end.get_piece().get_player()){
            return false;
        }
        return true;
    };

}
