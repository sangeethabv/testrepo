package com.calamp.colt.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	WebDriver driver;
	
	public DashboardPage(WebDriver driver){
		this.driver = driver;
	}
	public void waitForSearchBoxToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".search_options")));
	}
	public WebElement getLogoutButton() {
		return driver.findElement(By.linkText("Log Out"));
	}
	public WebElement getSearchButton(){
		return driver.findElement(By.id("search_button"));
	}
	public WebElement getSearchText(){
		return driver.findElement(By.id("keyword"));
	}
	public WebElement getIncSubAcc(){
		return driver.findElement(By.cssSelector("span[value='all_subs']"));
	}
	public WebElement getThisAcc(){
		return driver.findElement(By.cssSelector("span[value='this_account']"));
	}
	public WebElement getDeviceFilter(){
		return driver.findElement(By.cssSelector("#device_filter_select_button"));
	}
	public WebElement getDeviceFilterOption(){
		return driver.findElement(By.id("All"));
	}
	public void waitForDevicesToLoad(){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".devices_tab")));
	}
}
