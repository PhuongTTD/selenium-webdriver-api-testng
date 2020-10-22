package webdriver;

import org.testng.annotations.Test;

import javaForTest.RandomEmail;

import org.testng.annotations.BeforeClass;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, date, month, year, email, pass, confirmpass, company;

	By maleBy = By.id("gender-male");
	By firstnameBy = By.id("FirstName");
	By lastnameBy = By.id("LastName");
	By dateBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
		
		firstName = "Phuong";
		lastName = "Tran";
		date = "3";
		month = "March";
		year = "1913";
		email = RandomEmail.email();
		pass = "12345678";
		confirmpass = "12345678";
		company = "FPT";
	}

	@Test
	public void Register() {
		driver.findElement(maleBy).click();
		driver.findElement(firstnameBy).sendKeys(firstName);
		driver.findElement(lastnameBy).sendKeys(lastName);

		select = new Select(driver.findElement(dateBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(yearBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(pass);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmpass);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertTrue(driver.findElement(maleBy).isSelected());
		Assert.assertEquals(driver.findElement(firstnameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastnameBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(dateBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(monthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(yearBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
