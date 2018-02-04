package com.gui_auto.pages;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;


public class UIPage implements BaseElement{
	
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
	public  UIPage(WebDriver driver) throws Exception 
	 {  
	        super();
	        this._driver = driver;       
	 }


	
	public boolean onPage() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}
	public String  Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		//DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
        Date date = new Date();
        String suniqueNumber = dateFormat.format(date);
        
		return suniqueNumber;
	}

	@Override
	public boolean waitForPage() {
		// TODO Auto-generated method stub
		return false;
	}  
	
	
	public void NavigatetoSearchlistPage(String Object)throws Exception,IOException	
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/"+Object+"/list");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
	}
	public String GetData(String DataName)throws Exception,IOException	
	{	String HeaderName,DataValue="";
		Integer	 Size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/thead/tr/th")).size();
		for (int i = 3; i <= Size; i++) {
			
			HeaderName=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/thead/tr/th["+i+"]/a/span"));
			if(HeaderName.equalsIgnoreCase(DataName))
				DataValue=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td["+i+"]/div"));
			{
			}	return 	DataValue;
		}
		return null;
	}
	
	public String GetFirstRowValue(String DataName)throws Exception,IOException	
	{	String HeaderName,DataValue="";
		Integer	 Size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/thead/tr/th")).size();
		for (int i = 3; i <= Size; i++) {
			
			HeaderName=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/thead/tr/th["+i+"]/a/span"));
			if(HeaderName.equalsIgnoreCase(DataName))
			{
				DataValue=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td["+i+"]/div"));
				return 	DataValue;
			}	
		}
		return null;
	}
	
	public String GetFirstRowValueBySelect(String DataName)throws Exception,IOException	
	{	String HeaderName,DataValue="";
		Integer	 Size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/thead/tr/th")).size();
		for (int i = 3; i <= Size; i++) {
			
			HeaderName=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/thead/tr/th["+i+"]/a/span"));
			if(HeaderName.equalsIgnoreCase(DataName))
			{
				int size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td["+i+"]/div[@class='viewContainer']")).size();
				
				if(size==0)
				{
				DataValue=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td["+i+"]/div"));
				}
				else
				{
					List<WebElement> Elementlist=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr[1]/td[3]/div[@class='viewContainer']/preceding-sibling::*"));
					
					if(Elementlist.size()==1)
					{
						if(Elementlist.get(0).getTagName().contains("input")  && Elementlist.get(0).getAttribute("type").equals("text"))					
						{
							DataValue=Elementlist.get(0).getAttribute("value");
							if(DataValue.equals(null))
							{
								DataValue=Elementlist.get(0).getText();
							}
							
						}
						else if(Elementlist.get(0).getTagName().contains("input") && Elementlist.get(0).getAttribute("type").equals("text"))
						{
							DataValue=Elementlist.get(0).getAttribute("value");
							if(DataValue.equals(null))
							{
								DataValue=Elementlist.get(0).getText();
							}
							
						}
						else if(Elementlist.get(0).getTagName().contains("div"))
						{

							DataValue=Elementlist.get(0).getText();

							
						}
						else if(Elementlist.get(0).getTagName().contains("textarea"))
						{

							DataValue=Elementlist.get(0).getText();

							
						}

						else if(Elementlist.get(0).getTagName().contains("select"))
						{
							Select sele = new Select(Elementlist.get(0));
							String sOption = sele.getFirstSelectedOption().getText();

							DataValue= sOption;
						}
					}
				}
				return 	DataValue;
			}	
		}
		return null;
	}
	
	public String GetSecondRowValue(String DataName)throws Exception,IOException	
	{	String HeaderName,DataValue="";
		Integer	 Size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/thead/tr/th")).size();
		for (int i = 3; i <= Size; i++) {
			
			HeaderName=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/thead/tr/th["+i+"]/a/span"));
			if(HeaderName.equalsIgnoreCase(DataName))
			{
				int size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr[2]/td["+i+"]/div[@class='viewContainer']")).size();
				
				if(size==0)
				{
				DataValue=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr[2]/td["+i+"]/div"));
				}
				else
				{
					List<WebElement> Elementlist=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr[2]/td[3]/div[@class='viewContainer']/preceding-sibling::*"));
					
					if(Elementlist.size()==1)
					{
						if(Elementlist.get(0).getTagName().contains("input")  && Elementlist.get(0).getAttribute("type").equals("text"))					
						{
							DataValue=Elementlist.get(0).getAttribute("value");
							if(DataValue.equals(null))
							{
								DataValue=Elementlist.get(0).getText();
							}
							
						}
						else if(Elementlist.get(0).getTagName().contains("input") && Elementlist.get(0).getAttribute("type").equals("text"))
						{
							DataValue=Elementlist.get(0).getAttribute("value");
							if(DataValue.equals(null))
							{
								DataValue=Elementlist.get(0).getText();
							}
							
						}
						else if(Elementlist.get(0).getTagName().contains("div"))
						{

							DataValue=Elementlist.get(0).getText();

							
						}
						else if(Elementlist.get(0).getTagName().contains("textarea"))
						{

							DataValue=Elementlist.get(0).getText();

							
						}

						else if(Elementlist.get(0).getTagName().contains("select"))
						{
							Select sele = new Select(Elementlist.get(0));
							String sOption = sele.getFirstSelectedOption().getText();

							DataValue= sOption;
						}
					}
				}
				return 	DataValue;
			}	
		}
		return null;
	}
	
	
	public Boolean CheckAllRowData(String DataName,String Rowvalue)throws Exception,IOException		
	{	
		String HeaderName,DataValue="";
		Boolean headerFoundBoolean=false;
		Integer	 Size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/thead/tr/th")).size();
		for (int i = 3; i <= Size; i++)
		{
			HeaderName=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/thead/tr/th["+i+"]/a/span"));			
			Integer	 RowSize=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr")).size();
			for (int j = 1; j <= RowSize; j++) 
			{
				if(HeaderName.equalsIgnoreCase(DataName))					
				{
					headerFoundBoolean=true;
					int size=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr["+j+"]/td["+i+"]//div[@class='viewContainer']")).size();					
					if(size==0)
					{
						DataValue=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='appbox_implicit']/tbody/tr["+j+"]/td["+i+"]/div"));
						System.out.println("Value is found in the grid "+DataValue);
					}
					else
					{
						List<WebElement> Elementlist=_driver.findElements(By.xpath(".//*[@id='appbox_implicit']/tbody/tr["+j+"]/td[3]//div[@class='viewContainer']/preceding-sibling::*"));
						if(Elementlist.size()==1)
						{
							if(Elementlist.get(0).getTagName().contains("input")  && Elementlist.get(0).getAttribute("type").equals("text"))					
							{
								DataValue=Elementlist.get(0).getAttribute("value");
								if(DataValue.equals(null))
								{
									DataValue=Elementlist.get(0).getText();
									System.out.println("Value is found in the grid "+DataValue);
								}
							}
							else if(Elementlist.get(0).getTagName().contains("input") && Elementlist.get(0).getAttribute("type").equals("text"))
							{
								DataValue=Elementlist.get(0).getAttribute("value");
								if(DataValue.equals(null))
								{
									DataValue=Elementlist.get(0).getText();
									System.out.println("Value is found in the grid "+DataValue);
								}
							}
							else if(Elementlist.get(0).getTagName().contains("div"))
							{
								DataValue=Elementlist.get(0).getText();
								System.out.println("Value is found in the grid "+DataValue);
							}
							else if(Elementlist.get(0).getTagName().contains("a"))
							{
								DataValue=Elementlist.get(0).getText();
								System.out.println("Value is found in the grid "+DataValue);
							}
							else if(Elementlist.get(0).getTagName().contains("textarea"))
							{
								DataValue=Elementlist.get(0).getText();
								System.out.println("Value is found in the grid "+DataValue);
							}
							else if(Elementlist.get(0).getTagName().contains("select"))
							{
								Select sele = new Select(Elementlist.get(0));
								String sOption = sele.getFirstSelectedOption().getText();
								DataValue= sOption;
								System.out.println("Value is found in the grid "+DataValue);
							}
						}
					}
					if(!DataValue.contains(Rowvalue))
					{	
						return false;
					}
				}	
			}
			if(headerFoundBoolean)
			{
				return true;	
			}
		}
		return false;
	}
    public boolean  Search(String sValue,String sLabel) throws Exception
    {
    	CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), sLabel);
    	Thread.sleep(1000);
   	  _driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(sValue);
   	  _driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
   	  Thread.sleep(5000);
   	  String sTitle = _driver.getTitle();
   	  
   	 System.out.println(Date()+"sTitle " + sTitle);
	  String sETitle =  _driver.getTitle();
	  if(sETitle.length()>25)
	  {
	  sETitle = sETitle.substring(25);
	  System.out.println(Date()+sETitle);
	  }
   		 if(sValue.equals(sETitle))
   		 {
   			 
   			 System.out.println(Date()+"Element Present");
   			  return true;
   		 }
   		 else
   		 {
   			 System.out.println(Date()+"Element not present");
   			 return false;
   		 }
    }
    
    
    public void  SearchValue(String sValue,String sLabel) throws Exception
    {
    	try {
			


    	CommonFunctions.selectDropdownByValue(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), sLabel);
    	Thread.sleep(1000);
  	  	_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(sValue);
  	  	_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
  	  	System.out.println("Successfully element clicked");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
