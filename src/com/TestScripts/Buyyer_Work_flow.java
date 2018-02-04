package com.TestScripts;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.pages.Login;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;
import com.gui_auto.dao.ReadAndUpdate_Path;

public class Buyyer_Work_flow extends GUI_automation_base{

	//a[@class='ng-scope cart-checkout-button' ]
	//	cart_total
	ReadAndUpdate_Path dbConnection = new ReadAndUpdate_Path();
	ScreenShot TakeScreenShot = new ScreenShot();
	String sFile = _properties.getProperty(GUI_automation_properties.ScreenShotPath);
	File directory = new File(sFile); 
	public ArrayList  FinalFilename = new ArrayList();


	String Ordernum = "";
	public String Quote_Request() throws Exception
	{
		//=============================================================================================================
		//To Check that Request quote page is exists or not
		//=============================================================================================================
		WebElement Request_Quote_Page = _driver.findElement(By.xpath(Locators.getProperty(Locators.Request_Quote_Page)));

		if(Request_Quote_Page.getText().equalsIgnoreCase("Request Quote"))
		{
			System.out.println("Present Order requires Manual Quote...");
		}
		else
		{
			System.err.println("Requires quote is not displayed..");
		}	

		//=============================================================================================================
		//To check the total order amount and to Click on Send quote request button..			
		//=============================================================================================================

		WebElement Cart_Total = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total)));

		if (Cart_Total.getText().equalsIgnoreCase("Requires Quote"))
		{
			System.out.println("Total order amount is :: Requires quote");
		}
		else
		{
			System.err.println("Total order amount is not in 'Requires quote stage'");
			System.err.println("Order amount is::"+Cart_Total.getText());
		}

		//=============================================================================================================
		WebElement Send_Quote_Request_Button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Send_Quote_Request_Button)));

		if(Send_Quote_Request_Button.isDisplayed())
		{
			Send_Quote_Request_Button.click();
			System.out.println("Send Quote Request button is clicked.");
		}
		else
		{
			System.err.println("Send Quote Request button is not clicked.");
		}	

		//=============================================================================================================
		//Get the order number and check the status is order requires manual quote or not..
		//=============================================================================================================
		Thread.sleep(10000);
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Order_confirmation)));
		WebElement  Order_confirmation = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_confirmation)));
		if (CommonFunctions.Elementdisplayed_Enabled(Order_confirmation))
		{
			System.out.println("Order Confirmation page is displayed");
			CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Order_Number)));
			//			WebElement  Order_Number = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_Number)));
			//			Ordernum = Order_Number.getText();	
			Ordernum=fetch_OrderNumber();
			System.out.println("Request for quote is sent- Order Number is::"+Ordernum);    
			CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Order_App_status)));
			WebElement  Order_App_status = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_App_status)));
			// WebElement  Order_App_status = _driver.findElement(By.linkText("Order requires manual quote"));
			if (Order_App_status.getText().equalsIgnoreCase("Order requires manual quote"))
			{
				System.out.println("Order requires manual quote status is displayed in order confirmation page..");
			}
			else
			{
				System.err.println("Order requires manual quote status is not displayed in order confirmation page..");
			}			    
		}
		else
		{
			System.err.println("Requested page is not displayed...");
		}	  
		return Ordernum;	

		//WebElement orderstatus = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_App_status)));
		//
		//
		//
		//
	}

	public String approve_Quote_Order(String sTestcase,String Username,String Password,String Order_Num) throws Exception
	{

		Login Login1 = new Login(_driver);

		boolean sLogin = Login1.LoginToDSF("Smoke_Master","Smoke_Suite", sTestcase, Username, Password);

		Actions actions = new Actions(_driver);
		WebElement menuHoverLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)));
		actions.moveToElement(menuHoverLink).perform();
		Thread.sleep(2000);
		String orderhistoryoption = "Order History & Status";
		String strxpath = "//*[contains(text(),'"+ orderhistoryoption +"')]";
		System.out.println("Verifying for these options"+strxpath);
		WebElement subLink = _driver.findElement(By.xpath(strxpath));
		if (subLink.isDisplayed())
		{
			System.out.println(orderhistoryoption +" is displayed");
			subLink.click();
			Thread.sleep(3000);		        	
		}

		//To Click on order number
		String xpath_order = "//a[contains(text(),'"+ Order_Num +"')]";
		_driver.findElement(By.xpath(xpath_order)).click();
		Thread.sleep(10000);
		WebElement orderstatus = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_Manual_Quote_Required)));
		//WebElement orderstatus = _driver.findElement(By.linkText("Order requires approval from customer"));

		if(orderstatus.getText().equalsIgnoreCase("Order requires approval from customer"))
		{
			System.out.println("Order Requires approval from customer");
		}
		else
		{
			System.err.println("Order requires approval from customer is not visible. Hence Pls check");
		}	

		WebElement approver_comments = _driver.findElement(By.id(Locators.getProperty(Locators.approval_Comments_text_area_id)));


		if (CommonFunctions.Elementdisplayed_Enabled(approver_comments))
		{
			System.out.println("approver comments text box is available..");
			approver_comments.sendKeys("Approverd @ User level");
			Thread.sleep(1000);
		}
		else
		{
			System.err.println("approver comments box is not displayed..");
		}	

		//click on approve order
		WebElement approve_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Approve_button)));
		WebElement decline_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Decline_button)));

		if (CommonFunctions.Elementdisplayed_Enabled(decline_button))
		{
			System.out.println("decline button is available..");						
		}
		else
		{
			System.err.println("Decline button is not displayed..");
		}	

		if (CommonFunctions.Elementdisplayed_Enabled(approve_button))
		{
			System.out.println("approve button is available..");
			approve_button.click();	
			Thread.sleep(2000);
		}
		else
		{
			System.err.println("approve button is not displayed..");
		}

		return "nothing";
	}

	public boolean Elementdisplayed_Enabled(WebElement Element)
	{
		if(Element.isEnabled())
			//if(Element.isDisplayed()&& Element.isEnabled())
		{
			System.out.println("Object"+Element.getText()+"is displayed and Enabled");
			return true;
		}
		else
		{
			System.out.println("Object"+Element.getText()+"is not displayed");
			return false;
		}

	}


	public boolean Buyyer_Logout_From_Smart_Store() throws Exception
	{
		Actions actions = new Actions(_driver);
		WebElement menuHoverLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)));

		try
		{

			actions.moveToElement(menuHoverLink).perform();Thread.sleep(3000);
			System.out.println("In try block");
			WebElement subLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.Logout_From_Smart_Store)));

			if (subLink.isDisplayed())
			{
				System.out.println("LogOut option is displayed");
				subLink.click();
				System.out.println("Log Out option is Clicked");
				return true;

			}
			else
			{
				System.err.println("Logout option is not displayed");
				return false;
			}

		}
		catch(Exception  e)
		{

			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.MyAccountResp)));
			actions.moveToElement(_driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)))).perform(); Thread.sleep(3000);
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Logout_From_Smart_Store)));
			System.out.println("In catch block");
			System.out.println("Log Out option is Clicked");
			return true;
		}


	}

	public boolean Navigateto_UserSubMenuLink(String linkName) throws Exception
	{
		String Likn_Xpath=".//*[text()='"+linkName+"']";
		Actions actions = new Actions(_driver);
		WebElement menuHoverLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)));
		try
		{
			actions.moveToElement(menuHoverLink).perform();
		}
		catch(StaleElementReferenceException e1)
		{
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.MyAccountResp)));
			actions.moveToElement(_driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)))).perform();

		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		WebElement subLink = _driver.findElement(By.xpath(Likn_Xpath));

		if (subLink.isDisplayed())
		{
			System.out.println("Sub Menu Link  displayed"+linkName);
			subLink.click();
			System.out.println("Sub Menu Link  is Clicked"+linkName);
			return true;
		}
		else
		{
			System.err.println("Log Out option is not displayed");
			return false;
		}
	}

	public boolean buyyer_Print_Services(String sSheetName,String sScenario,String sTestcase,boolean tabs,boolean splpages,boolean splInstructions) throws Exception, IOException
	{

		System.out.println(" ======  Fetching all the Print Options from Excel ======");
		String Media = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Media");
		String MediaScaleToFit = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "MediaScaleToFit");
		Thread.sleep(2000);
		//Clicking on Media couple of times since window is going to back ground.
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
		Thread.sleep(2000);
		//			_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
		//			Thread.sleep(2000);
		//			_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
		//			Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SelectMediaSize))).sendKeys(Media);


		_driver.findElement(By.xpath(Locators.getProperty(Locators.MediaTable))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.MediaTable)));
		int Mediat = _driver.findElements(By.xpath(Locators.getProperty(Locators.MediaRows))).size();
		System.out.println("Selected Media count" +Mediat);
		//Select 1 row in the Media type if rows are not null
		if(Mediat >= 1){
			int SelectMediaT = 1 + (int)(Math.random() * ((Mediat - 1) + 1));
			System.out.println("Selected Media " +SelectMediaT);
			String SelectRow = "//div[@class='modaltable']/table/tbody/tr[" + SelectMediaT + "]/td[2]";
			System.out.println("Selected Media xpath " + SelectRow);
			Thread.sleep(5000);
			String Mediaselected = _driver.findElement(By.xpath(SelectRow)).getText();
			System.out.println("Selected Media text " + Mediaselected);
			_driver.findElement(By.xpath(SelectRow)).click();
			Thread.sleep(5000);
			//dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "MediaSelected", Mediaselected);
			String MediaScale = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "MediaScaleToFit");
			//Select Scale to fit check box with flag from test data file
			if(MediaScale.equals("Yes")){
				System.out.println(" ======  Select Scale to fit checkbox ======");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.MediaScaleToFit))).click();
				TakeScreenshot("Media Selected");
			}
		}
		Thread.sleep(3000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SelectMediaOkBtn))).click();
		//Media : A3,White as default item in Print Options
		Thread.sleep(3000);	
		//=========PrintinColor-Sides ===============
		System.out.println("PrintinColor-Sides");
		String PrintinColorSidesSubMenuSides = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubMenu");
		String PrintinColorSidesSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubSubMenu");
		System.out.println("Default Print In Color/Sides(Print in Color:Double Sided) is selected");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.P))).click();
		String PrinColor = "";
		if(PrintinColorSidesSubMenuSides.equals("Print In Black and White")){
			PrinColor =	"//label[@id='span-2']";			
		}else{
			PrinColor =	"//label[@id='span-4']";	
		}						
		Thread.sleep(1000);
		String PrintinColorSidesSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Print In ColorSides",PrintinColorSidesSubMenuSides , PrintinColorSidesSubSubMenu, "ImageID");
		_driver.findElement(By.xpath(PrinColor)).click();
		Thread.sleep(1000);

		String SubSubMenuPrintXpath = "//label[@id='span" + PrintinColorSidesSubSubMenuID + "']";
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(5000);

		//=========PrintinColor-Sides ===============
		//=========Front Cover ===============
		System.out.println("Front Cover");
		//Place No Front Cover as default print option in ticket template.
		String FrontCoverSubmenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuFrontCover");
		String FrontCoverSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownFrontCover");
		System.out.println("Default No Front Cover is clicked to select given Print option.");
		Thread.sleep(2000);
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).size()>0){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).click();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).click();
		}else{
			System.out.println("Not able to click on Default No Front Cover Print Option");
		}
		Thread.sleep(1000);
		String enetered = "No";
		if (FrontCoverSubmenu.equals("Print on Outside")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FcoverPrintonOutside))).click();
			Thread.sleep(2000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();

			if(FrontCoverSubSubMenu.equals("Print In Black and White")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinBlackandWhite))).click();
			}else if(FrontCoverSubSubMenu.equals("Print In Color")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinColor))).click();
			}

			_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
			enetered = "Yes";

		}else if (FrontCoverSubmenu.equals("Print on Outside and Inside")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FcoverPrintonOutsideandInside))).click();
			Thread.sleep(2000);	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();
			if(FrontCoverSubSubMenu.equals("Print In Black and White")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinBlackandWhite))).click();
			}else if(FrontCoverSubSubMenu.equals("Print In Color")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinColor))).click();
			}

			_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
			enetered = "Yes";
		}else if(enetered.equals("No")){
			String FrontCoverSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Front Cover",FrontCoverSubmenu , FrontCoverSubSubMenu, "ImageID");
			System.out.println(FrontCoverSubSubMenuID);

			SubSubMenuPrintXpath = "//label[@id='span" + FrontCoverSubSubMenuID + "']";
			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(1000);	
		}
		Thread.sleep(4000);	

		//=========Front Cover  ===============
		//=========Back Cover ===============
		System.out.println("Back Cover");
		//Place No Front Cover as default print option in ticket template.
		String BackCoverSubmenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuBackCover");
		String BackCoverSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownBackCover");
		System.out.println("Default No Front Cover is clicked to select given Print option.");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.BackCoverDefault))).click();
		Thread.sleep(1000);


		String enetered1 = "No";
		if (BackCoverSubmenu.equals("Print on Outside")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.BcoverPrintonOutside))).click();
			Thread.sleep(2000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();

			if(BackCoverSubSubMenu.equals("Print In Black and White")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinBlackandWhite))).click();
			}else if(BackCoverSubSubMenu.equals("Print In Color")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinColor))).click();
			}

			_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
			enetered1 = "Yes";

		}else if (BackCoverSubmenu.equals("Print on Outside and Inside")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.BcoverPrintonOutsideandInside))).click();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();
			if(BackCoverSubSubMenu.equals("Print In Black and White")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinBlackandWhite))).click();
			}else if(BackCoverSubSubMenu.equals("Print In Color")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinColor))).click();
			}

			_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
			enetered1 = "Yes";
		}else if(enetered1.equals("No")){
			String BackCoverSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Back Cover",BackCoverSubmenu , BackCoverSubSubMenu, "ImageID");
			System.out.println(BackCoverSubSubMenuID);

			SubSubMenuPrintXpath = "//label[@id='span" + BackCoverSubSubMenuID + "']";
			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(4000);	
		}
		Thread.sleep(4000);	
		//=========Back Cover  ===============

		//=========Back Cover===============
		System.out.println("Binding");
		//Place Perfect Bind  as default print option in ticket template.
		String SubMenuBinding = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuBinding");
		String SubSubMenuDropDownBinding = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownBinding");
		System.out.println("Default Perfect Bind is clicked to select given Print option.");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PerfectBindDefault))).click();
		Thread.sleep(2000);
		String BindingSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Binding",SubMenuBinding , SubSubMenuDropDownBinding, "ImageID");
		System.out.println(BindingSubSubMenuID);
		SubSubMenuPrintXpath = "//label[@id='span" + BindingSubSubMenuID + "']";

		if(SubMenuBinding.equals("Coil Bind")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.CoilBindxPath))).click();
		}else if(SubMenuBinding.equals("Comb Bind")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.CombBindxPath))).click();
		}else if(SubMenuBinding.equals("GBC")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.GBCBindxPath))).click();
		}else if(SubMenuBinding.equals("Ring Binder")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.RingBindxPath))).click();
		}else if(SubMenuBinding.equals("Tape Bind")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.TapeBindxPath))).click();
		}else if(SubMenuBinding.equals("Velo Bind")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.VeloBindxPath))).click();
		}else if(SubMenuBinding.equals("Wire Bind")){
			_driver.findElement(By.xpath(Locators.getProperty(Locators.WireBindxPath))).click();
		}
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(SubSubMenuPrintXpath));
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(5000);	

		//=========Collate/Do Not Collate ===============
		System.out.println("Collate/Do Not Collate ");
		//Place Collate(Collate into Sets)  as default print option in ticket template.
		//String BackCoverSubmenu = dbConnection.ReadFunction(SheetName, Scenario, TestCaseID, "Collate");
		String CollateSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Collate");
		System.out.println("Default No Front Cover is clicked to select given Print option.");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.CollateDefault))).click();
		Thread.sleep(1000);
		String CollateSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Collate",CollateSubMenu , "empty1", "ImageID");
		System.out.println(CollateSubMenuID);

		SubSubMenuPrintXpath = "//label[@id='span" + CollateSubMenuID + "']";
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(4000);	

		//=========Collate/Do Not Collate===============
		//=========Cutting ===============
		System.out.println("Cutting ");
		//Place Collate(Collate into Sets)  as default print option in ticket template.
		//String BackCoverSubmenu = dbConnection.ReadFunction(SheetName, Scenario, TestCaseID, "Collate");
		String CuttingSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Cutting");
		System.out.println("Default Cutting Cut in Half is clicked to select given Print option.");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingDefault))).click();
		Thread.sleep(1000);
		String CuttingSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Cutting",CuttingSubMenu , "empty1", "ImageID");
		System.out.println(CuttingSubMenuID);

		SubSubMenuPrintXpath = "//label[@id='span" + CuttingSubMenuID + "']";

		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(4000);     
		if (CuttingSubMenu.equals("Cut to Specific Margin"))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingMargin))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingMargin))).sendKeys("2");
			Thread.sleep(2000);     
			_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingOkBtn))).click();
			Thread.sleep(2000);
		}

		if (CuttingSubMenu.equals("Specific Width and Height"))
		{
			System.out.println("Inside specific width & height - cutting");
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingWidth))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingWidth))).sendKeys("4");
			Thread.sleep(2000);     
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingHeight))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.CuttingHeight))).sendKeys("6");
			Thread.sleep(2000);     
			_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingOkBtn))).click();
			Thread.sleep(2000);
		}
		Thread.sleep(2000);     



		//=========Cutting===============
		//=========Fold ===============
		System.out.println("Fold ");
		//Place Fold(No Folding)  as default print option in ticket template.

		String FoldSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Fold");
		System.out.println("Default Fold (No Folding) is clicked to select given Print option.");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.FoldingDefault))).click();
		Thread.sleep(1000);
		String FoldSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Fold",FoldSubMenu , "empty1", "ImageID");
		System.out.println(FoldSubMenuID);

		SubSubMenuPrintXpath = "//label[@id='span" + FoldSubMenuID + "']";
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(4000);	

		//=========Fold===============
		//=========Orientation ===============
		//			System.out.println("Orientation ");
		//			//Place Orientation(Portrait) as default print option in ticket template.
		//			
		//			String OrientationSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Orientation");
		//			System.out.println("Default Orientation(Portrait)is clicked to select given Print option.");
		//			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrientationDefault))).click();
		//			Thread.sleep(1000);
		//			String OrientationSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Orientation",OrientationSubMenu , "empty1", "ImageID");
		//			System.out.println(OrientationSubMenuID);
		//						
		//			SubSubMenuPrintXpath = "//label[@id='span" + OrientationSubMenuID + "']";
		//			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		//			Thread.sleep(4000);				
		//			//=========Orientation===============
		if(tabs)				
		{
			boolean tabs_Insert = this.Tabs_PrintService(true, "1", "2");
		}
		else
		{
			System.out.println("Tab print service is not selected as per the test case.");
		}

		if (splpages)
		{
			//perform the required operations
			boolean splPages_Insert = this.Add_Special_pages("1", "2");
		}

		if (splInstructions)
		{
			//click on special instructions and provide instructions
			boolean splInst = this.Insert_special_Instructions("Special Instrucitons are set");
		}
		return false;
	}

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

	public boolean Insert_special_Instructions(String instructions)
	{
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.special_Instructions))).size() > 0 )
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.special_Instructions))).click();
			System.out.println("Special Instuctions are clicked..");
		}
		else
		{
			System.err.println("Special instructions link is not visible. pls check xpath::special_Instructions in locators");
			return false;
		}

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.special_Instructions_text_area))).size() > 0 )
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.special_Instructions_text_area))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.special_Instructions_text_area))).sendKeys(instructions);
			System.out.println("Special Instuctions are Updated as.."+instructions);
		}
		else
		{
			System.err.println("Special instructions text area is not visible. pls check xpath::special_Instructions_text_area in locators");
			return false;
		}


		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.SP_Inst_Cancel))).size() > 0 )
		{

			System.out.println("Cancel button is available");
		}
		else
		{
			System.err.println("Cancel button is not visible. pls check xpath::SP_Inst_Cancel in locators");
			return false;
		}

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.SP_Inst_OK))).size() > 0 )
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SP_Inst_OK))).click();
			System.out.println("Ok button in Special Instuctions is clicked");
			return true;
		}
		else
		{
			System.err.println(" OK button in Special instructions is not visible. pls check xpath::SP_Inst_OK in locators");
			return false;
		}			

	}

	public  boolean Tabs_PrintService(boolean InsertTabs,String Insert_Before_Page,String Insert_After_Page) throws Exception
	{
		if (InsertTabs)
		{
			System.out.println("Inserting Tab process is started..");
		}
		else
		{			
			System.out.println("Tabs are not selected as per the user option.");
			return true;
		}

		//======================================================================================================
		//click on Tabs ps
		//======================================================================================================


		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_Option)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_Option))).click();
			System.out.println("Tabs Print service is displayed and clicked on the same.");
		}
		else
		{
			System.err.println("Tabs option is not displayed. Pls check locator 'Tabs_Option'");
			return false;
		}
		//======================================================================================================		 
		//Click on Insert Tabs
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.Insert_Tabs)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Insert_Tabs))).click();
			System.out.println("Insert Tabs Print service is displayed and clicked on the same.");
		}
		else
		{
			System.err.println(" Insert Tabs option is not displayed. Pls check locator 'Tabs_Option'");
			return false;
		}

		//======================================================================================================
		//Verify Insert Tab page is displayed or not.
		//======================================================================================================
		for (int dcount = 0;dcount<=15;dcount++)
		{
			Thread.sleep(1000);

			try
			{
				if (_driver.findElement(By.xpath("//div[@class='tabs-container-for-mobile']")).isDisplayed())

				{
					System.out.println("Insert Tabs page is displayed");
					break;
				}
			}
			catch (NoSuchElementException e)
			{
				System.err.println("Insert Tabs page is not displayed..");
				return false;
			}
		} // End for loop

		//======================================================================================================		 
		//click on drop down icon and  Choose Tabs (5 Across)
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.InsertTabs_Dropdown)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.InsertTabs_Dropdown))).click();
			System.out.println("Drop down icon to choose tabs is displayed and clicked on the same.");
			Thread.sleep(2000);
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Tabs_5_across)));
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_5_across))).click();
			System.out.println("Tabs (5 Across) is clicked.");
		}
		else
		{
			System.err.println("Drop Down icon is not displayed. Pls check locator 'InsertTabs_Dropdown'");
			return false;
		}

		//======================================================================================================		 
		//choose 2 tabs and provide the data
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.NoofTabs)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.id(Locators.getProperty(Locators.NoofTabs)),"1");
			System.out.println("2 tabs are provided");					 
		}
		else
		{
			System.err.println("NoofTabs id is not available.");
			return false;
		}

		//======================================================================================================		 
		//Click on Add button
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_Accept)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_Accept))).click();
			System.out.println("Tabs are accepted");					 
		}
		else
		{
			System.err.println("Tabs are not accepted. Check for locator 'tab_Accept' ");
			return false;
		}

		//======================================================================================================		 
		//Update tab1  details.
		//======================================================================================================

		//----for Tab1
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1))).sendKeys("Test_Chapter1");
			System.out.println("Tab Text is inserted");					 
		}
		else
		{
			System.err.println("Tabs text is not accepted. Check for locator 'tabtext1' ");
			return false;
		}

		//choose Insert before page number option  

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab1_Insert_Before_or_after)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.xpath(Locators.getProperty(Locators.tab1_Insert_Before_or_after)),"Insert Before Page number");
			System.out.println("Insert before page number option is selected..");					 
		}
		else
		{
			System.err.println("Insert before page number option is not selected ");
			return false;
		}


		//update the page number to insert the tab
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1))).sendKeys(Insert_Before_Page);
			System.out.println("Insert Tab before page.."+Insert_Before_Page);					 
		}
		else
		{
			System.err.println("Insert before page number data is not entered ");
			return false;
		}
		//==========Tab1


		//----for Tab2
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2))).sendKeys("Test_Chapter2");
			System.out.println("Tab Text is inserted");					 
		}
		else
		{
			System.err.println("Tabs text is not accepted. Check for locator 'tabtext2' ");
			return false;
		}

		//choose Insert before page number option  

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab2_Insert_Before_or_after)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.xpath(Locators.getProperty(Locators.tab2_Insert_Before_or_after)),"Insert After Page number");
			System.out.println("Insert before page number option is selected for second tab..");					 
		}
		else
		{
			System.err.println("Insert before page number option is not selected for second tab");
			return false;
		}


		//update the page number to insert the tab
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2))).sendKeys(Insert_After_Page);
			System.out.println("Insert Tab before page.."+Insert_Before_Page);					 
		}
		else
		{
			System.err.println("Insert before page number data is not entered ");
			return false;
		}
		//==========Tab2
		_driver.manage().window().maximize();
		//click on Accept button
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_cancel_button)))))
		{				 
			System.out.println("Cancel button is  displayed.");					 
		}
		else
		{
			System.err.println("Cancel button is not displayed.");				 
		}

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_OK_Button)))))
		{				 
			System.out.println("Accept button is  displayed.");		
			_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_OK_Button))).click();
			return true;
		}
		else
		{
			System.err.println("Accept button is not displayed.");				 
		}			 
		return false;
	}

	//To Add Special Pages
	public boolean Add_Special_pages(String pagefrom,String Pageto) throws Exception
	{
		//======================================================================================
		//To Click on special pages Print Service
		//======================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.special_pages_Option)))))
		{				 
			_driver.findElement(By.xpath(Locators.getProperty(Locators.special_pages_Option))).click();			 
			System.out.println("Special pages option is displayed and clicked.");
		}
		else
		{
			System.err.println("Special pages option is not displayed.");				 
		}		

		//======================================================================================
		//Check the window is exists or not
		//======================================================================================
		if (_driver.findElement(By.id("specialPage")).isDisplayed())
		{
			System.out.println("Special pages dialog is displayed");
		}

		else
		{
			System.err.println("Special pages option is not displayed.");
			return false;
		}

		//check pages and files options are exist or not.
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Pages))).isDisplayed() & _driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Files))).isDisplayed() )
		{
			System.out.println("Pages and files options are  displayed");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Pages))).click();
		}

		else
		{
			System.err.println("Pages and files options are not displayed");
			return false;
		}

		//========================================================================================
		//click on Add button
		//========================================================================================
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).click();
			System.out.println("Add button on special pages is clicked");
		}
		else
		{
			System.err.println("Add button on special pages is not displayed.");
		}

		//========================================================================================
		//update pages from and To
		//========================================================================================
		if (_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).sendKeys(pagefrom);
			System.out.println("Page from is Entered as::"+pagefrom);
		}
		else
		{
			System.err.println("Page from locator is not available.Pls check 'spl_Page_from'");
			return false;
		}

		if (_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).sendKeys(Pageto);
			System.out.println("Page to is Entered as::"+Pageto);
		}
		else
		{
			System.err.println("Page to locator is not available.Pls check 'spl_Page_to'");
			return false;
		}

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Scale_to_fit_Option))).isSelected())
		{
			System.out.println("scale to fit option is by default selected..");
		}
		else
		{
			//Select scale to fit option
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Scale_to_fit_Option))).click();
			System.out.println("scale to fit option is by selected explicitly..");
		}
		
		//Aravinth Commented 

