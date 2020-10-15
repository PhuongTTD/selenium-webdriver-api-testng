package webdriver;

import org.testng.annotations.Test;

import nl.flotsam.xeger.Xeger;

import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class Topic_02_Exercise_Xpath_CSS {
	WebDriver driver;
	Random rand;
	String emailAddress, firstName, lastName;
	By txtUsername = By.name("login[username]");
	By txtPassword = By.name("login[password]");
	By btnSend = By.name("send");
	By lbEmailAlert = By.id("advice-required-entry-email");
	By lbPassAlert = By.id("advice-required-entry-pass");
	By myAccountButton = By.xpath(".//div[@class='footer']//a[(text()='My Account')]");
	
	@BeforeClass
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		rand = new Random();
		emailAddress = "autotest" + rand.nextInt(999999) + "@gmail.com";
		firstName = "Automation";
		lastName = "Testing";
	}

	@Test 
	public void TC_01() {
		// Click on link "My Account" to redirect on login page
		driver.findElement(myAccountButton).click();
		// Empty username and pass
		driver.findElement(txtUsername).sendKeys();
		driver.findElement(txtPassword).sendKeys();
		// Click login btn
		driver.findElement(btnSend).click();
		// Verify error mess
		WebElement emailalert = driver.findElement(lbEmailAlert);
		String actual1 = emailalert.getText();
		WebElement passalert = driver.findElement(lbPassAlert);
		String actual2 = passalert.getText();
		String expected = "This is a required field.";
		Assert.assertEquals(actual1, expected);
		Assert.assertEquals(actual2, expected);
	}

	@Test
	public void TC_02() {
		driver.findElement(myAccountButton).click();
//		String generateUsername = getRandomStringWithRegex("@gmail.com$");
//		System.out.println("ALOALO:"+generateUsername);
		driver.findElement(txtUsername).sendKeys("122342@2324.com");
		driver.findElement(txtPassword).sendKeys("1242464");
		driver.findElement(btnSend).click();
		WebElement emailalert = driver.findElement(lbEmailAlert);
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
//
//	@Test
//	public void TC_04() {
//		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
//		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
//		driver.findElement(By.name("login[password]")).sendKeys("1243242343");
//		driver.findElement(By.name("send")).click();
//		WebElement emailalert = driver.findElement(By.xpath("//li[@class='error-msg']/child::*/li"));
//		String actual = emailalert.getText();
//		String expected = "Invalid login or password.";
//		Assert.assertEquals(actual, expected);
//	}
	
	@Test
	public void TC_05() {
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("12345678");
		driver.findElement(By.id("confirmation")).sendKeys("12345678");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		WebElement successmsg = driver.findElement(By.cssSelector(".success-msg li"));
		String actual = successmsg.getText();
		String expected = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(actual, expected);
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(firstName));
		Assert.assertTrue(contactInformation.contains(lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed());
	}
	
	@Test
	public void TC_06() {
		driver.findElement(By.xpath(".//div[@class='footer']//a[(text()='My Account')]")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.name("login[username]")).sendKeys("kekoyeu@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12345678");
		driver.findElement(By.name("send")).click();
		WebElement successmsg = driver.findElement(By.cssSelector(".page-title h1"));
		WebElement hellomsg = driver.findElement(By.cssSelector(".hello strong"));
		String actual = successmsg.getText();
		String actual1 = hellomsg.getText();
		String expected = "My Dashboard";
		Assert.assertEquals(actual, expected);
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(firstName));
		Assert.assertTrue(contactInformation.contains(lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
	}
		
	@AfterClass
	public void afterMethod() {
		driver.quit();
	}	
	
}
