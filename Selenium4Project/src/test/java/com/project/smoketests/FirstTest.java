package com.project.smoketests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class FirstTest {

	public static WebDriver driver = null;
	public String baseUrl = "https://the-internet.herokuapp.com/";
		
	/*public void main(String[] args) {
		loginTest1();
	}*/
	
	@BeforeTest
	public static void setupTest() {
		// TODO Auto-generated method stub
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@AfterTest
	public static void tearDownTest() {
		
		driver.quit();
		driver.close();
	}

	@Test
	public void loginTest1() {
		
		driver.navigate().to(baseUrl);
		driver.navigate().to(baseUrl + "/login");
		
		driver.findElement(By.id("username")).sendKeys("testuser111");
		driver.findElement(By.id("password")).sendKeys("pass123!");
		driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
		
		String flashMsg = driver.findElement(By.id("flash")).getText();
	
		
		assertEquals(flashMsg.trim(), "Your username is invalid!");
		System.out.println();
	}
	
	public void dropdownTest() {
		
		Select drpSelect = new Select(driver.findElement(By.id("dropdown")));
		drpSelect.selectByIndex(1);
		
		}
}