//		//Select media
//		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Media_to_Update))).isDisplayed())
//		{
//			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Media_to_Update))).click();
//			System.out.println("special pages - Media is selected");			  
//			//to select media as blue
//			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_media_choose))).click();
//			System.out.println("Media is selected as::"+Locators.spl_media_choose.toString());
//		}
//		else
//		{
//			System.err.println("Media locator is not available.Pls check 'spl_Media_to_Update'");
//			return false;
//		}


		//Select Color
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_to_Update))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_to_Update))).click();
			System.out.println("special pages - Color is selected");
			Thread.sleep(2000);
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.spl_color_BlackandWhitechoose)));
			//to select media as blue
			//Old code to select the color from dropdown
		//	_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_BlackandWhitechoose))).click();
			System.out.println("color is selected as::"+Locators.spl_color_BlackandWhitechoose.toString());
		}
		else
		{
			System.err.println("color locator is not available.Pls check 'spl_color_to_Update'");
			return false;
		}


		//Select sides
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_sides_to_Update))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_sides_to_Update))).click();
			System.out.println("special pages - Sides is selected");		
			Thread.sleep(2000);
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.spl_Single_side)));
			//to select media as blue
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Single_side))).click();
			System.out.println("side is selected as::"+Locators.spl_Single_side.toString());
		}
		else
		{
			System.err.println("color locator is not available.Pls check 'spl_color_to_Update'");
			return false;
		}

		//to Check the Add, Save, Cancel buttons

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).isDisplayed())
		{
			System.out.println("Special pages - Add button is displayed");
		}
		else
		{
			System.err.println("Special pages - Add button is not displayed'");
			return false;
		}


		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Close))).isDisplayed())
		{
			System.out.println("Special pages - close button is displayed");
		}
		else
		{
			System.err.println("Special pages - Close button is not displayed'");
			return false;
		}

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Save))).isDisplayed())
		{
			System.out.println("Special pages - Save button is displayed");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Save))).click();
			System.out.println("save button is clicked..");
			return true;
		}
		else
		{
			System.err.println("Special pages - Save button is not displayed'");
			return false;
		}


	}
	
	
	public boolean Add_Special_pages_With_Color_Sides(String pagefrom,String Pageto,String color,String Sides) throws Exception
	{
		//======================================================================================
		//To Click on special pages Print Service
		//======================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.special_pages_Option)))))
		{				 
			_driver.findElement(By.xpath(Locators.getProperty(Locators.special_pages_Option))).click();			 
			System.out.println("Special pages option is displayed and clicked.");
		}
		else
		{
			System.err.println("Special pages option is not displayed.");				 
		}		

		//======================================================================================
		//Check the window is exists or not
		//======================================================================================
		if (_driver.findElement(By.id("specialPage")).isDisplayed())
		{
			System.out.println("Special pages dialog is displayed");
		}

		else
		{
			System.err.println("Special pages option is not displayed.");
			return false;
		}

		//check pages and files options are exist or not.
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Pages))).isDisplayed() & _driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Files))).isDisplayed() )
		{
			System.out.println("Pages and files options are  displayed");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Pages))).click();
		}

		else
		{
			System.err.println("Pages and files options are not displayed");
			return false;
		}

		//========================================================================================
		//click on Add button
		//========================================================================================
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).click();
			System.out.println("Add button on special pages is clicked");
		}
		else
		{
			System.err.println("Add button on special pages is not displayed.");
		}

		//========================================================================================
		//update pages from and To
		//========================================================================================
		if (_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_from))).sendKeys(pagefrom);
			System.out.println("Page from is Entered as::"+pagefrom);
		}
		else
		{
			System.err.println("Page from locator is not available.Pls check 'spl_Page_from'");
			return false;
		}

		if (_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.spl_Page_to))).sendKeys(Pageto);
			System.out.println("Page to is Entered as::"+Pageto);
		}
		else
		{
			System.err.println("Page to locator is not available.Pls check 'spl_Page_to'");
			return false;
		}

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Scale_to_fit_Option))).isSelected())
		{
			System.out.println("scale to fit option is by default selected..");
		}
		else
		{
			//Select scale to fit option
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Scale_to_fit_Option))).click();
			System.out.println("scale to fit option is by selected explicitly..");
		}
		
		//Aravinth Commented 

