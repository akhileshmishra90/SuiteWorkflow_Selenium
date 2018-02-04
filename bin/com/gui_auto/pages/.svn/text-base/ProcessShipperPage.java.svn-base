package com.gui_auto.pages;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class ProcessShipperPage implements BaseElement 
{
	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public ProcessShipperPage(WebDriver driver) throws Exception {
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
	
	public void PSLogin(String sUN,String sPWD) throws Exception
	{
		System.out.println("Logon to Process Shipper");

		if(_driver.findElements(By.name("TxtUserID")).size()>0)
		{
			System.out.println(" Able to see Login Text");
			CommonFunctions.SendValue(_driver, By.name("TxtUserID"), sUN);
			CommonFunctions.SendValue(_driver, By.name("TxtPwd"), sPWD);
			CommonFunctions.selectDropdownByText(_driver, By.name("ddLanguage"), "en-US");
			CommonFunctions.ClickElement(_driver, By.xpath("//input[contains(@name,'rwbtnLogin')]"));
			CommonFunctions.cancelPopupMessageBox(_driver);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.switchTo().defaultContent();
			_driver.switchTo().frame("MainFrame");
			_driver.switchTo().frame("MenuFrame");
			CommonFunctions.Wait(_driver, By.name("btnLogout"));
		}
		else
		{
			System.err.println("Not Able to see Login fields");
		}		
	}
	
	public void PSLogout() throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("MenuFrame");
		
		CommonFunctions.ClickElement(_driver, By.name("btnLogout"));
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
	}
	
	public void LoadPage() throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		Thread.sleep(3000);
		while (CommonFunctions.isElementPresent(_driver, By.id("panelUpdateProgress")))
		{
			if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id("panelUpdateProgress"))))
			{
				System.out.println("Waiting for system to process");
				Thread.sleep(2000);
			}
			else
			{
				break;
			}
		}
	}
	
	public void getDeliveryDoc (String DeliveryNumber) throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		CommonFunctions.selectDropdown_ByValue(_driver, By.name("DDLInterface"), "Pace");
		LoadPage();
		CommonFunctions.SendValue(_driver, By.name("TxtdelDoc"), DeliveryNumber);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Get Delivery Doc']"));
		LoadPage();
	}
	
	public void getDeliveryDoc_Printstream (String DeliveryNumber) throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		CommonFunctions.selectDropdown_ByValue(_driver, By.name("DDLInterface"), "PrintStream");
		LoadPage();
		
		CommonFunctions.SendValue(_driver, By.name("TxtdelDoc"), DeliveryNumber);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Get Delivery Doc']"));
		LoadPage();
	}
	
	public void PackAll () throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		CommonFunctions.ClickElement(_driver, By.name("BtnPackAll"));
		LoadPage();
	}
	
	public void PartialPack (String PartialQuantity, String NumberOfItems) throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		for(int i=2; i<=Integer.valueOf(NumberOfItems)+1; i++)
		{
			CommonFunctions.SendValue(_driver, By.name("GVItems$ctl0"+i+"$TxtPartQty"), PartialQuantity);
		}
		CommonFunctions.ClickElement(_driver, By.name("btnPack"));
		LoadPage();
	}
	
	public void ProcessShipment(String Weight) throws Exception
	{
		LoadPage();
		
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		CommonFunctions.SendValue(_driver, By.name("GVPacking$ctl02$TxtWeight"), Weight);
//		CommonFunctions.selectDropdownByIndex(_driver, By.name("GVPacking$ctl02$ddlPacking"), 0);
//		CommonFunctions.SendValue(_driver, By.name("GVPacking$ctl02$cmbBoxTypes"), "1-123");
		LoadPage();
		CommonFunctions.ClickElement(_driver, By.name("TxtShipDate"));
		CommonFunctions.ClickElement(_driver, By.id("TxtShipDate_Calendar_today"));
		CommonFunctions.ClickElement(_driver, By.name("BtnShipment"));
		LoadPage();
		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//span[text()='Shipment Has Processed Successfully']"));
		CommonFunctions.closeAllChildWindows(_driver);
	}
	
	public void ProcessShipment_Printstream() throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
	//	CommonFunctions.SendValue(_driver, By.name("GVPacking$ctl02$TxtWeight"), Weight);
