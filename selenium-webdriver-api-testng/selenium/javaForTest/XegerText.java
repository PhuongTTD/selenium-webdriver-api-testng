package javaForTest;

import com.mifmif.common.regex.Generex;

import nl.flotsam.xeger.Xeger;

public class XegerText {

	public String test() {
//		String generateUsername = getRandomStringWithRegex("[A-Za-z0-9][gmail]{8,}");
		String generateUsername1 = getRandomStringWithGenerex("[A-Za-z0-9]{8,16}");
		return generateUsername1;
		
	}
	
	public String getRandomStringWithRegex(String regex) {
		String result = "";
		Xeger xeger = new Xeger(regex);
		result = xeger.generate();
		return result;
	}
	
	public String getRandomStringWithGenerex(String regex) {
		String result = "";
		Generex generex = new Generex(regex);
		result = generex.random();
		return result;
	}

	public static void main(String[] args) {
		XegerText test = new XegerText();
		System.out.println("ALo: " + test.test());
	}
}
