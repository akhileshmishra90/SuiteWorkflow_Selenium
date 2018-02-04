package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
public class DSF_Integration_Page implements BaseElement
{

	private static final int BUFFER_SIZE = 4096;
	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	private String _pageURL;
	protected WebDriver _driver;
	public String sLogs = null;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	private static  String sSERVER = null;
	private static  String sCOMPANY = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();


	public  DSF_Integration_Page(WebDriver driver) throws Exception 
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

	public String  UniqueAccount()
	{
		DateFormat dateFormat = new SimpleDateFormat("hhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String  ShippingMethod()
	{
		DateFormat dateFormat = new SimpleDateFormat("hhss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		return suniqueNumber;
	}

	public String  DSF_Cust()
	{
		DateFormat dateFormat = new SimpleDateFormat("hh_ss");
		Date date = new Date();
		String suniqueNumber = "DSF_Cust_"+dateFormat.format(date);
		return suniqueNumber;
	}

	//This is to click any alert boxes. It will click on 'OK' in alerts
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
			_driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}

	public void Logout() throws Exception, IOException
	{
		System.out.println("****Logging off****");
		_driver.findElement(By.id(Locators.getProperty(Locators.LogOutLink))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
	}

	// Verify the text - Object Added
	public boolean Object_Added() throws Exception, IOException
	{
		if(CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))
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
		else
		{
			System.out.println ("****Object is not added****");
			return false;
		}
	}


	// Click on Update button
	public boolean Update() throws Exception, IOException
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		System.out.println ("****Clicking the Update button****");
		Thread.sleep(1000);
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text))))
		{
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
		else
		{
			System.out.println ("**Page is not updated**");
			return false;
		}		  
	}

	//Check a checkbox
	public void selectCheckBox(boolean sFlag, By sLoc) throws Exception
	{
		if(sFlag == true)
		{
			if(_driver.findElement(sLoc).isSelected())
			{
				System.out.println("Checkbox is already selected");
			}
			else
			{
				_driver.findElement(sLoc).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				System.out.println("Selecting the checkbox");
			}
		}
		else if(sFlag == false)
		{
			if(_driver.findElement(sLoc).isSelected())
			{
				_driver.findElement(sLoc).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				System.out.println("Uncheching the checkbox");
			}
			else
			{
				System.out.println("Checkbox is already unchecked");
			}
		}
		else
		{
			System.err.println("Not able to select check Box");
		}
	}

	public void LogOffUsers() throws Exception
	{
		if( _driver.findElements(By.xpath(Locators.getProperty(Locators.Log_Off_Users))).size()>0)
		{
			_driver.findElement(By.xpath("//table[@id='users']/thead/tr/th[9]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Log_Off_Users))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.xpath("//div[text()='Welcome to EFI Pace, an innovation from EFI, Inc.']"));
			assertEquals("Welcome to EFI Pace",_driver.getTitle());
		}
	}	

	//**************************** Till here it is common for all*****************************

	public void EpaceLogin(String sUN,String sPWD,String sCompany) throws Exception, IOException
	{
		System.out.println("Launch & Login Start");
		String sLoggedinUserpath = "//div[@id='welcomeMessage']/a[text()='"+sUN+"']";

		if(_driver.findElements(By.id(Locators.getProperty(Locators.Logout))).size()>0)
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.Logout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}

		assertEquals("EFI Pace Print Management System Powered by EFI: Login", _driver.getTitle());

		if(_driver.findElements(By.name(Locators.getProperty(Locators.Login_Username))).size()>0)
		{
			System.out.println("Able to see Login Text");
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Username))).sendKeys(sUN);
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Password))).sendKeys(sPWD);
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Login_Company)), sCompany);
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Login_Submit))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver,By.id(Locators.getProperty(Locators.TestMode)));
			LogOffUsers();
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).size() > 0)
			{ 
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);

				boolean sFlag= CommonFunctions.isElementPresent(_driver,By.xpath(sLoggedinUserpath)); 
				if(sFlag == true)
				{

					System.out.println("Able to Login Successfully");	
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					//System.out.println(NewFileNamePath);
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
				boolean sFlag1= CommonFunctions.isElementPresent(_driver,By.xpath(sLoggedinUserpath)); 
				if(sFlag1 == true)
				{
					NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
					File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(NewFileNamePath));
					//System.out.println(NewFileNamePath);
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
			System.err.println("Not Able to see Login fields");
		}

	} 



















	public void DSFLogin(String sUN,String sPWD) throws Exception, IOException
	{
		//System.out.println("Author: Ashish");

		//assertEquals("ePace_Storefront-Storefront", _driver.getTitle());

		if(_driver.findElements(By.name(Locators.getProperty(Locators.DSF_Logo))).size()>0)
		{
			System.out.println("Able to see DSF Logo");
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_UserName))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_UserName))).sendKeys(sUN);
			System.out.println("Entering the UserName as: "+sUN);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Password))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Password))).sendKeys(sPWD);
			System.out.println("Entering the Password as: "+sPWD);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Login))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			System.out.println("Clicking the Login button");
			Thread.sleep(6000);
		}

		else
		{
			System.err.println("Not Able to see Login Text");
		}
	}


	//------------------------------------------------------------------------------------------------

	//Verify that license string is not empty and fulfillment is enabled   
	public boolean DSF_License() throws Exception, IOException
	{
		boolean isFulfillment = false;
		boolean islicenceString = false;
		boolean isLicense = false;
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.License))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("**Navigating to License Page**");
		boolean fulfillment = _driver.findElement(By.xpath(Locators.getProperty(Locators.Fulfillment))).isSelected();
		if (fulfillment == true)
		{
			System.out.println ("Fulfillment is checked");
			isFulfillment = true;
		}
		else
		{
			System.out.println ("Fulfillment is not checked");
			isFulfillment = false;
		}

		String licenseString = _driver.findElement(By.name(Locators.getProperty(Locators.License_String))).getText();
		if (licenseString == null)
		{
			System.out.println ("License String is not present");
			islicenceString = false;
		}
		else
		{
			System.out.println ("License String is present");
			islicenceString = true;
		}
		if(islicenceString == true && isFulfillment == true)
		{
			System.out.println ("License is verified");
			isLicense = true;
		}
		else
		{
			System.out.println ("License cannot be verified");
			isLicense = false;
		}
		return isLicense;

	}

	//Verify that the communication channel is present. If not present, create one
	//Get the name of the communication channel from the excel sheet
	public boolean Communication_Channels(String commChannel, String channel) throws Exception, IOException
	{
		boolean isChannelPresent = false;
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Manage_Communication_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("**Navigating to Manage Communication Channels Page**");
		Thread.sleep(3000);

		//Verify whether the expected communication channel is present
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 1; i<=rowCount; i++)
		{
			String communicationChannel = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr["+i+"]/td[2]")).getText(); 
			communicationChannel = communicationChannel.trim();
			System.out.println("Communication Channel in Row "+ i + " is: " +communicationChannel);

			if(communicationChannel.equals(commChannel))
			{
				System.out.println("commChannel = " +commChannel);
				System.out.println("Expected Communication Channel is present in row: "+i);
				isChannelPresent = true;
				break;
				//Since the channel is present, exit the loop.
			}
		}

		if(_driver.findElements(By.linkText(commChannel)).size()>0)
		{
			isChannelPresent=true;
		}
		//If channel is not present, create one
		if(isChannelPresent == false)
		{
			System.out.println("The communication channel: "+commChannel+" is not present. Hence creaing one");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			System.out.println("Clicking the Add New Channel button");

			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Channel_Type)), "Direct Channel");
			Thread.sleep(1000);
			//_driver.findElement(By.name(Locators.getProperty(Locators.Channel_Name))).sendKeys(commChannel);
			_driver.findElement(By.xpath("//input[@id='ctl00_ctl00_C_M_txtName']")).sendKeys(commChannel);

			//_driver.findElement(By.name(Locators.getProperty(Locators.Channel_Description))).sendKeys(commChannel);
			_driver.findElement(By.xpath("//textarea[@id='ctl00_ctl00_C_M_txtDescription']")).sendKeys(commChannel);


			channel = channel.trim().toLowerCase();
			System.out.println("channel is: "+channel);

			//_driver.findElement(By.xpath(Locators.getProperty(Locators.Site_Default_Certificate))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Configuration_URL))).sendKeys("http://"+channel+"/rpc/services/EFIConfiguration");
			System.out.println("Entered all the details needed to create a channel");

			_driver.findElement(By.name(Locators.getProperty(Locators.Register_New_Communication_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			System.out.println("Clickinig the Register button");
			Thread.sleep(7000);

			CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$M$btnEditInteg"));
			Thread.sleep(2000);
			//http://10.210.98.211/rpc/company:public/services/EFIIntegration
			CommonFunctions.SendValue(_driver, By.name("ctl00$ctl00$C$M$txtPopUpIntegUrl"), "http://"+commChannel.toLowerCase()+"/rpc/company:public/services/EFIIntegration");
			CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$M$btnUpdateIntegUrl"));
			Thread.sleep(3000);

			boolean isRegisteredSuccessfully = _driver.findElements(By.xpath(Locators.getProperty(Locators.Message_Table_Success))).size()>0;
			if (isRegisteredSuccessfully == true)
			{
				System.out.println("Site is registered successfully");
				isChannelPresent = true;
				_driver.findElement(By.name(Locators.getProperty(Locators.Back_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
			}
			else
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Back_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				System.out.println("Channel could not be successfully registered");
				isChannelPresent = false;
			}

		}

		return isChannelPresent; 
	}

	//Verify that the communication channel is registered
	//Get the license ID of the communication channel
	//Execute the method: Communication_Channels(String commChannel) just before executing this one
	public String Get_License_ID(String commChannel) throws Exception, IOException
	{
		String licenseID = null;
		boolean isRegisteredSuccessfully = false;

		//Verify whether the expected communication channel is present
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr")).size();

		for (int i = 1; i<=rowCount; i++)
		{
			String communicationChannel = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr["+i+"]/td[2]")).getText(); 
			communicationChannel = communicationChannel.trim();

			if (communicationChannel.equals(commChannel))
			{
				System.out.println("Expected Communication Channel is present");

				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr["+i+"]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				System.out.println("Clicking the Communication Channel");

				boolean isCheckStatusEnabled = _driver.findElement(By.name(Locators.getProperty(Locators.Check_Status))).isEnabled(); 
				System.out.println("Is Check Status button enabled? "+isCheckStatusEnabled);
				if (isCheckStatusEnabled == true)
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Check_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded(_driver);
					System.out.println("Clicking the Check Status button");

					Thread.sleep(6000);

					boolean isErrorMessage = _driver.findElements(By.xpath(Locators.getProperty(Locators.Message_Table_Failure))).size()>0;
					System.out.println("isErrorMessage: " +isErrorMessage);
					if(isErrorMessage == true)
					{
						String failureMessage = _driver.findElement(By.xpath("//table[2]/tbody/tr/td/table[@id='ctl00_ctl00_C_M_tblFailure']/tbody/tr/td/span")).getText();
						System.out.println("Failure message is: " +failureMessage);
						if(failureMessage.equals("Site License status is pending. Please check again in few minutes."))
						{
							Thread.sleep(10000);
							System.out.println("Waiting for 10 seconds");
							_driver.findElement(By.name(Locators.getProperty(Locators.Back_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
							CommonFunctions.waitForPageLoaded(_driver);
							System.out.println("Navigating back to the list of the channels");
							_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr["+i+"]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
							System.out.println("Again clicking the communication channel");
							_driver.findElement(By.name(Locators.getProperty(Locators.Check_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
							System.out.println("Again clicking the Check Status button");
							_driver.findElement(By.name(Locators.getProperty(Locators.Unregister_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
							System.out.println("Unregestering the channel");
							Thread.sleep(3000);
							_driver.findElement(By.name(Locators.getProperty(Locators.Register_New_Communication_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
							System.out.println("Regestering the channel");
							Thread.sleep(3000);
							_driver.findElement(By.name(Locators.getProperty(Locators.Check_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
							Thread.sleep(3000);
						}
					}

					Thread.sleep(3000);

					isRegisteredSuccessfully = _driver.findElements(By.xpath(Locators.getProperty(Locators.Message_Table_Success))).size()>0;
					System.out.println("isRegisteredSuccessfully is : " +isRegisteredSuccessfully);
					if (isRegisteredSuccessfully == true)
					{
						System.out.println("Site is Licensed for Communication");
					}
					else
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Unregister_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
						Thread.sleep(3000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Register_New_Communication_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
						Thread.sleep(3000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Check_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
						Thread.sleep(3000);
						isRegisteredSuccessfully = _driver.findElements(By.xpath(Locators.getProperty(Locators.Message_Table_Success))).size()>0;
						if(isRegisteredSuccessfully == false)
						{
							System.out.println("Channel could not be successfully registered. Verify that PaceConnect is licensed.");
						}
					}
				}
				else
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Register_New_Communication_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);CommonFunctions.waitForPageLoaded(_driver);
					System.out.println("Clicking the Register button");

					//Message table
					isRegisteredSuccessfully = _driver.findElements(By.xpath(Locators.getProperty(Locators.Message_Table_Success))).size()>0;
					if (isRegisteredSuccessfully == true)
					{
						System.out.println("Site is registered successfully");
					}
					else
					{
						System.out.println("Channel could not be successfully registered. Verify that PaceConnect is licensed.");
					}
				}

				if(isRegisteredSuccessfully == true)
				{

					Thread.sleep(5000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Back_Channel))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(1000);
					licenseID = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_EFIGridControlChannels']/tbody/tr["+i+"]/td[4]/span")).getText(); 
					licenseID = licenseID.trim();
					System.out.println("License ID of Communication Channel: " +commChannel+ " is: " +licenseID);
				}

				break;
			}
		}
		return licenseID;
	}

	//Create MIS in DSF
	public boolean Create_MIS(String commChannel, String channel) throws Exception
	{

		PurchasePage PO = new PurchasePage(_driver);
		boolean isMISPresent = false;

		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Systems))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to MIS Systems Page**");
		CommonFunctions.waitForPageLoaded(_driver);

		//Verify whether the expected MIS System is present or not
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			String MIS_System = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr["+i+"]/td[2]/a")).getText(); 
			MIS_System = MIS_System.trim();
			System.out.println("MIS System in Row "+ i + " is: " +MIS_System);

			if (MIS_System.equals(commChannel))
			{
				System.out.println("Expected MIS System is present");
				isMISPresent = true;

				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr["+i+"]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);				 
				System.out.println("Clicking the MIS System");
				Thread.sleep(2000);

				//_driver.findElement(By.xpath("//a[@id='SetupTab']/span/span/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='Settings']"));
				System.out.println("Clicking the Setup tab");
				PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$CheckBoxTransmitsInvoicePdf"));
				PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$CheckBoxDoesBilling"));
				CommonFunctions.selectDropdownByText(_driver, By.id("ctl00_ctl00_C_M_DropDownListCreditCard"), "Charge on MIS Request");
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Save button");

				Thread.sleep(5000);

				_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Register))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(1000);
				System.out.println("Clicking the Register button");


				String successMessage = _driver.findElement(By.xpath("//div[@id='ctl00_ctl00_C_M_Feedback_DivSuccess']/table/tbody/tr/td[2]/span")).getText();
				successMessage = successMessage.trim();
				if (successMessage.equals("Successfully Registered the site for Asynchronous Communication."))
				{
					System.out.println("Successfully Registered the site");
					isMISPresent = true;
				}
				else
				{
					System.out.println("MIS System could not be registered");
					isMISPresent = false;
				}

				break;
				//Since the channel is present, exit the loop.
			}
		}

		//If channel is not present, create one
		if(isMISPresent == false)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Add_New_MIS))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Add New MIS button");

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.MIS_Type)), "EFI Pace");
			Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Name))).sendKeys(commChannel);

			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Description))).sendKeys(commChannel);

			_driver.findElement(By.name(Locators.getProperty(Locators.Routing_Identifier))).sendKeys(commChannel+"1");

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Communication_Channel)), commChannel);
			Thread.sleep(1000);

			CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='Settings']"));
			System.out.println("Clicking the Settings tab");
			_driver.findElement(By.name(Locators.getProperty(Locators.Asynchronous_Order_Submission))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Accepts_Single_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.Update_Order_With_Final_Invoice))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_URL))).sendKeys("http://"+channel+"/");
			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_User_Name))).sendKeys("Administrator");
			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Password))).sendKeys("administrator");
			System.out.println("Entered all the details wrt a new MIS System");

			PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$CheckBoxTransmitsInvoicePdf"));
			PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$CheckBoxDoesBilling"));
			CommonFunctions.selectDropdownByText(_driver, By.id("ctl00_ctl00_C_M_DropDownListCreditCard"), "Charge on MIS Request");
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Save button");

			Thread.sleep(5000);

			_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Register))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Register button");

			String successMessage = _driver.findElement(By.xpath("//div[@id='ctl00_ctl00_C_M_Feedback_DivSuccess']/table/tbody/tr/td[2]/span")).getText();
			successMessage = successMessage.trim();
			if (successMessage.equals("Successfully Registered the site for Asynchronous Communication."))
			{
				System.out.println("Success has been achieved");
				isMISPresent = true;
			}
			else
			{
				System.out.println("MIS System could not be registered");
				isMISPresent = false;
			}

			/*String failureMessage = _driver.findElement(By.xpath("//div[@id='ctl00_ctl00_C_M_Feedback_DivFailure']/table/tbody/tr/td[2]/span")).getText();
			failureMessage = successMessage.trim();
			if (failureMessage.equals("Registration for MIS Update Failed. Please see event log for more details or Contact the site administrator."))
			{
				System.out.println("MIS System could not be registered");
				isMISPresent = false;
			}*/
		}

		return isMISPresent;
	}

	//Create Print Shop in DSF
	public boolean Create_PrintShop(String commChannel) throws Exception, IOException
	{
		boolean isPrintShopPresent = false;

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Print_Shops))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Print Shops**");

		//Verify whether the expected Print Shop is present or not	 
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllFacilities']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);
		int i = 1;
		for (i = 1; i<=rowCount; i++)
		{
			String printShop = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllFacilities']/tbody/tr["+i+"]/td[1]")).getText(); 
			printShop = printShop.trim();
			System.out.println("Print Shop in Row "+ i + " is: " +printShop);

			if (printShop.toLowerCase().equals(commChannel.toLowerCase()))
			{
				System.out.println("Expected Print Shop is present");
				isPrintShopPresent = true;
				break;
				//Since the Print Shop is present, exit the loop.
			}
		}

		//If Print Shop is not present, create one
		if(isPrintShopPresent == false)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Add_New_Print_Shop))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Add New Print Shop button");
			Thread.sleep(3000);

			_driver.findElement(By.name(Locators.getProperty(Locators.Print_Shop_Name))).sendKeys(commChannel);
			System.out.println("Entering the print shop name");

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Accounting_Codes)));
			System.out.println("Enabling the Accounting Codes checkbox");
			Thread.sleep(1000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Cost_Center)));
			System.out.println("Enabling the Cost Center checkbox");
			Thread.sleep(3000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Credit_Card_Payment_Method)));
			System.out.println("Enabling the Credit Card checkbox");
			//Thread.sleep(1000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Other_Account)));
			System.out.println("Enabling the Other Account checkbox");
			Thread.sleep(1000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Pay_At_Store_Payment_Method)));
			System.out.println("Enabling the Pay at store checkbox");
			//Thread.sleep(1000);

			selectCheckBox(false, By.name(Locators.getProperty(Locators.Pay_Pal)));
			System.out.println("Disabling the PayPal checkbox");
			Thread.sleep(1000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.PO_Number_Payment_Method)));
			System.out.println("Enabling the PONumber checkbox");
			Thread.sleep(1000);

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Credit_Card_Merchant)), "PFP");
			System.out.println("Select the Credit Card Merchant as PFP");
			Thread.sleep(1000);

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Default_Payment_Method)), "Pay At Store");
			System.out.println("Select the default payment method as Pay At Store");
			Thread.sleep(1000);

			CommonFunctions.SendValue(_driver, By.id("ctl00_ctl00_C_M_txtHandlingChargesShipment"), "20");
			System.out.println("Entering handling charges");

			selectCheckBox(true, By.xpath(Locators.getProperty(Locators.MIS_System_Checkbox)));
			System.out.println("Enabling the MIS System checkbox");
			Thread.sleep(3000);

			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.MIS_Dropdown)), commChannel);
			//CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.MIS_Dropdown)), "Seahorse");
			System.out.println("Selecting the MIS System as: "+commChannel);
			Thread.sleep(2000);


			_driver.findElement(By.xpath(Locators.getProperty(Locators.Contact_Information))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(1000);

			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_First_Name))).sendKeys("First Name");
			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_Last_Name))).sendKeys("Last Name");
			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_Addrss_1))).sendKeys("Addtress 1");
			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_City))).sendKeys("City");
			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_Zip_Code))).sendKeys("80001");
			_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Information_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			System.out.println("Entered all the details of the Contact Information");

			_driver.findElement(By.name(Locators.getProperty(Locators.PrintShop_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Saving the printshop");
			isPrintShopPresent = true;

		}
		else
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllFacilities']/tbody/tr["+i+"]/td[1]/a"));
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(1000);

			selectCheckBox(true, By.name(Locators.getProperty(Locators.Accounting_Codes)));
			Thread.sleep(1000);
			selectCheckBox(true, By.name(Locators.getProperty(Locators.Cost_Center)));
			Thread.sleep(1000);
			selectCheckBox(true, By.name(Locators.getProperty(Locators.Credit_Card_Payment_Method)));
			Thread.sleep(1000);
			selectCheckBox(true, By.name(Locators.getProperty(Locators.Other_Account)));
			Thread.sleep(1000);
			selectCheckBox(true, By.name(Locators.getProperty(Locators.Pay_At_Store_Payment_Method)));
			Thread.sleep(1000);
			selectCheckBox(false, By.name(Locators.getProperty(Locators.Pay_Pal)));
			Thread.sleep(1000);
			selectCheckBox(true, By.name(Locators.getProperty(Locators.PO_Number_Payment_Method)));
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Credit_Card_Merchant)), "PFP");
			Thread.sleep(1000);			 
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Default_Payment_Method)), "Pay At Store");
			Thread.sleep(1000);			 
			CommonFunctions.SendValue(_driver, By.id("ctl00_ctl00_C_M_txtHandlingChargesShipment"), "20");
			selectCheckBox(true, By.xpath(Locators.getProperty(Locators.MIS_System_Checkbox)));
			Thread.sleep(2000);							
			_driver.findElement(By.name(Locators.getProperty(Locators.PrintShop_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			isPrintShopPresent = true;
		}

		return isPrintShopPresent;
	}

	//Navigate to Add New Customer page
	public void NavigateToCustomerListPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/Customer/list");
		Thread.sleep(5000);
		//CommonFunctions.Wait(_driver, By.linkText(""));
		//assertEquals("Customers - Customer", _driver.getTitle());
		//_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInventoryItemListPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Customer List Page appears****");
	} 	

	//Add a New DSF shared customer page in Pace
	public String AddNewCustomer(String statusID) throws Exception, IOException
	{
		String DSF_Cust = DSF_Cust();
		String customerID = "";
		boolean isSuccess = false;
		boolean isDSFShared = false;

		NavigateToCustomerListPage();

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on Add New Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Customer_Name))).sendKeys(DSF_Cust);
		System.out.println("Entering the customer's name as: " +DSF_Cust);

		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DSF_Customer_Status)), statusID);
		System.out.println("Selecting the Customer Status as: " +statusID);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Contact_First_Name))).sendKeys("First Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Contact_Last_Name))).sendKeys("Last Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Address1))).sendKeys("Address 1");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Phone))).sendKeys("9988776655");



		System.out.println("Entered the details of the customer");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_PaceConnect_Tab))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Customer_Checkbox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Making the customer as a DSF Customer");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		Thread.sleep(10000);

		Object_Added();


		customerID = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Customer_ID))).getText();
		System.out.println("DSF Customer ID is: " +customerID);
		for(int i=1; i<=3; i++)
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_PaceConnect_Tab))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(1000);
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Customer has been shared with DSF");
			}
			else
			{
				System.out.println("This Customer is not shared with DSF");
				Thread.sleep(4000);
				_driver.navigate().refresh();
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		if (!customerID.equals("") && isDSFShared == true)
		{
			System.out.println("DSF Customer has been created and has been shared with DSF");
			isSuccess = true;
			return DSF_Cust;
		}
		else
		{
			return null;
		}


	}


	//Add a New DSF shared customer page in Pace
	public String AddNewCustomerWithContacts(String statusID,String sDefaultContact,String sShipToContact) throws Exception, IOException
	{
		String DSF_Cust = DSF_Cust();
		String customerID = "";
		boolean isSuccess = false;
		boolean isDSFShared = false;

		NavigateToCustomerListPage();

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on Add New Record button");
		CommonFunctions.waitForPageLoaded(_driver);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Customer_Name))).sendKeys(DSF_Cust);
		System.out.println("Entering the customer's name as: " +DSF_Cust);

		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DSF_Customer_Status)), statusID);
		System.out.println("Selecting the Customer Status as: " +statusID);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Contact_First_Name))).sendKeys("First Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Contact_Last_Name))).sendKeys("Last Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Address1))).sendKeys("Address 1");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Phone))).sendKeys("9988776655");




		System.out.println("Entered the details of the customer");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_PaceConnect_Tab))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Customer_Checkbox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Making the customer as a DSF Customer");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		Thread.sleep(10000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("defaultContact"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("shipToContact"), 1);
		Thread.sleep(1000);
		Object_Added();


		customerID = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Customer_ID))).getText();
		System.out.println("DSF Customer ID is: " +customerID);
		for(int i=1; i<=3; i++)
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_PaceConnect_Tab))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(1000);
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Customer has been shared with DSF");
			}
			else
			{
				System.out.println("This Customer is not shared with DSF");
				Thread.sleep(4000);
				_driver.navigate().refresh();
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		if (!customerID.equals("") && isDSFShared == true)
		{
			System.out.println("DSF Customer has been created and has been shared with DSF");
			isSuccess = true;
			return DSF_Cust;
			//return customerID;
		}
		else
		{
			return null;
		}


	}

	//Navigate To Customer Status List Page
	public void NavigateToCustomerStatusListPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/CustomerStatus/list");	
		Thread.sleep(5000);
		//CommonFunctions.Wait(_driver, By.linkText(""));
		//assertEquals("Customer Statuses", _driver.getTitle());
		//_driver.findElement(By.id(Locators.getProperty(Locators.Body_Click))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		NewFileNamePath = TakeScreenShot.getDestinationFile("NavigateToInventoryItemListPage");
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		System.out.println("****Customer Status List Page appears****");
	} 	

	//It will first verify whether the Customer Status with ID: O is present or not
	//If present, it will verify whether Active and Customer Active is enabled or not. If not, enable it
	//If Customer Status is not present, it will create one with ID = O
	public boolean AddDSFCustomerStatus(String id) throws Exception, IOException
	{
		NavigateToCustomerStatusListPage();
		boolean isCustomerStatusPresent = false;

		//Verify whether a customer status with ID = O is present or not
		int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 1; i<=rowCount; i++)
		{
			String customerStatus_ID = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText(); 
			customerStatus_ID = customerStatus_ID.trim();
			System.out.println("The Id in Row "+ i + " is: " +customerStatus_ID);

			if (customerStatus_ID.equals(id))
			{
				System.out.println("Expected Customer Status is present");

				//Now verify whether Active and Customer Active checkbox are enabled for this customer status

				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div[1]/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click the magnifying glass to go to the detail page");

				Thread.sleep(2000);

				String originalHandle = _driver.getWindowHandle();
				//System.out.println("Original Handle is: " +originalHandle);

				Set<String> availableWindows = _driver.getWindowHandles();
				//System.out.println("Available Windows are: " +availableWindows);

				if (!availableWindows.isEmpty()) 
				{	
					for (String windowId : availableWindows) 
					{ 
						if(_driver.switchTo().window(windowId).getTitle().equals("Customer Status "+id)) 
						{ 
							String Id = _driver.findElement(By.xpath("//div[@id='contents']/div/div[1]/div[1]/div/div")).getText();
							Id = Id.trim();
							System.out.println("The Id is: " +Id);

							if (Id.equals(id))
							{
								//check if Active and Customer Active checkbox is enabled or not
								selectCheckBox(true, By.name(Locators.getProperty(Locators.Active_Check)));
								selectCheckBox(true, By.name(Locators.getProperty(Locators.Customer_Active)));
								selectCheckBox(false, By.name(Locators.getProperty(Locators.Credit_Hold)));
								System.out.println("Checking Active and Customer Active checkbox and Unchecking Credit Hold");
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
								System.out.println ("****Clicking the Update button****");
								Thread.sleep(5000);
							}

							//_driver.close();	
							_driver.switchTo().window(originalHandle).getTitle().equals("Customer Statuses");
						} 
						else 
						{
							_driver.switchTo().window(originalHandle).getTitle().equals("Customer Statuses");

						}
					}
				}

				isCustomerStatusPresent = true;
				//Since the channel is present, exit the loop.
				break;

			}
		}

		if (isCustomerStatusPresent == false)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Click on Add New Record button");

			String originalHandle = _driver.getWindowHandle();
			System.out.println("Original Handle is: " +originalHandle);

			Set<String> availableWindows = _driver.getWindowHandles();
			System.out.println("Available Windows are: " +availableWindows);
			if (!availableWindows.isEmpty()) 
			{	
				for (String windowId : availableWindows) 
				{ 
					if(_driver.switchTo().window(windowId).getTitle().equals("Adding Customer Status")) 
					{ 
						String Id = _driver.findElement(By.name(Locators.getProperty(Locators.ID))).getText();

						if (Id.equals(""))
						{
							_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys("O");
							_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("Open");
							System.out.println("Entering the ID as O and description as Open");
							selectCheckBox(true, By.name(Locators.getProperty(Locators.Active_Check)));
							selectCheckBox(true, By.name(Locators.getProperty(Locators.Customer_Active)));
							selectCheckBox(false, By.name(Locators.getProperty(Locators.Credit_Hold)));
							System.out.println("Checking Active and Customer Active checkbox and Unchecking Credit Hold");
							_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
							Thread.sleep(10000);
							isCustomerStatusPresent = true;
						}
						else
						{
							System.out.println ("Could not enter the loop");

						}	
						_driver.close();	
						_driver.switchTo().window(originalHandle).getTitle().equals("Customer Statuses");
					}
					else 
					{
						_driver.switchTo().window(originalHandle).getTitle().equals("Customer Statuses");

					}
				}
			}
		}	
		return isCustomerStatusPresent;
	}


	//Make Customer Active = False
	public boolean CustomerStatus_Inactive(String CustStatus) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);

		boolean isCheck = false;

		System.out.println("Navigate To Customer Status List Page ");
		NavigateToCustomerStatusListPage();


		int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 1; i<=rowCount; i++)
		{
			String customerStatus_ID = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText(); 
			customerStatus_ID = customerStatus_ID.trim();
			System.out.println("The Id in Row "+ i + " is: " +customerStatus_ID);

			if (customerStatus_ID.equals(CustStatus))
			{
				String  originalHandle = _driver.getWindowHandle();
				System.out.println(originalHandle);
				String  sWindowTitle= _driver.getTitle();

				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(5000);

				Set<String> availableWindows = _driver.getWindowHandles();
				if (!availableWindows.isEmpty()) 
				{
					for (String windowId : availableWindows) 
					{
						if(_driver.switchTo().window(windowId).getTitle().equals("Customer Status "+CustStatus)) 
						{
							System.out.println("In active customer checkbox");
							PO.sSelectCheckBox(true, By.name("customerActiveBooleanCheck"));
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);;
							Thread.sleep(5000);
							isCheck = true;
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
						} 
						else 
						{
							//System.out.println("Not able to find window");
							_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

						}

					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}



			} 
			else
			{

				System.out.println("Customer Status ID in row "+ i + " is not 'C'. It is " +customerStatus_ID);
			}

			if(isCheck==true)
			{
				break;
			}
		}



		System.out.println("The isCheck is " +isCheck);
		return isCheck;
	}	


	public void SetCustomerStatusToInactive(String CustStatus) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();

		_driver.findElement(By.xpath("//div/h4[contains(label,'Customer Status')]/following-sibling::div/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Customer Status "+CustStatus)) 
				{
					System.out.println("In active customer checkbox");
					PO.sSelectCheckBox(false, By.name("customerActiveBooleanCheck"));
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(1000);
					PO.AcceptModalDialog();
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);;
					Thread.sleep(5000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
	}

	//Make inactive CustomerActive = true
	public void CustomerStatus_Active(String customerStatus) throws Exception, IOException
	{
		NavigateToCustomerStatusListPage();

		int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 1; i<=rowCount; i++)
		{
			String customerStatus_ID = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/div")).getText(); 
			customerStatus_ID = customerStatus_ID.trim();
			System.out.println("The Id in Row "+ i + " is: " +customerStatus_ID);

			if (customerStatus_ID.equals(customerStatus))
			{
				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[5]/input[2]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Making Customer Active as true");
				Update();
			}
		}

	}

	//Chargeback Account for customer
	public String Chargeback_Account(String sCustomer) throws Exception, IOException
	{
		boolean isCreated = true;
		String chargebackAccount = null;
		String account = "123"+UniqueAccount();

		NavigateToCustomerListPage();

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Customer_Search)), "Customer");
		System.out.println("Selected the search option as Customer");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(sCustomer);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Entered the customer id as " +sCustomer+" and click the Find button");
		Thread.sleep(5000);

		//If the Customer doesn't open directly, click the magnifying glass to open it
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle.equals("Customers - Customer"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//table[@id='Customer']/tbody/tr/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
		}

		String sCusID = _driver.findElement(By.xpath("//div/h4[contains(label,'Customer ID')]/following-sibling::div/div")).getText();
		System.out.println("sCusID is "+sCusID);
		/*
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Context_Menu))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Context menu");
		Thread.sleep(4000);

		_driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[2]/td[1]/a[text()='- Chargeback Accounts']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Chargeback Accounts");
		 */

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		//_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChargeBackAccount/customerList/"+sCusID);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChargeBackAccount/addCustomer/"+sCusID);
		Thread.sleep(3000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Chargeback_Account_Number))).sendKeys(account);
		_driver.findElement(By.name(Locators.getProperty(Locators.Chargeback_Account_Expiration_Date))).sendKeys("10/25/2015");
		System.out.println("Entered the chargeback account details");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);

		/*_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");
		String originalHandle = _driver.getWindowHandle();
		Set<String> availableWindows = _driver.getWindowHandles();

		 if (!availableWindows.isEmpty()) 
		 {	System.err.println("4");
			for (String windowId : availableWindows) 
			{ System.err.println("5");
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Charge Back Account")) 
				{ 
					System.err.println("check 1");
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Chargeback_Account_Number))).sendKeys(account);
					_driver.findElement(By.name(Locators.getProperty(Locators.Chargeback_Account_Expiration_Date))).sendKeys("10/25/2015");
					System.out.println("Entered the chargeback account details");

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals("Chargeback Accounts");
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Chargeback Accounts");

				}
			}
		}
		 */

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChargeBackAccount/customerList/"+sCusID);

		Thread.sleep(2000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);
		if(rowCount > 0)
		{
			chargebackAccount = account; 
		}
		return chargebackAccount;

	}

	//Create Shipping Method to an existing shipping provider
	public String Shipping_Method() throws Exception
	{	
		PurchasePage PO = new PurchasePage(_driver);
		boolean isShared = false;
		String shipVia = "";
		String shippingProvider = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipProvider/list");
		Thread.sleep(5000);

		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the 1st Ship Provider");

		System.out.println("Set as active");
		PO.sSelectCheckBox(true, By.name("activeBooleanCheck"));

		shippingProvider = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/input")).getAttribute("value");
		System.out.println("Shipping Provider is: "+shippingProvider);

		_driver.findElement(By.xpath("//div[@id='tabBar']/div[2]/span/a[text()='Shipping Methods']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the Shipping Methods tab");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");

		String shippingMethod = ShippingMethod();
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shippingMethod);
		System.out.println("Entered the Shipping Method as: " +shippingMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Enabling the DSF Delivery Method");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		Object_Added();

		boolean isDSFShared = false;

		for (int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared);

			if (isDSFShared == false)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			else
			{
				i = 4;
			}
		}
		if (isDSFShared == true)
		{
			System.out.println("This Shipping Method is DSF Shared");
			shipVia = shippingProvider+" "+shippingMethod;
		}
		return shipVia;
	}

	//Create Shipping Method to an existing shipping provider 
	//Make the Active flag as false and hence remove it from DSF
	public String Shipping_Method_Inactive() throws Exception, IOException
	{	
		boolean isShared = false;
		String shipVia = "";
		String shippingProvider = null;
		String shippingMethod = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipProvider/list");
		Thread.sleep(5000);

		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the 1st Ship Provider");

		shippingProvider = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/input")).getAttribute("value");
		System.out.println("Shipping Provider is: "+shippingProvider);

		_driver.findElement(By.xpath("//div[@id='tabBar']/div[2]/span/a[text()='Shipping Methods']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the Shipping Methods tab");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");

		shippingMethod = ShippingMethod();
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shippingMethod);
		System.out.println("Entered the Shipping Method as: " +shippingMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Enabling the DSF Delivery Method");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		Object_Added();

		boolean isDSFShared = false;

		for (int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared);

			if (isDSFShared == false)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time because isDSFShared = " +isDSFShared);
			}
			else
			{
				i = 4;
			}
		}

		//Now make this shipping method inactive by making the Active flag as False

		boolean isActive = false;
		//Assuming that by default, the flag is set to True. 
		_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Update();
		System.out.println("Making the Active flag as false");
		isActive = _driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).isSelected();
		System.out.println("The Active flag is set as: " +isActive);

		isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
		System.out.println("isDSFShared is "+isDSFShared);

		if (isActive == false)
		{
			System.out.println("This Shipping Method is inactive now");
			shipVia = shippingProvider+" "+shippingMethod;
		}

		return shipVia;
	}	


	//Create Shipping Method to an existing shipping provider 
	//Make the Active flag as false and hence remove it from DSF
	//Again make Active flag as true, DSF Delivery Method as true
	public String Shipping_Method_Active() throws Exception, IOException
	{	
		boolean isShared = false;
		boolean isActive = false;
		boolean isActive_1 = false;
		boolean isDeliveryMethod = false;
		boolean isSelected = false;
		boolean isDSFShared = false;
		boolean isDSFShared_1 = false;
		String shipVia = "";
		String shippingProvider = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipProvider/list");
		Thread.sleep(5000);

		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the 1st Ship Provider");

		shippingProvider = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/input")).getAttribute("value");
		System.out.println("Shipping Provider is: "+shippingProvider);

		_driver.findElement(By.xpath("//div[@id='tabBar']/div[2]/span/a[text()='Shipping Methods']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the Shipping Methods tab");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");

		String shippingMethod = ShippingMethod();
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shippingMethod);
		System.out.println("Entered the Shipping Method as: " +shippingMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Enabling the DSF Delivery Method");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		Object_Added();

		//Check if DSF Shared is true
		for (int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared);

			if (isDSFShared == false)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			else
			{
				i = 4;
			}
		}
		if (isDSFShared == true)
		{
			System.out.println("This Shipping Method is DSF Shared");
			//shipVia = shippingMethod;
		}

		//Now make this shipping method inactive by making the Active flag as False

		//Assuming that by default, the flag is set to True. So now make it False
		_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Update();
		isActive = _driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).isSelected();
		System.out.println("The Active flag is set as: " +isActive);

		//Name make the Active flag as true
		_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Update();
		isActive_1 = _driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).isSelected();
		System.out.println("The Active flag is now set as: " +isActive_1);

		//Set DSF Delivery Method as true
		isDeliveryMethod = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).isSelected();

		if(isDeliveryMethod == false)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Update();
			isSelected = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).isSelected();
			System.out.println("DSF Delivery method is: " +isSelected);
		}

		//Check if DSF Shared is true
		for (int i=1; i<=3; i++)
		{
			isDSFShared_1 = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared_1);

			if (isDSFShared_1 == false)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared_1);
			}
			else
			{
				i = 4;
			}
		}

		if (isDSFShared_1 == true && isActive_1 == true)
		{
			System.out.println("This Shipping Method is DSF Shared and is Active");
			shipVia = shippingProvider+" "+shippingMethod;
		}

		return shipVia;
	}	

	//Create Shipping Method which is DSF Shared. Then make DSF DElivery Method as false
	public String Shipping_Method_Non_DSF() throws Exception, IOException
	{	
		boolean isShared = false;
		boolean isDeliveryMethod = false;
		boolean isDSFShared = false;
		boolean isDSFShared_1 = false;
		String shipVia = "";
		String shippingProvider = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipProvider/list");
		Thread.sleep(5000);

		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the 1st Ship Provider");

		shippingProvider = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[1]/div[1]/div[1]/input")).getAttribute("value");
		System.out.println("Shipping Provider is: "+shippingProvider);

		_driver.findElement(By.xpath("//div[@id='tabBar']/div[2]/span/a[text()='Shipping Methods']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on the Shipping Methods tab");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");

		String shippingMethod = ShippingMethod();
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(shippingMethod);
		System.out.println("Entered the Shipping Method as: " +shippingMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Enabling the DSF Delivery Method");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		Object_Added();

		for (int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared);

			if (isDSFShared == false)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			else
			{
				i = 4;
			}
		}
		if (isDSFShared == true)
		{
			System.out.println("This Shipping Method is DSF Shared");
			//shipVia = shippingMethod;
		}

		//Make the DSF Delivery Method as false

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Update();
		isDeliveryMethod = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Delivery_Method))).isSelected();
		System.out.println("DSF Delivery Method is set to " + isDeliveryMethod);

		//Check that DSF Shared is false
		for (int i=1; i<=3; i++)
		{
			isDSFShared_1 = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			System.out.println("isDSFShared is "+isDSFShared_1);

			if (isDSFShared_1 == true)
			{
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared_1);
			}
			else
			{
				i = 4;
			}
		}
		if (isDSFShared_1 == false)
		{
			System.out.println("This Shipping Method is DSF Shared");
			shipVia = shippingProvider+" "+shippingMethod;
		}

		return shipVia;
	}


	public boolean Verify_Cost_Center_In_Company(String sCustomer,String costCenter) throws Exception
	{
		boolean sCustomerFlag = false,isCostCenterVerified=false;
		int rowCount=0;
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		// _driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Companies))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Companies");

		//Verify P_3018 and P_3021
		System.out.println("**Start Verification for P_3018 and P_3021**");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search))).sendKeys(sCustomer);
		System.out.println("Entering the Company to be searched as: " +sCustomer);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the find button");
		Thread.sleep(3000);

		//Filter out the company from table
		rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllCompanies']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);
		if(rowCount==1)
		{
			System.out.println("Expected Customer is not present ");
			sCustomerFlag =false;
		}
		else
		{
			if(_driver.findElements(By.xpath("//a[text()='"+sCustomer+"']")).size()>0)
			{
				sCustomerFlag =true;
				_driver.findElement(By.linkText(sCustomer)).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking on the Company: " +sCustomer);
				Thread.sleep(2000);

				System.out.println("Click on Cost Center view manage link");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Cost_Center_View))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);


				if(_driver.findElements(By.xpath("//a[text()='"+costCenter+"']")).size()>0)
				{
					System.out.println("Cost Center is Present");
					isCostCenterVerified = true;
				}
				else
				{
					System.err.println("Expected Cost Center is not Present");
					isCostCenterVerified = false; 
				}
			}
			else
			{
				System.out.println("Expected Customer is not present ");
				sCustomerFlag =false;
			}

		}


		return isCostCenterVerified;


	}
	//Verify the data from Pace in DSF for P_3018, 3019 and 3021
	public boolean Verify_Customer_DSF(String sCustomer, String costCenter) throws Exception, IOException
	{
		int rowCount=0;
		boolean sCustomerFlag = false;


		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//  _driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Companies))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Companies");

		//Verify P_3018 and P_3021
		System.out.println("**Start Verification for P_3018 and P_3021**");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search))).sendKeys(sCustomer);
		System.out.println("Entering the Company to be searched as: " +sCustomer);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the find button");
		Thread.sleep(5000);

		//Filter out the company from table
		rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllCompanies']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);
		if(rowCount==1)
		{
			System.out.println("Expected Customer is not present ");
			sCustomerFlag =false;
		}
		else
		{
			if(_driver.findElements(By.xpath("//a[text()='"+sCustomer+"']")).size()>0)
			{
				System.out.println("Able to Find Customer");
				sCustomerFlag =true;

			}
			else
			{
				System.out.println("Not Able to Find Customer");
				sCustomerFlag =false;
			}

		}

		return  sCustomerFlag;

	}

	//Verify the data from Pace in DSF for P_3022, 3023, 3024 and 3025
	public boolean Verify_Delivery_Method(String shippingMethod) throws Exception, IOException
	{
		int rowCount;
		int rowCount_1;
		int rowCount_2;
		int rowCount_3;
		int costCenterRowCount;
		boolean isStatus = false;
		boolean isVerified = false;
		boolean isVerified_1 = false;
		String deliveryMethod = null;
		String deliveryMethod_1 = null;
		String deliveryMethod_2 = null;
		String deliveryMethod_3 = null;
		boolean isDeliveryMethod = false;
		boolean isDeliveryMethod_1 = false;
		boolean isDeliveryMethod_2 = false;
		boolean isDeliveryMethod_3 = false;
		boolean isCostCenterVerified = false;

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Delivery_Methods))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Delivery Methods");

		//Verify P_3022
		System.out.println("**Start Verification for P_3022**");

		//Filter out the delivery method from the table
		rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);
		for (int i = 1; i<=rowCount; i++)
		{
			deliveryMethod = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr["+i+"]/td[1]")).getText(); 
			deliveryMethod = deliveryMethod.trim();
			System.out.println("Delivery Method in Row "+ i + " is: " +deliveryMethod);

			if (deliveryMethod.equals(shippingMethod))
			{
				System.out.println("Expected Delivery Method is present");
				isDeliveryMethod = true;
				break;
			} 
		}

		return isDeliveryMethod;


	} 




	//Create DSF Inventory Item Types
	public String Inventory_Item_Type() throws Exception, IOException
	{
		boolean isAdded = false;
		String itemID = null;
		String itemType = null;
		String Inventory_Item_ID = UniqueNum1();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItemType/list");	
		Thread.sleep(5000);
		System.out.println("**Inventory Item Type list page appears**");


		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking on Add New Records button");

		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(Inventory_Item_ID);
		System.out.println("Inventory Item Type ID = " +Inventory_Item_ID);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Item_Description))).sendKeys("DSF_Item");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Paper))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Catalog_Item))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Entered DSF Item description and enabled Paper and DSF Catalog Item checkboxes");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");
		isAdded = Object_Added();

		if(isAdded == true)
		{
			itemType = Inventory_Item_ID;
		}
		return itemType;

	}

	//Create a new Inventory Item using Inventory Item Type created above
	public String Add_Inventory_Item(String inventoryItemType) throws Exception, IOException
	{
		boolean isAdded = false;
		boolean isDSFShared = false;
		String inventoryItem = null;
		String sInv_Id  = "test_qty"+ UniqueNum();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItem/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Inventory Item List Page**");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		System.out.println("**General Info tab of Inventory Item Add Page Appears**");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_ID))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory Item ID as " + sInv_Id);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory description as " +sInv_Id);

		System.out.println("Inventory Item Type to be selected is: " +inventoryItemType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), inventoryItemType + " - DSF_Item");
		System.out.println("Selecting the Inventory Item Type as: " +inventoryItemType);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected() )
		{
			System.out.println("Active Checkbox is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000); 
			System.out.println("Enabling the Active Checkbox");
		}

		if(_driver.findElements(By.xpath("//span[text()='P']")).size()>0)
		{
			_driver.findElement(By.xpath("//span[text()='P']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			if(_driver.findElements(By.name("grainDirection")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
			}
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		isAdded = Object_Added();

		for(int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Inventory Item has been shared with DSF");
			}
			else
			{
				System.out.println("This Inventory Item is not shared with DSF, hence need to refresh the page");
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		if(isAdded == true && isDSFShared == true)
		{
			System.out.println("Inventory Item has been added and shared with DSF");
			inventoryItem = sInv_Id;
		}
		return inventoryItem;
	}

	//Create a new Inventory Item using Inventory Item Type created above and make the Active flag as false
	public String Add_Inventory_Item_Inactive(String inventoryItemType) throws Exception, IOException
	{
		boolean isAdded = false;
		boolean isActive = true;
		boolean isDSFShared = false;
		String inventoryItem = null;
		String sInv_Id  = "test_qty"+ UniqueNum();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItem/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Inventory Item List Page**");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**General Info tab of Inventory Item Add Page Appears**");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_ID))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory Item ID as " + sInv_Id);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory description as " +sInv_Id);

		System.out.println("Inventory Item Type to be selected is: " +inventoryItemType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), inventoryItemType + " - DSF_Item");
		System.out.println("Selecting the Inventory Item Type as: " +inventoryItemType);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected() )
		{
			System.out.println("Active Checkbox is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000); 
			System.out.println("Enabling the Active Checkbox");
		}

		if(_driver.findElements(By.xpath("//span[text()='P']")).size()>0)
		{
			_driver.findElement(By.xpath("//span[text()='P']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			if(_driver.findElements(By.name("grainDirection")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		isAdded = Object_Added();

		for(int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Inventory Item has been shared with DSF");
			}
			else
			{
				System.out.println("This Inventory Item is not shared with DSF, hence need to refresh the page");
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		//Now since it's DSF shared, make the active flag as false.
		if(_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected() )
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Update();
			Thread.sleep(2000);
			System.out.println("Disabling the Active Checkbox");
			isActive = _driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected();
			System.out.println("Is Active is: " +isActive);
		}
		else
		{
			System.out.println("Active Checkbox is disabled");
			isActive = _driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected();
			System.out.println("Is Active is: " +isActive);
		}

		if(isAdded == true && isDSFShared == true && isActive == false)
		{
			System.out.println("Inventory Item has been added and shared with DSF and active flag has been made false");
			inventoryItem = sInv_Id;
		}
		return inventoryItem;
	}

	//Create a new Inventory Item using Inventory Item Type created above and then change it to a non-dsf item
	//Changing Inv Item Tpe: 1 - Paper, Sheets to Non Dsf Type and setting this item type on this inv item
	public String Add_Inventory_Item_non_DSF(String inventoryItemType) throws Exception, IOException
	{
		boolean isAdded = false;
		boolean isDSFShared = false;
		boolean isDSFShared_1 = false;
		String inventoryItem = null;
		String inventItem = null;
		String sInv_Id  = "test_qty"+ UniqueNum();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItem/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Inventory Item List Page**");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**General Info tab of Inventory Item Add Page Appears**");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_ID))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory Item ID as " + sInv_Id);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory description as " + sInv_Id);

		System.out.println("Inventory Item Type to be selected is: " +inventoryItemType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), inventoryItemType + " - DSF_Item");
		System.out.println("Selecting the Inventory Item Type as: " +inventoryItemType);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected() )
		{
			System.out.println("Active Checkbox is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000); 
			System.out.println("Enabling the Active Checkbox");
		}
		if(_driver.findElements(By.xpath("//span[text()='P']")).size()>0)
		{
			_driver.findElement(By.xpath("//span[text()='P']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			if(_driver.findElements(By.name("grainDirection")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
			}
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		isAdded = Object_Added();

		for(int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Inventory Item has been shared with DSF");
			}
			else
			{
				System.out.println("This Inventory Item is not shared with DSF, hence need to refresh the page");
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		if(isAdded == true && isDSFShared == true)
		{
			System.out.println("Inventory Item has been added and shared with DSF");
			inventoryItem = sInv_Id;
		}
		//return inventoryItem;

		//Now change the Item type to non-dsf
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItemType/detail/1");	
		Thread.sleep(5000);
		System.out.println("**Inventory Item Type detail page appears**");

		//String itemID = _driver.findElement(By.name(Locators.getProperty(Locators.ID))).getText();
		//System.out.println("Inventory Item Type ID = " +itemID);

		String itemDescription = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Item_Description))).getAttribute("value");
		System.out.println("Inventory Item Type description = " +itemDescription);

		boolean isDSFSelected = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Catalog_Item))).isSelected();
		System.out.println("DSF Catalog item is: " +isDSFSelected);
		if(isDSFSelected == true)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Catalog_Item))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Update();
			System.out.println("Unchecking the checkbox for DSF Catalog item");
		}
		else
		{
			System.out.println("DSF Catalog item is disabled");
		}	

		//Go back to the inventory item
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItem/detail/"+sInv_Id);
		Thread.sleep(5000);
		System.out.println("**Navigate to Inventory Item Detail Page**");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)),  "1 - "+itemDescription);
		boolean isUpdate = Update();

		for(int i=1; i<=3; i++)
		{
			isDSFShared_1 = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared_1 == false)
			{
				System.out.println("This Inventory Item is not shared with DSF anymore");
			}
			else
			{
				System.out.println("This Inventory Item is still shared with DSF, hence need to refresh the page");
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared_1);
			}
			if(isDSFShared_1 == false)
			{
				break;
			}
		}

		if(isDSFShared_1 == false)
		{
			inventItem = sInv_Id;
		}
		return inventItem;
	}

	//Create a new Inventory Item using Inventory Item Type created above and then delete the item
	public String Delete_Inventory_Item(String inventoryItemType) throws Exception, IOException
	{
		boolean isAdded = false;
		boolean isDSFShared = false;
		String inventoryItem = null;
		String sInv_Id  = "test_qty"+ UniqueNum();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryItem/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Inventory Item List Page**");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Item_Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**General Info tab of Inventory Item Add Page Appears**");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item_ID))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory Item ID as " + sInv_Id);

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Description))).sendKeys(sInv_Id);
		System.out.println("Entering the Inventory description as " + sInv_Id);

		System.out.println("Inventory Item Type to be selected is: " +inventoryItemType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), inventoryItemType + " - DSF_Item");
		System.out.println("Selecting the Inventory Item Type as: " +inventoryItemType);

		if(_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).isSelected() )
		{
			System.out.println("Active Checkbox is enabled");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Active_Check))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000); 
			System.out.println("Enabling the Active Checkbox");
		}
		if(_driver.findElements(By.xpath("//span[text()='P']")).size()>0)
		{
			_driver.findElement(By.xpath("//span[text()='P']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			if(_driver.findElements(By.name("grainDirection")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
			}
		}
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");

		isAdded = Object_Added();

		for(int i=1; i<=3; i++)
		{
			isDSFShared = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shared_Checkbox))).isSelected();
			if(isDSFShared == true)
			{
				System.out.println("This Inventory Item has been shared with DSF");
			}
			else
			{
				System.out.println("This Inventory Item is not shared with DSF, hence need to refresh the page");
				_driver.navigate().refresh();
				System.out.println("Refreshing the page " + i + " time and isDSFShared = " +isDSFShared);
			}
			if(isDSFShared == true)
			{
				break;
			}
		}

		if(isAdded == true && isDSFShared == true)
		{
			System.out.println("Inventory Item has been added and shared with DSF");
			inventoryItem = sInv_Id;
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Deleting the Inventory Item");
		_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);


		return inventoryItem;
	}


	//Inventory_Item in pace is Product in dsf
	public boolean[] Verify_Inventory_Item_In_DSF(String inventoryItem,String sActive) throws Exception
	{
		boolean sInvFlag=false,sActiveFlag=false;


		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		//Verify for P_3027
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem);
		System.out.println("Entering the product to be searched as: "+inventoryItem);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		if(_driver.findElements(By.xpath("//a[text()='"+inventoryItem+"']")).size()>0)
		{
			System.out.println("Able to find Inventory");
			sInvFlag=true;
			String isActive = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr[2]/td[10]/span/span")).getText();  
			isActive=isActive.trim();
			if(isActive.equals(sActive))
			{
				System.out.println("Active Status is "+isActive);
				sActiveFlag=true;
			}
			else
			{
				System.out.println("Active Status is "+isActive+ " but expected status "+sActive);
				sActiveFlag=false;
			}
		}
		else
		{
			System.out.println("Not able to find Inventory");
			sInvFlag=false;
		}
		boolean[] sFlag= {sInvFlag,sActiveFlag};
		return sFlag;



	}
	//Verify the data from Pace in DSF for P_3027, 3028, 3029 and 3030
	public void Verify_IN_DSF_2(String inventoryItem, String inventoryItem_1, String inventoryItem_2, String inventoryItem_3) throws Exception, IOException
	{
		boolean isInactive = false;
		boolean isProductPresent = false;
		boolean isProductPresent_1 = false;
		boolean isProductPresent_2 = false;
		boolean isProductPresent_3 = false;

		System.out.println("The inventory items used for searching are: " +inventoryItem+" , "+inventoryItem_1+" , "+inventoryItem_2+" , "+inventoryItem_3);

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		//Verify for P_3027
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem);
		System.out.println("Entering the product to be searched as: "+inventoryItem);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		int rowCount = _driver.findElements(By.xpath("//div[@class='ctr-page']//div[@id='ctl00_ctl00_C_M_UpdatePanelMain']/div[6]/table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		if (rowCount < 2)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3027", "DSF_Status", " Not Verified_Product is not present");
			Assert.fail("Not Verified_Product is not present");
		}
		for (int i = 2; i<=rowCount; i++)
		{
			String productID = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+i+"]/td[5]")).getText();
			System.out.println("Product ID before converting to lower case is " +productID);
			productID = productID.trim();
			productID = productID.toLowerCase();
			System.out.println("Product ID after converting to lower case in row "+ i + " is: " +productID);

			if (productID.equals(inventoryItem))
			{
				System.out.println("Expected product is present");
				isProductPresent = true;
				dbConnection.UpdateFunction("DSF", "DSF", "P_3027", "DSF_Status", "Verified");
			}
			else
			{
				System.out.println("Product is not present");
				dbConnection.UpdateFunction("DSF", "DSF", "P_3027", "DSF_Status", " Not Verified_Product is not present");
				Assert.fail("Not Verified_Product is not present");

			}
			if(isProductPresent == true)
			{
				break;
			}
		}

		//Verify for P_3028
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem_1);
		System.out.println("Entering the product to be searched as: "+inventoryItem_1);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		int rowCount_1 = _driver.findElements(By.xpath("//div[@class='ctr-page']//div[@id='ctl00_ctl00_C_M_UpdatePanelMain']/div[6]/table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount_1);
		if (rowCount_1 < 2)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3028", "DSF_Status", " Not Verified_Product is not present");
		}
		for (int j = 2; j<=rowCount_1; j++)
		{
			String productID_1 = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+j+"]/td[5]")).getText(); 
			System.out.println("Product ID before converting to lower case is " +productID_1);
			productID_1 = productID_1.trim();
			productID_1 = productID_1.toLowerCase();
			System.out.println("Product ID after converting to lower case in row "+ j + " is: " +productID_1);

			if (productID_1.equals(inventoryItem_1))
			{
				System.out.println("Expected product is present");
				isProductPresent_1 = true;
				//Now check whether the product is Active or Inactive
				String isActive = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+j+"]/td[10]/span/span")).getText();  
				if(isActive.equals("Yes"))
				{
					System.out.println("The product is active");
					dbConnection.UpdateFunction("DSF", "DSF", "P_3028", "DSF_Status", "Not Verified_The product is active");
					Assert.fail("Not Verified_The product is active");
				}
				else
				{
					System.out.println("The product is inactive");
					dbConnection.UpdateFunction("DSF", "DSF", "P_3028", "DSF_Status", "Verified");
					isInactive = true;
				}
			}
			else
			{
				System.out.println("Product is not present");
				dbConnection.UpdateFunction("DSF", "DSF", "P_3028", "DSF_Status", "Not Verified_Product is not present");
				Assert.fail("Not Verified_Product is not present");
				//isProductPresent = false;
			}
			if(isProductPresent_1 == true && isInactive == true)
			{
				break;
			}
		}

		//Verify for P_3029
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem_2);
		System.out.println("Entering the product to be searched as: "+inventoryItem_2);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		int rowCount_2 = _driver.findElements(By.xpath("//div[@class='ctr-page']//div[@id='ctl00_ctl00_C_M_UpdatePanelMain']/div[6]/table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount_2);
		Thread.sleep(3000);
		if(rowCount_2==1)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3029", "DSF_Status", "Verified"); 
		}
		for (int k = 1; k<=rowCount_2; k++)
		{
			String productID_2 = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+k+"]/td[5]")).getText();
			System.out.println("Product ID before converting to lower case is " +productID_2);
			productID_2 = productID_2.trim();
			productID_2 = productID_2.toLowerCase();
			System.out.println("Product ID after converting to lower case in row "+ k + " is: " +productID_2);

			if (productID_2.equals(inventoryItem_2))
			{
				System.out.println("Expected product is present");
				isProductPresent_2 = true;
				dbConnection.UpdateFunction("DSF", "DSF", "P_3029", "DSF_Status", "Verified Failed Product present");
				Assert.fail("Verified Failed Product present");
			}
			else
			{
				System.out.println("Product is not present");
				dbConnection.UpdateFunction("DSF", "DSF", "P_3029", "DSF_Status", "Verified");
				isProductPresent_2 = false;

			}
			if(isProductPresent_2 == true)
			{
				break;
			}
		}		 

		//Verify for P_3030
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem_3);
		System.out.println("Entering the product to be searched as: "+inventoryItem_3);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		int rowCount_3 = _driver.findElements(By.xpath("//div[@class='ctr-page']//div[@id='ctl00_ctl00_C_M_UpdatePanelMain']/div[6]/table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount_3);
		if(rowCount_2==1)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3030", "DSF_Status", "Verified"); 
		}
		Thread.sleep(3000);
		for (int l = 1; l<=rowCount_3; l++)
		{
			String productID_3 = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+l+"]/td[5]")).getText();
			System.out.println("Product ID before converting to lower case is " +productID_3);
			productID_3 = productID_3.trim();
			productID_3 = productID_3.toLowerCase();
			System.out.println("Product ID after converting to lower case in row "+ l + " is: " +productID_3);

			if (productID_3.equals(inventoryItem_3))
			{
				System.out.println("Expected product is present");
				isProductPresent_3 = true;
				dbConnection.UpdateFunction("DSF", "DSF", "P_3030", "DSF_Status", "Verified Failed Deleted Product Still Present");
				Assert.fail("Verified Failed Product present");
			}
			else
			{
				System.out.println("Product is not present");
				dbConnection.UpdateFunction("DSF", "DSF", "P_3030", "DSF_Status", "Verified");

			}
			if(isProductPresent_3 == true)
			{
				break;
			}
		}
	}

	//Create Receive transaction for an Inventory Item
	//Assuming that default inv loc and bin is Floor and A1
	//Assuming auto post is enabled everywhere. If required, can be coaded to ensure that auto-post is enabled everywhere.
	public boolean CreateReceiveTrxn (String inventoryItem) throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);

		String sVersion =  FetchVersion();
		if(Integer.valueOf(sVersion) >=  27)
		{
			_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryLine/add");
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:public/object/InventoryTrn/add");
		}
		Thread.sleep(5000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Transaction_Type)), "1 - Receive");
		Thread.sleep(1000);

		if(Integer.valueOf(sVersion) >=  27)
		{

			CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBatch"), 1);
			Thread.sleep(1000);
		}

		System.out.println("Creating a 'Receive' transaction");

		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(inventoryItem);
		System.out.println("Inventory ID entered is " +inventoryItem);
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Purchase_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Quantity))).sendKeys("500");
		System.out.println("Entering the quantity as 500");
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).clear();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Total_Cost))).sendKeys("5");
		System.out.println("Entering the unit cost as $5");

		CommonFunctions.selectDropdownByText(_driver, By.name("inventoryLocation"), "FLOOR");
		Thread.sleep(1000);
		//CommonFunctions.selectDropdownByIndex(_driver, By.name("locationBinKey"), 1);
		//Thread.sleep(1000);

		int approvedCheckbox1 = _driver.findElements(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).size();
		if(approvedCheckbox1 > 0)
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
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Transaction_Approved_checkbox))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Checking Approved checkbox");
				}

			}
		}
		else
		{
			System.out.println("Approved checkbox is not displayed");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clickinig the Add button");
		Thread.sleep(2000);
		boolean isTrue = Object_Added();
		//assertEquals("Edit Inventory Transactions", _driver.getTitle());
		//System.out.println("Inventory transaction is added and Edit Inventory Transactions page appears");

		return isTrue;
	}

	//Verify the data from Pace in DSF for P_3032
	public boolean Verify_IN_DSF_3(String inventoryItem) throws Exception, IOException
	{
		boolean isInactive = false;
		boolean isProductPresent = false;
		boolean isQuantityVerified = false;
		String inventoryQuantity = null;
		boolean isVerified = false;

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search))).sendKeys(inventoryItem);
		System.out.println("Entering the product to be searched as: "+inventoryItem);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Products_Search_Button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Search button");
		Thread.sleep(4000);
		int rowCount = _driver.findElements(By.xpath("//div[@class='ctr-page']//div[@id='ctl00_ctl00_C_M_UpdatePanelMain']/div[6]/table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		if(rowCount < 2)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3032", "DSF_Status", "Not Verified_Product is not present");
		}
		for (int i = 2; i<=rowCount; i++)
		{
			String productID = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+i+"]/td[5]")).getText();
			System.out.println("Product ID before converting to lower case is " +productID);
			productID = productID.trim();
			productID = productID.toLowerCase();
			System.out.println("Product ID after converting to lower case in row "+ i + " is: " +productID);

			if (productID.equals(inventoryItem))
			{
				System.out.println("Expected product is present");
				isProductPresent = true;
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_GridProducts']/tbody/tr["+i+"]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the product to verify the inventory quantity");
				Thread.sleep(3000);
				_driver.findElement(By.xpath("//a[@id='TabSettings']/span/span/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the settings link");
				inventoryQuantity = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Inventory_Qty))).getAttribute("value");
				System.out.println("Inventory Quantity in DSF is: " +inventoryQuantity);
				if(inventoryQuantity.equals("10000"))
				{
					System.out.println("Inventory Quantity has been verified");
					dbConnection.UpdateFunction("DSF", "DSF", "P_3032", "DSF_Status", "Verified");
					isQuantityVerified = true;
				}
				else
				{
					System.out.println("Inventory Quantity could not be verified");
					dbConnection.UpdateFunction("DSF", "DSF", "P_3032", "DSF_Status", "Verified Failed");
					// Assert.fail("Verified Failed Invalid Qty");
				}

			}
			else
			{
				System.out.println("Product is not present");
				dbConnection.UpdateFunction("DSF", "DSF", "P_3032", "DSF_Status", "Verified Failed Invalid Qty");
				isProductPresent = false;
			}
			if(isProductPresent == true && isQuantityVerified == true)
			{
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}

	//Create a static product in DSF
	public String Create_Static_Product_In_DSF() throws Exception, IOException
	{	
		String productName = "DSF_"+UniqueAccount();

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Create_Product))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Create Product button");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Name))).sendKeys(productName);
		System.out.println("DSF product is: " +productName);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Product_Type)), "Static Document");
		Thread.sleep(1000);
		System.out.println("Selecting the product type as static document");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(4000);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DSF_Product_Next)));

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		//Assuming there are saved files (images) in DSF

		int rowCount = _driver.findElements(By.xpath("//select[@id='ctl00_ctl00_C_M_ctl00_W_ctl02_Savedfiles1_ListBox1']/option")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(1000);

		if(rowCount >0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_File))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Add File button");
			Thread.sleep(5000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Setting_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Next button");
			//Assuming 'Advanced Copies V3.0' is one of the options
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Ticket_Dropdown)), "Advanced Copies V3.0");
			System.out.println("Selecting 'Advanced Copies V3.0' from the dropdown");
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Finish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DSF_Publish)));
			System.out.println("Clicking the Finish button");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Publish button to publish the product");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ePace_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Selecting ePace Storefront to publish the product");
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Publish_Done))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the product Publish and then Done button");
		}
		else
		{
			System.out.println("Since there are no default files, hence this product cannot be created");
		}
		return productName;
	}

	//Create a static product in DSF with Zero cost and no shipping and no taxes
	public String Create_Zero_Cost_Static_Product_In_DSF() throws Exception
	{	
		PurchasePage PO = new PurchasePage(_driver);
		String productName = "DSF_"+UniqueAccount();

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Create_Product))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Create Product button");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Name))).sendKeys(productName);
		System.out.println("DSF product is: " +productName);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Product_Type)), "Static Document");
		Thread.sleep(1000);
		System.out.println("Selecting the product type as static document");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(4000);
		CommonFunctions.waitForPageLoaded(_driver);

		_driver.findElement(By.xpath("//a[@id='TabSettings']/span/span/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$ctl00$W$ctl01$chkShippingExempt"));
		PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$ctl00$W$ctl01$chkTaxExempt"));

		_driver.findElement(By.xpath("//a[@id='TabPricing']/span/span/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.xpath("//input[@id='tbl_0_PriceCatalog_regularprice_1']")).sendKeys("0");
		_driver.findElement(By.xpath("//input[@id='tbl_0_PriceCatalog_setupprice_1']")).sendKeys("0");
		_driver.findElement(By.xpath("//input[@id='tbl_1_PriceCatalog_regularprice_1']")).sendKeys("0");
		_driver.findElement(By.xpath("//input[@id='tbl_1_PriceCatalog_setupprice_1']")).sendKeys("0");
		_driver.findElement(By.xpath("//input[@id='tbl_5_PriceCatalog_regularprice_1']")).sendKeys("0");
		_driver.findElement(By.xpath("//input[@id='tbl_5_PriceCatalog_setupprice_1']")).sendKeys("0");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		//Assuming there are saved files (images) in DSF

		int rowCount = _driver.findElements(By.xpath("//select[@id='ctl00_ctl00_C_M_ctl00_W_ctl02_Savedfiles1_ListBox1']/option")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(1000);

		if(rowCount >0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_File))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Add File button");
			Thread.sleep(5000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Setting_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Next button");
			//Assuming 'Advanced Copies V3.0' is one of the options
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Ticket_Dropdown)), "Advanced Copies V3.0");
			System.out.println("Selecting 'Advanced Copies V3.0' from the dropdown");
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Finish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Finish button");
			((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Publish))));
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Publish button to publish the product");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ePace_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Selecting ePace Storefront to publish the product");
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Publish_Done))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the product Publish and then Done button");
		}
		else
		{
			System.out.println("Since there are no default files, hence this product cannot be created");
		}
		return productName;
	}

	//Order 1 DSF Static product
	public String Order_Product_In_DSF(String productName, String paymentMethod) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		ClearCart();
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");

		// CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
		// System.out.println("Selecting All from the dopdown");
		Thread.sleep(3000);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + productName);
		for (int i = rowCount; i>=1; i=i-2)
		{

			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				// System.out.println("Expected product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
				System.out.println("Entering the qty to be ordered as 10");

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(5000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				Thread.sleep(2000);
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Proceeding to Checkout");
				Thread.sleep(3000);
				//Logic for Payment method
				if (!paymentMethod.equals("Zero Payment"))
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
					Thread.sleep(1000);
				}
				System.out.println("Selecting the payment method as: " +paymentMethod);

				if(paymentMethod.equals("PO Number"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_PO_Number))).sendKeys("12345");
					System.out.println("Entering the PO number as '12345'");
				}
				if(paymentMethod.equals("Other Account"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Account_Number))).sendKeys("1234567");
					System.out.println("Entering the Account number as '1234567'");
				}
				if(paymentMethod.equals("Automation_AC"))
				{
					_driver.findElement(By.name("ctl00$C$CheckoutPaymentMethodControl$AccountCodeEntryArea$RP_AccCode$ctl00$AccCode1$TextBoxAccountCode")).sendKeys("12345");
					System.out.println("Entering the Accounting Code as '12345'");
				}


				Thread.sleep(2000);

				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}

	//Order 1 DSF Static product using Zero Payment
	public String Order_Product_In_DSF_Zero_Payment(String productName, String paymentMethod) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		ClearCart();
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");

		// CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
		// System.out.println("Selecting All from the dopdown");
		Thread.sleep(3000);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + productName);
		for (int i = rowCount; i>=1; i=i-2)
		{

			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				// System.out.println("Expected product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
				System.out.println("Entering the qty to be ordered as 10");

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(5000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				Thread.sleep(2000);
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Proceeding to Checkout");
				Thread.sleep(3000);

				//Since Zero Payment is enabled in ParintShop, user cannot see any other payment option in the dropdown
				//Hence directly clicking on the Next button as there is no need to select anything


				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}



	//Order 2 DSF Static products
	public String Order_Two_Static_Products_In_DSF(String product_1, String product_2, String paymentMethod) throws Exception
	{
		String[] sProducts = {product_1,product_2};

		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();

		for(int j =0;j<=1;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(sProducts[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + sProducts[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{
				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(sProducts[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					Thread.sleep(2000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking Add to Cart button");
					Thread.sleep(2000);
					//Begin: Adding second product
					boolean isProductfound_1 = false;
					if(sProducts[j].equals(product_1))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
						Thread.sleep(2000);
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}
		/* CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
				 System.out.println("Selecting All from the dopdown");
				 Thread.sleep(3000);

				 int rowCount_1 = _driver.findElements(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span")).size();
				 System.out.println("Total number of rows are: " + rowCount_1);
				 Thread.sleep(3000);
				 System.out.println("DSF product for which order is to be placed is: " + product_2);
				 for (int j = rowCount_1; j>=1; j=j-2)
				 {
					 String dsfProductName_1 = _driver.findElement(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span["+j+"]/div[1]/div[2]/div[1]/a")).getText();
					 System.out.println("DSF product in Storefront in row: "+j+" is: " + dsfProductName_1);
					 //Order the product
					 if(dsfProductName_1.equals(product_2))
					 {
						 isProductfound_1 = true;
						 System.out.println("Expected second product found in row: " +j);
						 _driver.findElement(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span["+j+"]/div[1]/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 System.out.println("Clicking the Begin button for " +dsfProductName_1 );
						 Thread.sleep(2000);
						 _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("50");
						 System.out.println("Entering the qty to be ordered as 50");
						 _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 System.out.println("Clicking Add to Cart button");
					 }
					 else
					 {
						 isProductfound_1 = false;
						 System.out.println("Second product is not found");
					 }
					 if(isProductfound_1 == true)
					 {
						 break;
					 }
				 }

		 */

		//End: Adding second product
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
		Thread.sleep(1000);
		System.out.println("Selecting the shipping method as Customer Pick-Up");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Proceeding to Checkout");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
		Thread.sleep(1000);
		System.out.println("Selecting the payment method as: " +paymentMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Click on Place My Order button");
		Thread.sleep(2000);
		CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}



		return dsfOrderNumber;
	}	

	//Order 2 DSF Static products
	public String Order_Three_Static_Products_In_DSF(String product_1, String product_2, String product_3, String paymentMethod) throws Exception
	{
		String[] sProducts = {product_1,product_2,product_3};

		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();

		for(int j =0;j<=2;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(sProducts[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + sProducts[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{
				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(sProducts[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					Thread.sleep(2000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking Add to Cart button");
					Thread.sleep(2000);
					//Begin: Adding second product
					boolean isProductfound_1 = false;
					if(sProducts[j].equals(product_1) || sProducts[j].equals(product_2))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
						Thread.sleep(2000);
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}
		/* CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
					 System.out.println("Selecting All from the dopdown");
					 Thread.sleep(3000);

					 int rowCount_1 = _driver.findElements(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span")).size();
					 System.out.println("Total number of rows are: " + rowCount_1);
					 Thread.sleep(3000);
					 System.out.println("DSF product for which order is to be placed is: " + product_2);
					 for (int j = rowCount_1; j>=1; j=j-2)
					 {
						 String dsfProductName_1 = _driver.findElement(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span["+j+"]/div[1]/div[2]/div[1]/a")).getText();
						 System.out.println("DSF product in Storefront in row: "+j+" is: " + dsfProductName_1);
						 //Order the product
						 if(dsfProductName_1.equals(product_2))
						 {
							 isProductfound_1 = true;
							 System.out.println("Expected second product found in row: " +j);
							 _driver.findElement(By.xpath("//div[@class='ctr-featuredproducts']/span[@id='ctl00_ctl00_C_W__FeaturedProductDisplayControlWP__myFeaturedProducts_Products']/span["+j+"]/div[1]/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
							 System.out.println("Clicking the Begin button for " +dsfProductName_1 );
							 Thread.sleep(2000);
							 _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("50");
							 System.out.println("Entering the qty to be ordered as 50");
							 _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
							 _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
							 System.out.println("Clicking Add to Cart button");
						 }
						 else
						 {
							 isProductfound_1 = false;
							 System.out.println("Second product is not found");
						 }
						 if(isProductfound_1 == true)
						 {
							 break;
						 }
					 }

		 */

		//End: Adding second product
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
		Thread.sleep(1000);
		System.out.println("Selecting the shipping method as Customer Pick-Up");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Proceeding to Checkout");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
		Thread.sleep(1000);
		System.out.println("Selecting the payment method as: " +paymentMethod);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Click on Place My Order button");
		Thread.sleep(2000);
		CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}



		return dsfOrderNumber;
	}

	public String Order_Multiple_Static_Products_In_DSF(String[] product, String paymentMethod) throws Exception
	{

		int j=0;
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();

		for( j =0;j<product.length;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(product[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + product[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{

				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(product[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking Add to Cart button");
					Thread.sleep(2000);

					//Begin: Adding second product
					boolean isProductfound_1 = false;

					System.out.println("Add product to cart "+j);
					if(j<product.length-1)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
						Thread.sleep(2000);
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}

		//End: Adding second product
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)));
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
		Thread.sleep(1000);
		System.out.println("Selecting the shipping method as Customer Pick-Up");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Proceeding to Checkout");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
		System.out.println("Selecting the payment method as: " +paymentMethod);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Click on Place My Order button");
		//	 Thread.sleep(2000);
		CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}



		return dsfOrderNumber;
	}	


	//Order 1 DSF FG product
	public String Order_FG_Product_In_DSF(String productName, String paymentMethod, String qty) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		ClearCart();
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");

		// CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
		// System.out.println("Selecting All from the dopdown");
		Thread.sleep(3000);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + productName);
		for (int i = rowCount; i>=1; i=i-2)
		{

			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[1]/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				// System.out.println("Expected product found in row: " +i);

				_driver.findElement(By.name("ctl00$ctl00$C$C1$EFIGridControlPrdSrch$ctl00$ProductDisplay1$prdQuant$TextBoxQuantity")).sendKeys(qty);
				//Add to Cart
				_driver.findElement(By.name("ctl00$ctl00$C$C1$EFIGridControlPrdSrch$ctl00$ProductDisplay1$beginB")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000); 

				Thread.sleep(5000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				Thread.sleep(2000);
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Proceeding to Checkout");
				Thread.sleep(3000);
				//Logic for Payment method
				if (!paymentMethod.equals("Zero Payment"))
				{
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
					Thread.sleep(1000);
				}
				System.out.println("Selecting the payment method as: " +paymentMethod);

				if(paymentMethod.equals("PO Number"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_PO_Number))).sendKeys("12345");
					System.out.println("Entering the PO number as '12345'");
				}
				if(paymentMethod.equals("Other Account"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Account_Number))).sendKeys("1234567");
					System.out.println("Entering the Account number as '1234567'");
				}
				if(paymentMethod.equals("Automation_AC"))
				{
					_driver.findElement(By.name("ctl00$C$CheckoutPaymentMethodControl$AccountCodeEntryArea$RP_AccCode$ctl00$AccCode1$TextBoxAccountCode")).sendKeys("12345");
					System.out.println("Entering the Accounting Code as '12345'");
				}


				Thread.sleep(2000);

				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}

	public String PlaceOrderPaymentModeSetToCreditCard(String[] product, String paymentMethod) throws Exception
	{
		int j=0;
		boolean isProductfound = false;
		String orderNo = null;
		boolean sCreditCard = false;
		String dsfOrderNumber = null;
		//Search the product in storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();

		for( j =0;j<product.length;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(product[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + product[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{

				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(product[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking Add to Cart button");
					Thread.sleep(2000);
					//Begin: Adding second product
					boolean isProductfound_1 = false;

					System.out.println("Add product to cart "+j);
					if(j<product.length-1)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
						Thread.sleep(2000);
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}


		//End: Adding second product
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
		Thread.sleep(1000);
		System.out.println("Selecting the shipping method as Customer Pick-Up");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Proceeding to Checkout");
		System.out.println("if paymethod is Credit Card" );
		/*if(paymentMethod.equals("Credit Card"))
				 {
					 CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
					 Thread.sleep(2000);
					 System.out.println("Selecting the payment method as: " +paymentMethod); 
					 _driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					 Thread.sleep(5000);
					 for(int h=0;h<=3;h++)
					 {
						 System.out.println("Verify the url" );
						 String sURL = _driver.getCurrentUrl();
						 System.out.println("sUrl is "+sURL);
						 if(sURL.equals("https://secure.ogone.com/ncol/test/orderstandard.asp"))
						 {
							 sCreditCard=true;
							 break;
						 }
						 else
						 {
							 Thread.sleep(3000);
							 sCreditCard=false;
							 System.out.println("Not able to navigate to credit card transaction page");
						 }


					 }
					 if(sCreditCard==true)
					 {
						 System.out.println("Enter credit card details" );
						 _driver.findElement(By.name("VISA_brand")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 Thread.sleep(5000);
						 _driver.findElement(By.name("Ecom_Payment_Card_Name")).sendKeys("Shilpa");
						 _driver.findElement(By.name("Ecom_Payment_Card_Number")).sendKeys("4111111111111111");
						CommonFunctions.selectDropdownByIndex(_driver, By.name("Ecom_Payment_Card_ExpDate_Month"), 12);
						Thread.sleep(1000);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("Ecom_Payment_Card_ExpDate_Year"), 12);
						Thread.sleep(1000);
						 _driver.findElement(By.name("Ecom_Payment_Card_Verification")).sendKeys("001");
						 _driver.findElement(By.name("payment")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 Thread.sleep(8000);

					 }
					 else
					 {
						 Assert.fail("Failed to do credit card transaction");
					 }					 
				 }*/

		System.out.println("Selecting the payment method as: " +paymentMethod); 
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
		Thread.sleep(2000);
		if(paymentMethod.equals("Credit Card"))
		{					 
			sCreditCard=CommonFunctions.isElementPresent(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardNumber)));

			if(sCreditCard==true)
			{
				System.out.println("Enter credit card details" );
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardType)), "Visa");
				Thread.sleep(1000);
				CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardNumber)), "4111111111111111");
				CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardCSV)), "001");
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardExpiryMonth)), "12");
				Thread.sleep(1000);
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardExpiryYear)), "2025");
				Thread.sleep(1000);					 
			}
			else
			{
				Assert.fail("Failed to do credit card transaction");
			}

		}

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);	
		System.out.println("Click on Place My Order button");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);			 
		CommonFunctions.waitForPageLoaded(_driver);	
		CommonFunctions.Wait(_driver, By.xpath("//span[@id='ctl00_C_LabelOrderNumber']"));

		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}		
		return dsfOrderNumber;
	}


	public void ExemptShippingCharges(String sKitProduct) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		boolean sCreditCard = false;
		String dsfOrderNumber = null;

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();
		System.out.println("Search for the Product");
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(sKitProduct);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of Product displayed are: " + rowCount);

		_driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch_ctl00_ProductDisplay1_ManageIt']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//a[@id='TabSettings']/span/span/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		selectCheckBox(true, By.name("ctl00$ctl00$C$M$ctl00$W$ctl01$chkShippingExempt"));
		_driver.findElement(By.name("ctl00$ctl00$C$M$ctl00$W$StartNavigationTemplateContainerID$ctl00$BtnSaveAndExit")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);


	}	 

	public String PlaceOrderForKit(String product,String sJobName,String sKitQTy) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		boolean sCreditCard = false;
		String dsfOrderNumber = null;

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); 
		CommonFunctions.waitForPageLoaded(_driver); 
		Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();
		System.out.println("Search for the Product");
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(product);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of Product displayed are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + product);
		for (int i = rowCount; i>=1; i=i-2)
		{
			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[1]/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(product))
			{
				isProductfound = true;
				System.out.println("Expected first product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000); 
				_driver.findElement(By.id("ctl00_ctl00_C_C1_txtJobName")).sendKeys(sJobName);
				_driver.findElement(By.id("ctl00_ctl00_C_C1_quantCntrl_TextBoxQuantity")).sendKeys(sKitQTy);

				int sProductsInKit=_driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_kits']/tbody/tr")).size();
				System.out.println("Number of products in kit are " +sProductsInKit);
				Thread.sleep(2000);
				for(int j=1;j<=sProductsInKit;j++)
				{
					String sProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_kits']/tbody/tr["+j+"]/td[1]/div[1]/div[1]/div[3]/div/a")).getText();
					String sProductStatus = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_kits']/tbody/tr["+j+"]/td/div[1]/div[1]/div[1]/span")).getText();
					sProductStatus=sProductStatus.trim();
					System.out.println("sProductName "+sProductName+" Status is " +sProductStatus );
					if(sProductStatus.equals("Customize"))
					{
						_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_kits']/tbody/tr["+j+"]/td[1]/div[1]/div[1]/div[3]/div[4]/div[3]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						Thread.sleep(5000);
						_driver.findElement(By.id("ctl00_C_ctl00_W_StartNavigationTemplateContainerID_Button1")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						Thread.sleep(2000);
						_driver.findElement(By.id("ctl00_C_ctl00_W_FinishNavigationTemplateContainerID_FinishButton")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						Thread.sleep(2000);
					}
					else
					{
						System.out.println("Product is Complete");
					}
				}
				CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$C1$btnKitGoBottom"));
				// _driver.findElement(By.name("ctl00$ctl00$C$C1$btnKitGoBottom")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				//Proceed to shipment page
				System.out.println("Shopping cart has 2 items. Now proceed to checkout");
				Thread.sleep(2000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				Thread.sleep(1000);
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Proceeding to Checkout");
				Thread.sleep(2000);
				System.out.println("paymethod is Pay At Store" );
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), "Pay At Store");
				Thread.sleep(2000);
				System.out.println("Selecting the payment method as: Pay At Store");
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}




			}
			else
			{
				System.out.println("Not able to find the product");
			}
		}
		return dsfOrderNumber;
	}

	public String PlaceOrderInDSF(String[] product, String paymentMethod,String sCostCenter,String sOtherAccount,String sPONumber) throws Exception
	{
		int j=0;
		boolean isProductfound = false;
		String orderNo = null;
		boolean sCreditCard = false;
		String dsfOrderNumber = null;
		//Search the product in storefront

		int continueShopping = _driver.findElements(By.xpath("//input[@id='ctl00_C_ButtonContinueShoppingTop']")).size();
		if(continueShopping>0)
		{
			_driver.findElement(By.xpath("//input[@id='ctl00_C_ButtonContinueShoppingTop']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		}
		else
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Storefront link");
		}

		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();

		for( j =0;j<product.length;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(product[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + product[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{

				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(product[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking Add to Cart button");
					Thread.sleep(2000);
					//Begin: Adding second product
					boolean isProductfound_1 = false;

					System.out.println("Add product to cart "+j);
					if(j<product.length-1)
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}


		//End: Adding second product
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
		Thread.sleep(1000);
		System.out.println("Selecting the shipping method as Customer Pick-Up");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Proceeding to Checkout");
		System.err.println("paymentMethod is: "+paymentMethod);

		//----------------If Payment Mode is Credit Card--------------------------------//

		if(paymentMethod.equals("Credit Card"))
		{
			Thread.sleep(1000);
			System.err.println("paymethod is Credit Card" );
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
			Thread.sleep(1000);
			System.err.println("Selecting the payment method as: " +paymentMethod); 
			Thread.sleep(2000);
			/*_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					 Thread.sleep(5000);
					 for(int h=0;h<=3;h++)
					 {
						 System.out.println("Verify the url" );
						 String sURL = _driver.getCurrentUrl();
						 System.out.println("sUrl is "+sURL);
						 if(sURL.equals("https://secure.ogone.com/ncol/test/orderstandard.asp"))
						 {
							 sCreditCard=true;
							 break;
						 }
						 else
						 {
							 Thread.sleep(3000);
							 sCreditCard=false;
							 System.out.println("Not able to navigate to credit card transaction page");
						 }


					 }
					 if(sCreditCard==true)
					 {
						 System.out.println("Enter credit card details" );
						 _driver.findElement(By.name("VISA_brand")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 Thread.sleep(5000);
						 _driver.findElement(By.name("Ecom_Payment_Card_Name")).sendKeys("Shilpa");
						 _driver.findElement(By.name("Ecom_Payment_Card_Number")).sendKeys("4111111111111111");
						CommonFunctions.selectDropdownByIndex(_driver, By.name("Ecom_Payment_Card_ExpDate_Month"), 12);
						Thread.sleep(1000);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("Ecom_Payment_Card_ExpDate_Year"), 12);
						Thread.sleep(1000);
						 _driver.findElement(By.name("Ecom_Payment_Card_Verification")).sendKeys("001");
						 _driver.findElement(By.name("payment")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						 Thread.sleep(8000);

					 }
					 else
					 {
						 Assert.fail("Failed to do credit card transaction");
					 }*/				 
			sCreditCard=CommonFunctions.isElementPresent(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardNumber)));

			if(sCreditCard==true)
			{
				System.out.println("Enter credit card details" );
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardType)), "Visa");
				Thread.sleep(1000);
				CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardNumber)), "4111111111111111");
				CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardCSV)), "001");
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardExpiryMonth)), "12");
				Thread.sleep(1000);
				CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.DSF_CreditCardExpiryYear)), "2025");
				Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
			}
			else
			{
				Assert.fail("Failed to do credit card transaction");
			}

		}

		//----------------If Payment Mode is Pay At Store---------------------------------//
		else if(paymentMethod.equals("Pay At Store"))
		{
			System.err.println("paymethod is Pay At Store" );
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
			Thread.sleep(1000);
			System.out.println("Selecting the payment method as: " +paymentMethod);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Click on Place My Order button");
			Thread.sleep(2000);
		}
		//----------------If Payment Mode is Cost Center--------------------------------//
		else if(paymentMethod.equals("Cost Center"))
		{
			System.err.println("paymethod is Cost Center" );
			Thread.sleep(2000);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
			System.err.println("Selecting the payment method as: " +paymentMethod);
			Thread.sleep(1000);
			System.out.println("Enter Cost Center Number");
			CommonFunctions.SendValue(_driver, By.name("ctl00$C$CheckoutPaymentMethodControl$TextboxBillingEntity"), sCostCenter);
			Thread.sleep(1000);

			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Click on Place My Order button");
			CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
			Thread.sleep(2000);
		}

		//----------------If Payment Mode is Other Account--------------------------------//
		else if(paymentMethod.equals("Other Account"))
		{
			Thread.sleep(2000);
			System.err.println("paymethod is Other Account" );
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
			System.err.println("Selecting the payment method as: " +paymentMethod);
			Thread.sleep(2000);
			System.out.println("Enter Other Account Number");
			CommonFunctions.SendValue(_driver, By.name("ctl00$C$CheckoutPaymentMethodControl$TextBoxFreeformAccountNumber"), sOtherAccount);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Click on Place My Order button");
			CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
			Thread.sleep(2000);
		}


		//----------------If Payment Mode is PO Number--------------------------------//
		else if(paymentMethod.equals("PO Number"))
		{
			Thread.sleep(2000);
			System.err.println("paymethod is PO Number" );
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
			System.err.println("Selecting the payment method as: " +paymentMethod);
			Thread.sleep(2000);
			System.out.println("Enter Other Account Number");
			CommonFunctions.SendValue(_driver, By.name("ctl00$C$CheckoutPaymentMethodControl$txtPONum"), sPONumber);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Click on Place My Order button");
			CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
			Thread.sleep(2000);
		}

		//--------------------------------------------------------------------------------//

		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}



		return dsfOrderNumber;
	}

	public String FetchJobNumber(String dsfOrderID) throws Exception
	{

		String sJobNumber ="";
		String sJobNumber_1 ="";
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/list");
		Thread.sleep(5000);
		System.out.println("**Job List page appears**");
		CommonFunctions.selectDropdownByText(_driver, By.name("list"), "Open Jobs");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "DSF Order ID");
		Thread.sleep(1000);
		System.out.println("Select DSF Order ID from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(dsfOrderID);
		System.out.println("Entering the dsf order is as: "+dsfOrderID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		Thread.sleep(5000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Jobs - Open Jobs"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		}
		else if(sTitle.equals("Jobs-ps - Open Jobs"))
		{
			sJobNumber_1 = _driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[3]/div")).getText();
			System.out.println("Job Number is: " +sJobNumber_1);
			_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/detail/"+sJobNumber_1);
		}
		
		_driver.findElement(By.xpath("//a[text()='Pace Connect']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		String sOrderNum = 	_driver.findElement(By.xpath("//div/h4[contains(label,'DSF Order ID')]/following-sibling::div/div")).getText();

		sOrderNum=sOrderNum.replace(",", "");
		sOrderNum=sOrderNum.trim();
		System.out.println("sOrderNum is "+sOrderNum);

		if(sOrderNum.equals(dsfOrderID))
		{
			sJobNumber= _driver.findElement(By.xpath("//div[@id='scrollableContent']/fieldset/div[1]/div[1]/div[1]/div/div")).getText();
			sJobNumber=sJobNumber.trim();
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Info']"));

		return sJobNumber;
	}

	//This testcase will navigate to job by using dsf order id
	public void NavigateToJobDetailsPage(String dsfOrderID) throws Exception
	{
		int j=0;
		String sJobNumber ="";
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/list");
		Thread.sleep(5000);
		System.out.println("**Job List page appears**");
		CommonFunctions.selectDropdownByText(_driver, By.name("list"), "Open Jobs");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "DSF Order ID");
		Thread.sleep(1000);
		System.out.println("Select DSF Order ID from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(dsfOrderID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);


		Thread.sleep(5000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		if(_driver.getTitle().equals("Jobs - Open Jobs"))
		{
			//			int ColCount = _driver.findElements(By.xpath("//table[@id='openJobs']/thead/tr/th")).size();
			//	        ArrayList<String> ColumnNames = new ArrayList<String>();
			//	        System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
			//	        for(int k =3;k<=ColCount;k++)
			//	        {
			//	        	
			//	        	int sSpanSize = _driver.findElements(By.xpath("//table[@id='openJobs']/thead/tr/th["+k+"]/a/span")).size();
			//	        	System.out.println("sSpanSize is "+sSpanSize);
			//	        	
			//	        	 String sCName = _driver.findElement(By.xpath("//table[@id='openJobs']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
			//	        	 sCName = sCName.trim();
			//	        	 System.out.println("sCName is "+sCName);
			//	        	 j=k;
			//	        	 System.out.println("j is "+j);
			//	        	 if(sCName.equals("DSF Order ID"))
			//	        	 break;
			//	        }
			//	        
			//			int rowCount =_driver.findElements(By.xpath("//table[@id='openJobs']/tbody/tr")).size();
			//			System.out.println("rowCount is "+rowCount);
			//			for(int i = 1;i<=rowCount;i++)
			//			{
			//				
			//				String sOrderId = _driver.findElement(By.xpath("//table[@id='openJobs']/tbody/tr["+i+"]/td["+j+"]/div")).getText();
			//				sOrderId =sOrderId.trim();
			//				System.out.println("sOrderId is "+sOrderId);
			//				if(sOrderId.equals(dsfOrderID))
			//				{
			//					_driver.findElement(By.xpath("//table[@id='openJobs']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			//					Thread.sleep(5000);
			//					break;
			//				}
			//			}
			_driver.findElement(By.xpath("//table[@id='openJobs']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.name("jobType"));
		}
		else
		{
			System.out.println("Navigated to Job");
		}
	}
	//Verify for P_3034 and P_3035
	//This method will only navigate to the desired job detail page
	public boolean Verify_Job_In_Pace(String dsfOrderID) throws Exception, IOException
	{
		String jobNumber = null; 
		String jobNum = null;
		boolean sFlag = false;
		int j=0;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/list");
		Thread.sleep(5000);
		System.out.println("**Job List page appears**");
		CommonFunctions.selectDropdownByText(_driver, By.name("list"), "All Jobs");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "DSF Order ID");
		Thread.sleep(1000);
		System.out.println("Select DSF Order ID from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(dsfOrderID);
		System.out.println("Searching for DSF Order: "+dsfOrderID);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		Thread.sleep(5000);

		if(_driver.getTitle().equals("Jobs - All Jobs"))
		{
			int ColCount = _driver.findElements(By.xpath("//table[@id='allJobs']/thead/tr/th")).size();
			ArrayList<String> ColumnNames = new ArrayList<String>();
			System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
			for(int k =3;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='allJobs']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='allJobs']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				j=k;
				System.out.println("j is "+j);
				if(sCName.equals("DSF Order ID"))
					break;
			}

			int rowCount =_driver.findElements(By.xpath("//table[@id='allJobs']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			if (rowCount > 0)
			{
				for(int i = 1;i<=rowCount;i++)
				{

					String sOrderId = _driver.findElement(By.xpath("//table[@id='allJobs']/tbody/tr["+i+"]/td["+j+"]/div")).getText();
					sOrderId =sOrderId.trim().replace(",", "").trim();
					System.out.println("sOrderId is "+sOrderId);
					if(sOrderId.equals(dsfOrderID))
					{
						_driver.findElement(By.xpath("//table[@id='allJobs']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						Thread.sleep(5000);
						sFlag = true;
						break;
					}
				}
			}
		}
		else
		{
			System.out.println("Navigated to Job");

			sFlag = true;
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.getPopupMessage(_driver);
		if (!sFlag)			
		{
			NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Error");
			System.err.println("System did not navigate to Job with DSF Order ID = "+dsfOrderID);
		}		
		return sFlag;

	}

	//Verify for P_3034 and P_3035
	//Verifying that the Job Part description is correct
	//Capturing the Job ID
	public String  VerifyProduct(String product) throws Exception
	{
		String jobNumber = null; 
		String jobNum = null;
		boolean sFlag = false;

		//Job detail page opens
		String sTitle_1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle_1);
		jobNum = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Num))).getText();

		//Verify the Part Description
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Job Parts info tab");

		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);

		for (int i = 2; i<=rowCount; i++)
		{
			System.out.println("I is "+i);
			String jobPartDescription = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[6]/input")).getAttribute("value");
			System.out.println("Job Part Description in row: "+i+" is: " + jobPartDescription);

			//Order the product
			if(jobPartDescription.equals(product))
			{
				System.out.println("Job Part description: "+jobPartDescription+" matches with the DSF Item: " +product);
				sFlag=  true;
			}	
			else
			{
				System.out.println("Job Part description does NOT match with the DSF Item");
			}
			i=i+1;	  

			//Give a break. 
			//If needed to verify for multiple parts, execute this function multiple times with different products.
			//Say there are 2 parts. Call this function 2 times.
			if(sFlag == true)
			{
				break;
			}

		}


		System.out.println("Job Number is: "+jobNum);
		if(!jobNum.equals(null) && sFlag == true)
		{
			jobNumber = jobNum;
		}
		return jobNumber;
	}

	public boolean VerifyJobPart(int sNumOfPart,String[] sJobParts) throws Exception
	{
		int j =0;
		boolean sFlag=false;
		System.out.println("Clcik on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		int ColCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
		for(int k =4;k<=ColCount;k++)
		{

			int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
			System.out.println("sSpanSize is "+sSpanSize);

			String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is "+sCName);
			j=k;
			System.out.println("j is "+j);
			if(sCName.equals("Part Description"))
				break;
		}

		for(int i = 0;i<sNumOfPart;i++)
		{
			int y = 0;
			if(i==0)
				y= i+2;

			else if(i==1)
				y=i+3;

			else if(i==2)
				y=i+4;

			String sProduct = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+y+"]/td["+j+"]/input")).getAttribute("value");
			sProduct=sProduct.trim();
			System.out.println("sProduct is "+sProduct);
			if(sProduct.equals(sJobParts[i]))
			{
				sFlag=true;
			}
			else
			{
				Assert.fail("sProduct "+sProduct+" is not equal to sJobParts"+sJobParts[i]);
			}


		}
		return sFlag;
	}

	public boolean VerifyJobPartDetails(int sNumOfPart,String[] sDSFKitJobName,String[] sDSFQty, String sComboITDesc) throws Exception
	{
		int j =0,Z=0;
		boolean sFlag=false;
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);

		String totalRecords = _driver.findElement(By.xpath("//fieldset[@id='JobPart_N1002F_fieldset']/div/div[1]/div[1]/strong")).getText();
		System.out.println("totalRecords is "+totalRecords);

		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("RowCount is "+rowCount);
		rowCount=rowCount-2;

		if(rowCount==sNumOfPart)	
		{
			int ColCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th")).size();
			ArrayList<String> ColumnNames = new ArrayList<String>();
			System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				j=k;
				System.out.println("j is "+j);
				if(sCName.equals("Part Description"))
					break;
			}

			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				Z=k;
				System.out.println("j is "+j);
				if(sCName.equals("Qty Ordered"))
					break;
			}



			for(int i = 0;i<sNumOfPart;i++)
			{
				int y = i+2;
				System.out.println("Y is "+y+" i is "+i);

				String sJobProduct = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+y+"]/td[4]/select"));
				sJobProduct=sJobProduct.trim();
				System.out.println("sJobProduct is "+sJobProduct);

				String sPartDesc = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+y+"]/td["+j+"]/input")).getAttribute("value");
				sPartDesc=sPartDesc.trim();
				System.out.println("sPartDesc is "+sPartDesc);

				String sQty = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+y+"]/td["+Z+"]/div")).getText();
				sQty=sQty.trim();
				System.out.println("sQty is "+sQty);


				if(sJobProduct.equals(sComboITDesc) && sPartDesc.equals(sDSFKitJobName[i]) && sQty.equals(sDSFQty[i])  )
				{
					sFlag=true;
				}
				else
				{
					Assert.fail("sJob Product is "+sJobProduct+" Expected Product is "+sComboITDesc+" sPartDesc is "+sPartDesc+" expected part desc is "+sDSFKitJobName+" Qty is "+sQty+" expected qty is sDSFQty "+sDSFQty[i]);
				}
			}

		}
		else
		{
			System.out.println("Expected No of Parts is "+sNumOfPart+" but actual parts is "+rowCount);
		}
		return sFlag;
	}

	public boolean VerifyPartDetails(int sNumOfPart,String[] sDSFKitJobName,String[] sDSFQty, String[] sComboITDesc) throws Exception
	{
		int j =0,Z=0;
		boolean sFlag=false;
		System.out.println("Clcik on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("RowCount is "+rowCount);
		//rowCount=rowCount-1;

		if(rowCount==sNumOfPart)	
		{
			int ColCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th")).size();
			ArrayList<String> ColumnNames = new ArrayList<String>();
			System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				j=k;
				System.out.println("j is "+j);
				if(sCName.equals("Part Description"))
					break;
			}

			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				Z=k;
				System.out.println("j is "+j);
				if(sCName.equals("Qty Ordered"))
					break;
			}



			for(int i = 2;i<=sNumOfPart;i++)
			{

				System.out.println(" i is "+i);


				String sJobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[4]/select")).replace("[", "").replace("]", "").trim();
				sJobProduct1=sJobProduct1.trim();
				System.out.println("sJobProduct is "+sJobProduct1);

				String sPartDesc1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td["+j+"]/input")).getAttribute("value");
				sPartDesc1=sPartDesc1.replace("Kit123 - ", "").trim();
				System.out.println("sPartDesc is "+sPartDesc1);

				String sQty1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td["+Z+"]/div")).getText();
				sQty1=sQty1.trim();
				System.out.println("sQty is "+sQty1);
				if(sJobProduct1.equals(sComboITDesc[i-2]) && sPartDesc1.equals(sDSFKitJobName[i-2]) && sQty1.equals(sDSFQty[i-2])  )
				{
					sFlag=true;
				}
				else
				{
					Assert.fail("sJob Product is "+sJobProduct1+" Expected Product is "+sComboITDesc[i-2]+" sPartDesc is "+sPartDesc1+" expected part desc is "+sDSFKitJobName[i-2]+" Qty is "+sQty1+" expected qty is sDSFQty "+sDSFQty[i-2]);
				}
			}


		}
		else
		{
			System.out.println("Expected No of Parts is "+sNumOfPart+" but actual parts is "+rowCount);
		}
		return sFlag;
	}
	public boolean VerifyEachJobPartDetails(int sNumOfPart,String[] sDSFKitJobName,String[] sDSFQty, String[] sComboITDesc) throws Exception
	{
		int j =0,Z=0;
		boolean sFlag=false;
		System.out.println("Clcik on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("RowCount is "+rowCount);
		//rowCount=rowCount-1;

		if(rowCount==sNumOfPart)	
		{
			int ColCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th")).size();
			ArrayList<String> ColumnNames = new ArrayList<String>();
			System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				j=k;
				System.out.println("j is "+j);
				if(sCName.equals("Part Description"))
					break;
			}

			for(int k =4;k<=ColCount;k++)
			{

				int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
				System.out.println("sSpanSize is "+sSpanSize);

				String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
				sCName = sCName.trim();
				System.out.println("sCName is "+sCName);
				Z=k;
				System.out.println("j is "+j);
				if(sCName.equals("Qty Ordered"))
					break;
			}

			String sJobProduct = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[4]/select")).replace("[", "").replace("]", "").trim();
			sJobProduct=sJobProduct.trim();
			System.out.println("sJobProduct is "+sJobProduct);

			String sPartDesc = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td["+j+"]/input")).getAttribute("value");
			sPartDesc=sPartDesc.trim();
			System.out.println("sPartDesc is "+sPartDesc);

			String sQty = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td["+Z+"]/div")).getText();
			sQty=sQty.trim();
			System.out.println("sQty is "+sQty);
			if(sJobProduct.equals(sComboITDesc[0]) && sPartDesc.equals(sDSFKitJobName[0]) && sQty.equals(sDSFQty[0])  )
			{
				sFlag=true;
			}
			else
			{
				Assert.fail("sJob Product is "+sJobProduct+" Expected Product is "+sComboITDesc+" sPartDesc is "+sPartDesc+" expected part desc is "+sDSFKitJobName+" Qty is "+sQty+" expected qty is sDSFQty "+sDSFQty[0]);
			}


			for(int i = 4;i<=sNumOfPart;i++)
			{

				System.out.println(" i is "+i);


				String sJobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[4]/select"));
				sJobProduct1=sJobProduct1.trim();
				System.out.println("sJobProduct is "+sJobProduct1);

				String sPartDesc1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td["+j+"]/input")).getAttribute("value");
				sPartDesc1=sPartDesc1.trim();
				System.out.println("sPartDesc is "+sPartDesc1);

				String sQty1 = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td["+Z+"]/div")).getText();
				sQty1=sQty1.trim();
				System.out.println("sQty is "+sQty1);
				if(sJobProduct1.equals(sComboITDesc[i-3]) && sPartDesc1.equals(sDSFKitJobName[i-3]) && sQty1.equals(sDSFQty[i-3])  )
				{
					sFlag=true;
				}
				else
				{
					Assert.fail("sJob Product is "+sJobProduct1+" Expected Product is "+sComboITDesc[i-3]+" sPartDesc is "+sPartDesc1+" expected part desc is "+sDSFKitJobName[i-3]+" Qty is "+sQty1+" expected qty is sDSFQty "+sDSFQty[i-3]);
				}
			}

		}
		else
		{
			System.out.println("Expected No of Parts is "+sNumOfPart+" but actual parts is "+rowCount);
		}
		return sFlag;
	}
	//Verify for P_3036, P_3038, P_3039
	public String  Verify_DSF_Payment_Method(String dsfPaymentMethod) throws Exception
	{
		String jobNumber = null; 
		String jobNum = null;
		String paymentMethod = null;
		String sFlag = null;

		//Job detail page opens
		String sTitle_1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle_1);
		jobNum = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Num))).getText();

		//Verify the payment method
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Billing_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Billing info tab");

		dsfPaymentMethod = dsfPaymentMethod.trim().toLowerCase();
		System.out.println("DSF Payment method is: "+dsfPaymentMethod);

		paymentMethod = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Payment_Method))).getText();
		paymentMethod = paymentMethod.trim().toLowerCase();
		System.out.println("MIS Payment Method is: "+paymentMethod);

		if(dsfPaymentMethod.equals(paymentMethod))
		{
			System.out.println("DSF Payment Method: "+dsfPaymentMethod+" matches with the one in Pace which is: " +paymentMethod);
			sFlag = jobNum;
		}
		else
		{
			System.out.println("DSF Payment Method is: "+dsfPaymentMethod+" and Pace payment method is: " +paymentMethod);
			sFlag = null;
		}
		return sFlag;
	}

	//Verify for P_3041, P_3042
	public String  Verify_Shipment_In_Job(String jobNumber) throws Exception
	{
		String shipVia = null;
		String shipToContact = null;
		String totalRecords = null;
		String totalNumberofRecords = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobShipment/listJob/"+jobNumber);
		Thread.sleep(5000);
		System.out.println("**Navigating to Job Shpiment page for job: "+jobNumber+"**");

		//Verify total number of shipments in job
		totalNumberofRecords = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Total_Records))).getText();
		System.out.println("Total number of records: " +totalNumberofRecords);

		int rowCount = _driver.findElements(By.xpath("//div[@id='shipmentGrid_source']/div")).size();
		System.out.println("Total number of rows are: " +rowCount);

		//Assuming that there will be not more than 2 shipments at a time in a single job
		if(totalNumberofRecords.equals("1"))
		{
			if(rowCount == 1)
			{
				System.out.println("This job has 1 shipment");
			}
		}
		else if(totalNumberofRecords.equals("2"))
		{
			if(rowCount == 2)
			{
				System.out.println("This job has 2 shipments");
			}
		}

		for (int i = 1; i<=rowCount; i++)
		{
			_driver.findElement(By.xpath("//div[@id='shipmentGrid_source']/div["+i+"]/div[1]/span/div/img[1]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Entering the Job Shipment detail page");
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.MIS_Ship_Via)));
			shipVia = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.MIS_Ship_Via)));
			System.out.println("Ship Via is: " +shipVia);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Shipment_Address))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Navigating to Shipment Address tab");

			shipToContact = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.MIS_Ship_To_Contact)));
			System.out.println("Ship To Contact is: " +shipToContact);

			Thread.sleep(2000);
			_driver.get("http://"+sSERVER+"/epace/company:public/object/JobShipment/listJob/"+jobNumber);
			Thread.sleep(3000);
			totalRecords = totalNumberofRecords;
		}		
		return totalRecords;
	}

	//Order 2 DSF Static products having 2 different shipments (multiple recipients) 
	public String Order_Two_Static_Products_With_Two_Shipments(String product_1, String product_2, String paymentMethod, String shipVia) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		String[] sProducts = {product_1,product_2};
		//Search the product in DSF
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		System.out.println("Clear Cart");
		ClearCart();
		for(int j =0;j<=1;j++)
		{
			System.out.println("Search for the Product");
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(sProducts[j]);
			Thread.sleep(1000);
			_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
			System.out.println("Total number of Product displayed are: " + rowCount);
			Thread.sleep(3000);
			System.out.println("DSF product for which order is to be placed is: " + sProducts[j]);
			for (int i = rowCount; i>=1; i=i-2)
			{
				String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
				System.out.println("DSF product in Storefront  is: " + dsfProductName);
				//Order the product
				if(dsfProductName.equals(sProducts[j]))
				{
					isProductfound = true;
					System.out.println("Expected first product found in row: " +i);
					_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Begin button for " +dsfProductName );
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
					System.out.println("Entering the qty to be ordered as 10");
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					System.out.println("Clicking Add to Cart button");

					//Begin: Adding second product
					boolean isProductfound_1 = false;
					if(sProducts[j].equals(product_1))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Continue_Shopping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking on Continue Shopping button");
						Thread.sleep(2000);
					}
				}
				else
				{
					System.out.println("Product not found");
					Assert.fail("Not able to find the product");
				}
			}
		}

		//End: Adding second product

		//Add logic to multiple shipments
		System.out.println("Now add 2 receipients to this job");
		//Assuming Customer Pickup has price setup done
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipments_Method)), "Customer Pick-Up");
		System.out.println("Selecting Customer Pickup as the 1st shipping method");
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Shipments_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Save button");
		Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_Another_Recipient))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the 'Add another recipient' button");
		Thread.sleep(3000);

		JavascriptExecutor jsx = (JavascriptExecutor)_driver;
		jsx.executeScript("window.scrollBy(0,800)", "");
		System.out.println("Scrolling down");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipments_Method_1)), shipVia);
		System.out.println("Selecting the Shipment method as: " +shipVia);
		Thread.sleep(4000);

		//Now add the 2nd recipient
		//Assuming that price setup has been taken care off for shipVia
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_First_Name))).sendKeys("DSF_First_Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Last_Name))).sendKeys("DSF_Last_Name");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shipments_Email))).sendKeys("xyz@gmail.com");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shipments_Address))).sendKeys("DSF_Shipments_Address");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shipments_City))).sendKeys("Denver");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipments_State)), "VI - (U.S.) Virgin Islands");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Shipments_ZIP))).sendKeys("56054");
		System.out.println("Entered the details of the recipint");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipments_Country)), "United States");
		System.out.println("Entered the Country as US");
		Thread.sleep(2000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Shipments_Save_1))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Save button");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_1))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//Thread.sleep(1000);
		//System.out.println("Clicking 1");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_1))).clear();
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_1))).sendKeys("4");
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_2))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//Thread.sleep(1000);
		//System.out.println("Clicking 2");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_2))).clear();
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_2))).sendKeys("16");
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_3))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_3))).clear();
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_3))).sendKeys("6");
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_4))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_4))).clear();
		//Thread.sleep(3000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Qty_4))).sendKeys("34");
		Thread.sleep(3000);
		System.out.println("Setting the quantities for recipient 1 and 2");
		System.out.println("Shopping cart has 2 items. Now proceed to checkout");

		//Now proceed to checkout
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout_Top))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Proceeding to Checkout");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
		System.out.println("Selecting the payment method as: " +paymentMethod);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Click on Place My Order button");
		CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);

		orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
		System.out.println("Order Number is: " +orderNo);

		if(!orderNo.equals(null))
		{
			dsfOrderNumber = orderNo;
		}
		else
		{
			dsfOrderNumber = null;
		}

		return dsfOrderNumber;
	}	

	//Create an ad-hoc product in DSF having a default item template (DEFDSFVAR)
	public String Create_AdHoc_Product_In_DSF(String productID) throws Exception, IOException
	{	
		String productName = "DSF_"+UniqueAccount();

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Create_Product))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Create Product button");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Name))).sendKeys(productName);
		System.out.println("DSF product is: " +productName);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Product_Type)), "Ad Hoc");
		System.out.println("Selecting the product type as Ad Hoc");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//Adding the default item template.
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_ID))).sendKeys(productID);
		System.out.println("Item Template attached with this AdHoc product is: " +productID);

		//Change the Buyer User Interface from Visual Product Builder to Classic HTML Interface
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_AdHoc_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Settings");
		Thread.sleep(2000);
		CommonFunctions.selectRadioButton(_driver, By.name(Locators.getProperty(Locators.DSF_HTML_Interface)), "False");
		//_driver.findElement(By.name(Locators.getProperty(Locators.DSF_HTML_Interface))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Classic HTML Interface button");
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ADHoc_Information))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Going back to the Information");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Next button");

		//Assuming 'Advanced Copies V3.0' is one of the options
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Ticket_Dropdown_AdHoc)), "Advanced Copies V3.0");
		System.out.println("Selecting 'Advanced Copies V3.0' from the dropdown");
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Finish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Finish button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Publish_AdHoc))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Publish button to publish the product");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ePace_Storefront_AdHoc))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Selecting ePace Storefront to publish the product");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Publish_AdHoc))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Selecting Publish button to publish the product");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Publish_Done_AdHoc))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the product Publish and then Done button");

		return productName;
	}

	//Order 1 DSF AdHoc product
	public String Order_AdHoc_Product_In_DSF(String productName, String paymentMethod) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		int rowCount;
		int rowCount_1;
		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);

		System.out.println("Clear Cart");
		ClearCart();

		System.out.println("Search for the Product");
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of Product displayed are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " +productName);

		for (int i = rowCount; i>=1; i=i-2)
		{
			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				System.out.println("Expected product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000);

				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_AdHoc_Job_Name))).sendKeys(productName);
				System.out.println("Entering the job name as 'test_123'");
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
				System.out.println("Entering the qty to be ordered as 10");
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_AdHoc_Pages))).sendKeys("50");
				System.out.println("Entering the number of pages as 50");

				JavascriptExecutor jsx = (JavascriptExecutor)_driver;
				jsx.executeScript("window.scrollBy(0,800)", "");


				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_AdHoc_Upload_Files))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking Upload Files");

				//Assuming there are saved files (images) in DSF

				rowCount_1 = _driver.findElements(By.xpath("//select[@id='ctl00_C_ctl00_W_ctl01_FilesPanel1_Filecontrol1_Savedfiles1_ListBox1']/option")).size();
				System.out.println("Total number of rows are: " + rowCount_1);
				Thread.sleep(1000);

				if(rowCount >0)
				{
					JavascriptExecutor jsx_1 = (JavascriptExecutor)_driver;
					jsx_1.executeScript("window.scrollBy(0,800)", "");

					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_AdHoc_Add_File))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Add File button");

					JavascriptExecutor jsx_2 = (JavascriptExecutor)_driver;
					jsx_2.executeScript("window.scrollBy(0,800)", "");					 

					Thread.sleep(10000);
					int counter = 0;
					do
					{
						Thread.sleep(3000);
						counter++;
					} while (!CommonFunctions.isElementPresent(_driver, By.xpath("//div[contains(@class,'ctr_scrollablesavedfiles')]")) && counter < 10);
					CommonFunctions.waitForPageLoaded(_driver);

					((JavascriptExecutor) _driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Next button");

					JavascriptExecutor jsx_3 = (JavascriptExecutor)_driver;
					jsx_3.executeScript("window.scrollBy(0,800)", "");					 
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(5000);

					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Add to Cart button");
					Thread.sleep(3000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
					Thread.sleep(1000);
					System.out.println("Selecting the shipping method as Customer Pick-Up");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Proceeding to Checkout");
					Thread.sleep(3000);
					//Logic for Payment method
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
					System.out.println("Selecting the payment method as: " +paymentMethod);
					Thread.sleep(1000);
					if(paymentMethod.equals("PO Number"))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_PO_Number))).sendKeys("12345");
						System.out.println("Entering the PO number as '12345'");
					}
					if(paymentMethod.equals("Other Account"))
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Account_Number))).sendKeys("1234567");
						System.out.println("Entering the Account number as '1234567'");
					}


					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(1000);
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Click on Place My Order button");
					// Thread.sleep(3000);
					CommonFunctions.WaitUntillPageNavigate(_driver, "Order/Quote Confirmation", 3);
					orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
					System.out.println("Order Number is: " +orderNo);
				}
				else
				{
					System.out.println("Since there are no default files, hence this product cannot be created");
				}
				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}

	//Create a static product in DSF having a default item template (DEFDSFVAR)
	public String Create_Static_Product_Default_Template(String productID) throws Exception, IOException
	{	
		String productName = "DSF_"+UniqueAccount();

		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");	

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Products))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking DSF Products");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Create_Product))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Create Product button");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Name))).sendKeys(productName);
		System.out.println("DSF product is: " +productName);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Product_Type)), "Static Document");
		System.out.println("Selecting the product type as static document");

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Next button to enter the product details");

		//Setting the item template
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_ID))).sendKeys(productID);
		_driver.findElement(By.name("ctl00$ctl00$C$M$ctl00$W$ctl01$txtMISEstimateId")).sendKeys(productID);
		System.out.println("Item Template attached with this static product is: " +productID);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//Assuming there are saved files (images) in DSF

		int rowCount = _driver.findElements(By.xpath("//select[@id='ctl00_ctl00_C_M_ctl00_W_ctl02_Savedfiles1_ListBox1']/option")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(1000);

		if(rowCount >0)
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_File))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Add File button");
			Thread.sleep(5000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Setting_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Next button");
			//Assuming 'Advanced Copies V3.0' is one of the options
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Ticket_Dropdown)), "Advanced Copies V3.0");
			System.out.println("Selecting 'Advanced Copies V3.0' from the dropdown");
			Thread.sleep(2000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Finish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Finish button");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the Publish button to publish the product");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ePace_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Selecting ePace Storefront to publish the product");
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Publish))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Publish_Done))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the product Publish and then Done button");
		}
		else
		{
			System.out.println("Since there are no default files, hence this product cannot be created");
		}
		return productName;
	}	

	//Verify for P_3045 and P_3048. 
	public ArrayList VerifyProduct_with_Item_Template(String product) throws Exception
	{
		String itemTemplate = null;
		String jobNumber = null; 
		String jobNum = null;
		boolean sFlag = false;
		ArrayList FinalFilename = new ArrayList();


		//Job detail page opens
		String sTitle_1 = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle_1);
		jobNum = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Num))).getText();
		System.out.println("Job Number of this job is: " +jobNum);
		FinalFilename.add(jobNum);

		//Verify the Part Description
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Job Parts info tab");

		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);

		for (int i = 2; i<=rowCount; i++)
		{
			String jobPartDescription = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[6]/input")).getAttribute("value");
			System.out.println("Job Part Description in row: "+i+" is: " + jobPartDescription);
			//Order the product
			if(jobPartDescription.equals(product))
			{
				System.out.println("Job Part description: "+jobPartDescription+" matches with the DSF Item: " +product);
				_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Opening the Job Part");
				itemTemplate = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[3]/div[1]/div[1]/div[1]/a")).getText();
				System.out.println("Item Template is: " +itemTemplate);
				FinalFilename.add(itemTemplate);
				//Go back to Job detail page
				sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
				_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/detail/"+jobNum+"?tab=1");
				Thread.sleep(5000);
				System.out.println("Navigating back to Job detail page");
				Thread.sleep(2000);
				sFlag=  true;
			}	
			else
			{
				System.out.println("Job Part description does NOT match with the DSF Item");
			}
			//Give a break. 
			//If needed to verify for multiple parts, execute this function multiple times with different products. 
			//Say there are 2 parts. Call this function 2 times.
			if(sFlag == true)
			{
				break;
			}
		} 

		System.out.println("Job Number is: "+jobNum);
		if(jobNum.equals(null) && sFlag == false)
		{
			Assert.fail("jobNum = " +jobNum + "sFalg = " +sFlag);
		}
		return FinalFilename;
	}	

	//Create Shipment Type having WebShipment=true, Planned=false, Status.DSFStatus="Shipping"
	//Assuming that there is a shipment status: Shipped already present in the system
	public ArrayList Create_Shipment_Type() throws Exception, IOException
	{
		String id = UniqueAccount();
		String description = "Shipment_Type_"+ShippingMethod();

		ArrayList  shipmentType = new ArrayList();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipmentType/list");
		System.out.println("**Navigating to Shipment Type list page");
		Thread.sleep(5000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add NEw Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(id);
		System.out.println("Entering the ID as: " +id);
		shipmentType.add(id);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Item_Description))).sendKeys(description);
		System.out.println("Entering the description as: " +description);
		shipmentType.add(description);

		//Assuming that there is a status: Shipped already created for P_3166
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Shipment_Status)), "Shipped");
		System.out.println("Setting the shipment status as Shipped");

		selectCheckBox(true, By.name(Locators.getProperty(Locators.DSF_Web_Shipment)));
		System.out.println("Enabling Web Shipment checkbox");
		Thread.sleep(1000);

		selectCheckBox(false, By.name(Locators.getProperty(Locators.DSF_Planned)));
		System.out.println("Disabling Planned checkbox");
		Thread.sleep(1000);

		selectCheckBox(true, By.name(Locators.getProperty(Locators.Active_CheckBox)));
		System.out.println("Enabling Active checkbox");
		Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(4000);
		Object_Added();

		return shipmentType;
	}

	//Verify for P_3084, P_3085, P_3086
	public boolean Change_Shipment_Type_In_Job(String jobNumber, String shipmentType) throws Exception, IOException
	{
		boolean isUpdated = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobShipment/listJob/"+jobNumber);
		Thread.sleep(5000);
		System.out.println("**Navigating to Job Shpiment page for job: "+jobNumber+"**");

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipment_Type_Dropdown)), shipmentType);
		System.out.println("Setting the shipment type as: " +shipmentType);

		isUpdated = Update();
		return isUpdated;
	}

	//Verify for P_3084
	//Verify DSF Order Statuses
	public String Verify_DSF_Order_Status(String orderNumber) throws Exception
	{
		boolean isOrderPresent = false;
		String dsfOrderNumber = null;
		String dsfOrderStatus = null;

		//Do not write the code to navigate to Administrator page here.
		//Instead, write it in the parent page.

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Order Status Page**");
		Thread.sleep(8000);

		//Get the row count
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			dsfOrderNumber = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[2]/span")).getText(); 
			dsfOrderNumber = dsfOrderNumber.trim();
			System.out.println("DSF Order Number in Row "+ i + " is: " +dsfOrderNumber);

			if (dsfOrderNumber.equals(orderNumber))
			{
				System.out.println("Expected Order Number is found");
				dsfOrderStatus = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[5]/span")).getText();
				dsfOrderStatus = dsfOrderStatus.trim().toLowerCase();
				System.out.println("DSF Order Status is: " +dsfOrderStatus);
				isOrderPresent = true;
			}
			if(isOrderPresent == true)
			{
				break;
			}
			else
			{
				System.out.println("Expected order could not be found");
			}
		}

		//Navigate to Administrator
		_driver.findElement(By.xpath("//div[@id='ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Navigating back to Administrator");

		return dsfOrderStatus;
	}
	public String VerifyDSFOrderStatus(String orderNumber,String sStatus) throws Exception
	{
		boolean isOrderPresent = false;
		String dsfOrderNumber = null;
		String dsfOrderStatus = null;

		//Do not write the code to navigate to Administrator page here.
		//Instead, write it in the parent page.

		//_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		_driver.findElement(By.xpath("//td[@id='ctl00_ctl00_RightHandMenun4']/table/tbody/tr/td/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Order Status Page**");
		Thread.sleep(8000);

		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListDate"), "One Week");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListStatus"), sStatus);
		Thread.sleep(5000);
		//Get the row count
		//int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr")).size();
		//System.out.println("Total number of rows are: " +rowCount);

		//for (int i = 2; i<=rowCount; i++)

		//Assumption made: Order will be found within first 6 rows
		for (int i = 2; i<=8; i++)
		{
			dsfOrderNumber = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[2]/span")).getText(); 
			dsfOrderNumber = dsfOrderNumber.trim();
			System.out.println("DSF Order Number in Row "+ i + " is: " +dsfOrderNumber);

			if (dsfOrderNumber.equals(orderNumber))
			{
				System.out.println("Expected Order Number is found");
				dsfOrderStatus = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[5]/span")).getText();
				// dsfOrderStatus = dsfOrderStatus.trim().toLowerCase();
				System.out.println("DSF Order Status in DSF page is: " +dsfOrderStatus);
				if(dsfOrderStatus.equals(sStatus))
				{
					isOrderPresent = true;
				}

			}
			else

			{ 
				System.out.println("Expected order could not be found");

			}
			if(isOrderPresent == true)
			{
				break;
			}
			else
			{
				System.out.println("Expected order status in wrong");
			}
		}

		//Navigate to Administrator
		_driver.findElement(By.xpath("//div[@id='ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Navigating back to Administrator");

		return dsfOrderStatus;
	}

	public String VerifyDSFOrderStatus_OrderView(String orderNumber,String printShop) throws Exception
	{
		boolean isOrderPresent = false;
		String dsfOrderNumber = null;
		String dsfOrderStatus = null;
		String status = null;

		//Do not write the code to navigate to Administrator page here.
		//Instead, write it in the parent page.

		_driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_HyperLinkOrderView2']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Order View Page**");
		Thread.sleep(3000);

		CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@id='ctl00_ctl00_C_M_DropDownListFacilities']"), printShop);
		Thread.sleep(2000);

		CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_DropDownListFilterBy']"), "Order");
		Thread.sleep(1000);

		System.out.println("Searching for Order No.: " +orderNumber);
		_driver.findElement(By.xpath("//input[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_TextBoxFilter']")).sendKeys(orderNumber);

		_driver.findElement(By.xpath("//input[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_ButtonFilter']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);

		String dsfOrderNo = _driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_DataGridOrders_ctl03_OrderViewItem_HyperLinkOrderNumber']")).getText();
		System.out.println("dsfOrderNo is: " +dsfOrderNo);

		if (dsfOrderNo.equals(orderNumber))
		{

			System.out.println("Expected Order is found");
			status = _driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_DataGridOrders_ctl03_OrderViewItem_LinkButtonStatus']")).getText();
			System.out.println("DSF Order status in Order View is: " +status);
		}
		else
		{
			System.out.println("Expected order could not be found");
		}

		//Navigate to Administrator
		//_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//System.out.println("Navigating back to Administrator");

		return status;
	}

	//Make ready - Job status
	//This is for In Production and Shipped
	public boolean Job_Status(String jobStatus) throws Exception, IOException
	{
		boolean isUpdate = false;
		boolean isUpdate_1 = false;
		boolean isUpdate_2 = false;
		boolean isSuccess = false;
		String jobDescription = null; 
		String jobDescription_1 = null; 

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobStatus/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Job Status list page**");		


		if(_driver.findElements(By.xpath("//div[@id='grid-contents']/div[1]/div[1]/div[2]/select[@class='pageSelect']")).size()>0)
		{
			Select dropDown = new Select(_driver.findElement(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")));
			java.util.List<WebElement> options = dropDown.getOptions();
			int sSize  = options.size();
			System.out.println("Size of the table is "+sSize);

			//Check the desired description in all the pages
			for (int j=1; j<=sSize; j++)
			{
				String k = String.valueOf(j);
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), k);
				Thread.sleep(5000);

				//Verify the job status
				int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
				System.out.println("Total number of rows are: " +rowCount);

				for (int i = 1; i<=rowCount; i++)
				{
					jobDescription = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]")).getText(); 
					jobDescription = jobDescription.trim();
					System.out.println("Job Description in Row "+ i + " is: " +jobDescription);

					//If Job Description matches with the desired job status
					if (jobDescription.equals(jobStatus))
					{
						System.out.println("Job description exists. Now editing it");
						_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking the job status");

						_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking the Settings tab");

						if(jobDescription.equals("Open"))
						{
							selectCheckBox(false, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
							System.out.println("Disabling DSF Completed checkbox");
						}
						else
						{
							selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
							System.out.println("Enabling DSF Completed checkbox");
						}


						_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
						Thread.sleep(2000);
						_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
						System.out.println("Entering the Job Status as: "+ jobStatus);

						isUpdate = Update();
					}
					if(isUpdate == true)
					{
						break;
					}
				}
				if(isUpdate == true)
				{
					break;
				}
			}
		}
		//if there is only 1 page, then the dorpdown for page will not appear. In that case, write an else condition
		else
		{
			//Verify the job status
			int rowCount_2 = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("Total number of rows are: " +rowCount_2);

			for (int i = 1; i<=rowCount_2; i++)
			{
				jobDescription = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]")).getText(); 
				jobDescription = jobDescription.trim();
				System.out.println("Job Description in Row "+ i + " is: " +jobDescription);

				//If Job Description matches with the desired job status
				if (jobDescription.equals(jobStatus))
				{
					System.out.println("Job description exists. Now editing it");
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the job status");

					_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Settings tab");

					selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
					System.out.println("Enabling DSF Completed checkbox");

					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
					System.out.println("Entering the Job Status as: "+ jobStatus);

					isUpdate_2 = Update();
				}
				if(isUpdate_2 == true)
				{
					break;
				}
			}
		}


		// If desired job status is not found, edit the 1st job description which is not any one of 
		//In Production or Shipped or Completed or Canceled
		if(isUpdate == false)
		{
			System.out.println("Expected Job status is not found in the list. Hence editing the existing ones");
			int rowCount_1 = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("Total number of rows are: " +rowCount_1);

			for (int j = 1; j<=rowCount_1; j++)
			{
				jobDescription_1 = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[4]")).getText();
				jobDescription_1 = jobDescription_1.trim();
				System.out.println("Job Description in Row "+ j + " is: " +jobDescription_1);

				if(!(jobDescription_1.equals("In Production") || jobDescription_1.equals("Canceled") || jobDescription_1.equals("Shipped") ||jobDescription_1.equals("Completed")))
				{
					System.out.println("Job description does not exists. Changing the existing ones");
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the job status");

					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(jobStatus);
					System.out.println("Setting the description as: " +jobStatus);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Settings tab");

					selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
					System.out.println("Disabling DSF Completed checkbox");

					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
					System.out.println("Entering the Job Status as: "+ jobStatus);

					isUpdate_1 = Update();
				}
				else
				{
					////////////////////FAIL
					System.out.println("Job description could not be found");
				}
				if(isUpdate_1 == true)
				{
					break;
				}
			}
		}

		if(isUpdate == true || isUpdate_1 == true)
		{
			isSuccess = true;
		}
		return isSuccess;
	}

	//Make ready - Job status
	//This is for Canceled
	public boolean Job_Status_Canceled(String jobStatus) throws Exception, IOException
	{
		boolean isUpdate = false;
		boolean isUpdate_1 = false;
		boolean isUpdate_2 = false;
		boolean isSuccess = false;
		String jobDescription = null; 
		String jobDescription_1 = null; 

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobStatus/list");
		Thread.sleep(5000);
		System.out.println("**Navigate to Job Status list page**");		


		if(_driver.findElements(By.xpath("//div[@id='grid-contents']/div[1]/div[1]/div[2]/select[@class='pageSelect']")).size()>0)
		{
			Select dropDown = new Select(_driver.findElement(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")));
			java.util.List<WebElement> options = dropDown.getOptions();
			int sSize  = options.size();
			System.out.println("Size of the table is "+sSize);

			//Check the desired description in all the pages
			for (int j=1; j<=sSize; j++)
			{
				String k = String.valueOf(j);
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), k);
				Thread.sleep(5000);

				//Verify the job status
				int rowCount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
				System.out.println("Total number of rows are: " +rowCount);

				for (int i = 1; i<=rowCount; i++)
				{
					jobDescription = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]")).getText(); 
					jobDescription = jobDescription.trim();
					System.out.println("Job Description in Row "+ i + " is: " +jobDescription);

					//If Job Description matches with the desired job status
					if (jobDescription.equals(jobStatus))
					{
						System.out.println("Job description exists. Now editing it");
						_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking the job status");

						_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.out.println("Clicking the Settings tab");

						selectCheckBox(false, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
						System.out.println("Disabling DSF Completed checkbox");

						selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Cancelled)));
						System.out.println("Enabling DSF Canceled checkbox");

						//call this function to check on alert boxes
						boolean sAlert = isAlertPresent();
						if(sAlert == true)
						{
							_driver.switchTo().alert().accept();
						}

						_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
						Thread.sleep(2000);
						_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
						System.out.println("Entering the Job Status as: "+ jobStatus);

						isUpdate = Update();
					}
					if(isUpdate == true)
					{
						break;
					}
				}
				if(isUpdate == true)
				{
					break;
				}
			}
		}
		//if there is only 1 page, then the dorpdown for page will not appear. In that case, write an else condition
		else
		{
			//Verify the job status
			int rowCount_2 = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("Total number of rows are: " +rowCount_2);

			for (int i = 1; i<=rowCount_2; i++)
			{
				jobDescription = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[4]")).getText(); 
				jobDescription = jobDescription.trim();
				System.out.println("Job Description in Row "+ i + " is: " +jobDescription);

				//If Job Description matches with the desired job status
				if (jobDescription.equals(jobStatus))
				{
					System.out.println("Job description exists. Now editing it");
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the job status");

					_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Settings tab");

					selectCheckBox(false, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
					System.out.println("Disabling DSF Completed checkbox");

					selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Cancelled)));
					System.out.println("Enabling DSF Canceled checkbox");

					//call this function to check on alert boxes
					AcceptModalDialog();

					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
					System.out.println("Entering the Job Status as: "+ jobStatus);

					isUpdate_2 = Update();
				}
				if(isUpdate_2 == true)
				{
					break;
				}
			}
		}


		// If desired job status is not found, edit the 1st job description which is not any one of 
		//In Production or Shipped or Completed or Canceled
		if(isUpdate == false)
		{
			System.out.println("Expected Job status is not found in the list. Hence editing the existing ones");
			int rowCount_1 = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("Total number of rows are: " +rowCount_1);

			for (int j = 1; j<=rowCount_1; j++)
			{
				jobDescription_1 = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[4]")).getText();
				jobDescription_1 = jobDescription_1.trim();
				System.out.println("Job Description in Row "+ j + " is: " +jobDescription_1);

				if(!(jobDescription_1.equals("In Production") || jobDescription_1.equals("Canceled") || jobDescription_1.equals("Shipped") ||jobDescription_1.equals("Completed")))
				{
					System.out.println("Job description does not exists. Changing the existing ones");
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the job status");

					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(jobStatus);
					System.out.println("Setting the description as: " +jobStatus);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_JobStatus_Settings))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the Settings tab");

					selectCheckBox(false, By.name(Locators.getProperty(Locators.MIS_DSF_Completed)));
					System.out.println("Disabling DSF Completed checkbox");

					selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_DSF_Cancelled)));
					System.out.println("Enabling DSF Canceled checkbox");

					//call this function to check on alert boxes
					AcceptModalDialog();

					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).clear();
					Thread.sleep(2000);
					_driver.findElement(By.name(Locators.getProperty(Locators.MIS_External_Status))).sendKeys(jobStatus);
					System.out.println("Entering the Job Status as: "+ jobStatus);

					isUpdate_1 = Update();
				}
				else
				{
					////////////////////FAIL
					System.out.println("Job description could not be found");
				}
				if(isUpdate_1 == true)
				{
					break;
				}
			}
		}

		if(isUpdate == true || isUpdate_1 == true)
		{
			isSuccess = true;
		}
		return isSuccess;
	}


	//Change the job status
	public boolean Change_Job_Status(String jobNumber, String jobStatus) throws Exception, IOException
	{
		boolean isUpdate = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/Job/detail/"+jobNumber);
		Thread.sleep(5000);
		System.out.println("Navigating to the job detail page of job: "+jobNumber);

		//Navigate to Job Part tab
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Job Parts info tab");

		System.out.println("Job Number is: " +jobNumber);
		System.out.println("Job Status is: " +jobStatus);

		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		if(rowCount < 2)
		{
			System.out.println("Job Part does not exist");
			dbConnection.UpdateFunction("DSF", "DSF", "P_3050", "Execution", "FAIL");
		}

		//Set the production status as job status for all the parts
		for (int i = 2; i<=rowCount; i++)
		{
			CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[9]/select"), jobStatus);
			Thread.sleep(1000);
			System.out.println("Selecting the Production Status as: " +jobStatus);
			i=i+1;
		}

		isUpdate = Update();
		if(isUpdate == false)
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3050", "Execution", "FAIL");
			dbConnection.UpdateFunction("DSF", "DSF", "P_3051", "Execution", "FAIL");
		}
		return isUpdate;
	}

	public boolean Change_Job_Status_For_Job_Part(String sDSFProduct, String jobStatus,int PartNum) throws Exception, IOException
	{
		int j =0,z=0;
		boolean isUpdate = false;
		System.err.println("sDSFProduct is: " +sDSFProduct);
		System.err.println("Job Status is: " +jobStatus);

		int ColCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
		for(int k =4;k<=ColCount;k++)
		{
			int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
			System.out.println("sSpanSize is "+sSpanSize);

			String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is "+sCName);
			j=k;
			if(sCName.equals("Part Description"))
				break;
		}
		for(int k =4;k<=ColCount;k++)
		{
			int sSpanSize = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span")).size();
			System.out.println("sSpanSize is "+sSpanSize);

			String sCName = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/thead/tr/th["+k+"]/a/span["+sSpanSize+"]")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is "+sCName);
			z=k;
			if(sCName.equals("Production Status"))
				break;
		}
		PartNum=PartNum+2;
		for(int i = 2;i<PartNum;i++)
		{
			int k =0;
			if(i==2)
				k=i+0;

			if(i==3)
				k=i+1;

			if(i==4)
				k=i+2;
			String sProduct = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+k+"]/td["+j+"]/input")).getAttribute("value");
			sProduct=sProduct.trim();
			System.out.println("sProduct is "+sProduct);
			if(sProduct.equals(sDSFProduct))
			{
				System.out.println("Selecting the Production Status as: " +jobStatus+" for Job Part "+sProduct);
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+k+"]/td["+z+"]/select"), jobStatus);
				Thread.sleep(1000);
				Update();
				Thread.sleep(5000);   
				break;
			}

		}
		for(int i = 2;i<PartNum;i++)
		{
			int k =0;
			if(i==2)
				k=i+0;

			if(i==3)
				k=i+1;

			if(i==4)
				k=i+2;
			String sProduct = _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+k+"]/td["+j+"]/input")).getAttribute("value");
			sProduct=sProduct.trim();
			System.out.println("sProduct is "+sProduct);
			if(sProduct.equals(sDSFProduct))
			{
				String sOption = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+k+"]/td["+z+"]/select"));

				if(sOption.equals(jobStatus))
				{
					isUpdate=true;
					System.out.println("Selected the Production Status as: " +jobStatus);
				}
				else
				{
					isUpdate=false;
				} 	
				break;
			}
			i =i+1;
		}






		return isUpdate;
	}

	//Set the specified Print Shop 
	public boolean Set_Print_Shop(String printShop) throws Exception, IOException
	{
		boolean isSuccess = false;
		boolean isDone = false;
		String print_Shop = null;
		String printShop1 = null;
		String printShop2 = null;

		print_Shop = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Selected_PrintShop))).getText();
		System.out.println("PrintShop to be set in DSF is: " +printShop);
		System.out.println("PrintShop currently set in DSF is: " +print_Shop);

		if(print_Shop.equals(printShop))
		{
			System.out.println("PrintShop is same");
			isSuccess = true;
		}
		else
		{
			System.out.println("PrintShop is different. Hence changing the printshop");
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Change_Print_Shop))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("Clicking the 'Change' button");

			CommonFunctions.selectDropdownByText(_driver, By.id("ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_PagetUITop_PageSizesDropDown"), "50");
			Thread.sleep(3000);

			//Set the print shop
			int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_dgAllFacilities']/tbody/tr")).size();
			System.out.println("Total number of rows are: " +rowCount);

			for (int i = 2; i<=rowCount; i++)
			{
				printShop1 = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_dgAllFacilities']/tbody/tr["+i+"]/td[2]")).getText(); 
				printShop1 = printShop1.trim();
				System.out.println("Print Shop in Row "+ i + " is: " +printShop1);

				if (printShop1.equals(printShop))
				{
					System.out.println("Expected print shop is present");

					boolean sFlag = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_dgAllFacilities']/tbody/tr["+i+"]/td[1]/input")).isSelected();
					if(sFlag == false)
					{
						String value = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_dgAllFacilities']/tbody/tr["+i+"]/td[1]/input")).getAttribute("value");
						System.out.println("Value is: "+value);
						CommonFunctions.selectRadioButton(_driver, By.xpath("//table[@id='ctl00_ctl00_C_W__myLocationWP__myLocation_FacilityPickerPopup_ctl00_dgAllFacilities']/tbody/tr["+i+"]/td[1]/input"), value);
						Thread.sleep(2000);
						System.out.println("Selecting the radio buttton");
					}

					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Change_Print_Shop_OK))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					System.out.println("Clicking the OK button");
					Thread.sleep(4000);

					printShop2 = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Selected_PrintShop))).getText();
					if(printShop2.equals(printShop))
					{
						System.out.println("PrintShop is now same");
						isSuccess = true;
					}

					break;
				}
			}
		}
		return isSuccess;
	}

	//Assign the company to user 
	public boolean Assign_Company_to_User(String company, String printShop) throws Exception, IOException
	{
		boolean isSuccess = false;
		String firstName = null;
		String firstName_1 = null;
		String lastName = null;
		String lastName_1 = null;
		String verifyCompany = null;

		/*
		//Get the First and Last name
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_My_Account))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to My Account Page**");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Edit))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the edit button");
		Thread.sleep(2000);

		//Set the first name
		firstName_1 = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_My_Account_First_Name))).getText();
		System.out.println("First Name is: "+firstName_1);
		if(firstName_1.equals(null))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_My_Account_First_Name))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_My_Account_First_Name))).sendKeys("Pace");
		}

		firstName = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_First_Name))).getText();
		System.out.println("First Name is: "+firstName);

		//Set the last name
		lastName_1 = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_First_Name))).getText();
		System.out.println("Last Name is: "+lastName_1);
		if(lastName_1.equals(null))
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Last_Name))).clear();
			_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Last_Name))).sendKeys("DSF");
		}

		lastName = _driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Last_Name))).getText();
		System.out.println("Last Name is: "+lastName);

		//Set the company
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Company))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Company))).sendKeys(company);
		System.out.println("Setting the company as: "+company);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_My_Account_Save))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Save button");
		Thread.sleep(2000);

		verifyCompany = _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_My_Account_View_Company))).getText();
		if(verifyCompany.equals(company))
		{
			isSuccess = true;
		}
		 */

		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Users))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to DSF Users Page**");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);

		//Assuming there is only 1 User Name for administrator
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Users_Dropdown)), "User Name");
		System.out.println("Selecting the User Name from dropdown");
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Users_Textbox))).sendKeys("administrator");
		System.out.println("Setting the User Name as 'administrator");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Users_Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);

		System.out.println("Select the company as: "+company);
		System.out.println("Select the print shop as: "+printShop);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			String userName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[4]")).getText(); 
			userName = userName.trim();
			System.out.println("User Name in Row "+ i + " is: " +userName);

			if (userName.equals("administrator"))
			{
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[8]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the 'Edit' button");
				Thread.sleep(2000);

				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[5]/select"), company);
				System.out.println("Selecting the company as: " +company);
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[6]/select"), printShop);
				System.out.println("Selecting the print shop as: " +printShop);

				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[8]/a[1]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Update button");
				Thread.sleep(1000);
				isSuccess = true;
				break;
			}
		}
		return isSuccess;
	}



	public boolean VerifyCustomerAvailabilty(String company) throws Exception
	{
		boolean isSuccess = false;
		_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Administration Page**");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Users))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to DSF Users Page**");

		//Assuming there is only 1 User Name for administrator
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Users_Dropdown)), "User Name");
		System.out.println("Selecting the User Name from dropdown");
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Users_Textbox))).sendKeys("administrator");
		System.out.println("Setting the User Name as 'administrator");
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Users_Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			String userName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[4]")).getText(); 
			userName = userName.trim();
			System.out.println("User Name in Row "+ i + " is: " +userName);

			if (userName.equals("administrator"))
			{
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[8]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the 'Edit' button");
				Thread.sleep(2000);


				System.out.println("Selecting the company as: " +company);
				WebElement dropdown = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_dataGridUsers']/tbody/tr["+i+"]/td[5]/select"));  
				Select select = new Select(dropdown);  

				java.util.List<WebElement> options = select.getOptions();
				for(WebElement we:options)  
				{  
					if(we.getText().equals(company))
					{
						System.err.println("Company  exists");
						isSuccess = true;
					}
					else
					{
						System.out.println("Company not exists");
						isSuccess = false;
					}
				}  


				Thread.sleep(1000);

				break;
			}
		}
		return isSuccess;
	}
	//Create Item Template Product Type
	public boolean Create_Item_Template_Product_Type(String paceconnectID) throws Exception, IOException
	{
		boolean isSuccess = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/PaceConnect/detail/"+paceconnectID);
		Thread.sleep(5000);
		System.out.println("**Navigating to the PaceConnect having id as: "+paceconnectID);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_DSF_Product_Types))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the DSF Product Types button");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add New Record button");

		//Assuming Business Cards is present 
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.MIS_Product_Type)), "Business Cards");
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("Business Cards");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		isSuccess = Object_Added();
		return isSuccess;
	}

	//Create Shipment Type having WebShipment=true, Planned=false
	//This is specifically for PaceConnect
	public List Create_Shipment_Type_for_PaceConnect() throws Exception, IOException
	{
		String id = UniqueAccount();
		String description = "Shipment_Type_"+ShippingMethod();

		List shipmentType = new ArrayList();

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/ShipmentType/list");
		System.out.println("**Navigating to Shipment Type list page");
		Thread.sleep(5000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Add New Record button");

		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(id);
		System.out.println("Entering the ID as: " +id);
		shipmentType.add(id);

		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Item_Description))).sendKeys(description);
		System.out.println("Entering the description as: " +description);
		shipmentType.add(description);

		//Leaving status as blank
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Shipment_Status)), "");
		System.out.println("Setting the shipment status as blank");

		selectCheckBox(false, By.name(Locators.getProperty(Locators.DSF_Web_Shipment)));
		System.out.println("Disabling Web Shipment checkbox");
		Thread.sleep(1000);

		selectCheckBox(false, By.name("shipToInventoryBooleanCheck"));
		System.out.println("Disabling Ship to Inventory");
		Thread.sleep(1000);


		selectCheckBox(false, By.name(Locators.getProperty(Locators.DSF_Planned)));
		System.out.println("Disabling Planned checkbox");
		Thread.sleep(1000);

		selectCheckBox(true, By.name(Locators.getProperty(Locators.Active_CheckBox)));
		System.out.println("Enabling Active checkbox");
		Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));		
		Object_Added();

		return shipmentType;
	}

	//PaceConnect Setup
	public boolean PaceConnect_Setup(String paceconnectID, String shipmentType, String shippingInvoice, String handlingInvoice) throws Exception, IOException
	{
		boolean isSuccess = false;
		String dsfLicenseMode = null;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/PaceConnect/detail/"+paceconnectID+"?tab=0");
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Active_Check)));
		System.out.println("**Navigating to the PaceConnect having id as: "+paceconnectID);

		selectCheckBox(true, By.name(Locators.getProperty(Locators.Active_Check)));
		System.out.println("Enabling the Active checkbox");
		Update();
		//Thread.sleep(1000);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_DSF_Connect_Setup))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Navigating to DSF Connect Setup tab");

		//Verify the license
		dsfLicenseMode = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_DSF_License_Mode))).getText();
		dsfLicenseMode=dsfLicenseMode.trim();
		if(dsfLicenseMode.equals("dsfFulfillment"))
		{
			System.out.println("License verified");
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3064", "Execution", "FAIL_License verification failed");
		}

		//Set the default shipment type having status as blank. 
		//Not necessary to keep it blank. Just ensure that the status in shipment type is not shipped.
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.MIS_Default_Shipment_Type)), shipmentType);
		Thread.sleep(1000);
		System.out.println("Setting the shipment type as: "+shipmentType);

		selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_Create_Cartons)));
		System.out.println("Enabling the Create Cartons checkbox");

		//Set Shipping and Handling Invoice
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.MIS_Shipping_Invoice)), shippingInvoice);
		Thread.sleep(1000);
		System.out.println("Setting the Shipping Invoice as: "+shippingInvoice);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.MIS_Handling_Invoice)), handlingInvoice);
		Thread.sleep(1000);
		System.out.println("Setting the Handling Invoice type as: "+handlingInvoice);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_DSF_Mapping))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Navigating to Mapping tab");

		//to code for mapping

		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_DSF_Execution_Options))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Navigating to Execution Options tab");
		Thread.sleep(1000);

		String totalRecords = _driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Event_Executions))).getText();
		if(totalRecords.equals("") || totalRecords.equals(null))
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3064", "Execution", "FAIL_event Executions are not created");
		}
		else
		{
			System.out.println("Event executions are not null");
		}

		isSuccess = Update();
		Thread.sleep(5000);

		return isSuccess;
	}

	//Create Job Billing batch. Add an invoice transaction for a specified job to this batch
	public String Job_Billing_Batch(String jobNumber) throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InvoiceBatch/add");
		Thread.sleep(5000);
		//Assuming that GL Period is setup in accordance with the current date

		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("Invoice Batch");
		System.out.println("Entering the description as 'Invoice Batch'");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the 'Add' button");
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		sTitle = sTitle.trim();
		System.out.println("Title of the page is "+sTitle);
		if(sTitle.equals("Batch"))
		{
			System.out.println("Invoice Batch detail page appears");
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3096", "Execution", "FAIL_Invoice Batch could not be added");
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the 'Add New Record' button");
		Thread.sleep(3000);
		String sTitle_1 = _driver.getTitle();
		sTitle_1 = sTitle_1.trim();
		System.out.println("Title of the page is "+sTitle_1);
		if(sTitle_1.equals("Adding Invoice Transaction"))
		{
			System.out.println("Adding Invoice page appears");
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3096", "Execution", "FAIL_Adding Invoice Transaction page did not appear");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Job_Number))).sendKeys(jobNumber);
		System.out.println("Entering the job Number as: "+jobNumber);

		_driver.findElement(By.name(Locators.getProperty(Locators.MIS_PO_Number))).sendKeys(jobNumber);
		System.out.println("Clicking the PO Number textbox"); 
		Thread.sleep(3000);

		if( _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Distribute_Tax))).getLocation().x < 0 || _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Distribute_Tax))).getLocation().y < 0 )
		{
			System.out.println("Distribute Tax checkbox is not displayed");
		}
		else
		{
			System.out.println("Distribute Tax checkbox displayed");
			selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_Distribute_Tax)));
			System.out.println("Enabling the Distribute Tax checkbox");
		}

		if( _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Partially_Bill))).getLocation().x < 0 || _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Partially_Bill))).getLocation().y < 0 )
		{
			System.out.println("Partailly Bill checkbox is not displayed");
		}
		else
		{
			System.out.println("Partailly Bill checkbox displayed");
			selectCheckBox(false, By.name(Locators.getProperty(Locators.MIS_Partially_Bill)));
			System.out.println("Disabling the Partially Bill checkbox");
		}

		if( _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Close_Job))).getLocation().x < 0 || _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Close_Job))).getLocation().y < 0 )
		{
			System.out.println("Close Job checkbox is not displayed");
		}
		else
		{
			System.out.println("Close Job checkbox displayed");
			selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_Close_Job)));
			System.out.println("Enabling the Close Job checkbox");
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Add button");
		Thread.sleep(3000);

		String sTitle_2 = _driver.getTitle();
		sTitle_2 = sTitle_2.trim();
		System.out.println("Title of the page is "+sTitle_2);
		if(sTitle_2.equals("Invoice Transaction"))
		{
			System.out.println("Invoice Transaction detail page appears");
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3096", "Execution", "FAIL_Invoice Transaction detail page did not appear");
		}

		//Get the Batch
		String batch = _driver.findElement(By.name(Locators.getProperty(Locators.MIS_Invoice_Batch))).getAttribute("value");
		batch = batch.trim();
		batch = batch.substring(0,4);
		System.out.println("Job Batch id is: "+batch);
		return batch;
	}

	//Approve batch ID
	public boolean Approve_Job_Batch(String batchID) throws Exception, IOException
	{
		boolean isSuccess = false;


		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InvoiceBatchTrn/list");
		Thread.sleep(5000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "ID");
		System.out.println("Selectig ID from the dropdown");

		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(batchID);
		System.out.println("Entering the batch Id as: "+batchID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the batch doesn't open directly, click the magnifying glass to open it
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Edit & Approve Invoice Batches - All"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//table[@id='All']/tbody/tr/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(4000);
		}

		selectCheckBox(true, By.name(Locators.getProperty(Locators.MIS_Approved)));
		System.out.println("Enabling Approved checkbox");

		isSuccess = Update();
		Thread.sleep(2000);
		return isSuccess;
	}

	//Process job batch
	public boolean Process_Job_Batch() throws Exception, IOException
	{
		boolean isSuccess = false;


		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/process/run?key=InvoiceBatchTrn.process");
		Thread.sleep(5000);	

		CommonFunctions.selectRadioButton(_driver, By.name(Locators.getProperty(Locators.MIS_Approved_Batches)), "Approved Batches");
		System.out.println("Clicking the Approved Batches radio button");
		_driver.findElement(By.name(Locators.getProperty(Locators.MIS_Invoice_Date))).sendKeys("T");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Process_Invoice_Batches))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Process Invoice Batches button");

		/*String sTitle = _driver.getTitle();
		sTitle = sTitle.trim();
		System.out.println("Title of the page is "+sTitle);
		if(sTitle.equals("Invoice Process in process..."))
		{
			_driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		}

		 */
		//_driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		//Assuming it will take max 25 seconds to process the batch
		Thread.sleep(25000);

		String sTitle_1 = _driver.getTitle();
		sTitle_1 = sTitle_1.trim();
		System.out.println("Title of the page is "+sTitle_1);
		if(sTitle_1.equals("Invoice Process Successful!"))
		{
			System.out.println("Batch has been processed");
			isSuccess = true;
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3097", "Execution", "FAIL");
			isSuccess = false;
		}
		return isSuccess;
	}	

	//Process job batch
	public boolean Post_Invoice(String batchID) throws Exception, IOException
	{
		boolean isSuccess = false;

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/process/run?key=InvoiceBatchTrn.post");
		Thread.sleep(5000);	

		CommonFunctions.selectRadioButton(_driver, By.name(Locators.getProperty(Locators.MIS_Approved_Batches)), "Approved Batches");
		System.out.println("Clicking the Approved Batches radio button");

		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Post_Invoice_Batches))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking Post Invoice Batches button");

		//Assuming it will take max 25 seconds to post the batch
		Thread.sleep(25000);

		String sTitle_1 = _driver.getTitle();
		sTitle_1 = sTitle_1.trim();
		System.out.println("Title of the page is "+sTitle_1);
		if(sTitle_1.equals("Invoice Post Successful!"))
		{
			System.out.println("Batch has been processed");
			isSuccess = true;
		}
		else
		{
			dbConnection.UpdateFunction("DSF", "DSF", "P_3098", "Execution", "FAIL");
			isSuccess = false;
		}

		/*
		//Verify that batch is posted

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/InvoiceBatch/list");
		Thread.sleep(5000);	

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "Original Batch ID");
		System.out.println("Select DSF Order ID from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(batchID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		Thread.sleep(2000);
		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);
		//boolean magnifyingGlass = CommonFunctions.isElementPresent(_driver,By.xpath(Locators.getProperty(Locators.Magnifying_Glass)));

		if(sTitle.equals("Posted Invoice Batches"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
		}
		 */

		return isSuccess;
	}
	public void ClearCart() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String sCartPath = "";
		if(_driver.findElements(By.xpath("//table[@id='ctl00_ctl00_RightHandMenu']/tbody/tr[1]/td[1]/table/tbody/tr/td/a")).size()>0)
		{
			sCartPath="//table[@id='ctl00_ctl00_RightHandMenu']/tbody/tr[1]/td[1]/table/tbody/tr/td/a";
		}
		else
		{
			sCartPath="//table[@id='ctl00_RightHandMenu']/tbody/tr[1]/td[1]/table/tbody/tr/td/a";
		}
		String sCart = _driver.findElement(By.xpath(sCartPath)).getText();
		sCart=sCart.trim();
		System.out.println("sCart is "+sCart);
		if(sCart.equals("Cart (0)"))
		{
			System.out.println("No item present in Cart");

		}
		else
		{
			_driver.findElement(By.xpath(sCartPath)).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			_driver.findElement(By.id("ctl00_C_ButtonClearCart")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(1000);
			PO.AcceptModalDialog();
			Thread.sleep(2000);
			String sEmptyCart=	_driver.findElement(By.xpath("//div[@id='ctl00_C_Div_SCEmpty']/table/tbody/tr[2]/td[1]/span[1]")).getText();
			sEmptyCart=sEmptyCart.trim();
			System.out.println("sEmptyCart is "+sEmptyCart);
			if(sEmptyCart.equals("Your Shopping Cart is empty."))
			{
				System.out.println("Cart got empty");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront1))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Storefront link");

			}
			else
			{
				Assert.fail("Not able to clear cart");
			}
		}
	}


	public void CreateProductItemLine() throws Exception
	{
		_driver.findElement(By.xpath("//a[text()='Product Items']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);


	}

	public boolean AddPrintService(String sDeterCode,String RecordType,String sDesc,String sUsePrice,String Seq,String Qty) throws Exception
	{
		boolean sFlag=false;
		DCPage DC = new DCPage(_driver);
		sLogs ="--Navigate to Add Print Service Page--";
		System.out.println("Navigate to Add Print Service Page");
		NavigateToAddPrintServicePage();
		sLogs =sLogs+"--Enter Print Service Details--";
		System.out.println("Enter Print Service Details");
		EnterPrintServiceDetails(sDeterCode,RecordType,sDesc,sUsePrice,Seq,Qty);
		sLogs =sLogs+"--Click On Add--";
		System.out.println("Click On Add");
		DC.Add();
		Thread.sleep(2000);
		sLogs =sLogs+"--Verify Print Service got added--";
		System.out.println("Verify Print Service got added");
		sFlag  = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		sLogs =sLogs+"--Print Service added "+ sFlag;
		System.out.println("Print Service added "+ sFlag);
		if(sFlag==true)
		{
			System.out.println("Print service got added");
		}
		else
		{
			Assert.fail(sLogs);
		}
		return sFlag;
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
					break;
				}	
				else {

					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}			
		}
	}

	//NavigateToListPrintServiceListPage
	public boolean AddPrintService_New(String sDeterCode,String RecordType,String sDesc,String sUsePrice,String Seq,String Qty) throws Exception
	{
		boolean sFlag=false;
		DCPage DC = new DCPage(_driver);
		sLogs ="--Navigate to Print Services List Page--";
		System.out.println("Navigate to Add Print Service Page");
		NavigateToListPrintServiceListPage();
		String sOriginalWindow = _driver.getWindowHandle();				
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(), 'Add New')]"));
		Thread.sleep(2000);
		SwitchToWindow("Adding PrintService");		
		sLogs =sLogs+"--Enter Print Service Details--";
		System.out.println("Enter Print Service Details");
		EnterPrintServiceDetails(sDeterCode,RecordType,sDesc,sUsePrice,Seq,Qty);
		sLogs =sLogs+"--Click On Add--";
		System.out.println("Click On Add");
		DC.Add();
		Thread.sleep(2000);
		_driver.switchTo().window(sOriginalWindow);
		CommonFunctions.waitForPageLoaded(_driver);
		sLogs =sLogs+"--Verify Print Service got added--";
		System.out.println("Verify Print Service got added");
		sFlag  = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		sLogs =sLogs+"--Print Service added "+ sFlag;
		System.out.println("Print Service added "+ sFlag);
		if(sFlag==true)
		{
			System.out.println("Print service got added");
		}
		else
		{
			Assert.fail(sLogs);
		}
		return sFlag;
	}	

	public void NavigateToAddPrintServicePage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PrintService/add");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("determinationCode"));
		assertEquals("Adding PrintService", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddPrintServicePage");
		System.out.println("****Print service page appears****");
	}

	public void NavigateToListPrintServiceListPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PrintService/list");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		assertEquals("PrintServices", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToListPrintServiceListPage");
		System.out.println("****Print services list page appears****");
	}

	public void EnterPrintServiceDetails(String sDeterCode,String RecordType,String sDesc,String sUsePrice,String Seq,String Qty) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.name("determinationCode")).clear();
		_driver.findElement(By.name("determinationCode")).sendKeys(sDeterCode);
		CommonFunctions.selectDropdownByText(_driver, By.name("recordType"), RecordType);
		Thread.sleep(1000);

		_driver.findElement(By.name("description")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("usePrice"), sUsePrice);
		Thread.sleep(1000);
		_driver.findElement(By.name("sequence")).clear();
		_driver.findElement(By.name("sequence")).sendKeys(Seq);

		_driver.findElement(By.name("quantityMultiplier")).clear();
		_driver.findElement(By.name("quantityMultiplier")).sendKeys(Qty);
		CommonFunctions.selectDropdownByText(_driver, By.name("bindingSide"), "Top");
		Thread.sleep(1000);
		PO.sSelectCheckBox(true, By.name("createPressFormBooleanCheck"));

		PO.sSelectCheckBox(true, By.name("activeBooleanCheck"));
	}

	public boolean AddProductLineItem(String sDeterCode,String RecordType,String sDesc,String sUsePrice,String sProductItem) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		boolean sFlag=false;


		sLogs ="--Click on product Tab--";
		System.out.println("Click on product Tab");
		_driver.findElement(By.xpath("//a[text()='Product Items']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		sLogs =sLogs+"--Click on Add In grid--";
		System.out.println("Click on Add In grid");
		_driver.findElement(By.xpath("//fieldset[@id='ItemTemplateProductTypeItem_N10038_fieldset']/div[1]/div[1]/div[2]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		sLogs =sLogs+"--Enter Details in Grid--";
		System.out.println("Enter Details in Grid");
		_driver.findElement(By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[2]/input")).clear();
		_driver.findElement(By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[2]/input")).sendKeys(sDeterCode);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[3]/select"), RecordType);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[4]/textarea")).clear();
		_driver.findElement(By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[4]/textarea")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[5]/select"), sProductItem);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateProductTypeItem_N10038']/tbody/tr[1]/td[6]/select"), sUsePrice);
		Thread.sleep(1000);
		sLogs =sLogs+"--Click on Update--";
		System.out.println("Click on Update");
		DC.Update();
		Thread.sleep(2000);
		sFlag =CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		if(sFlag == true)
		{
			System.out.println("Product line Item added");
		}
		else
		{
			sLogs =sLogs+"--Fail to add Product line Item added--";
			Assert.fail(sLogs);
		}
		return sFlag;
	}


	public  String FetchID(String sObject)
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sSERVER=sSERVER.toLowerCase();
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		System.out.println("sSERVER is "+sSERVER+" sCOMPANY is "+sCOMPANY);
		sSERVER=sSERVER.toLowerCase();

		String sGetURL = _driver.getCurrentUrl();
		System.out.println("sGetURL is "+sGetURL);
		String sStringReplace ="http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/"+sObject;
		System.out.println("sStringReplace is "+sStringReplace);
		String sID = sGetURL.replace(sStringReplace, "");

		System.out.println("sID is "+sID);

		sID = sID.substring(0, 4);
		System.out.println("sID is "+sID);
		return sID;
	}

	public  String FetchID_New(String sObject, String DeterminationCode) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		DC.SearchValue(DeterminationCode, "determinationCode");

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sSERVER=sSERVER.toLowerCase();
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		System.out.println("sSERVER is "+sSERVER+" sCOMPANY is "+sCOMPANY);
		sSERVER=sSERVER.toLowerCase();

		String sGetURL = _driver.getCurrentUrl();
		System.out.println("sGetURL is "+sGetURL);
		String sStringReplace ="http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/"+sObject;
		System.out.println("sStringReplace is "+sStringReplace);
		String sID = sGetURL.replace(sStringReplace, "");

		System.out.println("sID is "+sID);

		sID = sID.substring(0, 4);
		System.out.println("sID is "+sID);
		return sID;
	}

	public void AddNewInventoryTransaction(String sTransactionType,String sInvItem,String sQty,String sUnitPrice) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("transactionType"), sTransactionType);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("inventoryItem"), sInvItem);
		Thread.sleep(1000);
		_driver.findElement(By.name("inventoryLocation")).sendKeys(Keys.TAB);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryLocation"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("quantity"), sQty);
		Thread.sleep(1000);
		_driver.findElement(By.name("unitPrice")).clear();
		CommonFunctions.SendValue(_driver, By.name("unitPrice"), sUnitPrice);
		Thread.sleep(1000);
		//	CommonFunctions.selectDropdownByText(_driver, By.name("uom"), "C-/Hundred");
		Thread.sleep(1000);

	}

	public void AddNewInventoryTransactionChargeJob(String sTransactionType,String sInvItem,String sQty,String sJob) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("transactionType"), sTransactionType);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("inventoryItem"), sInvItem);
		Thread.sleep(1000);
		_driver.findElement(By.name("inventoryLocation")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryLocation"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("quantity"), sQty);
		Thread.sleep(1000);

		if(sTransactionType.equals("5 - Charge Job"))
		{
			CommonFunctions.SendValue(_driver, By.name("job"), sJob);
			Thread.sleep(1000);
		}
		_driver.findElement(By.name("inventoryItem")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		//	CommonFunctions.selectDropdownByText(_driver, By.name("uom"), "C-/Hundred");
		Thread.sleep(1000);

	}


	public String VerifyUnitPrice(String sTransactionType,String sInvItem,String sQty) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("transactionType"), sTransactionType);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("inventoryItem"), sInvItem);
		Thread.sleep(1000);
		_driver.findElement(By.name("inventoryLocation")).sendKeys(Keys.TAB);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryLocation"), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("inventoryBin"), 1);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("quantity"), sQty);
		Thread.sleep(1000);
		String sUnitPrice = CommonFunctions.GetValue(_driver, By.name("unitPrice"));
		Thread.sleep(1000);
		return sUnitPrice;

	}

	public void AddProductTypesToItemTemplate(String sItemTemplateProductTypeID) throws Exception
	{
		sLogs ="--Click on product Types--";
		System.out.println("Click on product Types");
		_driver.findElement(By.xpath("//a[text()='Product Types']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		sLogs ="--Click on Add Grid--";
		System.out.println("Click on Add Grid");
		_driver.findElement(By.xpath("//fieldset[@id='ItemTemplateProductTypeMapping_fieldset']/div[1]/div[1]/div[2]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);

		sLogs ="--Select Product Type--";
		System.out.println("Select Product Type");
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateProductTypeMapping']/tbody/tr[1]/td[2]/select"), sItemTemplateProductTypeID);
		Thread.sleep(1000);
	}

	public void NavigateToAddQuoteDepartmentPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteDepartment/add");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("code"));
		assertEquals("Adding Quote Department", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAddQuoteDepartmentPage");
		System.out.println("****Add Quote Department Page page appears****");
	}
	public void EnterQuoteDepartmentDetails(String sCode,String sDesc)
	{
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
	}

	public void AddQuoteCategory(String sCode,String sDesc,String sDept) throws Exception
	{
		System.out.println("Click on Add new Record");
		_driver.findElement(By.xpath("//fieldset[@id='QuoteCategory_fieldset']/div[1]/div[1]/div[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		System.out.println("Enter Quote Category Details");
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name("department"), sDept);


	}

	public void NavigateToQuoteItemPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteItemType/list");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		//assertEquals("Adding Quote Department", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToQuoteItem");
		System.out.println("****QuoteItem  page appears****");
	}

	public void SelectPrintServiceForQuoteItem(String sPrintService) throws Exception
	{
		System.out.println("Click on Print Service Setup");
		_driver.findElement(By.xpath("//a[text()='Print Service Setup']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Click on Add a grid");
		_driver.findElement(By.xpath("//fieldset[@id='QuoteItemTypePrintService_fieldset']/div[1]/div[1]/div[2]/Input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		System.out.println("select Print Service");
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='QuoteItemTypePrintService']/tbody/tr[1]/td[3]/select"), sPrintService);

		Thread.sleep(1000);

	}
	public void AddCategoryItemType(String sCode,String sDesc,String sName) throws Exception
	{
		System.out.println("Click on Add new Record");
		_driver.findElement(By.xpath("//fieldset[@id='QuoteItemType_fieldset']/div[1]/div[1]/div[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);
		System.out.println("Enter Quote Category Details");
		_driver.findElement(By.name("code")).sendKeys(sCode);
		_driver.findElement(By.name("name")).sendKeys(sName);
		_driver.findElement(By.name("description")).sendKeys(sDesc);

	}

	public void NavigateToActivePaceConnect(String sPaceConnect, String Server, String Company) throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/PaceConnect/detail/"+sPaceConnect);
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Pace Connect']"));
		//assertEquals("Adding Quote Department", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToActivePaceConnect");
		System.out.println("****Pace Connect  page appears****");
	}

	public  boolean VerifySuccessMessage(String sPaceConnect) throws Exception, IOException
	{
		boolean sFlag = false;
		int j=0;
		String pattern = "Success : .* posted to DSF";
		Pattern r = Pattern.compile(pattern);

		_driver.findElement(By.xpath("//a[text()='Successful Executions']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);
		int ColCount = _driver.findElements(By.xpath("//table[@id='SuccessResults']/thead/tr/th")).size();
		ArrayList<String> ColumnNames = new ArrayList<String>();
		System.out.println("NUMBER OF COLUMNS IN THIS TABLE = "+ColCount); 
		for(int k =4;k<=ColCount;k++)
		{
			String sCName = _driver.findElement(By.xpath("//table[@id='SuccessResults']/thead/tr/th["+k+"]/a/span")).getText();
			sCName = sCName.trim();
			System.out.println("sCName is "+sCName);
			j=k;
			System.out.println("j is "+j);
			if(sCName.equals("Result"))
				break;
		}
		System.out.println("Fetch warning message");
		String sWarningMsg = _driver.findElement(By.xpath("//table[@id='SuccessResults']/tbody/tr[1]/td["+j+"]/div")).getText();
		sWarningMsg=sWarningMsg.trim();
		System.out.println("sWarningMsg is "+sWarningMsg);
		Matcher m = r.matcher(sWarningMsg);
		//if(sWarningMsg.equals("Success : No update required to be sent to DSF for a credit card order that is completed."))
		if(m.find())
		{
			sFlag=true;
		}
		return sFlag;
	}

	public String  FetchVersion()
	{
		String sVersion = _driver.findElement(By.xpath("//div[@id='poweredby']/div[1]/div[1]")).getText();
		System.out.println("Version is "+sVersion);

		String sVer = sVersion.substring(0, Math.min(sVersion.length(), 2));

		System.out.println("Version  is  "+sVer);
		return sVer;
	}
	public void NavigateToEnterNewBatch() throws Exception, IOException
	{

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		System.out.println("sVersion is "+sVersion);
		if(Integer.valueOf(sVersion) >=  27)
		{			
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InvoiceBatch/add");
			Thread.sleep(2000);
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InvoiceBatchTrn/add");
			Thread.sleep(2000);
		}
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		//assertEquals("Adding Quote Department", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToEnterNewBatch");
		System.out.println("****New Batch page appears****");
	}

	public boolean AddNewInvoiceBatch(String sDesc) throws Exception
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);
		DCPage DC = new DCPage(_driver);
		_driver.findElement(By.name("description")).sendKeys(sDesc);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("glAccountingPeriod"), suniqueNumber);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name("date"), "t");		
		DC.Add();
		Thread.sleep(2000);
		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sFlag;
	}
	public boolean  AddInvoiceFromInvoiceBatchPage(String sJob,String sSaleDistributionMethod,String sTaxDistributionMethod ,String sTaxDistributionSource,String sCommissionDistributionSource,String sCommissionDistributionMethod,String sCloseJob) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		System.out.println("Click on Add new Invoice");
		Thread.sleep(3000);
		if( _driver.findElements(By.xpath("//fieldset[@id='InvoiceTrn_N1003AB_fieldset']/div[1]/div[1]/div[2]/a")).size()>0)
		{
			_driver.findElement(By.xpath("//fieldset[@id='InvoiceTrn_N1003AB_fieldset']/div[1]/div[1]/div[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(3000);
		}
		else
		{
			_driver.findElement(By.xpath("//fieldset[@id='Invoice_N1003AB_fieldset']/div[1]/div[1]/div[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(3000);
		}
		String sVersion=FetchVersion();
		System.out.println("sVersion is "+sVersion);
		if(Integer.valueOf(sVersion)< 27)
		{			
			_driver.findElement(By.name("job")).sendKeys(sJob);
			Thread.sleep(2000);
			_driver.findElement(By.name("poNumber")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			_driver.findElement(By.name("poNumber")).sendKeys("");
		}
		else
		{
			_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJob);
			Thread.sleep(2000);
			_driver.findElement(By.name("poNumber")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("salesDistributionMethod"), sSaleDistributionMethod);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("taxDistributionMethod"), sTaxDistributionMethod);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("taxDistributionSource"), sTaxDistributionSource);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("commissionDistributionMethod"), sCommissionDistributionMethod);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("commissionDistributionSource"), sCommissionDistributionSource);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name("closeJob"), sCloseJob);
			Thread.sleep(1000);			
			_driver.findElement(By.name("percentWipToRelieve")).clear();
			_driver.findElement(By.name("percentWipToRelieve")).sendKeys("0%");
			Thread.sleep(2000);

		}
		Thread.sleep(2000);
		DC.Add();
		Thread.sleep(5000);
		boolean sObjectAdded = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));


		return sObjectAdded;
	}

	public void ReturnToInvoiceBatch() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		_driver.findElement(By.xpath("//a[text()='Return to Invoice Batch']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		DC.Update();
		Thread.sleep(2000);
	}
	public void EnterINvoiceNumberAndApproveBatch(String sInvoiceNum) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		if( _driver.findElements(By.xpath("//table[@id='InvoiceTrn_N1003AB']/tbody/tr[1]/td[4]/input")).size()>0)
		{
			_driver.findElement(By.xpath("//table[@id='InvoiceTrn_N1003AB']/tbody/tr[1]/td[4]/input")).clear();
			_driver.findElement(By.xpath("//table[@id='InvoiceTrn_N1003AB']/tbody/tr[1]/td[4]/input")).sendKeys(sInvoiceNum);
		}
		else
		{
			_driver.findElement(By.xpath("//table[@id='Invoice_N1003AB']/tbody/tr[1]/td[4]/input")).clear();
			_driver.findElement(By.xpath("//table[@id='Invoice_N1003AB']/tbody/tr[1]/td[4]/input")).sendKeys(sInvoiceNum); 
		}


		_driver.findElement(By.name("approvedBooleanCheck")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		DC.Update();
		Thread.sleep(2000);
	}

	public void PostApprovedInvoice() throws Exception
	{
		int j=0;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion=FetchVersion();
		System.out.println("sVersion is "+sVersion);
		if(Integer.valueOf(sVersion)< 27)
		{			
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InvoiceBatchTrn.post");
			Thread.sleep(2000);
		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InvoiceBatch.post");
			Thread.sleep(2000);
		}
		_driver.findElement(By.xpath("//input[@value='Approved Batches']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//input[@value='Post Invoice Batches']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(10000);	
		String sTitle = _driver.getTitle();
		while(sTitle.contains("Invoice Post in process") && j<2)
		{
			Thread.sleep(5000);
			j++;
		}
		Thread.sleep(5000);
		//assertEquals("Invoice Post Successful!", _driver.getTitle());
	}

	public void CreateInvoiceAndPostInvoice(String sDesc,String sJob,String sSaleDistributionMethod,String sTaxDistributionMethod ,String sTaxDistributionSource,String sCommissionDistributionSource,String sCommissionDistributionMethod,String sCloseJob,String sInvoiceNum) throws Exception
	{
		System.out.println("Create new Batch ");
		NavigateToEnterNewBatch();
		System.out.println("Enter Invoice batch description ");
		boolean sFlag1 =  AddNewInvoiceBatch(sDesc);
		if(sFlag1==true)
		{
			System.out.println("Add Invoice and enter details ");
			boolean sFlag2 =  AddInvoiceFromInvoiceBatchPage(sJob,sSaleDistributionMethod,sTaxDistributionMethod ,sTaxDistributionSource,sCommissionDistributionSource,sCommissionDistributionMethod,sCloseJob);
			if(sFlag2==true)
			{
				System.out.println("Return to Invoice batch details page ");
				ReturnToInvoiceBatch();
				System.out.println("Enter INvoice Number And Approve Batch ");
				EnterINvoiceNumberAndApproveBatch(sInvoiceNum);
				System.out.println("Post Approved Invoice");
				PostApprovedInvoice();
			}
			else
			{
				Assert.fail("Failed to create invoice ");
			}
		}
		else
		{
			Assert.fail("Failed to create invoice batch");
		}
	}

	public boolean CancelAnOrderInDsf(String orderNumber,String sStatus) throws Exception
	{
		boolean isOrderPresent = false,isCancelled=false;
		String dsfOrderNumber = null;
		String dsfOrderStatus = null;

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Order Status Page**");
		Thread.sleep(8000);

		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListDate"), "One Week");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListStatus"), sStatus);
		Thread.sleep(5000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			dsfOrderNumber = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[2]/span")).getText(); 
			dsfOrderNumber = dsfOrderNumber.trim();
			System.out.println("DSF Order Number in Row "+ i + " is: " +dsfOrderNumber);

			if (dsfOrderNumber.equals(orderNumber))
			{
				System.out.println("Expected Order Number is found");
				isOrderPresent=true;

			}
			if(isOrderPresent==true)
			{
				System.out.println("Click on Cancel Button");
				_driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[5]/table/tbody/tr[2]/td/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				boolean sAlert = isAlertPresent();
				if(sAlert == true)
				{
					_driver.switchTo().alert().accept();
				}
				Thread.sleep(10000);
				String sSuccessMsg = _driver.findElement(By.xpath("//div[@id='ctl00_C_OrderHistoryControl1_FeedbackMessage_DivSuccess']/table/tbody/tr[1]/td[2]/span")).getText();	


				System.out.println("sSuccessMsg is: " +sSuccessMsg);

				if(sSuccessMsg.equals("Your order has been successfully canceled. Please note that cancellation charges may apply."))
				{		 
					isCancelled = true; 
				}

				break;
			}

		}
		return isCancelled;

	}
	public boolean VerifyOrderNumAndInvoice(String orderNumber,String sStatus,String sInvoiceNo,String sAmt) throws Exception
	{

		boolean isOrderPresent = false,isInvoicePresent=false;
		String dsfOrderNumber = null;
		String dsfOrderStatus = null;
		String sInvoiceNum=null;
		String sAmount=null;
		String sInvoiceLink=null;

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Status))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("**Navigating to Order Status Page**");
		Thread.sleep(8000);

		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListDate"), "One Week");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$C$OrderHistoryControl1$DropDownListStatus"), sStatus);
		Thread.sleep(5000);
		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr")).size();
		System.out.println("Total number of rows are: " +rowCount);

		for (int i = 2; i<=rowCount; i++)
		{
			dsfOrderNumber = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[2]/span")).getText(); 
			dsfOrderNumber = dsfOrderNumber.trim();
			System.out.println("DSF Order Number in Row "+ i + " is: " +dsfOrderNumber);

			if (dsfOrderNumber.equals(orderNumber))
			{
				System.out.println("Expected Order Number is found");
				dsfOrderStatus = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[3]/td[2]/span[5]/span")).getText();
				// dsfOrderStatus = dsfOrderStatus.trim().toLowerCase();
				System.out.println("DSF Order Status is: " +dsfOrderStatus);
				if(dsfOrderStatus.equals(sStatus))
				{		 
					isOrderPresent = true; 
				}

			}
			if(isOrderPresent == true)
			{
				sInvoiceNum = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[8]/td[1]/div/table/tbody/tr/td[1]")).getText(); 
				sInvoiceNum = sInvoiceNum.trim();
				System.out.println(" Invoice Num in Row "+ i + " is: " +sInvoiceNum);
				sAmount = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[8]/td[1]/div/table/tbody/tr/td[2]")).getText(); 
				sAmount = sAmount.trim();
				System.out.println(" sAmount in Row "+ i + " is: " +sAmount);
				sInvoiceLink = _driver.findElement(By.xpath("//table[@id='ctl00_C_OrderHistoryControl1_dataGridOrders']/tbody/tr["+i+"]/td/table/tbody/tr[8]/td[1]/div/table/tbody/tr/td[4]/a")).getText(); 
				sInvoiceLink = sInvoiceLink.trim();
				System.out.println(" sInvoiceLink in Row "+ i + " is: " +sInvoiceLink);

				if(sInvoiceNum.equals(sInvoiceNo) && sInvoiceLink.equals("Invoice File"))
				{
					isInvoicePresent=true;

				} 
				else
				{

					System.out.println("Expected Invoice Details could not be found");
				}
				break;
			}
			else
			{
				System.out.println("Expected order could not be found");
			}
		}
		return isInvoicePresent;
	}

	public boolean VerifyOrderNumAndInvoice_1(String printShop, String orderNumber,String sStatus,String sInvoiceNo,String sAmt) throws Exception
	{
		boolean isOrderStatusVerified = false,isInvoicePresent=false;

		_driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_HyperLinkOrderView2']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//_driver.findElement(By.xpath("//div[@id='ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//_driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_HyperLinkOrderView2']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$ctl00$C$M$DropDownListFacilities"), printShop);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("ctl00$ctl00$C$M$DataGridOrders$ctl02$OrderViewHeader$DropDownListFilterBy"), "Order");
		_driver.findElement(By.name("ctl00$ctl00$C$M$DataGridOrders$ctl02$OrderViewHeader$TextBoxFilter")).sendKeys(orderNumber);
		_driver.findElement(By.name("ctl00$ctl00$C$M$DataGridOrders$ctl02$OrderViewHeader$ButtonFilter")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);

		String orderStatus = _driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_DataGridOrders_ctl03_OrderViewItem_LinkButtonStatus']")).getText();
		orderStatus = orderStatus.trim();
		System.err.println("orderStatus is: "+orderStatus);
		Thread.sleep(2000);
		if(orderStatus.equals("Completed And Invoiced"))
		{
			isOrderStatusVerified = true;
		}
		else
		{
			isOrderStatusVerified = false;
		}

		return isOrderStatusVerified;
	}



	/*		public String VerifyDSFOrder_Invoice(String orderNumber,String sStatus,String sInvoiceNo,String sAmt) throws Exception
		{
			boolean isOrderPresent = false;
			String dsfOrderNumber = null;
			String dsfOrderStatus = null;
			String status = null;

			//Do not write the code to navigate to Administrator page here.
			//Instead, write it in the parent page.

			_driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_HyperLinkOrderView2']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			System.out.println("**Navigating to Order View Page**");
			Thread.sleep(3000);

			CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@id='ctl00_ctl00_C_M_DropDownListFacilities']"), printShop);
			Thread.sleep(2000);

			CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_DropDownListFilterBy']"), "Order");
			Thread.sleep(1000);

			System.out.println("Searching for Order No.: " +orderNumber);
			_driver.findElement(By.xpath("//input[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_TextBoxFilter']")).sendKeys(orderNumber);

			_driver.findElement(By.xpath("//input[@id='ctl00_ctl00_C_M_DataGridOrders_ctl02_OrderViewHeader_ButtonFilter']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(3000);

			String dsfOrderNo = _driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_DataGridOrders_ctl03_OrderViewItem_HyperLinkOrderNumber']")).getText();
			System.out.println("dsfOrderNo is: " +dsfOrderNo);

			if (dsfOrderNo.equals(orderNumber))
			 {

				System.out.println("Expected Order is found");
				status = _driver.findElement(By.xpath("//a[@id='ctl00_ctl00_C_M_DataGridOrders_ctl03_OrderViewItem_LinkButtonStatus']")).getText();
				System.out.println("DSF Order status in Order View is: " +status);
			}
			else
			{
				System.out.println("Expected order could not be found");
			}

			//Navigate to Administrator
			//_driver.findElement(By.xpath("//div[@id='ctl00_ctl00_Tabs']/table/tbody/tr[2]/td[2]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			//System.out.println("Navigating back to Administrator");

			 return status;
		}
	 */	 

	//This method will click on the 1st Job Part in Job Products grid and go to date tab enter the actual ship date
	public void EnterActualShippedDateForJobPart(String sActualShipDate) throws Exception
	{


		System.out.println("click on the 1st Job Part in Job Products grid");
		_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Able to attached to Popup window");
		System.out.println("Click on Date Information Tab");
		_driver.findElement(By.xpath("//div[@id='scrollableContent']/div[3]/div[2]/span/a/span")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println("Enter actual Ship Date");
		_driver.findElement(By.name("actualShipDate")).sendKeys(sActualShipDate);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
	}
	//go to Accounting>Job Billing>Process Invoice>Auto Job Billing-Standard
	public void NavigateToAutoJobBillingStandard() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=Job.createAutoInvoices");
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Create Invoices']"));
		assertEquals("Creating Invoices from Jobs", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToAutoJobBillingStandard");
		System.out.println("****Auto Job Billing Standard appears****");
	}

	// IN Accounting>Job Billing>Process Invoice>Auto Job Billing-Standard
	//Enter a date range to bill
	//Select DSF from PaceConnectType field

	public void EnterAutoJobBillingStandardDetails() throws Exception
	{
		_driver.findElement(By.name("startDate")).sendKeys("t");
		_driver.findElement(By.name("startDate")).sendKeys("t");
		CommonFunctions.selectDropdownByText(_driver,  By.name("PaceConnectInvoice"), "Only Include DSF Orders");
		Thread.sleep(1000);
	}

	//Click on Create Invoice button and wait
	public void ClickCreateInvoiceButton() throws Exception
	{
		int j=0;
		_driver.findElement(By.xpath("//input[@value='Create Invoices']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);


		String sTitle = _driver.getTitle();
		while(sTitle.contains("Invoice Creation in process...") && j<2)
		{
			Thread.sleep(5000);
			j++;
		}
	}

	public boolean ClickCreateInvoiceButton_1() throws Exception
	{
		int j=0;
		boolean isSuccess = false;

		_driver.findElement(By.xpath("//input[@value='Create Invoices']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(5000);


		String sTitle = _driver.getTitle();
		while(sTitle.contains("Invoice Creation in process...") && j<2)
		{
			Thread.sleep(5000);
			j++;
		}

		String sTitle1 = _driver.getTitle();
		System.err.println("sTitle1 is: "+sTitle1);
		if(sTitle1.contains("Successful"))
		{
			isSuccess = true;
			System.err.println("isSuccess is: "+isSuccess);
		}
		else
		{
			isSuccess = false;
			System.err.println("isSuccess is: "+isSuccess);
		}

		return isSuccess;

	}

	public void NavigateToPostInvoice() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sVersion = FetchVersion();
		if(Integer.valueOf(sVersion) >=  27)
		{

			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InvoiceBatch.post");

		}
		else
		{
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/process/run?key=InvoiceBatchTrn.post");

		}
		Thread.sleep(2000);
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Post Invoice Batches']"));
		assertEquals("Post Invoice Batches", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToPostInvoice");
		System.out.println("****PostInvoice page appears****");
	}


	public void VerifyInvoiceBatchCreated(String sJob) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		System.out.println("Navigate To Job");

		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobPart/invoices/"+sJob+"%3A01");
		Thread.sleep(2000);
	}

	public void EnableBillingOKInSelectedProductionStatus(String sJob) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);

		//		 _driver.findElement(By.xpath("//div/h4[contains(label,'Production Status')]/following-sibling::div/div[1]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/h4[contains(label,'Production Status')]/following-sibling::div/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000); 
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Job Status ")) 
				{
					_driver.findElement(By.xpath("//a[text()='Settings']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					PO.sSelectCheckBox(true, By.name("billingOKBooleanCheck"));
					DC.Update();
					Thread.sleep(5000);
					_driver.close();

					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part 01");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJob+" part 01");

				}

			}
		}
	}

	// This method is to navigate to Administrator Tab
	public void NavigateToAdministratorTab() throws Exception
	{
		System.out.println("Click on Administrators tab");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Home_Administrator))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		//	CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Home_Administrator)));
		Thread.sleep(5000);
	}

	// This method is to navigate to Administrator Tab
	public void NavigateToCompanyPage() throws Exception
	{

		CommonFunctions.ClickElement(_driver, By.linkText("Companies"));

	}

	// This method is to navigate to Taxes Page
	public void NavigateToTaxesPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.linkText("Taxes"));

	}

	// This method is to navigate to Pricing Page
	public void NavigateToPricingPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.linkText("Pricing"));

	}

	// This method is to navigate to MIS Systems Page
	public void NavigateToMISSystemsPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.linkText("MIS Systems"));
	}


	// This method is to navigate to Printshop Page
	public void NavigateToPrintshopPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@href='ManageFacilitiesLanding.aspx']"));

	}
	// This method is to Enable_Shipping_And_Taxes in taxes page
	public void Enable_Shipping_And_Taxes() throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Click on Settings");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Settings']"));
		Thread.sleep(2000);

		System.out.println("Enable_Shipping_And_Taxes checkbox");
		PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$PriceSettingCheckboxDoTaxes"));
		PO.sSelectCheckBox(true, By.name("ctl00$ctl00$C$M$PriceSettingCheckboxDoShipping"));

		System.out.println("Click on Save");
		Save();
	}

	public void Save() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$M$PriceSettingButtonSave"));
		Thread.sleep(2000);

	}

	public void CommonSave() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$M$ButtonSave"));
		Thread.sleep(2000);
	}

	public void EditPrintShop(String sPrintShop) throws Exception
	{

		System.out.println("In CheckPrintShopPresent");
		WebElement selectallprintshops = _driver.findElement(By.id("ctl00_ctl00_C_M_TopPagerUI_PageSizesDropDown"));
		selectallprintshops.sendKeys("All");
		Thread.sleep(10000);

		if(_driver.findElements(By.linkText(sPrintShop)).size() > 0)
		{
			String PSAttr = _driver.findElement(By.linkText(sPrintShop)).getAttribute("href");
			System.out.println(PSAttr);
			_driver.findElement(By.linkText(sPrintShop)).getAttribute("href");
			System.out.println("The Printshop" +sPrintShop+" is present");

			System.out.println("Click on  Printshop" +sPrintShop);
			_driver.findElement(By.linkText(sPrintShop)).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(5000);
		}
		else
		{
			System.out.println("The Printshop" +sPrintShop+" is not present");
		}
	}

	//This method enable/disable handling charges on Prinstop depending on sflag
	public void SetHandlingChargesInPrintshop(boolean sFlag) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Set Handling charges in printshop");
		PO.sSelectCheckBox(sFlag, By.name("ctl00$ctl00$C$M$cbDisplayHandlingCharges"));
		Thread.sleep(1000);
		CommonSave();
		Thread.sleep(5000);
	}

	//This method is to edit Mis System
	public void EditMISSystem(String sMISSystem) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr")).size();

		for(int i =2;i<=rowcount;i++)
		{
			String sMISSystem1 =_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr["+i+"]/td[2]/a")).getText();
			sMISSystem1=sMISSystem1.trim();
			System.out.println("sMISSystem is " +sMISSystem1);
			if(sMISSystem1.equals(sMISSystem))
			{
				CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='ctl00_ctl00_C_M_DataGrid1']/tbody/tr["+i+"]/td[2]/a"));
				Thread.sleep(2000);
				break;
			}
		}

	}


	//This Method is to Make sure to enable "Accepts Single Order" in MIS System
	public void EnableAcceptsSingleOrderInMISSystem(boolean sFlag,String sMISSystem) throws Exception
	{

		PurchasePage PO = new PurchasePage(_driver);
		EditMISSystem(sMISSystem);
		Thread.sleep(2000);
		System.out.println("Enable Accept  Single Order In MIS System");
		PO.sSelectCheckBox(sFlag, By.name("ctl00$ctl00$C$M$CheckBoxSingleOrder"));
		Thread.sleep(1000);
		CommonSave();
		Thread.sleep(5000);
	}

	//This Method is to fetch subtotal, shipping,handling ,taxes and total price from confrimation page
	public void FetchPricingFromConfirmationPage(String sScenario,String sTestCaseName)
	{
		System.out.println("Fetch subtotal, shipping,handling ,taxes and total price from confrimation page");
		int rowcount = _driver.findElements(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr")).size();
		System.out.println("row count is "+rowcount);
		if(rowcount>=5)
		{
			for(int i =1;i<=rowcount;i++)
			{
				String sGetRowName = _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[1]/span")).getText();
				sGetRowName=sGetRowName.trim();
				System.out.println("row name is "+sGetRowName);

				String sGetCost= _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[2]/span")).getText();
				sGetCost=sGetCost.trim();
				System.out.println("row Cost is "+sGetCost);


				if(sGetRowName.equals("Subtotal:"))
				{
					System.out.println("Subtotal : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "SubTotal", sGetCost);
				}
				else if(sGetRowName.equals("Shipping:"))
				{
					System.out.println("Shipping : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "ShippingAmount", sGetCost);
				}
				else if(sGetRowName.equals("Handling:"))
				{
					System.out.println("Handling : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "HandlingAmount", sGetCost);
				}
				else if(sGetRowName.equals("Taxes:"))
				{
					System.out.println("Taxes : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "TaxAmount", sGetCost);
				}
				else if(sGetRowName.equals("Total:"))
				{
					System.out.println("Total : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "Total", sGetCost);
				}
				else
				{
					System.out.println("Extra Pricing is present");
				}
			}
		}
		else
		{
			Assert.fail("Any one of these shipping,handling ,taxes is not enabled");
		}
	}

	//This Method is to fetch subtotal, shipping,handling ,taxes and total price from confrimation page
	public void FetchPricingFromConfirmationPage_1(String sScenario,String sTestCaseName[])
	{
		System.out.println("Fetch subtotal, shipping,handling ,taxes and total price from confrimation page");
		int rowcount = _driver.findElements(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr")).size();
		System.out.println("row count is "+rowcount);
		if(rowcount>=5)
		{
			for(int i =1;i<=rowcount;i++)
			{
				String sGetRowName = _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[1]/span")).getText();
				sGetRowName=sGetRowName.trim();
				System.out.println("row name is "+sGetRowName);

				String sGetCost= _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[2]/span")).getText();
				sGetCost=sGetCost.trim();
				System.out.println("row Cost is "+sGetCost);


				if(sGetRowName.equals("Subtotal:"))
				{
					System.out.println("Subtotal : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName[i], "SubTotal", sGetCost);
				}
				else if(sGetRowName.equals("Shipping:"))
				{
					System.out.println("Shipping : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName[i], "ShippingAmount", sGetCost);
				}
				else if(sGetRowName.equals("Handling:"))
				{
					System.out.println("Handling : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName[i], "HandlingAmount", sGetCost);
				}
				else if(sGetRowName.equals("Taxes:"))
				{
					System.out.println("Taxes : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName[i], "TaxAmount", sGetCost);
				}
				else if(sGetRowName.equals("Total:"))
				{
					System.out.println("Total : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName[i], "Total", sGetCost);
				}
				else
				{
					System.out.println("Extra Pricing is present");
				}
			}
		}
		else
		{
			Assert.fail("Any one of these shipping,handling ,taxes is not enabled");
		}
	}

	//This Method is to fetch subtotal, handling ,taxes and total price from confrimation page
	public void FetchPricing_WithoutShipping(String sScenario,String sTestCaseName)
	{
		System.out.println("Fetch subtotal, shipping,handling ,taxes and total price from confrimation page");
		int rowcount = _driver.findElements(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr")).size();
		System.out.println("row count is "+rowcount);
		if(rowcount>=4)
		{
			for(int i =1;i<=rowcount;i++)
			{
				String sGetRowName = _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[1]/span")).getText();
				sGetRowName=sGetRowName.trim();
				System.out.println("row name is "+sGetRowName);

				String sGetCost= _driver.findElement(By.xpath("//table[@id='ctl00_C_ShoppingCartOrderTotals1_GridViewTotals']/tbody/tr["+i+"]/td[2]/span")).getText();
				sGetCost=sGetCost.trim();
				System.out.println("row Cost is "+sGetCost);


				if(sGetRowName.equals("Subtotal:"))
				{
					System.out.println("Subtotal : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "SubTotal", sGetCost);
				}
				else if(sGetRowName.equals("Handling:"))
				{
					System.out.println("Handling : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "HandlingAmount", sGetCost);
				}
				else if(sGetRowName.equals("Taxes:"))
				{
					System.out.println("Taxes : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "TaxAmount", sGetCost);
				}
				else if(sGetRowName.equals("Total:"))
				{
					System.out.println("Total : "+sGetCost);
					dbConnection.UpdateFunction("DSF", sScenario, sTestCaseName, "Total", sGetCost);
				}
				else
				{
					System.out.println("Extra Pricing is present");
				}
			}
		}
		else
		{
			Assert.fail("Any one of these handling ,taxes is not enabled");
		}
	}


	//This method is to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab
	public boolean Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab(String sJobValue,String sTax,String sShipping,String sHandling) throws Exception
	{

		boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
		System.out.println("Click on Billing Info Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
		Thread.sleep(2000);
		//============================Fetch Job Value===========================================//
		System.out.println("Fetch Job Value");
		String sFetchJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
		sFetchJobValue=sFetchJobValue.trim();
		System.out.println("Actual  Job Value is "+sFetchJobValue);
		if(sFetchJobValue.equals(sJobValue))
		{
			System.out.println("Actual Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are same");
			sJobValueFlag = true;
		}
		else
		{
			System.err.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are not same");
		}
//		//============================Fetch W2P Tax Amount===========================================//	
//		System.out.println("Fetch Tax Amount");
//		String sFetchTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
//		sFetchTaxAmount=sFetchTaxAmount.trim();
//		System.out.println("Actual  Tax Amount is "+sFetchTaxAmount);
//		if(sFetchTaxAmount.equals(sTax))
//		{
//			System.out.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are same");
//			sTaxFlag = true;
//		}
//		else
//		{
//			System.err.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are not same");
//		}	
//		//============================Fetch W2P Shipping Amount===========================================//
//		System.out.println("Fetch Shipping Amount");
//		String sFetchShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
//		sFetchShippingAmount=sFetchShippingAmount.trim();
//		System.out.println("Actual  Shipping Amount is "+sFetchShippingAmount);
//		if(sFetchShippingAmount.equals(sShipping))
//		{
//			System.out.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are same");
//			sShippingFlag = true;
//		}
//		else
//		{
//			System.err.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are not same");
//		}
//		//============================Fetch W2P Handling Amount===========================================//	
//		System.out.println("Fetch Handling Amount");
//		String sFetchHandlingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount')]/following-sibling::div/div")).getText();
//		sFetchHandlingAmount=sFetchHandlingAmount.trim();
//		System.out.println("Actual  Handling Amount is "+sFetchHandlingAmount);
//		if(sFetchHandlingAmount.equals(sHandling))
//		{
//			System.out.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sHandling +" are same");
//			sHandlingFlag = true;
//		}
//		else
//		{
//			System.err.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sHandling +" are not same");
//		}
//		//======================================Verify all Flags are True==================================//
		if(sJobValueFlag==true)
		{
			return true;
		}
		else
		{
			System.err.println("sJobValueFlag is "+sJobValueFlag+" sTaxFlag is "+sTaxFlag+" sShippingFlag is "+sShippingFlag+" sHandlingFlag is "+sHandlingFlag);
			return false;
		}		
	}

	
	
	public boolean Verify_JobValue_Shipping_Handling_Taxes_Amount_On_Job_Billing_Tab_PS(String sJobValue,String sTax,String sShipping,String sHandling) throws Exception
	{

		boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
		System.out.println("Click on Billing Info Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
		Thread.sleep(2000);
		//============================Fetch Job Value===========================================//
		System.out.println("Fetch Job Value");
		String sFetchJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
		sFetchJobValue=sFetchJobValue.trim();
		System.out.println("Actual  Job Value is "+sFetchJobValue);
		if(sFetchJobValue.equals(sJobValue))
		{
			System.out.println("Actual Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are same");
			sJobValueFlag = true;
		}
		else
		{
			System.err.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are not same");
		}
		//============================Fetch W2P Tax Amount===========================================//	
		System.out.println("Fetch Tax Amount");
		String sFetchTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
		sFetchTaxAmount=sFetchTaxAmount.trim();
		System.out.println("Actual  Tax Amount is "+sFetchTaxAmount);
		if(sFetchTaxAmount.equals(sTax))
		{
			System.out.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are same");
			sTaxFlag = true;
		}
		else
		{
			System.err.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are not same");
		}	
		//============================Fetch W2P Shipping Amount===========================================//
		System.out.println("Fetch Shipping Amount");
		String sFetchShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
		sFetchShippingAmount=sFetchShippingAmount.trim();
		System.out.println("Actual  Shipping Amount is "+sFetchShippingAmount);
		if(sFetchShippingAmount.equals(sShipping))
		{
			System.out.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are same");
			sShippingFlag = true;
		}
		else
		{
			System.err.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are not same");
		}
		//============================Fetch W2P Handling Amount===========================================//	
		System.out.println("Fetch Handling Amount");
		String sFetchHandlingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount')]/following-sibling::div/div")).getText();
		sFetchHandlingAmount=sFetchHandlingAmount.trim();
		System.out.println("Actual  Handling Amount is "+sFetchHandlingAmount);
		if(sFetchHandlingAmount.equals(sHandling))
		{
			System.out.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sHandling +" are same");
			sHandlingFlag = true;
		}
		else
		{
			System.err.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sHandling +" are not same");
		}
		//======================================Verify all Flags are True==================================//
		if(sJobValueFlag==true && sTaxFlag==true && sShippingFlag==true && sHandlingFlag==true)
		{
			return true;
		}
		else
		{
			System.err.println("sJobValueFlag is "+sJobValueFlag+" sTaxFlag is "+sTaxFlag+" sShippingFlag is "+sShippingFlag+" sHandlingFlag is "+sHandlingFlag);
			return false;
		}
	}

	
	//This method is to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes AMount in Billing Tab
		public boolean Verify_JobValue_Shipping_Taxes_Amount_On_Job_Billing_Tab(String sJobValue,String sTax,String sShipping) throws Exception
		{

			boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false;
			System.out.println("Click on Billing Info Tab");
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
			Thread.sleep(2000);
			//============================Fetch Job Value===========================================//
			System.out.println("Fetch Job Value");
			String sFetchJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
			sFetchJobValue=sFetchJobValue.trim();
			System.out.println("Actual  Job Value is "+sFetchJobValue);
			if(sFetchJobValue.equals(sJobValue))
			{
				System.out.println("Actual Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are same");
				sJobValueFlag = true;
			}
			else
			{
				System.err.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sJobValue +" are not same");
			}
			//============================Fetch W2P Tax Amount===========================================//	
			System.out.println("Fetch Tax Amount");
			String sFetchTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
			sFetchTaxAmount=sFetchTaxAmount.trim();
			System.out.println("Actual  Tax Amount is "+sFetchTaxAmount);
			if(sFetchTaxAmount.equals(sTax))
			{
				System.out.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are same");
				sTaxFlag = true;
			}
			else
			{
				System.err.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sTax +" are not same");
			}	
			//============================Fetch W2P Shipping Amount===========================================//
			System.out.println("Fetch Shipping Amount");
			String sFetchShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
			sFetchShippingAmount=sFetchShippingAmount.trim();
			System.out.println("Actual  Shipping Amount is "+sFetchShippingAmount);
			if(sFetchShippingAmount.equals(sShipping))
			{
				System.out.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are same");
				sShippingFlag = true;
			}
			else
			{
				System.err.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sShipping +" are not same");
			}
			//============================Fetch W2P Handling Amount===========================================//	
			System.out.println("Fetch Handling Amount");
			String sFetchHandlingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount')]/following-sibling::div/div")).getText();
			sFetchHandlingAmount=sFetchHandlingAmount.trim();
			System.out.println("Actual  Handling Amount is "+sFetchHandlingAmount);
			
			//======================================Verify all Flags are True==================================//
			if(sJobValueFlag==true && sTaxFlag==true && sShippingFlag==true )
			{
				return true;
			}
			else
			{
				System.err.println("sJobValueFlag is "+sJobValueFlag+" sTaxFlag is "+sTaxFlag+" sShippingFlag is "+sShippingFlag);
				return false;
			}
		}


	
	//This method is to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab
	public boolean Verify_JobValue_Shipping_Handling_Taxes_Field_Is_Not_Editable_On_Job_Billing_Tab() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
		System.out.println("Click on Billing Info Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
		Thread.sleep(2000);
		//============================Fetch Job Value===========================================//
		System.out.println("Fetch Job Value");
		String sFetchJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
		sFetchJobValue=sFetchJobValue.trim();
		System.out.println("Actual  Job Value is "+sFetchJobValue);

		System.out.println("Edit Job Value");
		_driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).sendKeys("111");
		DC.Update();

		String sAfterEditingJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
		sAfterEditingJobValue=sAfterEditingJobValue.trim();
		System.out.println("Actual  Job Value is "+sAfterEditingJobValue);

		if(sFetchJobValue.equals(sAfterEditingJobValue))
		{
			System.out.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sAfterEditingJobValue +" are same");
			sJobValueFlag = true;
		}
		else
		{
			System.err.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sAfterEditingJobValue +" are not same");
		}
		//============================Fetch W2P Tax Amount===========================================//	
		System.out.println("Fetch Tax Amount");
		String sFetchTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
		sFetchTaxAmount=sFetchTaxAmount.trim();
		System.out.println("Actual  Tax Amount is "+sFetchTaxAmount);


		System.out.println("Edit Tax Amount");
		_driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).sendKeys("222");
		DC.Update();

		String sAfterEditingTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
		sAfterEditingTaxAmount=sAfterEditingTaxAmount.trim();
		System.out.println("Actual  Tax Amount is "+sAfterEditingTaxAmount);


		if(sFetchTaxAmount.equals(sAfterEditingTaxAmount))
		{
			System.out.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sAfterEditingTaxAmount +" are same");
			sTaxFlag = true;
		}
		else
		{
			System.err.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sAfterEditingTaxAmount +" are not same");
		}	
		//============================Fetch W2P Shipping Amount===========================================//
		System.out.println("Fetch Shipping Amount");
		String sFetchShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
		sFetchShippingAmount=sFetchShippingAmount.trim();
		System.out.println("Actual  Shipping Amount is "+sFetchShippingAmount);

		System.out.println("Edit Shipping  Amount");
		_driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).sendKeys("333");
		DC.Update();

		String sAfterEditingShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
		sAfterEditingShippingAmount=sAfterEditingShippingAmount.trim();
		System.out.println("Actual  Editing Shipping Amount is "+sAfterEditingShippingAmount);

		if(sFetchShippingAmount.equals(sAfterEditingShippingAmount))
		{
			System.out.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sAfterEditingShippingAmount +" are same");
			sShippingFlag = true;
		}
		else
		{
			System.err.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sAfterEditingShippingAmount +" are not same");
		}
		//============================Fetch W2P Handling Amount===========================================//	
		System.out.println("Fetch Handling Amount");
		String sFetchHandlingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount')]/following-sibling::div/div")).getText();
		sFetchHandlingAmount=sFetchHandlingAmount.trim();
		System.out.println("Actual  Handling Amount is "+sFetchHandlingAmount);

		System.out.println("Edit Handling  Amount");
		_driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount'')]/following-sibling::div/div")).sendKeys("444");
		DC.Update();

		String sAfterEditingHandlingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Handling Amount'')]/following-sibling::div/div")).getText();
		sAfterEditingHandlingAmount=sAfterEditingHandlingAmount.trim();
		System.out.println("Actual Handling Amount is "+sAfterEditingHandlingAmount);



		if(sFetchHandlingAmount.equals(sAfterEditingHandlingAmount))
		{
			System.out.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sAfterEditingHandlingAmount +" are same");
			sHandlingFlag = true;
		}
		else
		{
			System.err.println("Actaul Handling Amount "+sFetchHandlingAmount+" Expected Handling Amount "+sAfterEditingHandlingAmount +" are not same");
		}

		//======================================Verify all Flags are True==================================//
		if(sJobValueFlag==true && sTaxFlag==true && sShippingFlag==true && sHandlingFlag==true)
		{
			return true;
		}
		else
		{
			System.err.println("sJobValueFlag is "+sJobValueFlag+" sTaxFlag is "+sTaxFlag+" sShippingFlag is "+sShippingFlag+" sHandlingFlag is "+sHandlingFlag);
			return false;
		}
	}
	
	
	//This method is to Verify Job Value,W2P Shipping,W2P Handling and W2P Taxes field is not editable in Billing Tab
		public boolean Verify_JobValue_Shipping_Taxes_Field_Is_Not_Editable_On_Job_Billing_Tab() throws Exception
		{
			DCPage DC = new DCPage(_driver);
			boolean sJobValueFlag = false,sTaxFlag=false,sShippingFlag=false,sHandlingFlag=false;
			System.out.println("Click on Billing Info Tab");
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
			Thread.sleep(2000);
			//============================Fetch Job Value===========================================//
			System.out.println("Fetch Job Value");
			String sFetchJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
			sFetchJobValue=sFetchJobValue.trim();
			System.out.println("Actual  Job Value is "+sFetchJobValue);

			System.out.println("Edit Job Value");
			_driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).sendKeys("111");
			DC.Update();

			String sAfterEditingJobValue = _driver.findElement(By.xpath("//div/h4[contains(label,'Job Value')]/following-sibling::div/div")).getText();
			sAfterEditingJobValue=sAfterEditingJobValue.trim();
			System.out.println("Actual  Job Value is "+sAfterEditingJobValue);

			if(sFetchJobValue.equals(sAfterEditingJobValue))
			{
				System.out.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sAfterEditingJobValue +" are same");
				sJobValueFlag = true;
			}
			else
			{
				System.err.println("Actaul Job Value "+sFetchJobValue+" Expected job Value "+sAfterEditingJobValue +" are not same");
			}
			//============================Fetch W2P Tax Amount===========================================//	
			System.out.println("Fetch Tax Amount");
			String sFetchTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
			sFetchTaxAmount=sFetchTaxAmount.trim();
			System.out.println("Actual  Tax Amount is "+sFetchTaxAmount);


			System.out.println("Edit Tax Amount");
			_driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).sendKeys("222");
			DC.Update();

			String sAfterEditingTaxAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Tax Amount')]/following-sibling::div/div")).getText();
			sAfterEditingTaxAmount=sAfterEditingTaxAmount.trim();
			System.out.println("Actual  Tax Amount is "+sAfterEditingTaxAmount);


			if(sFetchTaxAmount.equals(sAfterEditingTaxAmount))
			{
				System.out.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sAfterEditingTaxAmount +" are same");
				sTaxFlag = true;
			}
			else
			{
				System.err.println("Actaul Tax Amount "+sFetchTaxAmount+" Expected Tax Amount "+sAfterEditingTaxAmount +" are not same");
			}	
			//============================Fetch W2P Shipping Amount===========================================//
			System.out.println("Fetch Shipping Amount");
			String sFetchShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
			sFetchShippingAmount=sFetchShippingAmount.trim();
			System.out.println("Actual  Shipping Amount is "+sFetchShippingAmount);

			System.out.println("Edit Shipping  Amount");
			_driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).sendKeys("333");
			DC.Update();

			String sAfterEditingShippingAmount = _driver.findElement(By.xpath("//div/h4[contains(label,'W2P Shipping Amount')]/following-sibling::div/div")).getText();
			sAfterEditingShippingAmount=sAfterEditingShippingAmount.trim();
			System.out.println("Actual  Editing Shipping Amount is "+sAfterEditingShippingAmount);

			if(sFetchShippingAmount.equals(sAfterEditingShippingAmount))
			{
				System.out.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sAfterEditingShippingAmount +" are same");
				sShippingFlag = true;
			}
			else
			{
				System.err.println("Actaul Shipping Amount "+sFetchShippingAmount+" Expected Shipping Amount "+sAfterEditingShippingAmount +" are not same");
			}
			
			//======================================Verify all Flags are True==================================//
			if(sJobValueFlag==true && sTaxFlag==true && sShippingFlag==true)
			{
				return true;
			}
			else
			{
				System.err.println("sJobValueFlag is "+sJobValueFlag+" sTaxFlag is "+sTaxFlag+" sShippingFlag is "+sShippingFlag+" sHandlingFlag is "+sHandlingFlag);
				return false;
			}
		}

	//This Functions navigates to Job type details page
	// Click on Job Type Billing option
	// selects Use Quoted Price as Line Item Price Options
	public void Navigate_To_JobType_Details_Page_From_Job_Detail_Page(String sJobNumber,String sLineItemPriceOption) throws Exception
	{
		System.out.println("Click on Job Type/ Sub Job Type Magnifying glass ");

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sSelectedJobType = CommonFunctions.GetSelectedOption(_driver, By.name("jobType"));
		Thread.sleep(1000);
		System.out.println("sSelectedJobType is "+sSelectedJobType);
		CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Job Type / Sub Job Type')]/following-sibling::div/a[1]/div"));
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("JobType: "+sSelectedJobType)) 
				{
					System.out.println("Click on Job Type Billing option");
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Type Billing Options']"));
					Thread.sleep(1000);

					System.out.println("Select/set Line Item Price Options as  'Use Quoted Price '");
					CommonFunctions.selectDropdownByText(_driver, By.name("lineItemPriceOptions"), sLineItemPriceOption);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(5000);


					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJobNumber);	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Job "+sJobNumber);

				}

			}
		}

	}

	//This TestCase will navigate to specified paceconnect id
	public void NavigateToPaceConnect(String paceconnectID) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PaceConnect/detail/"+paceconnectID);
		Thread.sleep(5000);
		System.out.println("**Navigating to the PaceConnect having id as: "+paceconnectID);
	}


	// This testcase will Navigate to DSF Connect Setup tab in Paceconnect detail page
	// set Estimated Shipping Invoice Extra Type to  "Shipping" and Handling Invoice Extra Type to "Handling"
	public void SetEstimateShippingInvoiceExtraTypeAndHandlingInvoiceInvoiceExtraType(String sEstShipping,String sEstHandling) throws Exception
	{
		System.out.println("CLick on DSF Connect Setup tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='DSF Connect Setup']"));
		Thread.sleep(2000);

		System.out.println("Set Estimated Shipping Invoice Extra Type to  Shipping ");
		CommonFunctions.selectDropdownByText(_driver, By.name("estimatedShippingInvoiceExtraType"), sEstShipping);
		Thread.sleep(1000);

		System.out.println("Set Estimated Handling Invoice Extra Type to Handling ");
		CommonFunctions.selectDropdownByText(_driver, By.name("handlingInvoiceExtraType"), sEstHandling);
		Thread.sleep(1000);

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(2000);
	}

	//This method is to Navigate to Billing Info and enable Invoice W2P Order Amount ,tax, shipping and handling Amount
	public void Enable_Invoice_W2P_Order_Tax_Shipping_And_Handling_Amount(boolean sOrderFlag,boolean sTaxFlag,boolean sShippingFlag,boolean sHandlingFlag) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Click on Billing Info Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
		Thread.sleep(2000);
		System.out.println("Enable Invoice W2P Order Amount ");
		PO.sSelectCheckBox(sOrderFlag,By.name("invoiceW2POrderAmountBooleanCheck"));

		System.out.println("Enable Invoice W2P tax Amount ");
		PO.sSelectCheckBox(sOrderFlag,By.name("invoiceW2PTaxAmountBooleanCheck"));

		System.out.println("Enable Invoice W2P shipping Amount ");
		PO.sSelectCheckBox(sOrderFlag,By.name("invoiceW2PShippingAmountBooleanCheck"));

		System.out.println("Enable Invoice W2P handling Amount ");
		PO.sSelectCheckBox(sOrderFlag,By.name("invoiceW2PHandlingAmountBooleanCheck"));

		System.out.println("Click on Update");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(5000);
	}

	//This TestCase will navigate to Add invoice
	public void NavigateToAddInvoice() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Invoice/add");
		Thread.sleep(5000);
		System.out.println("**Navigating to add invoice page");
		assertEquals("Adding Invoice Transaction",_driver.getTitle());

	}


	//This TestCase will navigate to Add Invoice Batch
	public void NavigateToAddInvoiceBatch() throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InvoiceBatch/add");
		Thread.sleep(5000);
		System.out.println("**Navigating to add invoice page");
		assertEquals("Adding Batch",_driver.getTitle());

	}


	//This Function is to add invoice batch details
	public String EnterInvoiceBatchDetails(String sDescription,boolean sApproved) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Enter Job");
		CommonFunctions.SendValue(_driver, By.name("description"),sDescription);
		Thread.sleep(2000);
		System.out.println("Approve Batch "+sApproved);
		PO.sSelectCheckBox(sApproved, By.name("approvedBooleanCheck"));

		System.out.println("Click on Add");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		Thread.sleep(5000);

		String sGetURL = _driver.getCurrentUrl();
		System.out.println("sGetURL is "+sGetURL);
		String sID = sGetURL.replace("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InvoiceBatch/detail/", "");
		sID = sID.substring(0, 4);
		System.out.println("sID is "+sID);
		return sID;
	}
	//This testcase is to  enter Invoice details
	public boolean  EnterInvoiceDetails(String sJob,String sBatch,String sTaxDistributionMethod,boolean sPartialBill) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Enter Job");
		CommonFunctions.SendValue(_driver, By.xpath("//input[contains(@name,'job')]"),sJob);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.getPopupMessage(_driver);
		
		Thread.sleep(1000);
		if (CommonFunctions.isElementPresent(_driver,By.name(Locators.getProperty(Locators.Job_Part_Key))))
		{CommonFunctions.selectDropdown(_driver,By.name(Locators.getProperty(Locators.Job_Part_Key)), sJob+":01");}
		
		if(_driver.findElements(By.name("invoiceBatch")).size()>0)
		{
			System.err.println("sBatch is: " +sBatch);
			CommonFunctions.selectDropdownByText(_driver, By.name("invoiceBatch"),sBatch);
			Thread.sleep(1000);
		}
		else if(_driver.findElements(By.name("invoiceBatch2")).size()>0)
		{
			System.err.println("sBatch is: " +sBatch);
			CommonFunctions.selectDropdownByText(_driver, By.name("invoiceBatch2"),sBatch);
			Thread.sleep(1000);
		}

		if(_driver.findElements(By.name("taxDistributionMethod")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("taxDistributionMethod"),sTaxDistributionMethod);
			Thread.sleep(1000);
		}

		CommonFunctions.SendValue(_driver, By.name("percentWipToRelieve"),"0%");
		System.out.println("Click on Add");

		PO.sSelectCheckBox(sPartialBill, By.name("partiallyBillBooleanCheck"));
		if(sPartialBill==true)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("closeJob"), "Never");
			Thread.sleep(1000);
		}
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		Thread.sleep(5000);

		boolean sInvoiceAdded = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		System.out.println("sInvoiceAdded ="+sInvoiceAdded);
		return sInvoiceAdded;
	}

	//This Function is to Create invoice
	public boolean CreateInvoiceForJob(String sBatch,boolean sApproved,String sJob,String sTaxDistributionMethod,boolean PartialBill) throws Exception
	{
		System.out.println("Navigate to Add Invoice Batch");
		NavigateToAddInvoiceBatch();

		System.out.println("Enter Invoice Batch Details");
		String sID = EnterInvoiceBatchDetails(sBatch,sApproved);
		String sInvoiceBatch = sID+"-"+sBatch;
		System.out.println("sInvoiceBatch is: " +sInvoiceBatch);


		System.out.println("Navigate to Add Invoice ");
		NavigateToAddInvoice();

		System.out.println("Navigate to Add Invoice ");
		boolean sInvoiceCreated = EnterInvoiceDetails(sJob,sInvoiceBatch,sTaxDistributionMethod,PartialBill);
		return sInvoiceCreated;
	}

	//This Function is to Verify Warning Mesage when "auto calculate" method is set Tax distribution method 
	public boolean Verify_Warning_Message_When_Auto_Calculate_Method_Is_Set_Tax_Distribution_Method() throws Exception
	{
		String sWarningMessage = CommonFunctions.GetText(_driver, By.xpath("//div[@id='fmessage']/ul/li[1]"));
		if(sWarningMessage.contains("The web to print calculated sales tax amount of ") && sWarningMessage.contains("does not match the Pace calculated sales tax amount of $"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//This Function is to verify Order ,tax,shipping and handling amount in invoice
	public boolean Verify_Order_Tax_Shipping_Hnadling_Amount_On_Invoice_Page(String sJobValue,String sJShipping,String sJHandling,String sJTax) throws Exception
	{
		System.out.println("Fetch Total Amount");
		String sTotal  = CommonFunctions.GetText(_driver, By.xpath("//table[@id='InvoiceLine_N101878']/tfoot/tr[1]/td[8]"));
		sTotal=sTotal.trim();
		String[] sTotalSplit = sTotal.split(":");
		System.out.println("Actual sTotal Amount is "+sTotalSplit[1]+" Expected Total Amount is "+sJobValue);
		assertEquals(sTotalSplit[1],sJobValue);

		System.out.println("Fetch Shipping Amount");
		String sShipping  = CommonFunctions.GetValue(_driver, By.xpath("//table[@id='InvoiceExtra_N101B2']/tbody/tr[2]/td[4]/input"));
		sShipping=sShipping.trim();
		System.out.println("Actual Shipping Amount is "+sShipping+" Expected Shipping  Amountis "+sJShipping);
		assertEquals(sShipping,sJShipping);

		System.out.println("Fetch Handling Amount");
		String sHandling  = CommonFunctions.GetValue(_driver, By.xpath("//table[@id='InvoiceExtra_N101B2']/tbody/tr[1]/td[4]/input"));
		sHandling=sHandling.trim();
		System.out.println("Actual Handling Amount is "+sHandling+" Expected Handling Amount is "+sJHandling);
		assertEquals(sHandling,sJHandling);

		System.out.println("Fetch Tax Amount");
		String sTax = "";
		if (sJTax.equals("$0.00"))
		{
			sTax = "Tax:$0.00";
		}
		else
		{
			sTax  = CommonFunctions.GetText(_driver, By.xpath("//table[contains(@id,'InvoiceTrnTaxDist')]/tfoot/tr[1]/td[11]"));
			//				sTax  = CommonFunctions.GetText(_driver, By.xpath("//table[contains(@id,'InvoiceTrnTaxDist')]/tfoot/tr[1]/td[9]"));
		}
		sTax=sTax.trim();
		String[] sTaxSplit = sTax.split(":");
		System.out.println("Actual Tax Amount is "+sTaxSplit[1]+" Expected Tax Amount is "+sJTax);
		assertEquals(sTaxSplit[1],sJTax);
		return true;

	}
	//This function is to search Company
	public boolean SearchCompany(String sCompany) throws Exception
	{
		System.out.println("Search for comapny "+sCompany);
		CommonFunctions.SendValue(_driver, By.name("ctl00$ctl00$C$M$TextBoxSearch"), sCompany);
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.name("ctl00$ctl00$C$M$ButtonSearch"));
		Thread.sleep(3000);	
		System.out.println("Click on company link");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='"+sCompany+"']"));
		Thread.sleep(3000);
		boolean sNavigatedToCompanyPage = CommonFunctions.isElementPresent(_driver, By.xpath("//span[text()='Manage Company - "+sCompany+"']"));
		return sNavigatedToCompanyPage;
	}	

	public void EnterCostCenterToCompany(String sCompany,String sAccountNum) throws Exception
	{
		boolean sFound = SearchCompany(sCompany);
		if(sFound==true)
		{
			System.out.println("Click on Cost center View/manage link");
			CommonFunctions.ClickElement(_driver, By.id("ctl00_ctl00_C_M_LinkButtonManageCostCenters"));
			Thread.sleep(2000);

			System.out.println("Click on Add Cost Center");
			CommonFunctions.ClickElement(_driver, By.id("ctl00_ctl00_C_M_ButtonAddCostCenter"));
			Thread.sleep(2000);
			System.out.println("Enter Account Number");
			CommonFunctions.SendValue(_driver, By.id("ctl00_ctl00_C_M_ModifyBillingEntityControl_billingEntityInformationControl_textBoxAccountNumber"), sAccountNum);
			Thread.sleep(1000);
			CommonFunctions.ClickElement(_driver, By.id("ctl00_ctl00_C_M_ModifyBillingEntityControl_ButtonSaveBillingEntity"));
			Thread.sleep(2000);

		}
		else
		{
			Assert.fail(sCompany+" Company not found");
		}

		/*
			int rowcount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllCompanies']/tbody/tr")).size();
			System.out.println("rowcount is  "+rowcount);
			for(int i =2;i<=rowcount;i++)
			{
				String sCompanyName = CommonFunctions.GetText(_driver, By.xpath("//table[@id='ctl00_ctl00_C_M_DataGridAllCompanies']/tbody/tr["+i+"]/td[2]/a"));

			}*/
	}


	//This testcase is to verify the Payment Mode in pace
	public String[] Verify_The_Payment_Mode_In_Pace() throws Exception
	{
		String sPaymentMethod="",sPaymentValue="";


		PurchasePage PO = new PurchasePage(_driver);
		System.out.println("Click on Billing Info Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Billing Info']"));
		Thread.sleep(2000);

		String sDSF_Payment_Mode = CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'DSF Payment Method')]/following-sibling::div/div"));
		sDSF_Payment_Mode=sDSF_Payment_Mode.trim();
		System.out.println("DSF Payment Mode on pace is "+sDSF_Payment_Mode);
		sPaymentMethod=sDSF_Payment_Mode;
		if(sDSF_Payment_Mode.equals("Cost Center"))
		{
			String sCharge_Back_Account = CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Charge Back Account')]/following-sibling::div/div"));
			sCharge_Back_Account=sCharge_Back_Account.trim();
			System.out.println("Charge BAck Account on pace is "+sCharge_Back_Account);
			sPaymentValue=sCharge_Back_Account;
		}
		else if(sDSF_Payment_Mode.equals("Credit Card"))
		{
			String sPayment_Authorization_Token = CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Payment Authorization Token')]/following-sibling::div/div"));
			sPayment_Authorization_Token=sPayment_Authorization_Token.trim();
			System.out.println("sPayment Authorization Token on pace is "+sPayment_Authorization_Token);
			sPaymentValue=sPayment_Authorization_Token;
		}
		else if(sDSF_Payment_Mode.equals("Accounting Codes"))
		{				
			String sorderAuthorizeDate  = CommonFunctions.GetText(_driver, By.xpath("//table[@id='JobBillingAccountingCode']/tbody/tr[1]/td[3]/div"));
			sorderAuthorizeDate=sorderAuthorizeDate.trim();
			System.out.println("Order Authorize Date on pace is "+sorderAuthorizeDate);
			sPaymentValue=sorderAuthorizeDate;
		}
		else if(sDSF_Payment_Mode.equals("Other Account"))
		{
			String sFree_Form_Account_Number = CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Free Form Account Number')]/following-sibling::div/div"));
			sFree_Form_Account_Number=sFree_Form_Account_Number.trim();
			System.out.println("Free Form Account Number on pace is "+sFree_Form_Account_Number);
			sPaymentValue=sFree_Form_Account_Number;
		}
		else if(sDSF_Payment_Mode.equals("PO Number"))
		{
			System.out.println("Payment methods is PO Number") ;
			sPaymentValue="No Value";
		}
		else if(sDSF_Payment_Mode.equals("Pay At Store"))
		{

			System.out.println("Payment method is Pay at Store") ;
			sPaymentValue="No Value";
		}
		else if(sDSF_Payment_Mode.equals("Zero Payment"))
		{
			System.out.println("Payment method is Zero Payment") ;
			sPaymentValue="No Value";
		}
		else
		{
			System.err.println("Invalid payment method") ;
			sPaymentValue="No Value";
		}
		String[] sPaymentMethod_And_Value={sPaymentMethod,sPaymentValue};
		System.out.println("Fetched Payment Method is "+sPaymentMethod_And_Value[0]+" Fetched Payment Value is "+sPaymentMethod_And_Value[1]);
		return sPaymentMethod_And_Value;
	}

	//This TestCase will navigate to Job Shipment Page
	public void NavigateToJobShipmentPage(String sJob) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobShipment/listJob/"+sJob);
		Thread.sleep(5000);
		System.out.println("**Navigate To Job Shipment Page");
		assertEquals("Job Shipments",_driver.getTitle());

	}

	//This Testcase is to verify Quote price in job shipment detail page
	public String VerifyQuotePriceInJobShipmentDetailPage() throws Exception
	{

		if(_driver.findElements(By.xpath("//a[text()='Return To Job']")).size()>0 || _driver.findElements(By.xpath("//a[text()='Add Detailed Record']")).size()>0)
		{
			System.out.println("Get on View Object img");
			/* String sOnClickID=  _driver.findElement(By.xpath("//div[@id='shipmentBox0']/div[1]/span[1]/div[1]/img[@title='View Object' and @src='/static/images/shared/ui/view.png']")).getAttribute("onclick");
			 System.out.println("sOnClickID is "+sOnClickID);
			 sOnClickID=sOnClickID.replace("goToDefaultContextLink('JobShipment', '", "");
			 sOnClickID=sOnClickID.replace("')", "");
			 sOnClickID=sOnClickID.trim();
			 System.out.println("sOnClickID is "+sOnClickID);


			 sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
			sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
			_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobShipment/detail/"+sOnClickID);		 
			Thread.sleep(2000);
			 */
			_driver.findElement(By.xpath("//div[@id='shipmentBox0']/div[1]/span[1]/div[1]/img[@title='View Object' and @src='/static/images/shared/ui/view.png']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(5000);
			CommonFunctions.Wait(_driver, By.xpath("//fieldset[@id='shipmentDetails']/legend/span"));
			System.out.println("Click on Shipment Details + image");
			CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='shipmentDetails']/legend/span"));
			Thread.sleep(2000);
		}
		else
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/div/a/img"));
			Thread.sleep(4000);
			assertEquals("Job Shipment",_driver.getTitle());

		}
		String sQuotedPrice = CommonFunctions.GetValue(_driver, By.name("quotedPrice"));
		System.out.println("sQuotedPrice is "+sQuotedPrice);
		return sQuotedPrice;
	} 

	//This Testcase is to verify Sales Tax on Job invoice and Customer 
	public boolean Verify_Sales_Tax_On_Job_Invoice_And_Customer(String sCustomer) throws Exception
	{
		String sSalesTaxOnCustomer="",sSalesTax="";
		System.out.println("Click on Invoice Info tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Invoice Info']"));
		Thread.sleep(2000);
		sSalesTax= _driver.findElement(By.xpath("//div/h4[contains(label,'Sales Tax')]/following-sibling::div/div[1]/a")).getText();
		sSalesTax=sSalesTax.trim();
		System.out.println("sSales tax  is "+ sSalesTax);


		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Customer')]/following-sibling::div/div[2]/a"));
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Customer "+sCustomer)) 
				{
					System.out.println("Click on Job Type Billing option");
					CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Tax Info']"));
					Thread.sleep(1000);

					sSalesTaxOnCustomer= CommonFunctions.GetSelectedOption(_driver, By.name("salesTax"));
					sSalesTaxOnCustomer=sSalesTaxOnCustomer.trim();
					System.out.println("sSales tax on customer page is "+ sSalesTaxOnCustomer);


					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals("Invoice Transaction");	

				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Invoice Transaction");

				}

			}
		}
		if(sSalesTax.equals(sSalesTaxOnCustomer))
		{
			System.out.println("Sales tax on Invoice Transaction page is same as Sales tax on selected customer");
			return true;
		}
		else
		{
			System.out.println("Sales tax on Invoice Transaction page is "+sSalesTax+" is not same as Sales tax on selected customer "+sSalesTaxOnCustomer);
			return false;
		}
	}

	//Navigate To Add COntact Page
	public void NavigateToAddContactPage() throws Exception, IOException
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Contact/add");
		Thread.sleep(3000);
		System.out.println(" Navigate To add contact Page");
		assertEquals("Adding Contact",_driver.getTitle());
	} 	

	//Enter Contact Details	
	public boolean EnterContactDetails(String sFirstName, String sLastName) throws Exception
	{
		CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));

		_driver.findElement(By.name("firstName")).sendKeys(sFirstName);
		_driver.findElement(By.name("lastName")).sendKeys(sLastName);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line1))).sendKeys("45");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line2))).sendKeys("WF");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_Add_Line3))).sendKeys("BNG");
		_driver.findElement(By.name("businessPhoneNumber")).sendKeys("01298567345");
		_driver.findElement(By.name("businessFaxNumber")).sendKeys("01298567345");
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_City))).sendKeys("BNG");
		_driver.findElement(By.name("email")).sendKeys("efiuser123@gmail.com");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Country)), "US - United States");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_State)), "AK - Alaska");
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Vendor_ZipCode))).sendKeys("511571");

		//			_driver.findElement(By.xpath("//a[text()='Customer/Vendor Info']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		if (CommonFunctions.isElementPresent(_driver, By.name("defaultCurrency")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("defaultCurrency"), "USD");
			Thread.sleep(1000);
		}				
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		Thread.sleep(5000);
		boolean sAddedFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sAddedFlag;
	}


	//This testcase is to verify Job shipment address
	public boolean VerifyJobShipmentAddressInJobPart(String loc,String sJobNo,String sPartNo,String sShipTo) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		boolean sShipToFlag=false;
		System.out.println("Clcik on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(3000);

		System.out.println("Clcik on job part magnifying glass ");
		CommonFunctions.ClickElement(_driver, By.xpath(loc));
		Thread.sleep(2000);

		assertEquals("Job "+sJobNo+" part "+sPartNo,_driver.getTitle());

		System.out.println("Navigate to Job Shipment page");
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobShipment/listJobPart/"+sJobNo+"%3A01");
		Thread.sleep(3000);
		System.out.println(" Navigate To Job Shipments Page");
		assertEquals("Job Shipments",_driver.getTitle());


		System.out.println("Click on Add new Record button");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Add Detailed Record']"));
		Thread.sleep(2000);

		System.out.println("Click on Shipment Address tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@id='shipmentAddress']"));
		Thread.sleep(2000);

		System.out.println("Get selected Ship To ");
		CommonFunctions.selectDropdownByText(_driver, By.name("jobContact_jobContact"), sShipTo);
		Thread.sleep(2000);
		String sActualShipTo= CommonFunctions.GetSelectedOption(_driver, By.name("jobContact_jobContact"));
		System.out.println("sActualShipTo is  "+sActualShipTo);

		if(sActualShipTo.equals(sShipTo))
		{
			sShipToFlag=true;
			System.out.println("sActualShipTo is same as sShipTo");
		}
		else
		{
			sShipToFlag=false;
			System.out.println("sActualShipTo is "+sActualShipTo+" but Expected sShipTo is "+sShipTo);
		}
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+sJobNo);
		Thread.sleep(3000);
		PO.AcceptModalDialog();

		return sShipToFlag;
	}

	//this testcase is to verify xml data
	public void DownlaodXMLFile(String sFile,String sUN,String sPWD) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		boolean sFileFlag = false;
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);

		System.out.println("Click on Successfull Executions tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Successful Executions']"));
		Thread.sleep(2000);
		int sRowcount = _driver.findElements(By.xpath("//table[@id='SuccessResults']/tbody/tr")).size();
		System.out.println("sRowcount is "+sRowcount);

		for(int i =1;i<=sRowcount;i++)
		{
			String sActaulFileName = CommonFunctions.GetText(_driver, By.xpath("//table[@id='SuccessResults']/tbody/tr["+i+"]/td[5]/div/a"));
			//System.err.println("sActaulFileName is: "+sActaulFileName+ " and sFile is: "+sFile);
			if(sActaulFileName.equals(sFile))
			{
				System.out.println("sActaulFileName is same as expected sFile");
				sFileFlag=true;

				System.out.println("get href value");
				String sGethref = _driver.findElement(By.xpath("//table[@id='SuccessResults']/tbody/tr["+i+"]/td[5]/div/a")).getAttribute("href");
				System.out.println("href is "+sGethref);

				String sXMLURL ="http://"+sSERVER+""+sGethref;
				_driver.navigate().to(sGethref);
				Thread.sleep(5000);
				String sCurrentURL= _driver.getCurrentUrl();
				System.out.println("sCurrentURL is "+sCurrentURL);


				Robot robot = new Robot();
				// press Ctrl+S the Robot's way
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_S);
				// press Enter
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);


				/*
						URL website = new URL(sCurrentURL);
						ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						FileOutputStream fos = new FileOutputStream("C:\\tmp\\"+sFile+".xml");
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);*/

				downloadFile(sCurrentURL,"C:\\tmp", sUN, sPWD);

				break;

			}
			else
			{
				System.out.println("sActaulFileName is "+sActaulFileName+" not same as expected sFile "+sFile);

			}
		}

	}

	public String SearchChargeBackAccountAndGetID(String sAccount) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String sGetURl="";
		sLogs = sLogs+"--get ID from Charge back account--";
		System.out.println("get ID from Charge back account");
		DC.Search(sAccount, "accountNumber");
		Thread.sleep(5000);

		String originalHandle = _driver.getWindowHandle();


		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		System.out.println("Clicking on magnifying glass");


		Set<String> availableWindows = _driver.getWindowHandles();		 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				if(_driver.switchTo().window(windowId).getTitle().contains("Chargeback Account")) 
				{ 
					Thread.sleep(2000);					
					sGetURl = _driver.getCurrentUrl();
					sGetURl=sGetURl.replace("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ChargeBackAccount/detail/", "");
					System.err.println("sGetURl is "+sGetURl);
					//dbConnection.UpdateFunction("DSF", "DSF", "P_3114_Prerequiste", "ChargeBackAccountID", sGetURl);

					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals("Chargeback Accounts");
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals("Chargeback Accounts");

				}
			}
		}

		return sGetURl;
	}

	public boolean ValidateXML(String sChargeBackAccountID,String sExpectedCostCenter,String sExpectedCostCenterName,String sExpectedAction) throws Exception, ParserConfigurationException, SAXException, IOException
	{

		boolean sFlag=false;
		File fXmlFile = new File("C:\\tmp\\"+sChargeBackAccountID);
		System.out.println("fXmlFile is "+fXmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("com:CostCenter");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			System.out.println(nList.item(0).getAttributes().getNamedItem("costCenterID").getNodeValue());		 
			String sActualCostCenter = nList.item(0).getAttributes().getNamedItem("costCenterID").getNodeValue();
			sActualCostCenter=sActualCostCenter.trim();

			System.out.println(nList.item(0).getAttributes().getNamedItem("costCenterName").getNodeValue());		 
			String sActualCostCenterName = nList.item(0).getAttributes().getNamedItem("costCenterName").getNodeValue();
			sActualCostCenterName=sActualCostCenterName.trim();

			System.out.println(nList.item(0).getAttributes().getNamedItem("action").getNodeValue());
			String sActualAction = nList.item(0).getAttributes().getNamedItem("action").getNodeValue();
			sActualAction=sActualAction.trim();
			if(sActualCostCenter.equals(sExpectedCostCenter) && sActualCostCenterName.equals(sExpectedCostCenterName) && sActualAction.equals(sExpectedAction) )
			{
				sFlag=true;
			}
			else
			{
				Assert.fail("sActualCostCenter "+sActualCostCenter +"is not same as expected  sExpectedCostCenter "+sExpectedCostCenter+" sActualCostCenterName "+sActualCostCenter +"is not same as expected  sExpectedCostCenter "+sExpectedCostCenterName+" sActualCostCenter "+sActualAction +"is not same as expected  sExpectedAction "+sExpectedAction);
			}
		}
		return sFlag;	
	}

	@SuppressWarnings("deprecation")
	public static void downloadFile(String fileURL, String saveDir,final String sUN,final String sPWD)
			throws IOException 
			{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://"+sSERVER+"/epace/login.html");
		HttpResponse response = null;
		java.util.List<NameValuePair> postFields = new ArrayList<NameValuePair>(2);

		// Set the post fields
		postFields.add(new BasicNameValuePair("username", sUN));
		postFields.add(new BasicNameValuePair("password", sPWD));
		post.setEntity(new UrlEncodedFormEntity(postFields, HTTP.UTF_8));

		// Execute the POST request
		response = client.execute(post);


		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication (sUN, sPWD.toCharArray());
			}
		});
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) 
		{
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null)
			{
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0)
				{
					fileName = disposition.substring(index + 10,
							disposition.length() - 1);
				}
			} 
			else 
			{
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
						fileURL.length());
			}

			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName+".xml";
			System.out.println("saveFilePath = " + saveFilePath);
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
			}

	//This Testcase is to Create Npp Product()
	public String Create_Npp_Product(String sProductName ,String sType,String sProductId,String sStorefront,String sRegularPrice,String sSetupPrice,String Width , String Length, String Height, String Qty) throws Exception
	{
		Product Pro = new Product(_driver);
		String sProductCreated = Pro.CreatePricedNPPProduct(sProductName, sType,sProductId,sStorefront,sRegularPrice,sSetupPrice,Width,Length,Height,Qty);
		return sProductCreated;
	}

	public void AddItemTemplatesToComboItemTemplate(int NoOfIT,String[] sItemTemplate) throws Exception
	{
		System.out.println("click on Item template tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Item Templates']"));
		Thread.sleep(2000);

		for(int i=1;i<=NoOfIT;i++)
		{
			System.out.println("click on Add new Record button");
			_driver.findElement(By.xpath("//fieldset[@id='ItemTemplateComboLink_fieldset']/div[1]/div[1]/div[2]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(1000);
			String j =""+i;
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ItemTemplateComboLink']/tbody/tr["+i+"]/td[2]/input"), j);
			// CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ItemTemplateComboLink']/tbody/tr["+i+"]/td[3]/input"), j);
			CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='ItemTemplateComboLink']/tbody/tr["+i+"]/td[4]/select"), sItemTemplate[i-1]);
			Thread.sleep(1000);
		}

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(2000);

	}


	public boolean VerifyItemTemplateInEachJobPart(String sItemTempalte) throws Exception
	{
		boolean sFlag=false;
		System.out.println("Verify Item Template In Each Job Part");
		String sCurrectURl= _driver.getCurrentUrl();
		int rowCount = _driver.findElements(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr")).size();
		System.out.println("RowCount is "+rowCount); 


		for(int i =2;i<=rowCount;i++)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(3000);
			int j=i-1;
			System.out.println("Click on "+j+" magnifying glass");
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr["+i+"]/td[3]/div/a/img"));
			Thread.sleep(2000);


			int sizeJobType = _driver.findElements(By.name("jobType")).size();

			if(sizeJobType>0)
			{
				String sJobType = CommonFunctions.GetSelectedOption(_driver, By.name("jobType"));
				sJobType=sJobType.trim();
				System.out.println("jobType is "+sJobType);
				if(sJobType.equals("Kit Job"))
				{
					String sItemTemplateOnPAce= CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Item Template')]/following-sibling::div/div[1]"));
					sItemTemplateOnPAce=sItemTemplateOnPAce.trim();
					System.out.println("sItem Template On Pace is "+sItemTemplateOnPAce);
					if(sItemTemplateOnPAce.equals(""))
					{
						sFlag=true;
					}
					else
					{
						Assert.fail("Item template on pace "+sItemTemplateOnPAce+" is not same as item template on DSF "+sItemTempalte);
					}

				}
				else
				{
					String sItemTemplateOnPAce= CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Item Template')]/following-sibling::div/div[1]"));
					sItemTemplateOnPAce=sItemTemplateOnPAce.trim();
					System.out.println("sItem Template On PAce is "+sItemTemplateOnPAce);
					if(sItemTemplateOnPAce.equals(sItemTempalte))
					{
						sFlag=true;
					}
					else
					{
						Assert.fail("Item template on pace "+sItemTemplateOnPAce+" is not same as item template on DSF "+sItemTempalte);
					}
				}

			}
			else
			{
				String sItemTemplateOnPAce= CommonFunctions.GetText(_driver, By.xpath("//div/h4[contains(label,'Item Template')]/following-sibling::div/div[1]"));
				sItemTemplateOnPAce=sItemTemplateOnPAce.trim();
				System.out.println("sItem Template On PAce is "+sItemTemplateOnPAce);
				if(sItemTemplateOnPAce.equals(sItemTempalte))
				{
					sFlag=true;
				}
				else
				{
					Assert.fail("Item template on pace "+sItemTemplateOnPAce+" is not same as item template on DSF "+sItemTempalte);
				}
			}



			_driver.get(sCurrectURl);
			Thread.sleep(5000);
		}
		return sFlag;

	}

	public void ChangePaceConnectHost(String sIPAddr) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Outputs']"));
		Thread.sleep(2000);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(" Drill into the EFI Connector dsfepace");
		_driver.findElement(By.xpath("//table[@id='OutputsMessage']/tbody/tr/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Pace Connect Location")) 
				{

					String  originalHandle1 = _driver.getWindowHandle();
					System.out.println(originalHandle1);
					String sWindowTitle1 =_driver.getTitle();
					System.out.println(" Drill into the EFI Connector dsfepace");
					_driver.findElement(By.xpath("//div/h4[contains(label,'Network Location')]/following-sibling::div/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
					Thread.sleep(2000);
					Set<String> availableWindow1 = _driver.getWindowHandles();
					if (!availableWindow1.isEmpty()) 
					{
						for (String windowId1 : availableWindow1) 
						{	
							if(_driver.switchTo().window(windowId1).getTitle().equals("Network Location")) 
							{

								CommonFunctions.SendValue(_driver, By.name("host"), sIPAddr);
								CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
								Thread.sleep(2000);
								_driver.switchTo().window(originalHandle1);
							} 
							else 
							{
								_driver.switchTo().window(originalHandle1);
							}

						}
					}

					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
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

	public void AddThumbnail(String[] sThumbnails,int sImages,String[] sExpectedImage) throws Exception
	{
		int j =0;
		System.out.println("Click on images tab");
		_driver.findElement(By.xpath("//a[text()='mages']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		for(int i=0;i<sImages;i++)
		{
			j=i+1;
			_driver.findElement(By.name("thumbnail_"+i)).sendKeys(sThumbnails[i]);
			Thread.sleep(3000);
			String sUploadedImage = _driver.findElement(By.xpath("//div[@id='_thumbnail_files_list']/div["+j+"]/div[1]/span")).getText();
			sUploadedImage=sUploadedImage.trim();
			System.out.println("sUploadedImage is "+sUploadedImage);
			if(sUploadedImage.equals(sExpectedImage[i]))
			{
				System.out.println("Image uploaded");
			}
			else
			{
				Assert.fail("Failed to upload image");
			}
		}

	}
	public void AddDocument(String[] sDocuments,int sImages,String[] sExpectedImage) throws Exception
	{
		int j =0;
		System.out.println("Click on images tab");
		_driver.findElement(By.xpath("//a[text()='mages']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		for(int i=0;i<sImages;i++)
		{
			j=i+1;
			_driver.findElement(By.name("document_"+i)).sendKeys(sDocuments[i]);
			Thread.sleep(3000);
			String sUploadedImage = _driver.findElement(By.xpath("//div[@id='_document_files_list']/div["+j+"]/div[1]/span")).getText();
			sUploadedImage=sUploadedImage.trim();
			System.out.println("sUploadedImage is "+sUploadedImage);
			if(sUploadedImage.equals(sExpectedImage[i]))
			{
				System.out.println("Image uploaded");
			}
			else
			{
				Assert.fail("Failed to upload image");
			}
		}

	}

	public void AddNewInventoryTransaction(String sTransactionType,String sInvItem,String sQty) throws Exception
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

	public boolean VerifyKitJobPartDetails(String sNppProduct, String sStaticProduct, String qtyOrderedforJobComp, String filesinPaceConnect, String jobProductKitJob, String jobTypeKitJob, String qtyToManufacture) throws Exception
	{

		String itemTemplate = null;
		String jobProduct = null;
		String qtyToMfg = null;
		String productName = null;
		boolean jobPartDetails_1 = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String jobComponent = null;
		String jobComponent1 = null;
		String qtyOrderedJobComp = null; 
		String qtyOrderedJobComp1 = null; 
		String paceConnectFiles = "";

		System.out.println("*********Verify 1st Job Part********");
		_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);

		System.out.println("sNppProduct inside VerifyKitJobPartDetails: "+sNppProduct);
		System.out.println("sStaticProduct inside VerifyKitJobPartDetails: "+sStaticProduct);

		jobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();
		String jobType = CommonFunctions.GetSelectedOption(_driver, By.name("jobType"));
		qtyToMfg = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("jobProduct: "+jobProduct);
		System.out.println("jobType: "+jobType);
		System.out.println("qtyToMfg: "+qtyToMfg);

		System.out.println("sNppProduct inside VerifyKitJobPartDetails: "+sNppProduct);
		System.out.println("sStaticProduct inside VerifyKitJobPartDetails: "+sStaticProduct);

		//ItemTemplate
		itemTemplate = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[3]/div[1]/div/div")).getText();
		itemTemplate = itemTemplate.trim();
		System.out.println("itemTemplate: " +itemTemplate);
		if(itemTemplate.equals("") || itemTemplate.equals(null))
		{
			itemTemplate11 = true;
			System.out.println("itemTemplate11: " +itemTemplate11);
		}
		else
		{
			itemTemplate11 = false;
			System.out.println("itemTemplate11: " +itemTemplate11);
		}

		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		String quotedPrice = _driver.findElement(By.name("quotedPrice")).getAttribute("value");
		boolean quotePrice = quotedPrice.contains("0,00");
		boolean quotePrice1 = quotedPrice.contains("0.00");
		boolean quotePrice2 = false; 
		System.out.println("quotedPrice: "+quotedPrice);
		System.out.println("quotePrice: "+quotePrice);
		System.out.println("quotePrice1: "+quotePrice1);

		System.out.println("Click on PaceConnect");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[11]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
		{
			paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
		}
		else
		{
			paceConnectFiles = "0";
		}
		System.out.println("paceConnectFiles: "+paceConnectFiles);

		//Verify Job Component

		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String jobComponentCount = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount: "+jobComponentCount);

		if(jobComponentCount.equals("1"))
		{
			System.out.println("Entering jobComponentCount = 1 loop");
			jobComponent = _driver.findElement(By.name("appbox_implicit.description")).getAttribute("value"); 
			System.out.println("jobComponent: "+jobComponent);
			qtyOrderedJobComp = _driver.findElement(By.name("appbox_implicit.qtyOrdered")).getAttribute("value");
			System.out.println("qtyOrderedJobComp: "+qtyOrderedJobComp);

			if(jobComponent.equals(sNppProduct) && qtyOrderedJobComp.equals(qtyOrderedforJobComp))
			{
				jobComponentVerified = true;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}
			else
			{
				jobComponentVerified = false;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}
		}
		else if(jobComponentCount.equals("2"))
		{
			System.out.println("Entering jobComponentCount = 2 loop");
			jobComponent = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[1]/td[4]/input")).getAttribute("value"); 
			System.out.println("jobComponent: "+jobComponent);
			jobComponent1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[2]/td[4]/input")).getAttribute("value"); 
			System.out.println("jobComponent1: "+jobComponent1);
			qtyOrderedJobComp = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[1]/td[5]/input")).getAttribute("value");
			System.out.println("qtyOrderedJobComp: "+qtyOrderedJobComp);
			qtyOrderedJobComp1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[2]/td[5]/input")).getAttribute("value");
			System.out.println("qtyOrderedJobComp1: "+qtyOrderedJobComp1);

			if(jobComponent.equals(sNppProduct) && jobComponent1.equals(sStaticProduct) && qtyOrderedJobComp.equals(qtyOrderedforJobComp) && qtyOrderedJobComp1.equals(qtyOrderedforJobComp))
			{
				jobComponentVerified = true;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}
			else
			{
				jobComponentVerified = false;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}

		}
		else
		{
			jobComponentVerified = false;
			System.out.println("jobComponentVerified: "+jobComponentVerified);
			System.out.println("jobComponebtCount != 1 or 2");
		}


		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);

		if(jobComponentVerified == true && paceConnectFiles.equals(filesinPaceConnect) && jobProduct.equals(jobProductKitJob) && jobType.equals(jobTypeKitJob) && qtyToMfg.equals(qtyToManufacture) && itemTemplate11 == true && (quotePrice == true || quotePrice1 == true))
		{
			jobPartDetails_1 = true;
			System.err.println("Part 1 Verified Successfully");
		}
		else
		{
			jobPartDetails_1 = false;
			System.err.println("Part 1 Failed to Verify Successfully");
		}

		//CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//div[@id='scrollableContent']/fieldset/div[1]/div/div/select"), 2);
		//CommonFunctions.waitForPageLoaded(_driver);

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		//System.out.println("Click on Part Info");
		//_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		return jobPartDetails_1;

	}

	public boolean VerifyFirstJobPartDetails(String jobPart_1, String jobPart_2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect) throws Exception
	{
		String sJobNumber="";
		String[] sQty={"15","15"};
		String[] sKitJobName={"Desc","Desc"};
		boolean jobPartNames = false;
		boolean jobProductNames = false;
		boolean partQuantity = false;
		String desc = null;
		String qtyRemaining = null;
		String itemTemplate = null;
		String jobNo = null;
		String qtyToMfg = null;
		boolean tempDesc = false;
		String productName = null;
		String product1Name = null;
		String product2Name = null;
		String qtyOrdered = null;
		boolean jobPartDetails_1 = false;
		boolean jobPartDetails_2 = false;
		boolean jobPartDetails_3 = false;
		boolean jobPartDetails_4 = false;
		boolean jobPartDetails_71 = false;
		boolean jobPartDetails_81 = false;
		String productValue = null;
		boolean jobCost_1 = false;
		String parentJobProduct = null;
		boolean parentPro = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String partDesc_2 = null;
		String partDesc_3 = null;
		String partDesc_8 = null;
		String partDesc_9 = null;
		boolean partDesc_4 = false;
		boolean partDesc_5 = false;
		boolean partDesc_6 = false;
		boolean partDesc_7 = false;
		String itemTemplate2 = null;
		String itemTemplate3 = null;
		String itemTemplate5 = null;
		String itemTemplate6 = null;
		boolean tempDesc2 = false;
		boolean tempDesc3 = false;
		boolean tempDesc6 = false;
		boolean tempDesc7 = false;
		boolean bPaceConnectFiles = false;
		String paceConnectFiles = "";



		System.out.println("*********Verify 2nd Job Part********");

		//Go to Products tab and verify that there are 1 product or 2 products.

		Thread.sleep(1000);
		System.out.println("Click on Products Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
		System.out.println("It is now on Products tab");
		Thread.sleep(2000);

		String totalRecordsProducts_1 = null;
		//		totalRecordsProducts_1 = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText().trim();
		totalRecordsProducts_1 = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobProducts']/tbody/tr"))).trim();
		System.out.println("totalRecordsProducts_1: "+totalRecordsProducts_1);

		if(totalRecordsProducts_1.equals("1"))
		{
			System.out.println("Click on Part Info: IF LOOP");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[3]/td[3]/div/a/img")));
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[3]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.out.println("Click on Part Info: ELSE LOOP");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[4]/td[3]/div/a/img")));
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[4]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}


		String jobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();

		//Part Description
		//Considering that the name will always be "description" only
		boolean isDescPresent = CommonFunctions.isElementPresent(_driver, By.name("description_label"));
		if(isDescPresent == true)
		{
			//			partDesc_2 = _driver.findElement(By.name("description_label")).getAttribute("value").replace("Kit123 - ", "").trim();
			partDesc_2 = _driver.findElement(By.name("description_label")).getAttribute("value").trim();
		}
		else
		{
			//			partDesc_3 = _driver.findElement(By.name("description")).getAttribute("value").replace("Kit123 - ", "").trim();
			partDesc_3 = _driver.findElement(By.name("description")).getAttribute("value").trim();
		}

		if(partDesc_2 != null)
		{
			if(partDesc_2.equals(jobPart_1) || partDesc_2.equals(jobPart_2))
			{
				partDesc_4 = true;
				System.out.println("partDesc_4: "+partDesc_4);
			}
		}
		else if(partDesc_3!=null)
		{
			if(partDesc_3.equals(jobPart_1) || partDesc_3.equals(jobPart_2))
			{
				partDesc_5 = true;
				System.out.println("partDesc_5: "+partDesc_5);
			}
		}


		String qtyToMfg1 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("jobProduct1: "+jobProduct1);
		System.out.println("partDesc_2: "+partDesc_2);
		System.out.println("partDesc_3: "+partDesc_3);
		System.out.println("qtyToMfg1: "+qtyToMfg1);

		//Item Template
		boolean isItemTemplatePresent = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));		
		boolean isItemTemplatePresent1 = CommonFunctions.isElementPresent(_driver, By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a"));
		if(isItemTemplatePresent1)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a")).getText();
		}
		else if(isItemTemplatePresent == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
		}
		else
		{
			itemTemplate3 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
		}


		System.out.println("itemTemplate2: " +itemTemplate2);
		System.out.println("itemTemplate3: " +itemTemplate3);
		System.out.println("templateDesc: " +templateDesc);
		if(!itemTemplate2.equals(""))
		{
			tempDesc2 = itemTemplate2.contains(templateDesc);
		}
		/*if(itemTemplate3.equals(null))
		{

		}
		else
		{
			tempDesc3 = itemTemplate3.contains(templateDesc);
		}
		 */
		System.out.println("tempDesc2: " +tempDesc2);
		System.out.println("tempDesc3: " +tempDesc3);

		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice4 = false; 
		String quotedPrice1 = _driver.findElement(By.name("quotedPrice")).getAttribute("value");
		System.out.println("quotedPrice1: "+quotedPrice1);
		System.out.println("jobValue: "+jobValue);
		if(quotedPrice1.equals(jobValue))
		{
			quotePrice4 = true;
			System.out.println("quotePrice4: "+quotePrice4);
		}
		else
		{
			quotePrice4 = false;
			System.out.println("quotePrice4: "+quotePrice4);
		}

		//PaceConnect Tab
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			if (paceConnectFiles.equals(filesinPaceConnect))
			{
				bPaceConnectFiles = true;
			}
		}
		else
		{
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String jobComponentCount1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount1: "+jobComponentCount1);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);


		System.err.println("jobComponentCount1 is: "+jobComponentCount1);
		System.err.println("bPaceConnectFiles is: "+bPaceConnectFiles);
		System.err.println("jobProduct1 is: "+jobProduct1);
		System.err.println("jobProduct is: "+jobProduct);
		System.err.println("partDesc_4 is: "+partDesc_4);
		System.err.println("partDesc_5 is: "+partDesc_5);
		System.err.println("qtyToMfg1 is: "+qtyToMfg1);
		System.err.println("qtytoMfgJobPart is: "+qtytoMfgJobPart);
		System.err.println("tempDesc2 is: "+tempDesc2);
		System.err.println("tempDesc3 is: "+tempDesc3);
		System.err.println("quotePrice4 is: "+quotePrice4);


		if(jobComponentCount1.equals("0") && bPaceConnectFiles && jobProduct1.equals(jobProduct) && (partDesc_4 == true || partDesc_5 == true)  && qtyToMfg1.equals(qtytoMfgJobPart) && (tempDesc2 == true || tempDesc3 == true) && quotePrice4 == true)
		{
			jobPartDetails_2 = true;
			System.err.println("Part 2 Verified Successfully");
		}
		else
		{
			jobPartDetails_2 = false;
			System.err.println("Part 2 Failed to Verify Successfully");
		}

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		return jobPartDetails_2; 

	}

	public boolean VerifySecondJobPartDetails(String jobPart_1, String jobPart_2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect) throws Exception
	{		 
		String sJobNumber="";
		String[] sQty={"15","15"};
		String[] sKitJobName={"Desc","Desc"};
		boolean jobPartNames = false;
		boolean jobProductNames = false;
		boolean partQuantity = false;
		String desc = null;
		String qtyRemaining = null;
		String itemTemplate = null;
		String jobNo = null;
		String qtyToMfg = null;
		boolean tempDesc = false;
		String productName = null;
		String product1Name = null;
		String product2Name = null;
		String qtyOrdered = null;
		boolean jobPartDetails_1 = false;
		boolean jobPartDetails_2 = false;
		boolean jobPartDetails_3 = false;
		boolean jobPartDetails_4 = false;
		boolean jobPartDetails_71 = false;
		boolean jobPartDetails_81 = false;
		String productValue = null;
		boolean jobCost_1 = false;
		String parentJobProduct = null;
		boolean parentPro = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String partDesc_2 = null;
		String partDesc_3 = null;
		String partDesc_8 = null;
		String partDesc_9 = null;
		boolean partDesc_4 = false;
		boolean partDesc_5 = false;
		boolean partDesc_6 = false;
		boolean partDesc_7 = false;
		String itemTemplate2 = null;
		String itemTemplate3 = null;
		String itemTemplate5 = null;
		String itemTemplate6 = null;
		boolean tempDesc2 = false;
		boolean tempDesc3 = false;
		boolean tempDesc6 = false;
		boolean tempDesc7 = false;
		boolean bPaceConnectFiles = false;
		String paceConnectFiles = "";

		System.out.println("*********Verify 3rd Job Part********");

		//Go to Products tab and verify that there are 1 product or 2 products.

		Thread.sleep(1000);
		System.out.println("Click on Products Tab");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Products']"));
		System.out.println("It is now on Products tab");
		Thread.sleep(2000);

		String totalRecordsProducts_1 = null;
		//		totalRecordsProducts_1 = _driver.findElement(By.xpath("//fieldset[@id='JobProducts_fieldset']/div/div[1]/div[1]/strong")).getText();
		totalRecordsProducts_1 = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobProducts']/tbody/tr"))).trim();
		System.out.println("totalRecordsProducts_1: "+totalRecordsProducts_1);

		if(totalRecordsProducts_1.equals("1"))
		{
			System.out.println("Click on Part Info");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[4]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.out.println("Click on Part Info");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[5]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}



		String jobProduct2 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();

		//Verify Description
		boolean isDescPresent1 = CommonFunctions.isElementPresent(_driver, By.name("description_label"));
		System.out.println("isDescPresent1: "+isDescPresent1);
		if(isDescPresent1 == true)
		{
			partDesc_8 = _driver.findElement(By.name("description_label")).getAttribute("value").replace("Kit123 - ", "").trim();
		}
		else
		{
			partDesc_9 = _driver.findElement(By.name("description")).getAttribute("value").replace("Kit123 - ", "").trim();
		}

		if(partDesc_8 != null)
		{
			if(partDesc_8.equals(jobPart_1) || partDesc_8.equals(jobPart_2))
			{
				partDesc_6 = true;
				System.out.println("partDesc_6: "+partDesc_6);
			}
		}
		else if(partDesc_9 != null && partDesc_9.equals(jobPart_1) || partDesc_9.equals(jobPart_2))
		{
			if(partDesc_9.equals(jobPart_1) || partDesc_9.equals(jobPart_2))
			{
				partDesc_7 = true;
				System.out.println("partDesc_7: "+partDesc_7);
			}
		}

		String qtyToMfg2 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("jobProduct2: "+jobProduct2);
		System.out.println("partDesc_8: "+partDesc_8);
		System.out.println("partDesc_9: "+partDesc_9);
		System.out.println("qtyToMfg2: "+qtyToMfg2);

		//Verify Item Template
		boolean isItemTemplatePresent1 = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));
		boolean isItemTemplatePresent2 = CommonFunctions.isElementPresent(_driver, By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a"));
		System.out.println("isItemTemplatePresent1: "+isItemTemplatePresent1);
		if(isItemTemplatePresent2)
		{
			itemTemplate5 = _driver.findElement(By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a")).getText();
			System.out.println("itemTemplate5: "+itemTemplate5);
		}
		else if(isItemTemplatePresent1 == false)
		{
			itemTemplate5 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
			System.out.println("itemTemplate5: "+itemTemplate5);
		}
		else
		{
			itemTemplate6 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
			System.out.println("itemTemplate6: "+itemTemplate6);
		}

		System.out.println("stdTemplateDesc: " +templateDesc);

		if(!itemTemplate5.equals(""))
		{
			tempDesc6 = itemTemplate5.contains(templateDesc);
		}
		/*if(itemTemplate6.equals(null))
{

}
else
{
	tempDesc7 = itemTemplate6.contains(stdTemplateDesc);
}
		 */
		System.out.println("tempDesc6: " +tempDesc6);
		System.out.println("tempDesc7: " +tempDesc7);


		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice7 = false; 
		String quotedPrice9 = _driver.findElement(By.name("quotedPrice")).getAttribute("value");
		System.out.println("quotedPrice9: "+quotedPrice9);
		//boolean quotePrice5 = quotedPrice1.contains("0,00");
		boolean quotePrice6 = quotedPrice9.contains(jobValue);
		//System.out.println("quotePrice5: "+quotePrice5);
		System.out.println("quotePrice6: "+quotePrice6);

		//PaceConnect Tab
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			if (paceConnectFiles.equals(filesinPaceConnect))
			{
				bPaceConnectFiles = true;
			}
		}	
		else
		{
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component

		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String jobComponentCount2 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount2: "+jobComponentCount2);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);

		System.err.println("jobComponentCount1 is: "+jobComponentCount2);
		System.err.println("bPaceConnectFiles is: "+bPaceConnectFiles);
		System.err.println("jobProduct1 is: "+jobProduct2);
		System.err.println("jobProduct is: "+jobProduct);
		System.err.println("partDesc_8 is: "+partDesc_8);
		System.err.println("partDesc_9 is: "+partDesc_9);
		System.err.println("partDesc_6 is: "+partDesc_6);
		System.err.println("partDesc_7 is: "+partDesc_7);
		System.err.println("qtyToMfg1 is: "+qtyToMfg2);
		System.err.println("qtytoMfgJobPart is: "+qtytoMfgJobPart);
		System.err.println("tempDesc6 is: "+tempDesc6);
		System.err.println("tempDesc7 is: "+tempDesc7);
		System.err.println("quotePrice6 is: "+quotePrice6);

		if(jobComponentCount2.equals("0") && bPaceConnectFiles && jobProduct2.equals(jobProduct) && (partDesc_6 == true || partDesc_7 == true)  && qtyToMfg2.equals(qtytoMfgJobPart) && (tempDesc6 == true || tempDesc7 == true) && quotePrice6 == true)
		{
			jobPartDetails_3 = true;
			System.err.println("Part 3 Verified Successfully");
		}
		else
		{
			jobPartDetails_3 = false;
			System.err.println("Part 3 Failed to Verify Successfully");
		}

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);	


		return jobPartDetails_3;

	}

	//To be used when there are 4 Job Parts
	public boolean VerifyJobPart3(String jobPartNo, String jobPart_1, String jobPart_2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect) throws Exception
	{

		String sJobNumber="";
		String[] sQty={"15","15"};
		String[] sKitJobName={"Desc","Desc"};
		boolean jobPartNames = false;
		boolean jobProductNames = false;
		boolean partQuantity = false;
		String desc = null;
		String qtyRemaining = null;
		String itemTemplate = null;
		String jobNo = null;
		String qtyToMfg = null;
		boolean tempDesc = false;
		String productName = null;
		String product1Name = null;
		String product2Name = null;
		String qtyOrdered = null;
		boolean jobPartDetails_1 = false;
		boolean jobPartDetails_2 = false;
		boolean jobPartDetails_3 = false;
		boolean jobPartDetails_4 = false;
		boolean jobPartDetails_71 = false;
		boolean jobPartDetails_81 = false;
		String productValue = null;
		boolean jobCost_1 = false;
		String parentJobProduct = null;
		boolean parentPro = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String partDesc_2 = null;
		String partDesc_3 = null;
		String partDesc_8 = null;
		String partDesc_9 = null;
		boolean partDesc_4 = false;
		boolean partDesc_5 = false;
		boolean partDesc_6 = false;
		boolean partDesc_7 = false;
		String itemTemplate2 = null;
		String itemTemplate3 = null;
		String itemTemplate5 = null;
		String itemTemplate6 = null;
		boolean tempDesc2 = false;
		boolean tempDesc3 = false;
		boolean tempDesc6 = false;
		boolean tempDesc7 = false;
		boolean bPaceConnectFiles = false;
		String paceConnectFiles = "";

		System.out.println("*********Verify "+jobPartNo+" Job Part********");

		//Go to Products tab and verify that there are 1 product or 2 products.

		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);

		if(jobPartNo.equals("3"))
		{
			((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[5]/td[3]/div/a/img")));
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[5]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else if(jobPartNo.equals("4"))
		{
			((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[6]/td[3]/div/a/img")));
			_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[6]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}


		String jobProduct2 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();

		//Verify Description
		boolean isDescPresent1 = CommonFunctions.isElementPresent(_driver, By.name("description_label"));
		System.out.println("isDescPresent1: "+isDescPresent1);
		if(isDescPresent1 == true)
		{
			partDesc_8 = _driver.findElement(By.name("description_label")).getAttribute("value");
		}
		else
		{
			partDesc_9 = _driver.findElement(By.name("description")).getAttribute("value");
		}
		if (partDesc_8 != null)
		{	
			if(partDesc_8.equals(jobPart_1) || partDesc_8.equals(jobPart_2))
			{
				partDesc_6 = true;
				System.out.println("partDesc_6: "+partDesc_6);
			}
		}
		else if (partDesc_9 != null)
		{
			if(partDesc_9.equals(jobPart_1) || partDesc_9.equals(jobPart_2))
			{
				partDesc_7 = true;
				System.out.println("partDesc_7: "+partDesc_7);
			}
		}

		String qtyToMfg2 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("jobProduct2: "+jobProduct2);
		System.out.println("partDesc_8: "+partDesc_8);
		System.out.println("partDesc_9: "+partDesc_9);
		System.out.println("qtyToMfg2: "+qtyToMfg2);

		//Verify Item Template
		boolean isItemTemplatePresent1 = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));
		System.out.println("isItemTemplatePresent1: "+isItemTemplatePresent1);
		if(isItemTemplatePresent1 == false)
		{
			itemTemplate5 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
			System.out.println("itemTemplate5: "+itemTemplate5);
		}
		else
		{
			itemTemplate6 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
			System.out.println("itemTemplate6: "+itemTemplate6);
		}

		System.out.println("stdTemplateDesc: " +templateDesc);

		if(!itemTemplate5.equals(""))
		{
			tempDesc6 = itemTemplate5.contains(templateDesc);
		}
		/*if(itemTemplate6.equals(null))
{

}
else
{
	tempDesc7 = itemTemplate6.contains(stdTemplateDesc);
}
		 */
		System.out.println("tempDesc6: " +tempDesc6);
		System.out.println("tempDesc7: " +tempDesc7);


		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice7 = false; 
		String quotedPrice9 = _driver.findElement(By.name("quotedPrice")).getAttribute("value");
		String quotedPrice10 = null;

		if(partDesc_8.equals(jobPart_2) && !jobPartNo.equals("3"))
		{
			quotedPrice10 = "$0.00";
		}
		else if(partDesc_8.equals(jobPart_1))
		{
			quotedPrice10 = "$753.00";
		}

		System.out.println("quotedPrice9: "+quotedPrice9);
		System.out.println("quotedPrice10: "+quotedPrice10);
		//boolean quotePrice5 = quotedPrice1.contains("0,00");
		//boolean quotePrice6 = quotedPrice9.contains(jobValue);
		//System.out.println("quotePrice5: "+quotePrice5);
		//System.out.println("quotePrice6: "+quotePrice6);

		//PaceConnect Tab
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			if (paceConnectFiles.equals(filesinPaceConnect))
			{
				bPaceConnectFiles = true;
			}
		}
		else
		{
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component

		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String jobComponentCount2 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount2: "+jobComponentCount2);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);

		if(jobComponentCount2.equals("0") && bPaceConnectFiles && jobProduct2.equals(jobProduct) && (partDesc_6 == true || partDesc_7 == true)  && qtyToMfg2.equals(qtytoMfgJobPart) && (tempDesc6 == true || tempDesc7 == true) && quotedPrice9.equals(quotedPrice10))
		{
			jobPartDetails_3 = true;
			System.err.println("Part "+jobPartNo+" Verified Successfully");
		}
		else
		{
			jobPartDetails_3 = false;
			System.err.println("Part "+jobPartNo+" Failed to Verify Successfully");
		}

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);	 

		return jobPartDetails_3;		 
	} 

	public boolean Verify1stJobPartDetails(String jobPart_1, String jobPart_2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect) throws Exception
	{
		String sJobNumber="";
		String[] sQty={"15","15"};
		String[] sKitJobName={"Desc","Desc"};
		boolean jobPartNames = false;
		boolean jobProductNames = false;
		boolean partQuantity = false;
		String desc = null;
		String qtyRemaining = null;
		String itemTemplate = null;
		String jobNo = null;
		String qtyToMfg = null;
		boolean tempDesc = false;
		String productName = null;
		String product1Name = null;
		String product2Name = null;
		String qtyOrdered = null;
		boolean jobPartDetails_1 = false;
		boolean jobPartDetails_2 = false;
		boolean jobPartDetails_3 = false;
		boolean jobPartDetails_4 = false;
		boolean jobPartDetails_71 = false;
		boolean jobPartDetails_81 = false;
		String productValue = null;
		boolean jobCost_1 = false;
		String parentJobProduct = null;
		boolean parentPro = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String partDesc_2 = null;
		String partDesc_3 = null;
		String partDesc_8 = null;
		String partDesc_9 = null;
		boolean partDesc_4 = false;
		boolean partDesc_5 = false;
		boolean partDesc_6 = false;
		boolean partDesc_7 = false;
		String itemTemplate2 = null;
		String itemTemplate3 = null;
		String itemTemplate5 = null;
		String itemTemplate6 = null;
		boolean tempDesc2 = false;
		boolean tempDesc3 = false;
		boolean tempDesc6 = false;
		boolean tempDesc7 = false;
		boolean bPaceConnectFiles = false;
		String paceConnectFiles = "";



		System.out.println("*********Verify 2nd Job Part********");

		//Go to Products tab and verify that there are 1 product or 2 products.

		Thread.sleep(1000);

		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[3]/td[3]/div/a/img"));
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", _driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[3]/td[3]/div/a/img")));
		_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[3]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);

		String jobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();

		//Part Description
		//Considering that the name will always be "description" only
		boolean isDescPresent = CommonFunctions.isElementPresent(_driver, By.name("description"));
		if(isDescPresent == true)
		{
			partDesc_2 = _driver.findElement(By.name("description")).getAttribute("value").replace("Kit123 - ", "").trim();
		}
		//else
		//{
		//	partDesc_3 = _driver.findElement(By.name("description_label")).getAttribute("value");
		//}

		if(partDesc_2.equals(jobPart_1) || partDesc_2.equals(jobPart_2))
		{
			partDesc_4 = true;
			System.out.println("partDesc_4: "+partDesc_4);
		}
		//else if(partDesc_3.equals(jobPart_1) || partDesc_3.equals(jobPart_2))
		//{
		//	partDesc_5 = true;
		//	System.out.println("partDesc_5: "+partDesc_5);
		//}


		String qtyToMfg1 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("jobProduct1: "+jobProduct1);
		System.out.println("partDesc_2: "+partDesc_2);
		System.out.println("partDesc_3: "+partDesc_3);
		System.out.println("qtyToMfg1: "+qtyToMfg1);

		//Item Template
		boolean isItemTemplatePresent = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));
		if(isItemTemplatePresent == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
		}
		else
		{
			itemTemplate3 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
		}


		System.out.println("itemTemplate2: " +itemTemplate2);
		System.out.println("itemTemplate3: " +itemTemplate3);
		System.out.println("templateDesc: " +templateDesc);
		if(!itemTemplate2.equals(""))
		{
			tempDesc2 = itemTemplate2.contains(templateDesc);
		}
		/*if(itemTemplate3.equals(null))
{

}
else
{
	tempDesc3 = itemTemplate3.contains(templateDesc);
}
		 */
		System.out.println("tempDesc2: " +tempDesc2);
		System.out.println("tempDesc3: " +tempDesc3);

		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice4 = false; 
		String quotedPrice1 = _driver.findElement(By.name("quotedPrice")).getAttribute("value").replace(",", "").trim();
		System.out.println("quotedPrice1: "+quotedPrice1);
		System.out.println("jobValue: "+jobValue);
		if(quotedPrice1.equals(jobValue))
		{
			quotePrice4 = true;
			System.out.println("quotePrice4: "+quotePrice4);
		}
		else
		{
			quotePrice4 = false;
			System.out.println("quotePrice4: "+quotePrice4);
		}

		//PaceConnect Tab
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			if (paceConnectFiles.equals(filesinPaceConnect))
			{
				bPaceConnectFiles = true;
			}
		}
		else
		{
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		String jobComponentCount1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount1: "+jobComponentCount1);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);


		System.err.println("jobComponentCount1: "+jobComponentCount1);
		System.err.println("paceConnectFiles: "+bPaceConnectFiles);
		System.err.println("jobProduct1: "+jobProduct1);
		System.err.println("jobProduct: "+jobProduct);
		System.err.println("partDesc_4: "+partDesc_4);
		System.err.println("partDesc_5: "+partDesc_5);
		System.err.println("qtyToMfg1: "+qtyToMfg1);
		System.err.println("qtytoMfgJobPart: "+qtytoMfgJobPart);
		System.err.println("tempDesc2: "+tempDesc2);
		System.err.println("tempDesc3: "+tempDesc3);
		System.err.println("quotePrice4: "+quotePrice4);


		if(jobComponentCount1.equals("0") && bPaceConnectFiles && jobProduct1.equals(jobProduct) && (partDesc_4 == true || partDesc_5 == true)  && qtyToMfg1.equals(qtytoMfgJobPart) && (tempDesc2 == true || tempDesc3 == true) && quotePrice4 == true)
		{
			jobPartDetails_2 = true;
			System.err.println("Part 2 Verified Successfully");
		}
		else
		{
			jobPartDetails_2 = false;
			System.err.println("Part 2 Failed to Verify Successfully");
		}

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		return jobPartDetails_2; 

	}

	//Order 1 DSF Static product
	public String Order_Product_In_DSF_Using_Specific_Contact(String productName, String paymentMethod, String contactName) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		boolean isContactPresent = false;
		String [] contactFullName = null;
		String contactFirstName = null;
		String contactLastName = null;

		//This contact is the one that we take as the input from the method
		String [] contact = null;
		String contact1stName = null;
		String contact2ndName = null;

		contact = contactName.split(" ");

		contact1stName = contact[0];
		contact1stName = contact1stName.trim();

		contact2ndName = contact[1];
		contact2ndName = contact2ndName.trim();

		System.err.println("contact1stName is: " +contact1stName);
		System.err.println("contact2ndName is: " +contact2ndName);

		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		ClearCart();
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");

		// CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
		// System.out.println("Selecting All from the dopdown");
		Thread.sleep(3000);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + productName);
		for (int i = rowCount; i>=1; i=i-2)
		{

			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				// System.out.println("Expected product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
				System.out.println("Entering the qty to be ordered as 10");

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(5000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				Thread.sleep(2000);

				//Address Book
				_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$ButtonAddressBook")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(1000);

				//_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$PopUpFrame1$ctl00$TextBoxSearch")).sendKeys(contactName);
				//_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$PopUpFrame1$ctl00$ImageButtonSearch")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);


				int rowCountAddressBook = _driver.findElements(By.xpath("//table[@id='ctl00_C_DataGrid_Recipients_ctl02_ShoppingCart_DisplayRecipient_PopUpFrame1_ctl00_dgAddressBook']/tbody/tr")).size();
				System.out.println("Total number of rows in Address Book are: " +rowCountAddressBook);

				for (int k = 2; k<= rowCountAddressBook; k++)
				{
					String contactName_1 = _driver.findElement(By.xpath("//table[@id='ctl00_C_DataGrid_Recipients_ctl02_ShoppingCart_DisplayRecipient_PopUpFrame1_ctl00_dgAddressBook']/tbody/tr["+k+"]/td[3]")).getText(); 
					contactName_1 = contactName_1.trim();
					System.out.println("Contact Name in Row "+ k + " is: " +contactName_1);

					System.err.println("contactName_1 is: "+contactName_1);
					contactFullName = contactName_1.split(" ");

					contactFirstName = contactFullName[0];
					contactFirstName = contactFirstName.trim();

					contactLastName = contactFullName[1];
					contactLastName = contactLastName.trim();

					System.err.println("contactFirstName is: "+contactFullName[0]);
					System.err.println("contactLastName is: "+contactFullName[1]);

					System.err.println("contact1stName is: " +contact1stName);
					System.err.println("contact2ndName is: " +contact2ndName);

					if (contactFirstName.equals(contact1stName) && contactLastName.equals(contact2ndName))
					{
						System.out.println("Expected Contact is present in row: "+k);
						_driver.findElement(By.xpath("//table[@id='ctl00_C_DataGrid_Recipients_ctl02_ShoppingCart_DisplayRecipient_PopUpFrame1_ctl00_dgAddressBook']/tbody/tr["+k+"]/td[1]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						_driver.findElement(By.xpath("//input[@id='ctl00_C_DataGrid_Recipients_ctl02_ShoppingCart_DisplayRecipient_PopUpFrame1_btnOK']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
						System.err.println("DESIRED CONTACT IS FOUND in row: "+k);
						isContactPresent = true;
						break;
					}
					else
					{
						System.err.println("Desired Contact is not found in row: "+k);
					}
				}

				if(isContactPresent == false)
				{
					Assert.fail("Desired Contact is not found");
				}

				CommonFunctions.selectDropdownByIndex(_driver, By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$DropDownListStateProvinceRegion"), 0);

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				CommonFunctions.waitForPageLoaded(_driver);
				CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)));
				System.out.println("Proceeding to Checkout");

				//Logic for Payment method
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
				Thread.sleep(1000);
				System.out.println("Selecting the payment method as: " +paymentMethod);

				if(paymentMethod.equals("PO Number"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_PO_Number))).sendKeys("12345");
					System.out.println("Entering the PO number as '12345'");
				}
				if(paymentMethod.equals("Other Account"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Account_Number))).sendKeys("1234567");
					System.out.println("Entering the Account number as '1234567'");
				}
				if(paymentMethod.equals("Automation_AC"))
				{
					_driver.findElement(By.name("ctl00$C$CheckoutPaymentMethodControl$AccountCodeEntryArea$RP_AccCode$ctl00$AccCode1$TextBoxAccountCode")).sendKeys("12345");
					System.out.println("Entering the Accounting Code as '12345'");
				}


				Thread.sleep(2000);

				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}	 

	//Order 1 DSF Static product
	public String Order_Product_In_DSF_Using_Manually_Entered_Contact(String productName, String paymentMethod) throws Exception
	{
		boolean isProductfound = false;
		String orderNo = null;
		String dsfOrderNumber = null;
		boolean isContactPresent = false;

		//Search the product in Storefront
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Storefront))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");
		Thread.sleep(2000);
		ClearCart();
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search))).sendKeys(productName);
		Thread.sleep(1000);
		_driver.findElement(By.id(Locators.getProperty(Locators.DSF_Search_Go))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		System.out.println("Clicking the Storefront link");

		// CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Show_Products_Dropdown)), "All");
		// System.out.println("Selecting All from the dopdown");
		Thread.sleep(3000);

		int rowCount = _driver.findElements(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr")).size();
		System.out.println("Total number of rows are: " + rowCount);
		Thread.sleep(3000);
		System.out.println("DSF product for which order is to be placed is: " + productName);
		for (int i = rowCount; i>=1; i=i-2)
		{

			String dsfProductName = _driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div/a")).getText();
			System.out.println("DSF product in Storefront  is: " + dsfProductName);
			//Order the product
			if(dsfProductName.equals(productName))
			{
				isProductfound = true;
				// System.out.println("Expected product found in row: " +i);
				_driver.findElement(By.xpath("//table[@id='ctl00_ctl00_C_C1_EFIGridControlPrdSrch']/tbody/tr[1]/td[1]/div/div[2]/div[9]/div[1]/input")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Clicking the Begin button for " +dsfProductName );
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Product_Qty))).sendKeys("10");
				System.out.println("Entering the qty to be ordered as 10");

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Order_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Add_To_Cart))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(5000);
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Shipping_Method)), "Customer Pick-Up");
				System.out.println("Selecting the shipping method as Customer Pick-Up");
				Thread.sleep(2000);

				_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$TextBoxFirstName")).clear();
				_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$TextBoxFirstName")).sendKeys("UVW");
				_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$TextBoxLastName")).clear();
				_driver.findElement(By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$TextBoxLastName")).sendKeys("XYZ");

				CommonFunctions.selectDropdownByIndex(_driver, By.name("ctl00$C$DataGrid_Recipients$ctl02$ShoppingCart_DisplayRecipient$DropDownListStateProvinceRegion"), 0);

				_driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_Checkout))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Proceeding to Checkout");

				//Logic for Payment method
				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.DSF_Payment_Method)), paymentMethod);
				Thread.sleep(1000);
				System.out.println("Selecting the payment method as: " +paymentMethod);

				if(paymentMethod.equals("PO Number"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_PO_Number))).sendKeys("12345");
					System.out.println("Entering the PO number as '12345'");
				}
				if(paymentMethod.equals("Other Account"))
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Account_Number))).sendKeys("1234567");
					System.out.println("Entering the Account number as '1234567'");
				}
				if(paymentMethod.equals("Automation_AC"))
				{
					_driver.findElement(By.name("ctl00$C$CheckoutPaymentMethodControl$AccountCodeEntryArea$RP_AccCode$ctl00$AccCode1$TextBoxAccountCode")).sendKeys("12345");
					System.out.println("Entering the Accounting Code as '12345'");
				}


				Thread.sleep(2000);

				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Payment_Method_Next))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				Thread.sleep(2000);
				_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Place_Order))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
				System.out.println("Click on Place My Order button");
				Thread.sleep(2000);
				orderNo = _driver.findElement(By.xpath("//span[@id='ctl00_C_LabelOrderNumber']")).getText();
				System.out.println("Order Number is: " +orderNo);

				if(!orderNo.equals(null))
				{
					dsfOrderNumber = orderNo;
				}
				else
				{
					dsfOrderNumber = null;
				}
			}
			else
			{
				isProductfound = false;
				System.out.println("Product not found");
			}
			if(isProductfound == true)
			{
				break;
			}
		}
		return dsfOrderNumber;
	}	 

	public boolean VerifyFirstJobPart(String sStaticProduct, String sNppProduct, String qtyOrderedforJobComp, String filesinPaceConnect, String jobProductKitJob, String jobTypeKitJob, String qtyToManufacture, String sJobNumber) throws Exception
	{

		String itemTemplate = null;
		String jobProduct = null;
		String qtyToMfg = null;
		String productName = null;
		boolean jobPartDetails_1 = false;
		boolean itemTemplate11 = false;
		boolean jobComponentVerified = false;
		String jobComponent = null;
		String jobComponent1 = null;
		String qtyOrderedJobComp = null; 
		String qtyOrderedJobComp1 = null; 
		String paceConnectFiles = "";

		/*		System.out.println("*********Verify 1st Job Part********");
		_driver.findElement(By.xpath("//table[@id='JobPart_N1002F']/tbody/tr[2]/td[3]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);

		System.out.println("sNppProduct inside VerifyKitJobPartDetails: "+sNppProduct);
		System.out.println("sStaticProduct inside VerifyKitJobPartDetails: "+sStaticProduct);
		 */

		jobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();
		String jobType = CommonFunctions.GetSelectedOption(_driver, By.name("jobType"));
		qtyToMfg = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.err.println("jobProduct: "+jobProduct);
		System.out.println("jobType: "+jobType);
		System.err.println("qtyToMfg: "+qtyToMfg);
		
		if(jobType.equals("Kit Job"))
		{
			System.err.println("This Job Part has Job Type: Kit Job");
		}

		System.out.println("sStaticProduct inside VerifyKitJobPartDetails: "+sStaticProduct);
		System.out.println("sNppProduct inside VerifyKitJobPartDetails: "+sNppProduct);

		//ItemTemplate
		itemTemplate = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[3]/div[1]/div/div")).getText().trim();
		System.out.println("itemTemplate: " +itemTemplate);
		if(itemTemplate.equals("") || itemTemplate.equals(null))
		{
			itemTemplate11 = true;
			System.out.println("itemTemplatePart1: " +itemTemplate11);
		}
		else
		{
			itemTemplate11 = false;
			System.out.println("itemTemplatePart1: " +itemTemplate11);
		}

		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		String quotedPrice = _driver.findElement(By.name("quotedPrice")).getAttribute("value");
		boolean quotePrice = quotedPrice.contains("0,00");
		boolean quotePrice1 = quotedPrice.contains("0.00");
		boolean quotePrice2 = false; 
		System.out.println("quotedPrice: "+quotedPrice);
		System.out.println("quotePrice: "+quotePrice);
		System.out.println("quotePrice1: "+quotePrice1);

		System.out.println("Click on PaceConnect");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[11]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
		{
			paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
		}
		else
		{
			paceConnectFiles = "0";
		}
		System.out.println("paceConnectFiles: "+paceConnectFiles);


		//Verify Job Component

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobComponent/listJobPart/"+sJobNumber+":01");
		Thread.sleep(4000);


		/*		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		 */		
		String jobComponentCount = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount: "+jobComponentCount);

		if(jobComponentCount.equals("2"))
		{
			System.out.println("Entering jobComponentCount loop");
			jobComponent = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[1]/td[4]/input")).getAttribute("value"); 
			System.out.println("jobComponent: "+jobComponent);
			System.out.println("sStaticProduct: "+sStaticProduct);
			jobComponent1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[2]/td[4]/input")).getAttribute("value"); 
			System.out.println("jobComponent1: "+jobComponent1);
			System.out.println("sNppProduct: "+sNppProduct);
			qtyOrderedJobComp = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[1]/td[5]/input")).getAttribute("value");
			System.out.println("qtyOrderedJobComp: "+qtyOrderedJobComp);
			System.out.println("qtyOrderedforJobComp: "+qtyOrderedforJobComp);
			qtyOrderedJobComp1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div[1]/table/tbody/tr[2]/td[5]/input")).getAttribute("value");
			System.out.println("qtyOrderedJobComp1: "+qtyOrderedJobComp1);
			System.out.println("qtyOrderedforJobComp: "+qtyOrderedforJobComp);

			if(jobComponent.equals(sStaticProduct) && jobComponent1.equals(sNppProduct) && qtyOrderedJobComp.equals(qtyOrderedforJobComp) && qtyOrderedJobComp1.equals(qtyOrderedforJobComp))
			{
				jobComponentVerified = true;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}
			else
			{
				jobComponentVerified = false;
				System.out.println("jobComponentVerified: "+jobComponentVerified);
			}

		}
		else
		{
			jobComponentVerified = false;
			System.out.println("jobComponentVerified: "+jobComponentVerified);
			System.out.println("jobComponebtCount != 2");
		}


		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);

		System.err.println("jobComponentVerified is: "+jobComponentVerified);
		System.err.println("paceConnectFiles is: "+paceConnectFiles);
		System.err.println("filesinPaceConnect is: "+filesinPaceConnect);
		System.err.println("jobProduct is: "+jobProduct);
		System.err.println("jobProductKitJob is: "+jobProductKitJob);
		System.err.println("jobType is: "+jobType);
		System.err.println("jobTypeKitJob is: "+jobTypeKitJob);
		System.err.println("qtyToMfg is: "+qtyToMfg);
		System.err.println("qtyToManufacture is: "+qtyToManufacture);
		System.err.println("itemTemplate11 is: "+itemTemplate11);
		System.err.println("quotePrice is: "+quotePrice);
		System.err.println("quotePrice1 is: "+quotePrice1);

		if(jobComponentVerified == true && paceConnectFiles.equals(filesinPaceConnect) 
		&& jobProduct.equals(jobProductKitJob) && jobType.equals(jobTypeKitJob) 
		&& qtyToMfg.equals(qtyToManufacture) && itemTemplate11 == true 
		&& (quotePrice == true || quotePrice1 == true))
		{
			jobPartDetails_1 = true;
			System.err.println("Part 1 Verified Successfully");
		}
		else
		{
			jobPartDetails_1 = false;
			System.err.println("Part 1 Failed to Verify Successfully");
		}

		//CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//div[@id='scrollableContent']/fieldset/div[1]/div/div/select"), 2);
		//CommonFunctions.waitForPageLoaded(_driver);

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		//System.out.println("Click on Part Info");
		//_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		return jobPartDetails_1;

	} 

	public boolean VerifySecondJobPart(String jobPart2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect, String sJobNumber) throws Exception
	{
		int files = 0; String[] sQty={"15","15"}; String[] sKitJobName={"Desc","Desc"}; boolean jobPartNames = false; boolean jobProductNames = false; boolean partQuantity = false; String desc = null; String qtyRemaining = null; String itemTemplate = null; String jobNo = null; String qtyToMfg = null; boolean tempDesc = false; String productName = null; String product1Name = null; String product2Name = null; String qtyOrdered = null; boolean jobPartDetails_1 = false; boolean jobPartDetails_2 = false; boolean jobPartDetails_3 = false; boolean jobPartDetails_4 = false; boolean jobPartDetails_71 = false; boolean jobPartDetails_81 = false; String productValue = null; boolean jobCost_1 = false; String parentJobProduct = null; boolean parentPro = false; boolean itemTemplate11 = false; boolean jobComponentVerified = false; String partDesc_2 = null; String partDesc_3 = null; String partDesc_8 = null; String partDesc_9 = null; boolean partDesc_4 = false; boolean partDesc_5 = false; boolean partDesc_6 = false; boolean partDesc_7 = false; String itemTemplate2 = null; String itemTemplate3 = null; String itemTemplate5 = null; String itemTemplate6 = null; boolean tempDesc2 = false; boolean tempDesc3 = false; boolean tempDesc6 = false; boolean tempDesc7 = false; boolean bPaceConnectFiles = false; String paceConnectFiles = "";

		//System.out.println("*********Verify 2nd Job Part********");

		//Part Description in Job Part Description page
		partDesc_2 = _driver.findElement(By.name("description_label")).getAttribute("value").trim();
		System.err.println("partDesc_2 is: "+partDesc_2);



		//Job Product
		String jobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();
		System.err.println("jobProduct1 is: "+jobProduct1);

		String qtyToMfg1 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.err.println("qtyToMfg1: "+qtyToMfg1);

		//Item Template
		boolean isItemTemplatePresent = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));		
		boolean isItemTemplatePresent1 = CommonFunctions.isElementPresent(_driver, By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a"));
		if(isItemTemplatePresent1 == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a")).getText();
		}
		else if(isItemTemplatePresent == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
		}
		else
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
		}


		System.err.println("itemTemplate2: " +itemTemplate2);
		System.err.println("templateDesc: " +templateDesc);

		if(!itemTemplate2.equals(""))
		{
			tempDesc2 = itemTemplate2.contains(templateDesc);
		}

		System.out.println("tempDesc2: " +tempDesc2);
		//System.out.println("tempDesc3: " +tempDesc3);

		//Job Part Cost
		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice4 = false; 
		String quotedPrice1 = _driver.findElement(By.name("quotedPrice")).getAttribute("value").trim();
		System.out.println("quotedPrice1: "+quotedPrice1);
		System.out.println("jobValue: "+jobValue);
		if(quotedPrice1.equals(jobValue))
		{
			quotePrice4 = true;
			System.out.println("quotePrice4: "+quotePrice4);
		}
		else
		{
			quotePrice4 = false;
			System.out.println("quotePrice4: "+quotePrice4);
		}

		//PaceConnect Tab
		//There will be 2 files out of which one will have Production boolean enabled
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			files = Integer.parseInt(paceConnectFiles);



			if ((files>=1)&&(files<3))
			{
				bPaceConnectFiles = true;
			}
			else
			{
				bPaceConnectFiles = false;
			}
		}
		else
		{
			bPaceConnectFiles = false;
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobComponent/listJobPart/"+sJobNumber+":02");
		Thread.sleep(4000);


		/*		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		 */		
		String jobComponentCount1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount1: "+jobComponentCount1);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);


		System.err.println("jobComponentCount1 is: "+jobComponentCount1);
		System.err.println("bPaceConnectFiles is: "+bPaceConnectFiles);
		System.err.println("paceConnectFiles is: "+paceConnectFiles);
		System.err.println("filesinPaceConnect is: "+filesinPaceConnect);
		System.err.println("jobProduct1 is: "+jobProduct1);
		System.err.println("jobProduct is: "+jobProduct);
		System.err.println("partDesc_2 is: "+partDesc_2);
		System.err.println("jobPart2 is: "+jobPart2);
		System.err.println("qtyToMfg1 is: "+qtyToMfg1);
		System.err.println("qtytoMfgJobPart is: "+qtytoMfgJobPart);
		System.err.println("tempDesc2 is: "+tempDesc2);
		System.err.println("quotePrice4 is: "+quotePrice4);
		
		//if(jobComponentCount1.equals("0") && bPaceConnectFiles == true && jobProduct1.equals(jobProduct) && partDesc_2.equals(jobPart2)  && qtyToMfg1.equals(qtytoMfgJobPart) && tempDesc2 == true && quotePrice4 == true)
		if(jobComponentCount1.equals("0") && paceConnectFiles.equals(filesinPaceConnect) 
		&& jobProduct1.equals(jobProduct) && partDesc_2.equals(jobPart2)  
		&& qtyToMfg1.equals(qtytoMfgJobPart) && tempDesc2 == true && quotePrice4 == true)
		{
			jobPartDetails_2 = true;
			//System.err.println("jobPartDetails_2 is: "+jobPartDetails_2);
			System.err.println("Part 2 Verified Successfully");
		}
		else
		{
			jobPartDetails_2 = false;
			//System.err.println("jobPartDetails_2 is: "+jobPartDetails_2);
			System.err.println("Part 2 Failed to Verify Successfully");
		}

		/*		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		 */		 
		return jobPartDetails_2; 

	}	
	
	public boolean VerifyThirdJobPart(String jobPart2, String jobValue, String templateDesc, String jobProduct, String qtytoMfgJobPart, String filesinPaceConnect, String sJobNumber) throws Exception
	{
		int files = 0; String[] sQty={"15","15"}; String[] sKitJobName={"Desc","Desc"}; boolean jobPartNames = false; boolean jobProductNames = false; boolean partQuantity = false; String desc = null; String qtyRemaining = null; String itemTemplate = null; String jobNo = null; String qtyToMfg = null; boolean tempDesc = false; String productName = null; String product1Name = null; String product2Name = null; String qtyOrdered = null; boolean jobPartDetails_1 = false; boolean jobPartDetails_2 = false; boolean jobPartDetails_3 = false; boolean jobPartDetails_4 = false; boolean jobPartDetails_71 = false; boolean jobPartDetails_81 = false; String productValue = null; boolean jobCost_1 = false; String parentJobProduct = null; boolean parentPro = false; boolean itemTemplate11 = false; boolean jobComponentVerified = false; String partDesc_2 = null; String partDesc_3 = null; String partDesc_8 = null; String partDesc_9 = null; boolean partDesc_4 = false; boolean partDesc_5 = false; boolean partDesc_6 = false; boolean partDesc_7 = false; String itemTemplate2 = null; String itemTemplate3 = null; String itemTemplate5 = null; String itemTemplate6 = null; boolean tempDesc2 = false; boolean tempDesc3 = false; boolean tempDesc6 = false; boolean tempDesc7 = false; boolean bPaceConnectFiles = false; String paceConnectFiles = "";

		//Part Description in Job Part Description page
		partDesc_2 = _driver.findElement(By.name("description")).getAttribute("value").trim();
		System.err.println("partDesc_3 is: "+partDesc_2);

		//Job Product
		String jobProduct1 = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct")).replace("[", "").replace("]", "").trim();
		System.err.println("jobProduct1 is: "+jobProduct1);

		String qtyToMfg1 = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.err.println("qtyToMfg1: "+qtyToMfg1);

		//Item Template
		boolean isItemTemplatePresent = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a"));		
		boolean isItemTemplatePresent1 = CommonFunctions.isElementPresent(_driver, By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a"));
		if(isItemTemplatePresent1 == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//h4[@title='Select the item template you want to use to automatically populate fields on the new job part. Max 50 characters. Letters and Numbers.']/following-sibling::div/div[1]/a")).getText();
		}
		else if(isItemTemplatePresent == true)
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[11]/div[2]/div/div[1]/a")).getText();
		}
		else
		{
			itemTemplate2 = _driver.findElement(By.xpath("//div[@id='contents']/div/div[3]/div/div/div[1]/a")).getText();
		}


		System.err.println("itemTemplate2: " +itemTemplate2);
		System.err.println("templateDesc: " +templateDesc);

		if(!itemTemplate2.equals(""))
		{
			tempDesc2 = itemTemplate2.contains(templateDesc);
		}

		System.out.println("tempDesc2: " +tempDesc2);
		//System.out.println("tempDesc3: " +tempDesc3);

		//Job Part Cost
		System.out.println("Click on Billing");
		_driver.findElement(By.xpath("//div[@id='tabBar']/div[3]/span/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);

		boolean quotePrice4 = false; 
		String quotedPrice1 = _driver.findElement(By.name("quotedPrice")).getAttribute("value").trim();
		System.out.println("quotedPrice1: "+quotedPrice1);
		System.out.println("jobValue: "+jobValue);
		if(quotedPrice1.equals(jobValue))
		{
			quotePrice4 = true;
			System.out.println("quotePrice4: "+quotePrice4);
		}
		else
		{
			quotePrice4 = false;
			System.out.println("quotePrice4: "+quotePrice4);
		}

		//PaceConnect Tab
		//There will be 2 files out of which one will have Production boolean enabled
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[text()='PaceConnect']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='PaceConnect']"));
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']")))
			{
				paceConnectFiles = _driver.findElement(By.xpath("//fieldset[@id='JobPartContentFile_fieldset']/div/div[1]/div[1]/strong")).getText(); 
			}
			else
			{
				paceConnectFiles = "0";
			}
			System.out.println("paceConnectFiles: "+paceConnectFiles);
			files = Integer.parseInt(paceConnectFiles);



			if ((files>=1)&&(files<3))
			{
				bPaceConnectFiles = true;
			}
			else
			{
				bPaceConnectFiles = false;
			}
		}
		else
		{
			bPaceConnectFiles = false;
			System.err.println("Paceconnect tab was not found. Ensure the pace connect is active.");
		}

		//Verify Job Component

		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:public/object/JobComponent/listJobPart/"+sJobNumber+":02");
		Thread.sleep(4000);


		/*		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4]")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//div/div/table[4]/tbody/tr[2]/td[4]/a")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		 */		
		String jobComponentCount1 = _driver.findElement(By.xpath("//div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
		System.out.println("jobComponentCount1: "+jobComponentCount1);	

		_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);


		System.err.println("jobComponentCount1 is: "+jobComponentCount1);
		System.err.println("bPaceConnectFiles is: "+bPaceConnectFiles);
		System.err.println("filesinPaceConnect is: "+filesinPaceConnect);
		System.err.println("jobProduct1 is: "+jobProduct1);
		System.err.println("jobProduct is: "+jobProduct);
		System.err.println("partDesc_2 is: "+partDesc_2);
		System.err.println("jobPart2 is: "+jobPart2);
		System.err.println("qtyToMfg1 is: "+qtyToMfg1);
		System.err.println("qtytoMfgJobPart is: "+qtytoMfgJobPart);
		System.err.println("tempDesc2 is: "+tempDesc2);
		System.err.println("quotePrice4 is: "+quotePrice4);
		
		if(jobComponentCount1.equals("0") && bPaceConnectFiles == false && jobProduct1.equals(jobProduct) 
		&& partDesc_2.equals(jobPart2)  && qtyToMfg1.equals(qtytoMfgJobPart) && tempDesc2 == true 
		&& quotePrice4 == true)
		{
			jobPartDetails_2 = true;
			//System.err.println("jobPartDetails_2 is: "+jobPartDetails_2);
			System.err.println("Part 3 Verified Successfully");
		}
		else
		{
			jobPartDetails_2 = false;
			//System.err.println("jobPartDetails_2 is: "+jobPartDetails_2);
			System.err.println("Part 3 Failed to Verify Successfully");
		}

		/*_driver.navigate().back();
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.navigate().refresh();
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Click on Part Info");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MIS_Job_Parts_Info))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(1000);
		 */		 
		return jobPartDetails_2; 

	}	
		
	public boolean VerifyParentJobProduct (String sKitName, String comboItemTemplate, String sJobValue) throws Exception
	{
		boolean tempDesc = false; 
		boolean parentPro = false;
		boolean jobCost_1 = false;
		boolean jobProductDetails_1 = false;
		boolean jobProductDetails_2 = false;
		boolean jobProductDetails_3 = false;
		boolean jobProductDetails_4 = false;
		boolean jobProductDetails_5 = false;
		
		System.out.println("*********Verify Job Product Details*********");
			
		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Entering the Job Product");
		
		//Verify the details in Kit Job Product
		//Verify description, qty remaining, Item Template
		//Assuming that PaceConnect has default Item Template set as DEFDSFVAR
		String desc = _driver.findElement(By.name("description")).getAttribute("value");
		System.out.println("desc: " +desc);
		
		String itemTemplate = _driver.findElement(By.xpath("//div[@id='contents']/div[1]/div[4]/div/div/div[1]/a")).getText().trim();
		comboItemTemplate = comboItemTemplate +" - ComboItemTemplate";
		System.err.println("comboItemTemplate: "+comboItemTemplate);
		System.err.println("itemTemplate is: "+itemTemplate);
		
		if(itemTemplate.equals(comboItemTemplate))
		{
			tempDesc = true;
		}
		else
		{
			tempDesc = false;
		}
	
		//Parent Job Product
		String parentJobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("parentJobProduct"));
		System.out.println("parentJobProduct is: "+parentJobProduct);
		if(parentJobProduct.equals(""))
		{
			parentPro = true;
			System.err.println("parentPro: " +parentPro);
		}
		else
		{
			parentPro = false;
			System.err.println("parentPro: " +parentPro);
		}
		
		
		String qtyRemaining = _driver.findElement(By.name("qtyRemaining")).getAttribute("value");
		String qtyOrdered = _driver.findElement(By.name("qtyOrdered")).getAttribute("value");
		String qtyToManufacture = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("qtyRemaining: " +qtyRemaining);
		System.out.println("qtyOrdered: " +qtyOrdered);
		System.out.println("qtyToManufacture: " +qtyToManufacture);
		
		String productValue = _driver.findElement(By.name("productValue")).getAttribute("value");
		System.out.println("productValue: " +productValue);
		System.out.println("jobValue: " +sJobValue);
		
		if(productValue.equals(sJobValue))
		{
			jobCost_1 = true;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		else
		{
			jobCost_1 = false;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		
		System.out.println("parentPro is: "+parentPro);
		System.out.println("jobCost_1 is: "+jobCost_1);
		System.out.println("desc is: "+desc);
		System.out.println("sKitName is: "+sKitName);
		System.out.println("qtyRemaining is: "+qtyRemaining);
		System.out.println("qtyOrdered is: "+qtyOrdered);
		System.out.println("qtyToManufacture is: "+qtyToManufacture);
		System.out.println("tempDesc is: "+tempDesc);
		
		if(parentPro == true && jobCost_1 == true && desc.equals(sKitName) && qtyRemaining.equals("25") 
				&& qtyOrdered.equals("25") && qtyToManufacture.equals("25") && tempDesc == true) 
		{
			jobProductDetails_1 = true;
			System.err.println("Check 1 Passed");
		}
		else
		{
			jobProductDetails_1 = false;
			System.err.println("Check 1 failed");
		}
			
		//Associated Job Products
		//_driver.findElement(By.xpath("//div/fieldset[2]/legend/span")).click();
		boolean jobProduct_9 = CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='jobProduct']/tbody/tr/td[2]/div"));
		System.out.println("jobProduct_9 is: " +jobProduct_9);
		if(jobProduct_9 == true)
		{
			String childJobProduct = _driver.findElement(By.xpath("//table[@id='jobProduct']/tbody/tr/td[3]/div")).getText();
			System.out.println("childJobProduct is: "+childJobProduct);
			if(childJobProduct.equals("ComboItemTemplate"))
			{
				jobProductDetails_2 = true;
				System.err.println("Check 2 Passed");
			}
			else
			{
				jobProductDetails_2 = false;
				System.err.println("Check 2 failed");
			}
		}
		else
		{
			jobProductDetails_2 = false;
			System.err.println("Check 2 failed");
		}
		
		
		//Get the total no. of Job Parts for this Product 
		//int totalJobParts =  _driver.findElements(By.xpath("//fieldset[@id='jobParts_fieldset']/div/div[1]/div[1]/strong")).size();
		int totalJobParts =  _driver.findElements(By.xpath("//table[@id='jobParts']/tbody/tr")).size();
		Thread.sleep(1000);
		System.err.println("Total Job Parts for this Product: " +totalJobParts);
		
		String jobProName[] = {"0","0","0"};
		String jobPart[] = {"0","0","0"};
		
		if(totalJobParts == 1)
		{
			for(int j=0; j<1; j++)
			{
				System.out.println("j: " +j);
				jobProName[j] = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[3]/select"));
				jobPart[j] = _driver.findElement(By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[4]/input")).getAttribute("value").trim();
				
				System.out.println("jobProName: " +jobProName[j]);
				//System.out.println("jobProName[1]: " +jobProName[1]);
				//System.out.println("jobProName[2]: " +jobProName[2]);
				
				System.out.println("jobPart: " +jobPart[j]);
				//System.out.println("jobPart[1]: " +jobPart[1]);
				//System.out.println("jobPart[2]: " +jobPart[2]);
			}
		}
		else
		{
			System.err.println("Total Job Parts are incorrect");
		}
		
		//if(jobProName[0].equals(sKitName[i]) && jobProName[1].equals(sKitName[i]) && jobProName[2].equals(sKitName[i]))
		if(jobProName[0].equals(sKitName))
		{
			jobProductDetails_3 = true;
			System.err.println("Check 3 Passed");
		}
		else
		{
			jobProductDetails_3 = false;
			System.err.println("Check 3 failed");
		}
		
		//if(jobPart[0].equals(sKitName[i]) && jobPart[1].equals(sStaticProduct[i]) && jobPart[2].equals(sNppProduct))
		if(jobPart[0].equals(sKitName))
		{
			jobProductDetails_4 = true;
			System.err.println("Check 4 Passed");
		}
		else
		{
			jobProductDetails_4 = false;
			System.err.println("Check 4 failed");
		}
		
		System.err.println("jobProductDetails_1 is: "+jobProductDetails_1);
		System.err.println("jobProductDetails_2 is: "+jobProductDetails_2);
		System.err.println("jobProductDetails_3 is: "+jobProductDetails_3);
		System.err.println("jobProductDetails_4 is: "+jobProductDetails_4);
		
		if(jobProductDetails_1 == true && jobProductDetails_2 == true && jobProductDetails_3 == true && jobProductDetails_4 == true)
		{
			jobProductDetails_5 = true;
			System.err.println("Check 5 Passed");
		}
		else
		{
			jobProductDetails_5 = false;
			System.err.println("Check 5 failed");
		}
		
		return jobProductDetails_5;
	}
	
	public boolean VerifyChildJobProduct (String sKitName, String part1Desc, String part2Desc) throws Exception
	{
		boolean tempDesc = false; 
		boolean parentPro = false;
		boolean jobCost_1 = false;
		boolean jobProductDetails_1 = false;
		boolean jobProductDetails_2 = false;
		boolean jobProductDetails_3 = false;
		boolean jobProductDetails_4 = false;
		boolean jobProductDetails_5 = false;
		
		System.out.println("*********Verify Job Product Details*********");
			
		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Entering the Job Product");
		
		//Verify the details in Kit Job Product
		//Verify description, qty remaining, Item Template
		//Assuming that PaceConnect has default Item Template set as DEFDSFVAR
		String desc = _driver.findElement(By.name("description")).getAttribute("value");
		System.out.println("desc: " +desc);
		
		String itemTemplate = _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[7]/div[1]/div[4]/div/div/div")).getText().trim();
		System.err.println("itemTemplate is: "+itemTemplate);
		
		if(itemTemplate.equals(""))
		{
			tempDesc = true;
		}
		else
		{
			tempDesc = false;
		}
	
		//Parent Job Product
		String parentJobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("parentJobProduct"));
		if(parentJobProduct.equals(sKitName))
		{
			parentPro = true;
			System.err.println("parentPro: " +parentPro);
		}
		else
		{
			parentPro = false;
			System.err.println("parentPro: " +parentPro);
		}
		
		
		String qtyRemaining = _driver.findElement(By.name("qtyRemaining")).getAttribute("value");
		String qtyOrdered = _driver.findElement(By.name("qtyOrdered")).getAttribute("value");
		String qtyToManufacture = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("qtyRemaining: " +qtyRemaining);
		System.out.println("qtyOrdered: " +qtyOrdered);
		System.out.println("qtyToManufacture: " +qtyToManufacture);
		
		String productValue = _driver.findElement(By.name("productValue")).getAttribute("value");
		System.out.println("productValue: " +productValue);
				
		if(productValue.equals(""))
		{
			jobCost_1 = true;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		else
		{
			jobCost_1 = false;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		
	
		if(parentPro == true && jobCost_1 == true && desc.equals("ComboItemTemplate") && qtyRemaining.equals("25") && qtyOrdered.equals("25") && qtyToManufacture.equals("25") && tempDesc == true) 
		{
			jobProductDetails_1 = true;
			System.err.println("Check 1 Passed");
		}
		else
		{
			jobProductDetails_1 = false;
			System.err.println("Check 1 failed");
		}
			
		//Associated Job Products
		//_driver.findElement(By.xpath("//div/fieldset[2]/legend/span")).click();
		boolean jobProduct_9 = CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='jobProduct']/tbody/tr/td[2]/div"));
		System.out.println("isChildProductPresent: " +jobProduct_9);
		if(jobProduct_9 == false)
		{
			jobProductDetails_2 = true;
			System.err.println("Check 2 Passed");
		}
		else
		{
			jobProductDetails_2 = false;
			System.err.println("Check 2 failed");
		}
		
		
		//Get the total no. of Job Parts for this Product 
		//int totalJobParts =  _driver.findElements(By.xpath("//fieldset[@id='jobParts_fieldset']/div/div[1]/div[1]/strong")).size();
		int totalJobParts =  _driver.findElements(By.xpath("//table[@id='jobParts']/tbody/tr")).size();
		Thread.sleep(1000);
		System.err.println("Total Job Parts for this Product: " +totalJobParts);
		
		String jobProName[] = {"0","0","0"};
		String jobPart[] = {"0","0","0"};
		
		if(totalJobParts == 2)
		{
			for(int j=0; j<2; j++)
			{
				System.out.println("j: " +j);
				jobProName[j] = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[3]/select"));
				jobPart[j] = _driver.findElement(By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[4]/input")).getAttribute("value").trim();
				
				System.out.println("jobProName: " +jobProName[j]);
				//System.out.println("jobProName[1]: " +jobProName[1]);
				//System.out.println("jobProName[2]: " +jobProName[2]);
				
				System.out.println("jobPart: " +jobPart[j]);
				//System.out.println("jobPart[1]: " +jobPart[1]);
				//System.out.println("jobPart[2]: " +jobPart[2]);
			}
		}
		else
		{
			System.err.println("Total Job Parts are incorrect");
		}
		
		
		if(jobProName[0].equals("ComboItemTemplate") && jobProName[1].equals("ComboItemTemplate"))
		{
			jobProductDetails_3 = true;
			System.err.println("Check 3 Passed");
		}
		else
		{
			jobProductDetails_3 = false;
			System.err.println("Check 3 failed");
		}
		
		if(jobPart[0].equals(part1Desc) && jobPart[1].equals(part2Desc))
		{
			jobProductDetails_4 = true;
			System.err.println("Check 4 Passed");
		}
		else
		{
			jobProductDetails_4 = false;
			System.err.println("Check 4 failed");
		}
		
		
		if(jobProductDetails_1 == true && jobProductDetails_2 == true && jobProductDetails_3 == true && jobProductDetails_4 == true)
		{
			jobProductDetails_5 = true;
			System.err.println("Check 5 Passed");
		}
		else
		{
			jobProductDetails_5 = false;
			System.err.println("Check 5 failed");
		}
		
		return jobProductDetails_5;
	}	
	
	public void GangingJobsandUpdate()
	{
	    _driver.findElement(By.name("gangableBooleanCheck")).click();
	    _driver.findElement(By.name("updateForm")).click();
	    CommonFunctions.waitForPageLoaded(_driver);
	    if (_driver.findElements(By.xpath(".//*[@id='fmessage']/ul/li[text()='Updated ']")).size()>0)
	    {
	    	System.out.println("Gangable checkbox is checked and updated Succesfully");
	    }
	    else
	    {
	     	System.err.println("Gangable checkbox is not checked and updated Succesfully");
	    }
	    
	    
	}
	public boolean VerifyChildJobProduct_1 (String sKitName, String part1Desc, String part2Desc, String part3Desc, String part4Desc) throws Exception
	{
		boolean tempDesc = false; 
		boolean parentPro = false;
		boolean jobCost_1 = false;
		boolean jobProductDetails_1 = false;
		boolean jobProductDetails_2 = false;
		boolean jobProductDetails_3 = false;
		boolean jobProductDetails_4 = false;
		boolean jobProductDetails_5 = false;
		
		System.out.println("*********Verify Job Product Details*********");
			
		Thread.sleep(1000);
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Entering the Job Product");
		
		//Verify the details in Kit Job Product
		//Verify description, qty remaining, Item Template
		//Assuming that PaceConnect has default Item Template set as DEFDSFVAR
		String desc = _driver.findElement(By.name("description")).getAttribute("value");
		System.out.println("desc: " +desc);
		
		String itemTemplate = _driver.findElement(By.xpath("//div[@id='scrollableContent']/div[7]/div[1]/div[4]/div/div/div")).getText().trim();
		System.err.println("itemTemplate is: "+itemTemplate);
		
		if(itemTemplate.equals(""))
		{
			tempDesc = true;
		}
		else
		{
			tempDesc = false;
		}
	
		//Parent Job Product
		String parentJobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("parentJobProduct"));
		if(parentJobProduct.equals(sKitName))
		{
			parentPro = true;
			System.err.println("parentPro: " +parentPro);
		}
		else
		{
			parentPro = false;
			System.err.println("parentPro: " +parentPro);
		}
		
		
		String qtyRemaining = _driver.findElement(By.name("qtyRemaining")).getAttribute("value");
		String qtyOrdered = _driver.findElement(By.name("qtyOrdered")).getAttribute("value");
		String qtyToManufacture = _driver.findElement(By.name("qtyToMfg")).getAttribute("value");
		System.out.println("qtyRemaining: " +qtyRemaining);
		System.out.println("qtyOrdered: " +qtyOrdered);
		System.out.println("qtyToManufacture: " +qtyToManufacture);
		
		String productValue = _driver.findElement(By.name("productValue")).getAttribute("value");
		System.out.println("productValue: " +productValue);
				
		if(productValue.equals(""))
		{
			jobCost_1 = true;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		else
		{
			jobCost_1 = false;
			System.out.println("jobCost_1: " +jobCost_1);
		}
		
	
		if(parentPro == true && jobCost_1 == true && desc.equals("ComboItemTemplate") && qtyRemaining.equals("25") && qtyOrdered.equals("25") && qtyToManufacture.equals("25") && tempDesc == true) 
		{
			jobProductDetails_1 = true;
			System.err.println("Check 1 Passed");
		}
		else
		{
			jobProductDetails_1 = false;
			System.err.println("Check 1 failed");
		}
			
		//Associated Job Products
		//_driver.findElement(By.xpath("//div/fieldset[2]/legend/span")).click();
		boolean jobProduct_9 = CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='jobProduct']/tbody/tr/td[2]/div"));
		System.out.println("isChildProductPresent: " +jobProduct_9);
		if(jobProduct_9 == false)
		{
			jobProductDetails_2 = true;
			System.err.println("Check 2 Passed");
		}
		else
		{
			jobProductDetails_2 = false;
			System.err.println("Check 2 failed");
		}
		
		
		//Get the total no. of Job Parts for this Product 
		//int totalJobParts = _driver.findElements(By.xpath("//fieldset[@id='jobParts_fieldset']/div/div[1]/div[1]/strong")).size();
		int totalJobParts = _driver.findElements(By.xpath("//table[@id='jobParts']/tbody/tr")).size();
		Thread.sleep(1000);
		System.err.println("Total Job Parts for this Product: " +totalJobParts);
		
		String jobProName[] = {"0","0","0","0"};
		String jobPart[] = {"0","0","0","0"};
		
		if(totalJobParts == 4)
		{
			for(int j=0; j<totalJobParts; j++)
			{
				System.out.println("j: " +j);
				jobProName[j] = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[3]/select"));
				jobPart[j] = _driver.findElement(By.xpath("//table[@id='jobParts']/tbody/tr["+(j+1)+"]/td[4]/input")).getAttribute("value").trim();
				
				System.out.println("jobProName: " +jobProName[j]);
				//System.out.println("jobProName[1]: " +jobProName[1]);
				//System.out.println("jobProName[2]: " +jobProName[2]);
				
				System.out.println("jobPart: " +jobPart[j]);
				//System.out.println("jobPart[1]: " +jobPart[1]);
				//System.out.println("jobPart[2]: " +jobPart[2]);
			}
		}
		else
		{
			System.err.println("Total Job Parts are incorrect");
		}
		
		
		if(jobProName[0].equals("ComboItemTemplate") && jobProName[1].equals("ComboItemTemplate")  && jobProName[2].equals("ComboItemTemplate")  && jobProName[3].equals("ComboItemTemplate"))
		{
			jobProductDetails_3 = true;
			System.err.println("Check 3 Passed");
		}
		else
		{
			jobProductDetails_3 = false;
			System.err.println("Check 3 failed");
		}
		
		if(jobPart[0].equals(part1Desc) && jobPart[1].equals(part2Desc)  && jobPart[2].equals(part3Desc)  && jobPart[3].equals(part4Desc))
		{
			jobProductDetails_4 = true;
			System.err.println("Check 4 Passed");
		}
		else
		{
			jobProductDetails_4 = false;
			System.err.println("Check 4 failed");
		}
		
		
		if(jobProductDetails_1 == true && jobProductDetails_2 == true && jobProductDetails_3 == true && jobProductDetails_4 == true)
		{
			jobProductDetails_5 = true;
			System.err.println("Check 5 Passed");
		}
		else
		{
			jobProductDetails_5 = false;
			System.err.println("Check 5 failed");
		}
		
		return jobProductDetails_5;
	}

	//Added By Aravinth WC6
	public void Navigate_TO_RequiredPage(String pageName) throws Exception 
	{
		String pageNameLink=pageName.trim();
		String pageNameLink_Xpath="//a[text()='"+pageNameLink+"']";
		CommonFunctions.Wait(_driver, By.xpath(pageNameLink_Xpath));
		if(_driver.findElements(By.xpath(pageNameLink_Xpath)).size()>0)
		{
			System.out.println(pageNameLink+" Link available in Admin Home page");
			try
			{
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(pageNameLink_Xpath));
				System.out.println("Clicked on " +pageNameLink+" Link");
				CommonFunctions.waitForPageLoaded(_driver);
			}
			catch(StaleElementReferenceException e1)
			{
				CommonFunctions.waitForPageLoaded(_driver);Thread.sleep(3000);
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(pageNameLink_Xpath));
				System.out.println("Clicked on " +pageNameLink+" Link");
				CommonFunctions.waitForPageLoaded(_driver);
			}
			
		}
		else
		{
			System.err.println(pageNameLink+" Link is not availble in Admin Home Page");
		}
		
	}
	
	public void ChoosePrintshop(String printShopName) throws Exception
	{
		String selectPrintShop_xpath=".//*[contains(@id,'DropDownListFacilities')]";
		CommonFunctions.Wait(_driver, By.xpath(selectPrintShop_xpath));
		CommonFunctions.selectDropdownByText(_driver, By.xpath(selectPrintShop_xpath), printShopName);
		System.out.println(printShopName+ " : Print shop selected");
	}
	
	public void ChooseSearchcriteria(String criteria) throws Exception
    {
		String filterBy_xpath=".//*[contains(@id,'OrderViewHeader_DropDownListFilterBy')]";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdownByText(_driver, By.xpath(filterBy_xpath), criteria);
		CommonFunctions.waitForPageLoaded(_driver);
	}
	
	public void SearchTheOrdrNumber(String orderNum) throws Exception
	{
		String ordersearchTextBox=".//*[contains(@id,'OrderViewHeader_TextBoxFilter')]";
		String orderSearchButton_xpath=".//*[contains(@id,'OrderViewHeader_ButtonFilter')]";
		String orderLink_xpath="//*[contains(@id,'HyperLinkOrderNumber')]";
		CommonFunctions.enterText(_driver, By.xpath(ordersearchTextBox), orderNum);
		CommonFunctions.ClickOnElement(_driver, By.xpath(orderSearchButton_xpath));
		CommonFunctions.waitForPageLoaded(_driver);
		if(_driver.findElements(By.xpath(orderLink_xpath)).size()==1)
		{
			System.out.println("Searched order is present");
		}
		else
		{
			System.err.println("Searched order is not present");
		}
		
	}
	
	public void clickOn_OrderHyperLink() throws Exception
	{
		String orderLink_xpath="//*[contains(@id,'HyperLinkOrderNumber')]";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(orderLink_xpath));
		CommonFunctions.waitForPageLoaded(_driver);
	}
	
	public void ClickOn_QuoteLinkBasedOnOrderNumber(String OrderNum) throws Exception
	{
		//String PriceLinkXpath="//a[text()='2268']/../../../../../..//*[contains(@id,'OrderViewItem_HyperlinkPrice')]";
		String PriceLinkXpath="//a[text()="+OrderNum+"]/../../../../../..//*[contains(@id,'OrderViewItem_HyperlinkPrice')]";
//		//CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(PriceLinkXpath));
//		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
//		_driver.findElement(By.xpath(PriceLinkXpath)).sendKeys(selectLinkOpeninNewTab);
//		CommonFunctions.waitForPageLoaded(_driver);
		//Incomplete
		
		WebElement link = _driver.findElement(By.xpath(PriceLinkXpath));
		Actions newWindow = new Actions(_driver);
		newWindow.keyDown(Keys.SHIFT).click(link).keyUp(Keys.SHIFT).build().perform();
		
	}
	
	
	public void switchTo_RequiredPage(String pageName) throws InterruptedException
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		Thread.sleep(10000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{      

				if(_driver.switchTo().window(windowId).getTitle().equals(pageName)) 
				{	
					String sPageTitle = _driver.getTitle();
					System.out.println("Switched the control  to browser :"+sPageTitle);
					//	                    		    assertTrue(isTextPresent(printOptionName));
					//	                    		    System.out.println(printOptionName + "is present at PJT");
				}
			}
		}

	}
	
	
	public void switchTo_RespectivePage(String Pagetitle) throws InterruptedException
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		Thread.sleep(10000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{      

				if(_driver.switchTo().window(windowId).getTitle().equals(Pagetitle)) 
				{	
					String sPageTitle = _driver.getTitle();
					System.out.println("Switched the control from the parent browser to child browser :"+sPageTitle);
					//	                    		    assertTrue(isTextPresent(printOptionName));
				}
			}
		}

	}
	
	
	public void enter_Price_for_FirstProduct(String price) throws Exception
	{
		String priceTextBox_Xpath=".//*[@id='ctl00_ctl00_C_M_SummaryControl_DataGridItemSummary_ctl02_TextBoxPrice']";
		CommonFunctions.enterText(_driver, By.xpath(priceTextBox_Xpath), price);
		System.out.println("Price enetered");
	}
	
	public void enter_Price_For_SecondPricetext(String price) throws Exception
	{
		String secondPrice_textBox=".//*[@id='ctl00_ctl00_C_M_SummaryControl_DataGridItemSummary_ctl03_TextBoxPrice']";
		CommonFunctions.enterText(_driver, By.xpath(secondPrice_textBox), price);
		System.out.println("Price enetered");
	}

	public void enter_price_for_firstComponent(String price) throws Exception
	{
		String ComponentPrice_Textbox=".//*[@id='ctl00_ctl00_C_M_DetailControl_DataGridQuoteItems_ctl02_TextBoxComponentPrice']";
		CommonFunctions.enterText(_driver, By.xpath(ComponentPrice_Textbox), price);
		System.out.println("Price enetered");
	}
	
	public void clickOn_refrfeshButton()
	{
		_driver.findElement(By.id(Locators.getProperty(Locators. Quote_Refresh))).click();
		System.out.println("Refresh button is clicked..");
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public void click_on_SaveChangesButton() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		_driver.findElement(By.id(Locators.getProperty(Locators. Quote_Save))).click();
		System.out.println("Save button is clicked.");
		Thread.sleep(7000);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.id(Locators.getProperty(Locators. Quote_SuccessMessage)));
		if(_driver.findElements(By.id(Locators.getProperty(Locators. Quote_SuccessMessage))).size() > 0 )
		{
			System.out.println("quote order is succefully saved..");
		}                    		 
	}
	
	public String fetchPrice(String labelName)
	{
		String reuiredLabelValue="//span[text()='"+labelName+"']/../..//td[3]/span";
		CommonFunctions.waitForPageLoaded(_driver);
		String value=_driver.findElement(By.xpath(reuiredLabelValue)).getText().trim();
		return  value;
	}
	
	 public boolean Compare_Pricing(String expected,String Actual)
	  {
		  if(expected.equalsIgnoreCase(Actual))
		  {
			  System.out.println("Prices are same");
			 return true; 
		  }
		  else
		  {
			  System.err.println("Prices are not same expected is "+expected + " actual is "+Actual );
			  return false;
		  }
	  }
	
	
	
	
}	