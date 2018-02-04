package com.TestScripts;
import static junit.framework.Assert.fail;

//import com.applitools.eyes.Eyes;
//import com.applitools.eyes.RectangleSize;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.JobName;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import com.TestScripts.RetryTest.Retry;
import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.dao.ReadAndUpdate_Path;
import com.gui_auto.pages.JCCPage;
import com.gui_auto.pages.JobPlanningPage;
import com.gui_auto.pages.Login;
import com.gui_auto.pages.DCPage;
import com.gui_auto.pages.ICPage;
import com.gui_auto.pages.DSF_Integration_Page;
import com.gui_auto.pages.JobBillingPage;
import com.gui_auto.pages.Product;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.Storefront;
import com.gui_auto.pages.StorefrontCustomizationPage;
import com.gui_auto.pages.Users;
import com.gui_auto.pages.WorkflowIntegrationPage;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;
import org.junit.runners.JUnit4;

public class IntegrationWorkFlow extends GUI_automation_base{
	private StringBuffer verificationErrors = new StringBuffer();
	private static String Mobile = "false";
	private static  String sSpecialChars = null;
	private static  String sAlphaNumeric = null;
	private static  String sMaxChars = null;
	private static  String sStorefront = null;
	ScreenShot TakeScreenshot = new ScreenShot();
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	String browser= _properties.getProperty(GUI_automation_properties.BASEURL);
	String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
	//public static Eyes eyes;
	
	long maxWait=10000;
	File directory = new File(sFile); 
	public ArrayList  FinalFilename = new ArrayList();


	private RegisterPage _register;
	RetryTest Retry = new RetryTest();

	@Rule
	public Retry retry = Retry.new Retry(1);
	public TestName name=new TestName();


	@Before
	public  void setUp() throws Exception 
	{
		//		super.setUp(getBrowser());
		//PropertyConfigurator.configure("config/log4j.properties");		
		//		_register = PageFactory.initElements(_driver, RegisterPage.class);
		
		//eyes = new Eyes();

         //eyes.setApiKey("U4H2HXkiij105f7H1jedc103H9d2tRA8Hdua6DepWhuic110");
         //eyes.setForceFullPageScreenshot(true);
	}

	public String  UniqueNum()
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		DateFormat dateFormat = new SimpleDateFormat("ddMMhhmmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}

	public String dateReturn()
	{	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm");	
		return sdf.format(date);
	}

	public String TakeScreenshot(String sPageTitle) throws IOException
	{	               
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		String NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ sPageTitle + dateFormat.format(date)+"_"+ ".png";   
		FinalFilename.add(NewFileNamePath);
		System.out.println(NewFileNamePath);
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		return NewFileNamePath;
	}

	public String TakeScreenshot(String sPageTitle,String sTestcase) throws IOException
	{	               
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		String NewFileNamePath = directory.getCanonicalPath()+ sTestcase + sPageTitle + dateFormat.format(date)+"_"+ ".png";   
		FinalFilename.add(NewFileNamePath);
		System.out.println(NewFileNamePath);
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		return NewFileNamePath;
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**	@throws Exception   */
	@After
	public void tearDown() throws Exception 
	{		   
		//_driver.close();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString))			{
			fail(verificationErrorString);
		}
	}

	@Test
	public void TC_6() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_6";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{
				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String Product2Name=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String product2_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String oddPageFile=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "File");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CreditCardNumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CreditCardNumber");
				String ExpirationMonth=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationMonth");
				String ExpirationYear=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationYear");

				String PaceURL=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEURL");
				String PaceUserN=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEUserName");
				String PacePass=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEPassword");
				String PaceCompany=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACECompany");
				String PACEServer=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEServer");
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				bwf.click_on_Add_Files_And_Upload_Files();

                us.Auto_IT_Upload_MultipleFiles(oddPageFile);
                
                bwf.click_On_Upload_File_Button();
                
	  			bwf.Click_on_Done_Button();
	  			
	  			bwf.Addto_Cart();
	  			
	  			//Adding another product to cart
	  			bwf.searchProductAndBuy(Product2Name);
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

				//Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
				//Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_CreditCardPaymentPethod(PaymentMethod);
				
				bwf.enterTheCreditCardNumber(CreditCardNumber);
				
