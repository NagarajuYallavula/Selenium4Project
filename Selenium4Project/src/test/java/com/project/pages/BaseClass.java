package com.project.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

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
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project.utilities.ExtentReport;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver = null;
	public String baseUrl = "https://demo.nopcommerce.com/";
	
	public ExtentReports extent = new ExtentReports();
	public ExtentSparkReporter sparkReport;
	public ExtentTest logger;
	
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
	
	@BeforeSuite
	public void setupSuite() {
		
		extentReportSetup();
	}
	
	@AfterSuite
	public void tearDownSuite() {
		System.out.println("Extent report created");
		extent.flush();
	}
	
	public void extentReportSetup() {
		
		System.out.println("Extent Report setup...");
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/Report/"+repName);
		
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Host Name", "localHost");;
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Tester");
		
		sparkReport.config().setDocumentTitle("Selenium  Test Automation");
		sparkReport.config().setReportName("Test Summary Report");
		sparkReport.config().setTheme(Theme.STANDARD);
		
		extent.attachReporter(sparkReport);
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
