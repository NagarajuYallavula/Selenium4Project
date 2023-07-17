package com.project.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver = null;
	public String baseUrl = "https://demo.nopcommerce.com/";
	
	@Parameters("browser")
	@BeforeTest
	public void setupTest(String br) {
		
		try{
			switch (br){

			case "chrome":
				ChromeOptions optsChrome = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				optsChrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
				
				driver = new ChromeDriver(optsChrome);
				break;
			case "firefox":
				FirefoxOptions optsFirefox = new FirefoxOptions();
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(optsFirefox);
				break;
			case "edge":
				EdgeOptions optsEdge = new EdgeOptions();
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(optsEdge);
				break;
			default:
				throw new RuntimeException("Invalid browser");
			}
		}
		catch(Exception e){
			System.out.println();
			System.out.println(e);
		}
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDownTest() {
		
		driver.close();
		driver.quit();
	}
	
	public void takeScreenshot(WebDriver driver, String tName) throws IOException{
		try{
						
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			String pathScreenshot = System.getProperty("user.dir") + "/test-output/" + "/Screenshots/" + tName + ".png";
			File tgtFile = new File(pathScreenshot);
			FileUtils.copyFile(srcFile, tgtFile);
		}
		catch(IOException e){
			e.printStackTrace();
			//log.info(e.getMessage());//added for logging
		}
	}
}
