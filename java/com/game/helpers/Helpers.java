package com.game.helpers;

public class Helpers {
	public static int getRandomInt(int range) {
		double random = Math.random() * range;
		return (int) random;
	}
}
