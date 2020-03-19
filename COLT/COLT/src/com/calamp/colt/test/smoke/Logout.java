package com.calamp.colt.test.smoke;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calamp.colt.test.config.CreateWebDriver;
import com.calamp.colt.ui.DashboardPage;
import com.calamp.colt.utils.CaptureScreenshot;

public class Logout extends Login{
	
	DashboardPage dashboard;
	@BeforeMethod
	public void setUp(){
		CreateWebDriver.instance(driver);
		dashboard = new DashboardPage(driver);
	}
	
	@Test
	public void logOut(){
		dashboard.getLogoutButton().click();
	}
	
	@AfterMethod
	public void tearDoen(ITestResult result){
		if(ITestResult.FAILURE == result.getStatus()){
			try{
				CaptureScreenshot.takeSnapShot(driver, "/screenshots/", result);
				System.out.println("Snaphot captured");
				driver.quit();
			}catch(Exception e){
				System.out.println("Error in capturing screenshot");
				driver.quit();
			}
		}
	}

}
