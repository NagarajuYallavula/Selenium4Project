package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver = null;
	public String baseUrl = "https://demo.nopcommerce.com/";
	
	@BeforeTest
	public void setupTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDownTest() {
		
		driver.quit();
		driver.close();
	}
	
	public void takeScreeenshot() {
		
	}
}
