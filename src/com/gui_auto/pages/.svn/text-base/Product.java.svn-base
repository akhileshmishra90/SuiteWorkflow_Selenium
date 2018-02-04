package com.gui_auto.pages;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;

import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;


public class Product implements BaseElement
{

	
	
	 Locators loc = new Locators();
	 protected static Locators _Locators = new Locators();
	 ReadAndUpdate dbConnection = new ReadAndUpdate();
	
	 private String _pageURL;
	 protected WebDriver _driver;
	 boolean acceptNextAlert = true;
	 ScreenShot TakeScreenShot = new ScreenShot();
	 String NewFileNamePath = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	
	

	
	
	public  Product(WebDriver driver) throws Exception 
	 {  
	        super();  
	        PropertyConfigurator.configure("config/log4j.properties");
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
	
	public Boolean NavigateToProducts() throws Exception, IOException
	{
		
	
		try
		{
		 _driver.findElement(By.linkText("Products")).click();
		 waitForPage();
		 Thread.sleep(2000);
		
		 System.out.println(Locators.getProperty(Locators.Add_New_Product));
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {   
			 System.out.println("Navigated to Product Page successfully");
			 assertEquals("Locked items cannot be deleted or edited.","Locked items cannot be deleted or edited.",_driver.findElement(By.id("ctl00_ctl00_C_M_LabelCannotEditOrDeleteLockedItems")).getText());  
		
			 NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "Error");
	          System.out.println(NewFileNamePath);
			 return true;
         }
         else
         {
        	
        	System.err.println("Not Able To Navigate to Product Page");
      		return false;
         }
		}
		catch (AssertionError ex) 
	     {
	   
	     }
		return false;
	}
	
