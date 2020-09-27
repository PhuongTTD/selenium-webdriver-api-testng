package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
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
		driver.findElement(By.name("login[username]")).sendKeys();
		driver.findElement(By.name("login[password]")).sendKeys();
		// Click login btn
		driver.findElement(By.name("send")).click();
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
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
//		String generateUsername = getRandomStringWithRegex("[^$@gmail.com]\\{1,16}");
		driver.findElement(By.name("login[username]")).sendKeys("1242@23432.123");
		driver.findElement(By.name("login[password]")).sendKeys("1242464");
		driver.findElement(By.name("send")).click();
		WebElement emailalert = driver.findElement(By.id("advice-validate-email-email"));
		String actual1 = emailalert.getText();
		String expected = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(actual1, expected);
	}
	
	@Test
	public void TC_03() {
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.name("send")).click();
//		WebElement emailalert = driver.findElement(By.id("advice-required-entry-email"));
//		String actual1 = emailalert.getText();
		WebElement passalert = driver.findElement(By.id("advice-validate-password-pass"));
		String actual2 = passalert.getText();
		String expected = "Please enter 6 or more characters without leading or trailing spaces.";
//		Assert.assertEquals(actual1, expected);
		Assert.assertEquals(actual2, expected);
	}

	@Test
	public void TC_04() {
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1243242343");
		driver.findElement(By.name("send")).click();
		WebElement emailalert = driver.findElement(By.xpath("//li[@class='error-msg']/child::*/li"));
		String actual = emailalert.getText();
		String expected = "Invalid login or password.";
		Assert.assertEquals(actual, expected);
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
