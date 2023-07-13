package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

	public WebDriver ldriver = null;
	
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Log in')]")
	WebElement login_link;
	
	@FindBy(id="Email")
	WebElement email_txt;
	
	@FindBy(id="Password")
	WebElement password_txt;
	
	@FindBy(xpath="//button[contains(text(),'Log in')]")
	WebElement login_btn;
	
	@FindBy(id="small-searchterms")
	WebElement search_txt;
	
	@FindBy(xpath="//button[contains(text(),'Search')]")
	WebElement search_btn;
	
	@FindBy(xpath="//a[@href='/']")
	WebElement nopCommerce_img;
	
	public void login(String email, String password) {
		
		login_link.click();
		
		email_txt.clear();
		email_txt.sendKeys(email);
		password_txt.clear();
		password_txt.sendKeys(password);
		
		login_btn.click();
	}
	
	public void searchStore(String searchString) {
		search_txt.click();
		search_txt.sendKeys(searchString);
		search_btn.click();
	}
	
	public boolean verifySearchResults(String result) {
		boolean appear = false;
		
		return appear; 
	}
	
	public void navigateToHomePage() {
		nopCommerce_img.click();
	}
}
