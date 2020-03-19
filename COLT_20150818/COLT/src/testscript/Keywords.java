package testscript;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Random; 

//import org.jdom2.contrib.beans.StringConverter;
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import junit.framework.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beust.jcommander.converters.StringConverter;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
//import org.openqa.selenium.firefox.FirefoxDriver;
import util.RandomUtilities;
import util.TestUtil;


public class Keywords extends DriverScript {
	/**
	 * firebugPath constant is being used to install firebug in webdriver
	 * Profile.
	 **/
	public static final String FIREBUGPATH = "C:\\FF_Profile\\firebug-2.0.4-fx.xpi";
	//public static final String FIREBUGPATH = "%APPDATA%\\Mozilla\\Firefox\\Profiles\\caebqnbh.default\\extensions\\firebug@software.joehewitt.com.xpi";
	//public static final String FIREBUGPATH = "C:\\Users\\mariela.vera\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\caebqnbh.default\\extensions\\firebug@software.joehewitt.com.xpi";
	/**
	 * My new changes
	 * firePath constant is becheck_SearchedAirIDing used to install firepath in
	 * webdriver Profile.
	 */
	public static final String FIREPATH = "C:\\FF_Profile\\firepath-0.9.7-fx.xpi";
	//public static final String FIREPATH = "%APPDATA%\\Mozilla\\Firefox\\Profiles\\caebqnbh.default\\extensions\\FireXPath@pierre.tholence.com.xpi";

	String name = driver.findElement(
			By.className("checkbox asset_check checked")).getText();

	static List<String> returnvalues = new ArrayList<String>();
	private static String AlphaName = RandomUtilities.getRandomUpperCase()
			.toString()
			+ RandomUtilities.getRandomWord()
			+ RandomUtilities.getRandomUpperCase();
	private static String Email;
	private static String AirID;
	private static String ESN;
	private static Double QTY = 0.0;
	private static Double UnitCost = 0.0;
	private static String Device;
	private static Double ShippingCost = 0.0;
	private static Integer four_DigitNumber = 0;
	private static String element_Name;
	private static String transferID;
	private static Integer rowsTable = 0;
	private static Integer rowsTotalTables = 0;
	private static Integer numPages = 0;
	private static List<Boolean> checkBoxesState = new ArrayList<Boolean>();
	private static Integer options = 0;
	private static Boolean emptyTable = false;

