package com.calamp.colt.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.calamp.colt.test.smoke.Login;

public class CaptureScreenshot extends Login {
	
	 public static void takeSnapShot(WebDriver driver,String fileWithPath,ITestResult result) throws Exception{
		 TakesScreenshot scrShot =((TakesScreenshot)driver);
		 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		 File DestFile= new File(fileWithPath+result.getName()+".png");
		 FileUtils.copyFile(SrcFile, DestFile);
	 }
		
}
