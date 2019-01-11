package com.game.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class Helpers {
	public static int getRandomInt(int range) {
		return ThreadLocalRandom.current().nextInt(0, range+1);
	}
	
	public static String convertJsonToString(InputStream body) {
		BufferedReader br = new BufferedReader( new InputStreamReader(body));
		String json = "";
		
		if(br != null) {
			try {
				json = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return json;
	}
}