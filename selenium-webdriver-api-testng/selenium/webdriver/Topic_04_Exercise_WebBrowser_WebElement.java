package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Exercise_WebBrowser_WebElement {

	WebDriver driver;
	By myAccountButton = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createAnAccountButton = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com");
	}

	@Test
	public void TC_01_VerifyURL() {
		driver.findElement(myAccountButton).click();
		String loginURL = driver.getCurrentUrl();
		Assert.assertEquals(loginURL, "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(createAnAccountButton).click();
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_VerifyTitle() {
		driver.findElement(myAccountButton).click();
		String loginTitle = driver.getTitle();
		Assert.assertEquals(loginTitle, "Customer Login");
		driver.findElement(createAnAccountButton).click();
		String RegisterTitle = driver.getTitle();
		Assert.assertEquals(RegisterTitle, "Create New Customer Account");

	}

	@Test
	public void TC_03_NavigateFunction() {
		driver.findElement(myAccountButton).click();
		driver.findElement(createAnAccountButton).click();
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		String loginURL = driver.getCurrentUrl();
		Assert.assertEquals(loginURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		String RegisterTitle = driver.getTitle();
		Assert.assertEquals(RegisterTitle, "Create New Customer Account");

	}

	@Test
	public void TC_04_GetPageSource() {
		driver.findElement(myAccountButton).click();
		String loginSource = driver.getPageSource();
		Assert.assertTrue(loginSource.contains("Login or Create an Account"));
		driver.findElement(createAnAccountButton).click();
		String registerSource = driver.getPageSource();
		Assert.assertTrue(registerSource.contains("Create an Account"));
		driver.manage().logs();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
