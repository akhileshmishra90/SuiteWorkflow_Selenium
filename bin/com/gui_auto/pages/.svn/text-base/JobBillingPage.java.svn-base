package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.S2Table;
import com.gui_auto.utilities.ScreenShot;
import org.openqa.selenium.Keys;
import java.lang.reflect.Method;

public class JobBillingPage implements BaseElement {

	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	private String _pageURL;
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	private static String sSERVER = null;
	private static String sCOMPANY = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public JobBillingPage(WebDriver driver) throws Exception {
		super();
		this._driver = driver;

	}

	public void navigateToPageAndWait() {
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

	public String UniqueNum() {
		// DateFormat dateFormat = new
		// SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		DateFormat dateFormat = new SimpleDateFormat("ddhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}

	public void NavigateToJobBillingInvoicesType() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceType/list");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Invoice Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToJobBillingInvoicesType");
	}
	
	public void NavigateToPostedInvoices() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Invoice/listPosted");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Invoice", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostedInvoices");
	}
	
	public void NavigateToAddInvoiceExtraType() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceExtraType/add");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Add)));
		assertEquals("Adding Invoice Extra Type", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAddInvoiceExtraType");
	}
	
	public void NavigateToCountryList() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Country/list");
			CommonFunctions.waitForPageLoaded(_driver);
		
	
	}
	
	
	public void NavigateToCountryDetailPage(String SequenceNo) throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Country/detail/"+SequenceNo);
			CommonFunctions.waitForPageLoaded(_driver);
		
	
	}
	
	
	
	
	
	public void NavigateToInvoiceExtraTypeLsit() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceExtraType/list");
	
		assertEquals("Invoice Extra Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToInvoiceExtraTypeLsit");
	}
	public void AddExtraType(String Description,String SalesCategory,String ExtraSalescategory)throws Exception,IOException
	{
		try {
			

				CommonFunctions.SendValue(_driver, By.name("description"), Description);
				
				CommonFunctions.selectDropdownByText(_driver,By.name("salesCategory"), SalesCategory);
				CommonFunctions.selectDropdownByText(_driver, By.name("extraCategory"), ExtraSalescategory);
				Add();
				
				if(Update())
				{
					System.out.println("New ExtraType is Successfuly Added");
				}
				else
				{
					System.err.println("Failed to Add ExtraType");
					Assert.fail("Failed to Add ExtraType");
				}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to create Extra type");
		}
		
		
			
	}
	
	public void NavigateToProcessInvoiceBatches() throws Exception,IOException
	 {
			sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=InvoiceBatch.process");
			CommonFunctions.Wait(_driver,
					By.name(Locators.getProperty(Locators.Search_TextField)));
			assertEquals("Process Invoice Batches", _driver.getTitle());
			NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
					"NavigateToProcessInvoiceBatches");
	}

	public boolean Update() throws Exception, IOException {
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update)))
				.click();
		System.out.println("****Clicking the Update button****");
		Thread.sleep(2000);
		if (_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Updated_text)))
				.isDisplayed()) {
			System.out.println("**Page is updated**");
			return true;
		} else {
			System.out.println("**Page is not updated**");
			return false;
		}
	}
	
	public boolean ObjectUpdated() throws Exception, IOException {
		
		
		Thread.sleep(2000);
		if (_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Updated_text)))
				.isDisplayed()) {
			System.out.println("**Page is updated**");
			return true;
		} else {
			System.out.println("**Page is not updated**");
			return false;
		}
	}

	public void NavigateNoteCategoryList()throws Exception, IOException 
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
			+ "/object/NoteCategory/list");
		CommonFunctions.Wait(_driver,
				By.name("search"));
		assertEquals("Note Categories", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateNoteCategoryList");
		
		
	}
	
	public void AddNewInvoiceType(String sDesc, String sEntryType)
			throws Exception {
		_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Add_New_Record)))
				.click();
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.EntryType)), sEntryType);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(1000);
	}

	public void NavigateToJobBillingInvoicesExtraType()
			throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceExtraType/list");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Invoice Extra Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToJobBillingInvoicesExtraType");
	}
	
	public void NavigateToJobPart(String JobID,String Part)throws Exception, IOException
	{
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/JobPart/detail/"+JobID+":"+Part);
		
	}
	public void NavigateToJobPartEdited(String JobID,String Part)throws Exception, IOException
	{
		
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/JobPart/detail/"+JobID+"%"+"3A"+Part);
		try {
			_driver.switchTo().alert().accept();
			CommonFunctions.ReturnToOriginalWindow(_driver);
			
		} catch (Exception e) {

		}
		
		
	}
	
	public void NavigateToJobDetailPage(String JobID)throws Exception, IOException
	{
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Job/detail/"+JobID+"?tab=0");
		
	}

	public void NavigateToAP_EnterNewBatch() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/BillBatch/add");
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Bill Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAP_EnterNewBatch");
	}

	public void NavigateToEnterNewInvoicePage() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Invoice/add");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Job)));
		assertEquals("Adding Invoice Transaction", _driver.getTitle());
		/*NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEnterNewInvoicePage");*/
	}

	public void NavigateToResetJobCostValue() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=JobCostTotalsReset.process");
		CommonFunctions.Wait(_driver, By.name("startDate"));
		assertEquals("Job Cost Total Reset", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToResetJobCostValue");
	}

	public void NavigateToPostCustomerPayment() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=PaymentBatch.post");
		CommonFunctions.Wait(_driver, By.name("updateForm"));
		assertEquals("Post Customer Payments", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostCustomerPayment");
	}

	public void NavigateToEnterNewBatch() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/add");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEnterNewBatch");
	}
	
	
	public void NavigateToPostedInvoiceBatchList() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/listPosted");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Posted Invoice Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostedInvoiceBatchList");
	}

	
	public void NavigateToViewAndEditInvoice() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Invoice/list");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		//assertEquals("Invoice", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToViewAndEditInvoice");
	}
	
	
	public void NavigateToRecurringMonthlyInvoice() throws Exception,
	IOException {
			sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
					+ "/process/run?key=AutoInvoice.generate");
			Thread.sleep(2000);
			CommonFunctions.Wait(_driver,
					By.name("appbox_currentTab"));
			assertEquals("Select auto-invoice generation date/job type.", _driver.getTitle());
			NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
					"NavigateToRecurringMonthlyInvoice");
			}

	public void NavigateToCreateAutoInvoice() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=Job.createAutoInvoices");
		Thread.sleep(2000);
	
		assertEquals("Creating Invoices from Jobs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToCreateAutoInvoice");
	}
	
	public void NavigateToAutoInvoiceCustomer(String CustomerID) throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/AutoInvoice/listCustomer/"+CustomerID);
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Auto Invoicing", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAutoInvoiceCustomer");
	}
	
	public void NavigateToCreateAutoInvoiceMonthlyRecurring() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=AutoInvoice.generate");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver,
				By.name("generationDate"));
	
		assertEquals("Select auto-invoice generation date/job type.", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToCreateAutoInvoiceMonthlyRecurring");
	}

	public void CreateStandardAutoInvoice(String CustomerId)throws Exception,IOException 
	{
		CommonFunctions.SendValue(_driver,By.name("startDate"),new Date().toString());
		CommonFunctions.SendValue(_driver,By.name("endDate"),new Date().toString());
		CommonFunctions.SendValue(_driver,By.name("customer"),CustomerId);
		
		CommonFunctions.ClickElement(_driver,By.xpath("//input[@value='Create Invoices']"));
		CommonFunctions.waitForPageLoaded(_driver);
	}
	
	public Boolean SearchInvoiceID(String InvoiceNum)throws Exception,	IOException
	
	{
		NavigateToViewAndEditInvoice();
		CommonFunctions.selectDropdownByValue(_driver,By.name("searchField"), "invoiceNum");
		CommonFunctions.SendValue(_driver, By.name("search"), InvoiceNum);
		
		CommonFunctions.ClickElement(_driver, By.name("performSearch"));
		NavigateToViewAndEditInvoice();
		
		if (_driver.findElements(
				By.xpath(".//table[@id='All']/tbody/tr")).size() == 1) {
			System.out.println("Invoice is successfuly found in the Grid");
			_driver.findElement(
					By.xpath(".//table[@id='All']/tbody/tr/td[2]/div/a/img"))
					.click();
			return true;

		} else {
			System.err.println("Failed  to find Record in the Grid");
			return false;
		}

	}

	public Boolean SearchInvoiceIDIntable(String InvoiceNum)throws Exception,	IOException
	
	{
		Boolean IsInvoiceSame;
		String InvoiceNumFromGrid="";
		
		
		
		if (_driver.findElements(
			By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() == 1) {
			InvoiceNumFromGrid=CommonFunctions.GetText(_driver,By.xpath(".//table[@id='appbox_implicit']/tbody/tr/td[3]/div"));
			 IsInvoiceSame=InvoiceNum.equals(InvoiceNumFromGrid);
			
			return IsInvoiceSame;

		}
		
		else if(_driver.findElements(
				By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size() >1)
		{
			Integer RowCount=_driver.findElements(By.xpath(".//table[@id='appbox_implicit']/tbody/tr")).size();
			
			
					
			for (int i = 1; i <= RowCount; i++) {
				InvoiceNumFromGrid=CommonFunctions.GetText(_driver,By.xpath(".//table[@id='appbox_implicit']/tbody/tr["+i+" ]/td[3]/div"));
				IsInvoiceSame=InvoiceNum.equals(InvoiceNumFromGrid);
				if (IsInvoiceSame)
				{
					return IsInvoiceSame;
				}
				
			}
			
			return false;
			
		}
		else {
			System.err.println("Failed  to find Record in the Grid");
			return false;
		}
		
		

	}
	
	
	public void PerformSearch(String InvoiceNum)throws Exception,	IOException
	
	{
		CommonFunctions.selectDropdownByValue(_driver,By.name("searchField"), "invoiceNum");
		CommonFunctions.SendValue(_driver, By.name("search"), InvoiceNum);
		
		CommonFunctions.ClickElement(_driver, By.name("performSearch"));
	}
	
	public Boolean SearchIntable(String InvoiceNum,String Tableid ,Integer columnNo)throws Exception,	IOException
	
	{
		
		Boolean IsInvoiceSame;
		String InvoiceNumFromGrid="";
		
	
		
		
		
		if (_driver.findElements(
			By.xpath(".//table[@id='"+Tableid+"']/tbody/tr")).size() == 1) {
			InvoiceNumFromGrid=CommonFunctions.GetText(_driver,By.xpath(".//table[@id='"+Tableid+"']/tbody/tr/td["+columnNo+"]"));
			 IsInvoiceSame=InvoiceNum.equals(InvoiceNumFromGrid);
			
			return IsInvoiceSame;

		}
		
		else if(_driver.findElements(
				By.xpath(".//table[@id='"+Tableid+"']/tbody/tr")).size() >1)
		{
			Integer RowCount=_driver.findElements(By.xpath(".//table[@id='"+Tableid+"']/tbody/tr")).size();
			
			
					
			for (int i = 1; i < RowCount+1; i++) {
				InvoiceNumFromGrid=	CommonFunctions.GetText(_driver,By.xpath(".//table[@id='"+Tableid+"']/tbody/tr["+i+" ]/td["+columnNo+"]"));
				IsInvoiceSame=InvoiceNum.equals(InvoiceNumFromGrid);
				if (IsInvoiceSame)
				{
					return IsInvoiceSame;
				}
				
			}
			
			return false;
			
		}
		else {
			System.err.println("Failed  to find Record in the Grid");
			return false;
		}
		
		

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
	
	 public void NavigateToCustomerDetailPage(String CustomerId) throws Exception, IOException
	 {
		 sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
	    	sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
	    _driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/detail/"+CustomerId+"?tab=0");	
	    CommonFunctions.waitForPageLoaded(_driver);
	    
	  
	 }
	public void CreateContact(String sFN,String sLN,String sComp,String sPhoneN,String Add2,String Add3,String Email,String Zip,String CustomerId,String SalestaxID,String Address) throws Exception
	{		
		NavigateToAddContactPage();	
		
		CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));
		
		CommonFunctions.ClickElement(_driver, By.xpath("//label[text()='Customer']/../following-sibling::div/div[@title='Click to select a object.']"));
		CommonFunctions.SwitchToWindow(_driver,"Please select a Customer - Customer");
		
		if(!SearchGetCustomer(CustomerId))
		{
			System.err.println("Unable to select the customer value as CustomerId "+CustomerId+" was not found  in the Customer Table");
			CommonFunctions.CloseWindow(_driver, "Please select a Customer - Customer");
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		else
		{			
			CommonFunctions.ClickElement(_driver, By.xpath(".//*[@id='Customer']/tbody/tr[1]/td[2]/a/div"));			
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		 
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);		
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sComp);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(sPhoneN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(Add2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(Add3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Email))).sendKeys(Email);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Zip))).sendKeys(Zip);
		
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), "US - United States");	
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(1000);
		if (CommonFunctions.isElementPresent(_driver, By.name("defaultCurrency")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("defaultCurrency"), "USD");
		}
		CommonFunctions.selectDropdownByText(_driver, By.name("taxableCode"),"Taxable");

		
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//label[text()='Sales Tax']/../following-sibling::div/div[@title='Click to select a object.']"));		  
		CommonFunctions.SwitchToWindow(_driver, "Please select a Sales Tax");

		if(!SearchTax(SalestaxID))
		{
			System.err.println("Unable to select the tax value as tax id "+SalestaxID+" was not found  in the Tax Table");
			CommonFunctions.CloseWindow(_driver, "Please select a Sales Tax");
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		else
		{			
			CommonFunctions.ClickElement(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		_driver.findElement(By.xpath("//a[text()='Contact Info']")).click();
		Thread.sleep(2000);
		
		Address=Address.toUpperCase();
		
		if (Address.equals("BILL"))
		{
			if (!_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
			if (_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
		}
		else if (Address.equals("SHIP"))
		{
			if (!_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
			if (_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
		}
		else if (Address.equals("BOTH"))
		{
			if (!_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
			
			if (!_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
		}
		else
		{
			System.out.println("Contact without default delivered address");
		}		
			
		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(5000);
		if(CommonFunctions.isTextPresent(_driver, "Object Added"))

		{
			System.out.println("Successfully created contact");
		}
		else
		{
			System.err.println("Unable to create contact ");
		}
	}
	 
	public void CreateContactFromJObPage(String sFN,String sLN,String sComp,String sPhoneN,String Add2,String Add3,String Email,String Zip,String CustomerId,String SalestaxID,String Address) throws Exception
	{
		
		CommonFunctions.ClickElement(_driver, By.xpath("//label[text()='Customer']/../following-sibling::div/div[@title='Click to select a object.']"));
		CommonFunctions.SwitchToWindow(_driver,"Please select a Customer - Customer");
		
		if(!SearchGetCustomer(CustomerId))
		{
			System.err.println("Unable to select the customer value as CustomerId "+CustomerId+" was not found  in the Customer Table");
			CommonFunctions.CloseWindow(_driver, "Please select a Customer - Customer");
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		else
		{			
			CommonFunctions.ClickElement(_driver, By.xpath(".//*[@id='Customer']/tbody/tr[1]/td[2]/a/div"));			
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		 
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);		
		 CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));
		
		
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sComp);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(sPhoneN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(Add2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(Add3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Email))).sendKeys(Email);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Zip))).sendKeys(Zip);
		
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), "US - United States");	
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(2000);
		if (CommonFunctions.isElementPresent(_driver, By.name("defaultCurrency")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("defaultCurrency"), "USD");
		}
		CommonFunctions.selectDropdownByText(_driver, By.name("taxableCode"),"Taxable");

		
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//label[text()='Sales Tax']/../following-sibling::div/div[@title='Click to select a object.']"));		  
		CommonFunctions.SwitchToWindow(_driver, "Please select a Sales Tax");

		if(!SearchTax(SalestaxID))
		{
			System.err.println("Unable to select the tax value as tax id "+SalestaxID+" was not found  in the Tax Table");
			CommonFunctions.CloseWindow(_driver, "Please select a Sales Tax");
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		else
		{
			
			CommonFunctions.ClickElement(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"));
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
		_driver.findElement(By.xpath("//a[text()='Contact Info']")).click();
		Thread.sleep(2000);
		
		Address=Address.toUpperCase();
		
		if (Address.equals("BILL"))
		{
			if (!_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
			if (_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
		}
		else if (Address.equals("SHIP"))
		{
			if (!_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
			if (_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
		}
		else if (Address.equals("BOTH"))
		{
			if (!_driver.findElement(By.name("billToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("billToBooleanCheck"));
			}
			
			if (!_driver.findElement(By.name("shipToBooleanCheck")).isSelected())
			{
				CommonFunctions.ClickElement(_driver, By.name("shipToBooleanCheck"));
			}
		}
		else
		{
			System.err.println("Invalid Address input format");
		}
		
			
		Add();
		CommonFunctions.waitForPageLoaded(_driver);		
		Thread.sleep(5000);
		if(CommonFunctions.isTextPresent(_driver, "Object Added"))

		{
			System.out.println("Successfully created contact");
		}
		else
		{
			System.err.println("Unable to create contact ");
		}
			
	}
	
	
	
	public Boolean SearchTax(String SalestaxID)throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[@name='searchField']"), "ID");
		CommonFunctions.SendValue(_driver, By.name("search"), SalestaxID, "SalestaxID");
		CommonFunctions.ClickElement(_driver, By.name("performSearch"));	

		Thread.sleep(4000);
		if(S2Table.getNumberOfRowsInTable("appbox_implicit",_driver)==1)
		{
			return true; 
		}
		else
		{
			return false;
		}

	}
	
	public Boolean SearchGetCustomer(String CustomerID)throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[@name='searchField']"), "Customer ID");
		CommonFunctions.SendValue(_driver, By.name("search"), CustomerID, "CustomerID");
		CommonFunctions.ClickElement(_driver, By.name("performSearch"));	

		Thread.sleep(4000);
		if(S2Table.getNumberOfRowsInTable("Customer",_driver)==1)
		{
			return true; 
		}
		else
		{
			return false;
		}

	}
	
	public Boolean Search(String SearchBy,String SearchByValue,String TableId)throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[@name='searchField']"), SearchBy);
		CommonFunctions.SendValue(_driver, By.name("search"), SearchByValue, "CustomerID");
		CommonFunctions.ClickElement(_driver, By.name("performSearch"));	

		Thread.sleep(4000);
		if(S2Table.getNumberOfRowsInTable(TableId,_driver)==1)
		{
			return true; 
		}
		else
		{
			return false;
		}

	}
	
	
	public void NavigateToEditAndApproveBatch() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/list");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Edit & Approve Invoice Batches - All", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEditAndApprove");
	}

	public void NavigateToEditAndApprovePaymentBatch()
			throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/PaymentBatch/list");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Edit & Approve Payment Batches - All", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEditAndApprove");
	}
	

	public void NavigateToJobBillingSetup() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/JobBillingSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name(Locators
				.getProperty(Locators.Job_Billing_Change_Order)));
		// assertEquals("Job Billing Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToJobBillingSetups");
	}

	public void NavigateToChangeOrderTypeInfo() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/ChangeOrderType/add");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Description)));
		assertEquals("Adding Change Order Type", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToChangeOrderTypeInfo");
	}
	
	public void NavigateToPreviewReport(String Id) throws Exception,
				IOException {
			sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://" + sSERVER + "/reports/prompt?report="+Id+"&amp;secure=false");			
			
}

	public void NavigateToJobTypeList() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/JobType/list");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Job Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToJobTypeList");
	}

	public void NavigateToJobChangeOrder(String sJobId)
			throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/ChangeOrder/listJob/" + sJobId);
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Change Orders", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToJobChangeOrder");
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
	
	public void NavigateToFindJob() throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Job/list");
		CommonFunctions.Wait(_driver, By.name(Locators
				.getProperty(Locators.Job_Billing_Commission_Basis_Type)));
		assertEquals("Job Billing Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToResetJobCostValue");
	}
	
	public void NavigateToInvoiceDetailPage(String InvoiceID)throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Invoice/detail/"+InvoiceID+"?tab=0");
		Thread.sleep(2000);
		//assertEquals("Invoice Transaction", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"navigatedtoJobDetail");
	}
	//http://crawfish/epace/company:c003/object/InvoiceBatch/consolidateInvoices/5704
	
	
	public void NavigateToConsolidatedInvoiceDetailPage(String BatchID)throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/consolidateInvoices/"+BatchID);
		Thread.sleep(2000);
		//assertEquals("Invoice Transaction", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToConsolidatedInvoiceDetailPage");
	}
	public void NavigateToPostedInvoiceDetailPage(String InvoiceID)throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Invoice/detail/"+InvoiceID+"?tab=0");
		Thread.sleep(2000);
		//assertEquals("Posted Invoice Transaction ", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostedInvoiceDetailPage");
	}
	
	
	public void AddNewInvoiceExtraType(String sDesc, String SalesCat,
			String sEntryCat) throws Exception {
		_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Add_New_Record)))
				.click();
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators
				.getProperty(Locators.JobBilling_Extra_Type_Sales_Category)),
				SalesCat);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators
				.getProperty(Locators.JobBilling_Extra_Type_Extra_Category)),
				sEntryCat);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(1000);
	}

	public void AddNewInvoiceExtraTypeInactive(String sDesc, String SalesCat,
			String sEntryCat) throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Add_New_Record)))
				.click();
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators
				.getProperty(Locators.JobBilling_Extra_Type_Sales_Category)),
				SalesCat);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators
				.getProperty(Locators.JobBilling_Extra_Type_Extra_Category)),
				sEntryCat);
		Thread.sleep(1000);
		PO.sSelectCheckBox(false,
				By.name(Locators.getProperty(Locators.Active_CheckBox)));
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(1000);
	}

	public void SelectJob(String sJob) throws Exception {
		DCPage DC = new DCPage(_driver);
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		_driver.findElement(
				By.xpath("//div/h4[contains(label,'Job')]/following-sibling::div[1]/div[2]"))
				.click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (_driver.switchTo().window(windowId).getTitle()
						.contains("Please select a Job")) {

					DC.SearchValue(sJob, "job");
					Thread.sleep(2000);
					_driver.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"))
							.click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				} else {
					// System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				}

			}
		}
	}

	public void EnterNewInvoiceDetails(String sJob, int sJobPartKey,
			String sPONum, String sInvoiceType, int sInvoiceBatch,
			int sSaleDistributionMethod, boolean DistributeTax,
			boolean PartiallyBill, boolean CloseJob, String sRelieve)
			throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		System.out.println("Select Job");
		SelectJob(sJob);
		Thread.sleep(2000);
		// CommonFunctions.selectDropdownByIndex(_driver,
		// By.name(Locators.getProperty(Locators.Job_Part_Key)), sJobPartKey);
		// Thread.sleep(1000);
		_driver.findElement(
				By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num)))
				.sendKeys(sPONum);
		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByText(_driver,
					By.name("invoiceBatch2"), sInvoiceType);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByText(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)),
					sInvoiceType);
			Thread.sleep(1000);
		}

		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				sSaleDistributionMethod);
		Thread.sleep(1000);
		// PO.sSelectCheckBox(DistributeTax,
		// By.name(Locators.getProperty(Locators.DistributeTaxBooleanCheckBox)));
		// PO.sSelectCheckBox(PartiallyBill,
		// By.name(Locators.getProperty(Locators.PartiallyBillBooleanCheckBox)));
		// PO.sSelectCheckBox(CloseJob,
		// By.name(Locators.getProperty(Locators.CloseJobBooleanCheckBox)));
		_driver.findElement(
				By.name(Locators.getProperty(Locators.PrecentWipToRelieve)))
				.clear();
		_driver.findElement(
				By.name(Locators.getProperty(Locators.PrecentWipToRelieve)))
				.sendKeys(sRelieve);
		DC.Add();

	}

	public void EnterInvoiceDetails(String sJob, int sJobPartKey,
			String sPONum, String sInvoiceType, int sInvoiceBatch,
			int sSaleDistributionMethod, boolean DistributeTax,
			boolean PartiallyBill, boolean CloseJob, String sRelieve)
			throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		System.out.println("Select Job");
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJob);

		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		System.out.println("Select Invoice Type");

		// CommonFunctions.selectDropdownByIndex(_driver,
		// By.name(Locators.getProperty(Locators.Job_Part_Key)), sJobPartKey);
		// Thread.sleep(1000);
		// _driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).sendKeys(sPONum);

		System.out.println("Select Invoice Extra Type");
		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByText(_driver,
					By.name("invoiceBatch2"), sInvoiceType);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByText(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)),
					sInvoiceType);
			Thread.sleep(1000);
		}

		System.out.println("Select Sale Distribution Method");
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				sSaleDistributionMethod);
		Thread.sleep(1000);

		System.out.println("Select DistributeTaxBooleanCheckBox");
		// PO.sSelectCheckBox(DistributeTax,
		// By.name(Locators.getProperty(Locators.DistributeTaxBooleanCheckBox)));
		System.out.println("Select PartiallyBillBooleanCheckBox");
		// PO.sSelectCheckBox(PartiallyBill,
		// By.name(Locators.getProperty(Locators.PartiallyBillBooleanCheckBox)));
		System.out.println("Select CloseJobBooleanCheckBox");
		// PO.sSelectCheckBox(CloseJob,
		// By.name(Locators.getProperty(Locators.CloseJobBooleanCheckBox)));
		System.out.println("Select PrecentWipToRelieve");
		_driver.findElement(
				By.name(Locators.getProperty(Locators.PrecentWipToRelieve)))
				.clear();
		_driver.findElement(
				By.name(Locators.getProperty(Locators.PrecentWipToRelieve)))
				.sendKeys(sRelieve);
		System.out.println("Select Add_button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);

	}

	public void AddChargeOrder(String sPart, String sType, String sDesc)
			throws Exception {
		_driver.findElement(
				By.xpath(Locators.getProperty(Locators.Add_New_Record)))
				.click();
		Thread.sleep(2000);
		CommonFunctions.selectDropdown(_driver, By.name("JobPartKey"), sPart);
		Thread.sleep(1000);
		CommonFunctions
				.selectDropdownByIndex(_driver, By.name("department"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("type"), sType);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sDesc);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
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

	
	public void AddLineItemToChangeOrder() throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.xpath("//a[text()='Line Items']")).click();
		Thread.sleep(2000);
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		_driver.findElement(
				By.xpath("//fieldset[@id='ChangeOrderLine_N10084_fieldset']/div[1]/div[1]/div[2]/a"))
				.click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (_driver.switchTo().window(windowId).getTitle()
						.contains("Adding Change Order Line")) {
					_driver.findElement(By.name("qty")).clear();
					_driver.findElement(By.name("qty")).sendKeys("30");
					_driver.findElement(By.name("unitCost")).clear();
					_driver.findElement(By.name("unitCost")).sendKeys("3");
					CommonFunctions.selectDropdown(_driver,
							By.name("salesCategory"), "1");
					Thread.sleep(1000);
					PO.sSelectCheckBox(true,
							By.name("lineBillFlagBooleanCheck"));
					_driver.findElement(
							By.xpath(Locators.getProperty(Locators.Add_button)))
							.click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				} else {
					// System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				}

			}
		}
	}

	public void AddNewInvoice(String sJobId) throws Exception,
			IOException {
		System.out.println("Navigate to enter new Invoice Page ");
		NavigateToEnterNewInvoicePage();
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByIndex(_driver,
					By.name("invoiceBatch2"), 1);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByIndex(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)), 1);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
	}

	public void AddNewInvoice(String sJobId, String BatchId) throws Exception, IOException
	{
		System.out.println("Navigate to enter new Invoice Page ");
		NavigateToEnterNewInvoicePage();
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByValue(_driver, By.name("invoiceBatch2"), BatchId);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.InvoiceType)), 1);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,By.name(Locators.getProperty(Locators.SaleDistributionMethod)),1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Error_Message))))
		{
			if (CommonFunctions.GetText(_driver, By.xpath(Locators.getProperty(Locators.WarningMessageAtTop))).equals("Must distribute tax when using job shipment zip sales tax basis: Invoice[distributeTax=false][distributeTax]"))
			{
				CommonFunctions.selectDropdown(_driver,By.name("taxDistributionMethod"), "1");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
			}
		}
		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))			
		{
			System.out.println("Invoice is Created successfully InvoiceNum");
		}
		else
		{
			Assert.fail("Failed to create the invoice");
		}
	}
	
	public void AddNewInvoice(String sJobId, String BatchId,String InvoiceType)
			throws Exception, IOException {
		System.out.println("Navigate to enter new Invoice Page ");
		NavigateToEnterNewInvoicePage();
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByValue(_driver,
					By.name("invoiceBatch2"), BatchId);
			Thread.sleep(1000);
		} 
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.InvoiceType)), InvoiceType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		
		if (Update())
		{   
		
			System.out.println("Invoice is Created successfully InvoiceNum");
		}
		else
		{
			Assert.fail("Failed to create the invoice");
		}
	}
	
	
	public Boolean  AddNewInvoiceFromInvoice(String sJobId, String BatchId)
			throws Exception, IOException {
		
		System.out.println("Navigate to  new Invoice Page from Invoice list ");
				
		CommonFunctions.ClickElement(_driver,By.xpath("//a[contains(text(),'Invoice (')]"));
		System.out.println("the  Invoice button is clicked");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add']"));
		
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByValue(_driver,
					By.name("invoiceBatch2"), BatchId);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByIndex(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)), 1);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		
		if (Update())
		{
			System.out.println("Invoice is Created successfully");
			return true;
		}
		else
		{
			return false;
		}
	}

	
	public void NavigateToInvoiceBatchDetail(String BatchId) throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/detail/"+BatchId+"?tab=0");
		Thread.sleep(2000);
		
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"navigatedtoJobDetail");
	}
	
	
	public void ApprovedInvoiceBatch(String Batchid) throws Exception, IOException
	{
		NavigateToInvoiceBatchDetail(Batchid);
		CommonFunctions.ClickElement(_driver, By.name("approvedBooleanCheck"));
		
		if(Update())
		{
			System.out.println("Invoice batch was successfully updated");
			
		}
		else
		{
			Assert.fail("Failed to approved the batch ");
		}
		
	}
	
	public void AddJobPart(String QuotePerM,String Description,String AddDescription) throws Exception, IOException
	{
		CommonFunctions.ClickElement(_driver, By.xpath(".//a[text()='Parts Info']"));
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[contains(text(),'Add New')]"));
		
		
		try {
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"),1);
			
			CommonFunctions.waitForPageLoaded(_driver);
		} catch (Exception e) {
			
		}
		
		
		
		
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[@accesskey='B']"));
		
		CommonFunctions.SendValue(_driver, By.name("quotePerM"), QuotePerM);
			
		CommonFunctions.SendValue(_driver, By.name("description_label"), Description);
	
		CommonFunctions.SendValue(_driver, By.name("additionalDescription"), AddDescription);
	
		Add();
		
		if (Update())
		{
			System.out.println("Job Part is added successfully ");
		}
		else
		{
			Assert.fail("Failed to Create job Part");
		}
		
	}

	public void AddJobPartQuantity(String QuotePrice,String Qtyshipped,String SalesCategory) throws Exception, IOException
	{
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[@accesskey='B']"));
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"),QuotePrice );
		try {
			CommonFunctions.SendValue(_driver, By.name("qtyShippedBilling"), Qtyshipped,"Qtyshipped");
		} catch (Exception e) {
			
		}
		
		CommonFunctions.selectDropdownByText(_driver, By.name("salesCategory"), SalesCategory);
		try {
			CommonFunctions.SendValue(_driver,By.name("qtyShippedBilling"), "1000","Qtyshipped");	
		} catch (Exception e) {
		
		}
		
		Thread.sleep(2000);
		if (Update())
		{
			System.out.println("Job Part is successfully updated with quantity enter");
		}
		else
		{
			Assert.fail("Failed to update quantity detailed in Job part");
		}
		
	}
	public void AddJobNewPartQuantity(String QuotePrice,String Qtyshipped,String SalesCategory) throws Exception, IOException
	{
		
		try {
			
	
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[@accesskey='B']"));
		Thread.sleep(2000);
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"),QuotePrice );		
		CommonFunctions.selectDropdownByText(_driver, By.name("salesCategory"), SalesCategory);		
		CommonFunctions.SendValue(_driver,By.name("qtyShippedBilling"), "1000","Qtyshipped");	
	
		
		Thread.sleep(2000);
		if (Update())
		{
			System.out.println("Job Part is successfully updated with quantity enter");
		}
		else
		{
			System.err.println("Failed To Add to Job Part");
		}
		
		} catch (Exception e) {
			System.err.println("Error While add New JobPart");
			e.printStackTrace();
		}
		
		if (!_driver.findElement(By.name("quotedPriceForcedFlagBooleanCheck")).isSelected())
		{
			CommonFunctions.ClickElement(_driver, By.name("quotedPriceForcedFlagBooleanCheck"));
			
		}
		if (Update())
		{
			System.err.println("Failed to Update quote Price Force enable");
		}
		
	}
	
	
	public void AddJobNewPartWithOrderAndShippedQuantity(String QuotePrice,String orderQuantity,String shippedQty,String SalesCategory) throws Exception, IOException
	{
		
		try {
			
	
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[@accesskey='B']"));
		Thread.sleep(2000);
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"),QuotePrice );		
		CommonFunctions.selectDropdownByText(_driver, By.name("salesCategory"), SalesCategory);		
		CommonFunctions.SendValue(_driver,By.name("qtyShippedBilling"), shippedQty,"Qtyshipped");	
	
		
		_driver.findElement(By.xpath(".//*[@name='qtyOrderedHeader']")).clear();
		_driver.findElement(By.xpath(".//*[@name='qtyOrderedHeader']")).sendKeys(orderQuantity);
		CommonFunctions.ClickElement(_driver, By.name("quotedPriceForcedFlagBooleanCheck"));
		
		
		Thread.sleep(2000);
		if (Update())
		{
			System.out.println("Job Part is successfully updated with quantity enter");
		}
		else
		{
			System.err.println("Failed To Add to Job Part");
		}
		
		} catch (Exception e) {
			System.err.println("Error While add New JobPart");
			e.printStackTrace();
		}
		
		if (!_driver.findElement(By.name("quotedPriceForcedFlagBooleanCheck")).isSelected())
		{
			CommonFunctions.ClickElement(_driver, By.name("quotedPriceForcedFlagBooleanCheck"));
			
		}
		if (Update())
		{
			System.err.println("Failed to Update quote Price Force enable");
		}
		
	}
	public void AddFinishedGoodJobPart(String QuotePrice,String Qtyshipped,String SalesCategory) throws Exception, IOException
	{
		CommonFunctions.ClickElement(_driver,By.xpath("//a[text()='Billing Information']"));
		CommonFunctions.SendValue(_driver, By.name("quotePerM"),QuotePrice );
		try {
			CommonFunctions.SendValue(_driver, By.name("qtyShippedBilling"), Qtyshipped,"Qtyshipped");
		} catch (Exception e) {
			
		}
		
		CommonFunctions.selectDropdownByText(_driver, By.name("salesCategory"), SalesCategory);
		
		
		Thread.sleep(2000);
		if (Update())
		{
			System.out.println("Job Part is successfully updated with quantity enter");
		}
		else
		{
			Assert.fail("Failed to update quantity detailed in Job part");
		}
		
	}
	public void AddJobPartItem(String FinalPrice) throws Exception, IOException
	{
		
		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[contains(text(),'Job Part Items')]"));
		Thread.sleep(5000);
		
		CommonFunctions.ClickElement(_driver, By.xpath(".//fieldset[contains(@id,'JobPartItem_')]//a[text()='Add New']/../../../div[1]//a[1]"));
		
		
		CommonFunctions.SwitchToWindow(_driver, "Adding Job Part Item");
		
		CommonFunctions.selectDropdownByIndex(_driver, By.name("quoteItemType"), 1);
		CommonFunctions.SendValue(_driver, By.name("finalPrice"), FinalPrice);
		
		Add();
		
		CommonFunctions.ReturnToOriginalWindow(_driver);
		
	}

	public boolean EnterContactDetails(String sFirstName, String sLastName,String VendorCountry,String VendorState) throws Exception
	{
		
			_driver.findElement(By.name("firstName")).sendKeys(sFirstName);
			_driver.findElement(By.name("lastName")).sendKeys(sLastName);
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line1))).sendKeys("45");
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line2))).sendKeys("WF");
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line3))).sendKeys("BNG");
			_driver.findElement(By.name("businessPhoneNumber")).sendKeys("01298567345");
			_driver.findElement(By.name("businessFaxNumber")).sendKeys("01298567345");
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_City))).sendKeys("BNG");
			_driver.findElement(By.name("email")).sendKeys("efiuser123@gmail.com");
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), VendorCountry);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_State)), VendorState);
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_ZipCode))).sendKeys("511571");
			
			_driver.findElement(By.xpath("//a[text()='Customer/Vendor Info']")).click();
			Thread.sleep(2000);
			CommonFunctions.selectDropdownByText(_driver, By.name("defaultCurrency"), "USD");
			Thread.sleep(1000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();

			Thread.sleep(5000);
			boolean sAddedFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
			return sAddedFlag;
	}

	public void AddJoPartInGrid() throws Exception 
	{
		CommonFunctions.ClickElement(_driver,By.xpath(".//a[text()='Parts Info']"));
		
		CommonFunctions.ClickElement(_driver,By.xpath(".//fieldset[ contains(@id,'JobPart_N')]/div/div[1]/div[2]//a"));
		
		
	}
	
	
	
	public void NavigateToAddContactPage() throws Exception, IOException
	{
			sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Contact/add");
			Thread.sleep(3000);
			System.out.println(" Navigate To add contact Page");
			assertEquals("Adding Contact",_driver.getTitle());
	}
	
	public void NavigateJobDetailPage(String sJobId) throws Exception, IOException
	{
	
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Job/detail/"+sJobId);		
		try {
			_driver.switchTo().alert().accept();
			CommonFunctions.ReturnToOriginalWindow(_driver);
			
		} catch (Exception e) {
		}
		
		CommonFunctions.waitForPageLoaded(_driver);

	}
	public void NavigateJobInvoiceList(String sJobId) throws Exception, IOException
	{
	
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Job/invoices/"+sJobId);		
		
		CommonFunctions.waitForPageLoaded(_driver);
		
		

	}
	public void AddNewInvoiceWithDesc(String sJobId, String BatchId,String sDesc)
			throws Exception, IOException {
		System.out.println("Navigate to enter new Invoice Page ");
		NavigateToEnterNewInvoicePage();
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByValue(_driver,
					By.name("invoiceBatch2"), BatchId);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByIndex(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)), 1);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
	}

	public void EnterInvoiceDetail(String sJob, int sJobPartKey, String sPONum,
			String sInvoiceType, int sSaleDistributionMethod,
			boolean DistributeTax, boolean PartiallyBill, boolean CloseJob,
			String sRelieve) throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		System.out.println("Select Job");
		SelectJob(sJob);
		Thread.sleep(2000);
		// CommonFunctions.selectDropdownByIndex(_driver,
		// By.name(Locators.getProperty(Locators.Job_Part_Key)), sJobPartKey);
		// Thread.sleep(1000);
		_driver.findElement(
				By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num)))
				.sendKeys(sPONum);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) {
			CommonFunctions.selectDropdownByText(_driver,
					By.name("invoiceBatch2"), sInvoiceType);
			Thread.sleep(1000);
		} else {
			CommonFunctions.selectDropdownByText(_driver,
					By.name(Locators.getProperty(Locators.InvoiceType)),
					sInvoiceType);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,
				By.name(Locators.getProperty(Locators.SaleDistributionMethod)),
				sSaleDistributionMethod);
		Thread.sleep(1000);
		DC.Add();

	}

	public String SelectContact(String sCust) throws Exception {
		DCPage DC = new DCPage(_driver);
		String sLastName = "";
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		_driver.findElement(
				By.xpath("//div[@id='leftSection']/fieldset/div/div[1]/div[1]/h4[contains(label,'Contact')]/following-sibling::div/div[2]"))
				.click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (_driver.switchTo().window(windowId).getTitle()
						.contains("Please select a Contact")) {

					DC.SearchValue(sCust, "customer");
					Thread.sleep(2000);
					sLastName = _driver
							.findElement(
									By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[3]/div"))
							.getText();
					sLastName = sLastName.trim();
					_driver.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div"))
							.click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				} else {
					// System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				}

			}
		}
		return sLastName;
	}

	public void AddBills(String sPO, String sBillNo, String sVendor)
			throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(
				By.xpath("//fieldset[@id='Bill_fieldset']/div[1]/div[1]/div[2]/a[contains(text(), 'Add New')]"))
				.click();
		Thread.sleep(5000);
		assertEquals("Adding Bill", _driver.getTitle());
		_driver.findElement(By.name("poNumberInput")).sendKeys(sPO);
		Thread.sleep(2000);
		_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		_driver.findElement(By.name("invoiceNumber")).sendKeys(sBillNo);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("terms"), 1);
		/*
		 * _driver.findElement(By.name("vendor")).sendKeys(sVendor);
		 * Thread.sleep(2000);
		 * _driver.findElement(By.name("dateDue")).sendKeys("t");
		 * Thread.sleep(2000); CommonFunctions.selectDropdownByIndex(_driver,
		 * By.name("terms"), 1);
		 */

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		boolean sAlert = PO.isAlertPresent();
		if (sAlert == true) {
			_driver.switchTo().alert().accept();
			_driver.navigate().refresh();
			Thread.sleep(2000);
			_driver.findElement(By.name("poNumberInput")).clear();
			_driver.findElement(By.name("poNumberInput")).sendKeys(sPO);
			_driver.findElement(By.name("poNumberInput")).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			_driver.findElement(By.name("invoiceNumber")).clear();
			_driver.findElement(By.name("invoiceNumber")).sendKeys(sBillNo);
			Thread.sleep(2000);
			CommonFunctions.selectDropdownByIndex(_driver, By.name("terms"), 1);
			_driver.findElement(
					By.xpath(Locators.getProperty(Locators.Add_button)))
					.click();
			Thread.sleep(2000);
		}
	}

	public void NavigateToPostBills() throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=BillBatch.post");
		CommonFunctions.Wait(_driver,
				By.xpath("//input[@value='Post Bill Batches']"));
		assertEquals("Post Bill Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostBills");
	}

	public void SearchValue(String sValue, String sLabel)
			throws Exception {
		_driver.findElement(
				By.name(Locators.getProperty(Locators.Search_TextField)))
				.clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Find)))
				.click();
		Thread.sleep(1000);
		CommonFunctions
				.selectDropdown(
						_driver,
						By.name(Locators.getProperty(Locators.Search_Dropdown)),
						sValue);
		Thread.sleep(1000);
		// _driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).clear();
		_driver.findElement(
				By.name(Locators.getProperty(Locators.Search_TextField)))
				.sendKeys(sLabel);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find)))
				.click();
		Thread.sleep(5000);
	}

	public String Date() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		// DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}

	
	
	
	public void Add() throws Exception, IOException {
		System.out.println(Date() + "Add");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		// NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver, "Add");

	}

	public void ApprovedPaymentBatch(String DescriptionValue,
			String PaymemtBatchID) throws Exception, IOException {

		NavigateToEditAndApprovePaymentBatch();

		
		CommonFunctions.selectDropdownByText(_driver,
				By.name(Locators.getProperty(Locators.Find_Jobs)), "All");
		Thread.sleep(1000);
		SearchValue("description",DescriptionValue);
		if (_driver.getTitle().equals("Batch")) {
			System.out.println("Navigated to Batch");
		} else {
			int rowCount = _driver.findElements(
					By.xpath("//table[@id='All']/tbody/tr")).size();
			System.out.println("rowCount is " + rowCount);
			for (int i = 1; i <= rowCount; i++) {

				String sDescription = _driver.findElement(
						By.xpath("//table[@id='All']/tbody/tr[" + i
								+ "]/td[8]/div")).getText();
				sDescription = sDescription.trim();
				System.out.println("sDescription is " + sDescription);
				if (sDescription.equals(DescriptionValue)) {
					_driver.findElement(
							By.xpath("//table[@id='All']/tbody/tr[" + i
									+ "]/td[2]/div/a/img")).click();
					Thread.sleep(3000);
					break;
				}
			}
		}
		if (!_driver.findElement(By.name("approvedBooleanCheck")).isSelected()) {
			_driver.findElement(By.name("approvedBooleanCheck")).click();
		}

		Update();

		
	}

	public void PostBills(String sDesc) throws Exception,
			IOException {
		NavigateToPostBills();
		System.out.println("Navigate to Single Batch");
		/*
		 * int sCount =
		 * _driver.findElements(By.xpath("//select[@name='batch']/option"
		 * )).size(); System.out.println("sCount is "+ sCount);
		 * CommonFunctions.selectDropdownByIndex(_driver, By.name("batch"),
		 * sCount-1); Thread.sleep(1000);
		 * 
		 * String sSelectedBatch = CommonFunctions.GetSelectedOption(_driver,
		 * By.name("batch")); System.out.println("sSelectedBatch is "+
		 * sSelectedBatch);
		 */
		CommonFunctions.selectDropdownByIndex(_driver, By.name("batch"), 0);
		Thread.sleep(1000);
		_driver.findElement(By.name("approvedOnlyBooleanCheck")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//input[@value='Post Bill Batches']"))
				.click();
		Thread.sleep(20000);

	}

	public void NavigateToAddBillPaymentBatch() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/BillPaymentBatch/add");
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Add BillPayment Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAddBillPaymentBatch");
	}

	public void NavigateToAddPaymentBatch() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/PaymentBatch/add");
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAddPaymentBatch");
	}

	public void NavigateToAddPaymentTransaction() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Payment/add");
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Payment Transaction", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAddPaymentTransaction");
	}

	public void NavigateToProcessVendorChecks() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=Bill.processChecks");
		CommonFunctions.Wait(_driver, By.name("postSingleBatch"));
		assertEquals("Process Vendor Checks", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToAddBillPaymentBatch");
	}


	public void NavigateToPostInvoiceBatch() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=InvoiceBatch.post");
		CommonFunctions.Wait(_driver, By.xpath("//input[@name='batchType' and position()=1]"));
		assertEquals("Post Invoice Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostInvoiceBatch");
				}
	
	public void PostApprovedBatch()throws Exception,IOException 	
	{
		NavigateToPostInvoiceBatch();
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@name='batchType' and position()=1]"));
		CommonFunctions.ClickElement(_driver, By.xpath(".//input[@value='Post Invoice Batches']"));
		
		Thread.sleep(10000);
		
	}
	public void ProcessApprovedBatch()throws Exception,IOException 	
	{
		String message;
		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Process / Post']"));
		CommonFunctions.waitForPageLoaded(_driver);
		
		try {
			Alert alert = _driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {

			message = null;
		}
		CommonFunctions.ClickElement(_driver, By.xpath(".//a[text()='Process']"));


		try {
			Alert alert = _driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {

			message = null;
		}
		System.out.println(message+"  Was displayed");
		CommonFunctions.SwitchToWindow(_driver, "Process Invoice Batch");	
		if(CommonFunctions.isElementPresent(_driver, By.name("date")))
		{
			CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Process Invoice Batch']"));
		Thread.sleep(15000);
		
		if(CommonFunctions.isTextPresent(_driver, "Invoice Batch: Processed"))
		{
			System.out.println("Successfully able to procees the invoice batch");
		}
		else
		{
			System.err.println("Failed to process the batch");
		}
		
		//CommonFunctions.CloseWindow(_driver, "Invoice Process Successful!");
		_driver.close();
		CommonFunctions.ReturnToOriginalWindow(_driver);
		
	}
	
	
	public void PostApprovedBatch1()throws Exception,IOException 	
	{
		String message;
		CommonFunctions.SetOriginalWindowHandle(_driver);
//		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Process / Post']"));	
		
		try {
			Alert alert = _driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {

			message = null;
		}
		if (CommonFunctions.isElementPresent(_driver, By.xpath(".//a[text()='Post']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(".//a[text()='Post']"));
			 
			try {
				Alert alert = _driver.switchTo().alert();
	
				message = alert.getText();
				alert.accept();
			} catch (Exception e) {
	
				message = null;
			}
			System.out.println(message+"  Was displayed");
			CommonFunctions.SwitchToWindow(_driver, "Post Invoice Batch");
			CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Post Invoice Batch']"));
			Thread.sleep(15000);
			
			if(CommonFunctions.isTextPresent(_driver, "Invoice Batch: Posting Completed"))
			{
				System.out.println("Successfully able to procees the invoice batch");
			}
			else
			{
				System.err.println("Failed to process the batch");
			}
			CommonFunctions.CloseWindow(_driver, "Invoice Post Successful!");
			CommonFunctions.ReturnToOriginalWindow(_driver);
		}
	}
	
	public void PostApproveBatch(String BatchID)throws Exception,IOException	
   {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/InvoiceBatch/detail/"+BatchID+"?tab=0");
		CommonFunctions.Wait(_driver, By.name("description"));
		assertEquals("Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"PostApproveBatch");
		
		CommonFunctions.ClickElement(_driver, By.name("approvedBooleanCheck"));
		
		if (! Update())
		{
			Assert.fail("Failed to Approve the batch");
		}
		
		PostApprovedBatch();
		
				
	}

	public void NavigateToPostVendorChecks() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=Bill.postChecks");
		CommonFunctions.Wait(_driver,
				By.xpath("//input[@value='Post Vendor Checks']"));
		assertEquals("Post Vendor Checks", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostVendorChecks");
	}

	public void SelectBills(String sVendor) throws Exception {
		int j = 0;

		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		_driver.findElement(
				By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[2]/input"))
				.click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (_driver.switchTo().window(windowId).getTitle()
						.contains("Select Bills for payment")) {

					_driver.findElement(By.name("beginningVendorId")).clear();
					_driver.findElement(By.name("beginningVendorId")).sendKeys(
							sVendor);
					_driver.findElement(By.name("endingVendorId")).clear();
					_driver.findElement(By.name("endingVendorId")).sendKeys(
							sVendor);
					_driver.findElement(By.name("dateDue")).clear();
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//input[@value='Search']"))
							.click();
					Thread.sleep(2000);

					int ColCount = _driver
							.findElements(
									By.xpath("//table[@id='postedFilteredBills']/thead/tr/th"))
							.size();
					ArrayList<String> ColumnNames = new ArrayList<String>();
					System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
							+ ColCount);
					for (int i = 3; i <= ColCount; i++) {
						String sCName = _driver
								.findElement(
										By.xpath("//table[@id='postedFilteredBills']/thead/tr/th["
												+ i + "]/a/span")).getText();
						sCName = sCName.trim();
						System.out.println("sCName is " + sCName);
						j = i;
						if (sCName.equals("Pay"))
							break;
					}

					_driver.findElement(
							By.xpath("//table[@id='postedFilteredBills']/tbody/tr[1]/td["
									+ j + "]/input[2]")).click();
					_driver.findElement(
							By.xpath("//input[@value='Add Bill Payments']"))
							.click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				} else {
					// System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				}

			}
		}
	}

	public void NavigateToPostChecks() throws Exception, IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/process/run?key=Bill.postChecks");
		CommonFunctions.Wait(_driver,
				By.xpath("//input[@value='Post Vendor Checks']"));
		assertEquals("Post Vendor Checks", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToPostChecks");
	}

	public void PostChecks() throws Exception, IOException {
		NavigateToPostVendorChecks();
		System.out.println("Post Valid Checks");

	}

	public ArrayList[] POCreation(int sCount, String sDesc, String[] sVendor,
			String sInv, boolean[] sReceive) throws Exception {
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		ArrayList PONumbers = new ArrayList();
		ArrayList sPrice = new ArrayList();

		for (int i = 0; i < sCount; i++) {

			PO.NavigateToEnterPONumber();
			System.out.println("Add NEW PO");
			String sPON = PO.UniqueNum();
			PO.EnterPONumberDetails(sDesc, sPON, sVendor[i], "", "Open",
					"James Bond-");
			PO.ADDPO();
			boolean sobjectadded = CommonFunctions.ElementPresent(_driver,
					By.xpath(Locators.getProperty(Locators.Object_added_text)));
			System.out.println("PO Order got added " + sobjectadded);
			// ------------------Add new LineItem------------------//
			System.out.println("Add new LineItem");
			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			_driver.findElement(
					By.xpath(Locators.getProperty(Locators.Add_New_Record)))
					.click();
			Thread.sleep(6000);
			PO.AddLineItemType(sInv, "Inventory", "3", "3",
					"00001-Epace Default Normal Account", true);
			Thread.sleep(5000);
			_driver.switchTo().window(originalHandle).getTitle()
					.equals(sWindowTitle);
			if (sReceive[i] == true) {
				PO.NavigateToPOReceive();
				System.out.println("Add Received details");
				originalHandle = _driver.getWindowHandle();
				sWindowTitle = _driver.getTitle();
				String sVersion = DC.FetchVersion();
				if (_driver
						.findElements(
								By.xpath(Locators
										.getProperty(Locators.PO_Edit_Line_Item_In_Receive)))
						.size() > 0) {
					_driver.findElement(
							By.xpath(Locators
									.getProperty(Locators.PO_Edit_Line_Item_In_Receive)))
							.click();
				} else {
					_driver.findElement(
							By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr[1]/td[2]/div/a/img"))
							.click();
				}
				Thread.sleep(6000);
				PO.AddReceiveDetailsForLineItem("Received Qty 30", "5", "30");
				Thread.sleep(3000);
				_driver.switchTo().window(originalHandle).getTitle()
						.equals(sWindowTitle);
				_driver.findElement(
						By.xpath(Locators
								.getProperty(Locators.Receive_PO_Update_And_Receive_Button)))
						.click();
				Thread.sleep(3000);
				_driver.findElement(By.xpath("//a[text()='Return to Detail']"))
						.click();
				Thread.sleep(5000);
			}
			DC.Update();
			Thread.sleep(3000);
			PONumbers.add(sPON);
			int j = 0;
			int ColCount = 0;
			ArrayList<String> ColumnNames = new ArrayList<String>();
			if (_driver
					.findElements(
							By.xpath("//table[@id='PurchaseOrderLinePaper']/thead/tr/th"))
					.size() > 0) {
				ColCount = _driver
						.findElements(
								By.xpath("//table[@id='PurchaseOrderLinePaper']/thead/tr/th"))
						.size();

				System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
						+ ColCount);
				for (int k = 3; k <= ColCount; k++) {
					String sCName = _driver
							.findElement(
									By.xpath("//table[@id='PurchaseOrderLinePaper']/thead/tr/th["
											+ k + "]/a/span")).getText();
					sCName = sCName.trim();
					System.out.println("sCName is " + sCName);
					j = k;
					if (sCName.equals("Extended Price"))
						break;
				}
			} else {
				ColCount = _driver
						.findElements(
								By.xpath("//table[@id='PurchaseOrderLine']/thead/tr/th"))
						.size();
				System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
						+ ColCount);
				for (int k = 3; k <= ColCount; k++) {
					String sCName = _driver
							.findElement(
									By.xpath("//table[@id='PurchaseOrderLine']/thead/tr/th["
											+ k + "]/a/span")).getText();
					sCName = sCName.trim();
					System.out.println("sCName is " + sCName);
					j = k;
					if (sCName.equals("Extended Price"))
						break;
				}
			}
			String sPriceValue = "";
			if (_driver
					.findElements(
							By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr[1]/td["
									+ j + "]/div")).size() > 0) {
				sPriceValue = _driver
						.findElement(
								By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr[1]/td["
										+ j + "]/div")).getText();
			} else {
				sPriceValue = _driver
						.findElement(
								By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[1]/td["
										+ j + "]/div")).getText();
			}

			sPriceValue = sPriceValue.trim();
			sPrice.add(sPriceValue);
			dbConnection.UpdateFunction("JobBilling", "JobBilling",
					"Prerequisites_" + i, "PO", sPON);
		}

		System.out.println("PONumbers are " + PONumbers);
		System.out.println("sPrice are " + sPrice);

		return new ArrayList[] { PONumbers, sPrice };
	}

	public void SelectPOReceipt(String sPO) throws Exception {
		DCPage DC = new DCPage(_driver);
		int j = 0;
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		_driver.findElement(By.xpath("//a[text()='Select PO Receipts']"))
				.click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (_driver
						.switchTo()
						.window(windowId)
						.getTitle()
						.contains(
								"Select Purchase Order Receipts to add to Bill.")) {

					DC.SearchValue(sPO, "poNumber");
					int ColCount = _driver
							.findElements(
									By.xpath("//table[@id='appbox_implicit']/thead/tr/th"))
							.size();
					ArrayList<String> ColumnNames = new ArrayList<String>();
					System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
							+ ColCount);
					for (int i = 3; i <= ColCount; i++) {
						String sCName = _driver
								.findElement(
										By.xpath("//table[@id='appbox_implicit']/thead/tr/th["
												+ i + "]/a/span")).getText();
						sCName = sCName.trim();
						System.out.println("sCName is " + sCName);
						j = i;
						if (sCName.equals("Select"))
							break;
					}

					_driver.findElement(
							By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td["
									+ j + "]/input[2]")).click();
					Thread.sleep(1000);
					_driver.findElement(
							By.xpath("//input[@value='Add Selected Receipts']"))
							.click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				} else {
					// System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle()
							.equals(sWindowTitle);

				}

			}
		}
	}

	public ArrayList CreateBillBatch(int sBillBatchCount, int sBillCount,
			String[] sDesc, ArrayList sPO, String[] sBillNo, String[] sVendor)
			throws Exception {

		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		int j = 0, k = 0;
		ArrayList sAmount = new ArrayList();
		// -------------------Create Bill Batch------------------------//
		for (int b = 0; b < sBillBatchCount; b++) {
			System.out
					.println("Navigate to Accounting->Payables->Enter bills ->Enter new batch");
			NavigateToAP_EnterNewBatch();

//			int sCount = _driver.findElements(
//					By.xpath("//select[@name='glAccountingPeriod']/option"))
//					.size();
//			System.out.println("sCount is " + sCount);
//			CommonFunctions.selectDropdownByIndex(_driver,
//					By.name("glAccountingPeriod"), sCount - 1);
//			Thread.sleep(1000);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			String glBatch = df.format(new Date());
			CommonFunctions.selectDropdownByText(_driver, By.name("glAccountingPeriod"), glBatch);

			_driver.findElement(
					By.name(Locators.getProperty(Locators.Description)))
					.sendKeys(sDesc[b]);
			Thread.sleep(1000);

			DC.Add();
			Thread.sleep(3000);
			boolean sObjectAdded = CommonFunctions.isElementPresent(_driver,
					By.xpath(Locators.getProperty(Locators.Object_added_text)));
			if (sObjectAdded == true) {
				System.out.println("New Batch " + sDesc[b] + " got created");
				// -------------------Add bills to bill
				// batch------------------------//
				for (int c = 0; c < sBillCount; c++) {
					String sGetURL = _driver.getCurrentUrl();
					System.out.println("sGetURL is " + sGetURL);
					Thread.sleep(3000);
					System.out.println("Add One Bills");

					String sPOValue = String.valueOf(sPO.get(c));
					sPOValue = sPOValue.toString();
					System.out.println("sPOValue is " + sPOValue);
					System.out.println("sBillNo is " + sBillNo[c]);
					AddBills(sPOValue, sBillNo[c], sVendor[c]);

					Thread.sleep(3000);
					assertEquals("Bill Transaction " + sBillNo[c],
							_driver.getTitle());
					sObjectAdded = CommonFunctions.isElementPresent(_driver, By
							.xpath(Locators
									.getProperty(Locators.Object_added_text)));

					System.out.println("Select PO Receipt");
					SelectPOReceipt(sPOValue);
					Thread.sleep(3000);

					int ColCount = _driver.findElements(
							By.xpath("//table[@id='BillLine']/thead/tr/th"))
							.size();
					ArrayList<String> ColumnNames = new ArrayList<String>();
					System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
							+ ColCount);
					for (int i = 4; i <= ColCount; i++) {
						String sCName = _driver.findElement(
								By.xpath("//table[@id='BillLine']/thead/tr/th["
										+ i + "]/a/span")).getText();
						sCName = sCName.trim();
						System.out.println("sCName is " + sCName);
						j = i;
						if (sCName.equals("Amount"))
							break;
					}

					/*
					 * for(int i =4;i<=ColCount;i++) { String sCName =
					 * _driver.findElement
					 * (By.xpath("//table[@id='BillLine']/thead/tr/th["
					 * +i+"]/a/span")).getText(); sCName = sCName.trim();
					 * System.out.println("sCName is "+sCName); k=i;
					 * if(sCName.equals("Tax Code")) break; }
					 */
					/*
					 * _driver.findElement(By.xpath(
					 * "//table[@id='BillLine']/tbody/tr[1]/td[3]/input"
					 * )).clear(); _driver.findElement(By.xpath(
					 * "//table[@id='BillLine']/tbody/tr[1]/td[3]/input"
					 * )).sendKeys("00001");
					 * 
					 * _driver.findElement(By.xpath(
					 * "//table[@id='BillLine']/tbody/tr[1]/td["
					 * +j+"]/input")).clear(); _driver.findElement(By.xpath(
					 * "//table[@id='BillLine']/tbody/tr[1]/td["
					 * +j+"]/input")).sendKeys("10");
					 * _driver.findElement(By.xpath
					 * ("//table[@id='BillLine']/tbody/tr[1]/td["
					 * +k+"]/input")).clear(); _driver.findElement(By.xpath(
					 * "//table[@id='BillLine']/tbody/tr[1]/td["
					 * +k+"]/input")).sendKeys("AL0000");
					 */

					_driver.findElement(
							By.xpath("//table[@id='BillLine']/tbody/tr[1]/td["
									+ j + "]/input")).clear();
					_driver.findElement(
							By.xpath("//table[@id='BillLine']/tbody/tr[1]/td["
									+ j + "]/input")).sendKeys("10");

					String sAmountValue = _driver.findElement(
							By.xpath("//table[@id='BillLine']/tbody/tr[1]/td["
									+ j + "]/input")).getAttribute("value");

					sAmount.add(sAmountValue);

					boolean sAlert = PO.isAlertPresent();
					if (sAlert == true) {
						_driver.switchTo().alert().accept();
					}
					_driver.findElement(
							By.xpath(Locators.getProperty(Locators.Update)))
							.click();
					Thread.sleep(2000);
					sAlert = PO.isAlertPresent();
					if (sAlert == true) {
						_driver.switchTo().alert().accept();
					}

					Thread.sleep(3000);
					_driver.get(sGetURL);
					Thread.sleep(5000);
				}
				// -------------------Add bills to bill
				// batch------------------------//
				_driver.findElement(By.name("approvedBooleanCheck")).click();
				DC.Update();
				Thread.sleep(3000);
			}
		}
		return sAmount;
	}

	public void AddPayBillBatch(String sBillDesc) throws IOException,
			Exception {
		NavigateToAddBillPaymentBatch();

//		int sCount = _driver.findElements(
//				By.xpath("//select[@name='glAccountingPeriod']/option")).size();
//		System.out.println("sCount is " + sCount);
//		CommonFunctions.selectDropdownByIndex(_driver,
//				By.name("glAccountingPeriod"), sCount - 1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String glBatch = df.format(new Date());
		CommonFunctions.selectDropdownByText(_driver, By.name("glAccountingPeriod"), glBatch);
		
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sBillDesc);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver, "Add");
		boolean sObjectAdded1 = CommonFunctions.isElementPresent(_driver,
				By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}

	public void AddPaymentBatch(String sBillDesc, String GLPeriod)
			throws IOException, Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String glBatch = df.format(new Date());
		
		NavigateToAddPaymentBatch();

