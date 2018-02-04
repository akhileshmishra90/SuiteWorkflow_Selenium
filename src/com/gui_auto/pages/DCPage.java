package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;



public class DCPage implements BaseElement
{


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


	public  DCPage(WebDriver driver) throws Exception 
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
		//DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}

	public String  MonthDateYear()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}

	public String  Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		//DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}

	public void EpaceLogin(String UerName, String Password, String Company) throws Exception
	{
		_driver.manage().window().maximize();

		if (CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Login_Username))))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Login_Username)), UerName);
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Login_Password)), Password);
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Login_Company)), Company);
			CommonFunctions.ClickElement(_driver, By.name(Locators.getProperty(Locators.Login_Submit)));
			CommonFunctions.waitForPageLoaded(_driver);

			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).size() > 0)
			{ 
				LogOffUsers();
			}
			else
			{
				CommonFunctions.Wait(_driver, By.xpath("//div[contains(text(),'Welcome,')]"));
				assertEquals("Welcome to EFI Pace",_driver.getTitle());
			}

			TakeScreenShot.ScreenShotWindow(_driver, "Login");
		}
		else
		{
			System.err.println("Not Able to see Login fields");
		}

		if (_properties.getProperty(GUI_automation_properties.BROWSER).toLowerCase().equals("firefox"))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//div[text() = 'Welcome, ']"));
			Thread.sleep(2000);
		}
	}

	public void EpaceLogin2(String sUN,String sPWD,String sCompany,String sWorkspace) throws Exception
	{


		System.out.println(Date()+"*** Author: Shilpa");
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		System.out.println(Date()+date);
		System.out.println(Date()+"*** Launch & Login Start");
		if(_driver.findElements(By.id(Locators.getProperty(Locators.Logout))).size()>0)
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.Logout))).click();
		}

		if(_driver.findElements(By.name(Locators.getProperty(Locators.Login_Username))).size()>0)
		{
			System.out.println(Date()+"Able to see Login Text");
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
				String sLoggedinUserpath = "//div[@id='welcomeMessage']/a[text()='"+sUN+"']";
				boolean sFlag= CommonFunctions.isElementPresent(_driver,By.xpath(sLoggedinUserpath)); 
				if(sFlag == true)
				{

					System.out.println(Date()+"Able to Login Successfully");	
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					System.out.println(Date()+NewFileNamePath);
					assertEquals("Employee Login for Workstation: "+sWorkspace, _driver.getTitle());
				}
				else
				{
					System.out.println("Login Failed");		
				}
			}
			else
			{
				assertEquals("Employee Login for Workstation: "+sWorkspace, _driver.getTitle());			
				boolean sFlag1= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Welcome_Message))); 
				if(sFlag1 == true)
				{
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					System.out.println(Date()+NewFileNamePath);
					assertEquals("Employee Login for Workstation: "+sWorkspace, _driver.getTitle());
					System.out.println(Date()+"Able to Login Successfully");	
				}
				else
				{
					System.out.println("Login Failed");		
				}
			}
		}

		else
		{
			System.err.println("Not Able to see Login fields");
		}

	}
	public void Logout() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.id("logOff")).click();
		Thread.sleep(2000);
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Login_Username)));
		assertEquals("EFI Pace Print Management System Powered by EFI: Login", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Logout Page");
	}

	public void Home() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/welcome");
		Thread.sleep(2000);
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}


	}
	public void NavigateToDC() throws Exception
	{

		System.out.println(Date()+"*** DC Workflow 2  # P-1066");
		System.out.println(Date()+"*** Author: Shilpa");
		System.out.println(Date()+"*** Created date:13-May-2013");
		System.out.println(Date()+"*** Navigate to DC SETUP PAGE");
		_driver.findElement(By.id(Locators.getProperty(Locators.PaceStartButton))).click();
		Thread.sleep(2000);
		_driver.findElement(By.linkText("Administration")).click();
		Thread.sleep(2000);
		_driver.findElement(By.linkText("System Setup")).click();
		Thread.sleep(2000);
		_driver.findElement(By.linkText("Data Collection")).click();
		Thread.sleep(2000);
		//   _driver.findElement(By.linkText("Data Collection Settings")).click();

		Thread.sleep(10000);

		WebElement elementToInteractWith =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_Administrator)));
		Actions holdClick = new Actions(_driver);
		holdClick.clickAndHold(elementToInteractWith).perform();
		Thread.sleep(1000);
		//   holdClick.release().perform();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_Administrator))).click();

		//  WebElement elementToInteractWith1 =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_System_Setup)));
		//   Actions holdClick1 = new Actions(_driver);
		//   holdClick1.clickAndHold(elementToInteractWith1).perform();
		Thread.sleep(3000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_System_Setup))).click();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_DC_Setup))).click();
		//   holdClick.release().perform();

		WebElement elementToInteractWith2 =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_Data_Collection)));
		Actions holdClick2 = new Actions(_driver);
		holdClick2.clickAndHold(elementToInteractWith2).perform();
		Thread.sleep(3000);

		WebElement elementToInteractWith3 =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_DC_Setup)));
		Actions holdClick3 = new Actions(_driver);
		holdClick3.clickAndHold(elementToInteractWith3).perform();
		Thread.sleep(3000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_System_Setup))).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_Data_Collection))).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Menu_DC_Setup))).click();
		Thread.sleep(1000);
		assertEquals("Data Collection Setup", _driver.getTitle());

	}
	public void NavigateToDataCollectionSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/DataCollectionSetup/detail/1");	
		CommonFunctions.Wait(_driver, By.linkText("Data Collection Setup"));
		assertEquals("Data Collection Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"DataCollectionSetup");
	}

	public void NavigateToEnterNewJob() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/add");	
		CommonFunctions.Wait(_driver, By.linkText("Part Info"));
		assertEquals("Adding Job", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Adding_Job");

	}
	public void NavigateToEmployeeStatusList() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EmployeeStatus/list");	
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Employee Statuses", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Employee");
	}

	public void NavigateToDepartmentMaintainance() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Department/list");
		Thread.sleep(3000);
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Departments", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Department");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(1000); 
	}

	public void NavigateToActivityCodeMaintainance() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/list");
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Activity Codes", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"AC");

	}
	public void NavigateToJobCostSetting() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobCostSetup/detail/1");
		CommonFunctions.Wait(_driver, By.linkText("Ask Settings"));
		assertEquals("Job Cost Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"JobCostSetting");

	}
	public void NavigateToFindEmployee() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Employee/list");	
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		//assertEquals("Employees", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"FindEmployee");

	}


	public void NavigateToActiveEmployeeTime() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EmployeeTime/list_supervisor");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Find)));
		assertEquals("Active Employee Times", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"ActiveEmployeeTimes");

	}
	public void NavigateToDataCollectionLogin() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Employee/lookup");	
		Thread.sleep(2000);
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Login)));
		assertEquals("Data Collection Login", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"DataCollectionLogin");

	} 
	public void NavigateToDailyPrintTimeSheet() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/reports/prompt?report=68&amp;secure=falses");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Update)));
		assertEquals("Running Report -Daily Time Sheet Report", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"DailyPrintTimeSheet");


	} 
	public void NavigateToSystemUsers() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SystemUser/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Update)));
		assertEquals("System Users", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"SystemUsers");
	} 

	public void NavigateToFindInventoryItem() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItem/list");
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Inventory Items", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"FindInvItem");
	}

	public void NavigateToFindJobPart() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/list");
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Job Parts - Open Jobs", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"FindJobPart");
	}


	public void NavigateToNonChargeableTypes() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/NonChargeableType/list");	
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Non Chargeable Types", _driver.getTitle());

	}
	public void NavigateToWorkStation() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Workstation/list");	
		CommonFunctions.Wait(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		assertEquals("Workstations", _driver.getTitle());

	}

	public boolean  Search(String sValue,String sLabel) throws Exception
	{
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), sLabel);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(sValue);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
		Thread.sleep(5000);
		String sTitle = _driver.getTitle();

		System.out.println(Date()+"sTitle " + sTitle);
		String sETitle =  _driver.getTitle();
		if(sETitle.length()>25)
		{
			sETitle = sETitle.substring(25);
			System.out.println(Date()+sETitle);
		}
		if(sValue.equals(sETitle))
		{

			System.out.println(Date()+"Element Present");
			return true;
		}
		else
		{
			System.out.println(Date()+"Element not present");
			return false;
		}
	}
	public void  SearchValue(String sValue,String sLabel) throws Exception
	{
		//    	 _driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).clear();
		//    	  	_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
		//    	  	Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), sLabel);
		Thread.sleep(1000);
		//_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(sValue);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
	}

	//By default, all should be enabled  
	public boolean ValidateDataCollectionSetup() throws Exception
	{
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Component))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Component))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Pay_Rate))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Pay_Rate))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Shift))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Shift))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).click();
		}

		String sVersion = FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).click();
			}


		}
		else
		{
			if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).click();
			}
		}

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Employee_Time))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Employee_Time))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).click();
		}
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Complete_Runs))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Complete_Runs))).click();
		}
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).click();
		}
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id))).click();
		}


		System.out.println(Date()+" Update");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		boolean sFlag  = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Overlapping)));
		boolean sFlag1 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Pausing)));
		boolean sFlag2 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))); 
		boolean sFlag3 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Component)));
		boolean sFlag4 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Pay_Rate)));
		boolean sFlag5 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Shift)));
		boolean sFlag6 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Job_Planning)));
		boolean sFlag7 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Use_Materials)));
		boolean sFlag8 = false;

		if(Integer.valueOf(sVersion)< 27)
		{
			sFlag8 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))); 
		}
		else
		{
			sFlag8 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))); 
		}

		boolean sFlag9 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Materials))); 
		boolean sFlag10 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Closed_Jobs)));
		boolean sFlag11 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Employee_Time)));
		boolean sFlag12 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time)));
		boolean sFlag13 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Pull_Multiple_Materials)));	 
		boolean sFlag14 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Complete_Runs)));	 
		boolean sFlag15 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Ganging)));	 	 
		boolean sFlag16 = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id)));	

		if(sFlag == true && sFlag1 == true && sFlag2 == true && sFlag3 == true && sFlag4 == true && sFlag5 == true && sFlag6 == true && sFlag7 == true && sFlag8 == true && sFlag9== true && sFlag10 == true && sFlag11 == true && sFlag12 == true && sFlag13 == true && sFlag14 == true && sFlag15 == false && sFlag16 == false)
		{
			System.out.println(Date()+"DATA COLLECTION SETUP VALIDATED i.e all checkboxes are enabled");
			return true; 
		}
		else
		{
			System.err.println("DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}
	}

	public boolean ValidateDisplayClosedJobUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Closed_Jobs)));



		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Closed_Jobs DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Closed_Jobs  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean ValidateDisplayEmployeeTimeUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Employee_Time))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Employee_Time))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Employee_Time)));

		System.out.println(Date()+"Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Employee_Time DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Employee_Time  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}


	public boolean ValidateAskJobComponentUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Component))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Component))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Component)));
		if(sFlag == false)
		{
			System.out.println(Date()+"Ask_Job_Component DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Ask_Job_Component  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public void Update() throws Exception
	{
		CommonFunctions.getPopupMessage(_driver);
		System.out.println(Date()+"Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
		TakeScreenShot.ScreenShotWindow(_driver, "Update_");
	}

	public void Add() throws Exception
	{
		System.out.println(Date()+"Add");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));  
		TakeScreenShot.ScreenShotWindow(_driver, "Add_");
	}

	public void Delete() throws Exception
	{
		System.out.println(Date()+"Delete");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Delete)));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Delete_Popup)));

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));
		TakeScreenShot.ScreenShotWindow(_driver, "Delete_");
	}

	public boolean ValidateAskPayRateUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Pay_Rate))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Pay_Rate))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Pay_Rate)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Ask_Pay_Rate DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Ask_Pay_Rate  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}


	public boolean ValidateAskShiftCountUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Shift))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Shift))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Shift)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Ask_Shift DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Ask_Shift  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean ValidateDisplayNCTimeUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Non_Chargeable_Time)));	  
		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Non_Chargeable_Time DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Non_Chargeable_Time  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAllowLappingUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Overlapping)));

		if(sFlag == false)
		{
			System.out.println(Date()+"ALLOW OVER LAPPING DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("ALLOW OVER LAPPING  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAllowPausingCheckedDataCollectionSetup() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Pausing)));	  
		if(sFlag == true)
		{
			System.out.println(Date()+"Allow Pausing DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Allow Pausing  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	} 


	public boolean ValidateAllowPausingUnCheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Pausing))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Pausing)));

		if(sFlag == false && _driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0)
		{
			System.out.println(Date()+"Allow Pausing DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Allow Pausing  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	} 


	public boolean ValidateDispalyClosedJobsCheckDataCollectionSetup() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Closed_Jobs))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Closed_Jobs)));



		if(sFlag == true)
		{
			System.out.println(Date()+"Display_Closed_Jobs DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Closed_Jobs  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	} 

	public boolean ValidateAllowEntryCloningCheckedDataCollectionSetup() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Entry_Cloning)));

		if(sFlag == true)
		{
			System.out.println(Date()+"Allow Entry Cloning DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Allow Entry Cloning  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAllowEntryCloningUncheckedDataCollectionSetup() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Entry_Cloning))).click();
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Entry_Cloning)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Allow Entry Cloning DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Allow Entry Cloning  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAllowLappingDataCollectionSetup() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Overlapping))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Overlapping)));


		if(sFlag == true)
		{
			System.out.println(Date()+"ALLOW OVER LAPPING DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("ALLOW OVER LAPPING  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAllowGanging() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Ganging)));


		if(sFlag == true)
		{
			System.out.println(Date()+"ALLOW Ganging DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("ALLOW Ganging  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateDisplayJobPlanningCheck() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Job_Planning)));
		if(sFlag == true)
		{
			System.out.println(Date()+"Display_Job_Planning DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Job_Planning DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean ValidateDisplayJobPlanningUncheck() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Job_Planning))).click();

			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Job_Planning)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Job_Planning DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Job_Planning DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateUseMaterailsCheck() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Use_Materials)));	  
		if(sFlag == true)
		{
			System.out.println(Date()+"Use_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Use_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean ValidateDisplayMaterialsCheck() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Materials)));


		if(sFlag == true)
		{
			System.out.println(Date()+"Display_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}



	public boolean ValidateAskJobMaterailIDCheck() throws Exception
	{
		boolean sFlag=false;
		String sVersion = FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).click();
			}
			sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1)));

		}
		else
		{
			if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).click();
			}
			sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id)));
		}




		if(sFlag == true)
		{
			System.out.println(Date()+"Display_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}


	public boolean ValidateAskJobMaterailIDUnCheck() throws Exception
	{
		boolean sFlag=false;
		String sVersion = FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1))).click();
			}
			sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id1)));

		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Material_Id))).click();
			}
			sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Material_Id)));
		}
		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateDisplayMaterialsUncheck() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Display_Materials))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Display_Materials)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Display_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Display_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean ValidateUseMaterialsUncheck() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Use_Materials))).click();

			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Use_Materials)));


		if(sFlag == false)
		{
			System.out.println(Date()+"Use_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Use_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}  


	public boolean ValidatePullMultipleMaterailsCheck() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Pull_Multiple_Materials)));
		if(sFlag == true)
		{
			System.out.println(Date()+"Pull_Multiple_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Pull_Multiple_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidateAskJobPlanIDCheck() throws Exception
	{

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Ask_Job_Plan_Id)));

		System.out.println(Date()+"Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		if(sFlag == true)
		{
			System.out.println(Date()+"Ask_Job_Plan_Id DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Ask_Job_Plan_Id DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}
	public boolean ValidatePullMultipleMaterialsUncheck() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Pull_Multiple_Materials))).click();

			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Pull_Multiple_Materials)));

		if(sFlag == false)
		{
			System.out.println(Date()+"Pull_Multiple_Materials DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("Pull_Multiple_Materials DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}  
	public boolean ValidateAllowGangingUnchecked() throws Exception
	{

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Allow_Ganging))).click();
			Thread.sleep(1000);
		}

		boolean sFlag = CommonFunctions.VerifyChecked(_driver, By.name(Locators.getProperty(Locators.Allow_Ganging)));
		if(sFlag == false)
		{
			System.out.println(Date()+"ALLOW Ganging DATA COLLECTION SETUP VALIDATED");
			return true; 
		}
		else
		{
			System.err.println("ALLOW Ganging  DATA COLLECTION SETUP VALIDATION FAILED");
			return false; 
		}



	}

	public boolean AddEmployeeNewRecord(String EmpID,String Description)throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Employee_Inactive_CheckBox)));
		Thread.sleep(1000);
		assertEquals("Adding Employee Status", _driver.getTitle());

		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_ID))).sendKeys(EmpID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Description))).sendKeys(Description);

		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Active_Checkbox))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Active_Checkbox))).click();
		}

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Inactive_CheckBox))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Active_Checkbox))).click();
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Add))).click();
		Thread.sleep(3000);

		assertEquals("Employee Status "+EmpID, _driver.getTitle());
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		System.out.println(Date()+" "+ sFlag);
		NewFileNamePath =  TakeScreenShot.getDestinationFile("Employee Status "+EmpID);
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(Date()+NewFileNamePath);
		String sEmployeeId =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Id_Text))).getText();
		sEmployeeId = sEmployeeId.trim();
		System.out.println(Date()+" "+ sEmployeeId); 
		assertFalse(_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Inactive_CheckBox))).isSelected());

		if(sEmployeeId.equals(EmpID) && sFlag == true)
		{
			System.out.println(Date()+"Created New Employee"); 
			return true;

		}
		else
		{
			System.err.println("Not able to create employee"); 
			return false;
		}
	}


	public void SearchEmployee(String EmpID) throws Exception
	{

		System.out.println(Date()+"Search EMPID");	
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "id");
		String sSelectedValue = _driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_Dropdown))).getText();
		System.out.println(Date()+sSelectedValue);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(EmpID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);

	}  

	public boolean DeleteEmployee(String EmpID) throws Exception
	{
		boolean sFlag = false;
		String sEmployeeStatus = "Employee Status "+EmpID ;
		System.out.println(Date()+"sEmployeeStatus " + sEmployeeStatus);	
		SearchEmployee(EmpID);
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);	

		if(sEmployeeStatus.equals(sTitle))
		{
			assertEquals(sEmployeeStatus, _driver.getTitle());
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
			Thread.sleep(2000);

			_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
			Thread.sleep(2000);
			sFlag =  CommonFunctions.isElementPresent(_driver, By.linkText(Locators.getProperty(Locators.Employee_Deleted_text)));
			return sFlag;
		}
		return sFlag;

	}

	public boolean SearchDepartment(String DepartmentID) throws Exception
	{

		String sDepartment = "Department : "+DepartmentID+" - Materials";
		String sXpath	= "//a[text()='"+sDepartment+"']";
		String sDepartmentPage = "Department "+DepartmentID ;
		System.out.println(Date()+"Department is  " + sDepartmentPage);	
		System.out.println(Date()+"Search Department");	

		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "id");
		String sSelectedValue = _driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_Dropdown))).getText();
		System.out.println(Date()+sSelectedValue);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(DepartmentID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);

		if(sDepartmentPage.equals(sTitle))
		{
			assertEquals(sDepartmentPage, _driver.getTitle());
			System.out.println(Date()+"Able to see Department");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Department");

			return false;
		}


	}  	 

	public boolean EditDepartment(String DepartmentID) throws Exception
	{
		boolean sFlag = false;
		String sDepartment = "Department : "+DepartmentID+" - Materials";
		String sXpath	= "//a[text()='"+sDepartment+"']";

		String sDepartmentPage = "Department "+DepartmentID ;
		System.out.println(Date()+" Department is  " + sDepartmentPage);	
		System.out.println(Date()+" Search Department");	
		SearchDepartment(DepartmentID);
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);	

		if(sDepartmentPage.equals(sTitle))
		{
			assertEquals(sDepartmentPage, _driver.getTitle());
			System.out.println(Date()+" Entered");

			/* WebElement elementToInteractWith = _driver.findElement(By.xpath(sXpath));
			 Actions holdClick = new Actions(_driver);
			 holdClick.clickAndHold(elementToInteractWith).perform();
			 Thread.sleep(3000);

			 Actions actions = new Actions(_driver);
			 WebElement menuHoverLink = _driver.findElement(By.xpath(sXpath));
			 actions.moveToElement(menuHoverLink);
			 actions.click();
			 // _driver.findElement(By.xpath("//a[text()='Department : 000 - Materials']")).click();
			 // _driver.findElement(By.linkText(Locators.getProperty(Locators.Data_Collection_Settings_Link))).click() ;
			 */
			Thread.sleep(1000);

			sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Department/detailDC/" +DepartmentID);

			sFlag = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Department_Update)));
			assertEquals(sDepartmentPage, _driver.getTitle());
			return sFlag;

		}
		else
		{
			System.err.println(" Department not present");
		}
		return sFlag;
	}


	public boolean UpdateDepartment(String PrintSignout,String TimeRounding,String EarlyBufferTime,String LaterBufferTime,String Day,String Time) throws Exception
	{
		boolean sFlag = false;

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Auto_Print_Signout)), PrintSignout);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Time_rounding)), TimeRounding);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_time_Early_buffer))).clear();
		Thread.sleep(500);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_time_Early_buffer))).sendKeys(EarlyBufferTime);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_time_Later_buffer))).clear();
		Thread.sleep(500);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_time_Later_buffer))).sendKeys(LaterBufferTime);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Beginning_Day_Of_Week)), Day);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.BEginning_Time_Of_Day))).clear();
		Thread.sleep(500);
		_driver.findElement(By.name(Locators.getProperty(Locators.BEginning_Time_Of_Day))).sendKeys(Time);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Department_Update))).click();

		sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Department_Updated_text)));

		if(sFlag == true)
		{
			return sFlag;
		}
		else
		{
			return sFlag;
		}
	}


	public boolean DeleteDepartment(String DepartmentID) throws Exception
	{

		boolean sFlag = false;
		String sDepartment = "Department : "+DepartmentID+" - Materials";
		String sXpath	= "//a[text()='"+sDepartment+"']";
		String sDepartmentPage = "Department "+DepartmentID ;
		System.out.println(Date()+"Department is  " + sDepartmentPage);	
		System.out.println(Date()+"Search Department");	

		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);	

		if(sDepartmentPage.equals(sTitle))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
			Thread.sleep(2000);

			_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
			Thread.sleep(2000);
			sFlag =  CommonFunctions.isElementPresent(_driver, By.linkText(Locators.getProperty(Locators.Employee_Deleted_text)));	  
		}
		else
		{
			sFlag = true;
			System.out.println(Date()+"Not Able to see Department"); 
		}

		return sFlag;


	}


	public boolean AddDepartment(String DeptID,String Description,String sCategory) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Active_CheckBox)));
		assertEquals("Adding Department", _driver.getTitle());

		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_ID))).sendKeys(DeptID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Description))).sendKeys(Description);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Department_Note_Category)), sCategory);
		Thread.sleep(1000);
		if(!_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Active_Checkbox))).isSelected())
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Active_Checkbox))).click(); 
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Add))).click();
		Thread.sleep(3000);
		String sTitle  = "Department "+DeptID;
		assertEquals(sTitle, _driver.getTitle());

		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		System.out.println(Date()+sFlag);


		if( sFlag == true)
		{
			System.out.println(Date()+"Created New Department"); 
			return true;

		}
		else
		{
			System.err.println("Not able to create Department"); 
			return false;
		}
	}

	public boolean UpdateTimeRound(String TimeRounding) throws Exception
	{
		boolean sFlag = false;


		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Time_rounding)),TimeRounding);
		Thread.sleep(1000);  
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);  
		sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Department_Updated_text)));
		if(sFlag == true)
		{
			System.out.println(Date()+"Updated");
			return sFlag;
		}
		else
		{
			System.err.println("Not able to Update");
			return sFlag;
		}

	}

	public boolean UpdateBeginningDayOfWeek(String Day) throws Exception
	{
		boolean sFlag = false;

		if(Day != "")
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Beginning_Day_Of_Week)),Day);
			Thread.sleep(1000);  
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Beginning_Day_Of_Week)),"");
			Thread.sleep(1000);
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);  
		sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Department_Updated_text)));
		if(sFlag == true)
		{
			System.out.println(Date()+"Updated");
			return sFlag;
		}
		else
		{
			System.err.println("Not able to Update");
			return sFlag;
		}



	}

	public boolean AddCostCenter(String sCostCenter,String sDeptID) throws Exception
	{
		String sDepartmentPage = "Department "+sDeptID ;
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sCC = "//a[@href ='/epace/company:"+sCOMPANY+"/object/CostCenter/detail/"+sCostCenter+"']";
		String originalHandle;
		boolean sFlag2 = false;

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Department_CostCenter))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_New_Record))).size()>0) 
		{
			boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(sCC));
			if(sFlag == false)
			{
				originalHandle = _driver.getWindowHandle();
				System.out.println(Date()+originalHandle);

				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
				Thread.sleep(2000);
				Set<String> availableWindows = _driver.getWindowHandles();
				if (!availableWindows.isEmpty()) 
				{
					for (String windowId : availableWindows) 
					{
						if(_driver.switchTo().window(windowId).getTitle().equals("Adding Cost Center")) 
						{

							_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(sCostCenter);
							_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("CstCntr "+sCostCenter+" description");
							_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
							Thread.sleep(2000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sDepartmentPage);
							if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
							{	
								System.out.println(Date()+"CostCenter Created");	
								sFlag2 = true;
							}
							else
							{
								System.err.println("Not able to Create CostCenter");
								sFlag2 = false;
							}		
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sDepartmentPage);

						}

					}
				}

			}
			else
			{
				System.out.println(Date()+"CostCenter Already Present");		
				return true;
			}
		}
		else
		{
			System.err.println("Not able to Create CostCenter");
			return false;
		}
		return sFlag2;

	}

	///////////Create Employee

	public boolean CreateEmployee(String sEmpId,String sFN,String sLN,String Dept,String Add1,String Status,String Phone,String Country,String sState,String zip) throws Exception
	{

		boolean sFlag = false;

		NavigateToFindEmployee();
		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Emp_Add)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_ID))).sendKeys(sEmpId);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_FirstName))).sendKeys(sFN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_LastName))).sendKeys(sLN);

		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Department)),Dept);
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add1))).sendKeys(Add1);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Emp_Status)),Status);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Phone))).sendKeys(Phone);
		/* CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Country)),Country);
		  Thread.sleep(1000);
		  CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_StateKey)),sState);
		  Thread.sleep(1000);*/
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Zip))).sendKeys(zip);
		Thread.sleep(1000);  

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Employee_PayRoll))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Employee_PayRoll_PayRate01)));
		_driver.findElement( By.name(Locators.getProperty(Locators.Employee_PayRoll_PayRate01))).sendKeys("10");


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Emp_Add))).click();
		boolean sNewEmp =   CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		boolean sEmpAlreadyPresent =   CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='form-errors']/ul/li[text()='Employee ID: Value already in use']"));
		if(sNewEmp == true || sEmpAlreadyPresent == true )
		{
			sFlag = true;
		}
		NewFileNamePath =  TakeScreenShot.getDestinationFile("CreateEmp");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(Date()+NewFileNamePath);
		//	  sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		return sFlag;

	}

	//// DCL_SignIn

	public void SetEmployeeStartAndStopTime(String sEmpId) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Employee/detail/"+sEmpId);
		System.out.println(Date()+" Navigate to Production tab and set Start and stop time");
		assertTrue(CommonFunctions.isTextPresentOnPage(_driver, "Production Info"));
		_driver.findElement(By.linkText("Production Info")).click();
		_driver.findElement(By.name("startTime")).clear();
		_driver.findElement(By.name("startTime")).sendKeys("09:00 AM");
		_driver.findElement(By.name("stopTime")).clear();
		_driver.findElement(By.name("stopTime")).sendKeys("06:00 PM");
		Update();
	}

	//this will capture the system time in minutes. Then it will check employee time early and late buffer. 
	//It will then round off the time based on the settings in Employee Time Rounding and will display the result in Recent Time Entries
	//Limitations: If system is in some different time zone, or if today's date dosen't matches with system's date, then this may fail. In that case, manually execute the TCs.
	public boolean VerifySignInToTime(String sTimeRounding)
	{
		DateFormat dateFormat = new SimpleDateFormat("mm");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		System.out.println("System  time in minutes is "+suniqueNumber);

		String sSignInTime = _driver.findElement(By.xpath("//div[@id='dc_status']/table/tbody/tr/td[3]")).getText();
		sSignInTime = sSignInTime.trim();
		//sSignInTime =sSignInTime.substring(13);

		sSignInTime =sSignInTime.replace("Signed In :", "");
		System.out.println("SignInTime time of employee is 0 "+sSignInTime);	
		System.out.println("MonthDateYear is  "+MonthDateYear());	
		sSignInTime =sSignInTime.replace(MonthDateYear(), "");
		System.out.println("SignInTime time of employee is 1 "+sSignInTime);	 
		sSignInTime =sSignInTime.replace(" - ", "");
		System.out.println("SignInTime time of employee is 2 "+sSignInTime);	

		if(sSignInTime.length()==7)
		{
			sSignInTime =sSignInTime.substring(2); 
		}
		else
		{
			sSignInTime =sSignInTime.substring(3);
		}

		System.out.println("SignInTime time of employee is 3 "+sSignInTime);	

		if(sSignInTime.contains("AM"))
		{
			sSignInTime =sSignInTime.replace(" AM", "");
			sSignInTime = sSignInTime.replace(" AM", "");
		}
		else
		{
			sSignInTime = sSignInTime.replace(" PM", ""); 
			sSignInTime = sSignInTime.replace(" PM", "");
		}

		System.out.println("SignInTime time of employee is 4 "+sSignInTime);	

		int Applicationmin = Integer.parseInt(sSignInTime);
		System.out.println("SignIn Time of Employee is "+Applicationmin);
		int sEight =  Integer.parseInt("08");
		int sNine =  Integer.parseInt("09");
		int min = Integer.parseInt(suniqueNumber);
		System.out.println("min time (or system time) is "+min);	

		//Applicationmin is the SignIn Time of employee and min is the system time in minutes

		if(sTimeRounding.equals("None"))
		{
			if(min == Applicationmin)
			{
				return true;
			}
			else
			{
				return false; 
			}
		}

		if(sTimeRounding.equals("Quarters (15m)"))
		{
			System.out.println("sTime Rounding is Quarters (15m)");
			if(min<10)
				min = 00;
			else if(min>=10 && min<=24)
				min = 15;
			else if(min>=25 && min<=39)
				min = 30;
			else if(min>=40 && min<=54)
				min = 45;
			else 
				min = 00;
		}	 

		if(sTimeRounding.equals("Tenths (6m)"))
		{
			System.out.println(Date()+"sTime Rounding is Tenths (6m)");
			if(min<=30)
			{
				if(min<15)
				{
					if(min==00 || min==01||min==02)
						min =00;
					else if(min==03 ||min==04 ||min==05 ||min==06 ||min==07 ||min==sEight)
						min =06;
					else if(min == sNine ||min == 10 ||min ==11||min==12 ||min ==13 ||min == 14 )
						min = 12; 
				}
				else
				{
					if(min == 15 ||min ==16||min==17 ||min ==18 ||min == 19 ||min == 20)
						min = 18;
					else if(min == 21 ||min ==22||min==23 ||min ==24 ||min == 25 ||min == 26)
						min = 24;
					else if(min == 27 ||min ==28||min==29 ||min ==30 ||min == 31 ||min == 32)
						min = 30; 
				}


			}
			else
			{
				if(min<45)
				{
					if(min==31 || min==32)
						min =30;
					else if(min==33 ||min==34 ||min==35 ||min==36 ||min==37 ||min ==38)
						min =36;
					else if(min == 39 ||min ==40||min==41 ||min ==42 ||min == 43 ||min ==44 )
						min = 42; 

				}
				else
				{
					if(min == 45 ||min ==46||min==47 ||min ==48 ||min == 49 ||min == 50)
						min = 48;
					else if(min == 51 ||min ==52||min==53 ||min ==54 ||min == 55 ||min == 56)
						min = 54;
					else if(min == 57 ||min ==58||min==60)
						min = 00; 
				} 
			}
		}

		System.out.println("Minutes is "+ min);
		if(min == Applicationmin)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean RecentEmployeeTimeEntries(String sStarttime ,String sStoptime,String sVersion) throws Exception
	{
		boolean sStartFlag =false;
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Recent_Time_Entries))).click();
		Thread.sleep(3000);
		String sStartTime="";
		String sStopTime="";
		if(Integer.valueOf(sVersion)< 27)
		{
			if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			int rowcount = _driver.findElements(By.xpath("//table[@id='EmployeeTime']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sStartTime = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr["+rowcount+"]/td[4]/div")).getText();
		}
		else
		{
			if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			int rowcount = _driver.findElements(By.xpath("//table[@id='employeeTimes']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sStartTime = _driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr["+rowcount+"]/td[4]/div")).getText(); 
		}




		sStartTime =sStartTime.trim();
		if(sStarttime.equals(sStarttime))
		{
			sStartFlag = true;
		}

		if(Integer.valueOf(sVersion)< 27)
		{
			int rowcount = _driver.findElements(By.xpath("//table[@id='EmployeeTime']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sStopTime = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr["+rowcount+"]/td[8]/div")).getText();
		}
		else
		{
			int rowcount = _driver.findElements(By.xpath("//table[@id='employeeTimes']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sStopTime = _driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr["+rowcount+"]/td[8]/div")).getText(); 
		}

		sStopTime =sStopTime.trim();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();
		return sStartFlag;
	}

	public boolean   EmployeeSignInEntries(String sEmpCode,String sFN,String sLN,String sSheetname,String sSN,String sTC,String sCN) throws Exception
	{
		boolean sFlag= false;

		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).sendKeys(sEmpCode);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		String sEmployeeName = "Employee Activity for  "+sFN+" "+sLN;
		System.out.println(Date()+Date() + " "+sEmployeeName);
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).size()>0)
		{
			NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "EmpSigIn"); 
			String shour = GetHour();
			int hour = Integer.parseInt(shour);
			if(hour > 12)
			{
				hour = hour - 12 ;
			}
			else
			{
				hour = hour + 12 ;
			}

			String sMins = GetMinutes();
			String sCurrentTime = hour+":"+sMins;
			String sCurrentTime1 = shour+":"+sMins;
			sCurrentTime1 = sCurrentTime1.substring(1); 
			System.out.println(Date()+"Current time is "+ sCurrentTime);
			System.out.println(Date()+"Current  time is "+ sCurrentTime1);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).click();
			Thread.sleep(3000);
			boolean sSignedIn =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));

			_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Recent_Time_Entries))).click();
			Thread.sleep(3000);
			//  assertEquals(sEmployeeName, _driver.getTitle());
			String sTime = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr/td[4]/div")).getText();
			sTime = sTime.trim();
			System.out.println(Date()+"Signed In Time in  Application is "+ sTime);
			if(sTime.length() > 5)
			{
				sTime = sTime.substring(0,sTime.length()-3);
			}

			System.out.println(Date()+"Signed In Time in  Application is "+ sTime);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();
			if(sTime.equals(sCurrentTime) || sTime.equals(sCurrentTime1))
			{
				dbConnection.UpdateFunction(sSheetname, sSN, sTC, sCN, sCurrentTime1);
				System.out.println(Date()+"Time Validated");
				sFlag = true;
			}
			else
			{
				dbConnection.UpdateFunction(sSheetname, sSN, sTC, sCN, "null");
				System.err.println("Time Failed");
				sFlag = false;
			}

		}
		if(sFlag == true)
		{
			return true;
		}
		else
		{
			return false; 
		}

	}

	public boolean VerifySignInTimeInRecentTimeEntries(String sDate ,String sTime,String sVersion) throws Exception
	{
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Recent_Time_Entries))).click();
		Thread.sleep(3000);
		String sActualTimeInRecentTimeEntier="",sActualDateInRecentTimeEntier="";

		//  assertEquals(sEmployeeName, _driver.getTitle());
		if(_driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).size()>0)
		{
			if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);

			int rowcount = _driver.findElements(By.xpath("//table[@id='EmployeeTime']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sActualTimeInRecentTimeEntier = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr["+rowcount+"]/td[4]/div")).getText();
			sActualTimeInRecentTimeEntier = sActualTimeInRecentTimeEntier.trim();
			System.out.println(Date()+"Signed In Time in  Application is "+ sActualTimeInRecentTimeEntier);
			sActualDateInRecentTimeEntier = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr["+rowcount+"]/td[3]/div")).getText();
			sActualDateInRecentTimeEntier = sActualDateInRecentTimeEntier.trim();
			System.out.println(Date()+"Signed In Date in  Application is "+ sActualDateInRecentTimeEntier);
		}
		else
		{

			if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).size()>0)
			{
				_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).click();
				Thread.sleep(1000);
				int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
				for(int i=1;i<=sRowCount;i++)
				{
					String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
					sText=sText.trim();
					System.out.println("sText is "+sText);
					if(sText.equals("Remove Sort"))
					{
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
						Thread.sleep(1000);
						break;
					}
				}	  
			}
			Thread.sleep(1000);
			int rowcount = _driver.findElements(By.xpath("//table[@id='employeeTimes']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);
			sActualTimeInRecentTimeEntier = _driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr["+rowcount+"]/td[4]/div")).getText();
			sActualTimeInRecentTimeEntier = sActualTimeInRecentTimeEntier.trim();
			System.out.println(Date()+"Signed In Time in  Application is "+ sActualTimeInRecentTimeEntier);
			sActualDateInRecentTimeEntier = _driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr["+rowcount+"]/td[3]/div")).getText();
			sActualDateInRecentTimeEntier = sActualDateInRecentTimeEntier.trim();
			System.out.println(Date()+"Signed In Date in  Application is "+ sActualDateInRecentTimeEntier);
		}




		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();

		if(sActualTimeInRecentTimeEntier.equals(sTime) && sActualDateInRecentTimeEntier.equals(sDate))
		{
			System.out.println(Date()+"SignIn Time and time in Recent time entries are same");
			return true;
		}
		else
		{
			System.err.println("SignIn Time and time in Recent time entries are not same");
			return false;
		}


	}


	public void NavigatetoRecentTimeEntries() throws Exception
	{
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Recent_Time_Entries))).click();
		Thread.sleep(3000);

	}

	public boolean EditRecentTimeEntry(String sEmpname,String sStartDate,String sEndDate,String sVersion) throws Exception
	{
		if(_driver.findElements(By.xpath("//table[@id='EmployeeTime']/tbody/tr")).size()>0)
		{
			if(_driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).size()>0)
				{
					_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[3]/a/img")).click();
					Thread.sleep(1000);
					int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
					for(int i=1;i<=sRowCount;i++)
					{
						String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
						sText=sText.trim();
						System.out.println("sText is "+sText);
						if(sText.equals("Remove Sort"))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
							Thread.sleep(1000);
							break;
						}
					}	  
				}
				Thread.sleep(1000);
				if( _driver.findElements(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).size()>0)
				{
					_driver.findElement(By.xpath("//table[@id='EmployeeTime']/thead/tr[1]/th[4]/a/img")).click();
					Thread.sleep(1000);
					int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
					for(int i=1;i<=sRowCount;i++)
					{
						String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
						sText=sText.trim();
						System.out.println("sText is "+sText);
						if(sText.equals("Remove Sort"))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
							Thread.sleep(1000);
							break;
						}
					}	  
				}

				Thread.sleep(1000);
			}  
			int rowcount = _driver.findElements(By.xpath("//table[@id='EmployeeTime']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);

			_driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr["+rowcount+"]/td[2]/div/a")).click();
			Thread.sleep(3000);
			//   assertEquals("Employee Time Entry",_driver.getTitle());

		}
		else
		{
			if(_driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).size()>0)
			{
				if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).size()>0)
				{
					_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[3]/a/img")).click();
					Thread.sleep(1000);
					int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
					for(int i=1;i<=sRowCount;i++)
					{
						String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
						sText=sText.trim();
						System.out.println("sText is "+sText);
						if(sText.equals("Remove Sort"))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
							Thread.sleep(1000);
							break;
						}
					}	  
				}
				Thread.sleep(1000);
				if( _driver.findElements(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).size()>0)
				{
					_driver.findElement(By.xpath("//table[@id='employeeTimes']/thead/tr[1]/th[4]/a/img")).click();
					Thread.sleep(1000);
					int sRowCount =  _driver.findElements(By.xpath("//div[@id='gridSortControllerID']/ul/li")).size();
					for(int i=1;i<=sRowCount;i++)
					{
						String sText =  _driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a")).getText();
						sText=sText.trim();
						System.out.println("sText is "+sText);
						if(sText.equals("Remove Sort"))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li["+i+"]/a[text()='Remove Sort']")).click();
							Thread.sleep(1000);
							break;
						}
					}	  
				}
				Thread.sleep(1000);
			}	  
			int rowcount = _driver.findElements(By.xpath("//table[@id='employeeTimes']/tbody/tr")).size();
			System.out.println("Rowcount is "+rowcount);

			_driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr["+rowcount+"]/td[2]/div/a")).click();
			Thread.sleep(3000);
			//   assertEquals("Employee Time Entry",_driver.getTitle());

		}

		String sSDate =  _driver.findElement(By.xpath("//table[@id='NonChargeableTime']/tbody/tr[1]/td[4]/div")).getText();
		sSDate =sSDate.trim();
		System.out.println(Date()+"Start Date is "+sSDate);

		String sNCTimesText =  _driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Time_Entry_Non_Chargeable_Times))).getText();
		sNCTimesText =sNCTimesText.trim();
		System.out.println(Date()+"NC Text is "+sNCTimesText); 

		String sEmployeeName =  _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/div[1]/a")).getText();
		sEmployeeName =sEmployeeName.trim();
		System.out.println(Date()+"Employee Name is "+sEmployeeName);

		String sEDate =  _driver.findElement(By.xpath("//table[@id='NonChargeableTime']/tbody/tr[1]/td[6]/div")).getText();
		sEDate =sEDate.trim();
		System.out.println(Date()+"End Date is "+sEDate);

		if(sEmployeeName.equals(sEmpname) && sSDate.equals(sStartDate) && sNCTimesText.equals("Non-Chargeable Times") && sEDate.equals(sEndDate))      
		{
			return true;
		}
		else
		{
			System.err.println("sEmployeeName is "+sEmployeeName+" sSDate is "+sSDate+" sNCTimesText is "+sNCTimesText);
			return false;
		}

	}
	public void SignoutDCL(String sSheetname,String sSN,String sTC,String sCN) throws Exception
	{
		boolean sFlag = false;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignOut))).click();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		System.out.println(Date()+"Signed out time is "+ sCurrentDate);
		dbConnection.UpdateFunction(sSheetname, sSN, sTC, sCN, sCurrentDate);

		NewFileNamePath =  TakeScreenShot.getDestinationFile("SignIn");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(Date()+NewFileNamePath);

		Thread.sleep(5000);
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_SignOut_Text))).size()>0)
		{
			System.out.println(Date()+"Signed out successfully!!!");
		}
		else
		{
			System.err.println("Not able to sign out");
		}
	}

	public void SignoutFromDCL() throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen)));
			Thread.sleep(2000);
			CommonFunctions.getPopupMessage(_driver);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut)));
		}

		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen)));
			Thread.sleep(2000);
			CommonFunctions.getPopupMessage(_driver);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut)));
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignOut))).click();
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_SignOut_Text))).size()>0)
		{
			System.out.println(Date()+"Signed out successfully!!!");
		}
		else
		{
			System.err.println("Not able to sign out");
		}
	}

	public boolean SignInToDCL(String sEmpCode ) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		boolean sFlag= false;
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}

		LoginToDCL(sEmpCode);

		ResetDCLToDefaultState();

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).click();
		CommonFunctions.Wait(_driver,  By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		boolean sSignedIn =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		return  sSignedIn ;    
	}

	public boolean LoginToDCL(String sEmpCode ) throws Exception
	{

		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).sendKeys(sEmpCode);

		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Login))).click();
		}

		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		boolean sSignedOut =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut_Text)));
		return  sSignedOut ;    
	} 

	public boolean DCLLogin(String sEmpCode ) throws Exception
	{

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCl_Employee_Code)), sEmpCode);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).sendKeys(sEmpCode);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		boolean sSignedOut =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut_Text)));
		return  sSignedOut ;    
	}

	public boolean DCLLogin1(String sEmpCode ) throws Exception
	{
		String originalHandle1 = _driver.getWindowHandle();
		String  sGetTitle1 = _driver.getTitle();
		_driver.findElement(By.xpath("//div[@id='login_wrap']/table/tbody/tr[2]/td/div/div/div/div[2]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1)
			{
				if(_driver.switchTo().window(windowId1).getTitle().contains("Please select a Employee")) 
				{
					Search(sEmpCode,"id");
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div")).click();
					_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
				}
			}
		}
		_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		boolean sSignedOut =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut_Text)));
		return  sSignedOut ;    
	}

	public boolean DCLSignIn(String sEmpCode ) throws Exception
	{

		LoginToDCL(sEmpCode);
		ResetDCLToDefaultState();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).click();
		CommonFunctions.Wait(_driver,  By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		boolean sSignedIn =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		return  sSignedIn ;

	}
	public boolean SignIn(String sEmpCode ) throws Exception
	{

		ResetDCLToDefaultState();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).click();
		CommonFunctions.Wait(_driver,  By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		boolean sSignedIn =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));
		return  sSignedIn ;

	}



	public boolean StartJobActivity(String sJob,String sJobPart,String sActivityCode,String ACText,String ProdsUnit ,String sHour) throws Exception
	{
		boolean sFlag = false,sFlag1 = false;
		String 	sACSetValue= "//div[@id='scrollableContent']//h4[@title='"+ACText+"']";
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));

		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(sJob);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+" Job selected");
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Part))).click();
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), sActivityCode);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+" AC selected");
		int optionCount = _driver.findElements(By.xpath("//select[@name='JobPartKey']/descendant::option")).size();
		System.out.println(Date() + " optionCount is "+optionCount);
		int count =0;
		while(optionCount<=2 && count < 1)
		{
			count++;
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(sJob);
			Thread.sleep(3000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			System.out.println(Date()+" Job selected");
			Thread.sleep(5000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Part))).click();
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), sActivityCode);
			Thread.sleep(5000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			System.out.println(Date()+" AC selected");
			Thread.sleep(1000);
			optionCount = _driver.findElements(By.xpath("//select[@name='JobPartKey']/descendant::option")).size();
			System.out.println(Date() + " After sometime optionCount is "+optionCount);
		}
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)), sJobPart);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+" Jobpart selected");

		if(ProdsUnit!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys(ProdsUnit); 
		}

		if(sHour!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).sendKeys(sHour);
		}

		if( _driver.findElements(By.xpath(sACSetValue)).size()>0)
		{
			sFlag = true;
		}
		else
		{
			sFlag = false;
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(Keys.TAB);
		assertEquals("Starting Job Entry", _driver.getTitle());
		if(sFlag == true)
		{
			System.out.println(Date()+"Charge basis is corrert");
			return true;
		}
		else
		{
			// System.err.println("Charge basis is incorrert");
			return false;
		}


	}

	public void StartJob() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));
		assertEquals("Starting Job Entry", _driver.getTitle());

	}
	public void DCLStartJobActivity(String[] a) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));
		assertEquals("Starting Job Entry", _driver.getTitle());
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(a[1]);
		Thread.sleep(5000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+"Job Entered");
		Thread.sleep(5000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), a[3]);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+"AC selected");
		Thread.sleep(5000);

		if(a[4]!="")
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Rate)), a[4]);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			System.out.println(Date()+"Rate selected");
			Thread.sleep(2000);

		}
		if(a[5]!="")
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Shift)), a[5]);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			System.out.println(Date()+" Shift selected");
			Thread.sleep(2000); 
		}
		if(a[7]!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).sendKeys(a[7]);
			System.out.println(Date()+"Hour Entered");
		}



		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			Thread.sleep(2000);
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		Thread.sleep(2000);



	}

	public void JobDetails() throws Exception
	{
		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Shift))).size()>0)
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Shift)), "1");
			Thread.sleep(1000);
			System.out.println(Date()+"Shift Entered");
		}

		/* if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Begin_Count))).size()>0)
	  {
		  _driver.findElement(By.name(Locators.getProperty(Locators.DCL_Begin_Count))).sendKeys("100");
		 System.out.println(Date()+"Begin Entered");
	  }*/


		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Rate))).size()>0)
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Rate)), "1");
			Thread.sleep(1000);
			System.out.println(Date()+"Rate Entered");
		}

		if(_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Complete))).isDisplayed() && _driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
			Thread.sleep(2000);
		}

		/* 
		 *   if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Hour))).size()>0)
	  {
		  _driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).sendKeys("1");
		 System.out.println(Date()+"Hour Entered");
	  }
	   if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
	  {
		  _driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys("1");
		 System.out.println(Date()+"DCL_ProdsUnit Entered");
	  }
		 */
	}
	public boolean SelectNonPlannedReason(String sReason,String sText) throws Exception
	{

		String 	sNonPlannedReasonText= "h4[title='"+sText+"'] > label";
		System.out.println(Date()+"Verify Non Planned Reason Text");
		if(_driver.findElement(By.cssSelector(sNonPlannedReasonText)).isDisplayed())
		{
			System.out.println(Date()+"Able to see Non Planned Reason Text");
			if(!sReason.equals(""))
			{
				System.out.println(Date()+"Select NPR");
				CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Non_Planned_Reason)), sReason);
				Thread.sleep(5000);
			}
			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Non Planned Reason Text");

			return false;
		}
	}

	public String GetBeginLocator(String sLoc )
	{
		ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLoc);
		String sBeginLoc = null;
		switch (sAttribute) 
		{
		case askCounts:
			sBeginLoc = Locators.getProperty(Locators.DCL_Begin_Count);
			break;
		case askPostage:
			sBeginLoc = Locators.getProperty(Locators.DCL_Begin_Meter);
			break;
		case askNotes: 
			sBeginLoc = Locators.getProperty(Locators.DCL_Notes);
			break;
		case askNonPlannedReason: 
			sBeginLoc = Locators.getProperty(Locators.DCL_Notes);
			break;
		}
		return sBeginLoc;
	}

	public void DCLJobCompletion(String sOption) throws Exception
	{
		if(sOption.equals("Elapsed Time"))
		{
			JobComplete("","");
		}
		else
		{
			DCLComplete();
			AddTransaction("John Adams");
		}
	}

	public void Combo15( String sEmpID ,String Option,String BeginLocator, String EndLocator,String sBegText,String sEndText,String ReqString,String ActCode) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String TestCaseNote = "";
		String sJob	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "Job");
		String sJobPart	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "JobPart");
		String sACtext="The number of production units used on this transaction. Numbers and Decimal Point.";
		//String[] sTimeChargedoption = {"Elapsed Time","Hours Entered","Move Job Only","No Hours","Preset Hrs/Unit"};
		String[] sTimeChargedoption = {"Elapsed Time","Hours Entered","Move Job Only","No Hours"};
		String[] sACOthersettings = {"Yes","Required","No"};
		boolean[] OtherOptB = {true,true,false};		// This is to validate the True False Conditions
		boolean ECheckBegLoc = false;
		boolean CheckBegLoc  =false,CheckEndLoc = false,TestCaseResult= false ,TestCaseResult2 = false,CheckReqMsgS = false , CheckReqMsgS1 = false;
		String sScenario = "AC_Workflow";
		String sTextCase = null,TextCase = "";

		for (int i=0; i<sTimeChargedoption.length; i++)
		{
			System.err.println("Iteration for Time Charge Option is: "+i+" and sTimeChargedoption is: "+sTimeChargedoption[i]);
			System.out.println(Date()+"Navigate to DC- Activity Code" );
			sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	
			//NavigateToActivityCodeMaintainance();
			//System.out.println(Date()+"Search Activity Code" );
			// FindActivityCode(ActCode);
			System.out.println(Date()+"Select Time Charged "+sTimeChargedoption[i]);
			ActivityCodeTimeCharged(sTimeChargedoption[i],"1","",false);
			Home();
			boolean sAlert = PO.isAlertPresent();
			if(sAlert == true)
			{
				_driver.switchTo().alert().accept();
			}
			for (int j=0; j<sACOthersettings.length; j++)
			{
				System.err.println("Iteration for Other Settings is: "+j+" and sACOthersettings is: "+sACOthersettings[j]);

				System.out.println(Date()+"Navigate to DC- Activity Code" );
				sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
				_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	
				sTextCase = Option+"_"+sTimeChargedoption[i]+"_"+sACOthersettings[j];
				TextCase += "Workflow of TestCase " + sTextCase ;
				System.err.println("TextCase is: "+sTextCase);					
				System.out.println(Date()+"Workflow of TestCase "+sTextCase);
				System.out.println(Date()+"Select OtherSetting  "+sACOthersettings[j] );
				if(!sTimeChargedoption[i].equals("Preset Hrs/Unit"))
				{
					ACOtherSettings(Option,sACOthersettings[j],ActCode);
					String sVersion = FetchVersion();
					if(Integer.valueOf(sVersion)< 27)
					{
						_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Production_Units_Charged))).click(); 

						CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)));
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), "No");
						Thread.sleep(1000);
					}
					else
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), "No");
						Thread.sleep(1000);
					}
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
				Home();
				sAlert = PO.isAlertPresent();
				if(sAlert == true)
				{
					_driver.switchTo().alert().accept();
				}
				System.out.println(Date()+"Navigate to Data Collection Login Page" );
				NavigateToDataCollectionLogin();
				SignInToDCL(sEmpID);
				StartJobActivity(sJob, sJobPart, ActCode,sACtext,"","");	
				//ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLocators[j]);
				System.err.println("OtherOptB is: "+OtherOptB[j]);
				ECheckBegLoc = OtherOptB[j];
				if(sTimeChargedoption[i].equals("Elapsed Time"))
				{

					ECheckBegLoc = VerifyElementPresent(BeginLocator,"",sBegText);
					System.out.println("Add Trans Pg - BeginLocator Present = " + ECheckBegLoc);
					TestCaseNote = "Add Trans Pg - BeginLocator Present = " + ECheckBegLoc ;
					if (!Option.equals("askNotes") &&  sACOthersettings[j] == "Required")
					{
						AddTransaction("John Adams");
						CheckReqMsgS = VerifyError(ReqString) ;
						System.out.println("CheckReqMsgS= " + CheckReqMsgS);
						CheckBegLoc = VerifyElementPresent(BeginLocator,"1",sBegText);
						System.out.println("CheckBegLoc= " + CheckBegLoc);
					}
					AddTransaction("John Adams");
					StopJobActivity("","");	

				}
				CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
				System.out.println("CheckBegLoc= " + CheckBegLoc);
				if(!EndLocator.equals("") )
				{
					CheckEndLoc =	VerifyElementPresent(EndLocator,"",sEndText);
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				else if(!sTimeChargedoption[i].equals("Preset Hrs/Unit"))
				{
					CheckEndLoc =	VerifyElementPresent(EndLocator,"",sEndText);
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				else
				{
					CheckEndLoc = OtherOptB[j];
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				TestCaseNote = "BeginLocator Present = " + CheckBegLoc + " & EndLocator Present = " + CheckEndLoc;

				if (CheckBegLoc == OtherOptB[j] && CheckEndLoc == OtherOptB[j] && ECheckBegLoc == OtherOptB[j])
				{
					TestCaseResult = true ;	
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
				}
				else
				{
					TestCaseResult = false ;
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
				}	

				if (sACOthersettings[j] == "Required" && TestCaseResult == true)
				{	
					//Required Option Condition		
					AddTransaction("");
					System.out.println(Date()+"Verify Begin and End  Error Msg");
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
					CheckReqMsgS = VerifyError(ReqString) ;
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"1",sBegText);
					AddTransaction("");
					//	DCLJobCompletion(sTimeChargedoption[i]);
					if(!EndLocator.equals(""))
					{
						CheckReqMsgS1 = VerifyError(ReqString);
						CheckEndLoc =	VerifyElementPresent(EndLocator,"10",sEndText);
					}
					else
						CheckReqMsgS1 = OtherOptB[j];
					TestCaseNote += "ReqMessage Present on empty values = " + CheckReqMsgS ;
					TestCaseNote += "ReqMessage Present on entering values = " + CheckReqMsgS ;
					if (CheckReqMsgS == OtherOptB[j] && CheckReqMsgS1 == OtherOptB[j])
					{
						TestCaseResult2 = true ;	
					}
					else
					{
						TestCaseResult2 = false ;
					}	

					if( TestCaseResult == true && TestCaseResult2 == true)
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
					}
					else
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
						//	 Assert.fail("Start Job Begin Count="+ sText + " , Stop Job Begin Count = "+sBeginCount+" and End Count "+sEndCount );
					}
				}
				AddTransaction("");
				SignoutFromDCL();	

				//System.out.println(Date()+"Navigate to DC- Activity Code" );
				//NavigateToActivityCodeMaintainance();
				//System.out.println(Date()+"Search Activity Code" );
				//FindActivityCode(ActCode);


			}
		}

	}

	public void Combo12( String sEmpID ,String Option,String BeginLocator, String EndLocator,String sBegText,String sEndText,String ReqString,String ActCode) throws Exception
	{
		String TestCaseNote = "";
		String sJob	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "Job");
		String sJobPart	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "JobPart");
		String sACtext="The number of production units used on this transaction. Numbers and Decimal Point.";
		String[] sTimeChargedoption = {"Elapsed Time","Hours Entered","Move Job Only","No Hours"};
		String[] sACOthersettings = {"Yes","Required","No"};
		boolean[] OtherOptB = {true,true,false};		// This is to validate the True False Conditions
		boolean ECheckBegLoc = false;
		boolean CheckBegLoc  =false,CheckEndLoc = false,TestCaseResult= false ,TestCaseResult2 = false,CheckReqMsgS = false , CheckReqMsgS1 = false;
		String sScenario = "AC_Workflow";
		String sTextCase = null,TextCase = "";

		for (int i=0; i<sTimeChargedoption.length; i++)
		{
			System.out.println(Date()+"Navigate to DC- Activity Code" );
			sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	

			System.out.println(Date()+"Select Time Charged "+sTimeChargedoption[i]);
			ActivityCodeTimeCharged(sTimeChargedoption[i],"1","",false);
			Home();
			for (int j=0; j<sACOthersettings.length; j++)
			{
				System.out.println(Date()+"Navigate to DC- Activity Code" );
				sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
				_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	
				sTextCase = Option+"_"+sTimeChargedoption[i]+"_"+sACOthersettings[j];
				TextCase += "Workflow of TestCase " + sTextCase ;
				System.out.println(Date()+"Workflow of TestCase "+sTextCase);
				System.out.println(Date()+"Select OtherSetting  "+sACOthersettings[j] );
				if(!sTimeChargedoption[i].equals("Preset Hrs/Unit"))
				{
					ACOtherSettings(Option,sACOthersettings[j],ActCode);
					String sVersion = FetchVersion();
					if(Integer.valueOf(sVersion)< 27)
					{
						_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Production_Units_Charged))).click(); 

						CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)));
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), "No");
						Thread.sleep(1000);
					}
					else
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), "No");
						Thread.sleep(1000);
					}
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
				Home();

				System.out.println(Date()+"Navigate to Data Collection Login Page" );
				NavigateToDataCollectionLogin();
				SignInToDCL(sEmpID);
				StartJobActivity(sJob, sJobPart, ActCode,sACtext,"","");	
				//ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLocators[j]);
				ECheckBegLoc = OtherOptB[j];
				if(sTimeChargedoption[i].equals("Elapsed Time"))
				{

					ECheckBegLoc = VerifyElementPresent(BeginLocator,"",sBegText);
					System.out.println("Add Trans Pg - BeginLocator Present = " + ECheckBegLoc);
					TestCaseNote = "Add Trans Pg - BeginLocator Present = " + ECheckBegLoc ;
					if (!Option.equals("askNotes") &&  sACOthersettings[j] == "Required")
					{
						AddTransaction("John Adams");
						CheckReqMsgS = VerifyError(ReqString) ;
						System.out.println("CheckReqMsgS= " + CheckReqMsgS);
						CheckBegLoc = VerifyElementPresent(BeginLocator,"1",sBegText);
						System.out.println("CheckBegLoc= " + CheckBegLoc);
					}
					AddTransaction("John Adams");
					StopJobActivity("","");	

				}
				CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
				System.out.println("CheckBegLoc= " + CheckBegLoc);
				if(!EndLocator.equals("") )
				{
					CheckEndLoc =	VerifyElementPresent(EndLocator,"",sEndText);
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				else if(!sTimeChargedoption[i].equals("Preset Hrs/Unit"))
				{
					CheckEndLoc =	VerifyElementPresent(EndLocator,"",sEndText);
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				else
				{
					CheckEndLoc = OtherOptB[j];
					System.out.println("CheckEndLoc= " + CheckEndLoc);
				}
				TestCaseNote = "BeginLocator Present = " + CheckBegLoc + " & EndLocator Present = " + CheckEndLoc;

				if (CheckBegLoc == OtherOptB[j] && CheckEndLoc == OtherOptB[j] && ECheckBegLoc == OtherOptB[j])
				{
					TestCaseResult = true ;	
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
				}
				else
				{
					TestCaseResult = false ;
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
				}	

				if (sACOthersettings[j] == "Required" && TestCaseResult == true)
				{	
					//Required Option Condition		
					AddTransaction("");
					System.out.println(Date()+"Verify Begin and End  Error Msg");
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
					CheckReqMsgS = VerifyError(ReqString) ;
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"1",sBegText);
					AddTransaction("");
					//	DCLJobCompletion(sTimeChargedoption[i]);
					if(!EndLocator.equals(""))
					{
						CheckReqMsgS1 = VerifyError(ReqString);
						CheckEndLoc =	VerifyElementPresent(EndLocator,"10",sEndText);
					}
					else
						CheckReqMsgS1 = OtherOptB[j];
					TestCaseNote += "ReqMessage Present on empty values = " + CheckReqMsgS ;
					TestCaseNote += "ReqMessage Present on entering values = " + CheckReqMsgS ;
					if (CheckReqMsgS == OtherOptB[j] && CheckReqMsgS1 == OtherOptB[j])
					{
						TestCaseResult2 = true ;	
					}
					else
					{
						TestCaseResult2 = false ;
					}	

					if( TestCaseResult == true && TestCaseResult2 == true)
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
					}
					else
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
						//	 Assert.fail("Start Job Begin Count="+ sText + " , Stop Job Begin Count = "+sBeginCount+" and End Count "+sEndCount );
					}
				}
				AddTransaction("");
				SignoutFromDCL();	

				//System.out.println(Date()+"Navigate to DC- Activity Code" );
				//NavigateToActivityCodeMaintainance();
				//System.out.println(Date()+"Search Activity Code" );
				//FindActivityCode(ActCode);


			}
		}

	}

	public void Combo15_test( String sEmpID ,String Option,String BeginLocator, String EndLocator,String sBegText,String sEndText,String ReqString,String ActCode) throws Exception
	{
		String TestCaseNote = "";
		DCPage DC = new DCPage(_driver);	
		String sJob	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "Job");
		String sJobPart	=	dbConnection.ReadFunction("DC", "Prerequisites", "Prerequisites", "JobPart");
		String sACtext="The number of production units used on this transaction. Numbers and Decimal Point.";
		String[] sTimeChargedoption = {"Preset Hrs/Unit","Elapsed Time","Hours Entered","Move Job Only","No Hours","Preset Hrs/Unit"};
		String[] sACOthersettings = {"Yes","Required","No"};
		boolean[] OtherOptB = {true,true,false};		// This is to validate the True False Conditions
		boolean ECheckBegLoc = false;
		boolean CheckBegLoc  =false,CheckEndLoc = false,TestCaseResult= false ,TestCaseResult2 = false,CheckReqMsgS = false , CheckReqMsgS1 = false;
		String sScenario = "AC_Workflow";
		String sTextCase = null,TextCase = "";

		for (int i=0; i<sTimeChargedoption.length; i++)
		{
			System.out.println(Date()+"Navigate to DC- Activity Code" );
			sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	
			//NavigateToActivityCodeMaintainance();
			//System.out.println(Date()+"Search Activity Code" );
			// FindActivityCode(ActCode);
			System.out.println(Date()+"Select Time Charged "+sTimeChargedoption[i]);
			ActivityCodeTimeCharged(sTimeChargedoption[i],"1","",false);
			Home();
			for (int j=0; j<sACOthersettings.length; j++)
			{
				System.out.println(Date()+"Navigate to DC- Activity Code" );
				sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
				_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+ActCode);	
				sTextCase = Option+"_"+sTimeChargedoption[i]+"_"+sACOthersettings[j];
				TextCase += "Workflow of TestCase " + sTextCase ;
				System.out.println(Date()+"Workflow of TestCase "+sTextCase);
				System.out.println(Date()+"Select OtherSetting  "+sACOthersettings[j] );
				if(!sTimeChargedoption[i].equals("Preset Hrs/Unit"))
				{
					ACOtherSettings(Option,sACOthersettings[j],ActCode); 
				}
				DC.Home();
				System.out.println(Date()+"Navigate to Data Collection Login Page" );
				NavigateToDataCollectionLogin();
				SignInToDCL(sEmpID);
				StartJobActivity(sJob, sJobPart, ActCode,sACtext,"","");	
				//ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLocators[j]);
				ECheckBegLoc = OtherOptB[j];
				if(sTimeChargedoption[i].equals("Elapsed Time"))
				{

					ECheckBegLoc = VerifyElementPresent(BeginLocator,"",sBegText);
					TestCaseNote = "Add Trans Pg - BeginLocator Present = " + ECheckBegLoc ;
					if (!Option.equals("askNotes") &&  sACOthersettings[j] == "Required")
					{
						AddTransaction("John Adams");
						CheckReqMsgS = VerifyError(ReqString) ;
						CheckBegLoc = VerifyElementPresent(BeginLocator,"1",sBegText);
					}
					AddTransaction("John Adams");
					StopJobActivity("","");	

				}

				CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
				if(!EndLocator.equals("") || sTimeChargedoption[i].equals("Preset Hrs/Unit"))
					CheckEndLoc =	VerifyElementPresent(EndLocator,"",sEndText);
				else
					CheckEndLoc = OtherOptB[j];

				TestCaseNote = "BeginLocator Present = " + CheckBegLoc + " & EndLocator Present = " + CheckEndLoc;

				if (CheckBegLoc == OtherOptB[j] && CheckEndLoc == OtherOptB[j] && ECheckBegLoc == OtherOptB[j])
				{
					TestCaseResult = true ;	
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
				}
				else
				{
					TestCaseResult = false ;
					dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
				}	

				if (sACOthersettings[j] == "Required" && TestCaseResult == true)
				{	
					//Required Option Condition		
					AddTransaction("");
					System.out.println(Date()+"Verify Begin and End  Error Msg");
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"",sBegText);
					CheckReqMsgS = VerifyError(ReqString) ;
					CheckBegLoc =	VerifyElementPresent(BeginLocator,"1",sBegText);
					AddTransaction("");
					//	DCLJobCompletion(sTimeChargedoption[i]);
					if(!EndLocator.equals(""))
					{
						CheckReqMsgS1 = VerifyError(ReqString);
						CheckEndLoc =	VerifyElementPresent(EndLocator,"10",sEndText);
					}
					else
						CheckReqMsgS1 = OtherOptB[j];
					TestCaseNote += "ReqMessage Present on empty values = " + CheckReqMsgS ;
					TestCaseNote += "ReqMessage Present on entering values = " + CheckReqMsgS ;
					if (CheckReqMsgS == OtherOptB[j] && CheckReqMsgS1 == OtherOptB[j])
					{
						TestCaseResult2 = true ;	
					}
					else
					{
						TestCaseResult2 = false ;
					}	

					if( TestCaseResult == true && TestCaseResult2 == true)
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "PASS");
					}
					else
					{
						dbConnection.UpdateFunction("DC", sScenario, sTextCase, "Execution", "FAIL");
						//	 Assert.fail("Start Job Begin Count="+ sText + " , Stop Job Begin Count = "+sBeginCount+" and End Count "+sEndCount );
					}
				}
				AddTransaction("");
				SignoutFromDCL();	

				//System.out.println(Date()+"Navigate to DC- Activity Code" );
				//NavigateToActivityCodeMaintainance();
				//System.out.println(Date()+"Search Activity Code" );
				//FindActivityCode(ActCode);


			}
		}

	}
	public void GotoActionScreen() throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).click();
		Thread.sleep(2000);
		/*  Alert alert = _driver.switchTo().alert();
      if (acceptNextAlert)
      {
        alert.accept();
        Thread.sleep(4000);
      } else 
      {
        alert.dismiss();
      }
	  CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity)));
	 // assertEquals("Employee Activity for*", _driver.getTitle());
		 */
	}


	public void ReturnToActionScreen() throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver,By.xpath(Locators.getProperty(Locators.DCL_SignOut)));
		//assertEquals("Employee Activity for  "+sUN, _driver.getTitle());
	}

	public void AddTransaction(String sEmpName) throws Exception
	{


		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))).size()>0) 
		{
			DCLComplete();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))).click();
			Thread.sleep(2000);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity)));
		}
		else if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete))).size()>0)
		{
			JobComplete("","");
		}
		else if( _driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).size()>0)
		{
			JobComplete("","");
		}
		else
		{
			System.out.println(Date()+"Not able to find Job complete or add transaction button");
		}
	}

	public void DCLAddTransaction(String sEmpName) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity)));
		String sTitle =  _driver.getTitle();
		assertTrue(sTitle, sTitle.contains(sEmpName));	 
	}

	public boolean VerifyAskCountError(String sActivityCode)
	{
		String xpath= "//span[contains(text(),'Activity Code ["+sActivityCode+" - NewJob/No Activity] requires a value for Counts')]";

		if(_driver.findElements(By.xpath(xpath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Activity Code ["+sActivityCode+"] requires a value for Counts" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Activity Code ["+sActivityCode+"] requires a value for Counts" );
			return false;
		}
	}

	public boolean VerifyError(String sReqString)
	{
		String sPath= "//span[contains(text(),'"+sReqString+"')]";
		System.out.println("sPath is "+sPath);
		if(_driver.findElements(By.xpath(sPath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Message" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Message" );
			return false;
		}
	}
	public boolean VerifyAskProdUnitError(String sActivityCode)
	{
		String xpath= "//span[contains(text(),'Activity Code ["+sActivityCode+" - automation test] requires a value for Production Units')]";

		if(_driver.findElements(By.xpath(xpath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Activity Code ["+sActivityCode+"] requires a value for Production Units" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Activity Code ["+sActivityCode+"] requires a value for Production Units" );
			return false;
		}
	}
	public boolean VerifyBeginMeterError(String sActivityCode)
	{
		String xpath= "//span[contains(text(),'Activity Code ["+sActivityCode+" - NewJob/No Activity] requires a value for Meters')]";

		if(_driver.findElements(By.xpath(xpath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Activity Code ["+sActivityCode+"] requires a value for Meters" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Activity Code ["+sActivityCode+"] requires a value for Meters" );
			return false;
		}
	}

	public boolean VerifyNotesError(String sActivityCode)
	{
		String xpath= "//span[contains(text(),'Activity Code ["+sActivityCode+" - NewJob/No Activity] requires a value for Notes')]";

		if(_driver.findElements(By.xpath(xpath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Activity Code ["+sActivityCode+"] requires a value for Notes" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Activity Code ["+sActivityCode+"] requires a value for Notes" );
			return false;
		}
	}

	public boolean VerifyNonPlannedReasonError(String sActivityCode)
	{
		String xpath= "//span[contains(text(),'Activity Code ["+sActivityCode+" - NewJob/No Activity] requires a Reason. Please choose a Reason Code.')]";

		if(_driver.findElements(By.xpath(xpath)).size()>0)
		{
			System.out.println(Date()+"Able to see error Activity Code ["+sActivityCode+"] requires a value for Notes" );
			return true;
		}
		else
		{
			System.err.println("Not Able to see error Activity Code ["+sActivityCode+"] requires a value for Notes" );
			return false;
		}
	}
	public boolean BeginCount(String sCount,String sText) throws Exception
	{

		String 	sBeginCountText=  "h4[title='"+sText+"'] > label";
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Begin_Count))).clear();
		if(sCount!="")   
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Begin_Count))).sendKeys(sCount);
			Thread.sleep(1000);
		}

		if(_driver.findElement(By.cssSelector(sBeginCountText)).isDisplayed() && _driver.findElements(By.xpath("//label[text()='Begin Count']")).size()>0 )
		{
			System.out.println(Date()+"Able to Begin Count Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Begin Count Text");

			return false;
		}
	}
	public boolean VerifyElementPresent(String sLoc,String sValue,String sText) throws Exception
	{
		boolean sFlag1 = false;
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath("//label[text()='"+sText+"']")); ;
		if(sFlag == true)
		{
			if(_driver.findElement(By.name(sLoc)).getLocation().x >0 || _driver.findElement(By.name(sLoc)).getLocation().y > 0 )
			{
				if(sLoc.equals(Locators.getProperty(Locators.DCL_Non_Planned_Reason)) || sLoc.equals(Locators.getProperty(Locators.DCL_Complete)))
				{
					System.out.println(Date()+"Select NPR");
					CommonFunctions.selectDropdown(_driver, By.name(sLoc), sValue);
					Thread.sleep(5000);
				}
				else
				{
					_driver.findElement(By.name(sLoc)).clear();
					_driver.findElement(By.name(sLoc)).sendKeys(sValue);
				}
				Thread.sleep(1000);

				System.out.println(Date()+"Element Present");
				return sFlag1 = true;
			}
			else
			{
				System.out.println(Date()+"Element Not Present");
				return false;
			}
		}
		else
		{
			System.out.println(Date()+"Element Not Present");
			return false;
		}
		/* if(_driver.findElement(By.name(sLoc)).isDisplayed() )
		{
		  _driver.findElement(By.name(sLoc)).clear();
		   _driver.findElement(By.name(sLoc)).sendKeys(sValue);
		  Thread.sleep(1000);

			System.out.println(Date()+"Element Present");
			return true;
		}
		else
		{
			System.out.println(Date()+"Element Not Present");
			return false;
		}

		 */
	}
	public boolean VerifyACSettingsDCL(String sLoc,String sCount,String sText,String sLabel) throws Exception
	{

		String 	sNewtText=  "h4[title='"+sText+"'] > label";

		if(sCount!="")
		{
			_driver.findElement(By.name(sLoc)).sendKeys(sCount);
			Thread.sleep(1000);
		}

		if(_driver.findElement(By.cssSelector(sNewtText)).isDisplayed() && _driver.findElements(By.xpath("//label[text()='"+sLabel+"']")).size()>0 )
		{
			System.out.println(Date()+"Able to Begin Count Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Begin Count Text");

			return false;
		}
	}
	public boolean StopJobActivityBeginCount(String sCount,String sText) throws Exception
	{

		String 	sBeginCountText=  "//div[@id='scrollableContent']//div/h4[@title='"+sText+"']";

		if(sCount!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Begin_Count))).sendKeys(sCount);
			Thread.sleep(1000);
		}

		if(_driver.findElements(By.xpath(sBeginCountText)).size()>0  )
		{
			System.out.println(Date()+"Able to Begin Count Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Begin Count Text");

			return false;
		}
	}

	public boolean Complete(String sStatus,String sText) throws Exception
	{
		String 	sCompleteText= "h4[title='"+sText+"'] > label";
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			sCompleteText= "h4[title='Indicates whether this activity was completed for this job. Select an available item.'] > label";
		}
		else
		{
			sCompleteText= "h4[title='"+sText+"'] > label";
		}
		if(sStatus!="")
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), sStatus);
			Thread.sleep(1000);
		}
		//_driver.findElements(By.cssSelector(sCompleteText)).size() > 0 &&
		if( _driver.findElements(By.xpath("//label[text()='Complete']")).size()>0 )
		{
			System.out.println(Date()+"Able to see Complete Dropdown");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Complete DropDown");

			return false;
		}
	}

	public boolean BeginMeter(String sMeter,String sText) throws Exception
	{

		String 	sBeginMeterText= "h4[title='"+sText+"'] > label";////div[@id='scrollableContent']//div/h4[@title='"+sText+"']";

		if(sMeter!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Begin_Meter))).sendKeys(sMeter);
			Thread.sleep(1000);
		}

		if(_driver.findElement(By.cssSelector(sBeginMeterText)).isDisplayed() && _driver.findElements(By.xpath("//label[text()='Begin Meter']")).size()>0 )
		{
			System.out.println(Date()+"Able to Begin Meter Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Begin Meter Text");

			return false;
		}
	}

	public boolean EndMeter(String sMeter,String sText) throws Exception
	{

		String 	sEndMeterText= "h4[title='"+sText+"'] > label";////div[@id='scrollableContent']//div/h4[@title='"+sText+"']";

		if(sMeter!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_End_Meter))).sendKeys(sMeter);
			Thread.sleep(1000);
		}

		if(_driver.findElements(By.cssSelector(sEndMeterText)).size()>0 && _driver.findElements(By.xpath("//label[text()='End Meter']")).size()>0 )
		{
			System.out.println(Date()+"Able to End Meter Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to End Meter Text");

			return false;
		}
	}

	public boolean EndCount(String sCount,String sText) throws Exception
	{

		String 	sEndCountText= "//div[@id='scrollableContent']//div/h4[@title='"+sText+"']";
		if(sCount!= "")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_End_Count))).sendKeys(sCount);
			Thread.sleep(1000);

		}
		if(_driver.findElements(By.xpath(sEndCountText)).size()>0)
		{
			System.out.println(Date()+"Able to End Count Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to End Count Text");

			return false;
		}

	}

	public int RowCountPullMaterials()
	{
		int rowcount =CommonFunctions.RowCount(_driver, By.xpath("//table[@id = 'materails']/tbody/tr"));
		return rowcount;
	}

	public boolean Notes(String sNotes,String sText) throws Exception
	{

		String 	sNotesText= "h4[title='"+sText+"'] > label";////div[@id='scrollableContent']//div/h4[@title='"+sText+"']";

		if(sNotes!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Notes))).sendKeys(sNotes);
			Thread.sleep(1000);
		}

		if(_driver.findElement(By.cssSelector(sNotesText)).isDisplayed() && _driver.findElements(By.xpath("//label[text()='Notes']")).size()>0 )
		{
			System.out.println(Date()+"Able to Notes Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Notes Text");

			return false;
		}
	}

	public boolean StopActivityNotes(String sNotes,String sText) throws Exception
	{

		String 	sNotesText= "//div[@id='scrollableContent']//div/h4[@title='"+sText+"']";

		if(sNotes!="")
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Notes))).sendKeys(sNotes);
			Thread.sleep(1000);
		}

		if(_driver.findElements(By.xpath(sNotesText)).size()>0)
		{
			System.out.println(Date()+"Able to Notes Text");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to Notes Text");

			return false;
		}

	}


	public boolean CompleteJobActivity(String sJob,String sEmpName ) throws Exception
	{

		String sPath	= "//table[@id='JobTransaction_SignedOut']/tbody/tr[1][@class='c1']/td[6]/div/a[text()='"+sJob+"']";
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Completed_Job_Activity))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Completed_Job_Activity_Table)));
		assertEquals("Completed Job Activities for Employee "+sEmpName, _driver.getTitle());

		String sJobId = _driver.findElement(By.xpath(sPath)).getText();
		sJobId = sJobId.trim();
		System.out.println(Date()+"sJobId is " + sJobId);
		if(sJobId.equals(sJob))
		{
			System.out.println(Date()+"Able to See completed job activity");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();   
			CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.DCL_Sign_Disabled)));

			return true;
		}
		else
		{
			System.err.println("Not Able to See completed job activity");
			Assert.fail("Not Able to See completed job activity");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();	   
			CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.DCL_Sign_Disabled)));
			return false;
		}
	}

	public void StopJobActivity(String sJob,String sAC) throws Exception
	{


		String sTitle = "Completed Activity for Job "+sJob+" Part Activity "+sAC;
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Stop_Job_Activity))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Job_Activity))).click();
			CommonFunctions.waitForPageLoaded(_driver);
