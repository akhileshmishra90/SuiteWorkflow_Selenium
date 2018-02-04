package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.sourceforge.htmlunit.corejs.javascript.ast.TryStatement;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import javax.print.DocFlavor.STRING;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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


public class APPage implements BaseElement {
	
	 Locators loc = new Locators();
	 protected static Locators _Locators = new Locators();
	 ReadAndUpdate dbConnection = new ReadAndUpdate();
	 private String _pageURL;
	 protected WebDriver _driver;
	 boolean acceptNextAlert = true;
	 ScreenShot TakeScreenShot = new ScreenShot();
	 String NewFileNamePath = null;
	 private static  String sFILEPATH = null;
	 private static  String sSERVER = null;
	 private static  String sCOMPANY = null;
	 
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	

	 public  APPage(WebDriver driver) throws Exception 
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
	
	public String AcceptModalDialog()
	{
	Alert alertDialog = _driver.switchTo().alert();
	String alertText = alertDialog.getText();
	System.out.println("alertText is "+ alertText);
	alertDialog.accept();
	return alertText;
	}
	
	//**************************** till here it is common for all*****************************

	 //Navigate to AP Settings page
	public void NavigateToAPSettingPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/APSetup/detail/1");	
    	CommonFunctions.Wait(_driver, By.linkText(Locators.getProperty(Locators.AP_Settings_Default_Tab)));
    	assertEquals("A/P Setup", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPSettingPage");
	}
	
	public void NavigateToAPVendorListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/list");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	assertEquals("Vendors", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPVendorListPage");
    	_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	public void NavigateToAPVendorTypeListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/VendorType/list");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	assertEquals("Vendor Types", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPVendorTypeListPage");
    	_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	
	public void NavigateToAPBillStatusListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BillStatus/list");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	assertEquals("Bill Statuses", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPBillStatusListPage");
    	_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	
	public void NavigateToAPVendorContactList(String sVendor) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/contacts/"+sVendor);	
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
    	assertEquals("Contacts", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPVendorContactList");
	}
	
	public void NavigateToAPVendorNoteList(String sVendor) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/VendorNote/list/"+sVendor);	
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
    	assertEquals("Vendor Notes", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAPVendorNoteList");
	}
	
	public void NavigateToAddNewBillBatch() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BillBatch/add");	
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));    	
    	assertEquals("Adding Bill Batch", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddNewBillBatch");
	}
	
	public void NavigateToAddNewPaymentBillBatch() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BillPaymentBatch/add");	
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));    	
    	assertEquals("Add BillPayment Batch", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddNewPaymentBillBatch");
	}
	
	public void NavigateToBillBatchList() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BillBatch/list");	
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Update)));    	
    	assertEquals("Edit/Approve Bill Batches", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToBillBatchList");
	}
	
	public void NavigateToPostBillBatch() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=BillBatch.post");
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Post_Bill_Batches)));
    	assertEquals("Post Bill Batches", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPostBillBatch");
	}
	
	public void NavigateToSelectForPaymentList() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/selectForPayment");
    	CommonFunctions.Wait(_driver, By.xpath("//table[@id='VendorsWithBalance']"));
    	assertTrue("Select for Payment list page was NOT displayed", _driver.getTitle().contains("Select for Payment"));
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToSelectForPaymentList");
	}

	public void NavigateToProcessChecks() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=Bill.processChecks");
    	CommonFunctions.Wait(_driver, By.xpath("//input[@value='Process Vendor Checks' and @name='updateForm']"));    	
    	assertEquals("Process Vendor Checks", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToProcessChecks");
	}
	
	public void NavigateToPostChecks() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=Bill.postChecks");
    	CommonFunctions.Wait(_driver, By.xpath("//input[@value='Post Vendor Checks' and @name='updateForm']"));    	
    	assertEquals("Post Vendor Checks", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPostChecks");
	}
	
	public ArrayList EnterAPDefaultSettings(int sTerms,int sVendorType,int Type) throws Exception
	{
		 ArrayList  DefaultSettings = new ArrayList();
		DCPage DC = new DCPage(_driver);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.AP_Settings_Default_Tab))).click();
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_Terms)), sTerms);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_Vendor_Type)), sTerms);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_1099_Type)), sTerms);
		Thread.sleep(1000);
		DC.Update();
		String sTerm=CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_Terms)));
		DefaultSettings.add(sTerm);
		String sVendType=CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_Vendor_Type)));
		DefaultSettings.add(sVendType);
		String sType=CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.AP_Settings_Default_1099_Type)));
		DefaultSettings.add(sType);
		return DefaultSettings;
	}
	 
	public void AddContact(String Lookup,String sFN,String sLN,String sDept,String sComp,String PhNo,String Add1,String sCity,String sCus) throws Exception
	{
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Contact"))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCus);
						
						CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));
						
						_driver.findElement(By.name(Locators.getProperty(Locators.AP_Contact_LookupHint))).sendKeys(Lookup);
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);						
						_driver.findElement(By.name(Locators.getProperty(Locators.AP_Contact_Department))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.AP_Contact_Department))).sendKeys(sDept);
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sComp);
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(PhNo);
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add1))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add1))).sendKeys(Add1);
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer_City))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Customer_City))).sendKeys(sCity);						
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), "US - United States");						
						
