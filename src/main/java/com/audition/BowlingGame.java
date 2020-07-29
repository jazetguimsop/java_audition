package com.audition;

public class BowlingGame {

	private static final int MAX_ROLLS = 21;
	private static final int FRAME_COUNT = 10;
	private static final int MAX_SPIN_SCORE = 10;
	
	private static final String STRIKE = "X";
	private static final String SPARE = "/";
	private static final String MISS = "-";

	private int[] rolls = new int[MAX_ROLLS];
	private int rollIndex = 0;

	
	public int fetchScore(String frameScores) {
		if (null == frameScores || frameScores.length() == 0) 
			return 0;
		else {
			String[] eachFrameScores = frameScores.split(" ");
			for (int i = 0; i < eachFrameScores.length; i++) {
				String frame = eachFrameScores[i];
				addStrike(frame);
				addFirstRollMiss(frame);
				addSecondRollMiss(frame);
				addSpare(frame, i, eachFrameScores.length);
				addIndependentSpinScores(frame);
			}
		}
		return score();
	}
	
	private void addStrike(String frame) {
		if (frame.contains(STRIKE)) 
			roll(10);
	}
	
	private void addFirstRollMiss(String frame) {
		if (frame.length() > 1 && charToString(frame.charAt(0)).equals(MISS)) {
			roll(0);
			roll(Integer.parseInt(charToString(frame.charAt(1))));
		}
	}
	
	private void addSecondRollMiss(String frame) {
		if (frame.length() > 1 && charToString(frame.charAt(1)).equals(MISS)) {
			roll(Integer.parseInt(charToString(frame.charAt(0))));
			roll(0);
		}
	}
	
	private void addSpare(String frame, int index, int totalLength) {
		if (frame.contains(SPARE)) {
			int firstRoll = Integer.parseInt(charToString(frame.charAt(0)));
			roll(firstRoll);
			roll(MAX_SPIN_SCORE - firstRoll);
			if (index == (totalLength - 1))
				roll(Integer.parseInt(charToString(frame.charAt(2))));
		}
	}
	
	private void addIndependentSpinScores(String frame) {
		if (frame.length() > 1 && !frame.contains(SPARE) && !frame.contains(MISS)) {
			roll(Integer.parseInt(charToString(frame.charAt(0))));
			roll(Integer.parseInt(charToString(frame.charAt(1))));
		}
	}

	private void roll(int roll) {
		if (rollIndex < MAX_ROLLS)
			rolls[rollIndex++] = roll;
	}

	private int score() {
		int score = 0;
		int index = 0;
		for (int i = 0; i < FRAME_COUNT; i++) {
			score += rollIndex(index++);
			
			if (isStrike(index - 1))
				score += rollIndex(index, index + 1);
			else {
				score += rollIndex(index++);
				if (isSpare(index - 2))
					score += rollIndex(index);
			}
		}
		return score;
	}

	private boolean isStrike(int index) {
		return rolls[index] == 10;
	}

	private boolean isSpare(int index) {
		return rolls[index] + rolls[index + 1] == 10;
	}

	private int rollIndex(int... indexes) {
		int sum = 0;
		for (int i = 0; i < indexes.length; i++)
			sum += rolls[indexes[i]];
		return sum;
	}
	
	private String charToString(char c) {
		return String.valueOf(c);
	}
}