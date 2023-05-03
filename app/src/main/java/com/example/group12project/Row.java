package com.example.group12project;

public class Row implements Comparable<Row>{
    private String name;
    private Integer wins;
    public Row(String n, Integer w){
        this.name = n;
        this.wins = w;
    }
    public String getName(){ return name; }
    public int getWins(){ return wins; }
    public void setName(String newName){ this.name = newName;}
    public void setWins(Integer w){ this.wins = w; }

    @Override
    public int compareTo(Row r){
        return this.wins.compareTo(r.getWins());
    }
}
