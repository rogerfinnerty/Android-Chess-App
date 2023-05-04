package com.example.group12project.ChessComponents;

import android.widget.TextView;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesTest extends TestCase {

    public void testPlaces_between() {
        Coordinates s1 = new Coordinates(0,0);
        Coordinates s2 = new Coordinates(0,7);
        List<Coordinates> between = new ArrayList<>();
        between.add(new Coordinates(0,1));
        between.add(new Coordinates(0,2));
        between.add(new Coordinates(0,3));
        between.add(new Coordinates(0,4));
        between.add(new Coordinates(0,5));
        between.add(new Coordinates(0,6));

        List<Coordinates> between_func = Coordinates.places_between(s1, s2);
        for(int i = 0; i < between.size(); i++){
            assertEquals(between.get(i).X(), between_func.get(i).X());
            assertEquals(between.get(i).Y(), between_func.get(i).Y());
        }
    }

    public void testTestEquals() {
        Coordinates s1 = new Coordinates(0,0);
        Coordinates s2 = new Coordinates(0,7);
        Coordinates s3 = new Coordinates(0, 0);

        assertTrue(s1.equals(s3));
        assertFalse(s2.equals(s1));
    }

    public void testIsWhite() {
        assertTrue(new Coordinates(0,0).isWhite());
        assertTrue(new Coordinates(0,2).isWhite());
        assertTrue(new Coordinates(0,4).isWhite());
        assertTrue(new Coordinates(7,7).isWhite());


        assertFalse(new Coordinates(1,0).isWhite());
        assertFalse(new Coordinates(0,3).isWhite());
        assertFalse(new Coordinates(4,3).isWhite());
        assertFalse(new Coordinates(6,7).isWhite());
    }
}