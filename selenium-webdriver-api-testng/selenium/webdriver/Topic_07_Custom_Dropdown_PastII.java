package webdriver;

import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Custom_Dropdown_PastII {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String[] firstmonths = {"January", "February", "March"};
	String[] secondmonths = {"January", "February", "March", "August", "October"};


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectitemincustomdropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Hockey");
		Assert.assertEquals(getAngularSelectedValuebyJS(), "Hockey");
	}
	
	
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectitemincustomdropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span[@class='text']", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
	}
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectitemincustomdropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "Second Option");
	}
	
	@Test
	public void TC_04_Edit() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectiteminEditdropdown("//input[@class='search']","//div[@role='listbox']//span[@class='text']","Armenia");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Armenia']")).isDisplayed());
	}
	
	
	@Test
	public void TC_05_MultipleSelect() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		SelectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", firstmonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSeleted(firstmonths));
		
		driver.navigate().refresh();
		
		SelectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", secondmonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSeleted(secondmonths));
		
		
	}
	
	
	public String getAngularSelectedValuebyJS() {
		return (String) jsExecutor.executeScript("return document.querySelector (\"select[name='games'] option\").text");
		
	}
	
	
	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond  * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectitemincustomdropdown(String parentXpath, String allitemXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allitemXpath)));
		for (int i = 0; i < allItems.size(); i++) {
			String actualText = allItems.get(i).getText().trim();
			if (actualText.equals(expectedText)) {
				allItems.get(i).click();
				break;
			}
		}
	}
	
	public void selectiteminEditdropdown(String editableXpath, String allitemXpath, String expectedValueItem) {
		driver.findElement(By.xpath(editableXpath)).sendKeys(expectedValueItem);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allitemXpath)));
		for (int i = 0; i < allItems.size(); i++) {
			String actualText = allItems.get(i).getText().trim();
			if (actualText.equals(expectedValueItem)) {
				allItems.get(i).click();
				break;
			}
		}
	}
	
	
	public void SelectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValuItem){
		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		for (WebElement childElement : allItems) {
			for (String item : expectedValuItem) {
				if (childElement.getText().equals(item)) {
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected =" + itemSelected.size());
					if (expectedValuItem.length == itemSelected.size()) {
						break;
					}
				}
				
			}
		}
	}
	
	public boolean areItemSeleted(String[] itemSeletedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSeleted = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']//span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		if (numberItemSeleted <=3 && numberItemSeleted > 0) {
			for (String item : itemSeletedText) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		}else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+ numberItemSeleted + " of 12 selected']")).isDisplayed();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