				bwf.SelectExpirationMonthAndYear(ExpirationMonth, ExpirationYear);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				String subTotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
				String ShippingAmount_Before_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
				String TotalValue_Before_code_apply =	 _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();
				String TaxCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Tax_Charges))).getText();
				String HandlingCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Handling_Charges))).getText();
				System.out.println("Shipping amount before Discount::"+ShippingAmount_Before_code_apply);
				System.out.println("SubTotal Value Before Discount::"+subTotalValue_Before_code_apply);
				System.out.println("Total Value Before Discount::"+TotalValue_Before_code_apply);
				System.out.println("Tax Charge Before Discount::"+TaxCharge_Before_code_apply);
				System.out.println("Handling Charge Before Discount::"+HandlingCharge_Before_code_apply);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal",subTotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount",ShippingAmount_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTotalAmt",TotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount",TaxCharge_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount",HandlingCharge_Before_code_apply);
				
				
				System.out.println("DSF Order No is ::"+orderNum);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);
								
				//==========================================================================
				///   Start PACE flow ////
				//==========================================================================
				String sJobNumber="";
				
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(PaceURL);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//_driver = eyes.open(_driver, "PACE", "TC_6_New", new RectangleSize(1024, 768));
				
				ICPage IC = new ICPage(_driver);
				DCPage DC = new DCPage(_driver);
				JobBillingPage  JB = new JobBillingPage(_driver);
				DSF_Integration_Page DSF = new DSF_Integration_Page (_driver);

				System.out.println("Loging in to Pace");
				DC.EpaceLogin(PaceUserN, PacePass, PaceCompany);
				
				String orderNumber = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Order_Number");
				
				System.out.println("Fetch Job Number using order number");
				//==========================================================================
				// Fetch Job Number using order number // 
				//==========================================================================
				
				sJobNumber = DSF.FetchJobNumber(orderNumber);
				if(!sJobNumber.equals(""))
				{
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "PACE_Job_Num", sJobNumber);
				}
				else
				{
					Assert.fail("Job Not Created for above Order Number");
				}
				
				//==========================================================================				
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab");

				String sJobValue = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal");
				String sTax = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount");
				String sShipping = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount");
				String sHandling = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount");

				
				boolean IsSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);
				//==========================================================================
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				boolean IsNotEditableSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);
			
				if(IsSuccess==true && IsNotEditableSuccess==true)
				{
					System.out.println("Verified Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				}
				else
				{
					Assert.fail("Failed to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in pace. IsSuccess is "+IsSuccess+" IsNotEditableSuccess is "+IsNotEditableSuccess);
				}

				
				//==========================================================================
				// ========Verify Job Products==============
				//==========================================================================
				System.out.println("========Verify Job Products==============");
				boolean jobProductNames =false;
				System.out.println("Click on Products Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
				TakeScreenshot.ScreenShotWindow(_driver, "ProductsPage");
				Thread.sleep(2000);

				String totalRecordsProducts = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalProducts: "+totalRecordsProducts);
				if (totalRecordsProducts.equals("2"))
				{
					String product1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[1]/td[3]/input")).getAttribute("value");
					String product2Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[2]/td[3]/input")).getAttribute("value");

					//One of the products will be the description of combo template which is ComboDesc mentioned in P_3134_3135_3136
					//Other product will be the description of the Kit Job which is Kit123 mentioned in P_3141
					Thread.sleep(1000);
					
					String sPro1 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					String sPro2 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");

					if((product1Name.equals(sPro1) || product1Name.equals(sPro2)) && (product2Name.equals(sPro1) || product2Name.equals(sPro2)) && !product1Name.equals(product2Name))
					{
						jobProductNames = true;
						System.out.println("jobProductNames: " +jobProductNames);
						System.out.println("Job Product Names have been verified");
					}
					else
					{
						jobProductNames = false;
						System.err.println("jobProductNames: " +jobProductNames);
					}

					Thread.sleep(1000);

				}
				else
				{
					System.err.println("Total Products created are incorrect");
				}
				
				String paymentMethod = null;
				String[] sPaymentMode={"Credit Card"};
				String[] sPaymentValue={"XXXXXXXXXXXX1111"};
				
				String[] PaymentOption_And_Value= DSF.Verify_The_Payment_Mode_In_Pace();
				System.out.println("PaymentOption_And_Value is "+PaymentOption_And_Value[0]+" PaymentOption_And_Value[1] is "+PaymentOption_And_Value[1]);

				if(PaymentOption_And_Value[0].equals(sPaymentMode[0]) && PaymentOption_And_Value[1].equals(sPaymentValue[0]))
				{
					System.out.println("The Payment Mode and Payment Value are correct");
				}
				else
				{
					Assert.fail("Fail");
				}
								
				//==========================================================================
				// ========//Verify the quantity ordered==============
				//==========================================================================
				//Since this Kit is associated with a combo template which has 2 standard item templates, the Job will have 1 kit part and 2 parts from combo item template

				//Verify the quantity ordered

				System.out.println("========Verify Job Parts==============");
				//boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
				System.out.println("Click on Parts Info Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
				Thread.sleep(2000);
				TakeScreenshot.ScreenShotWindow(_driver, "JobPartsInfo");
				String totalRecordsParts = _driver.findElement(By.xpath("//fieldset[@id='JobPart_N1002F_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalParts: "+totalRecordsParts);
				if (totalRecordsParts.equals("2"))
				{
					//Check the quantity of all the 4 parts
					//In this case, all the quantities should be 15 (qty is hardcoaded while placing the order)
					boolean partQuantity = false;
					String qty1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[8]/div")).getText();
					String qty2 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[4]/td[8]/div")).getText();
					System.out.println("qty1: " +qty1);
					System.out.println("qty2: " +qty2);
	
					if(qty1.equals("1") && qty2.equals("10"))
					{
						partQuantity = true;
						System.out.println("partQuantity: " +partQuantity);
						System.err.println("Job Quantity has been verified");
					}
					else
					{
						partQuantity = false;
						Assert.fail("Job qty could not be verified");
					}
					//==========================================================================
					// ========//Verify the Job Part Name==============
					//==========================================================================
					boolean jobPartNames = false;
					String sPro1 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					String sPro2 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
					//Check that the description of part 1 is the same as the Job Name
					String part1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[6]/input")).getAttribute("value").trim();
					System.out.println("part1Name: "+part1Name);

					String part2Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[4]/td[6]/input")).getAttribute("value").trim();
					System.out.println("part2Name: "+part2Name);

					Thread.sleep(1000);
					
					if(part1Name.contains(sPro1) && part2Name.contains(sPro2))
					{
						jobPartNames = true;	
						System.out.println("jobPartNames: " +jobPartNames);
						System.err.println("Job Part Names have been verified");
					}
					else
					{
						jobPartNames = false;
						System.err.println("jobPartNames: " +jobPartNames);
					}
					Thread.sleep(1000);

					//==========================================================================
					// //Verify that the Job Part is mapped to its appropriate Job Product
					//==========================================================================				
					boolean check1 = false;
					String sPro11 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					String sPro21 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
					String prod1 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[4]/select")).replace("[", "").replace("]", "").trim();
					String prod2 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[4]/td[4]/select")).replace("[", "").replace("]", "").trim();
					System.out.println("pro1: "+prod1);
					System.out.println("pro2: "+prod2);
					
					//Part 1 and Part 2 will always be Kit123 and so will be the product
					if(prod1.equals(sPro11) && prod2.equals(sPro21))
					{
						check1 = true;
						System.out.println("Product 1 matches Part 1 and Part 2");
					}
					//==========================================================================
					// //Verify the Material,Content and PrintOptions of Adhoc Order
					//==========================================================================
					
					CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img"));
					CommonFunctions.waitForPageLoaded(_driver);
					
					CommonFunctions.Wait(_driver, By.name("additionalDescription"));
					
					int iFieldsetCnt = CommonFunctions.RowCount(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset"));
					for (int i=1; i<iFieldsetCnt+1; i++)
					{
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]")));
						//String sFieldSetName = CommonFunctions.getText(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]/legend")).replace(" ", "");
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]"),"PartDetails"+i);
					}
					
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Details']"));
					Thread.sleep(1000);
									
					int iFieldsetCnt1 = CommonFunctions.RowCount(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset"));
					for (int i=1; i<iFieldsetCnt1+1; i++)
					{
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]")));
						//String sFieldSetName = CommonFunctions.getText(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]/legend")).replace(" ", "");
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]"), "JobDetails"+i);
					}
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='eneral']"));
					Thread.sleep(1000);
					
					TakeScreenshot.ScreenShotWindow(_driver, "General");					
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='illing']"));
					Thread.sleep(1000);
					
					int iFieldsetCnt3 = CommonFunctions.RowCount(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset"));
					for (int i=1; i<iFieldsetCnt3+1; i++)
					{
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]")));
						//String sFieldSetName = CommonFunctions.getText(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]/legend")).replace(" ", "");
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]"), "Billing"+i);
					}
					
					CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='a']"));
					Thread.sleep(1000);
					
					int iFieldsetCnt4 = CommonFunctions.RowCount(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset"));
					for (int i=1; i<iFieldsetCnt4+1; i++)
					{
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]")));
						//String sFieldSetName = CommonFunctions.getText(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]/legend")).replace(" ", "");
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]"), "Plan"+i);
					}
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
					Thread.sleep(1000);
					
					int iFieldsetCnt5 = CommonFunctions.RowCount(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset"));
					for (int i=1; i<iFieldsetCnt5+1; i++)
					{
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]")));
						//String sFieldSetName = CommonFunctions.getText(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]/legend")).replace(" ", "");
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']/fieldset["+i+"]"), "JobPartItems"+i);
					}
					
					
				}
				else
				{
					System.err.println("Total Parts created are incorrect");
				}
				//eyes.close();

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
				//eyes.abortIfNotClosed();
			}
		}//if execution required

	}//test end
	
	@Test
	
	public void TC_5() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_5";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{
				
				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
			    String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CreditCardNumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CreditCardNumber");
				String ExpirationMonth=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationMonth");
				String ExpirationYear=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationYear");

							
				String PaceURL=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEURL");
				String PaceUserN=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEUserName");
				String PacePass=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEPassword");
				String PaceCompany=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACECompany");
				String PACEServer=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEServer");

				//replace the gui automation properties
