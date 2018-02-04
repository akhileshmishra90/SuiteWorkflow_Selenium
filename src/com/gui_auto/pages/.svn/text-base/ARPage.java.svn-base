package com.gui_auto.pages;





import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
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
import com.gui_auto.utilities.ScreenShot;
import java.lang.reflect.Method;


public class ARPage implements BaseElement
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
	

	 public  ARPage(WebDriver driver) throws Exception 
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
	public void NavigateToARAddCustomerPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/add");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Customer)));
    	assertEquals("Adding Customer", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToARAddCustomerPage");
	}
	public void NavigateToARCustomerListPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/list");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	//assertEquals("Adding Customer", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToARCustomerListPage");
	}
	public void NavigateToARCustomerInquiryPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/inquiry");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	//assertEquals("Adding Customer", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToARCustomerInquiryPage");
	}
	
	public void NavigateToAREnterPaymentPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PaymentTrn/add");	
    	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
    	//assertEquals("Adding Customer", _driver.getTitle());
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToARCustomerInquiryPage");
	}
	
	public void gotoCustomerDetailPage(String Customer) throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
    	_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/detail/"+Customer.toUpperCase());
    	CommonFunctions.waitForPageLoaded(_driver);
    	CommonFunctions.Wait(_driver, By.name("customerStatus"));
    	NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"gotoCustomerDetailPage");
	}
	
	
}
