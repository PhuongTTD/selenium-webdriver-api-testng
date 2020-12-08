package webdriver;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Button_Radio_Checkbox {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUsername = By.cssSelector("#login_username");
	By loginPassword = By.cssSelector("#login_password");
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		Assert.assertFalse(isElementEnable(loginButton));
		driver.findElement(loginUsername).sendKeys("phuong@gmail.com");
		driver.findElement(loginPassword).sendKeys("12345678");
		sleepInSecond(2);
		Assert.assertTrue(isElementEnable(loginButton));
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		removeDisabledAttributedByJS(loginButton);
		sleepInSecond(5);
		Assert.assertTrue(isElementEnable(loginButton));
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("(//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert'])[1]")).getText(), "Thông tin này không thể để trống");
	}
	
	@Test
	public void TC_02_Checkbox_Select_Deselect_All() {
		driver.get("https://automationfc.github.io/multiple-fields");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (WebElement checkbox : checkboxes) {
			checkToCheckboxOrRadio(checkbox);
			sleepInSecond(2);
		}
		
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
		}
		
		for (WebElement checkbox : checkboxes) {
			UncheckToCheckbox(checkbox);
			sleepInSecond(2);
		}
		
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(isElementSelected(checkbox));
		}
	}
	@Test
	public void TC_03_Checkbox_Default() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"))));
		UncheckToCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"))));
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input"))));
	}
	
	
	@Test
	public void TC_04_Checkbox_Default() {
		
	}
	@Test
	public void TC_05_Radio() {
		
	}
	
	
	
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isElementEnable(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		}else {
			System.out.println("Element is disabled");
			return false;
		}
			
	}
	
	public boolean isElementSelected(By by){
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element is selected");
			return true;
		}else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	public boolean isElementSelected(WebElement element){
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		}else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	public void removeDisabledAttributedByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void checkToCheckboxOrRadio(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void UncheckToCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