//						_driver.findElement(By.xpath(Locators.getProperty(Locators.AP_Contact_Customer_Vendor_Info))).click();
//						Thread.sleep(1000);					
						
						_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
						Thread.sleep(2000);
						if (CommonFunctions.isElementPresent(_driver, By.name("defaultCurrency")))
						{
							CommonFunctions.selectDropdownByText(_driver, By.name("defaultCurrency"), "USD");
							Thread.sleep(1000);
						}
						
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(1000);
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	
	}
	
	
	public ArrayList AddContactNote() throws Exception
	{
		boolean sFlag = false;
		 ArrayList  Result = new ArrayList();
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 sFILEPATH =  _properties.getProperty(GUI_automation_properties.FILEPATH);
		 String ExcelPath1 = _properties.getProperty(GUI_automation_properties.FILEPATH);
		 final String dir = System.getProperty("user.dir");
	     // System.out.println("current dir = " + dir);
	      String ExcelPath = dir.concat(ExcelPath1);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.AP_Contact_Recent_Notes))).click();
						Thread.sleep(1000);
						String  originalHandle1 = _driver.getWindowHandle();
						 System.out.println(originalHandle1);
						 String sWindowTitle1 =_driver.getTitle();
						 System.out.println(sWindowTitle1);
						_driver.findElement(By.xpath("//fieldset[@id='ContactNote_N10093_fieldset']/div/div[1]/div[2]/a")).click();
						Thread.sleep(5000);
						
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{	
								
									if(_driver.switchTo().window(windowId1).getTitle().equals("Adding Contact Note")) 
									{
										System.out.println("Verify Category Warning Message when it is blank");
										CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), "[- Please select a value -]");
										Thread.sleep(1000);
										_driver.findElement(By.name("note")).sendKeys("Note");
										Thread.sleep(1000);
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
										Thread.sleep(1000);
										String sCategory = PO.AcceptModalDialog();
											if(sCategory.contains("The following error(s) occurred:") && sCategory.contains("- The Category field is required."))
											{
												Result.add("TRUE");
											}
											else
											{
												Result.add("FALSE");
											}
											
										System.out.println("Verify Note Warning Message when it is blank");
										CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), 1);
										Thread.sleep(1000);
										_driver.findElement(By.name("note")).clear();
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
										Thread.sleep(1000);
										String sNote = PO.AcceptModalDialog();
											if(sNote.contains("The following error(s) occurred:") && sNote.contains("- The Note field is required."))
											{
												Result.add("TRUE");
											}
											else
											{
												Result.add("FALSE");
											}
											
										System.out.println("Add Contact");
										CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), 1);
										Thread.sleep(1000);
										_driver.findElement(By.name("note")).sendKeys("notes");
										 System.out.println("Enter the upload path");
										 System.out.println("ExcelPath is "+ExcelPath);
										 _driver.findElement(By.name("attachment_0")).sendKeys(ExcelPath);
										 /*
										 setClipboardData(ExcelPath);
										//native key strokes for CTRL, V and ENTER keys
										Robot robot = new Robot();
										 _driver.findElement(By.name("attachment_0")).click();
										 Thread.sleep(3000);
										robot.keyPress(KeyEvent.VK_CONTROL);
										robot.keyPress(KeyEvent.VK_V);
										robot.keyRelease(KeyEvent.VK_V);
										robot.keyPress(KeyEvent.VK_CONTROL);
										robot.keyRelease(KeyEvent.VK_LEFT);
										robot.keyRelease(KeyEvent.VK_LEFT);
										robot.keyRelease(KeyEvent.VK_CONTROL);
										robot.keyPress(KeyEvent.VK_ENTER);
										robot.keyRelease(KeyEvent.VK_ENTER);
										*/
										 Thread.sleep(5000);
										
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
										Thread.sleep(1000);
										_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);	
										boolean sobjectAdded=CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
											if(sobjectAdded==true)
											{
												Result.add("TRUE");
											}
											else
											{
												Result.add("FALSE");
											}
							
									} 
									else 
									{
										_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
							
									}

							}
						}
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	return Result;
	}
	
	
	public ArrayList AddNote() throws Exception
	{
		boolean sFlag = false;
		 ArrayList  Result = new ArrayList();
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 sFILEPATH =  _properties.getProperty(GUI_automation_properties.FILEPATH);
		 String ExcelPath1 = _properties.getProperty(GUI_automation_properties.FILEPATH);
		 final String dir = System.getProperty("user.dir");
	     // System.out.println("current dir = " + dir);
	      String ExcelPath = dir.concat(ExcelPath1);
		 _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(2000);
		
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Vendor Note")) 
				{
					System.out.println("Verify Category Warning Message when it is blank");
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), "[- Please select a value -]");
					Thread.sleep(1000);
					_driver.findElement(By.name("note")).sendKeys("Note");
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(1000);
					String sCategory = PO.AcceptModalDialog();
						if(sCategory.contains("The following error(s) occurred:") && sCategory.contains("- The Category field is required."))
						{
							Result.add("TRUE");
						}
						else
						{
							Result.add("FALSE");
						}
						
					System.out.println("Verify Note Warning Message when it is blank");
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), 1);
					Thread.sleep(1000);
					_driver.findElement(By.name("note")).clear();
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(1000);
					String sNote = PO.AcceptModalDialog();
						if(sNote.contains("The following error(s) occurred:") && sNote.contains("- The Note field is required."))
						{
							Result.add("TRUE");
						}
						else
						{
							Result.add("FALSE");
						}
						
					System.out.println("Add Contact");
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), 1);
					Thread.sleep(1000);
					_driver.findElement(By.name("note")).sendKeys("notes");
					 System.out.println("Enter the upload path");
					 System.out.println("ExcelPath is "+ExcelPath);
					 setClipboardData(ExcelPath);
					//native key strokes for CTRL, V and ENTER keys
					Robot robot = new Robot();
					 _driver.findElement(By.name("attachment_0")).sendKeys(ExcelPath);
					
					 Thread.sleep(5000);
					
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(1000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					boolean sobjectAdded=CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
						if(sobjectAdded==true)
						{
							Result.add("TRUE");
						}
						else
						{
							Result.add("FALSE");
						}
		
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		
				}	

			}
		}
	return Result;
	}
	public ArrayList EditContactNote() throws Exception
	{
		boolean sFlag = false;
		 ArrayList  Result = new ArrayList();
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 sFILEPATH =  _properties.getProperty(GUI_automation_properties.FILEPATH);
		 String ExcelPath1 = _properties.getProperty(GUI_automation_properties.FILEPATH);
		 final String dir = System.getProperty("user.dir");
	     // System.out.println("current dir = " + dir);
	      String ExcelPath = dir.concat(ExcelPath1);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.AP_Contact_Recent_Notes))).click();
						Thread.sleep(1000);
						String  originalHandle1 = _driver.getWindowHandle();
						 System.out.println(originalHandle1);
						 String sWindowTitle1 =_driver.getTitle();
						 System.out.println(sWindowTitle1);
						_driver.findElement(By.xpath("//table[@id='ContactNote_N10093']/tbody/tr[1]/td[2]/div/a/img")).click();
						Thread.sleep(5000);
						
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{	
								
									if(_driver.switchTo().window(windowId1).getTitle().equals("Contact Note")) 
									{								
										System.out.println("Edit Caterogy");
										String sSelectedCat=	CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)));
										Thread.sleep(3000);
										sSelectedCat=sSelectedCat.trim();
										Result.add(sSelectedCat);
										CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)),2);
										Thread.sleep(1000);
										//_driver.findElement(By.xpath("//div/h4[contains(label,'Attachment')]/following-sibling::div/div/div[1]/div[1]/div[1]")).click();
										Thread.sleep(1000);
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
										Thread.sleep(5000);
									} 
									else 
									{
										_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
							
									}

							}
						}
						_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
						String sCater = _driver.findElement(By.xpath("//table[@id='ContactNote_N10093']/tbody/tr[1]/td[3]/div[1]/a")).getText();
						sCater=sCater.trim();
						Result.add(sCater);
						String sAttach = _driver.findElement(By.xpath("//table[@id='ContactNote_N10093']/tbody/tr[1]/td[7]/div[1]")).getText();
						sAttach=sAttach.trim();
						Result.add(sAttach);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	return Result;
	}
	
	public boolean EditNote() throws Exception
	{
		boolean sFlag = false;
		String sSelectedCat="";
		 ArrayList  Result = new ArrayList();
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 sFILEPATH =  _properties.getProperty(GUI_automation_properties.FILEPATH);
		 String ExcelPath1 = _properties.getProperty(GUI_automation_properties.FILEPATH);
		 final String dir = System.getProperty("user.dir");
	     // System.out.println("current dir = " + dir);
	      String ExcelPath = dir.concat(ExcelPath1);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Vendor Note")) 
					{
						
										System.out.println("Edit Caterogy");
										sSelectedCat=	CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)));
										Thread.sleep(1000);								
										CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)),2);
										Thread.sleep(1000);
										//_driver.findElement(By.xpath("//div/h4[contains(label,'Attachment')]/following-sibling::div/div/div[1]/div[1]/div[1]")).click();
										Thread.sleep(1000);
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
										Thread.sleep(5000);
						
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					sSelectedCat=sSelectedCat.trim();
					Result.add(sSelectedCat);
					System.out.println("sSelectedCat is "+sSelectedCat);
					String sCater = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[3]/div[1]/a")).getText();
					System.out.println("sCater is "+sCater);
					sCater=sCater.trim();
					Result.add(sCater);
					String sAttach = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[7]/div")).getText();
					System.out.println("sAttach is "+sAttach);
					sAttach=sAttach.trim();
					Result.add(sAttach);
					if(sSelectedCat.equals("Account") && sCater.equals("Collection"))
					{
						sFlag = true;
					}
			}
		}
	return sFlag;
	}
	
	public boolean DeleteContactNote() throws Exception
	{
		
		boolean sObjectDeleted=false;
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.AP_Contact_Recent_Notes))).click();
						Thread.sleep(1000);
						String  originalHandle1 = _driver.getWindowHandle();
						 System.out.println(originalHandle1);
						 String sWindowTitle1 =_driver.getTitle();
						 System.out.println(sWindowTitle1);
						_driver.findElement(By.xpath("//table[@id='ContactNote_N10093']/tbody/tr[1]/td[2]/div/a/img")).click();
						Thread.sleep(5000);
						
						Set<String> availableWindows1 = _driver.getWindowHandles();
						if (!availableWindows1.isEmpty()) 
						{
							for (String windowId1 : availableWindows1) 
							{	
								
									if(_driver.switchTo().window(windowId1).getTitle().equals("Contact Note")) 
									{								
										System.out.println("Delete Contact Notes");
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
										_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
										Thread.sleep(5000);
										
									} 
									else 
									{
										_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
							
									}

							}
						}
						_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
						Thread.sleep(2000);
						 sObjectDeleted = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Item_Deleted)));
						
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	return sObjectDeleted;
	}
	
	
	public boolean DeleteNote() throws Exception
	{
		
		boolean sObjectDeleted=false;
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Vendor Note")) 
					{
				
						System.out.println("Delete  Notes");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(5000);
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
						 sObjectDeleted = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Item_Deleted)));
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	return sObjectDeleted;
	}
	
	public boolean DuplicateAContact(String sFN,String sLN) throws Exception
	{
		
		boolean sObjectDuplicated=false;
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
						Thread.sleep(5000);
						
						 sObjectDuplicated = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Duplicated_text)));
							_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).clear();
							_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
							_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).clear();
							_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
							Thread.sleep(5000);
						
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
	return sObjectDuplicated;
	}
	
	
	public void DeleteUnAssociatedContact() throws Exception
	{
		
	
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						Thread.sleep(1000);
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(5000);
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}

	}
	
	public boolean DeleteAssociatedContact(String sVendorID) throws Exception
	{
		
		boolean sWarning=false;
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		 _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
					{
						
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						Thread.sleep(1000);
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(5000);
						String sWarningText = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li")).getText();
						System.out.println("Warning text is "+sWarningText);
						String sText=": ("+sVendorID+" "+sVendorID+"-V"+sVendorID+") still referenced by 1 Vendor with field: Ship";
						if(sWarningText.contains("Item in use. Contact") && sWarningText.contains(sText))
						{
							System.out.println("Warning Message Verified");
							sWarning=true;
						}
						
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
		return sWarning;
	}
	public static void setClipboardData(String string) 
	{
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	
	public boolean VendorPurchaseOrder(String sVendorID) throws Exception
	{
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		
		boolean sPage=false;
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println("originalHandle is: "+originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println("sWindowTitle is: "+sWindowTitle);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/vendor/"+sVendorID);
		   	Thread.sleep(4000);	
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Orders - Open")) 
					{
						System.out.println("PO page for vendor opens");
						sPage= true;
						_driver.close();
						//_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
		return sPage;
	}
	
	
		public boolean VerifyRequiredFieldWarningMessageInAutoBillLineItem(int sValue) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		boolean sFlag=false,sFlag1=false,sFlag2=false,Result=false;
		String  originalHandle = _driver.getWindowHandle();
		 System.out.println(originalHandle);
		 String sWindowTitle =_driver.getTitle();
		 System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//fieldset[@id='AutoBillLine_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Auto Bill Line")) 
					{
								if(sValue == 1)
								{
								_driver.findElement(By.name("glAccount")).clear();
								_driver.findElement(By.name("invoiceAmount")).clear();
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
								Thread.sleep(1000);
								String sText = PO.AcceptModalDialog();
								if(sText.contains("The following error(s) occurred:") && sText.contains("- The GL Account field is required.") && sText.contains("- The Invoice Amount field is required."))
									sFlag = true;
								
								_driver.findElement(By.name("invoiceAmount")).sendKeys("1");
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
								Thread.sleep(1000);
								sText = PO.AcceptModalDialog();
								
										if(sText.contains("The following error(s) occurred:") && sText.contains("- The GL Account field is required."))
											sFlag1 = true;
										
										_driver.findElement(By.name("glAccount")).sendKeys("00001");
										_driver.findElement(By.name("invoiceAmount")).clear();
										_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
										Thread.sleep(1000);
										//sText = PO.AcceptModalDialog();
									boolean sInvoiceWarning=	CommonFunctions.isElementPresent(_driver, By.xpath("//ul/li[text()='Invoice Amount: Value required.]'"));
										//if(sText.contains("The following error(s) occurred:") && sText.contains("- The Invoice Amount field is required."))
										if(sInvoiceWarning==true)		
											sFlag2 = true;
										
										if(sFlag==true && sFlag1== true && sFlag2 == true)
											Result = true;
										
											_driver.close();
											/* boolean sAlert = PO.isAlertPresent();
											 if(sAlert == true)
											 {
												 _driver.switchTo().alert().accept();
											 }*/
											_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
								}
								else if(sValue == 2)
								{
									_driver.findElement(By.name("glAccount")).clear();
									_driver.findElement(By.name("glAccount")).sendKeys("00001");
									_driver.findElement(By.name("invoiceAmount")).clear();
									_driver.findElement(By.name("invoiceAmount")).sendKeys("1");
									if( _driver.findElement(By.name("glLocation")).getLocation().x < 0 || _driver.findElement(By.name("glLocation")).getLocation().y < 0 )
									{
									CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 1);
									Thread.sleep(1000);
									CommonFunctions.selectDropdownByIndex(_driver, By.name("glDepartment"), 1);
									Thread.sleep(1000);
									}
									_driver.findElement(By.name("notes")).sendKeys("added");
									_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
									Thread.sleep(1000);
									_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
								}
								else if(sValue == 3)
								{
									_driver.findElement(By.name("glAccount")).sendKeys("A$%@!");
									_driver.findElement(By.name("invoiceAmount")).sendKeys("1");
									if( _driver.findElement(By.name("glLocation")).getLocation().x < 0 || _driver.findElement(By.name("glLocation")).getLocation().y < 0 )
									{
									CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 1);
									Thread.sleep(1000);
									CommonFunctions.selectDropdownByIndex(_driver, By.name("glDepartment"), 1);
									Thread.sleep(1000);
									}
									_driver.findElement(By.name("notes")).sendKeys("added");
									_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
									Thread.sleep(2000);
									System.out.println("Fetch Warning message");
									sFlag= CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='form-errors']/ul[text()='Invalid value']"));
									sFlag1= CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[1]/div/span[text()='Invalid value']"));
									if(sFlag==true && sFlag1== true)
										Result = true;
									_driver.close();
									 System.out.println("Switch to main window");
									_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
								}
						else
						{
							//_driver.close();
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
						}
						
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			
					}

			}
		}
		return Result;
	}
  
	
		public void EditAutoBillLineItem() throws Exception
		{
			String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//table[@id='AutoBillLine']/tbody/tr/td[2]/div/a/img")).click();
			Thread.sleep(2000);
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	
					
						if(_driver.switchTo().window(windowId).getTitle().equals("Bill line")) 
						{
							_driver.findElement(By.name("invoiceAmount")).clear();
							_driver.findElement(By.name("invoiceAmount")).sendKeys("2");
							if( _driver.findElement(By.name("glLocation")).getLocation().x < 0 || _driver.findElement(By.name("glLocation")).getLocation().y < 0 )
							{
							CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 2);
							Thread.sleep(1000);
							CommonFunctions.selectDropdownByIndex(_driver, By.name("glDepartment"), 2);
							Thread.sleep(1000);
							}
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
							Thread.sleep(1000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				
						}

				}
			}
		}
		
		
		public String FetchAmountDetails() throws Exception
		{
			String sAmount="";
			String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//table[@id='AutoBillLine']/tbody/tr/td[2]/div/a/img")).click();
			Thread.sleep(2000);
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	
					
						if(_driver.switchTo().window(windowId).getTitle().equals("Bill line")) 
						{
						
						sAmount = _driver.findElement(By.name("invoiceAmount")).getAttribute("value");
							Thread.sleep(2000);
						
						System.out.println("sAmount is "+sAmount);
						_driver.close();
							Thread.sleep(1000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				
						}

				}
			}
			return sAmount;
		}
		

		/**
		 * @throws Exception
		 */
		public void DeleteAutoBillLineItem() throws Exception
		{
			String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//table[@id='AutoBillLine']/tbody/tr/td[2]/div/a/img")).click();
			Thread.sleep(2000);
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	
					
						if(_driver.switchTo().window(windowId).getTitle().equals("Bill line")) 
						{
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
							_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
							Thread.sleep(5000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				
						}

				}
			}
		}
		
		//method for vendor type page.

		/**
		 * @param sUniqueID
		 * @throws Exception
		 */
		public Boolean  AddNewRecordInVendorTypes(String sUniqueID) throws Exception
		{
			
			 String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
			Thread.sleep(2000);
		
		
			
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	
					
						if(_driver.switchTo().window(windowId).getTitle().equals("Adding Vendor Type")) 
						{
							
							_driver.findElement(By.xpath("//label[text()='ID']/../..//input")).sendKeys(sUniqueID);
							_driver.findElement(By.xpath("//label[text()='Description']/../..//input")).sendKeys(sUniqueID+"automation");
							
							CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 1);
							_driver.findElement(By.xpath("//input[@title='Click to Add New Record']")).click();
							
							
							Thread.sleep(5000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
							
							if(_driver.findElements(By.xpath("//li[text()='Object Added']")).size()!= 0){
								System.out.println("!!!Object Added is successfully displayed");
								System.out.println("Record with "+sUniqueID+" Successfully added in the Grid");
								System.out.println();
								return true;
							}else{
								assertFalse("Object Added is not displayed",false);
								return false;
							}
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				
						}

				}
				
				return false;
				
				
			}
			return false;

		}
		
		public void VerifyBlankInvalidAndInvalidVendorIdType(String sUniqueID) throws Exception
		{

			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			System.out.println(sWindowTitle);

			try {

				_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
				.click();
				Thread.sleep(2000);

				Set<String> availableWindows = _driver.getWindowHandles();
				if (!availableWindows.isEmpty()) {
					for (String windowId : availableWindows) {

						if (_driver.switchTo().window(windowId).getTitle()
								.equals("Adding Vendor Type")) {

							_driver.findElement(
									By.xpath("//label[text()='ID']/../..//input"))
									.sendKeys("");
							_driver.findElement(
									By.xpath("//label[text()='Description']/../..//input"))
									.sendKeys("" + "automation");
							CommonFunctions.selectDropdownByIndex(_driver,
									By.name("glLocation"), 1);
							_driver.findElement(
									By.xpath("//input[@title='Click to Add New Record']"))
									.click();
							Thread.sleep(5000);
							Alert alertDialog = _driver.switchTo().alert();
							String alertText = alertDialog.getText();
							System.out.println("alertText is " + alertText);
							alertDialog.accept();
							try {
								assertEquals("Verifying blank id error message",
										alertText.replaceAll(" ", "").trim(),
										"The following error(s) occurred:\n - The ID field is required."
										.replaceAll(" ", "").trim());
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC3_Vendor_Types_Blank_Vendor_Type_ID",
										"Execution", "PASS");
								
							} catch (Exception e) {
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC3_Vendor_Types_Blank_Vendor_Type_ID",
										"Execution", "FAIL");
							}
							
						
							_driver.findElement(
									By.xpath("//label[text()='ID']/../..//input"))
									.sendKeys(sUniqueID+"2");
							CommonFunctions.selectDropdownByIndex(_driver,
									By.name("glLocation"), 1);
							_driver.findElement(
									By.xpath("//input[@title='Click to Add New Record']"))
									.click();

							Thread.sleep(5000);
							_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

							if (_driver.findElements(
									By.xpath("//li[text()='Object Added']")).size() != 0) {
								System.out
								.println("!!!Object Added is successfully displayed");
							} else {
								assertFalse("Object Added is not displayed", false);
							}

						} else {
							_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

						}

					}
					// Entering inValid ID.
					_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
					_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
					.click();
					Thread.sleep(2000);

					availableWindows = _driver.getWindowHandles();

					if (!availableWindows.isEmpty()) {
						for (String windowId : availableWindows) {

							if (_driver.switchTo().window(windowId).getTitle()
									.equals("Adding Vendor Type")) {

								_driver.findElement(
										By.xpath("//label[text()='ID']/../..//input"))
										.sendKeys(sUniqueID + "qq");
								_driver.findElement(
										By.xpath("//label[text()='Description']/../..//input"))
										.sendKeys("" + "automation");
								Thread.sleep(5000);
								Alert alertDialog = _driver.switchTo().alert();
								String alertText = alertDialog.getText();
								System.out.println("alertText is " + alertText);
								alertDialog.accept();
								
								try {
									assertEquals("Verifying blank id error message",
											alertText.replaceAll(" ", "").trim(),
											"Invalid Number".replaceAll(" ", "").trim());
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC4_Vendor_Types_Invalid_Vendor_Type_ID.",
											"Execution", "PASS");
								} catch (Exception e) {
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC4_Vendor_Types_Invalid_Vendor_Type_ID.",
											"Execution", "FAIL");
								}
						
								_driver.switchTo().window(windowId).close();

							} else {
								_driver.switchTo().window(originalHandle)
								.getTitle().equals(sWindowTitle);

							}
						}

					}
					// Validating for Duplicate vendor ID
					_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
					_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
					.click();
					Thread.sleep(2000);

					availableWindows = _driver.getWindowHandles();
					if (!availableWindows.isEmpty()) {
						for (String windowId : availableWindows) {

							if (_driver.switchTo().window(windowId).getTitle()
									.equals("Adding Vendor Type")) {

								_driver.findElement(
										By.xpath("//label[text()='ID']/../..//input"))
										.sendKeys(sUniqueID);
								_driver.findElement(
										By.xpath("//label[text()='Description']/../..//input"))
										.sendKeys(sUniqueID + "automation");
								CommonFunctions.selectDropdownByIndex(_driver,
										By.name("glLocation"), 1);
								_driver.findElement(
										By.xpath("//input[@title='Click to Add New Record']"))
										.click();
								Thread.sleep(3000);
								if (_driver
										.findElements(
												By.xpath("//span[text()='Value already in use']"))
												.size() != 0) {
									System.out
									.println("Duplicate vendor id message is successfully displayed");
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC5_Vendor_Types_Duplicate_Vendor_Type_ID.",
											"Execution", "PASS");
									
								} else {

									assertFalse(
											"Duplicate vendor id message :'Value already in use' is not displayed",
											false);
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC5_Vendor_Types_Duplicate_Vendor_Type_ID.",
											"Execution", "FAIL");
								}

								_driver.switchTo().window(windowId).close();
								_driver.switchTo().window(originalHandle)
								.getTitle().equals(sWindowTitle);
							} else {
								_driver.switchTo().window(originalHandle)
								.getTitle().equals(sWindowTitle);

							}
						}

					}
				}
			}

			catch (Exception e) {
				System.out
				.println("error in VerifyBlankInvalidAndInvalidVendorIdType"
						+ e.getMessage() + e.getCause());
			}

	}

		public void VerifyInvalidAndBlankGLAccount(String sUniqueID)  throws Exception
		
		{
			
			String  originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle =_driver.getTitle();
			System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
			Thread.sleep(2000);

			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	

					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Vendor Type")) 
					{

						_driver.findElement(By.xpath("//label[text()='ID']/../..//input")).sendKeys(sUniqueID);
						_driver.findElement(By.xpath("//label[text()='Description']/../..//input")).sendKeys(""+"automation");
						_driver.findElement(By.name("glAccount")).sendKeys("5467");	
						CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 1);
						_driver.findElement(By.xpath("//input[@title='Click to Add New Record']")).click();														
						Thread.sleep(5000);

						if(_driver.findElements(By.xpath("//span[text()='Invalid value']")).size()!= 0){
							System.out.println("!!!Invalid account message is successfully displayed");
							dbConnection
							.UpdateFunction("AP", "AP",
									"UC6_Vendor_Types_Invalid_GL_Account",
									"Execution", "PASS");
						}else{
							assertFalse("Invalid value message is not displayed for incorrect account number.",false);
							dbConnection
							.UpdateFunction("AP", "AP",
									"UC6_Vendor_Types_Invalid_GL_Account",
									"Execution", "FAIL");
						}

						_driver.findElement(By.xpath("//label[text()='ID']/../..//input")).clear();
						_driver.findElement(By.xpath("//label[text()='ID']/../..//input")).sendKeys(sUniqueID);
						_driver.findElement(By.xpath("//label[text()='Description']/../..//input")).sendKeys(""+"automation");
						_driver.findElement(By.name("glAccount")).clear();
						_driver.findElement(By.name("glAccount")).sendKeys("");	
						CommonFunctions.selectDropdownByIndex(_driver, By.name("glLocation"), 1);
						_driver.findElement(By.xpath("//input[@title='Click to Add New Record']")).click();														
						Thread.sleep(1000);

						Alert alertDialog = _driver.switchTo().alert();
						String alertText = alertDialog.getText();
						System.out.println("alertText is "+ alertText);
						alertDialog.accept();
						try {
							assertEquals("Verifying blank Account error message", alertText.replaceAll(" ","").trim().replaceAll("\n","")," The following error(s) occurred: - The GL Account field is required.".replaceAll(" ","").trim());
							dbConnection
							.UpdateFunction("AP", "AP",
									"UC7_Vendor_Types_Blank_GL_Account",
									"Execution", "PASS");
							
						} catch (Exception e) {
							
							dbConnection
							.UpdateFunction("AP", "AP",
									"UC7_Vendor_Types_Blank_GL_Account",
									"Execution", "FAIL");
							
						}
						
						_driver.switchTo().window(windowId).close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					}	
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					}
				}			
			}

		}		

		public void VerifyEditVendorType(String SearchID,String description)  throws Exception
		{
			 String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			 
			 
			_driver.findElement(By.name("performSearch")).click();
			_driver.findElement(By.name("search")).sendKeys(SearchID);
			_driver.findElement(By.name("performSearch")).click();
			Thread.sleep(5000);
			_driver.findElement(By.name("updateForm")).click();
			
			if(_driver.findElements(By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size()==1){
				System.out.println("!!!Invalid account message is successfully displayed");
				_driver.findElement(By.xpath(".//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img")).click();
				
			}else{
				assertFalse("No record  found with id"+SearchID,false);
			}
			
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) 
			{
				for (String windowId : availableWindows) 
				{	
					
						if(_driver.switchTo().window(windowId).getTitle().contains("Vendor Type ") )
						{
							
							_driver.findElement(By.xpath("//label[text()='Description']/../..//input")).clear();
							_driver.findElement(By.xpath("//label[text()='Description']/../..//input")).sendKeys(description);
							
							
							_driver.findElement(By.name("updateForm")).click();														
							Thread.sleep(5000);
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);		
							if(_driver.findElements(By.xpath("//li[text()='Updated']")).size()!= 0){
								System.out.println("Updated message is successfully displayed");
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC8_Vendor_Types_Edit_Vendor_Type_details",
										"Execution", "PASS");
								
							}else{
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC8_Vendor_Types_Edit_Vendor_Type_details",
										"Execution", "FAIL");
								assertFalse("Updated message is not displayed in vendor page.",false);
								
								
							}
							
						if	(_driver.findElement(By.xpath(".//*[@id='appbox_implicit']/tbody/tr/td[4]/div")).getText().equalsIgnoreCase(description)){
						
							System.out.println("record is successfully updated");
						}
						else{
							assertFalse("Updated message is not updated currectly.",false);
						}
						
							
						}	
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
						}
					}			
			}
		}

		public void VerifyDeleteActiveInActiveRecord(String SearchID) throws Exception
		{			
			

			String originalHandle = _driver.getWindowHandle();

			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			System.out.println(sWindowTitle);
			String sDescription, sID;

			CommonFunctions.selectDropdownByIndex(_driver, By.xpath(Locators.getProperty(Locators.Include_Dropdown)), 1);		

			_driver.findElement(By.name("search")).sendKeys(SearchID);
			_driver.findElement(By.name("performSearch")).click();

		

			int rowcount = _driver.findElements(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			if (rowcount >= 1) {
				sDescription = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
						.getText();
				sID = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
						.getText();
				sDescription = sDescription.trim();
				sID = sID.trim();
				System.out.println("sDescription is " + sDescription);
				System.out.println("sID is " + sID);
			
			}

			_driver.findElement(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"))
					.click();
			SwitchToWindow("Vendor Type ");
			_driver.findElement(
					By.xpath("//*[@id='buttonbox']/input[@value='Delete']"))
					.click();
			_driver.findElement(
					By.xpath("//*[@id='contentDiv']/table/tbody/tr[2]/td/div/input[@value='Delete']"))
					.click();

			_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
			_driver.findElement(By.name("search")).sendKeys(SearchID);
			_driver.findElement(By.name("performSearch")).click();

			if (_driver.findElements(
					By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() == 0) {
				System.out
						.println(" Record not  found after deletion in Active state, RecordID :"+SearchID);
				
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC10_Vendor_Types_Delete_vendor_type_in_use",
						"Execution", "PASS");
			} else {
				assertFalse("Record still exists in Grid after deletion , RecordID :"+SearchID, false);
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC10_Vendor_Types_Delete_vendor_type_in_use",
						"Execution", "FAIL");

			}

			CommonFunctions.selectDropdownByIndex(_driver,
					By.xpath(Locators.getProperty(Locators.Include_Dropdown)), 3);

			rowcount = _driver.findElements(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			if (rowcount >= 1) {
				sDescription = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
						.getText();
				sID = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
						.getText();
				sDescription = sDescription.trim();
				sID = sID.trim();
				System.out.println("sDescription is " + sDescription);
				System.out.println("sID is " + sID);

				_driver.findElement(
						By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"))
						.click();
				SwitchToWindow("Vendor Type ");
				_driver.findElement(
						By.xpath("//*[@id='buttonbox']/input[@value='Delete']"))
						.click();
				_driver.findElement(
						By.xpath("//*[@id='contentDiv']/table/tbody/tr[2]/td/div/input[@value='Delete']"))
						.click();

				_driver.switchTo().window(originalHandle).getTitle()
						.equals(sWindowTitle);
				_driver.findElement(By.name("search")).sendKeys(sID);
				_driver.findElement(By.name("performSearch")).click();

				if (_driver.findElements(
						By.xpath(".//table[@id='appbox_implicit']/tbody/tr"))
						.size() == 0) {
					System.out.println(" Record not  found after deletion in InActive state, RecordID"+sID);
							
					dbConnection
					.UpdateFunction("AP", "AP",
							"UC9_Vendor_Types_Delete_vendor_type_not_in_use",
							"Execution", "PASS");
					
				} else {
					assertFalse(
							"Record still exists in Grid after deletion inActive",
							false);
					System.out
					.println(" Record Still exists deletion in InActive state, RecordID"+sID);
					dbConnection
					.UpdateFunction("AP", "AP",
							"UC9_Vendor_Types_Delete_vendor_type_not_in_use",
							"Execution", "FAIL");
				
				}
			}
			else
			{
				
				
				System.out
				.println(" No record found in InActive State to delete");
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC9_Vendor_Types_Delete_vendor_type_not_in_use",
						"Execution", "FAIL");
				
			}
	  		
	  		
			
		}

		
		public void VerifySearchAndSortRecords(String Id, String desciption)
			throws Exception {

		String sDescription, sID;
		Actions action = new Actions(_driver);

		boolean RecordFound = true, bRemoveSort = false, sortAscend = false, sortDescend = false;
		boolean sortResults;

		try {
			JobPlanningPage JP = new JobPlanningPage(_driver);
			CommonFunctions.selectDropdownByIndex(_driver,
					By.xpath(Locators.getProperty(Locators.Include_Dropdown)),
					1);
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.findElement(By.name("search")).sendKeys(Id);
			_driver.findElement(By.name("performSearch")).click();
			CommonFunctions.waitForPageLoaded(_driver);
			
			if (_driver.findElements(
					By.xpath(".//table[@id='appbox_implicit']/tbody/tr"))
					.size() == 1) {
				System.out.println("Record found,Recordid"+Id);
			
			} else {
				
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC11_Vendor_Types_Search_for_Vendor_Type",
						"Execution", "FAIL");
				assertFalse("No record  found with id" + Id, true);
			}

			int rowcount = _driver.findElements(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			if (rowcount >= 1) {
				sDescription = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
						.getText();
				sID = _driver
						.findElement(
								By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
						.getText();
				sDescription = sDescription.trim();
				sID = sID.trim();
				System.out.println("sDescription is " + sDescription);
				System.out.println("sID is " + sID);
				
				try {
					
				
				assertEquals("validating Id with search id in grid", sID.trim()
						.toUpperCase().replace(" ", ""), Id.trim()
						.toUpperCase().replace(" ", ""));
				assertEquals(
						"validating Id with search id with description in grid",
						sDescription.trim().toUpperCase().replace(" ", ""),
						desciption.trim().toUpperCase().replace(" ", ""));
				
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC11_Vendor_Types_Search_for_Vendor_Type",
						"Execution", "PASS");
				} catch (Exception e) {
					
					dbConnection
					.UpdateFunction("AP", "AP",
							"UC11_Vendor_Types_Search_for_Vendor_Type",
							"Execution", "FAIL");
					
				}
				
			}

			Thread.sleep(3000);

			CommonFunctions.selectDropdownByIndex(_driver,
					By.name("searchField"), 1);

			_driver.findElement(By.name("search")).sendKeys(desciption);
			_driver.findElement(By.name("performSearch")).click();

			rowcount = _driver.findElements(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			for (int i = 1; i < rowcount - 1; i++) {
				sDescription = _driver.findElement(
						By.xpath("//table[@id='appbox_implicit']/tbody/tr[" + i
								+ "]/td[4]/div")).getText();

				sDescription = sDescription.trim();

				if (!(sDescription.contains(desciption))) {
					RecordFound = false;
					break;
				}
			}
			
			if (!RecordFound)
			{
		

				
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC12_Vendor_Types_Sorting_based_on_columns",
						"Execution", "FAIL");
				Assert.fail("Validating record in grid match with pass description");
			}

		

			System.out
					.println("On the Cost Centers Tab, For each applicable column disable sorting by clicking on the respective heading and selecting 'Remove Sort'");
			if (JP.removeSort("all",
					Locators.getProperty(Locators.Common_Webtable))) {
				System.out.println("Sorting was disabled on all columns");
				bRemoveSort = true;
			} else {
				
				Assert.fail("sorting was not disabled on all columns");
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC12_Vendor_Types_Sorting_based_on_columns",
						"Execution", "FAIL");
			}

			System.out
					.println("For each column individually, - Enable the sort option (Asce & Desc) - Disable the sort option");
			int iColCount = _driver.findElements(
					By.xpath(Locators.getProperty(Locators.Common_Webtable)
							+ "/thead/tr[1]/th")).size();
			for (int i = 3; i <= iColCount - 1; i++) {
				String sColumnName = _driver.findElement(
						By.xpath(Locators.getProperty(Locators.Common_Webtable)
								+ "/thead/tr[1]/th[" + i + "]/a/span"))
						.getText();

				System.out.println("\nsorting '" + sColumnName
						+ "' column in ascending order");
				sortAscend = JP.SortAndVerify("Ascend", sColumnName,
						Locators.getProperty(Locators.Common_Webtable));

				System.out.println("\nsorting '" + sColumnName
						+ "' column in descending order");
				sortDescend = JP.SortAndVerify("Descend", sColumnName,
						Locators.getProperty(Locators.Common_Webtable));

				if (!(sortDescend && sortAscend)) {
					sortResults = false;
				}
			}

			if (bRemoveSort) {
				System.out
						.println("\n1. Sorting was disabled on all columns. 2. Sorting worked according to the selected options");
				Assert.fail("sorting was not disabled on all columns");
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC12_Vendor_Types_Sorting_based_on_columns",
						"Execution", "PASS");

			} else {
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC12_Vendor_Types_Sorting_based_on_columns",
						"Execution", "FAIL");


				Assert.fail("The Sorting is not working according to the selected options");
			}

		} catch (Exception e) {
			System.out.println("error in VerifySearchAndSortRecords"
					+ e.getMessage() + e.getCause());
		}

	}

		public void VerifyFilter() throws Exception
		{
			
			
			try{
					JobPlanningPage JP = new JobPlanningPage(_driver);
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath(Locators.getProperty(Locators.Include_Dropdown)), 1);	
					String sDataBeforeFilter = "", sDataAfterFilter = "";
					
					JP.removeSort("all", "");
					System.out.println("Select a filtering option & ensure that the results are filtered based on selection; Click on the filtered column heading and disable filtering; Verify filtering is performed for all applicable columns");
					int ColCount = _driver.findElements(By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/thead/tr[1]/th")).size();
					for(int i = 3; i <= ColCount-1; i++)
					{	
						String cName = _driver.findElement(By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/thead/tr[1]/th["+i+"]/a/span")).getText();
							
						//filterBySelection option
						sDataBeforeFilter = JP.GetCellData(1, i, "");
						JP.applyFilter(1, i, 1, "", "");
						CommonFunctions.waitForPageLoaded(_driver);
						if ("Vendor Types".equals(_driver.getTitle()))						
						{
							List<String> sFilteredData = JP.GetColumnData(cName, "");		
							for(int k = 0;k<sFilteredData.size();k++)
							{
								if (!(sFilteredData.get(k).equals(sDataBeforeFilter))) {
									
									dbConnection
									  .UpdateFunction("AP", "AP",
													"UC13_Vendor_Types_Show_Grid_Filter",
													"Execution", "FAIL");
									Assert.fail("The filter option 'filterBySelection' is not performed correctly on '"+cName+"' column");
								}
							}
							System.out.println("The filter option 'filterBySelection' is performed correctly on '"+cName+"' column");
						}
						else
						{
							if (cName.equals("ID")) {
								sDataAfterFilter = CommonFunctions.GetText(_driver, By.name(Locators.getProperty(Locators.AC_ActivityCode))).trim();
							}
							else if (cName.equals("Description")) {
								sDataAfterFilter = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Description)));
							}
							
							if (!(sDataAfterFilter.equals(sDataBeforeFilter))) {
								
								dbConnection
								  .UpdateFunction("AP", "AP",
												"UC13_Vendor_Types_Show_Grid_Filter",
												"Execution", "FAIL");
								Assert.fail("The filter option 'filterBySelection' is not performed correctly on '"+cName+"' column");
							}
							System.out.println("The filter option 'filterBySelection' is performed correctly on '"+cName+"' column");
							JP.navigateToCostCenterMaintainance();
						}
						
						JP.removeFilter(cName, "");
						
						//filterExcludingSelection option
						sDataBeforeFilter = JP.GetCellData(1, i, "");
						JP.applyFilter(1, i, 2, "", "");	
						CommonFunctions.waitForPageLoaded(_driver);
						if (JP.SearchTable4Value(cName, "", sDataBeforeFilter) > 0) {							
							dbConnection
							  .UpdateFunction("AP", "AP",
											"UC13_Vendor_Types_Show_Grid_Filter",
											"Execution", "FAIL");
							Assert.fail("The filter option 'filterExcludingSelection' is not performed correctly on '"+cName+"' column");
						}
						System.out.println("The filter option 'filterExcludingSelection' is performed correctly on '"+cName+"' column");
						
						JP.removeFilter(cName, "");
						
						//filterFor option
						sDataBeforeFilter = JP.GetCellData(1, i, "");
						JP.applyFilter(1, i, 3, sDataBeforeFilter, "");	
						CommonFunctions.waitForPageLoaded(_driver);
						if ("Vendor Types".equals(_driver.getTitle()))						
						{
							List<String> sFilteredData = JP.GetColumnData(cName, "");		
							for(int k = 0;k<sFilteredData.size();k++)
							{
								if (!(sFilteredData.get(k).equals(sDataBeforeFilter))) {
									dbConnection
									  .UpdateFunction("AP", "AP",
													"UC13_Vendor_Types_Show_Grid_Filter",
													"Execution", "FAIL");
									
									Assert.fail("The filter option 'filterFor' is not performed correctly on '"+cName+"' column");
								}
							}
							System.out.println("The filter option 'filterFor' is performed correctly on '"+cName+"' column");
						}
						else
						{
							if (cName.equals("ID")) {
								sDataAfterFilter = CommonFunctions.GetText(_driver, By.name(Locators.getProperty(Locators.AC_ActivityCode))).trim();
							}
							else if (cName.equals("Description")) {
								sDataAfterFilter = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Description)));
							}
							
							if (!(sDataAfterFilter.equals(sDataBeforeFilter))) {
								dbConnection
								  .UpdateFunction("AP", "AP",
												"UC13_Vendor_Types_Show_Grid_Filter",
												"Execution", "FAIL");
								
								Assert.fail("The filter option 'filterFor' is not performed correctly on '"+cName+"' column");
							}
							System.out.println("The filter option 'filterFor' is performed correctly on '"+cName+"' column");
							JP.navigateToCostCenterMaintainance();
						}
						
						JP.removeFilter(cName, "");
						
						//filterLike option
						sDataBeforeFilter = JP.GetCellData(1, i, "");
						JP.applyFilter(1, i, 4, sDataBeforeFilter, "");	
						CommonFunctions.waitForPageLoaded(_driver);
						if ("Vendor Types".equals(_driver.getTitle()))						
						{
							List<String> sFilteredData = JP.GetColumnData(cName, "");		
							for(int k = 0;k<sFilteredData.size();k++)
							{
								if (!(sFilteredData.get(k).contains(sDataBeforeFilter))) {
									
									dbConnection
									  .UpdateFunction("AP", "AP",
													"UC13_Vendor_Types_Show_Grid_Filter",
													"Execution", "FAIL");
									Assert.fail("The filter option 'filterLike' is not performed correctly on '"+cName+"' column");
								}
							}
							System.out.println("The filter option 'filterLike' is performed correctly on '"+cName+"' column");
						}
						else
						{
							if (cName.equals("ID")) {
								sDataAfterFilter = CommonFunctions.GetText(_driver, By.name(Locators.getProperty(Locators.AC_ActivityCode))).trim();
							}
							else if (cName.equals("Description")) {
								sDataAfterFilter = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Description)));
							}
							
							if (!(sDataAfterFilter.contains(sDataBeforeFilter))) {
								

								dbConnection
										  .UpdateFunction("AP", "AP",
														"UC13_Vendor_Types_Show_Grid_Filter",
														"Execution", "FAIL");
								Assert.fail("The filter option 'filterLike' is not performed correctly on '"+cName+"' column");
								
							}
							System.out.println("The filter option 'filterLike' is performed correctly on '"+cName+"' column");
							JP.navigateToCostCenterMaintainance();
						}
						
					JP.removeFilter(cName, "");
				}				
				System.out.println("Filtering was performed & result were displayed in the Grid; The results were no longer filtered; All applicable columns can be filtered");
				dbConnection
				  .UpdateFunction("AP", "AP",
								"UC13_Vendor_Types_Show_Grid_Filter",
								"Execution", "PASS");
			}
			catch(Exception e)
			{
				System.out.println("Error in VerifyFilter"+e.getMessage()+e.getCause());
			}
			
		}
		public void SwitchToWindow(String sWindowName)
		{
			 String  originalHandle = _driver.getWindowHandle();
			 
			 String sWindowTitle =_driver.getTitle();
			 
			 Set<String> availableWindows = _driver.getWindowHandles();
			 
			 
			 if (!availableWindows.isEmpty()) {
				
					for (String windowId : availableWindows){	
				
						
							if(_driver.switchTo().window(windowId).getTitle().contains(sWindowName) ){												
								
								System.out.println(_driver.switchTo().window(windowId).getTitle());
								_driver.manage().window().maximize();
							}	
							else {
						
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
							}
						}			
				}
		}
			
		public void OrignalWindow()
		
		{
			 String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			 
			 
		}
		
		
		
		public boolean AddNewRecordInBillStatus(String sUniqueID) throws Exception
		{
			boolean bResult = false;
			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			System.out.println(sWindowTitle);
			_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
			Thread.sleep(4000);
	
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) {
				for (String windowId : availableWindows) {
	
					if (_driver.switchTo().window(windowId).getTitle()
							.equals("Adding Bill Status")) {
	
						_driver.findElement(
								By.xpath("//label[text()='ID']/../..//input"))
								.sendKeys(sUniqueID);
						_driver.findElement(
								By.xpath("//label[text()='Description']/../..//input"))
								.sendKeys(sUniqueID + "automation");
	
						_driver.findElement(
								By.xpath("//input[@title='Click to Add New Record']"))
								.click();
	
						Thread.sleep(5000);
						_driver.switchTo().window(originalHandle).getTitle()
								.equals(sWindowTitle);
	
						if (_driver.findElements(
								By.xpath(Locators.getProperty(Locators.Object_added_text))).size() != 0) {
							System.out
									.println("!!!Object Added is successfully displayed");
							bResult = true;
						} else {
							
							assertFalse("Object Added is not displayed", true);
							bResult = false;
						}
					} else {
						_driver.switchTo().window(originalHandle).getTitle()
								.equals(sWindowTitle);
						bResult = false;
					}
				}	
			}
			return bResult;
		}

		public void VerifyInvalidBlankAndDuplicateBillStatusID(String sUniqueID,String SecondID)
		{
			
			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			System.out.println(sWindowTitle);

			try {

				_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
				.click();
				Thread.sleep(2000);

				Set<String> availableWindows = _driver.getWindowHandles();
				if (!availableWindows.isEmpty()) {
					for (String windowId : availableWindows) {

						if (_driver.switchTo().window(windowId).getTitle()
								.equals("Adding Bill Status")) {

							_driver.findElement(
									By.xpath("//label[text()='ID']/../..//input"))
									.sendKeys("");
							_driver.findElement(
									By.xpath("//label[text()='Description']/../..//input"))
									.sendKeys("" + "automation");
					
							_driver.findElement(
									By.xpath("//input[@title='Click to Add New Record']"))
									.click();
							Thread.sleep(5000);
							Alert alertDialog = _driver.switchTo().alert();
							String alertText = alertDialog.getText();
							System.out.println("alertText is " + alertText);
							alertDialog.accept();
							
							if (alertText.replaceAll(" ", "").trim().equals("The following error(s) occurred:\n - The ID field is required."
									.replaceAll(" ", "").trim()))
							{
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC19_Bill_Status_Blank_Bill_Status_ID",
										"Execution", "PASS");	
								
							}
							else
							{
								dbConnection
								.UpdateFunction("AP", "AP",
										"UC19_Bill_Status_Blank_Bill_Status_ID",
										"Execution", "FAIL");	
							
								
							}
							
							assertEquals("Verifying blank id error message",
									alertText.replaceAll(" ", "").trim(),
									"The following error(s) occurred:\n - The ID field is required."
									.replaceAll(" ", "").trim());
							_driver.findElement(
									By.xpath("//label[text()='ID']/../..//input"))
									.sendKeys(SecondID);
						
							_driver.findElement(
									By.xpath("//input[@title='Click to Add New Record']"))
									.click();

							Thread.sleep(5000);
							_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

							if (_driver.findElements(
									By.xpath("//li[text()='Object Added']")).size() != 0) {
								System.out
								.println("!!!Object Added is successfully displayed");
								
							} else {
								assertFalse("Object Added is not displayed", false);
							}

						} else {
							_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

						}

					}
					// Entering inValid ID.
					_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
					_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
					.click();
					Thread.sleep(2000);

					availableWindows = _driver.getWindowHandles();

					if (!availableWindows.isEmpty()) {
						for (String windowId : availableWindows) {

							if (_driver.switchTo().window(windowId).getTitle()
									.equals("Adding Bill Status")) {

								_driver.findElement(
										By.xpath("//label[text()='ID']/../..//input"))
										.sendKeys(sUniqueID + "qq");
								_driver.findElement(
										By.xpath("//label[text()='Description']/../..//input"))
										.sendKeys("" + "automation");
								Thread.sleep(5000);
								Alert alertDialog = _driver.switchTo().alert();
								String alertText = alertDialog.getText();
								System.out.println("alertText is " + alertText);
								alertDialog.accept();
								
								if (alertText.replaceAll(" ", "").trim().equals("Invalid Number".replaceAll(" ", "").trim()))
								{
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC20_Bill_Status_Invalid_Bill_Status_ID",
											"Execution", "PASS");
								}
								else
								{
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC20_Bill_Status_Invalid_Bill_Status_ID",
											"Execution", "FAIL");
								}
								
								
								assertEquals("Verifying Invalid error message",
										alertText.replaceAll(" ", "").trim(),
										"Invalid Number".replaceAll(" ", "").trim());
								_driver.switchTo().window(windowId).close();
								
								

							} else {
								_driver.switchTo().window(originalHandle)
								.getTitle().equals(sWindowTitle);

							}
						}

					}
					// Validating for Duplicate vendor ID
					_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
					_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
					.click();
					Thread.sleep(2000);

					availableWindows = _driver.getWindowHandles();
					if (!availableWindows.isEmpty()) {
						for (String windowId : availableWindows) {

							if (_driver.switchTo().window(windowId).getTitle()
									.equals("Adding Bill Status")) {

								_driver.findElement(
										By.xpath("//label[text()='ID']/../..//input"))
										.sendKeys(SecondID);
								_driver.findElement(
										By.xpath("//label[text()='Description']/../..//input"))
										.sendKeys(SecondID + "automation");
								
								_driver.findElement(
										By.xpath("//input[@title='Click to Add New Record']"))
										.click();
								Thread.sleep(3000);
								if (_driver
										.findElements(
												By.xpath("//span[text()='Value already in use']"))
												.size() != 0) {
									System.out
									.println("Duplicate vendor id message is successfully displayed");
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC18_Bill_Status_Duplicate_Bill_Status_ID",
											"Execution", "PASS");
								} else {

									assertFalse(
											"Duplicate vendor id message :'Value already in use' is not displayed",
											false);
									dbConnection
									.UpdateFunction("AP", "AP",
											"UC18_Bill_Status_Duplicate_Bill_Status_ID",
											"Execution", "FAIL");
									
								}

								_driver.switchTo().window(windowId).close();
							} else {
								_driver.switchTo().window(originalHandle)
								.getTitle().equals(sWindowTitle);

							}
						}

					}
				}
			}

			catch (Exception e) {
				System.out
				.println("error in VerifyInvalidBlankAndDuplicateBillStatusID"
						+ e.getMessage() + e.getCause());
			}
			
		}
		
		public void CloseWindow(String sWindowName)
		{
			 String  originalHandle = _driver.getWindowHandle();
			 System.out.println(originalHandle);
			 String sWindowTitle =_driver.getTitle();
			 System.out.println(sWindowTitle);
			 Set<String> availableWindows = _driver.getWindowHandles();
			 
			 
			 if (!availableWindows.isEmpty()) {
				
					for (String windowId : availableWindows){	
				
						
							if(_driver.switchTo().window(windowId).getTitle().contains(sWindowName) ){												
								
								System.out.println("closing window "+sWindowName);
								_driver.switchTo().window(windowId).close();														
							}	
							else {
						
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
							}
						}			
				}
		}
		public void VerifyBlankDescription(String ThirdUniqueID)
		{
			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			System.out.println(sWindowTitle);
			
			try {

				_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]"))
				.click();
				Thread.sleep(2000);

				SwitchToWindow("Adding Bill Status");
				
				_driver.findElement(
						By.xpath("//label[text()='ID']/../..//input"))
						.sendKeys(ThirdUniqueID);
				_driver.findElement(
						By.xpath("//label[text()='Description']/../..//input"))
						.sendKeys("");
		
				_driver.findElement(
						By.xpath("//input[@title='Click to Add New Record']"))
						.click();
				Thread.sleep(5000);
				Alert alertDialog = _driver.switchTo().alert();
				String alertText = alertDialog.getText();
				System.out.println("alertText is " + alertText);
				alertDialog.accept();
				if (alertText.replaceAll(" ", "").trim().equals("The following error(s) occurred:\n- The Description field is required."
						.replaceAll(" ", "").trim()))
				{
					dbConnection
					.UpdateFunction("AP", "AP",
							"UC21_Bill_Status_Blank_Bill_Status_Description",
							"Execution", "PASS");	
				}
				else
				{
					dbConnection
					.UpdateFunction("AP", "AP",
							"UC21_Bill_Status_Blank_Bill_Status_Description",
							"Execution", "FAIL");	
					System.err.println("UC21_Bill_Status_Blank_Bill_Status_Description test failed ");
				}
				assertEquals("Verifying blank id error message",
						alertText.replaceAll(" ", "").trim(),
						"The following error(s) occurred:\n- The Description field is required."
						.replaceAll(" ", "").trim());
				
				CloseWindow("Adding Bill Status");
				_driver.switchTo().window(originalHandle)
				.getTitle().equals(sWindowTitle);
				 
			}
			catch(Exception e)
			{
				System.out
				.println("error in VerifyBlankDescription"
						+ e.getMessage() + e.getCause());
				
			}
		}

		public boolean SwitchToAnyWindow(String SecondwindoName)
		{
			 String  originalHandle = _driver.getWindowHandle();
			 
			 String sWindowTitle =_driver.getTitle();
			 
			 Set<String> availableWindows = _driver.getWindowHandles();
			 
			 
			 if (!availableWindows.isEmpty()) {
				
					for (String windowId : availableWindows){	
				
						
							
							if(_driver.switchTo().window(windowId).getTitle().contains(SecondwindoName))
							{
								System.out.println(_driver.switchTo().window(windowId).getTitle());		
								return true;
								
							}
							
						}			
				}
			 return false;
		}

		public void VerifyEditAndDeleteBillStatus(String SearchID,
			String description) throws Exception {
		
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		System.out.println(sWindowTitle);
		String sDescription, sID;

		_driver.findElement(By.name("search")).sendKeys(SearchID);
		_driver.findElement(By.name("performSearch")).click();
		Thread.sleep(5000);
		_driver.findElement(By.name("updateForm")).click();

		if (_driver.findElements(
				By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() == 1) {
			System.out
					.println("!!!Invalid account message is successfully displayed");
			_driver.findElement(
					By.xpath(".//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"))
					.click();

		} else {
			assertFalse("No record  found  to Delete with ID" + SearchID, false);
		}

		SwitchToWindow("Bill Status ");
		_driver.findElement(
				By.xpath("//label[text()='Description']/../..//input")).clear();
		_driver.findElement(
				By.xpath("//label[text()='Description']/../..//input"))
				.sendKeys(description);

		_driver.findElement(By.name("updateForm")).click();
		Thread.sleep(5000);

		_driver.switchTo().window(originalHandle).getTitle()
				.equals(sWindowTitle);
		if (_driver.findElements(By.xpath("//li[text()='Updated']")).size() != 0) {
			System.out.println("Updated message is successfully displayed");
			dbConnection
			.UpdateFunction("AP", "AP",
					"UC22_Bill_Status_Edit_Bill_Status_details",
					"Execution", "PASS");	
		} else {
			dbConnection
			.UpdateFunction("AP", "AP",
					"UC22_Bill_Status_Edit_Bill_Status_details",
					"Execution", "FAIL");	

			assertFalse(
					"Updated message is not displayed in Bill status page.",
					false);
		}

		Thread.sleep(5000);

		if (_driver
				.findElement(
						By.xpath(".//*[@id='appbox_implicit']/tbody/tr/td[4]/div"))
				.getText().equalsIgnoreCase(description)) {

			System.out.println("record is successfully updated");
		} else {
			assertFalse("Updated message is not updated currectly.", false);
		}

		CommonFunctions.selectDropdownByIndex(_driver,
				By.xpath(Locators.getProperty(Locators.Include_Dropdown)), 1);

		_driver.findElement(By.name("search")).sendKeys(SearchID);
		_driver.findElement(By.name("performSearch")).click();

		if (_driver.findElements(
				By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() == 1) {
			System.out.println("Record found");
		} else {
			System.err.println("No InActive record found to Delete");
			dbConnection
			.UpdateFunction("AP", "AP",
					"UC23_Bill_Status_Delete_Bill_Status_not_in_use",
					"Execution", "FAIL");	
			assertFalse("No record  found with id" + SearchID, false);
		}

		int rowcount = _driver.findElements(
				By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		if (rowcount >= 1) {
			sDescription = _driver
					.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
					.getText();
			sID = _driver
					.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
					.getText();
			sDescription = sDescription.trim();
			sID = sID.trim();
			System.out.println("sDescription is " + sDescription);
			System.out.println("sID is " + sID);
			assertEquals("validating Id with search id in grid", sID.trim()
					.toUpperCase().replace(" ", ""), SearchID.trim()
					.toUpperCase().replace(" ", ""));
			assertEquals(
					"validating Id with search id with description in grid",
					sDescription.trim().toUpperCase().replace(" ", ""),
					description.trim().toUpperCase().replace(" ", ""));
		}

		_driver.findElement(
				By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"))
				.click();
		SwitchToWindow("Bill Status ");
		_driver.findElement(
				By.xpath("//*[@id='buttonbox']/input[@value='Delete']"))
				.click();
		_driver.findElement(
				By.xpath("//*[@id='contentDiv']/table/tbody/tr[2]/td/div/input[@value='Delete']"))
				.click();

		_driver.switchTo().window(originalHandle).getTitle()
				.equals(sWindowTitle);
		_driver.findElement(By.name("search")).sendKeys(SearchID);
		_driver.findElement(By.name("performSearch")).click();

		if (_driver.findElements(
				By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() == 0) {
			System.out
					.println(" Record not  found after deletion in Active state");
			dbConnection
			.UpdateFunction("AP", "AP",
					"UC24_Bill_Status_Delete_Bill_Status_in_use",
					"Execution", "PASS");	
		} else {
			
			dbConnection
			.UpdateFunction("AP", "AP",
					"UC24_Bill_Status_Delete_Bill_Status_in_use",
					"Execution", "PASS");
			System.err.println("Record deletion fail  in Active state ");
			assertFalse("Record still exists in Grid after deletion", false);

		}

		CommonFunctions.selectDropdownByIndex(_driver,
				By.xpath(Locators.getProperty(Locators.Include_Dropdown)), 3);

		rowcount = _driver.findElements(
				By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		if (rowcount >= 1) {
			sDescription = _driver
					.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
					.getText();
			sID = _driver
					.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
					.getText();
			sDescription = sDescription.trim();
			sID = sID.trim();
			System.out.println("sDescription is " + sDescription);
			System.out.println("sID is " + sID);

			_driver.findElement(
					By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"))
					.click();
			SwitchToWindow("Bill Status ");
			_driver.findElement(
					By.xpath("//*[@id='buttonbox']/input[@value='Delete']"))
					.click();
			_driver.findElement(
					By.xpath("//*[@id='contentDiv']/table/tbody/tr[2]/td/div/input[@value='Delete']"))
					.click();

			_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
			_driver.findElement(By.name("search")).sendKeys(SearchID);
			_driver.findElement(By.name("performSearch")).click();

			if (_driver.findElements(
					By.xpath(".//table[@id='appbox_implicit']/tbody/tr"))
					.size() == 0) {
				System.out
						.println(" Record not  found after deletion in InActive state");
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC23_Bill_Status_Delete_Bill_Status_not_in_use",
						"Execution", "PASS");
			} else {
				dbConnection
				.UpdateFunction("AP", "AP",
						"UC23_Bill_Status_Delete_Bill_Status_not_in_use",
						"Execution", "FAIL");
				System.err.println("Record deletion fail  in Active state ");
				assertFalse(
						"Record still exists in Grid after deletion inActive",
						false);

			}

		}

	}
		
		
		public void VerifySearchAndSortRecordsBillStatus(String Id, String desciption)
				throws Exception {

			String sDescription, sID;
			Actions action = new Actions(_driver);

			boolean RecordFound = true, bRemoveSort = false, sortAscend = false, sortDescend = false;
			boolean sortResults;

			try {
				JobPlanningPage JP = new JobPlanningPage(_driver);
				CommonFunctions.selectDropdownByIndex(_driver,
						By.xpath(Locators.getProperty(Locators.Include_Dropdown)),
						1);
				String sDataBeforeFilter = "", sDataAfterFilter = "";
				_driver.findElement(By.name("search")).sendKeys(Id);
				_driver.findElement(By.name("performSearch")).click();

				if (_driver.findElements(
						By.xpath(".//table[@id='appbox_implicit']/tbody/tr"))
						.size() == 1) {
					System.out.println("Record found");
				} else {
					assertFalse("No record  found with id" + Id, false);
				}

				int rowcount = _driver.findElements(
						By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
				if (rowcount >= 1) {
					sDescription = _driver
							.findElement(
									By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[4]/div"))
							.getText();
					sID = _driver
							.findElement(
									By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[3]/div"))
							.getText();
					sDescription = sDescription.trim();
					sID = sID.trim();
					System.out.println("sDescription is " + sDescription);
					System.out.println("sID is " + sID);
					assertEquals("validating Id with search id in grid", sID.trim()
							.toUpperCase().replace(" ", "").replace("0", ""), Id.trim()
							.toUpperCase().replace(" ", "").replace("0", ""));
					assertEquals(
							"validating Id with search id with description in grid",
							sDescription.trim().toUpperCase().replace(" ", ""),
							desciption.trim().toUpperCase().replace(" ", ""));
				}

				Thread.sleep(3000);

				CommonFunctions.selectDropdownByIndex(_driver,
						By.name("searchField"), 1);

				_driver.findElement(By.name("search")).sendKeys(desciption);
				_driver.findElement(By.name("performSearch")).click();

				rowcount = _driver.findElements(
						By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
				for (int i = 1; i < rowcount - 1; i++) {
					sDescription = _driver.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr[" + i
									+ "]/td[4]/div")).getText();

					sDescription = sDescription.trim();

					if (!(sDescription.contains(desciption))) {
						RecordFound = false;
						break;
					}
				}

				assertEquals(
						"Validating record in grid match with pass description",
						true, RecordFound);

				System.out
						.println("On the Cost Centers Tab, For each applicable column disable sorting by clicking on the respective heading and selecting 'Remove Sort'");
				if (JP.removeSort("all",
						Locators.getProperty(Locators.Common_Webtable))) {
					System.out.println("Sorting was disabled on all columns");
					bRemoveSort = true;
				} else {
					
					Assert.fail("sorting was not disabled on all columns");
				}

				System.out
						.println("For each column individually, - Enable the sort option (Asce & Desc) - Disable the sort option");
				int iColCount = _driver.findElements(
						By.xpath(Locators.getProperty(Locators.Common_Webtable)
								+ "/thead/tr[1]/th")).size();
				for (int i = 3; i <= iColCount - 1; i++) {
					String sColumnName = _driver.findElement(
							By.xpath(Locators.getProperty(Locators.Common_Webtable)
									+ "/thead/tr[1]/th[" + i + "]/a/span"))
							.getText();

					System.out.println("\nsorting '" + sColumnName
							+ "' column in ascending order");
					sortAscend = JP.SortAndVerify("Ascend", sColumnName,
							Locators.getProperty(Locators.Common_Webtable));

					System.out.println("\nsorting '" + sColumnName
							+ "' column in descending order");
					sortDescend = JP.SortAndVerify("Descend", sColumnName,
							Locators.getProperty(Locators.Common_Webtable));

					if (!(sortDescend && sortAscend)) {
						sortResults = false;
					}
				}

				if (bRemoveSort) {
					System.out
							.println("\n1. Sorting was disabled on all columns. 2. Sorting worked according to the selected options");

				} else {

					Assert.fail("The Sorting is not working according to the selected options");
				}

			} catch (Exception e) {
				System.out.println("error in VerifySearchAndSortRecords"
						+ e.getMessage() + e.getCause());
			}

		}

	
		
	public String AddBillBatch(String GLAccPeriod, boolean approved, String BillBatchType) throws Exception
	{	
		DCPage DC = new DCPage(_driver);
		
		if (BillBatchType.equals("EnterBillBatch"))
		{
			NavigateToAddNewBillBatch();
		}
		else if(BillBatchType.equals("PaymentBillBatch"))
		{
			NavigateToAddNewPaymentBillBatch();
		}		
		
		if (GLAccPeriod.equals(""))
		{
			Select sele = new Select(_driver.findElement(By.name(Locators.getProperty(Locators.GL_AccountingPeriod))));
			int optionCnt = sele.getOptions().size();
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.GL_AccountingPeriod)), optionCnt-1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.GL_AccountingPeriod)), GLAccPeriod);			
		}
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("description"), "Bill Batch "+UniqueNum());
		CommonFunctions.sSelectCheckBox(_driver, approved, By.name("approvedBooleanCheck"));
		DC.Add();
		
		String sBillBatch = CommonFunctions.getIDfromURL(_driver);
		System.err.println("Bill Batch "+sBillBatch+" is created");
		return sBillBatch;
	}

	public String AddBill(String PONumber, String Vendor) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sBillNum = UniqueNum1();
		CommonFunctions.SendValue(_driver, By.name("poNumberInput"), PONumber);		
		_driver.findElement(By.name("poNumberInput")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		if(!Vendor.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.name("vendor"), PONumber);
		}
		
		CommonFunctions.SendValue(_driver, By.name("invoiceNumber"), sBillNum);
		DC.Add();
		
		System.err.println("Created new Bill "+sBillNum+" for vendor "+Vendor+" and purchase order "+PONumber);
		return sBillNum;
	}
	
	public String AddBilliwithDates(String PONumber, String Vendor, String BillDate, String DueDate) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sBillNum = UniqueNum1();
		CommonFunctions.SendValue(_driver, By.name("poNumberInput"), PONumber);		
		_driver.findElement(By.name("poNumberInput")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		if(!Vendor.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.name("vendor"), PONumber);
		}
		
		CommonFunctions.SendValue(_driver, By.name("invoiceNumber"), sBillNum);
		CommonFunctions.SendValue(_driver, By.name("invoiceDate"), BillDate);
		_driver.findElement(By.name("dateDue")).sendKeys(Keys.CONTROL + "a"); 
		_driver.findElement(By.name("dateDue")).sendKeys(Keys.DELETE);
		CommonFunctions.SendValue(_driver, By.name("dateDue"), DueDate);
		DC.Add();
		
		System.err.println("Created new Bill "+sBillNum+" for vendor "+Vendor+" and purchase order "+PONumber);
		return sBillNum;
	}
		
	public boolean PostEnteredBills(String BillBatchID) throws Exception
	{
		NavigateToPostBillBatch();
		
		if(!BillBatchID.equals(""))
		{
			CommonFunctions.selectDropdown(_driver, By.name("batch"), BillBatchID);
			Thread.sleep(2000);
		}
		else
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("approvedOnlyBooleanCheck"));
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Post Bill Batches' and @name='updateForm']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(12000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//li[contains(text(),'Bill Batch: Posted')]"));
		
		System.err.println("Posted bills for Bill Batch "+BillBatchID);
		return _driver.getTitle().equals("Bill Post Successful!");
	}
	
	public void selectBillsForPayment(String sVendorID, String PaymentBillbatchID) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		NavigateToSelectForPaymentList();
		
		DC.SearchValue(sVendorID, "name");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("billPaymentBatch"));
		
		CommonFunctions.selectDropdown(_driver, By.name("billPaymentBatch"), PaymentBillbatchID);
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='Pay']/../following-sibling::span/a[text()='(all)']"));
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='Pay With Discount']/../following-sibling::span/a[text()='(all)']"));
		DC.Update();
		System.err.println("Selected bills for payment for Payment bill batch "+PaymentBillbatchID+" and vendor "+sVendorID);
	}

	public boolean processChecks(String sPaymentBillBatchID) throws Exception
	{
		NavigateToProcessChecks();
		
		CommonFunctions.selectDropdown(_driver, By.name("postSingleBatch"), sPaymentBillBatchID);
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Process Vendor Checks' and @name='updateForm']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(12000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//li[contains(text(),'Bill Payment Batches: Processed')]"));
		
		System.err.println("Checks were processed for Payment Bill Batch "+sPaymentBillBatchID);
		return _driver.getTitle().equals("Bill Process Checks Successful!");
	}

	public boolean postChecks() throws Exception
	{
		NavigateToPostChecks();
		
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Post Vendor Checks' and @name='updateForm']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(12000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//li[contains(text(),'Bills: Paid')]"));
		
		System.err.println("Successfully posted the Checks");
		return _driver.getTitle().equals("Bill Process Checks Successful!");
	}
	
	public void pickPOReceipts(String VendorID, String BillNumber, String PONum) throws Exception
	{		
		String orginalWindow = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Pick PO Receipts']"));
		SwitchToWindow("Adding Purchase Order Receipts for "+VendorID+"-"+BillNumber);
		Thread.sleep(2000);
		System.out.println("Picking up the PO Receipt '"+CommonFunctions.GetText(_driver, By.xpath("//li[text()='PurchaseOrder ("+PONum+")']/ul/li/ul/li/a"))+"'");
		CommonFunctions.ClickElement(_driver, By.xpath("//li[text()='PurchaseOrder ("+PONum+")']/ul/li/ul/li/a"));
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Add Purchase Order Receipts' and @name='updateForm']"));
		Thread.sleep(3000);
		_driver.switchTo().window(orginalWindow);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}

	public void gotoPaymentBillBatch(String BillBatchID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
	   	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BillPaymentBatch/detail/"+BillBatchID);
	   	CommonFunctions.Wait(_driver, By.xpath("//a[text()='Bill Payments']"));
	   	assertEquals("BillPayment Batch", _driver.getTitle());
	   	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"gotoPaymentBillBatch");
	}

	public void navigateToVendorContactsPage(String VendorID) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		 sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/contacts/"+VendorID);
    	CommonFunctions.waitForPageLoaded(_driver);    	
    	CommonFunctions.Wait(_driver, By.id("appbox_implicit"));
    	assertEquals("Contacts", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"navigateToVendorContactsPage");
	}
	
	public void deleteVendorContacts(String VendorID) throws Exception
	{
		APPage AP = new APPage(_driver);
		
		navigateToVendorContactsPage(VendorID);
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr")) ;
		
		for (int i=1; i<iRwCnt+1; i++)
		{
			String sOriginalWindow = _driver.getWindowHandle();
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]//img"));
			Thread.sleep(1000);
			AP.SwitchToWindow("Contact");
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Delete)));
			Thread.sleep(1000);
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Delete_Popup)));
			Thread.sleep(2000);
			_driver.switchTo().window(sOriginalWindow);
		}
	}
	
}
