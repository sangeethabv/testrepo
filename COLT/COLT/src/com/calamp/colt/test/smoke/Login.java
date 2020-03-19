package com.calamp.colt.test.smoke;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calamp.colt.test.config.CreateWebDriver;
import com.calamp.colt.ui.LoginPage;
import com.calamp.colt.utils.CaptureScreenshot;

public class Login {
	//Create WebDriver and Page object model class references and don't initialize 
	protected static WebDriver driver;
	//WebDriver driver;
	LoginPage login;
	@BeforeMethod()
	public void setUp(){
		driver = CreateWebDriver.instance(driver);
		login = new LoginPage(driver);
	}
	@Test
	public void LoginTC01(){
		login.waitForLoginPageToLoad();
		login.getUserName().sendKeys("aricent_ca_admin");
		login.getPassword().sendKeys("Colt123@");
		login.getSignInButton().click();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) { 
		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus()) {
			try  {
					CaptureScreenshot.takeSnapShot(driver, "./screenshots/",result);
					System.out.println("Screenshot taken");
			} 
			catch (Exception e) {
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
	}
}
