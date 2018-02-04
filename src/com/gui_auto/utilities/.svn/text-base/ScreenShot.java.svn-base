package com.gui_auto.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.GUI_automation_properties;



public class ScreenShot {
	
	public static WebDriver _driver;

	private static String ScreenShotPath = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	public String[] FinalFileName;
	String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
	File directory = new File(sFile);
	public ArrayList  FinalFilename = new ArrayList();
	
	
	public String getDestinationFile(String sPageTitle) throws IOException
	{	               
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        Date date = new Date();
        String NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ sPageTitle + dateFormat.format(date)+"_"+ ".png";   
        FinalFilename.add(NewFileNamePath);
       
       // System.out.println(NewFileNamePath);
        return NewFileNamePath;
	}

	public String ScreenShotWindow(WebDriver driver ,String sPageTitle) throws IOException
	{	               
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        Date date = new Date();
        String NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ sPageTitle + dateFormat.format(date)+"_"+ ".png";   
        FinalFilename.add(NewFileNamePath);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(NewFileNamePath));
	     System.out.println(NewFileNamePath);
        return NewFileNamePath;
	}
	
	public String ScreenShotRegion (WebDriver driver, By locator, String ScreenshotName) throws Exception
	{
		JavascriptExecutor JSE = (JavascriptExecutor) driver;		
		WebElement element = driver.findElement(locator);
		JSE.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);

		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        Date date = new Date();
        String NewFileNamePath = directory.getCanonicalPath()+ "\\ScreenShots\\"+ ScreenshotName + dateFormat.format(date)+"_"+ ".png";   
        FinalFilename.add(NewFileNamePath);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(NewFileNamePath));
	    System.out.println(NewFileNamePath);
        return NewFileNamePath;
	}

}
