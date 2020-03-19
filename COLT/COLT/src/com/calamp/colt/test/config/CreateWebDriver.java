package com.calamp.colt.test.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.calamp.colt.utils.GetData;

public class CreateWebDriver {
	//initialize web driver and launch the URL
	public static WebDriver instance(WebDriver driver) {
		//GetData.fromProperties(filename,key)
		if (driver == null) {
			String browserName = GetData.fromProperties("config", "browser");
			Integer  timeouts = new Integer(GetData.fromProperties("config", "timeouts"));
			String url = GetData.fromProperties("config", "URL");
			
			if(browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./browser-drivers/geckodriver.exe"); // webdriver.firefox.marionette
				driver = new FirefoxDriver();
				
			} else if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./browser-drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				System.err.println("Improper Browser Selection");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(timeouts, TimeUnit.SECONDS);
			driver.get(url);
		}
		return driver;
	}
}
