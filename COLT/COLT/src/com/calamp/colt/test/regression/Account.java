package com.calamp.colt.test.regression;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calamp.colt.test.config.CreateWebDriver;
import com.calamp.colt.test.smoke.Login;
import com.calamp.colt.ui.AccountPage;
import com.calamp.colt.ui.LoginPage;
import com.calamp.colt.utils.CaptureScreenshot;

public class Account extends Login {
	AccountPage account;
	
	@BeforeMethod
	public void setUp(){
		driver = CreateWebDriver.instance(driver);
		account = new AccountPage(driver);
	}
	
	@Test
	public void createAcc() throws InterruptedException {
		
		account.waitForDasboardPageToLoad();
		Thread.sleep(10000);
		account.getMyAccountTab().click();
		Thread.sleep(5000);
		account.getCreateSubAccountButton().click();
		//Select parent account
		account.getChangeThisLink().click();
		Thread.sleep(1000);
		account.getSelectSubAccAlert();
		account.getSearchTextbox().sendKeys("Herndon QA");
		account.getSelectAccountButton().click();
		account.getAccountName().sendKeys("SeleniumTest1");
		//Select language from drop down
		account.getLanguageDropdown().click();
		driver.findElement(By.id("en")).click();
		
		account.getFirstName().sendKeys("Selenium");
		account.getLastName().sendKeys("Test");
		account.getEmail().sendKeys("navya.r@gmail.com");
		account.getAddress1().sendKeys("Bangalore");
		account.getAddress2().sendKeys("Bangalore");
		account.getAirtimeStoreCheckBox().click();
		account.getCommandRetryCheckbox().click();
		account.getHardwareStoreCheckBox().click();
		account.getSkipInstallProcessCheckBox().click();
		account.getReceiveEmailCheckbox().click();
		account.getMandatoryOdometerCheckbox().click();
		account.getLocValidationCheckboxx().click();
		account.getSkysmartCheckbox().click();
		Thread.sleep(5000);
		account.getCity().sendKeys("Bangalore");
		account.getZip().sendKeys("12345");
		account.getPhone1().sendKeys("1234567890");
		account.getPhone2().sendKeys("1234123412");
		//country drop down
		account.getCountryDropDown().click();
		driver.findElement(By.xpath("//a[@value='US']")).click();
		Thread.sleep(5000);
		
		//State drop down
		account.getStateDropdown().click();
		driver.findElement(By.linkText("California")).click();
		
		account.getCustomSupportCheckBox().click();
		Thread.sleep(5000);
		account.getAddAccountButton().click();
		account.waitForSuccessAlert();
	}
	
	// It will execute after every test execution 
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
