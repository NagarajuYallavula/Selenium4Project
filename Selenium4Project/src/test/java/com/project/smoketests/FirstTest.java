package com.project.smoketests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class FirstTest {

	public WebDriver driver = null;
	public String baseUrl = "https://the-internet.herokuapp.com/";

	@BeforeTest
	public void setupTest() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to(baseUrl);
	}
	
	@AfterTest
	public void tearDownTest() {
		
		driver.quit();
		driver.close();
	}

//	@Test(groups= {"api"})
	public void loginTest1() {
		
		driver.navigate().to(baseUrl + "/login");
		
		driver.findElement(By.id("username")).sendKeys("testuser111");
		driver.findElement(By.id("password")).sendKeys("pass123!");
		driver.findElement(By.xpath("//i[contains(text(),'Login')]")).click();
		
		String flashMsg = driver.findElement(By.id("flash")).getText();
			
		assertEquals(flashMsg.trim(), "Your username is invalid!");
		System.out.println();
	}
	
//	@Test
	public void googleTest() {
		
		driver.navigate().to("https://www.google.co.in/");
		driver.findElement(By.name("q")).sendKeys("Selenium Tutorial");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		driver.getTitle().contains("Selenium");
	}
	
//	@Test
	public void dropdownTest() {
		
		driver.navigate().to(baseUrl + "/dropdown");
		
		WebElement list = driver.findElement(By.id("dropdown"));
		Select drpSelect = new Select(list);
		
		try {
			drpSelect.selectByIndex(1);
			Thread.sleep(3000);
			drpSelect.selectByVisibleText("Option 2");
			Thread.sleep(3000);
			drpSelect.selectByValue("1");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void exceptionHandlingTest() {
		
		int x = 5;
		try {
			double y = x/0;
			System.out.println("### value of y: "+y);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void throwsException() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("throws exception");
	}
}