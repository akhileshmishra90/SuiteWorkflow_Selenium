package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class PriceListQuotingPage implements BaseElement
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
	
	public PriceListQuotingPage(WebDriver driver) throws Exception {
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
	
	public void navigateToQuoteProductTypeListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/ProductType/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Product Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteProductTypeListPage");
		System.out.println("****Quote Product Type List page appears****");
	}
	
	public void navigateToPriceListsPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PriceList/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Price Lists", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPriceListsPage");
		System.out.println("****Price Lists page appears****");
	}
	
	public void navigateToQuoteLetterTypeListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteLetterType/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Letter Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteLetterTypeListPage");
		System.out.println("****Quote Letter Type List page appears****");
	}
	
	public void navigateToQuoteDepartmentListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteDepartment/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Departments", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteDepartmentListPage");
		System.out.println("****Quote Department List page appears****");
	}
	
	public void navigateToQuoteCategoryListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteCategory/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Categories", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteCategoryListPage");
		System.out.println("****Quote Categories List page appears****");
	}
	
	public void navigateToQuoteItemTypeListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteItemType/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Item Types - All Area Items", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteItemTypeListPage");
		System.out.println("****Quote Item Types List page appears****");
	}
	
	public void navigateToQuoteExpressionListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteExpression/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Expressions", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteExpressionListPage");
		System.out.println("****Quote Expressions List page appears****");
	}
	
	public void navigateToQuoteLetterListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/QuoteLetter/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Quote Letters", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuoteLetterListPage");
		System.out.println("****Quote Letters List page appears****");
	}
	
	public void navigateToQuotePrintServiceSetupPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/printServiceSetup");
		CommonFunctions.waitForPageLoaded(_driver);
//		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
//		assertEquals("print Service Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToQuotePrintServiceSetupPage");
		System.out.println("****Print Service Setup page appears****");
	}
	
	public void navigateToEnterNewQuotePage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Quote/add");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//div[@id = 'tabBar']"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToEnterNewQuotePage");
		System.out.println("****Enter new quote page appears****");
	}

	public void navigateToQuoteListPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Quote/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//div[@id = 'search']"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToEnterNewQuotePage");
		System.out.println("****Enter new quote page appears****");
	}
	
	public void navigateToPriceListSetupPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PaceQuoteSetup/detail/1");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("defaultPriceList"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPriceListSetupPage");
		System.out.println("****Price List Setup page appears****");
	}

	public boolean addNewQuoteExpressionInGrid (String QuoteExpressionName, String CalculationType, String ExpressionName, String ExpressionValue) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		navigateToQuoteExpressionListPage();
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_In_Grid_1)));		
		CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='appbox_implicit_addRow']//input[@name='appbox_implicit.name']"), QuoteExpressionName);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//tr[@id='appbox_implicit_addRow']//select[@name='appbox_implicit.calculationType']"), CalculationType);
		
		if (ExpressionName.equals("Expression"))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='appbox_implicit_addRow']//textarea[@name='appbox_implicit.expression']"), ExpressionValue);
		}
		else if(ExpressionName.equals("Estimate Expression"))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='appbox_implicit_addRow']//textarea[@name='appbox_implicit.estimateExpression']"), ExpressionValue);
		}
		else if (ExpressionName.equals("Job Part Item Expression"))
		{
			CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='appbox_implicit_addRow']//textarea[@name='appbox_implicit.jobPartItemExpression']"), ExpressionValue);
		}
		DC.Update();
		
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}
	
	public boolean addNewQuoteExpression (String QuoteExpressionName, String CalculationType, String ExpressionName, String ExpressionValue) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		if (!CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record))))
		{
			navigateToQuoteExpressionListPage();
		}		
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("expression"));
		
		CommonFunctions.SendValue(_driver, By.name("name"), QuoteExpressionName);		
		CommonFunctions.selectDropdownByText(_driver, By.name("calculationType"), CalculationType);
		
		if (ExpressionName.equals("Expression"))
		{
			CommonFunctions.SendValue(_driver, By.name("expression"), ExpressionValue);
		}
		else if(ExpressionName.equals("Estimate Expression"))
		{
			CommonFunctions.SendValue(_driver, By.name("estimateExpression"), ExpressionValue);
		}
		else if (ExpressionName.equals("Job Part Item Expression"))
		{
			CommonFunctions.SendValue(_driver, By.name("jobPartItemExpression"), ExpressionValue);
		}		
		DC.Add();
		
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}
	
	public String addNewQuoteLettertoQuote (String QuoteLetterType, String QuoteQuantity) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sQuoteLetterID = "";
		
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[contains(@id, 'QuoteLetter')]/div/div[1]//a[text()='Add New Letter']"));
		CommonFunctions.waitForPageLoaded(_driver);
		
		if(QuoteLetterType.equals(""))
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("quoteLetterType"), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("quoteLetterType"), QuoteLetterType);
		}
		
		if (QuoteQuantity.equals(""))
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("quoteQuantity"), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("quoteQuantity"), QuoteQuantity);
		}
		DC.Add();
		
		if(CommonFunctions.isElementPresent(_driver, By.xpath("//label[text()='ID']/../following-sibling::div/div")))
		{
			sQuoteLetterID = CommonFunctions.GetText(_driver, By.xpath("//label[text()='ID']/../following-sibling::div/div"));
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Return To Quote']"));
			CommonFunctions.waitForPageLoaded(_driver);
		}
		
		return sQuoteLetterID;
	}
	
}