//			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Message)));
		}
	}

	public void StopMultipleJobActivity(String sVersion) throws Exception
	{
		StopJobActivity("", ""); 
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete))).size()>0 || _driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).size()>0)
		{
			JobComplete("John Adams","2");

		}
		else
		{

			if(Integer.valueOf(sVersion)< 27)
			{
				int sRowCount = _driver.findElements(By.xpath("//table[@id='JobTransaction']/tbody/tr")).size();
				System.out.println(Date()+"No of Activite Job are "+sRowCount );  
				if(sRowCount >0)
				{

					for(int i = 1 ; i<sRowCount ; i++ )
					{
						_driver.findElement(By.xpath("//table[@id='JobTransaction']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
						JobComplete("John Adams","2");
						StopJobActivity("", ""); 
					}

					JobComplete("John Adams","2");
				}
			}
			else
			{
				int sRowCount1 = _driver.findElements(By.xpath("//table[@id='JobCost']/tbody/tr")).size();
				System.out.println(Date()+"Number of Activite Job are "+sRowCount1 );  
				if(sRowCount1 >0)
				{

					for(int i = 1 ; i<sRowCount1 ; i++ )
					{
						_driver.findElement(By.xpath("//table[@id='JobCost']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
						JobComplete("John Adams","2");
						StopJobActivity("", ""); 
					}

					JobComplete("John Adams","2");
				}

			}



		}


	}

	public void DCLStopJobActivity(String sJob,String sAC) throws Exception
	{


		String sTitle = "Completed Activity for Job "+sJob+" Part Activity "+sAC;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Job_Activity))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Message)));
		assertEquals(sTitle, _driver.getTitle());


	}
	public String  VerifyStatusDLC()
	{
		String Status = _driver.findElement(By.cssSelector(Locators.getProperty(Locators.DCL_Status))).getText();
		return Status;
	}


	public boolean  VerifyAskProdUnit()
	{

		String sText = "h4[title='Prod Units / Desc'] > label";
		if(_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).isDisplayed())
		{
			System.out.println(Date()+"Able to see ASk Prod Unit");
			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see ASk Prod Unit");
			return false;
		}

	}

	public boolean  VerifyPullMaterials()
	{
		String sValue ="";
		String sText = "//legend[text()='Pull Materials']";
		sValue =  _driver.findElement(By.xpath("//table[@id='materials']/tbody/tr[1]/td[2]/div")).getText();
		sValue = sValue.trim();
		System.out.println(Date()+"Part is" + sValue);

		if(_driver.findElements(By.xpath(sText)).size()>0 && sValue.equals("01"))
		{
			System.out.println(Date()+"Able to see Pull Materails Text");
			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Pull Materails Text");
			return false;
		}

	}
	public boolean  VerifyPullMaterialsJob()
	{

		String sText = "//legend[text()='Pull Materials']";


		if(_driver.findElements(By.xpath(sText)).size()>0)
		{
			System.out.println(Date()+"Able to see Pull Materails Text");
			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Pull Materails Text");
			return false;
		}

	}
	public String FetchPlannedQty()
	{
		String xpath="//table[@id='materials']/tbody/tr/td[3]/div";
		String sValue =  _driver.findElement(By.xpath(xpath)).getText();
		System.out.println(Date()+"Job Part is" + sValue);
		sValue.replaceAll("\\.0*$", "");
		return sValue;
	}

	public String FetchQtyToPull()
	{

		String sValue =  _driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity_To_Pull))).getText();
		System.out.println(Date()+"Qty to pull is" + sValue);
		sValue.replaceAll("\\.0*$", "");
		return sValue;
	}
	public void UpdateQtyToPull(String sQty) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity_To_Pull))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity_To_Pull))).sendKeys(sQty);

	}

	public String  VerifyJobPart()
	{

		String sText = "//legend[text()='Pull Materials']";
		int i =1;
		String sValue = "null";
		String xpath="//table[@id='materials']/tbody/tr["+i+"]/td[2]/div";
		int rowCount = _driver.findElements(By.xpath("//table[@id='materials']/tbody/tr")).size();
		System.out.println(Date()+"rowCount is" + rowCount);
		if(rowCount!=0)
		{

			for( i = 1; i<= rowCount ;i++)
			{
				sValue =  _driver.findElement(By.xpath(xpath)).getText();
				System.out.println(Date()+"Part is" + sValue);
			}
		}

		System.out.println(Date()+"Part is " + sValue);
		return sValue;

	}
	public void JobComplete(String sEmpName,String sProdUnit) throws Exception
	{

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys(sProdUnit);
		}

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
			Thread.sleep(1000);
		}
		
		if(  _driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).size()>0)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).click();
			Thread.sleep(1000);
		}
		else if(  _driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete))).size()>0)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete))).click();
		}

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_SignOut)));

	}

	public void DCLJobComplete(String sEmpName,String sProdUnit,String sVersion) throws Exception
	{

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys(sProdUnit);
		}

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Qty_To_Pull))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Qty_To_Pull))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Qty_To_Pull))).sendKeys("0");
		}

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
			Thread.sleep(1000);
		}

		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).click();
		}

		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_SignOut)));
		String sTitle =  _driver.getTitle();
		sTitle = sTitle.substring(23);
		System.out.println(Date()+sTitle);
		assertEquals(sEmpName, sTitle);
	}



	public void DCLComplete() throws Exception
	{

		/* if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
	  {
		  _driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
		  _driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys("1");
	  }*/

		boolean sCompleteFlag = CommonFunctions.IsElementVisible(_driver, "Complete");
		System.out.println("sCompleteFlag is "+sCompleteFlag);
		String 	sProdUnitText= "h4[title='The number of production units used on this transaction. Numbers and Decimal Point.'] > label";
		String sHourText = "h4[title='The number of hours worked on this transaction. Numbers and Decimal Point.'] > label";


		if(sCompleteFlag==true  &&_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Complete)), "Yes");
			Thread.sleep(1000);
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+"selected");


		boolean sProdUnitFlag = CommonFunctions.IsElementVisible(_driver, "Prod Units");
		System.out.println("sProdUnitFlag is "+sProdUnitFlag);
		boolean sHourUnitFlag = CommonFunctions.IsElementVisible(_driver, "Hours");
		System.out.println("sProdUnitFlag is "+sProdUnitFlag);

		//  if( _driver.findElement(By.xpath("//label[text()='Prod Units']")).getLocation().x > 0 || _driver.findElement(By.xpath("//label[text()='Prod Units']")).getLocation().y > 0 )
		if(sProdUnitFlag==true)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys("1");
		}
		if( sHourUnitFlag==true )
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Hour))).sendKeys("1");
		}



	}

	public void AskProdUnits(String sProdUnit) throws Exception
	{

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys(sProdUnit);
		}

	}


	public void DuplicateJob(String sJobname,String sProdUnit,String sActivityCode) throws Exception
	{

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DCL_ProdsUnit))).sendKeys(sProdUnit);
		}

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete_And_Duplicate))).size()>0)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Job_Complete_And_Duplicate))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity_And_Duplicate))).click();
		}

		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));

		assertEquals("Duplicating Activity for Job "+sJobname+" Part Activity "+sActivityCode, _driver.getTitle());
	}


	public boolean VerifyDuplicateActivityPage(String sJob, String sACCode)
	{
		String sJobName = _driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Job))).getAttribute("value");
		// String sJobName = _driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job))).getText();
		//sJobName = sJobName.trim();
		System.out.println(Date()+"sJobName on duplicate Activity page is " + sJobName);
		String sActCode = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)));
		System.out.println(Date()+"Selected Activity code on duplicate Activity page is " + sActCode);
		if(sJobName.equals(sJob) && sActCode.equals(sACCode))
		{

			return true; 
		}
		else
		{
			return false; 
		}
	}
	public void SignInDCL(String sEmpCode , String sSheetname,String sSN,String sTC,String sCN,String sCN2) throws Exception
	{

		String sTime =null;
		boolean sFlag= false;
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Employee_Code))).sendKeys(sEmpCode);
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Login))).click();
		}
		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_SignIn))).click();
		Thread.sleep(3000);

		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);

		NewFileNamePath =  TakeScreenShot.getDestinationFile("SignIn");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(Date()+NewFileNamePath);

		System.out.println(Date()+"Signed out time is "+ sCurrentDate);
		dbConnection.UpdateFunction(sSheetname, sSN, sTC, sCN, sCurrentDate);
		boolean sSignedIn =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.DCL_Signed_In)));   
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Recent_Time_Entries))).click();
		Thread.sleep(5000);
		//  assertEquals(sEmployeeName, _driver.getTitle());

		if(_driver.findElements(By.xpath("//table[@id='employeeTimes']/tbody/tr[1]/td[4]/div")).size()>0)
		{
			sTime = _driver.findElement(By.xpath("//table[@id='employeeTimes']/tbody/tr[1]/td[4]/div")).getText();

		}
		else
		{
			sTime = _driver.findElement(By.xpath("//table[@id='EmployeeTime']/tbody/tr[1]/td[4]/div")).getText();
		}
		sTime = sTime.trim();
		System.out.println(Date()+"Signed In Time in  Application is "+ sTime);
		dbConnection.UpdateFunction(sSheetname, sSN, sTC, sCN2, sCurrentDate);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen))).click();

		CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.DCL_Sign_Disabled)));
		if(_driver.findElements(By.id(Locators.getProperty(Locators.DCL_Sign_Disabled))).size()>0)
		{
			System.out.println(Date()+"Signed In successfully!!!");
		}
		else
		{
			System.err.println("Not able to SignIn");
		}

	}



	public boolean SearchCostCenter(String sCostCenter) throws Exception
	{
		String sTitle = "Cost Center "+sCostCenter;
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sCC = "//a[@href ='/epace/company:"+sCOMPANY+"/object/CostCenter/detail/"+sCostCenter+"']";
		String sCC1 = "//table[@id='CostCenter']/tbody/tr[1]/td[2]/div/a/img";
		_driver.findElement(By.linkText(Locators.getProperty(Locators.Department_CostCenter))).click();

		if(_driver.findElements(By.xpath(sCC1)).size()>0) 
		{
			_driver.findElement(By.xpath(sCC1)).click();

			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Department_Activity_Codes)));
			assertEquals(sTitle, _driver.getTitle());
			NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, sTitle);
			return true;
		}
		else if(_driver.findElements(By.xpath(sCC)).size()>0) 
		{
			_driver.findElement(By.xpath(sCC)).click();

			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Department_Activity_Codes)));
			assertEquals(sTitle, _driver.getTitle());
			NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, sTitle);
			return true;
		}
		else
		{
			System.out.println(Date()+"Not able Find Cost Center");
			//Assert.fail("Not able Find Cost Center");
		}
		return false;
	}

	public boolean SearchActivityCode(String sActivityCode) throws Exception
	{
		String sTitle = "Activity code "+sActivityCode;
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sAC = "//a[@href ='/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+sActivityCode+"']";
		String sAC1 = "//table[@id='ActivityCode']/tbody/tr[1]/td[2]/div/a/img";



		_driver.findElement(By.xpath(Locators.getProperty(Locators.Department_Activity_Codes))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));

		if(_driver.findElements(By.xpath(sAC1)).size()>0) 
		{
			System.out.println(Date()+"Able to find Activity Code ");
			_driver.findElement(By.xpath(sAC1)).click();
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Department_General_Info)));
			assertEquals(sTitle, _driver.getTitle());
			return true;
		}

		else if(_driver.findElements(By.xpath(sAC)).size()>0) 
		{
			System.out.println(Date()+"Able to find Activity Code ");
			_driver.findElement(By.xpath(sAC)).click();
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Department_General_Info)));
			assertEquals(sTitle, _driver.getTitle());
			return true;
		}
		else
		{
			// Assert.fail("Not able Find Cost Center");
			System.out.println(Date()+"Not able to find Activity Code ");
			return false;
		}
	}


	public boolean FindActivityCode(String sActivityCode) throws Exception
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sAC = "//a[@href ='/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+sActivityCode+"']/div";
		String sActivityCodePage = "Activity code "+sActivityCode;
		String sXpath	= "//a[text()='"+sActivityCode+"']";
		System.out.println(Date()+"Search Activity Code");	

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "Activity Code");
		String sSelectedValue = _driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_Dropdown))).getText();
		System.out.println(Date()+sSelectedValue);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(sActivityCode);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(3000);


		/*  
	  String sPage =  _driver.getTitle();
	  if(sPage.equals("Activity Codes") || sPage.equals("Planning Integration"))
	  {
		  _driver.findElement(By.xpath(sAC)).click();
		  Thread.sleep(2000);
		  CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.AC_TimeCharged)));
	  }


		 */ 
		Home();
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+sActivityCode);
		Thread.sleep(3000);
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);

		if(sActivityCodePage.equals(sTitle))
		{
			assertEquals(sActivityCodePage, _driver.getTitle());
			System.out.println(Date()+"Able to see Activity Code");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Activity Code");

			return false;
		}

	}



	public void AddInventoryItem(String ItemType, String DefaultPullType) throws Exception
	{

		String sXpath = "//table[@id='ActivityCodeInventoryItemTypee']/tbody/tr";

		int rowcount = _driver.findElements(By.xpath(sXpath)).size();
		int i = 1;
		int j = 0;
		if(rowcount!=0)
		{

			for(i =1 ;i<=rowcount ; i++)
			{
				String spath = "//table[@id='ActivityCodeInventoryItemTypee']/tbody/tr["+i+"]/td[3]/select";
				String spath2 =  "//table[@id='ActivityCodeInventoryItemTypee']/tbody/tr["+i+"]/td[3]/select[@name='ActivityCodeInventoryItemTypee.inventoryItemType']";
				String spath3 =  "//table[@id='ActivityCodeInventoryItemTypee']/tbody/tr["+i+"]/td[4]/select[@name='ActivityCodeInventoryItemTypee.activityPullQuantityType']";
				String sItem = CommonFunctions.GetSelectedOption(_driver, By.xpath(spath));
				Thread.sleep(1000);
				System.out.println(Date()+"sItem is " + sItem);
				sItem = sItem.trim();
				if(sItem.equals(ItemType))
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath(spath2), 1);
					Thread.sleep(1000);
					System.out.println(Date()+"Selected");
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath(spath3), 1);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					System.out.println(Date()+"Inventory Item already Present");
				}
				else
				{

					j++;
				}

				if(j == i)
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.AC_Materials_Add_New_Record))).click();
					Thread.sleep(2000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AC_Materials_Item_Type)), 1);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AC_MAterials_Default_Pull_Type)), 1);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
				}
			}

		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_Materials_Add_New_Record))).click();
			Thread.sleep(2000);
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AC_Materials_Item_Type)), 1);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AC_MAterials_Default_Pull_Type)), 1);
			Thread.sleep(1000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		}

	}


	public boolean SearchInventoryItem(String sInItem) throws Exception
	{
		String sInventoryPage = "Inventory Item "+sInItem;
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "ID");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(sInItem);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.II_QuantityPriceInfo)));


		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);

		if(sInventoryPage.equals(sTitle))
		{
			assertEquals(sInventoryPage, _driver.getTitle());
			System.out.println(Date()+"Able to see Activity Code");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Activity Code");

			return false;
		}

	}

	public boolean SearchJobPart(String sJob,String sPart) throws Exception
	{
		String sInventoryItemPage = "Job "+sJob+" part "+sPart;
		System.out.println(Date()+"sInventoryItemPage  is " + sInventoryItemPage);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sXpath = "//a[@href='/epace/company:"+sCOMPANY+"/object/JobPart/detail/"+sJob+"%3A"+sPart+"']/div";
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(sJob);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);

		_driver.findElement(By.xpath(sXpath)).click();
		CommonFunctions.Wait(_driver,By.xpath(Locators.getProperty(Locators.Job_Materials_Link)));
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);

		if(sInventoryItemPage.equals(sTitle))
		{
			assertEquals(sInventoryItemPage, _driver.getTitle());
			System.out.println(Date()+"Able to see Job Part");

			return true;
		}
		else
		{
			System.out.println(Date()+"Not Able to see Job Part");

			return false;
		}

	}

	public void NavigateToJobMaterials() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Materials_Link))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_Materails_Planned_Qty)));

	}

	public String FetchPlannedQtyInJobMaterials() throws Exception
	{
		String sPlannedQty =   _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Materails_Planned_Qty))).getText();
		return sPlannedQty;
	}
	public String FetchPulledQtyInJobMaterials() throws Exception
	{
		String sPulledQty =   _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Materails_Pulled_Qty))).getText();
		return sPulledQty;
	}
	public String FetchQuanityOnHandValue() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.II_QuantityPriceInfo))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.II_Qty_On_Hand)));
		String sQty_On_Hand	=	 _driver.findElement(By.xpath(Locators.getProperty(Locators.II_Qty_On_Hand))).getText();
		return sQty_On_Hand;

	}
	public boolean VerifyInventoryItem(String ItemType) throws Exception
	{

		String sItem = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AC_Materials_Item_Type)));
		Thread.sleep(1000);
		if(sItem.equals(ItemType))
		{
			return true;
		}
		else
		{
			return false;
		}



	}


	public void ActivityCodeTimeCharged(String sChargebasis,String hrsPerProdUnit,String SaleCategory,boolean sRevenueProducing) throws Exception
	{
		System.err.println("sChargebasis is: "+sChargebasis);
		System.err.println("hrsPerProdUnit is: "+hrsPerProdUnit);

		String sCB = "The option that represents how you charge time for this activity. If you do not track time for this activity, select No Hours or Move Job Only. Enter a value in the Hrs Per Prod Unit field only if you select Preset Hrs/Unit in the Charge Basis field. Select an available item.";
		String 	sChargebasisText= "//div[@id='contents']/div[2]/div[2]/h4[@title='"+sCB+"']";

		String sHrsPerprodUnitText = "The amount of time, in hours, required for one production unit. Complete this field only if you select Preset Hrs/Unit in the Charge Basis field. Numbers and Decimal Point.";
		String 	sHrsPerprodUnitxpath = "//div[@id='contents']/div/div[2]/h4[@title='"+sCB+"']";
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_TimeCharged))).click();
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_TimeCharged_Charge_Basis)));

		if(_driver.findElements(By.name(Locators.getProperty(Locators.AC_TimeCharged_Charge_Basis))).size()>0) 
		{
			System.out.println(Date()+"Navigated to Time Charged");
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_TimeCharged_Charge_Basis)), sChargebasis);
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_TimeCharged_HrsPerProdUnit))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_TimeCharged_HrsPerProdUnit))).sendKeys(hrsPerProdUnit);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_TimeCharged_Sales_Category)), SaleCategory);
			Thread.sleep(1000);
			if(sRevenueProducing == true)
			{
				if( _driver.findElement(By.name(Locators.getProperty(Locators.AC_TimeCharged_Revenue_Producing))).isSelected())
				{
					System.out.println(Date()+"sRevenue Producing CheckBox is Checked");
				}
				else
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.AC_TimeCharged_Revenue_Producing))).click();
					System.out.println(Date()+"sRevenue Producing CheckBox is Checked");
				}

			}

			// CommonFunctions.isElementPresent(_driver, By.xpath(sCB));
			// CommonFunctions.isElementPresent(_driver, By.xpath(sHrsPerprodUnitxpath));

			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	 
		}
		else
		{
			System.out.println(Date()+"Not able Navigate to Time Charged");
		}
	}

	public void OtherSettings(String sAskCount) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)), sAskCount);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
	}


	public void OtherSettingsTab(String sAskCount,String sAskpostage,String sAskNotes,String sAskNonPlannedReason,String sAskProdUnit,boolean sAskifComplete) throws Exception
	{
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			System.out.println("Version less than 27");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Setting))).click();
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)), sAskCount);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskPostage)), sAskpostage);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Notes)), sAskNotes);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Non_Planned_Reason)), sAskNonPlannedReason);
		Thread.sleep(1000);
		ProductionUnitCharged(sAskProdUnit);
		JobPlanningSettings(sAskifComplete);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
	}
	public enum ACOtherSettingsData
	{

		askCounts,
		askPostage,
		askNotes,
		askNonPlannedReason
	} 

	public String  FetchVersion()
	{
		String sVersion = _driver.findElement(By.xpath("//div[@id='poweredby']/div[1]/div[1]")).getText();
		System.out.println("Version is "+sVersion);

		String sVer = sVersion.substring(0, Math.min(sVersion.length(), 2));

		System.out.println("Version  is  "+sVer);
		return sVer;
	}
	public void ACOtherSettingsSetUp(String... a) throws Exception
	{


		_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)));
		System.out.println(Date()+"Enter values");
		System.out.println(Date()+"Enter " + a[1] + a[2] + a[3]);
		ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(a[1]);

		if(!a[1].equals("") && !a[2].equals(""))
		{
			switch (sAttribute) 
			{ 
			case askPostage:        // only for AskPostage
				CommonFunctions.selectDropdownByText(_driver, By.name(a[1]), a[2]);
				Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).sendKeys(a[3]);
				break;

			default:
				CommonFunctions.selectDropdownByText(_driver, By.name(a[1]), a[2]);
				Thread.sleep(1000);
				break;

			}
		}

		else
		{
			System.err.println("Invalid Locator or Value");

		}


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
	}
	public void ACOtherSettings(String sLoc ,String sValue,String sActivityCode) throws Exception
	{

		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)));
		ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLoc);

		switch (sAttribute) 
		{
		// case askCounts:
		//  		CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
		//	  		Thread.sleep(1000);
		//	  		break;
		case askPostage:
			CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			Thread.sleep(1000); 
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).sendKeys(sActivityCode);
			break;
			//  case askNotes:
			//	  	CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			// 	  	Thread.sleep(1000);    
			//		 break;

		default:
			CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			Thread.sleep(1000);
			break;  
		}


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
	}

	public void VerifyACOtherSettingsInDCL(String sLoc ,String sValue,String sActivityCode,String sTimeCharged) throws Exception
	{

		ACOtherSettingsData sAttribute = ACOtherSettingsData.valueOf(sLoc);
		String sBeginCounttext="The count of the related Inventory Item when this transaction begins. Numbers and Decimal Point.";
		String sEndCountText	= "The count on the associated Inventory Item when this transaction is complete. Numbers and Decimal Point.";
		switch (sAttribute) 
		{
		case askCounts:
			boolean sText =	BeginCount(sValue, sBeginCounttext);
			break;
		case askPostage:
			CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			Thread.sleep(1000); 
			_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).sendKeys(sActivityCode);
			break;
			//  case askNotes:
			//	  	CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			// 	  	Thread.sleep(1000);    
			//		 break;

		default:

			CommonFunctions.selectDropdownByText(_driver, By.name(sLoc), sValue);
			Thread.sleep(1000);
			break;  
		}


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));	
	}
	public boolean OtherSettingsAskPostage(String sAskPostage,String sActivityCode) throws Exception
	{



		String sText = "Check if you want EFI Pace to create an actual job cost matching the estimated cost when converting an estimate to a job. These costs post when the estimate is converted. Check the box to select this option.";
		String 	scss= "h4[title='"+sText+"'] > label";
		String sPostageActivityText = "Activity code to use for generated postage job cost. Max 5 characters. Letters and Numbers.";
		String 	scss2= "h4[title='"+sPostageActivityText+"'] > label";


		_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskPostage)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskPostage)), sAskPostage);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).sendKeys(sActivityCode);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		if(_driver.findElements(By.cssSelector(scss)).size()>0 && _driver.findElements(By.cssSelector(scss2)).size()>0 )
		{
			System.out.println(Date()+"Text Verified");
		}
		else
		{
			System.err.println("Failed to verify Verified");
		}
		boolean sFlag	= 	CommonFunctions.isElementPresent(_driver, By.cssSelector(scss));
		System.out.println(Date()+"sFlag " + sFlag);
		boolean sFlag1	= 	CommonFunctions.isElementPresent(_driver, By.cssSelector(scss2));
		System.out.println(Date()+"sFlag1 " + sFlag1);
		if(sFlag == true && sFlag1 == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean OtherSettingsAskNote(String sAskNote) throws Exception
	{



		String sText = "Check if you want EFI Pace to create an actual job cost matching the estimated cost when converting an estimate to a job. These costs post when the estimate is converted. Check the box to select this option.";
		String 	scss= "h4[title='"+sText+"'] > label";
		String sPostageActivityText = "Activity code to use for generated postage job cost. Max 5 characters. Letters and Numbers.";
		String 	scss2= "h4[title='"+sPostageActivityText+"'] > label";
		_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_OtherSettings))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Notes)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Notes)), sAskNote);

		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		if(_driver.findElements(By.cssSelector(scss)).size()>0 && _driver.findElements(By.cssSelector(scss2)).size()>0 )
		{
			System.out.println(Date()+"Verify Text");
		}
		else
		{

		}
		boolean sFlag	= 	CommonFunctions.isElementPresent(_driver, By.cssSelector(scss));
		System.out.println(Date()+"sFlag " + sFlag);
		boolean sFlag1	= 	CommonFunctions.isElementPresent(_driver, By.cssSelector(scss2));
		System.out.println(Date()+"sFlag1 " + sFlag1);
		if(sFlag == true && sFlag1 == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public void ProductionUnitCharged(String sAskProdUnit) throws Exception
	{


		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion) >=  27)
		{

			_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Setting))).click();

		}
		else

		{
			_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Production_Units_Charged))).click(); 
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), sAskProdUnit);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

	}

	public void Materails(String sAskQtyOfMaterials,String InvertoryPrompt,boolean IncludeNonInventory) throws Exception
	{


		_driver.manage().window().maximize();
		Thread.sleep(2000);
		_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Materials))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Materials_AskQtyOfMaterials)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Materials_AskQtyOfMaterials)), sAskQtyOfMaterials);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Materials_Query_Materials)), InvertoryPrompt);
		Thread.sleep(1000);
		if(IncludeNonInventory == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ac_Materials_Include_IncludeNonInventoryItemsBooleanCheck))).isSelected() )
			{

			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ac_Materials_Include_IncludeNonInventoryItemsBooleanCheck))).click(); 
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Ac_Materials_Include_IncludeNonInventoryItemsBooleanCheck))).isSelected() )
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Ac_Materials_Include_IncludeNonInventoryItemsBooleanCheck))).click(); 
			}
			else
			{

			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

	}

	public void NavigateToMaterials() throws Exception
	{
		_driver.manage().window().maximize();
		Thread.sleep(2000);
		_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Materials))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Materials_AskQtyOfMaterials)));
	}

	public boolean VerifySelectedMaterails(String sAskQtyOfMaterials,String InvertoryPrompt) throws Exception
	{

		String sAskQty = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AC_Materials_AskQtyOfMaterials)));
		String sIP = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AC_Materials_Query_Materials)));
		if(sAskQty.equals(sAskQty) && sIP.equals(InvertoryPrompt))
		{
			System.out.println(Date()+"Selected option are " +sAskQty+ " and " +  sIP);
			return true;
		}
		else
		{
			System.out.println(Date()+"Selected option are " +sAskQty+ " and " +  sIP);
			return false;
		}
	}
	public boolean JobPlanningSettings(boolean sAskIfComplete) throws Exception
	{

		String sComplete =  "\"" + "Complete" + "\"";
		System.out.println(Date()+sComplete);
		String sText = "If checked, EFI Pace prompts the user when this activity is complete. If the activity is complete, then EFI Pace changes the status to "+sComplete+" in both the Job Planning and Scheduling modules. Check the box to select this option.";
		String 	scss= "h4[title='"+sText+"'] > label";
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.linkText(Locators.getProperty(Locators.AC_Job_Planning_Setting))).click();
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete)));
		if(sAskIfComplete == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).isSelected() )
			{

			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).click(); 
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).isSelected() )
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).click(); 
			}
			else
			{

			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		boolean sFlag	= 	CommonFunctions.isElementPresent(_driver, By.cssSelector(scss));
		System.out.println(Date()+"sFlag " + sFlag);

		if(sFlag == true )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean  AddActivityCode(String sCC ,String sActivityCode) throws Exception
	{

		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String xpath ="//a[@href ='/epace/company:"+sCOMPANY+"/object/ActivityCode/detail/"+sActivityCode+"']";

		String originalHandle;
		boolean sFlag2 = false;
		String sCostCenterPage = "Cost Center "+sCC ;

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Department_Activity_Codes))).click();
		Thread.sleep(500);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_New_Record))).size()>0) 
		{
			boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(xpath));
			if(sFlag == false)
			{
				originalHandle = _driver.getWindowHandle();
				System.out.println(Date()+originalHandle);

				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
				Thread.sleep(2000);
				Set<String> availableWindows = _driver.getWindowHandles();
				if (!availableWindows.isEmpty()) 
				{
					for (String windowId : availableWindows) 
					{
						if(_driver.switchTo().window(windowId).getTitle().equals("Adding Activity Code")) 
						{

							_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(sActivityCode);
							_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("ActvtyCode "+sActivityCode+" by Automation");
							_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_CostingRates))).click();
							Thread.sleep(500);
							if (CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Cost_Center_CostMarkupCategory))))
							{
								CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Cost_Center_CostMarkupCategory)), 1);
							}
							Thread.sleep(1000);
							_driver.findElement(By.xpath("//a[text()='Settings']")).click();
							Thread.sleep(500);
							_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
							Thread.sleep(5000);

							_driver.switchTo().window(originalHandle).getTitle().equals(sCostCenterPage);
							if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
							{	
								System.out.println(Date()+"Activity Code Created");	
								sFlag2 = true;
							}
							else
							{
								System.err.println("Not able to Create Activity Code");
								sFlag2 = false;
							}		
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sCostCenterPage);

						}

					}
				}

			}
			else
			{
				System.out.println(Date()+"Activity Code Already Present");		
				return true;
			}
		}
		else
		{
			System.err.println("Not able to Create Activity Code");
			return false;
		}

		return sFlag2;
	}

	public String GetHour()
	{

		DateFormat dateFormat = new SimpleDateFormat("hh");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		int sFinalHour = Integer.parseInt(sCurrentDate);
		if(sFinalHour<10)
		{
			sCurrentDate =sCurrentDate.replace("0", "");
		}
		return sCurrentDate;  
	}
	public String GetMinutes()
	{

		DateFormat dateFormat = new SimpleDateFormat("mm");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		return sCurrentDate;  
	}
	public String GetHourAndMinute()
	{

		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		return sCurrentDate;  
	}

	public String DCLLoginDateAndTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		System.out.println(Date()+"sCurrentDate is " + sCurrentDate );
		String sHour = GetHour();
		int sFinalHour = Integer.parseInt(sHour);
		if(sFinalHour > 12)
		{
			String sMins = GetMinutes();
			String sTime = sCurrentDate+" - "+sHour+":"+sMins+" PM";
			return sTime;
		}
		else
		{
			String sMins = GetMinutes();
			String sTime = sCurrentDate+" - "+sHour+":"+sMins+" AM";
			return sTime;
		}

	}

	public String DCLLoginDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String sCurrentDate = dateFormat.format(date);
		System.out.println(Date()+"sCurrentDate is " + sCurrentDate );
		return sCurrentDate;

	}
	public String DCLLoginTimeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("aa");
		Date date = new Date();
		String TimeStamp = dateFormat.format(date);
		System.out.println(Date()+"TimeStamp  is " + TimeStamp );
		return TimeStamp;

	}
	public String DCLLoginTime()
	{
		String sHour = GetHour();
		int sFinalHour = Integer.parseInt(sHour);

		if(sFinalHour > 12)
		{
			sFinalHour =sFinalHour-12;
			String sMins = GetMinutes();
			String sTime = sFinalHour+":"+sMins+" "+DCLLoginTimeStamp();
			return sTime;
		}
		else if(sFinalHour == 12)
		{
			String sMins = GetMinutes();
			String sTime =sHour+":"+sMins+" "+DCLLoginTimeStamp();
			return sTime;
		}
		else 
		{
			String sMins = GetMinutes();
			String sTime =sHour+":"+sMins+" "+DCLLoginTimeStamp();
			return sTime;
		}
	}
	public boolean AddNewNonChargeableType(String sDesc,String AskNotes,String CostCenter, boolean paid) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.NonChargeableType_AskNotes)), AskNotes);
		Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.NonChargeableType_CostCenter)), CostCenter);
		Thread.sleep(1000);
		if(paid == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Paid))).isSelected())
			{
				System.out.println(Date()+"Paid already in  selected Status ");

			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Paid))).click();
				System.out.println(Date()+"Paid Checkbox check");
			}
		}
		else
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Paid))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Paid))).click();
				System.out.println(Date()+"Paid Checkbox uncheck");


			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Paid))).click();
				System.out.println(Date()+"Paid already in  unchecked Status ");
			}
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		System.out.println(Date()+sFlag);


		if( sFlag == true)
		{
			System.out.println(Date()+"Created Non-ChargableType"); 
			return true;

		}
		else
		{
			System.err.println("Not able to create Non-ChargableType"); 
			return false;
		}
	}

	public String  FetchNonChargeableTypeId()
	{
		String id =  _driver.findElement(By.xpath(Locators.getProperty(Locators.NonChargeableType_Id))).getText();
		return id;
	}



	public boolean  VerifyNonChargableBarcodeReport(String sId) throws Exception
	{

		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);
		_driver.findElement(By.linkText(Locators.getProperty(Locators.NonChargeableType_NonChargeableTypeBarcodeReport))).click();
		Thread.sleep(20000);


		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Running Report -Non Chargeable Type Barcode Report")) 
				{

					boolean sflag =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.NonChargeableType_Display_text)));
					System.out.println(Date()+"sflag is " +sflag);
					String sTitle = _driver.getTitle();
					System.out.println(Date()+"sTitle is "+sTitle);
					if(sflag == true)
					{
						String sBeginningId =	_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Enter_Beginning_ID))).getAttribute("value");
						sBeginningId = sBeginningId.trim();
						System.out.println(Date()+"sBeginningId is "+sBeginningId);

						String sEndingId =	_driver.findElement(By.name(Locators.getProperty(Locators.NonChargeableType_Enter_Ending_ID))).getAttribute("value");
						sEndingId = sEndingId.trim();
						System.out.println(Date()+"sEndingId is " +sEndingId);

						if(sBeginningId.equals(sId) && sEndingId.equals(sId))
						{
							System.out.println(Date()+"Id's are same");
							sFlag = true;
						}
						else
						{
							System.err.println("ID's  are not same");
							sFlag = false;
							Assert.fail("ID's  are not same");
						}
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(originalHandle);					
					}

					else
					{		
						Thread.sleep(5000);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(originalHandle);
					}

				}

			}

		}
		return sFlag;
	} 



	public boolean AddNewWorkStation(String sDesc) throws Exception 
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add)));
		assertEquals("Adding Workstation",_driver.getTitle());
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);


		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text)));
		System.out.println(Date()+sFlag);


		if( sFlag == true)
		{
			System.out.println(Date()+"Created Non-ChargableType"); 
			return true;

		}
		else
		{
			System.err.println("Not able to create Non-ChargableType"); 
			return false;
		}

	} 



	public boolean AddWorkStationDetails(String sEmp,String sAcCode,String sInvitem,String sNonPlannedReason) throws Exception
	{

		System.out.println(Date()+"Add Employee Details");
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_Add_Emp_Grid))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.WorkStation_Employeename)));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_Employeename))).sendKeys(sEmp);

		System.out.println(Date()+"Add ActivityCode Details");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.ActivityCode_Link))).click();
		CommonFunctions.Wait(_driver, By.xpath("//span[text()='Activity Code']"));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_Add_Activity_Code_Grid))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.WorkStation_ActivityCode)));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_ActivityCode))).sendKeys(sAcCode);

		System.out.println(Date()+"Add Inventory Item Details");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Link))).click();
		CommonFunctions.Wait(_driver, By.xpath("//span[text()='Inventory Item']"));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_Add_Inventory_Code_Grid))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.WorkStation_Employeename)));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_InventoryCode))).sendKeys(sInvitem);

		System.out.println(Date()+"Add NonPlanned Reasons Details");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.WorkStation_NonPlanned_Reasons_Link))).click();
		CommonFunctions.Wait(_driver, By.xpath("//span[text()='Non Planned Reason']"));
		_driver.findElement(By.name(Locators.getProperty(Locators.WorkStation_Add_NonPlannedReasons_Code_Grid))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.WorkStation_NonPlanned_Reasons)));
		CommonFunctions.selectDropdownByIndex(_driver,  By.name(Locators.getProperty(Locators.WorkStation_NonPlanned_Reasons)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.WorkStation_NonPlanned_Reasons_Link))).click();

		System.out.println(Date()+"Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));

		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		System.out.println(Date()+sFlag);


		if( sFlag == true)
		{
			return true;
		}
		else
		{		
			return false;
		}


	}


	public boolean StartGangedJobActivity() throws Exception
	{
		System.out.println(Date()+"******Verify START GANGED JOB ACTIVITY IS ENABLED*************");  
		boolean sFlag =  _driver.findElement(By.xpath("//div[@id='link12']/a")).isEnabled();
		if(sFlag == true)
		{
			System.out.println(Date()+"******START GANGED JOB ACTIVITY IS ENABLED*************");  
			_driver.findElement(By.xpath("//div[@id='link12']/a")).click();
			CommonFunctions.Wait(_driver, By.xpath("//a[text()='Please Enter an Activity Code and Multiple Jobs to Start Your Activity']"));
			return sFlag;
		}
		else
		{
			System.err.println("******START GANGED JOB ACTIVITY IS NOT ENABLED*************");  
			return sFlag;
		}

	}
	public boolean StartGangedJobActivityIsEnabled() throws Exception
	{
		System.out.println(Date()+"******Verify START GANGED JOB ACTIVITY IS ENABLED*************");  
		boolean sFlag =  _driver.findElement(By.xpath("//div[@id='link12']/a")).isEnabled();
		return sFlag;


	} 

	public boolean IsDisabled(By locator) throws Exception
	{

		boolean sFlag =  _driver.findElement(locator).isEnabled();
		return sFlag;


	} 
	public boolean StopGangedJobActivity() throws Exception
	{
		System.out.println(Date()+"******Verify STOP GANGED JOB ACTIVITY IS ENABLED*************");  
		boolean sFlag =  _driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Stop_Ganged_Job_Activity))).isEnabled();
		if(sFlag == true)
		{
			System.out.println(Date()+"******STOP GANGED JOB ACTIVITY IS ENABLED*************");  
			_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Stop_Ganged_Job_Activity))).click();
			CommonFunctions.Wait(_driver, By.xpath("//a[text()='Editing Ganged Activity']"));
			return sFlag;
		}
		else
		{
			System.err.println("******STOP GANGED JOB ACTIVITY IS NOT ENABLED*************");  
			return sFlag;
		}

	}

	public boolean CompleteGangedJobActivity() throws Exception
	{
		System.out.println(Date()+"******Complete Job Activity*************");  

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).size()>0)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Stop_Activity))).click();
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Ganged_Complete))).click();
		}

		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Completed Ganged Activity']"));
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Gang_Completed)));
		return sFlag;
	}

	public void EnterGangedJobDetails(String sActivityCode, String sJob, String sJobPart) throws Exception
	{
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), sActivityCode);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Ganged_Job))).sendKeys(sJob);
		Thread.sleep(3000);
		_driver.findElement(By.xpath("//a[text()='Please Enter an Activity Code and Multiple Jobs to Start Your Activity']")).click();  
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Ganged_Job_Part))).sendKeys(Keys.TAB);
		Thread.sleep(3000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Ganged_Job_Part)), sJobPart);
		Thread.sleep(1000);

		_driver.findElement(By.xpath("//a[text()='Please Enter an Activity Code and Multiple Jobs to Start Your Activity']")).click();  
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Ganged_Add_Additional_Job))).click();
		Thread.sleep(3000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Ganged_Job_2))).sendKeys(sJob);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='Please Enter an Activity Code and Multiple Jobs to Start Your Activity']")).click();  
		CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.DCL_Ganged_Job_Part_2)), sJobPart);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='Please Enter an Activity Code and Multiple Jobs to Start Your Activity']")).click();  

	}






	public boolean StartPlannedJobActivity(String sUN) throws Exception
	{
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Start_Planned_Activity))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Return_To_Action_Screen)));
		assertEquals("Planned Activities for Employee "+sUN,_driver.getTitle());
		if(_driver.findElements(By.xpath("//a[text()='lanned Activities']")).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	public boolean VerifyPullMaterialsLink() throws Exception
	{
		if(_driver.findElements(By.linkText("Pull Materials")).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	public void NaviagateToPullMaterials() throws Exception
	{
		if(_driver.findElements(By.linkText("Pull Materials")).size() > 0)
		{

			_driver.findElement(By.linkText("Pull Materials")).click();
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));
			System.out.println(Date()+"Able to navigate to Pul Materails");
			// assertEquals("Pull Materials",_driver.getTitle());
		}
		else
		{
			System.err.println("Able to navigate to  Pul Materails");
		}

	}

	public void NaviagateToPullMaterialsUncheck() throws Exception
	{
		if(_driver.findElements(By.linkText("Pull Materials")).size() > 0)
		{

			_driver.findElement(By.linkText("Pull Materials")).click();
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));
			System.out.println(Date()+"Able to navigate to Pul Materails");
			assertEquals("Adding Inventory Transaction",_driver.getTitle());
		}
		else
		{
			System.err.println("Able to navigate to  Pul Materails");
		}

	}

	public void NaviagateToCompletePullMaterials() throws Exception
	{
		if(_driver.findElements(By.linkText(Locators.getProperty(Locators.DCL_Completed_Material_Pulls))).size() > 0)
		{

			_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Completed_Material_Pulls))).click();
			CommonFunctions.Wait(_driver, By.linkText("Auto Posted Inventory Entries"));
			System.out.println(Date()+"Able to navigate to Complete Pul Materails");
			//assertEquals("Adding Inventory Transaction",_driver.getTitle());
		}
		else
		{ 
			System.err.println(Date()+" Not Able to navigate to Complete Pul Materails");

		}

	}
	public void EnterPullMaterialsDetails(String sJob,String sMaterails,String sQty,String sLoc,String sBin) throws Exception
	{

		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(sJob);
		Thread.sleep(1000);
		_driver.findElement(By.id("dc_status")).click();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_PullMaterails_Select_Materails)), sMaterails);
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_PullMaterails_Quantity))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_PullMaterails_Quantity))).sendKeys(sQty);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.DCL_PullMaterails_Location)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.DCL_PullMaterails_InventoryBin)), 1);
		Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))).sendKeys(Keys.TAB);
	}





	public void PullMaterialsDetails(String sJob,String sJobPart,String sMaterails) throws Exception
	{



		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(sJob);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCl_Quantity))).click();
		System.out.println(Date()+"Entered job ID is " + sJob);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)), sJobPart);
		Thread.sleep(1000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_PullMaterails_Select_Materails)), sMaterails);
		Thread.sleep(2000);



	}

	public String FetchJobPart() throws Exception
	{

		String sJPart = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)));
		System.out.println(Date()+"Selected job part is " + sJPart); 
		return sJPart;  
	}

	public String FetchSelectedMaterial() throws Exception
	{	 
		String sSelectMaterails = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.DCL_PullMaterails_Select_Materails)));
		System.out.println(Date()+"Selected Materails is " + sSelectMaterails);
		Thread.sleep(2000);
		// _driver.findElement(By.xpath("//label[text()='UOM']")).click();

		return sSelectMaterails;  
	}
	public String FetchQty() throws Exception
	{


		System.out.println(Date()+"get quantity");
		Thread.sleep(5000);
		String sQty = _driver.findElement(By.name("quantity")).getAttribute("value");
		System.out.println(Date()+"Fetched qty is " + sQty);
		Thread.sleep(5000);
		//	  _driver.findElement(By.xpath("//label[text()='UOM']")).click();  

		return sQty;  
	}
	public void NavigateToSinglePullMaterails() throws Exception
	{

		if(_driver.findElements(By.linkText("Pull Materials")).size() > 0)
		{

			_driver.findElement(By.linkText("Pull Materials")).click();
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));
			System.out.println(Date()+"Able to navigate to Pul Materails");
			assertEquals("Pull Materials",_driver.getTitle());
			_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Single_Materails_Pull))).click();
			Thread.sleep(1000);
			assertEquals("Adding Inventory Transaction",_driver.getTitle());
		}
		else
		{
			System.err.println("Able to navigate to  Pul Materails");
		}

	}

	public boolean VerifyPlannedMaterailsTextPresent(String sText) throws Exception
	{
		boolean isTextPrest = false;
		isTextPrest = CommonFunctions.isTextPresent(_driver, sText);
		return isTextPrest;
	}

	public boolean VerifyTextInPullMaterialsPage()
	{
		boolean isMaterialTextPresent = false,isJobTextPresent = false, isJobPartTextPresent = false , isJobDescTextPresent = false;
		boolean isTextPrest = false;
		isMaterialTextPresent = CommonFunctions.isTextPresent(_driver, "Customer");
		isJobTextPresent = CommonFunctions.isTextPresent(_driver, "Job");
		isJobPartTextPresent = CommonFunctions.isTextPresent(_driver, "Job Part");
		isJobDescTextPresent = CommonFunctions.isTextPresent(_driver, "Job Description");
		if(isMaterialTextPresent == true && isJobTextPresent == true && isJobPartTextPresent == true && isJobDescTextPresent == true  )
		{
			System.out.println(Date()+"Able to see all the Text");

			isTextPrest = true;
		}
		else
		{
			System.err.println("Not Able to see all the Text isMaterialTextPresent " +isMaterialTextPresent+ "isJobTextPresent " + isJobTextPresent+ "isJobPartTextPresent " + isJobPartTextPresent+ "isJobDescTextPresent "+isJobDescTextPresent);
			isTextPrest = false;
		}
		return isTextPrest;

	}



	public boolean VerifyTextInAddInventoryTransaction()
	{
		boolean isPartTextPresent = false,isSelectedMaterialsTextPresent = false, isInventoryItemTextPresent = false ;
		boolean isTextPrest = false;
		isPartTextPresent = CommonFunctions.isTextPresent(_driver, "Part");
		isSelectedMaterialsTextPresent = CommonFunctions.isTextPresent(_driver, "Select Material");
		isInventoryItemTextPresent = CommonFunctions.isTextPresent(_driver, "Inventory Item");

		if(isPartTextPresent == true && isSelectedMaterialsTextPresent == true && isInventoryItemTextPresent == true )
		{
			System.out.println(Date()+"Able to see all the Text");

			isTextPrest = true;
		}
		else
		{
			System.err.println("Not Able to see all the Text isPartTextPresent " +isPartTextPresent+ " isSelectedMaterialsTextPresent " + isSelectedMaterialsTextPresent+ " isInventoryItemTextPresent " + isInventoryItemTextPresent);
			isTextPrest = false;
		}
		return isTextPrest;

	}
	public boolean  VerifyCompletePullMaterail(String sJob, String sJobPart ,String sQty)
	{
		String  sJobXpathPath = "//table[@id='InventoryTrn']/tbody/tr[1]/td[6]/div/a";
		String  sJobPartXpathPath = "//table[@id='InventoryTrn']/tbody/tr[1][@class='c1']/td[7]/div/a" ;
		String  sQtyXpathPath = "//table[@id='InventoryTrn']/tbody/tr[1][@class='c1']/td[5]/div";


		String sFetehJob = _driver.findElement(By.xpath(sJobXpathPath)).getText();
		sFetehJob = sFetehJob.trim();
		System.out.println("Fetched Job is: " +sFetehJob+" and Job is: "+sJob);

		String sFetehJobPart = _driver.findElement(By.xpath(sJobPartXpathPath)).getText();
		sFetehJobPart = sFetehJobPart.trim();
		System.out.println("Fetched Job Part is: " +sFetehJobPart+" and Job Part is: "+sJobPart);

		String sFetehQty = _driver.findElement(By.xpath(sQtyXpathPath)).getText();
		sFetehQty = sFetehQty.trim();
		System.out.println("Fetched Quantity is: "+sFetehQty+" and Qty is: "+sQty);

		if(sFetehJob.equals(sJob) && sFetehJobPart.equals(sJobPart) && sFetehQty.equals(sQty))
		{
			System.out.println(Date()+"Able to see Job");
			return true;
		}
		else

		{
			System.out.println(Date()+"Not Able to see Job");
			return false;
		}

	}


	public void LogOffUsers() throws Exception
	{
		if( _driver.findElements(By.xpath(Locators.getProperty(Locators.Log_Off_Users))).size()>0)
		{
			_driver.findElement(By.xpath("//table[@id='users']/thead/tr/th[9]/span/a")).click();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Log_Off_Users))).click();
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.xpath("//div[contains(text(),'Welcome,')]"));
			assertEquals("Welcome to EFI Pace",_driver.getTitle());
		}
	}



	public void ResetDCLToDefaultState() throws Exception
	{

		if(_driver.findElements(By.cssSelector(Locators.getProperty(Locators.DCL_SignIn_Button))).size()>0)
		{
			System.out.println(Date()+"sSignIn is Disabled " );			

			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_Stop_Job_Activity))).size()==0)
			{
				System.out.println(Date()+"DCL_Stop Job Activity Button is Disabled " );  
			}
			else
			{
				StopJobActivity("", ""); 
				if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Complete))).size()>0)
				{
					JobComplete("John Adams","2");
				}
				else
				{
					int sRowCount = _driver.findElements(By.xpath("//table[@id='JobTransaction']/tbody/tr")).size();
					System.out.println(Date()+"No of Activite Job are "+sRowCount );  
					if(sRowCount >0)
					{
						for(int i = 1 ; i<sRowCount ; i++ )
						{
							_driver.findElement(By.xpath("//table[@id='JobTransaction']/tbody/tr["+i+"]/td[2]/div/a/div")).click();
							JobComplete("John Adams","2");
							StopJobActivity("", ""); 
						}

						JobComplete("John Adams","2");
					}
				}
			}

			if(_driver.findElements(By.linkText(Locators.getProperty(Locators.DCL_Stop_Ganged_Job_Activity))).size()==0)
			{
				System.out.println(Date()+"DCL_Stop gang Activity Button is Disabled " );  
			}
			else
			{
				StopGangedJobActivity();
				CompleteGangedJobActivity();
				GotoActionScreen();  
			}
			
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.DCL_SignOut))).size()==0)
			{
				System.out.println(Date()+"sSignOut is Disabled " );  
			}
			else
			{
				SignoutFromDCL();	  
			}
		}
	}

	public boolean VerifyDCLEmployeeFirstNameAndLastName(String sEmpId,String FN,String LN)
	{
		String sExpectedText = "Employee :"+sEmpId+" - "+LN+", "+FN;
		System.out.println(Date()+"sExpectedText is "+ sExpectedText);
		//String spath = "//div[@id='dc_status']/table/tbody/tr/td[2]/span[text() = '"+sExpectedText+"']";
		// boolean sFlag = CommonFunctions.isTextPresent(_driver, "Employee :ROSB - Ross, Betsy");
		boolean sFlag = CommonFunctions.isTextPresent(_driver, sExpectedText);
		System.out.println(Date()+"sFlag  is "+ sFlag);
		return sFlag;
	}

	public boolean PauseCurrentJob()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Paused_Current_Job))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Paused_Transaction))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean ResumeCurrentJob()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Resume_Current_Job))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Resumed_All_Open_Transaction))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean ResumeAllOpenJobs()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Resume_All_Open_Job))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Resumed_All_Open_Transaction))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public boolean PauseAllOpenJobs()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Paused_All_Open_Job))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Paused_All_Open_Transaction))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean VerifyButtonDisabled(By locator)
	{
		if(_driver.findElements(locator).size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean VerifyAllowPausingError()
	{
		String sxpath = "//div[@id='body']/div[@id='form-errors']//li[contains(text(), 'Job Costs exist that are currently paused. Please unpause them first.  :  Data Collection Setup[allowPausing=false]')]";
		String sxpath1 = "//div[@id='contents']/div[1]/div[2]//span[contains(text(),'Job Costs exist that are currently paused. Please unpause them first.  :  Data Collection Setup[allowPausing=false]')]";
		String sxpath2 = "//div[@id='body']/div[@id='form-errors']//li[contains(text(), 'Job Costs exist that are currently paused. Please unpause them first.')]";
		String sxpath3 = "//div[@id='contents']/div[1]/div[2]//span[contains(text(),'Job Costs exist that are currently paused. Please unpause them first.')]";

		if(_driver.findElements(By.xpath(sxpath)).size()>0 || _driver.findElements(By.xpath(sxpath1)).size()>0 || _driver.findElements(By.xpath(sxpath2)).size()>0 || _driver.findElements(By.xpath(sxpath3)).size()>0)
		{

			return true;
		}
		else
		{

			return false;
		}
	}


	public boolean StartLunch()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Lunch))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Out_For_Lunch))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public boolean BackFromLunch()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Back_From_Lunch))).click();
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0 && _driver.findElements(By.xpath(Locators.getProperty(Locators.Back_From_Lunch))).size()>0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}



	public boolean VerifyElementPresentButton(By locator)
	{
		System.out.println(Date()+"Verify Element Present");
		boolean sFlag =	 CommonFunctions.ElementPresent(_driver,locator);
		return sFlag;
	}

	public String FetchQuantityFromSelectMaterialLookup() throws Exception
	{
		String sQty = null;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_PullMaterails_Select_Material_Lookup))).click();
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Job Material ")) 
				{

					sQty = _driver.findElement(By.name(Locators.getProperty(Locators.Job_Materails_Planned_Quantity))).getText();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals("Adding Inventory Transaction");	
					return sQty;
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Adding Inventory Transaction");
					return null;

				}

			}
		}
		return sQty;
	}

	public void SelectJobFromPlannedActivity() throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Planned_Activity_View_Pick_img))).click();
		Thread.sleep(10000);
		String sjob = null;


		/* Set<String> windows = _driver.getWindowHandles();

     for (String window : windows) 
     {
    	 _driver.switchTo().window(window);
         if (_driver.getTitle().contains("Please select a Job Plan")) 
         { 
        	// Please select a Job Plan - All Statuses
        	 System.out.println(Date()+"Able to Navigate to Please select a Job Plan - All Statuses Window ");
        	 sjob = _driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Select_Job_Plan_Job))).getText();
        	 System.out.println(Date()+"Selected job is  "+ sjob);
        	 _driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Select_Job_Plan))).click();
        	 Thread.sleep(10000);
			 _driver.switchTo().window(originalHandle).getTitle().equals("Starting Job Entry");	
			 return sjob;
         }
         else
         {
        	 _driver.switchTo().window(originalHandle).getTitle().equals("Starting Job Entry");		
				//	 Assert.fail("Not able to Navigate to Please select a Job Plan - All Statuses Window "); 
        	 return sjob;
         }

     }
     return sjob;

		 */

	}


	public void EnterPlannedActivityID(String id) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Planned_Activity_ID))).sendKeys(id);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+"Planned Activity Entered");

	}


	public boolean StartNonChargeableTime(String sVersion,String sNonChargeableType) throws Exception
	{
		boolean sFlag =false;
		boolean sNonChageableTimeStarted=false;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Non_Chargeable_Time))).click();
		Thread.sleep(2000); 
		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Non_Chargeable_Type))).size()>0)
		{

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Non_Chargeable_Type)), sNonChargeableType);
			Thread.sleep(1000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			sNonChageableTimeStarted =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
			sFlag=true;

		}
		else
		{
			sNonChageableTimeStarted =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.Non_Chargeable_Activity_Started)));
		}
		return sNonChageableTimeStarted;
	}

	public String  StartNonChargeableTime1(String sVersion,String sNonChargeableType) throws Exception
	{

		boolean sNonChageableTimeStarted=false;
		String sFlag = "0";
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Start_Non_Chargeable_Time))).click();
		Thread.sleep(2000); 
		if(_driver.findElements(By.name(Locators.getProperty(Locators.DCL_Non_Chargeable_Type))).size()>0)
		{

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Non_Chargeable_Type)), sNonChargeableType);
			Thread.sleep(1000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			sNonChageableTimeStarted =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
			sFlag="1";

		}
		else
		{
			sNonChageableTimeStarted =   CommonFunctions.isElementPresent( _driver, By.xpath(Locators.getProperty(Locators.Non_Chargeable_Activity_Started)));
		}
		return sFlag;
	}

	public void EndNonChargeableTime(String sNonChargeableType) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_End_Non_Chargeable_Time))).click();
		Thread.sleep(2000); 
		assertEquals("End Non-Chargeable Time",_driver.getTitle());
		if(sNonChargeableType != "")
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.DCL_Non_Chargeable_Type)), 1);
			Thread.sleep(1000);
			if(_driver.findElement(By.xpath("//label[text()='Note']")).getLocation().x > 0 || _driver.findElement(By.xpath("//label[text()='Note']")).getLocation().y > 0  )
			{
				_driver.findElement(By.name("note")).sendKeys("notes");
			}
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();

		}

	}
	public void  PrintJobJacket(String sJob ,String sPrinter) throws Exception
	{
		String originalHandle;  
		originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Print_Job_Jacket))).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Running Report -Print Job Jacket")) 
				{

					_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Print_JobJacket_Enter_Job))).sendKeys(sJob);
					if(!sPrinter.equals(""))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Select_Print)), sPrinter);
						Thread.sleep(1000);
					}
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Print_Preview))).click();
					Thread.sleep(15000);
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Job Jacket - Portrait - Print+Job+Jacket");

					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					System.out.println(Date()+NewFileNamePath);
					_driver.close();

					_driver.switchTo().window(originalHandle);

				} 
				else 
				{
					_driver.switchTo().window(originalHandle);

				}

			}
		}

	}



	public String  AddSystemUser(String sUN,String sPWD,String sUserType,String sWorkstation,String sDefaultUser) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SystemUser_Add)));
		_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_UserName))).sendKeys(sUN);
		_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_Password))).sendKeys(sPWD);
		_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_Password_Confirm))).sendKeys(sPWD);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_UserType)), sUserType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_WorkStation)), sWorkstation);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_defaultEmployee)), sDefaultUser);
		Thread.sleep(1000);	
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SystemUser_Add))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
		{
			System.out.println(Date()+"User Created");
			return "User Created";
		}
		else if(_driver.findElements(By.xpath(Locators.getProperty(Locators.SystemUser_UserAlreadyPresent))).size()>0)
		{
			System.out.println(Date()+"User Already Present");
			return "User Already Present";
		}
		else
		{
			System.err.println("Not able to create User");
			return "Not able to create User";
		}
	}
	public void UpdateSystemUser(String sUserType,String sWorkstation,String sDefaultUser) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_UserType)), sUserType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_WorkStation)), sWorkstation);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.SystemUser_defaultEmployee)), sDefaultUser);
		Thread.sleep(1000);	 
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Updated_text))).size()>0)
		{
			System.out.println(Date()+"Updated");
		}
		else
		{
			System.err.println("Update failed");
			Assert.fail("Update failed");
		}
	}

	public int VerifyGroupExists(String sGroupName)
	{
		String sPath = "//table[@id='JoinedGroup_N800032']/tbody/tr";
		int count =0;
		int sGridCount =  _driver.findElements(By.xpath(sPath)).size();
		System.out.println(Date()+"sGridCount is "+ sGridCount);
		if(sGridCount>=2)
		{
			for(int i = 1 ;i<sGridCount;i++)
			{
				String sPath1 = "//table[@id='JoinedGroup_N800032']/tbody/tr["+i+"]/td[3]/div/a";
				String sGroupText =  _driver.findElement(By.xpath(sPath1)).getText();
				System.out.println(Date()+"sGroupText is "+ sGroupText);
				if(sGroupText.equals(sGroupName))
				{
					count++;
				}
			}
		}

		return count;
	}
	public void AddGroupToUser(String sGroupName,String sUN ) throws Exception
	{	 
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding User to Group")) 
				{

					_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_Add_Group))).sendKeys(sGroupName);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.SystemUser_Add))).click();
					Thread.sleep(1000);

					// _driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals("Detailed information for "+sUN);			
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
					{
						System.out.println(Date()+"Group Created");

					}
					else if(_driver.findElements(By.xpath(Locators.getProperty(Locators.SystemUser_UserAlreadyPresent))).size()>0)
					{
						System.out.println(Date()+"Not able to create Group");	
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Detailed information for "+sUN);	

				}

			}
		}

	}

	public void SelectFirstActiveTime(String sStartTime,String sStopTime,String sLunchStart,String sLunchStop,String sNCStartTime,String sNCStopTime) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Active_Employee_time_Mag_Glass))).click();
		Thread.sleep(10000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Employee Time Entry")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Start_Time))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Start_Time))).sendKeys(sStartTime);
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Stop_Time))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Stop_Time))).sendKeys(sStopTime);
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Lunch_Start))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Lunch_Start))).sendKeys(sLunchStart);
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Lunch_Stop))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Time_Entry_Lunch_Stop))).sendKeys(sLunchStop);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Emp_Time_Entry_Non_Chargeable_Times))).click();
					Thread.sleep(1000);
					_driver.findElement(By.name("addGridRow_NonChargeableTime")).click();
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Emp_Time_Entry_Select_Non_Chargeable_Type)), "Break");
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Time_Entry_Start_Time))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Time_Entry_Start_Time))).sendKeys(sNCStartTime);
					_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Time_Entry_End_Time))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Time_Entry_End_Time))).sendKeys(sNCStopTime);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(1000);
					// CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					// Thread.sleep(10000);
					_driver.switchTo().window(originalHandle).getTitle().equals("Active Employee Times");			
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Active Employee Times");	

				}

			}
		}
	}


	public String PrintTimeSheet() throws Exception
	{
		_driver.findElement(By.linkText(Locators.getProperty(Locators.DCL_Print_Time_Sheet))).click();
		Thread.sleep(1000);
		NewFileNamePath =  TakeScreenShot.getDestinationFile("Print_Time_Sheet");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println(Date()+NewFileNamePath);
		return NewFileNamePath;
	}


	public void EnterPrintDailyTimeSheetDetails(String sBegDate,String sEndDate,String sEmpCode,String sDept,String sPrinter ) throws Exception
	{

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Running Report -Daily Time Sheet Report")) 
				{	
					System.out.println(Date()+"Window found");
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Date))).sendKeys(sBegDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Date))).sendKeys(sEndDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Employee_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Employee_Code))).sendKeys(sEmpCode);

					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Employee_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Employee_Code))).sendKeys(sEmpCode);

					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Department))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Beginning_Department))).sendKeys(sDept);
					System.out.println(Date()+" Next");
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Department))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Report_Daily_Time_Sheet_Report_Ending_Department))).sendKeys(sDept);

					//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Select_Print)), sPrinter); 
					// Thread.sleep(1000);	
					System.out.println(Date()+" Next1");
					PreviewPrint();
					System.out.println(Date()+" Next2");
					Thread.sleep(10000);
					// String sReporturl= _driver.getCurrentUrl();
					// System.out.println(Date()+"sReporturl is "+sReporturl);
					Runtime.getRuntime().exec("C:\\AutoIT\\01.exe");
				}
				else
				{
					System.err.println("Window not found");
				}
			}
		}
	}
	public void EnterTimeCardSummaryReportDetails(String sBegDate,String sEndDate,String sBegEmpCode,String sEndEmpCode ,String sSummary,String sPrinter ) throws Exception
	{

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Running Report -Timecard Summary Report")) 
				{	
					System.out.println(Date()+"Window found");
					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_Beg_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_Beg_Date))).sendKeys(sBegDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_End_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_End_Date))).sendKeys(sEndDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_Beg_Emp_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_Beg_Emp_Code))).sendKeys(sBegEmpCode);

					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_End_Emp_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Timecard_Summary_Report_End_Emp_Code))).sendKeys(sEndEmpCode);

					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Timecard_Summary_Report_Detail_or_Summary)), sSummary); 
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Select_Print)), sPrinter); 
					Thread.sleep(1000);	
					PreviewPrint();
					String sReporturl= _driver.getCurrentUrl();
					System.out.println(Date()+"sReporturl is "+sReporturl);

				}
				else
				{
					System.err.println("Window not found");
				}
			}
		}
	}

	public void EnterEmployeeTimeVarianceReportDetails(String sBegDate,String sEndDate,String sBegEmpCode,String sEndEmpCode ,String sDept,String sNPA,String sPrinter ) throws Exception
	{

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Running Report -Daily Time Sheet Report")) 
				{	
					System.out.println(Date()+"Window found");
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Beg_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Beg_Date))).sendKeys(sBegDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_End_Date))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_End_Date))).sendKeys(sEndDate);

					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Emp_Beg_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Emp_Beg_Code))).sendKeys(sBegEmpCode);

					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Emp_End_Code))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Emp_End_Code))).sendKeys(sEndEmpCode);


					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Beg_Dept))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Beg_Dept))).sendKeys(sDept);

					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_End_Dept))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_End_Dept))).sendKeys(sDept);




					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Time_Variance_Report_Display_Only_Non_Planned_Activity)), sNPA); 
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Select_Print)), sPrinter); 
					Thread.sleep(1000);	

					PreviewPrint();
					String sReporturl= _driver.getCurrentUrl();
					System.out.println(Date()+"sReporturl is "+sReporturl);
					// WebElement downloadButton = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/a[3]")); 
					String sourceLocation = sReporturl; //downloadButton.getAttribute("href"); 
					System.out.println("Source Location is "+sourceLocation);
					String wget_command = "cmd /c wget -P f: " + sourceLocation;
					Process exec = Runtime.getRuntime().exec(wget_command);
					int exitVal = exec.waitFor(); 
					System.out.println("Exit value: " + exitVal);
				}
				else
				{
					System.err.println("Window not found");
				}
			}
		}
	}


	public void AddNewJob(String sJob,String sCustomer)
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(sJob);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
	}
	public void PreviewPrint() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.NonChargeableType_Preview))).click();
		Thread.sleep(10000);
	}

	public void AddNewJobPart(String sJob,String sInv,String sPlannedQty) throws Exception
	{
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//a[text()='Parts Info']")).click();
		Thread.sleep(2000);
		if( _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img")).size()>0)
		{
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img")).click();
		}
		else
		{
			_driver.findElement(By.xpath("//table[2]/tbody/tr[2]/td[3]/div/a/div")).click();
		}
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(5000);
		// assertEquals("Job "+sJob+"  part 01", _driver.getTitle());
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Details_Materials_Tab))).click();
		Thread.sleep(5000);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);
		String  sGetTitle = _driver.getTitle();
		System.out.println(Date()+sGetTitle);

		_driver.findElement(By.xpath("//fieldset[@id='JobMaterial_N1010A_fieldset']/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]")).click();
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
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						System.out.println(Date()+originalHandle1);
						String  sGetTitle1 = _driver.getTitle();
						System.out.println(Date()+sGetTitle1);
						_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]")).click();
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									Search(sInv,"id");
									_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div")).click();
									_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
								}

							}
						}
					}





					/*
					if(_driver.findElements(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/a")).size()>0)
					{
						String originalHandle1 = _driver.getWindowHandle();
						 System.out.println(Date()+originalHandle1);
						String  sGetTitle1 = _driver.getTitle();
						 System.out.println(Date()+sGetTitle1);
						_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]")).click();
						Thread.sleep(3000);
						 Set<String> availableWindows1 = _driver.getWindowHandles();
							if (!availableWindows1.isEmpty()) 
							{
								for (String windowId1 : availableWindows1) 
								{
									if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
									{
										Search( sInv,"id");
										_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/a/div")).click();
									_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
									}

								}
							}
					//		CommonFunctions.selectDropdownByText(_driver, By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/select"), sInv);		
					}
					else
					{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					}
					 */
					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					_driver.findElement(By.name("plannedQuantity")).sendKeys(sPlannedQty);
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

	public void SelectPlannedId() throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(Date()+originalHandle);
		String  sGetTitle = _driver.getTitle();
		System.out.println(Date()+sGetTitle);

		_driver.findElement(By.xpath("//div[@id='scrollableContent']/table/tbody/tr[2]/td[1]/div/div/div/div[2]")).click();
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Please select a Job Plan - All Statuses")) 
				{
					_driver.findElement(By.xpath("//table[@id='allStatus']/tbody/tr[4]/td[2]/a/div")).click();
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

	public void StartJobActivityPlanned(String JobPlanID, String ProdsUnit, String Hours, String BeginCount) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Start_Job_Activity)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name)));

		CommonFunctions.SendValue(_driver, By.name("jobPlan"), JobPlanID);
		_driver.findElement(By.name("JobPartKey")).sendKeys(Keys.TAB);
		Thread.sleep(2000);

		if(ProdsUnit!="")
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.DCL_ProdsUnit)), ProdsUnit);
		}

		if(Hours!="")
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.DCL_Hour)), Hours);
		}

		if(BeginCount!="")
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.DCL_Begin_Count)), BeginCount);
		}
	}

	public void PullAllMaterials(String JobID, String JobPart) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Pull Materials']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), JobID);
		CommonFunctions.SendValue(_driver, By.name("customer"), "");
		CommonFunctions.selectDropdown(_driver, By.name("jobPart"), JobID+":"+JobPart);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Show Materials']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='(all)']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Pull Materials']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen)));
		CommonFunctions.waitForPageLoaded(_driver);
	}

}  














