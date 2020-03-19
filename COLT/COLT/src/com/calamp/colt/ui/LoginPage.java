package com.calamp.colt.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	//Page Object Model
	//1. Create web driver reference and don't initialize it
	WebDriver driver;
	
	//2.Define a public constructor with web driver as parameter and initialize the web driver
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	//3.Define a method for every webelement present in a web page.
	//The methods should be non static and public and should return webelement
	public WebElement getUserName(){
		return driver.findElement(By.id("login_username"));
	}
	public WebElement getPassword(){
		return driver.findElement(By.name("password"));
	}
	public WebElement getSignInButton(){
		return driver.findElement(By.cssSelector(".login_button"));
	}
	public WebElement getForgotPassword(){
		return driver.findElement(By.id("forgot_password"));
	}
	public WebElement getSignUpToday(){
		return driver.findElement(By.cssSelector(".signup_button"));
	}
	
	public WebElement getLogoutButton() {
		return driver.findElement(By.linkText("Log Out"));
	}
	//4. Define a public method to wait for page to load, use explicit wait
	public void waitForLoginPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".login_wrap")));
	}
	public void waitForDasboardPageToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".main_wrap")));
	}
	
}
