package com.TestScripts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.TestScripts.RetryTest.Retry;
import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.eCRMCustomerPages;
import com.gui_auto.utilities.ScreenShot;

public class eCrmWorkflow extends GUI_automation_base 

{
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	ScreenShot TakeScreenshot = new ScreenShot();
	String browser= _properties.getProperty(GUI_automation_properties.BASEURL);
	String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
	//public static Eyes eyes;
	
	long maxWait=10000;
	File directory = new File(sFile);

	private RegisterPage _register;
	RetryTest Retry = new RetryTest();

	@Rule
	public Retry retry = Retry.new Retry(1);

	//@Before
	public void setUp() throws Exception
	{		
		//eyes = new Eyes();
		//eyes.setApiKey("U4H2HXkiij105f7H1jedc103H9d2tRA8Hdua6DepWhuic110");
		//eyes.setForceFullPageScreenshot(true);
	}

	public int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}
	
	public String dateReturn()
	{	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		return sdf.format(date);
	}

//	@After
	public void tearDown() throws Exception 
	{
		_driver.quit();
	}
	
	@Test
	public void CrateecrmCustomer() throws Exception
	{
		//=======================================================================
			//Constant ones....
		//=======================================================================
				
		String sSheetName="IquoteData";
		String  sScenario = "eCRM";
		String  sTestcase = "TC_1";
		int val=gen();
		String CustomerName="AU"+val;
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"CustomerName",CustomerName);
		String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "eCRMURL");
		String UN=dbConnection.ReadFunction("Roles", "eCRM", "admin", "UserID");
		String PWD=dbConnection.ReadFunction("Roles", "eCRM", "admin", "Password");
		String sSalesperson=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Monar_SalesRep");
		String saddress1=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Address1");
		String sCity=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "City");
		String sPostalcode=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ZIP_Code");
		 String Postalcode=sPostalcode.replaceAll("\\.0*$", "");
		System.out.println("sPostalcode is : "+Postalcode);
		//=======================================================================
		  //Launching Application with Site URL 
	   //=======================================================================
		
		super.setUp(getBrowser());
		_register = PageFactory.initElements(_driver, RegisterPage.class);
		_register.launchapplication(SiteUrl);
		//_driver.manage().window().maximize();
		//_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(10000);
		
		//=======================================================================
		  //ECRM Functions
	   //=======================================================================
		eCRMCustomerPages EC= new eCRMCustomerPages(_driver);
		EC.Login_ecrm(UN, PWD);                //Login to ECRM
		EC.Navigate_to_eCrmcustomerpage();     //Navigate to Customer Edit page
		String Customerid=EC.Create_New_Customer(CustomerName, sSalesperson, saddress1, sCity, Postalcode); //Create New Customer
		System.out.println("Customer ID is : "+Customerid);
		Customerid= Customerid.replaceAll("\\p{P}", "");
		System.out.println("Customer ID is : "+Customerid);
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"CustomerID",Customerid);
		_driver.quit();
		//=======================================================================
		  //Test Case complete
		//=======================================================================
	}
	
}