	public String  UniqueNum()
	{
		//DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		DateFormat dateFormat = new SimpleDateFormat("ddMMhhmmss");
        Date date = new Date();
        String suniqueNumber = dateFormat.format(date);
        
		return suniqueNumber;
	}
	
	
	
	
	public String CreateNonPricedNPPProduct(String sProductName ,String sType) throws Exception
	{

		
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 waitForPage();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
			 Thread.sleep(1000);
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys(sType);
	         Thread.sleep(1000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	         waitForPage();
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
	         waitForPage();
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_It))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).clear();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).sendKeys("Storefront");
	         Thread.sleep(2000);
	       
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Find))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Select_RadioButton))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish))).click();
	         Thread.sleep(2000);
	         
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
	         Thread.sleep(2000);
	         return sProductName;
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}
	
	
	public String CreatePricedNPPProduct(String sProductName ,String sType,String sProductId,String sStorefront,String sRegularPrice,String sSetupPrice,String Width , String Length, String Height, String Qty) throws Exception
	{

		
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 waitForPage();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
			 Thread.sleep(1000);
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys(sType);
	         Thread.sleep(1000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	         waitForPage();
	         
	         _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(sProductId);
	         ProductSettings();
	         SetSubContainerDimensions(Width,Length,Height,Qty);
	         
	         ProductPricing();
	         SetPricing(sRegularPrice,sSetupPrice);
	         
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
	         waitForPage();
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_It))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).clear();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).sendKeys(sStorefront);
	         Thread.sleep(2000);
	       
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Find))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Select_RadioButton))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish))).click();
	         Thread.sleep(2000);
	         
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
	         Thread.sleep(2000);
	         return sProductName;
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}
	public String CreateStaticProduct(String sProductName ,String ProductSku,  String File, String Template, String Storefront) throws Exception
	{

		String ProductExists = "";
		
		String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
		System.out.println(StorefrontSelection);
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
	
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys("Static Document");

	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	        
	         
	         Thread.sleep(1000);
	         
	         if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
			 {
	        	 ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
				 if(ProductExists.contains("This name already exists"))
				 {
				 
				 }
			 }
	         
	         else
		         {
		         
	        	 Thread.sleep(5000);
		         // Set product id if present
	        	 if ((ProductSku.equalsIgnoreCase("No")))
		         {
		        	 // Do nothing,  product id is not set
	        		 System.out.println("Product id will not be set");
	        		 
		         }
		        else
		         {
		        	 _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductSku);
		         }
		        	 
	        	 ProductPricing();
		         SetPricing("10","3");
	        	 
		         _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();
		         
		         Thread.sleep(5000);
		         // Upload File
		         _driver.findElement(By.id(Locators.getProperty(Locators.StaticBrowse))).sendKeys(File);
		         _driver.findElement(By.id(Locators.getProperty(Locators.StaticFileUpload))).click();
		         Thread.sleep(15000);
		         _driver.findElement(By.id(Locators.getProperty(Locators.NextFileUpload))).click();
		         
		         // Choose Ticket Template
		         CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.TTSelection)), Template);
		         
		         Thread.sleep(3000);
		         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
		         
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
		      
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
		         Thread.sleep(2000);
		         
		        
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
		       
		       
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
		         Thread.sleep(2000);
		         
		         //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
		        // CommonFunctions.selectRadioButton(_driver, By.xpath(StorefrontSelection), Storefront+"Storefront");
		       // System.out.println("radio");
		       
		        _driver.findElement(By.xpath(StorefrontSelection)).click();
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
		
		         System.out.println("publish ok");
		         
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.ProductDone))).click();
		         
		        
		         return sProductName;
		         }
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}

	
	public String CreateKitProduct(String sProductName ,String ProductSku,  String[] product_Name_to_add, String KIT_Prod_Qty, String Storefront) throws Exception
	{

		String ProductExists = "";
		
		String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
		System.out.println(StorefrontSelection);
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
	
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys("Kit");

	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	         
	         
	         Thread.sleep(1000);
	         
	         if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
			 {
	        	 ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
				 if(ProductExists.contains("This name already exists"))
				 {
				 
				 }
			 }
	         
	         else
		         {
		         
	        	 Thread.sleep(5000);
		         // Set product id if present
	        	 if ((ProductSku.equalsIgnoreCase("No")))
		         {
		        	 // Do nothing,  product id is not set
	        		 System.out.println("Product id will not be set");
	        		 
		         }
		        else
		         {
		        	 _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductSku);
		         }
		        	 
		         _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();
		         
		         Thread.sleep(5000);
		         //Add Products
		         
		         for(int i=0;i<product_Name_to_add.length;i++)
		         {
		        	 
				         WebElement addproducts =  _driver.findElement(By.id(Locators.getProperty(Locators.Kit_Add_Button)));
		                 if (CommonFunctions.Elementdisplayed_Enabled(addproducts))
		                 {
		                        addproducts.click();
		                        Thread.sleep(1000);
		                        System.out.println("add products button is clicked on Ticket template page.");
		                 }
		                 
		                 //To check that search box is available or not.
		                 WebElement Kit_Product_Search =  _driver.findElement(By.id(Locators.getProperty(Locators.Kit_Product_Search)));
		                 if (CommonFunctions.Elementdisplayed_Enabled(Kit_Product_Search))
		                 {
		                	 	Kit_Product_Search.clear();
		                        Kit_Product_Search.sendKeys(product_Name_to_add[i]);
		                        System.out.println("Product name is entered in kit search box");
		                 }
		                 else
		                 {
		                        System.err.println("product name is not entered in kit search box..");
		                 }
		                 
		                 //to click on search button in product search box.
		                 WebElement KIT_Search =  _driver.findElement(By.id(Locators.getProperty(Locators.KIT_Search)));
		                 if (CommonFunctions.Elementdisplayed_Enabled(KIT_Search))
		                 {
		                        KIT_Search.click();
		                        System.out.println("Kit search button is clicked..");
		                 }
		                 else
		                 {
		                        System.err.println("Kit search button is not clicked..");
		                 }                           
                 
		            //check whether the product button is displayed or not..
		            String Prod_xpath = "//tr[td[contains(text(),'"+product_Name_to_add[i].trim() +"')]]/td/span/input";
		            if (_driver.findElements(By.xpath(Prod_xpath)).size() > 0)
		            {
		                  _driver.findElement(By.xpath(Prod_xpath)).click();
		                  System.out.println("product need to add to Kit is selected..");
		                  _driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_Product_Add_button))).click();
		            }
		            else
		            {
		                  System.err.println("product is not added correctly..");
		            }
		            
		            String Let_Buyyer_change_Item_qty = "//tbody[tr[td[contains(text(),'"+ product_Name_to_add[i].trim()+"')]]]/tr/td/Select";
		            String Products_Per_Kit = "//tbody[tr[td[contains(text(),'"+ product_Name_to_add[i].trim()+"')]]]/tr/td/span/input";
		            
		            //To Select the qty of product
		            if (_driver.findElements(By.xpath(Products_Per_Kit)).size() > 0)
		            {
		            	System.out.println("Adding  qty to Product "+product_Name_to_add[i]);
		            		_driver.findElement(By.xpath(Products_Per_Kit)).clear();
		                 // _driver.findElement(By.xpath(Products_Per_Kit)).sendKeys(KIT_Prod_Qty);
		            		_driver.findElement(By.id("ctl00_ctl00_C_M_ctl00_W_ctl02__KitPartsViewer_ctl00__QtyPerKit")).clear();
		            		_driver.findElement(By.id("ctl00_ctl00_C_M_ctl00_W_ctl02__KitPartsViewer_ctl00__QtyPerKit")).sendKeys("10");
		            		_driver.findElement(By.id("ctl00_ctl00_C_M_ctl00_W_ctl02__KitPartsViewer_ctl0"+i+"__QtyPerKit")).clear();
		            		_driver.findElement(By.id("ctl00_ctl00_C_M_ctl00_W_ctl02__KitPartsViewer_ctl0"+i+"__QtyPerKit")).sendKeys("5");
		            }
            
		         
		         }   
		         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
		         Thread.sleep(1000);
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
		         Thread.sleep(1000);
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
		         Thread.sleep(2000);
		         
		        
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
		         Thread.sleep(1000);
		       
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
		         Thread.sleep(2000);
		         
		         //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
		        // CommonFunctions.selectRadioButton(_driver, By.xpath(StorefrontSelection), Storefront+"Storefront");
		       // System.out.println("radio");
		       
		        _driver.findElement(By.xpath(StorefrontSelection)).click();
		        Thread.sleep(1000);
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
		         Thread.sleep(1000);
		         System.out.println("publish ok");
		         
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.ProductDone))).click();
		         
		         Thread.sleep(1000);
		         return sProductName;
		         }
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}
	public String CreateAdhocProduct(String sProductName ,String ProductId,   String Template, String Storefront) throws Exception
	{

		String ProductExists = "";
		
		String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
		System.out.println(StorefrontSelection);
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
	
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys("Ad Hoc");

	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	         
	         
	         Thread.sleep(1000);
	         
	         if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
			 {
	        	 ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
				 if(ProductExists.contains("This name already exists"))
				 {
				 
				 }
			 }
	         
	         else
		         {
		         
		         // Set product id if present
		         if (ProductId.equalsIgnoreCase("empty"))
		         {
		        	 // Do nothing,  product id is not set
		         }
		         else
		         {
		        	 _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductId);
		         }
		         
		    	 //Change the Buyer User Interface from Visual Product Builder to Classic HTML Interface
				 _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_AdHoc_Settings))).click();
				 System.out.println("Clicking the Settings");
				 Thread.sleep(2000);
				 CommonFunctions.selectRadioButton(_driver, By.name(Locators.getProperty(Locators.DSF_HTML_Interface)), "False");
				 //_driver.findElement(By.name(Locators.getProperty(Locators.DSF_HTML_Interface))).click();
				 System.out.println("Clicking the Classic HTML Interface button");
				 Thread.sleep(2000);
				 _driver.findElement(By.xpath(Locators.getProperty(Locators.DSF_ADHoc_Information))).click();
				 System.out.println("Going back to the Information");
		        	 
		         _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();
		         
		        
		        
		         Thread.sleep(3000);
		         // Choose Ticket Template
		         
		         CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.TTSelection)), Template);
		         
		         Thread.sleep(3000);
		         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
		         
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
		      
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
		         Thread.sleep(2000);
		         
		        
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
		       
		       
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
		         Thread.sleep(2000);
		         
		         //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
		       
		       
		        _driver.findElement(By.xpath(StorefrontSelection)).click();
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
		
		         
		         _driver.findElement(By.xpath(Locators.getProperty(Locators.ProductDone))).click();
		        
		         return sProductName;
		         }
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}
	
	
	public void ProductSettings()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.ProductSettings))).click();
	}
	
	
	
	
	public void ProductPricing()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.ProductPricing))).click();
	}
	
	public void ProductDetails()
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.ProductDetails))).click();
	}
	
	public void SetPricing(String RegPrice, String SetupPrice)
	{
		try {
			Thread.sleep(5000);
		_driver.findElement(By.id(Locators.getProperty(Locators.ProdRegualPrice))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.ProdRegualPrice))).sendKeys(RegPrice);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.ProdSetupPrice))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.ProdSetupPrice))).sendKeys(SetupPrice);
		
		_driver.findElement(By.xpath(Locators.getProperty(Locators.CopyAll))).click();
	
			Thread.sleep(5000);
			
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PasteAll))).click();
		Thread.sleep(2000);
		//_driver.findElement(By.id(Locators.getProperty(Locators.Category_Save_And_Exit))).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void SetTAT(String Value , String Duration) throws Exception// e.g. Value = 3, Duration = Day/Hour/Week/Month
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.TATRadioValue))).click();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.TATValue))).sendKeys(Value);
		CommonFunctions.selectDropdown(_driver, By.xpath(Locators.getProperty(Locators.TATDuration)), Duration);
		Thread.sleep(2000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Category_Save_And_Exit))).click();
		
	}
	
	
	public void SetFixedQuantities(String Fixed ) throws Exception
	{
		
		_driver.findElement(By.id(Locators.getProperty(Locators.FixedQuantity))).click();
		
		_driver.findElement(By.id(Locators.getProperty(Locators.FixedLevelOne))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.FixedLevelOne))).sendKeys(Fixed);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.FixedUpdateOne))).click();
		
	}
	
	public void SetKeywords(String Keywords) throws Exception 
	{
		_driver.findElement(By.id(Locators.getProperty(Locators.ProductKeywords))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.ProductKeywords))).sendKeys(Keywords);
		
	}
	
	
	public void SetMultipleQuantities(String Min , String Max, String Multiple, String AllowEdit) throws Exception 
	{
		
		_driver.findElement(By.id(Locators.getProperty(Locators.ByMultiples))).click();
		
		Thread.sleep(2000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Minimum))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Minimum))).sendKeys(Min);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.Maximum))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Maximum))).sendKeys(Max);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.Multiple))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Multiple))).sendKeys(Multiple);
		
		if (AllowEdit.equalsIgnoreCase("Yes"))
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.id(Locators.getProperty(Locators.AllowBuyerToEdit)));
		}
	}
	
	public void SetSubContainerDimensions(String Width , String Length, String Height, String Qty) throws Exception// e.g. Value = 3, Duration = Day/Hour/Week/Month
	{
		
		_driver.findElement(By.id(Locators.getProperty(Locators.SubWidth))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.SubWidth))).sendKeys(Width);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.SubLength))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.SubLength))).sendKeys(Length);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.SubHeight))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.SubHeight))).sendKeys(Height);
		
		_driver.findElement(By.id(Locators.getProperty(Locators.QtyPerPack))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.QtyPerPack))).sendKeys(Qty);
		
	}
	
	public void EditProduct(String sProduct) throws Exception, IOException
	{
		NavigateToProducts();
		Thread.sleep(5000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search))).sendKeys(sProduct);
		_driver.findElement(By.name(Locators.getProperty(Locators.DSF_Company_Search_Button))).click();
		Thread.sleep(5000);
		_driver.findElement(By.xpath("//a[text()='"+sProduct+"']")).click();
		Thread.sleep(5000);
	}
	
	public String CreateNonPricedNPPProduct_WithImages(String sProductName ,String sType) throws Exception
	{

		
	try
	{
		
		 if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
         {  
			 _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
			 waitForPage();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
			 _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
			 Thread.sleep(1000);
			 WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
	         selectallprintshops.sendKeys(sType);
	         Thread.sleep(1000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
	         waitForPage();
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
	         waitForPage();
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_It))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).clear();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish_Search))).sendKeys("Storefront");
	         Thread.sleep(2000);
	       
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Find))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Select_RadioButton))).click();
	         Thread.sleep(2000);
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Publish))).click();
	         Thread.sleep(2000);
	         
	         _driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
	         Thread.sleep(2000);
	         return sProductName;
         }
		 
		 else
		 {
			 System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
			 return null;
         }
	}
	 catch (AssertionError ex) 
     {
		 System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
      }
	return sProductName;
	}	
	
	public String CreateStaticProduct_withMultipleFiles(String sProductName ,String ProductId,  String File, String Template, String Storefront) throws Exception
    {

           String ProductExists = "";
           
           String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
           System.out.println(StorefrontSelection);
    try
    {
           
            if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
      {  
                   _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
                   
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
    
                   WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
             selectallprintshops.sendKeys("Static Document");

             _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
             
             
             Thread.sleep(1000);
             
             if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
                   {
                   ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
                         if(ProductExists.contains("This name already exists"))
                         {
                         
                         }
                   }
             
             else
                    {
                    
                    // Set product id if present
                    if (ProductId.isEmpty())
                    {
                          // Do nothing,  product id is not set
                    }
                    else
                    {
                          _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductId);
                    }
                          
                    _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();

                    //================================================================================
                    // Upload File
                    //To Select mulitple files..
                    //================================================================================
                    if (File.contains(","))
                    {
                          String[] File_to_be_Upload = File.split(",");                        
                            
                    for (int i =0; i<=File_to_be_Upload.length-1;i++)
                    {                   
                    Thread.sleep(1000);
                    CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.StaticBrowse)));
                    _driver.findElement(By.id(Locators.getProperty(Locators.StaticBrowse))).sendKeys(File_to_be_Upload[i]);
                    Thread.sleep(1000);
                    _driver.findElement(By.id(Locators.getProperty(Locators.StaticFileUpload))).click();
                    Thread.sleep(15000);
                    }
                    
                  }//If end ..
                    _driver.findElement(By.id(Locators.getProperty(Locators.NextFileUpload))).click();
                    
                    // Choose Ticket Template
                    CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.TTSelection)), Template);
                    
                    Thread.sleep(3000);
                    _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
                    
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
                 
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
                    Thread.sleep(2000);
                    
                   
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
                  
                  
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
                    Thread.sleep(2000);
                    
                    //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
                   // CommonFunctions.selectRadioButton(_driver, By.xpath(StorefrontSelection), Storefront+"Storefront");
                  // System.out.println("radio");
                  
                   _driver.findElement(By.xpath(StorefrontSelection)).click();
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
           
                    System.out.println("publish ok");
                    //_driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
                   
                    return sProductName;
                    }
      }
            
            else
            {
                   System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
                   return null;
      }
    }
     catch (AssertionError ex) 
  {
            System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
   }
    return sProductName;
    }
	
	/*public String CreateKITProduct_with_TATandQTY(String sProductName ,String ProductId, boolean Turn_Around_Time,String TATvalue,String duration,boolean qty_type,String qtyvalue, String product_Name_to_add, String Storefront,String KIT_Prod_Qty,String KIT_Prod_Buyyer_Option) throws Exception
    {

           String ProductExists = "";
           
           String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
           System.out.println(StorefrontSelection);
    try
    {
           
            if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
      {  
                   _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
                   
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
    
                   WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
             selectallprintshops.sendKeys("Kit");

             _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();         
             
             Thread.sleep(1000);
             
             if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
                   {
                   ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
                         if(ProductExists.contains("This name already exists"))
                         {
                         
                         }
                   }
             
             else
                    {
                    
                    // Set product id if present
                    if (ProductId.isEmpty())
                    {
                          // Do nothing,  product id is not set
                    }
                    else
                    {
                          _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductId);
                    }
                   //-----------------------------------------------------------------------------------
                    //To move to product settings and set TAT and qty values.
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.ProductSettings))).click();
                    System.out.println("Product settings box is clicked..");
                    Thread.sleep(2000);
                    if (Turn_Around_Time)
                    {
                          this.Product_SetTAT(TATvalue, duration);     
                          System.out.println("Turn Around Time is set as:"+TATvalue);
                    }
                    
                    if (qty_type)
                    {
                          this.SetFixedQuantities(qtyvalue);
                          System.out.println("fixed qty is set as::"+qtyvalue);
                    }                    
            //-----------------------------------------------------------------------------------
                    
                    
                    _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();

                    //================================================================================
                    //To Add required product.
                         Ticket_Templates_page TT = new Ticket_Templates_page<>();
                         WebElement addproducts =  _driver.findElement(By.id(Locators.getProperty(Locators.Kit_Add_Button)));
                         if (TT.Elementdisplayed_Enabled(addproducts))
                         {
                                addproducts.click();
                                System.out.println("add products button is clicked on Ticket template page.");
                         }
                         
                         //To check that search box is available or not.
                         WebElement Kit_Product_Search =  _driver.findElement(By.id(Locators.getProperty(Locators.Kit_Product_Search)));
                         if (TT.Elementdisplayed_Enabled(Kit_Product_Search))
                         {
                                Kit_Product_Search.sendKeys(product_Name_to_add);
                                System.out.println("Product name is entered in kit search box");
                         }
                         else
                         {
                                System.err.println("product name is not entered in kit search box..");
                         }
                         
                         //to click on search button in product search box.
                         WebElement KIT_Search =  _driver.findElement(By.id(Locators.getProperty(Locators.KIT_Search)));
                         if (TT.Elementdisplayed_Enabled(KIT_Search))
                         {
                                KIT_Search.click();
                                System.out.println("Kit search button is clicked..");
                         }
                         else
                         {
                                System.err.println("Kit search button is not clicked..");
                         }                           
                         
                    //check whether the product button is displayed or not..
                    String Prod_xpath = "//tr[td[contains(text(),'"+product_Name_to_add.trim() +"')]]/td/span/input";
                    if (_driver.findElements(By.xpath(Prod_xpath)).size() > 0)
                    {
                          _driver.findElement(By.xpath(Prod_xpath)).click();
                          System.out.println("product need to add to Kit is selected..");
                          _driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_Product_Add_button))).click();
                    }
                    else
                    {
                          System.err.println("product is not added correctly..");
                    }
                    
                    String Let_Buyyer_change_Item_qty = "//tbody[tr[td[contains(text(),'"+ product_Name_to_add.trim()+"')]]]/tr/td/Select";
                    String Products_Per_Kit = "//tbody[tr[td[contains(text(),'"+ product_Name_to_add.trim()+"')]]]/tr/td/span/input";
                    
                    //To Select the qty of product
                    if (_driver.findElements(By.xpath(Products_Per_Kit)).size() > 0)
                    {
                    		_driver.findElement(By.xpath(Products_Per_Kit)).clear();
                          _driver.findElement(By.xpath(Products_Per_Kit)).sendKeys(KIT_Prod_Qty);
                    }
                    
                    //To choose Let buyer change item qty:
                    if (_driver.findElements(By.xpath(Let_Buyyer_change_Item_qty)).size() > 0)
                    {
                         WebElement buyyeroption =  _driver.findElement(By.xpath(Let_Buyyer_change_Item_qty));
                         CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath(Let_Buyyer_change_Item_qty), KIT_Prod_Buyyer_Option);
                    }                    
                    
                    //================================================================================                       
                    
                    Thread.sleep(3000);
                    _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
                    
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
                 
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
                    Thread.sleep(2000);
                    
                   
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
                  
                  
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
                    Thread.sleep(2000);
                    
                    //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
                   // CommonFunctions.selectRadioButton(_driver, By.xpath(StorefrontSelection), Storefront+"Storefront");
                  // System.out.println("radio");
                  
                   _driver.findElement(By.xpath(StorefrontSelection)).click();
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
           
                    System.out.println("publish ok");
                    //_driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
                   
                    return sProductName;
                    }
      }
            
            else
            {
                   System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
                   return null;
      }
    }
     catch (AssertionError ex) 
  {
            System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
   }
    return sProductName;
    }
    */
    public String CreateStaticProduct_withTATandQTY(String sProductName ,String ProductId,  String File,boolean Turn_Around_Time,String TATvalue,String duration,boolean qty_type,String qtyvalue, String Template, String Storefront) throws Exception
    {

           String ProductExists = "";
           
           String StorefrontSelection = "//tr[td/span[contains(text(),'"+Storefront+"Storefront')]]/td/input[@type='radio']";
           System.out.println(StorefrontSelection);
    try
    {
           
            if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Product))).size() > 0)
      {  
                   _driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Product))).click();
                   
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).clear();
                   _driver.findElement(By.id(Locators.getProperty(Locators.Product_Name))).sendKeys(sProductName);
    
                   WebElement selectallprintshops = _driver.findElement(By.id(Locators.getProperty(Locators.Product_Type)));
             selectallprintshops.sendKeys("Static Document");

             _driver.findElement(By.id(Locators.getProperty(Locators.Product_Next))).click();
             
             
             Thread.sleep(1000);
             
             if (_driver.findElements(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).size() > 0)
                   {
                   ProductExists = _driver.findElement(By.id(Locators.getProperty(Locators.ProductAlreadyExists))).getText();
                         if(ProductExists.contains("This name already exists"))
                         {
                         
                         }
                   }
             
             else
                    {
                    
                    // Set product id if present
                    if (ProductId.isEmpty())
                    {
                          // Do nothing,  product id is not set
                    }
                    else
                    {
                          _driver.findElement(By.id(Locators.getProperty(Locators.ProductId))).sendKeys(ProductId);
                    }
                   //-----------------------------------------------------------------------------------
                    //To move to product settings and set TAT and qty values.
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.ProductSettings))).click();
                    System.out.println("Product settings box is clicked..");
                    Thread.sleep(2000);
                    if (Turn_Around_Time)
                    {
                          this.Product_SetTAT(TATvalue, duration);     
                          System.out.println("Turn Around Time is set as:"+TATvalue);
                    }
                    
                    if (qty_type)
                    {
                          this.SetFixedQuantities(qtyvalue);
                          System.out.println("fixed qty is set as::"+qtyvalue);
                    }                    
            //-----------------------------------------------------------------------------------
                    
                    
                    _driver.findElement(By.id(Locators.getProperty(Locators.NextInformation))).click();

                    //================================================================================
                    // Upload File
                    //To Select mulitple files..
                    //================================================================================
                  //  if (File.contains(","))
                  //  {
                   //    String[] File_to_be_Upload = File.split(",");                        
                            
                   // for (int i =0; i<=File_to_be_Upload.length-1;i++)
                   // {                 
                    Thread.sleep(1000);
                    CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.StaticBrowse)));
                    _driver.findElement(By.id(Locators.getProperty(Locators.StaticBrowse))).sendKeys(File);
                    Thread.sleep(1000);
                    _driver.findElement(By.id(Locators.getProperty(Locators.StaticFileUpload))).click();
                    Thread.sleep(15000);
               //     }
                    
                //  }//If end ..
                    _driver.findElement(By.id(Locators.getProperty(Locators.NextFileUpload))).click();
                    
                    // Choose Ticket Template
                    CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.TTSelection)), Template);
                    
                    Thread.sleep(3000);
                    _driver.findElement(By.id(Locators.getProperty(Locators.Product_Finish))).click();
                    
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProduct))).click();
                 
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).clear();
                    Thread.sleep(2000);
                    
                   
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchProductPublish))).sendKeys(Storefront);
                  
                  
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductFind))).click();
                    Thread.sleep(2000);
                    
                    //if storefront name is "bnm", pass bnmStorefront for selecting from radio button 
                   // CommonFunctions.selectRadioButton(_driver, By.xpath(StorefrontSelection), Storefront+"Storefront");
                  // System.out.println("radio");
                  
                   _driver.findElement(By.xpath(StorefrontSelection)).click();
                    _driver.findElement(By.xpath(Locators.getProperty(Locators.PublishProductOk))).click();
           
                    System.out.println("publish ok");
                    //_driver.findElement(By.id(Locators.getProperty(Locators.Product_Done))).click();
                   
                    return sProductName;
                    }
      }
            
            else
            {
                   System.out.println("NOT ABLE TO SEE CREATE NEW BUTTON");
                   return null;
      }
    }
     catch (AssertionError ex) 
  {
            System.err.println("NOT ABLE TO SEE CREATE NEW BUTTON");
   }
    return sProductName;
    }
    
    public void Product_SetTAT(String Value , String Duration) throws Exception// e.g. Value = 3, Duration = Day/Hour/Week/Month
    {
           _driver.findElement(By.xpath(Locators.getProperty(Locators.TATRadioValue))).click();
           _driver.findElement(By.xpath(Locators.getProperty(Locators.TATValue))).sendKeys(Value);
           CommonFunctions.selectDropdown(_driver, By.xpath(Locators.getProperty(Locators.TATDuration)), Duration);
           Thread.sleep(2000);        
           
    }
    
}