//		//Select media
//		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Media_to_Update))).isDisplayed())
//		{
//			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Media_to_Update))).click();
//			System.out.println("special pages - Media is selected");			  
//			//to select media as blue
//			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_media_choose))).click();
//			System.out.println("Media is selected as::"+Locators.spl_media_choose.toString());
//		}
//		else
//		{
//			System.err.println("Media locator is not available.Pls check 'spl_Media_to_Update'");
//			return false;
//		}


		//Select Color
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_to_Update))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_to_Update))).click();
			System.out.println("special pages - Color is selected");
			Thread.sleep(2000);
			String spl_color_BlackandWhitechoose = "//li[@popupid='spMediaDisabledDetail']//td[@title='"+color+"']";
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(spl_color_BlackandWhitechoose));
			//CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.spl_color_BlackandWhitechoose)));
			//to select media as blue
			//Old code to select the color from dropdown
		//	_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_color_BlackandWhitechoose))).click();
			System.out.println("color is selected as::"+Locators.spl_color_BlackandWhitechoose.toString());
		}
		else
		{
			System.err.println("color locator is not available.Pls check 'spl_color_to_Update'");
			return false;
		}


		//Select sides
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_sides_to_Update))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_sides_to_Update))).click();
			System.out.println("special pages - Sides is selected");		
			Thread.sleep(2000);
			String spl_Single_side = "//li[@popupid='spMediaDisabledDetail']//td[@title='"+Sides+"']";
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(spl_Single_side));
//			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.spl_Single_side)));
			//to select media as blue
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.spl_Single_side))).click();
			System.out.println("side is selected as::"+Locators.spl_Single_side.toString());
		}
		else
		{
			System.err.println("color locator is not available.Pls check 'spl_color_to_Update'");
			return false;
		}

		//to Check the Add, Save, Cancel buttons

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Add))).isDisplayed())
		{
			System.out.println("Special pages - Add button is displayed");
		}
		else
		{
			System.err.println("Special pages - Add button is not displayed'");
			return false;
		}


		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Close))).isDisplayed())
		{
			System.out.println("Special pages - close button is displayed");
		}
		else
		{
			System.err.println("Special pages - Close button is not displayed'");
			return false;
		}

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Save))).isDisplayed())
		{
			System.out.println("Special pages - Save button is displayed");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Spl_Save))).click();
			System.out.println("save button is clicked..");
			return true;
		}
		else
		{
			System.err.println("Special pages - Save button is not displayed'");
			return false;
		}


	}



	public boolean Search_Product_smart_store_Npp(String ProductName,String qty) throws Exception
	{
		Thread.sleep(5000);


		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);
			_driver.findElement(By.className("search-button")).click();
			System.out.println(" published product is provided in search box..");	 
		}
		else
		{
			System.err.println("search product box is not available in storefront");
		}

		CommonFunctions.waitForDSFPageLoad(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
		//check buy now option if it presents..	
		WebElement Item_Found_Check = _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Found_Check)));
		if (CommonFunctions.Elementdisplayed_Enabled(Item_Found_Check))
		{	

			System.out.println("Published product is avaialble in storefront..");	 
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
		}

		//Enter qty
		_driver.findElement(By.xpath("//div[contains(@ng-if,'ProductListingStyle')]/div[1]//input[@class='ng-pristine ng-untouched ng-valid ng-scope input-small']")).sendKeys(qty);
		//choose Buy Now Option.
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))
		{	
			product_Buy_Now_option.click();
			System.out.println("Published product is orderd..");	 
			return true;
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
			return false;
		}
	}

	public boolean Search_Product_smart_store(String ProductName) throws Exception
	{
		Thread.sleep(5000);


		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);
			Thread.sleep(2000);
			_driver.findElement(By.className("search-button")).click();
			System.out.println(" published product is provided in search box..");	 
		}
		else
		{
			System.err.println("search product box is not available in storefront");
		}	

		//check buy now option if it presents..	
		WebElement Item_Found_Check = _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Found_Check)));
		if (CommonFunctions.Elementdisplayed_Enabled(Item_Found_Check))
		{	

			System.out.println("Published product is avaialble in storefront..");	 
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
		}	


		//choose Buy Now Option.
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))
		{	
			product_Buy_Now_option.click();
			System.out.println("Published product is orderd..");	 
			return true;
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
			return false;
		}
	}
	public boolean Enter_JobnameandQTy()	
	{

		System.out.println(" ======  Creating Random string for Job Name and Randon integer from 1 -20 to enter in Quantitty field ======");
		String JName = CommonFunctions.getRandomString(10);
		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		String JQua = "" + JQuantity;
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.JobName)));
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).sendKeys("Auto_Product"+JName);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(JQua);
			System.out.println("Job name Entered as::"+"Auto_Product"+JName);
			System.out.println("QTY Entered as::"+JQua);
			return true;
		}
		return false;
	}


	public String UpdateJobNameAndQTy()	
	{

		System.out.println(" ======  Creating Random string for Job Name and Randon integer from 1 -20 to enter in Quantitty field ======");
		String JName = CommonFunctions.getRandomString(10);
		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		String JQua = "" + JQuantity;
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.JobName)));
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).sendKeys("Auto_Product"+JName);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(JQua);
			System.out.println("Job name Entered as::"+"Auto_Product"+JName);
			System.out.println("QTY Entered as::"+JQua);
			return JQua;
		}
		return JQua;
	}



	public boolean Enter_JobnameandQTy(boolean Fixed_qty)	
	{

		System.out.println(" ======  Creating Random string for Job Name and Randon integer from 1 -20 to enter in Quantitty field ======");
		String JName = CommonFunctions.getRandomString(10);
		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		String JQua = "" + JQuantity;
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).sendKeys("Auto_Product"+JName);
			if (!Fixed_qty)
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
				_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(JQua);
				System.out.println("QTY Entered as::"+JQua);
			}
			else
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).isEnabled())
				{
					System.err.println("For Fixed quantity products , Buyyer side qty field should not be enabled.");
				}
				else
				{
					System.out.println("Fixed qty value is correctly displayed..");
				}
			}

			System.out.println("Job name Entered as::"+"Auto_Product"+JName);					
			return true;
		}
		return false;
	}

	public void EnterJobName(String jobName)
	{
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).sendKeys(jobName);
			System.out.println("Job name Entered Successfully");
		}
		else
		{
			System.err.println("job name entered unsuccessful");
		}
	}

	public void EnterQty(String Qty)
	{
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(Qty);
			System.out.println("QTy entered Successfully");
		}
		else
		{
			System.err.println("Entering qty is unsuccessful");
		}

	}	

	public void selectQTY_From_DropDown(String qty) throws Exception
	{
		String qty_DropDown_Xpath="//select[@ng-change='OnQuantityChange()']";
		if(_driver.findElement(By.xpath(qty_DropDown_Xpath)).isDisplayed())
		{
			CommonFunctions.selectDropdownByText(_driver, By.xpath(qty_DropDown_Xpath), qty);
			System.out.println("Quantity selected successfully from the quantity dropdown");

		}
		else
		{
			System.err.println("uanble to select a quantity from the quantity dropdown please check manually once");
		}
	}
	public boolean Addto_Cart() throws Exception
	{
		WebElement add_To_Cart = _driver.findElement(By.xpath(Locators.getProperty(Locators.add_To_Cart)));	
		_driver.manage().window().maximize();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.add_To_Cart)));
		if (CommonFunctions.Elementdisplayed_Enabled(add_To_Cart))
		{		 
			//			add_To_Cart.click();
			CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.add_To_Cart)));
			if(CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='approvalDiv']/div[@class='modal-footer']//span[text()='I Agree']")))
			{
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath("//div[@id='approvalDiv']/div[@class='modal-footer']//span[text()='I Agree']"));
			}
			CommonFunctions.waitForDSFPageLoad(_driver);
			CommonFunctions.waitForPageLoaded(_driver);
			System.out.println("Add to Cart button is clicked..");	
			return true;
		}
		return false;
	}

	public boolean XMPIE_Add_Calender_Data() throws Exception
	{//this function is specific to TC_6_xmpie

		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Calender Fields are displayed..");
		}
		else
		{
			System.err.println("Calender details for xmpie is not displayed correctly");
			return false;
		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Calender_"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Calender_"+i);
		}

		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		//Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("calender VDP PDF proof is displayed..");

					boolean addtoc = this.Addto_Cart();

					if (addtoc)
					{
						System.out.println("xmpie product is added to cart..");
						return true;
					}
					else
					{
						System.err.println("Add to cart button is not displayed properly..");			
					}					
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
		}		
		return false;
	}

	public void waitForPageLoaded(WebDriver _driver) {

		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(_driver,80);
		try {
			wait.until(expectation);
		} catch(Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	} 

	public void Print_Console(String Heading)
	{
		System.out.println("*****************************************************");
		System.out.println("******Starting * "+ Heading+" **************" );	
		System.out.println("*****************************************************");
	}

	public void End_Print_Console(String EndofHeading)
	{
		System.out.println("*****************************************************");
		System.out.println("********End of* "+ EndofHeading+" **************" );	
		System.out.println("*****************************************************");
	}

	public void Start_Print_Console(String StartofHeading)
	{
		System.out.println("*****************************************************");
		System.out.println("********Start of* "+ StartofHeading+" **************" );	
		System.out.println("*****************************************************");
	}

	public void All_Products_In_KIT_Set_As_Zero(String sTestcase,String KITProductName)
	{
		//----------------------------------------------------------------------------------------------------
		//To set all products qty in kit as zero
		//----------------------------------------------------------------------------------------------------
		String sub_ProductName = dbConnection.ReadFunction("Smoke_Master", "Smoke_Suite", sTestcase, "Created_Products");		  
		String[] PSOptions = sub_ProductName.split(";");
		int options = PSOptions.length;		
		for (int i=0;i<options;i++)
		{
			System.out.println(PSOptions[i]);
			if (PSOptions[i].equalsIgnoreCase("End"))
			{
				break;
			}
			String Prod_qty_xpath = "//tr[td[div[a[contains(text(),'"+PSOptions[i]+"')]]]]/td[@class ='kit-td-item-quantity']/ng-quantity-control/div/Input";

			if (!PSOptions[i].equalsIgnoreCase(KITProductName))
			{ 	
				WebElement Prod_qty = _driver.findElement(By.xpath(Prod_qty_xpath));
				if (CommonFunctions.Elementdisplayed_Enabled(Prod_qty))
				{
					Prod_qty.clear();
					Prod_qty.sendKeys("0");
				}
			}
		} // For End..
	}

	public boolean set_KIT_JobNameandQty(String sTestcase,String KIT_Job_Name,String JQuantity)
	{
		String KITProductName = dbConnection.ReadFunction("Smoke_Master", "Smoke_Suite", sTestcase , "ProductName");
		WebElement Kit_Job_Name = _driver.findElement(By.id(Locators.getProperty(Locators.KIT_Job_Name)));

		//To Enter KIT - job name and quantity.
		if (CommonFunctions.Elementdisplayed_Enabled(Kit_Job_Name))
		{
			Kit_Job_Name.clear();		
			Kit_Job_Name.sendKeys(KIT_Job_Name);
			System.out.println("kit_Job_name Entered as::"+KIT_Job_Name);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).sendKeys(JQuantity);
			return true;
		}
		return false;
	}

	public boolean KIT_Customize_Button_Click(String KITProductName) throws Exception
	{

		String Customize_xpath = "//tr[td[div[@class='kit-product-name']/a[contains(text(),'"+ KITProductName +"')]]]/td/div/button[contains(text(),'Customize')]";
		WebElement Customize_btn = _driver.findElement(By.xpath(Customize_xpath));
		if (CommonFunctions.Elementdisplayed_Enabled(Customize_btn))
		{	
			Customize_btn.click();
			System.out.println("Customize button is clicked for product::"+KITProductName);
			Thread.sleep(4000);
		}
		else
		{
			System.err.println("Customize button is not avaialble in storefront..");
			System.err.println("product name::"+KITProductName);
			return true;
		}	
		return false;
	}

	public boolean KIT_Add_To_Cart_Click()
	{
		WebElement Kit_Add_to_Cart = _driver.findElement(By.xpath(Locators.getProperty(Locators.Kit_Add_to_Cart)));

		if (CommonFunctions.Elementdisplayed_Enabled(Kit_Add_to_Cart))
		{		 
			Kit_Add_to_Cart.click();					 
			System.out.println("KIT- Add to Cart button is clicked..");
			return true;
		}	
		return false;
	}

	public boolean Buyyer_set_Promo_code(String Promocode) throws Exception
	{// select promo code
		//		before proceeding with promocode
		//
		//		1. Get Total amount
		//		2. Get Shipping amount
		//		3. Get Sub total value..
		//
		//		 apply promo code
		//
		//		3. Get Total amount
		//		4. Get Shipping amount
		//
		//		now total amount and shipping amount should be same..
		//
		//		get discounted value.
		//		discounted value and subtotal value should be same.
		//		if it is then pass
		//		else
		//		fail.

		String subTotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
		String ShippingAmount_Before_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
		String TotalValue_Before_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();

		System.out.println("Shipping amount before Discount::"+ShippingAmount_Before_code_apply);
		System.out.println("SubTotal Value Before Discount::"+subTotalValue_Before_code_apply);
		System.out.println("Total Value Before Discount::"+TotalValue_Before_code_apply);

		//Apply Promo code..
		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Promocode_Editbox_In_Payment_Page))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Promocode_Editbox_In_Payment_Page))).clear();
			System.out.println("Promo code Edit box is available.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Promocode_Editbox_In_Payment_Page))).sendKeys(Promocode);
			System.out.println("Promo code value is entered as::"+Promocode);
		}
		else
		{
			System.err.println("PromoCode field is not available.");
		}


		//to click on apply button
		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Promocode_Apply_Button))).isDisplayed())
		{

			_driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Promocode_Apply_Button))).click();
			System.out.println("Apply Button is clicked.");
		}
		else
		{
			System.err.println("Apply Button is not available.");
		}


		String Buyyer_Promo_code_check = "//span[@compile-html='priceItem._Description']//strong[contains(text(),'"+Promocode+"')]";
		//check for discount tag with respect to promocode
		for(int i = 0;i<=60;i++)
		{		
			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.Buyyer_Discount_tag))).size() > 0 &  _driver.findElements(By.xpath(Buyyer_Promo_code_check)).size() > 0)			
			{
				System.out.println("Discount tag is displayed properly");
				String subTotalValue_After_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value))).getText();
				String ShippingAmount_After_code_apply= _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Shipping_value))).getText();
				String TotalValue_After_code_apply = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Total))).getText();
				String Discount_applied = _driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Discount_value))).getText();

				//to Check total value is caliculated only on shipping rather than subtotal value.
				//			if (TotalValue_After_code_apply.equalsIgnoreCase(ShippingAmount_After_code_apply) )
				//			{
				//				System.out.println("Discount value is properly applied. Only shipping is caliculated on total value as auction is on.");
				//			}
				//			else
				//			{
				//				System.err.println("Discount Value is not properly applied. Pls check again..");
				//				System.err.println("Total After discount::"+TotalValue_After_code_apply);
				//				System.err.println("Shipping After discount::"+ShippingAmount_After_code_apply);
				//			}

				//To check that discounted value and subtotal value is same or not.
				if (Discount_applied.equalsIgnoreCase(subTotalValue_Before_code_apply) )
				{
					System.out.println("Discount value is same as subtotal value");
				}
				else
				{
					System.err.println("Discount Value is not properly applied. Pls check again..");
					System.err.println("Sub Total Before discount::"+subTotalValue_Before_code_apply);
					System.err.println("Discount applied as::"+Discount_applied);
				}			
				break;
			}		
			else
			{
				Thread.sleep(3000);
			}	

		} //End for for loop

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.Buyyer_Discount_tag))).size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}

	public boolean FP_Add_Calender_Data(String JobQTY,String QTYType) throws Exception
	{//this function is specific to TC_10_FP_with_More than 12 pages.

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Fusion Pro Fields are displayed..");
		}
		else
		{
			System.err.println("Fusion Pro details is not displayed correctly. Check the product xpath. For xmpie and FP we have used common xpaths.");
			return false;
		}

		//Update the qty with provided input in smoke master sheet..
		try
		{

			CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath("//div[@id='globalrow']//Select[contains(@id,'quantityInput')]"), JobQTY);
			System.out.println("Job Qty value is updated as::"+JobQTY);
		}
		catch (Exception e1)
		{
			System.err.println("Qty field is not found...");
		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Auto Test-"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Auto Test-"+i);
		}

		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		//Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					//-------------------------------------------------------------------------------------------------						
					//to check the number pages.As product is maintained in db it should show as expected pages.
					//Expected is hard coded here..as 92. Update code with required value if occurs in future.
					//need to take care this parameter from excel. Note..Krishna..
					//-------------------------------------------------------------------------------------------------
					String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

					if (Nooffile_Uploaded.contains("/"))
					{
						String[] Nooffiles = Nooffile_Uploaded.split("/");
						int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
						if (TotalFiles == 2)
						{
							System.out.println("FP product with 2 pages are displayed correctly.. ");
						}
						else
						{
							System.err.println("Paages are not available correctly");
							System.err.println("No.Of pages expected :2");
							System.err.println("No.Of pages Actually available:"+TotalFiles);
							System.out.println("No.Of pages '2' count is hard coded. Hence pls check the product in application level.");
						}
					}								   	
					//-------------------------------------------------------------------------------------------------					
					boolean addtoc = this.Addto_Cart();

					if (addtoc)
					{
						System.out.println("FP product is added to cart..");
						return true;
					}
					else
					{
						System.err.println("Add to cart button is not displayed properly..");			
					}					
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
			Thread.sleep(3000);
		}		
		return false;
	}

	String roundOffTo2DecPlaces(double val)
	{
		return String.format("$%.2f", val);
	}

	public boolean Is_printshop_Page_Available() throws Exception
	{
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Default_PS_xpath)));

		for (int i=0;i<=50;i++)
		{
			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.Default_PS_xpath))).size() > 0)
			{
				if(_driver.findElement(By.xpath(Locators.getProperty(Locators.Default_PS_xpath))).isDisplayed())
				{
					return true;
				}
			}
			Thread.sleep(3000);
		}
		return false;
	}

	public boolean Is_Payment_Options_Page_Available() throws Exception
	{
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Default_PS_xpath)));

		for (int i=0;i<=50;i++)
		{
			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.Default_PS_xpath))).size() > 0)
			{
				if(_driver.findElement(By.xpath(Locators.getProperty(Locators.Default_PS_xpath))).isDisplayed())
				{
					return true;
				}
			}
			Thread.sleep(3000);
		}
		return false;
	}

	public  void cfn_GetWebTableData(WebElement Webtable)
	{
		//WebElement Webtable=driver.findElement(By.id("TableID")); // Replace TableID with Actual Table ID or Xpath
		List<WebElement> TotalRowCount=Webtable.findElements(By.xpath("//*[@id='TableID']/tbody/tr"));

		System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
		// Now we will Iterate the Table and print the Values   
		int RowIndex=1;
		for(WebElement rowElement:TotalRowCount)
		{
			List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
			int ColumnIndex=1;
			for(WebElement colElement:TotalColumnCount)
			{
				System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
				ColumnIndex=ColumnIndex+1;
			}
			RowIndex=RowIndex+1;
		}
	}

	public boolean FP_MCD_Selection(String JobQTY,String MCDName) throws Exception
	{ 

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Fusion Pro Fields are displayed..");
		}
		else
		{
			System.err.println("Fusion Pro details is not displayed correctly. Check the product xpath. For xmpie and FP we have used common xpaths.");
			return false;
		}

		//Update the qty with provided input in smoke master sheet..
		try
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(JobQTY);
			System.out.println("Job Qty value is updated as::"+JobQTY);
		}
		catch (NoSuchElementException e1)
		{
			System.err.println("Qty field is not found...");
		}
		//Choose batch mode option

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Radio_xp))).size() > 0 )
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Radio_xp))).click();
			System.out.println("Batch mode is selected.");	
		}
		else
		{
			System.err.println("Batch mode radio box is not available..");
			return false;
		}

		//Choose list box with provided MCD data
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.FP_Select_MCD))).size() > 0 )
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath(Locators.getProperty(Locators.FP_Select_MCD)),MCDName );
			System.out.println("MCD option is selected as::"+MCDName);	
		}
		else
		{
			System.err.println("Provided MCD option::"+MCDName+" is not selected.");
			return false;
		}

		//check data soruce table window is appeared or not
		for (int count =0;count<=10;count++)
		{
			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.FP_DataSource_window))).size() > 0)
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_DataSource_window))).isDisplayed())
				{
					System.out.println("Data Source window is displayed..");
					break;
				}
			}
			Thread.sleep(3000);
		}

		//print required data
		WebElement Datasource = _driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Datatable)));
		//cfn_GetWebTableData(Datasource); 

		//click on mapping
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Cancel_XP))).isDisplayed())
		{
			System.out.println("Cancel button is displayed.");
		}
		else
		{
			System.err.println("Cancel button is not displayed.");
		}

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Mapping_XP))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Select_All_Records_Checkbox_xp))).click();
			System.out.println("Column details are selected.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Mapping_XP))).click();
			System.out.println("Mapping button is displayed.");
		}
		else
		{
			System.err.println("Mapping button is not displayed.");
		}

		//check map data		
		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Map_Data_tab))).isEnabled())
		{
			System.out.println("Map data tab is enabled.");
		}
		else
		{
			System.err.println("Map Data Tab is not enabled.");
		}

		//Comment following for temporary print required Map data 
		//WebElement MapDatasource = _driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Map_Data)));
		//		cfn_GetWebTableData(MapDatasource);

		//click on fill form..
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_FillForm_XP))).isDisplayed())
		{_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_FillForm_XP))).click();
		System.out.println("fill fort button is clicked.");
		}
		else
		{
			System.err.println("Fill Form button is not displayed.");
		}

		//check the records..
		for (int i =0;i<=10;i++)
		{
			if(_driver.findElements(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).size() > 0)
			{
				boolean Records_enabled = _driver.findElement(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).isEnabled();

				if (Records_enabled)
				{
					System.out.println("FP multi column data selection is completed.");
					break;
				}
				Thread.sleep(3000);
			}
		}

		String NoofRecords = _driver.findElement(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).getAttribute("placeholder");

		if (NoofRecords.contains("/"))
		{
			String[] Nooffiles = NoofRecords.split("/");
			int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
			if (TotalFiles == 10)
			{
				System.out.println("FP product with 10 Records are uploaded correctly.. ");
			}
			else
			{
				System.err.println("Records are not available correctly");
				System.err.println("No.Of Records expected :10");
				System.err.println("No.Of records Actually available:"+TotalFiles);
				System.out.println("No.Of records '10' count is hard coded. Hence pls check the product in application level also.");
			}
		}

		//check for pagination controls.

		//To Check Pagination - Move first check
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Pagination_control_MoveFirst))).isDisplayed())
		{
			System.out.println("Pagination control - Move First is displayed.");
		}
		else
		{
			System.err.println("Pagination control - Move first is not displayed.");
		}
		//To Check Pagination - Move Last check
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Pagination_control_MoveLast))).isDisplayed())
		{
			System.out.println("Pagination control - Move Last is displayed.");
		}
		else
		{
			System.err.println("Pagination control - Move Last is not displayed.");
		}

		//To Check Pagination - Move Next check	   	
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Pagination_control_MoveNext))).isDisplayed())
		{
			System.out.println("Pagination control - Move Next is displayed.");
		}
		else
		{
			System.err.println("Pagination control - Move Next is not displayed.");
		}

		//To Check Pagination - Move Previous check
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Pagination_control_MovePrevious))).isDisplayed())
		{
			System.out.println("Pagination control - Move Previous is displayed.");
		}
		else
		{
			System.err.println("Pagination control - Move Previous is not displayed.");
		}

		//Click on update preview and proceed with addto cart buttons.
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}		


		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					//-------------------------------------------------------------------------------------------------						
					//to check the number pages.As product is maintained in db it should show as expected pages.
					//Expected is hard coded here..as 92. Update code with required value if occurs in future.
					//need to take care this parameter from excel. Note..Krishna..
					//-------------------------------------------------------------------------------------------------
					String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

					if (Nooffile_Uploaded.contains("/"))
					{
						String[] Nooffiles = Nooffile_Uploaded.split("/");
						int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
						if (TotalFiles == 92)
						{
							System.out.println("FP product with 92 pages are uploaded correctly.. ");
						}
						else
						{
							System.err.println("Paages are not available correctly");
							System.err.println("No.Of pages expected :92");
							System.err.println("No.Of pages Actually available:"+TotalFiles);
							System.out.println("No.Of pages '92' count is hard coded. Hence pls check the product in application level.");
						}
					}
					//-------------------------------------------------------------------------------------------------
					//to Check unit and total prices by changing the QTY fields. - Setup and regular price values are given 
					//as const . If you have changed the price in product level then u have to change here also.
					//-------------------------------------------------------------------------------------------------
					Double FP_RegularPrice = 10.00; //constants..set for product
					Double  FP_SetupPrice = 2.00;

					NoofRecords = _driver.findElement(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).getAttribute("placeholder");

					String[] Nooffiles = NoofRecords.split("/");
					int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;						   		
					//	JobQTY = _driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).getText();
					//============================================================================
					_driver.manage().window().maximize();
					double Expected_TotalPrice = (FP_RegularPrice*TotalFiles*Integer.valueOf(JobQTY))+ FP_SetupPrice;
					double Expected_Unit_Price= Expected_TotalPrice/Integer.valueOf(JobQTY);  
					String Unit_Price =  roundOffTo2DecPlaces(Expected_Unit_Price);
					String Total_price = roundOffTo2DecPlaces(Expected_TotalPrice);

					System.out.println("Expected Unit Price::"+Unit_Price);
					System.out.println("Expected Total Price::"+Total_price);

					String Actual_UnitPrice = _driver.findElement(By.id(Locators.getProperty(Locators.unitprice_Id))).getText();
					String Actual_TotalPrice = _driver.findElement(By.id(Locators.getProperty(Locators.TotalPrice_Id))).getText();

					//Unit Price verification..
					if (Unit_Price.equalsIgnoreCase(Actual_UnitPrice.trim()))
					{
						System.out.println("Expected and Actual Unit prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual unit prices are not matching correctly");
						System.err.println("Expected Unit Price::"+Unit_Price);
						System.err.println("Actual Unit Price::"+Actual_UnitPrice);
					}

					//total price verification
					if (Total_price.equalsIgnoreCase(Actual_TotalPrice.trim()))
					{
						System.out.println("Expected and Actual Total prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual Total prices are not matching correctly");
						System.err.println("Expected Total Price::"+Total_price);
						System.err.println("Actual Total Price::"+Actual_TotalPrice);
					}

					//-------------------------------------------------------------------------------------------------

					boolean addtoc = this.Addto_Cart();

					if (addtoc)
					{
						System.out.println("FP product is added to cart..");
						return true;
					}
					else
					{
						System.err.println("Add to cart button is not displayed properly..");			
					}					
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
			Thread.sleep(3000);
		}		
		return false;
	}

	public boolean CheckPrintShopPresent(String sPrintShop,boolean SelectPS) throws Exception, IOException
	{         
		System.out.println("In CheckPrintShopPresent");                  
		CommonFunctions.selectDropdown_ByvisibleText(_driver, By.id(Locators.getProperty(Locators.PrintShopsList)), "All");
		System.out.println("Show All is selected.");
		Thread.sleep(2000);
		if(_driver.findElements(By.linkText(sPrintShop)).size() > 0)
		{
			String PSAttr = _driver.findElement(By.linkText(sPrintShop)).getAttribute("href");

			System.out.println(PSAttr);
			System.out.println("The Printshop " +sPrintShop+" is present");
			if (SelectPS)
			{          
				_driver.findElement(By.linkText(sPrintShop)).click();
				System.out.println("Print shop is selected..");
			}
			return true;
		}
		else
		{
			System.out.println("The Printshop" +sPrintShop+" is not present");
			return false;
		}          
	}

	public boolean Buyyer_Option_Click(String BuyerOption) throws Exception
	{
		//function to click on required buyer option Eg;- Order history,My Saved Jobs..etc
		String expectedLandingPageLabel=".//span[text()='"+BuyerOption+"']";
		WebElement menuHoverLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)));
		Actions actions = new Actions(_driver);
		for(int i=1;i<=3;i++)
		{
			actions.moveToElement(menuHoverLink).perform();
			Thread.sleep(2000);		 
			String strxpath = "//*[contains(text(),'"+ BuyerOption +"')]";
			System.out.println("Verifying for option::-"+BuyerOption);
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(strxpath));
			if(_driver.findElements(By.xpath(expectedLandingPageLabel)).size()>0)
			{
				System.out.println("Navigated to respective page :"+BuyerOption);
				break;
			}
		}

		//         if (subLink.isDisplayed())
		//         {
		//        	System.out.println(BuyerOption +" is displayed");
		//        	subLink.click();
		//        	Thread.sleep(3000);		   
		//        	return true;
		//        }        
		return false;
	}

	public boolean FP_Add_Calender_Data(String JobQTY) throws Exception
	{//this function is specific to TC_10_FP_with_More than 12 pages.

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Fusion Pro Fields are displayed..");
		}
		else
		{
			System.err.println("Fusion Pro details is not displayed correctly. Check the product xpath. For xmpie and FP we have used common xpaths.");
			return false;
		}

		//Update the qty with provided input in smoke master sheet..
		try
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(JobQTY);
			System.out.println("Job Qty value is updated as::"+JobQTY);
		}
		catch (Exception e1)
		{
			System.err.println("Qty field is not found...");
		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Auto Test-"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Auto Test-"+i);
		}

		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		//Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					//-------------------------------------------------------------------------------------------------						
					//to check the number pages.As product is maintained in db it should show as expected pages.
					//Expected is hard coded here..as 92. Update code with required value if occurs in future.
					//need to take care this parameter from excel. Note..Krishna..
					//-------------------------------------------------------------------------------------------------
					String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

					if (Nooffile_Uploaded.contains("/"))
					{
						String[] Nooffiles = Nooffile_Uploaded.split("/");
						int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
						if (TotalFiles == 92)
						{
							System.out.println("FP product with 92 pages are uploaded correctly.. ");
						}
						else
						{
							System.err.println("Paages are not available correctly");
							System.err.println("No.Of pages expected :92");
							System.err.println("No.Of pages Actually available:"+TotalFiles);
							System.out.println("No.Of pages '92' count is hard coded. Hence pls check the product in application level.");
						}
					}
					//-------------------------------------------------------------------------------------------------
					//to Check unit and total prices by changing the QTY fields. - Setup and regular price values are given 
					//as const . If you have changed the price in product level then u have to change here also.
					//-------------------------------------------------------------------------------------------------
					Double FP_RegularPrice = 10.00; //constants..set for product
					Double  FP_SetupPrice = 2.00;
					//	JobQTY = _driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).getText();
					//============================================================================
					_driver.manage().window().maximize();
					double Expected_TotalPrice = (FP_RegularPrice*Integer.valueOf(JobQTY))+ FP_SetupPrice;
					double Expected_Unit_Price= Expected_TotalPrice/Integer.valueOf(JobQTY);  
					String Unit_Price =  roundOffTo2DecPlaces(Expected_Unit_Price);
					String Total_price = roundOffTo2DecPlaces(Expected_TotalPrice);

					System.out.println("Expected Unit Price::"+Unit_Price);
					System.out.println("Expected Total Price::"+Total_price);

					String Actual_UnitPrice = _driver.findElement(By.id(Locators.getProperty(Locators.unitprice_Id))).getText();
					String Actual_TotalPrice = _driver.findElement(By.id(Locators.getProperty(Locators.TotalPrice_Id))).getText();

					//Unit Price verification..
					if (Unit_Price.equalsIgnoreCase(Actual_UnitPrice.trim()))
					{
						System.out.println("Expected and Actual Unit prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual unit prices are not matching correctly");
						System.err.println("Expected Unit Price::"+Unit_Price);
						System.err.println("Actual Unit Price::"+Actual_UnitPrice);
					}

					//total price verification
					if (Total_price.equalsIgnoreCase(Actual_TotalPrice.trim()))
					{
						System.out.println("Expected and Actual Total prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual Total prices are not matching correctly");
						System.err.println("Expected Total Price::"+Total_price);
						System.err.println("Actual Total Price::"+Actual_TotalPrice);
					}

					//-------------------------------------------------------------------------------------------------

					boolean addtoc = this.Addto_Cart();

					if (addtoc)
					{
						System.out.println("FP product is added to cart..");
						return true;
					}
					else
					{
						System.err.println("Add to cart button is not displayed properly..");			
					}					
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
			Thread.sleep(3000);
		}		
		return false;
	}

	public boolean production_Job_Ticket_Modification(String JobName,String Companay_Name,String StrOrderNumber,String FileName,boolean QTY_Change,String OriginalQTy,String Update_Qty_Value,boolean PSchange,String PrintServiceName,String SubMenuprintServiceName) throws Exception
	{
		String Oringal_File_Name= "//*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_OriginalFileRow']//a[contains(text(),'"+FileName+"')]";
		String File_Uploaded = " //*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_FilesRow']//a[contains(text(),'"+FileName+"')]";

		//Navigate to Admin
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.AdminTab)));
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.AdminTab))).size()>0)
		{
			System.out.println("Admin Tab is available..");
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.AdminTab)));
			_driver.findElement(By.xpath(Locators.getProperty(Locators.AdminTab))).click();
			System.out.println("Admin Tab is clicked");
		}
		else
		{
			System.err.println("Admin Tab is not available.");
		}

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Customer_Order_History)));
		//Click on Customer order history
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Order_History))).isDisplayed())
		{
			System.out.println("Customer Order History link is available..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Order_History))).click();
			System.out.println("Customer Order History Tab is clicked");
		}
		else
		{
			System.err.println("Customer Order History Link is not available.");
		}

		//Provide customer order number
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).isDisplayed())
		{
			System.out.println("Order Number field is available..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).sendKeys(StrOrderNumber);
			System.out.println("Order Number is entered as::"+StrOrderNumber);
		}
		else
		{
			System.err.println("Order Number field is not available.");
		}

		//Click on search button
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Search_Btn))).click();
		System.out.println("Search Button is clicked.");

		String PJT_Link = "//a[contains(text(),'"+JobName.trim()+"')]";

		//Check the production job ticket link
		if (_driver.findElement(By.xpath(PJT_Link)).isDisplayed())
		{
			System.out.println("production Job Ticket is available..");
			_driver.findElement(By.xpath(PJT_Link)).click();	
			Thread.sleep(3000);
		}
		else
		{
			System.err.println("Production Job Ticket Link is not available.");
			System.err.println("Used xpath::"+PJT_Link);
		}

		//Get the driver details of production job ticket		 

		String  originalHandle = _driver.getWindowHandle();
		System.out.println("originalHandle of customer order hisotory is::"+originalHandle);
		//  Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();

		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{      

				if(_driver.switchTo().window(windowId).getTitle().equals("Production Job Ticket")) 
				{	

					//Check original and files which are already uploaded are available
					if(_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_Rep_OriginalFiles_ctl01_lnkFileLocation")).isDisplayed())
					{
						System.out.println("original File is available");
						System.out.println("Original File name is::"+_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_Rep_OriginalFiles_ctl01_lnkFileLocation")).getText());
					}
					else
					{
						System.err.println("original File is not available.");
					}

					if(_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_FileRepeater_ctl01_FileLinkButton")).isDisplayed())
					{
						System.out.println("File converted as PDF is available");
					}
					else
					{
						System.err.println("File converted as PDF is not available.");
					}

					//Capture the details of ticket.
					//to Check the qy
					String QTY_xpath = "//tr[td[contains(text(),'Quantity')]]//td[contains(text(),"+ OriginalQTy+")]";

					if(_driver.findElement(By.xpath(QTY_xpath)).isDisplayed())
					{
						System.out.println("Oringal Qty data is available");
					}
					else
					{
						System.err.println("Oringinal Qty Data is not available.");
						System.err.println("Oringal QTy expected as::"+OriginalQTy);
						System.err.println("Xpath used as::"+QTY_xpath);
					}

					//To Check the Product name
					String ProdName = "//span[contains(@id,'lblProductType')]";   
					String Compname = "//span[contains(@id,'CompanyNameValue')]";
					if(_driver.findElement(By.xpath(ProdName)).isDisplayed())
					{
						System.out.println("Product Type label is available");
						System.out.println("Product type is::"+_driver.findElement(By.xpath(ProdName)).getText());
					}
					else
					{
						System.err.println("Product Type label is not available.");
					}

					System.out.println("Company name appeard in PJT as::"+_driver.findElement(By.xpath(Compname)).getText());

					//company Name

					if (Companay_Name.equalsIgnoreCase(_driver.findElement(By.xpath(Compname)).getText()))
					{
						System.out.println("Comapany name is available in PJT correctly");
					}
					else
					{
						System.err.println("Expected company:-"+Companay_Name);
						System.err.println("Actual Company shown in PJT::"+_driver.findElement(By.xpath(Compname)).getText());
					}

					//To click on Edit button..
					_driver.findElement(By.xpath(".//*[contains(@id,'ctl01_PrintOptionsRepeater_ctl00_JobSummaryParts_OptionsEditLink')]")).click();
					System.out.println("Clicked on Edit link button");

					Thread.sleep(15000);



					//if qty is need to be changed then update the same
					if (QTY_Change)
					{
						try
						{	  
							WebElement iframe = _driver.findElement(By.xpath("//iframe[contains(@src,'CatalogName=FP_Auto_Saved_Job')]"));
							_driver.switchTo().frame(iframe);
							//String Update_Qty_Value="30";
							_driver.findElement(By.xpath("//Input[contains(@id,'quantityInput')]")).clear();
							_driver.findElement(By.xpath("//Input[contains(@id,'quantityInput')]")).sendKeys(Update_Qty_Value);
							System.out.println("QTY value is updated as:"+Update_Qty_Value);
						}
						catch(Exception  e)
						{
							e.printStackTrace();
							String qtydropDown_xpath=".//*[contains(@id,'ItemQuantityControl_DropDownQuantity')]";
							CommonFunctions.selectDropdownByText(_driver, By.xpath(qtydropDown_xpath), Update_Qty_Value);
							System.out.println("QTY value is updated as:"+Update_Qty_Value);

						}
					}	         
					Thread.sleep(2000);
					//get the print service changed.
					if (PSchange)
					{
						String MainPrintService="//div[contains(@title,'"+PrintServiceName +"')]";

						String SubPrintService = "//li[contains(@title,'"+ SubMenuprintServiceName+"')]";

						try
						{
							WebElement iframe = _driver.findElement(By.xpath("//iframe[contains(@src,'CatalogName=FP_Auto_Saved_Job')]"));
							_driver.switchTo().frame(iframe);
							if (_driver.findElement(By.xpath(MainPrintService)).isDisplayed())
							{
								System.out.println("Main Print Service is displayed.");
								_driver.findElement(By.xpath(MainPrintService)).click();
							}

							if (_driver.findElement(By.xpath(SubPrintService)).isDisplayed())
							{
								System.out.println("sub Menu Print Service is displayed.");
								_driver.findElement(By.xpath(SubPrintService)).click();
							}

							_driver.findElement(By.id("updatePreviewSpan")).click();
							System.out.println("Update Preview button is clicked.");
							Thread.sleep(30000);

							//Click on update button
							_driver.findElement(By.id("addtoCartBtnSpan")).click();
							System.out.println("Addto Cart Button is clicked.");        
							CommonFunctions.waitForPageLoaded(_driver);
						}
						catch(Exception  e)
						{
							String collate_dropDown_Xpath="//input[contains(@value,'Collate into Sets')]";
							SubPrintService=".//Span[text()='"+SubMenuprintServiceName+"']";
							String next_button_Xpath=".//*[contains(@id,'StepNavigationTemplateContainerID_StepNextButton')]";
							String updateButton_Xpath=".//input[contains(@id,'FinishNavigationTemplateContainerID_FinishButton')]";
							CommonFunctions.ClickOnElement(_driver, By.xpath(collate_dropDown_Xpath));
							CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(SubPrintService));
							CommonFunctions.ClickOnElement(_driver, By.xpath(next_button_Xpath));
							CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(updateButton_Xpath));
							CommonFunctions.ClickOnElement(_driver, By.xpath(updateButton_Xpath));
							CommonFunctions.waitForPageLoaded(_driver);
						}
					}
					else
					{
						System.out.println("Print services are not changed as per the option");
					}
					//Click on updatePreviewSpan

					//					

					try
					{
						if (_driver.findElement(By.xpath("//span[contains(text(),'The Ordered Quantity and the Recipient Quantities do not match')]")).isDisplayed())
						{
							System.out.println("'The Ordered Quantity and the Recipient Quantities do not match'  String is displayed.");
						}
					}

					catch(NoSuchElementException e1)
					{
						System.err.println("'The Ordered Quantity and the Recipient Quantities do not match' string is not displayed");
						e1.printStackTrace();
					}                         	 


					//
					//Get the updated details of QTy and changed print service details and ensure that choosen details are available.
					QTY_xpath = "//tr[td[contains(text(),'Quantity')]]//td[contains(text(),"+Update_Qty_Value+")]";

					if(_driver.findElement(By.xpath(QTY_xpath)).isDisplayed())
					{
						System.out.println("Updated Qty data is available");
					}
					else
					{
						System.err.println("Oringinal Qty Data is not available.");
						System.err.println("Updated QTy expected as::"+Update_Qty_Value);
						System.err.println("Xpath used as::"+QTY_xpath);
					}

					String Updated_Ps="//td[contains(text(),'"+SubMenuprintServiceName+"')]";

					if(_driver.findElement(By.xpath(Updated_Ps)).isDisplayed())
					{
						System.out.println("Print service is updated as:: "+SubMenuprintServiceName);
					}
					else
					{
						System.err.println("Print service is not updated in PJT");
						System.err.println("PS is expected as::"+SubMenuprintServiceName);
						System.err.println("Xpath used as::"+Updated_Ps);
					}	                            	

					_driver.close();
					_driver.switchTo().window(originalHandle);
					return true;
				}		}


		}	
		else
		{
			System.err.println("Browser Window is not available.");
		}






		return false;
	}

	public String XMPIE_Add_Calender_Data(float itemqty) throws Exception
	{//this function is specific to TC_6_xmpie

		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Calender Fields are displayed..");
		}
		else
		{
			System.err.println("Calender details for xmpie is not displayed correctly");

		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Calender_"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Calender_"+i);
		}


		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		boolean pdfproof=false;
		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("calender VDP PDF proof is displayed..");
					pdfproof=true;
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
		}
		System.out.println("pdfproof generated  :"+pdfproof);


		String NoOFpages = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");
		String[] NoOFpage = NoOFpages.split("/");
		float Totalpages=Float.valueOf(NoOFpage[NoOFpage.length-1].trim());

		//Verifies the preselected print service prices
		String actualTotalPrice=verifyprintServicePricing(itemqty,Totalpages,false,"0.35",0);

		boolean addtoc = this.Addto_Cart();

		if (addtoc)
		{
			System.out.println("xmpie product is added to cart..");

		}
		else
		{
			System.err.println("Add to cart button is not displayed properly..");			
		}					
		return actualTotalPrice;
	}

	public void enterVdpTextFieldValues()
	{
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Calender Fields are displayed..");
		}
		else
		{
			System.err.println("Calender details for xmpie is not displayed correctly");

		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Calender_"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Calender_"+i);
		}

	}

	public void clickOnUpdatePreViewButtonForVDP_VerifyPdfProofGenerated() throws Exception
	{
		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		boolean pdfproof=false;
		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("calender VDP PDF proof is displayed..");
					pdfproof=true;
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
		}
		System.out.println("pdfproof generated  :"+pdfproof);

	}

	public String fetch_PrintOption_Price(String printoptionName) throws Exception
	{
		String Ps_price="";
		String psName="";
		String all_Ps_names="//table[@class='print-options-info']//tbody/tr/td[1]";
		int total_ps=_driver.findElements(By.xpath(all_Ps_names)).size();
		int i=1;
		for(;i<=total_ps;i++)
		{   
			String required_ps="//table[@class='print-options-info']//tbody/tr["+i+"]/td[1]";
			CommonFunctions.Wait(_driver, By.xpath(required_ps));
			psName=_driver.findElement(By.xpath(required_ps)).getAttribute("textContent").trim();
			if(psName.equalsIgnoreCase(printoptionName))
			{
				String getPrice_Of_Required_PrintOption="//table[@class='print-options-info']//tbody/tr["+i+"]/td[5]";
				Ps_price=_driver.findElement(By.xpath(getPrice_Of_Required_PrintOption)).getAttribute("textContent").trim();
				break;
			}
		}
		if(i==total_ps && !psName.equalsIgnoreCase(printoptionName) )
		{
			System.err.println("There is no price calculated for the :"+printoptionName+ " Check manually once");
		}

		return Ps_price;
	}

	public float Verify_Price_PrintOption(String PrintOption,String ps_Price,String qty,float ps_UnitPrice)
	{
		String ps_Price_String=ps_Price.substring(1);
		float actual_ps_Price_Value=Float.valueOf(ps_Price_String);
		float itemQty=Float.valueOf(qty);
		float expected_ps_Price_value=ps_UnitPrice*itemQty;
		DecimalFormat df = new DecimalFormat("0.##");
		df.setRoundingMode(RoundingMode.DOWN);
		float expected_Ps_Price=Float.valueOf(df.format(expected_ps_Price_value));
		if(expected_Ps_Price==actual_ps_Price_Value)
		{
			System.out.println("Price calculated properly for the printoption selected :"+PrintOption);
		}
		else
		{
			System.err.println("Price calculated for the printoptions is wrong and actual price is:  "+actual_ps_Price_Value+ " and expected price is :"+expected_Ps_Price);
		}
		return expected_Ps_Price; 
	}

	public String verifyprintServicePricing(float itemqty,float Totalpages,boolean Nup,String impressionPriceAsPerMediaSelected, float otherPrintOptionPrice) throws Exception
	{

		_driver.manage().window().maximize();
		Thread.sleep(3000);
		WebElement unitPrice=_driver.findElement(By.id("unitPriceSpan"));
		Actions action=new Actions(_driver);
		action.moveToElement(unitPrice).perform();  //Temp Comment

		//************Get the prices *********************************//
		String bindingPrice_Xpath="//table[@class='print-options-info']/tbody/tr[2]/td[5]";
		String CuttingPrice_Xpath="//table[@class='print-options-info']/tbody/tr[3]/td[5]";
		String impressionPrice_Xpath="//table[@class='print-options-info']/tbody/tr[4]/td[5]";
		String SubTotal_Xpath_Xpath="//tr[td[text()='Subtotal']]/td[2]";
		String Fee_Xpath="//tr[td[text()='Fee']]/td[2]";
		String TaxAuth_Xpath="//tr[td[text()='Auto_Tax_Auth']]/td[2]";
		String Total_Xpath="//tr[td[text()='Total']]/td[2]";

		//Fetching the actual Prinservices Values	
		String bindingPrice=_driver.findElement(By.xpath(bindingPrice_Xpath)).getAttribute("textContent").trim();
		String CuttingPrice=_driver.findElement(By.xpath(CuttingPrice_Xpath)).getAttribute("textContent").trim();
		String impressionPrice=_driver.findElement(By.xpath(impressionPrice_Xpath)).getAttribute("textContent").trim();
		String subTotalPrice=_driver.findElement(By.xpath(SubTotal_Xpath_Xpath)).getAttribute("textContent").trim();
		String feePrice=_driver.findElement(By.xpath(Fee_Xpath)).getAttribute("textContent").trim();

		float ExpectedTaxValue=productLevelTax(TaxAuth_Xpath);
		String TotalPrice=_driver.findElement(By.xpath(Total_Xpath)).getAttribute("textContent").trim();


		//Assuming the print service prices for 1 qty
		String _1qtyBindingPrice="2.00";
		String _1qtyCuttingPrice="1.20";
		String _1qtyImpressionPrice=impressionPriceAsPerMediaSelected; //it was with "0.35"

		//		float expectedqtyBindingPrice=Float.valueOf(_1qtyBindingPrice)*itemqty;
		//		float expectedqtyCuttingPrice=Float.valueOf(_1qtyCuttingPrice)*6*itemqty;
		//		float expectedqtyImpressionPrice=Float.valueOf(_1qtyImpressionPrice)*12*itemqty;


		float expectedqtyBindingPrice=Float.valueOf(_1qtyBindingPrice)*itemqty;
		float expectedqtyCuttingPrice1=Float.valueOf(_1qtyCuttingPrice)*(Totalpages/2)*itemqty;

		//float expectedqtyImpressionPrice=Float.valueOf(_1qtyImpressionPrice)*Totalpages*itemqty;
		float expectedqtyImpressionPrice1 =calculatingImpressionPrice(_1qtyImpressionPrice,itemqty,Totalpages,Nup);

		DecimalFormat df = new DecimalFormat("0.##");
		df.setRoundingMode(RoundingMode.DOWN);
		float expectedqtyCuttingPrice=Float.valueOf(df.format(expectedqtyCuttingPrice1));
		float expectedqtyImpressionPrice=Float.valueOf(df.format(expectedqtyImpressionPrice1));




		//Removing the $ from the prices
		String bindingValue= bindingPrice.substring(1);
		String cuttingValue=CuttingPrice.substring(1);
		String impressionvalue=impressionPrice.substring(1);
		String Subtotalvalue=subTotalPrice.substring(1);
		String feeevalue=feePrice.substring(1);
		//		 String taxValue=TaxAuthPrice.substring(1);
		String TotalValue=TotalPrice.substring(1);

		//Converting the Actual Price String to Float Values
		float ActualqtyBindingPrice=Float.valueOf(bindingValue);
		float ActualqtyCuttingPrice=Float.valueOf(cuttingValue);
		float ActualimpressionPrice=Float.valueOf(impressionvalue);
		float ActualSubtotalvalue=Float.valueOf(Subtotalvalue);
		float ActualfeeValue=Float.valueOf(feeevalue);

		float ActualTotalValue=Float.valueOf(TotalValue);


		if(expectedqtyBindingPrice==ActualqtyBindingPrice)
		{
			System.out.println("Binding price calcualted properly" + "and price is " +ActualqtyBindingPrice );
		}
		else
		{
			System.err.println("Binding price calcualted is wrong" + "  and price is for  "+ itemqty + " quanity is"+ ActualqtyBindingPrice );
		}

		if(expectedqtyCuttingPrice==ActualqtyCuttingPrice)
		{
			System.out.println("Cutting price calcualted properly" + "and price is " +ActualqtyCuttingPrice );
		}
		else
		{
			System.err.println("Cutting price calcualted is wrong" + "and price is for"+ itemqty + " quanity is"+ ActualqtyCuttingPrice );	
		}

		if(expectedqtyImpressionPrice==ActualimpressionPrice)
		{
			System.out.println("Impression price calcualted properly" + "and price is " + ActualimpressionPrice);
		}
		else
		{
			System.err.println("Impression price calcualted is wrong  " + " and price is for "+ itemqty + "quanity Actual price is "+ ActualimpressionPrice  +" and Expected Impression Price is :"+expectedqtyImpressionPrice);
		}

		//Calculating total Price after changing the qty


		float expectedsubTotalPrice=ActualqtyBindingPrice+ActualqtyCuttingPrice+ActualimpressionPrice+otherPrintOptionPrice;

		if(expectedsubTotalPrice==ActualSubtotalvalue)
		{
			System.out.println("Sub total calculated proeperly and Sunb total value is : "+ActualSubtotalvalue);
		}
		else
		{
			System.err.println("Sub total Calculated Wronglt and Expected Sub total Value is :" +expectedsubTotalPrice + " actual:- "+ ActualSubtotalvalue);
		}
		float ExpectedFeevalue=26;
		//float ExpectedTaxValue= (float) 15.90;
		float expectedTotalPrice=expectedsubTotalPrice+ExpectedFeevalue+ExpectedTaxValue;
		if(expectedTotalPrice==ActualTotalValue)
		{
			System.out.println("total Price Calculated Properly and the total prce is :"+ActualTotalValue);
		}
		else
		{
			System.err.println("total price calacualted wrongly and expected total price :"+expectedTotalPrice+ "and Actual price is :"+ ActualTotalValue);
		}

		return TotalValue;

	}

	public float productLevelTax(String TaxAuth_Xpath)
	{
		try{
			String TaxAuthPrice=_driver.findElement(By.xpath(TaxAuth_Xpath)).getAttribute("textContent").trim();
			String taxValue=TaxAuthPrice.substring(1);
			float ActualtaxValue=Float.valueOf(taxValue);
			float ExpectedTaxValue= (float) 15.90;
			return ExpectedTaxValue;
		}

		catch(NoSuchElementException  e){
			float ExpectedTaxValue= (float) 00.00;
			return ExpectedTaxValue;

		}
	}

	public float calculatingImpressionPrice(String _1qtyImpressionPrice,float itemqty,float Totalpages,boolean Nup )
	{
		if(Nup==true) 
		{
			float expectedqtyImpressionPrice=Float.valueOf(_1qtyImpressionPrice)*(Totalpages/2)*itemqty;
			return expectedqtyImpressionPrice;
		}
		else
		{
			float expectedqtyImpressionPrice=Float.valueOf(_1qtyImpressionPrice)*Totalpages*itemqty;
			return expectedqtyImpressionPrice;
		}

	}

	public void verify_the_field_values(String fieldname,String expectedValue)
	{
		String fieldnput_Xpath="//tbody[tr[td[table[tbody[tr[td[text()='"+fieldname+"']]]]]]]//table[1]//input";
		String actualValue= _driver.findElement(By.xpath(fieldnput_Xpath)).getAttribute("value").trim();
		if(actualValue.equals(expectedValue))
		{
			System.out.println("For the Field Name : "+fieldname+ " Expected Value is been displayed properly that is " +actualValue );
		}
		else
		{
			System.err.println("For the Field Name : "+fieldname+ "Expected Value is not been dispalyed and actual value is "+actualValue+ "Expected Value is :"+expectedValue);
		}


	}

	public void enter_VDP_FieldValues(String fieldName,String inputValue)
	{
		String fieldnput_Xpath="//tbody[tr[td[table[tbody[tr[td[text()='"+fieldName+"']]]]]]]//table[1]//input";
		_driver.findElement(By.xpath(fieldnput_Xpath)).sendKeys(inputValue);
	}

	public void selectAnImageFromGallery() throws Exception
	{
		String selectFromGallaryLink="//a[text()='Select from Gallery']";
		String selectAnImage_Xpath="//a[text()='Select']";
		String imageXpath="page1.jpg";
		_driver.findElement(By.xpath(selectFromGallaryLink)).click();
		CommonFunctions.Wait(_driver, By.xpath(selectAnImage_Xpath));
		_driver.findElement(By.xpath(selectAnImage_Xpath)).click();
		CommonFunctions.Wait(_driver, By.xpath(imageXpath));
		if(_driver.findElements(By.xpath(imageXpath)).size()>0)
		{
			System.out.println("Image selected from gallery successfully");
		}
		else
		{
			System.err.println("Image selction from gallery unsuccessful chek manually once ");
		}

	}

	public void Enter_Data_Into_TextArea(String data)
	{
		String biography_textArea_Xpath=".//*[@id='personalizationContentDiv']//textarea";
		_driver.findElement(By.xpath(biography_textArea_Xpath)).sendKeys(data);

	}

	public boolean Tabs_PrintServiceFromThumbNaailView(boolean InsertTabs,String Insert_Before_Page,String Insert_After_Page) throws Exception
	{
		if (InsertTabs)
		{
			System.out.println("Inserting Tab process is started..");
		}
		else
		{			
			System.out.println("Tabs are not selected as per the user option.");
			return true;
		}

		//		 //======================================================================================================
		//		//click on Tabs ps
		//		 //======================================================================================================
		//		 
		//		 
		//		 if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_Option)))))
		//		 {
		//			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_Option))).click();
		//			 System.out.println("Tabs Print service is displayed and clicked on the same.");
		//		 }
		//		 else
		//		 {
		//			 System.err.println("Tabs option is not displayed. Pls check locator 'Tabs_Option'");
		//			 return false;
		//		 }
		//		 //======================================================================================================		 
		//		//Click on Insert Tabs
		//		 //======================================================================================================
		//		 if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.Insert_Tabs)))))
		//		 {
		//			 _driver.findElement(By.xpath(Locators.getProperty(Locators.Insert_Tabs))).click();
		//			 System.out.println("Insert Tabs Print service is displayed and clicked on the same.");
		//		 }
		//		 else
		//		 {
		//			 System.err.println(" Insert Tabs option is not displayed. Pls check locator 'Tabs_Option'");
		//			 return false;
		//		 }
		//		
		//======================================================================================================
		//Verify Insert Tab page is displayed or not.
		//======================================================================================================
		for (int dcount = 0;dcount<=15;dcount++)
		{
			Thread.sleep(1000);

			try
			{
				if (_driver.findElement(By.xpath("//div[@class='tabs-container-for-mobile']")).isDisplayed())

				{
					System.out.println("Insert Tabs page is displayed");
					break;
				}
			}
			catch (NoSuchElementException e)
			{
				System.err.println("Insert Tabs page is not displayed..");
				return false;
			}
		} // End for loop

		//======================================================================================================		 
		//click on drop down icon and  Choose Tabs (5 Across)
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.InsertTabs_Dropdown)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.InsertTabs_Dropdown))).click();
			System.out.println("Drop down icon to choose tabs is displayed and clicked on the same.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Tabs_5_across))).click();
			System.out.println("Tabs (5 Across) is clicked.");
		}
		else
		{
			System.err.println("Drop Down icon is not displayed. Pls check locator 'InsertTabs_Dropdown'");
			return false;
		}

		//======================================================================================================		 
		//choose 2 tabs and provide the data
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.NoofTabs)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.id(Locators.getProperty(Locators.NoofTabs)),"1");
			System.out.println("2 tabs are provided");					 
		}
		else
		{
			System.err.println("NoofTabs id is not available.");
			return false;
		}

		//======================================================================================================		 
		//Click on Add button
		//======================================================================================================
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_Accept)))))
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_Accept))).click();
			System.out.println("Tabs are accepted");					 
		}
		else
		{
			System.err.println("Tabs are not accepted. Check for locator 'tab_Accept' ");
			return false;
		}

		//======================================================================================================		 
		//Update tab1  details.
		//======================================================================================================

		//----for Tab1
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext1))).sendKeys("Test_Chapter1");
			System.out.println("Tab Text is inserted");					 
		}
		else
		{
			System.err.println("Tabs text is not accepted. Check for locator 'tabtext1' ");
			return false;
		}

		//choose Insert before page number option  

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab1_Insert_Before_or_after)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.xpath(Locators.getProperty(Locators.tab1_Insert_Before_or_after)),"Insert Before Page number");
			System.out.println("Insert before page number option is selected..");					 
		}
		else
		{
			System.err.println("Insert before page number option is not selected ");
			return false;
		}


		//update the page number to insert the tab
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number1))).sendKeys(Insert_Before_Page);
			System.out.println("Insert Tab before page.."+Insert_Before_Page);					 
		}
		else
		{
			System.err.println("Insert before page number data is not entered ");
			return false;
		}
		//==========Tab1


		//----for Tab2
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tabtext2))).sendKeys("Test_Chapter2");
			System.out.println("Tab Text is inserted");					 
		}
		else
		{
			System.err.println("Tabs text is not accepted. Check for locator 'tabtext2' ");
			return false;
		}

		//choose Insert before page number option  

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab2_Insert_Before_or_after)))))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.xpath(Locators.getProperty(Locators.tab2_Insert_Before_or_after)),"Insert After Page number");
			System.out.println("Insert before page number option is selected for second tab..");					 
		}
		else
		{
			System.err.println("Insert before page number option is not selected for second tab");
			return false;
		}


		//update the page number to insert the tab
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2)))))
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.tab_page_Number2))).sendKeys(Insert_After_Page);
			System.out.println("Insert Tab before page.."+Insert_Before_Page);					 
		}
		else
		{
			System.err.println("Insert before page number data is not entered ");
			return false;
		}
		//==========Tab2
		_driver.manage().window().maximize();
		//click on Accept button
		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_cancel_button)))))
		{				 
			System.out.println("Cancel button is  displayed.");					 
		}
		else
		{
			System.err.println("Cancel button is not displayed.");				 
		}

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_OK_Button)))))
		{				 
			System.out.println("Accept button is  displayed."); Thread.sleep(3000);		
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(Locators.getProperty(Locators.tab_OK_Button)));
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.tab_OK_Button))).click();
			return true;
		}
		else
		{
			System.err.println("Accept button is not displayed.");				 
		}			 
		return false;
	}

	public void ClickUpatePreviewButton_and_VerifyPages(int ExpectedNoOFPages) throws Exception
	{
		//Click on update preview and verify the expected Number of pages
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}		


		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					//-------------------------------------------------------------------------------------------------						
					//to check the number pages.As product is maintained in db it should show as expected pages.
					//Expected is hard coded here..as 92. Update code with required value if occurs in future.
					//need to take care this parameter from excel. Note..Krishna..
					//-------------------------------------------------------------------------------------------------
					String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

					if (Nooffile_Uploaded.contains("/"))
					{
						String[] Nooffiles = Nooffile_Uploaded.split("/");
						int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
						if (TotalFiles == ExpectedNoOFPages)
						{
							System.out.println("xMPIE product with " +ExpectedNoOFPages+ "pages are uploaded correctly.. ");
							break;
						}
						else
						{
							System.err.println("Pages are not available correctly");

							System.err.println("No.Of pages Actually available:"+TotalFiles);

						}
					}
				}
				else
				{
					System.out.println("Still updating the preview");
					Thread.sleep(3000);
				}
			}
		}	
	}

	public void verifyMediaSize(boolean flag) throws Exception
	{

		String horizontalSize_Xpath=".//*[@id='singlePageViewContainer']/canvas[2]";
		String verticalSize_Xpath=".//*[@id='singlePageViewContainer']/canvas[3]";

		if(flag==true)
		{
			try{

				CommonFunctions.Wait(_driver, By.xpath(horizontalSize_Xpath));
				CommonFunctions.Wait(_driver, By.xpath(verticalSize_Xpath));

				if(_driver.findElement(By.xpath(horizontalSize_Xpath)).isDisplayed() & _driver.findElement(By.xpath(verticalSize_Xpath)).isDisplayed())
				{
					System.out.println("Media size is displayed for dynamic preview enabled ");
				}
			}
			catch (NoSuchElementException e)
			{
				System.err.println("Media size is not displayed for dynamic preview enabled please verify manually once");
			}

		}
		else
		{

			try{

				CommonFunctions.Wait(_driver, By.xpath(horizontalSize_Xpath));
				if(_driver.findElement(By.xpath(horizontalSize_Xpath)).isDisplayed() & _driver.findElement(By.xpath(verticalSize_Xpath)).isDisplayed())
				{
					System.err.println("Media size is displayed for dynamic preview displayed please check manually once ");
				}
			}
			catch (NoSuchElementException e)
			{

				System.out.println("Media size is not displayed for dynamic preview disabled");
			}

		}
	}

	public void VerifyDownLoadButton_OrderConfirmationPage()
	{
		String download="//span[text()='Download']";
		if(_driver.findElements(By.xpath(download)).size()>0)
		{
			System.out.println("Download button is available at the orderconfirmation page");
			//		_driver.findElement(By.xpath(download)).click();
			System.out.println("Perform  click action on download button to download the vdp pdf");
		}
		else
		{
			System.err.println("Download button is not avavilable at the orderconfirmation page pleae chack manaully once ");
		}
	}

	public boolean production_Job_Ticket_Modification_New(String JobName,String Companay_Name,String StrOrderNumber,String FileName,boolean QTY_Change,String OriginalQTy,String Update_Qty_Value,boolean PSchange,String PrintServiceName,String SubMenuprintServiceName) throws Exception
	{
		String Oringal_File_Name= "//*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_OriginalFileRow']//a[contains(text(),'"+FileName+"')]";
		String File_Uploaded = " //*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_FilesRow']//a[contains(text(),'"+FileName+"')]";

		//	 //Navigate to Admin
		//	  CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.AdminTab)));
		//	 if (_driver.findElements(By.xpath(Locators.getProperty(Locators.AdminTab))).size()>0)
		//	 {
		//		 System.out.println("Admin Tab is available..");
		//		 CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.AdminTab)));
		//		 _driver.findElement(By.xpath(Locators.getProperty(Locators.AdminTab))).click();
		//		 System.out.println("Admin Tab is clicked");
		//	 }
		//	 else
		//	 {
		//		 System.err.println("Admin Tab is not available.");
		//	 }
		//	 
		//Click on Customer order history
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Order_History))).isDisplayed())
		{
			System.out.println("Customer Order History link is available..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Order_History))).click();
			System.out.println("Customer Order History Tab is clicked");
		}
		else
		{
			System.err.println("Customer Order History Link is not available.");
		}

		//Provide customer order number
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).isDisplayed())
		{
			System.out.println("Order Number field is available..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber_Search))).sendKeys(StrOrderNumber);
			System.out.println("Order Number is entered as::"+StrOrderNumber);
		}
		else
		{
			System.err.println("Order Number field is not available.");
		}

		//Click on search button
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Search_Btn))).click();
		System.out.println("Search Button is clicked.");

		String PJT_Link = "//a[contains(text(),'"+JobName.trim()+"')]";

		//Check the production job ticket link
		if (_driver.findElement(By.xpath(PJT_Link)).isDisplayed())
		{
			System.out.println("production Job Ticket is available..");
			_driver.findElement(By.xpath(PJT_Link)).click();	
			Thread.sleep(3000);
		}
		else
		{
			System.err.println("Production Job Ticket Link is not available.");
			System.err.println("Used xpath::"+PJT_Link);
		}

		//Get the driver details of production job ticket		 

		String  originalHandle = _driver.getWindowHandle();
		System.out.println("originalHandle of customer order hisotory is::"+originalHandle);
		//  Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();

		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{      

				if(_driver.switchTo().window(windowId).getTitle().equals("Production Job Ticket")) 
				{	

					//Check original and files which are already uploaded are available
					if(_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_Rep_OriginalFiles_ctl01_lnkFileLocation")).isDisplayed())
					{
						System.out.println("original File is available");
						System.out.println("Original File name is::"+_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_Rep_OriginalFiles_ctl01_lnkFileLocation")).getText());
					}
					else
					{
						System.err.println("original File is not available.");
					}

					if(_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_FileRepeater_ctl01_FileLinkButton")).isDisplayed())
					{
						System.out.println("File converted as PDF is available");
					}
					else
					{
						System.err.println("File converted as PDF is not available.");
					}

					//Capture the details of ticket.
					//to Check the qy
					String QTY_xpath = "//tr[td[contains(text(),'Quantity')]]//td[contains(text(),"+ OriginalQTy+")]";

					if(_driver.findElement(By.xpath(QTY_xpath)).isDisplayed())
					{
						System.out.println("Oringal Qty data is available");
					}
					else
					{
						System.err.println("Oringinal Qty Data is not available.");
						System.err.println("Oringal QTy expected as::"+OriginalQTy);
						System.err.println("Xpath used as::"+QTY_xpath);
					}

					//To Check the Product name
					String ProdName = "//span[contains(@id,'lblProductType')]";   
					String Compname = "//span[contains(@id,'CompanyNameValue')]";
					if(_driver.findElement(By.xpath(ProdName)).isDisplayed())
					{
						System.out.println("Product Type label is available");
						System.out.println("Product type is::"+_driver.findElement(By.xpath(ProdName)).getText());
					}
					else
					{
						System.err.println("Product Type label is not available.");
					}

					System.out.println("Company name appeard in PJT as::"+_driver.findElement(By.xpath(Compname)).getText());

					//company Name

					if (Companay_Name.equalsIgnoreCase(_driver.findElement(By.xpath(Compname)).getText()))
					{
						System.out.println("Comapany name is available in PJT correctly");
					}
					else
					{
						System.err.println("Expected company:-"+Companay_Name);
						System.err.println("Actual Company shown in PJT::"+_driver.findElement(By.xpath(Compname)).getText());
					}

					//To click on Edit button..
					try
					{
						_driver.findElement(By.id("ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_FileRepeater_ctl00_FilesEditLinkButtonForFlexTicket")).click();
						System.out.println("Clicked on Edit link button");
					}
					catch(NoSuchElementException e)
					{

						_driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_PrintOptionsRepeater_ctl00_JobSummaryParts_OptionsEditLinkForTicketWizard']")).click();
						System.out.println("Clicked on Edit link button");
					}
					Thread.sleep(15000);

					//Commented temp
					//                             WebElement iframe = _driver.findElement(By.xpath(".//*[@id='FlexTicketingDiv']/table/tbody/tr[1]/td/iframe"));
					//                        	 _driver.switchTo().frame(iframe);
					availableWindows = _driver.getWindowHandles();

					if (!availableWindows.isEmpty()) 
					{
						for (String windowId1 : availableWindows) 
						{      

							if(_driver.switchTo().window(windowId1).getTitle().equals("Ticket Wizard"))

							{	 


								//if qty is need to be changed then update the same
								if (QTY_Change)
								{
									try
									{	
										//String Update_Qty_Value="30";
										_driver.findElement(By.xpath("//Input[contains(@id,'quantityInput1')]")).clear();
										_driver.findElement(By.xpath("//Input[contains(@id,'quantityInput1')]")).sendKeys(Update_Qty_Value);
										System.out.println("QTY value is updated as:"+Update_Qty_Value);
									}
									catch(Exception e)
									{
										_driver.findElement(By.xpath(".//*[contains(@id,'TextBoxQuantity')]")).clear();
										_driver.findElement(By.xpath(".//*[contains(@id,'TextBoxQuantity')]")).sendKeys(Update_Qty_Value);
										System.out.println("QTY value is updated as:"+Update_Qty_Value);
										e.printStackTrace();
									}
								}	         
								Thread.sleep(2000);
								//get the print service changed.
								if (PSchange)
								{

									//String MainPrintService="//div[contains(@title,'"+PrintServiceName +"')]";
									String MainPrintService="//span[text()='"+PrintServiceName+"']";
									String SubPrintService = "//span[text()='"+SubMenuprintServiceName+"']";
									//String SubPrintService = "//li[contains(@title,'"+ SubMenuprintServiceName+"')]";

									try
									{
										if (_driver.findElement(By.xpath(MainPrintService)).isDisplayed())
										{
											System.out.println("Main Print Service is displayed.");
											_driver.findElement(By.xpath(MainPrintService)).click();
											Thread.sleep(5000);
										}

										if (_driver.findElement(By.xpath(SubPrintService)).isDisplayed())
										{
											System.out.println("sub Menu Print Service is displayed.");
											_driver.findElement(By.xpath(SubPrintService)).click();
										}
									}
									catch(NoSuchElementException e)
									{
										MainPrintService=".//*[contains(@value,'Collate')]";
										SubPrintService=".//*[text()='Do Not Collate']";
										if (_driver.findElement(By.xpath(MainPrintService)).isDisplayed())
										{
											System.out.println("Main Print Service is displayed.");
											_driver.findElement(By.xpath(MainPrintService)).click();
											Thread.sleep(5000);
										}

										if (_driver.findElement(By.xpath(SubPrintService)).isDisplayed())
										{
											System.out.println("sub Menu Print Service is displayed.");
											_driver.findElement(By.xpath(SubPrintService)).click();
										}
									}
								}
								else
								{
									System.out.println("Print services are not changed as per the option");
								}
								//Click on updatePreviewSpan

								try
								{
									_driver.findElement(By.id("updatePreviewSpan")).click();
									System.out.println("Update Preview button is clicked.");
									Thread.sleep(30000);

									//Click on update button
									_driver.findElement(By.id("addtoCartBtnSpan")).click();
									System.out.println("Addto Cart Button is clicked.");
								}
								catch(NoSuchElementException e)
								{
									_driver.findElement(By.xpath(".//*[contains(@value,'Next')]")).click(); Thread.sleep(3000);
									_driver.findElement(By.xpath(".//*[contains(@value,'Update')]")).click();
								}

								try
								{
									if (_driver.findElement(By.xpath("//span[contains(text(),'The Ordered Quantity and the Recipient Quantities do not match')]")).isDisplayed())
									{
										System.out.println("'The Ordered Quantity and the Recipient Quantities do not match'  String is displayed.");
									}
								}

								catch(NoSuchElementException e1)
								{
									System.err.println("'The Ordered Quantity and the Recipient Quantities do not match' string is not displayed");
									e1.printStackTrace();
								}                         	 


								//
								//Get the updated details of QTy and changed print service details and ensure that choosen details are available.
								QTY_xpath = "//tr[td[contains(text(),'Quantity')]]//td[contains(text(),"+Update_Qty_Value+")]";

								if(_driver.findElement(By.xpath(QTY_xpath)).isDisplayed())
								{
									System.out.println("Updated Qty data is available");
								}
								else
								{
									System.err.println("Oringinal Qty Data is not available.");
									System.err.println("Updated QTy expected as::"+Update_Qty_Value);
									System.err.println("Xpath used as::"+QTY_xpath);
								}

								String Updated_Ps="//td[contains(text(),'"+SubMenuprintServiceName+"')]";

								if(_driver.findElement(By.xpath(Updated_Ps)).isDisplayed())
								{
									System.out.println("Print service is updated as:: "+SubMenuprintServiceName);
								}
								else
								{
									System.err.println("Print service is not updated in PJT");
									System.err.println("PS is expected as::"+SubMenuprintServiceName);
									System.err.println("Xpath used as::"+Updated_Ps);
								}	                            	
							}//End if
						}//End for
					}//end if
					_driver.close();
					_driver.switchTo().window(originalHandle);
					return true;
				}		}


		}	
		else
		{
			System.err.println("Browser Window is not available.");
		}






		return false;
	}

	public void MCD_Selection_ListBox_Mapping_All_Records(String MCDName) throws Exception
	{
		//Choose list box with provided MCD data
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.FP_Select_MCD))).size() > 0 )
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath(Locators.getProperty(Locators.FP_Select_MCD)),MCDName );
			System.out.println("MCD option is selected as::"+MCDName);	
		}
		else
		{
			System.err.println("Provided MCD option::"+MCDName+" is not selected.");

		}

		//check data soruce table window is appeared or not
		for (int count =0;count<=10;count++)
		{
			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.FP_DataSource_window))).size() > 0)
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_DataSource_window))).isDisplayed())
				{
					System.out.println("Data Source window is displayed..");
					break;
				}
			}
			Thread.sleep(3000);
		}

		//Mapping all records
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Mapping_XP))).isDisplayed())
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Select_All_Records_Checkbox_xp))).click();
			System.out.println("Column details are selected.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_Mapping_XP))).click();
			System.out.println("Mapping button is displayed.");
		}
		else
		{
			System.err.println("Mapping button is not displayed.");
		}
		//Map data select dropdown section
		//check map data		
		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_Map_Data_tab))).isEnabled())
		{
			System.out.println("Map data tab is enabled.");
		}
		else
		{
			System.err.println("Map Data Tab is not enabled.");
		}
		//Map the field value from the available field value drop down list
		String Total_DropDown_Fields=".//*[@id='selectdata']/div/table/tbody//tr/td[2]/select";
		int totalDropDownfields=_driver.findElements(By.xpath(Total_DropDown_Fields)).size();
		String[] value={"0","1","2","3","4","5","6","7"};
		for(int i=2;i<=totalDropDownfields+1;i++)
		{
			String dropdownField=".//*[@id='selectdata']/div/table/tbody//tr["+i+"]/td[2]/select";
			Select sele = new Select(_driver.findElement(By.xpath(dropdownField)));
			sele.selectByValue(value[i-2]);
		}

		//click on fill form..
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_FillForm_XP))).isDisplayed())
		{_driver.findElement(By.xpath(Locators.getProperty(Locators.FP_BatchMode_FillForm_XP))).click();
		System.out.println("fill fort button is clicked.");
		}
		else
		{
			System.err.println("Fill Form button is not displayed.");
		}
		//check the records..
		for (int i =0;i<=10;i++)
		{
			if(_driver.findElements(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).size() > 0)
			{
				boolean Records_enabled = _driver.findElement(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).isEnabled();

				if (Records_enabled)
				{
					System.out.println("FP multi column data selection is completed.");
					break;
				}
				Thread.sleep(3000);
			}
		}



	}

	public int Fetch_NumBer_Records_VDP(int ExpectedRecords)
	{
		String NoofRecords = _driver.findElement(By.id(Locators.getProperty(Locators.FP_NO_of_Records))).getAttribute("placeholder");

		String[] Nooffiles = NoofRecords.split("/");
		int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
		if (TotalFiles == ExpectedRecords)
		{
			System.out.println("FP product with" +ExpectedRecords+ "Records are uploaded correctly.. ");

		}
		else
		{
			System.err.println("Records are not available correctly");
			System.err.println("No.Of Records expected :"+ExpectedRecords);
			System.err.println("No.Of records Actually available:"+TotalFiles);

		}

		return TotalFiles;

	}

	public int Verify_number_Of_Pages(int ExpectedRecords)
	{
		String NoofRecords = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

		String[] Nooffiles = NoofRecords.split("/");
		int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
		if (TotalFiles == ExpectedRecords)
		{
			System.out.println( +ExpectedRecords+ "  pgaes are uploaded correctly.. ");

		}
		else
		{
			System.err.println("pages are not available correctly");
			System.err.println("No.Of pages expected :"+ExpectedRecords);
			System.err.println("No.Of pages Actually available:"+TotalFiles);

		}

		return TotalFiles;

	}

	public void Click_On_Update_Preview_Button()
	{
		//Click on update preview and proceed with addto cart buttons.
		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}		
	}

	public void verify_Pdf_Proof_For_VDP() throws Exception
	{
		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					break;

				}
				else
				{
					System.out.println("Still updating the preview");
					Thread.sleep(3000);
				}
			}
		}	

	}

	public void verify_Records(int actualRecords, String page) throws Exception
	{
		String RecordCount="//span[@ng-show='item._VDPTotalRecordCount'][1]";
		CommonFunctions.Wait(_driver, By.xpath(RecordCount));
		String recordCount=_driver.findElement(By.xpath(RecordCount)).getText().trim();
		int NoOFrecord=Integer.parseInt(recordCount);
		if(NoOFrecord==actualRecords)
		{
			System.out.println("Number of Records shown at  "+page+" is correct");
		}
		else
		{
			System.err.println("Number of records shown at  "+page+" is incorrect check manually once");
		}

	}

	public void Verify_QTY(String expectedQty, String page) throws Exception
	{
		String QtyCount="//span[@class='quanity-value ng-binding']";
		CommonFunctions.Wait(_driver, By.xpath(QtyCount));
		String Qty_Value=_driver.findElement(By.xpath(QtyCount)).getText().trim();
		int qty_value=Integer.parseInt(Qty_Value);
		int expected_qty=Integer.parseInt(expectedQty);
		if(qty_value==expected_qty)
		{
			System.out.println("Quanity displayed in "+page+"  page is correct");	
		}
		else
		{
			System.err.println("Quantity displayed in "+page+"  page is incorrect");
		}

	}

	public void Edit_PJT() throws Exception
	{
		String Edit_Button_Xpath=".//*[@id='ctl00_ctl00_C_M_JobSummary_Repeater_ctl00_JobSummary_JobList_ctl01_PrintOptionsRepeater_ctl00_JobSummaryParts_OptionsEditLinkForTicketWizard']";
		_driver.findElement(By.xpath(Edit_Button_Xpath)).click();
		String collate_xpath="//input[contains(@value,'Collate into Sets')]";
		CommonFunctions.Wait(_driver, By.xpath(collate_xpath));
		//Click on collate dropdown
		_driver.findElement(By.xpath(collate_xpath)).click();
		String doNotCollate_Xpath=".//Span[text()='Do Not Collate']";
		//Click on do not collate xpath
		_driver.findElement(By.xpath(doNotCollate_Xpath)).click();

		//Click on next button
		String next_Button_Xpath=".//*[@id='ctl00_C_ctl00_W_StepNavigationTemplateContainerID_StepNextButton']";
		_driver.findElement(By.xpath(next_Button_Xpath)).click();

		//Click on update button
		String updateButton_Xpath=".//*[@id='ctl00_C_ctl00_W_FinishNavigationTemplateContainerID_FinishButton']";
		_driver.findElement(By.xpath(updateButton_Xpath)).click();



	}

	public void Set_WrapAround_Cover() throws Exception
	{
		String wrapCover_Xpath="//a/label[@id='spanFC123']";
		String coloroption_Xpath="//a/label[@id='span-16']";
		String color_listBox_xpath="//div[@class='selected-items-box']";
		String Color_Option_Xpath="//td[text()='Front and Back inside and outside']";
		String okButton_Xpath="//a[@class='OK-button']";
		_driver.findElement(By.xpath(wrapCover_Xpath)).click();
		Thread.sleep(3000);
		_driver.findElement(By.xpath(coloroption_Xpath)).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(color_listBox_xpath)).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Color_Option_Xpath)).click();
		_driver.findElement(By.xpath(okButton_Xpath)).click();  
	}

	public void Insert_Blank_pages(int PageNumberAfterToInsertBlankPage, String xpthTo_InserAfterBlank) throws Exception
	{
		//Click on thumbnail View button
		String thumbnailView_Xpath="//span[@class='icon-large-thumbnails']";
		String Page=".//*[@id='thumbnails']/div["+PageNumberAfterToInsertBlankPage+"]/div[1]/div/canvas";
		String InsertBlankAfter=xpthTo_InserAfterBlank;

		CommonFunctions.Wait(_driver, By.xpath(thumbnailView_Xpath));
		_driver.findElement(By.xpath(thumbnailView_Xpath)).click();
		CommonFunctions.Wait(_driver, By.xpath(Page));
		WebElement FirstPageCanvas= _driver.findElement(By.xpath(Page));
		//Right Click on the first page
		Actions action=new Actions(_driver);
		action.contextClick(FirstPageCanvas).build().perform();
		//Click on the After Page Tab link from the context menu
		_driver.findElement(By.xpath(InsertBlankAfter)).click();


	}

	public String Verify_CCH_Tax(String page)
	{
		//Verify the  CCH tax at cart page
		String Taxes_Xpath="//tr[td[div[h2[text()='Taxes' or text()='Taxes:']]]]//td[2]/div[1]/h3";
		if(_driver.findElement(By.xpath(Taxes_Xpath)).isDisplayed())
		{
			System.out.println("Tax is been displayed in the " +page+ " page");
			String actualtaxValue=_driver.findElement(By.xpath(Taxes_Xpath)).getText().trim();
			System.out.println("Tax value shown at " +page+ " page is: "+actualtaxValue);
			return actualtaxValue;

		}
		else
		{
			System.err.println("Tax is not been displayed in the" +page+ "page");
			return "";
		}
	}

	public void Click_On_ProceedTOCheckOut() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		for(int i=0;i<=3;i++)
		{
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Proceed_To_CheckOut_link)));
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Proceed_To_CheckOut_link))).size()>0)
			{
				WebElement proceed_to_check_out = _driver.findElement(By.xpath(Locators.getProperty(Locators.Proceed_To_CheckOut_link)));
				if(CommonFunctions.Elementdisplayed_Enabled(proceed_to_check_out))
				{
					CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.Proceed_To_CheckOut_link)));
					System.out.println("Proceed to check out link is clicked");
					CommonFunctions.waitForPageLoaded(_driver);
					Thread.sleep(3000);
					break;
				}
				else
				{
					Thread.sleep(3000);
				}
			}
		}
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)));
	}

	public String fetch_OrderNumber() throws Exception
	{
		String Ordernum=null;
		WebElement  Order_Number = _driver.findElement(By.xpath(Locators.getProperty(Locators.Order_Number)));
		for(int i=0;i<3;i++)
		{
			Ordernum  = Order_Number.getText();
			if(!Ordernum.isEmpty())
			{
				System.out.println("Order Number fetched" +Ordernum);
				break;
			}
			else
			{
				Thread.sleep(3000);
			}
		}

		return Ordernum;
	}

	public void searchProductAndBuy(String ProductName) throws Exception
	{
		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);			
			Thread.sleep(1000);
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(Keys.ENTER);
			_driver.findElement(By.className("search-button")).click();
			System.out.println(" published product is provided in search box..");	 
		}
		else
		{
			System.err.println("search product box is not available in storefront");
		}

		//check buy now option if it presents..	
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		WebElement Item_Found_Check = _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Found_Check)));
		if (CommonFunctions.Elementdisplayed_Enabled(Item_Found_Check))
		{	

			System.out.println("Published product is avaialble in storefront..");	 
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
		}

		//choose Buy Now Option.
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))
		{	
			product_Buy_Now_option.click();
			System.out.println("Published product is orderd..");
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.waitForDSFPageLoad(_driver);
		Thread.sleep(3000);
	}

	public void BuyWithQuantityandAddtoCart(String Quantity)
	{
		WebElement QuantityField = _driver.findElement(By.xpath(".//input[contains(@id,'quantityInput') and contains(@ng-added-hint-validate, 'QuantityValidations')] "));
		if (CommonFunctions.Elementdisplayed_Enabled(QuantityField))
		{
			_driver.findElement(By.xpath(".//input[contains(@id,'quantityInput') and contains(@ng-added-hint-validate, 'QuantityValidations')] ")).sendKeys(Quantity);
			System.out.println("Quantity Entered is :"+Quantity);
			_driver.findElement(By.id("addtoCartBtnSpan")).click();
		}
		
		
		else{
			System.err.println("Quantity Field is not Displayed");
		}
		
		
	}
	
	public void searchProductAndBuyWithQuantity(String ProductName, String Quantity) throws Exception                            
	{                                                                                                                            
		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));                    
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))                                                             
		{	                                                                                                                     
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();                                   
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);			         
			Thread.sleep(1000);                                                                                                  
			//_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(Keys.ENTER);                    
			_driver.findElement(By.className("search-button")).click();                                                          
			System.out.println(" published product is provided in search box..");	                                             
		}                                                                                                                        
		else                                                                                                                     
		{                                                                                                                        
			System.err.println("search product box is not available in storefront");                                             
		}	                                                                                                                     
	                                                                                                                             
		//check buy now option if it presents..	                                                                                 
		CommonFunctions.waitForPageLoaded(_driver);                                                                              
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));                          
		WebElement Item_Found_Check = _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Found_Check)));            
		if (CommonFunctions.Elementdisplayed_Enabled(Item_Found_Check))                                                          
		{	                                                                                                                     
	                                                                                                                             
			System.out.println("Published product is avaialble in storefront..");	                                             
		}                                                                                                                        
		else                                                                                                                     
		{                                                                                                                        
			System.err.println("Published product is not avaialble in storefront..");                                            
		}                                                                                                                        
	                                                                                                                             
		//choose Buy Now Option.                                                                                                 
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))                                                    
		{	                                                                                                                     
			CommonFunctions.SendValue(_driver, By.xpath("//div/ng-quantity-control/div/input[1]"), Quantity);                    
			product_Buy_Now_option.click();                                                                                      
			System.out.println("Published product is orderd..");                                                                 
		}                                                                                                                        
		else                                                                                                                     
		{                                                                                                                        
			System.err.println("Published product is not avaialble in storefront..");                                            
		}                                                                                                                        
		CommonFunctions.waitForPageLoaded(_driver);                                                                              
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.JobAddFiles)));                                     
		Thread.sleep(3000);                                                                                                      
	}                                                                                                                            
	
	public void click_on_Add_Files_And_Upload_Files() throws Exception
	{
		System.out.println("Select a External files by clicking on ADD FILES");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobAddFiles))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files)));

