package javaForTest;

import java.util.Random;

public class RandomEmail {

	public static String email() {
		Random rand = new Random();
		return "autotesting" + rand.nextInt(999999) + "@gmail.com";

	}

}