//				_properties.setProperty(GUI_automation_properties.BASEURL, PaceURL);
//				_properties.setProperty(GUI_automation_properties.SERVER, PACEServer);
				
				//==========================================================================
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				//==========================================================================
				//All Constructors
				//==========================================================================
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================
				//Login as user DSF
				//==========================================================================
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);
				
				bwf.Search_Product_smart_store_Npp(ProductName, product_qty);
				
				bwf.Click_On_ProceedTOCheckOut();

			    //Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
				//Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_CreditCardPaymentPethod(PaymentMethod);
				
				bwf.enterTheCreditCardNumber(CreditCardNumber);
				
				bwf.SelectExpirationMonthAndYear(ExpirationMonth, ExpirationYear);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				//==========================================================================
				//  Place NPP order and Capture DSF OrderNumber
				//==========================================================================
				
				//==========================================================================
				// Capture Subtotal,Shipping,Handling ,Tax and Total Value from DSF OrderConfirmation
				//==========================================================================
				String subTotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
				String ShippingAmount_Before_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
				String TotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();
				String TaxCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Tax_Charges))).getText();
				String HandlingCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Handling_Charges))).getText();
				System.out.println("Shipping amount before Discount::"+ShippingAmount_Before_code_apply);
				System.out.println("SubTotal Value Before Discount::"+subTotalValue_Before_code_apply);
				System.out.println("Total Value Before Discount::"+TotalValue_Before_code_apply);
				System.out.println("Tax Charge Before Discount::"+TaxCharge_Before_code_apply);
				System.out.println("Handling Charge Before Discount::"+HandlingCharge_Before_code_apply);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal",subTotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount",ShippingAmount_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTotalAmt",TotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount",TaxCharge_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount",HandlingCharge_Before_code_apply);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				System.out.println("DSF Order No is ::"+orderNum);
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);
				//==========================================================================
				///   Start PACE flow ////
				//==========================================================================
				String sJobNumber="";
				
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(PaceURL);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//_driver = eyes.open(_driver, "PACE", "TC_5", new RectangleSize(1024, 768));
				ICPage IC = new ICPage(_driver);
				DCPage DC = new DCPage(_driver);
				JobBillingPage  JB = new JobBillingPage(_driver);
				DSF_Integration_Page DSF = new DSF_Integration_Page (_driver);

				System.out.println("Loging in to Pace");
				DC.EpaceLogin(PaceUserN, PacePass, PaceCompany);
				TakeScreenshot.ScreenShotWindow(_driver, "EpaceLogin");
				String orderNumber = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Order_Number");
				
				System.out.println("Fetch Job Number using order number");
				//==========================================================================
				// Fetch Job Number using order number // 
				//==========================================================================
				
				sJobNumber = DSF.FetchJobNumber(orderNumber);
				if(!sJobNumber.equals(""))
				{
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "PACE_Job_Num", sJobNumber);
				}
				else
				{
					Assert.fail("Job Not Created for above Order Number");
				}
				
				//==========================================================================				
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab");

				String sJobValue = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal");
				String sTax = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount");
				String sShipping = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount");
				String sHandling = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount");
				
				
				boolean IsSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);
				TakeScreenshot.ScreenShotWindow(_driver, "ShipHandTaxesAmount");
				//==========================================================================
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				boolean IsNotEditableSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);

				
				if(IsSuccess==true && IsNotEditableSuccess==true)
				{
					System.out.println("Verified Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				}
				else
				{

					Assert.fail("Failed to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in pace. IsSuccess is "+IsSuccess+" IsNotEditableSuccess is "+IsNotEditableSuccess);
				}

				//==========================================================================
				// ========Verify Job Products==============
				//==========================================================================
				System.out.println("========Verify Job Products==============");

				System.out.println("Click on Products Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
				Thread.sleep(2000);

				String totalRecordsProducts = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalProducts: "+totalRecordsProducts);
				if (totalRecordsProducts.equals("1"))
				{
					String product1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[1]/td[3]/input")).getAttribute("value");
					Thread.sleep(1000);
					String sProd = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					boolean jobProductNames = false;
					if(product1Name.equals(sProd))
					{
						jobProductNames = true;
						System.out.println("jobProductNames: " +jobProductNames);
						System.out.println("Job Product Name has been verified");
					}
					else
					{
						System.err.println("jobProductNames: " +jobProductNames);
					}

					Thread.sleep(1000);
					TakeScreenshot.ScreenShotWindow(_driver, "ProductsTab");

				}
				else
				{
					System.err.println("Total Products created are incorrect");
				}
				
				String paymentMethod = null;
				String[] sPaymentMode={"Credit Card"};
				String[] sPaymentValue={"XXXXXXXXXXXX1111"};
				
				String[] PaymentOption_And_Value= DSF.Verify_The_Payment_Mode_In_Pace();
				System.out.println("PaymentOption_And_Value is "+PaymentOption_And_Value[0]+" PaymentOption_And_Value[1] is "+PaymentOption_And_Value[1]);
				TakeScreenshot.ScreenShotWindow(_driver, "PaymentOptionAndValue");
				if(PaymentOption_And_Value[0].equals(sPaymentMode[0]) && PaymentOption_And_Value[1].equals(sPaymentValue[0]))
				{
					System.out.println("The Payment Mode and Payment Value are correct");
				}
				else
				{
					Assert.fail("Fail");
				}
				
				
				//==========================================================================
				// ========//Verify the quantity ordered==============
				//==========================================================================
				System.out.println("========Verify Job Parts==============");
				//boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
				System.out.println("Click on Parts Info Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
				Thread.sleep(2000);
				boolean jobPartNames = false;
				String totalRecordsParts = _driver.findElement(By.xpath("//fieldset[@id='JobPart_N1002F_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalParts: "+totalRecordsParts);
				if (totalRecordsParts.equals("1"))
				{
					
					String qty1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[8]/div")).getText();
			
					System.out.println("qty1: " +qty1);
		
					boolean partQuantity;
					if(qty1.equals("10"))
					{
						partQuantity = true;
						System.out.println("partQuantity: " +partQuantity);
						System.out.println("Job Quantity has been verified");
					}
					else
					{
						Assert.fail("Job qty could not be verified");
						System.err.println("Job Quantity has been verified and failed");
					}

					//==========================================================================
					// ========//Verify the Job Part Name==============
					//==========================================================================
					
					String sProd = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					//Check that the description of part 1 is the same as the Job Name
					String part1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[6]/input")).getAttribute("value");
					//CommonFunctions.GetValue(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[6]/input"));
					System.out.println("part1Name: "+part1Name);

					Thread.sleep(1000);

					if(part1Name.equals(sProd) )
					{
						jobPartNames = true;
						System.out.println("jobPartNames: " +jobPartNames);
						System.out.println("Job Part Names have been verified");
					}
					else
					{
						System.err.println("jobPartNames: " +jobPartNames);
						Assert.fail("jobPartNames: " +jobPartNames);
					}
					Thread.sleep(1000);

					//==========================================================================
					// //Verify that the Job Part is mapped to its appropriate Job Product
					//==========================================================================				

					String pro1 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[4]/select")).replace("[", "").replace("]", "").trim();
					System.out.println("pro1: "+pro1);
					boolean check1;
					//Part 1 will always be Kit123 and so will be the product
					if(pro1.equals(part1Name))
					{
						check1 = true;
						System.out.println("All the part belongs to the same product");
					}
					else
					{
						System.err.println("All the part belongs to the same product failed");
					}
					TakeScreenshot.ScreenShotWindow(_driver, "JobPart");
				}
				else
				{
					System.err.println("Total Parts created are incorrect");
				}

				//eyes.close();
				
			}//try end
			finally
			{
				TakeScreenshot(sTestcase);
				//eyes.abortIfNotClosed(); 
			}
		}//if execution required

	}//Test end
		
	@Test

	public void TC_7() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_7";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
			    String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CreditCardNumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CreditCardNumber");
				String code1=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code1");
				String code2=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code2");
				
				String PaceURL=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEURL");
				String PaceUserN=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEUserName");
				String PacePass=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEPassword");
				String PaceCompany=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACECompany");
				String PACEServer=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEServer");
				
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);
				
				bwf.searchProductAndBuy(ProductName);
				
				bwf.clickOn_PrintandDownLink();
				
				bwf.Addto_Cart();
				
				bwf.changeQty_From_Cart(product_qty);
				
				bwf.Click_On_ProceedTOCheckOut();

			    //Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
				//Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_AccoutingCodePayment(PaymentMethod);
				
				bwf.enter_AccountingCodes(code1, code2);
				
	         	bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				String subTotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
				String ShippingAmount_Before_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
				String TotalValue_Before_code_apply =	 _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();
				String TaxCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Tax_Charges))).getText();
				String HandlingCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Handling_Charges))).getText();
				System.out.println("Shipping amount before Discount::"+ShippingAmount_Before_code_apply);
				System.out.println("SubTotal Value Before Discount::"+subTotalValue_Before_code_apply);
				System.out.println("Total Value Before Discount::"+TotalValue_Before_code_apply);
				System.out.println("Tax Charge Before Discount::"+TaxCharge_Before_code_apply);
				System.out.println("Handling Charge Before Discount::"+HandlingCharge_Before_code_apply);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal",subTotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount",ShippingAmount_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTotalAmt",TotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount",TaxCharge_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount",HandlingCharge_Before_code_apply);
								
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

				
				
				//==========================================================================
				///   Start PACE flow ////
				//==========================================================================
				String sJobNumber="";
				
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(PaceURL);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				ICPage IC = new ICPage(_driver);
				DCPage DC = new DCPage(_driver);
				JobBillingPage  JB = new JobBillingPage(_driver);
				DSF_Integration_Page DSF = new DSF_Integration_Page (_driver);

				System.out.println("Loging in to Pace");
				DC.EpaceLogin(PaceUserN, PacePass, PaceCompany);
				
				String orderNumber = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Order_Number");
				
				System.out.println("Fetch Job Number using order number");
				//==========================================================================
				// Fetch Job Number using order number // 
				//==========================================================================
				
				sJobNumber = DSF.FetchJobNumber(orderNumber);
				if(!sJobNumber.equals(""))
				{
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "PACE_Job_Num", sJobNumber);
				}
				else
				{
					Assert.fail("Job Not Created for above Order Number");
				}
				
				//==========================================================================				
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab");

				String sJobValue = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal");
				String sTax = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount");
				String sShipping = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount");
				String sHandling = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount");

				
				boolean IsSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);
				//==========================================================================
				//==========Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab==========//
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				boolean IsNotEditableSuccess =DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);

				
				if(IsSuccess==true && IsNotEditableSuccess==true)
				{
					System.out.println("Verified Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab");
				}
				else
				{
					Assert.fail("Failed to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in pace. IsSuccess is "+IsSuccess+" IsNotEditableSuccess is "+IsNotEditableSuccess);
				}

				//==========================================================================
				// ========Verify Job Products==============
				//==========================================================================
				System.out.println("========Verify Job Products==============");

				System.out.println("Click on Products Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
				Thread.sleep(2000);

				String totalRecordsProducts = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalProducts: "+totalRecordsProducts);
				if (totalRecordsProducts.equals("1"))
				{
					String product1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[1]/td[3]/input")).getAttribute("value");
					Thread.sleep(1000);
					String sProd = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					boolean jobProductNames = false;
					if(product1Name.equals(sProd))
					{
						jobProductNames = true;
						System.out.println("jobProductNames: " +jobProductNames);
						System.out.println("Job Product Name has been verified");
					}
					else
					{
						System.err.println("jobProductNames: " +jobProductNames);
					}

					Thread.sleep(1000);

				}
				else
				{
					System.err.println("Total Products created are incorrect");
				}
				
				String accountingCode = "12345";
				String code1Name = "";
				String code1Value = "";
				String code2Name = "";
				String code2Value = "";
				
				System.out.println("Verify the Payment Mode In Billing Info");
				
				System.out.println("Click on Billing Info Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
				Thread.sleep(2000);

				String sDSF_Payment_Mode = CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'DSF Payment Method')]/following-sibling::div/div"));
				sDSF_Payment_Mode=sDSF_Payment_Mode.trim();
				System.err.println("DSF Payment Mode on pace is "+sDSF_Payment_Mode);
				if(sDSF_Payment_Mode.equals("Accounting Codes"))
				{
					code1Name = _driver.findElement(By.xpath("//table[@id='JobBillingAccountingCode']/tbody/tr[1]/td[2]/div")).getText().trim();
					code1Value = _driver.findElement(By.xpath("//table[@id='JobBillingAccountingCode']/tbody/tr[1]/td[3]/div")).getText().trim();
					System.out.println("Code Name is: "+code1Name+ " and Code Value is: "+code1Value);
				}

				String Code2 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code2");
				if(code1Name.equals("Code2") && code1Value.equals(Code2))
				{
					System.out.println("Code 2 value is correct");
				}
				else
				{
					Assert.fail("Fail");
				}
				
				if(sDSF_Payment_Mode.equals("Accounting Codes"))
				{
					code2Name = _driver.findElement(By.xpath("//table[@id='JobBillingAccountingCode']/tbody/tr[2]/td[2]/div")).getText().trim();
					code2Value = _driver.findElement(By.xpath("//table[@id='JobBillingAccountingCode']/tbody/tr[2]/td[3]/div")).getText().trim();
					System.out.println("Code Name is: "+code2Name+ " and Code Value is: "+code2Value);
				}

				String Code1 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code1");
				if(code2Name.equals("Code 1") && code2Value.equals(Code1))
				{
					System.out.println("Code 1 value is correct");
				}
				else
				{
					Assert.fail("Fail");
				}
				
				
				//==========================================================================
				// ========//Verify the quantity ordered==============
				//==========================================================================
				System.out.println("========Verify Job Parts==============");
				//boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
				System.out.println("Click on Parts Info Tab");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
				Thread.sleep(2000);
				boolean jobPartNames = false;
				String totalRecordsParts = _driver.findElement(By.xpath("//fieldset[@id='JobPart_N1002F_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalParts: "+totalRecordsParts);
				if (totalRecordsParts.equals("1"))
				{
					
					String qty1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[8]/div")).getText();
			
					System.out.println("qty1: " +qty1);
		
					boolean partQuantity;
					String sQnty = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
					if(qty1.equals(sQnty))
					{
						partQuantity = true;
						System.out.println("partQuantity: " +partQuantity);
						System.out.println("Job Quantity has been verified");
					}
					else
					{
						Assert.fail("Job qty could not be verified");
						System.err.println("Job Quantity has been verified and failed");
					}

					//==========================================================================
					// ========//Verify the Job Part Name==============
					//==========================================================================
					
					String sProd = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					//Check that the description of part 1 is the same as the Job Name
					String part1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[6]/input")).getAttribute("value");
					//CommonFunctions.GetValue(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[6]/input"));
					System.out.println("part1Name: "+part1Name);

					Thread.sleep(1000);

					if(part1Name.contains(sProd) )
					{
						jobPartNames = true;
						System.out.println("jobPartNames: " +jobPartNames);
						System.out.println("Job Part Names have been verified");
					}
					else
					{
						System.err.println("jobPartNames: " +jobPartNames);
						Assert.fail("jobPartNames: " +jobPartNames);
					}
					Thread.sleep(1000);

					//==========================================================================
					// //Verify that the Job Part is mapped to its appropriate Job Product
					//==========================================================================				

					String pro1 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//div[@class='table-contents']/table[@id='JobPart_N1002F']/tbody/tr[2]/td[4]/select")).replace("[", "").replace("]", "").trim();
					System.out.println("pro1: "+pro1);
					boolean check1;
					//Part 1 will always be Kit123 and so will be the product
					if(part1Name.contains(pro1))
					{
						check1 = true;
						System.out.println("All the part belongs to the same product");
					}
					else
					{
						System.err.println("All the part belongs to the same product failed");
					}
				}
				else
				{
					System.err.println("Total Parts created are incorrect");
				}

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//Test end

	@Test
	public void TC_8() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_8";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String Product2Name=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String product2_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String oddPageFile=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "File");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CostCenterAccNo=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CostCenterAccNo");
				String PurchaseOrderNo=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PurchaseOrderNo");
				
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				bwf.click_on_Add_Files_And_Upload_Files();

                us.Auto_IT_Upload_MultipleFiles(oddPageFile);
                
                bwf.click_On_Upload_File_Button();
                
	  			bwf.Click_on_Done_Button();
	  			
	  			bwf.selectPrintColor(sSheetName, sScenario, sTestcase);
	  			
	  			bwf.selectOrientation(sSheetName, sScenario, sTestcase);
	  			
	  			bwf.Addto_Cart();
	  			
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

			   //Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
			   //Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.chooseCostCenterPaymentMethod(PaymentMethod);
				
				bwf.enter_CostCsnterAccountInfoAndPurchaseOrderNum(CostCenterAccNo, PurchaseOrderNo);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//test end
	
	
	@Test
	public void TC_9() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_9";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String Product2Name=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String product2_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String oddPageFile=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "File");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CostCenterAccNo=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CostCenterAccNo");
				String PurchaseOrderNo=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PurchaseOrderNo");
				

				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				bwf.click_on_Add_Files_And_Upload_Files();

                us.Auto_IT_Upload_MultipleFiles(oddPageFile);
                
                bwf.click_On_Upload_File_Button();
                
	  			bwf.Click_on_Done_Button();
	  			
	  			bwf.Addto_Cart();
	  			
	  			bwf.changeQty_From_Cart(product_qty);
	  			
	  			//Adding another product to cart
	  			bwf.Search_Product_smart_store_Npp(Product2Name, product2_qty);
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

				//Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
			   //Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.chooseCostCenterPaymentMethod(PaymentMethod);
				
				bwf.enter_CostCsnterAccountInfoAndPurchaseOrderNum(CostCenterAccNo, PurchaseOrderNo);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//test end
	
	
	@Test
	public void TC_10() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_10";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String Product2Name=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String product2_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String oddPageFile=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "File");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String CreditCardNumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "CreditCardNumber");
				String ExpirationMonth=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationMonth");
				String ExpirationYear=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ExpirationYear");
				

				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				bwf.click_on_Add_Files_And_Upload_Files();

                us.Auto_IT_Upload_MultipleFiles(oddPageFile);
                
                bwf.click_On_Upload_File_Button();
                
	  			bwf.Click_on_Done_Button();
	  			
	  			bwf.Addto_Cart();
	  			
	  			bwf.clickon_iAgreeLink();
	  			
	  			bwf.changeQty_From_Cart(product_qty);
	  			
	  			//Adding another product to cart
	  			bwf.Search_Product_smart_store_Npp(Product2Name, product2_qty);
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

				//Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
			   //Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_CreditCardPaymentPethod(PaymentMethod);
				
				bwf.enterTheCreditCardNumber(CreditCardNumber);
				
				bwf.SelectExpirationMonthAndYear(ExpirationMonth, ExpirationYear);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//test end
	
	
	@Test
	public void TC_11() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_11";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try
			{
				//==================================================Data required=================================================
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String PONumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PONumber");
				String Width=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Width");
				String Height=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Height");
				String BackCoverOption=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "BackCoverOption");
				String FrontCoverOption=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "FrontCoverOption");
				
				String PaceURL=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEURL");
				String PaceUserN=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEUserName");
				String PacePass=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEPassword");
				String PaceCompany=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACECompany");
				String PACEServer=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PACEServer");
				
				//====================================Place an Order in DSF with adhoc product======================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				//All Constructors
				Login Login1 = new Login(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);			

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				bwf.enterFinalHeight_Width(Width, Height);
				
				bwf.selectBackCoverFirstLevelOption(BackCoverOption);
				
				bwf.selectFrontCoverFirstLevelOption(FrontCoverOption);
				
				bwf.selectOrientation(sSheetName, sScenario, sTestcase);
	  		
				bwf.Addto_Cart();
	  			
	  		    bwf.changeQty_From_Cart(product_qty);
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

				//Select a delivery method
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
			   //Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_PO_PaymentMethod_Enter_PO_number(PaymentMethod, PONumber);
				
				//place order
				bwf.click_on_PlaceMyOrder();
				String orderNum=bwf.fetch_OrderNumber();
				System.out.println("DSF Order No is ::"+orderNum);
				
				//get billing details				
				String subTotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
				String ShippingAmount_Before_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
				String TotalValue_Before_code_apply =	 _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();
				String TaxCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Tax_Charges))).getText();
				String HandlingCharge_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Handling_Charges))).getText();
				System.out.println("Shipping amount before Discount::"+ShippingAmount_Before_code_apply);
				System.out.println("SubTotal Value Before Discount::"+subTotalValue_Before_code_apply);
				System.out.println("Total Value Before Discount::"+TotalValue_Before_code_apply);
				System.out.println("Tax Charge Before Discount::"+TaxCharge_Before_code_apply);
				System.out.println("Handling Charge Before Discount::"+HandlingCharge_Before_code_apply);
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal",subTotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount",ShippingAmount_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTotalAmt",TotalValue_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount",TaxCharge_Before_code_apply);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount",HandlingCharge_Before_code_apply);			
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);
				
				
				//========================================== verify Job in Pace =============================================================				
				String sJobNumber="";
				
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(PaceURL);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//_driver = eyes.open(_driver, "PACE", "TC_11_New", new RectangleSize(1024, 768));
				
				DCPage DC = new DCPage(_driver);
				DSF_Integration_Page DSF = new DSF_Integration_Page (_driver);
				JobPlanningPage JP = new JobPlanningPage(_driver);
				WorkflowIntegrationPage WIP = new WorkflowIntegrationPage(_driver);
				
				System.out.println("Loging in to Pace");
				DC.EpaceLogin(PaceUserN, PacePass, PaceCompany);
				
				//==========================================================================
				//======================Fetch Job Number using order number=================
				//==========================================================================				
				System.out.println("Fetch Job Number using order number");
				
				String orderNumber = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Order_Number");
				String sPaceConnect = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaceConnect");
				
				DSF.NavigateToActivePaceConnect(sPaceConnect, PACEServer, PaceCompany);
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Successful Executions']"));
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(1000);
				String sResultMessage = "";
				do
				{
					Thread.sleep(4000);
					_driver.navigate().refresh();
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(4000);
					sResultMessage = CommonFunctions.GetText(_driver, By.xpath("//table[@id='SuccessResults']/tbody/tr[1]/td[7]/div"));
				} while (!(sResultMessage.contains("Success : Created Job#") && sResultMessage.contains("for DSF Order#"+orderNumber)));				
				
				sJobNumber = DSF.FetchJobNumber(orderNumber);
				if(!sJobNumber.equals(""))
				{
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "PACE_Job_Num", sJobNumber);
				}
				else
				{
					Assert.fail("Job Not Created for above Order Number");
				}
								
				//==========================================================================
				//================================Verify Job Products======================
				//==========================================================================
				System.out.println("Verify Job Products");
				
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
				TakeScreenshot.ScreenShotWindow(_driver, "ProductsPage");
				Thread.sleep(2000);
				
				String totalRecordsProducts = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText();
				System.out.println("totalProducts: "+totalRecordsProducts);
				if (totalRecordsProducts.equals("2"))
				{
					String product1Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[1]/td[3]/input")).getAttribute("value");
					String product2Name = _driver.findElement(By.xpath("//div[@class='table-contents']/table[@id='JobProducts']/tbody/tr[2]/td[3]/input")).getAttribute("value");
					String sPro1 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
					String sPro2 = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");

					if((product1Name.equals(sPro1) || product1Name.equals(sPro2)) && (product2Name.equals(sPro1) || product2Name.equals(sPro2)) && !product1Name.equals(product2Name))
					{
						System.out.println("Job Product Names have been verified");
					}
					else
					{
						System.out.println("Product 1 = "+product1Name+", Product  = "+product2Name);
					}
				}
				else
				{
					System.err.println("Total Products created are incorrect, Expected = 2, Actual = "+totalRecordsProducts);
					Assert.fail("Total Products created are incorrect, Expected = 2, Actual = "+totalRecordsProducts);
				}
				
				//==========================================================================
				//==========Verify Job Value, Shipping, Handling and Taxes Amount in Billing Tab==========
				//==========================================================================
				System.out.println("Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab");
				
				String sJobValue = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFSubTotal");
				String sTax = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFTaxAmount");
				String sShipping = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFShippingAmount");
				String sHandling = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DSFHandlingAmount");
				
				boolean IsBillingDetailsVerified = DSF.Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(sJobValue, sTax, sShipping, sHandling);
				
				if(IsBillingDetailsVerified==true)
				{
					System.out.println("Verified Job Value, W2P Shipping, W2P Handling and W2P Taxes field is not editable in Billing Tab");
				}
				else
				{
					Assert.fail("Failed to Verify Job Value, W2P Shipping, W2P Handling and W2P Taxes Amount in pace. IsNotEditableSuccess is "+IsBillingDetailsVerified);
				}
				
				System.out.println("Verify payment method in pace for DSF order");
				String sPaymentMode="PO Number";
				
				String[] PaymentOption_And_Value= DSF.Verify_The_Payment_Mode_In_Pace();
				if(PaymentOption_And_Value[0].equals(sPaymentMode))
				{
					System.out.println("The Payment Mode displayed are correct");
				}
				else
				{
					System.err.println("Payment Method, Expected = PO Number, Actual= "+sPaymentMode);
					Assert.fail("Payment Method, Expected = PO Number, Actual= "+sPaymentMode);
				}
				
				//==========================================================================
				//===================Verify the Job Parts created===========================
				//==========================================================================
				System.out.println("Verify Job Parts");
				
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
				TakeScreenshot.ScreenShotWindow(_driver, "JobPartsInfo");
				
				int iNumofJobPartsCreated = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr"));				
				if (iNumofJobPartsCreated == 4)
				{
					System.out.println("Iterate through each part adn verify Job Part Details");
					
					for (int i=1; i<=iNumofJobPartsCreated; i++)
					{
						CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[3]/div/a/img"));
						CommonFunctions.waitForPageLoaded(_driver);
						CommonFunctions.Wait(_driver, By.name("additionalDescription"));
						
						//verify General details
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='G']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']"), "GeneralDetails_Part"+i);
												
						//verify Material details						
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='M']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//fieldset[contains(@id, 'JobMaterial')]")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//fieldset[contains(@id, 'JobMaterial')]"), "JobMaterialDetails_Part"+i);
						
						//verify pre-press operations
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='P']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//fieldset[contains(@id, 'JobPartPrePressOp')]")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//fieldset[contains(@id, 'JobPartPrePressOp')]"), "PrepressOperations_Part"+i);
						
						//verify press info
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='r']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//fieldset[contains(@id, 'JobPartPressForm')]")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//fieldset[contains(@id, 'JobPartPressForm')]"), "JobPartPressForm_Part"+i);
						
						if (CommonFunctions.RowCount(_driver, By.xpath("//table[contains(@id,'JobPartPressForm')]/tbody/tr")) > 0)
						{							
							String sOriginalWindow = _driver.getWindowHandle();
							CommonFunctions.ClickElement(_driver, By.xpath("//table[contains(@id,'JobPartPressForm')]/tbody/tr[1]/td[2]/div/a/img"));
							Thread.sleep(3000);
							CommonFunctions.SwitchToWindow(_driver, "Job Part Press Form");
							_driver.manage().window().maximize();
							Thread.sleep(1000);
							((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']")));
							TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@id='scrollableContent']/div[@id='contents']/div[@class='active']"), "JPPFDetails_Part"+i);
							_driver.close();
							_driver.switchTo().window(sOriginalWindow);							
						}
						
						//verify finishing operations
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='F']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//fieldset[contains(@id, 'JobPartFinishingOp')]")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//fieldset[contains(@id, 'JobPartFinishingOp')]"), "JobPartFinishingOpe_Part"+i);
						
						//verify pace connect content files
						CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
						((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//fieldset[contains(@id, 'JobPartContentFile')]")));
						TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//fieldset[contains(@id, 'JobPartContentFile')]"), "PaceConnectContentFiles_Part"+i);
					}					
				}
				else
				{
					System.err.println("Total Job Parts created are incorrect, Expected = 4, Actual = "+iNumofJobPartsCreated);
					Assert.fail("Total Job Parts created are incorrect, Expected = 4, Actual = "+iNumofJobPartsCreated);
				}
				
				//==========================================================================
				//===================Verify the Job Plans created===========================
				//==========================================================================
				System.out.println("Navigate to Job Plan Production page");
				
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job : "+sJobNumber+"']"));				
				Thread.sleep(1000);
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Production Plan']"));
				CommonFunctions.waitForPageLoaded(_driver);
				CommonFunctions.Wait(_driver, By.xpath("//a[text()='Production Plan']"));
				
				System.out.println("Verify Job Plans");				
				int iJobPlanCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr"));
				if(iJobPlanCnt == 12)
				{
					String sJobPlanIDs = JP.getJobPlans();
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "JobPlanIDs", sJobPlanIDs);
				}
				else
				{
					System.err.println("Total Job Plans created are incorrect, Expected = 12, Actual = "+iJobPlanCnt);
					Assert.fail("Total Job Plans created are incorrect, Expected = 12, Actual = "+iJobPlanCnt);
				}			
				
				System.out.println("Verify the Job is scheduled");
				if (WIP.verifyJobPlansScheduledCheckBox(12))
				{
					System.out.println("Job was schedueld");
				}
				else
				{
					System.err.println("Job was not scheduled");
					Assert.fail("Job was not scheduled");
				}
				
				//==========================================================================
				//===================Verify the Job shipment created========================
				//==========================================================================
				System.out.println("Navigate to Job Shipment Detail page");
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job : "+sJobNumber+"']"));				
				Thread.sleep(1000);
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Shipments']"));
				CommonFunctions.waitForPageLoaded(_driver);
				CommonFunctions.Wait(_driver, By.name("shipmentTypefieldIteration0"));
				
				System.out.println("Verify Job Shipment details");
				if (CommonFunctions.RowCount(_driver, By.xpath("//div[contains(@id, 'shipmentBox')]")) == 1)
				{
					CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='shipmentBox0']/div[1]/span/div[1]//img[@title='View Object']"));
					CommonFunctions.waitForPageLoaded(_driver);
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Delete)));
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='General Shipment']"));
					TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@class='active']"), "JobShipmentDetails_TC11");
					
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Shipment Address']"));
					TakeScreenshot.ScreenShotRegion(_driver, By.xpath("//div[@class='active']"), "JobShipmentContactDetails_TC11");
										
					String sJobShipmentID = CommonFunctions.getIDfromURL(_driver);
					dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "JobShipmentID", sJobShipmentID);
				}
				else
				{
					System.err.println("No Job shipment was created");
					Assert.fail("No Job shipment was created");
				}			
				
			}//try end
			finally
			{
				TakeScreenshot(sTestcase);
			}
		}//if execution required

	}//test end
	
	//Note:- None of the matching print options are available on VPB of this product to select while placing an order. Compare given order url given by kiran.
	
	@Test
	public void TC_14() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_14";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String Product2Name=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2Name");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String product2_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product2_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
				String oddPageFile=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "File");
				String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
				String PONumber=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PONumber");
				String Width=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Width");
				String Height=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Height");
				
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);

				//Search a product
				bwf.searchProductAndBuy(ProductName);

				//bwf.enterFinalHeight_Width(Width, Height);
				bwf.enterVdpTextFieldValues();
				
				//Select the Radio Button
				String maleRadioButtonXpath=".//*[contains(@id,'Male')]";
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(maleRadioButtonXpath));
				
				//Enter the last text field value
				String lastTextField_Xpath=".//table[@class='form-controls ng-scope']//tbody/tr[10]//input[@name='fieldName']";
				CommonFunctions.enterText(_driver, By.xpath(lastTextField_Xpath), "hello");
				
				bwf.clickOnUpdatePreViewButtonForVDP_VerifyPdfProofGenerated();
				
				bwf.Addto_Cart();
	  			
	  		    bwf.changeQty_From_Cart(product_qty);
	  			
	  			//Proceed to check out
				bwf.Click_On_ProceedTOCheckOut();

				//Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
			   //Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_PO_PaymentMethod_Enter_PO_number(PaymentMethod, PONumber);
				
				bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//test end
	
	
	
	@Test

	public void TC_15() throws Exception
	{
		long startTime = System.nanoTime();
		//=======================================================================
		//Constant ones....
		//=======================================================================
		String sSheetName="IntegrationWF";
		String  sScenario = "WorkFlow";
		String  sTestcase = "TC_15";
		String dater = dateReturn();
		dbConnection.UpdateFunction(sSheetName,sScenario,sTestcase,"Executed_On",dater);
		//========================================================================	
		String Exec_Required= dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Execution_Required");
		if (Exec_Required.equalsIgnoreCase("Yes"))
		{

			try{

				//=====================Data required========================================	 
				String ProductName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "ProductName");
				String SiteUrl=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SiteUrl"); 
				String Server=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Server");
				String userName=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "USERNAME");
				String passWord=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PASSWORD");
				String product_qty=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Product_Qty");
				String DeliveryMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "DeliveryMethod");
			    String PaymentMethod=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PaymentMethod");
			    String code1=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code1");
				String code2=dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "code2");
				
				//Instantiate the Forefox profile
				//==========================================================================
				super.setUp(getBrowser());
				_register = PageFactory.initElements(_driver, RegisterPage.class);
				_register.launchapplication(SiteUrl);
				_driver.manage().window().maximize();
				_driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//==========================================================================
				//All Constructors
				Login Login1 = new Login(_driver);
				Users us = new Users(_driver);
				Buyyer_Work_flow bwf = new Buyyer_Work_flow();
				Storefront sfront = new Storefront(_driver);
				 
				//==========================================================================

				//Login as user
				Login1.LoginToDSF(sSheetName, sScenario, sTestcase, userName, passWord);

				//Clear Cart
				CommonFunctions.ClearCart(_driver);
				
				bwf.Search_Product_smart_store_Npp(ProductName, product_qty);
				
				bwf.Click_On_ProceedTOCheckOut();

			    //Select a deliverymethod
				sfront.choose_ShipmentMethodBasedOnValue(DeliveryMethod);
				
				//Click on proceed to payment button
				sfront.ProceedToPayment(); 
				
				//Choose the credit card payment option
				bwf.choose_DepartMentCodePayment(PaymentMethod);
				
				bwf.enterDepartmentCode(code1, code2);
				
	         	bwf.click_on_PlaceMyOrder();
				
				String orderNum=bwf.fetch_OrderNumber();
				
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "Order_Number", orderNum);
				
				bwf.End_Print_Console(sTestcase);

				long endTime = System.nanoTime();		
				long durations = (endTime - startTime);
				double durations1 = (double)durations / 1000000000.0;
				System.out.println("Execution Time in Seconds::"+new DecimalFormat("#.###").format(durations1));
				durations1 = durations1/60;	
				String ExecutionTimeInMinutes=new DecimalFormat("#.###").format(durations1);
				System.out.println("Execution Time In minutes::"+ExecutionTimeInMinutes);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "ExecutionTime",ExecutionTimeInMinutes);

			}//try end
			finally
			{
				TakeScreenshot(sTestcase); 
			}
		}//if execution required

	}//Test end
	
}
