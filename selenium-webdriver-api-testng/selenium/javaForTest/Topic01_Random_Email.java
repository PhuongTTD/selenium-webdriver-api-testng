package javaForTest;

import java.util.Random;

public class Topic01_Random_Email {

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println("autotesting" + rand.nextInt(999999) + "@gmail.com");

	}

}
