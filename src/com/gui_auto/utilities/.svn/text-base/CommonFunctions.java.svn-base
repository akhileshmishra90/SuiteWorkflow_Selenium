/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.utilities;

import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gui_auto.base_classes.GUI_automation_properties;

public class CommonFunctions {	

	static String  CommonOriginalHandle=null;
	static String CommonOrignalwindowTitle=null; 

	static char specialCharacters[] = { '!', '@', '#', '$', '%', '^', '&', '*',
		'(', ')', '?', '/', '"', '|', '{', '[', '<', '>', ';', '`', ',',
		'_', '-' };

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	/**
	 * Retrieve popup text message.
	 * 
	 * @param WebDriver
	 *            driver
	 * @return
	 */
	public static String getPopupMessage(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}
		//		System.out.println("message : "+message);
		return message;
	}


	public String  Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);        
		return suniqueNumber;
	}

	public static String UniqueNum3Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String sUniqueNumber = Integer.toString(random.nextInt(10))+chars[random.nextInt(chars.length)]+Integer.toString(random.nextInt(10));
		return sUniqueNumber;
	}

	public static String UniqueNum4Digit()
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}

	public static String UniqueNum5Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();

		String sUniqueNumber = dateFormat.format(date) + chars[random.nextInt(chars.length)];
		return sUniqueNumber;
	}	

	public static  String getUniqueNumDigit(String Item, int Length) throws Exception
	{
		String sUniqueNumber = "", sURL = "",sSERVER,sCOMPANY;

		do
		{
			switch (Length) {
			case 3 :
				sUniqueNumber = UniqueNum3Digit();
				break;
			case 4 :
				sUniqueNumber = UniqueNum4Digit();
				break;
			case 5 :
				sUniqueNumber = UniqueNum5Digit();
				break;
			}


			sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
			sURL = "http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/"+Item+"/detail/"+sUniqueNumber;

		} while (URLExists(sURL));

		return sUniqueNumber.trim();
	}

	public static  String getUniqueNumDigit(String Item,char PreChar, int Length) throws Exception
	{
		String sUniqueNumber = "", sURL = "",sSERVER,sCOMPANY;

		do
		{
			switch (Length) {
			case 3 :
				sUniqueNumber = PreChar+UniqueNum3Digit();
				break;
			case 4 :
				sUniqueNumber = PreChar+UniqueNum4Digit();
				break;
			case 5 :
				sUniqueNumber = PreChar+UniqueNum5Digit();
				break;
			}
			sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
			sURL = "http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/"+Item+"/detail/"+sUniqueNumber;
		} while (URLExists(sURL));

		return sUniqueNumber.trim();
	}
	public static void SetOriginalWindowHandle(WebDriver _driver )
	{
		System.out.println("-----Setting WebDriver Control To original window -----");

		CommonOriginalHandle= _driver.getWindowHandle();		
		CommonOrignalwindowTitle =_driver.getTitle();

	}
	public static void ReturnToOriginalWindow(WebDriver _driver)
	{
		System.out.println("Return to Original window");
		_driver.switchTo().window(CommonOriginalHandle).getTitle().equals(CommonOrignalwindowTitle);
	}

	public static String GetAccountingPeriod() throws ParseException
	{
		String AccountPeriod;
		DateFormat inputDF  = new SimpleDateFormat("MM/dd/yy");
		Date dt = new Date();

		String date1 = inputDF.format(dt);

		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDF.parse(date1));

		int month = cal.get(Calendar.MONTH)+ 1;
		int day = cal.get(Calendar.MONTH) ;
		int year = cal.get(Calendar.YEAR);

		if(month<10)
		{
			AccountPeriod=year+"-0"+month;
		}
		else
		{
			AccountPeriod=year+"-"+month;
		}

		return AccountPeriod;
	}

	public static void CloseWindow(WebDriver _driver,String WindowCaption)
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		System.out.println("closing "+WindowCaption+ "Window");


		if (!availableWindows.isEmpty()) {

			for (String windowId : availableWindows){	


				if(_driver.switchTo().window(windowId).getTitle().contains(WindowCaption) ){												

					System.out.println("closing window "+WindowCaption);
					_driver.switchTo().window(windowId).close();														
				}	

			}			
		}
		ReturnToOriginalWindow(_driver);
	}

	public static void SwitchToWindow(WebDriver _driver, String sWindowName) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();

		String sWindowTitle =_driver.getTitle();

		Set<String> availableWindows = _driver.getWindowHandles();


		if (!availableWindows.isEmpty()) {

			for (String windowId : availableWindows){	


				if(_driver.switchTo().window(windowId).getTitle().contains(sWindowName) )
				{
					System.out.println(_driver.switchTo().window(windowId).getTitle());
					_driver.manage().window().maximize();
					Thread.sleep(1000);
				}	
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}			
		}
	}

	public static Boolean isWindowExistAndSet(WebDriver _driver, String sWindowName)
	{
		String  originalHandle = _driver.getWindowHandle();

		String sWindowTitle =_driver.getTitle();

		Set<String> availableWindows = _driver.getWindowHandles();


		if (!availableWindows.isEmpty()) {

			for (String windowId : availableWindows){	


				if(_driver.switchTo().window(windowId).getTitle().contains(sWindowName) ){												

					System.out.println(_driver.switchTo().window(windowId).getTitle());		
					return true;
				}	
				else {

					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}			
		}

		return false;
	}

	public static void ClosesWindow(WebDriver _driver, String sWindowName)
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
	public static  boolean URLExists(String URLName)
	{
		try 
		{
			HttpURLConnection.setFollowRedirects(false);
			//HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection con =
					(HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static String getAbsoluteXPath(WebElement element,WebDriver driver)
	{
		return (String) ((JavascriptExecutor) driver).executeScript(
				"function absoluteXPath(element) {"+
						"var comp, comps = [];"+
						"var parent = null;"+
						"var xpath = '';"+
						"var getPos = function(element) {"+
						"var position = 1, curNode;"+
						"if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
						"return null;"+
						"}"+
						"for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"+
						"if (curNode.nodeName == element.nodeName) {"+
						"++position;"+
						"}"+
						"}"+
						"return position;"+
						"};"+

				    "if (element instanceof Document) {"+
				    "return '/';"+
				    "}"+

				    "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
				    "comp = comps[comps.length] = {};"+
				    "switch (element.nodeType) {"+
				    "case Node.TEXT_NODE:"+
				    "comp.name = 'text()';"+
				    "break;"+
				    "case Node.ATTRIBUTE_NODE:"+
				    "comp.name = '@' + element.nodeName;"+
				    "break;"+
				    "case Node.PROCESSING_INSTRUCTION_NODE:"+
				    "comp.name = 'processing-instruction()';"+
				    "break;"+
				    "case Node.COMMENT_NODE:"+
				    "comp.name = 'comment()';"+
				    "break;"+
				    "case Node.ELEMENT_NODE:"+
				    "comp.name = element.nodeName;"+
				    "break;"+
				    "}"+
				    "comp.position = getPos(element);"+
				    "}"+

				    "for (var i = comps.length - 1; i >= 0; i--) {"+
				    "comp = comps[i];"+
				    "xpath += '/' + comp.name.toLowerCase();"+
				    "if (comp.position !== null) {"+
				    "xpath += '[' + comp.position + ']';"+
				    "}"+
				    "}"+

				    "return xpath;"+

				"} return absoluteXPath(arguments[0]);", element);
	}


	public static String cancelPopupMessageBox(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}

		return message;
	}

	private static SecureRandom random = new SecureRandom();

	/**
	 * Generate random string of special characters of length x
	 * 
	 * @return
	 */


	public String getRandomSpecialString(int length) {
		int len = specialCharacters.length;
		String str = "";
		Random randomGenerator = new Random();
		int index;

		for (int i = 0; i < length; i++) {
			index = randomGenerator.nextInt(len - 1);
			str = str + specialCharacters[index];
		}
		return str;
	}
	public static boolean WaitUntillPageNavigate(WebDriver driver,String sTitle,int i) throws Exception
	{
		boolean sPageNavigated = false;
		if(driver.getTitle().equals(sTitle) && i<=3)
		{
			System.out.println("Page Navigated to "+sTitle);
			sPageNavigated=true;
		}
		else
		{
			i++;
			Thread.sleep(3000);
		}
		return sPageNavigated;
	}
	/**
	 * Generate random string of length x
	 * 
	 * @return
	 */
	public static String getRandomString(int length) {
		String result = new BigInteger(Long.SIZE * length, random).toString(32);
		return result.substring(0, length);
	}
	public static boolean Elementdisplayed_Enabled(WebElement Element)
	{
		if(Element.isDisplayed())
		{
			if(Element.isEnabled())
			{
				System.out.println("Object::"+Element.getText()+" is displayed and Enabled");
				return true;
			}
			else
			{
				System.out.println("Object::"+Element.getText()+" is displayed and Not enabled");
				return false;
			}
		}
		else
		{
			System.out.println("Object::"+Element.getText()+"is not displayed");
			return false;
		}

	}
	/**
	 * Generate random string of length x
	 * 
	 * @return
	 */
	public static void populateField(WebDriver driver, By locator, String value) {
		WebElement field = driver.findElement(locator);
		field.clear();
		field.sendKeys(value);

	}

	public static boolean VerifyChecked(WebDriver driver, By locator) {
		WebElement field = driver.findElement(locator);
		if(field.isSelected())
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	/**
	 * Check hover message text
	 * 
	 * @param driver
	 * @param by
	 * 
	 * @return string
	 */


	public static void waitForPageLoaded(WebDriver _driver)
	{

		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() 
				{
			public Boolean apply(WebDriver driver) 
			{
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
				};

				WebDriverWait wait = new WebDriverWait(_driver,80);
				try 
				{
					wait.until(expectation);
				}
				catch(Throwable error) 
				{
					System.out.println("Timeout waiting for Page Load Request to complete.");
				}
	}

	public static boolean IsElementVisible(WebDriver driver,String sLabel)
	{
		if(driver.findElements(By.xpath("//div[@style='display: block;']//h4[label[contains(text(),'"+sLabel+"')]]")).size()>0)
		{
			System.out.println("Element Visible");
			return true;
		}
		else
		{
			System.out.println("Element not Visible");
			return false;
		}
	}
	public static String checkHoverMessage(WebDriver driver, By locator){
		String tooltip = driver.findElement(locator).getAttribute("title");
		return tooltip;
	}

	public static void ClickElement(WebDriver driver, By locator) throws Exception
	{
		try {


			if(driver.findElements(locator).size()>0)
			{
				driver.findElement(locator).click();
				System.out.println("Successfully clicked in the locator "+locator.toString());
				Thread.sleep(1000);
			}
			else
			{

				//Assert.fail("Not able click on Element.Locator is not present "+locator);
				System.err.println("Not able to find the element "+locator);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void SendValue(WebDriver driver, By locator,String sValue) throws Exception
	{
		if(driver.findElements(locator).size()>0)
		{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(".");
			driver.findElement(locator).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			driver.findElement(locator).sendKeys(sValue);
			driver.findElement(locator).sendKeys(Keys.TAB);
		}
		else
		{
			Assert.fail("Not able to enter value.Locator is not present "+locator);			
		}
	}
	public static void SendValue(WebDriver driver, By locator,String sValue,String ElementName) throws Exception
	{
		if(driver.findElements(locator).size()>0)
		{
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(sValue);
			driver.findElement(locator).sendKeys(Keys.TAB);
		}
		else
		{
			System.err.println("Not able to send value to "+ElementName);	
		}
	}


	public static String GetText(WebDriver driver, By locator) throws Exception
	{
		String sText="";
		if(driver.findElements(locator).size()>0)
		{

			sText= driver.findElement(locator).getText().trim();
			System.out.println("sText is "+sText);
		}
		else
		{
			Assert.fail("Not able to get text.Locator is not present "+locator);

		}
		return sText;
	}

	public static String GetValue(WebDriver driver, By locator) throws Exception
	{

		String sValue="";
		if(driver.findElements(locator).size()>0)
		{

			sValue=driver.findElement(locator).getAttribute("value");
		}
		else
		{
			Assert.fail("Not able to get text.Locator is not present "+locator);

		}
		return sValue;
	}
	public static boolean Wait(WebDriver driver, By locator) throws Exception{


		int numAttemps = 0;
		int specifiedAttempts = 3;
		boolean success = false;
		try
		{
			while(success == false && numAttemps < specifiedAttempts)
			{
				Thread.sleep(1000);
				if(driver.findElements(locator).size() > 0)
				{
					success = true;
				}
				else
				{
					success = false;
				}
				numAttemps++;    
			} 
			return success;
		}
		catch (NoSuchElementException e)
		{
			System.err.println("Element not found");
			return success;
		}
	}



	public static boolean isTextPresentOnPage(WebDriver driver, String str)
	{
		WebElement bodyElement = driver.findElement(By.tagName("body"));
		return bodyElement.getText().contains(str);
	}

	public static boolean isTextPresent(WebDriver driver,String text){
		try
		{
			if (driver.findElement(By.xpath("//*[contains(.,'" + text + "')]")) != null)
			{
				return true;
			}
		} 
		catch (NoSuchElementException e)
		{
			return false;
		}

		return false;
	}
	/**
	 * Select radio button
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectRadioButton(WebDriver driver, By locator, String value)
	{
		List<WebElement> select = driver.findElements(locator);

		for (WebElement radio : select)
		{
			if (radio.getAttribute("value").equalsIgnoreCase(value))
			{
				radio.click(); 	            	

			}

		}
	}

	/**
	 * Select multiple check boxes
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectCheckboxes(WebDriver driver, By locator, String value){

		List<WebElement> abc = driver.findElements(locator);
		List<String> list = new ArrayList<String>(Arrays.asList(value.split(",")));

		for (String check : list){
			for (WebElement chk : abc){        	
				if(chk.getAttribute("value").equalsIgnoreCase(check)){	        	
					chk.click();	    	            	
				}      		        		            	
			}
		}
	}


	/**
	 * Select drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectDropdown(WebDriver driver, By locator, String value) throws Exception
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByValue(value);
		Thread.sleep(1000);
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static void sSelectCheckBox(WebDriver driver,boolean sFlag,By sLoc)
	{
		if(sFlag == true)
		{
			if(driver.findElement(sLoc).isSelected())
			{
				System.out.println("Already checked Status");
			}
			else
			{
				driver.findElement(sLoc).click();
				System.out.println("checked Status");
			}
		}
		else if(sFlag == false)
		{
			if(driver.findElement(sLoc).isSelected())
			{
				driver.findElement(sLoc).click();
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
	public static void selectDropdownByText(WebDriver driver, By locator, String text) throws Exception
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByVisibleText(text);
		Thread.sleep(1000);
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) throws Exception
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByIndex(index);
		Thread.sleep(1000);		
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static void selectDropdownByValue(WebDriver driver, By locator, String Value) throws Exception
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByValue(Value);
		Thread.sleep(1000);		
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static String GetIdFromUrl(WebDriver driver)
	{
		String arry[],second;

		String URL=driver.getCurrentUrl();
		arry=URL.split("/");
		int Index=arry.length;
		Index=Index-1;

		second=arry[Index].replace("?tab=0","");

		return second;
	}
	public static void selectDropdownOptions(WebDriver driver, By locator, String text)
	{
		Select sele = new Select(driver.findElement(locator));
		sele.getOptions();

		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}
	public static String GetSelectedOption(WebDriver driver, By locator)
	{
		Select sele = new Select(driver.findElement(locator));
		String sOption = sele.getFirstSelectedOption().getText();

		return sOption;
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}
	public static String GetSelectedOptionValue(WebDriver driver, By locator)
	{
		Select sele = new Select(driver.findElement(locator));
		String sOption = sele.getFirstSelectedOption().getAttribute("value");
		return sOption;
		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}
	public static boolean ElementPresent(WebDriver driver,By locatorKey) 
	{
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public static int RowCount(WebDriver driver, By locator)
	{
		try
		{
			int rowCount = driver.findElements(locator).size();
			return rowCount;
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			return 0;
		}

	}
	/**
	 * Select auto-suggest search drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */


	public static boolean isElementPresent(final WebDriver driver, By by) {


		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public static void selectSearchDropdown(WebDriver driver, By locator, String value){
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value);
		driver.findElement(locator).sendKeys(Keys.TAB);	 
	}

	public static boolean verifyOptionExistsinDropdown (WebDriver driver, By locator, String value)
	{
		boolean bOptionExists = false;
		List <String> OptionList = new ArrayList<String>();

		if (isElementPresent(driver, locator))
		{
			Select select = new Select(driver.findElement(locator));
			List<WebElement> options = select.getOptions();

			for(WebElement option : options)  {
				OptionList.add(option.getText().trim());
			}

			bOptionExists = OptionList.indexOf(value) >= 0;
		}
		else
		{
			System.err.println("Dropdown object doesn't exists");			
		}

		return bOptionExists;
	}



	public static void uploadFile(WebDriver driver, By locator, String value){
		driver.findElement(locator).sendKeys(value);		  
	}

	public static void selectByVisibleText(WebDriver _driver, By xpath,
			WebElement option) {
		// TODO Auto-generated method stub

	}

	public String getNextElementValue(WebDriver _Driver,String Element)
	{
		String Value;
		List<WebElement> Elementlist=_Driver.findElements(By.xpath("//label[text()='Category']/../../div/*"));
		for(WebElement Ele:Elementlist)
		{	
			if(Ele.getAttribute("class").contains("input"))
			{
				Value=Ele.getAttribute("value");
				return Value;
			}
			else if(Ele.getAttribute("class").contains("select"))
			{
				Select sele = new Select(Ele);
				String sOption = sele.getFirstSelectedOption().getText();

				return sOption;
			}


		}
		return null;
	}

	public static boolean verifyWindowExists(WebDriver driver, String WindowTitle)
	{
		String  originalHandle = driver.getWindowHandle();
		boolean bWindowExists = false;
		try
		{					 
			Set<String> availableWindows = driver.getWindowHandles();			
			if (!availableWindows.isEmpty())
			{			
				for (String windowId : availableWindows)
				{
					driver.switchTo().window(windowId);
					waitForPageLoaded(driver);
					if(driver.getTitle().contains(WindowTitle))
					{
						System.out.println("Window with title "+WindowTitle+" exists");
						bWindowExists = true;
						break;
					}	
					else
					{
						driver.switchTo().window(originalHandle);
					}
				}
				return bWindowExists;
			}
			else
			{
				System.err.println("window doesnot exists");
				driver.switchTo().window(originalHandle);
				return bWindowExists;
			}			
		}
		catch (Exception e)
		{
			System.out.println(e);
			driver.switchTo().window(originalHandle);
			return bWindowExists;
		}
	}

	public static String getIDfromURL(WebDriver driver)
	{
		String currentURL =  driver.getCurrentUrl();
		String[] aURL = currentURL.split("/");
		String ID = aURL[aURL.length-1].replace("?tab=0", "").trim();

		System.out.println("Id selected is "+ID);
		return ID;
	}

	public static String getAttribute(WebDriver driver, By locator, String attribute) throws Exception
	{	
		String sAttr = "";

		if (isElementPresent(driver, locator))
		{
			sAttr = driver.findElement(locator).getAttribute(attribute);
		}
		else
		{
			System.err.println("Object doesn't exists");			
		}

		return sAttr;
	}

	public static String FetchID(WebDriver driver, String sObject)
	{
		String sSERVER =_properties.getProperty(GUI_automation_properties.SERVER).toLowerCase();
		String sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY).toLowerCase();		 

		String sGetURL = driver.getCurrentUrl();
		String sStringReplace ="http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/"+sObject;
		String sID = sGetURL.replace(sStringReplace, "").trim();	

		System.out.println("sID is "+sID);
		return sID;
	}


	public static boolean SearchValueFromDropDown(WebDriver driver, String sDropDownValue, String sValue)
	{
		boolean bValueFound = false;
		String sGetURL = driver.getCurrentUrl();

		try {
			CommonFunctions.selectDropdownByText(driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), sDropDownValue);
			CommonFunctions.SendValue(driver, By.name(Locators.getProperty(Locators.Search_TextField)), sValue);
			CommonFunctions.ClickElement(driver, By.name(Locators.getProperty(Locators.Find)));
			Thread.sleep(1000);
			String sURLVal = driver.getCurrentUrl();
			if (sURLVal.contains(sGetURL)) {
				int iRows = driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
				if (iRows > 0) {
					System.out.println("The value, "+sValue+" is filtered by "+sDropDownValue+" and the table displays the value. ");
					bValueFound = true;
				}
			}
			else if  (sURLVal.contains("detail")) {
				System.out.println("The value, "+sValue+" is filtered by "+sDropDownValue+" and the detail page is opened of the value. ");
				bValueFound = true;
			}
			else {
				System.out.println("Failed to filter the value, "+sValue+" using the criteria, "+sDropDownValue+" . ");			
			}
		}

		catch (Exception e)
		{
			System.out.println("search value failed");
			return bValueFound;
		}

		return bValueFound;
	}

	public static void ClearCart(WebDriver driver) throws Exception
	{
		CommonFunctions.waitForPageLoaded(driver);
		CommonFunctions.Wait(driver, By.xpath("//div[@class='mini-cart-label']//span[@class='item-number ng-binding']"));
		String No_Items_In_Cart= driver.findElement(By.xpath("//div[@class='mini-cart-label']//span[@class='item-number ng-binding']")).getText();

		int no_Items= Integer.parseInt(No_Items_In_Cart.trim());
		if(no_Items>0)
		{
			System.out.println("Items are available in the cart");
			driver.findElement(By.xpath("//div[@class='mini-cart-label']//span[@class='item-number ng-binding']")).click();
			waitForPageLoaded(driver);
			Thread.sleep(5000);
			CommonFunctions.WaitFor_ElementVisiblity(driver, By.xpath("//a[@class='cart-clear-button ng-scope']"));
			driver.findElement(By.xpath("//a[@class='cart-clear-button ng-scope']")).click();
			driver.findElement(By.xpath("//div[@class='modal-footer']/a[@class='OK-button']")).click();
			waitForPageLoaded(driver);

			System.out.println("Cart has been cleared");
		}
		else
		{
			System.out.println("Cart is Already empty ");
		}


	}

	public static String validateTheDropDownListOptions(WebDriver driver,By locator,String expectedString)
	{
		Select sele = new Select(driver.findElement(locator));
		List alloptions=sele.getOptions();
		int listSize=  alloptions.size();
		WebElement listOption;
		String Value=null;
		for(int i=0;i<listSize;i++)
		{
			listOption= (WebElement) alloptions.get(i);
			System.out.println("listOption is : "+listOption); 
			String actualText =listOption.getText().trim();
			System.out.println("actual text: "+ actualText);
			if(actualText.equalsIgnoreCase(expectedString))
			{
				Value=listOption.getAttribute("value"); 
				System.out.println("Expected option is present in the dropdown list that is : "+listOption);
				System.out.println("Value is :- " + Value);
				break;

			}
			else
			{
				System.err.println("Expected option : "+expectedString +"is not listed in the dropdown list ");

			}
		}
		return Value;
	}

	public static void WaitFor_ElementVisiblity(WebDriver driver,By Loc)
	{
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(Loc));
	}

	public static void ClickOn_Element_BasedOnClickable(WebDriver _driver, By Loc) throws Exception
	{

		ExpectedCondition<WebElement> element=ExpectedConditions.elementToBeClickable(Loc);
		//System.out.println("element is :"+ element);
		try
		{
			ClickOnElement(_driver,Loc);
		}
		catch (StaleElementReferenceException e)
		{
			ClickOnElement(_driver,Loc);
		}
	}

	public static void ClickOnElement(WebDriver _driver,By loc) throws Exception
	{
		Wait(_driver, loc);
		_driver.findElement(loc).click();
	}

	public static void selectDropdown_ByValue(WebDriver driver, By locator, String value)
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByValue(value);

		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static void enterText(WebDriver driver, By locator, String text) throws Exception
	{
		Wait(driver,locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text) ; 
		System.out.println("Entered the text as "+text);
	}

	public static void ClickOn_Elemet_BasedOnVisiblity(WebDriver _driver,By Locator) throws Exception
	{
		ExpectedCondition<WebElement> element=ExpectedConditions.visibilityOfElementLocated(Locator);
		ClickOnElement(_driver,Locator);

	}

	public static void selectDropdown_ByvisibleText(WebDriver driver, By locator, String value)
	{
		Select sele = new Select(driver.findElement(locator));
		sele.selectByVisibleText(value);

		//new Select (driver.findElement(locator)).selectByVisibleText(value); 
	}

	public static boolean VerifyValueisNotNull(WebDriver _driver,By field_xpath,String fieldName)
	{
		String isValue=_driver.findElement(field_xpath).getText().trim();
		if(!isValue.isEmpty())
		{
			return true;
		}
		else
		{
			System.err.println("value is empty for the field : "+fieldName);
			return false;
		}
	}

	public static String getText(WebDriver driver,By loc)
	{
		String actualText= driver.findElement(loc).getText().trim();
		System.out.println("fetched text is : "+actualText);
		return actualText;
	}

	//modify date/time format as required
	public static String changeFormat(String Date_Time, String currentFormat, String desiredFormat) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat(currentFormat);
		SimpleDateFormat myFormat = new SimpleDateFormat(desiredFormat);

		return myFormat.format(fromUser.parse(Date_Time));
	}

	public static void waitForDSFPageLoad(WebDriver driver) throws Exception
	{
		do 
		{
			Thread.sleep(2000);

		} while (CommonFunctions.isElementPresent(driver, By.xpath("//div[@id='loadingSpinner' and contains(@style, 'display: block')]")));
	}
	
	public static void closeAllChildWindows(WebDriver _driver) throws Exception
	{
		_driver.switchTo().window(CommonOriginalHandle);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty())
		{
			for (String windowId : availableWindows)
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(!windowId.equals(CommonOriginalHandle))
				{
					_driver.close();
					Thread.sleep(1000);
				}
			}
		}
		_driver.switchTo().window(CommonOriginalHandle);
	}

	public static String GetCellData(WebDriver driver, int rowNum, int colNum, String tableXPath)
	{
		String sObjData = "";
		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		try
		{
			List<WebElement> sFetchV = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/*"));
			int count = 0;
			int ChBxInd = 0;
			for(WebElement obj1 : sFetchV)
			{			
				ChBxInd = ChBxInd+1;
				String nodeName = obj1.getAttribute("nodeName");
				if (count == 0)
				{
					if(nodeName.equals("DIV"))
					{
						String sCName =	 driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getAttribute("class");
						if(sCName.equals("wrappingLabel") || sCName.equals("label") || sCName.equals("notesWrap"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getText().trim();
							if (!sObjData.startsWith("-"))
							{
								String[] parts = sObjData.split("-");
								sObjData = parts[0];
							}
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("viewLinkContainerGrid"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/a")).getText();
							String[] parts = sObjData.split("-");
							sObjData = parts[0];
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("forcingDefault") || sCName.equals("forcingForced"))
						{
							if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")))
							{
								String sChildCName = driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")).getAttribute("class");
								if (sChildCName.equals("label sizeWidget"))
								{
									List<WebElement> size = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div/span"));

									for (WebElement lenwid : size)
									{
										sObjData = sObjData+lenwid.getText().trim();
									}
								}
								else if (sChildCName.equals("label"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")).getText().trim();
									if (!sObjData.startsWith("-"))
									{
										String[] parts = sObjData.split("-");
										sObjData = parts[0];
									}
								}
								else if(sCName.equals("viewLinkContainerGrid"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div/a")).getText();
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							else if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/span")))
							{								
								sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/span")).getText().trim();
								if (!sObjData.startsWith("-"))
								{
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							else if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/input")))
							{								
								sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/input[1]")).getAttribute("value").trim();
								if (!sObjData.startsWith("-"))
								{
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("INPUT"))
					{
						String sType =	 driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).getAttribute("type");
						if(sType.equals(null) || sType.equals("") || sType.equals("text"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input")).getAttribute("value");
							String[] parts = sObjData.split("-");
							sObjData = parts[0];
							sObjData = sObjData.trim();
							count++;
						}
						else if(sType.equals("checkbox"))
						{
							boolean sFetchValue1=  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).isSelected();
							sObjData = "" +sFetchValue1;
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("SELECT"))
					{
						sObjData =  GetSelectedOption(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/select"));
						sObjData = sObjData.trim();
						count++;
					}
					else if(nodeName.equals("TEXTAREA"))
					{							
						sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/textarea")).getText().trim();
						count++;
					}
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return sObjData;
		}
		return sObjData;
	}

	public static List<String> GetColumnData (WebDriver driver, String sColumnName, String tableXPath) throws Exception
	{
		String sFetchValue = "";
		int rowcount = 0, ColCount = 0;
		List <String> SortedList = new ArrayList<String>();
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			ColCount = driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();
			if (driver.findElements(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")).size() > 0)
			{
				Select dropDown = new Select(driver.findElement(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")));
				java.util.List<WebElement> options = dropDown.getOptions();
				int iPages  = options.size();
				selectDropdownByText(driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);

				rowcount = driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
				String sTotalRecord =driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
				int sTotalRecords = Integer.parseInt(sTotalRecord);

				//fetch the values of the 
				for(int h = 3;h<=ColCount;h++)
				{
					String sCName1 = driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();				 
					if(sCName1.equals(sColumnName))
					{
						if(rowcount>0 && sTotalRecords<1000)
						{
							for(int c = 1 ;c<=iPages;c++)
							{
								rowcount = driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
								for(int j = 1 ;j<=rowcount;j++)
								{
									sFetchValue = GetCellData(driver, j, h, tableXPath);
									SortedList.add(sFetchValue);					
								}							
								int d = c+1;
								String e = String.valueOf(d);
								if(iPages!=c)
								{
									selectDropdownByText(driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), e);
									Thread.sleep(1000);
								}					
							}					
						}
						else
						{
							Assert.fail("RowCount is less then 0"+rowcount+" or Total records are more then 1000"+sTotalRecords);
						}
					}					
				}
				selectDropdownByText(driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);
			}
			else
			{
				//fetch the values
				for(int h = 3;h<=ColCount;h++)
				{
					rowcount = driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
					String sCName1 = driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();				 
					if(sCName1.equals(sColumnName))
					{
						if(rowcount>0)
						{
							for(int j = 1 ;j<=rowcount;j++)
							{
								sFetchValue = GetCellData(driver, j, h, tableXPath);							
								SortedList.add(sFetchValue);					
							}					
						}
						else
						{
							Assert.fail("RowCount is less then 0"+rowcount);
						}
					}					
				}	
			}
			return SortedList;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("GetColumnData failed due to exception mentioned");
			return null;
		}
	}

	public static int SearchTable4Value (WebDriver driver, String ColumnName, String tableXPath, String Value) throws Exception
	{
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			List<String> lColumnData  = GetColumnData (driver, ColumnName, tableXPath);

			if (lColumnData.indexOf(Value) < 0)
			{
				System.err.println("Row Number = "+lColumnData.indexOf(Value));
				return lColumnData.indexOf(Value);
			}
			else
			{
				System.out.println("Row Number = "+(lColumnData.indexOf(Value)+1));
				return lColumnData.indexOf(Value)+1;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("SearchTable4Value failed due to exception mentioned");
			return -1;
		}
	}

	public static int getColumnNum(WebDriver driver, String ColumnName, String tableXPath) throws Exception
	{

		int ColCount = 0, colNum = 0;
		String sCName = "";
		try
		{			
			//**********Remove Sort for the columns specified*******************
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			ColCount = driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();			
			for(int i=1;i<=ColCount;i++)
			{	
				if(isElementPresent(driver, By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")))
				{
					sCName = driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
					if(sCName.equals(ColumnName))
					{		
						colNum = i;
						break;
					}
				}
			}
			return colNum;
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return colNum;
		}

	}

	public static int getRowNum (WebDriver driver, String ColumnName, String ColumnValue, String tableXPath) throws Exception
	{
		int iRowNumber = -1;

		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			List<String> lColumnData  = GetColumnData(driver, ColumnName, tableXPath);

			if (lColumnData.indexOf(ColumnValue) < 0)
			{
				System.err.println("Row Number = "+lColumnData.indexOf(ColumnValue));
			}
			else
			{ 				
				iRowNumber = lColumnData.indexOf(ColumnValue)+1;
				if (isElementPresent(driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")))
				{
					if (iRowNumber > 20)
					{
						iRowNumber = iRowNumber/20;
						System.out.println("Row Number = "+iRowNumber);
					}
				}
			}
			return iRowNumber;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("getRowNum failed due to exception mentioned");
			return -1;
		}
	}

	public static boolean launchTableElement (WebDriver driver, String value, String columnName, String tableXPath) throws Exception
	{
		String sOldTitle = driver.getTitle();
		if (tableXPath.equals("")) {

			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		int searchValueIndex = SearchTable4Value(driver, columnName, tableXPath, value);
		if (searchValueIndex > 0)
		{
			if (searchValueIndex > 20) 
			{
				if (driver.findElements(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")).size() > 0)
				{
					String pageIndex = String.valueOf(searchValueIndex%20);
					String valueRowNum = String.valueOf(searchValueIndex/20);

					selectDropdownByText(driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), pageIndex);
					Thread.sleep(1000);

					//click magnifying glass next to record
					driver.findElement(By.xpath(tableXPath+"//tbody/tr["+valueRowNum+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);
				}
				else
				{
					//click magnifying glass next to record
					driver.findElement(By.xpath(tableXPath+"//tbody/tr["+searchValueIndex+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);	
				}
			}
			else
			{
				//click magnifying glass next to record
				driver.findElement(By.xpath(tableXPath+"//tbody/tr["+searchValueIndex+"]/td[2]/div/a/img")).click();
				Thread.sleep(2000);	
			}
		}
		else
		{
			System.err.println("The given entry '"+value+"' was not found in '"+columnName+"' column");
		}

		waitForPageLoaded(driver);
		String sNewTitle = driver.getTitle();
		if (sNewTitle.equals(sOldTitle))
		{
			return false;
		}
		else
		{
			return true;	
		}
	}

	public static String GetCellDataByColName(WebDriver driver, int rowNum, String ColnumName, String tableXPath)
	{
		String sObjData = "";
		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		try
		{
			int colNum = getColumnNum(driver, ColnumName, tableXPath);

			List<WebElement> sFetchV = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/*"));
			int count = 0;
			int ChBxInd = 0;
			for(WebElement obj1 : sFetchV)
			{			
				ChBxInd = ChBxInd+1;
				String nodeName = obj1.getAttribute("nodeName").toUpperCase();
				if (count == 0)
				{
					if(nodeName.equals("DIV"))
					{
						String sCName =	driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getAttribute("class");
						if(sCName.equals("wrappingLabel") || sCName.equals("label") || sCName.equals("notesWrap"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getText().trim();
							if (!sObjData.startsWith("-"))
							{
								String[] parts = sObjData.split("-");
								sObjData = parts[0];
							}
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("viewLinkContainerGrid"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/a")).getText();
							String[] parts = sObjData.split("-");
							sObjData = parts[0];
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("forcingDefault") || sCName.equals("forcingForced"))
						{
							if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")))
							{
								String sChildCName = driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")).getAttribute("class");
								if (sChildCName.equals("label sizeWidget"))
								{
									List<WebElement> size = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div/span"));

									for (WebElement lenwid : size)
									{
										sObjData = sObjData+lenwid.getText().trim();
									}
								}
								else if (sChildCName.equals("label"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div")).getText().trim();
									if (!sObjData.startsWith("-"))
									{
										String[] parts = sObjData.split("-");
										sObjData = parts[0];
									}
								}
								else if(sCName.equals("viewLinkContainerGrid"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/div/a")).getText();
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							else if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/span")))
							{								
								sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/span")).getText().trim();
								if (!sObjData.startsWith("-"))
								{
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("INPUT"))
					{
						String sType =	 driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).getAttribute("type");
						if(sType.equals(null) || sType.equals("") || sType.equals("text"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input")).getAttribute("value").trim();
							count++;
						}
						else if(sType.equals("checkbox"))
						{
							boolean sFetchValue1=  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).isSelected();
							sObjData = "" +sFetchValue1;
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("SELECT"))
					{							
						sObjData =  GetSelectedOptionValue(driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/select")).trim();
						count++;
					}
					else if(nodeName.equals("TEXTAREA"))
					{							
						sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/textarea")).getText().trim();
						count++;
					}
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return sObjData;
		}
		return sObjData;
	}

	public static String GetCellDataByKey(WebDriver driver, String KeyColumnName, String KeyColumnValue, String GetColumnName, String tableXPath) throws Exception
	{
		String sObjData = "";
		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		int iKeyValueRowNum = getRowNum(driver, KeyColumnName, KeyColumnValue, tableXPath); 

		try
		{
			int colNum = getColumnNum(driver, GetColumnName, tableXPath);

			List<WebElement> sFetchV = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/*"));
			int count = 0;
			int ChBxInd = 0;
			for(WebElement obj1 : sFetchV)
			{			
				ChBxInd = ChBxInd+1;
				String nodeName = obj1.getAttribute("nodeName").toUpperCase();
				if (count == 0)
				{
					if(nodeName.equals("DIV"))
					{
						String sCName =	driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div")).getAttribute("class");
						if(sCName.equals("wrappingLabel") || sCName.equals("label") || sCName.equals("notesWrap"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div")).getText().trim();
							if (!sObjData.startsWith("-"))
							{
								String[] parts = sObjData.split("-");
								sObjData = parts[0];
							}
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("viewLinkContainerGrid"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/a")).getText();
							String[] parts = sObjData.split("-");
							sObjData = parts[0];
							sObjData = sObjData.trim();
							count++;
						}
						else if(sCName.equals("forcingDefault") || sCName.equals("forcingForced"))
						{
							if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/div")))
							{
								String sChildCName = driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/div")).getAttribute("class");
								if (sChildCName.equals("label sizeWidget"))
								{
									List<WebElement> size = driver.findElements(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/div/span"));

									for (WebElement lenwid : size)
									{
										sObjData = sObjData+lenwid.getText().trim();
									}
								}
								else if (sChildCName.equals("label"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/div")).getText().trim();
									if (!sObjData.startsWith("-"))
									{
										String[] parts = sObjData.split("-");
										sObjData = parts[0];
									}
								}
								else if(sCName.equals("viewLinkContainerGrid"))
								{
									sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/div/a")).getText();
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							else if (isElementPresent(driver, By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/span")))
							{								
								sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/div/span")).getText().trim();
								if (!sObjData.startsWith("-"))
								{
									String[] parts = sObjData.split("-");
									sObjData = parts[0];
								}
							}
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("INPUT"))
					{
						String sType =	 driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).getAttribute("type");
						if(sType.equals(null) || sType.equals("") || sType.equals("text"))
						{
							sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/input")).getAttribute("value").trim();
							count++;
						}
						else if(sType.equals("checkbox"))
						{
							boolean sFetchValue1=  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).isSelected();
							sObjData = "" +sFetchValue1;
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("SELECT"))
					{							
						sObjData =  GetSelectedOptionValue(driver, By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/select")).trim();
						count++;
					}
					else if(nodeName.equals("TEXTAREA"))
					{							
						sObjData =  driver.findElement(By.xpath(tableXPath+"/tbody/tr["+iKeyValueRowNum+"]/td["+colNum+"]/textarea")).getText().trim();
						count++;
					}
				}
				else
				{
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return sObjData;
		}
		return sObjData;
	}

}