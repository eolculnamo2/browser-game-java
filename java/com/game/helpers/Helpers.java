package com.game.helpers;

import java.util.concurrent.ThreadLocalRandom;

public class Helpers {
	public static int getRandomInt(int range) {
		return ThreadLocalRandom.current().nextInt(0, range+1);
	}
}