//		CommonFunctions.selectDropdownByIndex(_driver, By.name("GVPacking$ctl02$ddlPacking"), 0);
		//CommonFunctions.SendValue(_driver, By.name("GVPacking$ctl02$cmbBoxTypes"), "1-123");
		LoadPage();
		CommonFunctions.ClickElement(_driver, By.name("BtnShipment"));
		LoadPage();
		CommonFunctions.Wait(_driver, By.xpath("//span[text()='Shipment Has Processed Successfully']"));
	}
	
	public String[] getTrackingDetails(String DeliveryNumber) throws Exception
	{
		_driver.switchTo().defaultContent();
		_driver.switchTo().frame("MainFrame");
		_driver.switchTo().frame("showframe");
		
		String[] TrackingDetails = new String[3];
		
		String sOriginalWidnow = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.name("TxtShipDate"));
		CommonFunctions.ClickElement(_driver, By.id("TxtShipDate_Calendar_today"));
		CommonFunctions.selectDropdown_ByValue(_driver, By.name("DDLInterface"), "Pace");
		LoadPage();
		CommonFunctions.ClickElement(_driver, By.name("IBDelDoc"));
		Thread.sleep(3000);
		CommonFunctions.SwitchToWindow(_driver, "Delivery Document List - Process Shipper");
		
		_driver.switchTo().defaultContent();
		TakeScreenShot.ScreenShotWindow(_driver, "getTrackingDetails");
		int iDeliveryCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr"));
		for (int i=2; i<=iDeliveryCnt; i++)
		{
			String sDeliveryNum = CommonFunctions.getText(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]/td[2]"));
			if (sDeliveryNum.equals(DeliveryNumber))
			{
				TrackingDetails[0] = CommonFunctions.getText(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]/td[7]/span"));
				TrackingDetails[1] = CommonFunctions.getText(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]/td[5]"));
				TrackingDetails[2] = CommonFunctions.getText(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]/td[8]"));
				
				break;
			}
			else if (i == iDeliveryCnt)
			{
				Assert.fail("Delivery was not found");
			}
		}
		_driver.close();
		_driver.switchTo().window(sOriginalWidnow);
		
		return TrackingDetails;
	}
	
	public void cancelShipment (String DeliveryNumber) throws Exception
	{
		_driver.switchTo().defaultContent().switchTo().frame("MainFrame").switchTo().frame("showframe");
		
		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.ClickElement(_driver, By.name("IBDelDoc"));
		Thread.sleep(3000);
		CommonFunctions.SwitchToWindow(_driver, "Delivery Document List - Process Shipper");
		
		_driver.switchTo().defaultContent();
		
		int iDeliveryCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr"));
		for (int i=2; i<=iDeliveryCnt; i++)
		{
			String sDeliveryNum = CommonFunctions.getText(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]/td[2]"));
			if (sDeliveryNum.equals(DeliveryNumber))
			{
				CommonFunctions.ClickElement(_driver, By.xpath("//table[contains(@id,'GridView')]/tbody/tr["+i+"]//a[text()='Select']"));
				break;
			}
			else if (i == iDeliveryCnt)
			{
				Assert.fail("Delivery was not found");
			}
		}
		
		CommonFunctions.ReturnToOriginalWindow(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
		
		_driver.switchTo().defaultContent().switchTo().frame("MainFrame").switchTo().frame("showframe");
		CommonFunctions.Wait(_driver, By.id("btnCancelShipment"));
		
		CommonFunctions.ClickElement(_driver, By.id("btnCancelShipment"));
		CommonFunctions.getPopupMessage(_driver);
		Thread.sleep(2000);
		_driver.switchTo().defaultContent().switchTo().frame("MainFrame").switchTo().frame("showframe");
		LoadPage();
		CommonFunctions.waitForPageLoaded(_driver);
		
		getDeliveryDoc(DeliveryNumber);
		CommonFunctions.getPopupMessage(_driver);
		_driver.switchTo().defaultContent().switchTo().frame("MainFrame").switchTo().frame("showframe");
		
		assertFalse("The Delivery was not cancelled properly. Please check manually.", CommonFunctions.isElementPresent(_driver, By.xpath("//span[text()='Not a Planned Shipment']")));		
	}

}
