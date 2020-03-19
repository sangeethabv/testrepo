package testscript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ie.InternetExplorerDriver;

import reports.ReportUtil;

import util.RandomUtilities;
import util.SendMail;
import util.TestUtil;
import datatable.Xls_Reader;

public class DriverScript {
	public static Properties CONFIG;
	public static Properties APPTEXT;
	public static Properties OR;
	public static WebDriver driver=null;
//	public static EventFiringWebDriver driver;
	public static Xls_Reader controller;
	public static Xls_Reader TestData;
	public static String currentTest;
	public static String currentTitle;
	public static String description;
	public static String keyword;
	public static String object;
	public static String dataColumnName;
	public static int testRepeat;
	public static String testStatus=null;
	public static List<String> objects = new ArrayList<String>();
    public static String mainDir = null;
    public static String subDir = null;
    public static int splitTestStatus = 4;
    public static String testDataStatus = null;

    static {

        // load properties file

        try {
    		//load properties file
    		CONFIG = new Properties();
    		FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
    		CONFIG.load(fin);
    		
    		OR = new Properties();
    	     fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\OR.properties");
    		OR.load(fin);
    		
    		APPTEXT = new Properties();
    		fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\app_text.properties");
    		APPTEXT.load(fin);

        } catch (IOException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
	  @BeforeClass //to create the reports before each class testApp
	    public static void startTesting() throws IOException {
	
	        // Create Report main directory if it doesn't exist and sub directories
	        // to hold historical data
	        mainDir = System.getProperty("user.dir") + "\\demoreports";
	        subDir = mainDir + "\\" + "demoreport"
	                + TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");

	        File dir1 = new File(mainDir);                                  
	        boolean exists = dir1.exists();
	        try {
	            if (!exists) {
	            	System.out.println("the main directory you are searching does not exist : "
	                        + exists);
	                dir1.mkdir(); // creating main directory if it doesn't exist
	                new File(subDir).mkdir(); // creating time-stamped sub-directory
	            } else {
	            	System.out.println("the main directory you are searching does exist : "
	                        + exists);
	                new File(subDir).mkdir();
	            }
	        } catch (Throwable t) {
	        	System.out.println("FAILS TO CREATE REPORT FOLDERS");
	        }
	        ReportUtil.startTesting(subDir + "//index.html",
	                TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), "QA", "4.0");
	    }

	    @Before
	    public final void initialize() throws IOException {

	       try{
	    	controller = new Xls_Reader(System.getProperty("user.dir")
	                + "\\src\\config\\Controller_VF_DEV.xlsx");
	        TestData = new Xls_Reader(System.getProperty("user.dir")
	                + "\\src\\config\\TestData_VF_DEV.xlsx");
	       }
	       catch(Throwable t){
	    	   
	       } 
	    }
		
	@Test
	public final void testApp()
	{
		
		String startTime = null;
		ReportUtil.startSuite("Suite1");
		
		for(int rows =2;rows<=controller.getRowCount("Suite1");rows++){ //for the suite
			currentTest = controller.getCellData("Suite1", "TCID", rows);
			currentTitle = controller.getCellData("Suite1", "Title", rows);
			if(controller.getCellData("Suite1", "Runmode", rows).equals("Y")){
				
				System.out.println(rows +" Execute Test "+currentTest);
				int totalSets = TestData.getRowCount(currentTest);
				System.out.println("rows in testdata "+totalSets);
				if(totalSets<=1){
					totalSets = 2; //run at least once
				}
				for( testRepeat=2;testRepeat<=totalSets;testRepeat++) //run any number of rows TestData has
				{
					startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
					for(int tsid = 2;tsid<=controller.getRowCount(currentTest);tsid++) //run for each step
					{				
						description = controller.getCellData(currentTest, "Decription", tsid);
						keyword = controller.getCellData(currentTest, "Keyword", tsid);
						object = controller.getCellData(currentTest, "Object", tsid);
						dataColumnName = controller.getCellData(currentTest, "Data_Column_Name", tsid);
						if(keyword.trim()!="")
						{
							try
							{
								/*if (keyword == "verifyRecordWasDeleted")
								{
								 String test = "test";
								}*/
								//Keywords.navigate();
								//return;
								String tsidCol = controller.getCellData(currentTest, "TSID", tsid);
								System.out.println("TSID: " + tsidCol + " - " + keyword);
								Method method =Keywords.class.getMethod(keyword);	//get the info of the method
								String result = (String) method.invoke(method);     //call method by reflexion
								//objects.clear();
								System.out.println("Result "+result);
								System.out.println("--------------END of TEST STEP---------------");								
								String fileName = currentTest + "ScreenShot" + tsid + ".jpg";																			
								if(result != null)
								{
									if(result.equals("Pass"))
									{					
										ReportUtil.addKeyword(description, keyword, result, null);
										//TestUtil.takeScreenShot(subDir + "\\" + fileName);
									}
							
			                        if (result.startsWith("Fail")) {
			                        	System.out.println("Screen shot path:" + subDir + "\\" + fileName);
			                            TestUtil.takeScreenShot(subDir + "\\" + fileName);
			                            ReportUtil.addKeyword(description, keyword, result, fileName);
			                            testStatus = result;
			                            driver.quit();
										driver = null;									    										    
			                            break;
			                        }
								}
							}								
							catch(Throwable t)
							{
								
								System.out.println("error came");
								System.out.println(keyword);
								t.printStackTrace();
							}
					   }
					}
					
					if(testStatus==null){
						
						testStatus = "Pass";
					}
					System.out.println("currentTest "+testStatus);
					System.out.println("************END of TEST CASE***************");
	                ReportUtil.addTestCase(currentTest, currentTitle, startTime,
	                		TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
	                		testStatus.substring(0, splitTestStatus));
				}//testrepeat
							
			}
			else{
				System.out.println("SkipTest "+currentTest);
				testStatus = "Skip";
				ReportUtil.addTestCase(currentTest, currentTitle, 
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus );

			}
			testStatus = null;
		}
		
		ReportUtil.endSuite();
				
	}

	@AfterClass
	public static void endScript(){
		
        ReportUtil.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
                testDataStatus);
	//	SendMail.callThisMethodToSendMail();
	}
	
}
