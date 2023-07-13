package com.project.smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.project.pages.BaseClass;

public class SpecialElements extends BaseClass {

	@Test
	public void actionsTest() {
	
		driver.get("https://www.myntra.com/");
		this.navMethod("Women", "Jeans");
	}
	
	public void navMethod(String menu, String item) {
		
		Actions actions = new Actions(driver);
		
		WebElement menuItem = driver.findElement(By.xpath("//a[contains(text(),"+ menu +") and @class = 'desktop-main']"));
		//actions.
		actions.moveToElement(menuItem);
		actions.build().perform();
		
		driver.findElement(By.linkText(item)).click();
	}
	
	@Test
	public void keysMethod() {
		
		driver.get("https://the-internet.herokuapp.com/key_presses");
		Actions actions = new Actions(driver);
		
		WebElement target = driver.findElement(By.id("target"));
		
		target.sendKeys(Keys.ENTER);
		actions.build().perform();
		WebElement result = driver.findElement(By.id("result"));
		System.out.println("### ENTER: " + result.getText());
		
		actions.sendKeys(Keys.TAB);
		actions.build().perform();
		System.out.println("### Tab: " + result.getText());
	}
}
