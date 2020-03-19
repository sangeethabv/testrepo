package com.calamp.colt.test.regression;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calamp.colt.test.config.CreateWebDriver;
import com.calamp.colt.test.smoke.Login;
import com.calamp.colt.ui.DashboardPage;
import com.calamp.colt.ui.LoginPage;
import com.calamp.colt.utils.CaptureScreenshot;

public class Dashboard extends Login {
	DashboardPage dashboard;
	
	@BeforeMethod
	public void setUp(){
		driver    = CreateWebDriver.instance(driver);
		dashboard = new DashboardPage(driver);
	}
	
	@Test
	public void AdvancedSearch() throws InterruptedException{
		dashboard.waitForSearchBoxToLoad();
		Thread.sleep(10000);
		dashboard.getDeviceFilter().click();
		dashboard.getDeviceFilterOption().click();
		dashboard.getSearchButton().click();
		dashboard.waitForDevicesToLoad();
	}
	@AfterMethod
	public void tearDown(ITestResult result){
		if(ITestResult.FAILURE == result.getStatus()){
			try{
				CaptureScreenshot.takeSnapShot(driver, "/screenshots/", result);
				System.out.println("Screenshot taken");
			} catch(Exception e){
				System.out.println("Error in taking screenshot"+e.getMessage());
			}
		}
		
	}
}