//		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).size() > 0)
//		{
//			System.out.println("Adhoc upload files button is available");
//			_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).click();
//			System.out.println("Upload files button is clicked..");
//			Thread.sleep(2000);
//		}
//		else
//		{
//			System.err.println("Adhoc - Upload files button is not displayed..");
//		}
	}

	public void click_on_Add_Files_And_Saved_Files() throws Exception
	{
		System.out.println("Select a External files by clicking on ADD FILES");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobAddFiles))).click();
		Thread.sleep(10000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Adhoc_SavedFiles)));

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Adhoc_SavedFiles))).size() > 0)
		{
			System.out.println("Adhoc saved files button is available");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_SavedFiles))).click();
			System.out.println("Saved files button is clicked..");
			Thread.sleep(2000);
		}
		else
		{
			System.err.println("Adhoc - Saved files button is not displayed..");
		}
	}

	public void click_On_Upload_File_Button() throws Exception
	{
		for(int i=0;i<=3;i++)
		{
			Thread.sleep(5000);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Upload_button))); 
			//WebElement Upload_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_button)));		 				  
			//For Upload_button button
			if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_button))).isDisplayed())
			{
				System.out.println("Upload button is available and enabled.");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_button))).click();
				Thread.sleep(5000);
				break;
			}
			else
			{
				Thread.sleep(3000);
			}
		}
	}

	public void Click_on_Done_Button() throws Exception 
	{
		for (int i =0; i<=15;i++)
		{
			Thread.sleep(6000);
			// For Close button
			if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).isDisplayed() & _driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).isEnabled())
			{
				System.out.println("Done button is available and enabled.");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).click();

				break;
			}		  
		}

	}

	public void choose_DeliveryMethod(String Delivary_Method) throws Exception
	{

		String Shipping_Charges_value = "nothing";

		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Buyyer_Delivary_Method)));

		// WebElement Buyyer_Delivary_Method =  _driver.findElement(By.xpath(Locators.getProperty(Locators.Buyyer_Delivary_Method)));

		if( _driver.findElements(By.xpath(Locators.getProperty(Locators.Buyyer_Delivary_Method))).size()>0)
			//if (CommonFunctions.Elementdisplayed_Enabled(Buyyer_Delivary_Method))
		{
			CommonFunctions.selectDropdown_ByvisibleText(_driver,By.xpath(Locators.getProperty(Locators.Buyyer_Delivary_Method)), Delivary_Method);
			System.out.println("Delivary Method is selected as::"+Delivary_Method);		 		 
		}

		else
		{
			System.err.println("Delivary method list box is not displayed correctly");

		}


	}

	public void choose_PaymentMethod(String paymentMethod) throws Exception
	{
		String paymentMethod_Xpath ="//ul[@class='payment-type']//label[text()='"+paymentMethod+"']/../input";//Credit Card
		//CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		WebElement paymentOption_Radio = _driver.findElement(By.xpath(paymentMethod_Xpath));
		if (paymentOption_Radio.isDisplayed())
		{
			paymentOption_Radio.click();
			System.out.println("Payment Method choosen as :"+ paymentMethod);
		}
		else
		{
			System.err.println("Payment method : "+paymentMethod +" Option is not available");

		}
	}

	public void choose_CreditCardPaymentPethod(String paymentMethod) throws Exception
	{
		String paymentMethod_Xpath="//ul[@class='payment-type']//label[text()='Credit Card']/../input";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(paymentMethod_Xpath));
		System.out.println("Payment method selected as : "+paymentMethod );

	}

	public void choose_AccoutingCodePayment(String paymentMethod) throws Exception
	{
		String paymentMethod_Xpath="//ul[@class='payment-type']//label[text()='AccountingCode']/../input";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(paymentMethod_Xpath));
		System.out.println("Payment method selected as : "+paymentMethod );

	}	
	
	public void enter_AccountingCodes(String code1,String code2) throws Exception
	{
		String code1_xpath=".//label[contains(text(),'Code 1')]/..//input";
		String code2_Xpath=".//label[contains(text(),'Code2')]/..//input";
		CommonFunctions.enterText(_driver, By.xpath(code1_xpath), code1);
		System.out.println("Code1 entered as  : "+code1);
		CommonFunctions.enterText(_driver, By.xpath(code2_Xpath), code2);
		System.out.println("Code2 entered as : "+code2);
	}
	
	public void enter_AccountingCode(String AccCode) throws Exception
	{
		String AccCode_xpath="//input[contains(@id,'id_AC')]";
		CommonFunctions.enterText(_driver, By.xpath(AccCode_xpath), AccCode);
		System.out.println("Accounting Code entered as : "+AccCode);
	}

	public void choose_DepartMentCodePayment(String paymentMethod) throws Exception
	{
		String paymentMethod_Xpath="//ul[@class='payment-type']//label[text()='Department Code']/../input";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(paymentMethod_Xpath));
		System.out.println("Payment method selected as : "+paymentMethod );
	}	

	public void enterDepartmentCode(String code1,String code2) throws Exception
	{
		String code1_xpath=".//label[contains(text(),'Dept Name')]/..//input";
		String code2_Xpath=".//label[contains(text(),'Second One')]/..//input";
		CommonFunctions.enterText(_driver, By.xpath(code1_xpath), code1);
		System.out.println("Code1 entered as  : "+code1);
		CommonFunctions.enterText(_driver, By.xpath(code2_Xpath), code2);
		System.out.println("Code2 entered as : "+code2);
	}

	public void chooseCostCenterPaymentMethod(String paymentMethod) throws Exception
	{
		String paymentMethod_Xpath="//ul[@class='payment-type']//label[text()='Cost Center']/../input";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(paymentMethod_Xpath));
		System.out.println("Payment method selected as : "+paymentMethod );
	}

	public void enter_CostCsnterAccountInfoAndPurchaseOrderNum(String accNo,String PoNum) throws Exception
	{
		String CostAcc_xpath=".//*[@id='id_CC_AccountNumber']";
		String purchaseOrderNum_Xpath=".//*[@id='id_CC_PONumber']";
		CommonFunctions.Wait(_driver, By.xpath(CostAcc_xpath));
		CommonFunctions.enterText(_driver, By.xpath(CostAcc_xpath), accNo);
		System.out.println("Code1 entered as  : "+accNo);
		CommonFunctions.enterText(_driver, By.xpath(purchaseOrderNum_Xpath), PoNum);
		System.out.println("Code2 entered as : "+PoNum);
	}	
	
	public void choose_PO_PaymentMethod_Enter_PO_number(String paymentMethod,String POnumber) throws Exception
	{
		String paymentMethod_Xpath="//ul[@class='payment-type']//label[text()='PO Number']/../input";
		String PONumber_Xpath=".//*[@id='id_PurchaseOrder_PONumber']";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(paymentMethod_Xpath));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(paymentMethod_Xpath));
		System.out.println("Payment method selected as : "+paymentMethod );
		CommonFunctions.enterText(_driver, By.xpath(PONumber_Xpath), POnumber);
		System.out.println("PO number entered as : "+POnumber);
	}
	                                                                            
	public void click_on_buyNow()
	{
		//choose Buy Now Option.
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))
		{	
			product_Buy_Now_option.click();
			System.out.println("Published product is orderd..");	 

		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");

		}
	}

	public void NavigateToLinkPage(String linkName)
	{
		String Link_Xpath= "//a[text()='"+linkName+"']";
		_driver.findElement(By.xpath(Link_Xpath)).click();

	}

	public boolean verifyOverridenString(String overRideString)
	{
		String OverriddenText_xpath="//label[text()='"+overRideString+"']";
		if(_driver.findElement(By.xpath(OverriddenText_xpath)).isDisplayed())
		{
			System.out.println("Over Ridden String is Displayed");
			return true;
		}
		else
		{
			System.err.println("Over Ridden String is not displayed");
			return false;
		}

	}

	public void choose_PrintService_Vpb(String mainPSName,String SubPsName,String SubSubPsName) throws Exception
	{
		String mainPsName_Xpath=".//*[text()='"+mainPSName+"' and @class='print-service-name']";
		String subpsName_Xpath=".//*[text()='"+mainPSName+"' and @class='print-service-name']/../..//*[text()='"+SubPsName+"']";
		String subSubPsName_Xpath=".//*[text()='"+mainPSName+"' and @class='print-service-name']/../..//*[text()='"+SubPsName+"']/../..//*[text()='"+SubSubPsName+"']";

		//Selecting the Main print service 

		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(mainPsName_Xpath));
		Actions action=new Actions(_driver);
		WebElement mainps= _driver.findElement(By.xpath(mainPsName_Xpath));
		action.moveToElement(mainps).click().perform();
		//_driver.findElement(By.xpath(mainPsName_Xpath)).click();


		if(_driver.findElements(By.xpath(subpsName_Xpath)).size()>0)
		{
			//Clicking on the sub print service if any 
			Thread.sleep(2000);
			WebElement subPs= _driver.findElement(By.xpath(subpsName_Xpath));
			action.moveToElement(subPs).click().perform();
			// _driver.findElement(By.xpath(subpsName_Xpath)).click();
			System.out.println("Sub print service "+SubPsName+ " is been clicked");

			if(_driver.findElements(By.xpath(subSubPsName_Xpath)).size()>0)
			{
				//Clicking on the sub sub print service if any 
				Thread.sleep(2000);
				_driver.findElement(By.xpath(subSubPsName_Xpath)).click();
				System.out.println("Sub sub print service "+SubSubPsName+ " is been clicked");
			}
		}

	}

	public boolean verify_PrintService_status(boolean DisableMainPs,String psName,boolean disablesubPs,String subPsName) throws Exception
	{
		String mainPs_Xpath=".//*[text()='"+psName+"' and @class='print-service-name']/..";
		String subPsName_Xpath=".//*[text()='"+psName+"' and @class='print-service-name']/../..//*[text()='"+subPsName+"']";
		String isEnabled;

		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(mainPs_Xpath));
		//verifying whether the main PS is  enabled or not
		if(DisableMainPs)
		{
			isEnabled= _driver.findElement(By.xpath(mainPs_Xpath)).getAttribute("class");
			if(isEnabled.equalsIgnoreCase("disabledoption"))
			{
				System.out.println("Print Service is not enabled as expected "+ psName );
				return true; 
			}
			else
			{
				System.err.println("Print Service is enabled "+ psName );
				return false; 
			}

		}
		else
		{ 
			isEnabled= _driver.findElement(By.xpath(mainPs_Xpath)).getAttribute("class");
			if(isEnabled.equalsIgnoreCase("disabledoption"))
			{
				System.err.println("Print Service is disabled  "+ psName );
				return false; 
			}
			else
			{
				System.out.println("Print Service is enabled as expected"+ psName );
				return true; 
			}

		}

		//verifying whether the Sub printservice is enabled

	}

	public void navigateTo_categoryPage(String cateGoryName) throws Exception
	{
		String category_xpath=".//*[text()='Category']";
		String specificCatgory_xpath=".//*[text()='"+cateGoryName+"']";
		try
		{
			WebElement cateGory=_driver.findElement(By.xpath(category_xpath));
			Actions action=new Actions(_driver);
			action.moveToElement(cateGory).perform();Thread.sleep(1000);
			_driver.findElement(By.xpath(specificCatgory_xpath)).click();
		}
		catch(NoSuchElementException e1)
		{
			_driver.findElement(By.xpath(specificCatgory_xpath)).click();
		}
		catch(MoveTargetOutOfBoundsException e2)
		{
			_driver.findElement(By.xpath(specificCatgory_xpath)).click();
		}

		CommonFunctions.waitForPageLoaded(_driver);

	}

	public int FetchDisplayPriorityOrder(String productName)
	{
		String totalProducts=".//*[contains(@id,'ProductItem')]//h5";
		int size= _driver.findElements(By.xpath(totalProducts)).size();
		System.out.println("No of products :"+size);
		for(int i=1;i<=size;i++)
		{
			totalProducts=".//*[contains(@id,'ProductItem')]["+i+"]//h5";
			String actual=_driver.findElement(By.xpath(totalProducts)).getText().trim();
			if(actual.equalsIgnoreCase(productName))
			{
				System.out.println("product found at  : "+i);
				return i;
			}


		}
		return 0;
	}

	public boolean verifyProductImage()
	{
		String productImage=".//*[contains(@style,'DSF/IMGS')]";
		int size=_driver.findElements(By.xpath(productImage)).size();
		if(size==1)
		{
			System.out.println("Product Image is present in smartStore");
			return true;
		}
		else
		{
			System.err.println("Product image is not present in smartstore");
			return false;
		}

	}

	public boolean Only_Search_Product_smart_store(String ProductName) throws Exception
	{
		Thread.sleep(5000);


		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);
			Thread.sleep(2000);
			_driver.findElement(By.className("search-button")).click();
			System.out.println(" published product is provided in search box..");	 
		}
		else
		{
			System.err.println("search product box is not available in storefront");
		}	

		//check buy now option if it presents..	
		WebElement Item_Found_Check = _driver.findElement(By.xpath(Locators.getProperty(Locators.Item_Found_Check)));
		if (CommonFunctions.Elementdisplayed_Enabled(Item_Found_Check))
		{	

			System.out.println("Published product is avaialble in storefront..");	
			return true;
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
			return true;
		}	

	}

	public void changeQty_From_Cart(String qty) throws Exception
	{
		String qtyXpath="//input[@ng-model='QuantitySelection.SelQuantityForUI.Value' and @ng-class='InputControlClassName']";
		CommonFunctions.Wait(_driver, By.xpath(qtyXpath));
		_driver.findElement(By.xpath(qtyXpath)).clear();
		_driver.findElement(By.xpath(qtyXpath)).sendKeys(qty);
		_driver.findElement(By.xpath(qtyXpath)).sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public String fetchUnitPrice() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);Thread.sleep(4000);
		String unitPrice_Xpath=".//*[text()='Unit Price' and @ng-localize='Mobile.OrderLineItem.UnitPrice']//..//span";
		String unitPrice=_driver.findElement(By.xpath(unitPrice_Xpath)).getText().trim();
		return unitPrice;
	}

	public void enterKitJobnameandQty(String jobName, String qty)
	{
		//	   System.out.println(" ======  Creating Random string for Job Name and Randon integer from 1 -20 to enter in Quantitty field ======");
		//		String JName = CommonFunctions.getRandomString(10);
		//		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		//		String JQua = "" + JQuantity;

		WebElement Kit_Job_Name = _driver.findElement(By.id(Locators.getProperty(Locators.KIT_Job_Name)));

		//To Enter KIT - job name and quantity.
		if (CommonFunctions.Elementdisplayed_Enabled(Kit_Job_Name))
		{
			Kit_Job_Name.clear();
			Kit_Job_Name.sendKeys(jobName);
			//System.out.println("kit_Job_name Entered as::"+"KIT_"+JName);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).sendKeys(String.valueOf(qty));
		}
	}

	public void kitCustomization(String KITProductName,String sSheetName,String sScenario,String sTestcase) throws Exception
	{
		System.out.println(" ======  Creating Random string for Job Name and Randon integer from 1 -20 to enter in Quantitty field ======");
		String JName = CommonFunctions.getRandomString(10);
		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		String JQua = "" + JQuantity;

		String Customize_xpath = "//tr[td[div[@class='kit-product-name']/a[contains(text(),'"+ KITProductName +"')]]]/td/div/button[contains(text(),'Customize')]";
		WebElement Kit_Job_Name = _driver.findElement(By.id(Locators.getProperty(Locators.KIT_Job_Name)));

		//To Enter KIT - job name and quantity.
		if (CommonFunctions.Elementdisplayed_Enabled(Kit_Job_Name))
		{
			Kit_Job_Name.clear();
			Kit_Job_Name.sendKeys("KIT_"+JQua);
			System.out.println("kit_Job_name Entered as::"+"KIT_"+JName);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.KIT_QTY))).sendKeys(String.valueOf(JQuantity));
		}

		//----------------------------------------------------------------------------------------------------
		//To set all products qty in kit as zero
		//----------------------------------------------------------------------------------------------------
		String sub_ProductName = dbConnection.ReadFunction(sSheetName, sSheetName, sTestcase, "KitItems");		  
		String[] PSOptions = sub_ProductName.split(";");
		int options = PSOptions.length;

		for (int i=0;i<options;i++)
		{
			System.out.println(PSOptions[i]);
			if (PSOptions[i].equalsIgnoreCase("End"))
			{
				break;
			}
			String Prod_qty_xpath = "//tr[td[div[a[contains(text(),'"+PSOptions[i]+"')]]]]/td[@class ='kit-td-item-quantity']/ng-quantity-control/div/Input";

			if (!PSOptions[i].equalsIgnoreCase(KITProductName))
			{ 	
				WebElement Prod_qty = _driver.findElement(By.xpath(Prod_qty_xpath));
				if (CommonFunctions.Elementdisplayed_Enabled(Prod_qty))
				{
					Prod_qty.clear();
					Prod_qty.sendKeys("0");
				}
			}
		} // For (int i =0) ..end..
		//=====================================================================================================
		WebElement Customize_btn = _driver.findElement(By.xpath(Customize_xpath));
		if (CommonFunctions.Elementdisplayed_Enabled(Customize_btn))
		{	
			Customize_btn.click();
			System.out.println("Customize button is clicked..");
			Thread.sleep(4000);
		}
		else
		{
			System.err.println("Customize button is not avaialble in storefront..");
			System.out.println("product name::"+KITProductName);
		}	
	}

	public void click_On_Product_customization_FinishButton()
	{
		String finsh_Xpath=".//*[text()='Finish']";
		_driver.findElement(By.xpath(finsh_Xpath)).click();
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public void Click_On_transfer_button()
	{
		String TransferButton_Xpath=".//*[contains(text(),'Transfer')]";
		_driver.findElement(By.xpath(TransferButton_Xpath)).click();
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public boolean verifyExpectedAspxPage(String exppectedAspxPage) throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(10000);
		String currentUrl=_driver.getCurrentUrl();
		System.out.println("currentUrl is : "+currentUrl);
		if(currentUrl.contains(exppectedAspxPage))
		{
			System.out.println("Expected aspx page is been shown");
			return true;
		}
		else
		{
			System.err.println("Expected aspx page is not been shown");
			return false;
		}
	}

	public boolean veifyKeyValue(String keyName,String expectedKeyValue)
	{
		String KeyValue_Xpath=".//*[text()='"+keyName+"']/..//td[2]";
		String actualKeyValue= _driver.findElement(By.xpath(KeyValue_Xpath)).getText().trim();
		if(expectedKeyValue.equalsIgnoreCase(actualKeyValue))
		{
			System.out.println("Keyvalue expected is been shown at the receiver aspx page");
			return true;
		}
		else
		{
			System.err.println("Expect keyvalue is not been shown at the receiver aspx page and the expectedKeyValue for the : "+expectedKeyValue + "  actual keyvalue displayed is :"+actualKeyValue);
			return false;
		}
	}

	public boolean Enter_Vdp_fields_VerifyPrice_Add_to_Cart(Double RegularPrice, Double SetupPrice, String JobQTY) throws Exception
	{



		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.sendKeys("Auto Test-"+i);
			System.out.println("Input Field::"+i+"::is updated as::-Auto Test-"+i);
		}

		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		//Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("FP VDP PDF proof is displayed..");
					//-------------------------------------------------------------------------------------------------						
					//to check the number pages.As product is maintained in db it should show as expected pages.
					//Expected is hard coded here..as 92. Update code with required value if occurs in future.
					//need to take care this parameter from excel. Note..Krishna..
					//-------------------------------------------------------------------------------------------------
					String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

					if (Nooffile_Uploaded.contains("/"))
					{
						String[] Nooffiles = Nooffile_Uploaded.split("/");
						int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
						if (TotalFiles == 92)
						{
							System.out.println("FP product with 92 pages are uploaded correctly.. ");
						}
						else
						{
							System.err.println("Paages are not available correctly");
							System.err.println("No.Of pages expected :92");
							System.err.println("No.Of pages Actually available:"+TotalFiles);
							System.out.println("No.Of pages '92' count is hard coded. Hence pls check the product in application level.");
						}
					}
					//-------------------------------------------------------------------------------------------------
					//to Check unit and total prices by changing the QTY fields. - Setup and regular price values are given 
					//as const . If you have changed the price in product level then u have to change here also.
					//-------------------------------------------------------------------------------------------------
					Double FP_RegularPrice = RegularPrice; //constants..set for product
					Double  FP_SetupPrice = SetupPrice;
					//	JobQTY = _driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).getText();
					//============================================================================
					_driver.manage().window().maximize();
					double Expected_TotalPrice = (FP_RegularPrice*Integer.valueOf(JobQTY))+ FP_SetupPrice;
					double Expected_Unit_Price= Expected_TotalPrice/Integer.valueOf(JobQTY);  
					String Unit_Price =  roundOffTo2DecPlaces(Expected_Unit_Price);
					String Total_price = roundOffTo2DecPlaces(Expected_TotalPrice);

					System.out.println("Expected Unit Price::"+Unit_Price);
					System.out.println("Expected Total Price::"+Total_price);

					String Actual_UnitPrice = _driver.findElement(By.id(Locators.getProperty(Locators.unitprice_Id))).getText();
					String Actual_TotalPrice = _driver.findElement(By.id(Locators.getProperty(Locators.TotalPrice_Id))).getText();

					//Unit Price verification..
					if (Unit_Price.equalsIgnoreCase(Actual_UnitPrice.trim()))
					{
						System.out.println("Expected and Actual Unit prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual unit prices are not matching correctly");
						System.err.println("Expected Unit Price::"+Unit_Price);
						System.err.println("Actual Unit Price::"+Actual_UnitPrice);
					}

					//total price verification
					if (Total_price.equalsIgnoreCase(Actual_TotalPrice.trim()))
					{
						System.out.println("Expected and Actual Total prices are matching correctly.");
					}
					else
					{
						System.err.println("Expected and actual Total prices are not matching correctly");
						System.err.println("Expected Total Price::"+Total_price);
						System.err.println("Actual Total Price::"+Actual_TotalPrice);
					}

					//-------------------------------------------------------------------------------------------------

					boolean addtoc = this.Addto_Cart();

					if (addtoc)
					{
						System.out.println("FP product is added to cart..");
						return true;
					}
					else
					{
						System.err.println("Add to cart button is not displayed properly..");			
					}					
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}
			Thread.sleep(3000);
		}		
		return false;
	}

	public void click_on_PlaceMyOrder() throws Exception
	{
		CommonFunctions.waitForDSFPageLoad(_driver);
		WebElement  Cart_Place_Order = _driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Place_Order)));
		if (Elementdisplayed_Enabled(Cart_Place_Order))
		{
			CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.Cart_Place_Order)));
			System.out.println("Place My Order Button is clicked");
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.err.println("Place My Order Button is not available");
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Order_Number)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Cart_Subtotal_value)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Cart_Shipping_value)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Cart_Total)));
	}

	public void CSSA_OrderCompletion(boolean ValidateFieldValueArePresentAlready) throws Exception
	{
		String firsName_Xpath=".//*[@id='bill_to_forename']";
		String LastName_Xpath=".//*[@id='bill_to_surname']";
		String Address_Xpath=".//*[@id='bill_to_address_line1']";
		String city_Xpath=".//*[@id='bill_to_address_city']";
		String country_Xpath=".//*[@id='bill_to_address_country']";
		String State_Xpath=".//*[@id='bill_to_address_state_us_ca']";
		String postal_Xpath=".//*[@id='bill_to_address_postal_code']";
		String Email_xpath=".//*[@id='bill_to_email']";
		String card_Number_Xpath=".//*[@id='card_number']";
		String expiryMonth_xpath=".//*[@id='card_expiry_month']";
		String expiryYear_Xpath=".//*[@id='card_expiry_year']";
		String nextButton_xpath=".//*[@value='Next']";
		String total_Price_With_buffer_Xpath=".//*[@id='amounts']/ul/p";

		if(ValidateFieldValueArePresentAlready) 
		{
			boolean isFirstNameValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(firsName_Xpath), "FirstName");
			boolean LastNameValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(LastName_Xpath), "LastName");
			boolean Address1ValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(Address_Xpath), "Address1");
			boolean CityValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(city_Xpath), "City");
			boolean CountryValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(country_Xpath), "Country");
			boolean StateValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(State_Xpath), "State");
			boolean ZipValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(postal_Xpath), "Zip");
			boolean EmailValueExist= CommonFunctions.VerifyValueisNotNull(_driver, By.xpath(Email_xpath), "Email");
			if(isFirstNameValueExist && LastNameValueExist && Address1ValueExist && CityValueExist && CountryValueExist && StateValueExist && ZipValueExist && EmailValueExist)
			{
				System.out.println("All the required bvalues are already present");
			}
			else
			{
				System.err.println("Required values are not present already hence we have to enter");
				CommonFunctions.enterText(_driver, By.xpath(firsName_Xpath),"Firstname");
				CommonFunctions.enterText(_driver, By.xpath(LastName_Xpath),"LastName");
				CommonFunctions.enterText(_driver, By.xpath(Address_Xpath),"Address1");
				CommonFunctions.enterText(_driver, By.xpath(city_Xpath),"Norcross");
				CommonFunctions.enterText(_driver, By.xpath(postal_Xpath),"30071");
				CommonFunctions.enterText(_driver, By.xpath(Email_xpath),"xyz@.123.com");
				//CommonFunctions.selectDropdownByText(_driver, B, text)


			}
		}	

	}

	//**********************Kalunga order placement flow methods implementation****************************************************
	public void clearCart() throws Exception
	{
		String clearCart_xpath=".//*[@id='ButtonClearCart']"; 
		_driver.findElement(By.xpath(clearCart_xpath)).click();
		System.out.println("Clicked on Clear cart button");
		Alert alert = _driver.switchTo().alert();
		alert.accept(); Thread.sleep(3000);

	}
	public void select_User_Agent(String userAgentSelectText) throws Exception
	{
		String userAgentDropDown_xpath=".//*[@id='DdlUserAgents']";
		CommonFunctions.Wait(_driver, By.xpath(userAgentDropDown_xpath));
		CommonFunctions.selectDropdownByText(_driver, By.xpath(userAgentDropDown_xpath),userAgentSelectText);

	}

	public void select_PunchOut(String punchMethodText) throws Exception
	{
		String punchMethod_xpath=".//*[@id='DdlPunchOutOMethods']";
		CommonFunctions.Wait(_driver, By.xpath(punchMethod_xpath));
		CommonFunctions.selectDropdownByText(_driver, By.xpath(punchMethod_xpath),punchMethodText);
	}

	public void selectProduct(String productName) throws Exception
	{
		String Product_dropDown_Xpath=".//*[@id='DdlProducts']";
		CommonFunctions.Wait(_driver, By.xpath(Product_dropDown_Xpath));
		CommonFunctions.selectDropdownByText(_driver, By.xpath(Product_dropDown_Xpath),productName);
	}

	public void click_CreateCartButton()
	{
		String createCart_xpath=".//*[@id='ButtonCreateCart']";
		_driver.findElement(By.xpath(createCart_xpath)).click();
		System.out.println("Clicked on create cart button");
	}

	public void click_launchDsfLink()
	{
		String launchDsfButton_Xpath=".//a[@id='LinkButtonGoToDSF']";
		_driver.findElement(By.xpath(launchDsfButton_Xpath)).click();
		System.out.println("Clicked on launch dsf link");
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public void enterQuantity_from_SimulatorFlow(String qty) throws Exception
	{
		_driver.switchTo().frame(0);
		String productQty_xpath=".//*[@class='ng-scope ng-pristine ng-valid input-small']";//.//*[contains(@id,'TextBoxQuantity')]
		CommonFunctions.Wait(_driver, By.xpath(productQty_xpath));
		CommonFunctions.enterText(_driver, By.xpath(productQty_xpath), qty);
		System.out.println("qty has been entered as : "+qty );
	}

	public void clickOn_BuyNow_button_from_SimulatorFlow() throws Exception
	{
		String buyNow_xpath=".//*[contains(text(),'Buy Now')]";
		String OrderId_Value_xpath=".//*[@id='dgItems_ctl02_lblOrder']";

		_driver.findElement(By.xpath(buyNow_xpath)).click();
		System.out.println("Clicked on buy now option");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(OrderId_Value_xpath));

	}

	public void Switch_to_frame(int index)
	{
		_driver.switchTo().frame(index);
	}

	public String fetch_OrderId_SimulatorWorkFlow()
	{
		String OrderId_Value_xpath=".//*[@id='dgItems_ctl02_lblOrder']";
		String orderNum= _driver.findElement(By.xpath(OrderId_Value_xpath)).getText().trim();
		System.out.println("Order Number is : "+orderNum);
		return orderNum;
	}

	public void launchDsf(WebDriver driver,String url)
	{

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		for(int i=0;i<=2;i++)
			if(driver.findElements(By.xpath(Locators.getProperty(Locators.MyAccountResp))).size()>0)
			{
				System.out.println("Login Page Loded Succcessfully");
				break;
			}
			else
			{
				driver.get(url);

			}

	}
	public boolean verifyOrderStatus(WebDriver driver,String expectedOrderStatus)
	{
		String orderStatus_Xpath=".//*[contains(@id,'OrderHistoryItem_LabelOrderStatus')]";
		String actualOrderStatus=driver.findElement(By.xpath(orderStatus_Xpath)).getText().trim();
		if(actualOrderStatus.equalsIgnoreCase(expectedOrderStatus))
		{
			System.out.println("Order status is Proper as expected");
			return true;
		}
		else
		{
			System.err.println("Order status is not proper expected is  : "+expectedOrderStatus+"  Actual is : "+ actualOrderStatus);
			return false;
		}
	}

	public void click_On_FinalizeOrder() throws Exception
	{
		String orderFinalized=".//*[contains(text(),'Order finalized')]";
		String finalizeOrderButton_xpath=".//*[@id='ButtonFinalizeOrder']";
		_driver.findElement(By.xpath(finalizeOrderButton_xpath)).click(); 
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver,By.xpath(orderFinalized));
		System.out.println("clicked on finalize button");
	}

	public boolean verifyPunchoutOrderStatus(String expectedPunchOutOrderStatus)
	{
		String punchOutOrderStatus_xpath=".//*[@id='LabelPunchOutSetupStatus']";
		String actualPunchoutOrderStatus=_driver.findElement(By.xpath(punchOutOrderStatus_xpath)).getText().trim();
		if(actualPunchoutOrderStatus.equalsIgnoreCase(expectedPunchOutOrderStatus))
		{
			System.out.println("Order status is Proper as expected");
			return true;
		}
		else
		{

			System.err.println("Order status is not proper expectedPunchOutStatus is  : "+expectedPunchOutOrderStatus+"  Actual is : "+ actualPunchoutOrderStatus);
			return false;
		}
	}

	public void clickOnAddToCart_Button_SimulatorFlow() throws Exception
	{
		String OrderId_Value_xpath=".//*[@id='dgItems_ctl02_lblOrder']";
		String addToCart_Xpath=".//*[@id='addtoCartBtnSpan']";
		String addToCart=".//*[text()='Add to Cart']";
		try{

			if(_driver.findElements(By.xpath(addToCart_Xpath)).size()>0)
			{
				WebElement addTocart= _driver.findElement(By.xpath(addToCart_Xpath));
				Actions action=new Actions(_driver);
				action.moveToElement(addTocart).click().perform();
				//		_driver.findElement(By.xpath(addToCart_Xpath)).click();
				System.out.println("Clicked on add to cart option");
				CommonFunctions.waitForPageLoaded(_driver);
				CommonFunctions.Wait(_driver, By.xpath(OrderId_Value_xpath));
			}
		}
		catch(NoSuchElementException e)
		{
			_driver.manage().window().maximize();
			CommonFunctions.Wait(_driver, By.xpath(addToCart));
			WebElement addTocart= _driver.findElement(By.xpath(addToCart));
			Actions action=new Actions(_driver);
			for(int i=1;i<=3;i++)
			{
				action.moveToElement(addTocart).click().perform();
				System.out.println("Clicked on add to cart option");
				CommonFunctions.waitForPageLoaded(_driver);
				int size=_driver.findElements(By.xpath(addToCart)).size();
				if(size==0)
				{
					System.out.println("Moved back to simulator page ");
					break;
				}
				else
				{
					System.out.println("Clicking on add to cart at : "+i+"th time" );
				}
			}
		}

		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(OrderId_Value_xpath));


	}



	//**********************END of Kalunga order placement flow methods implementation**********************************************

	public void logout() throws Exception
	{
		// Logout from classic UI
		CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.linkText("Logout"));
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public void ApproveTheOrderFromCustomer() throws Exception
	{
		WebElement approver_comments = _driver.findElement(By.id(Locators.getProperty(Locators.approval_Comments_text_area_id)));


		if (CommonFunctions.Elementdisplayed_Enabled(approver_comments))
		{
			System.out.println("approver comments text box is available..");
			approver_comments.sendKeys("Approverd @ User level");
			Thread.sleep(1000);
		}
		else
		{
			System.err.println("approver comments box is not displayed..");
		}	

		//click on approve order
		WebElement approve_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Approve_button)));
		WebElement decline_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Decline_button)));

		if (CommonFunctions.Elementdisplayed_Enabled(decline_button))
		{
			System.out.println("decline button is available..");						
		}
		else
		{
			System.err.println("Decline button is not displayed..");
		}	

		if (CommonFunctions.Elementdisplayed_Enabled(approve_button))
		{
			System.out.println("approve button is available..");
			approve_button.click();	
			Thread.sleep(2000);
		}
		else
		{
			System.err.println("approve button is not displayed..");
		}
	}


	public boolean XMPIE_Add_Calender_Data_Validate_Pdf_Proof() throws Exception
	{//this function is specific to TC_6_xmpie

		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size() > 0)
		{
			System.out.println("Calender Fields are displayed..");
		}
		else
		{
			System.err.println("Calender details for xmpie is not displayed correctly");
			return false;
		}

		int fieldcount = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text))).size();
		List<WebElement> collection_elements = _driver.findElements(By.xpath(Locators.getProperty(Locators.TC6_Xmpie_Calender_Text)));
		for (int i = 0;i<fieldcount-1;i++)
		{
			WebElement inputfield = collection_elements.get(i);
			inputfield.clear();
			inputfield.sendKeys("input0_"+i);
			System.out.println("Input Field::"+i+"::is updated as::-input0_"+i);
		}

		//check for update preview button

		if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).isDisplayed())
		{
			System.out.println("Update preview button is displayed..");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_Update_Preview_btn))).click();
			System.out.println("Update preview button is clicked..");
		}

		else
		{
			System.err.println("Update preview button is not displayed..");
		}

		//Thread.sleep(150000); //blind wait -- need to remove this..
		//Loop to get the preview - its not working properly.. Need to check this..

		for (int j=0;j<=60;j++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).size() > 0 )
			{
				if (_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
				{
					System.out.println("calender VDP PDF proof is displayed..");
					System.out.println("Pass");
					break;
				}			
				else
				{
					Thread.sleep(5000);
				}
			}

			else
			{
				System.out.println("still preview is generating");
			}
		}	

		if(_driver.findElement(By.xpath(Locators.getProperty(Locators.XMPIE_PDF_Proof))).isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	public boolean Search_Product_smart_storefront(String ProductName) throws Exception
	{
		Thread.sleep(5000);


		WebElement searchproduct = _driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField)));
		if (CommonFunctions.Elementdisplayed_Enabled(searchproduct))
		{	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(ProductName);
			_driver.findElement(By.className("search-button")).click();
			System.out.println(" published product is provided in search box..");	 
		}
		else
		{
			System.err.println("search product box is not available in storefront");
		}	

		//check buy now option if it presents..	
		//choose Buy Now Option.
		Thread.sleep(3000);
		WebElement product_Buy_Now_option = _driver.findElement(By.xpath(Locators.getProperty(Locators.product_Buy_Now_option)));
		if (CommonFunctions.Elementdisplayed_Enabled(product_Buy_Now_option))
		{	
			product_Buy_Now_option.click();
			System.out.println("Published product is orderd..");	 
			return true;
		}
		else
		{
			System.err.println("Published product is not avaialble in storefront..");
			return false;
		}
	}

	//*******************************************Campaign Resusable Method*****************************************************************//
	public void click_on_begin() throws Exception
	{
		String begin_Xpath=".//*[@ng-click='openCreateCampaign()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(begin_Xpath));
		System.out.println("Clicked on begin button for the campaign");
	}
	public void enterCampaignName(String campaignName) throws Exception
	{
		String campaignNameTextBox=".//*[@id='txtCampaignName']";
		CommonFunctions.enterText(_driver, By.xpath(campaignNameTextBox), campaignName);
	}

	public void selectCampaignType(String campaignValue)
	{
		String campaignAccountType=".//*[@value='"+campaignValue+"']";
		CommonFunctions.selectRadioButton(_driver, By.xpath(campaignAccountType), campaignValue);
	}

	public void click_createButton() throws Exception
	{
		String createCampaign_Xpath=".//a[@class='OK-button']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(createCampaign_Xpath));
	}

	public boolean verify_InitializeSectionActive()
	{
		String initializeSection_Xpath=".//*[text()='Intialize']/../..";
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(initializeSection_Xpath));
		if(_driver.findElement(By.xpath(initializeSection_Xpath)).getAttribute("class").trim().equals("active"))
		{
			System.out.println("Initialize section is exist and active");
			return true;
		}

		else
		{
			System.err.println("Initialize section is not active");
			return false;
		}

	}

	public boolean verify_CampaignTitle(String campaignTitle)
	{
		String campaignTitle_Xpath=".//*[@ng-model='localModel.campaignName']";
		String actualTitleName=_driver.findElement(By.xpath(campaignTitle_Xpath)).getText().trim();
		System.out.println("Actual Compaign Name is :- " +actualTitleName);
		if(actualTitleName.equalsIgnoreCase(campaignTitle))
		{
			System.out.println("Campaign title displayed Correctly");
			return true;
		}
		else
		{
			System.err.println("Campaign title is not improper check manually once");
			return false;
		}
	}

	public boolean VerifyCampaignType(String campaignType)
	{
		String campaignType_Xpath=".//*[@class='campaign-type ng-binding']";
		String actualCampaignType=_driver.findElement(By.xpath(campaignType_Xpath)).getText().trim();
		if(actualCampaignType.equalsIgnoreCase(campaignType))
		{
			System.out.println("Campaign Type is Proper : "+actualCampaignType);
			return true;
		}
		else
		{
			System.err.println("Campaign Type is improper actual : "+actualCampaignType+ " Expected is :"+campaignType);
			return false;
		}
	}

	public void ClickOn_CreateNewContact() throws Exception
	{
		String createNewContact_Link=".//*[@class='create-new-contact']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(createNewContact_Link));
		System.out.println("Clicked on create New Contact link");
	}

	public boolean VerifyImportContactSection()
	{
		String importContacts_Xpath=".//*[text()='Import Contacts']";
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(importContacts_Xpath));
		if(_driver.findElement(By.xpath(importContacts_Xpath)).isDisplayed())
		{
			System.out.println("Import Contact Section is been displayed");
			return true;
		}
		else
		{
			System.err.println("Import Contact Section is not been displayed");
			return false;
		}
	}

	public void Clickon_ImportContactSection() throws Exception
	{
		String importContacts_Xpath=".//*[text()='Import Contacts']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importContacts_Xpath));
		System.out.println("Clicked on Import Contact Link");
	}

	public void selectDataSource(String dataSourceName)
	{
		String selectDataSource_Xpath=".//*[@ng-change='onSourceChange()']";
		CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath(selectDataSource_Xpath), dataSourceName);
		System.out.println("Data Source selcted as :"+dataSourceName);

	}

	public void EnterBatchName(String batchName) throws Exception
	{
		String batchNameText_Xpath=".//*[@ng-model='localModel.batchName']";
		CommonFunctions.enterText(_driver, By.xpath(batchNameText_Xpath), batchName);
		System.out.println("Batch Name Entered As : "+batchName);
	}

	public void selectDuplicateUpdateMode(String modeValue)
	{
		String radioButtonMode_Xpath=".//*[@value='"+modeValue+"']";
		CommonFunctions.selectRadioButton(_driver, By.xpath(radioButtonMode_Xpath), modeValue);
	}

	public void clickOn_SelectFile_Link() throws Exception
	{
		String selectFileLink_Xpath=".//*[@class='choose-file-button']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(selectFileLink_Xpath));
		System.out.println("Clicked on select file link");Thread.sleep(3000);

	}

	public void clickOn_ImportLink() throws Exception
	{
		Thread.sleep(5000);
		String importLink_Xpath=".//a[@class='OK-button' and @ng-click='doAction()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importLink_Xpath));
		String dataSourceDropDown_Xpath=".//*[text()='FirstName']/../..//select";
		for(int i=0;i<=10;i++)
		{

			if(_driver.findElements(By.xpath(dataSourceDropDown_Xpath)).size()>0)
			{
				System.out.println("Clicked on Import link");
				break;
			}
			else
			{
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importLink_Xpath));Thread.sleep(2000);
			}
		}


	}

	public boolean mapDataBasedOnColumnName(String columnName,String dataSourceName)
	{
		//String sampleDate_XpathAsPerColumnName=.//*[text()='FirstName']/../../td[3]/span
		String dataSourceDropDown_Xpath=".//*[text()='"+columnName+"']/../..//select";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdown_ByvisibleText(_driver, By.xpath(dataSourceDropDown_Xpath), dataSourceName);
		System.out.println("Selected data Source is : "+ dataSourceName);
		String selectedOption= CommonFunctions.GetSelectedOption(_driver, By.xpath(dataSourceDropDown_Xpath));
		if(selectedOption.equalsIgnoreCase(dataSourceName))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void clickOn_MappingLink() throws Exception
	{
		Thread.sleep(5000);
		String importLink_Xpath=".//a[@class='OK-button' and @ng-click='doAction()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importLink_Xpath));
		String successfulMessage_Xpath=".//*[text()='Contacts Imported Successfully']";

		for(int i=0;i<3;i++)
		{
			if(_driver.findElements(By.xpath(successfulMessage_Xpath)).size()>0)
			{
				System.out.println("Clicked on Mapping link");
				break;

			}
			else
			{
				CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importLink_Xpath));Thread.sleep(2000);
			}
		}



	}

	public boolean verifyImportedContactsSuccessfulMessage()
	{
		String successfulMessage_Xpath=".//*[text()='Contacts Imported Successfully']";
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(successfulMessage_Xpath));
		if(_driver.findElement(By.xpath(successfulMessage_Xpath)).isDisplayed())
		{
			System.out.println("Import Contacts Successful message is been displayed");
			return true;
		}
		else
		{
			System.err.println("Contact Imorted Successfully message is not been displayed");
			return false;
		}
	}

	public void clickOn_okLink() throws Exception
	{
		Thread.sleep(5000);
		String importLink_Xpath=".//a[@class='OK-button' and @ng-click='doAction()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(importLink_Xpath));
		System.out.println("Clicked on OK link");
	}

	public boolean verifyNumberOfRecordsImported(String exepectedNumberRecords) throws Exception
	{
		String NumberOfRecords_Xpath=".//*[@class='set-number ng-binding']";
		CommonFunctions.waitForPageLoaded(_driver);Thread.sleep(2000);
		String actualNumberOfRecords=CommonFunctions.getText(_driver, By.xpath(NumberOfRecords_Xpath));
		if(exepectedNumberRecords.equalsIgnoreCase(actualNumberOfRecords))
		{
			System.out.println("Imported Contacts aresuccessful and all the records are being displayed");
			return true;
		}
		else
		{
			System.err.println("There is a maismatch in the expected number of records and actual records that are displayed check manually once");
			System.err.println("Ecpected number of records are : " +exepectedNumberRecords + "and actual records are : "+actualNumberOfRecords);
			return false;

		}

	}

	public void ClickOn_AssigntoCampaignLink() throws Exception
	{
		String assignToCampaignLink=".//*[@class='OK-button' and @ng-click='assignFilter()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(assignToCampaignLink));
		System.out.println("Clicked on Assign to Campaign link ");
	}

	public boolean verifyCreatedBatchHasSelectedOnContactsdropDown(String expectedBatchName)
	{
		String campaignFilterDropdown_xpath=".//*[@ng-model='campaignFactory.currentFilter']";
		String actualBatchNameSelected=CommonFunctions.GetSelectedOption(_driver, By.xpath(campaignFilterDropdown_xpath));
		if(actualBatchNameSelected.equals(expectedBatchName))
		{
			System.out.println("Expected batch name and selected batch name are same");
			return true;
		}
		else
		{
			System.err.println("Expected batch name and selected batch names are not same");
			return false;
		}
	}

	public void clickOn_createNewFilter() throws Exception
	{
		String createNewFilter_Xpath=".//*[@class='OK-button' and @ng-click='openFilter(false)']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(createNewFilter_Xpath));
		System.out.println("Clicked on Create new filter link");
	}

	public void EnterNewFilterName(String filterName) throws Exception
	{
		String FilterName_TextBox_Xpath=".//*[@ng-model='cmmFilterModel.Filter._Name']";
		CommonFunctions.enterText(_driver, By.xpath(FilterName_TextBox_Xpath), filterName);
		System.out.println("Entered the Filter Name as : " +filterName);
	}

	public void clickOn_AddRowLink() throws Exception
	{
		String AddRow_Xpath=".//*[@ng-click='AddRow()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(AddRow_Xpath));
		System.err.println("Clicked on add row link");

	}
	public void Clickon_SaveFilterLink() throws Exception
	{
		String saveFilterLink_Xpath=".//*[@ng-click='SaveFilter()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(saveFilterLink_Xpath));
		System.out.println("Clicked on save filter link");

	}

	public void clickOn_NextStepLink() throws Exception
	{
		String nextStepLink_Xpath=".//*[@ng-click='goToNextStep()']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(nextStepLink_Xpath));
		System.out.println("Clicked on next step link");
	}

	public boolean VerifyCustomizeSectionActive()
	{
		String customizeSectionSection_Xpath=".//*[text()='Customize']/../..";
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(customizeSectionSection_Xpath));
		if(_driver.findElement(By.xpath(customizeSectionSection_Xpath)).getAttribute("class").trim().equals("active"))
		{
			System.out.println("Customize section is exist and active");
			return true;
		}

		else
		{
			System.err.println("Customize section is not active");
			return false;
		}


	}


	public void clickon_CustomizeButtonBasedOnMediaType(String mediaType) throws Exception
	{
		String customizeButtonBasedOnMediaType_Xpath="//span[text()='"+mediaType+"']/../..//*[@class='customize-button']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(customizeButtonBasedOnMediaType_Xpath));
		System.out.println("Clicked on Customize button for the media type : "+mediaType);
	}

	public void ClickOn_DoneButton() throws Exception
	{
		String doneButton_Xpath=".//*[text()='Done']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(doneButton_Xpath));
		System.out.println("Clicked on done button");
	}

	public boolean customizeUpdateDataMediaType(String email) throws Exception
	{
		String emailTextBox=".//*[@type='email']";
		String submitButtonXpath=".//*[@value='Submit']";
		String updatedmailXpath=".//*[text()='Aravinth.Amirthalingam1@efi.com']";
		_driver.switchTo().frame(0);
		CommonFunctions.enterText(_driver, By.xpath(emailTextBox), email);
		System.out.println("Email updated as  : " +email);
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(submitButtonXpath));
		System.out.println("Clicked on submit button");

		boolean isUpdated= CommonFunctions.isElementPresent(_driver, By.xpath(updatedmailXpath));
		if(isUpdated)
		{
			System.out.println("updated the mail as : "+email);
			return true;
		}
		else
		{
			System.err.println("mail updation is unsuessful verify manually once");
			return false;
		}


	}

	public void selectPrintColor(String sSheetName,String sScenario,String sTestcase) throws Exception
	{
		//=========PrintinColor-Sides ===============
		System.out.println("PrintinColor-Sides");
		String PrintinColorSidesSubMenuSides = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubMenu");
		String PrintinColorSidesSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubSubMenu");
		System.out.println("Default Print In Color/Sides(Print in Color:Double Sided) is selected");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.P))).click();
		String PrinColor = "";
		if(PrintinColorSidesSubMenuSides.equals("Print In Black and White")){
			PrinColor =	"//label[@id='span-96']";			
		}else{
			PrinColor =	"//label[text()='Print in Color']/..";	
		}						
		Thread.sleep(1000);
		String PrintinColorSidesSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Print In ColorSides",PrintinColorSidesSubMenuSides , PrintinColorSidesSubSubMenu, "ImageID");
		_driver.findElement(By.xpath(PrinColor)).click();
		Thread.sleep(1000);

		String SubSubMenuPrintXpath = "//label[@id='span" + PrintinColorSidesSubSubMenuID + "']";
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(5000);

		//=========PrintinColor-Sides ===============
	}

	public void selectOrientation(String sSheetName,String sScenario,String sTestcase) throws Exception
	{
		//=========Orientation ===============
		System.out.println("Orientation ");
		//Place Orientation(Portrait) as default print option in ticket template.

		String OrientationSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Orientation");
		System.out.println("Default Orientation(Portrait)is clicked to select given Print option.");
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.OrientationDefault)));
		Thread.sleep(1000);
		String OrientationSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Orientation",OrientationSubMenu , "empty1", "ImageID");
		System.out.println(OrientationSubMenuID);

		String SubSubMenuPrintXpath = "//label[@id='span" + OrientationSubMenuID + "']";
		_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
		Thread.sleep(4000);				
		//=========Orientation===============
	}

	public void enterFinalHeight_Width(String width,String height) throws Exception
	{
		String finalWidthHeight_Xpath=".//label[text()='Final Width and Height']/../div/img";
		String width_Textbox=".//*[@id='txtCustomWidth']";
		String height_Textbox=".//*[@id='txtCustomHeight']";
		String ok_Link=".//*[@id='okFWHA']";
		CommonFunctions.waitForPageLoaded(_driver);

		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(finalWidthHeight_Xpath));		
		Thread.sleep(1000);
		CommonFunctions.enterText(_driver, By.xpath(width_Textbox), width);
		System.out.println("Width entered as : "+width);
		CommonFunctions.enterText(_driver, By.xpath(height_Textbox), height);
		System.out.println("Height entered as : "+height);
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(ok_Link));
	}

	public void selectBackCoverFirstLevelOption(String BackCoverSubMenu) throws Exception
	{

		//String backCoverSubMenu=".//*[@id='span546']";
		String BackCoverOption_Xpath="//label[text()='"+BackCoverSubMenu+"']/..";	
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.BackCoverDefault)));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(BackCoverOption_Xpath));
		System.out.println("Back cover selected as : "+BackCoverSubMenu);
	}

	public void selectFrontCoverFirstLevelOption(String FrontCoverSubMenu) throws Exception
	{
		//String frontCoverSubMentXpath=".//*[@id='span542']";
		String frontCoverSubMentXpath="//label[text()='"+FrontCoverSubMenu+"']/..";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.FrontCoverDefault)));
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(frontCoverSubMentXpath));
		System.out.println("Front cover option selected as : " +FrontCoverSubMenu);
	}
	
	public void selectMediaOptions(String MediaValue) throws InterruptedException
	{
	  _driver.findElement(By.xpath(".//a[contains(@title,'Media')]")).click();
	  Thread.sleep(2000);
	  if (_driver.findElements(By.id("mediaListContainer")).size()>0)
	  {
		  System.out.println("Media List is displayed");
		  String mediaxpath="//td[contains(@title,'"+MediaValue+"')]";
		  _driver.findElement(By.xpath(mediaxpath)).click();
		  _driver.findElement(By.xpath("//a[@class='OK-button']")).click();
		  
	  }
	  else
	  {
		  System.err.println("Media List is not displayed");
	  }
	}
	
	public void selectPrintServiceOptions(boolean Printoptions, String sSheetName,String sScenario,String sTestcase) throws Exception
	{
		//Select print options..
		//====================================================================================
		//Choosing Media/Print
		//====================================================================================
		//Media : A3,White as default item in Print Options
		if (Printoptions)
		{
			System.out.println(" ======  Fetching all the Print Options from Excel ======");
			String Media = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Media");
			String MediaScaleToFit = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "MediaScaleToFit");
			Thread.sleep(2000);

			//Clicking on Media couple of times since window is going to back ground.
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
			Thread.sleep(2000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
			Thread.sleep(2000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Media))).click();
			Thread.sleep(2000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SelectMediaSize))).sendKeys(Media);


			_driver.findElement(By.xpath(Locators.getProperty(Locators.MediaTable))).click();
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.MediaTable)));
			int Mediat = _driver.findElements(By.xpath(Locators.getProperty(Locators.MediaRows))).size();
			System.out.println("Selected Media count" +Mediat);
			//Select 1 row in the Media type if rows are not null
			if(Mediat >= 1){
				int SelectMediaT = 1 + (int)(Math.random() * ((Mediat - 1) + 1));
				System.out.println("Selected Media " +SelectMediaT);
				String SelectRow = "//div[@class='modaltable']/table/tbody/tr[" + SelectMediaT + "]/td[2]";
				System.out.println("Selected Media xpath " + SelectRow);
				Thread.sleep(5000);
				String Mediaselected = _driver.findElement(By.xpath(SelectRow)).getText();
				System.out.println("Selected Media text " + Mediaselected);
				_driver.findElement(By.xpath(SelectRow)).click();
				Thread.sleep(5000);
				dbConnection.UpdateFunction(sSheetName, sScenario, sTestcase, "MediaSelected", Mediaselected);
				String MediaScale = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "MediaScaleToFit");
				//Select Scale to fit check box with flag from test data file
				if(MediaScale.equals("Yes")){
					System.out.println(" ======  Select Scale to fit checkbox ======");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.MediaScaleToFit))).click();
					TakeScreenshot("Media Selected");
				}
			}
			Thread.sleep(3000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SelectMediaOkBtn))).click();
			//Media : A3,White as default item in Print Options
			Thread.sleep(3000);	
			//=========PrintinColor-Sides ===============
			System.out.println("PrintinColor-Sides");
			String PrintinColorSidesSubMenuSides = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubMenu");
			String PrintinColorSidesSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "PrintinColorSidesSubSubMenu");
			System.out.println("Default Print In Color/Sides(Print in Color:Double Sided) is selected");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.P))).click();
			String PrinColor = "";
			if(PrintinColorSidesSubMenuSides.equals("Print In Black and White")){
				PrinColor =	"//label[@id='span-2']";			
			}else{
				PrinColor =	"//label[@id='span-4']";	
			}						
			Thread.sleep(1000);
			String PrintinColorSidesSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Print In ColorSides",PrintinColorSidesSubMenuSides , PrintinColorSidesSubSubMenu, "ImageID");
			_driver.findElement(By.xpath(PrinColor)).click();
			Thread.sleep(1000);

			String SubSubMenuPrintXpath = "//label[@id='span" + PrintinColorSidesSubSubMenuID + "']";
			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(5000);

			//=========PrintinColor-Sides ===============
			//=========Front Cover ===============
			System.out.println("Front Cover");
			//Place No Front Cover as default print option in ticket template.
			String FrontCoverSubmenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuFrontCover");
			String FrontCoverSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownFrontCover");
			System.out.println("Default No Front Cover is clicked to select given Print option.");
			Thread.sleep(2000);
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).size()>0){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).click();
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FrontCoverDefault))).click();
			}else{
				System.out.println("Not able to click on Default No Front Cover Print Option");
			}
			Thread.sleep(1000);
			String enetered = "No";
			if (FrontCoverSubmenu.equals("Print on Outside")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FcoverPrintonOutside))).click();
				Thread.sleep(2000);
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();

				if(FrontCoverSubSubMenu.equals("Print In Black and White")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinBlackandWhite))).click();
				}else if(FrontCoverSubSubMenu.equals("Print In Color")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinColor))).click();
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
				enetered = "Yes";

			}else if (FrontCoverSubmenu.equals("Print on Outside and Inside")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.FcoverPrintonOutsideandInside))).click();
				Thread.sleep(2000);	
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();
				if(FrontCoverSubSubMenu.equals("Print In Black and White")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinBlackandWhite))).click();
				}else if(FrontCoverSubSubMenu.equals("Print In Color")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.FCoverPrintinColor))).click();
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
				enetered = "Yes";
			}else if(enetered.equals("No")){
				String FrontCoverSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Front Cover",FrontCoverSubmenu , FrontCoverSubSubMenu, "ImageID");
				System.out.println(FrontCoverSubSubMenuID);

				SubSubMenuPrintXpath = "//label[@id='span" + FrontCoverSubSubMenuID + "']";
				_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
				Thread.sleep(1000);	
			}
			Thread.sleep(4000);	

			//=========Front Cover  ===============
			//=========Back Cover ===============
			System.out.println("Back Cover");
			//Place No Front Cover as default print option in ticket template.
			String BackCoverSubmenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuBackCover");
			String BackCoverSubSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownBackCover");
			System.out.println("Default No Front Cover is clicked to select given Print option.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.BackCoverDefault))).click();
			Thread.sleep(1000);


			String enetered1 = "No";
			if (BackCoverSubmenu.equals("Print on Outside")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BcoverPrintonOutside))).click();
				Thread.sleep(2000);
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();

				if(BackCoverSubSubMenu.equals("Print In Black and White")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinBlackandWhite))).click();
				}else if(BackCoverSubSubMenu.equals("Print In Color")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinColor))).click();
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
				enetered1 = "Yes";

			}else if (BackCoverSubmenu.equals("Print on Outside and Inside")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.BcoverPrintonOutsideandInside))).click();
				_driver.findElement(By.xpath(Locators.getProperty(Locators.DropDownIcon))).click();
				if(BackCoverSubSubMenu.equals("Print In Black and White")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinBlackandWhite))).click();
				}else if(BackCoverSubSubMenu.equals("Print In Color")){
					_driver.findElement(By.xpath(Locators.getProperty(Locators.BCoverPrintinColor))).click();
				}

				_driver.findElement(By.xpath(Locators.getProperty(Locators.OKButton))).click();
				enetered1 = "Yes";
			}else if(enetered1.equals("No")){
				String BackCoverSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Back Cover",BackCoverSubmenu , BackCoverSubSubMenu, "ImageID");
				System.out.println(BackCoverSubSubMenuID);

				SubSubMenuPrintXpath = "//label[@id='span" + BackCoverSubSubMenuID + "']";
				_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
				Thread.sleep(4000);	
			}
			Thread.sleep(4000);	
			//=========Back Cover  ===============

			//=========Back Cover===============
			System.out.println("Binding");
			//Place Perfect Bind  as default print option in ticket template.
			String SubMenuBinding = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubMenuBinding");
			String SubSubMenuDropDownBinding = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "SubSubMenuDropDownBinding");
			System.out.println("Default Perfect Bind is clicked to select given Print option.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.PerfectBindDefault))).click();
			Thread.sleep(2000);
			String BindingSubSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Binding",SubMenuBinding , SubSubMenuDropDownBinding, "ImageID");
			System.out.println(BindingSubSubMenuID);
			SubSubMenuPrintXpath = "//label[@id='span" + BindingSubSubMenuID + "']";

			if(SubMenuBinding.equals("Coil Bind")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.CoilBindxPath))).click();
			}else if(SubMenuBinding.equals("Comb Bind")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.CombBindxPath))).click();
			}else if(SubMenuBinding.equals("GBC")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.GBCBindxPath))).click();
			}else if(SubMenuBinding.equals("Ring Binder")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.RingBindxPath))).click();
			}else if(SubMenuBinding.equals("Tape Bind")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.TapeBindxPath))).click();
			}else if(SubMenuBinding.equals("Velo Bind")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.VeloBindxPath))).click();
			}else if(SubMenuBinding.equals("Wire Bind")){
				_driver.findElement(By.xpath(Locators.getProperty(Locators.WireBindxPath))).click();
			}

			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(5000);	

			//	 			//=========Collate/Do Not Collate ===============
			//	 			System.out.println("Collate/Do Not Collate ");
			//	 			//Place Collate(Collate into Sets)  as default print option in ticket template.
			//	 			//String BackCoverSubmenu = dbConnection.ReadFunction(SheetName, Scenario, TestCaseID, "Collate");
			//	 			String CollateSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Collate");
			//	 			System.out.println("Default No Front Cover is clicked to select given Print option.");
			//	 			_driver.findElement(By.xpath(Locators.getProperty(Locators.CollateDefault))).click();
			//	 			Thread.sleep(1000);
			//	 			String CollateSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Collate",CollateSubMenu , "empty1", "ImageID");
			//	 			System.out.println(CollateSubMenuID);
			//
			//	 			SubSubMenuPrintXpath = "//label[@id='span" + CollateSubMenuID + "']";
			//	 			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			//	 			Thread.sleep(4000);	

			//=========Collate/Do Not Collate===============
			//=========Cutting ===============
			System.out.println("Cutting ");
			//Place Collate(Collate into Sets)  as default print option in ticket template.
			//String BackCoverSubmenu = dbConnection.ReadFunction(SheetName, Scenario, TestCaseID, "Collate");
			String CuttingSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Cutting");
			System.out.println("Default Cutting Cut in Half is clicked to select given Print option.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingDefault))).click();
			Thread.sleep(1000);
			String CuttingSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Cutting",CuttingSubMenu , "empty1", "ImageID");
			System.out.println(CuttingSubMenuID);

			SubSubMenuPrintXpath = "//label[@id='span" + CuttingSubMenuID + "']";

			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(4000);     
			if (CuttingSubMenu.equals("Cut to Specific Margin"))
			{
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingMargin))).clear();
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingMargin))).sendKeys("2");
				Thread.sleep(2000);     
				_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingOkBtn))).click();
				Thread.sleep(2000);
			}

			if (CuttingSubMenu.equals("Specific Width and Height"))
			{
				System.out.println("Inside specific width & height - cutting");
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingWidth))).clear();
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingWidth))).sendKeys("4");
				Thread.sleep(2000);     
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingHeight))).clear();
				_driver.findElement(By.id(Locators.getProperty(Locators.CuttingHeight))).sendKeys("6");
				Thread.sleep(2000);     
				_driver.findElement(By.xpath(Locators.getProperty(Locators.CuttingOkBtn))).click();
				Thread.sleep(2000);
			}
			Thread.sleep(2000);     



			//=========Cutting===============
			//=========Fold ===============
			System.out.println("Fold ");
			//Place Fold(No Folding)  as default print option in ticket template.

			String FoldSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Fold");
			System.out.println("Default Fold (No Folding) is clicked to select given Print option.");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.FoldingDefault))).click();
			Thread.sleep(1000);
			String FoldSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Fold",FoldSubMenu , "empty1", "ImageID");
			System.out.println(FoldSubMenuID);

			SubSubMenuPrintXpath = "//label[@id='span" + FoldSubMenuID + "']";
			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			Thread.sleep(4000);	

			//=========Fold===============
			//=========Orientation ===============
			//			System.out.println("Orientation ");
			//			//Place Orientation(Portrait) as default print option in ticket template.
			//			
			//			String OrientationSubMenu = dbConnection.ReadFunction(sSheetName, sScenario, sTestcase, "Orientation");
			//			System.out.println("Default Orientation(Portrait)is clicked to select given Print option.");
			//			_driver.findElement(By.xpath(Locators.getProperty(Locators.OrientationDefault))).click();
			//			Thread.sleep(1000);
			//			String OrientationSubMenuID = dbConnection.GetPrintOptionImageID("ImageIDs","Orientation",OrientationSubMenu , "empty1", "ImageID");
			//			System.out.println(OrientationSubMenuID);
			//						
			//			SubSubMenuPrintXpath = "//label[@id='span" + OrientationSubMenuID + "']";
			//			_driver.findElement(By.xpath(SubSubMenuPrintXpath)).click();
			//			Thread.sleep(4000);				
			//=========Orientation===============
			//====================================================================================
			//Print job summary
			//		Export_Import EIMP = new Export_Import();
			//		WebElement Review_Job = _driver.findElement(By.xpath(Locators.getProperty(Locators.Review_Job)));
			//		
			//		if (CommonFunctions.Elementdisplayed_Enabled(Review_Job))
			//		{
			//		 System.out.println("Review Job button is visible");
			//		 Review_Job.click();
			//		 Thread.sleep(1000);
			//		 WebElement Job_summary = _driver.findElement(By.xpath(Locators.getProperty(Locators.Job_Summary)));
			//		 EIMP.GetRowWithCellText("Media",Job_summary);
			//		 _driver.findElement(By.xpath("//a[@class='Cancel-button']")).click();
			//		 System.out.println("job summary is completed..");
			//		}
			TakeScreenshot("Print Options Selected");
		} //Printoptions - Flag	
	}


	//****************************************************************


	//Verify configure section is exist and active
	public boolean verifyConfigureSectionActive()
	{
		String configureSectionSection_Xpath=".//*[text()='Configure']/../..";
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(configureSectionSection_Xpath));
		if(_driver.findElement(By.xpath(configureSectionSection_Xpath)).getAttribute("class").trim().equals("active"))
		{
			System.out.println("Configure section is exist and active");
			return true;
		}

		else
		{
			System.err.println("Configure section is not active");
			return false;
		}
	}


	//Click on the due date picker calender as per the number of activity
	public void clickOn_DueDatePicker(int activityNumber) throws Exception
	{
		String dueDateCalender_Xpath=".//*[@ng-repeat='timeline in campaignFactory.Timelines']/table/tbody/tr["+activityNumber+"]/td[5]//a[@class='due-date-picker']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(dueDateCalender_Xpath))	;
		System.out.println(activityNumber +"st activity calender has been cliked");
	}

	public void clickonDate(String daydate) throws Exception
	{
		String dayDate_Xpath=".//*[text()='"+daydate+"']/..";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(dayDate_Xpath));
		System.out.println("Selected Date is : "+daydate);

	}


	//***************************End of the CMM Reusable methods******************************************//

	public void enterTheCreditCardNumber(String creditCardNumber) throws Exception
	{
		String creditCard_textBox_Xpath=".//*[@id='id_ccNumber']";
		CommonFunctions.enterText(_driver, By.xpath(creditCard_textBox_Xpath), creditCardNumber);
		System.out.println("Credit card Number Entered as : "+creditCardNumber);

	}

	public void SelectExpirationMonthAndYear(String month,String year) throws Exception
	{
		String selectExpirationMonthDropDown_Xpath=".//*[@id='id_ccExpMonth']";
		String selectExpirationYearDropDown_Xpath=".//*[@id='id_ccExpYear']";
		CommonFunctions.selectDropdownByText(_driver, By.xpath(selectExpirationMonthDropDown_Xpath), month);
		CommonFunctions.selectDropdownByText(_driver, By.xpath(selectExpirationYearDropDown_Xpath), year);

	}

	public void clickOn_PrintandDownLink() throws Exception
	{
		String printAndDownLoad_Xpath=".//*[text()='Print And Download']/..";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(printAndDownLoad_Xpath));
		System.out.println("Clicked on Print and Download Link");
	}

	public void clickon_iAgreeLink() throws Exception
	{
		String iAgree_Xpath=".//*[@ng-click='InvokeAddtoCart()']";
		CommonFunctions.waitForPageLoaded(_driver);
		try
		{
			CommonFunctions.ClickOn_Elemet_BasedOnVisiblity(_driver, By.xpath(iAgree_Xpath));
			System.out.println("Clicked on I agree link as it is present");
		}
		catch(NoSuchElementException e1)
		{
			System.out.println("Caught an exception as there is no i agree link present on web page");
		}
	}

	public void SelectSavedFile(String FileName) throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//span[contains(text(),'"+FileName+"')]")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//span[contains(text(),'"+FileName+"')]/../../../td[1]/input"));
			CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='MediaFilter']//span[text()='OK']"));
			Thread.sleep(5000);
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(2000);
		}
		
	}

	public void enterCreditCardDetails (String CreditCardNumber, String ExpMonth, String ExpYear) throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//span[text()='Click Place My Order to be directed to the secure payment page to enter your credit card information and complete your order.']")))
		{
			CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.Cart_Place_Order)));
			CommonFunctions.waitForPageLoaded(_driver);
			
			CommonFunctions.ClickElement(_driver, By.name("VISA_brand"));
			CommonFunctions.WaitFor_ElementVisiblity(_driver, By.name("Ecom_Payment_Card_Number"));
			CommonFunctions.SendValue(_driver, By.name("Ecom_Payment_Card_Number"), CreditCardNumber);
			
			if (Integer.valueOf(ExpMonth) < 10)
			{CommonFunctions.selectDropdownByText(_driver, By.name("Ecom_Payment_Card_ExpDate_Month"), "0"+ExpMonth);}
			else
			{CommonFunctions.selectDropdownByText(_driver, By.name("Ecom_Payment_Card_ExpDate_Month"), ExpMonth);}
			
			CommonFunctions.selectDropdownByText(_driver, By.name("Ecom_Payment_Card_ExpDate_Year"), ExpYear);
			CommonFunctions.SendValue(_driver, By.name("Ecom_Payment_Card_Verification"), "123");
			CommonFunctions.ClickElement(_driver, By.name("payment"));
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			enterTheCreditCardNumber(CreditCardNumber);
			SelectExpirationMonthAndYear(ExpMonth, ExpYear);
		}
	}
	
}
