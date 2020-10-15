package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_01_Firefox_Chrome_Edge {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");

	@Test
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		driver.get("https://vi-vn.facebook.com/");

		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		
		//1: relative path
		System.setProperty("webdriver.chrome.driver", "./browerDrivers/chromedriver");

		//2: project location
		System.setProperty("webdriver.chrome.driver", project_location +  "/browerDrivers/chromedriver");

		driver = new ChromeDriver();

		driver.get("https://vi-vn.facebook.com/");

		driver.quit();
	}

}
