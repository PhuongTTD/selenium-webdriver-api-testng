package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Custom_Dropdown_PastI {

	WebDriver driver;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectitemincustomdropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond  * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectitemincustomdropdown(String parentXpath, String allitemXpath, String expectedText) {
		//  1 - Click vào 1 element bất kì của dropdown để cho nó xổ hết tất cả các item ra
		driver.findElement(By.xpath(parentXpath)).click();
		//	2 - Chờ cho all các item được load lên
		//	3 - Lưu nó lại vào 1 List chứa những element 
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allitemXpath)));
		//	4 - Lấy ra text của từng element
		//	5 - Kiểm tra nó có bằng với cái text cần tìm hay ko
		//	6 - Nếu như có thì click vào  - thoát khỏi vòng lặp
		//	Nếu như ko có thì tiếp tục duyệt những item khác cho đến khi hết all item
		for (int i = 0; i < allItems.size(); i++) {
			String actualText = allItems.get(i).getText();
			if (actualText.equals(expectedText)) {
				allItems.get(i).click();
				break;
			}
		}
		
		//for each
//		for (WebElement item : allItems) {
//			String actualText = item.getText();
//			if (actualText.equals(expectedText)) {
//				item.click();
//				break;
//			}
//		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
