package webdriver;

import org.testng.annotations.Test;

import nl.flotsam.xeger.Xeger;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_02_Exercise_Xpath_CSS {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01() {
		// Click on link "My Account" to redirect on login page
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		// Empty username and pass
		driver.findElement(By.xpath(".//input[@name='login[username]']")).sendKeys();
		driver.findElement(By.xpath(".//input[@name='login[password]']")).sendKeys();
		// Click login btn
		driver.findElement(By.xpath(".//button[@name='send']")).click();
		// Verify error mess
		WebElement emailalert = driver.findElement(By.id("advice-required-entry-email"));
		String actual1 = emailalert.getText();
		WebElement passalert = driver.findElement(By.id("advice-required-entry-pass"));
		String actual2 = passalert.getText();
		String expected = "This is a required field.";
		Assert.assertEquals(actual1, expected);
		Assert.assertEquals(actual2, expected);
	}

	@Test
	public void TC_02() {
		// Click on link "My Account" to redirect on login page
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		// Empty username and pass
//		String generateUsername = getRandomStringWithRegex("[^$@gmail.com]\\{1,16}");
		driver.findElement(By.xpath(".//input[@name='login[username]']")).sendKeys("12341234@1234.123");
		driver.findElement(By.xpath(".//input[@name='login[password]']")).sendKeys("1242464");
		// Click login btn
		driver.findElement(By.xpath(".//button[@name='send']")).click();
		// Verify error mess
		WebElement emailalert = driver.findElement(By.id("advice-validate-email-email"));
		String actual1 = emailalert.getText();
		String expected = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(actual1, expected);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
//	public String getRandomStringWithRegex(String regex) {
//		String result ="";
//		Xeger xeger = new Xeger(regex);
//		result = xeger.generate();
//		return result;
//	}

}
