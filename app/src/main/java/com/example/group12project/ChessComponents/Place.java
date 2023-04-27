package com.example.group12project.ChessComponents;

public class Place {

    private int x;
    private int y;
    private Piece p;

    public Place(Piece pin, int xco, int yco){
        x = xco;
        y = yco;
        p = pin;
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public void set_piece(Piece pin){
        this.p = pin;
    }

    public void set_location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Piece get_piece(){
        return p;
    }
}