	// private static Date pastDate = null;
	// private static String vehicleName = "";
	public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) 
	{
		return new Function<WebDriver, WebElement>() 
		{
			public WebElement apply(final WebDriver driver) 
			{
				return driver.findElement(locator);
			}
		};
	}

	public static void popUpClose() { //static method because it is not necessary to instance the class (new class) to call the method
		System.out.println("Closing pop-up");
		Alert alert = driver.switchTo().alert();
		
		if (alert != null) {
			alert.dismiss();
		}
		// set implicit wait
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		// return "Pass";
	}

	public static String verifyCheckboxStatus() {

		System.out.println("Execute function isCheckboxSelected " + object);
		// WebElement checkBox =
		// driver.findElement(By.xpath(OR.getProperty(object)));
		try {
			WebElement element = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(values[0]))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			String checkBox = element.getAttribute("value");
			String classcheckbox= element.getAttribute("class");
			System.out.println(checkBox);
			if(values[1].contains("1"))
			{
				if ((checkBox!=null)&&(!object.contains("class")))
				{
					if (checkBox.equals("on")||classcheckbox.contains("checked"))
					{
						return "Pass";
					}
					else
					{
						return "Fail. The CheckBox was : " + checkBox;
					}
				}
				else
				{
					if (classcheckbox.contains("checked"))
					{
						return "Pass";
					}
					else
					{
						return "Fail. The CheckBox was : " + classcheckbox;
					}
				}
				
			}else if(values[1].contains("0"))
			{
				if ((checkBox!=null)&&(!object.contains("class")))
				{
					if (checkBox.equals("off"))
					{
						return "Pass";
					}
					else
					{
						return "Fail. The CheckBox was : " + checkBox;
					}
				}
				else
				{
					if (!classcheckbox.contains("checked"))
					{
						return "Pass";
					}
					else
					{
						return "Fail. The CheckBox was : " + classcheckbox;
					}
				}
				
				
			}
			else
			{
				return "Fail -> Please add in the object the status to verify";
			}
			
		} catch (Throwable t) {
			System.out.println("Error while isCheckboxSelected "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}

	public static String checkByStateOrValidChecks() 
	{
		try {
			System.out.println("Execute checkByStateOrValidChecks " + object);
			WebElement element = null;
			Boolean result = null;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(values[0]))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (checkBoxesState.isEmpty())// always the list empty
			{
				List<WebElement> checkBoxes = element.findElements(By
						.className(values[1]));
				if (checkBoxes.size() > 0) {
					/*
					 * for (WebElement checkbox : checkBoxes) { WebElement input
					 * = checkbox.findElement(By.tagName("input")); WebElement
					 * check = checkbox.findElement(By.tagName("span")); if
					 * (input.getAttribute("value").equals("on")) {
					 * check.click(); checkBoxesState.add(false); } else {
					 * check.click(); checkBoxesState.add(true); } }
					 */
					// better solution
					for (WebElement checkbox : checkBoxes) {
						// WebElement input =
						// checkbox.findElement(By.tagName("input"));
						WebElement check = checkbox.findElement(By
								.tagName("span"));
						if (check.getAttribute("class").contains("checked")) {
							check.click();
							checkBoxesState.add(false);
						} else {
							check.click();
							checkBoxesState.add(true);
						}
					}// until here the better solution
				} else {
					return "Fail -> There is No elements in the list";
				}
			} else {
				List<WebElement> checkBoxes = element.findElements(By
						.className(values[1]));
				Integer numberofCheckboxes = checkBoxes.size();
				Integer i = 0;
				Boolean equal = false;
				if (checkBoxes.size() > 0) {
					for (WebElement checkbox : checkBoxes) {
						WebElement span = checkbox.findElement(By
								.tagName("span"));
						Boolean state = null;

						if (span.getAttribute("class").contains("checked")) {
							state = true;
						} else {
							state = false;
						}
						/*
						 * if (input.getAttribute("value").equals("on")) { state
						 * = true; } else { state = false; }
						 */
						if (state.equals(checkBoxesState.get(i))) {
							equal = true;
						} else {
							return "Fail -> The edition was not applied correctly checkbox number: "
									+ i + "should be:" + checkBoxesState.get(i);
						}
						i++;
					}

				} else {
					return "Fail -> There is No Roles in the list";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error while isCheckboxSelected "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String saveRandomCheckedList()
	{
		try {
			System.out.println("Execute saveRandomCheckedList " + object);
			WebElement element = null;
			Boolean result = null;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 																			
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(values[0]))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (checkBoxesState.isEmpty())// always the list empty
			{
				//List<WebElement> checkBoxes = element.findElements(By.className(values[1]));
				int count = 0;
				List<WebElement> checkBoxes = element.findElements(By.name(values[1]));
				for (WebElement checkbox : checkBoxes) 
				{
					if(checkbox.isDisplayed())
					{
						count++;
						//System.out.println(driver.findElement(By.tagName("label")).getAttribute("text"));
					}
				}
				System.out.println(count);
				Random random = new Random();
				int pos = random.nextInt(count);
				System.out.println(pos);
				count = 0;
				//pos=19;
				//System.out.println(checkBoxes.get(pos).getAttribute("text").toString());
				for (WebElement checkbox : checkBoxes) 
				{
					if(checkbox.isDisplayed())
					{
						count++;
						System.out.println(count);
						if(count==pos)
						{
							checkbox.click();
							break;
						}
						//System.out.println(driver.findElement(By.tagName("label")).getAttribute("text"));
					}
				}
				//checkBoxes.get(pos).click();
				
				for (WebElement checkbox : checkBoxes) 
				{
					if(checkbox.isDisplayed())
					{
						if (checkbox.getAttribute("class").contains("checked")) 
						{
							checkBoxesState.add(true);
						} 
						else
						{
							checkBoxesState.add(false);
						}
					}
				}
			}
			else
			{
				return "Fail -> The List is not empty";
			}
			
		} catch (Throwable t) {
			System.out.println("Error while saveRandomCheckedList "+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}
	
	public static String compareWithSavedList()
	{
		System.out.println("Execute compareWithSavedList " + object);
		WebElement element = null;
		Boolean result = false;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 																			
		if (object.contains("_ID")) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			element = wait.until(presenceOfElementLocated(By.id(OR
					.getProperty(values[0]))));
		} else if (object.contains("_xpath")) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			element = wait.until(presenceOfElementLocated(By.xpath(OR
					.getProperty(values[0]))));
		} else if (object.contains("_css")) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			element = wait.until(presenceOfElementLocated(By.cssSelector(OR
					.getProperty(values[0]))));
		} else {
			return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
		}
		
		try
		{
			int count = 0;
			List<WebElement> checkBoxes = element.findElements(By.name(values[1]));
			Boolean state = false;
			for (WebElement checkbox : checkBoxes) 
			{
				if(checkbox.isDisplayed())
				{
					
					if (checkbox.getAttribute("class").contains("checked")) 
					{
						state =true;
					} 
					else
					{
						state = false;
					}
					if(state == checkBoxesState.get(count))
					{
						result = true;
					}
					else
					{
						return "Fail -> The edition was not applied correctly checkbox number: "
						+ count + "should be:" + checkBoxesState.get(count);
					}
					count++;
					System.out.println(count);
					//System.out.println(driver.findElement(By.tagName("label")).getAttribute("text"));
				}
			}
		}
		catch (Throwable t)
		{
			System.out.println("Error while compareWithSavedList "+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		if (result)
		{
			return "Pass";
		}
		else
		{
			return "Fail -> The edition was not performed correctly";
		}
	}
	
	public static void Refreshbrowser() {
		System.out.println("Execute refresh browser " + object);

		try {
			// set implicit wait
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

			driver.navigate().refresh();
			Thread.sleep(600);
		} catch (Throwable t) {
			System.out.println("Error while clickButton " + t.getMessage());
			return;
		}
	}

		
	public static String navigate() {
		System.out.println("Launching new browser " + object);
		if (driver == null) {
			if (CONFIG.getProperty("testBrowser").equals("Firefox")) {
				try {
					DesiredCapabilities cap = DesiredCapabilities.firefox();
					FirefoxProfile profile = new FirefoxProfile();
					// New code
					/*
					 * FirefoxBinary binary = new FirefoxBinary(new
					 * File(firefoxBinaryPath)); WebDriver webDriver = new
					 * FirefoxDriver(binary, profile);
					 */

					profile.addExtension(new File(FIREBUGPATH));
					profile.addExtension(new File(FIREPATH));
					profile.setEnableNativeEvents(false);
					cap.setCapability("firefox_profile", profile);
					cap.setJavascriptEnabled(false);//maye added
					profile.setPreference("browser.tabs.autoHide", "true");
					// set default start page to zero
					profile.setPreference("extensions.firebug.currentVersion",
							"2.0.4"); // avoid startup screen
					profile.setPreference("extensions.firepath.currentVersion",
							"0.9.7"); // avoid startup screen
					profile.setPreference("extensions.firebug.console."
							+ "enableSites", true); // enable console
					// profile.setPreference("extensions.lastAppVersion",
					// "3.5.11");
					driver = new FirefoxDriver(profile);
					//driver = new FirefoxDriver();
				} catch (Throwable t) {

				}
			} else if (CONFIG.getProperty("testBrowser").equals("IE")) {
				try{
					File file = new File("C:/IEDriver/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					/*DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
					ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					ieCapabilities.setJavascriptEnabled(false);*/
					DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
					ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					//ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
					driver = new InternetExplorerDriver(ieCapabilities);
					//driver.setJavasEnabled(false);
				}
				catch(Throwable t)
				{
					String a = t.getMessage();
					return t.getMessage();
				}
				/*DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				ieCapabilities.setJavascriptEnabled(false);
				//To work with Internet Explorer*/
				
				//driver = new InternetExplorerDriver(ieCapabilities);
				// driver. setJavasEnabled(false);
			
				
			} 
			else 
			{
				File file = new File("C:/Chrome/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new ChromeDriver();

			}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(CONFIG.getProperty(object));
		driver.manage().window().maximize();
		return "Pass";
	}

	public static String clickRadioButton() {
		System.out.println("Execute clickRadioButton " + object);
		try {
			String[] temp;
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			// String delimiter = ", ";
			temp = data.split(", ");
			System.out.println("Radio Name : " + temp[0]
					+ " And Radio index : " + temp[1]);

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			List<WebElement> radio = driver.findElements(By.name(temp[0]));
			System.out.println(radio.get(0).getAttribute("value") + "----"
					+ radio.get(0).getAttribute("checked"));
			System.out.println(radio.get(1).getAttribute("value") + "----"
					+ radio.get(1).getAttribute("checked"));
			System.out.println(radio.get(2).getAttribute("value") + "----"
					+ radio.get(2).getAttribute("checked"));
			int a = Integer.parseInt(temp[1]);
			radio.get(a).click();
			Thread.sleep(600);

		} catch (Throwable t) {
			System.out
					.println("Error while clickRadioButton " + t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String inputRandomAlphaName() {
		System.out.println("Execute inputRandomAlphaName " + object);
		try {
			AlphaName = RandomUtilities.getRandomUpperA().toString()
					+ RandomUtilities.getRandomWord()
					+ RandomUtilities.getRandomUpperCase()+ RandomUtilities.getRandomUpperCase();
			System.out.println(AlphaName);
			WebElement element = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}

			element.sendKeys(AlphaName);
			Thread.sleep(600);
		} catch (Throwable t) {
			System.out.println("Error while inserting Random Password "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String inputRandomConfirmAlphaName() {
		System.out.println("Execute inputRandomConfirmAlphaName " + object);
		try {
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebElement element = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.sendKeys(AlphaName);
			Thread.sleep(600);
		} catch (Throwable t) {
			System.out.println("Error while inserting Random Password "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String inputRandom4DigitNumber() {
		System.out.println("Execute inputRandom4DigitNumberID " + object);
		try {
			four_DigitNumber = RandomUtilities.getRandom4DigitNumber();
			System.out.println(four_DigitNumber);
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.sendKeys(four_DigitNumber.toString());
			return "Pass";

		} catch (Throwable t) {
			System.out.println("Error while inserting Random 4 Digit Number "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}

	public static String inputConfirm4DigitNumber() {
		System.out
				.println("Execute inputConfirmRandom4DigitNumberID " + object);
		try {
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			System.out.println(four_DigitNumber);
			element.sendKeys(four_DigitNumber.toString());
			return "Pass";
		} catch (Throwable t) {
			System.out
					.println("Error while inserting Comfirm Random 4 Digit Number "
							+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}

	}

	public static String inputQTY() {
		try {
			System.out.println("Executing inputQTY: " + object);
			System.out.println(object);
			String[] values = object.split("#");

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement table = wait.until(presenceOfElementLocated(By.id(OR
					.getProperty(values[0])))); // Wait for element to Load
			// WebElement table =
			// driver.findElement(By.id(OR.getProperty(values[0])));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			if (rows.size() > 0) {
				UnitCost = Double.parseDouble(rows.get(0)
						.findElement(By.id(OR.getProperty(values[1])))
						.getAttribute("value"));
				List<WebElement> inputs = rows.get(0).findElements(
						By.xpath(OR.getProperty(values[2])));
				if (inputs.size() > 0) {
					String data = TestData.getCellData(currentTest,
							dataColumnName, testRepeat);
					QTY = Double.parseDouble(data);
					for (WebElement input : inputs) {
						if (input.isDisplayed()) {
							input.sendKeys(QTY.toString());
							return "Pass";
						}
					}
				} else {
					return "Fail -> There is no TextBox in the row";
				}
			} else {
				return "Fail -> There is No rows in the table";
			}
		} catch (Throwable t) {
			System.out.println("error in inputQTY: ");
			return "Fail" + t.getMessage();
		}
		return "Fail";
	}

	public static String getTextElement()

	{
		System.out.println("Execute getTextElement " + object);
		try {
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebElement element = null;
			Boolean result = false;
			char firststr = 5;
			char secondstr=1;
			String value = null;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			List<WebElement> elements= null; 
			if (object.contains("_ID"))
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));
				elements = driver.findElements(By.id(OR.getProperty(values[0])));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));
				elements = driver.findElements(By.xpath(OR.getProperty(values[1])));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));
				elements = driver.findElements(By.cssSelector(OR.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			for(WebElement ele: elements)
			{
				if(ele.isDisplayed())
				{
					value = element.getText();
					if(value.isEmpty())
					{
						value= element.getAttribute("value");
					}
					break;
				}
			}
			
			System.out.println(value);
			if(object.contains("date"))
			{
				System.out.println("The value to save is :" + value);
				if(value.charAt(3) =='0')
				{
					value = element.getText().substring(0, 2) + "/" + element.getText().substring(4, element.getText().length());
					System.out.println("The value with the day fixed is :" + value);
					element_Name = value;
					result = true;
				}
				
				else if (value.charAt(0) == '0')
				{
					value = value.substring(1);
					//String value = element.getText().replace('0', ' ');
					System.out.println("The value with the month fixed is :" + value);
					element_Name = value;
					result = true;
				}
				else
				{
					element_Name = value;
					result = true;
				}
				
			}
			else
			{
				element_Name = value;
				result = true;
			}
			if (result)
			{
				return "Pass";
			}
			else
			{
				return "Failse -> The element_Name was not able to be saved";
			}
			
		} catch (Throwable t) {
			System.out.println("Error while getting the Name of the element "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}

	public static String saveTextElement()

	{
		System.out.println("Execute getTextElement " + object);
		char firststr = 5;
		char secondstr=1;
		String value = null;
		try {
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			Boolean result = false;
			WebElement element = null;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(values[0]))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			
			value = element.getText();
			
			if(object.contains("date"))
			{
				System.out.println("The value to save is :" + value);
				if(value.charAt(3) =='0')
				{
					value = value.substring(0, 2) + "/" + value.substring(4, value.length());
					System.out.println("The value with the day fixed is :" + value);
				}
				if (value.charAt(0) == '0')
				{
					value = value.substring(1);
					//String value = element.getText().replace('0', ' ');
					System.out.println(value);
					//result = TestData.setCellData(currentTest, dataColumnName,testRepeat, value);
				}
				
					result = TestData.setCellData(currentTest, dataColumnName,testRepeat, value);
					System.out.println("The value saved is: " + value);
			}
			else
			{
				result = TestData.setCellData(currentTest, dataColumnName,testRepeat, value);
				System.out.println("The value saved is: " + value);
			}
			if (result) 
			{
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error while igetting the Name of the element "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Fail->Error while executing";
	}

	public static String inputPasswordAccount() {
		System.out.println("Execute inputPasswordAccount " + object);
		try {

			WebElement element = null;
			System.out.println(element_Name);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element_Name = element_Name + "123!";
			element.sendKeys(element_Name);
			return "Pass";
		} catch (Throwable t) {
			System.out.println("Error while inputPasswordAccount "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}

	public static String inputElementName() {
		System.out.println("Execute inputElementName " + object);
		try {
			System.out.println(element_Name);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.sendKeys(element_Name);
			return "Pass";
		} catch (Throwable t) {
			System.out
					.println("Error while inputElementName " + t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}

	public static String clearInput() {
		System.out.println("Execute clearInput " + object);
		try {
			
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.clear();
			return "Pass";
		} catch (Throwable t) {
			System.out.println("Error while clearing the field "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
	}
	
	public static String clearInputIfDisplayed () {
		System.out.println("Execute clearInputIfDisplayed " + object);
		try {
			
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			List <WebElement> elements= null;
			if (object.contains("_ID")) {
				//WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
				//		20);
			/*	elements = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));*/
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.id(OR.getProperty(object)));

				
			} else if (object.contains("_xpath")) {
				//WebDriverWait wait = new WebDriverWait(driver, /* seconds= */60);
				/*elements = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));*/
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.xpath(OR.getProperty(object)));

			} else if (object.contains("_css")) {
				//WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
				//		60);
			/*	elements = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));*/
				
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.cssSelector(OR.getProperty(object)));

			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			
			//*
			for(WebElement element:elements)
			{
				if(element.isDisplayed())
				{

			element.clear();
			return "Pass";
			
				}
			}
		} catch (Throwable t) {
			System.out.println("Error while clearing the field "
					+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Fail -> Error while Clear Input";
		

	}

	

	public static String validateDeviceCostID() {
		try {
			String[] values = object.split("#");
			System.out
					.println("Executing validateDeviceTotalCostID: " + object);
			// driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			List<WebElement> tables = driver.findElements(By.id(OR
					.getProperty(values[0])));
			for (WebElement table : tables) {
				if (table.isDisplayed()) {
					List<WebElement> rows = table
							.findElements(By.tagName("tr"));
					if (rows.size() > 0) {
						Double currentDeviceCost = Double.parseDouble(rows
								.get(0)
								.findElement(By.id(OR.getProperty(values[1])))
								.getText());
						System.out.println(currentDeviceCost);
						Double expected_cost = (QTY * UnitCost);
						System.out.println(expected_cost);
						DecimalFormat decimals = new DecimalFormat("0.00");
						expected_cost = Double.parseDouble(decimals
								.format(expected_cost));
						// Double rounding = Math.rint(expected_cost*1000/1000);
						// expected_cost =
						// RandomUtilities.Rounding2Decimals(expected_cost);
						if (currentDeviceCost.equals(expected_cost)) {
							return "Pass";
						} else {
							return "Fail -> The validation is not Correct: "
									+ expected_cost + "!=" + currentDeviceCost;
						}
					} else {
						return "Fail -> There is No Rows to Calculate Cost";
					}
				}
			}
		} catch (Throwable t) {
			System.out.println("error in verifyTotalTableID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String validateDeviceTotalCost() {
		try {
			String[] values = object.split("#");
			System.out
					.println("Executing validateDeviceTotalCostID: " + object);
			Double current_total = 0.0;
			Double expected_total = 0.0;
			List<WebElement> blockInfos = driver.findElements(By.className(OR
					.getProperty(values[0])));
			for (WebElement blockInfo : blockInfos) {
				if (blockInfo.isDisplayed()) {
					List<WebElement> infoTypes = blockInfo.findElements(By
							.className(OR.getProperty(values[1])));
					for (WebElement type : infoTypes) {
						if (type.isDisplayed()) {
							List<WebElement> info_Row = type.findElements(By
									.tagName("div"));

							String unit = info_Row.get(1).getAttribute("id");
							System.out.println(unit);

							if (!unit.contentEquals("units")
									&& !unit.contentEquals("total")) {
								expected_total = expected_total
										+ Double.parseDouble(info_Row.get(1)
												.getText());
								// System.out.println(expected_total);
							}
							if (unit.contentEquals("total")) {
								current_total = Double.parseDouble(info_Row
										.get(1).getText());
								// System.out.println(current_total);
							}
						}

					}
					DecimalFormat decimals = new DecimalFormat("0.00");
					Double total = Double.parseDouble(decimals
							.format(expected_total));
					if (current_total.equals(total)) {
						return "Pass";
					} else {
						return "Fail -> The validation is not Correct: "
								+ expected_total + "!=" + current_total;
					}
				}
			}

		} catch (Throwable t) {
			System.out.println("error in validateTotalCostID ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String editFolderID() {
		try {
			System.out.println("Executing editFolderID: " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println("folder selected to edit is: " + data);

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement folderList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load

			// WebElement folderList =
			// driver.findElement(By.id(OR.getProperty(object))); //getting the
			// dropdown
			List<WebElement> elements = folderList.findElements(By
					.className("folder"));
			for (WebElement element : elements) {
				String folderName = element.findElement(By.tagName("b"))
						.getText();
				if (folderName.contains(data)) {
					WebElement editLink = element.findElement(By
							.className("pencil"));
					editLink.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("error in editFolderID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String addDevice() {
		try {
			System.out.println("Executing addDevice: " + object);
			String[] objects = object.split("#");

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement devicesList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(objects[0])))); 
			List<WebElement> elements = devicesList.findElements(By
					.className("device"));
			int num=0;
			if (elements.size() > 0) {
				Device = elements.get(0).getText();
				for (WebElement element : elements) {
					WebElement addLink = element.findElement(By.className(OR
							.getProperty(objects[1])));
					addLink.click();
					Thread.sleep(600);
					num++;
					if(!object.contains("2Devices"))
					{
						return "Pass";
					}
					if(!(num<2))
					{
						return "Pass";
					}
					
				}
			} else {
				System.out.println("There is no Device that can be added");
				return "Fail";
			}
		} catch (Throwable t) {
			System.out.println("error in addDeviceID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String addDeviceID()// Folders TC
	{
		try {
			System.out.println("Executing addDeviceID: " + object);
			List<String> objects = new ArrayList<String>();
			String[] values = object.split("#");
			if (values.length > 1) {
				for (int i = 0; i < values.length; i++) {
					objects.add(values[i]);
					// System.out.println(objects.get(i));
				}
			}

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement devicesList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(objects.get(0))))); // Wait for element
															// to Load

			// WebElement devicesList =
			// driver.findElement(By.id(OR.getProperty(objects.get(0))));
			// //getting the dropdown
			List<WebElement> elements = devicesList.findElements(By
					.className(OR.getProperty(objects.get(1))));
			if (elements.size() > 0) {
				for (WebElement element : elements) {

					WebElement addLink = element.findElement(By.className(OR
							.getProperty(objects.get(2))));
					addLink.click();
					Thread.sleep(600);
					TestData.setCellData(currentTest, dataColumnName,
							testRepeat, element.getText());
					break;
				}
				return "Pass";
			} else {
				System.out.println("There is no Device that can be added");
				return "Fail";
			}
		} catch (Throwable t) {
			System.out.println("error in addDeviceID: ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String expandFolderID() {
		try {
			System.out.println("Executing expandFolderID: " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println("folder selected to edit is: " + data);

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement folderList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load

			// WebElement folderList =
			// driver.findElement(By.id(OR.getProperty(object))); //getting the
			// dropdown
			List<WebElement> elements = folderList.findElements(By
					.className("folder"));
			for (WebElement element : elements) {
				String folderName = element.findElement(By.tagName("b"))
						.getText();
				if (folderName.contains(data)) {
					WebElement expandLink = element
							.findElement(By.tagName("a"));
					expandLink.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("error in expandFolderID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String verifyDeviceID() {
		try {
			System.out.println("Executing verifyDeviceID: " + object);
			List<String> objects = new ArrayList<String>();
			String[] values = object.split("#");
			if (values.length > 1) {
				for (int i = 0; i < values.length; i++) {
					objects.add(values[i]);
					// System.out.println(objects.get(i));
				}
			}
			String deviceName = TestData.getCellData(currentTest,
					dataColumnName, testRepeat);
			System.out.println("Device to verify: " + deviceName);

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement folderList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(objects.get(0))))); // Wait for element
															// to Load

			// WebElement folderList =
			// driver.findElement(By.id(OR.getProperty(objects.get(0))));
			List<WebElement> devices = folderList.findElements(By.className(OR
					.getProperty(objects.get(1))));
			for (WebElement device : devices) {
				if (device.getText().contains(deviceName)) {
					return "Pass";
				}
			}
			return "Fail";
		} catch (Throwable t) {
			System.out.println("Error in Verify Device ID: ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String removeFolderID() {
		try {
			System.out.println("Executing removeFolderID: " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement folderList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load

			// WebElement folderList =
			// driver.findElement(By.id(OR.getProperty(object))); //getting the
			// dropdown
			List<WebElement> elements = folderList.findElements(By
					.className("folder"));
			for (WebElement element : elements) {
				String folderName = element.findElement(By.tagName("b"))
						.getText();
				if (folderName.contains(data)) {
					WebElement expandLink = element.findElement(By
							.className("remove"));
					expandLink.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("error in expandFolderID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String input() {
		try {
			System.out.println("Execute input " + object);
			System.out.println("Input Value -> " + OR.getProperty(object)); // Added
																			// by
																			// Prashant
																			// on
																			// June
																			// 25
			List <WebElement> elements = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (object.contains("_ID")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.id(OR.getProperty(object)));
				//elements = wait.until(presenceOfElementLocated(By.id(OR.getProperty(object))));
				
				
			} 
			else if (object.contains("_xpath")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.xpath(OR.getProperty(object)));
				//element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(object))));
			} 
			else if (object.contains("_css")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */10);
				elements = driver.findElements(By.cssSelector(OR.getProperty(object)));
				//element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(object))));
				
			} 
			else 
			{
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (!dataColumnName.isEmpty()) {
				System.out.println(dataColumnName);
				String data = TestData.getCellData(currentTest, dataColumnName,
						testRepeat);
				if (!data.isEmpty()) {
					
					for(WebElement element:elements)
					{
						if(element.isDisplayed())
						{
							element.sendKeys(data);
							return "Pass";
						}
					}
					
				} else {
					return "Fail -> The data is empty";
				}
			} else {
				if (object.contains("_AirID")) {
					for(WebElement element:elements)
					{
						if(element.isDisplayed())
						{
							element.sendKeys(AirID);
							return "Pass";
						}
					}
					
				} else if (object.contains("_ESN")) {
					for(WebElement element:elements)
					{
						if(element.isDisplayed())
						{
							element.sendKeys(ESN);
							return "Pass";
						}
					}
					
				} else if (object.contains("_Email")) {
					AlphaName = RandomUtilities.getRandomEmail();
					for(WebElement element:elements)
					{
						if(element.isDisplayed())
						{
							element.sendKeys(AlphaName);
							return "Pass";
						}
					}
					
				} else {
					return "Fail -> The object is not accurate, it should include: '_AirID', '_AlphaName', '_Email', or '_ESN";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error while input ");
			return "Fail ->" + t.getMessage();
		}
		 return "Fail -> Error while input";
	}

	public static String clickButton() {
		try {
			System.out.println("Execute clickButton " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(object))));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
				

			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));

			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			//element.sendKeys(Keys.ENTER);
			element.click();
			return "Pass";

		} catch (Throwable t) {
			System.out.println("Error while clickButton ");
			return "Fail ->" + t.getMessage();
		}

	}

		
	public static String clickButtonIfDisplayed() {
		try {
			System.out.println("Execute clickButtonIfDisplayed " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebElement element = null;
			List<WebElement> buttons = null;
			int i = 1;
			Boolean found = false;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
				buttons = driver.findElements(By.id(OR.getProperty(object)));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
				buttons = driver.findElements(By.xpath(OR.getProperty(object)));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
				buttons = driver.findElements(By.cssSelector(OR
						.getProperty(object)));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			while (i <= 20000 && !found) {
				for (WebElement button : buttons) {
					if (button.isDisplayed()) {
						Thread.sleep(600);
						button.click();
						found = true;
						break;

					}
					// System.out.println("buttonNotDisplayed");
				}
				System.out.println("Wait for " + i + " Sec");
				i++;
			}
			if (found) {
				return "Pass";
			} else {
				return "Fail -> Not able to find the Element";
			}
		} catch (Throwable t) {
			System.out.println("Error while clickButtonIfDisplayed");
			return "Fail ->" + t.getMessage();
		}
		// return "Fail";
	}

	public static String clickButtonIfExistAndDisplay() {
		try {
			System.out
					.println("Execute clickButtonIfExistAndDisplay " + object);
			WebElement button_displayed = null;
			Boolean displayed = null;
			WebElement element = null;

			List<WebElement> buttons = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						10);
				try {
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(object))));
					buttons = driver
							.findElements(By.id(OR.getProperty(object)));
				} catch (Throwable t) {
					displayed = false;
				}

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						10);
				try {
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(object))));
					buttons = driver.findElements(By.xpath(OR
							.getProperty(object)));
				} catch (Throwable t) {
					displayed = false;
				}

			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						10);
				try {
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(object))));
					buttons = driver.findElements(By.cssSelector(OR
							.getProperty(object)));
				} catch (Throwable t) {
					displayed = false;
				}
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (displayed == null) {
				for (WebElement button : buttons) {
					if (button.isDisplayed()) {
						Thread.sleep(1000);
						displayed = true;
						button_displayed = button;
						break;
					}
					// i++;
				}
				if (displayed != null) {
					button_displayed.click();
					Thread.sleep(600);
					return "Pass";
				} else {
					return "Pass";
				}
			} else {
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error while clickButtonIfExistAndDisplay ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String clickButtonIfExist() {
		try {
			System.out.println("Execute clickButtonIfExist " + object);
			WebElement button_displayed = null;
			Boolean displayed = false;
			WebElement element = null;

			List<WebElement> buttons = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
				buttons = driver.findElements(By.id(OR.getProperty(object)));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
				buttons = driver.findElements(By.xpath(OR.getProperty(object)));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
				buttons = driver.findElements(By.cssSelector(OR
						.getProperty(object)));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			for (WebElement button : buttons) {
				if (button.isDisplayed()) {
					Thread.sleep(1000);
					displayed = true;
					button_displayed = button;
					break;
				}
			}
			if (displayed == true) {
				
				button_displayed.click();
				return "Pass";
			} else {
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error while clickButtonIfExist ");
			return "Fail ->" + t.getMessage();
		}
		// return "Fail -> Error executing clickButtonIfExistAndDisplayed()";
	}

/*	public static String clickButtonDisplayedInstall() {
		System.out.println("Execute clickButtonDisplayedInstall " + object);
		String[] objects = object.split("#");
		WebElement button_SkipGPSTestID = null;
		WebElement button_Finish = null;
		WebElement button_Submit = null;
		WebElement button_Yes = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		
		boolean done = false;
		int counter = 0;
		if (!object.contains("#realDevice")) {
			while (!done) {
				try {
					counter++;
					System.out.println("Counter Number " + counter);
					WebDriverWait wait = new WebDriverWait(driver,  seconds= 20);
					button_SkipGPSTestID = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[0]))));
					
					if (button_SkipGPSTestID.isDisplayed()) {
						button_SkipGPSTestID.click();
						waitCOLTServer();
						Thread.sleep(1000);
						button_Yes = driver.findElement(By.xpath(OR
								.getProperty(objects[3])));
						button_Yes.click();
						waitCOLTServer();
						Thread.sleep(2000);
						button_Submit = driver.findElement(By.id(OR
								.getProperty(objects[2])));
						button_Submit.click(); // Button Finish from Functional
												// Testing
						button_Yes = driver.findElement(By.xpath(OR
								.getProperty(objects[3])));
						button_Yes.click();
						Thread.sleep(1000);
						done = true;
						return "Pass";
					}
					button_Finish = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[1]))));
					
					if (button_Finish.isDisplayed()) {
						button_Finish.click();
						waitCOLTServer();
						Thread.sleep(1000);
						button_Yes = driver.findElement(By.xpath(OR
								.getProperty(objects[3])));
						button_Yes.click();
						Thread.sleep(1000);
						done = true;
						return "Pass";

					}
					button_Submit = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[2]))));
					if (button_Submit.isDisplayed()) {
						button_Submit.click();
						waitCOLTServer();
						Thread.sleep(2000);
						button_Submit = driver.findElement(By.id(OR
								.getProperty(objects[2])));
						button_Submit.click();
						waitCOLTServer();
						button_Yes = driver.findElement(By.xpath(OR
								.getProperty(objects[3])));
						button_Yes.click();
						waitCOLTServer();
						done = true;
						return "Pass";
					}
					Thread.sleep(1000);
					if (counter > 1500) {
						return "Fail -> The TestDiagnostic was not completed, time waited: "
								+ counter;
					}

				} catch (Throwable t) {
					System.out.println("Error while clickButtonDisplayed ");
					return "Fail ->" + t.getMessage();
				}
			}
		} else {
			while (!done) {
				try {
					counter++;
					System.out.println("Counter Number " + counter);
					WebDriverWait wait = new WebDriverWait(driver,  seconds= 20);
					button_SkipGPSTestID = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[0]))));
					button_Finish = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[1]))));
					button_Submit = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[2]))));
					if (button_Submit.isDisplayed()) {
						button_Submit.click();
						Thread.sleep(2000);
						button_Submit = driver.findElement(By.id(OR
								.getProperty(objects[2])));
						button_Submit.click();
						button_Yes = driver.findElement(By.xpath(OR
								.getProperty(objects[3])));
						button_Yes.click();
						done = true;
						return "Pass";
					}
					//Thread.sleep(1000);
					if (counter > 1500) {
						return "Fail -> The TestDiagnostic of Real Device was not completed, time waited: "
								+ counter;
					}

				} catch (Throwable t) {
					System.out.println("Error while clickButtonDisplayed ");
					return "Fail ->" + t.getMessage();
				}
			}
		}

		return "Fail-> Not able to complete the installation";
	}*/

	public static String clickButtonDisplayedInstall() {
		System.out.println("Execute clickButtonDisplayedInstall " + object);
		String[] objects = object.split("#");
		WebElement button_SkipGPSTestID = null;
		WebElement button_Finish = null;
		WebElement button_Submit = null;
		WebElement button_Yes = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
		boolean done = false;
		int counter = 0;
		if (!object.contains("#realDevice")) {
			while (!done) {
				try {
					Thread.sleep(1500);
					counter++;
					System.out.println("Counter Number " + counter);
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					button_SkipGPSTestID = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[0]))));
					
					if (button_SkipGPSTestID.isDisplayed()) {
						Thread.sleep(600);
						button_SkipGPSTestID.click();
						waitCOLTServer();
						Thread.sleep(500);
						
						System.out.println("Skip button clicked");
						done = true;
						return "Pass";
					}
					button_Finish = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[1]))));
					
					if (button_Finish.isDisplayed()) {
						button_Finish.click();
						waitCOLTServer();
						Thread.sleep(1000);
						
						System.out.println("Finish button clicked");
						done = true;
						return "Pass";

					}
					button_Submit = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[2]))));
					if (button_Submit.isDisplayed())
					{
						if(!button_SkipGPSTestID.isDisplayed())
						{
							button_Submit.click();
							waitCOLTServer();
							System.out.println("Submit button clicked");
							done = true;
							return "Pass";
						}
						
					}
				
					if (counter > 1500) {
						return "Fail -> The TestDiagnostic was not completed, time waited: "
								+ counter;
					}

				} catch (Throwable t) {
					System.out.println("Error while clickButtonDisplayed ");
					return "Fail ->" + t.getMessage();
				}
			}
		} else {
			while (!done) {
				try {
					counter++;
					System.out.println("Counter Number " + counter);
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					button_SkipGPSTestID = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[0]))));
					button_Finish = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[1]))));
					button_Submit = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(objects[2]))));
					if (button_Submit.isDisplayed()) {
						button_Submit.click();
						
						System.out.println("Submit button clicked from Real Device");
						done = true;
						return "Pass";
					}
					//Thread.sleep(1000);
					if (counter > 1500) {
						return "Fail -> The TestDiagnostic of Real Device was not completed, time waited: "
								+ counter;
					}

				} catch (Throwable t) {
					System.out.println("Error while clickButtonDisplayed ");
					return "Fail ->" + t.getMessage();
				}
			}
		}

		return "Fail-> Not able to complete the installation";
	}
	
	
	
	public static String clickLinkByTitle() {
		String title;

		try {
			System.out.println("Executing clickLinkByTitle " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			if(data.isEmpty())
			{
				return "Fail The data is empty";
			}
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement command_box = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load
			List<WebElement> links = command_box.findElements(By.tagName("a"));
			for (WebElement link : links) {
				title = link.getAttribute("title");
				System.out.println(title);
				if (data.contains(title)) {
					link.click();
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Clicking the link by title ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String clickButtonAccordingDate() {
		try {
			System.out.println("Execute clickButtonAccordingDate " + object);
			String[] values = element_Name.split("/");
			if (values[1].equals("29") | values[1].equals("30")
					| values[1].equals("31")) {
				driver.manage().timeouts()
						.implicitlyWait(20L, TimeUnit.SECONDS); // Wait for page
																// to Load

				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				WebElement element = wait.until(presenceOfElementLocated(By
						.xpath(OR.getProperty(object))));
				element.click();
				return "Pass";
			} else {
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error while clickButtonAccordingDate ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String clickLink() {
		try {
			System.out.println("Execute clickLink " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebElement element = null;

			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));

			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));

			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.click();
			return "Pass";

		} catch (Throwable t) {
			System.out.println("Error while clickLink ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String click_checkBoxDeviceAndSave() {
		System.out.println("Executing: click_checkBoxDeviceAndSave " + object);
		String[] objects = object.split("#");
		String ValuetoCompare = "Ready to install";
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		Boolean valueFound = false;
		Boolean result = false;
		WebElement checkbox = null;
		String airID = "";
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
		WebElement link_next = wait.until(presenceOfElementLocated(By.xpath(OR
				.getProperty(objects[1])))); // Wait for element to Load
		try {
			while (inactive != true) {
				Integer i = 0;
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty(objects[0])));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));

						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							airID = cells.get(0).getText();
							if (cell.getText().contains(ValuetoCompare)) {
								valueFound = true;
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								result = TestData.setCellData(currentTest,
										dataColumnName, testRepeat, airID);
								if (result) {
									return "Pass";
								} else {
									return "Unable to Save AirID into TestData file";
								}
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				if (!link_next.getAttribute("class").contains("inactive")) {
					driver.findElement(By.xpath(OR.getProperty(objects[1])))
							.click();
					Thread.sleep(5000);
					System.out.println("Click on Next link");
				} else {
					return "Fail There is No AirID Available";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the AirID ");
			return "Fail" + t.getMessage();
		}
		return "Fail";
	}

	public static String saveAirIDInTestData() {

		try {
			System.out.println("Execute saveAirIDInTestData " + object);
			Boolean result = TestData.setCellData(currentTest, dataColumnName,
					testRepeat, AirID);
			if (result) {
				return "Pass";
			} else {
				return "Fail -> Not Able to save the AirID";
			}
		} catch (Throwable t) {
			System.out.println("Error while saveAirIDInTestData ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String checkAirIDReadyToInstall() {
		System.out.println("Executing: checkAirIDReadyToInstall " + object);
		String[] objects = object.split("#");
		String ValuetoCompare = "Ready to install";
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		WebElement element = null;
		WebElement checkbox = null;
		WebElement link_next = null;
		Boolean link_next_displayed = false;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 

		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}

		try {
			if (values[1].contains("_ID")) {
				link_next = driver
						.findElement(By.id(OR.getProperty(values[1])));
			} else if (values[1].contains("_xpath")) {
				link_next = driver.findElement(By.xpath(OR
						.getProperty(values[1])));
			} else if (values[1].contains("_css")) {
				link_next = driver.findElement(By.cssSelector(OR
						.getProperty(values[1])));
			} else {
				return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			link_next_displayed = true;
		} catch (Throwable t) {
		}

		try {
			while (inactive != true) {
				Integer i = 0;
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				// WebElement table =
				// element.findElement(By.xpath(OR.getProperty(objects[0])));
				List<WebElement> rows = element.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));

						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							AirID = cells.get(0).getText();
							ESN = cells.get(3).getText();
							if (cell.getText().contains(ValuetoCompare)) {
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				if (!link_next.getAttribute("class").contains("inactive")) {
					driver.findElement(By.xpath(OR.getProperty(objects[1])))
							.click();
					Thread.sleep(5000);
					System.out.println("Click on Next link");
				} else {
					return "Fail There is No AirID Available";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the Vehicle ");
			return "Fail" + t.getMessage();
		}
		return "Fail";
	}

	public static String verifyAirIDIsNotDisplayed() {
		try {
			System.out
					.println("Executing: verifyAirIDIsNotDisplayed " + object);
			String result = "Fail";
			result = check_SearchedAirID();
			if (result.contains("Fail")) {
				return "Pass";
			} else {
				return "Fail -> The AirID or Real Device searched is displayed in the list of AirIDs to install";
			}
		} catch (Throwable t) {
			System.out.println("Error in verifyAirIDIsNotDisplayed ");
			return "Fail" + t.getMessage();
		}
	}

	public static String check_SearchedAirID() {
		  System.out.println("Executing: check_SearchedAirID " + object);
		  WebElement element = null;
		  String ValuetoCompare = null;
		  if (!dataColumnName.isEmpty()) {
		   ValuetoCompare = TestData.getCellData(currentTest, dataColumnName,
		     testRepeat);
		  } 
		  else if(object.contains("Random"))
		  {
			  ValuetoCompare = AlphaName;
		  }
			  else {
		   ValuetoCompare = AirID;
		  }
		  ValuetoCompare = ValuetoCompare.toUpperCase();
		  System.out.println(ValuetoCompare);
		  Boolean inactive = false;
		  WebElement checkbox = null;
		  Boolean link_next_displayed = false;
		  List<WebElement> link_nexts = null;
		  driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
		                   // for
		                   // page
		                   // to
		                   // Load
		  String[] values = object.split("#");
		  System.out.println(values.length);
		  if (values.length < 2) {
		   values = new String[2];
		   values[0] = object;
		   values[1] = object;
		  }
		  try {
		   while (inactive != true) {
		    Integer i = 0;
		    if (values[0].contains("_ID")) {
		     WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
		       20);
		     element = wait.until(presenceOfElementLocated(By.id(OR
		       .getProperty(values[0]))));
		    } else if (values[0].contains("_xpath")) {
		     WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
		       20);
		     element = wait.until(presenceOfElementLocated(By.xpath(OR
		       .getProperty(values[0]))));
		    } else if (values[0].contains("_css")) {
		     WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
		       20);
		     element = wait.until(presenceOfElementLocated(By
		       .cssSelector(OR.getProperty(values[0]))));
		    } else {
		     return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
		    }

		    List<WebElement> rows = element.findElements(By.tagName("tr"));
		    System.out.println(rows.size());
		    if (rows.size() > 0) {
		     for (WebElement row : rows) {
		      i++;
		      System.out.println("Row: " + i.toString());
		      if (!object.contains("Compare"))
		      {
		       checkbox = row.findElement(By.tagName("span"));
		       if ((values.length > 2)
		         && (values[2].contains("reject"))) {
		        List<WebElement> checkboxes = row.findElements(By
		          .tagName("span"));
		        // checkbox = checkboxes.get(1);
		        for (WebElement checkbox_reject : checkboxes) {
		         if (checkbox_reject.getAttribute("class")
		           .contains("reject")) {
		          checkbox = checkbox_reject;
		          break;
		         }
		        }
		       }
		      }
		      
		      
		      List<WebElement> cells = row.findElements(By
		        .tagName("td"));
		      
		      for (WebElement cell : cells) {
		       System.out.print(cell.getText() + "  ");
		       String current = cell.getText().toUpperCase();
		       if (current.contentEquals(ValuetoCompare)) {
		        System.out.println("found: " + cell.getText());
		        if (!object.contains("Compare"))
		        {
		         checkbox.click();
		        }
		        
		        Thread.sleep(600);
		        return "Pass";
		       }
		      }
		      System.out.println(" ");
		     }
		    } else {
		     return "Fail There is No Search Results";
		    }
		    try {
		     if (values[1].contains("_ID")) {
		      link_nexts = driver.findElements(By.id(OR
		        .getProperty(values[1])));
		     } else if (values[1].contains("_xpath")) {
		      link_nexts = driver.findElements(By.xpath(OR
		        .getProperty(values[1])));
		     } else if (values[1].contains("_css")) {
		      link_nexts = driver.findElements(By.cssSelector(OR
		        .getProperty(values[1])));
		     } else {
		      return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
		     }
		     link_next_displayed = true;
		    } catch (Throwable t) {
		    }
		    if (link_next_displayed) {
		     for (WebElement link_next : link_nexts)

		     {
		      if (link_next.isDisplayed()) {
		       // System.out.println(link_next.getAttribute("class"));
		       if (!link_next.getAttribute("class").contains(
		         "inactive")) {
		        try {
		         link_next.click();
		         waitCOLTServer();
		         System.out.println("Next link");
		        } catch (Throwable t) {
		         return "Fail -> Not possible to click on next link";
		        }

		       } else {
		        inactive = true;
		        return "Fail";
		       }
		      }
		     }
		    } else {
		     inactive = true;
		    }

		   }
		  } catch (Throwable t) {
		   System.out.println("Error in Finding the Vehicle ");
		   return "Fail: " + t.getMessage();
		  }
		  return "Fail";
		 }
	
	
	/*public static String check_SearchedAirID() {
		System.out.println("Executing: check_SearchedAirID " + object);
		WebElement element = null;
		String ValuetoCompare = null;
		if (!dataColumnName.isEmpty()) {
			ValuetoCompare = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
		} else {
			ValuetoCompare = AirID;
		}
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		WebElement checkbox = null;
		Boolean link_next_displayed = false;
		List<WebElement> link_nexts = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		String[] values = object.split("#");
		System.out.println(values.length);
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		try {
			while (inactive != true) {
				Integer i = 0;
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver,  seconds= 
							20);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));
				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver,  seconds= 
							20);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));
				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver,  seconds= 
							20);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				List<WebElement> rows = element.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));
						if ((values.length > 2)
								&& (values[2].contains("reject"))) {
							List<WebElement> checkboxes = row.findElements(By
									.tagName("span"));
							// checkbox = checkboxes.get(1);
							for (WebElement checkbox_reject : checkboxes) {
								if (checkbox_reject.getAttribute("class")
										.contains("reject")) {
									checkbox = checkbox_reject;
									break;
								}
							}
						}
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							if (cell.getText().contains(ValuetoCompare)) {
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				try {
					if (values[1].contains("_ID")) {
						link_nexts = driver.findElements(By.id(OR
								.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
						link_nexts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
						link_nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
					} else {
						return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					link_next_displayed = true;
				} catch (Throwable t) {
				}
				if (link_next_displayed) {
					for (WebElement link_next : link_nexts)

					{
						if (link_next.isDisplayed()) {
							// System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									waitCOLTServer();
									System.out.println("Next link");
								} catch (Throwable t) {
									return "Fail -> Not possible to click on next link";
								}

							} else {
								inactive = true;
								return "Fail";
							}
						}
					}
				} else {
					inactive = true;
				}

			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the Vehicle ");
			return "Fail: " + t.getMessage();
		}
		return "Fail";
	}*/

	public static String check_SearchedAirIDSaved() {
		System.out.println("Executing: check_SearchedAirID " + object);
		String[] objects = object.split("#");
		String ValuetoCompare = TestData.getCellData(currentTest,
				dataColumnName, testRepeat);
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		WebElement checkbox = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
		WebElement link_next = wait.until(presenceOfElementLocated(By.id(OR
				.getProperty(objects[1])))); // Wait for element to Load
		try {
			while (inactive != true) {
				Integer i = 0;
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty(objects[0])));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							if (cell.getText().contains(ValuetoCompare)) {
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				if (!link_next.getAttribute("class").contains("inactive")) {
					driver.findElement(By.id(OR.getProperty(objects[1])))
							.click();
					Thread.sleep(1000);
					System.out.println("Click on Next link");
				} else {
					return "Fail: There is No the AirID specified";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the Vehicle ");
			return "Fail: " + t.getMessage();
		}
		return "Fail";
	}

	public static String check_SearchedAirIDReadyToInstall() {
		System.out.println("Executing: check_SearchedAirIDReadyToInstall "
				+ object);
		String[] objects = object.split("#");
		String ValuetoCompare = null;
		if (!dataColumnName.isEmpty()) {
			ValuetoCompare = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
		} else {
			ValuetoCompare = AirID;
		}
		String StatusCompare = "Ready to install";
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		WebElement checkbox = null;
		WebElement link_next = null;
		Boolean link_next_displayed = false;
		WebElement element = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load

		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		// //
		try {
			if (values[1].contains("_ID")) {
				link_next = driver
						.findElement(By.id(OR.getProperty(values[1])));
			} else if (values[1].contains("_xpath")) {
				link_next = driver.findElement(By.xpath(OR
						.getProperty(values[1])));
			} else if (values[1].contains("_css")) {
				link_next = driver.findElement(By.cssSelector(OR
						.getProperty(values[1])));
			} else {
				return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			link_next_displayed = true;
		} catch (Throwable t) {
		}

		try {
			while (inactive != true) {
				Integer i = 0;

				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				List<WebElement> rows = element.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						System.out.println(cells.get(1).getText());
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							if (cell.getText().equals(ValuetoCompare)
									&& (cells.get(1).getText()
											.equals(StatusCompare))) {
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				if (!link_next.getAttribute("class").contains("inactive")) {
					driver.findElement(By.id(OR.getProperty(objects[1])))
							.click();
					Thread.sleep(1000);
					System.out.println("Click on Next link");
				} else {
					return "Fail: There is No the AirID specified";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the Vehicle ");
			return "Fail: " + t.getMessage();
		}
		return "Fail";
	}

	public static String check_SearchedAlphaName() {
		System.out.println("Executing: check_SearchedAlphaName " + object);
		String[] objects = object.split("#");
		String ValuetoCompare = AlphaName;
		System.out.println(ValuetoCompare);
		Boolean inactive = false;
		WebElement checkbox = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load

		WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
		WebElement link_next = wait.until(presenceOfElementLocated(By.id(OR
				.getProperty(objects[1])))); // Wait for element to Load
		// WebElement link_next =
		// driver.findElement(By.id(OR.getProperty(objects[1])));
		try {
			while (inactive != true) {
				Integer i = 0;
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty(objects[0])));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				System.out.println(rows.size());
				if (rows.size() > 0) {
					for (WebElement row : rows) {
						i++;
						System.out.println("Row: " + i.toString());
						checkbox = row.findElement(By.tagName("span"));
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "  ");
							if (cell.getText().contains(ValuetoCompare)) {
								System.out.println("found: " + cell.getText());
								checkbox.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
						System.out.println(" ");
					}
				} else {
					return "Fail There is No Search Results";
				}
				if (!link_next.getAttribute("class").contains("inactive")) {
					driver.findElement(By.id(OR.getProperty(objects[1])))
							.click();
					Thread.sleep(2000);
					System.out.println("Click on Next link");
				} else {
					return "Fail: There is No the AirID specified";
				}
			}
		} catch (Throwable t) {
			System.out.println("Error in Finding the Vehicle ");
			return "Fail: " + t.getMessage();
		}
		return "Fail";
	}

	public static String clickCheckBox() {
		System.out.println("Execute clickCheckBox " + object);
		try {
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.click();
			Thread.sleep(600);
		} catch (Throwable t) {
			System.out.println("Error while clickCheckBox ");
			return "Fail ->" + t.getMessage();
		}

		return "Pass";
	}

	public static String select() {

		System.out.println("Execute select " + object);
		String data = TestData.getCellData(currentTest, dataColumnName,
				testRepeat);
		System.out.println(data);
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		try {
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement dropDownListBox = wait.until(presenceOfElementLocated(By
					.xpath(OR.getProperty(object)))); // Wait for element to
														// Load
			// WebElement dropDownListBox =
			// driver.findElement(By.xpath(OR.getProperty(object)));
			Select clickThis = new Select(dropDownListBox);
			clickThis.selectByValue(data);
			Thread.sleep(600);
		} catch (Throwable t) {
			System.out.println("Error while select ");
			return "Fail ->" + t.getMessage();
		}

		return "Pass";
	}

	public static String selectDropdown() {
		try {
			System.out.println("Executing selectDropdown " + object);
			WebElement dropDownListBox = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.xpath(OR.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println(data);
			if (!data.isEmpty()) {
				//Thread.sleep(1500); 
				dropDownListBox.click();
								
				Thread.sleep(600);
				List<WebElement> links = dropDownListBox.findElements(By
						.xpath("//a[text()='" + data + "']"));
				System.out.println(links.size());
				for (WebElement link : links) {
					System.out.println(link.getText());
					if (link.isDisplayed()) {
						if (link.getText().contentEquals(data.trim())) {
							link.click();
							return "Pass";
						}

					}
				}
			} else {
				return "Fail -> There is no data readed from TestData is empty";
			}
		} catch (Throwable t) {
			System.out.println("Error in selectDropdown: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail -> Error Executing SelectDropdown";
	}
	
	public static String verifyElementNotInDropdown() {
		try {
			System.out.println("Executing verifyElementNotInDropdown " + object);
			String result = selectDropdown();
			if(result.contains("Fail"))
			{
				return "Pass";
			}
			else
			{
				return "Fail --> the element was able to select";
			}
		}
		catch(Throwable t)
		{
			return "Fail --> The keyword failed" + t;
		}
		//eturn "Fail";
	}
	
	
	
	public static String scrollUpOrDown() {
		try {
			System.out.println("Executing scrollDown" + object);
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			//WebElement dropDownListBox = null;
			WebElement scroll = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			
			String data = TestData.getCellData(currentTest, dataColumnName, testRepeat);
			System.out.println("Number to scroll = " + data);
			if (!data.isEmpty()) {
			
				//**************new code*******\\\\\\
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By
							.xpath(OR.getProperty(values[0]))));
				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));
				}
				else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
			
				System.out.println(scroll.getLocation());
				//scroll.getLocation().moveBy(0, 100);
				//dropDownListBox.getLocation().
				Actions action = null;
				action = new Actions(driver);
				//action.clickAndHold(scroll);
				System.out.println(scroll.getSize());
				//action.clickAndHold(scroll).moveToElement(scroll, 0, 50).release().perform();
				action.clickAndHold(scroll).moveToElement(scroll, 0, Integer.parseInt(data)).release().perform();
				Thread.sleep(600);
			}
			else
			{
				System.out.println("The data is empty");
			}
		} catch (Throwable t) {
			System.out.println("Error in selectDropdownScrolled: ");
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}	
	
	public static String scrolling(){
		System.out.println("Executing selectDropdownScrolled" + object);
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		WebElement scroll = null;
		List<WebElement> dropdowns = null;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		if (values[0].contains("_ID")) {
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
					20);
			scroll = wait.until(presenceOfElementLocated(By.id(OR
					.getProperty(values[0]))));
			dropdowns = driver.findElements(By.id(OR.getProperty(values[0])));

		} else if (values[0].contains("_xpath")) {
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
					20);
			scroll = wait.until(presenceOfElementLocated(By
					.xpath(OR.getProperty(values[0]))));
			//dropdowns = driver.findElements(By.id(OR.getProperty(values[0])));
		} else if (values[0].contains("_css")) {
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
					20);
			scroll = wait.until(presenceOfElementLocated(By
					.cssSelector(OR.getProperty(values[0]))));
			dropdowns = driver.findElements(By.id(OR.getProperty(values[0])));
		} else {
			return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
		}
		String data = TestData.getCellData(currentTest, dataColumnName,
				testRepeat);
		System.out.println(data);
		if(!data.isEmpty()){
			if(object.contains("Down"))
			{
				try{
				((JavascriptExecutor) driver).executeScript(
		                "arguments[0].scrollBy("+data+")", scroll);
				}
				catch (Throwable t)
				{
					
				}
				return "Pass";
			}
			else if(object.contains("Up"))
			{
				((JavascriptExecutor) driver).executeScript(
		                "arguments[0].scrollBy(arguments[1])", scroll, data);
				
				return "Pass";
			}
			else
			{
				return "Fail -> The object does not specify if the scroll should be Up or Down";
			}
		}
		else
		{
			return "Fail -> The data is empty";
		}
				
	}
	
	public static String selectDropdownScrolled() {
		try {
			System.out.println("Executing selectDropdownScrolled" + object);
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			WebElement dropDownListBox = null;
			WebElement scroll = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));

			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.xpath(OR.getProperty(values[0]))));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			dropDownListBox.click();
			Thread.sleep(600);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println(data);
			if (!data.isEmpty()) {
				//Thread.sleep(600);
				
				//**************new code*******\\\\\\
				if (values[1].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[1]))));

				} else if (values[1].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By
							.xpath(OR.getProperty(values[1]))));
				} else if (values[1].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					scroll = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[1]))));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				System.out.println(scroll.getLocation());
				//scroll.getLocation().moveBy(0, 100);
				//dropDownListBox.getLocation().
				Actions action = null;
				
				if(object.contains("Down"))
				{
					action = new Actions(driver);
					action.clickAndHold(scroll);
					action.clickAndHold(scroll).moveByOffset(0, 100).build().perform();
				}
				else if(object.contains("Up"))
				{
					action = new Actions(driver);
					action.clickAndHold(scroll).moveByOffset(0, -1).build().perform();
				}
				else
				{
					return "Fail -> The object does not specify if the scroll should be Up or Down";
				}
				List<WebElement> links = dropDownListBox.findElements(By.xpath("//a[text()='" + data + "']"));
				System.out.println(links.size());
				for (WebElement link : links) {
					System.out.println(link.getText());
					if (link.isDisplayed()) {
						if (link.getText().contentEquals(data.trim())) {
							link.click();
							return "Pass";
						}

					}
					
				}
			} else {
				return "Fail -> There is no data readed from TestData is empty";
			}
		} catch (Throwable t) {
			System.out.println("Error in selectDropdownScrolled: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail -> Error Executing selectDropdownScrolled";
	}

	public static String selectDropdownIfDisplayed() {
		try {
			System.out.println("Executing selectDropdownIfDisplayed " + object);
			WebElement dropDownListBox = null;
			List<WebElement> dropdowns = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
				dropdowns = driver.findElements(By.id(OR.getProperty(object)));
				System.out.println("ID");
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.xpath(OR.getProperty(object))));
				dropdowns = driver
						.findElements(By.xpath(OR.getProperty(object)));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				dropDownListBox = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(object))));
				dropdowns = driver.findElements(By.cssSelector(OR
						.getProperty(object)));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println(data);
			if (!data.isEmpty()) {
				Thread.sleep(2500);
				for (WebElement drop : dropdowns) {
					if (drop.isDisplayed()) {
						drop.click();
						Thread.sleep(600);
						List<WebElement> links = drop.findElements(By
								.xpath("//a[text()='" + data + "']"));
						System.out.println(links.size());
						for (WebElement link : links) {
							System.out.println(link.getText());
							if (link.isDisplayed()) {
								if (link.getText().contentEquals(data)) {
									link.click();
									return "Pass";
								}

							}
						}
					}
				}

			} else {
				return "Fail -> There is no data readed from TestData is empty";
			}
		} catch (Throwable t) {
			System.out.println("Error in selectDropdown: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail -> Error Executing SelectDropdown";
	}

	public static String selectAlphaNameFromDropdownID() {
		try {
			System.out.println("Executing selectAlphaNameFromDropdownID "
					+ object);
			String data = AlphaName;
			System.out.println(data);
			// WebElement dropDownListBox;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement dropDownListBox = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load
			Thread.sleep(1000);
			// dropDownListBox =
			// driver.findElement(By.id(OR.getProperty(object))); //getting the
			// dropdown
			dropDownListBox.click();
			System.out.println("The selection made from the drop-down");
			// dropDownListBox =
			// driver.findElement(By.id(OR.getProperty(object)));
			Thread.sleep(1000);
			List<WebElement> elements = dropDownListBox.findElements(By
					.tagName("a")); // getting the list of links
			for (WebElement element : elements) {
				if (element.getText().contains(data)) {
					element.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("error in selectAlphaNameFromDropdownID: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String Wait() {
		try {
			System.out.println("Executing Wait "
					+ object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println(data);
			String arr[] = data.split("\\.");
			System.out.println("wait of :" + arr[0] + " milliseconds");
			long temp1 = Long.parseLong(arr[0]);
			Thread.sleep(temp1); // 5000.0
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("error while waiting");
			return "Fail ->" + e.getMessage();
		}
		return "Pass";

	}

	
	public static String waitCOLTServer() 
	{
		System.out.println("Executing WaitCOLTServer() ");
		int waited = 0;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		try 
		{
			while(driver.findElement(By.id("cancel")).isDisplayed()&&(waited<=65000))
			{
				Thread.sleep(100);
				waited++;
				System.out.println("Time waited = " + waited + "miliseconds");
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.out.println("error while waiting");
			return "Fail ->" + e.getMessage();
		}
		if(waited>65000)
		{
			System.out.println("The app. continues loading, the limit of wait exceeded");
			return "Fail";
		}
		else
		{
			return "Pass";
		}
		

	}

	public static String waitCOLTServerDEI() 
	{
		System.out.println("Executing WaitCOLTServer() ");
		int waited = 0;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		try 
		{
			while(driver.findElement(By.id(OR.getProperty(object))).isDisplayed()&&(waited<=65000))
			{
				Thread.sleep(100);
				waited++;
				System.out.println("Time waited = " + waited + "miliseconds");
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.out.println("error while waiting");
			return "Fail ->" + e.getMessage();
		}
		if(waited>65000)
		{
			System.out.println("The app. continues loading, the limit of wait exceeded");
			return "Fail";
		}
		else
		{
			return "Pass";
		}
		

	}

	
	
	public static String waitLoading() 
	{
		System.out.println("Executing WaitLoading() ");
		int waited = 0;
		try 
		{
			while(driver.findElement(By.id("device_loading")).isDisplayed()&&(waited<=65000))
			{
				Thread.sleep(100);
				waited++;
				System.out.println("Time waited = " + waited + "miliseconds");
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.out.println("error while waiting");
			return "Fail ->" + e.getMessage();
		}
		if(waited>65000)
		{
			System.out.println("The app. continues loading, the limit of wait exceeded");
			return "Fail";
		}
		else
		{
			return "Pass";
		}
		
	}
	
	
	
	public static String waitLoadDevices() 
	{
		System.out.println("Executing WaitCOLTServer() ");
		int waited = 0;
		try 
		{
			while(driver.findElement(By.id("device_loading']")).isDisplayed()&&(waited<=150))
			{
				Thread.sleep(100);
				waited++;
				System.out.println("Time waited = " + waited + "miliseconds");
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.out.println("error while waiting");
			return "Fail ->" + e.getMessage();
		}
		if(waited>250)
		{
			System.out.println("The app. continues loading, the limit of wait exceeded");
			return "Fail";
		}
		else
		{
			return "Pass";
		}
		

	}
	public static String verifyElementIsEmpty()
	{
		System.out.println("Execute verifyElementIsEmpty " + object);
		try{
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			WebElement element;
			List<WebElement> elements;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				String value = OR.getProperty(values[0]);
				System.out.println(value);
				if (value.contains("%0") || value.contains("%1"))
				{
					value = value.replace("%0", transferID).replace("%1", AirID);
				}
				element = wait.until(presenceOfElementLocated(By.id(value)));
				elements = driver.findElements(By.id(value));
			} 
			else if 
			(object.contains("_xpath"))
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				String value = OR.getProperty(values[0]);
				System.out.println(value);
				if (value.contains("%0") || value.contains("%1")) {
					value = value.replace("%0", transferID).replace("%1",
							AirID);
					System.out.println(value);
				}
				element = wait.until(presenceOfElementLocated(By
						.xpath(value)));
				elements = driver.findElements(By.xpath(value));
			} 
			else if (object.contains("_css")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				String value = OR.getProperty(values[0]);
				System.out.println(value);
				if (value.contains("%0") || value.contains("%1")) {
					value = value.replace("%0", transferID).replace("%1",
							AirID);
				}
				element = wait.until(presenceOfElementLocated(By
						.cssSelector(value)));
				elements = driver.findElements(By.cssSelector(value));
			}
			else 
			{
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			for (WebElement e:elements)
			{
				if(e.isDisplayed())
				{
					if(e.getText().isEmpty())
					{
						return "Pass";
					}
					else
					{
						return "Fail -> The Element is not Empty";
					}
				}
			}
			
			
		}
		catch(Throwable t){
			
		}
		return "Fail -> Error Executing Element is empty?";
	}
	
	
	public static String verifyText() 
	{
		System.out.println("Execute verifyText " + object);
		System.out.println("Object->" + object);
		System.out.println("Address->" + OR.getProperty(object));
		String expected = null;
		String actual = null;
		try {
			int count=30;
			if(object.contains("#No")){
				count=1;
			}
			String[] values = object.split("#");
			
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			Boolean found = false;
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement element = null;
			List<WebElement> elements = null;
			int i = 1;
			
			while (i <= count && !found) 
			{
				if (object.contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					String value = OR.getProperty(values[0]);
					System.out.println(value);
					if (value.contains("%0") || value.contains("%1"))
					{
						value = value.replace("%0", transferID).replace("%1", AirID);
					}
					element = wait.until(presenceOfElementLocated(By.id(value)));
					elements = driver.findElements(By.id(value));
				} 
				else if 
				(object.contains("_xpath"))
				{
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					String value = OR.getProperty(values[0]);
					System.out.println(value);
					if (value.contains("%0") || value.contains("%1")) {
						value = value.replace("%0", transferID).replace("%1",
								AirID);
						System.out.println(value);
					}
					element = wait.until(presenceOfElementLocated(By
							.xpath(value)));
					elements = driver.findElements(By.xpath(value));
				} 
				else if (object.contains("_css")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					String value = OR.getProperty(values[0]);
					System.out.println(value);
					if (value.contains("%0") || value.contains("%1")) {
						value = value.replace("%0", transferID).replace("%1",
								AirID);
					}
					element = wait.until(presenceOfElementLocated(By.cssSelector(value)));
					elements = driver.findElements(By.cssSelector(value));
				}
				else 
				{
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				if (!dataColumnName.isEmpty()) {
					System.out.println(dataColumnName);
					String data = TestData.getCellData(currentTest,	dataColumnName, testRepeat);
					if (!data.isEmpty()) 
					{
						expected = data;
					} 
					else 
					{
						return "Fail -> The data readed from TestData is empty";
					}
				} 
				else if (object.contains("_AirID")) {
					expected = AirID;
				} else if (object.contains("_AlphaName")) {
					expected = AlphaName;
				} else if (object.contains("_ElementName")) {
					expected = element_Name;
				} else if (object.contains("_4DigitNumber")) {
					expected = four_DigitNumber.toString();
				} 
				else if(object.contains("#currentDate")){
					String currentDate= RandomUtilities.getCurrentDate();
					expected =	currentDate;			
				}
				else
				{
					expected = APPTEXT.getProperty(values[1]);
					
					if (expected.contains("%0") || (expected.contains("%1"))) {
						expected = expected.replace("%0", transferID).replace("%1", AirID);
					}
					//System.out.println("EXPECTED TEXT IS:" + expected);
				}
				System.out.println("EXPECTED TEXT IS:" + expected);
				if (!expected.trim().isEmpty()) {
					for (WebElement text : elements) {
						if (text.isDisplayed()) 
						{
							actual = text.getText();
							System.out.println("The actual text is: " + actual);
							/*String actual1 = text.getAttribute("value");
							System.out.println(actual1);*/
							if (!actual.isEmpty()) 
							{
								if (object.contains("_Phone"))
								{
									actual = actual.replace("(", "");
									System.out.println(actual);
									actual = actual.replace(")", "");
									System.out.println(actual);
									actual = actual.replace("-", "");
									System.out.println(actual);
									actual = actual.replace(" ", "");
									System.out.println(actual);
								}
								//System.out.println("Actual text ->" + actual);
								actual = actual.toUpperCase().trim();
								expected = expected.toUpperCase().trim();
								if (actual.trim().contains(expected.trim())) 
								{
									found = true;
									break;
								}
								else
								{
									System.out.println("Not equal");
								}
							} 
							else if (!text.getAttribute("value").isEmpty()) {
								actual = text.getAttribute("value");
								if (object.contains("_Phone")) {
									actual = actual.replace("(", "");
									System.out.println(actual);
									actual = actual.replace(")", "");
									System.out.println(actual);
									actual = actual.replace("-", "");
									System.out.println(actual);
									actual = actual.replace(" ", "");
									System.out.println(actual);
								}
								actual = actual.toUpperCase();
								expected = expected.toUpperCase();
								System.out.println("The actual is: "+ actual);
								System.out.println("The expected is : "+ expected);
								if (actual.trim().contains(expected.trim()))
								{
									found = true;
									break;
								}
								else
								{
									System.out.println("Actual different than expected");
								}
							}
						}
					}
				} else {
					return "Fail -> There is No Value to compare";
				}

				System.out.println("Wait for " + i + " Sec");
				Thread.sleep(1000);
				i = i + 1;
			}

			if (found) 
			{
				return "Pass";
			} 
			else
			{
				System.out.println("error in verifyText " + "Expected Text: "
						+ expected + "Actual Text: " + actual);
				return "Fail: " + "Expected-> " + expected + " Actual->"
						+ actual;
			}
		} catch (Throwable t) {
			return "Fail: " + "Expected-> " + expected + " Actual-> " + actual;
		}
	}

	public static String verifyTextNotDisplayed()
	{
		System.out.println("Execute verifyTextNotDisplayed " + object);
		String result = "fail";
		try{
			result = verifyText();
			
		}
		catch(Throwable t){
			System.out.println("Error in verify the text is Not displayed"+t);
		}
		if(result.contains("Fail"))
		{
			return "Pass";
		}
		else
		{
			return "Fail --> The element exists";
		}
	}
	
	
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	
 /* 
 The object should be contain '#num' for value numeric in the table (values to sort) other case will be for the alphanumeric values
 */
	public static String verify_ASC_DSC_Sorting()
	{
		System.out.println("Executing: verify_ASC_DSC_Sorting " + object);
		String[] values = object.split("#");
		if (values.length < 2)
		{
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		Boolean inactive = false;
		Boolean valueFound = false;
		Boolean link_next_displayed = false;
		String ValuetoCompare = null;
		WebElement element = null;
		Integer compare = null;
		Boolean result = false;
		List<WebElement> elements = null;
		List<WebElement> link_nexts = null;
		String firstvalue = null;
		String nextRowValue = null;
		String order = "ASC";
		Date value, secondvalue;
		//String secondvalue;
		while (inactive != true) 
		{
			Integer i = 0;
			try {
				driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
					element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));
					elements = driver.findElements(By.id(OR.getProperty(values[0])));
				} 
				else if (values[0].contains("_xpath")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));
					elements = driver.findElements(By.xpath(OR
							.getProperty(values[0])));
				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));
					elements = driver.findElements(By.cssSelector(OR
							.getProperty(values[0])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				for (WebElement table : elements)
				{
					if (table.isDisplayed()) 
					{
						List<WebElement> rows = table.findElements(By.tagName("tr"));
						System.out.println(rows.size());
						if (rows.size() > 0) 
						{
							Thread.sleep(200);
							
					/// 
   						if (object.contains("#num"))
   						{
   					//		
   							for (WebElement row : rows) 
							{
								if (rows.size() == i+1)
								{
									break;
								}
								System.out.println("Row: " + i.toString());
								//WebElement cell = row.findElement(By.tagName("td"));
								List<WebElement> cells = row.findElements(By.tagName("td"));
								if(!values[3].isEmpty())
								{
									firstvalue = cells.get(Integer.parseInt(values[3])).getText();
									System.out.println("The first value displayed is: " + firstvalue);
									nextRowValue = rows.get(i+1).findElements(By.tagName("td")).get(Integer.parseInt(values[3])).getText();
									System.out.println("The second value displayed is: " + nextRowValue);
									value = null;
									secondvalue = null;

									Boolean isDate = false;
									DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
									try
									{
										value = df.parse(firstvalue);
										isDate =true;
										System.out.println("The first date value is: " + value);
									}
									catch(Throwable t){ }
									
									try {
										secondvalue = df.parse(nextRowValue);
										isDate =true;
										System.out.println("The second date value is: " + secondvalue);
									}
									catch(Throwable t){	}									
									if (isDate)
									{
										if (firstvalue.contains("N/A"))
										{
											value = df.parse("1/1/1900");
										}
										if (nextRowValue.contains("N/A"))
										{
											secondvalue = df.parse("1/1/1900");
										}
										//addded to 'None' option
										if (firstvalue.contains("None"))
										{
											value = df.parse("1/1/1900");
										}
										if (nextRowValue.contains("None"))
										{
											secondvalue = df.parse("1/1/1900");
										}
									
										compare = value.compareTo(secondvalue);
										System.out.println("The value of the compare is: " + compare);
									}
									else
									{
										//compare = firstvalue.toUpperCase().compareTo(nextRowValue.toUpperCase());
										
										compare = firstvalue.length() - nextRowValue.length();
									}
								}						
								else
								{
									return "Fail -> Was not specified the Sorting column to verify";
								}
								WebElement asc_desc =  driver.findElement(By.xpath(OR.getProperty(values[2])));
								String orderby = asc_desc.getAttribute("className");
								
								System.out.println(asc_desc.getAttribute("className"));
								
								if (orderby.contains("headerSortUp"))
								{
									if (compare <= 0)
									{
										// accusam34 accusam346diam
										result = true;
										System.out.println("Correctly ordered ASC ");
									}
									else
									{
										return "Fail -> The sorting was Not correct " + firstvalue + value + "Not less than " + nextRowValue + secondvalue ;
									}
								}
								else if (orderby.contains("headerSortDown"))
								{
									if (compare >= 0)
									{
										// accusam34 accusam346diam
										result = true;
										System.out.println("Correctly ordered DESC ");
									}
									else
									{
										return "Fail -> The sorting was Not correct " + firstvalue + value + "Not greater than " + nextRowValue + secondvalue ;
									}
								}
								else
								{
									return "Fail -> The object does not contains the header link ";
								}
								i++;
							
							}
   						}
					/////
							else 
							{
								for (WebElement row : rows) 
								{
									if (rows.size() == i+1)
									{
										break;
									}
									System.out.println("Row: " + i.toString());
									//WebElement cell = row.findElement(By.tagName("td"));
									List<WebElement> cells = row.findElements(By.tagName("td"));
									if(!values[3].isEmpty())
									{
										firstvalue = cells.get(Integer.parseInt(values[3])).getText();
										System.out.println("The first value displayed is: " + firstvalue);
										nextRowValue = rows.get(i+1).findElements(By.tagName("td")).get(Integer.parseInt(values[3])).getText();
										System.out.println("The second value displayed is: " + nextRowValue);
										value = null;
										secondvalue = null;

										Boolean isDate = false;
										DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
										try
										{
											value = df.parse(firstvalue);
											isDate =true;
											System.out.println("The first date value is: " + value);
										}
										catch(Throwable t){ }
										
										try {
											secondvalue = df.parse(nextRowValue);
											isDate =true;
											System.out.println("The second date value is: " + secondvalue);
										}
										catch(Throwable t){	}									
										if (isDate)
										{
											if (firstvalue.contains("N/A"))
											{
												value = df.parse("1/1/1900");
											}
											if (nextRowValue.contains("N/A"))
											{
												secondvalue = df.parse("1/1/1900");
											}
											
											//addded to 'None' option
											if (firstvalue.contains("None"))
											{
												value = df.parse("1/1/1900");
											}
											if (nextRowValue.contains("None"))
											{
												secondvalue = df.parse("1/1/1900");
											}
											compare = value.compareTo(secondvalue);
											System.out.println("The value of the compare is: " + compare);
										}
										else
										{
											if(object.contains("Replace"))
											{
												if(nextRowValue.contains("_"))
												{
													String firstchar= nextRowValue.substring(0, 1);
													if(!firstchar.contains("_")){
													nextRowValue= nextRowValue.replace('_', '@');
													}
													//secondvalue = nextRowValue;
												}
												if(firstvalue.contains("_"))
												{
													String firstc=firstvalue.substring(0, 1);
													if(!firstc.contains("_")){
													firstvalue= firstvalue.replace('_', '@');
													}
												}
											}
											
											System.out.println(firstvalue);
											System.out.println(nextRowValue);
											
											compare = firstvalue.toUpperCase().compareTo(nextRowValue.toUpperCase());
											
										}
									}						
									else
									{
										return "Fail -> Was not specified the Sorting column to verify";
									}
									WebElement asc_desc =  driver.findElement(By.xpath(OR.getProperty(values[2])));
									String orderby = asc_desc.getAttribute("className");
									
									System.out.println(asc_desc.getAttribute("className"));
									
									if (orderby.contains("headerSortUp"))
									{
										if(firstvalue.contains("null"))
										{
											if(nextRowValue.isEmpty())
											{
												compare=0;
											}
										}
										if (compare <= 0)
										{
											// accusam34 accusam346diam
											result = true;
											System.out.println("Correctly ordered ASC ");
										}
										else
										{
											return "Fail -> The sorting was Not correct " + firstvalue + value + "Not less than " + nextRowValue + secondvalue;
										}
									}
									else if (orderby.contains("headerSortDown"))
									{
										if(firstvalue.isEmpty())
										{
											if(nextRowValue.contains("null"))
											{
												compare=0;
											}
										}
										if (compare >= 0)
										{
											// accusam34 accusam346diam
											result = true;
											System.out.println("Correctly ordered DESC ");
										}
										else
										{
											return "Fail -> The sorting was Not correct " + firstvalue + value + "Not greater than " + nextRowValue + secondvalue ;
										}
									}
									else
									{
										return "Fail -> The object does not contains the header link ";
									}
									i++;
							   }
							}
							//i++;
							System.out.println(" ");
						} 
						else 
						{
							return "Fail ->There is No Elements in the Table";
						}
					}

				}

			} 
			catch (Throwable t)
			{
				System.out.println("Error verifying the sorting ASC/DESC ");
				return "Fail " + t.getMessage();
			}
			try
			{
				if(!object.contains("NoLinkNext"))
				{
					if (values[1].contains("_ID")) 
					{
						link_nexts = driver.findElements(By.id(OR.getProperty(values[1])));
					} 
					else if (values[1].contains("_xpath"))
					{
						link_nexts = driver.findElements(By.xpath(OR.getProperty(values[1])));
					} 
					else if (values[1].contains("_css")) 
					{
						link_nexts = driver.findElements(By.cssSelector(OR.getProperty(values[1])));
					}
					else 
					{
						return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					link_next_displayed = true;
				}
				
			} 
			catch (Throwable t)
			{}
			if (link_next_displayed)
			{
				for (WebElement link_next : link_nexts)
				{
					if (link_next.isDisplayed()) 
					{
						System.out.println(link_next.getAttribute("class"));
						if (!link_next.getAttribute("class").contains("inactive")) 
						{
							try 
							{
								link_next.click();
								waitCOLTServer();
								System.out.println("Next link");
								break;
							} 
							catch (Throwable t) 
							{
								return "Fail -> Not possible to click on next link";
							}
						} 
						else
						{
							inactive = true;
							return "Pass";
						}
					}
				}
			} 
			else 
			{
				inactive = true;
			}

		}
		if (result)
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	
	public static String verifyNumRecords()
	{
		System.out.println("Executing: verifyNumRecords " + object);
		try
		{
			List<WebElement> tables = null;
			List<WebElement> rows = null;
			WebElement element = null;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (values[0].contains("_ID"))
			{
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));
				tables = driver.findElements(By.id(OR.getProperty(values[0])));
			} 
			else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));
				tables = driver.findElements(By.xpath(OR.getProperty(values[0])));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));
				tables = driver.findElements(By.cssSelector(OR.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			for (WebElement table : tables)
			{
				if (table.isDisplayed())
				{
					rows = table.findElements(By.tagName("tr"));
				}
			}
			System.out.println(rows.size());
			String num = TestData.getCellData(currentTest, dataColumnName, testRepeat);
			Integer n = Integer.parseInt(num);
			System.out.println(n);
			if(rows.size() == n)
			{
				return "Pass";
			}
			else
				return "Fail -> The number of Paymets were not correctly created, just " + rows.size()+" were created and should be " + num;
		}
		catch (Throwable T)
		{
			return "Error executing the verifyNumber of Records keyword";
		}
		//return "Fail";
	}
	/* clickLinkRandomRecordAction()has following properties:
	 * options of the values in Object:
	 * 		value[0] -> the xpath for the table
	 *      value[1] -> the xpath of the "link_next" of the table for pagination)
	 *      value[2] -> the value "_AirID" that will correspond to search the record by "_AirID", "element_name"
	 *      value[3] -> the value "_Compare" that will mean this function only searches searches element "" in the table (not clicking on the link) 
	 * Options of values in dataColumnName:
	     	data[0] -> action link where the click will be done
	     	data[1] -> value to compare
	   The comparison of a value is 
		1)Object contains "AirID"  -> the comparison element is the "AirID" variable
		2)Object contains "element_Name"  -> the comparison element is the "element_Name" variable
		3) dataColumnName contains "#" -> the comparison element is the data[1] value of ColumnName from TestData file.
		4) If none of the 3 first conditions applies, the value of comparison is variable AlphaName
		5) If object contains "reverse the list will  search in reverse"
		6)If object contains '#Equal' compare should be = instead of like 
	  */
	public static String clickLinkRandomRecordAction() {
		System.out.println("Executing: clickLinkRandomRecordAction " + object);
		List<WebElement> link_nexts = null;
		String[] data = dataColumnName.split("#");
		Boolean compared = false;
		if (data.length < 2) {
			data = new String[2];
			data[0] = dataColumnName;
			data[1] = dataColumnName;
		}
		String action = TestData.getCellData(currentTest, data[0], testRepeat);
		System.out.println(action);
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		Boolean inactive = false;
		Boolean valueFound = false;
		Boolean link_next_displayed = false;
		String ValuetoCompare = null;
		WebElement element = null;
		List<WebElement> elements = null;
		if (object.contains("AirID"))
		{
			ValuetoCompare = AirID;
		}
		else if (object.contains("element_Name"))  
		{
			ValuetoCompare = element_Name;
		}
		else if (dataColumnName.contains("#"))
		{
			ValuetoCompare = TestData.getCellData(currentTest, data[1], testRepeat);
		}
		else
		{
			ValuetoCompare = AlphaName;
		}
			
		System.out.println(dataColumnName);
		System.out.println(ValuetoCompare);
		
		if (ValuetoCompare.charAt(0) == '0')
		{
			ValuetoCompare = ValuetoCompare.substring(1);
			//String value = element.getText().replace('0', ' ');
			System.out.println("The value with the month fixed is :" + ValuetoCompare);
			
		}	
		System.out.println(ValuetoCompare);
		//ValuetoCompare="9999-00be2762-b0c3-4";
		while (inactive != true) {
			Integer i = 0;
			try {
				driver.manage().timeouts()
						.implicitlyWait(20L, TimeUnit.SECONDS);
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));
					elements = driver.findElements(By.id(OR
							.getProperty(values[0])));
				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));
					elements = driver.findElements(By.xpath(OR
							.getProperty(values[0])));
				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));
					elements = driver.findElements(By.cssSelector(OR
							.getProperty(values[0])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}

				for (WebElement table : elements) {
					if (table.isDisplayed()) {
						List<WebElement> rows = table.findElements(By
								.tagName("tr"));
						if(object.contains("#reverse"))
						{
							rows =Lists.reverse(rows);
						}
						//List<WebElement> rowsReverse = Lists.reverse(rows);
						System.out.println(rows.size());
						if (rows.size() > 0) {
							for (WebElement row : rows) {
								i++;
								System.out.println("Row: " + i.toString());
								List<WebElement> cells = row.findElements(By
										.tagName("td"));
								for (WebElement cell : cells) {
									System.out.print(cell.getText() + "---");
									///*
									///*Daysi 401
									if(object.contains("#Equal")) {
										if (cell.getText().equals(ValuetoCompare)) {
											// accusam34 accusam346diaclickLinkRandomRecordActionm
											valueFound = true;
											
											System.out.println("found: "
													+ cell.getText());
									    }
										
									 }
									
									else { 
									if (cell.getText().contains(ValuetoCompare)) {
										// accusam34 accusam346diaclickLinkRandomRecordActionm
										valueFound = true;
										
										System.out.println("found: "
												+ cell.getText());
									  }
									}
									////*
									if(valueFound)
									{
										if(object.contains("#Compare"))
										{
											String compareWith= null;
										/*	if(object.contains("Date"))
											{
												String currentDate= RandomUtilities.getCurrentDate();
												System.out.println("Today's date " + currentDate);
												compareWith=null;
											}
											else
											{*/
												compareWith=TestData.getCellData(currentTest, data[0], testRepeat);
											//}
											if (cell.getText().contains(compareWith))
											{
												System.out.println("Value to compare in the row found: "+ cell.getText());
												return "Pass";
											}
										}
										else
										{
											if (cell.getText().contains(action)) 
											{
													List<WebElement> links = cell.findElements(By.tagName("a"));
													for (WebElement linkA : links) 
													{
														if (linkA.getText()
																.contains(action)) {
															linkA.click();
															Thread.sleep(1500);
															return "Pass";
														}
													}
											}
											
										
										}
											
									}
									
									
								}
								System.out.println(" ");
							}
						} else {
							return "Fail ->There is No Elements in the Table";
						}
					}

				}

			} catch (Throwable t) {
				System.out.println("Error in clicking the Action Link or comparing");
				return "Fail " + t.getMessage();
			}
			if(!values[1].contains("Nolink_next"))
			{
			try {
				
					if (values[1].contains("_ID")) {
					link_nexts = driver.findElements(By.id(OR
							.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
					link_nexts = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
					link_nexts = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
					} else {
					return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					link_next_displayed = true;
				
				
				} catch (Throwable t) {	}
			}	
			if (link_next_displayed) {
				for (WebElement link_next : link_nexts)

				{
					if (link_next.isDisplayed()) {
						System.out.println(link_next.getAttribute("class"));
						if (!link_next.getAttribute("class").contains(
								"inactive")) {
							try {
								link_next.click();
								waitCOLTServer();
								System.out.println("Next link");
								break;
							} catch (Throwable t) {
								return "Fail -> Not possible to click on next link";
							}

						} else {
							inactive = true;
							return "Fail";
						}
					}
				}
			} else {
				inactive = true;
			}

		}
		return "Fail";
	}
	
	
	//////*****Daysi 401
	public static String verifyRandomElementsDisplayed() {
		System.out.println("Executing: verifyElementsDisplayed " + object);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement next = null;
		String ValuetoCompare = null;
		String[] data = dataColumnName.split("#");
		if (data.length < 2) {
			data = new String[2];
			data[0] = dataColumnName;
			data[1] = dataColumnName;
		}
		
//
		String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);

//
		if(!dataColumnName.isEmpty())
		{
			ValuetoCompare = TestData.getCellData(currentTest, data[0], testRepeat);
			System.out.println("The value to compare readed from TestData is :" + ValuetoCompare);
		}
		else if(object.contains("_AirID"))
		{
			ValuetoCompare = AirID;
			System.out.println("The value to compare is the AirID : " + ValuetoCompare);
		}
		
//
		if(!datainfo[0].isEmpty())
			{
			 ValuetoCompare = datainfo[0];
			}
			
///
		else
		{
		ValuetoCompare = AlphaName;
		System.out.println("The value to compare is the AlphaName : " + ValuetoCompare);
		}
		
		if (object.contains("element_Name"))  
		{
			ValuetoCompare = element_Name;
		}
		 ValuetoCompare = ValuetoCompare.toUpperCase();
		Boolean inactive = false;
		List<WebElement> link_nexts = null;
		WebElement element = null;
		Boolean link_next_displayed = false;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}

		while (inactive != true) {
			try {
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				List<WebElement> rows = null;
				if(element.isDisplayed())
				{
					rows = element.findElements(By.tagName("tr"));
				}
				else
				{
					System.out.println("The table is not displayed");
					return "Pass";
				}

				//List<WebElement> rows2 = Lists.reverse(rows);

				if (rows.size() > 0) {
					System.out.println(rows.size());
					for (WebElement row : rows) {
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "   ");
							
							////Daysi 401
						 if (object.contains("#Equal")){
							  if (!cell.getText().toUpperCase().equals(ValuetoCompare)) {
									return "Fail-> Record: " + ValuetoCompare
											+ " was not found";
								}
						    }
						 else{	if (!cell.getText().toUpperCase().contains(ValuetoCompare)) {
								return "Fail-> Record: " + ValuetoCompare
										+ " was not found";
							}
						  }
						 }
						System.out.println(" ");
					}
					try {
						if (values[1].contains("_ID")) {
							next = driver.findElement(By.id(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.id(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_xpath")) {
							next = driver.findElement(By.xpath(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.xpath(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_css")) {
							next = driver.findElement(By.cssSelector(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.cssSelector(OR
									.getProperty(values[1])));
						} else {
							return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						link_next_displayed = true;
					} catch (Throwable t) {
					}




					if (link_next_displayed) {
						if (link_nexts.size() > 0) {
							for (WebElement link_next : link_nexts) {
								if (link_next.isDisplayed()) {
									System.out.println(link_next
											.getAttribute("class"));
									if (!link_next.getAttribute("class")
											.contains("inactive")) {
										try {
											link_next.click();
											waitCOLTServer();
											System.out.println("Next link");
											break;
										} catch (Throwable t) {
											return "Pass";
										}

									} else {
										inactive = true;
										return "Pass";
									}
								}
							}
						} else {
							inactive = true;
						}

					} else {
						inactive = true;
					}
				} else {
					return "Pass"; 
				}
	} catch (Throwable t) {				System.out
						.println("Error in verify that the New record was Removed successfully");
				return "Fail " + t.getMessage();
			}
		} 
		return "Pass";
	

	}
	
	
	/* If object contains '#Equal' compare should be '=' instead of like */

	public static String verifyRandomRecordDeleted() {
		System.out.println("Executing: verifyRandomRecordDeleted " + object);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement next = null;
		String ValuetoCompare = null;
		String[] data = dataColumnName.split("#");
		if (data.length < 2) {
			data = new String[2];
			data[0] = dataColumnName;
			data[1] = dataColumnName;
		}
		
//
		String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);

//
		if(!dataColumnName.isEmpty())
		{
			ValuetoCompare = TestData.getCellData(currentTest, data[0], testRepeat);
			System.out.println("The value to compare readed from TestData is :" + ValuetoCompare);
		}
		else if(object.contains("_AirID"))
		{
			ValuetoCompare = AirID;
			System.out.println("The value to compare is the AirID : " + ValuetoCompare);
		}
		
//
		else if(!datainfo[0].isEmpty())
			{
			 ValuetoCompare = datainfo[0];
			}
			
///
		else
		{
		ValuetoCompare = AlphaName;
		System.out.println("The value to compare is the AlphaName : " + ValuetoCompare);
		}
		
		if (object.contains("element_Name"))  
		{
			ValuetoCompare = element_Name;
		}
		
		Boolean inactive = false;
		List<WebElement> link_nexts = null;
		WebElement element = null;
		Boolean link_next_displayed = false;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}

		while (inactive != true) {
			try {
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				List<WebElement> rows = null;
				if(element.isDisplayed())
				{
					rows = element.findElements(By.tagName("tr"));
				}
				else
				{
					System.out.println("The table is not displayed");
					return "Pass";
				}

				//List<WebElement> rows2 = Lists.reverse(rows);

				if (rows.size() > 0) {
					System.out.println(rows.size());
					for (WebElement row : rows) {
						List<WebElement> cells = row.findElements(By
								.tagName("td"));
						for (WebElement cell : cells) {
							System.out.print(cell.getText() + "   ");
							
							////Daysi 401
						 if (object.contains("#Equal")){
							  if (cell.getText().equals(ValuetoCompare)) {
									return "Fail-> Record: " + ValuetoCompare
											+ " was found";
								}
						    }
						 else{	if (cell.getText().contains(ValuetoCompare)) {
								return "Fail-> Record: " + ValuetoCompare
										+ " was found";
							}
						  }
						 }
						System.out.println(" ");
					}
					try {
						if (values[1].contains("_ID")) {
							next = driver.findElement(By.id(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.id(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_xpath")) {
							next = driver.findElement(By.xpath(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.xpath(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_css")) {
							next = driver.findElement(By.cssSelector(OR
									.getProperty(values[1])));
							link_nexts = driver.findElements(By.cssSelector(OR
									.getProperty(values[1])));
						} else {
							return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						link_next_displayed = true;
					} catch (Throwable t) {
					}
					if (link_next_displayed) {
						if (link_nexts.size() > 0) {
							for (WebElement link_next : link_nexts) {
								if (link_next.isDisplayed()) {
									System.out.println(link_next
											.getAttribute("class"));
									if (!link_next.getAttribute("class")
											.contains("inactive")) {
										try {
											link_next.click();
											waitCOLTServer();
											System.out.println("Next link");
											break;
										} catch (Throwable t) {
											return "Pass";
										}

									} else {
										inactive = true;
										return "Pass";
									}
								}
							}
						} else {
							inactive = true;
						}

					} else {
						inactive = true;
					}
				} else {
					return "Pass";
				}
			} catch (Throwable t) {
				System.out
						.println("Error in verify that the New record was Removed successfully");
				return "Fail " + t.getMessage();
			}
		}
		return "Pass";
	}

	public static String deleteRecordByDate()

	{
		System.out.println("Executing: deleteRecordByDate " + object);
		AlphaName = TestUtil.getCurrentDate();
		String result = "Pass";
		String[] values = object.split("#");

		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		try {
			Thread.sleep(2000);
			while (result.contains("Pass")) {
				result = clickLinkRandomRecordAction();
				Thread.sleep(2000);
				if (result.contains("Pass")) {
					WebElement confirmButton = driver.findElement(By.xpath(OR
							.getProperty(values[2])));
					confirmButton.click();
					Thread.sleep(1500);
				}
			}
			if (result.contains("Fail")) {
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error Deleting");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";

	}

	public static String deleteRecordByParameter()

	{
		System.out.println("Executing: deleteRecordByDate " + object);
		String result = "Pass";
		String[] values = object.split("#");
		if (object.contains("#CurrentDate")) {
			AlphaName = TestUtil.getCurrentDate();
		} else if (dataColumnName.contains("#Parameter")) {
			String[] data = dataColumnName.split("#");
			if (data.length < 2) {
				return "Fail -> There is no Value to Compare to Delete Records";
			}
			AlphaName = TestData.getCellData(currentTest, data[1], testRepeat);
		}
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load
		try {
			Thread.sleep(2000);
			while (result.contains("Pass")) {
				result = clickLinkRandomRecordAction();
				// Thread.sleep(1000);
				if (result.contains("Pass")) {
					WebElement confirmButton = driver.findElement(By.xpath(OR
							.getProperty(values[2])));
					confirmButton.click();
					Thread.sleep(1500);
				}
			}
			if (result.contains("Fail")) {
				return "Pass";
			}
		} catch (Throwable t) {
			System.out.println("Error Deleting");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";

	}

	public static String closeBrowser() {
		System.out.println("Executing: closeBrowser");
		try {
			//driver.quit();
			driver.close();
			driver = null;
		} catch (Throwable t) {
			return "Fail->" + t.getMessage();
		}
		return "Pass";
	}

	public static String save_TransferID() {
		System.out.println("Executing save TransferID from account " + object);
		String[] values;
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																			// for
																			// page
																			// to
																			// Load

		try {
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement element = wait.until(presenceOfElementLocated(By
					.xpath(OR.getProperty(object)))); // Wait for element to
														// Load

			values = element.getText().trim().split("ID:");
			System.out.println(values[1]);
			transferID = values[1];
			TestData.setCellData(currentTest, dataColumnName, testRepeat,
					values[1]);
		} catch (Throwable t) {
			System.out.println("Error in save New UserName: ");
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String searchText() {
		try {
			System.out.println("Executing: searchText " + object);
			String expected = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement ul = wait.until(presenceOfElementLocated(By.xpath(OR
					.getProperty(object)))); // Wait for element to Load

			// WebElement ul =
			// driver.findElement(By.xpath(OR.getProperty(object)));
			List<WebElement> elements = ul.findElements(By.tagName("li"));
			int i;
			for (WebElement element : elements) {
				String[] text = element.getText().trim().split(" ");
				System.out.println(text[0]);
				for (i = 0; i < text.length; i++) {
					if (text[i].contains(expected)) {
						return "Pass";
					}
				}
			}
		} catch (Throwable t) {
			System.out
					.println("Error while getting the Current Date from Calendar "
							+ t.getMessage());
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String compareNumberInvoice() {
		System.out.println("Executing save Number Invoice " + object);
		try {
			String[] values = object.split("#");
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement table = wait.until(presenceOfElementLocated(By.xpath(OR
					.getProperty(values[0])))); // Wait for element to Load

			// WebElement table =
			// driver.findElement(By.xpath(OR.getProperty(objects.get(0))));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			System.out.println(rows.size());
			int i = 1;
			if (rows.size() > 0) {
				int randomvalue = RandomUtilities.random.nextInt(rows.size()) + 1;
				System.out.println(randomvalue);

				for (WebElement row : rows) {
					System.out.println(i);
					System.out.println(randomvalue);
					if (i == randomvalue) {

						String IID = row.findElement(By.className("iid"))
								.getText();
						System.out.println(IID);
						TestData.setCellData(currentTest, dataColumnName,
								testRepeat, IID);
						List<WebElement> cells = row.findElements(By
								.tagName("a"));
						for (WebElement cell : cells) {
							if (cell.getText().contains("View")) {
								cell.click();
								Thread.sleep(1000);
								WebElement text_ViewMode = driver
										.findElement(By.xpath(OR
												.getProperty(values[1])));
								String ViewModeID = text_ViewMode.getText()
										.trim();
								System.out.println(ViewModeID);
								if (text_ViewMode.getText().contains(IID)) {
									return "Pass";
								}
							}
						}
					}
					i++;
				}
			} else {
				return "Fail, There is No Manage Invoices in the table";
			}
		} catch (Throwable t) {
			System.out.println("Error in comparing the Manage Invoice ID ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String saveValue() {
		Boolean result = false;
		char firststr = 5;
		try {
			System.out.println("Executing saveDefaultDropDownValue " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			WebElement element = wait.until(presenceOfElementLocated(By.id(OR
					.getProperty(values[0])))); // Wait for element to Load
			
			// WebElement element =
			// driver.findElement(By.xpath(OR.getProperty(object)));
			if(object.contains("date"))
			{
				firststr =element.getText().charAt(0);
				System.out.println(firststr);
				
				if (element.getText().charAt(0) == '0')
				{
					String value = element.getText().substring(1);
					//String value = element.getText().replace('0', ' ');
					System.out.println(value);
					result = TestData.setCellData(currentTest, dataColumnName,testRepeat, value);
				}
				else
				{
					result = TestData.setCellData(currentTest, dataColumnName,testRepeat, element.getText());
				}
			}
		} catch (Throwable t) {
			System.out.println("error in saveDefaultDropDownValue: ");
			return "Fail ->" + t.getMessage();
		}
		if (result == true) {
			return "Pass";
		} else {
			return "Fail";
		}
	}
	public static String saveCurrentDate() {
		Boolean result = false;
		try {
			System.out.println("Executing saveCurrentDate " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			String[] values = object.split("#");
			String currentDate= RandomUtilities.getCurrentDate();
			//currentDate="'" +currentDate;
			result = TestData.setCellData(currentTest, dataColumnName,testRepeat, currentDate);
			
		} catch (Throwable t) {
			System.out.println("Error Saving the current Date in TestData ");
			return "Fail ->" + t.getMessage();
		}
		if (result == true) {
			return "Pass";
		} else {
			return "Fail";
		}
	}
	/*For task 235, save random name of user into Test Data*/
	public static String saveRandomValue() {
		Boolean result = false;
		try{
			System.out.println("Executing save Random Value in Test Data " );
			System.out.println(AlphaName);
			result = TestData.setCellData(currentTest, dataColumnName,testRepeat, AlphaName);

		} catch (Throwable t) {
			System.out.println("error in save Random Value in Test Data  ");
			return "Fail ->" + t.getMessage();
		}
		if (result == true) {
			return "Pass";
		} else {
			return "Fail";
		}
	}
	
	
	public static String selectNotDefaultDropdownValue() {
		try {
			System.out.println("Executing selectNotDefaultDropdownValue: "
					+ object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println("Default value is: " + data);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement dropDownListBox = wait.until(presenceOfElementLocated(By
					.xpath(OR.getProperty(object)))); // Wait for element to
														// Load

			// WebElement dropDownListBox =
			// driver.findElement(By.xpath(OR.getProperty(object))); //getting
			// the dropdown
			dropDownListBox.click();
			Thread.sleep(600);
			List<WebElement> elements = dropDownListBox.findElements(By
					.tagName("a"));
			for (WebElement element : elements) {
				if (!element.getText().contains(data)) {
					element.click();
					Thread.sleep(1000);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			System.out.println("error in selectNotDefaultDropdownValue: ");
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}

	public static String checkFirstVehicleList() {
		String vehicleName = "";
		try {
			System.out.println("Executing checkFirstVehicleList" + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement devicesList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load

			// WebElement devicesList =
			// driver.findElement(By.id(OR.getProperty(object))); //getting
			// devices list block
			System.out.println(devicesList.getClass());
			Boolean checked = false;
			List<WebElement> devices = devicesList.findElements(By
					.className("device"));
			if (devices.size() > 0) {
				for (WebElement device : devices) {
					List<WebElement> links = device.findElements(By
							.tagName("span"));
					for (WebElement link : links) {
						link.click();
						Thread.sleep(800);
						checked = true;
						break;
					}
					//
					List<WebElement> divs = device.findElements(By
							.tagName("div"));
					Thread.sleep(600);
					for (WebElement div : divs) {
						vehicleName = div.getText();
						System.out.println(vehicleName);
						return "Pass";
					}
				}
			} else {
				System.out.println("There are No Vehicles in the List");
				return "Fail";
			}
		} catch (Throwable t) {
			System.out.println("Error checking the Vehicle " + t);
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String checkSearchedVehicle() {
		try {
			System.out.println("Executing checkSearchedVehicleID " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebElement list= null;
			String valueCompare=null;
			Boolean inactive = false;
			List<WebElement> link_nexts=null;
			Boolean next_displayed= false;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			/*else
			{
				datainfo= new String[2];
				datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
				datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			}*/
			
			if(!datainfo[0].isEmpty())
			{
				valueCompare = datainfo[0];
			}
			if(object.contains("_elementName"))
			{
				valueCompare=element_Name;
			}
			while(!inactive)
			{
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					list = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				List<WebElement> devices = list.findElements(By.className("device"));
				if (devices.size() > 0) 
				{
					for (WebElement device : devices)
					{
						WebElement checkbox = device.findElements(By.tagName("span")).get(0);
						List<WebElement> divs = device.findElements(By.tagName("div"));
						for (WebElement div : divs) 
						{
							System.out.println(div.getText()+ "--");
							String divtext=div.getText();
							if(divtext.contains("-\n"))
							{
								divtext = divtext.replace("-\n", "");
							}
							if (divtext.equals(valueCompare))
							{
								checkbox.click();
								if(object.contains("checkClass"))
								{
									String classvalue=div.getAttribute("class");
									System.out.println(div.getAttribute("class"));
									if(classvalue.contains(datainfo[1]))
									{
										return "Pass";
									}
								}
								else
								{
									return "Pass";
								}
								
							}
						}
					}
				} else {
					System.out.println("There are No Vehicles in the List");
					return "Fail";
				}
				try 
				{
					
					if (values[1].contains("_ID"))
					{
					link_nexts = driver.findElements(By.id(OR.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
					link_nexts = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
					link_nexts = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
					} else {
					return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					next_displayed= true;
				
				
				} catch (Throwable t) {	}	
				if(next_displayed)
				{
					for(WebElement link_next:link_nexts)
					{
						if(link_next.isDisplayed())
						{
						try {
							link_next.click();
							System.out.println("Going to next page");
							waitCOLTServer();
							break;
						} catch (Throwable t) {
							return "Fail -> Not possible to click on next link";
						}
						}
					}
					inactive=true;
				}
				else
				{
					inactive=true;
				}
			}
			
		} catch (Throwable t) {
			System.out.println("Error checking the Vehicle " + t);
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}

	public static String verifyTextWelcomeUser() {
		String actual = "";
		String expected = "";
		try {
			System.out.println("Executing verifyTextWithSavedValueID: "
					+ object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			expected = APPTEXT.getProperty(object) + data;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */60);
			WebElement element = wait.until(presenceOfElementLocated(By.id(OR
					.getProperty(object)))); // Wait for element to Load

			actual = element.getText();
			Assert.assertEquals(expected.trim(), actual.trim());
			return "Pass";
		} catch (Throwable t) {
			System.out.println("Error in Header: " + "Expected Text: "
					+ expected + "; Actual Text: " + actual);
			return "Fail" + t.getMessage();
		}
	}

	public static String verifyTextUppercase() {
		String actual = "";
		String expected = "";
		try {
			System.out.println("Executing verifyTextWithSavedValue: " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement element = wait.until(presenceOfElementLocated(By
					.xpath(OR.getProperty(object)))); // Wait for element to
														// Load
			actual = element.getText();
			expected = data.toUpperCase();
			Assert.assertEquals(expected.trim(), actual.trim());
			return "Pass";
		} catch (Throwable t) {
			System.out.println("Error in Header: " + "Expected Text: "
					+ expected + "; Actual Text: " + actual);
			return "Fail ->" + t.getMessage();
		}
	}

	public static String verifyFolderDeletedID() {
		try {
			System.out.println("Executing verifyFolderDeletedID: " + object);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			System.out.println("folder to verify is: " + data);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement folderList = wait.until(presenceOfElementLocated(By
					.id(OR.getProperty(object)))); // Wait for element to Load

			// WebElement folderList =
			// driver.findElement(By.id(OR.getProperty(object)));
			List<WebElement> elements = folderList.findElements(By
					.className("folder"));
			Thread.sleep(600);
			for (WebElement element : elements) {
				String folderName = element.findElement(By.tagName("b"))
						.getText();
				if (folderName.contains(data)) {
					System.out.println("Error: Folder was not deleted");
					return "Fail";
				}
			}
			return "Pass";
		} catch (Throwable t) {
			System.out.println("error in editFolderID: ");
			return "Fail ->" + t.getMessage();
		}
	}

	public static String clickSubAccountSearched() 
	{
		try {
			System.out.println("Executing clickSubAccountSearched: " + object);
			String accountName = TestData.getCellData(currentTest,dataColumnName, testRepeat);
			
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
																				// Load
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(object))));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(object))));

			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(object))));

			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			
			
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
													// Load
			if (element.getText().contentEquals(accountName)) 
				{
					element.click();
					return "Pass";
				}
			else
				{
				System.out.println("Account name was not found");
				return "Fail";
				}
			
		} catch (Throwable t) {
			System.out.println("Error in clickSubAccountSearched ");
			return "Fail" + t.getMessage();
		}
		
	}

	public static String clickAlphaNameSearched() {
		try {

						
			System.out.println("Executing clickSubAccountSearched: " + object);
			String valueToCompare = AlphaName;
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));

			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));

			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));

			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			
			
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); // Wait
																				// for
																				// page
																				// to
													// Load
			if (element.getText().contentEquals(valueToCompare)) 
				{
					element.click();
					return "Pass";
				}
			else
				{
				System.out.println("Account name was not found");
				return "Fail";
				}
			
		} catch (Throwable t) {
			System.out.println("Error in clickSubAccountSearched ");
			return "Fail" + t.getMessage();
		}
	}

	public static String check_FirstAirID() {
		try {
			System.out.println("Execute check_FirstAirID: " + object);
			List<WebElement> cellsList = driver.findElement(
					By.xpath(OR.getProperty(object))).findElements(
					By.tagName("tr"));
			for (WebElement cell : cellsList) {
				List<WebElement> checkBoxs = cell.findElements(By
						.tagName("span"));
				List<WebElement> columns = cell.findElements(By.tagName("td"));
				String text = columns.get(0).getText();
				System.out.println("AirID: " + text);
				ESN = columns.get(3).getText();
				System.out.println("ESN: " + ESN);
				System.out.println(text);
				for (WebElement checkbox : checkBoxs) {

					String value = checkbox.getAttribute("class");
					System.out.println(value);
					if (!value.contains("checked")) {
						checkbox.click();
						AirID = text;
						ESN = ESN;
						System.out.println(AirID);
						return "Pass";
					}

				}
			}
			return "Fail The AirIDs table is Empty";
		} catch (Throwable t) {
			System.out.println("Error in clickCheckboxTable:ID ");
			return "Fail" + t.getMessage();
		}

	}

	public static String click_FirstCheckboxListByClass() {
		try {
			System.out.println("Executing clickCheckboxList: " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
			WebElement list = wait.until(presenceOfElementLocated(By
					.className(OR.getProperty(object)))); // Wait for element to
															// Load
			// WebElement checkboxList =
			// driver.findElement(By.xpath(OR.getProperty(object)));
			List<WebElement> checkBoxes = list.findElements(By.tagName("span"));
			if (checkBoxes.size() > 0) {
				checkBoxes.get(0).click();
				return "Pass";
			} else {
				return "Fail -> There is not elements in the list to select";
			}
		} catch (Throwable t) {
			System.out.println("Error in click_FirstCheckboxList: " + t);
			return "Fail";
		}
	}

	public static String click_FirstRole() {
		try {
			System.out.println("Executing clickCheckboxList: " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebElement element = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			List<WebElement> checkBoxes = element.findElements(By
					.className("checkbox"));
			if (checkBoxes.size() > 0) {
				checkBoxes.get(0).click();
				return "Pass";
			} else {
				return "Fail -> There is not elements in the list to select";
			}
		} catch (Throwable t) {
			System.out.println("Error in click_FirstCheckboxList: " + t);
			return "Fail";
		}
	}

	public static String getTotalRowsTables() {
		System.out.println("Executing: getTotalRowsTables " + object);
		//Receives List in object when there is a list
		List<WebElement> link_nexts = null;
		rowsTotalTables = 0;
		String[] values = object.split("#");
		if (values.length < 2) {
			values = new String[2];
			values[0] = object;
			values[1] = object;
		}
		Boolean inactive = false;
		Boolean valueFound = false;
		Boolean link_next_displayed = false;
		WebElement element = null;
		List<WebElement> elements = null;
		String attribute= null;
		while (inactive != true) {
			Integer i = 0;
			try {
				driver.manage().timeouts()
						.implicitlyWait(20L, TimeUnit.SECONDS);
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[0]))));
					elements = driver.findElements(By.id(OR
							.getProperty(values[0])));
				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[0]))));
					elements = driver.findElements(By.xpath(OR
							.getProperty(values[0])));
				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[0]))));
					elements = driver.findElements(By.cssSelector(OR
							.getProperty(values[0])));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				for (WebElement table : elements) {
					if (table.isDisplayed()) {
						
						if(object.contains("List"))
						{
							rowsTotalTables = rowsTotalTables + table.findElements(By.className("device_holder")).size();
						}
						else
						{
							rowsTotalTables = rowsTotalTables
							+ table.findElements(By.tagName("tr")).size();
							Thread.sleep(1000);
						}
						
						System.out.println(rowsTotalTables);
					}
				}
				try {
					if (values[1].contains("_ID")) {
						link_nexts = driver.findElements(By.id(OR
								.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
						link_nexts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
						link_nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
					} else {
						return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					link_next_displayed = true;
				} catch (Throwable t) {
				}
				if (link_next_displayed) {
					for (WebElement link_next : link_nexts) {
						if (link_next.isDisplayed()) {
							attribute = link_next.getAttribute("class");
							System.out.println(attribute);
							
							if (((attribute.contains("inactive")))||((attribute.contains("hidden"))))///ERROR HERE
							{
								inactive = true;
								return "Pass";

							} else {
								try {
									link_next.click();
									waitCOLTServer();
									Thread.sleep(2000);
									System.out.println("Next link");
								} catch (Throwable t) {
									return "Fail -> Not possible to click on next link";
								}
								
							}
						}
						else
						{
							System.out.println(link_next.getAttribute("class"));
							if ((link_next.getAttribute("class").contains("inactive"))||(link_next.getAttribute("class").contains("hidden")))
							{
								inactive = true;
								return "Pass";
							}
						}
						
					}
				} else {
					inactive = true;
					return "Pass";
				}
			} catch (Throwable t) {
				return "Fail -> Error Executing getTotalRowsTables" + t;
			}

		}
		return "Fail -> Error Executing getTotalRowsTables";
	}

	public static String getNumPagesPerPage() {
		try {
			System.out.println("Executing: getNumPagesPerPage" + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);

			if (!data.isEmpty()) {
				int perPage = Integer.parseInt(data);
				Thread.sleep(600);
				numPages = rowsTotalTables / perPage;
				System.out.println(numPages);
				if (numPages == 0) {
					numPages = 1;
					System.out.println(numPages);
					return "Pass";
				} else {
					Integer mod5 = rowsTotalTables % perPage;
					Thread.sleep(600);
					System.out.println("Total rows: " + rowsTotalTables
							+ "mod DisplayedPerPage " + perPage + '=' + mod5);
					if (mod5 > 0) {
						numPages++;
						System.out.println("Num of Pages=" + numPages);
						Thread.sleep(600);
						return "Pass";
					} else {
						System.out.println("Num of Pages=" + numPages);
						return "Pass";
					}
				}

			} else {
				return "Fail -> The data is empty from TestData.xls";
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
	}

	public static String getNumOptionsDropdown() {
		try {
			System.out.println("Executing: getNumOptionsDropdown " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			WebElement element = null;
			List<WebElement> elements = null;
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
				elements = driver.findElements(By.id(OR.getProperty(object)));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
				elements = driver
						.findElements(By.xpath(OR.getProperty(object)));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
				elements = driver.findElements(By.cssSelector(OR
						.getProperty(object)));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}

			for (WebElement e : elements) {
				if (e.isDisplayed()) {
					e.click();
					Thread.sleep(2500);
					int count = 0;
					List<WebElement> ops = e.findElements(By.tagName("a"));
					for (WebElement o : ops) {
						if (o.isDisplayed()) {
							count++;
							Thread.sleep(700);
						}
					}
					System.out.println(count);
					options = count;
					System.out.println(options);
					Thread.sleep(1000);
					e.click();
					Thread.sleep(1000);
					return "Pass";
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error getNumOptionsDropdown" + t;
		}
		return "Fail -> Not able to complete with ";
	}

	public static String verifyNumberOfPages() {
		try {
			System.out.println("Executing: verifyNumberOfPages " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (numPages == options) {
				return "Pass";
			} else {
				return "Fail -> The Number of Pages is not accurate. Expected:"
						+ numPages + "Current:" + options;
			}
		} catch (Throwable t) {
			return "Fail -> Error getNumOptionsDropdown" + t;
		}

	}

	public static String getRowsTable() {
		try {
			System.out.println("Executing: getRowsTable " + object);
			String[] values = object.split("#");
			WebElement element = null;
			Boolean displayed = false;
			List<WebElement> elements = null;
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
				elements = driver
						.findElements(By.id(OR.getProperty(values[0])));
			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				elements = driver.findElements(By.xpath(OR
						.getProperty(values[0])));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
						20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(values[0]))));
				elements = driver.findElements(By.cssSelector(OR
						.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}

			for (WebElement table : elements) {
				if (table.isDisplayed()) {
					displayed = true;
					if(object.contains("List"))
					{
						rowsTable = table.findElements(By.className("device_holder")).size();
					}
					else
					{
						rowsTable = table.findElements(By.tagName("tr")).size();	
					}
					
					System.out.println(rowsTable);
					return "Pass";
				}
			}
			if (!displayed) {
				rowsTable = 0;
				emptyTable = true;
				System.out.println(rowsTable);
				return "Pass";
			}

		} catch (Throwable t) {
			return "Fail -> Error executing the getRowsTable" + t;
		}
		return "Fail -> Error executing the getRowsTable";
	}

	/*public static String verifyNextPreviousLinks() {
		Boolean valid = false;
		String numPage = null;
		try {
			System.out.println("Executing: verifyNextPreviousLinks " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String[] values = object.split("#");
			List<WebElement> link_Firsts = null;
			List<WebElement> link_Previous = null;
			List<WebElement> link_Nexts = null;
			List<WebElement> link_Lasts = null;
			WebElement pageNum = null;

			List<WebElement> pageNums = null;

			if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
					&& (values[3].contains("_ID"))
					&& (values[4].contains("_ID"))) {
				link_Firsts = driver.findElements(By.id(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.id(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.id(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.id(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_xpath"))
					&& (values[2].contains("_xpath"))
					&& (values[3].contains("_xpath"))
					&& (values[4].contains("_xpath"))) {
				link_Firsts = driver.findElements(By.xpath(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.xpath(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.xpath(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.xpath(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_css"))
					&& (values[2].contains("_css"))
					&& (values[3].contains("_css"))
					&& (values[4].contains("_css"))) {
				link_Firsts = driver.findElements(By.cssSelector(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.cssSelector(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.cssSelector(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.cssSelector(OR
						.getProperty(values[4])));
			} else {
				return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}

			if (numPages == 1) {
				for (WebElement link_First : link_Firsts) {
					if (link_First.isDisplayed()) {
						if (link_First.getAttribute("class").contains(
								"inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link First is displayed and Active";
						}
					}
				}
				for (WebElement link_Prev : link_Previous) {
					if (link_Prev.isDisplayed()) {
						if (link_Prev.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Previous is displayed as Active";
						}
					}
				}
				for (WebElement link_next : link_Nexts) {
					if (link_next.isDisplayed()) {
						if (link_next.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Next is displayed as Active";
						}
					}
				}
				for (WebElement link_Last : link_Lasts) {
					if (link_Last.isDisplayed()) {
						if (link_Last.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Last is displayed and Active";
						}
					}
				}
				// Until here tested the links in 1 page
				if (valid) {
					String result = getRowsTable();
					if (result == "Pass") {
						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								Thread.sleep(600);
							}
						}
						if ((rowsTable == rowsTotalTables)
								&& (numPage.equals("1"))) {
							return "Pass";
						} else {
							return "Fail -> The number of Records displayed is not the accurate. Expected = "
									+ rowsTotalTables + "Current=" + rowsTable;
						}
					}
				}
			} else {
				int page = 1;
				while (page < numPages) {
					if (values[5].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, 
																		 * seconds=
																		 60);
						pageNum = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[5]))));
						pageNums = driver.findElements(By.id(OR
								.getProperty(values[5])));
					} else if (values[5].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, 
																		 * seconds=
																		 60);
						pageNum = wait.until(presenceOfElementLocated(By
								.xpath(OR.getProperty(values[5]))));
						pageNums = driver.findElements(By.xpath(OR
								.getProperty(values[5])));
					} else if (values[5].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, 
																		 * seconds=
																		 60);
						pageNum = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[5]))));
						pageNums = driver.findElements(By.cssSelector(OR
								.getProperty(values[5])));

					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					for (WebElement pageN : pageNums) {
						if (pageN.isDisplayed()) {
							List<WebElement> cancel_buttons = driver
									.findElements(By.id("cancel"));
							for (WebElement cancel : cancel_buttons) {
								while (!cancel.isDisplayed()) {
									numPage = pageN.getText();
									break;
								}
							}

						}
					}
					if (Integer.parseInt(numPage) == page) {
						valid = true;
					} else {
						return "Fail -> The 'Page Number' does not display the correct number. Expected="
								+ page + "Current=" + numPage;
					}

					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								link_next.click();
								if (object.contains("#time")) {
									waitCOLTServer();
								} else {
									waitCOLTServer();
								}
								page++;
							} else {
								return "Fail -> The link Next is displayed as Inactive";
							}
						}
					}
				}
				if (page == numPages)// Last page
				{
					// Here we valid if all the links are displayed correctly
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (!link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed as Inactive";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed as Active";
							}
						}
					}
				}// until here tested Last page links
				while (page > 1)// Here start go back to First page
				{
					for (WebElement link_Prev : link_Previous) {

						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								Thread.sleep(1000);
							}
						}
						if (Integer.parseInt(numPage) == page) {
							valid = true;
						} else {
							return "Fail -> The 'Page Number' does not display the correct number. Expected="
									+ page + "Current=" + numPage;
						}

						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								link_Prev.click();
								if (object.contains("#time")) {
									waitCOLTServer();
								} else {
									waitCOLTServer();
								}
								page--;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
				}

				if (page == 1)// First page .. To test again the links
				{
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed and Active";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Active";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed and Active";
							}
						}
					}
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail -> Error executing verify: 'Next' and 'Previous' Links";
		}
	}

	public static String verifyFirstLastLinks() {
		Boolean valid = false;
		String numPage = null;
		try {
			System.out.println("Executing: verifyFirstLastLinks " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String[] values = object.split("#");
			List<WebElement> link_Firsts = null;
			List<WebElement> link_Previous = null;
			List<WebElement> link_Nexts = null;
			List<WebElement> link_Lasts = null;
			WebElement pageNum = null;
			List<WebElement> pageNums = null;

			if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
					&& (values[3].contains("_ID"))
					&& (values[4].contains("_ID"))) {
				link_Firsts = driver.findElements(By.id(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.id(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.id(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.id(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_xpath"))
					&& (values[2].contains("_xpath"))
					&& (values[3].contains("_xpath"))
					&& (values[4].contains("_xpath"))) {
				link_Firsts = driver.findElements(By.xpath(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.xpath(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.xpath(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.xpath(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_css"))
					&& (values[2].contains("_css"))
					&& (values[3].contains("_css"))
					&& (values[4].contains("_css"))) {
				link_Firsts = driver.findElements(By.cssSelector(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.cssSelector(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.cssSelector(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.cssSelector(OR
						.getProperty(values[4])));
			} else {
				return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (numPages == 1) {
				for (WebElement link_First : link_Firsts) {
					if (link_First.isDisplayed()) {
						if (link_First.getAttribute("class").contains(
								"inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link First is displayed and Active";
						}
					}
				}
				for (WebElement link_Prev : link_Previous) {
					if (link_Prev.isDisplayed()) {
						if (link_Prev.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Previous is displayed as Active";
						}
					}
				}
				for (WebElement link_next : link_Nexts) {
					if (link_next.isDisplayed()) {
						if (link_next.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Next is displayed as Active";
						}
					}
				}
				for (WebElement link_Last : link_Lasts) {
					if (link_Last.isDisplayed()) {
						if (link_Last.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Last is displayed and Active";
						}
					}
				}
				// Until here tested the links in 1 page
				if (valid) {
					String result = getRowsTable();
					if (result == "Pass") {
						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, 
																			 * seconds
																			 * =
																			 
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								System.out.println("NumPages: " + numPage);
								Thread.sleep(600);
							}
						}
						if ((rowsTable == rowsTotalTables)
								&& (numPage.equals("1"))) {
							return "Pass";
						} else {
							return "Fail -> The number of Records displayed is not the accurate. Expected = "
									+ rowsTotalTables + "Current=" + rowsTable;
						}
					}
				}
			} else {
				int page = 1;
				// Start Testing of First and Last links
				if (page == 1) {
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								link_Last.click();
								waitCOLTServer();
								page = numPages;
								List<WebElement> buttons_cancel = driver
										.findElements(By.id("cancel"));
								for (WebElement button : buttons_cancel) {
									while (!button.isDisplayed()) {
										if (values[5].contains("_ID")) {
											WebDriverWait wait = new WebDriverWait(
													driver,  seconds= 60);
											pageNum = wait
													.until(presenceOfElementLocated(By.id(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.id(OR
															.getProperty(values[5])));
										} else if (values[5].contains("_xpath")) {
											WebDriverWait wait = new WebDriverWait(
													driver,  seconds= 60);
											pageNum = wait
													.until(presenceOfElementLocated(By.xpath(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.xpath(OR
															.getProperty(values[5])));
										} else if (values[5].contains("_css")) {
											WebDriverWait wait = new WebDriverWait(
													driver,  seconds= 60);
											pageNum = wait
													.until(presenceOfElementLocated(By.cssSelector(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.cssSelector(OR
															.getProperty(values[5])));

										} else {
											return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
										}

										for (WebElement pageN : pageNums) {
											if (pageN.isDisplayed()) {
												numPage = pageN.getText();
												Thread.sleep(600);
												break;
											}
										}
										if (Integer.parseInt(numPage) == numPages) {
											valid = true;
											break;
										} else {
											return "Fail -> The number of Page is not accurate. Expected = "
													+ numPages
													+ "Current="
													+ numPage;
										}
									}
								}
								// until here the active
							} else {
								return "Fail -> The link Last is displayed as Inactive";
							}
						}
					}
				}
				if (page == numPages)// Last page
				{
					// Here we valid if all the links are displayed correctly

					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed as Active";
							}
						}
					}
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (!link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								link_First.click();
								waitCOLTServer();
								page = 1;
								if (values[5].contains("_ID")) {
									WebDriverWait wait = new WebDriverWait(
											driver,  seconds= 60);
									pageNum = wait
											.until(presenceOfElementLocated(By.id(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By.id(OR
											.getProperty(values[5])));
								} else if (values[5].contains("_xpath")) {
									WebDriverWait wait = new WebDriverWait(
											driver,  seconds= 60);
									pageNum = wait
											.until(presenceOfElementLocated(By.xpath(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By.xpath(OR
											.getProperty(values[5])));
								} else if (values[5].contains("_css")) {
									WebDriverWait wait = new WebDriverWait(
											driver,  seconds= 60);
									pageNum = wait
											.until(presenceOfElementLocated(By.cssSelector(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By
											.cssSelector(OR
													.getProperty(values[5])));

								} else {
									return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
								}

								for (WebElement pageN : pageNums) {
									if (pageN.isDisplayed()) {
										numPage = pageN.getText();
										Thread.sleep(600);
										break;
									}
								}
								if (numPage.equals("1")) {
									valid = true;
								} else {
									return "Fail -> The number of Page is not accurate. Expected = 1, Current="
											+ numPage;
								}
							} else {
								return "Fail -> The link First is displayed as Inactive";
							}
						}
					}
				}// until here tested Last page links
				if (page == 1)// First page .. To test again the links
				{
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed and Active";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Active";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed and Active";
							}
						}
					}
				}
			}
		}

		catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail -> Error executing verify: 'Next' and 'Previous' Links";
		}
	}
*/
	
	public static String verifyNextPreviousLinks() {
		Boolean valid = false;
		String numPage = null;
		try {
			System.out.println("Executing: verifyNextPreviousLinks " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String[] values = object.split("#");
			List<WebElement> link_Firsts = null;
			List<WebElement> link_Previous = null;
			List<WebElement> link_Nexts = null;
			List<WebElement> link_Lasts = null;
			WebElement pageNum = null;

			List<WebElement> pageNums = null;

			if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
					&& (values[3].contains("_ID"))
					&& (values[4].contains("_ID"))) {
				link_Firsts = driver.findElements(By.id(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.id(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.id(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.id(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_xpath"))
					&& (values[2].contains("_xpath"))
					&& (values[3].contains("_xpath"))
					&& (values[4].contains("_xpath"))) {
				link_Firsts = driver.findElements(By.xpath(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.xpath(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.xpath(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.xpath(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_css"))
					&& (values[2].contains("_css"))
					&& (values[3].contains("_css"))
					&& (values[4].contains("_css"))) {
				link_Firsts = driver.findElements(By.cssSelector(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.cssSelector(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.cssSelector(OR.getProperty(values[3])));
				link_Lasts = driver.findElements(By.cssSelector(OR
						.getProperty(values[4])));
			} else {
				return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}

			if (numPages == 1) {
				for (WebElement link_First : link_Firsts) {
					if (link_First.isDisplayed()) {
						if (link_First.getAttribute("class").contains(
								"inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link First is displayed and Active";
						}
					}
				}
				for (WebElement link_Prev : link_Previous) {
					if (link_Prev.isDisplayed()) {
						if (link_Prev.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Previous is displayed as Active";
						}
					}
				}
				for (WebElement link_next : link_Nexts) {
					if (link_next.isDisplayed()) {
						if (link_next.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Next is displayed as Active";
						}
					}
				}
				for (WebElement link_Last : link_Lasts) {
					if (link_Last.isDisplayed()) {
						if (link_Last.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Last is displayed and Active";
						}
					}
				}
				// Until here tested the links in 1 page
				if (valid) {
					String result = getRowsTable();
					if (result == "Pass") {
						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								Thread.sleep(600);
							}
						}
						if ((rowsTable == rowsTotalTables)
								&& (numPage.equals("1"))) {
							return "Pass";
						} else {
							return "Fail -> The number of Records displayed is not the accurate. Expected = "
									+ rowsTotalTables + "Current=" + rowsTable;
						}
					}
				}
			} else {
				int page = 1;
				while (page < numPages) {
					if (values[5].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						pageNum = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[5]))));
						pageNums = driver.findElements(By.id(OR
								.getProperty(values[5])));
					} else if (values[5].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						pageNum = wait.until(presenceOfElementLocated(By
								.xpath(OR.getProperty(values[5]))));
						pageNums = driver.findElements(By.xpath(OR
								.getProperty(values[5])));
					} else if (values[5].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						pageNum = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[5]))));
						pageNums = driver.findElements(By.cssSelector(OR
								.getProperty(values[5])));

					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					
					
					for (WebElement pageN : pageNums) {
						if (pageN.isDisplayed()) {
							List<WebElement> cancel_buttons = driver
									.findElements(By.id("cancel"));
							for (WebElement cancel : cancel_buttons) {
								while (!cancel.isDisplayed()) {
									numPage = pageN.getText();
									break;
								}
							}

						}
					}
					if (Integer.parseInt(numPage) == page) {
						valid = true;
					} else {
						return "Fail -> The 'Page Number' does not display the correct number. Expected="
								+ page + "Current=" + numPage;
					}

					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								link_next.click();
								waitCOLTServer();
								Thread.sleep(2500);
								page++;
							} else {
								return "Fail -> The link Next is displayed as Inactive";
							}
						}
					}
				}
				if (page == numPages)// Last page
				{
					// Here we valid if all the links are displayed correctly
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					
					
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (!link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed as Inactive";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed as Active";
							}
						}
					}
				}// until here tested Last page links
				while (page > 1)// Here start go back to First page
				{
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					
					for (WebElement link_Prev : link_Previous) {

						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								Thread.sleep(1000);
							}
						}
						if (Integer.parseInt(numPage) == page) {
							valid = true;
						} else {
							return "Fail -> The 'Page Number' does not display the correct number. Expected="
									+ page + "Current=" + numPage;
						}

						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								link_Prev.click();
								waitCOLTServer();
								Thread.sleep(2500);
								page--;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
				}

				if (page == 1)// First page .. To test again the links
				{
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
	
					
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed and Active";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Active";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed and Active";
							}
						}
					}
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail -> Error executing verify: 'Next' and 'Previous' Links";
		}
	}

	public static String verifyFirstLastLinks() {
		Boolean valid = false;
		String numPage = null;
		try {
			System.out.println("Executing: verifyFirstLastLinks " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String[] values = object.split("#");
			List<WebElement> link_Firsts = null;
			List<WebElement> link_Previous = null;
			List<WebElement> link_Nexts = null;
			List<WebElement> link_Lasts = null;
			WebElement pageNum = null;
			List<WebElement> pageNums = null;

			if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
					&& (values[3].contains("_ID"))
					&& (values[4].contains("_ID"))) {
				link_Firsts = driver.findElements(By.id(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.id(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.id(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.id(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_xpath"))
					&& (values[2].contains("_xpath"))
					&& (values[3].contains("_xpath"))
					&& (values[4].contains("_xpath"))) {
				link_Firsts = driver.findElements(By.xpath(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.xpath(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.xpath(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.xpath(OR
						.getProperty(values[4])));
			} else if ((values[1].contains("_css"))
					&& (values[2].contains("_css"))
					&& (values[3].contains("_css"))
					&& (values[4].contains("_css"))) {
				link_Firsts = driver.findElements(By.cssSelector(OR
						.getProperty(values[1])));
				link_Previous = driver.findElements(By.cssSelector(OR
						.getProperty(values[2])));
				link_Nexts = driver.findElements(By.cssSelector(OR
						.getProperty(values[3])));
				link_Lasts = driver.findElements(By.cssSelector(OR
						.getProperty(values[4])));
			} else {
				return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (numPages == 1) {
				for (WebElement link_First : link_Firsts) {
					if (link_First.isDisplayed()) {
						if (link_First.getAttribute("class").contains(
								"inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link First is displayed and Active";
						}
					}
				}
				for (WebElement link_Prev : link_Previous) {
					if (link_Prev.isDisplayed()) {
						if (link_Prev.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Previous is displayed as Active";
						}
					}
				}
				for (WebElement link_next : link_Nexts) {
					if (link_next.isDisplayed()) {
						if (link_next.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Next is displayed as Active";
						}
					}
				}
				for (WebElement link_Last : link_Lasts) {
					if (link_Last.isDisplayed()) {
						if (link_Last.getAttribute("class")
								.contains("inactive")) {
							valid = true;
							break;
						} else {
							return "Fail -> The link Last is displayed and Active";
						}
					}
				}
				// Until here tested the links in 1 page
				if (valid) {
					String result = getRowsTable();
					if (result == "Pass") {
						if (values[5].contains("_ID")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.id(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.id(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_xpath")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.xpath(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.xpath(OR
									.getProperty(values[5])));
						} else if (values[5].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, /*
																			 * seconds
																			 * =
																			 */
									60);
							pageNum = wait.until(presenceOfElementLocated(By
									.cssSelector(OR.getProperty(values[5]))));
							pageNums = driver.findElements(By.cssSelector(OR
									.getProperty(values[5])));

						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}

						for (WebElement pageN : pageNums) {
							if (pageN.isDisplayed()) {
								numPage = pageN.getText();
								System.out.println("NumPages: " + numPage);
								Thread.sleep(600);
							}
						}
						if ((rowsTable == rowsTotalTables)
								&& (numPage.equals("1"))) {
							return "Pass";
						} else {
							return "Fail -> The number of Records displayed is not the accurate. Expected = "
									+ rowsTotalTables + "Current=" + rowsTable;
						}
					}
				}
			} else {
				int page = 1;
				// Start Testing of First and Last links
				if (page == 1) {
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								link_Last.click();
								waitCOLTServer();
								page = numPages;
								List<WebElement> buttons_cancel = driver
										.findElements(By.id("cancel"));
								for (WebElement button : buttons_cancel) {
									while (!button.isDisplayed()) {
										if (values[5].contains("_ID")) {
											WebDriverWait wait = new WebDriverWait(
													driver, /* seconds= */60);
											pageNum = wait
													.until(presenceOfElementLocated(By.id(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.id(OR
															.getProperty(values[5])));
										} else if (values[5].contains("_xpath")) {
											WebDriverWait wait = new WebDriverWait(
													driver, /* seconds= */60);
											pageNum = wait
													.until(presenceOfElementLocated(By.xpath(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.xpath(OR
															.getProperty(values[5])));
										} else if (values[5].contains("_css")) {
											WebDriverWait wait = new WebDriverWait(
													driver, /* seconds= */60);
											pageNum = wait
													.until(presenceOfElementLocated(By.cssSelector(OR
															.getProperty(values[5]))));
											pageNums = driver
													.findElements(By.cssSelector(OR
															.getProperty(values[5])));

										} else {
											return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
										}

										for (WebElement pageN : pageNums) {
											if (pageN.isDisplayed()) {
												numPage = pageN.getText();
												Thread.sleep(600);
												break;
											}
										}
										if (Integer.parseInt(numPage) == numPages) {
											valid = true;
											break;
										} else {
											return "Fail -> The number of Page is not accurate. Expected = "
													+ numPages
													+ "Current="
													+ numPage;
										}
									}
								}
								// until here the active
							} else {
								return "Fail -> The link Last is displayed as Inactive";
							}
						}
					}
				}
				if (page == numPages)// Last page
				{
					// Here we valid if all the links are displayed correctly

					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (!link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Inactive";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed as Active";
							}
						}
					}
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (!link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								link_First.click();
								waitCOLTServer();
								page = 1;
								if (values[5].contains("_ID")) {
									WebDriverWait wait = new WebDriverWait(
											driver, /* seconds= */60);
									pageNum = wait
											.until(presenceOfElementLocated(By.id(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By.id(OR
											.getProperty(values[5])));
								} else if (values[5].contains("_xpath")) {
									WebDriverWait wait = new WebDriverWait(
											driver, /* seconds= */60);
									pageNum = wait
											.until(presenceOfElementLocated(By.xpath(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By.xpath(OR
											.getProperty(values[5])));
								} else if (values[5].contains("_css")) {
									WebDriverWait wait = new WebDriverWait(
											driver, /* seconds= */60);
									pageNum = wait
											.until(presenceOfElementLocated(By.cssSelector(OR
													.getProperty(values[5]))));
									pageNums = driver.findElements(By
											.cssSelector(OR
													.getProperty(values[5])));

								} else {
									return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
								}

								for (WebElement pageN : pageNums) {
									if (pageN.isDisplayed()) {
										numPage = pageN.getText();
										Thread.sleep(600);
										break;
									}
								}
								if (numPage.equals("1")) {
									valid = true;
								} else {
									return "Fail -> The number of Page is not accurate. Expected = 1, Current="
											+ numPage;
								}
							} else {
								return "Fail -> The link First is displayed as Inactive";
							}
						}
					}
				}// until here tested Last page links
				if (page == 1)// First page .. To test again the links
				{
					if ((values[1].contains("_ID")) && (values[2].contains("_ID"))
							&& (values[3].contains("_ID"))
							&& (values[4].contains("_ID"))) {
						link_Firsts = driver.findElements(By.id(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.id(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.id(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_xpath"))
							&& (values[2].contains("_xpath"))
							&& (values[3].contains("_xpath"))
							&& (values[4].contains("_xpath"))) {
						link_Firsts = driver.findElements(By.xpath(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.xpath(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.xpath(OR
								.getProperty(values[4])));
					} else if ((values[1].contains("_css"))
							&& (values[2].contains("_css"))
							&& (values[3].contains("_css"))
							&& (values[4].contains("_css"))) {
						link_Firsts = driver.findElements(By.cssSelector(OR
								.getProperty(values[1])));
						link_Previous = driver.findElements(By.cssSelector(OR
								.getProperty(values[2])));
						link_Nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
						link_Lasts = driver.findElements(By.cssSelector(OR
								.getProperty(values[4])));
					} else {
						return "Fail -> One of the Objects is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					
					for (WebElement link_First : link_Firsts) {
						if (link_First.isDisplayed()) {
							if (link_First.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link First is displayed and Active";
							}
						}
					}
					for (WebElement link_Prev : link_Previous) {
						if (link_Prev.isDisplayed()) {
							if (link_Prev.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Previous is displayed as Active";
							}
						}
					}
					for (WebElement link_next : link_Nexts) {
						if (link_next.isDisplayed()) {
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Next is displayed as Active";
							}
						}
					}
					for (WebElement link_Last : link_Lasts) {
						if (link_Last.isDisplayed()) {
							if (!link_Last.getAttribute("class").contains(
									"inactive")) {
								valid = true;
								break;
							} else {
								return "Fail -> The link Last is displayed and Active";
							}
						}
					}
				}
			}
		}

		catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail -> Error executing verify: 'Next' and 'Previous' Links";
		}
	}

	
	public static String verifyDropdownLinksRecords() {
		int page = 1;
		Boolean valid = false;
		try {
			System.out.println("Executing: verifyDropdownLinksRecords "
					+ object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			String[] values = object.split("#");
			WebElement element = null;
			List<WebElement> elements = null;
			int recordsPerPage = 0;

			String data = TestData.getCellData(currentTest, dataColumnName,
					testRepeat);
			if (!data.isEmpty()) {
				recordsPerPage = Integer.parseInt(data);
				Thread.sleep(600);
			} else {
				return "Fail -> The data readed from TestData.xls file is empty";
			}

			while (page <= options) {
				if (values[1].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[1]))));
					elements = driver.findElements(By.id(OR
							.getProperty(values[1])));
				} else if (values[1].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[1]))));
					elements = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
				} else if (values[5].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							60);
					element = wait.until(presenceOfElementLocated(By
							.cssSelector(OR.getProperty(values[1]))));
					elements = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				for (WebElement drop : elements) {
					if (drop.isDisplayed()) {
						drop.click();
						waitCOLTServer();
						Thread.sleep(1500);
						List<WebElement> links = drop.findElements(By
								.tagName("a"));
						for (WebElement link : links) {
							System.out.println(link.getText());
							int linkText = Integer.parseInt(link.getText());
							if (linkText == page) {
								link.click();
								waitCOLTServer();
								Thread.sleep(1500);
								break;
							}
						}
					}
				}
				if (page != numPages) {
					String result = getRowsTable();
					if (result == "Pass") {
						if (emptyTable) {
							valid = true;
						} else {
							if (rowsTable == recordsPerPage) {
								valid = true;
							} else {
								return "Fail -> The Number of Records displayed is not accurate, Expected:"
										+ recordsPerPage
										+ "Current:"
										+ rowsTable;
							}
						}
					}
				} else {
					int modPerPage = rowsTotalTables % recordsPerPage;
					System.out.println(modPerPage);
					if (modPerPage > 0) {
						String result = getRowsTable();
						if (result == "Pass") {
							if (emptyTable) {
								valid = true;
							} else {
								if (rowsTable == modPerPage) {
									valid = true;
								} else {
									return "Fail -> The Number of Records displayed is not accurate, Expected:"
											+ modPerPage
											+ "Current:"
											+ rowsTable;
								}
							}

						} else {
							return "Fail -> Not possible to get the Number of Rows table";
						}
					} else {
						String result = getRowsTable();
						if (result == "Pass") {
							if (emptyTable) {
								valid = true;
							} else {
								if (rowsTable == recordsPerPage) {
									valid = true;
								} else {
									return "Fail -> The Number of Records displayed is not accurate, Expected:"
											+ recordsPerPage
											+ "Current:"
											+ rowsTable;
								}
							}
						} else {
							return "Fail -> Not possible to get the Number of Rows table";
						}
					}
				}
				page++;
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail";
		}
	}

	/*public static String selectUnselectFunctionality() {
		System.out.println("Executing: selectUnselectFunctionality " + object);
		// The parameteres that receive are checkAll, UncheckAll, table, nextlink if applies
		// if the object contains OnePage just verify the page displayed
		// if the object contains list, goes trough the div and spans and not rows
		Boolean inactive = false;
		Boolean valueFound = false;
		String[] values = object.split("#");
		Boolean valid = false;
		WebElement checkAll = null;
		WebElement unCheckAll = null;
		Boolean allChecked = false;
		WebElement next = null;
		List<WebElement> link_nexts = null;
		try {
			// String result = isCheckboxSelected();
			WebElement t = null;
			List<WebElement> tables = null;
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
				checkAll = driver.findElement(By.id(OR.getProperty(values[0])));
			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				checkAll = driver.findElement(By.xpath(OR
						.getProperty(values[0])));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
				checkAll = driver.findElement(By.cssSelector(OR
						.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (values[1].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[1]))));
				unCheckAll = driver
						.findElement(By.id(OR.getProperty(values[1])));

			} else if (values[1].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				unCheckAll = driver.findElement(By.xpath(OR
						.getProperty(values[1])));
			} else if (values[1].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
				unCheckAll = driver.findElement(By.cssSelector(OR
						.getProperty(values[1])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			while (inactive != true) {
				Integer i = 0;
				System.out.println("Class property of the checkAll is :"+ checkAll.getAttribute("class"));
				if (!checkAll.getAttribute("class").contains("checked")) {
					Thread.sleep(200);
					checkAll.click();
					Thread.sleep(600);
					allChecked = true;
				} else {
					unCheckAll.click();
				}
				if (values[2].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.id(OR
							.getProperty(values[2])));
				} else if (values[2].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.xpath(OR
							.getProperty(values[2])));
				} else if (values[2].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.cssSelector(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.cssSelector(OR
							.getProperty(values[2])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				if(!object.contains("OnePage"))
				{
					if (values[3].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[3]))));
						link_nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
					} else if (values[3].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.xpath(OR
								.getProperty(values[3]))));
						link_nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
					} else if (values[3].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[3]))));
						link_nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
				}
				
				for (WebElement table : tables) {
					if (table.isDisplayed()) {
						if(!object.contains("List"))
						{
							List<WebElement> rows = table.findElements(By
									.tagName("tr"));
							// List<WebElement> rowsReverse = Lists.reverse(rows);
							System.out.println(rows.size());
							if (rows.size() > 0) {
								for (WebElement row : rows) {
									i++;
									System.out.println("Row: " + i.toString());
									List<WebElement> cells = row.findElements(By
											.tagName("td"));
									for (WebElement cell : cells) {
										WebElement span = cell.findElement(By
												.tagName("span"));
										if (allChecked) {
											if (span.getAttribute("class")
													.contains("checked")) {
												valid = true;
												break;
											} else {
												return "Fail -> The following row was not checked: "
														+ i;
											}
										} else {
											if (!span.getAttribute("class")
													.contains("checked")) {
												valid = true;
												break;
											} else {
												return "Fail -> The following row was checked: "
														+ i;
											}
										}

									}
									System.out.println(" ");
								}

							} else {
								return "Fail ->There is No Elements in the Table";
							}
						}
						else //working the list
						{
							List<WebElement> devices = table.findElements(By.className("device"));
							if (devices.size() > 0) 
							{
								Integer a=1;
								for (WebElement device : devices)
								{
									
									WebElement checkbox = device.findElements(By.tagName("span")).get(0);
									if (allChecked) {
										if (checkbox.getAttribute("class")
												.contains("checked")) {
											valid = true;
											System.out.println("row = "+a);
											a++;
										} else {
											return "Fail -> The following row was not checked: "
													+ i;
										}
									} else {
										if (!checkbox.getAttribute("class")
												.contains("checked")) {
											valid = true;
											System.out.println("row = "+a);
											a++;
										} else {
											return "Fail -> The following row was checked: "
													+ i;
										}
									}
								}
								inactive = true;
							} else {
								System.out.println("There are No Vehicles in the List");
								return "Fail";
							}
						}
						
						
					}

				}
				if (!object.contains("OnePage"))
				{
					for (WebElement link_next : link_nexts) {
						if (link_next.isDisplayed()) {
							System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									Thread.sleep(3000);
									System.out.println("Next page displayed");
									break;
								} catch (Throwable j) {
									return "Fail -> Not possible to click on next link";
								}
							} else {
								inactive = true;
								break;
							}
						} else {
							inactive = true;
							break;
						}
					}
				}
				
				
				
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail-> Error executing the function";
		}
	}*/
	public static String selectUnselectFunctionality() {
		System.out.println("Executing: selectUnselectFunctionality " + object);
		// The parameteres that receive are checkAll, UncheckAll, table, nextlink if applies
		// if the object contains OnePage just verify the page displayed
		// if the object contains list, goes trough the div and spans and not rows, specially for devices list
		
		Boolean inactive = false;
		Boolean valueFound = false;
		String[] values = object.split("#");
		Boolean valid = false;
		WebElement checkAll = null;
		WebElement unCheckAll = null;
		Boolean allChecked = false;
		WebElement next = null;
		List<WebElement> link_nexts = null;
		try {
			// String result = isCheckboxSelected();
			WebElement t = null;
			List<WebElement> tables = null;
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
				checkAll = driver.findElement(By.id(OR.getProperty(values[0])));
			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				checkAll = driver.findElement(By.xpath(OR
						.getProperty(values[0])));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				checkAll = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
				checkAll = driver.findElement(By.cssSelector(OR
						.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			if (values[1].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[1]))));
				unCheckAll = driver
						.findElement(By.id(OR.getProperty(values[1])));

			} else if (values[1].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				unCheckAll = driver.findElement(By.xpath(OR
						.getProperty(values[1])));
			} else if (values[1].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				unCheckAll = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
				unCheckAll = driver.findElement(By.cssSelector(OR
						.getProperty(values[1])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			while (inactive != true) {
				Integer i = 0;
				System.out.println("Class property of the checkAll is :"+ checkAll.getAttribute("class"));
				if (!checkAll.getAttribute("class").contains("checked")) {
					Thread.sleep(2000);
					checkAll.click();
					Thread.sleep(6000);
					allChecked = true;
				} else {
					unCheckAll.click();
				}
				if (values[2].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.id(OR
							.getProperty(values[2])));
				} else if (values[2].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.xpath(OR
							.getProperty(values[2])));
				} else if (values[2].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.cssSelector(OR
							.getProperty(values[2]))));
					tables = driver.findElements(By.cssSelector(OR
							.getProperty(values[2])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				if(!object.contains("OnePage"))
				{
					if (values[3].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[3]))));
						link_nexts = driver.findElements(By.id(OR
								.getProperty(values[3])));
					} else if (values[3].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.xpath(OR
								.getProperty(values[3]))));
						link_nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
					} else if (values[3].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[3]))));
						link_nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
				}
				
				for (WebElement table : tables) {
					if (table.isDisplayed()) {
						if(!object.contains("List"))
						{
							List<WebElement> rows = table.findElements(By
									.tagName("tr"));
							// List<WebElement> rowsReverse = Lists.reverse(rows);
							System.out.println(rows.size());
							if (rows.size() > 0) {
								for (WebElement row : rows) {
									i++;
									System.out.println("Row: " + i.toString());
									List<WebElement> cells = row.findElements(By
											.tagName("td"));
									for (WebElement cell : cells) {
										WebElement span = cell.findElement(By
												.tagName("span"));
										if (allChecked) {
											if (span.getAttribute("class")
													.contains("checked")) {
												valid = true;
												break;
											} else {
												return "Fail -> The following row was not checked: "
														+ i;
											}
										} else {
											if (!span.getAttribute("class")
													.contains("checked")) {
												valid = true;
												break;
											} else {
												return "Fail -> The following row was checked: "
														+ i;
											}
										}

									}
									System.out.println(" ");
								}

							} else {
								return "Fail ->There is No Elements in the Table";
							}
						}
						else //working the list
						{
							List<WebElement> devices = table.findElements(By.className("device"));
							if (devices.size() > 0) 
							{
								Integer a=1;
								for (WebElement device : devices)
								{
									
									WebElement checkbox = device.findElements(By.tagName("span")).get(0);
									if (allChecked) {
										if (checkbox.getAttribute("class")
												.contains("checked")) {
											valid = true;
											System.out.println("row = "+a);
											a++;
										} else {
											return "Fail -> The following row was not checked: "
													+ i;
										}
									} else {
										if (!checkbox.getAttribute("class")
												.contains("checked")) {
											valid = true;
											System.out.println("row = "+a);
											a++;
										} else {
											return "Fail -> The following row was checked: "
													+ i;
										}
									}
								}
								inactive = true;
							} else {
								System.out.println("There are No Vehicles in the List");
								return "Pass";
							}
						}
						
						
					}

				}
				if (!object.contains("OnePage"))
				{
					for (WebElement link_next : link_nexts) {
						if (link_next.isDisplayed()) {
							System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									waitCOLTServer();
									Thread.sleep(1500);
									System.out.println("Next page displayed");
									break;
								} catch (Throwable j) {
									return "Fail -> Not possible to click on next link";
								}
							} else {
								inactive = true;
								break;
							}
						} else {
							inactive = true;
							break;
						}
					}
				}
				
				
				
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail-> Error executing the function";
		}
	}
	
	
	public static String unselectAllAndSelect() {
		System.out.println("Executing: unselectAll " + object);
		// The parameteres that receive are checkAll, UncheckAll, table, nextlink if applies
		// if the object contains OnePage just verify the page displayed
		// if the object contains list, goes trough the div and spans and not rows, specially for devices list
		
		Boolean inactive = false;
		Boolean valueFound = false;
		String[] values = object.split("#");
		Boolean valid = false;
		WebElement checkAll = null;
		WebElement uncheckAll = null;
		Boolean allChecked = true;
		WebElement next = null;
		List<WebElement> link_nexts = null;
		try {
			// String result = isCheckboxSelected();
			WebElement t = null;
			List<WebElement> tables = null;
			List<WebElement> uncheckAlls = null;
			if (values[0].contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				uncheckAll = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(values[0]))));
				uncheckAlls = driver.findElements(By.id(OR.getProperty(values[0])));
			} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				uncheckAll = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(values[0]))));
				uncheckAlls = driver.findElements(By.xpath(OR
						.getProperty(values[0])));
			} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				uncheckAll = wait.until(presenceOfElementLocated(By
						.cssSelector(OR.getProperty(values[0]))));
				uncheckAlls = driver.findElements(By.cssSelector(OR
						.getProperty(values[0])));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			
			while (inactive != true) {
				Integer i = 0;
				for(WebElement uncheck : uncheckAlls )
				{
					if(uncheck.isDisplayed())
					{
						System.out.println("Class property of the checkAll is :"+ uncheckAll.getAttribute("class"));
						if (!uncheckAll.getAttribute("class").contains("checked")) {
							Thread.sleep(600);
							uncheckAll.click();
							Thread.sleep(600);
							if(object.contains("Uncheck"))
							{
								allChecked = false;
							}
							
						} else {
							uncheckAll.click();
						}
					}
				}
				
				if (values[1].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.id(OR
							.getProperty(values[1]))));
					tables = driver.findElements(By.id(OR
							.getProperty(values[1])));
				} else if (values[1].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.xpath(OR
							.getProperty(values[1]))));
					tables = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
				} else if (values[1].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					t = wait.until(presenceOfElementLocated(By.cssSelector(OR
							.getProperty(values[1]))));
					tables = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				if(!object.contains("OnePage"))
				{
					if (values[2].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[2]))));
						link_nexts = driver.findElements(By.id(OR
								.getProperty(values[2])));
					} else if (values[2].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By.xpath(OR
								.getProperty(values[2]))));
						link_nexts = driver.findElements(By.xpath(OR
								.getProperty(values[3])));
					} else if (values[2].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, 60);
						next = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[2]))));
						link_nexts = driver.findElements(By.cssSelector(OR
								.getProperty(values[3])));
					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
				}
				
				for (WebElement table : tables) {
					if (table.isDisplayed()) {
						if(!object.contains("List"))
						{
							List<WebElement> rows = table.findElements(By
									.tagName("tr"));
							// List<WebElement> rowsReverse = Lists.reverse(rows);
							System.out.println(rows.size());
							if (rows.size() > 0) {
								for (WebElement row : rows) {
									i++;
									System.out.println("Row: " + i.toString());
									List<WebElement> cells = row.findElements(By
											.tagName("td"));
									for (WebElement cell : cells) {
										
											WebElement span = cell.findElement(By
													.tagName("span"));
											if (allChecked) {
												if (span.getAttribute("class")
														.contains("checked")) {
													valid = true;
													break;
												} else {
													return "Fail -> The following row was not checked: "
															+ i;
												}
											} else {
												if (!span.getAttribute("class")
														.contains("checked")) {
													valid = true;
													break;
												} else {
													return "Fail -> The following row was checked: "
															+ i;
												}
											}
										
										
										

									}
									System.out.println(" ");
								}

							} else {
								return "Fail ->There is No Elements in the Table";
							}
						}
						else //working the list
						{
							List<WebElement> devices = table.findElements(By.className("device"));
							if (devices.size() > 0) 
							{
								Integer a=1;
								for (WebElement device : devices)
								{
									String style = device.getAttribute("style");
									if(!style.contains("none")){
										WebElement checkbox = device.findElements(By.tagName("span")).get(0);
										if (allChecked) {
											if (checkbox.getAttribute("class")
													.contains("checked")) {
												valid = true;
												System.out.println("row = "+a);
												a++;
											} else {
												return "Fail -> The following row was not checked: "
														+ i;
											}
										} else {
											if (!checkbox.getAttribute("class")
													.contains("checked")) {
												valid = true;
												System.out.println("row = "+a);
												a++;
											} else {
												return "Fail -> The following row was checked: "
														+ i;
											}
										}
									}
									
									
								}
								inactive = true;
							} else {
								System.out.println("There are No Vehicles in the List");
								return "Pass";
							}
						}
						
						
					}

				}
				if (!object.contains("OnePage"))
				{
					for (WebElement link_next : link_nexts) {
						if (link_next.isDisplayed()) {
							System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									waitCOLTServer();
									Thread.sleep(1500);
									System.out.println("Next page displayed");
									break;
								} catch (Throwable j) {
									return "Fail -> Not possible to click on next link";
								}
							} else {
								inactive = true;
								break;
							}
						} else {
							inactive = true;
							break;
						}
					}
				}
				
				
				
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}
		if (valid) {
			return "Pass";
		} else {
			return "Fail-> Error executing the function";
		}
	}
	
	
	
	
	
	public static String verifyElementDisplayed() {
		System.out.println("Executing: verifyElementDisplayed " + object);
		WebElement element = null;
		List<WebElement> elements = null;
		try {
			if (object.contains("_ID")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.id(OR
						.getProperty(object))));
				elements = driver.findElements(By.id(OR.getProperty(object)));
			} else if (object.contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR
						.getProperty(object))));
				elements = driver
						.findElements(By.xpath(OR.getProperty(object)));
			} else if (object.contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR
						.getProperty(object))));
				elements = driver.findElements(By.cssSelector(OR
						.getProperty(object)));
			} else {
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			for (WebElement e : elements) {
				if (e.isDisplayed()) {
					return "Pass";
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error executing verifyElementDisplayed" + t;
		}
		return "Fail -> The element was not find or Error executing the method";
	}
	
	public static String verifyElementNotDisplayed()
	{
		System.out.println("Executing: verifyElementNotDisplayed " + object);
		String result = verifyElementDisplayed();
		if(result.contains("Fail"))
		{
			return "Pass";
		}
		else
		{
			return "Fail -> The element is displayed";
		}
	}
	public static String selectPastDate()
	{
		System.out.println("Executing: selectPastDate " + object);
		try 
		{
			String[] objects = object.split("#");
			WebElement element = null;
			WebElement prev = null;
			int pastDays = Integer.parseInt(TestData.getCellData(currentTest, dataColumnName, testRepeat));
			Date currentDate = Calendar.getInstance().getTime();
			int today = currentDate.getDate();
			long diffDays = today - pastDays;
			System.out.println(diffDays);
			if (objects[0].contains("_ID")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[0]))));
			}
			else if (objects[0].contains("_xpath")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[0]))));
			} 
			else if (objects[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[0]))));
			}
			else
			{
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.click();
			Thread.sleep(600);
			while (diffDays < 0) 
			{
				System.out.println(diffDays);
				if (objects[1].contains("_ID")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					prev = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[1]))));
				} 
				else if (objects[1].contains("_xpath")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, 60);
					prev = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[1]))));
				} 
				else if (objects[1].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 60);
					prev = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[1]))));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				prev.click();
				Thread.sleep(600);
				try
				{
					if (driver.findElement(By.xpath("//a[text()='31']")).isDisplayed()) {
						diffDays = diffDays + 31;
						break;
					}
				} catch (Throwable T) {
				}
				try {
					if (driver.findElement(By.xpath("//a[text()='30']"))
							.isDisplayed()) {
						diffDays = diffDays + 30;
						break;
					}
				} catch (Throwable T) {
				}
				try {
					if (driver.findElement(By.xpath("//a[text()='29']"))
							.isDisplayed()) {
						diffDays = diffDays + 29;
						break;
					}
				} catch (Throwable T) {
				}
				try {
					if (driver.findElement(By.xpath("//a[text()='28']"))
							.isDisplayed()) {
						diffDays = diffDays + 28;
						break;
					}
				} catch (Throwable T) {
				}
			}// while
			System.out.println(diffDays);
			if (diffDays == 0)
			{
				diffDays = 1;
			}
			List<WebElement> dayEquals = driver.findElements(By.xpath("//a[text()='" + diffDays + "']"));
			for (WebElement pastDay : dayEquals) {
				if (pastDay.isDisplayed())
				{
					pastDay.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} 
		catch (Throwable t) 
		{
			return "Fail -> error executing selectPastDate" + t;
		}
		return "Fail";
	}

	public static String selectFutureDate()
	{
		System.out.println("Executing: selectFutureDate " + object);
		try 
		{
			String[] objects = object.split("#");
			WebElement element = null;
			WebElement next = null;
			Date currentDate = null;
			int today = 0;
			long diffDays = 0;
			int days = Integer.parseInt(TestData.getCellData(currentTest, dataColumnName, testRepeat));
			currentDate = Calendar.getInstance().getTime();
			
			today = currentDate.getDate();
			System.out.println("Today's date " + today);
			System.out.println("# of Days to add: " + days);
			if (object.contains("LastDays"))
			{
				if(today == 31)
				{
					diffDays = 31;
				}
				else if (today == 30)
				{
					diffDays = 30;
				}
				else
				{
					diffDays = 29;
				}
			}
			else
			{
				diffDays = today + days;
			}
							
			System.out.println("Difference of Days: "+diffDays);
			if (objects[0].contains("_ID")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[0]))));
			}
			else if (objects[0].contains("_xpath")) 
			{
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[0]))));
			} 
			else if (objects[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				element = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[0]))));
			}
			else
			{
				return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
			}
			element.click();
			Thread.sleep(600);
			
			while(diffDays>31)
			 {
				if (objects[1].contains("_ID")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, 20);
					next = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[1]))));
				} 
				else if (objects[1].contains("_xpath")) 
				{
					WebDriverWait wait = new WebDriverWait(driver, 20);
					next = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[1]))));
				} 
				else if (objects[1].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					next = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[1]))));
				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				next.click();
				diffDays = diffDays-31;
			 }
			if (diffDays>30)
			{
				try
				{
					if(driver.findElement(By.xpath("//a[text()='30']")).isDisplayed())
					  {
						if (objects[1].contains("_ID")) 
						{
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[1]))));
						} 
						else if (objects[1].contains("_xpath")) 
						{
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[1]))));
						} 
						else if (objects[1].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[1]))));
						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						next.click();
						diffDays = diffDays-30;
						
					  }
				}
				catch(Throwable t)
				{}
			}
			else if(diffDays>28)//
			{
				try
				{
					if(driver.findElement(By.xpath("//a[text()='31']")).isDisplayed())
					  {
						List<WebElement> dayEquals = driver.findElements(By.xpath("//a[text()='" + diffDays + "']"));
						for (WebElement pastDay : dayEquals) {
							if (pastDay.isDisplayed())
							{
								pastDay.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
					  }
				}
				catch(Throwable t)
				{}
				try
				{
					if(driver.findElement(By.xpath("//a[text()='30']")).isDisplayed())
					  {
						List<WebElement> dayEquals = driver.findElements(By.xpath("//a[text()='" + diffDays + "']"));
						for (WebElement pastDay : dayEquals) {
							if (pastDay.isDisplayed())
							{
								pastDay.click();
								Thread.sleep(600);
								return "Pass";
							}
						}
					  }
					
				}
				catch(Throwable t)
				{
					
				}
				try
				{
					if(driver.findElement(By.xpath("//a[text()='28']")).isDisplayed())
					  {
						if (objects[1].contains("_ID")) 
						{
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.id(OR.getProperty(objects[1]))));
						} 
						else if (objects[1].contains("_xpath")) 
						{
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(objects[1]))));
						} 
						else if (objects[1].contains("_css")) {
							WebDriverWait wait = new WebDriverWait(driver, 20);
							next = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(objects[1]))));
						} else {
							return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						next.click();
						diffDays = diffDays-28;
					}
					
				}
				catch(Throwable t)
				{
					
				}
			}
									 
			List<WebElement> dayEquals = driver.findElements(By.xpath("//a[text()='" + diffDays + "']"));
			for (WebElement pastDay : dayEquals) {
				if (pastDay.isDisplayed())
				{
					pastDay.click();
					Thread.sleep(600);
					return "Pass";
				}
			}
		} 
		catch (Throwable t) 
		{
			return "Fail -> error executing selectFutureDate" + t;
		}
		return "Fail";
	}
	
	
	public static String verifyRangeOfDatesElements() {
		System.out.println("Executing: verifyRangeOfDatesElements" + object);
		String[] values = object.split("#");
		WebElement element = null;
		Boolean inactive = false;
		List<WebElement> elements = null;
		List<WebElement> link_nexts = null;
		Boolean link_next_displayed = false;
		Boolean valid = false;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		////MV gettting current date
			/*Long current = Calendar.getInstance().getTimeInMillis();
			System.out.println(current);
			String cu1 = dateFormat.format(current);
			System.out.println(cu1);
			Date curr = dateFormat.parse(cu1);
			System.out.println(curr);
			Long curr1 = curr.getTime();
			System.out.println(curr1);*/
			
			//////////////////////
			
			Date firstDate = dateFormat.parse(element_Name);
			long startDate = firstDate.getTime();
					
			// System.out.println(startDate);
			System.out.println("Start Date: " + dateFormat.format(startDate)
					+ " In Milliseconds= " + startDate);
			
			long endDate = Calendar.getInstance().getTimeInMillis();
			System.out.println("End Date: " + dateFormat.format(endDate)
					+ " In Milliseconds=" + endDate);
			while (inactive != true) {
				Integer i = 0;
				try {
					driver.manage().timeouts()
							.implicitlyWait(20L, TimeUnit.SECONDS);
					if (values[0].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[0]))));
						elements = driver.findElements(By.id(OR
								.getProperty(values[0])));
					} else if (values[0].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By
								.xpath(OR.getProperty(values[0]))));
						elements = driver.findElements(By.xpath(OR
								.getProperty(values[0])));
					} else if (values[0].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[0]))));
						elements = driver.findElements(By.cssSelector(OR
								.getProperty(values[0])));
					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					for (WebElement table : elements) {
						if (table.isDisplayed()) {
							List<WebElement> rows = table.findElements(By
									.tagName("tr"));
							// List<WebElement> rowsReverse =
							// Lists.reverse(rows);
							System.out.println(rows.size());
							if (rows.size() > 0) {
								for (WebElement row : rows) {
									i++;
									System.out.println("Row: " + i.toString());
									List<WebElement> cells = row
											.findElements(By.tagName("td"));
									for (WebElement cell : cells) {
										System.out.print(cell.getText()
												+ " -> ");
										Date compareDate = dateFormat
												.parse(cell.getText());
										long compareDateMill = compareDate
												.getTime();
										System.out.println(compareDateMill);
																		 									
								if (compareDateMill >= startDate
												&& compareDateMill <= endDate) {
											valid = true;
											break;
										} else {
											return "Fail -> The date: "
													+ cell.getText()
													+ "is not in the range of StartDate:"
													+ dateFormat
															.format(startDate)
													+ "and End Date: "
													+ dateFormat
															.format(endDate);
										}
										
										
										
								  }
								}
							} else {
								return "Fail ->There is No Elements in the Table";
							}
						}
					}

				} catch (Throwable t) {
					System.out.println("Error in clicking the Action Link ");
					return "Fail " + t.getMessage();
				}
				if(!object.contains("NoNext"))
				{
					try {
						
						if (values[1].contains("_ID")) {
							link_nexts = driver.findElements(By.id(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_xpath")) {
							link_nexts = driver.findElements(By.xpath(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_css")) {
							link_nexts = driver.findElements(By.cssSelector(OR
									.getProperty(values[1])));
						} else {
							return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						link_next_displayed = true;
					} catch (Throwable t) {
					}
				}
				
				if (link_next_displayed) {
					for (WebElement link_next : link_nexts)

					{
						if (link_next.isDisplayed()) {
							System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									Thread.sleep(3000);
									System.out.println("Next page displayed");
									break;
								} catch (Throwable t) {
									return "Fail -> Not possible to click on next link";
								}
							} else {
								inactive = true;
								return "Pass";
							}
						}
					}
				} else {
					inactive = true;
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}

		if (valid) {
			return "Pass";
		} else {
			return "Fail - Error executing the function";
		}
	}
//****
	
	
	public static String verifyCurrentDatesElements() {
		System.out.println("Executing: verifyRangeOfDatesElements" + object);
		String[] values = object.split("#");
		WebElement element = null;
		Boolean inactive = false;
		List<WebElement> elements = null;
		List<WebElement> link_nexts = null;
		Boolean link_next_displayed = false;
		Boolean valid = false;
		try {
			//SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			
			Date current = Calendar.getInstance().getTime();
			Date currentDate= dateFormat.parse(dateFormat.format(current));
			
			
			long endDate=currentDate.getTime();
		
			
			
			//long endDate= Calendar.getInstance().getTimeInMillis();
			
								
			System.out.println("End Date: " + dateFormat.format(endDate)
					+ " In Milliseconds=" + endDate);
			while (inactive != true) {
				Integer i = 0;
				try {
					driver.manage().timeouts()
							.implicitlyWait(20L, TimeUnit.SECONDS);
					if (values[0].contains("_ID")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By.id(OR
								.getProperty(values[0]))));
						elements = driver.findElements(By.id(OR
								.getProperty(values[0])));
					} else if (values[0].contains("_xpath")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By
								.xpath(OR.getProperty(values[0]))));
						elements = driver.findElements(By.xpath(OR
								.getProperty(values[0])));
					} else if (values[0].contains("_css")) {
						WebDriverWait wait = new WebDriverWait(driver, /*
																		 * seconds=
																		 */60);
						element = wait.until(presenceOfElementLocated(By
								.cssSelector(OR.getProperty(values[0]))));
						elements = driver.findElements(By.cssSelector(OR
								.getProperty(values[0])));
					} else {
						return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}

					for (WebElement table : elements) {
						if (table.isDisplayed()) {
							List<WebElement> rows = table.findElements(By
									.tagName("tr"));
							// List<WebElement> rowsReverse =
							// Lists.reverse(rows);
							System.out.println(rows.size());
							if (rows.size() > 0) {
								for (WebElement row : rows) {
									i++;
									System.out.println("Row: " + i.toString());
									List<WebElement> cells = row
											.findElements(By.tagName("td"));
									for (WebElement cell : cells) {
										System.out.print(cell.getText()
												+ " -> ");
										Date compareDate = dateFormat.parse(cell.getText());
												
											//	parse(cell.getText());
										/*Date compareDate = dateFormat2
												.parse(cells.get(Integer.parseInt(values[3])).getText());
										// firstvalue = cells.get(Integer.parseInt(values[3])).getText();*/
										
										long compareDateMill = compareDate.getTime();
										System.out.println(compareDateMill);
										
									 if(object.contains("#NotEqCurrentD")){
											if (compareDateMill != endDate) {
												valid = true;
												break;
											} else {
												return "Fail -> The date: "
														+ cell.getText()
														+ "is equal to:"
														+ dateFormat.format(endDate);
											}
										}	
									 else if(object.contains("#EqCurrentD")){
											if (compareDateMill == endDate) {
												valid = true;
												break;
											} else {
												return "Fail -> The date: "
														+ cell.getText()
														+ "is not equal to:"
														+ dateFormat.format(endDate);
											}
										}	
									 else if(object.contains("#MinCurrentD")){
										if (compareDateMill < endDate) {
											valid = true;
											break;
										} else {
											return "Fail -> The date: "
													+ cell.getText()
													+ "is not minor to:"
													+ dateFormat.format(endDate);
										}
									}	
								 else if(object.contains("#MinEqCurrentD")){
											if (compareDateMill <= endDate) {
												valid = true;
												break;
											} else {
												return "Fail -> The date: "
														+ cell.getText()
														+ "is not minor or equal to:"
														+ dateFormat.format(endDate);
											}
									 }
									
								 else if(object.contains("#MajCurrentD")){
										if (compareDateMill > endDate) {
											valid = true;
											break;
										} else {
											return "Fail -> The date: "
													+ cell.getText()
													+ "is not major to:"
													
													+ dateFormat.format(endDate);
										}
								    }
								
								 
								 else if(object.contains("#MajEqCurrentD")){
										if (compareDateMill >= endDate) {
											valid = true;
											break;
										} else {
											return "Fail -> The date: "
													+ cell.getText()
													+ "is not Major or equal to:"
													+ dateFormat.format(endDate);
										}
								    }
									
																		
								  }
								}
							} else {
								return "Fail ->There is No Elements in the Table";
							}
						}
					}

				} catch (Throwable t) {
					System.out.println("Error in clicking the Action Link ");
					return "Fail " + t.getMessage();
				}
				if(!object.contains("NoNext"))
				{
					try {
						
						if (values[1].contains("_ID")) {
							link_nexts = driver.findElements(By.id(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_xpath")) {
							link_nexts = driver.findElements(By.xpath(OR
									.getProperty(values[1])));
						} else if (values[1].contains("_css")) {
							link_nexts = driver.findElements(By.cssSelector(OR
									.getProperty(values[1])));
						} else {
							return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
						}
						link_next_displayed = true;
					} catch (Throwable t) {
					}
				}
				
				if (link_next_displayed) {
					for (WebElement link_next : link_nexts)

					{
						if (link_next.isDisplayed()) {
							System.out.println(link_next.getAttribute("class"));
							if (!link_next.getAttribute("class").contains(
									"inactive")) {
								try {
									link_next.click();
									Thread.sleep(3000);
									System.out.println("Next page displayed");
									break;
								} catch (Throwable t) {
									return "Fail -> Not possible to click on next link";
								}
							} else {
								inactive = true;
								return "Pass";
							}
						}
					}
				} else {
					inactive = true;
				}
			}
		} catch (Throwable t) {
			return "Fail -> Error executing" + t;
		}

		if (valid) {
			return "Pass";
		} else {
			return "Fail - Error executing the function";
		}
	}
	
	public static String checkSearchedAction() {
		try {
			System.out.println("Executing checkSearchedActionID " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebElement list= null;
			String valueCompare=null;
			Boolean inactive = false;
			List<WebElement> link_nexts=null;
			Boolean next_displayed= false;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			/*else
			{
				datainfo= new String[2];
				datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
				datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			}*/
			
			if(!datainfo[0].isEmpty())
			{
				valueCompare = datainfo[0];
			}
			if(object.contains("_elementName"))
			{
				valueCompare=element_Name;
			}
			while(!inactive)
			{
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					list = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				List<WebElement> devices = list.findElements(By.className("wr"));
				devices = Lists.reverse(devices);
				if (devices.size() > 0) 
				{
					for (WebElement device : devices)
					{
						WebElement checkbox = device.findElements(By.tagName("span")).get(0);
						List<WebElement> divs = device.findElements(By.tagName("label"));
						for (WebElement div : divs) 
						{
							System.out.println(div.getText()+ "--");
							
							if (div.getText().equals(valueCompare))
							{
								checkbox.click();
								if(object.contains("checkClass"))
								{
									String classvalue=div.getAttribute("class");
									System.out.println(div.getAttribute("class"));
									if(classvalue.contains(datainfo[1]))
									{
										return "Pass";
									}
								}
								else
								{
									return "Pass";
								}
								
							}
						}
					}
				} else {
					System.out.println("There are No Vehicles in the List");
					return "Fail";
				}
				try 
				{
					
					if (values[1].contains("_ID"))
					{
					link_nexts = driver.findElements(By.id(OR.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
					link_nexts = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
					link_nexts = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
					} else {
					return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					next_displayed= true;
				
				
				} catch (Throwable t) {	}	
				if(next_displayed)
				{
					for(WebElement link_next:link_nexts)
					{
						if(link_next.isDisplayed())
						{
						try {
							link_next.click();
							System.out.println("Going to next page");
							waitCOLTServer();
							break;
						} catch (Throwable t) {
							return "Fail -> Not possible to click on next link";
						}
						}
					}
					inactive=true;
				}
				else
				{
					inactive=true;
				}
			}
			
		} catch (Throwable t) {
			System.out.println("Error checking the Vehicle " + t);
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}
	
	
	
	public static String checkRandomSearchedAction() {
		try {
			System.out.println("Executing checkRandomSearchedActionID " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebElement list= null;
			String valueCompare=null;
			Boolean inactive = false;
			List<WebElement> link_nexts=null;
			Boolean next_displayed= false;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			/*else
			{
				datainfo= new String[2];
				datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
				datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			}*/
			
			if(!datainfo[0].isEmpty())
			{
				valueCompare = datainfo[0];
			}
			else
			{
				valueCompare = AlphaName;
			}
			if(object.contains("_elementName"))
			{
				valueCompare=element_Name;
			}
			while(!inactive)
			{
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					list = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
			List<WebElement> devices = list.findElements(By.className("wr"));
				devices = Lists.reverse(devices);
				if (devices.size() > 0) 
				{
					for (WebElement device : devices)
					{
						WebElement checkbox = device.findElements(By.tagName("span")).get(0);
						List<WebElement> divs = device.findElements(By.tagName("label"));
						for (WebElement div : divs) 
						{
							System.out.println(div.getText()+ "--");
							
							if (div.getText().equals(valueCompare))
							{
								checkbox.click();
								if(object.contains("checkClass"))
								{
									String classvalue=div.getAttribute("class");
									System.out.println(div.getAttribute("class"));
									if(classvalue.contains(datainfo[1]))
									{
										return "Pass";
									}
								}
								else
								{
									return "Pass";
								}
								
							}
						}
					}
				} else {
					System.out.println("There are No Vehicles in the List");
					return "Fail";
				}
				try 
				{
					
					if (values[1].contains("_ID"))
					{
					link_nexts = driver.findElements(By.id(OR.getProperty(values[1])));
					} else if (values[1].contains("_xpath")) {
					link_nexts = driver.findElements(By.xpath(OR
							.getProperty(values[1])));
					} else if (values[1].contains("_css")) {
					link_nexts = driver.findElements(By.cssSelector(OR
							.getProperty(values[1])));
					} else {
					return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
					}
					next_displayed= true;
				
				
				} catch (Throwable t) {	}	
				if(next_displayed)
				{
					for(WebElement link_next:link_nexts)
					{
						if(link_next.isDisplayed())
						{
						try {
							link_next.click();
							System.out.println("Going to next page");
							waitCOLTServer();
							break;
						} catch (Throwable t) {
							return "Fail -> Not possible to click on next link";
						}
						}
					}
					inactive=true;
				}
				else
				{
					inactive=true;
				}
			}
			
		} catch (Throwable t) {
			System.out.println("Error checking the Vehicle " + t);
			return "Fail ->" + t.getMessage();
		}
		return "Pass";
	}
	
   public static String VerifyRandomSearchedList() {
		try {
			System.out.println("Executing VerifyRandomSearchedListID " + object);
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS); 
			WebElement list= null;
			String valueCompare=null;
			Boolean inactive = false;
			List<WebElement> link_nexts=null;
			WebElement next = null;
			Boolean next_displayed= false;
			String[] values = object.split("#");
			if (values.length < 2) {
				values = new String[2];
				values[0] = object;
				values[1] = object;
			}
			String[] datainfo = dataColumnName.split("#");
			
			if(datainfo.length <2)
			{
				datainfo= new String[2];
				datainfo[0]=dataColumnName;
				datainfo[1]=dataColumnName;
			}
			datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
			datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			/*else
			{
				datainfo= new String[2];
				datainfo[0] =TestData.getCellData(currentTest, datainfo[0],testRepeat);
				datainfo[1] =TestData.getCellData(currentTest, datainfo[1],testRepeat);
			}*/
			
			if(!datainfo[0].isEmpty())
			{
				valueCompare = datainfo[0];
			}
			else
			{
				valueCompare = AlphaName;
			}
			if(object.contains("_elementName"))
			{
				valueCompare=element_Name;
			}
			System.out.println("The value to compare is: " + valueCompare);
			while(!inactive)
			{
				if (values[0].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					list = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));

				} else if (values[0].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[0]))));

				} else if (values[0].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					list = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[0]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
				
				///****///
				if (values[1].contains("_ID")) {
					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
							20);
					next = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[1]))));

				} else if (values[1].contains("_xpath")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					next = wait.until(presenceOfElementLocated(By.xpath(OR.getProperty(values[1]))));

				} else if (values[1].contains("_css")) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
					next = wait.until(presenceOfElementLocated(By.cssSelector(OR.getProperty(values[1]))));

				} else {
					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
				}
			List<WebElement> devices = list.findElements(By.tagName("li"));
				Integer c =0;
				if (devices.size() > 0) 
				{
						while(c<devices.size())	
						{
							next.click();
							for (WebElement div : devices) 
							{
								if(div.isDisplayed())
								{
									System.out.println(div.getText());
									if (div.getText().contains(valueCompare))
									{
										
										return "Pass";
											
									}
									//System.out.println(div.getAttribute("value"));
								}
								
								
							}
						}
						
							
							
							
							/*System.out.println(div.getAttribute("value"));
							//System.out.println(div.getText(), "--");//////******
							
							if (div.getText().equals(valueCompare))
							{
								
								return "Pass";
									
							}
								else
								{
									return "Fail";
								}
								
							}*/
						//}
					}
				 
					
			}	
			
		} catch (Throwable t) {
			System.out.println("Error checking the Vehicle " + t);
			return "Fail ->" + t.getMessage();
		}
		return "Fail";
	}
	
   /* 
   The object should be contain '#alpha' for value alphanumeric in the table (values to sort) other case will be for the numeric values
   */
      
      public static String verify_ASC_DSC_SortingInv()
   	{
   		System.out.println("Executing: verify_ASC_DSC_SortingInv " + object);
   		String[] values = object.split("#");
   		if (values.length < 2)
   		{
   			values = new String[2];
   			values[0] = object;
   			values[1] = object;
   		}
   		Boolean inactive = false;
   		Boolean valueFound = false;
   		Boolean link_next_displayed = false;
   		String ValuetoCompare = null;
   		WebElement element = null;
   		Integer compare = null;
   		Boolean result = false;
   		List<WebElement> elements = null;
   		List<WebElement> link_nexts = null;
   		String firstvalue = null;
   		String nextRowValue = null;
   		String order = "ASC";
   		Date value, secondvalue;
   		while (inactive != true) 
   		{
   			Integer i = 0;
   			try {
   				driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
   				if (values[0].contains("_ID")) {
   					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */20);
   					element = wait.until(presenceOfElementLocated(By.id(OR.getProperty(values[0]))));
   					elements = driver.findElements(By.id(OR.getProperty(values[0])));
   				} 
   				else if (values[0].contains("_xpath")) 
   				{
   					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
   							20);
   					element = wait.until(presenceOfElementLocated(By.xpath(OR
   							.getProperty(values[0]))));
   					elements = driver.findElements(By.xpath(OR
   							.getProperty(values[0])));
   				} else if (values[0].contains("_css")) {
   					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
   							20);
   					element = wait.until(presenceOfElementLocated(By
   							.cssSelector(OR.getProperty(values[0]))));
   					elements = driver.findElements(By.cssSelector(OR
   							.getProperty(values[0])));
   				} else {
   					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
   				}

   				for (WebElement table : elements)
   				{
   					if (table.isDisplayed()) 
   					{
   						List<WebElement> rows = table.findElements(By.tagName("tr"));
   						System.out.println(rows.size());
   						if (rows.size() > 0) 
   						{
   							Thread.sleep(200);
   					
   					///
   							if (object.contains("#alpha"))
   							{
   					//
   								
   							for (WebElement row : rows) 
   							{
   								if (rows.size() == i+1)
   								{
   									break;
   								}
   								System.out.println("Row: " + i.toString());
   								//WebElement cell = row.findElement(By.tagName("td"));
   								List<WebElement> cells = row.findElements(By.tagName("td"));
   								if(!values[3].isEmpty())
   								{
   									firstvalue = cells.get(Integer.parseInt(values[3])).getText();
   									System.out.println("The first value displayed is: " + firstvalue);
   									nextRowValue = rows.get(i+1).findElements(By.tagName("td")).get(Integer.parseInt(values[3])).getText();
   									System.out.println("The second value displayed is: " + nextRowValue);
   									value = null;
   									secondvalue = null;

   									Boolean isDate = false;
   									DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
   									try
   									{
   										value = df.parse(firstvalue);
   										isDate =true;
   										System.out.println("The first date value is: " + value);
   									}
   									catch(Throwable t){ }
   									
   									try {
   										secondvalue = df.parse(nextRowValue);
   										isDate =true;
   										System.out.println("The second date value is: " + secondvalue);
   									}
   									catch(Throwable t){	}									
   									if (isDate)
   									{
   										if (firstvalue.contains("N/A"))
   										{
   											value = df.parse("1/1/1900");
   										}
   										if (nextRowValue.contains("N/A"))
   										{
   											secondvalue = df.parse("1/1/1900");
   										}
   										compare = value.compareTo(secondvalue);
   										System.out.println("The value of the compare is: " + compare);
   									}
   									else
   									{
   										compare = firstvalue.toUpperCase().compareTo(nextRowValue.toUpperCase());
   									}
   								}						
   								else
   								{
   									return "Fail -> Was not specified the Sorting column to verify";
   								}
   								WebElement asc_desc =  driver.findElement(By.xpath(OR.getProperty(values[2])));
   								String orderby = asc_desc.getAttribute("className");
   								
   								System.out.println(asc_desc.getAttribute("className"));
   								
   								if (orderby.contains("headerSortDown"))
   								{
   									if (compare <= 0)
   									{
   										// accusam34 accusam346diam
   										result = true;
   										System.out.println("Correctly ordered ASC ");
   									}
   									else
   									{
   										return "Fail -> The sorting was Not correct " + firstvalue + value + "Not less than " + nextRowValue + secondvalue ;
   									}
   								}
   								else if (orderby.contains("headerSortUp"))
   								{
   									if (compare >= 0)
   									{
   										// accusam34 accusam346diam
   										result = true;
   										System.out.println("Correctly ordered DESC ");
   									}
   									else
   									{
   										return "Fail -> The sorting was Not correct " + firstvalue + value + "Not greater than " + nextRowValue + secondvalue ;
   									}
   								}
   								else
   								{
   									return "Fail -> The object does not contains the header link ";
   								}
   								i++;
   							}
   						}
   						//
   							else
   							{
   								for (WebElement row : rows) 
   								{
   									if (rows.size() == i+1)
   									{
   										break;
   									}
   									System.out.println("Row: " + i.toString());
   									//WebElement cell = row.findElement(By.tagName("td"));
   									List<WebElement> cells = row.findElements(By.tagName("td"));
   									if(!values[3].isEmpty())
   									{
   										firstvalue = cells.get(Integer.valueOf(values[3])).getText();
   									    System.out.println("The first value displayed is: " + firstvalue);
   										nextRowValue = rows.get(i+1).findElements(By.tagName("td")).get(Integer.valueOf(values[3])).getText();
   										System.out.println("The second value displayed is: " + nextRowValue);
   										value = null;
   										secondvalue = null;

   										Boolean isDate = false;
   										DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
   										try
   										{
   											value = df.parse(firstvalue);
   											isDate =true;
   											System.out.println("The first date value is: " + value);
   										}
   										catch(Throwable t){ }
   										
   										try {
   											secondvalue = df.parse(nextRowValue);
   											isDate =true;
   											System.out.println("The second date value is: " + secondvalue);
   										}
   										catch(Throwable t){	}									
   										if (isDate)
   										{
   											if (firstvalue.contains("N/A"))
   											{
   												value = df.parse("1/1/1900");
   											}
   											if (nextRowValue.contains("N/A"))
   											{
   												secondvalue = df.parse("1/1/1900");
   											}
   											compare = value.compareTo(secondvalue);
   											System.out.println("The value of the compare is: " + compare);
   										}
   										else
   										{
   											//compare = firstvalue.toUpperCase().compareTo(nextRowValue.toUpperCase());
   											compare = firstvalue.length() - nextRowValue.length();
   										}
   									}						
   									else
   									{
   										return "Fail -> Was not specified the Sorting column to verify";
   									}
   									WebElement asc_desc =  driver.findElement(By.xpath(OR.getProperty(values[2])));
   									String orderby = asc_desc.getAttribute("className");
   									
   									System.out.println(asc_desc.getAttribute("className"));
   									
   									if (orderby.contains("headerSortDown"))
   									{
   										if (compare <= 0)
   										{
   											// accusam34 accusam346diam
   											result = true;
   											System.out.println("Correctly ordered ASC ");
   										}
   										else
   										{
   											return "Fail -> The sorting was Not correct " + firstvalue + value + "Not less than " + nextRowValue + secondvalue ;
   										}
   									}
   									else if (orderby.contains("headerSortUp"))
   									{
   										if (compare >= 0)
   										{
   											// accusam34 accusam346diam
   											result = true;
   											System.out.println("Correctly ordered DESC ");
   										}
   										else
   										{
   											return "Fail -> The sorting was Not correct " + firstvalue + value + "Not greater than " + nextRowValue + secondvalue ;
   										}
   									}
   									else
   									{
   										return "Fail -> The object does not contains the header link ";
   									}
   									i++;
   								}
   								
   							}
   	////////
   							
   							
   							//i++;
   							System.out.println(" ");
   							
   							
   						} 
   						else 
   						{
   							return "Fail ->There is No Elements in the Table";
   						}
   					}

   				}

   			} 
   			catch (Throwable t)
   			{
   				System.out.println("Error verifying the sorting ASC/DESC ");
   				return "Fail " + t.getMessage();
   			}
   			try
   			{
   				if(!object.contains("NoLinkNext"))
   				{
   					if (values[1].contains("_ID")) 
   					{
   						link_nexts = driver.findElements(By.id(OR.getProperty(values[1])));
   					} 
   					else if (values[1].contains("_xpath"))
   					{
   						link_nexts = driver.findElements(By.xpath(OR.getProperty(values[1])));
   					} 
   					else if (values[1].contains("_css")) 
   					{
   						link_nexts = driver.findElements(By.cssSelector(OR.getProperty(values[1])));
   					}
   					else 
   					{
   						return "Fail -> The object of next link is not accurate, it should include: '_ID', '_xpath' or '_css'";
   					}
   					link_next_displayed = true;
   				}
   				
   			} 
   			catch (Throwable t)
   			{}
   			if (link_next_displayed)
   			{
   				for (WebElement link_next : link_nexts)
   				{
   					if (link_next.isDisplayed()) 
   					{
   						System.out.println(link_next.getAttribute("class"));
   						if (!link_next.getAttribute("class").contains("inactive")) 
   						{
   							try 
   							{
   								link_next.click();
   								waitCOLTServer();
   								System.out.println("Next link");
   								break;
   							} 
   							catch (Throwable t) 
   							{
   								return "Fail -> Not possible to click on next link";
   							}
   						} 
   						else
   						{
   							inactive = true;
   							return "Pass";
   						}
   					}
   				}
   			} 
   			else 
   			{
   				inactive = true;
   			}

   		}
   		if (result)
   		{
   			return "Pass";
   		}
   		else
   		{
   			return "Fail";
   		}
   	}

      public static String scrollControl() {
  		try {
  			System.out.println("Executing scrollDown" + object);
  			String[] values = object.split("#");
  			if (values.length < 2) {
  				values = new String[2];
  				values[0] = object;
  				values[1] = object;
  			}
  			//WebElement dropDownListBox = null;
  			WebElement scroll = null;
  			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
  			
  			String data = TestData.getCellData(currentTest, dataColumnName, testRepeat);
  			System.out.println(data);
  			if (!data.isEmpty()) {
  			
  				//**************new code*******\\\\\\
  				if (values[0].contains("_ID")) {
  					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
  							20);
  					scroll = wait.until(presenceOfElementLocated(By.id(OR
  							.getProperty(values[0]))));

  				} else if (values[0].contains("_xpath")) {
  					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
  							20);
  					scroll = wait.until(presenceOfElementLocated(By
  							.xpath(OR.getProperty(values[0]))));
  				} else if (values[0].contains("_css")) {
  					WebDriverWait wait = new WebDriverWait(driver, /* seconds= */
  							20);
  					scroll = wait.until(presenceOfElementLocated(By
  							.cssSelector(OR.getProperty(values[0]))));
  				}
  				else {
  					return "Fail -> The object is not accurate, it should include: '_ID', '_xpath' or '_css'";
  				}
  			
  				/*if (driver instanceof JavascriptExecutor) {
  				    ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('jScrollPaneDrag')[0].setAttribute('style', 'width: 10px; height: 118.212px; top: 200px;')");
  				}*/
  				//getElementsByClassName
  				//
  				
  				System.out.println(scroll.getLocation());
  				//scroll.getLocation().moveBy(0, 100);
  				//dropDownListBox.getLocation().
  				Actions action = null;
  				action = new Actions(driver);
  				//action.clickAndHold(scroll);
  				///*Daysi
  				/*System.out.println(scroll.getSize());
  				action.clickAndHold(scroll).moveToElement(scroll, 0, Integer.parseInt(data)).release().perform();*/
  				
  				
  				Thread.sleep(600);
  			}
  			else
  			{
  				System.out.println("The data is empty");
  			}
  		} catch (Throwable t) {
  			System.out.println("Error in selectDropdownScrolled: ");
  			return "Fail ->" + t.getMessage();
  		}
  		return "Pass";
  	}
      
}


