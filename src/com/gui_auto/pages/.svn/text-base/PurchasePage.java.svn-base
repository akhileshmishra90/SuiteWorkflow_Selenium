package com.gui_auto.pages;




import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;


public class PurchasePage implements BaseElement
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


	public  PurchasePage(WebDriver driver) throws Exception 
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
		DateFormat dateFormat = new SimpleDateFormat("ddhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
	}



	public void NavigateToPurchaseOrderSetup() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/POSetup/detail/1");
		CommonFunctions.Wait(_driver, By.linkText("Purchase Order Setup"));
		assertEquals("Purchase Order Setup", _driver.getTitle());
	}
	public void NavigateToAutoCreatePO() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/autoCreate");	
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.linkText("Create Purchase Order"));
		assertEquals("Auto Create Purchase Order Review", _driver.getTitle());
	}
	public void NavigateToGeneralLedgerSetup() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/GLSetup/detail/1");	
		CommonFunctions.Wait(_driver, By.linkText("Purchase Order Setup"));
		assertEquals("General Ledger Setup", _driver.getTitle());
	}



	public void NavigateToVendorinquiry() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/inquiry");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Vendors - Vendors", _driver.getTitle());
		System.out.println("Navigated to Vendor Inquiry page");
	}
	public void NavigateToSystemUser() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SystemUser/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("System Users", _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	public void NavigateToPurchaseOrderType() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderType/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Purchase Order Type", _driver.getTitle());
		System.out.println("Navigated to Purchase Order Type list page");
	}

	public void NavigateToPurchaseOrderTypeModalDialog() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderType/list");	
		AcceptModalDialog();
		CommonFunctions.Wait(_driver, By.linkText("Purchase Order Setup"));
		assertEquals("Purchase Order Type", _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}


	public void NavigateToPOReceive() throws Exception, IOException
	{

		String sGetURL = _driver.getCurrentUrl();
		System.out.println("sGetURL is "+sGetURL);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		//sGetURL=sGetURL.replace("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/detail/", "");
		String[] aGetURL = sGetURL.split("/");
		sGetURL = aGetURL[aGetURL.length-1];
		sGetURL = sGetURL.replace("?tab=0", "");

		System.out.println("ID  is "+sGetURL);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/receive/"+sGetURL);
		Thread.sleep(5000);
		// _driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();

		//_driver.findElement(By.xpath("//div/span/a[@onclick='openContextBox(event, 'PurchaseOrder')']")).click();
		////	Thread.sleep(2000);
		//	_driver.findElement(By.xpath("//table[2]/tbody/tr[2]/td/a[text()='- Receive']")).click();

		CommonFunctions.Wait(_driver, By.linkText("Return to Detail"));

	}

	public void NavigateToPOStatus() throws Exception, IOException
	{

		String sGetURL = _driver.getCurrentUrl();
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sGetURL=sGetURL.replace("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/detail/", "");
		sGetURL = sGetURL.replace("?tab=0", "");
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/status/"+sGetURL);  
		Thread.sleep(5000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		//_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		//  	Thread.sleep(1000);
		//  	_driver.findElement(By.xpath("//table[3]/tbody/tr[2]/td[1]/a[text()='- Status']")).click();
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Change Status']"));


	}
	public void NavigateToPOReport() throws Exception, IOException
	{
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//table[4]/tbody/tr[2]/td/a[text()='- Purchase Order']")).click();
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	public void NavigateToPOEmail(String PO) throws Exception, IOException
	{

		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/email/"+PO);  
		Thread.sleep(5000);
		CommonFunctions.Wait(_driver, By.linkText("SendEmail"));
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	public void NavigateToPOAdd() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/add");  
		Thread.sleep(5000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		//	_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		//	Thread.sleep(1000);
		//	_driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td/div/div[1]/a[text()='Add']")).click();
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Enter Information']"));

	}
	public void NavigateToPOList() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/list");  
		Thread.sleep(5000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		//_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		//	Thread.sleep(1000);
		//	_driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td/div/div[3]/a[text()='List']")).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));

	}
	public void NavigateToPOAutoCreatePO() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/autoCreate");  
		Thread.sleep(5000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		//	_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		//	Thread.sleep(1000);
		//	_driver.findElement(By.xpath("//table[2]/tbody/tr[2]/td[2]/a[text()='- Auto Create POs']")).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.CreatePurchaseOrder)));

	}
	public void NavigateToPODetails() throws Exception, IOException
	{
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[3]")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td/div/div[2]/a[text()='Detail']")).click();
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.linkText("Line Items"));

	}
	public void NavigateToEnterPONumber() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)));
		assertEquals("Adding Purchase Order", _driver.getTitle());
		System.out.println("Navigated to Enter new Purchase Order page");
	}

	public void NavigateToPage(String sURL,String sTitle) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/"+sURL);	
		Thread.sleep(3000);
		//assertEquals(sTitle, _driver.getTitle());
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
	}
	public void NavigateToDupeExistingPO() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/listClone");
		CommonFunctions.Wait(_driver, By.name("search"));
		assertEquals("Choose a Purchase Order to copy - All", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"DupeExistingPO");
	}
	public void NavigateToPurchaseOrder() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("search"));
		assertEquals("Purchase Orders - Open", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"PurchaseOrder");
	}
	public void NavigateToPO() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrder/list");
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name("search"));
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"PurchaseOrder");
	}
	public void NavigateToFindPurchaseOrderLines() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderLine/purchaseOrder");
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Purchase Order Lines by Purchase Order - Open", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"PurchaseOrderLines");
	}

	public void NavigateToReceivePO() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderLine/receive");
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Purchase Orders", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"ReceivesPO");
	}
	public void NavigateToFindPOReceipt() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PurchaseOrderReceipt/list");
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Purchase Order Receipts by Purchase Order - Open", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"POReceipt");
	}
	public void sSelectCheckBox(boolean sFlag,By sLoc)
	{
		if(sFlag == true)
		{
			if(_driver.findElement(sLoc).isSelected())
			{
				System.out.println("Already checked Status");
			}
			else
			{
				_driver.findElement(sLoc).click();
				System.out.println("checked Status");
			}
		}
		else if(sFlag == false)
		{
			if(_driver.findElement(sLoc).isSelected())
			{
				_driver.findElement(sLoc).click();
				System.out.println("Unchecked Status");
			}
			else
			{
				System.out.println("Already Unchecked Status");
			}
		}
		else
		{
			System.err.println("Not able to select check Box");
		}
	}

	public void PurchaseOrderSetup(String sAC,String sUOM,String sStatus,String sLineType,String sQty,String sQtyToRcv,String sContact,boolean sApprove,boolean PO_num,boolean sClose_purchase) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Default_Activity_Code)), sAC);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_UOM)), sUOM);
		Thread.sleep(1000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Default_Status)), sStatus);
		Thread.sleep(1000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Default_Line_Type)), sLineType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Qty)), sQty);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Default_Quantity_To_Receive)), sQtyToRcv);
		Thread.sleep(1000);
		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Default_Shipment_To_Contact)), sContact);
		//Thread.sleep(1000);
		sSelectCheckBox(sApprove, By.name(Locators.getProperty(Locators.PO_Auto_Approve_Inventory)));
		sSelectCheckBox(PO_num, By.name(Locators.getProperty(Locators.PO_Allow_Duplicate_PO_Number)));
		sSelectCheckBox(sClose_purchase, By.name(Locators.getProperty(Locators.PO_Auto_Close_Purchase_Order)));


		if(_driver.findElements(By.name("defaultInventoryUnitPrice")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Default_Inventory_Unit_Price)), "Average");
		}

	}


	public void NavigateToPurchaseOrderNumbering() throws Exception
	{
		_driver.findElement(By.linkText("Purchase Order Numbering")).click();
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.xpath("//label[text()='PO Number 1']"));

	}

	public void NavigateToInterfaceSetup() throws Exception
	{
		_driver.findElement(By.linkText("Interface Setup")).click();
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.PO_Interface_setup_Interface_AP)));

	}



	public void NavigateToAutomaticPurchaseOrderCreation() throws Exception
	{
		_driver.findElement(By.linkText("Automatic Purchase Order Creation")).click();
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));

	}
	public int VerifyPONumberPerfix()
	{
		int count =0; 
		for(int i =1 ;i<=9;i++)
		{
			if(_driver.findElements(By.xpath("//label[text()='PO Number Prefix "+i+"']")).size()>0)
			{
				System.out.println("PO Number Prefix "+i+" is Visible");
			}
			else
			{
				System.err.println("PO Number Prefix "+i+" is Not visible");
				count++;
			}
		}
		return count;
	}

	public int VerifyPONumber()
	{
		int count =0; 
		for(int i =1 ;i<=9;i++)
		{
			if(_driver.findElements(By.xpath("//label[text()='PO Number "+i+"']")).size()>0)
			{
				System.out.println("PO Number "+i+" is Visible");
			}
			else
			{
				System.err.println("PO Number "+i+" is Not visible");
				count++;
			}
		}
		return count;
	}


	public void EnterPONumberPerfix()
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_1))).sendKeys("A");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_2))).sendKeys("B");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_3))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_3))).sendKeys("C");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_4))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_4))).sendKeys("D");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_5))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_5))).sendKeys("E");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_6))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_6))).sendKeys("F");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_7))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_7))).sendKeys("E");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_8))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_8))).sendKeys("G");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_9))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_Perfix_9))).sendKeys("H");

	}

	public void EnterPONumber(String Num1,String Num2,String Num3,String Num4,String Num5,String Num6,String Num7,String Num8,String Num9)
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_1))).sendKeys(Num1);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_2))).sendKeys(Num2);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_3))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_3))).sendKeys(Num3);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_4))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_4))).sendKeys(Num4);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_5))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_5))).sendKeys(Num5);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_6))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_6))).sendKeys(Num6);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_7))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_7))).sendKeys(Num7);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_8))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_8))).sendKeys(Num8);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_9))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Number_9))).sendKeys(Num9);

	}

	public void EnterPONum(String sNum,String sLoc) throws Exception
	{
		_driver.findElement(By.name(sLoc)).clear();
		_driver.findElement(By.name(sLoc)).sendKeys(sNum);
		_driver.findElement(By.id("quickJumpDropdown")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}

	public String AcceptModalDialog()
	{

		Alert alertDialog = _driver.switchTo().alert();
		String alertText = alertDialog.getText();
		System.out.println("alertText is "+ alertText);
		alertDialog.accept();
		return alertText;
	}
	public boolean isAlertPresent() 
	{ 
		try 
		{ 
			System.out.println("Alert present");
			_driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			System.out.println("No Alert present");
			return false; 
		}   // catch 
	} 

	public boolean VerifyFieldsInInterfaceSetup() throws Exception
	{
		boolean sInterfaceJCNever = false,sInterfaceJCEntry = false,sInterfaceJCReceipt = false,sInterfaceAP= false,TaxToJobCost = false,IntegrateWithScheduling = false,LeadTime= false;
		sInterfaceJCNever = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Interface_setup_Interface_JC_Never))).isDisplayed();
		sInterfaceJCEntry = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Interface_setup_Interface_JC_Entry))).isDisplayed();
		sInterfaceJCReceipt = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Interface_setup_Interface_JC_Receipt))).isDisplayed();
		sInterfaceAP = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Interface_setup_Interface_AP))).isDisplayed();
		TaxToJobCost	= _driver.findElement(By.name(Locators.getProperty(Locators.PO_Interface_setup_Tax_To_Job_Cost))).isDisplayed();
		IntegrateWithScheduling	= _driver.findElement(By.name(Locators.getProperty(Locators.PO_Interface_setup_Integrate_with_Scheduling))).isDisplayed();
		sSelectCheckBox(true, By.name(Locators.getProperty(Locators.PO_Interface_setup_Integrate_with_Scheduling)));
		Thread.sleep(1000);

		LeadTime	= _driver.findElement(By.name(Locators.getProperty(Locators.PO_Interface_setup_Lead_Time))).isDisplayed();
		if(sInterfaceJCNever == true && sInterfaceJCEntry == true && sInterfaceJCReceipt == true && sInterfaceAP== true && TaxToJobCost == true && IntegrateWithScheduling == true &&LeadTime== true)
		{
			System.out.println("All the fields in Interface setup are present");
			return true;
		}
		{
			System.err.println("Some of the fields in interface setup  are not present");

			Assert.fail("sInterfaceJCNever= "+ sInterfaceJCNever+"sInterfaceJCEntry= "+sInterfaceJCEntry+"sInterfaceJCReceipt= "+sInterfaceJCReceipt+"sInterfaceAP = "+sInterfaceAP +"TaxToJobCost ="+TaxToJobCost+ "IntegrateWithScheduling ="+IntegrateWithScheduling+ "LeadTime ="+LeadTime);
			return false;
		}
	}



	public boolean VerifyFieldsInAutomaticPOCreation()
	{
		boolean sGLAccount = false,sCreateJobMaterialLine = false,AutoCreateJobPartOutSidePurchLines = false,AutoCreateInventoryItemLine= false,IncludeSetup = false;
		sGLAccount = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account))).isDisplayed();
		sCreateJobMaterialLine = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Job_Materail_Line))).isDisplayed();
		AutoCreateJobPartOutSidePurchLines = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_AutoCreate_JobPart_OutSide_PurchLines))).isDisplayed();
		AutoCreateInventoryItemLine = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Inventory_Item_Line))).isDisplayed();
		IncludeSetup	= _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Include_Setup))).isDisplayed();
		if(sGLAccount == true && sCreateJobMaterialLine == true && AutoCreateJobPartOutSidePurchLines == true && IncludeSetup== true)
		{
			System.out.println("All the fields in  Aumtomatic PO Creation tab are present");
			return true;
		}
		{
			System.err.println("Some of the fields in  Aumtomatic PO Creation tab are not present");

			Assert.fail("sGLAccount= "+ sGLAccount+"sCreateJobMaterialLine= "+sCreateJobMaterialLine+"AutoCreateJobPartOutSidePurchLines= "+AutoCreateJobPartOutSidePurchLines+"AutoCreateInventoryItemLine = "+AutoCreateInventoryItemLine +"IncludeSetup ="+IncludeSetup);
			return false;
		}
	}
	public void SetAutomaticPOCreation(String sGLAccount,boolean sCreateJobMaterials,boolean JobPartOutSidePurchLines,boolean InventoryItemLine,boolean IncludeSetup) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), sGLAccount);
		sSelectCheckBox(sCreateJobMaterials,By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Job_Materail_Line)));
		sSelectCheckBox(JobPartOutSidePurchLines,By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_AutoCreate_JobPart_OutSide_PurchLines)));
		sSelectCheckBox(InventoryItemLine,By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Inventory_Item_Line)));
		sSelectCheckBox(IncludeSetup,By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Include_Setup)));	
	}	
	public boolean VerifyAutomaticPOCreation(String sGLAcount,boolean CreateJobMaterials,boolean JobPartOutSidePurchLines,boolean InventoryItemLine,boolean IncludeSetup)
	{
		String sSelectedGLAccount = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));
		sSelectedGLAccount=sSelectedGLAccount.trim();
		System.out.println("sSelectedGLAccount is "+sSelectedGLAccount);

		boolean sCreateJobMaterials = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Job_Materail_Line))).isSelected();
		System.out.println("sCreateJobMaterials is "+sCreateJobMaterials);

		boolean sJobPartOutSidePurchLines = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_AutoCreate_JobPart_OutSide_PurchLines))).isSelected();
		System.out.println("sJobPartOutSidePurchLines is "+sJobPartOutSidePurchLines);

		boolean sInventoryItemLine = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Auto_Create_Inventory_Item_Line))).isSelected();
		System.out.println("sInventoryItemLine is "+sInventoryItemLine);

		boolean sIncludeSetup = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Include_Setup))).isSelected();
		System.out.println("sIncludeSetup is "+sIncludeSetup);

		if(sSelectedGLAccount.equals(sGLAcount) && sCreateJobMaterials == CreateJobMaterials && sJobPartOutSidePurchLines == JobPartOutSidePurchLines && sInventoryItemLine == InventoryItemLine && sIncludeSetup == IncludeSetup)
		{
			return true;	
		}
		else
		{
			return false;
		}
	}	
	public String AddPOType(String sDesc,String sPOPerfix,String sPONum,boolean sAutoNum,boolean sActive) throws Exception
	{
		String sWText="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Type")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Type_PO_Num_Perfix_DropDown)), sPOPerfix);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Type_PO_Num_Sequence_DropDown)), sPONum);
					Thread.sleep(1000);
					sSelectCheckBox(sAutoNum, By.name(Locators.getProperty(Locators.PO_Type_Auto_Num_Only)));
					sSelectCheckBox(sActive, By.name(Locators.getProperty(Locators.PO_Type_Active)));
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					if(sDesc.equals(""))
					{
						sWText = AcceptModalDialog();
						if(sWText.contains(" - The Description field is required."))
						{
							System.out.println("Able to see warning Message");
							sDesc = "De"+sUniqueNum();
							_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
							_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
						}
						else
						{
							System.err.println("Not Able to see warning Message");
						}
					}

					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

				}

			}
		}
		System.err.println("Created new POType "+sDesc);
		return sDesc;
	}


	public String UpdatePOType(String sDesc,String sPOPerfix,String sPONum,boolean sAutoNum,boolean sActive) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		int i =0;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Type_first_Magnifying_Glass))).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Order Type")) 
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Description))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Type_PO_Num_Perfix_DropDown)), sPOPerfix);
						Thread.sleep(1000);
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Type_PO_Num_Sequence_DropDown)), sPONum);
						Thread.sleep(1000);
						sSelectCheckBox(sAutoNum, By.name(Locators.getProperty(Locators.PO_Type_Auto_Num_Only)));
						sSelectCheckBox(sActive, By.name(Locators.getProperty(Locators.PO_Type_Active)));
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");		
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}
		return sDesc;
	}


	public void DeletePOType() throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		int i =0;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Type_first_Magnifying_Glass))).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Order Type")) 
					{
						System.out.println("Delete");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(2000);
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");		
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}
	}



	public boolean DeletePOTypeAssociatedWithPOOrder(String sDesc) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		int i =0;
		String sText =""; boolean sFlag1 =false;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Type_first_Magnifying_Glass))).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Order Type")) 
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
						_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
						Thread.sleep(2000);
						sText = 	_driver.findElement(By.xpath("//div[@id='fmessage']/ul/li")).getText();
						if(sText.contains("("+sDesc+") still referenced by 1 PurchaseOrder with field: Purchase Order Type"))
						{
							sFlag1 = true;
							_driver.close();
							return sFlag1;
						}
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");		
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}
		return sFlag1;
	}
	public void ADDPO() throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		int i =0;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("orderStatus"));
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
					{
						//						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div/div/div[2]/div/div/a")).getText();
						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div/div[2]/div[1]/div/div[1]/a")).getText();
						sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
						sPOOrder = sPOOrder.replace(")","");
						if (sPOOrder.contains("("))
						{
							String[] aPOOrder = sPOOrder.split("\\(");
							sPOOrder = aPOOrder[0].trim();
						}
						System.out.println("PO Order is "+sPOOrder);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+sPOOrder);
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}

	}

	public void CloseModalDialog(String  originalHandle) throws Exception
	{

		int i =0;
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
					{
						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div/div/div[2]/div/div/a")).getText();
						sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
						sPOOrder = sPOOrder.replace(")","");
						if (sPOOrder.contains("("))
						{
							String[] aPOOrder = sPOOrder.split("\\(");
							sPOOrder = aPOOrder[0].trim();
						}
						System.out.println("PO Order is "+sPOOrder);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+sPOOrder);
						System.out.println("Page title is Purchase order "+sPOOrder);
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}

	}

	public String FetchValueOnModalWindowAndClose(String  originalHandle,By locator) throws Exception
	{

		int i =0; String sGLAccount = "";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if( i==1)
				{
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
					{
						sGLAccount =  CommonFunctions.GetSelectedOption(_driver, locator);
						Thread.sleep(1000);
						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div/div/div[2]/div/div/a")).getText();
						sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
						sPOOrder = sPOOrder.replace(")","");
						if (sPOOrder.contains("("))
						{
							String[] aPOOrder = sPOOrder.split("\\(");
							sPOOrder = aPOOrder[0].trim();
						}
						System.out.println("PO Order is "+sPOOrder);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+sPOOrder);
						System.out.println("Page title is Purchase order "+sPOOrder);
					} 
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

					}
				}
				else
				{
					i++;
				}
			}
		}
		return sGLAccount;
	}
	public boolean VerifyVendorContactPopUp(String sVendor,String sLoc) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		int i =0;
		boolean sFlag= false;
		_driver.findElement(By.xpath(sLoc)).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Vendor "+sVendor)) 
				{
					System.out.println("Verify Contact Details");
					String sVendorId= 	_driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div/div")).getText();sVendorId=sVendorId.trim();
					String sVendorName= 	_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Name))).getAttribute("value");sVendorName=sVendorName.trim();
					String sContactTitle= 	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_Title))).getAttribute("value");sContactTitle=sContactTitle.trim();
					String sContactFN=	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_First_Name))).getAttribute("value");sContactFN=sContactFN.trim();
					String sContactLN=		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_Last_Name))).getAttribute("value");sContactLN=sContactLN.trim();
					String sAdd1=		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line1))).getAttribute("value");sAdd1=sAdd1.trim();
					String sAdd2=		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line2))).getAttribute("value");sAdd2=sAdd2.trim();
					String sAdd3=	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line3))).getAttribute("value");sAdd3=sAdd3.trim();
					String sPhoneNum=		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_phoneNum))).getAttribute("value");sPhoneNum=sPhoneNum.trim();
					String sFaxNum=	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_FaxNum))).getAttribute("value");sFaxNum=sFaxNum.trim();
					String sCity= 	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_City))).getAttribute("value");sCity=sCity.trim();
					String sEmail= 	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Email))).getAttribute("value");sEmail=sEmail.trim();
					String sCountry =	CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)));
					Thread.sleep(1000);
					sCountry=sCountry.trim();
					String sState =	CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Vendor_State)));
					Thread.sleep(1000);
					sState=sState.trim();
					String sZip =	_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_ZipCode))).getAttribute("value");
					sZip=sZip.trim();
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					//System.out.println("sVendor is "+sVendor+" ")
					//	if(sVendorId.equals(sVendor) && sVendorName.equals(sVendor) && sContactTitle.equals(sVendor) && sContactFN.equals(sVendor) && sContactLN.equals(sVendor) && sAdd1.equals("45") && sAdd2.equals("WF") && sAdd3.equals("BNG") && sPhoneNum.equals("123456") && sFaxNum.equals("123456") && sCity.equals("BNG") && sEmail.equals("efiuser123@gmail.com") && sState.equals("511571") && sZip.equals("ZZZZZZ - Unknown State") && sCountry.equals("US - United States"))
					if(sVendorId.equals(sVendor) && sVendorName.equals("V"+sVendor) && sContactTitle.equals(sVendor) && sContactFN.equals(sVendor) && sContactLN.equals(sVendor) && sAdd1.equals("45") && sAdd2.equals("WF") && sAdd3.equals("BNG") && sPhoneNum.equals("01298567345") && sFaxNum.equals("01298567345") && sCity.equals("BNG") && sEmail.equals("efiuser123@gmail.com") && sState.contains("ZZZZZZ") && sZip.equals("511571") && sCountry.contains("US"))
					{
						sFlag = true;
					}
					else
					{
						Assert.fail("sVendorId is "+sVendorId+" Vendor Name is "+sVendorName+" sContact title is "+sContactTitle+" sContactFN is"+sContactFN+" sContactLN is "+sContactLN+" sAdd1 is  "+sAdd1+" sAdd2 is "+sAdd2+" sAdd3 is "+sAdd3+" sPhoneNum is "+sPhoneNum+" sFaxNum is "+sFaxNum+" sCity is "+sCity+" sEmail is "+sEmail+" sState is "+sState+" sZip is "+sZip+ "sCountry is "+sCountry);
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}



	public boolean VerifyContactPopUp(String sVendor,String sLoc) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		int i =0;
		boolean sFlag= false;
		_driver.findElement(By.xpath(sLoc)).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Contact")) 
				{
					System.out.println("Verify Contact Details");
					String sContactFN=	_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).getAttribute("value");
					String sContactLN=		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).getAttribute("value");

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					if(sContactFN.equals(sVendor) && sContactLN.equals(sVendor))
					{
						sFlag = true;
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}

	public boolean VerifyOrderStatusPopUp(String sLoc,String sLoc1,String sStatus) throws Exception
	{
		String sDesc="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		int i =0;
		boolean sFlag= false;
		_driver.findElement(By.xpath(sLoc1)).click(); 
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Order Status O")) 
				{
					System.out.println("Verify Order status  Details");
					if(_driver.findElements(By.xpath(sLoc)).size()>0)
					{
						sDesc=	_driver.findElement(By.xpath(sLoc)).getText();
					}
					else
					{
						sDesc = _driver.findElement(By.name("description")).getAttribute("value");
					}
					sDesc =sDesc.trim();

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					if(sDesc.equals(sStatus))
					{
						sFlag = true;
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}

	public boolean VerifyVendorCountryPopUp(String sLoc) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		int i =0;
		boolean sFlag= false;
		_driver.findElement(By.xpath(sLoc)).click(); 
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Country US")) 
				{
					System.out.println("Verify Contact Details");
					String sName=	_driver.findElement(By.name("name")).getAttribute("value");


					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					if(sName.equals("United States"))
					{
						sFlag = true;
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}
	public String  sUniqueNum()
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		DateFormat dateFormat = new SimpleDateFormat("hhmmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}		
	public void EnterPONumberDetails(String sDesc,String sPO,String sVendor,String sContact,String sOrderstatus,String sShiptoContact) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)), sDesc);
		Thread.sleep(1000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).sendKeys(sPO);
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).sendKeys(sVendor);
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		//		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor_Contact)), sContact);
		//	Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Order_Status)), sOrderstatus);
		//		Thread.sleep(1000);
		//		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Default_Ship_To_Contact)), sShiptoContact);
		//	Thread.sleep(1000);

	}

	public void EnterPONumberDetails1(String sDesc,String sPO,String sVendor,String sContact,String sOrderstatus,String sShiptoContact) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)), sDesc);
		Thread.sleep(1000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).sendKeys(sPO);
		}

		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor_Contact)), sContact);
		//	Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Order_Status)), sOrderstatus);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Default_Ship_To_Contact)), sShiptoContact);
		//	Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).sendKeys(sVendor);
		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();

	}

	public void EnterPONumber(String sPO,String sVendor,String sContact,String sOrderstatus,String sShiptoContact) throws Exception
	{
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)), 1);
		Thread.sleep(1000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num))).sendKeys(sPO);
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor))).sendKeys(sVendor);
		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor_Contact)), sContact);
		//	Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Order_Status)), sOrderstatus);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='Enter Information']")).click();
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Default_Ship_To_Contact)), sShiptoContact);
		//	Thread.sleep(1000);

	}
	public String ContactInformation() throws Exception, IOException
	{
		DateFormat dateFormat = new SimpleDateFormat("hhmmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		String sVendorID = sUniqueNumber;
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Vendor1");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Id))).sendKeys(sVendorID);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Vendor12");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Name))).click();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Name))).sendKeys("V"+sVendorID);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Vendor112");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_Title))).sendKeys(sVendorID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_First_Name))).sendKeys(sVendorID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Contact_Last_Name))).sendKeys(sVendorID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line1))).sendKeys("45");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line2))).sendKeys("WF");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line3))).sendKeys("BNG");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_phoneNum))).sendKeys("01298567345");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_FaxNum))).sendKeys("01298567345");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_City))).sendKeys("BNG");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Email))).sendKeys("efiuser123@gmail.com");
		/*CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), "AU - Australia");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_State)), "ZZZZZZ - Unknown State");
		Thread.sleep(1000);*/
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_ZipCode))).sendKeys("511571");
		return sVendorID;
	}


	public void POSettingsDetails(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Setting))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Vendor_Settings_Tax_Number)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Settings_Tax_Number))).sendKeys(sTaxNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Settings_Registration_Num))).sendKeys(RegNum);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Vendor_GL_Location))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_GL_Location))).sendKeys(GL_Location);
		}
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax1)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code1)),1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax2)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code2)),1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Default_Freight_OnBoard))).sendKeys(FreightOnBoard);

	}

	public void TaxSettings(String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax1)), SaleTax1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code1)),TaxableC1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax2)), SaleTax2);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code2)),TaxableC2);
		Thread.sleep(1000);	
	}
	public void POSettingsDetails1(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Vendor_Setting))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Vendor_Settings_Tax_Number)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Settings_Tax_Number))).sendKeys(sTaxNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Settings_Registration_Num))).sendKeys(RegNum);

		if(_driver.findElements(By.name(Locators.getProperty(Locators.Vendor_GLAccount))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_GLAccount))).sendKeys("00001");
		}
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Vendor_GL_Location))).size()>0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_GL_Location))).sendKeys(GL_Location);
		}
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax1)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code1)),1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Sale_Tax2)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Vendor_Taxable_Code2)),1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Default_Freight_OnBoard))).sendKeys(FreightOnBoard);

	}
	public String  CreateNewVendorInquiry(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Vendor_Id)));
		String sVendorID = ContactInformation();
		POSettingsDetails(sTaxNum,RegNum,GL_Location,SaleTax1,SaleTax2,TaxableC1,TaxableC2,FreightOnBoard);	
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sVendorID;
	}

	public String  CreateNewVendor(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Vendor_Id)));
		String sVendorID = ContactInformation();
		POSettingsDetails(sTaxNum,RegNum,GL_Location,SaleTax1,SaleTax2,TaxableC1,TaxableC2,FreightOnBoard);	
		TaxSettings(SaleTax1,SaleTax2,TaxableC1,TaxableC2);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sVendorID;
	}
	public String  CreateNewVendorInquiry1(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Vendor_Id)));
		String sVendorID = ContactInformation();
		POSettingsDetails1(sTaxNum,RegNum,GL_Location,SaleTax1,SaleTax2,TaxableC1,TaxableC2,FreightOnBoard);	
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sVendorID;
	}

	public String  CreateVendor(String sTaxNum,String RegNum,String GL_Location,String SaleTax1,String SaleTax2,String TaxableC1,String TaxableC2,String FreightOnBoard) throws Exception, IOException
	{

		String sVendorID = ContactInformation();
		POSettingsDetails1(sTaxNum,RegNum,GL_Location,SaleTax1,SaleTax2,TaxableC1,TaxableC2,FreightOnBoard);	
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sVendorID;
	}

	public boolean AddNewLineITem(String sInv,String sLineType,String sDesc,String sGLAccount,String sLocation) throws Exception
	{
		boolean sFlag = false;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		System.out.println("Date is "+sUniqueNumber);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(6000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(3000);
					String sEnteredLineType =CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)));sLineType=sLineType.trim(); 
					sEnteredLineType = sEnteredLineType.trim();
					String sSelectedUOM =CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_UOM)));sSelectedUOM=sSelectedUOM.trim(); 
					sSelectedUOM =sSelectedUOM.trim();
					//					String sDataEntered =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Entered))).getText();sDataEntered=sDataEntered.trim();
					//					sDataEntered =sDataEntered.trim();
					String sDataEntered =  CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Entered_byname)));
					//String sSelGLAccount = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));
					//sSelGLAccount =sSelGLAccount.trim();
					String sSelLocation = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Vendor_GL_Location)));
					sSelLocation =sSelLocation.trim();
					boolean sTax=  _driver.findElement(By.name("taxableBooleanCheck")).isSelected();
					if(sTax == true && sEnteredLineType.equals("Inventory") && sSelectedUOM.equals("EA-Each") && sDataEntered.equals(sUniqueNumber)  && sSelLocation.equals(sLocation))
					{
						sFlag = true;
						System.out.println("Values are same as entered in PO setup");
					}
					else
					{
						sFlag = false;
						Assert.fail("sTax is "+sTax+" Selected sLineType is "+sEnteredLineType+" Selected sSelectedUOM is "+sSelectedUOM+" sDataEntered is "+sDataEntered+ " Selected GL location is "+sSelLocation);
					}
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)), sLineType);
					Thread.sleep(2000);
					boolean sPresent	= _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).isDisplayed();
					if(sPresent == true)
					{
						Assert.fail("Other field still present after selecting description as Linetype");
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}

	public boolean AddNewLineItemBySelectingInventoryItem(String sInv,String sVendorPartNum,String Desc,String Price,String UOM,String sDateEntered,String sRequiredDate) throws Exception
	{
		boolean sFlag = false,sflag2=false;
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.PO_Unit_Price))).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(2000);

					String sVendorPN = _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).getAttribute("value");
					sVendorPN =sVendorPN.trim();
					System.out.println("sVendorPN is "+sVendorPN);

					String sDesc = _driver.findElement(By.name(Locators.getProperty(Locators.Description))).getAttribute("value");
					sDesc =sDesc.trim();
					System.out.println("sDesc is "+sDesc);

					String sPrice = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Unit_Price))).getAttribute("value");
					sPrice=sPrice.replace("$", "");
					sPrice =sPrice.trim();
					System.out.println("sPrice is "+sPrice);

					String sUOM =CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)));
					System.out.println("sUOM is "+sUOM);

					//				String sDataEntered =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Entered))).getText();sDataEntered=sDataEntered.trim();
					//				sDataEntered =sDataEntered.trim();
					String sDataEntered =  CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Entered_byname)));
					System.out.println("sDataEntered is "+sDataEntered);

					String sDataRequired =  _driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Required))).getAttribute("value");
					sDataRequired=sDataRequired.trim();

					boolean sTax=  _driver.findElement(By.name("taxableBooleanCheck")).isSelected();

					System.out.println("Expected sInv is "+sInv+" sVendorPartNum is "+sVendorPartNum+" Desc is "+Desc+" Price is "+Price+" UOM is "+UOM+" sDateEntered is "+sDateEntered+" sRequiredDate is "+sRequiredDate);

					if(sPrice.equals("0.00") || sPrice.equals("0.0000") || sPrice.equals("3.00") || sPrice.equals("3.0000"))
					{
						sflag2=true;
					}

					if(sVendorPN.equals(sVendorPartNum)  && sflag2==true && sUOM.equals(UOM) && sDataEntered.equals(sDateEntered))
					{
						sFlag = true;
					}
					else
					{
						sFlag = false;
						System.err.println("sVendorPartNum is "+sVendorPN+" sDesc is "+sDesc+" sPrice is  "+sPrice+ " sUOM is "+sUOM+" sDataEntered is  "+sDataEntered+ " sDataRequired is "+sDataRequired);
						Assert.fail("sVendorPartNum is "+sVendorPN+" sDesc is "+sDesc+" sPrice is  "+sPrice+ " sUOM is "+sUOM+" sDataEntered is  "+sDataEntered+ " sDataRequired is "+sDataRequired);
					}
					//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),"00001-Epace Default Normal Account");
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), "00001-Epace Default Normal Account"); 
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys("2");
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys("2");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);
				} 


			}
		}
		return sFlag;
	}


	public void AddNewInventory() throws Exception
	{

		ICPage IC = new ICPage(_driver);
		IC.NavigateToInventoryItemListPage();
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		}
		Thread.sleep(8000);
		assertEquals("Adding Inventory Item", _driver.getTitle());
	}

	public void AddJobJobPartAC(String sJob,String sJobPart,String sAC) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(sJob);
		_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
		Thread.sleep(3000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), sAC);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Activity_Code))).sendKeys(Keys.TAB);

	}

	public String  FetchVersion()
	{
		String sVersion = _driver.findElement(By.xpath("//div[@id='poweredby']/div[1]/div[1]")).getText();
		System.out.println("Version is "+sVersion);

		String sVer = sVersion.substring(0, Math.min(sVersion.length(), 2));

		System.out.println("Version  is  "+sVer);
		return sVer;
	}
	public void EnterInventoryGeneralInfo(String sInv,String sDesc,String sAddDesc,String sItemType,String UOM,String Vendor,String Customer,String Comp,String sStatus,String sAC,String sMRAC,String SalesCat,String ItemTemp,boolean sActive,boolean sAutopost) throws Exception, IOException
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(sInv);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Addl_Desc))).sendKeys(sAddDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), sItemType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), UOM);
		Thread.sleep(1000);
		/*	if(_driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Vendor))).size()>0)
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Vendor))).sendKeys(Vendor);
	}*/

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Customer))).sendKeys(Customer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Owner))).sendKeys(Comp);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Status)), sStatus);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Activity_Code)), sAC);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Make_Ready_AC)), sMRAC);
		Thread.sleep(1000);
		/*CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Sales_Category)), SalesCat);
	Thread.sleep(1000);
	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Template)), ItemTemp);
	Thread.sleep(1000);*/
		sSelectCheckBox(sActive, By.name(Locators.getProperty(Locators.Inventory_Active)));
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			sSelectCheckBox(sAutopost, By.name(Locators.getProperty(Locators.Inventory_Auto_post)));
		}
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "GeneralInfo");

	}

	public void EntereServiceInfo(String sProd,String sSubPro,String sUnitWeight) throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_eService_info))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Inventory_eService_Unit_Weight)));
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_eService_Product_Group)), sProd);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_eService_Sub_Prod_Key)), sSubPro);
			Thread.sleep(1000);
		}
		_driver.findElement(By.xpath("//a[contains(text(),'eneral Info')]")).click();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_eService_Unit_Weight))).sendKeys(sUnitWeight);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "SServiceinfo");
	}


	public void EnterOrderingInfo(String sMixStock,String sMaxStock,String VendorNum,String LeadTime,boolean sTaxable) throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Ordering_Info))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Min_Stock_Level)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Min_Stock_Level))).sendKeys(sMixStock);	
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Max_Stock_Level))).sendKeys(sMaxStock);	
		String sVersion=  FetchVersion();
		if(Integer.valueOf(sVersion)< 27)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).sendKeys(VendorNum);
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Lead_Time))).sendKeys(LeadTime);	
		}

		sSelectCheckBox(sTaxable, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "OrderInfo");
	}	

	public boolean VerifyWarningMessageOnLineItemType(String sInv,String sLineType) throws Exception
	{
		boolean sFlag = false,sFlag1 =false,finalValue = false;
		String sInvItem ="",sInvItem1="",sInvItem2="",sInvItem3="";
		String sText="Required for non description line types",sInvText="",sInvIdText="",sJobText="Invalid value",sGLAccountText="GLAccount required on non-description lines",sUOMText1="Required for non description line types";
		String sQtyText = "Quantity Ordered: Required for non description line types",sUnitPriceText="Unit Price: Required for non description line types",sUOMText="UOM: Required for non description line types",sGLAccText="GL Account: GLAccount required on non-description lines";
		String  originalHandle = _driver.getWindowHandle();
		Set<String> availableWindows = _driver.getWindowHandles();

		String sGLAcount= "", sGLAcount1= "", sQtyReqW= "", sQtyReqW1= "", sUnitPrice= "", sUnitPrice1= "", sUOM= "", sUOM1= "", sjobW= "", sjobW1 = "";

		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)),sLineType);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(2000);

					if (sLineType.equals("Inventory"))
					{

						//------------verify Quantity warning message-------------------------//

						System.out.println("verify Quantity warning message-");
						sQtyReqW= _driver.findElement(By.xpath("//label[text() = 'Quantity Ordered']/../..//span")).getText();
						sQtyReqW1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li[1]")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys("2");
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.TAB);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//------------verify Unit Price warning message-------------------------//
						System.out.println("verify Unit Price warning message-");
						sUnitPrice= _driver.findElement(By.xpath("//div/h4[contains(label,'Unit Price')]/following-sibling::span")).getText();
						sUnitPrice1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys("2");
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 0);
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), "");
						CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), "");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//------------verify UOM warning message-------------------------//
						System.out.println("verify UOM warning message-");	 
						sUOM= _driver.findElement(By.xpath("//div/h4[contains(label,'UOM')]/following-sibling::span")).getText();
						sUOM1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)),"EA-Each");
						//------------verify JOB warning message-------------------------//
						System.out.println("verify JOB warning message");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys("@@@");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						sjobW= _driver.findElement(By.xpath("//label[text()= 'Job/Job Part']/../..//span")).getText();
						sjobW1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);

						sInvText="Required for Inventory line types";sInvIdText="Invalid value";
						//------------verify Inventory item warning message-------------------------//
						System.out.println("verify Inventory item warning message");
						sInvItem= _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[4]/fieldset/div[1]//span")).getText();
						sInvItem1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul")).getText();

						//------------verify Inventory item invalid id warning message-------------------------//
						System.out.println("verify Inventory item invalid id warning message");
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys("@@@");
						_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						CommonFunctions.getPopupMessage(_driver);

						sInvItem2= _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[4]/fieldset/div[1]//span")).getText();
						sInvItem3= _driver.findElement(By.xpath("//div[@id='form-errors']/ul")).getText(); 
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
						_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
						Thread.sleep(5000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear(); 
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);

						sGLAcount= sGLAccountText;
						sGLAcount1= sGLAccText;
						sUOMText1 = "Required for Inventory line types";
						sUOMText = "UOM: Required for Inventory line types";
					}				 
					else
					{
						//------------verify Quantity warning message-------------------------//

						System.out.println("verify Quantity warning message-");
						sQtyReqW= _driver.findElement(By.xpath("//label[text() = 'Quantity Ordered']/../..//span")).getText();
						sQtyReqW1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li[1]")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys("2");
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Keys.TAB);
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys("");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//------------verify Unit Price warning message-------------------------//
						System.out.println("verify Unit Price warning message-");
						sUnitPrice= _driver.findElement(By.xpath("//div/h4[contains(label,'Unit Price')]/following-sibling::span")).getText();
						sUnitPrice1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys("2");
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)),"");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//------------verify UOM warning message-------------------------//
						System.out.println("verify UOM warning message-");	 
						sUOM= _driver.findElement(By.xpath("//div/h4[contains(label,'UOM')]/following-sibling::span")).getText();
						sUOM1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)),"EA-Each");
						//------------verify JOB warning message-------------------------//
						System.out.println("verify JOB warning message");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys("@@@");
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						sjobW= _driver.findElement(By.xpath("//label[text()= 'Job/Job Part']/../..//span")).getText();
						sjobW1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul")).getText();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), 0);
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), "");
						CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), "");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//------------verify GL Account warning message-------------------------//
						System.out.println("verify GL Account warning message");
						sGLAcount= _driver.findElement(By.xpath("//div/h4[contains(label,'GL Account')]/following-sibling::span")).getText();
						sGLAcount1= _driver.findElement(By.xpath("//div[@id='form-errors']/ul/li")).getText();
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),1);
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						//					 sGLAcount= sGLAccountText;
						//					 sGLAcount1= sGLAccText;
					}			 			 

					//				 if(sjobW.equals(sJobText) && sQtyReqW.equals(sText) && sUnitPrice.equals(sText) && sUOM.equals(sText) && sInvItem.equals(sInvText) &&  sInvItem2.equals(sInvIdText) && sGLAcount.equals(sGLAccountText) )
					if(sjobW.contains(sJobText) && sQtyReqW.contains(sText) && sUnitPrice.contains(sText) && sUOM.contains(sUOMText1) && sInvItem.contains(sInvText) &&  sInvItem2.contains(sInvIdText) && sGLAcount.contains(sGLAccountText) )
					{
						sFlag = true;
						System.out.println("Verified all warning");
					}
					else
					{
						sFlag = false; 
						Assert.fail("sQtyReqW is "+ sQtyReqW+" sUnitPrice is "+sUnitPrice+" sUOM is  "+sUOM+" sInvItem is "+sInvItem+" sGLAcount is "+sGLAcount);
					}
					//				 if(sjobW1.equals(sJobText) && sQtyReqW1.equals(sQtyText) && sUnitPrice1.equals(sUnitPriceText) && sUOM1.equals(sUOMText) && sInvItem1.equals(sInvText) && sInvItem3.equals(sInvIdText) && sGLAcount1.equals(sGLAccText))
					if(sjobW1.contains(sJobText) && sQtyReqW1.contains(sQtyText) && sUnitPrice1.contains(sUnitPriceText) && sUOM1.contains(sUOMText) && sInvItem1.contains(sInvText) && sInvItem3.contains(sInvIdText) && sGLAcount1.contains(sGLAccText))
					{
						sFlag1 = true;
						System.out.println("Verified all warning");
					}
					else
					{
						sFlag1 = false; 
						Assert.fail("sQtyReqW1 is "+ sQtyReqW1+" sUnitPrice1 is "+sUnitPrice1+" sUOM1 is  "+sUOM1+" sInvItem1 is "+sInvItem1+" sGLAcount1 is "+sGLAcount1);
					}
					if(sFlag == true && sFlag1== true)
					{
						finalValue = true;
					}
					else
					{
						finalValue= false;
					}
				}
			}
		}
		//	_driver.switchTo().window(originalHandle);
		return finalValue;
	}

	public void AddLineItemType(String sInv,String sLineType,String Qty,String UnitPrice,String GLAccount,boolean sTaxable) throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)),sLineType);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(5000);
					CommonFunctions.SendValue(_driver, By.xpath("//input[@name='dateRequired']"), "t");
					if(CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM))).equals(""))
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Qty);
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).clear();
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys(Qty);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),GLAccount);
					sSelectCheckBox(sTaxable, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));				 
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(1000);

				} 


			}
		}

	}	

	public String AddLineItemTypeReturnQtyOrder(String sInv,String sLineType,String Qty,String UnitPrice,String GLAccount,boolean sTaxable) throws Exception
	{
		String sOrderQty="";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)),sLineType);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(5000);
					if(CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM))).equals(""))
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
					}
					sOrderQty= 	_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).getAttribute("value");
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Qty);
					//				sOrderQty= 	_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).getAttribute("value");
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).clear();
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys(Qty);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)),1);
					//	 CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),GLAccount);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),1);
					sSelectCheckBox(sTaxable, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

				} 


			}
		}
		return sOrderQty;
	}	
	public void AddLineItemTypeWithJob(String sInv,String sLineType,String Qty,String UnitPrice,String GLAccount,boolean sTaxable,String sJob,String sJobPart,String sAC) throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)),sLineType);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(5000);
					if(CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM))).equals(""))
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Qty);
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).clear();
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys(Qty);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),GLAccount);
					sSelectCheckBox(sTaxable, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));
					AddJobJobPartAC(sJob, sJobPart, sAC);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(2000);

				} 


			}
		}

	}	
	public void EditLineITem(String sInv,String Qty,String UnitPrice,String GLAccount) throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();		
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);

					// _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).clear();		
					// _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).sendKeys("Vendor123");
					Thread.sleep(5000);
					if(CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM))).equals(""))
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Qty);
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).clear();
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys(Qty);
					Thread.sleep(1000);

					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),2);
					String sGLAccount =CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));
					// CommonFunctions.selectDropdownByText(_driver, By.name("glDepartment"),"000 - Default Department");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(2000);

				} 


			}
		}

	}	
	public String EditLineITem1(String sInv,String Qty,String UnitPrice,String GLAccount) throws Exception
	{
		String sGLAccount="";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).clear();
					Thread.sleep(1000);
					if (isAlertPresent())
					{
						AcceptModalDialog();
						Thread.sleep(2000);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					_driver.findElement(By.name("taxableBooleanCheck")).sendKeys(Keys.TAB);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
					Thread.sleep(1000);
					// _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).clear();		
					// _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level))).sendKeys("Vendor123");
					if (isAlertPresent())
					{
						AcceptModalDialog();
					} 
					Thread.sleep(5000);	 
					if(CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM))).equals(""))
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
					}
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).sendKeys(Qty);
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).clear();
					//				_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).sendKeys(Qty);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)),2);
					sGLAccount =CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));
					// CommonFunctions.selectDropdownByText(_driver, By.name("glDepartment"),"000 - Default Department");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(5000);

				} 


			}
		}
		return sGLAccount;
	}	


	public void DuplicateLineTypeItem() throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{
					Thread.sleep(2000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
					Thread.sleep(10000);
				}
			}
		}
	}
	public void DeleteLineTypeItem() throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(2000);
					Thread.sleep(2000);
				}
			}
		}
	}
	public boolean DeleteLineTypeItemWhichisReceive() throws Exception
	{
		boolean sFlag=false;
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(2000);
					String sMessage = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();
					if(sMessage.contains(": Can't delete a purchase order line with received quantity") && sMessage.contains("PurchaseOrderLine"))
					{
						sFlag = true;
					}
					else
					{
						sFlag = false;
					}
					_driver.close();
				}
			}
		}

		return sFlag;
	}
	public boolean VerifyModifiedValesLineITem(String sInv,String Qty,String UnitPrice,String GLAccount) throws Exception
	{ 
		boolean sFlag =false;
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase order line Inventory item for PO Inventory item for PO")) 
				{

					String sInventoryItem=	 _driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).getAttribute("value");
					sInventoryItem=sInventoryItem.trim();
					System.out.println("Actual sInventoryItem is "+sInventoryItem+" expected sInventory Item is "+sInv);

					String sQtyOrdered =		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered))).getAttribute("value");
					//			String sQtyOrdered =		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Required))).getAttribute("value");
					sQtyOrdered=sQtyOrdered.trim();
					System.out.println("Actual Qty is "+sQtyOrdered+" expected Qty is "+Qty);

					String sUnitPrice=	_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).getAttribute("value");
					sUnitPrice=sUnitPrice.trim();
					System.out.println("Actual Unit price is "+sUnitPrice+" expected Unit price is "+UnitPrice);

					String sGLAccount 	= CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)));
					sGLAccount=sGLAccount.trim();
					System.out.println("Actual GLAccount  is "+sGLAccount+" expected GLAccount is "+GLAccount);
					//	 CommonFunctions.selectDropdownByText(_driver, By.name("glDepartment"),"000 - Default Department");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(3000);

					if(sInventoryItem.equals(sInv) && sQtyOrdered.equals(Qty) && sUnitPrice.equals(UnitPrice) && sGLAccount.equals(GLAccount))
					{
						System.out.println("Values got modified");
						sFlag = true;
					}
					else
					{	
						sFlag = false;
						Assert.fail("Values not got modified sInventoryItem is  "+sInventoryItem+" sQtyOrdered is "+sQtyOrdered+" sUnitPrice is "+sUnitPrice+" sGLAccount is "+sGLAccount);
					}
				} 


			}
		}
		return	sFlag;
	}	



	public boolean AddReceiveDetailsForLineItem(String sNote,String UnitPrice,String sQTy) throws Exception
	{ 
		boolean sFlag =false;
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Receive Purchase Order")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Receiving_Note))).sendKeys(sNote);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
					Thread.sleep(1000); 
					_driver.findElement(By.name(Locators.getProperty(Locators.Receive_PO_Qty_To_Receive))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Receive_PO_Qty_To_Receive))).sendKeys(sQTy);
					Thread.sleep(1000); 
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Receive_PO_Receive_Button))).click();
					Thread.sleep(1000);
				}
			}
		}
		return	sFlag;
	}	


	public void ChangeSaleTax(By locator,String sCountry,String sRate1) throws Exception
	{
		_driver.findElement(locator).click();
		Thread.sleep(1000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Sales Tax Code "+sCountry)) 
				{
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.SalesTax_Rate)));
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax1)), sRate1);
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax2)), "0");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax3)), "0");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax4)), "0");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax5)), "0");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax6)), "0");
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.SalesTax7)), "0");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(1000);
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					_driver.close();
				} 		
			}
		}



	}

	public boolean PleaseSelectContact(String sSearch,String sVendor,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Please select a Contact")) 
				{
					System.out.println("Search  vendor");
					DC.SearchValue(sVendor,sSearch);
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div")).click();
					Thread.sleep(1000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}
	public boolean SelectContact(String sSearch,String sVendor,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Please select a Contact")) 
				{
					System.out.println("Search  vendor");
					DC.SearchValue(sVendor,sSearch);
					Thread.sleep(2000);
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div")).click();
					Thread.sleep(1000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}
	public void SetTaxBaseOrTaxAmount(By locators,String sAmount) throws Exception
	{
		_driver.findElement(locators).clear();
		_driver.findElement(locators).sendKeys(sAmount);
		_driver.findElement(locators).sendKeys(Keys.CONTROL + "a");
		_driver.findElement(locators).sendKeys(Keys.DELETE);
		_driver.findElement(locators).sendKeys(sAmount);
		_driver.findElement(locators).sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}
	public String VerifyNewLineItemDisabled() throws Exception
	{
		String sFlag="0";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		_driver.findElement(By.xpath("//a[text()='Line Items']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(6000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{

					boolean sLineType	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)));
					System.out.println("sLineType is "+sLineType);
					boolean sJob	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Job)));
					System.out.println("sJob is "+sJob);
					boolean sJobPart	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)));
					System.out.println("sJobPart is "+sJobPart);
					boolean sInventoryItem	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Item)));
					System.out.println("sInventoryItem is "+sInventoryItem);
					boolean sVendorPartNum	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level)));
					System.out.println("sVendorPartNum is "+sVendorPartNum);
					boolean sQtyOrdered = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered)));
					System.out.println("sQtyOrdered is "+sQtyOrdered);
					boolean sUnitPrice = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price)));
					System.out.println("sUnitPrice is "+sUnitPrice);
					boolean sUOM = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)));
					System.out.println("sUOM is "+sUOM);
					boolean sTaxable = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));
					System.out.println("sTaxable is "+sTaxable);
					if(sLineType == false && sJob == false && sJobPart == false && sInventoryItem == false && sVendorPartNum == false && sQtyOrdered == false && sUnitPrice== false && sUOM==false  && sTaxable == true )
					{
						System.out.println("Line item,job,jobpart,inventoryitem,vendor part num,qty ordered,unit price, uom, activity code,taxable are disabled");
						sFlag="1";
						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[2]/div/div/a")).getText();
						sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
						sPOOrder = sPOOrder.replace(")","");
						if (sPOOrder.contains("("))
						{
							String[] aPOOrder = sPOOrder.split("\\(");
							sPOOrder = aPOOrder[0].trim();
						}
						System.out.println("PO Order is "+sPOOrder);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+sPOOrder);
						return sFlag;
					}
					else
					{
						System.err.println("Failed to verify sLine item,job,jobpart,inventoryitem,vendor part num,qty ordered,unit price, uom, activity code,taxable");
						Assert.fail("sLineType is enabled "+sLineType+" sJob is enabled "+sJob+" sJobPart is enabled "+sJobPart+" sInventoryItem is enabled "+sInventoryItem+" Vendor Part Num is enabled "+sVendorPartNum+" qty ordered is enabled "+sQtyOrdered+" Unitprice enable "+sUnitPrice+" Uom enabled "+sUOM+" Taxable enabled "+sTaxable);
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

				}
			}
		}

		return sFlag;		
	}


	public String VerifyNewLineItemEnabled() throws Exception
	{
		String sFlag="0";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		_driver.findElement(By.xpath("//a[text()='Line Items']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(6000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Purchase Order Line")) 
				{

					boolean sLineType	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)));
					System.out.println("sLineType is "+sLineType);
					boolean sJob	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Job)));
					System.out.println("sJob is "+sJob);
					boolean sJobPart	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)));
					System.out.println("sJobPart is "+sJobPart);
					boolean sInventoryItem	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Item)));
					System.out.println("sInventoryItem is "+sInventoryItem);
					boolean sVendorPartNum	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Vendor_Part_Level)));
					System.out.println("sVendorPartNum is "+sVendorPartNum);
					boolean sQtyOrdered = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered)));
					System.out.println("sQtyOrdered is "+sQtyOrdered);
					boolean sUnitPrice = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price)));
					System.out.println("sUnitPrice is "+sUnitPrice);
					boolean sUOM = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)));
					System.out.println("sUOM is "+sUOM);
					boolean sTaxable = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Taxable)));
					System.out.println("sLineType is "+sLineType);

					if(sLineType == true && sJob == true  && sInventoryItem == true && sVendorPartNum == true && sQtyOrdered == true && sUnitPrice== true && sUOM==true && sTaxable == true )
					{
						System.out.println("Line item,job,jobpart,inventoryitem,vendor part num,qty ordered,unit price, uom, activity code,taxable are enabled");
						sFlag="2";
						String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[2]/div/div/a")).getText();
						sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
						sPOOrder = sPOOrder.replace(")","");
						if (sPOOrder.contains("("))
						{
							String[] aPOOrder = sPOOrder.split("\\(");
							sPOOrder = aPOOrder[0].trim();
						}
						System.out.println("PO Order is "+sPOOrder);
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals("Purchase order "+sPOOrder);
						return sFlag;
					}
					else
					{
						System.err.println("Failed to verify sLine item,job,jobpart,inventoryitem,vendor part num,qty ordered,unit price, uom, activity code,taxable");
						Assert.fail("sLineType is enabled "+sLineType+" sJob is enabled "+sJob+" sJobPart is enabled "+sJobPart+" sInventoryItem is enabled "+sInventoryItem+" Vendor Part Num is enabled "+sVendorPartNum+" qty ordered is enabled "+sQtyOrdered+" Unitprice enable "+sUnitPrice+" Uom enabled "+sUOM+" Taxable enabled "+sTaxable);
					}

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Type");

				}
			}
		}

		return sFlag;		
	}


	public String VerifyTaxableChangesDisabled() 
	{
		String sFlag="0";

		boolean sSaleTax1	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_LineItem_Salestax1)));
		boolean sSaleTax2	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_LineItem_Salestax2)));
		boolean sTaxableCode1	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_LineItem_Taxable_Code1)));
		boolean sTaxableCode2	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_LineItem_Taxable_Code2)));
		boolean sTaxbase1	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Tax_Base1)));
		boolean sTaxbase2 = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Tax_Base2)));
		boolean sTaxAmount1 = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Tax_Amount1)));
		boolean sTaxAmount2 = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Tax_Amount2)));

		if(sSaleTax1 == false && sSaleTax2 == false && sTaxableCode1 == false && sTaxableCode2 == false && sTaxbase1 == false && sTaxbase2 == false && sTaxAmount1==false && sTaxAmount2 == false )
		{
			System.out.println("Taxable Changes are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sSaleTax1 == true && sSaleTax2 == true && sTaxableCode1 == true && sTaxableCode2 == true && sTaxbase1 == true && sTaxbase2 == true && sTaxAmount1==true && sTaxAmount2 == true )
		{
			System.out.println("Taxable Changes are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			Assert.fail("sSaleTax1 enabled "+sSaleTax1+" sSaleTax2 enabled "+sSaleTax2+" sTaxableCode1 enabled "+sTaxableCode1+" sTaxableCode2 enabled "+sTaxableCode2+" sTaxbase1 enabled "+sTaxbase1+" sTaxbase2 enabled "+sTaxbase2+" sTaxAmount1 enabled "+sTaxAmount1+" sTaxAmount2 enabled "+sTaxAmount2);
		}
		return sFlag;
	}


	public String VerifySummaryInfoDisabled() throws Exception 
	{
		String sFlag="0";
		System.out.println("Navigate to Summary Info tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Summary_Information))).click();
		Thread.sleep(2000);
		boolean sPOType	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)));
		boolean sConfirmedBy	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Confirmed_by)));
		boolean sDateConfirmed	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Date_Confirmed)));
		boolean sShipVia	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Ship_Via)));
		boolean sFreightOnBoard	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Freight_On_Board)));
		boolean sTerm = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Terms)));
		boolean sAuthorizedBy = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Authorized_By)));
		boolean sRequester = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Requester)));
		boolean sBuyer = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Buyer)));
		boolean sNotes = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Notes)));

		if(sPOType == false && sConfirmedBy == false && sDateConfirmed == false && sShipVia == false && sFreightOnBoard == false && sTerm == false && sAuthorizedBy==false && sRequester == false && sBuyer == false && sNotes == false )
		{
			System.out.println("Summary Info are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sPOType == true && sConfirmedBy == true && sDateConfirmed == true && sShipVia == true && sFreightOnBoard == true && sTerm == true && sAuthorizedBy==true && sRequester == true && sBuyer == true && sNotes == true )
		{
			System.out.println("Summary Info are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			System.err.println("Summary Info are enabled");
			Assert.fail("sPOType enabled "+sPOType+" sConfirmedBy enabled "+sConfirmedBy+" sDateConfirmed enabled "+sDateConfirmed+" sShipVia enabled "+sShipVia+" sFreightOnBoard enabled "+sFreightOnBoard+" sTerm enabled "+sTerm+" sAuthorizedBy enabled "+sAuthorizedBy+" sRequester enabled "+sRequester+" sBuyer enabled "+sBuyer+" sNotes enabled "+sNotes);
		}
		return sFlag;
	}

	public String VerifySummaryInfoDisabledForOrderStatusPending() throws Exception 
	{
		String sFlag="0";
		System.out.println("Navigate to Summary Info tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Summary_Information))).click();
		Thread.sleep(2000);
		boolean sPOType	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)));
		boolean sConfirmedBy	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Confirmed_by)));
		boolean sDateConfirmed	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Date_Confirmed)));
		boolean sShipVia	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Ship_Via)));
		boolean sFreightOnBoard	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Freight_On_Board)));
		boolean sTerm = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Terms)));
		boolean sAuthorizedBy = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Authorized_By)));
		boolean sNotes = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Summary_Info_Notes)));

		if(sPOType == false && sConfirmedBy == false && sDateConfirmed == false && sShipVia == false && sFreightOnBoard == false && sTerm == false && sAuthorizedBy==false && sNotes == false )
		{
			System.out.println("Summary Info are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sPOType == true && sConfirmedBy == true && sDateConfirmed == true && sShipVia == true && sFreightOnBoard == true && sTerm == true && sAuthorizedBy==true &&  sNotes == true )
		{
			System.out.println("Summary Info are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			System.err.println("Summary Info are enabled");
			Assert.fail("sPOType enabled "+sPOType+" sConfirmedBy enabled "+sConfirmedBy+" sDateConfirmed enabled "+sDateConfirmed+" sShipVia enabled "+sShipVia+" sFreightOnBoard enabled "+sFreightOnBoard+" sTerm enabled "+sTerm+" sAuthorizedBy enabled "+sAuthorizedBy+" sNotes enabled "+sNotes);
		}
		return sFlag;
	}
	public String  VerifyAddressInfoDisabled() throws Exception 
	{
		String sFlag="0";
		System.out.println("Navigate to Address Info tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Address_Information))).click();
		Thread.sleep(2000);
		boolean sVendor= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Vendor)));
		boolean sVendorContact	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor_Contact)));
		boolean sShipToContact	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Default_Ship_To_Contact)));

		if(sVendor == false && sVendorContact == false && sShipToContact == false)
		{
			System.out.println("Address Info are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sVendor == true && sVendorContact == true && sShipToContact == true)
		{
			System.out.println("Address Info are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			System.err.println("Address Info are enabled");
			Assert.fail("sVendor enabled "+sVendor+" sVendorContact enabled "+sVendorContact+" sShipToContact enabled "+sShipToContact);
		}
		return sFlag;
	}

	public String  VerifyAddressInfoDisabledWhenOrderStatusISPending() throws Exception 
	{
		String sFlag="0";
		System.out.println("Navigate to Address Info tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Address_Information))).click();
		Thread.sleep(2000);
		boolean sVendor= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Inventory_Vendor)));
		boolean sVendorContact	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor_Contact)));
		boolean sShipToContact	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Default_Ship_To_Contact)));

		if(sVendor == false && sVendorContact == false && sShipToContact == true)
		{
			System.out.println("Address Info are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sVendor == true && sVendorContact == true && sShipToContact == true)
		{
			System.out.println("Address Info are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			System.err.println("Address Info are enabled");
			Assert.fail("sVendor enabled "+sVendor+" sVendorContact enabled "+sVendorContact+" sShipToContact enabled "+sShipToContact);
		}
		return sFlag;
	}
	public String VerifyReceiveInfoDisabled() throws Exception 
	{
		String sFlag="0";
		boolean sOrderStatus= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)));
		boolean Receive_PO_Recieved_Date	= CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Receive_PO_Recieved_Date)));


		if(sOrderStatus == false && Receive_PO_Recieved_Date == false)
		{
			System.out.println("Receive Info are disabled");
			sFlag = "1";
			return sFlag;
		}
		else if(sOrderStatus == true && Receive_PO_Recieved_Date == true)
		{
			System.out.println("Receive Info are disabled");
			sFlag = "2";
			return sFlag;
		}
		else
		{
			System.err.println("Receive Info are enabled");
			Assert.fail("sOrderStatus enabled "+sOrderStatus+" Receive_PO_Recieved_Date enabled "+Receive_PO_Recieved_Date);
		}
		return sFlag;
	}

	public boolean VerifyEmailPage(String sPONum)
	{
		boolean sFlag =false,Verfied =false;
		boolean sToRecipientImg = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img))).click();
		boolean sToRecipientField = CommonFunctions.isElementPresent(_driver, By.id(Locators.getProperty(Locators.PO_EMail_TO_TextField)));
		boolean sCCRecipientImg = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_EMail_CC_img)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_CC_img))).click();
		boolean sCCRecipientField = CommonFunctions.isElementPresent(_driver, By.id(Locators.getProperty(Locators.PO_EMail_CC_TextField)));
		boolean sBCCRecipientImg = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_EMail_BCC_img)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_BCC_img))).click();
		boolean sBCCRecipientField = CommonFunctions.isElementPresent(_driver, By.id(Locators.getProperty(Locators.PO_EMail_BCC_TextField)));
		boolean sDragEmailAddress = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_Email_Drag_Email_Address_Here)));
		boolean sReplyTo = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)));
		boolean sReplyToName = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name)));
		boolean sSubject = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Email_Subject)));
		boolean sTemplate = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Email_Template)));
		boolean sBody = CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PO_Email_Body)));
		String sSubjectPrsent =  _driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).getAttribute("value");
		if(sSubjectPrsent.equals("PurchaseOrder ("+sPONum+")"))
		{
			sFlag = true;
		}
		if(sToRecipientImg == true && sToRecipientField == true && sCCRecipientImg== true && sCCRecipientField==true && sBCCRecipientImg==true && sBCCRecipientField==true && sDragEmailAddress==true && sReplyTo==true && sReplyToName==true && sSubject==true && sTemplate ==true && sBody==true && sFlag ==true)
		{
			Verfied = true;
			return Verfied;
		}
		else
		{
			Assert.fail("sToRecipientImg is "+sToRecipientImg+" sToRecipientField is "+sToRecipientField+" sCCRecipientImg is "+sCCRecipientImg+" sCCRecipientField is "+sCCRecipientField+" sBCCRecipientImg is "+sBCCRecipientImg+" sBCCRecipientField is "+sBCCRecipientField+" sDragEmailAddress is "+sDragEmailAddress+" sReplyTo is "+sReplyTo+" sReplyToName is "+sReplyToName+" sSubject is "+sSubject+" sTemplate is "+sTemplate+" sBody is "+sBody+" sFlag is "+sFlag);
		}
		return Verfied;	
	}




	public void EnterEmailDetails(String sTO,String sCC,String sBCC,String sReplyTo,String sReplyToName,String sSub,String sTemplate,String sBody) throws Exception
	{
		//_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img))).click();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img))).click();
		Thread.sleep(1000);

		Actions action = new Actions(_driver);
		WebElement we = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img)));
		action.moveToElement(we).moveToElement(_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img)))).click().build().perform();

		//_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_TO_TextField))).click();
		_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_TO_TextField))).sendKeys(sTO);
		Thread.sleep(1000);
		/*
	_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).click();
	Thread.sleep(1000);
	_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_CC_TextField))).click();
	_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_CC_TextField))).sendKeys(sCC);
	Thread.sleep(1000);

	_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).click();
	Thread.sleep(1000);
	_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_BCC_TextField))).click();
	_driver.findElement(By.id(Locators.getProperty(Locators.PO_EMail_BCC_TextField))).sendKeys(sBCC);
	Thread.sleep(1000);
		 */


		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name))).sendKeys(sReplyToName);

		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).sendKeys(sSub);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Email_Template)), sTemplate);
		Thread.sleep(1000);

		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Body))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Body))).sendKeys(sBody);

		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).sendKeys(sReplyTo);

	}

	public boolean VerifyDetailsPage()
	{
		boolean sFlag =false;
		boolean sPONum =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_POrder_Text)));
		boolean sVendor =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_Vendor_Text)));
		boolean sSaleTax =CommonFunctions.isElementPresent(_driver, By.name("orderStatus"));
		boolean sTaxBase =CommonFunctions.isElementPresent(_driver, By.xpath("//label[text()='Tax Base1']"));
		System.out.println("sPONum is "+sPONum+" sVendor "+sVendor+" sSaleTax "+sSaleTax+" sTaxBase "+sTaxBase);
		if(sPONum == true && sVendor== true && sSaleTax== true &&  sTaxBase == true)
		{
			sFlag = true;
			return sFlag;
		}
		return sFlag;	
	}
	public boolean VerifyPODetailsPage(String sPON)
	{
		boolean sFlag =false;
		boolean sPONum =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_POrder_Text)));
		String sPO = _driver.findElement(By.xpath("//div/h4[contains(label,'PO Number')]/following-sibling::div/div")).getText();
		sPO = sPO.trim();
		boolean sVendor =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.PO_Vendor_Text)));
		if(sPONum == true && sVendor== true && sPO.equals(sPON))
		{
			sFlag = true;
			return sFlag;
		}
		return sFlag;	
	}

	public boolean VerifyReceivePage()
	{
		boolean sFlag =false;
		boolean sReturnToDetails =CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='Return to Detail']"));
		boolean sUpdateAndReceive =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Receive_PO_Update_And_Receive_Button)));
		if(sReturnToDetails == true && sUpdateAndReceive== true)
		{
			sFlag = true;
			return sFlag;
		}
		return sFlag;	
	}
	public void SendEmail() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(2000);
	}



	public boolean VerifyPOReceiptPage(String sPONUM) throws Exception
	{
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		int i =0;
		_driver.findElement(By.xpath("//table[@id='Open']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(15000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Purchase Order Receipt")) 
				{
					boolean sStatus = CommonFunctions.isElementPresent(_driver, By.name("status"));
					String sPOOrder = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[2]/div[1]/div/div[1]")).getText();
					sPOOrder = sPOOrder.replace("PurchaseOrder (", "");
					sPOOrder = sPOOrder.replace(")","");
					if (sPOOrder.contains("("))
					{
						String[] aPOOrder = sPOOrder.split("\\(");
						sPOOrder = aPOOrder[0].trim();
					}
					System.out.println("PO Order is "+sPOOrder);
					if(sStatus == true && sPOOrder.equals(sPONUM))
					{
						sFlag = true;
					}
					else
					{
						sFlag = false;
					}
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Receipts by Purchase Order - Open");		
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Purchase Order Receipts by Purchase Order - Open");

				}

			}
		}
		return sFlag;
	}

	public boolean VerifyTotalRecords() throws Exception
	{ 
		int RowCount = 0,RowCount1=0;
		String sTotalRecord =  _driver.findElement(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div/strong")).getText();
		System.out.println("Total Record is "+sTotalRecord);
		int TotalRecord = Integer.parseInt(sTotalRecord);
		System.out.println("Total Record is "+TotalRecord);

		RowCount1 = _driver.findElements(By.xpath("//table[@id='candidates']/tbody/tr")).size();
		System.out.println("Row Count is "+RowCount1);
		RowCount1 =RowCount+RowCount1;
		if(_driver.findElements(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div[2]/a[1]/img")).size()>0)
		{
			_driver.findElement(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div[2]/a[1]/img")).click();
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(3000);
			RowCount = _driver.findElements(By.xpath("//table[@id='candidates']/tbody/tr")).size();
			Thread.sleep(2000);
			RowCount1 =RowCount+RowCount1;
			System.out.println("Row Count is "+RowCount1);
		}
		while(_driver.findElements(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div[2]/a[3]/img")).size()>0)
		{
			_driver.findElement(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div[2]/a[3]/img")).click();
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(3000);	
			RowCount = _driver.findElements(By.xpath("//table[@id='candidates']/tbody/tr")).size();
			Thread.sleep(2000);
			RowCount1 =RowCount+RowCount1;
			System.out.println("Row Count is "+RowCount1);
		}
		System.out.println("Row Count  is "+RowCount1);
		if(TotalRecord == RowCount1)
		{
			return true;
		}
		else
		{
			System.out.println("Total Record is "+TotalRecord+" Actual Records present "+RowCount1);
			return false;
		}
	}


	public void VerifyAutoCreateTotalRecordsForCombination(boolean sOption1,boolean sOption2,boolean sOption3,int sMaterial,int sOutsidePurch , int sInvItem )
	{
		int T1=0,T2=0,T3=0,T4=0;
		String sTotalRecord =  _driver.findElement(By.xpath("//fieldset[@id='candidates_fieldset']/div/div[1]/div/strong")).getText();
		System.out.println("Total Record is "+sTotalRecord);
		int TotalRecord = Integer.parseInt(sTotalRecord);
		System.out.println("Total Record is "+TotalRecord);
		if(sOption1==true && sOption2==true && sOption3 == false)
		{
			T1= sMaterial+sOutsidePurch;
			if(T1 == TotalRecord )
			{
				System.out.println("Total Record is Materials line Order + OutSide Purch Order"+T1);
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819a", "Execution", "PASS");	
			}
			else
			{
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819a", "Execution", "FAIL");	
				Assert.fail("Miss Match in Total Record for T1 Excepted is "+T1+" Actual is "+TotalRecord);
			}
		}
		else if(sOption1==true && sOption2==false && sOption3== true)
		{
			T2= sMaterial+sInvItem;
			if(T2 == TotalRecord )
			{
				System.out.println("Total Record is Materials line Order + OutSide Purch Order"+T2);
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819b", "Execution", "PASS");	
			}
			else
			{
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819b", "Execution", "FAIL");	
				Assert.fail("Miss Match in Total Record for T2 Excepted is "+T2+" Actual is "+TotalRecord);
			}
		}
		else if(sOption1==false && sOption2==true && sOption3 == true)
		{
			T3 = sOutsidePurch+sInvItem;
			if(T3 == TotalRecord )
			{
				System.out.println("Total Record is Materials line Order + OutSide Purch Order"+T3);
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819c", "Execution", "PASS");	
			}
			else
			{
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819c", "Execution", "FAIL");	
				Assert.fail("Miss Match in Total Record for T2 Excepted is "+T3+" Actual is "+TotalRecord);
			}
		}
		else
		{
			T4= sMaterial+sOutsidePurch+sInvItem;
			if(T4 == TotalRecord )
			{
				System.out.println("Total Record is Materials line Order + OutSide Purch Order"+T4);
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819d", "Execution", "PASS");	
			}
			else
			{
				dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_819d", "Execution", "FAIL");	
				Assert.fail("Miss Match in Total Record for T4 Excepted is "+T4+" Actual is "+TotalRecord);
			}
		}

	}

	public void CreatePurchaseOrder(String sStatus) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.CreatePurchaseOrder))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PO_POrder_Text)));
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)), sStatus);
		Thread.sleep(1000);

	}


	public boolean VerifyAutoCreateSelectAll(String sloc,int sColumn) throws Exception
	{
		int RowCount1=0,Count=0;
		boolean sIsChecked = false,sFlag=false;

		if(_driver.findElements(By.xpath(sloc)).size()>0)
		{
			_driver.findElement(By.xpath(sloc)).click();
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(2000);

			RowCount1 = _driver.findElements(By.xpath("//table[@id='candidates']/tbody/tr")).size();
			System.out.println("Row Count is "+RowCount1);
			for(int i =1;i<=RowCount1;i++)
			{
				System.out.println("Row Number is "+i);
				sIsChecked =_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr["+i+"]/td["+sColumn+"]/input[2]")).isSelected();
				if(	sIsChecked == false)
				{
					Count++;
				}
			}
			if(Count==0)
			{
				sFlag =true;
				System.out.println("All the row are Selected");	
			}
			else
			{	
				Assert.fail("All Check box is not selected");
			}
		}
		else
		{
			dbConnection.UpdateFunction("PO_SetUp", "Auto_Create_PO", "P_820", "Execution", "FAIL");	
			Assert.fail("Not able to find Select All/Reviewed All");
		}
		return sFlag;
	}

	public boolean VerifyAutoCreatedPO(String OrderStatus,String Vendor,String sQty)
	{
		boolean sFlag =false;
		String sOrderStatus = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)));
		String sVendor = _driver.findElement(By.xpath(Locators.getProperty(Locators.PO_Vendor_Text))).getText();
		sVendor =sVendor.trim();
		String sQty1 = _driver.findElement(By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[1]/td[3]/div")).getText();
		sQty1 = sQty1.trim();
		String sQty2 = _driver.findElement(By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[2]/td[3]/div")).getText();
		sQty2 = sQty2.trim();
		if
		(sOrderStatus.equals(OrderStatus) && sVendor.equals(Vendor) && sQty1.equals(sQty) && sQty2.equals(sQty))
		{
			System.out.println("Created data is same as Entered");
			sFlag = true;
		}
		else
		{
			Assert.fail("Created data is OrderStatus "+sOrderStatus+" Vendor is "+sVendor+" sQty1 is  "+sQty1+" sQty2 is "+sQty2);
		}
		return sFlag;
	}

	public void AutoCreateSearchJob(String sJob) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Search_Option)), "Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Search_String))).sendKeys(sJob);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.POReview_Search))).click();
		Thread.sleep(1000);
	}

	public boolean VerifyItemsToReviewInAutoCreatePO(String sType)
	{
		boolean sFlag = false;
		String sJob = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[4]/div")).getText();
		sJob =sJob.trim();
		String sJobPart = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[5]/div")).getText();
		sJobPart =sJobPart.trim();	
		if(sType.equals("MaterialsLine"))
		{
			String sStockNum = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[8]/div")).getText();
			sStockNum =sStockNum.trim();
			if(!sJob.equals("") && !sJobPart.equals("") && !sStockNum.equals(""))
			{
				System.out.println("Verified for "+sType);
				sFlag = true;
			}
			else
			{
				Assert.fail("sJob is "+sJob+" sJobPart is "+sJobPart+" sStockNum is "+sStockNum);
			}
		}
		else if(sType.equals("CreateOutSidePurch"))
		{
			String sStockNum = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[9]/div")).getText();
			sStockNum =sStockNum.trim();
			if(!sJob.equals("") && !sJobPart.equals("") && sStockNum.equals(""))
			{
				System.out.println("Verified for "+sType);
				sFlag = true;
			}
			else
			{
				Assert.fail("sJob is "+sJob+" sJobPart is "+sJobPart+" sStockNum is "+sStockNum);
			}
		}
		else
		{
			String sStockNum = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[8]/div")).getText();
			sStockNum =sStockNum.trim();
			if(sJob.equals("") && sJobPart.equals("") && !sStockNum.equals(""))
			{
				System.out.println("Verified for "+sType);
				sFlag = true;
			}
			else
			{
				Assert.fail("sJob is "+sJob+" sJobPart is "+sJobPart+" sStockNum is "+sStockNum);
			}	
		}

		return sFlag;	

	}


	public void ModifyAnyRecordInAutoCreatePO(String sQty,String sDateRq,String sVendor)
	{
		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[6]/input[1]")).clear();
		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[6]/input[1]")).sendKeys(sQty);

		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[11]/input[1]")).clear();
		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[11]/input[1]")).sendKeys(sVendor);

		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[9]/input[1]")).clear();
		_driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[9]/input[1]")).sendKeys(sDateRq);

	}
	public boolean  VerifyChangesMadeonRecordInAutoCreatePO(String sQty,String sDateRq,String sVendor)
	{
		boolean sFlag = false;
		String sFetchQty = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[6]/input[1]")).getAttribute("value");
		sFetchQty =sFetchQty.trim();
		String sFetchDate = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[11]/input[1]")).getAttribute("value");
		sFetchDate = sFetchDate.trim();
		String sFetchVendor = _driver.findElement(By.xpath("//table[@id='candidates']/tbody/tr[1]/td[9]/input[1]")).getAttribute("value");
		sFetchVendor =sFetchVendor.trim();
		if(sFetchQty.equals(sQty) && sFetchDate.equals(sDateRq) && sFetchVendor.equals(sVendor))
		{	
			sFlag =  true;
		}
		else
		{
			Assert.fail("Changes are not made sFetchQty is "+sFetchQty+" sFetchDate is "+sFetchDate+" Vendor is "+sFetchVendor);
		}
		return sFlag;
	}



	public void EnterBuyOutDetails(String sDesc,String sQuoteNum,String sQty,String sMarkup,String sDateRequired,String sNotes,String sVendor) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		boolean sFlag= false;
		System.out.println(" Add New Record");
		_driver.findElement(By.xpath("//div[@id='contents']/div[10]/fieldset[1]/div[1]/div[1]/div[@id='buttonbox']/a")).click();
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	

				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Job Part Buy Out")) 
				{
					System.out.println("Enter Details");
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
					_driver.findElement(By.name(Locators.getProperty(Locators.Quote_Num))).sendKeys(sDesc);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys(sQty);
					_driver.findElement(By.name(Locators.getProperty(Locators.Outside_Purchase_Markup))).sendKeys(sMarkup);
					_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Required))).sendKeys(sDateRequired);
					_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Notes))).sendKeys(sNotes);
					_driver.findElement(By.name(Locators.getProperty(Locators.Vendor))).sendKeys(sVendor);
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).click();
					_driver.findElement(By.xpath("//div[@id='buttonbox']/input[1][@name='updateForm' and @value='Add']"));
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	


				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}	
	}

	public void CreatePurchaseFromBuyout(String sUnitPrice,String sSetupCost,String sUOM,String sMWeight) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(" Drill into the Buy Out line item ");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.BuyOut_Record_1))).click();
		Thread.sleep(1000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Job Part Buy Out")) 
				{
					System.out.println("Click on Create Purchase Order");
					_driver.findElement(By.name("unitPrice2")).sendKeys(sUnitPrice);
					CommonFunctions.selectDropdownByText(_driver, By.name("uom2"), sUOM);
					_driver.findElement(By.name("setupCost2")).sendKeys(sSetupCost);
					_driver.findElement(By.name("mWeight2")).sendKeys(sUOM);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.CreatePurchaseOrder))).click();
					Thread.sleep(2000);

					_driver.switchTo().window(originalHandle);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle);
				}

			}
		}
	}

	public interface IRangeCheck
	{
		String FindOptionForPOOrder(int val);
	}
	public class RangeChecker implements IRangeCheck
	{
		public String FindOptionForPOOrder(int sValue)
		{
			String sOption="";
			if(sValue==1)
				sOption = "C";
			else if(sValue==2)
				sOption =  "P";
			else if(sValue==3)
				sOption =  "R";
			else if(sValue==4)
				sOption = "Z";
			else if(sValue==5)
				sOption = "X";
			return sOption;
		}
	}

	public void PerformSearchAndVerify(String[] sFindOption, String[] sSearchOption ,String[] sSearchValue,String[] sOther,String sPON) throws Exception
	{	

		DCPage DC = new DCPage(_driver);
		String sFetchValue="";
		boolean sFlag=false;

		System.out.println("Length of Find option is "+sFindOption.length);
		System.out.println("Length of Search option is "+sSearchOption.length);
		for(int j=0;j<sFindOption.length;j++)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("list"), sFindOption[j]);

			for(int i =0;i<sSearchOption.length;i++)
			{

				if(j==1)
					sSearchValue[3]="C";
				else if(j==2)
					sSearchValue[3]="P";
				else if(j==3)
					sSearchValue[3]="R";
				else if(j==4)
					sSearchValue[3]="Z";
				else if(j==5)
					sSearchValue[3]="X";

				System.out.println("Search option is "+sSearchOption[i]+" Search value is  "+sSearchValue[i]);
				DC.SearchValue(sSearchValue[i],sSearchOption[i]);

				String sTitle = _driver.getTitle();
				if(sTitle.equals("Purchase order "+sPON))
				{
					System.out.println(sSearchValue[i]+" displayed");
					boolean sFlag1 =  VerifyPODetailsPage(sPON);
					if(sFlag1==false)
					{
						Assert.fail(" PO details page verificatio failed");
					}
					NavigateToPO();
					assertEquals("Purchase Orders - "+sFindOption[j], _driver.getTitle());

				}
				else
				{
					int rowCount = _driver.findElements(By.xpath("//table[@id='"+sFindOption[j]+"']/tbody/tr")).size();
					System.out.println("RowCount is "+rowCount);

					for(int k =1;k<=1;k++)
					{
						if(j==1)
							sSearchValue[3]="Closed";
						else if(j==0)
							sSearchValue[3]="Open";
						else if(j==2)
							sSearchValue[3]="Pending";
						else if(j==3)
							sSearchValue[3]="Received";
						else if(j==4)
							sSearchValue[3]="Reconciled";
						else if(j==5)
							sSearchValue[3]="Cancelled";
						sFetchValue = _driver.findElement(By.xpath("//table[@id='"+sFindOption[j]+"']/tbody/tr[1]/td["+sOther[i]+"]/div")).getText();
						sFetchValue =sFetchValue.trim();
						if(sFetchValue.equals(sSearchValue[i]) )
						{
							sFlag= true;
							break;
						}
						else
						{
							Assert.fail("Fetched value is "+sFetchValue+" Expected value is "+sSearchValue[i]);
						}
					}
				}

			}

			if(j == sFindOption.length-1 )
			{
				break;
			}
			else
			{
				System.out.println("Change the Status");
				NavigateToPO();
				assertEquals("Purchase Orders - "+sFindOption[j], _driver.getTitle());
				DC.Search(sPON,"poNumber" );
				String sTitle = _driver.getTitle();
				System.out.println("Title of the page is "+sTitle);
				if(sTitle.equals("Purchase order "+sPON))
				{
					System.out.println("Able to see "+sPON);		
				}
				else
				{
					int rowCount =_driver.findElements(By.xpath("//table[@id='"+sFindOption[j]+"']/tbody/tr")).size();
					System.out.println("rowCount is "+rowCount);
					for(int i = 1;i<=rowCount;i++)
					{
						String sEst = _driver.findElement(By.xpath("//table[@id='"+sFindOption[j]+"']/tbody/tr["+i+"]/td[3]/div")).getText();
						sEst =sEst.trim();
						System.out.println("sEst is "+sEst);
						if(sEst.equals(sPON))
						{
							_driver.findElement(By.xpath("//table[@id='"+sFindOption[j]+"']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
							break;
						}
					} 
				}	
				int a = j+1;
				System.out.println("List value is "+sFindOption[a]);
				if(sFindOption[a].equals("All"))
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)), "Open");
					Thread.sleep(1000);
				}
				else
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PO_Order_Status)), sFindOption[a]);
					Thread.sleep(1000);
				}
				DC.Update();
				NavigateToPO();
			}

		}	

	}


	public void sDuplicateUser(String sUserName) throws Exception
	{

		_driver.findElement(By.xpath("//a[text()='Duplicate']")).click();
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_UserName))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.SystemUser_UserName))).sendKeys(sUserName);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
		Thread.sleep(2000);
		CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Duplicated_text)));
	}


	public void EnterQtyPriceInfoDetails(String sSellPrice) throws Exception, IOException
	{
		_driver.findElement(By.xpath("//a[text()='uantity / Price Info']")).click();
		Thread.sleep(2000);

		CommonFunctions.Wait(_driver, By.name("sellPrice1"));
		CommonFunctions.SendValue(_driver, By.name("sellPrice1"), sSellPrice);
		Thread.sleep(1000);
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "EnterQtyPriceInfoDetails");
	}

	public String switchToPOLineItem(String OriginalWindowId) throws Exception
	{	
		String POLineItemWindHandle = "";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Purchase order line")) 
				{
					POLineItemWindHandle = windowId;
					break;
				}
				else
				{
					_driver.switchTo().window(OriginalWindowId);
				}
			}
		}
		return POLineItemWindHandle;
	}

	public void receivePO(String Quantity, String Note) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Purchase  Order : PurchaseOrder')]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='- Receive']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Receive_PO_Update_And_Receive_Button)));

		if(!Quantity.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[1]//input[@name='PurchaseOrderLine.quantityToReceive']"), Quantity);
		}
		if(!Note.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr[1]//input[@name='PurchaseOrderLine.receivingNote']"), Note);
		}

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Receive_PO_Update_And_Receive_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Receive_PO_Purchase_Order_line_Message)));
	}

	public void CreateCustomerVendorContact(String CustomerID, String VendorID, String sFN, String sLN, String sCompany, String sDept, String sPhone , String sAdd1, String sAdd2,String sAdd3, String sCity, String ContactType) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		JobBillingPage JB = new JobBillingPage(_driver);
				
		JB.NavigateToAddContactPage();
		
		CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));
		
		if(CustomerID.equals(""))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Vendor))).sendKeys(VendorID);
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(CustomerID);
		}
		
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sCompany);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Department))).sendKeys(sDept);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(sPhone);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add1))).sendKeys(sAdd1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add2))).sendKeys(sAdd2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add3))).sendKeys(sAdd3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_city))).sendKeys(sCity);
		CommonFunctions.selectDropdownByText(_driver, By.name("country"), "US - United States");
		CommonFunctions.selectDropdown(_driver, By.name("stateKey"), "1:AK");
		CommonFunctions.SendValue(_driver, By.name("zip"), "99501");
		
		if (ContactType.toUpperCase().equals("BILL"))
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("billToBooleanCheck"));
		}
		else if (ContactType.toUpperCase().equals("SHIP"))
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("shipToBooleanCheck"));
		}
		else if (ContactType.toUpperCase().equals("BOTH"))
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("billToBooleanCheck"));
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("shipToBooleanCheck"));
		}
		
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(1000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Vendor_Default_Currency))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Default_Currency)), "USD");
			Thread.sleep(1000);
		}
		_driver.findElement(By.name("salesTax")).clear();
		_driver.findElement(By.name("salesTax")).sendKeys("CA0000");
		
		DC.Add();
	}

	public String CreatePOfromInvDetailPage(String PurchaseOrderType, String Quantity, String Vendor) throws Exception
	{
		String sPurchaseOrder = "";
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Inventory_PO)));
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Purchase Order"))
				{
					if (!Vendor.equals(""))
					{CommonFunctions.selectDropdownByText(_driver, By.name("purchaseOrderType"), PurchaseOrderType);}
					else
					{CommonFunctions.selectDropdownByIndex(_driver, By.name("purchaseOrderType"), 1);}
					
					if (!Vendor.equals(""))
					{
						CommonFunctions.sSelectCheckBox(_driver, true, By.name("selectCustomVendorBooleanCheck"));
						CommonFunctions.SendValue(_driver, By.name("vendor"), Vendor);
						_driver.findElement(By.name("vendor")).sendKeys(Keys.TAB);
						Thread.sleep(2000);
					}
					
					CommonFunctions.SendValue(_driver, By.name("qtyOrdered"), Quantity);					
					
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					CommonFunctions.waitForPageLoaded(_driver);
					CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));

					sPurchaseOrder = CommonFunctions.GetText(_driver, By.xpath("//label[text()='PO Number']/../following-sibling::div/div")).trim();
					_driver.close();
					Thread.sleep(1000);
					break;					
				}
			}
		}
		
		return sPurchaseOrder;
	}
	
	public String addDaystoDate(int numOfDays, String format) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, numOfDays); // Adding 5 days
		return sdf.format(c.getTime());	
	}

	public void navigateToVendorDetalPage(String VendorID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Vendor/detail/"+VendorID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("contactTitle"));
		assertEquals("Vendor "+VendorID, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"navigateToVendorDetalPage");
		System.out.println("Navigated to Vendor detail page");
	}

	public void searchInAutoCreatePO (String SearchKey, String SearchValue) throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.name("filterType")))
		{
			NavigateToAutoCreatePO();
			Thread.sleep(5000);
		}
		
		CommonFunctions.selectDropdownByText(_driver, By.name("filterType"), SearchKey);
		_driver.findElement(By.name("filterType")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		if (SearchKey.equals("Inventory Item Type"))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("inventoryItemTypeString"), SearchValue);
		}
		else if (SearchKey.equals("Vendor"))
		{
			CommonFunctions.SendValue(_driver, By.name("searchVendor"), SearchValue);
		}
		else if (SearchKey.equals("Job"))
		{
			CommonFunctions.SendValue(_driver, By.name("searchJob"), SearchValue);
		}
		else if (SearchKey.equals("Customer"))
		{
			CommonFunctions.SendValue(_driver, By.name("searchCustomer"), SearchValue);
		}
		
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Search/Sort' and @name='updateForm']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(3000);
	}

}