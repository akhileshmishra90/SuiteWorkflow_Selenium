package com.TestScripts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.TestScripts.RetryTest.Retry;
import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.eCRMCustomerPages;
import com.gui_auto.utilities.ScreenShot;

public class eCRM_Validation extends GUI_automation_base 

{
//	ReadAndUpdate dbConnection = new ReadAndUpdate();
//	//ScreenShot TakeScreenshot = new ScreenShot();
//	String browser= _properties.getProperty(GUI_automation_properties.BASEURL);
//	//String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
//	//public static Eyes eyes;
//	
//	long maxWait=10000;
//	//File directory = new File(sFile);
//
	private RegisterPage _register;
//	RetryTest Retry = new RetryTest();
//
//	@Rule
//	public Retry retry = Retry.new Retry(1);
//
//	//@Before
//	public void setUp() throws Exception
//	{		
//		//eyes = new Eyes();
//		//eyes.setApiKey("U4H2HXkiij105f7H1jedc103H9d2tRA8Hdua6DepWhuic110");
//		//eyes.setForceFullPageScreenshot(true);
//	}
//
//	public int gen() {
//	    Random r = new Random( System.currentTimeMillis() );
//	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
//	}
//	
//	public String dateReturn()
//	{	
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
//		return sdf.format(date);
//	}
//
////	@After
//	public void tearDown() throws Exception 
//	{
//		_driver.quit();
//	}
	@Test
	public void Validate_Customer() throws Exception
	{
		
		//=======================================================================
			//Constant ones....
		//=======================================================================
				
		
//		String CustomerName=dbConnection.ReadFunction("RadiusData", "Prospect_Radius", "RadiusData", "Customer_Name");
 //          System.out.println(CustomerName);
 //   	String SiteUrl = dbConnection.ReadFunction(sSheetName, ScenarioName, TestCase, ColumnName);
//		String UN=dbConnection.ReadFunction("Roles", "eCRM", "admin", "UserID");
//		String PWD=dbConnection.ReadFunction("Roles", "eCRM", "admin", "Password");
        
		//=======================================================================
		  //Launching Application with Site URL--
	   //=======================================================================
		
		System.out.println("launch browser");
		
	
	super.setUp(getBrowser());
			_register = PageFactory.initElements(_driver, RegisterPage.class);
		_register.launchapplication("https://www.facebook.com/");
		//_driver.manage().window().maximize();
		//_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//		Thread.sleep(10000);
//		String sCustomerName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CustomerName");
//		
//		//=======================================================================
//		  //ECRM Functions--pages
//	   //=======================================================================
//		eCRMCustomerPages EC= new eCRMCustomerPages(_driver);
//		EC.Login_ecrm(UN, PWD);                //Login to ECRM
//		EC.Navigate_CutomerList_Page();     //Navigate to Customer Edit page
//		EC.eCRM_Validate_Customer(sCustomerName, "Customer");
//		//=======================================================================
//		  //Test Case complete
//		//=======================================================================
	}

}
