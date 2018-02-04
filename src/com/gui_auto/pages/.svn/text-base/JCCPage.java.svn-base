package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.print.DocFlavor.STRING;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.pages.DCPage.ACOtherSettingsData;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class JCCPage implements BaseElement {

	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	private String _pageURL;
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;

	private static  String sSERVER = null;
	private static  String sCOMPANY = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();


	public  JCCPage(WebDriver driver) throws Exception 
	{  
		super();
		this._driver = driver;       
	}  

	public void navigateToPageAndWait() 
	{
		_driver.get(_pageURL);
	} 

	@Override
	public boolean onPage() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean waitForPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public String  UniqueNum()
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String  UniqueNum1()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String  UniqueNum2()
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String AcceptModalDialog()
	{
		Alert alertDialog = _driver.switchTo().alert();
		String alertText = alertDialog.getText();
		System.out.println("alertText is "+ alertText);
		alertDialog.accept();
		return alertText;
	}

	public void Add()
	{
		_driver.findElement(By.xpath("//input[@value='Add' and  @name='updateForm']")).click();
		System.out.println("Clicking the Add button");

	}	

	public Boolean AddInvoiceExtraTypeToActivityCode(String InvoiceEXtratype,String SalesCategory)throws Exception, IOException
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Settings']"));

		CommonFunctions.selectDropdownByText(_driver, By.name("invoiceExtraType"),InvoiceEXtratype);
		CommonFunctions.selectDropdownByText(_driver, By.name("salesCategory"), SalesCategory);
		if(Update())
		{
			return true;
		}
		else
		{
			return false;
		}

	}



	public void EpaceLogin(String sUN,String sPWD,String sCompany) throws Exception, IOException
	{
		System.out.println("Author: Ashish");
		assertEquals("EFI Pace Print Management System Powered by EFI: Login", _driver.getTitle());
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.EPACE_Text))).size()>0)
		{
			System.out.println("Able to see Login Text");
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Username))).sendKeys(sUN);
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Password))).sendKeys(sPWD);
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Login_Company)), sCompany);
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Submit))).click();
			Thread.sleep(2000);
			CommonFunctions.Wait(_driver,By.id(Locators.getProperty(Locators.TestMode)));
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).size() > 0)
			{ 
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).click();
				Thread.sleep(2000);
				boolean sFlag = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Welcome_Message))); 
				if(sFlag == true)
				{
					System.out.println("Able to Login Successfully");	
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					System.out.println(NewFileNamePath);
					assertEquals("Welcome to EFI Pace", _driver.getTitle());
				}
				else
				{
					System.err.println("Login Failed");		
				}
			}
			else
			{
				assertEquals("Welcome to EFI Pace", _driver.getTitle());
				boolean sFlag1= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Welcome_Message))); 
				if(sFlag1 == true)
				{
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					System.out.println(NewFileNamePath);
					assertEquals("Welcome to EFI Pace", _driver.getTitle());
					System.out.println("Able to Login Successfully");	
				}
				else
				{
					System.err.println("Login Failed");		
				}
			}
		}

		else
		{
			System.err.println("Not Able to see Login Text");
		}
	}

	public void Logout() throws Exception, IOException
	{
		System.out.println("****Logging off****");
		_driver.findElement(By.id(Locators.getProperty(Locators.Logout))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Login_Username)));
		assertEquals("EFI Pace Print Management System Powered by EFI: Login", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("Logout Page");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(NewFileNamePath);
	}

	// Verify the text - Object Added
	public boolean Object_Added() throws Exception, IOException
	{
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Object_added_text))).isDisplayed())
		{
			System.out.println ("****Object is added****");
			return true;
		}
		else
		{
			System.out.println ("****Object is not added****");
			return false;
		}
	}


	// Click on Update button
	public boolean Update() throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		System.out.println ("****Clicking the Update button****");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Updated_text))).isDisplayed())
		{
			System.out.println ("**Page is updated**");
			return true;
		}
		else
		{
			System.out.println ("**Page is not updated**");
			return false;
		}
	}

	//******************************************************************************************************	  

	//Navigate to Add a new Job page
	public void NavigateToAddNewJobPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/add");	
		//CommonFunctions.Wait(_driver, By.name("Job"));
		//assertEquals("Adding Job", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToAddNewJobPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Add New Job page appears****");
	}
	//Navigate to Add a JCC Settings page
	public void NavigateToJCCSettingsPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobControlSetup/detail/1");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("Use_Job_Projects"));
		assertEquals("Job Control Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCSettingsPage");
		System.out.println("****JCC Settings page appears****");
	}
	//Navigate to Add a Job Projects page
	public void NavigateToJobProjectsPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");	
		CommonFunctions.Wait(_driver, By.xpath("Add_New_Record"));
		assertEquals("Job Projects", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobProjectsPage");
		System.out.println("****Job Projects page appears****");
	}

	//Navigate to Find Inquiries page
	public void NavigateToFindInquiriesPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/inquiry/UserDefinedInquiry/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		assertEquals("PaceStation Inquiries - User Inquiries", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToFindInquiriesPage");
		System.out.println("****Find Inquiries page appears****");
	}

	//Navigate to Gang Jobs page
	public void NavigateToGangJobsPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/gangingList");	
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Gang']"));
		assertEquals("Ganging - Unganged Only", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToGangJobsPage");
		System.out.println("****Ganging page appears****");
	}

	//Navigate to Job Type list page
	public void NavigateToJobTypeListPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");	
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Update']"));
		assertEquals("Job Types", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobTypeListPage");
		System.out.println("****JobType list page appears****");
	}


	//******************************************************************************************************	  


	//JCC Settings Page
	public void JCCSettingPage(boolean useJobProjects, String comboJobPerType, String comboJobPerRoundingMethod) throws Exception, IOException
	{
		NavigateToJCCSettingsPage();

		if(useJobProjects == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Use_Job_Projects))).isSelected())
			{
				System.out.println("Use Job Projects is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Use_Job_Projects))).click();
				System.out.println("Checking Use Job Projects checkbox");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Use_Job_Projects))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Use_Job_Projects))).click();
				System.out.println("Disabling Use Job Projects");
			}
			else
			{

				System.out.println("Use Job Projects is already disabled");
			}
		}

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Combo_Job_Percentage_Calculation_Type)), comboJobPerType);
		System.out.println("Combo Job Percentage Calculation Type is "+comboJobPerType);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Combo_Job_Percentage_Calculation_Rounding_Method)), comboJobPerRoundingMethod);
		System.out.println("Combo Job Percentage Calculation Rounding Method is "+comboJobPerRoundingMethod);


		Update();
	}

	//JCC Settings Page only for shipments
	public void JCCSettingPageForShipment(boolean shipMaterials, boolean shipPressForms, boolean shipProofs, boolean shipComponents, boolean shipJobPartItems) throws Exception, IOException
	{
		NavigateToJCCSettingsPage();

		if(shipMaterials == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Materials))).isSelected())
			{
				System.out.println("Ship Materials is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Materials))).click();
				System.out.println("Enabling Ship Materials");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Materials))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Materials))).click();
				System.out.println("Disabling Ship Materials");
			}
			else
			{

				System.out.println("Ship Materials is already disabled");
			}
		}
		if(shipPressForms == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Press_Forms))).isSelected())
			{
				System.out.println("Ship Press Forms is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Press_Forms))).click();
				System.out.println("Enabling Ship Press Forms");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Press_Forms))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Press_Forms))).click();
				System.out.println("Disabling Ship Press Forms");
			}
			else
			{

				System.out.println("Ship Press Forms is already disabled");
			}
		}
		if(shipProofs == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Proofs))).isSelected())
			{
				System.out.println("Ship Proofs is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Proofs))).click();
				System.out.println("Enabling Ship Proofs");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Proofs))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Proofs))).click();
				System.out.println("Disabling Ship Proofs");
			}
			else
			{

				System.out.println("Ship Proofs is already disabled");
			}
		}
		if(shipComponents == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Components))).isSelected())
			{
				System.out.println("Ship Components is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Components))).click();
				System.out.println("Enabling Ship Components");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Components))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_Components))).click();
				System.out.println("Disabling Ship Components");
			}
			else
			{

				System.out.println("Ship Components is already disabled");
			}
		}
		if(shipJobPartItems == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_JobPart_Items))).isSelected())
			{
				System.out.println("Ship JobPart Items is selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_JobPart_Items))).click();
				System.out.println("Enabling Ship JobPart Items");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ship_JobPart_Items))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ship_JobPart_Items))).click();
				System.out.println("Disabling Ship JobPart Items");
			}
			else
			{

				System.out.println("Ship JobPart Items is already disabled");
			}
		}


		Update();
	}

	//Verify whether Use Job Project is visible on Add a new job page
	public boolean VerifyJobProject() throws Exception, IOException
	{
		boolean isVisible = false;
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
			//			_driver.findElement(By.name("quickJumpDropdown")).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			//			_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		boolean jobProject = CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Job_Project)));

		if(jobProject == true)
		{
			System.out.println("Job Project dropdown is visible in the page");
			isVisible = true;
		}
		else
		{
			System.out.println("Job Project dropdown is not present in the page");
			isVisible = false;
		}
		return isVisible;
	}

	//Create a New Job 
	//Assume that the user knows what all job type the system is having.
	public String CreateNewJob(String jobType) throws Exception, IOException
	{
		NavigateToAddNewJobPage();

		//String jobID = "T_" + UniqueNum1();
		//String jobID = UniqueNum1();
		//_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(jobID);
		//System.out.println("Job entered is " + jobID);
		//NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).clear();
			Thread.sleep(1000);
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			Thread.sleep(2000);
			//			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);

			//					 CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.Quick_Search_Textbox)), "");
			// _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
			Thread.sleep(3000);
			//_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			java.util.List<WebElement> sVal = _driver.findElements(By.xpath("//select[@name='jobProductType']"));

			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);


		}

		Thread.sleep(2000);
		//Assume that the system has a customer by the name HOUSE
		//  _driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		//_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		//_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);
		//_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");

		String jobID = "JOB_"+UniqueNum1();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);

		CommonFunctions.SendValue(_driver, By.name("customer"), "HOUSE");

		/*	 if(_driver.findElements(By.name("jobType")).size()>0)

			 {
				 WebElement dropDown1 = _driver.findElement(By.xpath("//select[@name='jobType']"));
				 java.util.List<WebElement> options = dropDown1.findElements(By.tagName("option"));
				 for (WebElement el : options)
				 {
		                System.out.println(el.getText());
		               if(el.getText().equals("Sheetfed Print Job")) 
		               {
		            	   el.click();
		            	   break;
		               }
		               else
		               {
		            	   CommonFunctions.selectDropdownByIndex(_driver, By.name("jobType"), 1);
							 Thread.sleep(2000);
		               }
				 }



			 }*/
		if(jobType.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), "Sheetfed Print Job");
			Thread.sleep(2000);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), jobType);
			Thread.sleep(2000); 
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");

		Thread.sleep(9000);

		// Verify that the object has been added
		// Object_Added();
		int i =0;
		System.out.println("****Object has been added and Job Detail Page appears****");
		if(_driver.getTitle().equals("Job "+jobID) && i<= 2 )
		{
			Thread.sleep(3000);
			// Fetching the Job ID from Job Detail Page
			jobID = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).getText();
			jobID=jobID.trim();
		}
		else
		{
			Thread.sleep(3000);
		}
		return jobID;
	}

	public String CreateJob(String sJobID ,String jobType) throws Exception, IOException
	{


		//String jobID = "T_" + UniqueNum1();
		//String jobID = UniqueNum1();
		//_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(jobID);
		//System.out.println("Job entered is " + jobID);
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).clear();
			Thread.sleep(1000);
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
			//			_driver.findElement(By.name("quickJumpDropdown")).sendKeys(Keys.TAB);
			//			_driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
			Thread.sleep(3000);

			// _driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			java.util.List<WebElement> sVal = _driver.findElements(By.xpath("//select[@name='jobProductType']"));

			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);


		}

		//Assume that the system has a customer by the name HOUSE
		//  _driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		// _driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		// _driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		// System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");

		String jobID = UniqueNum1();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys("");
		System.out.println("Job entered is " + jobID);

		/*	 if(_driver.findElements(By.name("jobType")).size()>0)

		 {
			 WebElement dropDown1 = _driver.findElement(By.xpath("//select[@name='jobType']"));
			 java.util.List<WebElement> options = dropDown1.findElements(By.tagName("option"));
			 for (WebElement el : options)
			 {
	                System.out.println(el.getText());
	               if(el.getText().equals("Sheetfed Print Job")) 
	               {
	            	   el.click();
	            	   break;
	               }
	               else
	               {
	            	   CommonFunctions.selectDropdownByIndex(_driver, By.name("jobType"), 1);
						 Thread.sleep(2000);
	               }
			 }



		 }*/
		if(jobType.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), "Sheetfed Print Job");
			Thread.sleep(2000);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), jobType);
			Thread.sleep(2000); 
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("Clicking the Add button");

		// Verify that the object has been added
		Object_Added();
		System.out.println("****Object has been added and Job Detail Page appears****");

		// Fetching the Job ID from Job Detail Page
		jobID = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).getText();
		System.out.println("The job id is " + jobID );

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).isDisplayed())
		{
			return jobID;
		}
		else
		{
			return "Job ID could not be generated";
		}
	}

	//Add a Job Part to a Job
	public boolean CreateJobPart(String jobID) throws Exception, IOException
	{
		boolean isAdded = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+jobID);
		Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Parts_Info_Tab))).click();
		System.out.println("Navigating to Parts Info tab");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		System.out.println("Clicking the Add New Record button");
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Adding Job Part"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Part_Description))).sendKeys("test");
			_driver.findElement(By.name(Locators.getProperty(Locators.Part_Additional_Description))).sendKeys("test_automation");
			System.out.println("Entering the Part description and Additional description as 'test' and 'test_automation'");
			//Consider that there is a Production Status by the name Open in the system
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Production_Status)), "Open");
			System.out.println("Selecting 'Open' as the production status");
			Add();
			boolean objectAdded = Object_Added();
			if(objectAdded == true)
			{
				System.out.println("Job Part has been added");
				isAdded = true;
			}
			else
			{
				System.out.println("Job Part could not be added");
				isAdded = false;
			}
		}
		else
		{
			System.out.println("Add Job Part page could not be opened");
			isAdded = false;
		}
		return isAdded; 
	}

	//Add a Job Part in a grid
	public boolean CreateJobPartInGrid(String jobID) throws Exception, IOException
	{
		boolean isAdded = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+jobID);
		Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Parts_Info_Tab))).click();
		System.out.println("Navigating to Parts Info tab");

		_driver.findElement(By.name(Locators.getProperty(Locators.Add_In_Grid))).click();
		System.out.println("Clicking the Add In Grid button");
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Part_Description_In_Grid))).sendKeys("Part 2");
		System.out.println("Entering the Second part as 'Part 2'");

		CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Production_Status_In_Grid)), "Open");
		System.out.println("Selecting 'Open' as the production status");

		boolean isUpdate = Update();

		if(isUpdate == true)
		{
			System.out.println("Job Part has been added in grid");
			isAdded = true;
		}
		else
		{
			System.out.println("Job Part could not be added in the grid");
			isAdded = false;
		}
		return isAdded;
	}	 

	//Create a Combo Job
	//Execute CreateNewJob("Combo Job") and use the job Id here 
	public boolean ComboJob(String jobID, String jobID_1, String jobID_2) throws Exception, IOException
	{
		String originalHandle = null;
		boolean isSuccess = false;
		_driver.manage().window().maximize();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		//_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+jobID_2+"?tab=4");
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+jobID_2+"?tab=5");
		System.out.println("Entering the Combo Job Detail page");
		//Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		System.out.println("############################");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record_1))).click();
		Thread.sleep(5000);
		System.out.println("Clicking the Add New Record button");
		CommonFunctions.waitForPageLoaded(_driver);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Combo Job")) 
				{
					boolean job= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Job))); 

					if (job == true)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(jobID);
						jobID = jobID.trim();
						System.out.println ("Entering the Job as "+jobID);
						_driver.findElement(By.name(Locators.getProperty(Locators.Job_Part_Key))).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						Thread.sleep(2000);
						//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Part_Key)), "P01 - ");
						System.out.println("Entering the Job Part Key");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
						System.out.println("Clicking the Add New button");
						Thread.sleep(2000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).clear();
						jobID_1 = jobID_1.trim();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(jobID_1);
						System.out.println ("Entering the Job as "+jobID_1);
						_driver.findElement(By.name(Locators.getProperty(Locators.Job_Part_Key))).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						Thread.sleep(2000);
						//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Part_Key)), "P01 - ");
						System.out.println("Entering the Job Part Key");

						//_driver.findElement(By.xpath(Locators.getProperty(Locators.Child_Job_Info))).click();
						_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
						System.out.println ("Clicking the ADD button");
						Thread.sleep(2000);
						//invLOCandBIN = ID;
						_driver.switchTo().window(originalHandle).getTitle().equals("Job "+ jobID_2);
						isSuccess = true;
					}
					else
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Job "+ jobID_2);
						System.out.println ("Could not add the jobs");
						//invLOCandBIN = null;
					}	

					System.out.println ("Jobs has been added");
					//_driver.close();	
					//_driver.switchTo().window(originalHandle).getTitle().equals("Inventory Location "+ ID);
				}
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+ jobID_2);
					//invLOCandBIN = null;			
				}
			}
		}	
		return isSuccess;
	}	 


	//Create a New Job Type 
	/*	 public String AddJobType(String jobType) throws Exception, IOException
	 {
		 String description = "T_"+UniqueNum1();

		 sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		 _driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");
		 System.out.println("****Navigating to Job Type list page****");
		 _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		 System.out.println("Clicking the Add New Record button");
		 _driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(description);
		 System.out.println("The description entered is "+description);

		 CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Jacket_Type)), jobType);
		 System.out.println("The Job Type is "+jobType);

		 boolean active = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Active)));
		 if (active == true)
		 {
			 if(_driver.findElement(By.name(Locators.getProperty(Locators.Active))).isSelected())
			 {
				 System.out.println("Active is enabled");
			 }
			 else
			 {
				 _driver.findElement(By.name(Locators.getProperty(Locators.Active))).click();
				 System.out.println("Enabling Active checkbox");
			 }
		 }

		 _driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		 boolean success = Object_Added();
		 if(success == true)
		 {
			 return description;
		 }
		 else
		 {
			 return null;
		 }
	 }	
	 */	 
	//Create a New Job Type with specific names for each of the Job Jacket
	public String AddJobType(String jobType, boolean displayPartItems) throws Exception, IOException
	{
		String description = "T_"+UniqueNum1();
		String FGdescription = "Finished Goods_"+UniqueNum1();
		String comboPressdescription = "Combo Press_"+UniqueNum1();
		String PriceListdescription = "Price List_"+UniqueNum1();
		String Printingdescription = "Printing_"+UniqueNum1();
		String QuickEntrydescription = "QuickEntry_"+UniqueNum1();
		String mailingdescription = "Mailing_"+UniqueNum1();

		boolean finishedGoods = false, comboPressSheet = false, priceList = false, printing = false, quickEntry = false, mailing = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");
		System.out.println("****Navigating to Job Type list page****");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		System.out.println("Clicking the Add New Record button");
		//_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(description);
		//System.out.println("The description entered is "+description);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Jacket_Type)), jobType);
		System.out.println("The Job Type is "+jobType);

		if(displayPartItems == true)
		{
			System.out.println("Enabling Displaying Part Items");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Part_Items))).isSelected())
			{
				System.out.println("Displaying Part Items are already selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Display_Part_Items))).click(); 
				System.out.println("Checking Displaying Part Items");
			}
		}
		else
		{
			System.out.println("Disabling Displaying Part Items");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Part_Items))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Display_Part_Items))).click(); 
				System.out.println("UnChecking Displaying Part Items");
			}
			else
			{
				System.out.println("Displaying Part Items are already Unchecked");
			}
		}

		if(jobType.equals("Finished Goods"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(FGdescription);
			System.out.println("The description entered is "+FGdescription);
			finishedGoods = true;
		}
		else if(jobType.equals("Combo Press Sheet"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(comboPressdescription);
			System.out.println("The description entered is "+comboPressdescription);
			comboPressSheet = true;
		}
		else if(jobType.equals("Price List"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(PriceListdescription);
			System.out.println("The description entered is "+PriceListdescription);
			priceList = true;
		}
		else if(jobType.equals("Printing"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(Printingdescription);
			System.out.println("The description entered is "+Printingdescription);
			printing = true;
		}
		else if(jobType.equals("Quick Entry"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(QuickEntrydescription);
			System.out.println("The description entered is "+QuickEntrydescription);
			quickEntry = true;
		}
		else if(jobType.equals("Mailing"))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(mailingdescription);
			System.out.println("The description entered is "+mailingdescription);
			mailing = true;
		}

		boolean active = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Active)));
		if (active == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Active))).isSelected())
			{
				System.out.println("Active is enabled");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Active))).click();
				System.out.println("Enabling Active checkbox");
			}
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		Thread.sleep(9000);
		boolean success = Object_Added();

		if(jobType.equals("Finished Goods") && success == true)
		{
			return FGdescription;
		}
		else if(jobType.equals("Combo Press Sheet") && success == true)
		{
			return comboPressdescription;
		}
		else if(jobType.equals("Price List") && success == true)
		{
			return PriceListdescription;
		}
		else if(jobType.equals("Printing") && success == true)
		{
			return Printingdescription;
		}
		else if(jobType.equals("Quick Entry") && success == true)
		{
			return QuickEntrydescription;
		}
		else if(jobType.equals("Mailing") && success == true)
		{
			return mailingdescription;
		}
		else
		{
			return null;
		}


	}	 

	//Duplicate a Job Type 
	public boolean DuplicateJobType(String jobType) throws Exception, IOException
	{
		String duplicateDesc = "T_"+UniqueNum1();
		boolean isDuplicate = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");
		System.out.println("****Job type List page opens****");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Description");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobType);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Job Types"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Job_Type_Duplicte))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("Clicking the duplicate button");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(duplicateDesc);
		System.out.println("The duplicate description entered is "+duplicateDesc);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click(); 
		Thread.sleep(2000);
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Duplicated_text)));


		if(sFlag==true)
		{
			System.out.println("Job has been successfully duplicated");
			isDuplicate = true; 
		}
		else
		{
			System.out.println("Job could not besuccessfully duplicated");
			isDuplicate = false;
		}
		return isDuplicate;

	}	 

	//Delete a Job Type 
	public boolean DeleteJobType(String jobType) throws Exception, IOException
	{
		boolean isDeleted = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");
		System.out.println("****Job type List page opens****");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Description");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobType);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Job Types"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
		System.out.println("Clicking the delete button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete_Popup))).click();
		System.out.println("Clicking the Delete Popup");
		Thread.sleep(2000);

		String sTitle1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle1);

		if(sTitle1.equals("Job Types"))
		{
			System.out.println("Job Type has been successfully deleted");
			isDeleted = true; 
		}
		else
		{
			System.out.println("Job Type could not be deleted");
			isDeleted = false;
		}
		return isDeleted;
	}

	//Create Job Sub type
	public boolean CreateJobSubType(String jobType) throws Exception, IOException
	{
		boolean subJobType = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobType/list");
		System.out.println("****Navigating to Job Type list page****");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Description");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobType);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		Thread.sleep(2000);
		if(_driver.getTitle().equals("JobType: "+jobType))
		{
			System.out.println("Navigated to Job Type");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sJT = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText();
				sJT =sJT.trim();
				System.out.println("sJT is "+sJT);
				if(sJT.equals(jobType))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}

		Thread.sleep(2000);

		System.out.println("Clicking the Sub Types tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Sub_Types))).click();
		System.out.println("Clicking the Add New Record button");
		int rowcount = _driver.findElements(By.xpath("//table[@id='subJobTypes']/tbody/tr")).size();

		_driver.findElement(By.name(Locators.getProperty(Locators.Add_New_Record_Sub_type))).click();
		System.out.println("Entering the sub job type as 'test sub job type'");
		rowcount=rowcount+1;
		_driver.findElement(By.xpath("//table[@id='subJobTypes']/tbody/tr["+rowcount+"]/td[3]/input")).sendKeys("test sub job type");
		System.out.println("Clicking the Update button");
		boolean isSuccess = Update();

		if (isSuccess == true)
		{
			subJobType = true;
		}
		else
		{
			subJobType = false;
		}

		return subJobType;
	}

	//Create a new Job Shipment Type
	public String ShipmentType() throws Exception, IOException
	{	 
		boolean isSuccess = false;
		String shipmentID = UniqueNum1();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ShipmentType/list");
		System.out.println("****Navigating to Job Shipment Type list page****");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(1000);
		System.out.println("Clicking the Add New Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(shipmentID);
		System.out.println("Shipment ID entered is "+shipmentID);

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shipmentID);
		System.out.println("Entering the description as 'test_automation'");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Shipment_Status)), "In Prepress");
		System.out.println("Setting the Shipment Status as 'In Prepress'");

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		isSuccess = Object_Added();

		if (isSuccess == true)
		{
			return shipmentID;
		}
		else
		{
			System.out.println("Shipment type could not be created");
			return null;
		}
	}

	public String AddNewShipmentType() throws Exception, IOException
	{	 
		boolean isSuccess = false;
		String shipmentID = UniqueNum1();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ShipmentType/list");
		System.out.println("****Navigating to Job Shipment Type list page****");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(1000);
		System.out.println("Clicking the Add New Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(shipmentID);
		System.out.println("Shipment ID entered is "+shipmentID);

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shipmentID);
		System.out.println("Entering the description as "+shipmentID);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Shipment_Status)), "In Prepress");
		System.out.println("Setting the Shipment Status as 'Open'");

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();

		isSuccess = Object_Added();

		if (isSuccess == true)
		{
			return shipmentID;
		}
		else
		{
			System.out.println("Shipment type could not be created");
			return null;
		}
	}
	//Delete the Shipment Type
	public boolean DeleteShipmentType(String shipmentID) throws Exception, IOException
	{	 
		boolean isDeleted = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ShipmentType/list");
		System.out.println("****Navigate to Job Shipment List page****");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Shipment_Search_Type)), "ID");
		System.out.println("Selecting 'ID' from dropdown");

		_driver.findElement(By.name(Locators.getProperty(Locators.Shipment_Type_Search))).sendKeys(shipmentID);
		System.out.println("searching the Shipment ID "+shipmentID);

		_driver.findElement(By.name(Locators.getProperty(Locators.Shipment_Find))).click();



		Thread.sleep(2000);
		if(_driver.getTitle().equals("Shipment Type "+shipmentID))
		{
			System.out.println("Navigated to Shipment Type");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sJT = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText();
				sJT =sJT.trim();
				System.out.println("sJT is "+sJT);
				if(sJT.equals(shipmentID))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Shipment_Type_Delete))).click();
		System.out.println("Clicking the delete button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete_Popup))).click();
		System.out.println("Clicking the delete popup button");


		String isDeletedMessage = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li")).getText();
		System.out.println("The message is "+isDeletedMessage);

		if(isDeletedMessage.equals("Item deleted."))
		{
			System.out.println("Shipment Type has been deleted");
			isDeleted = true;
		}
		else
		{
			System.out.println("Shipment Type could not be deleted");
			isDeleted = false;
		}
		return isDeleted;
	}

	//Create a new Change Order Type
	public String CreateChangeOrderType(boolean billable, boolean postAdvance) throws Exception, IOException
	{	 
		boolean isSuccess = false;
		String changeOrderDescription = "Change_Order_"+UniqueNum();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChangeOrderType/list");
		System.out.println("****Navigate to Job ChangeOrderType page****");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("Clicking the Add New Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(changeOrderDescription);
		System.out.println("Entring the Change Order description as "+changeOrderDescription);

		if(billable == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).isSelected())
			{
				System.out.println("Billable is enabled");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).click();
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
				System.out.println("Enabling Billable");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).click();
				System.out.println("Disabling Billable");
			}
			else
			{
				System.out.println("Billable is disabled");
			}
		}
		if(postAdvance == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).isSelected())
			{
				System.out.println("Post Advance is enabled");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).click();
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
				System.out.println("Enabling Post Advance");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).click();
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
				System.out.println("Disabling Post Advance");
			}
			else
			{
				System.out.println("Post Advance is disabled");
			}
		}

		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		isSuccess = Object_Added();
		if(isSuccess == true)
		{
			System.out.println("Change Order has been added");
			return changeOrderDescription;
		}
		else
		{
			System.out.println("Change Order could not be added");
			return null;
		}

	}	 

	//Add a new Job Project
	public String AddJobProject() throws Exception, IOException
	{	 
		//boolean objectAdded = false;
		String name = "JP_"+UniqueNum1();
		//NavigateToJobProjectsPage();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");	
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add_New_Record))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Adding Job Project"))
		{
			System.out.println("**Adding Job Projects page appears**");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Name))).sendKeys(name);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Code))).sendKeys("test");
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("test_automation");
		System.out.println("Entering the Name, Code and Description as "+name+" test and test_automation respectively");
		_driver.findElement(By.name(Locators.getProperty(Locators.Due_Date))).sendKeys("T");		
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.Due_Date))).sendKeys(Keys.TAB);
		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		boolean objectAdded = Object_Added();
		if(objectAdded == true)
		{
			return name;
		}
		else
		{
			return null;
		}

	}

	//Modify a Job Project
	public boolean ModifyJobProject(String name) throws Exception, IOException
	{	 
		String modifiedName = "M_"+UniqueNum1();
		//NavigateToJobProjectsPage();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("**Job Project List page appears**");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Name");
		System.out.println("Job Project search dropdown has Name populated");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(name);
		System.out.println("Entering the Job Project to search as "+name);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("Clicking the Find button");

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Job Projects"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click();
			CommonFunctions.waitForPageLoaded(_driver);
		}			 
		Thread.sleep(2000);

		String sTitle1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle1);

		if(sTitle1.equals("Job Project "+name))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Name))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Name))).sendKeys(modifiedName);
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Code))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Job_Project_Code))).sendKeys("test_automation");
			_driver.findElement(By.name(Locators.getProperty(Locators.Description))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("test");
			System.out.println("Entering the Name, Code and Description as "+name+" test_automation and test respectively");
			_driver.findElement(By.name(Locators.getProperty(Locators.Due_Date))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Due_Date))).sendKeys("T");
			_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(Keys.TAB);
			_driver.findElement(By.name(Locators.getProperty(Locators.Due_Date))).sendKeys(Keys.TAB);
		}
		else
		{
			System.out.println("Could not open the detail page of Job Project "+name);
		}

		boolean isModified = Update();
		if(isModified == true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	//Delete a Job Project
	public boolean DeleteJobProject(String name) throws Exception, IOException
	{	 

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");

		System.out.println("**Job Project List page appears**");

		boolean isDeleted = false;

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(name);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		Thread.sleep(2000);


		if(_driver.getTitle().equals("Job Project "+name))
		{
			System.out.println("Navigated to Job Project");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sJobProject = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText();
				sJobProject =sJobProject.trim();
				System.out.println("Job Project is "+sJobProject);
				if(sJobProject.equals(name))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
		System.out.println("Clicking the delete button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete_Popup))).click();
		System.out.println("Clicking the Delete Popup");
		Thread.sleep(2000);
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));

		if(sFlag == true)
		{
			System.out.println("Job Project has been successfully deleted");
			isDeleted = true; 
		}
		else
		{
			System.out.println("Job Type could not be deleted");
			isDeleted = false;
		}
		return isDeleted;

	}

	//Duplicate a Job Project
	public boolean DuplicateJobProject(String name) throws Exception, IOException
	{	 

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");

		System.out.println("**Job Project List page appears**");

		boolean isDuplicated = false;

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(name);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();


		Thread.sleep(2000);
		if(_driver.getTitle().equals("Job Project "+name))
		{
			System.out.println("Navigated to Job Project");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sJobProject = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText();
				sJobProject =sJobProject.trim();
				System.out.println("Job Project is "+sJobProject);
				if(sJobProject.equals(name))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
		System.out.println("Clicking the duplicate button");
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Duplicated_text)));
		System.out.println("The message is "+sFlag);

		if(sFlag == true)
		{
			System.out.println("Job Project has been duplicated");
			isDuplicated = true;
		}
		else
		{
			isDuplicated = false;
		}


		return isDuplicated;
	}

	public boolean AddNonBillableChangeOrderType(String description) throws Exception, IOException
	{	 
		boolean isAdded = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChangeOrderType/list");
		System.out.println("Change Order Type List page appears");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add_New_Record))).click();
		System.out.println("Clicking on Add NEw Record button");

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Adding Change Order Type"))
		{
			System.out.println("**Adding Change Order Type page appears**");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(description);
		System.out.println("The Description entered is "+description);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).click(); 
			System.out.println("Disabling Billable");
		}
		else
		{

			System.out.println("Billable is already unchecked");
		}
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).isSelected())
		{
			System.out.println("Post Advance is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).click();
			System.out.println("Enabling Post Advance");
		}


		Add();
		boolean objectAdded = Object_Added();
		if(objectAdded == true)
		{
			isAdded = true;
		}
		else
		{
			isAdded = false;
		}
		return isAdded;
	}
	//Add a new Change Order Type
	public boolean AddChangeOrderType(String description) throws Exception, IOException
	{	 
		boolean isAdded = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChangeOrderType/list");
		System.out.println("Change Order Type List page appears");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add_New_Record))).click();
		System.out.println("Clicking on Add NEw Record button");

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Adding Change Order Type"))
		{
			System.out.println("**Adding Change Order Type page appears**");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(description);
		System.out.println("The Description entered is "+description);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).isSelected())
		{
			System.out.println("Billable is already selected");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Billable_Check))).click(); 
			System.out.println("Enabling Billable");
		}
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).isSelected())
		{
			System.out.println("Post Advance is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Post_Advance))).click();
			System.out.println("Enabling Post Advance");
		}


		Add();
		boolean objectAdded = Object_Added();
		if(objectAdded == true)
		{
			isAdded = true;
		}
		else
		{
			isAdded = false;
		}
		return isAdded;
	}

	//Search Job Part
	public boolean SearchJobPart(String jobID) throws Exception, IOException
	{
		boolean jobPart = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/list");
		System.out.println("****JobPart list page appears****");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Find_Jobs)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Find_Jobs)), "All Jobs");
		System.out.println("Selecting 'All Jobs' from the dropdown");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Job");
		System.out.println("Selecting 'Job' in the Search Field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobID);
		System.out.println("Entering the Job ID to search as "+jobID);
		//Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(8000);
		if(_driver.getTitle().equals("Job "+jobID+" part 01"))
		{
			System.out.println("Navigated to Estimate");
			jobPart=true;
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='All']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sJob = _driver.findElement(By.xpath("//table[@id='All']/tbody/tr["+i+"]/td[3]/div")).getText();
				sJob =sJob.trim();
				System.out.println("sJob is "+sJob);
				if(sJob.equals(jobID))
				{
					_driver.findElement(By.xpath("//table[@id='All']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					Thread.sleep(8000);
					if(_driver.getTitle().equals("Job "+jobID+" part 01"))
					{
						System.out.println("Navigated to Estimate");
						jobPart=true;
					}
					break;
				}
			}
		}

		return jobPart;
	}

	//Add a Job Component
	//Run only after executing SearchJobPart(String jobID) method
	public boolean AddJobComponent() throws Exception, IOException
	{
		boolean jobComponentsAdded = false;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Part_Context_Menu))).click();
		System.out.println("Clicking the Context dropdown");
		Thread.sleep(2000);
		/*
		 _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Part_Components))).click();
		 System.out.println("Clicking the Components");
		 Thread.sleep(1000);
		 */
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add_New_Record))).click();
		System.out.println("Clicking on Add New Record button");
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Component")) 
				{
					boolean code= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Description))); 

					if (code == true)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("test_component");
						System.out.println ("Entering the description as test_component");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Qty_Ordered))).sendKeys("5");
						System.out.println ("Entering the quantity ordered as '5'");
						Add();
						Thread.sleep(3000);
						jobComponentsAdded = true;
					}
					else
					{
						System.out.println ("Could not add Job Component");
						jobComponentsAdded = false;
					}	

					System.out.println ("Job Component has been added");
					// _driver.close();	
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components");
				}
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components");
					jobComponentsAdded = false;			
				}
			}
		}
		return jobComponentsAdded;
	}

	//Add a Job Component in Grid
	//Run only after executing SearchJobPart(String jobID) method
	public boolean AddJobComponentInGrid() throws Exception, IOException
	{
		boolean jobComponentAddedinGrid = false;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Part_Context_Menu))).click();
		System.out.println("Clicking the Context dropdown");
		Thread.sleep(2000);

		System.out.println("Clicking the Components");
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Add_In_Grid))).click();
		System.out.println("Clicking the Add In Grid button");

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Description))).sendKeys("test_components");
		System.out.println("Entering the description as 'test_components'");

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Qty_Ordered_Grid))).sendKeys("50");
		System.out.println("Entering the quantity ordered as 50");

		boolean isUpdate = Update();
		if(isUpdate == true)
		{
			System.out.println("Job Component is Added");
			jobComponentAddedinGrid = true;
		}
		else
		{
			System.out.println("Job Component could not be added");
			jobComponentAddedinGrid = false;
		}
		return jobComponentAddedinGrid;
	}	 

	//Search and modify a Job Component
	public boolean SearchandModifyJobComponent(String jobID) throws Exception
	{
		boolean isModified = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobComponent/list");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("****JobComponent list page appears****");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Find_Jobs)), "All Jobs");
		System.out.println("Selecting 'All Jobs' from the dropdown");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Job");
		System.out.println("Selecting 'Job' in the Search Field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobID);
		System.out.println("Entering the Job ID to search as "+jobID);
		//Thread.sleep(1000);

		String originalHandle = _driver.getWindowHandle();

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("Clicking the Find button");
		//Thread.sleep(1000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Job Components - All Jobs"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass_Job_Search))).click();
		}			 
		Thread.sleep(6000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Job Component for Job Part")) 
				{	
					boolean code= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Description))); 

					if (code == true)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Description))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("modified_test_component");
						System.out.println ("Modifying the description as 'modified_test_component'");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Qty_Ordered))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Qty_Ordered))).sendKeys("1250");
						System.out.println ("Modifying the quantity ordered as '1250'");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
						Thread.sleep(2000);
						isModified = true;
					}
					else
					{
						System.out.println ("Could not open the Job Component");
						isModified = false;
					}	

					System.out.println ("Job Component has been searched and modified");
					//_driver.close();	
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components - All Jobs");
				}
				else 
				{
					System.out.println("Could not open the popup");
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components - All Jobs");
					isModified = false;			
				}
			}
		}
		return isModified;
	}

	//Delete a Job Component
	public boolean DeleteJobComponent(String jobID) throws Exception, IOException
	{
		boolean isDeleted = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobComponent/list");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("****JobComponent list page appears****");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Find_Jobs)), "All Jobs");
		System.out.println("Selecting 'All Jobs' from the dropdown");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Search_Field)), "Job");
		System.out.println("Selecting 'Job' in the Search Field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Search))).sendKeys(jobID);
		System.out.println("Entering the Job ID to search as "+jobID);
		//Thread.sleep(1000);

		String originalHandle = _driver.getWindowHandle();

		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Perform_Search))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(5000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Job Components - All Jobs"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass_Job_Search))).click(); 
		}			 
		Thread.sleep(6000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Job Component for Job Part")) 
				{	
					boolean code= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Description))); 

					if (code == true)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						System.out.println("Clicking the delete button");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete_Popup))).click();
						System.out.println("Clicking the Delete Popup");
						Thread.sleep(2000);
						isDeleted = true;
					}
					else
					{
						System.out.println ("Could not open the Job Component");
						isDeleted = false;
					}	

					System.out.println ("Job Component has been deleted");
					//_driver.close();	
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components - All Jobs");
				}
				else 
				{
					System.out.println("Could not open the popup");
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Components - All Jobs");
					isDeleted = false;			
				}
			}
		}
		return isDeleted;

	}	 


	public void NavigateToConvertEstimateToJobPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/listConvertToJob");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Estimates to convert to jobs", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToConvertEstimateToJobPage");
	}
	public void NavigateToActivityCodeList() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Activity Codes", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToActivityCodeList");
	}
	public void NavigateToJCCJobListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Jobs - Open Jobs", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCJobListPage");
	}

	public void NavigateToJCCJobStatusListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobStatus/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Job Statuses", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCJobStatusListPage");
	}

	public void NavigateToJCCJobProjectPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProject/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Job Projects", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCJobProjectPage");
	}

	public void NavigateToAddNewJobStatus() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobStatus/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Description)));
		assertEquals("Adding Job Status", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddNewJobStatus");
	}

	public void NavigateToJob(String Job) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+Job);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Description)));
		assertEquals("Job "+Job, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJob");
	}

	public void NavigateToJobPart(String Job, String JobPart) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/detail/"+Job+"%3A"+JobPart);	
		CommonFunctions.Wait(_driver, By.name("description_label"));
		assertEquals("Job "+Job+" part "+JobPart, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobPart");
	}

	public void NavigateToJobStatusList() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobStatus/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)));
		assertEquals("Job Statuses", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobStatusList");
	}
	public void NavigateToEnterNewJobQuickEntry() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/addQuickEntry");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Job)));
		assertEquals("Adding Job Part", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToEnterNewJobQuickEntry");
	}
	public void NavigateToDuplicateExistingJob() throws IOException, Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/addViaDuplicate");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		// assertEquals("Adding Job Part", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToDuplicateExistingJob");
	}

	public void NavigateToJCCShipmentTypes() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ShipmentType/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Shipment Types", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCShipmentTypes");
	}
	public void NavigateToUserInterfaceSetAdd() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/UserInterfaceSet/add");	
		CommonFunctions.Wait(_driver, By.name("productionType"));
		assertEquals("Adding User Interface Set", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToUserInterfaceSetAdd");
	}

	public void EnterJobPartDetails(String sJob,String sStatus,String sCust,String sDesc,String sJobType,String suniqueNumber) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(sJob);
		CommonFunctions.selectDropdownByText(_driver, By.name("adminStatus"), sStatus);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), sJobType);
		Thread.sleep(1000);
		_driver.findElement(By.name("promiseDate")).sendKeys(suniqueNumber);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_SalePerson)), 1);
		Thread.sleep(1000);	
		CommonFunctions.selectDropdownByIndex(_driver, By.name("csr"), 1);
		Thread.sleep(1000);
	}

	public void EnterGeneralPartInfo(String sPDesc,String sStatus,String sQty,String sFinalSizeW,String sFinalSizeH,String sNumSep,String sFlatSizeW,String sFlatSizeH) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Part_Description))).sendKeys(sPDesc);
		if( _driver.findElements(By.name(Locators.getProperty(Locators.Part_Additional_Description))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Part_Additional_Description))).sendKeys(sPDesc); 
		}
		CommonFunctions.selectDropdownByText(_driver, By.name("productionStatus"), sStatus);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Qty_Ordered))).sendKeys(sQty);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinalSizeW); 
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinalSizeH); 
		_driver.findElement(By.name(Locators.getProperty(Locators.Part_Description))).sendKeys(sNumSep); 
		_driver.findElement(By.name("flatSizeW")).sendKeys(sFlatSizeW); 
		_driver.findElement(By.name("flatSizeH")).sendKeys(sFlatSizeH); 
	}
	public void EnterDataInfo(String sSetupDate,String sDeskTopTime,String sDeskTopNote,String sProofOutTime,String sProofOutNote)
	{
		_driver.findElement(By.name("dateSetup")).sendKeys(sSetupDate);  

		_driver.findElement(By.name("act1Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act1Time")).sendKeys(sDeskTopTime);  
		_driver.findElement(By.name("act1Note")).sendKeys(sDeskTopNote);  

		_driver.findElement(By.name("act2Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act2Time")).sendKeys(sProofOutTime);  
		_driver.findElement(By.name("act2Note")).sendKeys(sProofOutNote);  

		_driver.findElement(By.name("act3Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act3Time")).sendKeys(sDeskTopTime);  
		_driver.findElement(By.name("act3Note")).sendKeys(sDeskTopNote);  

		_driver.findElement(By.name("act4Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act4Time")).sendKeys(sProofOutTime);  
		_driver.findElement(By.name("act4Note")).sendKeys(sProofOutNote);  

		_driver.findElement(By.name("act5Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act5Time")).sendKeys(sDeskTopTime);  
		_driver.findElement(By.name("act5Note")).sendKeys(sDeskTopNote);  

		_driver.findElement(By.name("act6Date")).sendKeys(sSetupDate);  
		_driver.findElement(By.name("act6Time")).sendKeys(sProofOutTime);  
		_driver.findElement(By.name("act6Note")).sendKeys(sProofOutNote); 

	}

	public void EnterInkInfo(String sDescS1,String sDescS2,String sColorsS1,String sColorsS2,String sColorsS3 )
	{
		_driver.findElement(By.name("inkDescS1")).sendKeys(sDescS1);  
		_driver.findElement(By.name("inkDescS2")).sendKeys(sDescS2);  
		_driver.findElement(By.name("colorsS1")).sendKeys(sColorsS1);  
		_driver.findElement(By.name("colorsS2")).sendKeys(sColorsS2);  
		_driver.findElement(By.name("colorsTotal")).sendKeys(sColorsS3);  
	}

	public void EnterPressAndMailInfo() throws Exception
	{
		CommonFunctions.selectDropdownByIndex(_driver, By.name("press1"),1 );
		Thread.sleep(1000);

		int runmethod =  _driver.findElements(By.name("runMethod1")).size();
		if(runmethod > 0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("runMethod1"), 1);
			Thread.sleep(1000);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//select[@name='runMethod' or @name='printRunMethod']"), 1);
			Thread.sleep(1000);
		}		 

		CommonFunctions.selectDropdownByIndex(_driver, By.name("shipToContact"), 1);
		Thread.sleep(1000);
	}


	public void EnterMiscInfo(String sQtyToMfg,String sAllowableOvers)
	{
		_driver.findElement(By.name("qtyToMfg")).sendKeys(sQtyToMfg);
		_driver.findElement(By.name("allowableOvers")).sendKeys(sAllowableOvers);
	}

	public void EnterBillInfo(String sQuotePerM,String sQtyShipped) throws Exception
	{
		CommonFunctions.selectDropdownByIndex(_driver, By.name("priceList"),1 );
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("billRate"),1 );
		Thread.sleep(1000);
		_driver.findElement(By.name("quotePerM")).clear();
		_driver.findElement(By.name("quotePerM")).sendKeys(sQuotePerM);
		_driver.findElement(By.name("qtyShippedBilling")).sendKeys(sQtyShipped);

	}

	public void AddPrePressOperations(String sJob,String sQty,String sUnitLabel,String sHour) throws Exception
	{	 
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//fieldset[@id='JobPartPrePressOp_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Part PrePress Operation")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("prepressItem"),1 );
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity))).sendKeys(sQty);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).sendKeys(sUnitLabel);
					_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).sendKeys(sHour);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				}

			}
		}

		_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	
	}


	public void AddFinishingInfo(String sJob,String sQty,String sUnit) throws Exception
	{	 
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//fieldset[@id='JobPartFinishingOp1_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Part Finishing Operation")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("finishingOperation"),1 );
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity))).sendKeys(sQty);
					_driver.findElement(By.name("units")).sendKeys(sUnit);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				}

			}
		}

		_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	
	}

	public void AddShippingInfo(String sJob,String sQty,String sUnit) throws Exception
	{	 
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//fieldset[@id='JobPartFinishingOp2_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Part Finishing Operation")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("finishingOperation"),1 );
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity))).sendKeys(sQty);
					_driver.findElement(By.name("units")).sendKeys(sUnit);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				}

			}
		}

		_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	
	}

	public void AddMaterailInfo(String sJob,String sInv,String sPlannedQty) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//fieldset[@id='JobMaterial_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(10000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Material")) 
				{

					if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Item))).size()>0)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						System.out.println(originalHandle1);
						String  sGetTitle1 = _driver.getTitle();
						System.out.println(sGetTitle1);
						_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]")).click();
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									DC.Search( sInv,"id");
									Thread.sleep(3000);
									_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/a/div")).click();
									_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
								}

							}
						}
					}

					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					_driver.findElement(By.name("plannedQuantity")).sendKeys(sPlannedQty);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				}

			}
		}
	}


	public void NavigateToAssignContact(String sJob) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/assignment/"+sJob);	
		CommonFunctions.Wait(_driver, By.name("billToJobContact"));
		assertEquals("Job Contact Assignment", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAssignContact");
	}

	public void NavigateToFindQuotes() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Quote/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Quotes", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToFindQuotes");
	}
	public void AddJobContactToBillToJobContact() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div/h4[contains(label,'Bill To Job Contact')]/following-sibling::div/div[2]")).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Contact")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("contact"),1 );
					Thread.sleep(1000);
					PO.sSelectCheckBox(true, By.name("billToBooleanCheck"));

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Contact Assignment");	

				}

			}
		}

		_driver.switchTo().window(originalHandle).getTitle().equals("Job Contact Assignment");	
	}

	public void AddNotesInJobMaterials() throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sGetTitle = _driver.getTitle();
		System.out.println(sGetTitle);

		_driver.findElement(By.xpath("//fieldset[@id='JobNote_N10130_fieldset']/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Note for Material")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AP_Contact_Department)), 1);
					Thread.sleep(1000);
					_driver.findElement(By.name("note")).sendKeys("Notes added");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);

				}

			}
		}
	}


	public void AddMaterailFromJobPartContext(String sJob,String sInv,String sPlannedQty) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
		Thread.sleep(10000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Material")) 
				{

					if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Item))).size()>0)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						System.out.println(originalHandle1);
						String  sGetTitle1 = _driver.getTitle();
						System.out.println(sGetTitle1);
						_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]")).click();
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									DC.Search( sInv,"id");
									_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/a/div")).click();
									_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
								}

							}
						}
					}

					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					_driver.findElement(By.name("plannedQuantity")).sendKeys(sPlannedQty);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part");	

				}

			}
		}
	}

	public void AddProofFromJobPartContext(String sJob,String sDesc,String sNotes,String sApprovee) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
		Thread.sleep(10000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Proof")) 
				{
					_driver.findElement(By.name("description")).sendKeys(sDesc);
					_driver.findElement(By.name("notes")).sendKeys(sNotes);
					_driver.findElement(By.xpath("//a[text()='Approvee Information']")).click();
					Thread.sleep(1000);
					_driver.findElement(By.name("approvee")).sendKeys(sApprovee);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Proofs");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Proofs");	

				}

			}
		}
	}

	public void AddProductionJobPlanFromJobPartContext() throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div[@id='contents']/div[1]/fieldset/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Plan")) 
				{
					_driver.findElement(By.name("activityCode")).sendKeys("000");
					CommonFunctions.selectDropdownByIndex(_driver, By.name("status"), 1);
					Thread.sleep(1000);


					_driver.findElement(By.name("startDate")).sendKeys("t");
					_driver.findElement(By.name("endDate")).sendKeys("t");
					_driver.findElement(By.name("plannedHours")).sendKeys("2");
					_driver.findElement(By.name("priority")).sendKeys("40");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Production Plan for Job  Part");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Production Plan for Job  Part");	

				}

			}
		}
	}


	public void AddJobCostFromJobPartContext() throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div[@id='contents']/div[2]/fieldset/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Adding Job ")) 
				{

					CommonFunctions.selectDropdownByIndex(_driver, By.name("activityCode"), 1);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Adding Job Cost");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Adding Job Cost");	

				}

			}
		}
	} 
	public void AddNotesFromJobPartContext() throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div[@id='contents']/div[1]/fieldset/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Note")) 
				{

					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AP_Contact_Department)), 1);
					Thread.sleep(1000);
					_driver.findElement(By.name("note")).sendKeys("Notes added");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Notes for job part");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Notes for job part");	

				}

			}
		}
	} 

	public void AddPOFromJobPartContext(String sVendorID) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sInv = dbConnection.ReadFunction("JCC", "JCC", "CreateInventory", "InventoryItem");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order")) 
				{

					CommonFunctions.selectDropdownByIndex(_driver, By.name("purchaseOrderType"), 1);
					Thread.sleep(1000);						
					//CommonFunctions.selectDropdownByText(_driver, By.name("orderStatus"), "Open");
					//	Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name("lineType"),"Inventory");
					Thread.sleep(1000);						
					_driver.findElement(By.name("inventoryItem")).sendKeys(sInv);
					Thread.sleep(2000);
					//						CommonFunctions.sSelectCheckBox(_driver, true, By.name("selectCustomVendorBooleanCheck"));
					//						Thread.sleep(1000);
					_driver.findElement(By.name("vendor")).sendKeys(sVendorID);
					_driver.findElement(By.name("qtyOrdered")).sendKeys("11");
					//						_driver.findElement(By.name("qtyRequired")).sendKeys("11");
					_driver.findElement(By.name("unitPrice")).sendKeys("1");
					CommonFunctions.selectDropdownByText(_driver, By.name("glAccount"), "00001-Epace Default Normal Account");
					Thread.sleep(1000);

					CommonFunctions.selectDropdownByIndex(_driver, By.name("uom"), 1);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Orders");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Orders");	

				}

			}
		}
	} 


	public void AddInvoiceFromJobPartContext() throws Exception
	{
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div[@id='contents']/div[1]/fieldset[2]/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Invoice Transaction")) 
				{

					CommonFunctions.selectDropdownByText(_driver, By.name("invoiceType"), "Invoice");
					Thread.sleep(1000);



					if(_driver.findElements(By.name("invoiceBatch2")).size()>0)
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name("invoiceBatch2"), 1);
						Thread.sleep(1000);
					}
					else
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name("invoiceBatch"), 1);
						Thread.sleep(1000);
					}

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals("Notes for job part");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Notes for job part");	

				}

			}
		}
	} 


	public void AddShipmentTypeDetails(String sId,String sDesc) throws Exception
	{
		_driver.findElement(By.name("id")).sendKeys(sId);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("status"), "In Prepress");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("freightActivityCode"), 1);
		Thread.sleep(1000);

	}


	public void EnterPartInformation(String sPartDes,String sAddDes,String sFinalSizeW,String sFinalSizeH) throws Exception
	{

		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			_driver.findElement(By.name("quickJumpDropdown")).sendKeys(Keys.TAB);
			Thread.sleep(2000);			
			//			_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProduct")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProduct"), 1);
			Thread.sleep(2000);
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Part_Description))).sendKeys(sPartDes);
		_driver.findElement(By.name(Locators.getProperty(Locators.Part_Additional_Description))).sendKeys(sAddDes);
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='o']")).click();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinalSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinalSizeH);


	}
	public void NavigateToJobPartComponent(String sJobID) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobComponent/listJobPart/"+sJobID+":01");
		System.out.println("****JobPart Component page appears****");
		Thread.sleep(5000);
	}

	public void SearchJobPartComponent() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobComponent/list");
		System.out.println("****JobPart Component page Search appears****");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
	}

	public void NavigateToAddNewShippmentsPage() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobShipment/addDirect");     

		//assertEquals("Adding Job", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Adding Job Shipment");

	}
	public void NavigateToJobReOrderListPage() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobReorder/list");     
		Thread.sleep(3000);
		assertEquals("Reordered Jobs", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Adding Job Shipment");

	}

	public void NavigateToJCCItemTemplatePage() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ItemTemplateSetup/detail/1");     
		assertEquals("Item Template Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCItemTemplatePage");
		System.out.println("****ItemTemplate Setup page appears****");           
	}

	public void NavigateToJCCItemTemplate() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ItemTemplate/list");     
		assertEquals("Item Template List - Standard", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCItemTemplate");

	}

	public void NavigateToJCCAddItemTemplateType() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ItemTemplateType/add");
		Thread.sleep(2000);
		assertEquals("Adding Item Template Type", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCAddItemTemplateType");		
	}

	public void NavigateToJCCAddItemTemplate() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ItemTemplate/add");     
		assertEquals("Adding Item Template", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCAddItemTemplate");

	}

	public void NavigateToJCCFulfillmentSettings() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/FulfillmentSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name("pickTicketId"));
		assertEquals("Fulfillment Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJCCAddItemTemplate");
		System.out.println("****Fulfillment Setup page appears****");
	}

	public void NavigateToJCCShippmentsListPage() throws Exception, IOException
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobShipment/list");   

		//assertEquals("Adding Job", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Adding Job Shipment");

	}
	public void CreateNewJCCShipment(String sJob) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(1000);

	}



	public void SetJobStatus(boolean sActive) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Status')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Status")) 
				{

					PO.sSelectCheckBox(sActive, By.name("activeBooleanCheck"));

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(2000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
	}

	public void JobStatusSetting(boolean sCheckBox,boolean sCheckBox1,boolean sCheckBox2 ) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Status')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Status")) 
				{
					_driver.findElement(By.xpath("//a[text()='Settings']")).click();

					PO.sSelectCheckBox(sCheckBox, By.name("adminToProductionBooleanCheck"));
					PO.sSelectCheckBox(sCheckBox1, By.name("adminBooleanCheck"));
					PO.sSelectCheckBox(sCheckBox2, By.name("productionBooleanCheck"));

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(2000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
	}  

	public void JobStatusProductionSetting(boolean sCheckBox,boolean sCheckBox1,boolean sCheckBox2 ) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Production Status')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Status")) 
				{
					_driver.findElement(By.xpath("//a[text()='Settings']")).click();

					PO.sSelectCheckBox(sCheckBox, By.name("adminToProductionBooleanCheck"));
					PO.sSelectCheckBox(sCheckBox1, By.name("adminBooleanCheck"));
					PO.sSelectCheckBox(sCheckBox2, By.name("productionBooleanCheck"));

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(2000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
	}   


	public boolean  AddUnpostedCosts(String sJob,int i) throws Exception
	{	 
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath("//div[@id='contents']/div[2]/fieldset/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Cost")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("activityCode"),1 );
					Thread.sleep(1000);
					if(CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//label[text()='Complete']"))))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
						Thread.sleep(1000);
					}


					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					if(i == 1)
					{
						String sText =  _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						if(sText.equals("JobCost (null): Job Cost not permitted for the following jobs because the Job Status or Planning Record does not allow charges. Change the status of the job or the configuration of the Job Status and then repost"))
						{
							System.out.println("Able to see Warning Message");
							sFlag = true;
						}
					}
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job Costs for Job "+sJob+" Part 01");	

				}

			}
		}

		_driver.switchTo().window(originalHandle).getTitle().equals("Job Costs for Job "+sJob+" Part 01");	
		return sFlag;
	}


	public void AddNewJobProject(String sName) throws Exception, IOException
	{
		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();  
		Thread.sleep(2000);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "AddingJobProject");	
		_driver.findElement(By.xpath("//div/h4[contains(label,'Name')]/following-sibling::div/Input")).sendKeys(sName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sName);
		_driver.findElement(By.name("dueDate")).sendKeys("t");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(2000);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Add");

	}

	public String NavigateToJobProjectFromJob(String sName ) throws Exception
	{
		String sJob = "";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Job Project')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Project "+sName)) 
				{
					_driver.findElement(By.xpath("//a[text()='Project Details']")).click();
					Thread.sleep(1000);
					sJob=	_driver.findElement(By.xpath("//table[@id='Job_N10039']/tbody/tr[1]/td[3]/div")).getText();
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
		return sJob;
	}   

	public void ModifyChangeOrderToNonBillable(String sName) throws Exception
	{
		String sJob = "";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Type')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Change Order Type "+sName)) 
				{
					_driver.findElement(By.name("billableBooleanCheck")).click();
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(2000);
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

	}  


	public void AddDetailsofUserInterfaceSetEstimate() throws Exception
	{
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePartAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePartAddBlank"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateQuantityDetail"), 1);
		Thread.sleep(100);

		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePaperAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePaperDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateInkDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePrepressOpAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePrepressOpDetail"), 1);
		Thread.sleep(100);


		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePressAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePressDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOpAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOpDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOpShippingAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOpShippingDetail"), 1);
		Thread.sleep(100);
	}


	public void AddDetailsofUserInterfaceSetJobDetails() throws Exception
	{
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartPressFormAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartPressFormDirectAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartFinishingOpAdd"), 1);
		Thread.sleep(100);

		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobMaterialAdd"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartPressFormDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartPressFormDirectDetail"), 1);
		Thread.sleep(100);


		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobPartFinishingOpDetail"), 1);
		Thread.sleep(100);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobMaterialDetail"), 1);
		Thread.sleep(100);

	}

	public void AddChangeOrderLineItem(By loc) throws Exception
	{
		String sJob = "";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(loc).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Adding Change Order Line")) 
				{
					_driver.findElement(By.name("unitCost")).sendKeys("2");

					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

	}  


	public void AddPart(String sDesc,String sJobType) throws Exception
	{
		_driver.findElement(By.xpath("//div[@id='contents']/div[2]/fieldset/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();

		Thread.sleep(2000);
		if(_driver.findElements(By.name("jobProduct")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProduct"), 1);
			Thread.sleep(2000);
		}

		if(CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.name("jobProductType"))))
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}


		_driver.findElement(By.name("description_label")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), sJobType);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(2000);

	}

	public boolean VerifyPartJacket(int i,String sJob ,String sPart) throws Exception
	{
		boolean sFlag=false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();

		if( _driver.findElements(By.xpath("//table[2]/tbody/tr["+i+"]/td[3]/div/a/img")).size()>0)
		{
			_driver.findElement(By.xpath("//table[2]/tbody/tr["+i+"]/td[3]/div/a/img")).click();
		}
		else
		{
			_driver.findElement(By.xpath("//table[2]/tbody/tr["+i+"]/td[3]/div/a/div")).click();
		}
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{

				String sTitle = "Job "+sJob+" part "+sPart;
				System.out.println("sTitle is "+sTitle);
				if(_driver.switchTo().window(windowId).getTitle().equals(sTitle)) 
				{
					System.out.println("Verify Part Jacket is Present");

					sFlag = CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='Part Jacket']"));
					System.out.println("sFlag is "+sFlag);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}


	public void EnterItemTemplateDetails(String sCode,String sDesc,String sItemTemplateType,String sBaseObject,String sNote,int sJobProType,int sSalePerson,String sEst,String sQuote, String sUnitW,String qtyOption) throws Exception
	{
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("itemTempformlateType"), sItemTemplateType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("baseObject"), sBaseObject);
		Thread.sleep(1000);
		_driver.findElement(By.name("note")).sendKeys(sNote);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), sJobProType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("salesCategory"), sSalePerson);
		Thread.sleep(1000);
		_driver.findElement(By.name("estimate")).sendKeys(sEst);
		_driver.findElement(By.name("quote")).sendKeys(sQuote);
		_driver.findElement(By.name("unitWeight")).sendKeys(sUnitW);
		CommonFunctions.selectDropdownByText(_driver, By.name("qtyOptions"), qtyOption);
		Thread.sleep(1000);
		_driver.findElement(By.name("jobDescription")).sendKeys(sItemTemplateType);
	}

	public void AddItemTemplateLineItem(By loc,String sDataObject) throws Exception
	{
		_driver.findElement(By.xpath("//div[@id='contents']/div[3]/fieldset/div[1]/div[1]/div[2]/input")).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver,loc, sDataObject);
		Thread.sleep(1000);

	}

	public void AddItemTemplateType(String sCode,String sDesc,boolean sNewPart) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		PO.sSelectCheckBox(sNewPart, By.name("newPartBooleanCheck"));
	}

	public void PickAttribute() throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//a[text()='Pick Attributes']")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Adding Item Template Attributes")) 
				{
					_driver.findElement(By.xpath("//input[@value='Select Required']")).click();
					Thread.sleep(3000);
					_driver.findElement(By.xpath("//input[@value='Add Attributes']")).click();
					Thread.sleep(3000);

					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
	}


	public void PickAttributeSelectAttribute(String sAtt) throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//a[text()='Pick Attributes']")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				CommonFunctions.Wait(_driver, By.id("attributes"));
				if(_driver.getTitle().contains("Adding Item Template Attributes")) 
				{
					int sliCount = _driver.findElements(By.xpath("//div[@id='attributes']/div/ul/li/ul/li")).size();
					System.out.println("sliCount is "+sliCount);
					for(int i = 1 ; i<=sliCount;i++)
					{
						String sAttribute = _driver.findElement(By.xpath("//div[@id='attributes']/div/ul/li/ul/li["+i+"]/a")).getText();
						if(sAttribute.equals(sAtt))
						{
							_driver.findElement(By.xpath("//div[@id='attributes']/div/ul/li/ul/li["+i+"]/a")).click();
							Thread.sleep(1000);
							break;
						}
					}
					_driver.findElement(By.xpath("//input[@value='Add Attributes']")).click();
					Thread.sleep(3000);

					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}
	}

	public String CreateNewJobWithItemTemplate(String sItemTemplate) throws Exception, IOException
	{

		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).clear();
			Thread.sleep(1000);
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			Thread.sleep(1000);
			//			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
			//				 _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
			Thread.sleep(3000);

			// _driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		String jobID = "T_" + UniqueNum1();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");


		_driver.findElement(By.xpath("//a[text()='Part Info']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//fieldset[@id='jobparts_fieldset']/div[1]/div[1]/div[2]/input")).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='jobparts']/tbody/tr[1]/td[2]/select"), sItemTemplate);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(7000);
		CommonFunctions.waitForPageLoaded(_driver);

		System.out.println("****Object has been added and Job Detail Page appears****");

		// Fetching the Job ID from Job Detail Page
		jobID = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).getText();
		jobID=jobID.trim();
		System.out.println("The job id is " + jobID );

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).isDisplayed())
		{
			return jobID;
		}
		else
		{
			return "Job ID could not be generated";
		}
	}

	public void NavigateToJobPartNotes(String sJobId) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/notes/"+sJobId+"%3A01");	
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Job Part Notes']"));
		assertEquals("Notes for job part", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobPartNotes");
	}

	public void NavigateTo5457PaceConnect() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PaceConnect/detail/5457");	
		// CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Pace Connect 5457 - Printable Get Inventory Levels", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPaceAllConnect");
	}
	public void NavigateToPaceAllConnect() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PaceConnect/list");	

		assertEquals("Pace Connects", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPaceAllConnect");
	}


	public void NavigateToSharedInventoryValidation() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItem/sharedInventoryValidation");	
		//   CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Shared Inventory Validation", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToSharedInventoryValidation");
	}

	public void AddPrintableGetInventoryLevelsConnect() throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("type2"), "Printable Get Inventory Levels");
		_driver.findElement(By.name("name")).sendKeys("printable inventory");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Printable Setup']"));
		_driver.findElement(By.xpath("//a[text()='Printable Setup']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.name("supplierID")).sendKeys("1");
		//_driver.findElement(By.name("printableInventoryToken")).sendKeys("1");

	}



	public void ItemTemplateJobJobPartDetails() throws Exception
	{
		String sAttribute="";
		/*	if(_driver.findElements(By.xpath("//a[@id='group_controller_options_upToQuantity']/img")).size()>0)
			{
				_driver.findElement(By.xpath("//a[@id='group_controller_options_upToQuantity']/img")).click();
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[5]/a")).click();
			}*/
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("billRate"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("iif(ancestor::Customer/@billRate != 0,ancestor::Customer/@billRate, 2)");

			}
			else if(sAttribute.equals("colorsS1"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("4");
			}
			else if(sAttribute.equals("colorsS2"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("4");
			}
			else if(sAttribute.equals("colorsTotal"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("4");
			}
			else if(sAttribute.equals("gangable"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("invoiceW2PHandlingAmount"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("invoiceW2POrderAmount"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("invoiceW2PShippingAmount"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("invoiceW2PTaxAmount"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("productionStatus"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("O");
			}
		}

	}
	public void ItemTemplateJobPartJobCommissionDistribution(String sID,String sRate) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("commissionRate"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sRate);

			}
			else if(sAttribute.equals("salesPerson"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sID);
			}
		}
	}

	public void ItemTemplateJobPartProof(String sApprover,String sComments,String sDesc,String sNotes,String sStatus) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("approvee"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sApprover);

			}
			else if(sAttribute.equals("comments"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sComments);
			}
			else if(sAttribute.equals("description"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);
			}
			else if(sAttribute.equals("notes"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}
			else if(sAttribute.equals("status"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sStatus);
			}

		}
	}

	public void ItemTemplateJobChargeOrderType(String sDept,String sDesc,String sNotes,String sEnteredBy,String sType) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("billed"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("false");

			}
			else if(sAttribute.equals("department"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDept);
			}
			else if(sAttribute.equals("description"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);
			}
			else if(sAttribute.equals("notes"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}
			else if(sAttribute.equals("enteredBy"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sEnteredBy);
			}
			else if(sAttribute.equals("type"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sType);
			}
		}
	}

	public void ItemTemplateJobShipment(String sShipmentType,String sName,String sWeight,String sCost) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("shipmentType"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sShipmentType);

			}
			else if(sAttribute.equals("name"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sName);
			}
			else if(sAttribute.equals("weight"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sWeight);
			}
			else if(sAttribute.equals("cost"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sCost);
			}

		}
	}
	public void ItemTemplateJobPartPrePressOP(String sHours,String sDesc,String sNumOut,String sPrepressItem,String sQty,String sSizeH,String sSizeW) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("description"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);

			}
			else if(sAttribute.equals("hours"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sHours);
			}
			else if(sAttribute.equals("numOut"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumOut);
			}
			else if(sAttribute.equals("prepressItem"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPrepressItem);
			}
			else if(sAttribute.equals("quantity"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQty);
			}
			else if(sAttribute.equals("sizeHeight"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sSizeH);
			}
			else if(sAttribute.equals("sizeWidth"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sSizeW);
			}
		}
	}


	public void ItemTemplateJobPartItem(String sAdjustValue,String sFinalValue,String sName,String sNotes,String sQtyOrdered,String sQtyShipped,String sQuoteItemType,String sUnitPrice) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("adjustValue"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAdjustValue);

			}
			else if(sAttribute.equals("finalPrice"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sFinalValue);
			}
			else if(sAttribute.equals("name"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sName);
			}
			else if(sAttribute.equals("notes"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}
			else if(sAttribute.equals("qtyOrdered"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQtyOrdered);
			}
			else if(sAttribute.equals("qtyShipped"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQtyShipped);
			}
			else if(sAttribute.equals("shipmentType"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQuoteItemType);
			}
			else if(sAttribute.equals("unitPrice"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sUnitPrice);
			}
		}
	}
	public void ItemTemplateJobPart_JobPartOutsidePurch(String sAC,String sDesc,String sNotes,String sOPM,String sQty,String sSetupCost,String sTotalCost ,String sUnitPrice,String sUom) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("activityCode"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAC);

			}
			else if(sAttribute.equals("description"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);
			}
			else if(sAttribute.equals("notes"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}
			else if(sAttribute.equals("outsidePurchaseMarkup"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sOPM);
			}
			else if(sAttribute.equals("quantity"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQty);
			}
			else if(sAttribute.equals("setupCost"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sSetupCost);
			}
			else if(sAttribute.equals("totalCost"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sTotalCost);
			}
			else if(sAttribute.equals("unitPrice"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sUnitPrice);
			}
			else if(sAttribute.equals("uom"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sUom);
			}
		}
	}
	public void ItemTemplateJobJobProduct(String sAddDesc,String sDesc,String sInv,String sProductVa,String sQtyOr,String sQtyShi,String sQtyMfg,String sR1,String sR2) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("additionalDescription"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAddDesc);

			}
			else if(sAttribute.equals("description"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);
			}
			else if(sAttribute.equals("inventoryItem"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sInv);
			}
			else if(sAttribute.equals("productValue"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sProductVa);
			}
			else if(sAttribute.equals("qtyOrdered"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQtyOr);
			}
			else if(sAttribute.equals("qtyShipped"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQtyShi);
			}
			else if(sAttribute.equals("qtyToMfg"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQtyMfg);
			}
			else if(sAttribute.equals("reference1"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sR1);
			}
			else if(sAttribute.equals("reference2"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sR2);
			}
			else if(sAttribute.equals("singleWebDelivery"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}
			else if(sAttribute.equals("inventoryItem"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sInv);
			}
		}
	}

	public void ItemTemplateJobJobPartJobPartPressForm(String sS1,String sS2,String sTotal,String sDesc,String sFormNum,String sMRH,String sMRS,String sNote,String sPSheet,String sOtherHours,String sNumPlates,String sNumPasses,String sNumAlong,String sNumAcross,String sPress) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("colorsSide1"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sS1);

			}
			else if(sAttribute.equals("colorsSide2"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sS2);
			}
			else if(sAttribute.equals("colorsTotal"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sTotal);
			}
			else if(sAttribute.equals("description"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);
			}

			else if(sAttribute.equals("formNum"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sFormNum);
			}

			else if(sAttribute.equals("makeReadyHours"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sMRH);
			}
			else if(sAttribute.equals("makeReadySheets"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sMRS);
			}

			else if(sAttribute.equals("note"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNote);
			}
			else if(sAttribute.equals("numAcross"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumAcross);
			}
			else if(sAttribute.equals("numAlong"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumAlong);
			}
			else if(sAttribute.equals("numPasses"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumPasses);
			}
			else if(sAttribute.equals("numPlates"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumPlates);
			}

			else if(sAttribute.equals("otherHours"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sOtherHours);
			}
			else if(sAttribute.equals("pressSheets"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPSheet);
			}
			else if(sAttribute.equals("press"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPress);
			}
			else if(sAttribute.equals("secondWeb"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");
			}

		}
	}
	public void ItemTemplateJobJobContact(String sBillTo,String sShipTo,String sNotes,String sCustomer) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("billTo"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sBillTo);

			}
			else if(sAttribute.equals("customer"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sCustomer);
			}
			else if(sAttribute.equals("shipTo"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sShipTo);
			}
			else if(sAttribute.equals("notes"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}

		}
	}
	public void ItemTemplateJobPartJobCMaterail(String sInv,String sPlannedQty) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("fromEstimating"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("true");

			}
			else if(sAttribute.equals("inventoryItem"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sInv);
			}
			else if(sAttribute.equals("plannedQuantity"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPlannedQty);
			}

		}
	}

	public void ItemTemplateJobPartJobFinishingOp(String sFinOp,String sNote,String sNumPasses,String sNumUp,String sQty,String sUnits) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("finishingOperation"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sFinOp);

			}
			else if(sAttribute.equals("note"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNote);
			}
			else if(sAttribute.equals("numPasses"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumPasses);
			}
			else if(sAttribute.equals("numUp"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNumUp);
			}
			else if(sAttribute.equals("quantity"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQty);
			}
			else if(sAttribute.equals("units"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sUnits);
			}
		}
	}
	public void ItemTemplateJobPartJobReOrder(String sAdd1,String sAdd2,String sAdd3,String sComp,String sCity,String sCustomer,String sPhoneN,String sZip,String sQty) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("address1"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAdd1);

			}
			if(sAttribute.equals("address2"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAdd2);

			}
			if(sAttribute.equals("address3"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAdd3);

			}
			else if(sAttribute.equals("city"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sCity);
			}
			else if(sAttribute.equals("companyName"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sComp);
			}
			else if(sAttribute.equals("customer"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sCustomer);
			}
			else if(sAttribute.equals("phone"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPhoneN);
			}
			else if(sAttribute.equals("zip"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sZip);
			}
			else if(sAttribute.equals("quantity"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sQty);
			}

		}
	}


	public void ItemTemplateJobPartJobPlan(String sAC,String sDate,String sEstimateHours,String sNotes,String sPlannedHours,String sPrio) throws Exception
	{
		String sAttribute="";
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();

			sAttribute=sAttribute.trim();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("activityCode"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sAC);

			}
			else if(sAttribute.equals("endDate"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "XPath Expression");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDate);
			}
			else if(sAttribute.equals("endTime"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "XPath Expression");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDate);
			}
			else if(sAttribute.equals("estimatedHours"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sEstimateHours);
			}
			else if(sAttribute.equals("note"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sNotes);
			}
			else if(sAttribute.equals("plannedHours"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPlannedHours);
			}
			else if(sAttribute.equals("priority"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sPrio);
			}
			else if(sAttribute.equals("startDate"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "XPath Expression");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDate);
			}
			else if(sAttribute.equals("startTime"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "XPath Expression");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDate);
			}
		}
	}
	public void ItemTemplateJobJobPartJobComponentDetails(String sDesc) throws Exception
	{
		String sAttribute="";
		/*	if(_driver.findElements(By.xpath("//a[@id='group_controller_options_upToQuantity']/img")).size()>0)
			{
				_driver.findElement(By.xpath("//a[@id='group_controller_options_upToQuantity']/img")).click();
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[5]/a")).click();
			}*/
		int rowcount = _driver.findElements(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr")).size();
		System.out.println("rowcount is "+rowcount);
		for(int i =2;i<=rowcount;i++)	   
		{
			sAttribute = _driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[3]/div")).getText();
			System.out.println("sAttribute is "+sAttribute);
			if(sAttribute.equals("description"))
			{

				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys(sDesc);

			}
			else if(sAttribute.equals("colorsS1"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("4");
			}
			else if(sAttribute.equals("colorsS2"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("0");
			}
			else if(sAttribute.equals("colorsTotal"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("4");
			}
			else if(sAttribute.equals("finalSizeH"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("14");
			}
			else if(sAttribute.equals("finalSizeW"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("12");
			}
			else if(sAttribute.equals("freightAmt"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("15");
			}
			else if(sAttribute.equals("numSigs"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("2");
			}
			else if(sAttribute.equals("qtyOrdered"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("10");
			}
			else if(sAttribute.equals("qtyToMfg"))
			{
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).clear();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[5]/select"), "Static Value");
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//table[@id='ItemTemplateLineAttribute']/tbody/tr["+i+"]/td[6]/div/textarea")).sendKeys("5");
			}
		}

	}

	public boolean VerifyItemTemplateJobPartDetails(String sItemTemp) throws Exception
	{
		String sItemTemplate= _driver.findElement(By.xpath("//div/h4[contains(label,'Item Template')]/following-sibling::div/div[1]/a")).getText();
		sItemTemplate=sItemTemplate.trim();

		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='B']")).click();
		Thread.sleep(1000);
		boolean invoiceW2PHandlingAmount = _driver.findElement(By.name("invoiceW2POrderAmountBooleanCheck")).isSelected();
		boolean invoiceW2POrderAmount = _driver.findElement(By.name("invoiceW2PTaxAmountBooleanCheck")).isSelected();
		boolean invoiceW2PShippingAmount = _driver.findElement(By.name("invoiceW2PShippingAmountBooleanCheck")).isSelected();
		boolean invoiceW2PTaxAmount = _driver.findElement(By.name("invoiceW2PHandlingAmountBooleanCheck")).isSelected();

		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='I']")).click();
		Thread.sleep(1000);
		String sS1= _driver.findElement(By.name("colorsS1")).getAttribute("value");sS1=sS1.trim();
		String sS2= _driver.findElement(By.name("colorsS2")).getAttribute("value");sS2=sS2.trim();
		String stotal= _driver.findElement(By.name("colorsTotal")).getAttribute("value");stotal=stotal.trim();

		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='r']")).click();
		Thread.sleep(1000);
		boolean sGangable= _driver.findElement(By.name("gangableBooleanCheck")).isSelected();
		System.out.println("sItemTemplate = "+sItemTemplate+" sS1 = "+sS1+" sS2 ="+sS2+" stotal = "+stotal);
		System.out.println("invoiceW2PHandlingAmount = "+invoiceW2PHandlingAmount+" invoiceW2POrderAmount = "+invoiceW2POrderAmount+" invoiceW2PShippingAmount ="+invoiceW2PShippingAmount+" invoiceW2PTaxAmount = "+invoiceW2PTaxAmount+" sGangable ="+sGangable);
		if(sItemTemplate.equals(sItemTemp) && sS1.equals("4") && sS2.equals("4") && stotal.equals("4") &&invoiceW2PHandlingAmount==true && invoiceW2POrderAmount== true && invoiceW2PShippingAmount==true && invoiceW2PTaxAmount==true && sGangable==true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	public void CreateNewItemTemplateType(String sCode,String sDesc,boolean sNewPart,boolean sVarible) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		PO.sSelectCheckBox(sNewPart, By.name("newPartBooleanCheck"));
		PO.sSelectCheckBox(sVarible, By.name("variableBooleanCheck"));
	}

	public boolean VerifyItemTemplateJobPartCommissionDetails(String sName,String sRate) throws Exception
	{
		boolean sFlag = false;
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='B']")).click();
		Thread.sleep(2000);
		String sSalesPerson =CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='CustomerCommissionDistribution']/tbody/tr[1]/td[2]/select"));
		String sCommissionRate = _driver.findElement(By.xpath("//table[@id='CustomerCommissionDistribution']/tbody/tr[1]/td[3]/input")).getAttribute("value");
		if(sSalesPerson.equals(sName) && sCommissionRate.equals(sRate))
		{
			System.out.println("Verified JobPart Commission Distribution Tempalte");
			sFlag=true;
		}

		return sFlag;
	}

	public boolean VerifyItemTemplateJobPartMaterialsDetails(String sInventory,String sQty) throws Exception
	{
		boolean sFlag = false;
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='M']")).click();
		Thread.sleep(1000);
		System.out.println("Verify Job Part Materials Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Material")) 
				{
					System.out.println("Fetch the details");
					String sInv = _driver.findElement(By.name("inventoryItem")).getAttribute("value");sInv=sInv.trim();
					String sPlannedQty = _driver.findElement(By.name("plannedQuantity")).getAttribute("value");sPlannedQty=sPlannedQty.trim();
					boolean sEstimate =_driver.findElement(By.name("fromEstimatingBooleanCheck")).isSelected();
					System.out.println("sInv = "+sInv+" sPlannedQty = "+sPlannedQty);


					if(sInv.equals(sInventory) && sPlannedQty.equals(sQty) && sEstimate==true)						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;
	}

	public boolean VerifyItemTemplateJobPartFinishingOperationDetails(String sFinOp,String sQty,String sUnits,String sNum,String sNumPass,String sNote) throws Exception
	{
		boolean sFlag = false;
		CommonFunctions.Wait(_driver, By.xpath("//div[@id='tabBar']//span[text()='F']"));
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='F']")).click();
		Thread.sleep(1000);


		System.out.println("Verify Job Part Job Plan Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='JobPartFinishingOp']/tbody/tr[3]/td[4]/div/a/img")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Part Finishing Operation")) 
				{
					System.out.println("Fetch the details");
					String sFinOper = _driver.findElement(By.xpath("//div/h4[contains(label,'Finishing Operation')]/following-sibling::div/div[1]/a")).getText();sFinOper=sFinOper.trim();
					String sQty1 = _driver.findElement(By.name("quantity")).getAttribute("value");sQty1=sQty1.trim();
					String sUnit = _driver.findElement(By.name("units")).getAttribute("value");sUnit=sUnit.trim();
					String sNumup = _driver.findElement(By.name("numUp")).getAttribute("value");sNumup=sNumup.trim();
					String sNumPasses = _driver.findElement(By.name("numPasses")).getAttribute("value");sNumPasses=sNumPasses.trim();
					String sNotes= _driver.findElement(By.name("note")).getAttribute("value");sNotes=sNotes.trim();
					System.out.println("sFinOper = "+sFinOper+" sQty1 = "+sQty1+" sUnits is "+sUnits+" sNumup is "+sNumup+" sNumPasses is "+sNumPasses);


					if(sFinOper.equals(sFinOp) && sQty1.equals(sQty) && sUnit.equals(sUnits) && sNumup.equals(sNum) && sNumPasses.equals(sNumPass) && sNotes.equals(sNote))						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;
	}


	public boolean VerifyItemTemplateJobPartJobPlanDetails(String sJob,String sEstHour,String sAC,String sDate,String sPlannedH,String sPrio,String sNote) throws Exception
	{
		boolean sFlag = false;
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/plan/"+sJob+"%3A01");
		Thread.sleep(4000);
		System.out.println("Verify Job Part Finishing Operation Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='JobPlan']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Plan")) 
				{
					System.out.println("Fetch the details");
					String sEstimateHour = _driver.findElement(By.xpath("//div/h4[contains(label,'Estimated Hours')]/following-sibling::div/div")).getText();sEstimateHour=sEstimateHour.trim();
					String sActivityCode= _driver.findElement(By.name("activityCode")).getAttribute("value");sAC=sAC.trim();
					String sStartDate = _driver.findElement(By.name("startDate")).getAttribute("value");sStartDate=sStartDate.trim();
					String sEndDate = _driver.findElement(By.name("endDate")).getAttribute("value");sEndDate=sEndDate.trim();
					String sPlannedHour = _driver.findElement(By.name("plannedHours")).getAttribute("value");sPlannedHour=sPlannedHour.trim();
					String sPriority= _driver.findElement(By.name("priority")).getAttribute("value");sPriority=sPriority.trim();
					String sNotes= _driver.findElement(By.name("note")).getAttribute("value");sNotes=sNotes.trim();
					System.out.println("sEstimateHour = "+sEstimateHour+" sAC = "+sAC+" sStartDate is "+sStartDate+" sEndDate is "+sEndDate+" sPlannedHour is "+sPlannedHour+"sPriority is "+sPriority+" sNotes is "+sNotes);


					if(sEstimateHour.equals(sEstHour) && sActivityCode.equals(sAC) && sStartDate.equals(sDate) && sEndDate.equals(sDate) && sPlannedHour.equals(sPlannedH) && sPriority.equals(sPrio) && sNotes.equals(sNote))						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;
	}
	public void NavigateToJobPartComponentPage(String sJobId) throws IOException, Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobComponent/listJobPart/"+sJobId+"%3A01");	
		//   CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Job Components", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobPartComponentPage");
	}

	public boolean VerifyItemTemplateJobPartJobComponentDetails(String sJobId,String sDescription) throws IOException, Exception
	{
		boolean sFlag=false;
		System.out.println("Navigate to Job Part Component");
		NavigateToJobPartComponentPage(sJobId);

		System.out.println("Verify Job Component Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Job Component for Job Part")) 
				{
					System.out.println("Fetch the details");
					String sDesc = _driver.findElement(By.name("description")).getAttribute("value");sDesc=sDesc.trim();
					String sQtyOrdered = _driver.findElement(By.name("qtyOrdered")).getAttribute("value");sQtyOrdered=sQtyOrdered.trim();
					String sNumSigs = _driver.findElement(By.name("numSigs")).getAttribute("value");sNumSigs=sNumSigs.trim();
					String sFinalSizeW = _driver.findElement(By.name("finalSizeW")).getAttribute("value");sFinalSizeW=sFinalSizeW.trim();
					String sFinalSizeH = _driver.findElement(By.name("finalSizeH")).getAttribute("value");sFinalSizeH=sFinalSizeH.trim();
					String sQtyToMfg= _driver.findElement(By.name("qtyToMfg")).getAttribute("value");sQtyToMfg=sQtyToMfg.trim();
					String sS1 = _driver.findElement(By.name("colorsS1")).getAttribute("value");sS1=sS1.trim();
					String sS2 = _driver.findElement(By.name("colorsS2")).getAttribute("value");sS2=sS2.trim();
					String sTotal= _driver.findElement(By.name("colorsTotal")).getAttribute("value");sTotal=sTotal.trim();
					String sFreightAmt = _driver.findElement(By.name("freightAmt")).getAttribute("value");sFreightAmt=sFreightAmt.trim();
					System.out.println("sDesc = "+sDesc+" sS1 = "+sS1+" sS2 ="+sS2+" sTotal = "+sTotal);
					System.out.println("sQtyOrdered = "+sQtyOrdered+" sNumSigs = "+sNumSigs+" sFinalSizeW ="+sFinalSizeW+" sFinalSizeH = "+sFinalSizeH);
					System.out.println("sQtyToMfg = "+sQtyToMfg+" sFreightAmt = "+sFreightAmt);

					if(sDesc.equals(sDescription) && sQtyOrdered.equals("10") && sNumSigs.equals("2") && sFinalSizeW.equals("12") && sFinalSizeH.equals("14") && sQtyToMfg.equals("5") && sS1.equals("4") && sS2.equals("0") && sTotal.equals("4") && sFreightAmt.equals("$15.00"))						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;


	}


	public void CreateContact(String sFN,String sLN,String sComp,String sPhoneN,String Add2,String Add3,String Email,String Zip) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sComp);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(sPhoneN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(Add2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(Add3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Email))).sendKeys(Email);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Zip))).sendKeys(Zip);
		_driver.findElement(By.xpath("//a[text()='Customer/Vendor Info']")).click();
		Thread.sleep(2000);
		if(_driver.findElements(By.name("defaultCurrency")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("defaultCurrency"), 1);
			Thread.sleep(1000);
		}

	}

	public boolean VerifyItemTemplateDefaultJobReOrderDetails(String sCust,String sJob,String sCompN,String sAdd1,String sAdd2,String sAdd3,String sCity1,String sPhoneN,String sZipN)
	{
		boolean sFlag = false;
		String sCustomer = _driver.findElement(By.name("customer")).getAttribute("value");sCustomer=sCustomer.trim();
		String sJobID = _driver.findElement(By.xpath("//div/h4[contains(label,'Job')]/following-sibling::div/div[1]")).getText();
		sJobID=sJobID.trim();
		String sJobpart = _driver.findElement(By.xpath("//div/h4[contains(label,'Job')]/following-sibling::div/div[2]")).getText();
		sJobpart=sJobpart.trim();
		String sCompanyName= _driver.findElement(By.name("companyName")).getAttribute("value");sCompanyName=sCompanyName.trim();
		String sAddress1= _driver.findElement(By.name("address1")).getAttribute("value");sAddress1=sAddress1.trim();
		String sAddress2= _driver.findElement(By.name("address2")).getAttribute("value");sAddress2=sAddress2.trim();
		String sAddress3= _driver.findElement(By.name("address3")).getAttribute("value");sAddress3=sAddress3.trim();
		String sCity= _driver.findElement(By.name("city")).getAttribute("value");sCity=sCity.trim();
		String sPhone= _driver.findElement(By.name("phone")).getAttribute("value");sPhone=sPhone.trim();
		String sZip= _driver.findElement(By.name("zip")).getAttribute("value");sZip=sZip.trim();
		System.out.println("sCustomer is "+sCustomer+" sCust is "+sCust);
		System.out.println("sJobID is "+sJobID+" sJob is "+sJob);
		System.out.println("sCompanyName is "+sCompanyName+" sCompN is "+sCompN);
		System.out.println("sJobpart is "+sJobpart+" sJobpart is 01");
		System.out.println("sAddress1 is "+sAddress1+" sAdd1 is "+sAdd1);
		System.out.println("sAddress2 is "+sAddress2+" sAdd2 is "+sAdd2);
		System.out.println("sAddress3 is "+sAddress3+" sAdd3 is "+sAdd3);
		System.out.println("sCity is "+sCity+" sCity1 is "+sCity1);
		System.out.println("sPhone is "+sPhone+" sPhoneN is "+sPhoneN);
		System.out.println("sZip is "+sZip+" sZipN is "+sZipN);

		if(sCustomer.equals(sCust) && sJobID.equals(sJob) && sJobpart.equals("01") && sCompanyName.equals(sCompN) && sAddress1.equals(sAdd1) && sAddress2.equals(sAdd2) && sAddress3.equals(sAdd3) && sCity.equals(sCity1) && sPhone.equals(sPhoneN) && sZip.equals(sZipN))
		{
			sFlag = true;
		}

		return sFlag;
	}


	public boolean VerifyItemTemplateJobPartProofDetails(String sJobId,String sApprover, String sComments, String sDescrip, String sNotes,String sStatus) throws IOException, Exception
	{
		boolean sFlag=false;
		System.out.println("Navigate to jobPart->Proof");
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Proof/listJobPart/"+sJobId+"%3A01");

		System.out.println("Verify Job Component Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Proof")) 
				{
					System.out.println("Fetch the details");

					String sStatus1 = _driver.findElement(By.xpath("//div/h4[contains(label,'Status')]/following-sibling::div/div")).getText();sStatus1=sStatus1.trim();
					System.out.println("sStatus1 = "+sStatus1);
					String sDesc = _driver.findElement(By.name("description")).getAttribute("value");sDesc=sDesc.trim();
					System.out.println("sDesc = "+sDesc);
					String sNote = _driver.findElement(By.name("notes")).getAttribute("value");sNote=sNote.trim();
					System.out.println("sNote = "+sNote);
					_driver.findElement(By.xpath("//a[text()='Approvee Information']")).click();
					Thread.sleep(2000);


					String sApprovee = _driver.findElement(By.name("approvee")).getAttribute("value");sApprovee=sApprovee.trim();
					System.out.println("sApprovee = "+sApprovee);
					String sComment= _driver.findElement(By.xpath("//div/h4[contains(label,'Comments')]/following-sibling::div/div")).getText();sComment=sComment.trim();
					System.out.println("sComment = "+sComment);
					if(sDesc.equals(sDescrip) && sNote.equals(sNotes) && sApprovee.equals(sApprover) && sComments.equals(sComment) && sStatus1.equals(sStatus)  )						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}

	public boolean  VerifyItemTemplateJobProductDetails(String sAddDesc, String sDesc, String sInv, String sProductVa1, String sQtyOr, String sQtyShi, String sQtyMfg, String sR1, String sR2)
	{
		boolean sFlag=false;
		String sDesc1= _driver.findElement(By.name("description")).getAttribute("value");
		System.out.println("sDesc1 is "+sDesc1);
		String sAddDesc1= _driver.findElement(By.name("additionalDescription")).getAttribute("value");
		System.out.println("sAddDesc1 is "+sAddDesc1);
		String sRef1= _driver.findElement(By.name("reference1")).getAttribute("value");
		System.out.println("sRef1 is "+sRef1);
		String sRef2= _driver.findElement(By.name("reference2")).getAttribute("value");
		System.out.println("sRef2 is "+sRef2);
		String sQtyOrdered= _driver.findElement(By.name("qtyOrdered")).getAttribute("value");
		System.out.println("sQtyOrdered is "+sQtyOrdered);
		String sQtyToMfg= _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("sQtyToMfg is "+sQtyToMfg);
		String sQtyShipped = _driver.findElement(By.name("qtyShipped")).getAttribute("value");
		System.out.println("sQtyShipped is "+sQtyShipped);
		String sProductValue= _driver.findElement(By.name("productValue")).getAttribute("value");
		System.out.println("sProductValue is "+sProductValue);
		boolean sSingleWebDelivery = _driver.findElement(By.name("singleWebDelivery")).isSelected();
		if(sSingleWebDelivery == true && sDesc1.equals(sDesc) && sAddDesc1.equals(sAddDesc) && sRef1.equals(sR1) && sRef2.equals(sR2) && sQtyOrdered.equals(sQtyOr) && sQtyToMfg.equals(sQtyMfg) && sQtyShipped.equals(sQtyShi) && sProductValue.equals(sProductVa1))
		{
			sFlag=true;
		}
		return sFlag;
	}


	public boolean VerifyItemTemplateJobContactDetails(String sCustomer,String sNotes1) throws IOException, Exception
	{
		boolean sFlag=false;

		System.out.println("Verify Job Contact Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();

		System.out.println("Navigate to jobb->jobContact");
		int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("rowCount is "+rowCount);
		for(int i = 1;i<=rowCount;i++)
		{

			String sJobC = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]/div/a")).getText();
			sJobC =sJobC.trim();
			System.out.println("sJobC is "+sJobC);
			if(sJobC.equals("HOUSE - Internal Customer"))
			{
				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
				break;
			}
		}


		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Job Contact ")) 
				{
					System.out.println("Fetch the details");

					String sCustomer1 = _driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/div[1]/a")).getText();sCustomer1=sCustomer1.trim();
					System.out.println("sCustomer1 = "+sCustomer1);
					boolean sShipTo = _driver.findElement(By.name("shipToBooleanCheck")).isSelected();
					System.out.println("sShipTo = "+sShipTo);
					boolean sBillTo = _driver.findElement(By.name("billToBooleanCheck")).isSelected();
					System.out.println("sBillTo = "+sBillTo);

					String sNotes = _driver.findElement(By.name("notes")).getAttribute("value");sNotes=sNotes.trim();
					System.out.println("sNotes = "+sNotes);

					if(sCustomer1.equals(sCustomer) && sShipTo==true && sBillTo==true && sNotes.equals(sNotes1)   )						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}

	public boolean VerifyItemTemplateJobJobPartJobPartPressFormDetails(String sDesc1,String sForm1,String sPasses1,String sNumAlong1,String sNumAcross1,String S1,String S2,String sTotal1,String sMRH1,String sMRS1,String sOtherHours1,String sPressSheets1,String sNotes1,String sPress1) throws IOException, Exception
	{
		boolean sFlag=false;

		System.out.println("Verify Job Contact Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();

		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Job Part Press Form")) 
				{
					System.out.println("Fetch the details");

					String sDesc= _driver.findElement(By.name("description")).getAttribute("value");
					System.out.println("sDesc is "+sDesc+" sDesc1 is "+sDesc1);

					String sForm = _driver.findElement(By.name("formNum")).getAttribute("value");
					System.out.println("sForm is "+sForm+" sForm1 is "+sForm1);

					String sPasses= _driver.findElement(By.name("numPasses")).getAttribute("value");
					System.out.println("sPasses is "+sPasses+" sPasses1 is "+sPasses1);

					String sNumAlong= _driver.findElement(By.name("numAlong")).getAttribute("value");
					System.out.println("sNumAlong is "+sNumAlong+" sNumAlong1 is "+sNumAlong1);

					String sNumAcross= _driver.findElement(By.name("numAcross")).getAttribute("value");
					System.out.println("sNumAcross is "+sNumAcross+" sNumAcross1 is "+sNumAcross);

					String sS1= _driver.findElement(By.name("colorsSide1")).getAttribute("value");
					System.out.println("sS1 is "+sS1+" S1 is "+S1);

					String sS2= _driver.findElement(By.name("colorsSide2")).getAttribute("value");
					System.out.println("sS2 is "+sS2+" S2 is "+S2);

					String sTotal= _driver.findElement(By.name("colorsTotal")).getAttribute("value");
					System.out.println("sTotal is "+sTotal+" sTotal1 is "+sTotal1);

					String sMRH= _driver.findElement(By.name("makeReadyHours")).getAttribute("value");
					System.out.println("sMRH is "+sMRH+" sMRH1 is "+sMRH1);

					String sMRS= _driver.findElement(By.name("makeReadySheets")).getAttribute("value");
					System.out.println("sMRS is "+sMRS+" sMRS1 is "+sMRS1);

					String sOtherHours= _driver.findElement(By.name("otherHours")).getAttribute("value");
					System.out.println("sOtherHours is "+sOtherHours+" sOtherHours1 is "+sOtherHours);

					String sPressSheets= _driver.findElement(By.name("pressSheets")).getAttribute("value");
					System.out.println("sPressSheets is "+sPressSheets+" sPressSheets1 is "+sPressSheets1);

					String sNotes= _driver.findElement(By.name("note")).getAttribute("value");
					System.out.println("sNotes is "+sNotes+" sNotes1 is "+sNotes1);

					String sPress= CommonFunctions.GetSelectedOption(_driver, By.name("press"));
					Thread.sleep(1000);
					System.out.println("sPress is "+sPress+" sPress1 is "+sPress1);

					boolean sSecondWeb=_driver.findElement(By.name("secondWebBooleanCheck")).isSelected();

					//String sDesc1,String sForm1,String sPasses1,String sNumAlong1,String sNumAcross1,String S1,String S2,String sTotal1,String sMRH1,String sMRS1,String sOtherHours1,String sPressSheets1,String sNotes1,String sPress1)
					if(sSecondWeb==true && sDesc.equals(sDesc1) && sForm.equals(sForm1) && sPasses.equals(sPasses1) &&  sS1.equals(S1) && sS2.equals(S2) && sTotal.equals(sTotal1) && sMRH.equals(sMRH1) && sMRS.equals(sMRS1) && sOtherHours.equals(sOtherHours1) && sPressSheets.equals(sPressSheets1) && sNotes.equals(sNotes1) && sPress.equals(sPress1))

					{
						sFlag= true;
					}
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}


	public boolean  VerifyItemTemplateJobChargeOrderDetails(String sDept,String sDesc,String sNotes,String sEnteredBy,String sType) throws Exception
	{
		boolean sFlag=false;


		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);
		String sDepartment =  CommonFunctions.GetSelectedOption(_driver, By.name("department"));
		Thread.sleep(1000);
		System.out.println("sDepartment is "+sDepartment);
		String sType1=  CommonFunctions.GetSelectedOption(_driver, By.name("type"));
		Thread.sleep(1000);
		System.out.println("sType1 is "+sType1);
		String sEnteredBy1= _driver.findElement(By.name("enteredBy")).getAttribute("value");
		System.out.println("sEnteredBy1 is "+sEnteredBy1);
		String sNote= _driver.findElement(By.name("notes")).getAttribute("value");
		System.out.println("sNote is "+sNote);
		String sDesc1= _driver.findElement(By.name("description")).getAttribute("value");
		System.out.println("sDesc1 is "+sDesc1);
		boolean sBilled= _driver.findElement(By.name("billedBooleanCheck")).isSelected();

		if(sBilled == false && sDepartment.equals(sDept) && sNote.equals(sNotes) && sType1.equals(sType) && sEnteredBy1.equals(sEnteredBy) && sDesc1.equals(sDesc))
		{
			sFlag=true;
		}
		return sFlag;
	}

	public boolean VerifyItemTemplateJobPartPurchaseOutsideDetails(String sDesc1,String sSetupCost1,String sUnitPrice1,String sQty1,String sOutSidePM1,String sNotes1,String sTotalCost1,String sAC1,String sUOM1) throws IOException, Exception
	{
		boolean sFlag=false;
		System.out.println("Navigate to BuyOut");
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='u']")).click();
		Thread.sleep(2000);
		System.out.println("Verify Job Contact Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();

		System.out.println("Navigate to job->jobpart->buyout");
		_driver.findElement(By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Job Part Buy Out")) 
				{
					System.out.println("Fetch the details");

					String sDesc= _driver.findElement(By.name("description")).getAttribute("value");sDesc=sDesc.trim();
					System.out.println("sDesc = "+sDesc+" sDesc1 is "+sDesc1);

					String sSetupCost= _driver.findElement(By.name("setupCost")).getAttribute("value");sSetupCost=sSetupCost.trim();
					System.out.println("sSetupCost = "+sSetupCost+" sSetupCost1 is "+sSetupCost);

					String sUnitPrice= _driver.findElement(By.name("unitPrice")).getAttribute("value");sUnitPrice=sUnitPrice.trim();
					System.out.println("sUnitPrice = "+sUnitPrice+" sUnitPrice1 is "+sUnitPrice1);

					String sQty= _driver.findElement(By.name("quantity")).getAttribute("value");sQty=sQty.trim();
					System.out.println("sQty = "+sQty+" sQty1 is "+sQty1);

					String sOutSidePM= _driver.findElement(By.name("outsidePurchaseMarkup")).getAttribute("value");sOutSidePM=sOutSidePM.trim();
					System.out.println("sOutSidePM = "+sOutSidePM+" sOutSidePM1 is "+sOutSidePM1);

					String sNotes= _driver.findElement(By.name("notes")).getAttribute("value");sNotes=sNotes.trim();
					System.out.println("sNotes = "+sNotes+" sNotes1 is "+sNotes1);

					String sTotalCost = _driver.findElement(By.xpath("//div/h4[contains(label,'Total Cost')]/following-sibling::div/div")).getText();sTotalCost=sTotalCost.trim();
					System.out.println("sTotalCost = "+sTotalCost+" sTotalCost1 ="+sTotalCost1);

					String sAC = CommonFunctions.GetSelectedOption(_driver, By.name("activityCode"));
					Thread.sleep(1000);
					String sUOM = CommonFunctions.GetSelectedOption(_driver, By.name("uom"));
					Thread.sleep(1000);

					if(sDesc.equals(sDesc1) && sSetupCost.equals(sSetupCost1) && sUnitPrice.equals(sUnitPrice1) && sQty.equals(sQty1) && sOutSidePM.equals(sOutSidePM1) && sNotes.equals(sNotes1) &&  sTotalCost.equals(sTotalCost1) && sAC.equals(sAC1) && sUOM.equals(sUOM1))						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}


	public boolean VerifyItemTemplateJobPartPrePressOP(String sDesc1,String sHours1,String sNumout1,String sQty1,String sSizeW1,String sSizeH1,String sPrepressItem1,String sState1) throws IOException, Exception
	{
		boolean sFlag=false;
		System.out.println("Navigate to BuyOut");
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='P']")).click();
		Thread.sleep(2000);
		System.out.println("Verify Job Contact Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();

		System.out.println("Navigate to job->jobpart->PrepressOP");
		_driver.findElement(By.xpath("//table[@id='JobPartPrePressOp_N100C6']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Job Part Pre Press Operation")) 
				{
					System.out.println("Fetch the details");

					String sDesc= _driver.findElement(By.name("description")).getAttribute("value");sDesc=sDesc.trim();
					System.out.println("sDesc = "+sDesc+" sDesc1 is "+sDesc1);

					String sHours= _driver.findElement(By.name("hours")).getAttribute("value");sHours=sHours.trim();
					System.out.println("sHours = "+sHours+" sHours1 is "+sHours1);

					String sNumout= _driver.findElement(By.name("numOut")).getAttribute("value");sNumout=sNumout.trim();
					System.out.println("sNumout = "+sNumout+" sNumout1 is "+sNumout1);

					String sQty= _driver.findElement(By.name("quantity")).getAttribute("value");sQty=sQty.trim();
					System.out.println("sQty = "+sQty+" sQty1 is "+sQty1);

					String sSizeW= _driver.findElement(By.name("SizeW")).getAttribute("value");sSizeW=sSizeW.trim();
					System.out.println("sSizeW = "+sSizeW+" sSizeW1 is "+sSizeW1);

					String sSizeH= _driver.findElement(By.name("SizeH")).getAttribute("value");sSizeH=sSizeH.trim();
					System.out.println("sSizeH = "+sSizeH+" sSizeH1 is "+sSizeH1);


					String sPrepressItem = CommonFunctions.GetSelectedOption(_driver, By.name("prepressItem"));
					Thread.sleep(1000);
					System.out.println("sPrepressItem = "+sPrepressItem+" sPrepressItem1 is "+sPrepressItem);
					String sState= CommonFunctions.GetSelectedOption(_driver, By.name("state"));
					Thread.sleep(1000);
					System.out.println("sState = "+sState+" sState1 is "+sState1);

					if(sDesc.equals(sDesc1) && sHours.equals(sHours1) && sNumout.equals(sNumout1) && sQty.equals(sQty1) && sSizeW.equals(sSizeW1) && sSizeH.equals(sSizeH1) &&  sPrepressItem.equals(sPrepressItem1) && sState.equals(sState1) )						
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}

	public boolean  VerifyItemTemplateJobShipmentDetails(String sShipmentType1,String sContact1,String sWeight,String sCost) throws Exception
	{
		boolean sFlag=false;


		_driver.findElement(By.xpath("//div[@id='shipmentBox0']/div[2]/span/div[1]/div[2]/fieldset/legend[text()='Details']")).click();
		Thread.sleep(5000);



		String sContact =  CommonFunctions.GetSelectedOption(_driver, By.name("jobContactfieldIteration0")).trim();
		Thread.sleep(1000);
		System.out.println("sContact is "+sContact+"sContact1 is "+sContact1);

		String sShipmentType=  CommonFunctions.GetSelectedOption(_driver, By.name("shipmentTypefieldIteration0")).trim();
		Thread.sleep(1000);
		System.out.println("sShipmentType is "+sShipmentType+" sShipmentType1 is "+sShipmentType1);

		String sCost1= _driver.findElement(By.name("costfieldIteration0")).getAttribute("value").trim();
		System.out.println("sCost1 is "+sCost1+" sCost is "+sCost);
		String sWeight1= _driver.findElement(By.name("weightfieldIteration0")).getAttribute("value").trim();
		System.out.println("sWeight1 is "+sWeight1+" sWeight is "+sWeight);

		if(sContact.equals(sContact1) && sShipmentType.equals(sShipmentType1) && sCost1.equals(sCost) && sWeight1.equals(sWeight) )
		{
			sFlag=true;
		}
		return sFlag;
	}


	public boolean VerifyItemTemplateJobPartItem(String sAdjustValue,String sFinalPrice,String sName,String sNotes,String sQtyOrdered,String sQtyShipped,String sQuoteItemType,String sUnitPrice) throws IOException, Exception
	{
		boolean sFlag=false;
		System.out.println("Navigate to BuyOut");
		_driver.findElement(By.xpath("//div[@id='tabBar']//span[text()='P']")).click();
		Thread.sleep(2000);
		System.out.println("Verify Job Contact Details");
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sGetTitle = _driver.getTitle();

		System.out.println("Navigate to job->jobpart->PrepressOP");
		_driver.findElement(By.xpath("//table[@id='JobPartPrePressOp_N100C6']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(5000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Part Item")) 
				{
					System.out.println("Fetch the details");

					String sName1= _driver.findElement(By.name("name")).getAttribute("value");sName1=sName1.trim();
					System.out.println("sName1 = "+sName1+" sName is "+sName);

					String sQtyOrdered1= _driver.findElement(By.name("qtyOrdered")).getAttribute("value");sQtyOrdered1=sQtyOrdered1.trim();
					System.out.println("sQtyOrdered1 = "+sQtyOrdered1+" sQtyOrdered is "+sQtyOrdered);

					String sUnitPrice1= _driver.findElement(By.name("unitPrice")).getAttribute("value");sUnitPrice1=sUnitPrice1.trim();
					System.out.println("sUnitPrice1 = "+sUnitPrice1+" sUnitPrice is "+sUnitPrice);

					String sFinalPrice1= _driver.findElement(By.name("finalPrice")).getAttribute("value");sFinalPrice1=sFinalPrice1.trim();
					System.out.println("sFinalPrice1 = "+sFinalPrice1+" sFinalPrice is "+sFinalPrice);

					String sAdjustValue1= _driver.findElement(By.name("adjustValue")).getAttribute("value");sAdjustValue1=sAdjustValue1.trim();
					System.out.println("sAdjustValue1 = "+sAdjustValue1+" sAdjustValue is "+sAdjustValue);

					String sNotes1= _driver.findElement(By.name("notes")).getAttribute("value");sNotes1=sNotes1.trim();
					System.out.println("sNotes1 = "+sNotes1+" sNotes is "+sNotes);


					String sQuoteItemType1 = CommonFunctions.GetSelectedOption(_driver, By.name("quoteItemType"));
					Thread.sleep(1000);
					String sQtyShipped1 = _driver.findElement(By.xpath("//div/h4[contains(label,'Qty Shipped')]/following-sibling::div/div")).getText();sQtyShipped1=sQtyShipped1.trim();
					System.out.println("sQtyShipped1 = "+sQtyShipped1+" sQtyShipped ="+sQtyShipped);

					System.out.println("sQuoteItemType = "+sQuoteItemType+" sQuoteItemType1 is "+sQuoteItemType1);
					if(sName1.equals(sName) && sQtyOrdered1.equals(sQtyOrdered) && sUnitPrice1.equals(sUnitPrice) && sFinalPrice1.equals(sFinalPrice) && sAdjustValue1.equals(sAdjustValue) && sNotes1.equals(sNotes) && sQuoteItemType1.equals(sQuoteItemType) && sQtyShipped1.equals(sQtyShipped))
					{
						sFlag=true;
					}

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);	

				}

			}
		}

		return sFlag;

	}


	public void AddPressFrom(String Form, String Press, String Plate, String Description) throws Exception
	{
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("usePressFormsBooleanCheck"));
		CommonFunctions.Wait(_driver, By.xpath("//fieldset[@id='JobPartPressForm_N1018D_fieldset']//a[contains(text(), 'Add New')]"));

		String  originalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobPartPressForm_N1018D_fieldset']//a[contains(text(), 'Add New')]"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Job Part Press Form")) 
				{
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Form)), Form);
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Run)), "1");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Passes)), "1");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Quantity)), "1000");

					if (!Press.equals(""))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PressForm_Press)), Press);
					}
					else
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PressForm_Press)), 1);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.PressForm_Plate))).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.PressForm_Press))).sendKeys(Keys.TAB);
					Thread.sleep(2000);

					if (!Plate.equals(""))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PressForm_Plate)), Plate);
					}
					else
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PressForm_Plate)), 1);
					}
					Thread.sleep(1000);
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Description)), Description);
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_RunSizeWidth)), "25");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_RunSizeHeight)), "38");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Pages)), "16");
					_driver.findElement(By.name(Locators.getProperty(Locators.PressForm_RunSizeHeight))).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.PressForm_Pages))).sendKeys(Keys.TAB);
					Thread.sleep(2000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PressForm_FoldPattern)), "16 Pg Right Angle Booklet");
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PressForm_PrintRunMethod)), "Perfect");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_S1)), "4");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_S2)), "4");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Total)), "4");
					CommonFunctions.sSelectCheckBox(_driver, true, By.name(Locators.getProperty(Locators.PressForm_Aq)));
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PressForm_Registration)), "Tight Register");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_SheetstoPress)), "13995");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_MR)), "937");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_RunSpoilageSheets)), "558");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_OffPressQty)), "12500");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_MakeReadyHours)), "1.95");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_RunHours)), "1.73");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_WashupHours)), "0.6");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PressForm_OtherHours)), "0");
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle);
				}
				else
				{
					_driver.switchTo().window(originalHandle);
				}
			}
		}
		else
		{
			System.err.println("Add Press from window was not displayed");
			assertFalse("Unable to add press forms to job part", true);
		}
	}

	public String CreateNewJobWithJobTypeJobPlans (String JobTypeDesc) throws Exception
	{
		String jobID = UniqueNum1();

		NavigateToAddNewJobPage();
		CommonFunctions.SendValue(_driver, By.name("customer"), "HOUSE");
		_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		System.out.println("Customer entered is 'HOUSE'");

		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), jobID);
		System.out.println("Job Number entered is "+jobID);

		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job_Description)), jobID);
		System.out.println("Job Description entered is "+jobID);

		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), JobTypeDesc);
		Thread.sleep(1000);
		System.out.println("Job Type entered is "+JobTypeDesc);

		CommonFunctions.sSelectCheckBox(_driver, true, By.name(Locators.getProperty(Locators.Add_Plan_From_JobType)));
		System.out.println("Add Plan From JobType is selected");

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Job_Add)));
		System.out.println("Clicking Add button");

		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));

		if(_driver.getTitle().equals("Job "+jobID))
		{						
			jobID = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).getText();
			jobID=jobID.trim();
		}
		else
		{
			jobID = "";
		}
		return jobID;
	}

	public void AddJobTypePlan (String ActivityCode) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Job_Type_JobTypePlans)));
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Job_Type_AddNewRecord)));
		int iRwCnt = _driver.findElements(By.xpath("//table[@id='jobTypePlans']/tbody/tr")).size();
		CommonFunctions.selectDropdown(_driver, By.xpath("//table[@id='jobTypePlans']/tbody/tr["+iRwCnt+"]/td[3]/select"), ActivityCode);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='jobTypePlans']/tbody/tr["+iRwCnt+"]/td[4]/textarea"), "4");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public boolean AddOutsidePurchase(String Description, String Notes, String Quantity, String SetupCost, String UnitPrice, String UOM, String mWeight) throws IOException, Exception
	{
		System.out.println("Navigate to BuyOut");
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='u']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobPartOutsidePurch_N102C2_fieldset']/div[1]/div[1]/div[2]//input[@name='addGridRow_JobPartOutsidePurch_N102C2']"));
		Thread.sleep(1000);
		int iRwCnt = _driver.findElements(By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr")).size();

		System.out.println("Fill in the details");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//textarea[@name='JobPartOutsidePurch_N102C2.description_JobPartOutsidePurch']"), Description);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//textarea[@name='JobPartOutsidePurch_N102C2.notes']"), Notes);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//input[@name='JobPartOutsidePurch_N102C2.quantity']"), Quantity);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//input[@name='JobPartOutsidePurch_N102C2.setupCost']"), SetupCost);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//input[@name='JobPartOutsidePurch_N102C2.unitPrice']"), UnitPrice);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//select[@name='JobPartOutsidePurch_N102C2.uom']"), UOM);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr["+iRwCnt+"]//input[@name='JobPartOutsidePurch_N102C2.mWeight']"), mWeight);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);

		int iRwCnt1 = _driver.findElements(By.xpath("//table[@id='JobPartOutsidePurch_N102C2']/tbody/tr")).size();

		if (iRwCnt == iRwCnt1)
		{
			System.out.println("Outside purchase was added");
			return true;
		}
		else
		{
			System.err.println("Outside purchase was not added to job part");
			return false;
		}
	}

	public boolean CheckReport(String ReportTitle)
	{
		boolean reportOpened = false;
		String originalHandle = _driver.getWindowHandle();
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				//				System.out.println(_driver.getTitle().trim());
				if (_driver.getTitle().trim().contains(ReportTitle))
				{
					reportOpened = true;
					System.out.println("Report with title '"+ReportTitle+"' was opened");
					_driver.close();
					_driver.switchTo().window(originalHandle);
				}
				else
				{
					_driver.switchTo().window(originalHandle);
				}
			}
		}
		else
		{
			System.err.println("Report with title '"+ReportTitle+"' was not opened");
		}		
		return reportOpened;
	}

	public String createCustomer (String CustomerName) throws Exception
	{
		ARPage AR = new ARPage(_driver);
		if (CustomerName.equals(""))
		{
			CustomerName = "Cust"+UniqueNum();
		}

		AR.NavigateToARCustomerListPage();			
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.waitForPageLoaded(_driver);			
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_Name)), CustomerName);			
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_FirstName)), "QA FN");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_LastName)), "QA LN");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_Add1)), "QA Address");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_Phone)), "9988776655");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_City)), "Seattle");
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_StateKey)), "1:NY");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_Zip)), "98101");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Customer_Email)), "qaautomation@efi.com");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Customer "+CustomerName, _driver.getTitle());
		System.out.println("Created new Customer '"+CustomerName);

		return CustomerName;
	}

	public String AddContacttoCustomer () throws Exception
	{
		String sFN = "FN"+UniqueNum();
		String sLN = "LN"+UniqueNum();

		System.out.println("Go to the customer's contact list page and add a new Contact");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Customer : ')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Contacts']"));
		CommonFunctions.waitForPageLoaded(_driver);
		String originalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		Thread.sleep(2000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Contact")) 
				{
					CommonFunctions.sSelectCheckBox(_driver, true, By.name("autoUpdateBooleanCheck"));

					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Contact_FirstName)), sFN);
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Contact_LastName)), sLN);
					//					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Contact_Company)), "EFI");
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle);
				}
				else
				{
					_driver.switchTo().window(originalHandle);
				}
			}
		}
		else
		{
			System.err.println("Add contact window was not displayed");
		}

		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))
		{
			System.out.println("Contact '"+sFN+" "+sLN+"' was added to the customer");
			return sFN+" "+sLN;
		}
		else
		{
			return "";
		}
	}

	public void AddNewJobPart(String PartDesription, String Status, String Quantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sErrMsg ="";

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobPart_N1002F_fieldset']/div/div[1]//a[text()='Add New']"));
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.isElementPresent(_driver, By.name("jobProductType")))
		{
			if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.name("jobProductType"))))
			{
				CommonFunctions.selectDropdown(_driver, By.name("jobProductType"), "10PG");
				Thread.sleep(2000);
				CommonFunctions.waitForPageLoaded(_driver);
			}
		}

		CommonFunctions.Wait(_driver, By.name("description_label"));
		CommonFunctions.SendValue(_driver, By.name("qtyOrderedHeader"), Quantity);
		CommonFunctions.SendValue(_driver, By.name("description_label"), PartDesription);		
		if (!(Status.equals("")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionStatus"), Status);
		}		

		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))
		{
			System.out.println("Added new Job part to the job");
		}
		else
		{
			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Error_Message))))
			{
				sErrMsg = CommonFunctions.GetText(_driver, By.xpath(Locators.getProperty(Locators.Error_Message)));
			}
			System.err.println("Unable to add new Job part to the job. "+sErrMsg);
		}
	}

	public String ConvertEstimateToJob(String ConvertType, String Customer, String Jobtype) throws Exception
	{
		String sJob = "", sErrMsg = "";
		JobPlanningPage JP = new JobPlanningPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(), 'Estimates : Est # ')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@class='contextItemBox']//a[text()='- Convert']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='New Job']"));
		if (ConvertType.toLowerCase().trim().equals("new"))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(("//input[@value='New Job']")));
		}
		else
		{
			CommonFunctions.ClickElement(_driver, By.xpath(("//input[@value='Existing Job']")));
			Thread.sleep(3000);
		}

		if (!(Customer.equals("")))
		{
			CommonFunctions.SendValue(_driver, By.name("customer"), Customer);
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
			Thread.sleep(1000);			
		}

		CommonFunctions.Wait(_driver, By.name("jobType"));
		if (Jobtype.equals(""))
		{
			CommonFunctions.selectDropdown(_driver, By.name("jobType"), "1");			
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), Jobtype);
		}
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("promiseDate"), "t");
		CommonFunctions.SendValue(_driver, By.name("scheduledShipDate"), "t+++");
		//Additional Code
		if (!ConvertType.toLowerCase().trim().equals("new"))
		{
			String sExistingJobNum = CommonFunctions.GetValue(_driver, By.name("jobNumber"));
			if (sExistingJobNum == null || sExistingJobNum.equals(""))
			{
				sExistingJobNum = CommonFunctions.GetText(_driver, By.xpath("//label[text()='Last Job Converted To']/../../div/div"));
				CommonFunctions.SendValue(_driver, By.name("jobNumber"), sExistingJobNum);
			}
			CommonFunctions.sSelectCheckBox(_driver, true, By.name(Locators.getProperty(Locators.EstimateConvert_UpdateJobInfo)));
			CommonFunctions.sSelectCheckBox(_driver, true, By.name(Locators.getProperty(Locators.EstimateConvert_RemoveExtraJobParts)));
		}

		String Qty = "";
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//div[@id='priceSummaryDiv']//select[starts-with(@name,'quantityOrdered')]"))))
		{
			Qty = CommonFunctions.GetSelectedOptionValue(_driver, By.xpath("//div[@id='priceSummaryDiv']//select[starts-with(@name,'quantityOrdered')]"));
			if (Qty.equals("") || Qty == null)
			{
				try
				{CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//div[@id='priceSummaryDiv']//select[starts-with(@name,'quantityOrdered')]"), 1);}
				catch(Exception e){}
			}
		}
		else
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//table[starts-with(@id,'parts')]//a[text()='(all)']"));
			int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[starts-with(@id,'parts')]/tbody/tr"));
			for (int i=1; i<=iRwCnt; i++)
			{
				if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[starts-with(@id,'parts')]/tbody/tr["+i+"]//select[@name='partsGrouped.quantityOrdered']")))
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//table[starts-with(@id,'parts')]/tbody/tr["+i+"]//select[@name='partsGrouped.quantityOrdered']"), 0);
				}
			}
		}
		//End of Additional Code
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Convert_And_Go_To_Job)));
		CommonFunctions.waitForPageLoaded(_driver); 
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Job Info']"));

		sJob = JP.getJobIDfromURL();
		if (sJob.equals(""))
		{
			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Error_Message))))
			{
				sErrMsg = CommonFunctions.GetText(_driver, By.xpath(Locators.getProperty(Locators.Error_Message)));
			}
			System.err.println("Unable to create Job from the Estimate. "+sErrMsg);
		}
		else
		{
			System.out.println("Created Job "+sJob+" from estimate");
		}				
		return sJob;
	}

	public void NavigateToQuoteItemsListPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteItemType/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		assertEquals("Quote Item types - All Area Items", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToQuoteItemsListPage");
		System.out.println("****Quote Item List page appears****");
	}

	public void NavigateToAddNewQuoteItemPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteItemType/add");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		assertEquals("Adding Quote Item Type", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddNewQuoteItemsPage");
		System.out.println("****Add New Quote Item page appears****");
	}

	public void addChangeOrdertoJobPart (String ChangeOrderType, String Department, String Description) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Part : ')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Change Orders']"));
		CommonFunctions.waitForPageLoaded(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='ChangeOrder_N10021_fieldset']/div/div[1]//a[text()='Add New']"));
		CommonFunctions.waitForPageLoaded(_driver);

		if (Department.equals(""))
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("department"), 1);
		}
		else
		{
			CommonFunctions.selectDropdown(_driver, By.name("type"), Department);
		}
		Thread.sleep(1000);    	
		if (ChangeOrderType.equals(""))
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("type"), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("type"), ChangeOrderType);
		}
		Thread.sleep(1000);
		if (Description.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.name("description"), "Change Order created by Automation");
		}
		else
		{
			CommonFunctions.SendValue(_driver, By.name("description"), Description);
		}
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='ADD QUOTE ITEMS']"));
	}

	public String AddNewQuoteItemType() throws Exception
	{
		String QuoteItem = "QAQT"+UniqueNum1();

		if (!(_driver.getTitle().equals("Adding Quote Item Type")))
		{
			NavigateToAddNewQuoteItemPage();
		}

		System.out.println("Adding new quote item type");
		CommonFunctions.selectDropdown(_driver, By.name("category"), "5072");
		CommonFunctions.SendValue(_driver, By.name("code"), QuoteItem);
		CommonFunctions.SendValue(_driver, By.name("name"), QuoteItem);
		CommonFunctions.SendValue(_driver, By.name("description"), "Quote Item Type "+QuoteItem+" added by automation");
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@name='availability' and @value='2']"));
		CommonFunctions.selectDropdown(_driver, By.name("uom"), "LT");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		System.out.println("Added new quote item type "+QuoteItem);

		//add price to quote item type
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Quote  Item  Type : ')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Prices']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@name='addGridRow_appbox_implicit']"));
		int irwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr"));
		CommonFunctions.selectDropdown(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+irwCnt+"]//select[@name='appbox_implicit.priceList']"), "1");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+irwCnt+"]//input[@name='appbox_implicit.lowQuantity']"), "1");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+irwCnt+"]//input[@name='appbox_implicit.highQuantity']"), "100000");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+irwCnt+"]//input[@name='appbox_implicit.flatPrice']"), "10");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+irwCnt+"]//input[@name='appbox_implicit.unitPrice']"), "2");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));

		System.out.println("Added prices to the quote item type "+QuoteItem);

		return QuoteItem;
	}

	public String AddQuoteItemToChangeOrder(String QuoteItemType) throws Exception
	{
		String QuoteItemSelected = "";
		String  originalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='ADD QUOTE ITEMS']"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding items to Change Order"))
				{
					CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='quoteItemTypes']/div/ul/li[1]"));
					Thread.sleep(3000);
					if (QuoteItemType.equals(""))
					{
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='quoteItemTypes']/div[1]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[1]/a"));
					}
					else
					{
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='quoteItemTypes']/div/ul/li[1]//a[text()='"+QuoteItemType+"']"));
					}
					Thread.sleep(1000);
					QuoteItemSelected = CommonFunctions.GetText(_driver, By.xpath("//select[@id='quoteItemTypes-selected']/option"));
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_Item)));
					Thread.sleep(2000);
					break;
				}
				else
				{
					_driver.switchTo().window(originalHandle);
				}
			}				
		}
		else
		{
			System.err.println("Add Quote Items window did not appear.");
		}
		_driver.switchTo().window(originalHandle);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		System.out.println("Quote Item = "+QuoteItemSelected+" was added to the Change Order");
		return QuoteItemSelected;
	}

	public void NavigateToPreparePickTicketsPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=JobMaterialPreparePickTickets.process");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("jobType"));
		assertEquals("Prepare Pick Tickets", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPreparePickTicketsPage");
	}

	public void NavigateToVoidCommitPickTicketsPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=JobMaterialCommitPickTickets.process");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Commit Pick Tickets']"));
		assertEquals("Commit Pick Tickets", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToVoidCommitPickTicketsPage");
	}

	public void NavigateToJobPurchaseOrdersList(String JobNum) throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderLine/listJob/"+JobNum.toUpperCase());
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobPurchaseOrdersList");
	}

	public void deleteJobStatus(String StatusID) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		NavigateToJobStatusList();
		DC.SearchValue(StatusID, "id");
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.RowCount(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr")) > 0)
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img"));
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.name("description"));

			DC.Delete();
			CommonFunctions.waitForPageLoaded(_driver);
		}
	}

	public void addInventoryToFinishedGoodsJob(String InventoryItem, String Quantity) throws Exception
	{
		APPage AP = new APPage(_driver);
		DCPage DC = new DCPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Parts Info']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//table[2]/tbody/tr[2]/td[3]/div/a/img"));
		CommonFunctions.waitForPageLoaded(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Inventory Order Items']"));
		String sOriginalHandle = _driver.getWindowHandle();				
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobMaterial_N100C9_fieldset']/div/div[1]//a"));
		Thread.sleep(2000);
		AP.SwitchToWindow("Adding Job Material");
		String sOriginalHandle1 = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]"));
		Thread.sleep(2000);
		AP.SwitchToWindow("Please select an Inventory Item");
		DC.Search(InventoryItem, "id");
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));
		Thread.sleep(1000);
		_driver.switchTo().window(sOriginalHandle1);
		CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), Quantity);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		Thread.sleep(2000);
		_driver.switchTo().window(sOriginalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public boolean addJobMaterial(String InventoryItem, String PlannedQuantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Details_Materials_Tab)));
		String originalHandle = _driver.getWindowHandle();

		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobMaterial_N1010A_fieldset']/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Job Material"))
				{
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Item))).size()>0)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(InventoryItem);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]"));
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									DC.Search(InventoryItem,"id");
									CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));
									Thread.sleep(1000);									
									break;
								}

							}
						}
						_driver.switchTo().window(originalHandle1);
					}
					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), PlannedQuantity);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(2000);
					break;					
				}
			}
		}
		_driver.switchTo().window(originalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}

	public boolean editJobMaterial(String InventoryItem, String UpdateQuantity) throws Exception
	{
		int iRwNum = 1;

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Details_Materials_Tab)));
		String originalHandle = _driver.getWindowHandle();

		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr"));
		for(int i=1; i<iRwCnt+1;i++)
		{
			String sInvItem = CommonFunctions.GetText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr/td[4]/div"));
			if (sInvItem.equals(InventoryItem))
			{
				System.out.println("Inventory Item found in row "+i);
				iRwNum = i;				
			}
		}		

		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr["+iRwNum+"]/td[2]/div/a/img"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows)
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Job Material"))
				{
					CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), UpdateQuantity);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
					Thread.sleep(2000);
					break;
				}
			}
		}
		_driver.switchTo().window(originalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}

	public void addJobMaterialtoFinishedGoodsJob(String InventoryItem, String PlannedQuantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Inventory Order Items']"));
		String originalHandle = _driver.getWindowHandle();

		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='JobMaterial_N100C9_fieldset']/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Job Material"))
				{
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Item))).size()>0)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(InventoryItem);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]"));
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									DC.Search(InventoryItem,"id");
									CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));
									Thread.sleep(1000);									
									break;
								}

							}
						}
						_driver.switchTo().window(originalHandle1);
					}
					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), PlannedQuantity);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(2000);
					break;					
				}
			}
		}
		_driver.switchTo().window(originalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
	}

}