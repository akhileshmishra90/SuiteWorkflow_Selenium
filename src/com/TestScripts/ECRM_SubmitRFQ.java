package com.TestScripts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

public class ECRM_SubmitRFQ extends GUI_automation_base 

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
	public void SubmitRfq() throws Exception
	{
		//=======================================================================
			//Constant ones....
		//=======================================================================
				
		String sSheetName="IquoteData";
		String  sScenario = "eCRM";
		String  sTestcase = "TC_1";
		String CustomerName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CustomerName");
		String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "eCRMURL");
		String UN=dbConnection.ReadFunction("Roles", "eCRM", "admin", "UserID");
		String PWD=dbConnection.ReadFunction("Roles", "eCRM", "admin", "Password");
		String rfqCustomer=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "eCrmRFQCust");
		String rfqForm=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "eCRmForm");
		String repUsername=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCRMRep");
		String userContact=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCRMContact");
        String assigne=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCRMassigne");
        String rfqStatus=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCRMrfqStatus");
        String refDescription=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCrmDescription");
        String Quantity=dbConnection.ReadFunction("eCRMRFQ", "eCRM", "carton_test", "eCRMQuantity");
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
		EC.Login_ecrm(UN, PWD);    
		EC.Navigate_To_RFQ_Page();
		EC.Select_open_RFQ(rfqCustomer, rfqForm);
		String RFQID=EC.CreateRFQ(repUsername, userContact, assigne, rfqStatus, refDescription, Quantity);
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"RFQID",RFQID);
		_driver.quit();
	
	}//
}
