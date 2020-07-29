package com.audition;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlingGameTest {

    private BowlingGame bowling;

    @Before
    public void setUp() {
        bowling = new BowlingGame();
    }

    @Test
    public void testAllStrikes() {
		assertEquals(300, bowling.fetchScore("X X X X X X X X X X X X"));
    }
    
    @Test
    public void testAllMissInSecondSpin() {
    	assertEquals(90, bowling.fetchScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"));
    }
    
    @Test
    public void testAllMissInFirstSpin() {
    	assertEquals(90, bowling.fetchScore("-9 -9 -9 -9 -9 -9 -9 -9 -9 -9"));
    }
    
    @Test
    public void testAllSpares() {
    	assertEquals(150, bowling.fetchScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"));
    }
    
    @Test
    public void testRandomSpins() {
    	assertEquals(70, bowling.fetchScore("52 52 52 52 52 52 52 52 52 52"));
    }
}