//		int sCount = _driver.findElements(
//				By.xpath("//select[@name='glAccountingPeriod']/option")).size();
//		System.out.println("sCount is " + sCount);
//		CommonFunctions.selectDropdownByIndex(_driver, By.name("glAccountingPeriod"), sCount-1);
		CommonFunctions.selectDropdownByText(_driver, By.name("glAccountingPeriod"), glBatch);
	/*	CommonFunctions.selectDropdownByText(_driver,
				By.name("glAccountingPeriod"), GLPeriod);*/
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description)))
				.sendKeys(sBillDesc);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button)))
				.click();
		Thread.sleep(2000);
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver, "Add");
		boolean sObjectAdded1 = CommonFunctions.isElementPresent(_driver,
				By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}

	public void AddPayment(String PaymentBatch, String Customer, String JobId,
			String EntryType) throws Exception, IOException {		
		NavigateToAddPaymentTransaction();

		CommonFunctions.selectDropdownByValue(_driver, By.name("entryType"),
				EntryType);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByValue(_driver,
				By.name("paymentBatch2"), PaymentBatch);
		_driver.findElement(By.name("customer")).sendKeys(Customer);
		_driver.findElement(By.name("currencyAwareAmount")).sendKeys("2000");
		// _driver.findElement(By.name("customer")).sendKeys(Keys.RETURN);

		Thread.sleep(5000);
//		CommonFunctions.selectDropdownByValue(_driver, By.xpath("//input[contains(@name,'job')]"), JobId);	
		pickJob(JobId);
		
		Add();
		
/*		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver, "Add");
		boolean sObjectAdded1 = CommonFunctions.isElementPresent(_driver,
				By.xpath(Locators.getProperty(Locators.Object_added_text)));

		if (sObjectAdded1 = false) {
			Assert.fail("Adding of payment failed ");
		}*/
	}
	
	public void pickJob(String JobID) throws Exception
	{
		APPage AP = new APPage(_driver);
		DCPage DC = new DCPage(_driver);
		
		String sOriginalWindow = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//label[text()='Job']/../following-sibling::div/div[@class='viewPick']"));
		Thread.sleep(2000);
		AP.SwitchToWindow("Please select a Job");
		CommonFunctions.waitForPageLoaded(_driver);
		DC.SearchValue(JobID, "job");
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/a/div"));
		Thread.sleep(1000);
		_driver.switchTo().window(sOriginalWindow);
		Thread.sleep(1000);
	}

	public void PostPaymentBatch(String BatchID) throws Exception,
			IOException {

		NavigateToPostCustomerPayment();
		CommonFunctions.selectDropdownByValue(_driver,
				By.name("postSingleBatch"), BatchID);

		_driver.findElement(
				By.xpath("//input[@name='updateForm' and @value='Post Customer Payments']"))
				.click();
		System.out.println("****Clicking the Update button****");
		Thread.sleep(10000);
		if (CommonFunctions.isTextPresent(_driver, "1 Payment Batch: Posting Completed"))
		{
			System.out.println("Payment successfully posted");
		}
		else
		{
			System.err.println("Failed to post the payment");
			Assert.fail("Failed to post the payment");
		}
	}

	public void SelectBill(String[] sVendor, String[] sGroup)
			throws Exception {
		int j = 0, p = 0;
		for (int v = 0; v < sVendor.length; v++) {
			String originalHandle = _driver.getWindowHandle();
			System.out.println(originalHandle);
			String sWindowTitle = _driver.getTitle();
			_driver.findElement(
					By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[2]/input[@value='Select Bills']"))
					.click();
			Thread.sleep(5000);
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty()) {
				for (String windowId : availableWindows) {
					if (_driver.switchTo().window(windowId).getTitle()
							.contains("Select Bills for payment")) {

						_driver.findElement(By.name("beginningVendorId"))
								.clear();
						_driver.findElement(By.name("beginningVendorId"))
								.sendKeys(sVendor[v]);
						_driver.findElement(By.name("endingVendorId")).clear();
						_driver.findElement(By.name("endingVendorId"))
								.sendKeys(sVendor[v]);
						_driver.findElement(By.name("dateDue")).clear();
						Thread.sleep(2000);

						_driver.findElement(
								By.xpath("//input[@value='Search']")).click();
						Thread.sleep(5000);

						String sTotalRecords = _driver
								.findElement(
										By.xpath("//fieldset[@id='postedFilteredBills_fieldset']/div[1]/div[1]/div[1]/strong"))
								.getText();
						sTotalRecords = sTotalRecords.trim();
						System.out.println("Total Recors are " + sTotalRecords);
						int sRecords = Integer.parseInt(sTotalRecords);
						int ColCount = _driver
								.findElements(
										By.xpath("//table[@id='postedFilteredBills']/thead/tr/th"))
								.size();
						ArrayList<String> ColumnNames = new ArrayList<String>();
						System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
								+ ColCount);
						for (int i = 3; i <= ColCount; i++) {
							String sCName = _driver
									.findElement(
											By.xpath("//table[@id='postedFilteredBills']/thead/tr/th["
													+ i + "]/a/span"))
									.getText();
							sCName = sCName.trim();
							System.out.println("sCName is " + sCName);
							j = i;
							if (sCName.equals("Group Check"))
								break;
						}

						int ColCount1 = _driver
								.findElements(
										By.xpath("//table[@id='postedFilteredBills']/thead/tr/th"))
								.size();
						ArrayList<String> ColumnNames1 = new ArrayList<String>();
						System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "
								+ ColCount);
						for (int l = 3; l <= ColCount1; l++) {
							String sCName = _driver
									.findElement(
											By.xpath("//table[@id='postedFilteredBills']/thead/tr/th["
													+ l + "]/a/span"))
									.getText();
							sCName = sCName.trim();
							System.out.println("sCName is " + sCName);
							p = l;
							if (sCName.equals("Pay"))
								break;
						}
						System.out.println("p is " + p);
						int sRecord = _driver
								.findElements(
										By.xpath("//table[@id='postedFilteredBills']/tbody/tr"))
								.size();
						System.out.println("sRecord is " + sRecord);
						for (int k = 2; k <= sRecord; k++) {
							// int m= k-1;
							// System.out.println("m is "+m);
							// CommonFunctions.selectDropdownByText(_driver,
							// By.xpath("//table[@id='postedFilteredBills']/tbody/tr["+k+"]/td["+j+"]/select"),
							// sGroup[m]);
							// Thread.sleep(1000);
							System.out.println("Select Bill");
							_driver.findElement(
									By.xpath("//table[@id='postedFilteredBills']/tbody/tr["
											+ k + "]/td[" + p + "]/input[2]"))
									.click();
						}

						_driver.findElement(
								By.xpath("//input[@value='Add Bill Payments']"))
								.click();
						Thread.sleep(5000);

						_driver.switchTo().window(originalHandle).getTitle()
								.equals(sWindowTitle);

					} else {
						// System.out.println("Not able to find window");
						_driver.switchTo().window(originalHandle).getTitle()
								.equals(sWindowTitle);

					}

				}
			}
		}
	}

	public void SelectGroup(String[] sGroup) throws Exception {
		int j = 0;
		int sRowCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		for (int i = 2; i < sRowCount; i++) {
			j = i - 2;
			CommonFunctions.selectDropdownByText(
					_driver,
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + i
							+ "]/td[4]/select"), sGroup[j]);
			Thread.sleep(1000);
		}
	}

	public void SelectGroup1(String[] sGroup) throws Exception {
		int j = 0;
		int sRowCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		for (int i = 2; i <= 6; i++) {
			j = i - 2;
			CommonFunctions.selectDropdownByText(
					_driver,
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + i
							+ "]/td[4]/select"), sGroup[j]);
			Thread.sleep(1000);
		}
	}

	public void PostCheckAndProcessCheck(String sSingleBillBatch,
			String sCheckNo) throws Exception, IOException {
		int i = 0, j = 0;

		NavigateToPostVendorChecks();
		_driver.findElement(By.xpath("//input[@value='Post Vendor Checks']"))
				.click();
		Thread.sleep(10000);

		String sTitle = _driver.getTitle();
		while (sTitle.contains("Bill Post Checks in process...") && j < 2) {
			Thread.sleep(5000);
			j++;
		}
		Thread.sleep(5000);
		// assertEquals("Bill Post Checks Successful!", _driver.getTitle());

		NavigateToProcessVendorChecks();
		int sCount = _driver.findElements(
				By.xpath("//select[@name='postSingleBatch']/option")).size();
		System.out.println("sCount is " + sCount);
		CommonFunctions.selectDropdownByText(_driver,
				By.name("postSingleBatch"), sSingleBillBatch);
		Thread.sleep(1000);

		String sSelectedBatch = CommonFunctions.GetSelectedOption(_driver,
				By.name("postSingleBatch"));
		System.out.println("sSelectedBatch is " + sSelectedBatch);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("bankAccount"),
				1);
		Thread.sleep(1000);

		_driver.findElement(By.name("checkNumber")).clear();
		_driver.findElement(By.name("checkNumber")).sendKeys(sCheckNo);

		_driver.findElement(By.xpath("//input[@value='Process Vendor Checks']"))
				.click();
		Thread.sleep(15000);
		String sTitle1 = _driver.getTitle();
		while (sTitle1.contains("Bill Process Checks in process...") && i < 2) {
			Thread.sleep(5000);
			i++;
		}

		assertEquals("Bill Process Checks Successful!", _driver.getTitle());

	}

	public void NavigateToEditApprovePage() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/BillPaymentBatch/listApprove");
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("BillPayment Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEditApprovePage");
	}

	public void NavigateToJobArchivePage() throws Exception,
			IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/JobArchive/list");
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Job History - Job History", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToEditApprovePage");
	}

	public void EditBillBatch(String sBillBatch) throws Exception {
		DCPage DC = new DCPage(_driver);
		NavigateToEditApprovePage();
		DC.Search(sBillBatch, "description");
		Thread.sleep(5000);
		if (_driver.getTitle().equals("BillPayment Batch")) {
			System.out.println("Navigated to BillPayment Batch");
		} else {
			_driver.findElement(
					By.xpath("//table[@id='billPaymentBatch']/tbody/tr[1]/td[2]/div/a/img"))
					.click();
			Thread.sleep(5000);
		}

	}

	public boolean VerifySelectedBills(int sCountVendor, int CountChecks,
			String[] sVendor, String[] sGroupChk, ArrayList sAmount,
			String[] sBillNo, String sChkNo, int sVRC, int sCRC) {
		boolean sFlag = false;
		int j = 0, k = 0;
		int ColCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/thead/tr[1]/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = " + ColCount);
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			j = i;
			if (sCName.equals("Original Amt"))
				break;
		}
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			k = i;
			if (sCName.equals("Bill Number"))
				break;
		}

		int sVendorrowcount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		int sCheckrowcount = _driver.findElements(
				By.xpath("//table[@id='BillCheck']/tbody/tr")).size();
		System.out.println("sVendorrowcount is " + sVendorrowcount
				+ " sVRC is " + sVRC);
		System.out.println("sCheckrowcount is " + sCheckrowcount + " sCRC is "
				+ sCRC);

		String sTotalVendors = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalVendors = sTotalVendors.trim();

		int sVendorRecords = Integer.parseInt(sTotalVendors);
		System.out.println("sVendorRecords is " + sVendorRecords
				+ " sCountVendor is " + sCountVendor);

		String sTotalChecks = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalChecks = sTotalChecks.trim();
		int sTotalCheck = Integer.parseInt(sTotalChecks);
		System.out.println("sTotalCheck is " + sTotalCheck + " CountChecks is "
				+ CountChecks);

		String sCheckNum = _driver.findElement(
				By.xpath("//table[@id='BillCheck']/tbody/tr[2]/th[2]/div[2]"))
				.getText();
		sCheckNum = sCheckNum.trim();
		System.out
				.println("sCheckNum is " + sCheckNum + " sChkNo is " + sChkNo);

		String sVendorName = _driver
				.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[1]/th[1]/div[2]/a"))
				.getText();
		sVendorName = sVendorName.trim();
		System.out.println("sVendorName is " + sVendorName + " sVendor is "
				+ sVendor[0] + " - V" + sVendor[0]);
		for (int a = 1; a <= sVendorRecords; a++) {
			int b = a + 1;
			int c = a - 1;
			String sGroupCheck = CommonFunctions.GetSelectedOption(
					_driver,
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[4]/select"));
			sGroupCheck = sGroupCheck.trim();
			System.out.println("sGroupCheck is " + sGroupCheck
					+ " sGroupChk is " + sGroupChk[c]);

			String sOriginalAmt = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[" + j + "]/div")).getText();
			sOriginalAmt = sOriginalAmt.trim();
			sOriginalAmt = sOriginalAmt.replace("$", "");
			sOriginalAmt = sOriginalAmt.substring(0,
					Math.min(sOriginalAmt.length(), 2));
			System.out.println("sOriginalAmt is " + sOriginalAmt);
			System.out.println("sOriginalAmt is " + sOriginalAmt
					+ " sOriAmt is " + sAmount.get(c));

			String sBillNum = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[" + k + "]/div")).getText();
			sBillNum = sBillNum.trim();
			System.out.println("sBillNum is " + sBillNum + " sBillNo is "
					+ sBillNo[c]);

			if (sVendorrowcount == sVRC && sCheckrowcount == sCRC
					&& sVendorRecords == sCountVendor
					&& sTotalCheck == CountChecks
					&& sVendorName.equals(sVendor[c] + " - V" + sVendor[c])
					&& sGroupCheck.equals(sGroupChk[c])
					&& sOriginalAmt.equals(sAmount.get(c))
					&& sBillNum.equals(sBillNo[c]) && sCheckNum.equals(sChkNo)) {

				sFlag = true;
			} else {
				Assert.fail("Failed");
			}

		}

		return sFlag;
	}

	public boolean VerifySelectedBills1(int sCountVendor, int CountChecks,
			String[] sVendor, String[] sGroupChk, ArrayList sAmount,
			String[] sBillNo, String[] sBillNo1, String[] sChkNo, int sVRC,
			int sCRC, int[] sFlagChk, String[] sChkBillNo, int[] sFlagChk1) {
		boolean sFlag = false, sFlag1 = false, sFlag2 = false;
		int j = 0, k = 0;
		int v = 0, w = 0;
		int ColCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/thead/tr[1]/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = " + ColCount);
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			j = i;
			if (sCName.equals("Original Amt"))
				break;
		}
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			k = i;
			if (sCName.equals("Bill Number"))
				break;
		}

		int sVendorrowcount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		int sCheckrowcount = _driver.findElements(
				By.xpath("//table[@id='BillCheck']/tbody/tr")).size();
		System.out.println("sVendorrowcount is " + sVendorrowcount
				+ " sVRC is " + sVRC);
		System.out.println("sCheckrowcount is " + sCheckrowcount + " sCRC is "
				+ sCRC);

		String sTotalVendors = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalVendors = sTotalVendors.trim();

		int sVendorRecords = Integer.parseInt(sTotalVendors);
		System.out.println("sVendorRecords is " + sVendorRecords
				+ " sCountVendor is " + sCountVendor);

		String sTotalChecks = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalChecks = sTotalChecks.trim();
		int sTotalCheck = Integer.parseInt(sTotalChecks);
		System.out.println("sTotalCheck is " + sTotalCheck + " CountChecks is "
				+ CountChecks);

		String sVendorName = _driver
				.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[1]/th[1]/div[2]/a"))
				.getText();
		sVendorName = sVendorName.trim();
		System.out.println("sVendorName is " + sVendorName + " sVendor is "
				+ sVendor[0] + " - V" + sVendor[0]);

		// --------------------Verify vendor
		// Bills-------------------------------//

		for (int a = 1; a <= sVendorRecords; a++) {
			int b = a + 1;
			int c = a - 1;
			String sGroupCheck = CommonFunctions.GetSelectedOption(
					_driver,
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[4]/select"));
			sGroupCheck = sGroupCheck.trim();
			System.out.println("sGroupCheck is " + sGroupCheck
					+ " sGroupChk is " + sGroupChk[c]);

			String sOriginalAmt = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[" + j + "]/div")).getText();
			sOriginalAmt = sOriginalAmt.trim();
			sOriginalAmt = sOriginalAmt.replace("$", "");
			sOriginalAmt = sOriginalAmt.substring(0,
					Math.min(sOriginalAmt.length(), 2));
			System.out.println("sOriginalAmt is " + sOriginalAmt);
			System.out.println("sOriginalAmt is " + sOriginalAmt
					+ " sOriAmt is " + sAmount.get(c));

			String sBillNum = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
							+ "]/td[" + k + "]/div")).getText();
			sBillNum = sBillNum.trim();
			System.out.println("sBillNum is " + sBillNum + " sBillNo is "
					+ sBillNo[c]);

			if (sVendorrowcount == sVRC && sCheckrowcount == sCRC
					&& sVendorRecords == sCountVendor
					&& sTotalCheck == CountChecks
					&& sVendorName.equals(sVendor[c] + " - V" + sVendor[c])
					&& sGroupCheck.equals(sGroupChk[c])
					&& sOriginalAmt.equals(sAmount.get(c))
					&& sBillNum.equals(sBillNo[c])) {

				sFlag = true;
			} else {
				Assert.fail("Failed");
			}

		}
		// --------------------Verify Check Num-------------------------------//
		for (int b = 2; b <= sCheckrowcount; b++) {
			int g = b - 2;
			if (sFlagChk[g] == 0) {

				String sBNum = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/th[2]/div[2]")).getText();
				sBNum = sBNum.trim();
				System.out.println("sBNum is " + sBNum + " sChkNo is "
						+ sChkNo[v]);

				if (sBNum.equals(sChkNo[v])) {
					sFlag1 = true;
				} else {
					Assert.fail("Check no is not same " + sBNum + " and "
							+ sChkNo[v]);
				}
				v++;
			}

		}

		// --------------------Verify Bills in
		// check-------------------------------//
		for (int b = 3; b <= sCheckrowcount; b++) {
			int g = b - 3;
			if (sFlagChk1[g] == 1) {
				System.out.println("w is " + w);
				String sBillNumInCheck = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/td[6]/div[1]/a")).getText();
				sBillNumInCheck = sBillNumInCheck.trim();
				System.out.println("sBillNumInCheck is " + sBillNumInCheck
						+ " sBillNo is " + sVendor[0] + "-" + sBillNo[w]);
				if (sBillNumInCheck.equals(sVendor[0] + "-" + sBillNo[w])) {
					sFlag2 = true;
				} else {
					Assert.fail("Bill no is not same " + sBillNumInCheck
							+ " and " + sVendor[0] + "-" + sBillNo[w]);
				}
				w++;
			}

		}
		if (sFlag == true && sFlag1 == true && sFlag2 == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean VerifyVendors(String[] sVendor, int[] sFlagChk2) {

		boolean sFlag3 = false;
		int v = 0, w = 0, z = 0;
		// --------------------Verify Vendor
		// Num-------------------------------//
		for (int b = 2; b <= sFlagChk2.length; b++) {
			int g = b - 2;
			if (sFlagChk2[g] == 0) {
				System.out.println("sFlagChk2[g] is  " + g);
				String sVendorName = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + g
								+ "]/th[1]/div[2]/a")).getText();
				sVendorName = sVendorName.trim();
				System.out.println("sVendorName is " + sVendorName
						+ " sVendor is " + sVendor[z] + " - V" + sVendor[z]);

				if (sVendorName.equals(sVendor[z] + " - V" + sVendor[z])) {
					sFlag3 = true;
				} else {
					Assert.fail("Vendor names are not same " + sVendorName
							+ " and " + sVendor[z] + " - V" + sVendor[z]);
				}

			}

			z++;
		}

		return sFlag3;
	}

	public boolean VerifyGroups(String[] sGroupChk, String[] sAmount,
			String[] sBillNo, int[] sFlagChk1, int sCountVendor,
			int CountChecks, int sVRC, int sCRC) {
		boolean sFlag3 = false;
		int v = 0, w = 0, z = 0, j = 0, k = 0;

		int sVendorrowcount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		int sCheckrowcount = _driver.findElements(
				By.xpath("//table[@id='BillCheck']/tbody/tr")).size();
		System.out.println("sVendorrowcount is " + sVendorrowcount
				+ " sVRC is " + sVRC);
		System.out.println("sCheckrowcount is " + sCheckrowcount + " sCRC is "
				+ sCRC);

		String sTotalVendors = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalVendors = sTotalVendors.trim();

		int sVendorRecords = Integer.parseInt(sTotalVendors);
		System.out.println("sVendorRecords is " + sVendorRecords
				+ " sCountVendor is " + sCountVendor);

		String sTotalChecks = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalChecks = sTotalChecks.trim();
		int sTotalCheck = Integer.parseInt(sTotalChecks);
		System.out.println("sTotalCheck is " + sTotalCheck + " CountChecks is "
				+ CountChecks);

		int ColCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/thead/tr[1]/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = " + ColCount);
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			j = i;
			if (sCName.equals("Original Amt"))
				break;
		}

		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			k = i;
			if (sCName.equals("Bill Number"))
				break;
		}
		// --------------------Verify Vendor
		// Num-------------------------------//
		for (int b = 2; b <= sFlagChk1.length; b++) {
			int g = b - 2;
			if (sFlagChk1[g] == 1) {
				String sGroupCheck = CommonFunctions.GetSelectedOption(
						_driver,
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[4]/select"));
				sGroupCheck = sGroupCheck.trim();
				System.out.println("sGroupCheck is " + sGroupCheck
						+ " sGroupChk is " + sGroupChk[z]);

				String sOriginalAmt = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[" + j + "]/div")).getText();
				sOriginalAmt = sOriginalAmt.trim();
				sOriginalAmt = sOriginalAmt.replace("$", "");
				sOriginalAmt = sOriginalAmt.substring(0,
						Math.min(sOriginalAmt.length(), 2));
				System.out.println("sOriginalAmt is " + sOriginalAmt);
				System.out.println("sOriginalAmt is " + sOriginalAmt
						+ " sOriAmt is " + sAmount[z]);

				String sBillNum = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[" + k + "]/div")).getText();
				sBillNum = sBillNum.trim();
				System.out.println("sBillNum is " + sBillNum + " sBillNo is "
						+ sBillNo[z]);

				if (sGroupCheck.equals(sGroupChk[z])
						&& sOriginalAmt.equals(sAmount[z])
						&& sBillNum.equals(sBillNo[z])
						&& sVendorrowcount == sVRC && sCheckrowcount == sCRC
						&& sVendorRecords == sCountVendor
						&& sTotalCheck == CountChecks) {
					sFlag3 = true;
				} else {
					Assert.fail("sGroupCheck are not same " + sGroupCheck
							+ " and " + sGroupChk[z]);
				}

			}
			z++;

		}

		return sFlag3;
	}

	public boolean VerifyCheckNums(int[] sFlagChk, String[] sChkNo) {
		int v = 0;
		boolean sFlag1 = false;
		for (int b = 2; b <= sFlagChk.length; b++) {
			int g = b - 2;
			if (sFlagChk[g] == 1) {

				String sBNum = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/th[2]/div[2]")).getText();
				sBNum = sBNum.trim();
				System.out.println("sBNum is " + sBNum + " sChkNo is "
						+ sChkNo[v]);

				if (sBNum.equals(sChkNo[v])) {
					sFlag1 = true;
				} else {
					Assert.fail("Check no is not same " + sBNum + " and "
							+ sChkNo[v]);
				}

			}

			v++;
		}
		return sFlag1;
	}

	public boolean VerifyBillNumInCheck(int[] sFlagChk1, String[] sVendor,
			String[] sBillNo) {
		int w = 0;
		boolean sFlag2 = false;

		for (int b = 3; b <= sFlagChk1.length; b++) {
			int g = b - 3;
			if (sFlagChk1[g] == 1) {
				System.out.println("w is " + w);
				String sBillNumInCheck = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/td[6]/div[1]/a")).getText();
				sBillNumInCheck = sBillNumInCheck.trim();
				System.out.println("sBillNumInCheck is " + sBillNumInCheck
						+ " sBillNo is " + sVendor[w] + "-" + sBillNo[w]);
				if (sBillNumInCheck.equals(sVendor[w] + "-" + sBillNo[w])) {
					sFlag2 = true;
				} else {
					Assert.fail("Bill no is not same " + sBillNumInCheck
							+ " and " + sVendor[w] + "-" + sBillNo[w]);
				}

			}
			w++;
		}
		return sFlag2;
	}

	public boolean VerifySelectedBills2(int sCountVendor, int CountChecks,
			String[] sVendor, String[] sGroupChk, ArrayList sAmount,
			String[] sBillNo, String[] sBillNo1, String[] sChkNo, int sVRC,
			int sCRC, int[] sFlagChk, String[] sChkBillNo, int[] sFlagChk1,
			int[] sFlagChk2) {
		boolean sFlag = false, sFlag1 = false, sFlag2 = false, sFlag3 = false;
		int j = 0, k = 0;
		int v = 0, w = 0, z = 0;
		int ColCount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/thead/tr[1]/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = " + ColCount);
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			j = i;
			if (sCName.equals("Original Amt"))
				break;
		}
		for (int i = 4; i <= ColCount; i++) {
			String sCName = _driver.findElement(
					By.xpath("//table[@id='Bill_N10087']/thead/tr/th[" + i
							+ "]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is " + sCName);
			k = i;
			if (sCName.equals("Bill Number"))
				break;
		}

		int sVendorrowcount = _driver.findElements(
				By.xpath("//table[@id='Bill_N10087']/tbody/tr")).size();
		int sCheckrowcount = _driver.findElements(
				By.xpath("//table[@id='BillCheck']/tbody/tr")).size();
		System.out.println("sVendorrowcount is " + sVendorrowcount
				+ " sVRC is " + sVRC);
		System.out.println("sCheckrowcount is " + sCheckrowcount + " sCRC is "
				+ sCRC);

		String sTotalVendors = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalVendors = sTotalVendors.trim();

		int sVendorRecords = Integer.parseInt(sTotalVendors);
		System.out.println("sVendorRecords is " + sVendorRecords
				+ " sCountVendor is " + sCountVendor);

		String sTotalChecks = _driver
				.findElement(
						By.xpath("//fieldset[@id='Bill_N10087_fieldset']/div[1]/div[1]/div[1]/strong"))
				.getText();
		sTotalChecks = sTotalChecks.trim();
		int sTotalCheck = Integer.parseInt(sTotalChecks);
		System.out.println("sTotalCheck is " + sTotalCheck + " CountChecks is "
				+ CountChecks);

		// --------------------Verify Vendor
		// Num-------------------------------//
		for (int b = 2; b <= sFlagChk2.length; b++) {
			int g = b - 2;
			if (sFlagChk2[g] == 0) {
				System.out.println("sFlagChk2[g] is  " + g);
				String sVendorName = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + g
								+ "]/th[1]/div[2]/a")).getText();
				sVendorName = sVendorName.trim();
				System.out.println("sVendorName is " + sVendorName
						+ " sVendor is " + sVendor[z] + " - V" + sVendor[z]);

				if (sVendorName.equals(sVendor[z] + " - V" + sVendor[z])) {
					sFlag3 = true;
				} else {
					Assert.fail("Vendor names are not same " + sVendorName
							+ " and " + sVendor[z] + " - V" + sVendor[z]);
				}
				z++;
			}

		}

		// --------------------Verify vendor
		// Bills-------------------------------//

		for (int a = 1; a <= sFlagChk1.length; a++) {

			int b = a + 1;
			int c = a - 1;
			int g = b - 1;
			if (sFlagChk1[g] == 1) {
				System.out.println("b is " + b + " c is " + c);
				String sGroupCheck = CommonFunctions.GetSelectedOption(
						_driver,
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[4]/select"));
				sGroupCheck = sGroupCheck.trim();
				System.out.println("sGroupCheck is " + sGroupCheck
						+ " sGroupChk is " + sGroupChk[c]);

				String sOriginalAmt = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[" + j + "]/div")).getText();
				sOriginalAmt = sOriginalAmt.trim();
				sOriginalAmt = sOriginalAmt.replace("$", "");
				sOriginalAmt = sOriginalAmt.substring(0,
						Math.min(sOriginalAmt.length(), 2));
				System.out.println("sOriginalAmt is " + sOriginalAmt);
				System.out.println("sOriginalAmt is " + sOriginalAmt
						+ " sOriAmt is " + sAmount.get(c));

				String sBillNum = _driver.findElement(
						By.xpath("//table[@id='Bill_N10087']/tbody/tr[" + b
								+ "]/td[" + k + "]/div")).getText();
				sBillNum = sBillNum.trim();
				System.out.println("sBillNum is " + sBillNum + " sBillNo is "
						+ sBillNo[c]);

				if (sVendorrowcount == sVRC && sCheckrowcount == sCRC
						&& sVendorRecords == sCountVendor
						&& sTotalCheck == CountChecks
						&& sGroupCheck.equals(sGroupChk[c])
						&& sOriginalAmt.equals(sAmount.get(c))
						&& sBillNum.equals(sBillNo[c])) {

					sFlag = true;
				} else {
					Assert.fail("Failed");
				}
			}

		}
		// --------------------Verify Check Num-------------------------------//
		for (int b = 2; b <= sFlagChk.length; b++) {
			int g = b - 2;
			if (sFlagChk[g] == 1) {

				String sBNum = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/th[2]/div[2]")).getText();
				sBNum = sBNum.trim();
				System.out.println("sBNum is " + sBNum + " sChkNo is "
						+ sChkNo[v]);

				if (sBNum.equals(sChkNo[v])) {
					sFlag1 = true;
				} else {
					Assert.fail("Check no is not same " + sBNum + " and "
							+ sChkNo[v]);
				}

			}

			v++;
		}

		// --------------------Verify Bills in
		// check-------------------------------//
		for (int b = 3; b <= sFlagChk1.length; b++) {
			int g = b - 3;
			if (sFlagChk1[g] == 1) {
				System.out.println("w is " + w);
				String sBillNumInCheck = _driver.findElement(
						By.xpath("//table[@id='BillCheck']/tbody/tr[" + b
								+ "]/td[6]/div[1]/a")).getText();
				sBillNumInCheck = sBillNumInCheck.trim();
				System.out.println("sBillNumInCheck is " + sBillNumInCheck
						+ " sBillNo is " + sVendor[0] + "-" + sBillNo[w]);
				if (sBillNumInCheck.equals(sVendor[0] + "-" + sBillNo[w])) {
					sFlag2 = true;
				} else {
					Assert.fail("Bill no is not same " + sBillNumInCheck
							+ " and " + sVendor[0] + "-" + sBillNo[w]);
				}
				w++;
			}

		}
		if (sFlag == true && sFlag1 == true && sFlag2 == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean SearchJobArchive(String jobId) throws Exception,
			IOException {

		CommonFunctions.selectDropdownByText(_driver, By.name("searchField"),
				"Job");
		_driver.findElement(By.name("search")).sendKeys(jobId);
		_driver.findElement(By.name("performSearch")).click();

		NavigateToJobArchivePage();
		Thread.sleep(3000);
		int rowCount = _driver.findElements(
				By.xpath("//table[@id='JobHistory']/tbody/tr")).size();

		if (rowCount == 1) {
			return true;

		} else {
			return false;
		}

	}
	
	public void addFinalShipmentViaJob(String Notes) throws Exception
	{
		
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Job : ')]"));
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add Shipment']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType2"), "Planned Shipment");
		CommonFunctions.ClickElement(_driver, By.xpath("//*[@id='shipmentDetails']/legend/span"));
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("notes2"),Notes);
		CommonFunctions.SendValue(_driver, By.name("cost"), "25");
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"), "20");
		CommonFunctions.SendValue(_driver, By.name("weight"), "5");
		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(5000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType"), "Final Shipment");
		Update();
		Thread.sleep(2000);
	}
	public void addFinalShipmentViaJobPart(String Notes) throws Exception
	{
		
		
		CommonFunctions.ClickElement(_driver, By.xpath("//*[contains(text(),'Part :')]"));
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add Shipment']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType2"), "Planned Shipment");
		CommonFunctions.ClickElement(_driver, By.xpath("//*[@id='shipmentDetails']/legend/span"));
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("notes2"),Notes);
		CommonFunctions.SendValue(_driver, By.name("cost"), "25");
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"), "20");
		CommonFunctions.SendValue(_driver, By.name("weight"), "5");
		
		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(5000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType"), "Final Shipment");
		Update();
		Thread.sleep(2000);
	}
	public void addFinalShipmentViaJobPart(String Notes,String Count,String Cost) throws Exception
	{
		
		
		CommonFunctions.ClickElement(_driver, By.xpath("//*[contains(text(),'Part :')]"));
		Thread.sleep(3000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add Shipment']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType2"), "Final Shipment");
		CommonFunctions.ClickElement(_driver, By.xpath("//*[@id='shipmentDetails']/legend/span"));
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("notes2"),Notes);
		CommonFunctions.SendValue(_driver, By.name("cost"), Cost);
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"), "20");
		CommonFunctions.SendValue(_driver, By.name("weight"), "5");
		CommonFunctions.SendValue(_driver, By.name("count"), Count);
		Add();
		CommonFunctions.waitForPageLoaded(_driver);
		
		
	}
	
	
	 
	public void navigateToPostJobCostsPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/process/run?key=JobCost.post");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PostJobCost_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Posting Job Costs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToPostJobCostsPage");
		System.out.println("****Post Job Costs page appears****");
	}
	
	
	public void addPlannedShipmentViaJob(String Notes) throws Exception
	{
		
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Job : ')]"));
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add Shipment']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType2"), "Planned Shipment");
		CommonFunctions.ClickElement(_driver, By.xpath("//*[@id='shipmentDetails']/legend/span"));
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("notes2"),Notes);
		CommonFunctions.SendValue(_driver, By.name("cost"), "25");
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"), "20");
		CommonFunctions.SendValue(_driver, By.name("weight"), "5");
		Add();
	
	}
	
	public void AddJobPartItem()throws Exception
	{
		
		
		String  originalHandle = _driver.getWindowHandle();
		
		String sWindowTitle =_driver.getTitle();
		
		Set<String> availableWindows = _driver.getWindowHandles();
		
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
		CommonFunctions.ClickElement(_driver, By.xpath(".//fieldset [contains(@id,'JobPartItem_')]/div/div[1]//a"));
		
		
		
	}
	
	
	public void Validate_job_General_Information(String Description , String QuantityOrd, String Shiptocontact)
	{
		String Descp=_driver.findElement(By.name("description")).getText();
		
		if (Description.equals(Descp))
		{
			System.out.println("Job part description is desplayed correctly");
		}
		else
		{
			System.err.println("Job part description is not desplayed correctly");
		}
		
		String Qty=_driver.findElement(By.name("qtyOrdered")).getText();
		
		if(QuantityOrd.equals(Qty))
		{
			System.out.println("Quantity is displayed correctly");
		}
		else
		{
			System.err.println("Quantity is not displayed correctly");
		}
       
		String STC =CommonFunctions.GetSelectedOptionValue(_driver, By.name("shipToContact"));
		
		if(Shiptocontact.equals(STC))
		{
			System.out.println("ShiptoContact is displayed correctly");
		}
		else
			
		{
			System.err.println("ShiptoContact is not displayed correctly");
		}
	}
	
	
	public void Validate_JobPart_IventoryItems()
	{
		_driver.findElement(By.xpath("//a[text()='Inventory Order Items']")).click();
		
		
		
	}
	
	public void navigateToJobPart(String JobID, String Server, String Company, String PartNum) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/JobPart/detail/"+JobID+"%3A0"+PartNum);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		NewFileNamePath = TakeScreenShot.getDestinationFile("Navigatetopart"+PartNum);
		
		
		
	}
}
