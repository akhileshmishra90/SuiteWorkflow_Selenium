package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*; 

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate_Path;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class Login implements BaseElement{


	Locators loc = new Locators();
	protected WebDriver _driver;
	protected static Locators _Locators = new Locators();
	ReadAndUpdate_Path dbConnection = new ReadAndUpdate_Path();
	private String _pageURL;
	// ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	public String[] FinalFileName;
	String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
	File directory = new File(sFile); 
	public ArrayList  FinalFilename = new ArrayList();
	String sServer=_properties.getProperty(GUI_automation_properties.SERVER);

	public String TakeScreenshot(String sPageTitle) throws IOException
	{	               
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		String NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ sPageTitle + dateFormat.format(date)+"_"+ ".png";   
		FinalFilename.add(NewFileNamePath);
		System.out.println(NewFileNamePath);
		File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(NewFileNamePath));
		return NewFileNamePath;


	}
	
	// Username, password needs to be passed explicitly
	public Boolean LoginToDSF(String sSheetName, String sScenario, String sTestcase, String Username, String Password) throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
//		 CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.MyAccountResp)));
		// Login to responsive UI
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.MyAccountResp))).size() > 0)
		{
			System.out.println("My Account link found");

			System.out.println("LOGIN ");

			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.UserNameResp))).size()<=0)
			{
				for(int i=0;i<=3;i++)
				{
					CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.MyAccountResp)));
					//_driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp))).click();
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.UserNameResp))).size()>0)
					{
						System.out.println("User name field is available  after clicking on the login button on "+ i +"Attempt");
						break;
					}				
				}
			}
			
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp))).click();	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.UserNameResp))).sendKeys(Username);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.PasswordResp))).sendKeys(Password);	              

			System.out.println("CLICK ON LOGIN BUTTON");

			_driver.findElement(By.className(Locators.getProperty(Locators.LoginResp))).click();
			// Thread.sleep(15000);

			// CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SignOutResp)));


			if (_driver.findElements(By.xpath("//div[@class='dropdown-menu']/ul[@style='display: none;']/li[@ng-click='DoSignOut();']")).size() >0)
			{
				// Thread.sleep(5000);
				System.out.println("Display none");


			}
			else
			{
				System.out.println("Signing in...");
			}


			// CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SignOutResp)));
			while (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//img[@src='responsiveui/assets/images/ajax-loader.gif']"))))
			{
				Thread.sleep(2000);
			}

			if((_driver.findElements(By.xpath(Locators.getProperty(Locators.SignOutResp))).size()>0 )|| (_driver.findElements(By.linkText("Storefront")).size() > 0))
			{
				System.out.println("User logged in successfully");

				//TakeScreenshot("Responsive UI LoginPage");
				return true;
			}
			else
			{    TakeScreenshot("ResponsiveUI LoginError");
			System.err.println("User is not able to login");
			return false;
			}

		}
		else if(_driver.findElements(By.linkText("Home")).size() > 0)
		{
			System.out.println("LOGIN AS ADMIN"); 

			_driver.findElement(By.id(Locators.getProperty(Locators.Home_Login_UserName))).sendKeys(Username);
			_driver.findElement(By.id(Locators.getProperty(Locators.Home_Login_Password))).sendKeys(Password);
			// CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.Home_Login_Button)));

			System.out.println("CLICK ON LOGIN BUTTON"); 

			_driver.findElement(By.id(Locators.getProperty(Locators.Home_Login_Button))).click();
			while (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//img[@src='responsiveui/assets/images/ajax-loader.gif']"))))
			{
				Thread.sleep(2000);
			}
			// CommonFunctions.Wait(_driver, By.linkText("Storefront"));
			if(_driver.findElements(By.linkText("Storefront")).size() > 0)
			{

				assertEquals("Storefront",_driver.findElement(By.linkText("Storefront")).getText());
				//  dbConnection.UpdateFunction("Details", "DSF_CORE", "DSF_LOGIN", "EXECUTION_STATUS", "PASS");


				//TakeScreenshot("LoginPage");
				return true;
			}

			else
			{
				return false;
			}

		}
		else if( _driver.findElements(By.xpath(Locators.getProperty(Locators.UserNameResp))).size()>0)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.UserNameResp))).sendKeys(Username);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.PasswordResp))).sendKeys(Password);	              

			System.out.println("CLICK ON LOGIN BUTTON");

			_driver.findElement(By.className(Locators.getProperty(Locators.LoginResp))).click();
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(5000);
			CommonFunctions.Wait(_driver, By.xpath("//div[@class='myaccount-label']"));
			while (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//img[@src='responsiveui/assets/images/ajax-loader.gif']"))))
			{
				Thread.sleep(2000);
			}
			return true;
		}

		else
		{   System.err.println("UNABLE TO LOGIN");
		return false;
		}
	}

	public  Login(WebDriver driver) 
	{  
		super();  
		// PropertyConfigurator.configure("config/log4j.properties");
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
	
	public void Logout() throws Exception
	{
		try
		{
			JavascriptExecutor executor = (JavascriptExecutor)_driver;
			
			WebElement element = _driver.findElement(By.xpath("//div[@class='myaccount-label']/span[3]"));				
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			element = _driver.findElement(By.xpath("//ul[@class='myaccount-list']//li[text()='Logout']"));				
			executor.executeScript("arguments[0].click();", element);
		}
		catch(Exception ex)
		{
			Actions action = new Actions(_driver);
			
			WebElement account = _driver.findElement(By.xpath("//div[@class='myaccount-label']/span[3]"));
			WebElement logout = _driver.findElement(By.xpath("//ul[@class='myaccount-list']//li[text()='Logout']"));
			action.moveToElement(account).build().perform();
			Thread.sleep(2000);
			action.moveToElement(logout).click().perform();
		}
		
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
	}
}
