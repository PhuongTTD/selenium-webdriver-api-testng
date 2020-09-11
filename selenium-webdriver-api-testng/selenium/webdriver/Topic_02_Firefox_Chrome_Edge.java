package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
	WebDriver driver;

	@Test
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();

		driver.get("https://vi-vn.facebook.com/");

		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", "/Users/k3mshiro/TestAutomation/02 - Selenium API/selenium-webdriver-api-testng/browerDrivers/chromedriver");

		driver = new ChromeDriver();

		driver.get("https://vi-vn.facebook.com/");

		driver.quit();
	}

	@Test
	public void TC_03_Run_On_Edge() {
		System.setProperty("webdriver.edge.driver", "/Users/k3mshiro/TestAutomation/02 - Selenium API/selenium-webdriver-api-testng/browerDrivers/msedgedriver");
		driver = new EdgeDriver();
		
		driver.get("https://vi-vn.facebook.com/");

		driver.quit();

	}
}
