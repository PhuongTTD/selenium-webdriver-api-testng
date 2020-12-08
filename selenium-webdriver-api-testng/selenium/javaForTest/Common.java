package javaForTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
	WebDriver driver;
	WebDriverWait explicitWait; 
	
	
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

}
}
