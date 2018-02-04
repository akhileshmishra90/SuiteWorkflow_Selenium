package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class JobShipmentsPage implements BaseElement
{
	
	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbconnection = new ReadAndUpdate();
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	private static  String sSERVER = null;
	private static  String sCOMPANY = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	
	public JobShipmentsPage(WebDriver driver) throws Exception {
		super();
		this._driver = driver;
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
	
	public String  Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        Date date = new Date();
        String suniqueNumber = dateFormat.format(date);        
		return suniqueNumber;
	}
	
	public String UniqueNum3Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String sUniqueNumber = Integer.toString(random.nextInt(10))+chars[random.nextInt(chars.length)]+Integer.toString(random.nextInt(10));
		return sUniqueNumber;
	}
	
	public String UniqueNum4Digit()
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}
	
	public String UniqueNum5Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		
		String sUniqueNumber = dateFormat.format(date) + chars[random.nextInt(chars.length)];
		return sUniqueNumber;
	}
	
	public void navigateToJobShipmentListpage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobShipment/list");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Job Shipments - Open Jobs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobShipmentListpage");
		System.out.println("****Job Shipments List page appears****");
	}
	
	public void navigateToBillOfLadingListpage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/BillOfLading/list");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Bill Of Lading - Planned", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToBillOfLadingListpage");
		System.out.println("****Bill Of Lading List page appears****");
	}
	
	public void navigateToMasterBillOfLadingListpage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/MasterBillOfLading/list");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Master Bill Of Lading - Planned", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToMasterBillOfLadingListpage");
		System.out.println("****Master Bill Of Lading List page appears****");
	}
	
	public void CompanySetupPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Company/detail/001");
		CommonFunctions.Wait(_driver, By.name("country"));
		assertEquals("Company Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("CompanySetupPage");
		System.out.println("****Company Setup page appears****");
	}
	
	public String AddBOL() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sBOL = "";
		
		if (!(_driver.getTitle().equals("Bill Of Lading - Planned")))
		{
			navigateToBillOfLadingListpage();
		}		
		assertTrue("Bill Of Lading list page was not displayed", _driver.getTitle().contains("Bill Of Lading"));
    	System.out.println("The bill of lading list form was displayed");
    	
    	System.out.println("Click [Add New Record]");
    	CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location)));
    	
    	System.out.println("Select a manufacturing location, Ship via and Ship date, Click [Update]");
    	CommonFunctions.selectDropdownByValue(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location)), "1");
    	CommonFunctions.selectDropdownByText(_driver, By.name("shipVia"), "UPS - Ground");
    	CommonFunctions.SendValue(_driver, By.name("shipDate"), "t");
    	DC.Add();
    	CommonFunctions.waitForPageLoaded(_driver);
    	
    	if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))
    	{
    		sBOL = CommonFunctions.GetText(_driver, By.xpath("//h4[@title='Unique identifier for this record. Max 17 characters. Letters and Numbers.']/following-sibling::div/div"));
    		System.out.println("New Bill of lading "+sBOL+" was created");
    	}
    	else
    	{
    		System.err.println("New Bill of Lading was not created");
    	}
    	
    	return sBOL;
	}
	
	public String AddMBOl() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sMBOL = "";
		
		if (!(_driver.getTitle().equals("Master Bill Of Lading - Planned")))
		{
			navigateToMasterBillOfLadingListpage();
		}		
		assertTrue("Master Bill Of Lading List page was not displayed", _driver.getTitle().contains("Master Bill Of Lading"));
    	System.out.println("The Master bill of lading list form was displayed");
    	
    	System.out.println("Click [Add New Record]");
    	CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location)));
    	
    	System.out.println("Select a manufacturing location, Ship via and Ship date, Click [Update]");
    	CommonFunctions.selectDropdownByValue(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location)), "1");
    	CommonFunctions.selectDropdownByText(_driver, By.name("shipViaID"), "UPS - Ground");
    	CommonFunctions.SendValue(_driver, By.name("shipDate"), "t");
    	DC.Add();
    	CommonFunctions.waitForPageLoaded(_driver);
    	
    	if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))
    	{
    		sMBOL = CommonFunctions.GetText(_driver, By.xpath("//h4[@title='A unique code for identification purposes. Max 17 characters. Letters and Numbers.']/following-sibling::div/div"));
    		System.out.println("New Master Bill of lading "+sMBOL+" was created");
    	}
    	else
    	{
    		System.err.println("New Master Bill of Lading was not created");
    	}
    	
    	return sMBOL;
	}

	public void addFinalShipmentViaJob() throws Exception
	{		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(),'Job : ')]"));
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@title='Add Shipment']"));
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("shipmentType2"), "Planned Shipment");
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("date"), new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("notes2"),"Notes added on"+new Date().toString());
		CommonFunctions.SendValue(_driver, By.name("cost"), "25");
		CommonFunctions.SendValue(_driver, By.name("quotedPrice"), "20");
		CommonFunctions.SendValue(_driver, By.name("weight"), "5");		
	}

	public void createShipToInvetoryShipment(String ShipmentType, String InventoryItem, String Quantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Add Detailed Record']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Job_Shipment_Planned)));
		
		CommonFunctions.selectDropdown(_driver, By.xpath(Locators.getProperty(Locators.Job_Shipment_ShipmentType)), ShipmentType);
		CommonFunctions.sSelectCheckBox(_driver, true, By.name(Locators.getProperty(Locators.Job_Shipment_Planned)));

		if (!(CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='shipmentDetails']/legend/span"));
		}
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Inventory_Item)), InventoryItem);
		_driver.findElement(By.name("plannedQuantity")).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("plannedQuantity"), Quantity);
		DC.Add();		
	}
	
	public void navigateToJobPartShipmentListpage(String Job, String JobPart) throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobShipment/listJobPart/"+Job+"%3A"+JobPart);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Add Detailed Record']"));
		assertEquals("Job Shipments", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobPartShipmentListpage");
		System.out.println("****Job Part Shipments List page appears****");
	}
	
}