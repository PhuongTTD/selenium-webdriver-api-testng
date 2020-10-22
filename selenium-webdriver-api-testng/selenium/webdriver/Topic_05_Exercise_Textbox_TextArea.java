package webdriver;

import org.testng.annotations.Test;

import javaForTest.RandomEmail;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Exercise_Textbox_TextArea {
	WebDriver driver;
	String email, userID, password, loginURL;
	String name, dobinput, doboutput, address, city, state, PIN, phone, customerID;
	String editaddress, editcity, editstate, editPIN, editphone, editemail;

	By nameBy = By.name("name");
	By dobBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By PINBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");

		email = RandomEmail.email();
		loginURL = driver.getCurrentUrl();

		name = "Donnal Trump";
		address = "846 Oxford Street";
		city = "Baton Rouge";
		state = "Louisiana";
		PIN = "708945";
		phone = "2254722127";
		dobinput = "10/01/1954";
		doboutput = "1954-10-01";

		editaddress = "150 Rutledge Road";
		editcity = "Monticello";
		editstate = "Minnesota";
		editPIN = "555655";
		editphone = "7639579351";
		editemail = RandomEmail.email();
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		String WelcomePage = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();

		Assert.assertEquals(WelcomePage, "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameBy).sendKeys(name);
		driver.findElement(dobBy).sendKeys(dobinput);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(PINBy).sendKeys(PIN);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),doboutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(nameBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobBy).getAttribute("value"), doboutput);
		Assert.assertEquals(driver.findElement(addressBy).getText(), address);
		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(PINBy).getAttribute("value"), PIN);
		Assert.assertEquals(driver.findElement(phoneBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		
		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(editaddress);
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(editcity);
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(editstate);
		driver.findElement(PINBy).clear();
		driver.findElement(PINBy).sendKeys(editPIN);
		driver.findElement(phoneBy).clear();
		driver.findElement(phoneBy).sendKeys(editphone);
		driver.findElement(emailBy).clear();
		driver.findElement(emailBy).sendKeys(editemail);
		
		driver.findElement(By.name("sub")).click();
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Customer details updated Successfully!!!");

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),doboutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editaddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editcity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editstate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editphone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editemail);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
