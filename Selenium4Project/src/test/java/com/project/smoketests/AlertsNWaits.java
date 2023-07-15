package com.project.smoketests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v109.page.model.Frame;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pages.BaseClass;

public class AlertsNWaits extends BaseClass {

	public void alertsTest() {
		
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//JS Alert
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		
		Alert jsAlert = driver.switchTo().alert();

		jsAlert.accept();  
		
			if(driver.getPageSource().contains("You successfully clicked an alert"))
				System.out.println("##### clicked alert");
		//JS Confirm
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		Alert jsConfirm = driver.switchTo().alert();
		//Frame frame1 = driver.switchTo().frame(frameElement)		
		jsConfirm.dismiss();
		
			if(driver.getPageSource().contains("You clicked: Cancel"))
				System.out.println("##### You clicked: Cancel");
		//JS Prompt
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
		Alert jsPrompt = driver.switchTo().alert();
		jsPrompt.sendKeys("Learning automation");
		jsPrompt.accept();
			if(driver.getPageSource().contains("You entered: Learn Automation"))
				System.out.println("##### You entered: Learn Automation");
	}

	public void waitsTest() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/checkboxes");
//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement checkbox = driver.findElement(By.id("checkbox"));
//Explicit wait		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		
		checkbox.click();
		Thread.sleep(3000); //hard wait, not recommended to use in real project
//Fluent Wait
		
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver> (driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(org.openqa.selenium.NoSuchElementException.class);
				
		fluentWait.until(ExpectedConditions.elementToBeClickable(checkbox));
		checkbox.click();
		
		//Thread.sleep(3000);
	}
}
