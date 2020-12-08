package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Default_MultiDropdown {

	WebDriver driver;
	Select select;

	String[] array = { "Linh", "Phuong", "Lam", "Nga", "Diem" };

	List<String> names;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		names = new ArrayList<>();
		for(int i = 0; i<array.length; i++) {
			names.add(array[i]);
		}
		names.add("Viet");
		names.remove(0);
		names.add(3, "Phuong");

		for (int i = 0; i < 10; i++) {
			if (i % 3 == 2) {
				names.add("Linh");
			}
		}

	}
	// Mang co du 5 phan tu hay ko

	// Verify text cua phan tu thu ba

	// Verify mang co chua Viet khong
	
	// Co bao nhieu Linh trong mang
	
	
	@Test
	public void TC_01() {
		Assert.assertEquals(names.size(), 5);
	}

	@Test
	public void TC_02() {
		String item3 = names.get(2);
		Assert.assertEquals(item3, "Lam");
	}

	@Test
	public void TC_03() {
		Assert.assertFalse(names.contains("Viet"));
	}
	
	@Test
	public void TC_04() {
		List<String> items = new ArrayList<String>();
		
		int dem =0;
		for (String item : names) {
			//dem = 0;
		 //item -> ten || Linh - dem +1;
			if(item.equals("Linh")) {
				dem = dem+1;
			}
		}
		System.out.println("ALOALOALAOALOALAOALOAOALOAOA:  "+dem);
		Assert.assertEquals(dem, 2);
	}

	@Test
	public void MultiDropdown() throws InterruptedException {

		List<String> itemtext = new ArrayList<String>();
		itemtext.add("Manual");
		itemtext.add("Mobile");
		itemtext.add("Security");
		
		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		
		select.selectByVisibleText("Manual");
		Thread.sleep(2000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(2000);
		select.selectByVisibleText("Security");
		Thread.sleep(2000);

		
		List<WebElement> itemselected = select.getAllSelectedOptions();
		Assert.assertEquals(itemselected.size(), 3);
		List<String> itemSelectedtext = new ArrayList<String>();
		
		
		for (WebElement item : itemselected) {
			itemSelectedtext.add(item.getText());
			System.out.print(item.getText());
		}
		
		Assert.assertTrue(itemSelectedtext.contains("Manual"));
		Assert.assertTrue(itemSelectedtext.contains("Mobile"));
		Assert.assertTrue(itemSelectedtext.contains("Security"));
		
		Assert.assertEquals(itemSelectedtext, itemtext);

		System.out.println(names.toString());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
