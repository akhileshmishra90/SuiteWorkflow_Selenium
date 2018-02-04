package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;
//import com.sun.org.apache.xerces.internal.util.LocatorWrapper;

public class ICPage implements BaseElement
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


	public  ICPage(WebDriver driver) throws Exception 
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
		DateFormat dateFormat = new SimpleDateFormat("yymmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String  UniqueNum5()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyhhmmss");
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

	//**************************** till here it is common for all*****************************

	public void EpaceLogin(String sUN,String sPWD,String sCompany) throws Exception
	{
		//System.out.println("*** DC Workflow 2  # P-1066");
		System.out.println("Author: Ashish");
		//System.out.println("*** Created date:13-May-2013");
		//System.out.println("*** Launch & Login Start");
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
				boolean sFlag= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Welcome_Message))); 
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

	public void Logout() throws Exception
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
	public boolean Object_Added() throws Exception
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
	public boolean Update() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		System.out.println ("****Clicking the Update button****");
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


	// Navigate to Inventory Item List page
	public void NavigateToInventoryItemListPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItem/list");	
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Inventory Items", _driver.getTitle());			
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Department");
		System.out.println("****Inventory Item List Page appears****");
	} 


	public void NavigateToInventoryItemSerialIDs(String sInventoryID) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/listInventoryItemSerialIDS/"+sInventoryID);	
		//CommonFunctions.Wait(_driver, By.linkText(""));
		assertEquals("Inventory Movement", _driver.getTitle());

		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"Department");
		System.out.println("****Inventory Item Serial ID's page appears****");
	} 
	// Add a new Inventory Item. 
	// Fill in the details like Inventory ID, description, vendor, enable/disable auto post,
	// Fill in the details like average/replacement costs, minimum/maximum stock level
	// Includes the method NavigateToInventoryItemListPage()
	public String AddNewInventoryItem(String sReplacement_Cost, String  sAverage_Cost, boolean autoPost,String sVendor) throws Exception
	{

		String sInv_Id  = "INV_ITEM_"+ UniqueNum();
		NavigateToInventoryItemListPage();
		System.out.println("****Navigate to Inventory Item List Page****");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		System.out.println("**General Info tab of Inventory Item Add Page Appears**");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_ID))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory Item ID as " + sInv_Id);
		/*if(autoPost == true)
			  {
				  System.out.println("Auto Post is true ie. enable autopost");
				  if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_AutoPost))).isSelected() )
				  {
					  System.out.println("Auto Post is enabled");
				  }
				  else
				  {
					  _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_AutoPost))).click(); 
					  System.out.println("Enabling auto post");
				  }
			  }
			  else
			  {
				  System.out.println("Auto Post is false ie. disable auto post");
				  if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_AutoPost))).isSelected() )
				  {
					  _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_AutoPost))).click(); 
					  System.out.println("Disabling auto post");
				  }
				  else
				  {
					  System.out.println("Auto Post is already disabled");
				  }
			  }
		 */
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).sendKeys("test_automation");
		System.out.println("Entering the Inventory description as 'test_automation'");
		//_driver.findElement(By.name(Locators.getProperty(Locators.Vendor))).sendKeys(sVendor);
		//System.out.println("Entering the Vendor ID as "+sVendor);
		//_driver.findElement(By.name(Locators.getProperty(Locators.Vendor))).sendKeys(Keys.TAB);
		//System.out.println("Entered the description as 'test_automation' and vendor as '"+sVendor);

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Quantity_Price))).click();
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("trackQuantityBooleanCheck"));
		System.out.println("**Quantity/Price Info tab of Inventory Item Add Page Appears**");		
		if(sReplacement_Cost.equals(""))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Replacement_Cost))).sendKeys("10");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Replacement_Cost))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Replacement_Cost))).sendKeys(sReplacement_Cost);
		}
		if(sAverage_Cost.equals(""))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Average_Cost))).sendKeys("20");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Average_Cost))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Average_Cost))).sendKeys(sAverage_Cost);
		}
		
		System.out.println("Entered the Replacement and Average Cost as " +sReplacement_Cost+ " and " + sAverage_Cost + " respectively");

		//Make sure to disable Quantity Based Pricing in Invevtory Settings in order to set the sell price
		boolean sellPrice= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Sell_Price)));
		if (sellPrice == true)
		{
			if (_driver.findElement(By.name(Locators.getProperty(Locators.Sell_Price))).isDisplayed())
			{
				System.out.println("Identified the SellPrice text box");
				_driver.findElement(By.name(Locators.getProperty(Locators.Sell_Price))).clear();
				_driver.findElement(By.name(Locators.getProperty(Locators.Sell_Price))).sendKeys("100");
				System.out.println("Entered the Sell Price as $100.00");
			}

		}
		else
		{
			System.out.println("Couldn't identify the SellPrice text box");
		} 

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Ordering_Info))).click();
		System.out.println("**Ordering Info Info tab of Inventory Item Add Page Appears**");
		//Make sure that Use Location Stock Level in Inventory Settings is set to false
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Min_Stock_Level))).sendKeys("1");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Max_Stock_Level))).sendKeys("10000000");
		System.out.println("Entering the min and max stock level as '1' and '10000000' respectively");
		if(_driver.findElements(By.xpath("//span[text()='P']")).size()>0)
		{
			_driver.findElement(By.xpath("//span[text()='P']")).click();
			Thread.sleep(2000);
			if(_driver.findElements(By.name("grainDirection")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
			}
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add))).click();
		System.out.println("Clicking the Inventory Item Add button");			
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		Boolean objAdded = _driver.findElement(By.xpath(Locators.getProperty(Locators.Object_added_text))).isDisplayed();
		System.out.println("****Inventory Item detail page appears****");
		if (objAdded == true)
		{
			System.out.println("Object is added");

			if (!sVendor.equals(""))
			{
				System.out.println("Assign vendor to inventory item created");
				_driver.findElement(By.linkText(Locators.getProperty(Locators.Ordering_Info))).click();
				Thread.sleep(500);
				if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='vendors']")))
				{
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.AddinGrid_Vendors)));
					CommonFunctions.selectDropdown(_driver, By.xpath("//table[@id='vendors']//tr[1]//select[@name='vendors.vendor']"), sVendor);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//table[@id='vendors']//tr[1]//select[@name='vendors.purchasingUOM']"), 1);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(2000);
				}
			}
			
			return sInv_Id.toUpperCase();
		}
		else
		{
			System.err.println("Inventory Item was not created");
			return "";
		}	
	} 

	// Duplicate an inventory item
	public String DuplicateInventoryItem(String inventoryID) throws Exception
	{
		String duplicateInventoryID = "H_" + UniqueNum();

		NavigateToInventoryItemListPage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000); 

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Duplicate))).click();
		System.out.println("Clicking the duplicate button in detail page");
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_Duplicate_ID))).clear();
		System.out.println("Clearing the ID field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_Duplicate_ID))).sendKeys(duplicateInventoryID);
		System.out.println("The duplicate id entered is " +duplicateInventoryID);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Duplicate))).click();
		System.out.println("Clicking the duplicate button in inventory item clone page");

		Thread.sleep(3000);

		/*	 boolean inventoryItemPresent= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Inventory_Magnifying_Glass)));

		 if(inventoryItemPresent == true)
		 {
			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Magnifying_Glass))).click();
			 Thread.sleep(3000);

		 } */

		boolean duplicatedMessage= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Duplicate_Message)));

		if(duplicatedMessage == true)
		{
			System.out.println("The item has been duplicated");
			return duplicateInventoryID;
		}
		else
		{
			System.out.println("The item could not be duplicated");
			return null;
		}
	}

	// Compare duplicated inventory item
	public boolean CompareDuplicatedInventoryItem(String inventoryID, String duplicateInventoryID) throws Exception
	{
		boolean itemType = false, vendor = false, UOM = false, itemDescription  = false, minStockLevel  = false, maxStockLevel  = false;

		// Capture the values of inventory item

		NavigateToInventoryItemListPage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the original inventory item's id as " + inventoryID+ " in search field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		String inventoryItemType = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)));
		System.out.println ("The item type is " +inventoryItemType);

		String inventoryUOM = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)));
		System.out.println ("The UOM is " +inventoryUOM);



		if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Active))).isSelected())
		{
			String inventoryActive = "enabled";
			System.out.println ("Active checkbox is checked");
		}
		else
		{
			String inventoryActive = "disabled";
			System.out.println ("Active checkbox is unchecked");
		}

		String inventoryDescription = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).getAttribute("value");
		System.out.println ("The description is " +inventoryDescription);



		_driver.findElement(By.linkText(Locators.getProperty(Locators.Ordering_Info))).click();
		Thread.sleep(1000);
		System.out.println ("Ordering Info tab appears");
		String inventoryMinStockLevel = _driver.findElement(By.xpath(Locators.getProperty(Locators.Min_Stock_Level))).getAttribute("value");
		System.out.println ("The minimum stock level is " +inventoryMinStockLevel);

		String inventoryMaxStockLevel = _driver.findElement(By.xpath(Locators.getProperty(Locators.Max_Stock_Level))).getAttribute("value");
		System.out.println ("The maximum stock level is " +inventoryMaxStockLevel);

		// Capture the values of duplicated inventory item

		NavigateToInventoryItemListPage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(duplicateInventoryID);
		System.out.println("Entering the duplicated inventory item's id as " + duplicateInventoryID+ " in search field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);
		String sTitle1 = _driver.getTitle();
		System.out.println("title of the page is "+sTitle1);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle1.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		String duplicateinventoryItemType = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)));
		System.out.println ("The item type is " +duplicateinventoryItemType);

		String duplicateinventoryUOM = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)));
		System.out.println ("The UOM is " +duplicateinventoryUOM);



		if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Active))).isSelected())
		{
			String duplicateinventoryActive = "enabled";
			System.out.println ("Active checkbox is checked for duplicate inventory item");
		}
		else
		{
			String duplicateinventoryActive = "disabled";
			System.out.println ("Active checkbox is unchecked for duplicate inventory item");
		}

		String duplicateinventoryDescription = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).getAttribute("value");
		System.out.println ("The description is " +duplicateinventoryDescription);


		_driver.findElement(By.linkText(Locators.getProperty(Locators.Ordering_Info))).click();
		Thread.sleep(1000);
		String duplicateinventoryMinStockLevel = _driver.findElement(By.xpath(Locators.getProperty(Locators.Min_Stock_Level))).getAttribute("value");
		System.out.println ("The minimum stock level is " +duplicateinventoryMinStockLevel);

		String duplicateinventoryMaxStockLevel = _driver.findElement(By.xpath(Locators.getProperty(Locators.Max_Stock_Level))).getAttribute("value");
		System.out.println ("The maximum stock level is " +duplicateinventoryMaxStockLevel);

		if(inventoryItemType.equals(duplicateinventoryItemType))
		{
			itemType = true;
			System.out.println ("The inventory item type is same in original and duplicated invetory items");
		}
		else
		{
			itemType = false;
			System.out.println ("The inventory item type is not the same in original and duplicated invetory items");
		}
		if(inventoryUOM.equals(duplicateinventoryUOM))
		{
			UOM = true;
			System.out.println ("The UOM is same in original and duplicated invetory items");
		}
		else
		{
			UOM = false;
			System.out.println ("The UOM is not the same in original and duplicated invetory items");
		}
		/*	 if(inventoryAutoPost == duplicateinventoryAutoPost)
			 {
				 System.out.println ("The AutoPost checkbox is same in original and duplicated invetory items");
			 }
			 if(inventoryActive == duplicateinventoryActive)
			 {
				 System.out.println ("The Active checkbox is same in original and duplicated invetory items");
			 } */
		if(inventoryDescription.equals(duplicateinventoryDescription))
		{
			itemDescription = true;
			System.out.println ("The description is same in original and duplicated invetory items");
		}
		else
		{
			itemDescription = false;
			System.out.println ("The description is not the same in original and duplicated invetory items");
		}

		if(inventoryMinStockLevel.equals(duplicateinventoryMinStockLevel))
		{
			minStockLevel = true;
			System.out.println ("The minimum stock level is same in original and duplicated invetory items");
		}
		else
		{
			minStockLevel = false;
			System.out.println ("The minimum stock level is not the same in original and duplicated invetory items");
		}
		if(inventoryMaxStockLevel.equals(duplicateinventoryMaxStockLevel))
		{
			maxStockLevel = true;
			System.out.println ("The maximim stock level is same in original and duplicated invetory items");
		}
		else
		{
			maxStockLevel = false;
			System.out.println ("The maximim stock level is not the same in original and duplicated invetory items");
		}
		if(itemType == true && UOM == true && itemDescription == true  && minStockLevel == true && maxStockLevel == true )
		{
			System.out.println("Values for duplicated Inventory item is same as that of the original Inventory item");
			boolean compareInventoryItems = true;
			return compareInventoryItems;		 
		}
		else
		{
			System.out.println("Values for duplicated Inventory item is not the same as that of the original Inventory item");
			return false;
		}

	}
	public String  FetchVersion()
	{
		String sVersion = _driver.findElement(By.xpath("//div[@id='poweredby']/div[1]/div[1]")).getText();
		System.out.println("Version is "+sVersion);

		String sVer = sVersion.substring(0, Math.min(sVersion.length(), 2));

		System.out.println("Version  is  "+sVer);
		return sVer;
	}
	// Navigate to Add Inventory Transactions page
	public void NavigateToAddInventoryTransactionPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion = FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{					
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryTrn/add");	
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/add");	
		}
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Inventory_Location)));
		assertEquals("Adding Inventory Transaction", _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		/*	NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToAddInventoryTransactionPage");
			File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(NewFileNamePath));
			System.out.println("****Add Inventory Transaction page appears****");*/
	}

	// Create a new inventory transaction
	// Includes the method AddNewInventoryItem() and NavigateToAddInventoryTransactionPage()
	// Always run the method AddNewInventoryItem() before this to get the Inventory ID

	// Issue in displaying auto-approve message

	public void CreateReceiveTransaction (String inventoryID, String sTransactionMethod, String jobID) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		_driver.manage().window().maximize();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);


		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("5000");
		System.out.println("Entering the quantity as 5000");

		boolean approvedCheckbox = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isEnabled();
		if (approvedCheckbox == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isSelected())
			{
				System.out.println("Approved checkbox is checked");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click();
				System.out.println("Checking Approved checkbox");
			}

		}
		else
		{
			System.out.println("Approved checkbox is not checked");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		assertEquals("Edit Inventory Transactions", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		/* if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Auto_Post_Message))).isDisplayed())
		  {
			  System.out.println ("**Inventory Transaction is auto approved**");
		  }
		 else
		  {
			  System.out.println ("**Inventory Transaction is not auto approved**");
		  } */


	}

	//Specific for calculating the Average Cost
	//This will create two receive transactions
	public void CreateReceiveTrxn (String inventoryID, String sTransactionMethod, String jobID) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);


		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("500");
		System.out.println("Entering the quantity as 500");
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).sendKeys("5");
		System.out.println("Entering the unit cost as $5");

		boolean approvedCheckbox = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isEnabled();
		if (approvedCheckbox == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isSelected())
			{
				System.out.println("Approved checkbox is checked");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click();
				System.out.println("Checking Approved checkbox");
			}

		}
		else
		{
			System.out.println("Approved checkbox is not displayed");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		assertEquals("Edit Inventory Transactions", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		InventoryItemHistoryPage(inventoryID);

		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);


		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("500");
		System.out.println("Entering the quantity as 500");
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).sendKeys("10");
		System.out.println("Entering the unit cost as $10");

		boolean approvedCheckbox1 = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isEnabled();
		if (approvedCheckbox1 == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isSelected())
			{
				System.out.println("Approved checkbox is checked");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click();
				System.out.println("Checking Approved checkbox");
			}

		}
		else
		{
			System.out.println("Approved checkbox is not displayed");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		assertEquals("Edit Inventory Transactions", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		InventoryItemHistoryPageChargeJob(inventoryID);

		/* if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Auto_Post_Message))).isDisplayed())
		  {
			  System.out.println ("**Inventory Transaction is auto approved**");
		  }
		 else
		  {
			  System.out.println ("**Inventory Transaction is not auto approved**");
		  } */


	}


	//This is to be used only in the case when Serial ID is true in Inventory Settings
	//This will enter the num serial ID while creating a receive transaction
	public String CreateReceiveTrxnwithSerialIDs (String inventoryID, String sTransactionMethod, String jobID,String sBatch) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);


		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name("inventoryBatch"), sBatch);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryLocation"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("2000");
		System.out.println("Entering the quantity as 5000");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Num_Serial_ID))).sendKeys("5");
		System.out.println("Entering the numSerialID as 5");

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");
		String sSerialID =  _driver.findElement(By.name("serialID")).getAttribute("value");
		sSerialID=sSerialID.trim();
		System.out.println("sSerialID starts from "+sSerialID);
		return sSerialID;
	}

	//This is to be used only in the case when a specific Location & Bin and Serial ID needs to be selected
	//This will enter the num serial ID while creating a receive transaction
	public void CreateReceiveTrxnwithLocandBin (String locID, String inventoryID, String sTransactionMethod) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);

		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("5000");
		System.out.println("Entering the quantity as 5000");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Num_Serial_ID))).sendKeys("8");
		System.out.println("Entering the numSerialID as 8");

		if (CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))))
		{
			boolean approvedCheckbox = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isEnabled();
			if (approvedCheckbox == true)
			{
				if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isSelected())
				{
					System.out.println("Approved checkbox is checked");
				}
				else
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click();
					System.out.println("Checking Approved checkbox");
				}

			}
			else
			{
				System.out.println("Approved checkbox is not checked");
			}
		}


		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Location)), locID);
		System.out.println("Entering the location ID as " +locID);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Location))).sendKeys(Keys.TAB);
		// _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_LocationBin))).sendKeys("test");
		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_LocationBin)), "test");
		System.out.println("Entering the bin");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBatch"), 1);

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		assertEquals("Inventory Transaction", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		/* if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Auto_Post_Message))).isDisplayed())
		  {
			  System.out.println ("**Inventory Transaction is auto approved**");
		  }
		 else
		  {
			  System.out.println ("**Inventory Transaction is not auto approved**");
		  } */


	}
	//CreateChargeJobTransaction
	public void CreateChargeJobTransaction (String inventoryID, String sTransactionMethod, String jobID) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		_driver.manage().window().maximize();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);

		System.out.println("Creating a 'Charge Job' transaction");	 

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(4000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Job))).sendKeys(jobID);
		System.out.println("Job entered is " +jobID);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(2000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("500");
		System.out.println("Entering the quantity as 500");
		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");

		Object_Added();
		assertEquals("Edit Inventory Transactions", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");


	}

	//CreateChargeJobTransactionForNegativeQuantity
	public boolean CreateChargeJobTransactionForNegativeQuantity (String inventoryID, String sTransactionMethod, String jobID) throws Exception
	{
		boolean noNegative = false;
		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);

		System.out.println("Creating a 'Charge Job' transaction");	 

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Job))).sendKeys(jobID);
		System.out.println("Job entered is " +jobID);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("6000");
		System.out.println("Entering the quantity as 6000");
		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_No_Negative_Qty_Error_Msg))).isDisplayed())
		{
			System.out.println("Since No Negative entry is enabled, it is not allowing it to add this transaction");
			noNegative = true;
			return noNegative;
		}
		else
		{
			System.out.println("Transaction added successfully");
			noNegative = false;
			return noNegative;
		} 

	}

	//Count Transaction
	public void CreateCountTransaction (String inventoryID, String sTransactionMethod, String jobID) throws Exception
	{
		NavigateToAddInventoryTransactionPage();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), sTransactionMethod);
		Thread.sleep(1000);


		System.out.println("Creating a 'Count' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryID);
		System.out.println("Inventory ID entered is " +inventoryID);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("5000");
		System.out.println("Entering the quantity as 5000");

		boolean approvedCheckbox = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isEnabled();
		if (approvedCheckbox == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).isSelected())
			{
				System.out.println("Approved checkbox is checked");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click();
				System.out.println("Checking Approved checkbox");
			}

		}
		else
		{
			System.out.println("Approved checkbox is not checked");
		}


		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		Object_Added();
		assertEquals("Edit Inventory Transactions", _driver.getTitle());
		System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		/* if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Auto_Post_Message))).isDisplayed())
			  {
				  System.out.println ("**Inventory Transaction is auto approved**");
			  }
			 else
			  {
				  System.out.println ("**Inventory Transaction is not auto approved**");
			  } */
	}

	//GetSerialIDs.
	//This will take the serial number from Inventory Settings 
	public boolean GetSerialID (String serialNumber, String inventoryID) throws Exception
	{
		boolean compareIDs = false;
		String[] serialIDs = new String[50];
		NavigateToInventoryItemListPage();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		String sTitle1 = _driver.getTitle();
		System.out.println("title of the page is "+sTitle1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("title of the page is "+sTitle);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PostedInventoryTrn/listInventoryItemSerialIDS/"+inventoryID);
		/*	 
			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Context_Drop_Down))).click();
			 System.out.println ("Opening the context dropdown");
			 Thread.sleep(2000);
			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Context_Serial_ID))).click();
			 System.out.println ("Clicking the Serial ID link");
			 Thread.sleep(3000);
		 */
		int count = _driver.findElements(By.xpath(Locators.getProperty(Locators.Inventory_Get_Serial_ID_Size))).size();
		System.out.println ("The count of serial ID is " + count);
		String sID ="";
		if(count>0)
		{	
			for(int i = 1; i<=count; i++)
			{
				String path = "//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div";
				sID = _driver.findElement(By.xpath(path)).getText();
				sID=sID.trim();
				System.out.println ("Serial Id is " + sID);
				serialIDs[i] = sID;
				//System.out.println ("Serial ID[5] is " + serialIDs[5]);
			}
		}

		//This is the serial ID from the Inventory Item
		String serID = serialIDs[5];
		String serialID = serID.trim();
		System.out.println ("Serial ID[5] after trimming is " + serialID);
		int serial_ID = Integer.parseInt(serialID);
		System.out.println ("Serial ID[5] after converting to integer is " + serialID);

		//This is the serial ID from the Inventory Settings page
		System.out.println ("Serial ID fetched from settings page is " + serialNumber);
		String sText = serialNumber.replace(",","");
		int serial_Number = Integer.parseInt(sText);
		System.out.println ("Serial ID after converting to an integer is " + serial_Number);
		int s_ID = serial_Number+count;
		System.out.println ("Serial ID after adding the count is " + s_ID);

		System.out.println ("Comparing serial id from settings page with inventory item's page");

		if(serial_ID == s_ID)
		{
			System.out.println ("Serial Id from inventory item matches with that of the settings page");
			compareIDs = true;
		}
		else
		{
			System.out.println ("Serial Id from inventory item does not match with that of the settings page");
			compareIDs = false;
		}

		return compareIDs;
		/* while(count>0)
			 {
				 System.out.println ("-----------");
				 int k = 1;
				 serial_Number = serial_Number+1;
				 System.out.println (serial_Number);
				 System.out.println ("Serial ID " + serialIDs[k]);
				 int j = Integer.parseInt(serialIDs[k]);
				 System.out.println ("J " + j);

				 if(serial_Number == j)
				 {
					 System.out.println ("*****************");
				 }
				 k++;
				 count--;

			 }*/
	}

	//GetSerialIDs.
	//This will take the serial number from Inventory Settings 
	public String GetValueofSerialID (String serialNumber, String inventoryID) throws Exception
	{
		boolean compareIDs = false;
		String[] serialIDs = new String[50];
		//			 NavigateToInventoryItemListPage();

		/*
			 _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
			 System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
			 String sTitle1 = _driver.getTitle();
			 System.out.println("title of the page is "+sTitle1);
			 _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
			 System.out.println("Clicking the Find button");
			 Thread.sleep(2000);
			 String sTitle = _driver.getTitle();
			 System.out.println("title of the page is "+sTitle);
			//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

			 if(sTitle.equals("Inventory Items"))
			 {
				 System.out.println("Click the magnifying glass to go to the detail page");
				 _driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
			  }			 
			 Thread.sleep(2000);
		 */

		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/listInventoryItemSerialIDS/"+inventoryID.toUpperCase());
		/* _driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Context_Drop_Down))).click();
			 System.out.println ("Opening the context dropdown");
			 Thread.sleep(2000);
			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Context_Serial_ID))).click();
			 System.out.println ("Clicking the Serial ID link");
			 Thread.sleep(3000);
		 */
		int count = _driver.findElements(By.xpath(Locators.getProperty(Locators.Inventory_Get_Serial_ID_Size))).size();
		System.out.println ("The count of serial ID is " + count);

		if(count>0)
		{	
			for(int i = 1; i<=count; i++)
			{
				String path = "//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div";
				String sID = _driver.findElement(By.xpath(path)).getText();
				System.out.println ("Serial Id is " + sID);
				serialIDs[i] = sID;
				//System.out.println ("Serial ID[5] is " + serialIDs[5]);
			}
		}

		//This is the serial ID from the Inventory Item
		String serID = serialIDs[count-1];
		String serialID = serID.trim();
		System.out.println ("Serial ID[5] after trimming is " + serialID);
		int serial_ID = Integer.parseInt(serialID);
		System.out.println ("Serial ID[5] after converting to integer is " + serialID);

		//This is the serial ID from the Inventory Settings page
		System.out.println ("Serial ID fetched from settings page is " + serialNumber);
		String sText = serialNumber.replace(",","");
		int serial_Number = Integer.parseInt(sText);
		System.out.println ("Serial ID after converting to an integer is " + serial_Number);
		int s_ID = serial_Number+count;
		s_ID = s_ID-1;
		System.out.println ("Serial ID after adding the count is " + s_ID);

		System.out.println ("Comparing serial id from settings page with inventory item's page");
		if(serial_ID == s_ID)
		{
			System.out.println ("Serial Id from inventory item matches with that of the settings page");
			return serialIDs[5];
		}
		else
		{
			System.out.println ("Serial Id from inventory item does not match with that of the settings page");
			return null;
		}



		/* while(count>0)
			 {
				 System.out.println ("-----------");
				 int k = 1;
				 serial_Number = serial_Number+1;
				 System.out.println (serial_Number);
				 System.out.println ("Serial ID " + serialIDs[k]);
				 int j = Integer.parseInt(serialIDs[k]);
				 System.out.println ("J " + j);

				 if(serial_Number == j)
				 {
					 System.out.println ("*****************");
				 }
				 k++;
				 count--;

			 }*/
	}

	// Navigate to Inventory Settings page
	public void NavigateToInventorySettingsPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventorySetup/detail/1");	
		CommonFunctions.Wait(_driver, By.name("inventoryBinsBooleanCheck"));
		assertEquals("Inventory Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInventorySettingsPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inventory Settings page appears****");
	} 
	// Navigate to Inventory Settings page
	public void UncheckLocationStockLevel(boolean sUseLocStock) throws Exception
	{

		_driver.findElement(By.xpath("//a[text()='Other Settings']")).click();
		Thread.sleep(1000);
		CommonFunctions.sSelectCheckBox(_driver,sUseLocStock, By.name(Locators.getProperty(Locators.Inventory_Setting_Use_Location_Stock_Level)));
	} 

	public void CheckInventoryBin(boolean sUseLocStock) throws Exception
	{

		CommonFunctions.sSelectCheckBox(_driver,sUseLocStock, By.name(Locators.getProperty(Locators.Inventory_Bin)));
	}  
	public void UncheckCheckPlannedWork(boolean PlannedWork) throws Exception
	{

		_driver.findElement(By.xpath("//a[text()='Other Settings']")).click();
		Thread.sleep(1000);
		CommonFunctions.sSelectCheckBox(_driver,PlannedWork, By.name("checkPlannedWorkBooleanCheck"));
	} 
	//Approve Inventory Transactions
	//This method will only be used when Auto Post/Auto approve will be disabled from either the inventory item or inventory settings
	//This will accept the inventory id as parameter from AddNewInventoryItem()

	public boolean ApproveInventoryTransactions(String inventoryID) throws Exception
	{
		boolean isSelected = false;
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{		
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryTrn/approve");
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryBatch/approve"); 
		}
		System.out.println ("****Approve Inventory Transactions Page Appears****");

		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Inventory_Search_Field)), "description");
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		Thread.sleep(5000);
		
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("approvedBooleanCheck"));
		Update();
		Thread.sleep(2000);
		boolean approvedCheckBox= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Updated_text)));
		System.out.println("approvedCheckBox is "+approvedCheckBox);
		// _driver.findElement(By.name(Locators.getProperty(Locators.Approved_Check_Box))).isSelected()
		if(approvedCheckBox == true)
		{
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Approved_Check_Box))).isSelected())
			{
				System.out.println ("**Inventory transaction is approved**");
				return true;


			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Approved_Check_Box))).click();
				System.out.println ("**Inventory Transaction was not approved. Approving it now**");
				Thread.sleep(2000);
				Update();
				Thread.sleep(2000);
				isSelected = _driver.findElement(By.name("approvedBooleanCheck")).isSelected(); 
				return isSelected;
			}

		}
		else
		{
			return false;
		}
	}
	// Navigate to Post an Inventory Transaction page
	public void NavigateToPostInventoryTransactionPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{		
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InventoryTrn.post");
			Thread.sleep(2000);
		}
		else
		{

			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InventoryBatch.post");
			Thread.sleep(2000);
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("postBatch"));
		assertEquals("Posting Inventory Transactions", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToPostInventoryTransactionPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Posting Inventory Transactions Page appears****");		 

	}

	// Post an Inventory Transaction with today's date
	public void PostInventoryTransactions() throws Exception
	{
		NavigateToPostInventoryTransactionPage();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Post_Transaction_Date))).sendKeys("T");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Post_Transactions))).click();
		Thread.sleep(11000);		 
	}

	// Navigate to Posted Inventory Transaction page/Items History Page
	public void NavigateToPostedInventoryTransactionPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{	
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PostedInventoryTrn/list");
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/listPosted");
		}
		//CommonFunctions.Wait(_driver, By.linkText(""));
		//assertEquals("Inventory Items", _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToItemHistoryPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Items History Page appears****");		 

	}

	//Inventory Item History Page
	public boolean InventoryItemHistoryPage(String inventoryID) throws Exception
	{

		NavigateToPostedInventoryTransactionPage();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Search_Field)), "Inventory Item");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();

		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Posted Transactions"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000); 

		String sTitle1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		String sText = _driver.findElement(By.xpath("//div[@id='contents']/div/div[6]/div[2]/div/div")).getText();
		sText = sText .trim();
		System.out.println("The Unit Price before trimming is " +sText);
		sText = sText.replace("$","");
		sText = sText.replace(".00","");
		System.out.println("The Unit Price is " +sText);

		if(sTitle1.equals("Posted Inventory Transaction"))
		{
			System.out.println("Inventory Item " + inventoryID + " has been posted");
			return true;
		}
		else
		{
			return false;
		}


	}

	//Inventory Item History Page only for Charge Job transactions
	public boolean InventoryItemHistoryPageChargeJob(String inventoryID) throws Exception
	{

		NavigateToPostedInventoryTransactionPage();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Search_Field)), "Inventory Item");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();

		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Posted Transactions"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass_1))).click(); 
		}			 
		Thread.sleep(2000); 

		String sTitle1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		String sText = _driver.findElement(By.xpath("//div[@id='contents']/div/div[6]/div[2]/div/div")).getText();
		sText = sText .trim();
		System.out.println("The Unit Price before trimming is " +sText);
		sText = sText.replace("$","");
		sText = sText.replace(".00","");
		System.out.println("The Unit Price is " +sText);

		if(sTitle1.equals("Posted Inventory Transaction"))
		{
			System.out.println("Inventory Item " + inventoryID + " has been posted");
			return true;
		}
		else
		{
			return false;
		}


	}		 
	// Posted Inventory Transactions page
	public String PostedInventoryTransactions(String inventoryID) throws Exception
	{

		NavigateToPostedInventoryTransactionPage();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Search_Field)), "Inventory Item");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();

		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Posted Transactions"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000); 

		String sTitle1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		String sText = _driver.findElement(By.xpath("//div[@id='contents']/div/div[6]/div[2]/div/div")).getText();
		sText = sText .trim();
		System.out.println("The Unit Price before trimming is " +sText);
		sText = sText.replace("$","");
		sText = sText.replace(".00","");
		System.out.println("The Unit Price is " +sText);

		if(sTitle1.equals("Posted Inventory Transaction"))
		{
			System.out.println("Inventory Item " + inventoryID + " has been posted");
			return sText;
		}
		else
		{
			return "";
		}


	}

	// Delete an Inventory Item
	// This method should be run after AddNewInventoryItem() as it will use inventory ID as parameter

	public void DeleteInventoryItem1(String inventoryID) throws Exception
	{
		NavigateToInventoryItemListPage();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		System.out.println("test ");
		String sTitle1 = _driver.getTitle();

		System.out.println("title of the page is "+sTitle1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("title of the page is "+sTitle);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Delete))).click();
		Thread.sleep(2000);
		System.out.println("Clicking the Delete button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Popup_Delete))).click();
		System.out.println("Clicking the Delete button in popup");

		// _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Deleted))).isDisplayed();
		boolean delete = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Item_Deleted)));
		if (delete == true)
		{
			System.out.println("Inventory Item has been deleted");
		}
		else
		{
			System.out.println("Inventory Item could not be deleted");
		}
	}


	// Set the costing method as replacement cost, average cost
	public void CostingMethods (String sCostingMethod) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Costing_Method)), sCostingMethod);
		Thread.sleep(1000);
		Update();
	}

	// Enable/Disable Inventory Bin, Serial ID, Allow Auto Post in Inventory Settings tab 
	// Includes the method NavigateToInventorySettingsPage()

	public void InventorySettings1(boolean sFlag,String sLoc,String sCostingMethod) throws Exception
	{
		NavigateToInventorySettingsPage();
		if(sFlag == true)
		{
			System.out.println("Enabling");
			if(_driver.findElement(By.name(sLoc)).isSelected())
			{
				System.out.println("already selected");
			}
			else
			{
				_driver.findElement(By.name(sLoc)).click(); 
				System.out.println("Checking Inventory Bins");
			}
		}	

		else
		{
			System.out.println("Disabling");
			if(_driver.findElement(By.name(sLoc)).isSelected())
			{
				_driver.findElement(By.name(sLoc)).click(); 
				System.out.println("UnChecking");
			}
			else
			{

			}
		}
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Costing_Method)), sCostingMethod);
		Thread.sleep(1000);
		Update();
	}

	public String InventorySettings(boolean invBin, boolean serialID, boolean allowAutoPost, boolean noNegativeQuantity, boolean quantityBasedPricing, String sCostingMethod) throws Exception
	{

		PurchasePage PO = new PurchasePage(_driver);
		String serialNumber = null;
		NavigateToInventorySettingsPage();
		Thread.sleep(3000);
		PO.sSelectCheckBox(invBin, By.name(Locators.getProperty(Locators.Inventory_Bin)));
		PO.sSelectCheckBox(serialID, By.name(Locators.getProperty(Locators.Inventory_SerialID)));

		_driver.findElement(By.xpath("//a[text()='Inventory Setup Numbering']")).click();
		Thread.sleep(3000);
		serialNumber = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_SerialID_Field))).getAttribute("value");
		System.out.println("As per settings page, serial ID starts from " + serialNumber);

		_driver.findElement(By.linkText(Locators.getProperty(Locators.Other_Settings))).click();
		System.out.println("Go to the Other Settings tab");
		Thread.sleep(3000);
		PO.sSelectCheckBox(quantityBasedPricing, By.name(Locators.getProperty(Locators.Inventory_Quantity_Base_Pricing)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Costing_Method)), sCostingMethod);
		Thread.sleep(1000);
		Update();
		Thread.sleep(3000);
		return serialNumber;


	}

	// Verify the fields in Ask Settings tab of Inventory settings	 
	public boolean  VerifyAskSettings() throws Exception
	{
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Location))).isSelected())
		{
			return true;

		}
		else
		{
			return false;
		}

	}

	// Navigate to Inventory Item Type List page
	public void  NavigateInventoryItemTypeListPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItemType/list");	
		//CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction)));
		assertEquals("Inventory Item Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateInventoryItemTypeListPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inventory Item Type List Page appears****");
	}

	// Add a new Inventory Item Type of paper, ink
	// This will return the Item Type ID
	// TO IMPLEMENT --> Default Convert Job Type
	public String  AddNewInventoryItemType(boolean paper, boolean ink) throws Exception
	{
		NavigateInventoryItemTypeListPage();
		System.out.println("Clicking the Add New Record button");
		String Item_Type = UniqueNum1();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(Item_Type);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("test automation");

		if (paper == true)
		{
			System.out.println("Enable the checkbox for Paper");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Paper))).isSelected())
			{
				System.out.println("This inventory type has paper enabled");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Paper))).click();
				System.out.println("This inventory type has paper enabled");
			}
		}
		else
		{
			System.out.println("Disable the checkbox for Paper");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Paper))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Paper))).click();
				System.out.println("This inventory type has paper disabled");
			}
			else
			{
				System.out.println("This inventory type has paper disabled");
			}
		}
		if (ink == true)
		{
			System.out.println("Enable the checkbox for Ink");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ink))).isSelected())
			{
				System.out.println("This inventory type has ink enabled");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ink))).click();
				System.out.println("This inventory type has ink enabled");
			}
		}
		else
		{
			System.out.println("Disable the checkbox for Ink");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ink))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ink))).click();
				System.out.println("This inventory type has ink disabled");
			}
			else
			{
				System.out.println("This inventory type has ink disabled");
			}
		}

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Default_Convet_Job_Type)), "Sheetfed Print Job");
		System.out.println("Setting the job type as Sheetfed Print Job");
		//Assuming system has a job type as Sheetfed Print Job

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add))).click();

		Object_Added();

		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.Object_added_text))).isDisplayed())
		{
			return Item_Type;
		}
		else
		{
			return null;
		}

	}

	// In PO settings page, ensure that Allow Auto Post is enabled
	// Includes the method NavigateToPurchaseOrderSettingsPage()
	public void POSettings(boolean autoApproveInventoryTransaction) throws Exception
	{
		NavigateToPurchaseOrderSettingsPage();
		if(autoApproveInventoryTransaction == true)
		{
			System.out.println("Enabling Auto Approve Inventory Transation");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction))).isSelected() )
			{
				System.out.println("Auto Approve Inventory Transation is already selected");
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction))).click(); 
				System.out.println("Enabling Auto Approve Inventory Transation checkbox");
			}
		}
		else
		{
			System.out.println("Disabling Auto Approve Inventory Transation");
			if(_driver.findElement(By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction))).click(); 
				System.out.println("UnChecking Auto Approve Inventory Transation");
			}
			else
			{
				System.out.println("Auto Approve Inventory Transation 8is already disabled");
			}
		}


		int unitPrice = _driver.findElements(By.name("defaultInventoryUnitPrice")).size();
		if(unitPrice > 0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("defaultInventoryUnitPrice"), "Average");
		}


		Update();
	}	 

	// Navigate to Purchase Order Settings page
	public void NavigateToPurchaseOrderSettingsPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/POSetup/detail/1");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Auto_Approve_Inventory_Transaction)));
		assertEquals("Purchase Order Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToPurchaseOrderSettingsPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Purchase Order Setup Page appears****");
	}

	// Create a Purchase Order for an inventory item. Open the inventory item and click on Create Purchase Order
	// Always run the method AddNewInventoryItem() before this to get the Inventory ID
	// This method will also receive that PO
	public String CreatePurchaseOrder (String inventoryID) throws Exception
	{
		NavigateToInventoryItemListPage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_PO))).click();
		Thread.sleep(2000);
		System.out.println("Clicking the Purchase Order button");

		boolean poOrderStatus= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.PO_Order_Status)));
		if(poOrderStatus == true)
		{
			System.out.println("**Purchase Order is created**");
		}
		else
		{
			System.out.println("**Purchase Order could not be created**");
		}

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)), "Open");

		System.out.println("Changing the Order Status from 'Pending' to 'Open'");

		Update();

		String PO_No = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Number))).getText();
		System.out.println ("and the purchase order number is " +PO_No);

		/*_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Context_Dropdown))).click();
			 System.out.println ("Opening the context dropdown");
			 Thread.sleep(2000);
			 _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Receive))).click();
			 System.out.println ("Clicking the Receive link");

			 System.out.println ("****Purchase Order Receive Page Appears****");

			 _driver.findElement(By.name(Locators.getProperty(Locators.PO_Update_and_Receive))).click();
			 System.out.println("Receiving the PO");*/



		boolean poNoFlag= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.PO_Number))); 
		if(poNoFlag == true)
		{
			return PO_No;
		}
		else
		{
			return null;
		}



	}

	// Create a Purchase Order for an inventory item. Open the inventory item and click on Create Purchase Order
	// Always run the method AddNewInventoryItem() before this to get the Inventory ID
	// This method will also receive that PO
	public String CreatePurchaseOrderNew (String inventoryID) throws Exception
	{
		String PO_No = "";
		NavigateToInventoryItemListPage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		String originalHandle = _driver.getWindowHandle();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_PO))).click();
		System.out.println("Clicking the Purchase Order button");

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Purchase Order")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name("purchaseOrderType"), 1);
					Thread.sleep(1000);
					System.out.println("Changing the Order Status from 'Pending' to 'Open'");
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)), "Open");
					Thread.sleep(1000);
					CommonFunctions.SendValue(_driver, By.name("qtyOrdered"), "10000");
					//						CommonFunctions.SendValue(_driver, By.name("qtyRequired"), "10000");						
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					CommonFunctions.waitForPageLoaded(_driver);
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));

					//						 PO_No = CommonFunctions.GetText(_driver, By.xpath("//li[contains(text(),'PurchaseOrder')]"));
					PO_No = CommonFunctions.GetText(_driver, By.xpath("//label[text()='PO ID #']/../following-sibling::div/div[1]/a"));
					PO_No = PO_No.replace("PurchaseOrder (", " ").replace(")", "").trim();
					System.out.println ("and the purchase order number is " +PO_No);

					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
					Thread.sleep(2000); 	
					_driver.switchTo().window(originalHandle).getTitle().contains("Inventory Item");
				}

				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().contains("Inventory Item");
				}
			}
		}

		boolean poNoFlag= CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text))) ;
		if(poNoFlag && !PO_No.equals(""))
		{
			return PO_No;
		}
		else
		{
			return null;
		}
	}

	public boolean DeleteInventoryItem(String inventoryID) throws Exception
	{
		boolean deleteInventoryItem = false;
		NavigateToInventoryItemListPage();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		System.out.println("Entering the inventory id as " + inventoryID+ " in search field");
		String sTitle1 = _driver.getTitle();
		System.out.println("title of the page is "+sTitle1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		System.out.println("Clicking the Find button");
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("title of the page is "+sTitle);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle.equals("Inventory Items"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
		}			 
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Delete))).click();
		Thread.sleep(2000);
		System.out.println("Clicking the Delete button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Popup_Delete))).click();
		System.out.println("Clicking the Delete button in popup");

		// _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Deleted))).isDisplayed();
		boolean delete = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Item_Deleted)));
		if (delete == true)
		{
			System.out.println("Inventory Item has been deleted");
			deleteInventoryItem = true;
			return deleteInventoryItem;
		}
		else
		{
			System.out.println("Inventory Item could not be deleted");
			deleteInventoryItem = false;
			return deleteInventoryItem;
		}
	}


	public String CreateInventoryBin() throws Exception
	{
		String invLOCandBIN = null;
		String ID = UniqueNum1();
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLocation/list");	
		/*CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record)));
				assertEquals("Purchase Orders", _driver.getTitle());
				NewFileNamePath = TakeScreenShot.getDestinationFile("InventoryMovement");
				File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(NewFileNamePath));*/
		System.out.println("****Inventory Locations Page appears****");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Inventory_Location_ID)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Location_ID))).sendKeys(ID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("test_automation");
		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLocation/detail/"+ID+"?tab=1");
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click();
		System.out.println("****Inventory Bin Page appears****");

		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			System.err.println("check 1");
			for (String windowId : availableWindows) 
			{
				System.err.println("check 2");
				Thread.sleep(1000);
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Inventory Bin")) 
				{
					System.err.println("check 3");
					boolean code= CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Inventory_Location_ID))); 
					System.err.println("code is: "+code);
					Thread.sleep(1000);
					if (code == true)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Location_ID))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Location_ID))).sendKeys("test");
						Thread.sleep(5000);
						System.out.println ("Entering the code as test");
						_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
						System.out.println ("Clicking the ADD button");
						Thread.sleep(2000);
						invLOCandBIN = ID;
					}
					else
					{
						System.out.println ("Could not enter the code");
						invLOCandBIN = null;
					}	

					System.err.println("check 4");
					System.out.println ("Inventory bin has been added");
					// _driver.close();	
					_driver.switchTo().window(originalHandle).getTitle().equals("Inventory Location "+ ID);
				}

				else 
				{
					System.err.println("check 5");
					_driver.switchTo().window(originalHandle).getTitle().equals("Inventory Location "+ ID);
					invLOCandBIN = null;			
				}
			}
		}	
		System.err.println("check 6");
		System.out.println ("Inventory location has been created having ID = " +invLOCandBIN);
		return invLOCandBIN;

	}
	// Navigate to Receive Purchase Order page
	public void NavigateToReceivePurchaseOrder() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderLine/receive");	
		//CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Inventory_Location)));
		assertEquals("Purchase Orders", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToReceivePurchaseOrder");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Receive Purchase Order Page appears****");
	} 


	// Search and receive a Purchase Order
	// This should run after the method CreatePurchaseOrder()
	// CreatePurchaseOrder() will return PO_No which this will take in as a parameter
	public void ReceivePurchaseOrder(String purchase_order) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/list");
		Thread.sleep(3000);
		System.out.println ("****Purchase Order List Page Appears****");
		purchase_order = purchase_order.trim();
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Find_Jobs)), "Open");
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Search))).sendKeys(purchase_order);
		System.out.println("PO Number entered is " +purchase_order);
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Find))).click();
		System.out.println("Clicking the find button");
		Thread.sleep(1000);
		if(_driver.getTitle().equals("Purchase order "+purchase_order))
		{
			System.out.println("Navigated to Purchase");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='Open']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sPO = _driver.findElement(By.xpath("//table[@id='Open']/tbody/tr["+i+"]/td[3]/div")).getText();
				sPO =sPO.trim();
				System.out.println("PO is "+sPO);
				if(sPO.equals(purchase_order))
				{
					_driver.findElement(By.xpath("//table[@id='Open']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}
		/*
			 if(sTitle.equals("Purchase order "+purchase_order))
			 {
				 System.out.println("Click the magnifying glass to go to the detail page");
				 _driver.findElement(By.xpath(Locators.getProperty(Locators.Magnifying_Glass))).click(); 
			  }
			  else
			  {
			  }			 
			 Thread.sleep(2000);
		 */
		System.out.println ("****Purchase Order Detail Page Re-Appears****");
		Thread.sleep(2000);
		PO.NavigateToPOReceive();

		System.out.println ("****Purchase Order Receive Page Appears****");

		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Update_and_Receive))).click();
		System.out.println("Receiving the PO");
		Thread.sleep(2000);

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Received_Message))).isDisplayed())
		{
			System.out.println ("**PO is received**");
		}
		else
		{
			System.out.println ("**PO is not received**");
		}
		/*if (_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Auto_Post_Message))).isDisplayed())
			  {
				  System.out.println ("**PO is auto approved**");
			  }
			 else
			  {
				  System.out.println ("**PO is not auto approved**");
			  }*/
	}

	// Go to PO and open the inventory line item and delete it
	// This should run after the method CreatePurchaseOrder()
	// CreatePurchaseOrder() will return PO_No which this will take in as a parameter
	public boolean DeleteInventoryItemInPO(String purchase_order) throws Exception
	{
		String originalHandle;
		boolean deletePOlineItem = false;
		DCPage DC = new DCPage(_driver);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/list");
		Thread.sleep(3000);
		System.out.println ("****Purchase Order List Page Appears****");
		purchase_order = purchase_order.trim();


		CommonFunctions.selectDropdown(_driver, By.name("list"), "Open");
		Thread.sleep(1000);
		System.out.println ("****Purchase Order Detail Page Re-Appears****");
		DC.SearchValue(purchase_order,"poNumber");
		Thread.sleep(5000);
		if(_driver.getTitle().equals("Purchase order "+purchase_order))
		{
			System.out.println("Navigated to Estimate");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='Open']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sPO = _driver.findElement(By.xpath("//table[@id='Open']/tbody/tr["+i+"]/td[3]/div")).getText();
				sPO =sPO.trim();
				System.out.println("sPO is "+sPO);
				if(sPO.equals(purchase_order))
				{
					_driver.findElement(By.xpath("//table[@id='Open']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}
		originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		if(_driver.findElements(By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr")).size()>0)
		{
			_driver.findElement(By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[1]/td[2]/div/a/img")).click();
		}
		else
		{
			_driver.findElement(By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr[1]/td[2]/div/a/img")).click();	
		}
		Thread.sleep(5000);
		System.out.println ("**Opening the inventory line item**");

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line test_automation")) 
				{	
					boolean delete= CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.PO_Delete_InventoryLine))); 

					if (delete == true)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						Thread.sleep(2000);
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(2000);
						deletePOlineItem = true;

					}
					else
					{
						System.out.println ("**Delete button is not identified**");
						deletePOlineItem = false;
					}	
					//	 _driver.close();	
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+ purchase_order);
				}
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+ purchase_order);
					deletePOlineItem = false;

				}
			}
		}	
		return deletePOlineItem;
	}	 

	// Navigate to Add new job page
	public void NavigateToAddNewJobPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/add");	
		Thread.sleep(1000);
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
		//						_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver, "CreateNewJob");
		System.out.println("****Add a new Job Page appears****");
	}


	// Create a new job
	// Includes the method NavigateToAddNewJobPage()

	public String  UniqueNum2()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String CreateNewJob() throws Exception
	{

		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			Thread.sleep(1000);
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);			
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_IC)));
//		String jobID = "J_" + UniqueNum2();
//		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
//		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("Description for Job");
		System.out.println("Description entered is test_job");

		try{CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), "Sheetfed Print Job");}
		catch(Exception e){CommonFunctions.selectDropdownByIndex(_driver, By.name("jobType"), 1);}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		// Verify that the object has been added
		// Object_Added();
		System.out.println("****Object has been added and Job Detail Page appears****");

		// Fetching the Job ID from Job Detail Page
		String jobID = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_ID))).getText();
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

	public String CreateNewJobWithJobProject(String sJobProject) throws Exception
	{

		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).clear();
			Thread.sleep(1000);
			_driver.findElement(By.name("customer")).sendKeys("HOUSE");
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
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
		String jobID = "H_" + UniqueNum1();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");


		WebElement sSize=  _driver.findElement(By.xpath("//select[@name='jobProject']"));
		int sEle = sSize.findElements(By.tagName("option")).size();
		System.out.println("No of Elements present in dropdown is "+sEle);
		sEle=sEle-1;
		//	 _driver.findElement(By.name("jobProject")).click();
		//	 Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProject"), sEle);
		Thread.sleep(5000);
		System.out.println("Clicking the Add button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		Thread.sleep(3000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		// Verify that the object has been added
		Object_Added();
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

	public String CreateJob() throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNum = dateFormat.format(date);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();	 
		String jobID = "S"+sUniqueNum+"-03";
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);

		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys("HOUSE");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
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

	public String CreateJobItem(String sCustomer) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys(sCustomer);
			Thread.sleep(1000);
			//_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_IC)));	 
		String jobID = "K_" + UniqueNum5();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("QA Description for Job "+jobID);
		System.out.println("Description entered is test_job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		// Verify that the object has been added
		Object_Added();
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

	public String CreateJobItemWithDescription(String sCustomer,String desc,String AddDesc) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys(sCustomer);

			Thread.sleep(1000);
			//_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
			_driver.findElement(By.name("customer")).sendKeys(Keys.TAB);
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_IC)));	 
		//String jobID = "T_" + UniqueNum5();
		String jobID = CommonFunctions.getUniqueNumDigit("Job", 'K', 5);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), "Sheetfed Print Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is "+sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(desc);
		System.out.println("Description entered is "+desc);

		_driver.findElement(By.name("description2")).clear();
		_driver.findElement(By.name("description2")).sendKeys(AddDesc);
		System.out.println("Additional Description entered is "+AddDesc);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		// Verify that the object has been added
		Object_Added();
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
	public String CreateJobItemWithDescription(String sCustomer,String desc,String AddDesc,String JobType) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys(sCustomer);
			Thread.sleep(1000);
			//_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_IC)));	 
		String jobID = "J_" + UniqueNum5();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), JobType);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is "+sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(desc);
		System.out.println("Description entered is "+desc);

		_driver.findElement(By.name("description2")).clear();
		_driver.findElement(By.name("description2")).sendKeys(AddDesc);
		System.out.println("Additional Description entered is "+AddDesc);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		// Verify that the object has been added
		Object_Added();
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


	public String CreateFinishedJobItem(String sCustomer,String desc,String AddDesc) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys(sCustomer);
			Thread.sleep(1000);
			//_driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_IC)));	 
		String jobID = "S_" + UniqueNum5();
		CommonFunctions.selectDropdownByText(_driver, By.name("jobType"), "Finished Goods Job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is "+sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys(desc);
		System.out.println("Description entered is "+desc);

		_driver.findElement(By.name("description2")).clear();
		_driver.findElement(By.name("description2")).sendKeys(AddDesc);
		System.out.println("Additional Description entered is "+AddDesc);
		CommonFunctions.ClickElement(_driver, By.id("finishedGoods"));
		CommonFunctions.SendValue(_driver, By.name("partDescription"), "PartDescriptioon");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");

		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_ID)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		// Verify that the object has been added
		Object_Added();
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
	public void CreateJobCreditHold(String sCustomer) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Entered Create New Job Method");
		NavigateToAddNewJobPage();
		if(_driver.findElements(By.name("customer")).size()>0)
		{
			_driver.findElement(By.name("customer")).sendKeys(sCustomer);
			Thread.sleep(1000);
			_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
			// _driver.findElement(By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/a")).click();
		}
		else if(_driver.findElements(By.name("jobProductType")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("jobProductType"), "10PG-10 Page");
			Thread.sleep(2000);
		}
		String jobID = "H_" + UniqueNum1();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_IC))).sendKeys(jobID);
		System.out.println("Job entered is " + jobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).click();
		System.out.println("Customer entered is House");
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Description))).sendKeys("test_job");
		System.out.println("Description entered is test_job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Add))).click();
		System.out.println("Clicking the Add button");
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		// Verify that the object has been added

	}

	public void NavigateToAddInventoryBatch() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion = FetchVersion();

		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryBatch/add");
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Inventory Batch", _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		/*	NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToAddInventoryTransactionPage");
	File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile, new File(NewFileNamePath));
	System.out.println("****Add Inventory Transaction page appears****");*/
	}
	
	public void NavigateToInventoryBatchList() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryBatch/list");
		CommonFunctions.Wait(_driver, By.name("performSearch"));
		assertEquals("Inventory Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInventoryBatchList");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inventory Batch list page appears****");
	}

	public void AddNewInvantoryTransaction(String sTransactionType,String sInvItem,String sQty) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("transactionType"), sTransactionType);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("inventoryItem"), sInvItem);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryLocation"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("quantity"), sQty);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("unitPrice"), "2");
		Thread.sleep(1000);
		//	CommonFunctions.selectDropdownByText(_driver, By.name("uom"), "C-/Hundred");
		Thread.sleep(1000);

	}
	
	public void SetCostingMethodInInevtorySettingPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Other Settings']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("costingMethod"), "Sell Price");
		Thread.sleep(1000);
		Update();
	}
	public void PostInventoryTransactions1(String sTransaction) throws Exception
	{
		NavigateToPostInventoryTransactionPage();
		CommonFunctions.waitForPageLoaded(_driver);
		String sVersion=FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{	
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Post_Transaction_Date))).sendKeys("T");
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Post_Transactions))).click();
			Thread.sleep(1000);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("postBatch"), sTransaction);
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Post_Transactions))).click();
			Thread.sleep(1000);
		}	 	

		//		 if(_driver.getTitle().equals("Inventory Batch Post Successful!"))
		//		 {
		//			 System.out.println("Inventory item posted");
		//		 }
		//		 else
		//		 {
		Thread.sleep(15000);
		//		 }
		CommonFunctions.waitForPageLoaded(_driver);
		//assertEquals("Inventory Batch Post Successful!", _driver.getTitle());
	}
	public void ApproveInventoryTransactions1(String sDesc) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		boolean isSelected = false;
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{		
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryTrn/approve");
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryBatch/approve"); 
		}
		System.out.println ("****Approve Inventory Transactions Page Appears****");

		CommonFunctions.selectDropdown(_driver, By.name("searchField"), "description");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();
		Thread.sleep(5000);
		PO.sSelectCheckBox(true,By.name("approvedBooleanCheck"));

	}

	public boolean InventoryItemHistoryPage1(String inventoryID,String sExpectedBatch,String sETransactionType) throws Exception
	{
		String sBatch="";


		NavigateToPostedInventoryTransactionPage();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Search_Field)), "Inventory Item");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Search))).sendKeys(inventoryID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Find))).click();

		Thread.sleep(5000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Posted Inventory Transaction"))
		{

			sBatch=_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Batch')]/following-sibling::div/div[1]/a[1]")).getText();
			sBatch=sBatch.trim();
		}

		else
		{
			int rowcount =  _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowcount is "+rowcount);
			for(int i =1;i<=rowcount;i++)
			{
				String sTransactionType = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[7]/div/a")).getText();
				sTransactionType=sTransactionType.trim();
				System.out.println("sTransactionType is "+sTransactionType);
				if(sTransactionType.equals("4 - Count") &&  sTransactionType.equals(sETransactionType) )
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//table[@id='relatedLines']/tbody/tr[1]/td[2]/div/a/div")).click();
					Thread.sleep(2000);
					sBatch=_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Batch')]/following-sibling::div/div[1]/a[1]")).getText();
					sBatch=sBatch.trim();
					break;

				}


				else if(sTransactionType.equals(sETransactionType))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);
					sBatch=_driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Batch')]/following-sibling::div/div[1]/a[1]")).getText();
					sBatch=sBatch.trim();
					break;
				}
			}
		}

		//	if(sBatch.equals(sExpectedBatch))
		if(sBatch.contains(sExpectedBatch))
		{
			System.out.println("sBatch " + sBatch + " has been posted");
			return true;
		}
		else
		{
			return false;
		}


	}		 


	public void SetVerifyNegativeQuantityInInevtorySettingPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Other Settings']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("verifyNegativeQuantity"), "On Post");
		Thread.sleep(500);
		Update();
	}

	public String getInvBatch(String InvItem) throws Exception
	{

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/list");	
		CommonFunctions.waitForPageLoaded(_driver);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "Inventory Item");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Search_TextField)), InvItem);
		CommonFunctions.ClickElement(_driver, By.name(Locators.getProperty(Locators.Find)));
		CommonFunctions.waitForPageLoaded(_driver);

		String sInvBatch = CommonFunctions.GetSelectedOption(_driver, By.name("inventoryBatch"));
		String sInvBatchID = CommonFunctions.GetSelectedOptionValue(_driver, By.name("inventoryBatch"));
		String sInvBatchDesc = sInvBatch.replace(sInvBatchID+"-", "").trim();

		return sInvBatchDesc;
	}

	public void setCertificationAuthority(String CertificationAutority, String CertificaitonLevel, String CertificateNumber) throws Exception
	{
		System.out.println("Setting Certificaiton Autority and level to the inventory item");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Certifications']"));	
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='inventoryItemCertification_fieldset']/div/div[1]//input[@value='Add Certification']"));
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='inventoryItemCertification']/tbody/tr"));
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='inventoryItemCertification']/tbody/tr["+iRwCnt+"]//select[@name='inventoryItemCertification.certificationAuthority']"), CertificationAutority);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='inventoryItemCertification']/tbody/tr["+iRwCnt+"]//select[@name='inventoryItemCertification.certificationLevel']"), CertificaitonLevel);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='inventoryItemCertification']/tbody/tr["+iRwCnt+"]//input[@name='inventoryItemCertification.certificateNumber']"), CertificateNumber);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}


	public void AddMaterialWithCertification(String sJob, String sInv, String sPlannedQty, String CertificationAutority, String CertificationLevel) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		CommonFunctions.waitForPageLoaded(_driver);		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Details_Materials_Tab)));
		String originalHandle = _driver.getWindowHandle();
		String sGetTitle = _driver.getTitle();
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
					if (!CertificationAutority.equals(""))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("certificationAuthority"), CertificationAutority);
						Thread.sleep(1000);
					}
					if (!CertificationLevel.equals(""))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("certificationLevel"), CertificationLevel);
						Thread.sleep(1000);
					}

					if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Item))).size()>0)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);	
					}
					else
					{
						String originalHandle1 = _driver.getWindowHandle();
						String sGetTitle1 = _driver.getTitle();
						CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]"));						
						Thread.sleep(3000);
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{
								if(_driver.switchTo().window(windowId1).getTitle().contains("Please select an Inventory Item")) 
								{
									DC.Search(sInv,"id");
									CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));									 
									_driver.switchTo().window(originalHandle1).getTitle().equals(sGetTitle1);
								}									
							}
						}
					}
					if(_driver.findElements(By.name("altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("altCurrency"), "USD");
					}
					CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), sPlannedQty);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(2000);
					break;
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sGetTitle);		
				}		
			}
		}
		_driver.switchTo().window(originalHandle);
	}

	// Navigate to Inventory Item List page
	public void NavigateToInventoryItemDetailPage(String InventoryItem) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String InventoryItem1 = InventoryItem.trim().replace(" ", "%20");
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItem/detail/"+InventoryItem1);	
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("inventoryItemType"));
		assertEquals("Inventory Item "+InventoryItem, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToInventoryItemDetailPage");
		System.out.println("****Inventory Item Detail Page appears****");
	} 

	// Navigate to Item that needs Review page
	public void NavigateToItemThatNeedsReviewPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);		
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/listNeedsReview?tab=0");		
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("list"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToItemThatNeedsReviewPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Item That Needs Review Page appears****");
	}
	
	// Navigate to approve transactions page
	public void NavigateToApproveTransactionsPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);		
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryBatch/approve");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToApproveTransactionsPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Approve Transactions Page appears****");
	}
	
	// Navigate to edit transactions page
	public void NavigateToEditTransactionsPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);		
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToEditTransactionsPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Edit Transactions Page appears****");
	}
	
	// NavigateToInventoryItemTypeDetailPage
	public void NavigateToInventoryItemTypeDetailPage(String InvItemTypeID) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItemType/detail/"+InvItemTypeID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("verifyNegativeQuantity "));
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInventoryItemTypeDetailPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inv Item Type Page appears****");
	}
	
	//navigate to InvPostingScheduledTask page
	public void NavigateToInvPostingScheduledTaskPage(String ScheduledTaskID) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ScheduledTask/detail/"+ScheduledTaskID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("maximumExecutionResultsToStore "));
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInvPostingScheduledTaskPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inv Posting Scheduled task Page appears****");
	}
	
	//modify inventory posting scheduled task execution duration
	public void modifyInvPostingScheduledtaskExecutionDuration(String ScheduledTaskID, String ExecutionType, String Frequency) throws Exception
	{
		APPage AP = new APPage(_driver);
		
		NavigateToInvPostingScheduledTaskPage(ScheduledTaskID);
		String sOriginalWindow = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='Recurrence_schedule']/tbody/tr/td[2]/div/a/img"));
		AP.SwitchToWindow("Recurrence");
		CommonFunctions.selectDropdownByText(_driver, By.name("type"), ExecutionType);
		CommonFunctions.SendValue(_driver, By.name("frequency"), Frequency);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		_driver.switchTo().window(sOriginalWindow);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}

	public void PerformInventoryTransaction(String InventoryItem, String TransactionType, String LocationID, String BinID, String TransfertoLocationID, String TransfertoBinID, String Quanity, String Job, String SerialID, String NumSerialIDs, String UnitPrice) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		String sInvBatchDesc = UniqueNum();
		
		System.out.println("Add new Inventory Batch");
		NavigateToAddInventoryBatch();
		CommonFunctions.SendValue(_driver, By.name("description"), sInvBatchDesc);
		DC.Add();
		String sID = CommonFunctions.getIDfromURL(_driver);
		String sInvBatch = sID+"-"+sInvBatchDesc;	
		
		AddTransaction(InventoryItem, TransactionType, LocationID, BinID, TransfertoLocationID, TransfertoBinID, Quanity, Job, SerialID, NumSerialIDs, UnitPrice);
		
		System.out.println("Approve Inventory Batch");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Return To Inventory Batch']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("approvedBooleanCheck"));
		DC.Update();
		
		System.out.println("Post Inventory Transaction");
		PostInventoryTransactions1(sInvBatch);
	}
	
	public void AddTransaction(String InventoryItem, String TransactionType, String LocationID, String BinID, String TransfertoLocationID, String TransfertoBinID, String Quanity, String Job, String SerialID, String NumSerialIDs, String UnitPrice) throws Exception
	{
		DCPage DC = new DCPage(_driver);	
		
		System.out.println("Add new Inventory Transaction");
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='InventoryLines_fieldset']/div[1]/div[1]/div[2]/a"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdownByText(_driver, By.name("transactionType"), TransactionType);
		CommonFunctions.SendValue(_driver, By.name("inventoryItem"), InventoryItem);
		
		CommonFunctions.selectDropdown(_driver, By.name("inventoryLocation"), LocationID);
		_driver.findElement(By.name("inventoryLocation")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		if(!BinID.equals("")){CommonFunctions.selectDropdown(_driver, By.name("inventoryBin"), LocationID+":"+BinID);}
		else{CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);}
		
		CommonFunctions.SendValue(_driver, By.name("quantity"), Quanity);
		
		if(TransactionType.equals("7 - Transfer Locations"))
		{
			CommonFunctions.selectDropdown(_driver, By.name("transferToInventoryLocation"), TransfertoLocationID);
			_driver.findElement(By.name("transferToInventoryLocation")).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			if(!TransfertoBinID.equals("")){CommonFunctions.selectDropdown(_driver, By.name("transferToInventoryBinKey"), TransfertoLocationID+":"+TransfertoBinID);}
			else{CommonFunctions.selectDropdownByIndex(_driver, By.name("transferToInventoryBinKey"), 1);}
		}
		else if(TransactionType.equals("5 - Charge Job"))
		{
			CommonFunctions.SendValue(_driver, By.name("Job"), Job);
			_driver.findElement(By.name("Job")).sendKeys(Keys.TAB);
			Thread.sleep(2000);
		}
		else if(TransactionType.equals("1 - Receive"))
		{
			CommonFunctions.SendValue(_driver, By.name("unitPrice"), UnitPrice);
			_driver.findElement(By.name("unitPrice")).sendKeys(Keys.TAB);
		}
		
		if (CommonFunctions.isElementPresent(_driver, By.name("serialID")))
		{
			CommonFunctions.SendValue(_driver, By.name("serialID"), SerialID);
			CommonFunctions.SendValue(_driver, By.name("numSerialIDs"), NumSerialIDs);
		}
		
		DC.Add();
	}
	
	// Navigate to Inventory Item Detail page
	public void NavigateInventoryItemDetailPage(String InventoryItem) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryItem/detail/"+InventoryItem.toUpperCase());
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("inventoryItemType"));
		assertEquals("Inventory Item "+InventoryItem.toUpperCase(), _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateInventoryItemDetailPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inventory Item Detail Page appears****");
	}
	
	// Navigate to Add new Inventory lcoation page
	public void NavigatetoAddNewInventoryLocationPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLocation/add");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("code"));
		assertEquals("Adding Inventory Location", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoAddNewInventoryLocationPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Adding Inventory Location Page appears****");
	}
	
	// Add new Inventory location
	public String AddNewInventoryLocation() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		String LocationID = "IL"+UniqueNum5()+UniqueNum1();
		
		NavigatetoAddNewInventoryLocationPage();
		
		CommonFunctions.SendValue(_driver, By.name("code"), LocationID);
		CommonFunctions.SendValue(_driver, By.name("description"), "Inventory Location "+LocationID);
		DC.Add();
		
		return LocationID;
	}
	
	// Add new Inventory location
	public String AddNewInventoryLocationBin() throws Exception
	{
		APPage AP = new APPage(_driver);
		
		String sInvLocBinID = "ILB"+UniqueNum5()+UniqueNum1();
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Bins']"));
		
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='InventoryBin_N1004B_fieldset']/div/div[1]//a[text()='Add New']"));
		
		AP.SwitchToAnyWindow("Adding Inventory Bin");
		CommonFunctions.SendValue(_driver, By.name("code"), sInvLocBinID);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		Thread.sleep(2000);
		_driver.switchTo().window(sOriginalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
		
		return sInvLocBinID;
	}
	
	//add inventory item to be used in estimate
	public String CreateInventoryItem() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		ICPage IC = new ICPage(_driver);
		
		DateFormat dateFormat = new SimpleDateFormat("ddhhmmss");
		String sUniqueNumber = dateFormat.format(new Date());

		String sInv = "INVITEM"+sUniqueNumber;
		String sItemType ="1 - Paper,Sheets", sMinStock = "1000",sMaxStock="1000000000";
		String sPapertype= sUniqueNumber+" - Gloss Book";
		
		//--------create paper type----------
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PaperType/addDirect");
		CommonFunctions.Wait(_driver, By.name("standardPaperType"));
		CommonFunctions.SendValue(_driver, By.name("code"), sUniqueNumber);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("standardPaperType"), 1);
		CommonFunctions.SendValue(_driver, By.name("description"), "Gloss Book");
		CommonFunctions.selectDropdownByText(_driver, By.name("weightType"), "Caliper");
		CommonFunctions.selectDropdownByText(_driver, By.name("pressType"), "Heatset");
		CommonFunctions.selectDropdownByText(_driver, By.name("frontCoating"), "Glossy");
		CommonFunctions.selectDropdownByText(_driver, By.name("backCoating"), "Glossy");
		CommonFunctions.SendValue(_driver, By.name("texture"), "smooth");
		PO.sSelectCheckBox(true, By.name("allowPerfectingBooleanCheck"));
		CommonFunctions.SendValue(_driver, By.name("recycledPercentage"), "2%");
		DC.Add();
		Thread.sleep(3000);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Weights']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='contents']/div[2]/fieldset/div[1]/div[1]/div[2]/input"));
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='PaperWeight']/tbody/tr[1]/td[3]/input"), "60");
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='PaperWeight']/tbody/tr[1]/td[4]/input"), "0.0031");
		DC.Update();
		
		//----------------Add new Inventory Item--------
		IC.NavigateToInventorySettingsPage();
		IC.CheckInventoryBin(true);
		IC.UncheckLocationStockLevel(false);
		IC.Update();

		PO.AddNewInventory();
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.ID)), sInv);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Description)), "Letter Paper");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), sItemType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), "M-/Thousand");
		CommonFunctions.SendValue(_driver, By.name("unitWeight"), "1.25");
		
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='P']"));
		if(_driver.findElements(By.name("size")).size() > 0)
		{	CommonFunctions.SendValue(_driver, By.name("size"), "23 mm x 29 mm");	}
		else
		{	CommonFunctions.SendValue(_driver, By.name("sizeW"), "60");
			CommonFunctions.SendValue(_driver, By.name("sizeH"), "70");		}

		if(_driver.findElements(By.name("grainDirection")).size()>0)
		{CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");}
		
		CommonFunctions.SendValue(_driver, By.name("basis"), "80");
		CommonFunctions.SendValue(_driver, By.name("mWeight"), "0.55");

		CommonFunctions.selectDropdownByText(_driver, By.name("paperType"), sPapertype);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("paperWeight"), 1);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("mediaColorName"), 1);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='eService Info']"));
		CommonFunctions.SendValue(_driver, By.name("maxWeightPerBox"), "25");
		CommonFunctions.SendValue(_driver, By.name("sellUnitQty"), "0.15");
		CommonFunctions.SendValue(_driver, By.name("freightCost"), "$20.00");

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Inventory_Ordering_Info)));
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Min_Stock_Level)), sMinStock);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Max_Stock_Level)), sMaxStock);
		
		DC.Add();
		
		return sInv.toUpperCase();
	}

	// Navigate to Item history page
	public void NavigatetoItemHistoryPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InventoryLine/listPosted");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Posted Transactions", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoItemHistoryPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Posted Transactions Page appears****");
	}
	
	// Navigate to Product Group list page
	public void NavigatetoProductGroupListPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ProductGroup/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Product Groups", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoProductGroupListPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Product Groups List Page appears****");
	}
	
	//create product group
	public void CreateProductGroup(String ProductGroup) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		NavigatetoProductGroupListPage();
		DC.SearchValue(ProductGroup, "productGroup");
		CommonFunctions.waitForPageLoaded(_driver);
		
		if (_driver.getTitle().equals("Product Groups"))
		{
			System.out.println("Adding new product Group "+ProductGroup);
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.SendValue(_driver, By.name("productGroup"), ProductGroup);
			CommonFunctions.SendValue(_driver, By.name("description"), "Product Group "+ProductGroup);
			DC.Add();
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Sub Product Groups']"));
			String sOriginalWindow = _driver.getWindowHandle();
			CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='SubProductGroup_N10028_fieldset']/div/div[1]//a"));
			CommonFunctions.SwitchToWindow(_driver, "Adding Sub Product Group");
			CommonFunctions.SendValue(_driver, By.name("subProductGroup"), ProductGroup);
			CommonFunctions.SendValue(_driver, By.name("description"), "Sub Product Group "+ProductGroup);
			DC.Add();
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
			Thread.sleep(2000);
			_driver.switchTo().window(sOriginalWindow);
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.out.println("Product Group "+ProductGroup+" is already present.");
		}	
	}
	
	//add quantity price to inventory item
	public void addQuantityPrice(String Quantity, String Price, String Discount) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='Q']"));
		
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='QuantityPriceDiscount_fieldset']/div/div[1]//input"));
		CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='QuantityPriceDiscount_addRow']//input[@name='QuantityPriceDiscount.upToQuantity']"), Quantity);
		CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='QuantityPriceDiscount_addRow']//input[@name='QuantityPriceDiscount.price']"), Price);
		
		if(!Discount.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='QuantityPriceDiscount_addRow']//input[@name='QuantityPriceDiscount.priceDiscount']"), Discount);
		}
		
		DC.Update();
	}

	//check quantity allocated and sell price of job material
	public boolean checkJobMaterialSellPrice(String InventoryItem, String Quantity, String SellPrice) throws Exception
	{
		boolean verifiedSellPrice = false;
		int iRwNum = 0;
		
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
		
		if (iRwNum >= 1)
		{
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
						CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Inventory Item Info']"));
						String sQtyAllocated = CommonFunctions.GetText(_driver, By.xpath("//label[text()='Qty Allocated']/../following-sibling::div/div")).replace(",", "").trim();
						String sSellPrice = CommonFunctions.GetText(_driver, By.xpath("//label[text()='Sell Price']/../following-sibling::div/div"));
						
						if(sQtyAllocated.equals(Quantity) && sSellPrice.equals(SellPrice))
						{
							verifiedSellPrice = true;
							System.out.println("Quantity Allocated and Sell price are correclty dispalyed");
						}
						else
						{
							System.out.println("Quantity Allocated and Sell price are NOT correctly dispalyed" +
												"Quantity Allocated, Expected = "+Quantity+", Actual = " +sQtyAllocated+
												"Sell Price, Expected = "+SellPrice+", Actual = " +sSellPrice);
						}
						
						_driver.close();
						Thread.sleep(2000);
						break;
					}
				}
			}
		}
		else
		{
			System.err.println("Job Material was not found");
		}
		
		_driver.switchTo().window(originalHandle);
		CommonFunctions.waitForPageLoaded(_driver);
		return verifiedSellPrice;
	}
	
	//get inventory location quantity
	public String getInventoryLocationQuantity(String InventoryItem, String Location) throws Exception
	{
		String sQuantity = "";
		
		NavigateToInventoryItemDetailPage(InventoryItem);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Inventory  Location : "+InventoryItem+"']"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Quantities by Location']"));
		CommonFunctions.waitForPageLoaded(_driver);
		
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='nonzero']/tbody/tr"));
		for (int i=1;i<iRwCnt+1;i++)
		{
			String sInventoryLocation = CommonFunctions.GetText(_driver, By.xpath("//table[@id='nonzero']/tbody/tr["+i+"]/td[3]/div/a"));
			if(sInventoryLocation.equals(Location))
			{
				sQuantity = CommonFunctions.GetText(_driver, By.xpath("//table[@id='nonzero']/tbody/tr["+i+"]/td[4]/div")).replace(",", "").trim();
				break;
			}
		}
		
		return sQuantity;
	}
	
	//check inventory location reciept
	public boolean checkInventoryLocationReceipt(String InventoryItem, String Location, String Quantity) throws Exception
	{
		boolean sReceiptExists = false;
		
		NavigateToInventoryItemDetailPage(InventoryItem);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(), 'Inventory  Item : "+InventoryItem+"')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Receipts']"));
		CommonFunctions.waitForPageLoaded(_driver);
		
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr"));
		for (int i=1;i<iRwCnt+1;i++)
		{
			String sInventoryLocation = CommonFunctions.GetText(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]/div/a"));
			if(sInventoryLocation.equals(Location))
			{
				String sReceiptQuantity = CommonFunctions.GetText(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[6]/div")).replace(",", "").trim();
				
				if(sReceiptQuantity.equals(Quantity))
				{
					sReceiptExists = true;
				}				
				break;
			}
		}
		
		if (!sReceiptExists)
		{
			System.err.println("Receipt for "+Location+" doesnot exists");
		}
		return sReceiptExists;
	}
	
	//create eGoodsOrder
	public String createEGoodOrder(String Customer, String InventoryItem, String Quantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("customer"));
		
		CommonFunctions.SendValue(_driver, By.name("customer"), Customer);
		CommonFunctions.selectDropdown(_driver, By.name("inventoryItem"), InventoryItem);
		CommonFunctions.SendValue(_driver, By.name("quantity"), Quantity);
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Shipping Info']"));
		CommonFunctions.selectDropdownByIndex(_driver, By.name("shipToContact"), 1);
		
		DC.Add();
		
		if (_driver.getTitle().contains("Order Confirmation"))
		{			
			String[] aTitle = _driver.getTitle().split(":");
			String sOrder = aTitle[aTitle.length-1].trim();
			System.out.println("eGoods Order was created "+sOrder);
			return sOrder;
		}
		else
		{
			System.err.println("Unable to create eGoods order.");
			return "";
		}
	}
	
	// Navigate to Item history page
	public void NavigatetoSerialIDTransactionPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InventoryLine.chargeMultiSerialId");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("roll1"));
		assertEquals("Inventory Transaction Serial ID Charge Job", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoSerialIDTransactionPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Inventory Transaction Serial ID Charge Job Page appears****");
	}
	
	//navigate to Job Materials page
	public void JobPartJobMaterialPage(String Job, String Part) throws Exception
	{
		Job = Job.replace(" ", "%20");
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobMaterial/listJobPart/"+Job+"%3A"+Part);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Job Materials", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("JobPartJobMaterialPage");
		System.out.println("****Job Part Job Materials page appears****"); 
	}
	
	//Perform a Job Material Pull
	public boolean PerformJobMaterialPull(String Job, String Part, String InventoryItem, String PullQuantity) throws Exception
	{
		APPage AP = new APPage(_driver);
		
		boolean jobMaterialExists = false, pullTransaction = false;
		
		JobPartJobMaterialPage(Job, Part);
				
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr"));		
		for(int i=1;i<iRwCnt+1;i++)
		{
			String sInvItem = CommonFunctions.GetText(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div"));
			if(sInvItem.equals(InventoryItem))
			{
				jobMaterialExists = true;
				CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img"));
				CommonFunctions.waitForPageLoaded(_driver);
				break;
			}
		}
		
		if(jobMaterialExists)
		{
			String sOriginalWindow = _driver.getWindowHandle();
			
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PULL']"));
			Thread.sleep(2000);
			AP.SwitchToWindow("Adding Inventory Transaction");
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Inventory_Quantity)), PullQuantity);
			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))))
			{
				CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction)));
			}
			else
			{
				CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
			}
			Thread.sleep(2000);
			_driver.switchTo().window(sOriginalWindow);
			CommonFunctions.waitForPageLoaded(_driver);
			
			pullTransaction = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='fmessage']/ul/li[contains(text(), 'Successfully posted transaction(s) have been moved to a new batch')]"));
		}
		else
		{
			System.err.println("Job Material does not exists. Unable to pull material");
		}
		
		return pullTransaction;
	}
	
	// Navigate to Add new Inventory lcoation page
	public void NavigatetoObjectModelBrowserPage() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/model/list?customizations=true");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.id("browserAlphabetNav"));
		assertEquals("Object Browser", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoObjectModelBrowserPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Object Model Browser Page appears****");
	}
	
	// Navigate to Add new Inventory lcoation page
	public void NavigatetoSystemUserDetailPage(String SystemUser) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SystemUser/detail/"+SystemUser);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.id("punchoutUserName"));
		assertEquals("Detailed information for "+SystemUser, _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigatetoSystemUserDetailPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****System user Detail Page appears****");
	}
	
	// approve and post manual invnentory batch
	public void ApproveandPostManualBatch() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = df.format(new Date());
		String sInvBatchDesc = "Inventory lines for : "+sDate+" (Manual)";
		
		NavigateToApproveTransactionsPage();
		DC.SearchValue(sInvBatchDesc, "description");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("approvedBooleanCheck"));			
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("approvedBooleanCheck"));
		DC.Update();
		String sInvBatchID = CommonFunctions.getIDfromURL(_driver);
		
		NavigateToPostInventoryTransactionPage();
		CommonFunctions.selectDropdown(_driver, By.name("postBatch"), sInvBatchID);
		CommonFunctions.ClickElement(_driver, By.name(Locators.getProperty(Locators.Inventory_Post_Transactions)));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(11000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
	}
	
}