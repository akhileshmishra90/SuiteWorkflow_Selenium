/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class RegisterPage implements BaseElement
{

	String NewFileNamePath = null;
	 
	 
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	ScreenShot TakeScreenShot = new ScreenShot();
	Locators loc = new Locators();
	
	
	
    private StringBuffer verificationErrors = new StringBuffer();
	private String _pageURL;
	private RegisterPage _dsf;
	protected WebDriver _driver;
	private String _DSFURL;
	

	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	protected static Locators _Locators = new Locators();
	
	public RegisterPage(final WebDriver driver) 
	{
		_driver = driver;
		_pageURL = GUI_automation_base.getUrl();
		
	}

	/**
	 * Action: Register to website
	 * 
	 */
	

	public void navigateToPageAndWait() 
	{
		_driver.get(_pageURL);
	}
	
	
	
	@Override
	public boolean waitForPage() 
	{
		return false;
	}
	public void navigateToDSFPageAndWait() 
	{
		_driver.get(_DSFURL);
	}
	
	@Override
	public boolean onPage() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}

	public void launchapplication(String url) throws Exception
	{
		_driver.get(url);
		CommonFunctions.waitForPageLoaded(_driver);	
	}
}
