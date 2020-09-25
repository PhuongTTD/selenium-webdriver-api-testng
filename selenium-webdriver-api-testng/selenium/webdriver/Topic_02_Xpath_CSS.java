package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

@Test
public class Topic_02_Xpath_CSS {
	WebDriver driver;
  public void TC_01() {
	  driver = new FirefoxDriver();
	  driver.get("https://vi-vn.facebook.com/");
	  driver.findElement(By.id("u_0_2")).click();
	  driver.findElement(By.id("email")).sendKeys("tranphuong@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("1234");
	  driver.findElement(By.name("login")).click();
	  int linknumber = driver.findElements(By.tagName("input")).size();
	  System.out.println("ALOALO: "+linknumber);
	  driver.findElement(By.linkText("Messenger")).click();
	  driver.quit();
  }
}
