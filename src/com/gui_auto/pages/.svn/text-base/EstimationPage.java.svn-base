package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
import org.openqa.selenium.interactions.Actions;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;



public class EstimationPage implements BaseElement
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


	public  EstimationPage(WebDriver driver) throws Exception 
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
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);

		return suniqueNumber;
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


	public void NavigateToEstimationSetupPage() throws Exception, IOException
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateSetup/detail/1");	


		if (isAlertPresent())
		{
			Alert alertDialog = _driver.switchTo().alert();    		
			String alertText = alertDialog.getText();
			System.out.println("alertText is "+ alertText);
			alertDialog.accept();
		}


		CommonFunctions.Wait(_driver, By.linkText("Estimate Setup"));
		assertEquals("Estimate Setup", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"EstimationSetup");
	}

	public void QuickSearch(String sOption,String sText,By sLoc) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.Quick_Search)), sOption);
		Thread.sleep(4000);
		_driver.findElement(By.id(Locators.getProperty(Locators.Quick_Search_Textbox))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Quick_Search_Textbox))).sendKeys(sText);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Quick_Search_Find_Button))).click();
		CommonFunctions.Wait(_driver, sLoc);

	}

	public void Recalculate() throws Exception
	{

		System.out.println("Recalculate");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Recalculate))).click();
		CommonFunctions.waitForPageLoaded(_driver);		
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		Thread.sleep(3000);
	}
	public void EstimateViewDetails() throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Quick_Search_Find_Button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Details_Print_Tab)));
	}

	public void UpadteEstimatePressDetails(String sSpreadSheet,String sPress,String sRunSizeW,String sRunSizeH,String sRunMethod,String sMRS,String RSS,String FMRS,String FRSS) throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					//CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Estimate_Press_Select_Press)), sPress);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).sendKeys(sSpreadSheet);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys(sRunSizeW);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys(sRunSizeH);
					CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).sendKeys(sMRS);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).sendKeys(RSS);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).sendKeys(FMRS);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).sendKeys(FRSS);
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

				} 


			}
		}
		else
		{
			Assert.fail(" Window Estimate Press not found");
		}
	}	

	public void EnterGripperAllowanceAndColorBar(String sGripperAllowance,String sColorBar,String sPress) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		System.out.println("Enter Gripper Allowance and Color Bar");
		_driver.findElement(By.xpath("//table[@id='estimatePress']/tbody/tr[1]/td[3]/div[1]/div[1]/div[1]/a/div")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Press: "+sPress)) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Gripper_Allowance))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Gripper_Allowance))).sendKeys(sGripperAllowance);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Bar_Allowance))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Bar_Allowance))).sendKeys(sColorBar);
					DC.Update();
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 


			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
			Assert.fail(" Window Press: 40 not found");
		}

	}

	public void SelectPressType(String sRunMethod) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Select_Press))).click();
		Thread.sleep(9000);
		//_driver.findElement(By.xpath("//a[@onclick='popup( this.href, 850, 900, false ); return false;']/img[1]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Press")) 
				{
					String sVersion=FetchVersion();
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).size()>0)
					{

						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys("152");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys("100");
						_driver.findElement(By.name("EstimatePresses.materialUp")).sendKeys(Keys.TAB);
						CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePresses.grainDirection"), "Short");
						CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
						Thread.sleep(4000);

					}
					else
					{

						_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys("152");
						_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys("100");
						//CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.runMethod"), sRunMethod);
						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.printRunMethod"), sRunMethod);

						Thread.sleep(4000);


					}




					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
						Thread.sleep(4000);
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
						Thread.sleep(4000);
					}
					Thread.sleep(3000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize==2)
					{
						_driver.close();
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

		}
	}
	public String FetchPressSheetsFromBuy() throws Exception
	{
		String sPressSheets ="";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					sPressSheets = _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Press_Sheets_From_Buy))).getAttribute("value");
				} 
				else
				{
					Assert.fail(" Window Estimate Press not found");
				}

			}
		}
		return sPressSheets;
	}	

	public void CreateCustomer(String sCustID,String sCustName,String sFName,String sLName,String sAdd1,String sAdd2,String sAdd3,String sPhoneNo,String sCity,int sSalePer,int sSaleCat,String CUstomerStatus) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Id))).sendKeys(sCustID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Name))).sendKeys(sCustName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_FirstName))).sendKeys(sFName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_LastName))).sendKeys(sLName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add1))).sendKeys(sAdd1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(sAdd2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(sAdd3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Phone))).sendKeys(sPhoneNo);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_City))).sendKeys(sCity);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_SalesPerson)), sSalePer);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Tax_Info))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_Sale_Category)), sSaleCat);
		Thread.sleep(4000);

		//CommonFunctions.selectDropdownByText(_driver, By.xpath("customerStatus"), "Open");
	}

	public void UpadteEstimatePressDetails1(String sSpreadSheet,String sPress,String sRunSizeW,String sRunSizeH,String sRunMethod,String sMRS,String RSS,String FMRS,String FRSS,String sGCB) throws Exception
	{

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion=FetchVersion();
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Details_Gripper_Allowance_Color_Bar))).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Details_Gripper_Allowance_Color_Bar)), sGCB);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).sendKeys(sSpreadSheet);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys(sRunSizeW);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys(sRunSizeH);
						CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).sendKeys(sMRS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).sendKeys(RSS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).sendKeys(FMRS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).sendKeys(FRSS);
						System.err.println("check 2");
					}
					else
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.gripperColorBar"), sGCB);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.pressSheets")).clear();
						_driver.findElement(By.name("estimatePresses.pressSheets")).sendKeys(sSpreadSheet);

						if(_driver.findElements(By.name("estimatePresses.runSize")).size()>0)
						{
							_driver.findElement(By.name("estimatePresses.runSize")).clear();
							_driver.findElement(By.name("estimatePresses.runSize")).sendKeys(sRunSizeW+ "x" +sRunSizeH);

						}
						else
						{	 
							_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
							_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys(sRunSizeW);
							_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
							_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys(sRunSizeH);
						}

						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.printRunMethod"), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).sendKeys(sMRS);

						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).sendKeys(RSS);

						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).sendKeys(FMRS);

						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).sendKeys(FRSS);
						System.err.println("check 3");
					}

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						System.err.println("check 4");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();


					}
					else
					{
						System.err.println("check 6");
						int sSize1 = _driver.getWindowHandles().size();
						boolean hasQuit1 =_driver.getWindowHandles().isEmpty();
						System.out.println("sSize is "+sSize1+" hasQuit is "+hasQuit1);

						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();


					}

					Thread.sleep(3000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize == 2)
					{
						_driver.close();
					}

				}

			} 


		}

		else
		{
			Assert.fail(" Window Estimate Press not found");
		}
		System.err.println("check 8");


	}

	public void UnExpectedAlert(String sTitle) throws Exception
	{
		String originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{
				if(_driver.switchTo().window(windowId1).getTitle().equals(sTitle)) 
				{
					if(_driver.findElements(By.xpath("//input[@value='Update & Go To Next']")).size()>0)
					{
						System.out.println("Pop up exists");
						_driver.switchTo().alert().accept();
						Thread.sleep(4000);
						System.err.println("check 5");
					}
				}

			}
		}
	}

	public String[] FetchDataFromPress() throws Exception
	{

		ArrayList<String> actual_role = new ArrayList<String>( );
		String sPressSheets ="",sNumAlong = "",sNumAcross="";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).size()>0)
					{
						sNumAlong = _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).getAttribute("value");
						sNumAcross= _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Across))).getAttribute("value");
						sPressSheets = _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Press_Sheets_From_Buy))).getAttribute("value");
					}
					else
					{
						sNumAlong = _driver.findElement(By.name("estimatePresses.numAlong")).getAttribute("value");
						sNumAcross= _driver.findElement(By.name("estimatePresses.numAcross")).getAttribute("value");
						sPressSheets = _driver.findElement(By.name("estimatePresses.numPressSheetsFromBuy")).getAttribute("value");
					}
					sNumAlong=sNumAlong.trim();
					actual_role.add(sNumAlong);
					System.out.println("sNumAlong is "+sNumAlong);

					sNumAcross=sNumAcross.trim();
					actual_role.add(sNumAcross);
					System.out.println("sNumAcross is "+sNumAcross);

					sPressSheets=sPressSheets.trim();
					System.out.println("sPressSheets is "+sPressSheets);
					actual_role.add(sPressSheets);
					_driver.close();

				} 
			}
		}
		return (String[]) actual_role.toArray( new String[ actual_role.size() ] );
	}	

	public String FetchNumPressForms() throws Exception
	{
		String sNumPressForms="";

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();


					if(_driver.findElements(By.xpath("//table[@id='EstimatePresses']/tbody/tr")).size()>0)
					{
						sNumPressForms = _driver.findElement(By.xpath("//span[text()='Num Press Forms']/../../../td/div")).getText();
					}
					else
					{
						sNumPressForms = _driver.findElement(By.xpath("//span[text()='Num Press Forms']/../../../td/div")).getText();
					}
					sNumPressForms=sNumPressForms.trim();
					System.out.println("sNumPressForms is "+sNumPressForms);
					_driver.close();

				} 
			}
		}
		return sNumPressForms;
	}	


	public String FetchNumPressForms1(String sRunMethod) throws Exception
	{
		String sNumPressForms="";

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();
					if(Integer.valueOf(sVersion)< 27)
					{
						CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
						Thread.sleep(4000);
					}
					else
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.printRunMethod"), sRunMethod);
						Thread.sleep(4000);
					}


					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

					if(Integer.valueOf(sVersion)< 27)
					{
						sNumPressForms = _driver.findElement(By.xpath("//table[@id='EstimatePresses']/tbody/tr[14]/td/div")).getText();
					}
					else
					{
						sNumPressForms = _driver.findElement(By.xpath("//table[@id='estimatePresses']/tbody/tr[14]/td/div")).getText();
					}
					sNumPressForms=sNumPressForms.trim();
					System.out.println("sNumPressForms is "+sNumPressForms);
					_driver.close();

				} 
			}
		}
		return sNumPressForms;
	}	
	public ArrayList FetchDataFromPress1() throws Exception
	{

		ArrayList  actual_role = new ArrayList();
		String sPressSheets ="",sNumAlong = "",sNumAcross="";
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();
					if( _driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).size()>0)
					{
						sNumAlong = _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).getAttribute("value");
						sNumAcross= _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Across))).getAttribute("value");
						sPressSheets = _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Press_Sheets_From_Buy))).getAttribute("value");
					}
					else
					{
						sNumAlong = _driver.findElement(By.name("estimatePresses.numAlong")).getAttribute("value");
						sNumAcross= _driver.findElement(By.name("estimatePresses.numAcross")).getAttribute("value");
						sPressSheets = _driver.findElement(By.name("estimatePresses.numPressSheetsFromBuy")).getAttribute("value");
					}
					sNumAlong=sNumAlong.trim();
					actual_role.add(sNumAlong);
					System.out.println("sNumAlong is "+sNumAlong);

					sNumAcross=sNumAcross.trim();
					actual_role.add(sNumAcross);
					System.out.println("sNumAcross is "+sNumAcross);

					sPressSheets=sPressSheets.trim();
					System.out.println("sPressSheets is "+sPressSheets);
					actual_role.add(sPressSheets);
					_driver.close();
				} 
			}
		}
		return actual_role;
	}
	public void UpadteEstimateRunSize(String sRunSizeW,String sRunSizeH) throws Exception
	{
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();
					if(_driver.findElements(By.name("estimatePresses.runSizeW")).size()>0)
					{
						_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys(sRunSizeW);
						_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys(sRunSizeH);
					}
					else
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys(sRunSizeW);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys(sRunSizeH);
					}
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}
				} 


			}
		}
		else
		{
			Assert.fail(" Window Estimate Press not found");
		}
	}		

	public ArrayList FetchEstimateRunSize() throws Exception
	{
		ArrayList  AddSize = new ArrayList();
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion = FetchVersion();
					String sRunSizeW="", sRunSizeH="";
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).size()>0)
					{
						sRunSizeW= 	_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).getAttribute("value");
					}
					else
					{
						sRunSizeW= 	_driver.findElement(By.name("estimatePresses.runSizeW")).getAttribute("value");
					}
					System.out.println("sRunSizeW is "+sRunSizeW);
					AddSize.add(sRunSizeW);
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).size()>0)
					{
						sRunSizeH= _driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).getAttribute("value");
					}
					else
					{
						sRunSizeH= _driver.findElement(By.name("estimatePresses.runSizeH")).getAttribute("value");
					}

					System.out.println("sRunSizeH is "+sRunSizeH);
					AddSize.add(sRunSizeH);
				} 
			}
		}
		else
		{
			Assert.fail(" Window Estimate Press not found");
		}
		return AddSize;
	}		
	public void UpadteEstimatePrintingMaterialsDetails(String sBuySizeW,String sBuySizeH) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		String sInv = dbConnection.ReadFunction("EstimatePre", "Estimate", "CreateEstimateByCreatingAllRequiredData", "MaterialType");
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Paper")) 
				{
					PleaseSelectCorrectInventoryItem1("id",sInv,"//table[@id='estimatePapers']/tbody/tr[5]/td/div/div[1]");

					if(_driver.findElements(By.name("estimatePapers.buySize")).size()>0)
					{
						_driver.findElement(By.name("estimatePapers.buySize")).clear();
						_driver.findElement(By.name("estimatePapers.buySize")).sendKeys(sBuySizeW+ "x" +sBuySizeH);
					}
					else
					{
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).sendKeys(sBuySizeW);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).sendKeys(sBuySizeH);					
						_driver.findElement(By.name("estimatePapers.amtRequired")).sendKeys(Keys.TAB);
					}

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

					Thread.sleep(4000);

					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize==2)
					{
						_driver.close();
					}
				}
			} 


		}

		else
		{
			Assert.fail(" Window Estimate Paper not found");
		}
	}		

	public void UpadteEstimateProductsDetails(String sFinalSizeW,String sFinalSizeH) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Product_Final_Size_W))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Product_Final_Size_W))).sendKeys(sFinalSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Product_Final_Size_H))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Product_Final_Size_H))).sendKeys(sFinalSizeH);
	}

	public void UpdateAndRecalculate() throws Exception
	{
		System.out.println("Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(4000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		System.out.println("Recalculate");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Recalculate))).click();
		Thread.sleep(4000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}


	public void NavigateToTotalsQtys() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_TotalQtys))).click();
		Thread.sleep(4000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Estimate_TotalQtys_NumSig)));
	}

	public void NavigateToAddEstimator() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimator/add");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Estimator_Name)));
		assertEquals("Adding Estimator", _driver.getTitle());
	}
	public boolean AddNewEstimator(String sName,String sEstimatePrintoutPerference) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimator_Name))).sendKeys(sName);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Printout_Perference)), sEstimatePrintoutPerference);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(4000);
		boolean sFlag = CommonFunctions.isTextPresent(_driver, "Object Added");
		return sFlag;
	}
	public void NavigateToAddSalePerson() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SalesPerson/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Salespersons", _driver.getTitle());
	}
	public boolean AddNewSalePerson(String sName,String sRate) throws Exception
	{
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(6000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Salesperson")) 
				{
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimator_Name))).sendKeys(sName);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimator_SalePerson_Commission_Rate))).sendKeys(sRate);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);	
					sFlag = CommonFunctions.isTextPresent(_driver, "Object Added");

				}

			}
		}
		else
		{
			System.out.println("Not able find window");
		}
		return sFlag;
	}

	public void NavigateToSalestax() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SalesTax/list");	
		CommonFunctions.waitForPageLoaded(_driver);
	}


	public void NavigateToPressInkType() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PressInkType/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimator_Add_New_Grid)));
		assertEquals("Press Ink Types", _driver.getTitle());
	} 
	public void AddSalesTax(String SalesCode) throws Exception
	{
		NavigateToSalestax();
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Add New']"));




	}
	public void AddNewPressInkType(String sPressInkType) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimator_Add_New_Grid))).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[2]/input")).sendKeys(sPressInkType);
		DC.Update();
	}

	public void DeletePressInkType() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount); 
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[3]/input[2]")).click();
		Thread.sleep(4000);
		DC.Update();

	}
	public boolean VerifyPressInkTypeDeleted(String sPressInkType)
	{
		boolean sFlag = CommonFunctions.isTextPresent(_driver,sPressInkType);
		System.out.println("sFlag value is "+sFlag);
		return sFlag;
	}
	public void NavigateToPressType() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PressType/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Press Types", _driver.getTitle());
	} 
	public void AddPressTypes(String sDesc,String sPressType, String sPressSpeedMethod) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Press_Press_Type)), sPressType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_PressSpeedMethod)), sPressSpeedMethod);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));

	}
	public void PressTypeCheckboxSettings(boolean sDuplicate,boolean SplitForms,boolean InterpolateSpeed,boolean ImposeCenteredFlats,boolean CanTile ) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sDuplicate, By.name(Locators.getProperty(Locators.Estimate_Duplicator_Checkbox)));
		PO.sSelectCheckBox(SplitForms, By.name(Locators.getProperty(Locators.Estimate_SplitForms_Checkbox)));
		PO.sSelectCheckBox(InterpolateSpeed, By.name(Locators.getProperty(Locators.Estimate_InterpolateSpeed_Checkbox)));
		PO.sSelectCheckBox(ImposeCenteredFlats, By.name(Locators.getProperty(Locators.Estimate_ImposeCenteredFlats_Checkbox)));
		PO.sSelectCheckBox(CanTile, By.name(Locators.getProperty(Locators.Estimate_CanTile_Checkbox)));
	}
	public boolean VerifyCheckboxIsChecked(By sLoc) throws Exception
	{
		if(_driver.findElement(sLoc).isSelected())
		{
			System.out.println("Already checked Status");
			return true;
		}
		else
		{
			return false;
		}

	}

	public void PressTypeUserCheckboxSettings(boolean sUsesPlates,boolean sUsesPressEvents,boolean UsesInk) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sUsesPlates, By.name(Locators.getProperty(Locators.Estimate_UsesPlates_Checkbox)));
		PO.sSelectCheckBox(sUsesPressEvents, By.name(Locators.getProperty(Locators.Estimate_UsesPressEvents_Checkbox)));
		PO.sSelectCheckBox(UsesInk, By.name(Locators.getProperty(Locators.Estimate_UsesInk_Checkbox)));
	}
	public void PressTypeRunSpeedCheckboxSettings(boolean sSize,boolean Colors,boolean sInkType,boolean sSpeedFactor,boolean sSeparateSizes,boolean sUseTotal) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sSize, By.name(Locators.getProperty(Locators.Estimate_RunSpeedSize_Checkbox)));
		PO.sSelectCheckBox(Colors, By.name(Locators.getProperty(Locators.Estimate_RunSpeedColors_Checkbox)));
		PO.sSelectCheckBox(sInkType, By.name(Locators.getProperty(Locators.Estimate_RunSpeedInktype_Checkbox)));
		PO.sSelectCheckBox(sSpeedFactor, By.name(Locators.getProperty(Locators.Estimate_SpeedFactor_Checkbox)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(4000);
		PO.sSelectCheckBox(sSeparateSizes, By.name(Locators.getProperty(Locators.Estimate_SeparateSizes_Checkbox)));

		PO.sSelectCheckBox(sUseTotal, By.name(Locators.getProperty(Locators.Estimate_UseTotalImpression_Checkbox)));
	}

	public void PressTypeSize1AndSize2CheckboxSettings(boolean sSize1UnitCharge,boolean sSize2UnitCharge,boolean sSize2UnitSpeed,boolean sSize2Spoilage) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sSize1UnitCharge, By.name(Locators.getProperty(Locators.Estimate_RunSpeedSide1UnitCharge_Checkbox)));
		PO.sSelectCheckBox(sSize2UnitCharge, By.name(Locators.getProperty(Locators.Estimate_RunSpeedSide2UnitCharge_Checkbox)));
		PO.sSelectCheckBox(sSize2UnitSpeed, By.name(Locators.getProperty(Locators.Estimate_RunSpeedSide2Speed_Checkbox)));
		PO.sSelectCheckBox(sSize2Spoilage, By.name(Locators.getProperty(Locators.Estimate_RunSpeedSide2Spoilage_Checkbox)));
	}
	public void AddPressInkTypes(String sPressInkType) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Press_Ink_Types))).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Add_New_Press_Ink_Types))).click();
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Select_Press_Ink_Type)), 1);
		Thread.sleep(4000);
	}
	public void NavigateToSpeedFactor() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/SpeedFactor/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Speed Factors", _driver.getTitle());
	}
	public void AddNewSpeedFactor(String sSpeedFactor) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		int rowcount = _driver.findElements(By.xpath("//table[@id='All']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Add_New_SpeedFactor))).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='All']/tbody/tr["+rowcount+"]/td[3]/input")).sendKeys(sSpeedFactor);
		DC.Update();
	}
	public void AddNewMaterialsProvider(String sName) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Add_New_Record))).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[2]/input")).sendKeys(sName);
		System.out.println("Update");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(4000);
		System.out.println(_driver.getCurrentUrl());
	}
	public void AddNewJobDifficultly(String sName) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		String j = ""+rowcount;
		if(rowcount<9)
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Add_New_Record))).click();
			Thread.sleep(4000);

			_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[2]/input")).sendKeys(j);
			_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[3]/input")).sendKeys(sName);
			System.out.println("Update");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			Thread.sleep(4000);

		}
		else
		{
			System.out.println("Already 9 Id's are present");
			System.out.println("Update");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			Thread.sleep(4000);
		}
	}

	public void AddNewPrePressWorkflow(String sPrepressWorkflow,String MaterialProvided,String PrepBindery) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Workflow)), sPrepressWorkflow);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Material_Provided)), MaterialProvided);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Bindery_Only)), PrepBindery);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(4000);
	}
	public void AddNewPressEventWorkflow(String sDesc) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(4000);
	}

	public void AddLinkPressEventToPressEventWorkflow(String sOrderStatus,String sDeviceCapability) throws Exception
	{

		_driver.findElement(By.xpath("//fieldset[@id='pressEventWorkflowItem_fieldset']/div[1]/div[1]/div[2]/input")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Link_Press_Event_Order_Status))).sendKeys(sOrderStatus);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Link_Press_Event_Device_Capability)), sDeviceCapability);
		Thread.sleep(4000);
	}





	public void AddNewPrepressActivity(String sSeq,String sPrePressItem,String sQntyCalc,String sEstExp,String sSizeCalc) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Prep_Activity_Record))).click();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Prep_Activity_Sequence))).sendKeys(sSeq);
		if (!(sPrePressItem.equals("")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_PerPress_Item)), sPrePressItem);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_PerPress_Item)), 1);
		}
		Thread.sleep(1000);

		if (!(sQntyCalc.equals("")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Quantity_Calc_Method)), sQntyCalc);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Quantity_Calc_Method)), 1);
		}
		Thread.sleep(1000);

		if (!(sEstExp.equals("")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Estimate_Expression)), sEstExp);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Estimate_Expression)), 1);
		}
		Thread.sleep(1000);

		if (!(sSizeCalc.equals("")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Size_Calc_Method)), sSizeCalc);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Prep_Activity_Size_Calc_Method)), 1);
		}
		Thread.sleep(1000);
	}

	public void NavigateToEnterNewEstimate() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimate_Number)));
		//assertEquals("Adding Estimate", _driver.getTitle());
	}

	public void NavigateToRequestNewEstimate() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateRequest/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimate_Number)));
		//assertEquals("Adding Estimate", _driver.getTitle());
	}

	public void NavigateToEstimatorList() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimator/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Estimators", _driver.getTitle());
	}
	public void NavigateToEstimatorQuantity() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);

		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Quantity/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Quantities", _driver.getTitle());
	}
	public void NavigateToEstimatorSize() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);  
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Size/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Sizes", _driver.getTitle());
	}
	public void NavigateToEstimate() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Search for Estimates", _driver.getTitle());
	}
	public void NavigateToCustomer() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Customer/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Customer_Name)));
		assertEquals("Adding Customer", _driver.getTitle());
	}
	public void ConvertAndGoToJob(String sEst,String sJob) throws Exception
	{
		String spath = "//div[@id='fmessage']/ul/li[contains(text(),'Estimate "+sEst+" converted to job "+sJob+"')]";
		System.out.println("Convert and go to Job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Convert_And_Go_To_Job))).click();
		Thread.sleep(4000);
		CommonFunctions.Wait(_driver, By.xpath(spath));

	}

	public boolean ConvertAndGoToJobVerify(String sEst,String sJob) throws Exception
	{
		String spath = "//div[@id='fmessage']/ul/li[contains(text(),'Estimate "+sEst+" converted to job "+sJob+"')]";
		System.out.println("Convert and go to Job");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Convert_And_Go_To_Job))).click();
		Thread.sleep(4000);
		boolean sFlag =  CommonFunctions.isElementPresent(_driver, By.xpath(spath));
		return sFlag;
	}
	public void ConvertEstimateToJob(String sEst,String sID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/convertToJob/"+sID);	
		CommonFunctions.Wait(_driver, By.linkText("Convert to Job"));
		assertEquals("Convert Estimate '"+sEst+"' to Job", _driver.getTitle());
	}
	public void AddPartEstimate(String sID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimatePart/add/"+sID);	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_Part_And_Calculate)));
		assertEquals("Adding Estimate Part", _driver.getTitle());
	}
	public void AddBlankPartEstimate(String sID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimatePart/addBlank/"+sID);	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_Part_And_Calculate)));
		//assertEquals("Adding Estimate Part", _driver.getTitle());
	}
	public void DupePartEstimate(int sID) throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		//_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateQuantity/detail/"+sID);
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[4][text()='Part : Product type']")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//a[text()='- Dupe Part']")).click();
		CommonFunctions.Wait(_driver, By.xpath("//div[@id='fmessage']/ul/li[text()='Duplicated part']"));
		assertEquals("Estimate Details", _driver.getTitle());
	}

	public void NavigateToFindEstimate() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Search for Estimates", _driver.getTitle());
	}
	public void NavigateToPressSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Press/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Presses", _driver.getTitle());
	}
	public void EnterPressInfoInPressSetupPage(String sCode,String sPressType,String sDesc,String MaxNum,String sMinDiff,String sMaxDiff,String sLiftHeight,String sColorBar,String sGripperAllowance,String sSideAllowance,String sMaterialGap,String sHelperAC,String sNoHelpers,boolean HaveSheets) throws Exception
	{

		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Code))).sendKeys(sCode);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Press_Press_Type)), sPressType);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Max_Num_Webs))).sendKeys(MaxNum);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Auto_Select_Min_Diff)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Auto_Select_Max_Diff)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Lift_Height))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Lift_Height))).sendKeys(sLiftHeight);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Bar_Allowance))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Bar_Allowance))).sendKeys(sColorBar);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Gripper_Allowance))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Gripper_Allowance))).sendKeys(sGripperAllowance);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Side_Allowance))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Side_Allowance))).sendKeys(sSideAllowance);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Material_Gap))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Material_Gap))).sendKeys(sMaterialGap);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Helper_Activity_Code)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Number_Helpers))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Number_Helpers))).sendKeys(sNoHelpers);
		PO.sSelectCheckBox(HaveSheets, By.name(Locators.getProperty(Locators.Press_Have_Sheeter)));
	}

	public void NavigateToPressSetupColorInfo() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Color_Info))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Front_Per_Pass)));
	}

	public void EnterPressSetupColorInfo(String sFrontPerPass,String sBackPerPass,String sTotalPerPass,String sMinColors ,String sMaxColors ,String sMaxWebPrint,String sCoatingSides,boolean sUnit) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		NavigateToPressSetupColorInfo();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Front_Per_Pass))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Front_Per_Pass))).sendKeys(sFrontPerPass);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Back_Per_Pass))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Back_Per_Pass))).sendKeys(sBackPerPass);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Total_Per_Pass))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Colors_Total_Per_Pass))).sendKeys(sTotalPerPass);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Auto_Select_Min_Colors))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Auto_Select_Min_Colors))).sendKeys(sMinColors);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Auto_Select_Max_Colors))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Auto_Select_Max_Colors))).sendKeys(sMaxColors);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Web_Print_Units))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Color_Info_Max_Web_Print_Units))).sendKeys(sMaxWebPrint);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Press_Color_Coating_Sides)), sCoatingSides);
		Thread.sleep(1000);
		PO.sSelectCheckBox(sUnit, By.name(Locators.getProperty(Locators.Press_Color_Varnish_Needs_Unit_Boolean_Check)));
	}

	public void NavigateToPressSetupSizeInfo() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Size_Info))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Size_Info_Min_Width)));
	}


	public void EnterPressSetupSizeInfo(String sMinWidth,String sMinLength,String sMaxWidth,String sMaxLength ,String sMaxImageWidth ,String sMaxImageLength,String sMiniCaliper,String sMaxCaliper,String sCoreDiameter,boolean sMaxPressSize) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		NavigateToPressSetupSizeInfo();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Min_Width))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Min_Width))).sendKeys(sMinWidth);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Min_Length))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Min_Length))).sendKeys(sMinLength);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Width))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Width))).sendKeys(sMaxWidth);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Length))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Length))).sendKeys(sMaxLength);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Image_Width))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Image_Width))).sendKeys(sMaxImageWidth);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Image_Length))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Max_Image_Length))).sendKeys(sMaxImageLength);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Auto_Select_Mini_Caliper))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Auto_Select_Mini_Caliper))).sendKeys(sMiniCaliper);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Auto_Select_Max_Caliper))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Auto_Select_Max_Caliper))).sendKeys(sMaxCaliper);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Core_Diameter))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Size_Info_Core_Diameter))).sendKeys(sCoreDiameter);

		PO.sSelectCheckBox(sMaxPressSize, By.name(Locators.getProperty(Locators.Press_Size_Info_Use_Max_Press_Size_As_Buy_Candidate)));
	}

	public void NavigateToPressSetupMakeReady() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Make_Ready))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_MakeReady_Activity_Code)));
	}


	public void EnterPressSetupMakeReady(String sMRAC,String sMinMRTime, String sUnitLabel,String sMRInitial, String sMRSideGuide,String sMRGripperChange,String sMRInitialSheets,String sMRSideGuideSheets,String sMRGripperChangeSheets,String sEasy,String sMedium,String sDifficult ,String sCoating,String sEasySheet,String sMediumSheet,String sDifficultSheet,String sCoatingSheet,boolean sMRSheetsInRun) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		NavigateToPressSetupMakeReady();
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_MakeReady_Activity_Code)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Min_MakeReady_Time))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Min_MakeReady_Time))).sendKeys(sMinMRTime);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Unit_Label))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Unit_Label))).sendKeys(sUnitLabel);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Initial))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Initial))).sendKeys(sMRInitial);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_SideGuide))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_SideGuide))).sendKeys(sMRSideGuide);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Gripper_Change))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Gripper_Change))).sendKeys(sMRGripperChange);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Initial_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Initial_Sheets))).sendKeys(sMRInitialSheets);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_SideGuide_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_SideGuide_Sheets))).sendKeys(sMRSideGuideSheets);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Gripper_Change_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Gripper_Change_Sheets))).sendKeys(sMRGripperChangeSheets);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Easy))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Easy))).sendKeys(sEasy);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Medium))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Medium))).sendKeys(sMedium);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Difficult))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Difficult))).sendKeys(sDifficult);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Coating))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Times_Per_Ink_Color_Coating))).sendKeys(sCoating);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Easy))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Easy))).sendKeys(sEasySheet);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Medium))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Medium))).sendKeys(sMediumSheet);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Difficult))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Difficult))).sendKeys(sDifficultSheet);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Coating))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_MakeReady_Sheets_Per_Ink_Color_Coating))).sendKeys(sCoatingSheet);

		PO.sSelectCheckBox(sMRSheetsInRun, By.name(Locators.getProperty(Locators.Press_MakeReady_Include_MRSheets_In_Run)));

	}


	public void NavigateToPressSetupWashUp() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Washup))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Washup_Activity_Code)));
	}

	public void EnterPressSetupWashUp(String sWashupAC,String sHrsForced, String sMinWashupTime,String sMaxWashupTime, String sBlack,String sStandard,String sAqueous,String sTimePMS,String sProcess,String sVarnish) throws Exception
	{

		NavigateToPressSetupWashUp();
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Washup_Activity_Code)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hrs_Forced))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hrs_Forced))).sendKeys(sHrsForced);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Min_Washup_Time))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Min_Washup_Time))).sendKeys(sMinWashupTime);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Max_Washup_Time))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Max_Washup_Time))).sendKeys(sMaxWashupTime);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Black))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Black))).sendKeys(sBlack);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Standard))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Standard))).sendKeys(sStandard);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Aqueous))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Aqueous))).sendKeys(sAqueous);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_TimePMS))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_TimePMS))).sendKeys(sTimePMS);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Process))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Process))).sendKeys(sProcess);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Varnish))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Washup_Hours_Per_Ink_Type_Varnish))).sendKeys(sVarnish);
	}

	public void NavigateToPressSetupDry() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Dry))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Dry_Other_Activity_Code)));
	}

	public void EnterPressSetupDry(String sDryAC,String sOver30,String sOver60, String sOver90,String sOver120) throws Exception
	{

		NavigateToPressSetupDry();
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Dry_Other_Activity_Code)),1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_30))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_30))).sendKeys(sOver30);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_60))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_60))).sendKeys(sOver60);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_90))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_90))).sendKeys(sOver90);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_120))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Dry_Time_Over_120))).sendKeys(sOver120);
	}
	public void NavigateToPressSetupSpoilage() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Spoilage))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_60)));
	}

	public void EnterPressSetupSpoilage(String sOver30,String sOver60, String sOver90,String sOver120,boolean IsRunSpoilage) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		NavigateToPressSetupSpoilage();

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_30))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_30))).sendKeys(sOver30);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_60))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_60))).sendKeys(sOver60);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_90))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_90))).sendKeys(sOver90);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_120))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Spoilage_Spoil_Over_120))).sendKeys(sOver120);
		PO.sSelectCheckBox(IsRunSpoilage, By.name(Locators.getProperty(Locators.Press_Spoilage_Include_Run_Spoilage_In_Run)));
	}

	public void NavigateToPressSetupSpeed() throws Exception
	{

		if(_driver.findElements(By.xpath("//span[text()='e']")).size()>0)
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='e']"));
		}
		else
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Speed']"));
		}
		// _driver.findElement(By.xpath("//span[text()='e']")).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Speed_More_Than_30)));
		System.out.println("Navigated to Press Setup Speed tab");
	}


	public void EnterPressSetupSpeed(String sRunAC,String sMoreThan30,String sMoreThan60, String sMoreThan90,String sMoreThan120,String sMinRunTime,String sLiftChange,String sLiftChangeSheets,String sRollChangeLength) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		NavigateToPressSetupSpeed();

		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Speed_Run_Activity_Code)), 1);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_30))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_30))).sendKeys(sMoreThan30);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_60))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_60))).sendKeys(sMoreThan60);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_90))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_90))).sendKeys(sMoreThan90);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_120))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_More_Than_120))).sendKeys(sMoreThan120);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_Min_Run_Time))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_Min_Run_Time))).sendKeys(sMinRunTime);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_MakeReady_LiftChange))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_MakeReady_LiftChange))).sendKeys(sLiftChange);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_MakeReady_LiftChange_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_MakeReady_LiftChange_Sheets))).sendKeys(sLiftChangeSheets);

		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_Roll_Change_Length))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Speed_Roll_Change_Length))).sendKeys(sRollChangeLength);
	}

	public void NavigateToPressSetupEvent() throws Exception
	{
		_driver.findElement(By.xpath("//a[text()='Events']")).click();
		CommonFunctions.Wait(_driver, By.id("pressEvents_fieldset"));
	}
	public void AddNewPressEvent(int sDeviceCap) throws Exception
	{


		CommonFunctions.selectDropdownByIndex(_driver, By.name("deviceCapability"), sDeviceCap);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("activityCode"), sDeviceCap);
		Thread.sleep(4000);
	}
	public void  AddNewPressEventSetupDetails(int sDeviceCap) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		NavigateToPressSetupEvent();
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);

		_driver.findElement(By.xpath("//fieldset[@id='pressEvents_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Press Event")) 
				{
					AddNewPressEvent(sDeviceCap);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}	
	}
	public void NavigateToPressSetupPlates() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Press_Plates))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Press_Plates_Plates_Per_Revolution)));
	}

	public void  EnterPressSetupPlates(String sPlatesPerRevolution) throws Exception
	{
		NavigateToPressSetupPlates();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Plates_Plates_Per_Revolution))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Plates_Plates_Per_Revolution))).sendKeys(sPlatesPerRevolution);
	}
	public void PressPlatesMakeReady(String sHrsNoReg,String sHrsMediumReg,String sHrsTightReg,String sSheetNoReg,String sSheetMediumReg ,String sSheetTightReg,String sSizeW,String sSizeH) throws Exception
	{
		_driver.findElement(By.xpath("//a[text()='Make Ready']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_No_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_No_Register))).sendKeys(sHrsNoReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_Medium_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_Medium_Register))).sendKeys(sHrsMediumReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_Tight_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Hours_Tight_Register))).sendKeys(sHrsTightReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_No_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_No_Register))).sendKeys(sSheetNoReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_Medium_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_Medium_Register))).sendKeys(sSheetMediumReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_Tight_Register))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Sheets_Tight_Register))).sendKeys(sSheetTightReg);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Plate_Size_W))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Plate_Size_W))).sendKeys(sSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Plate_Size_H))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Make_Ready_Plate_Size_H))).sendKeys(sSizeH);
	}
	public void  AddNewPressSetupPlates(String sDesc,String sInv,String sMinDiff,String sMaxDiff,String sSetupHrs,String sMaterialCost,String sBurnHours,String sPlateLife,String LaborAC,String MaterialAC,String sUnitLabel,String sHrsNoReg,String sHrsMediumReg,String sHrsTightReg,String sSheetNoReg,String sSheetMediumReg ,String sSheetTightReg,String sSizeW,String sSizeH) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		NavigateToPressSetupPlates();
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//fieldset[@id='plates_fieldset']/div[1]/div[1]/div[2]/a[contains(text(),'Add New')]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Plate")) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
					_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Min_Difficulty)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Max_Difficulty)), 1);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Set_Up_Hours))).sendKeys(sSetupHrs);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_MaterialCost))).sendKeys(sMaterialCost);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_per_BurnHours))).sendKeys(sBurnHours);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Plate_Life))).sendKeys(sPlateLife);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Press_Labor_Activity_Code)), LaborAC);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Press_Materials_Activity_Code)), MaterialAC);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).sendKeys(sUnitLabel);
					PressPlatesMakeReady(sHrsNoReg,sHrsMediumReg,sHrsTightReg,sSheetNoReg,sSheetMediumReg,sSheetTightReg,sSizeW,sSizeH);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(3000);
					System.out.println(originalHandle);
					System.out.println(sWindowTitle);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
	}


	public void  DeactivateePressSetupPlates() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		String sname = _driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[2]/td[3]/div")).getText();sname=sname.trim();
		_driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[2]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Plate:"+sname)) 
				{
					_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}	
	}
	public ArrayList EditPlatesPlateDetails(int LaborAC,int MaterialAC,String sUnitLabel) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		ArrayList plate = new ArrayList();
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		String sname = _driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[1]/td[3]/div")).getText();sname=sname.trim();
		_driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Plate:"+sname)) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Labor_Activity_Code)), LaborAC);
					Thread.sleep(4000);
					String sFetchSelectedLAC= CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Press_Labor_Activity_Code)));
					plate.add(sFetchSelectedLAC);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Press_Materials_Activity_Code)), MaterialAC);
					Thread.sleep(4000);
					String sFetchSelectedMAC= CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Press_Materials_Activity_Code)));
					plate.add(sFetchSelectedMAC);
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).sendKeys(sUnitLabel);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}	
		return plate;
	}
	public void EditPlatesMakeReadyDetails(String sHrsNoReg,String sHrsMediumReg,String sHrsTightReg,String sSheetNoReg,String sSheetMediumReg ,String sSheetTightReg,String sSizeW,String sSizeH) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		String sname = _driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[1]/td[3]/div")).getText();sname=sname.trim();
		_driver.findElement(By.xpath("//table[@id='plates']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Plate:"+sname)) 
				{
					PressPlatesMakeReady(sHrsNoReg,sHrsMediumReg,sHrsTightReg,sSheetNoReg,sSheetMediumReg,sSheetTightReg,sSizeW,sSizeH);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}	
	}
	public void NavigateToFolderSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Folder/list");	

		try 
		{ 
			System.out.println("Alert present");
			_driver.switchTo().alert(); 

		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			System.out.println("No Alert present");

		}  
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Folder", _driver.getTitle());
	}

	public void NavigateToCutterSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Cutter/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Cutter", _driver.getTitle());
	}

	public void NavigateToBinderSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Binder/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Binder", _driver.getTitle());
	}

	public void NavigateToPaddingSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Padding/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Padding", _driver.getTitle());
	}

	public void NavigateTo3KnifeSetup() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ThreeKnife/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Three Knife", _driver.getTitle());
	}
	public void EnterFolderSetupGeneralDetails(String sDesc,String sMinSizeW,String sMinSizeH ,String sMaxSizeW,String sMaxSizeH,String sDefaultNumup ,boolean sUseAdvanced) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		/* _driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMinSize_Width))).sendKeys(sMinSizeW);
			 _driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMinSize_Lenght))).sendKeys(sMinSizeH);
			 _driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMaxSize_Width))).sendKeys(sMaxSizeW);
			 _driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMaxSize_Lenght))).sendKeys(sMaxSizeH);

			 _driver.findElement(By.name("minSize")).sendKeys(sMinSizeW+" x "+sMinSizeH);
			 _driver.findElement(By.name("maxSize")).sendKeys(sMaxSizeW+" x "+sMaxSizeH);
		 */  

		int sSize = _driver.findElements(By.name("minSize")).size();
		if(sSize > 0)
		{
			_driver.findElement(By.name("minSize")).sendKeys(sMinSizeW+" x "+sMinSizeH);
			_driver.findElement(By.name("maxSize")).sendKeys(sMaxSizeW+" x "+sMaxSizeH);
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMinSize_Width))).sendKeys(sMinSizeW);
			_driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMinSize_Lenght))).sendKeys(sMinSizeH);
			_driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMaxSize_Width))).sendKeys(sMaxSizeW);
			_driver.findElement(By.name(Locators.getProperty(Locators.Folder_GeneralMaxSize_Lenght))).sendKeys(sMaxSizeH);
		}

		CommonFunctions.selectDropdownByText(_driver, By.name("machineType"), "Folder");
		Thread.sleep(4000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Folder_General_Default_NumUp)), sDefaultNumup);
		Thread.sleep(4000);
		PO.sSelectCheckBox(sUseAdvanced, By.name(Locators.getProperty(Locators.Folder_General_Use_Advanced)));
	}

	public void NavigateToFolderSetupSettings() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Folder_Setting))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Folder_Settings_Max_Units)));
	}

	public void EnterFolderSetupSettingsDetails(String sMaxUnits,String sMaxPlates,String sFirstUnitSetup,String sFirstUnitSetupSheet ,String sFirstUnitSpoilage,String sFirstUnitGap,String sAddUnitSetup ,String sAddUnitSetupSheet,String sAddUnitSpoilage,String sAddUnitGap,String sEachPlate,String sSetupSheet,String sPlateSpoilage,String sLiftChange,String sLiftChangeSheets,String sLiftHeight) throws Exception
	{
		NavigateToFolderSetupSettings();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Max_Units))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Max_Units))).sendKeys(sMaxUnits);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Max_Plates))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Max_Plates))).sendKeys(sMaxPlates);

		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_First_Unit_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_First_Unit_Setup))).sendKeys(sFirstUnitSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_First_Unit_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_First_Unit_Setup_Sheets))).sendKeys(sFirstUnitSetupSheet);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_First_Unit_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_First_Unit_Spoilage))).sendKeys(sFirstUnitSpoilage);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_First_Unit_Gap))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_First_Unit_Gap))).sendKeys(sFirstUnitGap);

		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Additional_Unit_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Additional_Unit_Setup))).sendKeys(sAddUnitSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Additional_Unit_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Settings_Additional_Unit_Setup_Sheets))).sendKeys(sAddUnitSetupSheet);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Additional_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Additional_Spoilage))).sendKeys(sAddUnitSpoilage);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Additional_Gap))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Additional_Gap))).sendKeys(sAddUnitGap);


		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_SetUp_Each_Plate))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_SetUp_Each_Plate))).sendKeys(sEachPlate);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_SetUp_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_SetUp_Sheets))).sendKeys(sSetupSheet);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Plate_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Plate_Spoilage))).sendKeys(sPlateSpoilage);

		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Change))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Change))).sendKeys(sLiftChange);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Change_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Change_Sheets))).sendKeys(sLiftChangeSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Height))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Setting_Lift_Height))).sendKeys(sLiftHeight); 
	}

	public void NavigateToFolderSetupSpeedTab() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Folder_Speed))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Folder_Speed_Units_Per_Hour)));
	}

	public void EnterFolderSetupSpeedDetails(String sLengthPerHour,String PerUnitSpeedAdjust,String PerUnitPlateAdjust) throws Exception
	{
		NavigateToFolderSetupSpeedTab();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Units_Per_Hour))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Units_Per_Hour))).sendKeys(sLengthPerHour);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Per_Unit_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Per_Unit_Speed_Adjust))).sendKeys(PerUnitSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Per_Plate_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Speed_Per_Plate_Speed_Adjust))).sendKeys(PerUnitPlateAdjust);
	}
	public void EnterFolderSetupSpeedDetailsWhenUseAdvanceDisabled(String sSize,String sSpeed) throws Exception
	{
		NavigateToFolderSetupSpeedTab();
		_driver.findElement(By.xpath("//fieldset[@id='FolderSpeeds_fieldset']/div[1]/div[1]/div[2]/input")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name("FolderSpeeds.upToSize")).sendKeys(sSize);
		_driver.findElement(By.name("FolderSpeeds.speed")).sendKeys(sSpeed);

	}


	public void NavigateToFolderSetupAdjustmentTab() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Folder_Adjustment))).click();
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Setup)));
	}

	public void EnterFolderSetupAdjustmentDetails(String sGateFoldSetup,String sGateFoldSetupSheets,String sGateFoldSpeedAdjust,String sGateFoldSpoilage,String sScoreFoldSetup,String sScoreFoldSetupSheets,String sScoreFoldSpeedAdjust,String sScoreFoldSpoilage,String sPerfFoldSetup,String sPerfFoldSetupSheets,String sPerfFoldSpeedAdjust,String sPerfFoldSpoilage,String sSlitFoldSetup,String sSlitFoldSetupSheets,String sSlitFoldSpeedAdjust,String sSlitFoldSpoilage,String sGlueFoldSetup,String sGlueFoldSetupSheets,String sGlueFoldSpeedAdjust,String sGlueFoldSpoilage) throws Exception
	{
		NavigateToFolderSetupAdjustmentTab();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Setup))).sendKeys(sGateFoldSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Setup_Sheets))).sendKeys(sGateFoldSetupSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Speed_Adjust))).sendKeys(sGateFoldSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Gate_Fold_Spoilage))).sendKeys(sGateFoldSpoilage);


		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Setup))).sendKeys(sScoreFoldSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Setup_Sheets))).sendKeys(sScoreFoldSetupSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Speed_Adjust))).sendKeys(sScoreFoldSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Score_Fold_Spoilage))).sendKeys(sScoreFoldSpoilage);

		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Setup))).sendKeys(sPerfFoldSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Setup_Sheets))).sendKeys(sPerfFoldSetupSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Speed_Adjust))).sendKeys(sPerfFoldSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Perf_Fold_Spoilage))).sendKeys(sPerfFoldSpoilage);

		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Setup))).sendKeys(sSlitFoldSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Setup_Sheets))).sendKeys(sSlitFoldSetupSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Speed_Adjust))).sendKeys(sSlitFoldSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Slit_Fold_Spoilage))).sendKeys(sSlitFoldSpoilage);


		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Setup))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Setup))).sendKeys(sGlueFoldSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Setup_Sheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Setup_Sheets))).sendKeys(sGlueFoldSetupSheets);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Speed_Adjust))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Speed_Adjust))).sendKeys(sGlueFoldSpeedAdjust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Spoilage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Adjustment_Glue_Fold_Spoilage))).sendKeys(sGlueFoldSpoilage);
	}

	public void SimpleEstimateCreation(String sEstNum,String sCust,String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String sPrePress,String sPressEvent,String sPress) throws Exception
	{

		String sInv = dbConnection.ReadFunction("EstimatePre", "Estimate", "CreateEstimateByCreatingAllRequiredData", "MaterialType");
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
			Thread.sleep(4000);
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital Printing");
				Thread.sleep(4000);
			}
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimate_Number)));
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).sendKeys(sEstNum);

		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_PartInfo))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		Thread.sleep(2000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), 1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Press_Event_Workflow)), sPressEvent);
		Thread.sleep(1000);
		//CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Press)), 1);
		//Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(1000);

		PleaseSelectCorrectInventoryItem("id",sInv,"//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]");

		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys("4");
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys("0");
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys("4");
	}



	public void CreateEstimateWithEachOf(String sEstNum,String sCust,String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String EachOF,String sS1,String sS2,String  sTotal) throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		String sInv = dbConnection.ReadFunction("EstimatePre", "Estimate", "CreateEstimateByCreatingAllRequiredData", "MaterialType");
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
			Thread.sleep(4000);
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital Printing");
				Thread.sleep(4000);
			}
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).sendKeys(sEstNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_PartInfo))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		Thread.sleep(4000);

		if(_driver.findElements(By.name("finalSize")).size()>0)
		{
			_driver.findElement(By.name("finalSize")).sendKeys("28.5 x 41");
		}
		else
		{
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		}


		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).sendKeys(EachOF);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//div/h4[contains(label,'Material Type')]/following-sibling::div/select"), "Inventory Item");
		Thread.sleep(4000);

		//		PleaseSelectCorrectInventoryItem("id",sInv,"//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]");

		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys(sTotal);
	}
	public void CreateEstimatePartInfo(String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String EachOF,String sS1,String sS2,String  sTotal) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_PartInfo))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).sendKeys(EachOF);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys(sTotal);
	}
	public String CreateEstimateGeneralDetails(String sEstNum,String sDesc,String sCust,String sContact,String sDueDate) throws Exception
	{
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
			Thread.sleep(4000);
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital Printing");
				Thread.sleep(4000);
			}
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).sendKeys(sEstNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Des))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Des))).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);

		CommonFunctions.selectDropdownByText(_driver, By.name("contact"), sContact);
		Thread.sleep(4000);

		// _driver.findElement(By.name("prospectCompany")).sendKeys(sCompany);
		//	_driver.findElement(By.name("prospectCompany")).sendKeys(Keys.TAB);
		_driver.findElement(By.name("dueDate")).sendKeys(sDueDate);	

		return sContact;
	}
	public void EnterDetailsForBlankPart(String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String EachOF,String sS1,String sS2,String  sTotal) throws Exception
	{
		String sInv = dbConnection.ReadFunction("EstimatePre", "Estimate", "CreateEstimateByCreatingAllRequiredData", "MaterialType");

		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("jobProductType"), 1);
			Thread.sleep(4000);
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
				Thread.sleep(4000);

			}
			//	 CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital Printing");
			// Thread.sleep(4000);
		}
		assertEquals("Adding Estimate Part", _driver.getTitle());
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).sendKeys(EachOF);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(4000);
		PleaseSelectCorrectInventoryItem("id",sInv,"//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]");
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys(sTotal);
	}

	public void EnterEstimateDetails(String sEstNum,String sCust,String sSalePerson,String sEstimator) throws Exception
	{
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
			Thread.sleep(4000);
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital Printing");
				Thread.sleep(4000);
			}
		}
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Number))).sendKeys(sEstNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Des))).sendKeys(sEstNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_SalePerson)), 1);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Estimator)), 1);
		Thread.sleep(4000);
	}

	public void EnterEstimatePartInfoDetails(String sCat,String sProd,String sComProd,String sProdType,String sDesc,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String sPages,String sFoldPattern,boolean sTile) throws Exception
	{

		System.out.println(sCat+ " -- " +sProd+ " -- " +sComProd+ " -- " +sProdType+ " -- " +sDesc+ " -- " +sFinaleSizeW+ " -- " +sFinaleSizeH+ " -- " +sQty1+ " -- " +sQty1Desc+ " -- " +sPages+ " -- " +sFoldPattern);
		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_PartInfo))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		Thread.sleep(4000);
		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Composit_Product)), sComProd);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_ProductionType)), sProdType);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Part_Desc))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Addl_Desc))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Pages)), sPages);
		Thread.sleep(4000);
		//	CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Fold_Pattern)), 1);
		//	Thread.sleep(4000);
		//	PO.sSelectCheckBox(true,By.name(Locators.getProperty(Locators.Est_Tile_Product)));
	}

	public void EditJobProductType() throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();


		System.out.println("Click on Product type Magnifying Glass in estimate part info page");
		CommonFunctions.ClickElement(_driver, By.xpath("//div/h4[contains(label,'Product')]/following-sibling::div/a/div"));
		Thread.sleep(3000);

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Job Product Type")) 
				{
					System.out.println("Click on Setting tab");
					CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']/div[2]/span/a"));
					Thread.sleep(4000);
					System.out.println("Clear the Inventory Item");
					_driver.findElement(By.name("inventoryItem")).clear();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(3000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		else
		{
			System.out.println("Not able to find window");
		}

	}
	public void EnterEstimatePartInfoDetails1(String sEachOf,String sPrePress,String sPressEvent,String sGrainSpec,String sGripperColorBar,String sSpineBleed,String sWidthBleed,String sFinishing,String sShipMail,String sDiffculty,String sPress,String sRunMethod ) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Each_Of))).sendKeys(sEachOf);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), sPrePress);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Press_Event_Workflow)), sPressEvent);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Grain_Specification)), sGrainSpec);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Gripper_Color_Bar)), sGripperColorBar);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Spine_Bleed)), sSpineBleed);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Width_Bleed)), sWidthBleed);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Finishing)), 1);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ship_Mail)), sShipMail);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Diffculty)), sDiffculty);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Press)), 1);
		Thread.sleep(4000);

		int sSize = _driver.findElements(By.name("pressTypeRunMethod")).size();
		if(sSize>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("pressTypeRunMethod"), sRunMethod);
		}
		else if (CommonFunctions.isElementPresent(_driver, By.name("runMethod")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("runMethod"), sRunMethod);
		}
		else if (CommonFunctions.isElementPresent(_driver, By.name("printRunMethod")))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("printRunMethod"), sRunMethod);
		}

		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Run_Method)), sRunMethod);
		Thread.sleep(4000);
	}
	public void EnterEstimatePartInfoDetails2(boolean sGangable,String sSaleCat,String sPriceLevel,String sSpeedFactor,String sCommRate,String sPriceList,String sItemCat,String sPrdType,String sDiscount) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sGangable,By.name(Locators.getProperty(Locators.Est_Gangable_Checkbox)));
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Sales_Category)), 1);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Price_Level)), sPriceLevel);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Speed_Factor_1)), sSpeedFactor);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Commission_Rate))).sendKeys(sCommRate);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Price_List)), sPriceList);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Item_Category)), sItemCat);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Product_Type)), sPrdType);
		Thread.sleep(4000);
		//_driver.findElement(By.name(Locators.getProperty(Locators.Est_Item_Discount))).sendKeys(sDiscount);

	}

	public void EnterEstimateMaterialPaperDetails(String sMatType,String sStdTypeWeight,String sPaperType,String sPaperWeight,String sPaperDesc,String sPriceUOM,String sUSD,String sCurrency,String sBuySizeW,String sBuySizeH,String sRunSizeW,String sRunSizeH,String sVendor,String sQuote,String sQuotePrice ) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Material_Type)), sMatType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Std_Type_Weight)), 2);
		Thread.sleep(4000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Est_Paper_Type))).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Paper_Type)), 1);
			Thread.sleep(4000);
		}
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Paper_Weight)), 1);
		Thread.sleep(4000);
		//	_driver.findElement(By.name(Locators.getProperty(Locators.Est_Paper_Desc))).sendKeys(sPaperDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Price_UOM))).sendKeys(sPriceUOM);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_USD)), sUSD);
		Thread.sleep(4000);
		//	CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Currency)), sCurrency);
		//	Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Buy_Size_W))).sendKeys(sBuySizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Buy_Size_H))).sendKeys(sBuySizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_W))).sendKeys(sRunSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_H))).sendKeys(sRunSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Vendor))).sendKeys(sVendor);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Quote))).sendKeys(sQuote);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Quoted_price))).sendKeys(sQuotePrice);

	}
	public void EnterEstimateMaterialInkDetails(String sInkDefault,String sS1,String sS2,String sTotal,String sInkType,String sPressInkType,String sCoverageFront,String sCoverageBack,String sCosting,String sVanish) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Default)), sInkDefault);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys(sTotal);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Type)), sInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Press_Ink_Type)), sPressInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Front)), sCoverageFront);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Back)), sCoverageBack);
		Thread.sleep(4000);
		//	CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Costing)), 1);
		//	Thread.sleep(4000);
		//	CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Vanish)), 1);
		Thread.sleep(4000);
	}

	public void CreateInkDefaults(String sDesc,String sS1,String sS2,String sTotal,String sInkType,String sPressInkType,String sCoverageFront,String sCoverageBack,String sCosting,String sVanish) throws Exception
	{

		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);

		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);

		//_driver.findElement(By.name("colorsTotal")).sendKeys(sTotal);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Type)), sInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Front)), sCoverageFront);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Back)), sCoverageBack);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Costing)), sCosting);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Vanish)), sVanish);
		Thread.sleep(4000);
	}

	public boolean Calculate() throws Exception
	{
		int i =0;
		System.out.println("Clicking the Calculate Button");
		_driver.findElement(By.xpath("//input[@name='updateForm' and @value='Calculate']")).click();
		Thread.sleep(3000);	
		while(_driver.findElements(By.xpath("//input[@name='updateForm' and @value='Calculate']")).size()>0 && i<=3)
		{
			_driver.findElement(By.xpath("//input[@name='updateForm' and @value='Calculate']")).click();
			Thread.sleep(3000);	
			i++;
		}
		//_driver.findElement(By.xpath(Locators.getProperty(Locators.Calculate))).sendKeys(Keys.ENTER);

		boolean sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Recalculate)));
		return sFlag;

	}



	public void NavigateToMaterialSetUpPaperSetupPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/StandardPaperType/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Standard Paper Types", _driver.getTitle());
	}

	public void NavigateToMaterialSetUpInkTonerSetupPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Ink/list");	
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Ink/Toner", _driver.getTitle());
	}

	public void NavigateToMiscellaneousMaterialsProvidedPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/MaterialProvided/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Materials Provided", _driver.getTitle());
	}
	public void NavigateToMiscellaneousJobDiffcultyPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobDifficulty/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Job Difficulties", _driver.getTitle());
	}
	public void NavigateToWorkflowPrepressWorkflowPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PrepressWorkflow/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Prepress Workflow", _driver.getTitle());
	}
	public void NavigateToWorkflowPressEventWorkflow() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PressEventWorkflow/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Press Event Workflows", _driver.getTitle());
	}
	public void NavigateToWorkflowFinishingtWorkflow() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/BindingMethod/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Finishing Workflows", _driver.getTitle());
	}
	public void NavigateToWorkflowShippingAndMailingtWorkflow() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/ShippingWorkflow/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Shipping and Mailing Workflow", _driver.getTitle());
	}

	public void NavigateToEstimateExpression() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateExpression/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Estimate Expressions", _driver.getTitle());
	}

	public void NavigateToEstimateFolderPatterns() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/FoldPattern/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Fold Patterns", _driver.getTitle());
	}

	public void NavigateToEstimateRequestadd() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateRequest/add");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Fold Patterns", _driver.getTitle());
	}

	public void NavigateToEstimateProductCategory() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateProductCategory/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Estimate Product Categories", _driver.getTitle());
	}
	public void NavigateToInkDefaults() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/InkDefault/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Ink Defaults", _driver.getTitle());
	}

	public void NavigateToPrepressItem() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PrepressItem/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Prepress Item", _driver.getTitle());
	}

	public void NavigateToFinishingShippingOperation() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/FinishingOperation/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Finishing Operation", _driver.getTitle());
	}
	public void NavigateToEstimateJobProductType() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/JobProductType/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Product Types", _driver.getTitle());
	}
	public void NavigateToEstimateCompositeProduct() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateCompositeProduct/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Composite Products", _driver.getTitle());
	}
	public void NavigateToAddQuoteLetterTypes() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteLetterType/add");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Description)));
		assertEquals("Adding Estimate Quote Letter Type", _driver.getTitle());
	}

	public void NavigateToAddQuoteLetterTextList() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/QuoteLetterText/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Quote Letter Texts", _driver.getTitle());
	}
	public void NavigateToMiscellaneousPrePMethodPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/PrepMethod/list");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Prep Methods", _driver.getTitle());
	}

	public void NavigateToEstimateSetupPage() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/EstimateSetup/detail/1");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Base_Price_Level)));
		Thread.sleep(4000);
		assertEquals("Estimate Setup", _driver.getTitle());
	}
	public void EnterPaperSetupDetails(String sCode,String sName,String sSizeW,String sSizeH ,String sBasisSheets,String sUOM,String sAbb) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Code))).sendKeys(sCode);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PaperSetup_Name))).sendKeys(sName);
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_BasicSizeW))).sendKeys(sSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_BasicSizeH))).sendKeys(sSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_BasisSheets))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_BasisSheets))).sendKeys(sBasisSheets);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PaperSetup_Weight_UOM)), 1);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_Basis_Weight_Abbreviation))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_Basis_Weight_Abbreviation))).sendKeys(sAbb);
	}

	public boolean AddingPaperType(String sCode,String sStdPaperType,String sDesc,String sAC,String sMRAC ,String sWeightType,String sInkMileage,String sRollPressType,String sFrontCoating,String sBackCoating,String sCoatingPremitted,String sTexture,String sRecycledPer,boolean sStockMayBeCutDown,boolean sFrontandBackSame,boolean sAllowPerfecting) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		PurchasePage PO = new PurchasePage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		boolean sFlag = false;
		_driver.findElement(By.xpath("//fieldset[@id='paperType_fieldset']/div[1]/div[1]/div[2]/a[contains(text(),'Add New')]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Paper Type")) 
				{

					_driver.findElement(By.name(Locators.getProperty(Locators.Press_Code))).sendKeys(sCode);
					///CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PaperSetup_Standard_Paper_Type)), sStdPaperType);
					//Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_Activity_Code)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_Make_Ready_AC)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PaperSetup_WeightType)), 1);
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_Paper_Ink_Mileage))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_Paper_Ink_Mileage))).sendKeys(sInkMileage);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Press_Press_Type)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PaperSetup_Front_Coating)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PaperSetup_Back_Coating)), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Whether_Coating_Is_Permitted_For_This_Paper_Type)), 1);
					Thread.sleep(4000);

					_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_Textture))).sendKeys(sTexture);
					_driver.findElement(By.name(Locators.getProperty(Locators.PaperSetup_RecycledPercentage))).sendKeys(sRecycledPer);
					PO.sSelectCheckBox(sStockMayBeCutDown, By.name(Locators.getProperty(Locators.PaperSetup_Stock_May_Be_Cut_Down)));
					PO.sSelectCheckBox(sFrontandBackSame, By.name(Locators.getProperty(Locators.PaperSetup_Front_And_Back_Same)));
					PO.sSelectCheckBox(sAllowPerfecting, By.name(Locators.getProperty(Locators.PaperSetup_Allow_Perfecting)));
					DC.Add();
					sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}	
		return sFlag;
	}



	public boolean EnterPaperTypeWeightDetails(String sCode,String sWeight,String sCaliper) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		boolean sFlag = false;

		int rowCount =_driver.findElements(By.xpath("//table[@id='paperType']/tbody/tr")).size();
		System.out.println("rowCount is "+rowCount);
		for(int i = 1;i<=rowCount;i++)
		{

			String sCodeIS = _driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[3]/input")).getAttribute("value");
			sCodeIS =sCodeIS.trim();
			System.out.println("sCodeIS is "+sCodeIS);
			if(sCodeIS.equals(sCode))
			{
				_driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
				Thread.sleep(3000);
				break;
			}
		}


		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Paper Type: "+sCode)) 
				{
					System.out.println("Click on Weights tab");
					_driver.findElement(By.xpath("//a[text()='Weights']")).click();
					Thread.sleep(4000);
					String sTotalRec = _driver.findElement(By.xpath("//fieldset[@id='PaperWeight_fieldset']/div/div[1]/div[1]/strong")).getText();
					sTotalRec=sTotalRec.trim();
					if(sTotalRec.equals("0"))
					{
						_driver.findElement(By.xpath("//fieldset[@id='PaperWeight_fieldset']/div[1]/div[1]/div[2]/input[1]")).click();
						Thread.sleep(4000);
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr[1]/td[3]/input")).sendKeys(sWeight);
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr[1]/td[4]/input")).sendKeys(sCaliper);
					}
					else
					{
						int sTotalRecord = _driver.findElements(By.xpath("//table[@id='PaperWeight']/tbody/tr")).size();
						System.out.println("sTotalRecord is "+sTotalRecord);
						sTotalRecord=sTotalRecord+1;

						_driver.findElement(By.xpath("//fieldset[@id='PaperWeight_fieldset']/div[1]/div[1]/div[2]/input[1]")).click();
						Thread.sleep(4000);

						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr["+sTotalRecord+"]/td[3]/input")).sendKeys(sWeight);
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr["+sTotalRecord+"]/td[4]/input")).sendKeys(sCaliper);
					}
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		return sFlag;
	}



	public boolean EnterPaperTypeWeightSizeDetails(String sCode,String sBuySizeW,String sBuySizeH,String sCartonQty,String sRollDiameter,String sRollLength,String sInv) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		boolean sFlag = false;
		int rowCount =_driver.findElements(By.xpath("//table[@id='paperType']/tbody/tr")).size();
		System.out.println("rowCount is "+rowCount);
		for(int i = 1;i<=rowCount;i++)
		{

			String sEst = _driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[3]/input")).getAttribute("value");
			sEst =sEst.trim();
			System.out.println("sEst is "+sEst);
			if(sEst.equals(sCode))
			{
				_driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
				Thread.sleep(3000);
				break;
			}
		}
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Paper Type: "+sCode)) 
				{
					_driver.findElement(By.xpath("//a[text()='Weights']")).click();
					Thread.sleep(4000);

					String  originalHandle1 = _driver.getWindowHandle();
					System.out.println(originalHandle1);
					String sWindowTitle1 =_driver.getTitle();
					System.out.println(originalHandle1);
					if(_driver.findElements(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/img")).size()>0)
					{
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/img")).click();
					}
					else
					{
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/div")).click();
					}
					Thread.sleep(3000);
					Set<String> availableWindows1 = _driver.getWindowHandles();
					if (!availableWindows1.isEmpty()) 
					{
						for (String windowId1 : availableWindows1) 
						{	
							if(_driver.switchTo().window(windowId1).getTitle().contains("Paper Weight: 20")) 
							{
								String  originalHandle2= _driver.getWindowHandle();
								System.out.println(originalHandle2);
								String sWindowTitle2 =_driver.getTitle();
								System.out.println(sWindowTitle2);



								_driver.findElement(By.xpath("//fieldset[@id='PaperSize_fieldset']/div[1]/div[1]/div[2]/a[contains(text(),'Add New')]")).click();
								Thread.sleep(4000);
								Set<String> availableWindows2 = _driver.getWindowHandles();
								if (!availableWindows2.isEmpty()) 
								{
									for (String windowId2 : availableWindows2) 
									{	
										if(_driver.switchTo().window(windowId2).getTitle().equals("Adding Paper Size")) 
										{
											_driver.findElement(By.name(Locators.getProperty(Locators.Est_Buy_Size_W))).sendKeys(sBuySizeW);
											_driver.findElement(By.name(Locators.getProperty(Locators.Est_Buy_Size_H))).sendKeys(sBuySizeH);
											_driver.findElement(By.name(Locators.getProperty(Locators.PaperSize_CartonQty))).sendKeys(sCartonQty);
											_driver.findElement(By.name(Locators.getProperty(Locators.PaperSize_RollDiameter))).sendKeys(sRollDiameter);

											if(_driver.findElements(By.name(Locators.getProperty(Locators.PaperSize_RollLength))).size()>0)
											{
												_driver.findElement(By.name(Locators.getProperty(Locators.PaperSize_RollLength))).sendKeys(sRollLength);
											}
											else
											{
												_driver.findElement(By.name("rollLength")).sendKeys(sRollLength);
											}

											CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item)), sInv);
											Thread.sleep(4000);
											DC.Add();
											sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
											_driver.close();
											_driver.switchTo().window(originalHandle2).getTitle().equals(sWindowTitle2);
										}
										else
										{
											_driver.switchTo().window(originalHandle2).getTitle().equals(sWindowTitle2);
										}
									}
								}
								_driver.close();
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);			
							}
							else
							{
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
							}

						}
					}	

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		return sFlag;
	}


	public boolean EnterPaperTypeWeightCostDetails(String sCode,String sQty,String sPrice,String sUOM) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		boolean sFlag = false;
		int rowCount =_driver.findElements(By.xpath("//table[@id='paperType']/tbody/tr")).size();
		System.out.println("rowCount is "+rowCount);
		for(int i = 1;i<=rowCount;i++)
		{

			String sCodeIS = _driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[3]/input")).getAttribute("value");
			sCodeIS =sCodeIS.trim();
			System.out.println("sCodeIS is "+sCodeIS);
			if(sCodeIS.equals(sCode))
			{
				_driver.findElement(By.xpath("//table[@id='paperType']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
				Thread.sleep(3000);
				break;
			}
		}
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{	
				if(_driver.switchTo().window(windowId).getTitle().contains("Paper Type: "+sCode)) 
				{
					_driver.findElement(By.xpath("//a[text()='Weights']")).click();
					Thread.sleep(4000);

					String  originalHandle1 = _driver.getWindowHandle();
					System.out.println(originalHandle1);
					String sWindowTitle1 =_driver.getTitle();
					System.out.println(originalHandle1);
					if(_driver.findElements(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/img")).size()>0)
					{
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/img")).click();
					}
					else
					{
						_driver.findElement(By.xpath("//table[@id='PaperWeight']/tbody/tr/td[2]/div/a/div")).click();
					}
					Thread.sleep(3000);
					Set<String> availableWindows1 = _driver.getWindowHandles();
					if (!availableWindows1.isEmpty()) 
					{
						for (String windowId1 : availableWindows1) 
						{	
							if(_driver.switchTo().window(windowId1).getTitle().contains("Paper Weight: 20")) 
							{
								String  originalHandle2= _driver.getWindowHandle();
								System.out.println(originalHandle2);
								String sWindowTitle2 =_driver.getTitle();
								System.out.println(sWindowTitle2);
								if(_driver.findElements(By.xpath("//table[@id='PaperSize']/tbody/tr/td[2]/div/a/img")).size()>0)
								{
									_driver.findElement(By.xpath("//table[@id='PaperSize']/tbody/tr/td[2]/div/a/img")).click();
								}
								else
								{
									_driver.findElement(By.xpath("//table[@id='PaperSize']/tbody/tr/td[2]/div/a/div")).click();
								}
								Thread.sleep(4000);
								Set<String> availableWindows2 = _driver.getWindowHandles();
								if (!availableWindows2.isEmpty()) 
								{
									for (String windowId2 : availableWindows2) 
									{	
										if(_driver.switchTo().window(windowId2).getTitle().contains("Paper Size: ")) 
										{
											_driver.findElement(By.xpath("//a[text()='Costs']")).click();
											Thread.sleep(4000);
											_driver.findElement(By.xpath("//fieldset[@id='PaperCost_fieldset']/div[1]/div[1]/div[2]/input[1]")).click();
											Thread.sleep(4000);
											_driver.findElement(By.name(Locators.getProperty(Locators.PaperCost_Qty))).sendKeys(sQty);
											_driver.findElement(By.name(Locators.getProperty(Locators.PaperCost_Price))).sendKeys(sPrice);
											CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PaperCost_UOM)), sUOM);
											Thread.sleep(4000);
											DC.Update();
											sFlag = true;


											_driver.close();
											_driver.switchTo().window(originalHandle2).getTitle().equals(sWindowTitle2);
										}
										else
										{
											_driver.switchTo().window(originalHandle2).getTitle().equals(sWindowTitle2);
										}
									}
								}
								_driver.close();
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);			
							}
							else
							{
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);
							}

						}
					}	

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
				else
				{
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		return sFlag;
	}


	public void EnterMaterialsInkTonerDetails(String sInkType,String sPressInkType,String sDesc,String sInv,String sSetupCost,String sSetUpAmt,String sCostPerUnit,String sUOM,String sMileage,String sAC,String sWashupFactor,String sRunSpoilage,String sRunSpeedAdjustment) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.InKToner_InkType)), sInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.InKToner_PressInkType)), 1);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_SetUpCost))).sendKeys(sSetupCost);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_SetUpAmount))).sendKeys(sSetUpAmt);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_CostPerUnit))).sendKeys(sCostPerUnit);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), 1);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.InkToner_InkMileage))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.InkToner_InkMileage))).sendKeys(sMileage);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Inventory_Activity_Code)), 1);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_WashupFactor))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_WashupFactor))).sendKeys(sWashupFactor);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_RunSpoilage))).sendKeys(sRunSpoilage);
		_driver.findElement(By.name(Locators.getProperty(Locators.InKToner_RunSpeed_Adjustment))).sendKeys(sRunSpeedAdjustment);
	}
	public boolean AddNewPrepMethod(String sDesc) throws Exception
	{ 
		boolean sFlag = false;
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(4000);
		sFlag= CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		System.out.println(_driver.getCurrentUrl());
		return sFlag;

	}

	public boolean AddNewLineMaterails(String sMaterialProvider) throws Exception
	{
		boolean sFlag = false;
		_driver.findElement(By.xpath("//fieldset[@id='prepressWorkflow_fieldset']/div[1]/div[1]/div[2]/input[@name='addGridRow_prepressWorkflow']")).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name("prepressWorkflow.materialProvided"), sMaterialProvider);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(4000);
		sFlag= CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		System.out.println(_driver.getCurrentUrl());
		return sFlag;
	}

	public void AddNewPrepressEstimateExpression(String sName,String sCalcType,String sPrepressExp) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Expression_Name))).sendKeys(sName);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Expression_Calculation_Type)), sCalcType);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Expression_Expression_Prepress))).sendKeys(sPrepressExp);
		Thread.sleep(4000);
	}

	public void AddNewFinishingEstimateExpressionFromGrid(String sName,String sCalcType,String sFinishingExp) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		_driver.findElement(By.xpath("//input[@value='Add In Grid']")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[3]/input")).sendKeys(sName);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[4]/select"), sCalcType);
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[6]/textarea")).sendKeys(sFinishingExp);
	}

	public void AddNewFolderPattern(String sPage,String sPatternNum,String sDesc,String sBindingSide,String sJogSide,String sJDFType) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Page))).sendKeys(sPage);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Number))).sendKeys(sPatternNum);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Folder_Pattern_Binding_Side)), sBindingSide);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Folder_Pattern_jog_Side)), sJogSide);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Folder_Pattern_Jdftype)), sJDFType);
		Thread.sleep(4000);
		System.out.println(_driver.getCurrentUrl());
	}

	public void EnterFolderPatternSpecsDetails(String sSpineEdge,String sParallelFold,String sNumberScore,String sSetupHr,String sUnits,String sPlates,String sWidthEdge,String sFoldPass2,String sMinStock,String sMaxStock,String sAngleFold,String sAngleFoldPass2,String sNumPerfs,String sSlits,String  sGlues, boolean sMustGateFold,boolean sMustDieCut,boolean sAskPlies) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Folder_Pattern_Specs_Tab))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Pages_Spine_Edge))).sendKeys(sSpineEdge);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Parallel_Folds))).sendKeys(sParallelFold);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Number_Scores))).sendKeys(sNumberScore);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Additional_Folder_Setup_Hours))).sendKeys(sSetupHr);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Folder_Units))).sendKeys(sUnits);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Folder_Plates))).sendKeys(sPlates);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Pages_Width_Edge))).sendKeys(sWidthEdge);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Parallel_Folds_Pass2))).sendKeys(sFoldPass2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Min_Stock_Caliper))).sendKeys(sMinStock);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Max_Stock_Caliper))).sendKeys(sMaxStock);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Right_Angle_Folds))).sendKeys(sAngleFold);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Num_Right_Angle_Folds_Pass2))).sendKeys(sAngleFoldPass2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Number_Perfs))).sendKeys(sNumPerfs);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Number_Slits))).sendKeys(sSlits);
		_driver.findElement(By.name(Locators.getProperty(Locators.Folder_Pattern_Number_Glues))).sendKeys(sGlues);
		PO.sSelectCheckBox(sMustGateFold, By.name(Locators.getProperty(Locators.Folder_Pattern_Must_Gate_Fold)));
		PO.sSelectCheckBox(sMustDieCut, By.name(Locators.getProperty(Locators.Folder_Pattern_Must_Die_Cut)));
		PO.sSelectCheckBox(sAskPlies, By.name(Locators.getProperty(Locators.Folder_Pattern_Ask_Plies)));
		System.out.println(_driver.getCurrentUrl());
	}

	public void EnterFolderPatternAllowanceDetails(String sTrimSpine,String sTrimFace,String sTrimHead,String sTrimFoot,String sMarginRight,String sBleedsSpine,String sBleedsFace,String sBleedsHead,String sBleedsFoot,String sMarginBottom ,String sTabSpine,String sTabFace,String sTabHead,String sTabFoot,String snumOddPanelsSpine,String sOddPanelsSpine,String snumOddPanelsWidth,String sOddPanelsWidth,String sSpineSize,boolean sHasSpine  ) throws Exception
	{

		PurchasePage PO = new PurchasePage(_driver);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Folder_Pattern_Allowance_Tab))).click();
		Thread.sleep(4000);

		_driver.findElement(By.name(Locators.getProperty(Locators.Trim_Spine))).sendKeys(sTrimSpine);
		_driver.findElement(By.name(Locators.getProperty(Locators.Trim_Face))).sendKeys(sTrimFace);
		_driver.findElement(By.name(Locators.getProperty(Locators.Trim_Head))).sendKeys(sTrimHead);
		_driver.findElement(By.name(Locators.getProperty(Locators.Trim_Foot))).sendKeys(sTrimFoot);
		_driver.findElement(By.name(Locators.getProperty(Locators.Margin_Right))).sendKeys(sMarginRight);
		_driver.findElement(By.name(Locators.getProperty(Locators.Bleeds_Spine))).sendKeys(sBleedsSpine);
		_driver.findElement(By.name(Locators.getProperty(Locators.Bleeds_Face))).sendKeys(sBleedsFace);
		_driver.findElement(By.name(Locators.getProperty(Locators.Bleeds_Head))).sendKeys(sBleedsHead);
		_driver.findElement(By.name(Locators.getProperty(Locators.Bleeds_Foot))).sendKeys(sBleedsFoot);
		_driver.findElement(By.name(Locators.getProperty(Locators.Margin_Bottom))).sendKeys(sMarginBottom);
		_driver.findElement(By.name(Locators.getProperty(Locators.Tab_Spine))).sendKeys(sTabSpine);
		_driver.findElement(By.name(Locators.getProperty(Locators.Tab_Face))).sendKeys(sTabFace);
		_driver.findElement(By.name(Locators.getProperty(Locators.Tab_Head))).sendKeys(sTabHead);
		_driver.findElement(By.name(Locators.getProperty(Locators.Tab_Foot))).sendKeys(sTabFoot);
		_driver.findElement(By.name(Locators.getProperty(Locators.OddPanelsSpine))).sendKeys(snumOddPanelsSpine);
		_driver.findElement(By.name(Locators.getProperty(Locators.Odd_Panel_Spine))).sendKeys(sOddPanelsSpine);
		_driver.findElement(By.name(Locators.getProperty(Locators.OddPanelsWidth))).sendKeys(snumOddPanelsWidth);
		_driver.findElement(By.name(Locators.getProperty(Locators.Odd_Panel_Width))).sendKeys(sOddPanelsWidth);
		_driver.findElement(By.name(Locators.getProperty(Locators.Spine_Size))).sendKeys(sSpineSize);

		PO.sSelectCheckBox(sHasSpine, By.name(Locators.getProperty(Locators.Has_Spine)));	
	}

	public String  FetchVersion()
	{
		String sVersion = _driver.findElement(By.xpath("//div[@id='poweredby']/div[1]/div[1]")).getText();
		System.out.println("Version is "+sVersion);

		String sVer = sVersion.substring(0, Math.min(sVersion.length(), 2));

		System.out.println("Version  is  "+sVer);
		return sVer;
	}
	public void CreateJobProductType(String sID,String sDesc,String sCust,String sProductionType,String sPage,String sFoldPattern) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobProductType_ID))).sendKeys(sID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(Keys.TAB);
		Thread.sleep(4000);
		/*	String sVersion = FetchVersion();
			if(!sVersion.equals(27))
			{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_ProductionType)), sProductionType);
			Thread.sleep(4000);
			}
			else
			{*/
		//CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), sProductionType);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_ProductionType)), sProductionType);
		Thread.sleep(4000);
		//}
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name("userInterfaceSet"),1);
			Thread.sleep(4000);
		}
		_driver.findElement(By.xpath("//a[text()='Settings']")).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.JobProductType_Settings_Pages)), sPage);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobProductType_Settings_Fold_Pattern)), sFoldPattern);
		Thread.sleep(4000);


	}
	public void UpadteEstimatePressDetails2(String sSpreadSheet,String sPress,String sRunSizeW,String sRunSizeH,String sRunMethod,String sMRS,String RSS,String FMRS,String FRSS) throws Exception
	{

		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Press")) 
				{
					String sVersion=FetchVersion();
					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Details_Gripper_Allowance_Color_Bar))).size()>0)
					{
						//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Details_Gripper_Allowance_Color_Bar)), sGCB);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Press_Sheet))).sendKeys(sSpreadSheet);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys(sRunSizeW);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys(sRunSizeH);
						CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).sendKeys(sMRS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).sendKeys(RSS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).sendKeys(FMRS);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).sendKeys(FRSS);
						System.err.println("check 2");
					}
					else
					{
						//CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.gripperColorBar"), sGCB);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.pressSheets")).clear();
						_driver.findElement(By.name("estimatePresses.pressSheets")).sendKeys(sSpreadSheet);

						if(_driver.findElements(By.name("estimatePresses.runSize")).size()>0)
						{
							_driver.findElement(By.name("estimatePresses.runSize")).clear();
							_driver.findElement(By.name("estimatePresses.runSize")).sendKeys(sRunSizeW+ "x" +sRunSizeH);

						}
						else
						{	 
							_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
							_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys(sRunSizeW);
							_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
							_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys(sRunSizeH);
						}

						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.printRunMethod"), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).sendKeys(sMRS);

						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).sendKeys(RSS);

						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).sendKeys(FMRS);

						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).sendKeys(FRSS);
						System.err.println("check 3");
					}

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						System.err.println("check 4");
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();


					}
					else
					{
						System.err.println("check 6");
						int sSize1 = _driver.getWindowHandles().size();
						boolean hasQuit1 =_driver.getWindowHandles().isEmpty();
						System.out.println("sSize is "+sSize1+" hasQuit is "+hasQuit1);

						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();


					}

					Thread.sleep(7000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize == 2)
					{
						_driver.close();
					}

				}

			} 


		}

		else
		{
			Assert.fail(" Window Estimate Press not found");
		}
		System.err.println("check 8");


	}


	public void EnterSettingDetailsForJobProductType(String sPage,String sFoldPattern,String sInv,String sBuySizeW,String sBuySizeH,String sPrePressWorkflow,String sFinishingWorkflow,String sPress,String sRunSizeW,String sRunSizeH ) throws Exception
	{
		_driver.findElement(By.xpath("//a[text()='Settings']")).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.JobProductType_Settings_Pages)), sPage);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobProductType_Settings_Fold_Pattern)), sFoldPattern);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(sInv);
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		//CommonFunctions.selectDropdownByIndex(_driver, By.name("standardPaperType"), 1);
		/*
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_W))).sendKeys(sRunSizeW);
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_H))).sendKeys(sRunSizeH);
		 */
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), sPrePressWorkflow);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("itemsBindery"), sFinishingWorkflow);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("press"), sPress);
		Thread.sleep(2000);
		/*
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_W))).sendKeys(sRunSizeW);
			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Run_Size_H))).sendKeys(sRunSizeH);
		 */
	}
	public void CreateNewJobProductType(String sID,String sDesc,String sCust,String sProductionType,String sComponentType,String sSaleCat,String sDiffculty) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobProductType_ID))).sendKeys(sID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCust);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_ProductionType)), sProductionType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_ComponentType)), sComponentType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Sales_Category)), sSaleCat);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Diffculty)), sDiffculty);
		Thread.sleep(4000);
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{

			CommonFunctions.selectDropdownByIndex(_driver, By.name("userInterfaceSet"), 1);
			Thread.sleep(1000);
		}

	}
	public void AddNewEstimateProductCategory(String sProdcutCategory) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		rowcount = rowcount+1;
		_driver.findElement(By.xpath("//input[@name='addGridRow_appbox_implicit']")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+rowcount+"]/td[3]/input")).sendKeys(sProdcutCategory);

	}

	public void AddProductTypeProductCategory(String sProductCategory, String sProductType,String sCompositeProduct) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);

		DCPage DC = new DCPage(_driver);
		DC.Search(sProductCategory, "description");
		Thread.sleep(3000);
		int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("rowCount is "+rowCount);
		for(int i = 1;i<=rowCount;i++)
		{

			String sEst = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/input")).getAttribute("value");

			sEst =sEst.trim();
			System.out.println("sEst is "+sEst);
			if(sEst.equals(sProductCategory))
			{
				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
				break;
			}
		}


		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Product Category")) 
				{
					_driver.findElement(By.xpath("//fieldset[@id='EstimateProductCategoryJobProductType_fieldset']/div[1]/div[1]/div[2]/input")).click();
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimateProductCategoryJobProductType.jobProductType"), sProductType);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimateProductCategoryJobProductType.compositeProduct"), sCompositeProduct);
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
	}

	public void DeleteProductTypeProductCategory(String sProductCategory) throws Exception
	{

		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		for(int i = 1;i<=rowcount;i++)
		{
			int j = i+0;
			String sCat = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[3]/input")).getAttribute("value");
			sCat =sCat.trim();
			System.out.println("sCat is "+sCat);
			if(sCat.equals(sProductCategory))
			{
				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[2]/div/a/img")).click();
				break;
			}
		}

		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Product Category")) 
				{
					_driver.findElement(By.name("EstimateProductCategoryJobProductType.deleteBooleanCheck")).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
	}


	public void EditEstimateInkDefaults() throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//table[@id='estimatePress3']/tbody/tr/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Ink Colors")) 
				{
					_driver.findElement(By.name("estimatePresses.colorsSide1")).clear();
					_driver.findElement(By.name("estimatePresses.colorsSide1")).sendKeys("4");
					_driver.findElement(By.name("estimatePresses.colorsSide2")).clear();
					_driver.findElement(By.name("estimatePresses.colorsSide2")).sendKeys("0");
					_driver.findElement(By.name("estimatePresses.colorsTotal")).clear();
					_driver.findElement(By.name("estimatePresses.colorsTotal")).sendKeys("4");

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

					Thread.sleep(4000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize==2)
					{
						_driver.close();
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
	}
	public void DeleteProductCategory(String sProductCategory) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		DC.SearchValue(sProductCategory, "description");
		Thread.sleep(3000);

		for(int i = 1;i<=rowcount;i++)
		{
			int j = i+0;
			String sCat = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[3]/input")).getAttribute("value");
			sCat =sCat.trim();
			System.out.println("sCat is "+sCat);
			if(sCat.equals(sProductCategory))
			{
				_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+j+"]/td[2]/div/a/img")).click();
				break;
			}
		}

		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Product Category")) 
				{
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);

					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
	}
	public boolean AddProductTypeAndCompositeProductToProductCategory(String sProductCategory, String sProductType,String sCompositeProduct) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		boolean sFlag = false;
		int rowcount = _driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
		System.out.println("Rowcount is "+rowcount);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);

		DC.SearchValue(sProductCategory, "description");
		Thread.sleep(4000);
		if(_driver.getTitle().equals("Estimate Product Category"))
		{
			System.out.println("Navigated to Estimate Product Category");
		}
		else
		{
			int rowCount =_driver.findElements(By.xpath("//table[@id='appbox_implicit']/tbody/tr")).size();
			System.out.println("rowCount is "+rowCount);
			for(int i = 1;i<=rowCount;i++)
			{

				String sEst = _driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[3]/input")).getAttribute("value");
				sEst =sEst.trim();
				System.out.println("sEst is "+sEst);
				if(sEst.equals(sProductCategory))
				{
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr["+i+"]/td[2]/div/a/img")).click();
					break;
				}
			}
		}



		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Product Category")) 
				{
					_driver.findElement(By.xpath("//fieldset[@id='EstimateProductCategoryJobProductType_fieldset']/div[1]/div[1]/div[2]/input")).click();
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimateProductCategoryJobProductType.jobProductType"), sProductType);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimateProductCategoryJobProductType.compositeProduct"), sCompositeProduct);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(4000);
					sFlag =	  CommonFunctions.isElementPresent(_driver, By.xpath("//ul/li[text()='You may only supply either a composite product or a job product type.']"));
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
		return sFlag;
	}
	public boolean AddNewFunction(String sDesc) throws Exception
	{ 
		boolean sFlag = false;
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		Thread.sleep(4000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		Thread.sleep(4000);
		sFlag= CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return sFlag;
	}


	public void AddNewInkDefault(String sDesc,String sS1,String sS2,String sTotal,String sInkType,String sCosting,String sVanish) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys(sS1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys(sS2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys(sTotal);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.InKToner_InkType)), sInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Costing)), sInkType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Vanish)), sInkType);
		Thread.sleep(4000);	
	}

	public void AddNewPrepressItem(String sCode , String sDesc,String sLabor,String sMaterials,String sPrepressType,String sPrepressGroup,String sUnitLabel,String sMinColor,String sMaxColor,boolean sUseSize) throws Exception
	{
		_driver.findElement(By.xpath("//a[contains(text(),'Add New')]")).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Code))).sendKeys(sCode);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Activity_Code_Labor)), sLabor);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Activity_Code_Materials)), sMaterials);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Prepress_Type)), sPrepressType);
		Thread.sleep(4000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Prepress_Group)), sPrepressGroup);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Prepress_Unit_Label))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Prepress_Unit_Label))).sendKeys(sUnitLabel);
		_driver.findElement(By.name(Locators.getProperty(Locators.Min_Colors))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Min_Colors))).sendKeys(sMinColor);
		_driver.findElement(By.name(Locators.getProperty(Locators.Max_Colors))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Max_Colors))).sendKeys(sMaxColor);
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sUseSize, By.name(Locators.getProperty(Locators.Use_Sizes_Chechbox)));
	}

	public boolean AddPrepressCost(String sQtyUpTo, String sSetUpHours,String sHours,String sMaterialCost ) throws Exception
	{
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//table[@id='prepressSize']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Prepress Size")) 
				{
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Prepress_Cost))).click();
					Thread.sleep(4000);
					_driver.findElement(By.name(Locators.getProperty(Locators.Qty_Up_To))).sendKeys(sQtyUpTo);
					_driver.findElement(By.name(Locators.getProperty(Locators.Set_Up_Hours))).sendKeys(sSetUpHours);
					_driver.findElement(By.name(Locators.getProperty(Locators.Hours))).sendKeys(sHours);
					_driver.findElement(By.name(Locators.getProperty(Locators.MaterialCost))).sendKeys(sMaterialCost);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
		return sFlag;
	}


	public void AddPrepressCostWhenUseSizesisDisabled(String sQtyUpTo, String sSetUpHours,String sHours,String sMaterialCost ) throws Exception
	{
		boolean sFlag = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//fieldset[@id='prepressCosts_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(4000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Prepress Cost")) 
				{

					_driver.findElement(By.name("quantity")).sendKeys(sQtyUpTo);
					_driver.findElement(By.name("setupHours")).sendKeys(sSetUpHours);
					_driver.findElement(By.name("hours")).sendKeys(sHours);
					_driver.findElement(By.name("materialCost")).sendKeys(sMaterialCost);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}

	}


	public void EnterFinishingOperationDetails(String sCode,String sRunCost,String sCategory,String sOperType,String sFinishingGroup ,String sDesc,String sNote,String sNumUp,String sUnitLabel,String sNumHelpers,String sMaterialAC,String sMakeReadyAC,String sRunAC,String sHelperAC,String sQtyMultiplier ) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Code))).sendKeys(sCode);
		_driver.findElement(By.name(Locators.getProperty(Locators.Minimum_Run_Cost))).sendKeys(sRunCost);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCategory);
		Thread.sleep(4000);
		if(!sCategory.equals("Shipping"))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Operation_Type)), sOperType);
			Thread.sleep(4000);
		}
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Finishing_Group)), 1);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Note))).sendKeys(sNote);
		_driver.findElement(By.name(Locators.getProperty(Locators.NumUp))).sendKeys(sNumUp);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Unit_Label))).sendKeys(sUnitLabel);
		_driver.findElement(By.name(Locators.getProperty(Locators.Number_Helpers))).sendKeys(sNumHelpers);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Materials_Activity_Code)), sMaterialAC);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Make_Ready_AC)), sMakeReadyAC);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Press_Speed_Run_Activity_Code)), sRunAC);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Press_Helper_Activity_Code)), sHelperAC);
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Quantity_Multiplier)), sQtyMultiplier);
		Thread.sleep(4000);

	}
	public void EnterFinishingOperationMachineSelectionDetails(String sCutter,String sFolder,String sBinder,String sThreeKnife,String sPadding) throws Exception
	{
		System.err.println("**************COMMENTING THE MACHINE SELECTION OPTIONS********************************");
		/*_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
			 Thread.sleep(4000);
			 CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Cutter)), sCutter);
			 Thread.sleep(4000);
			 CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Folder)), sFolder);
			 Thread.sleep(4000);
			 CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Binder)), sBinder);
			 Thread.sleep(4000);
			 CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Three_Knife)), sThreeKnife);
			 Thread.sleep(4000);
			 CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Padding)), sPadding);
			 Thread.sleep(4000);
		 */
	}
	public void EnterFinishingOperationMachinefolder() throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name("machineCategory"), "Folder");
	}
	public void EnterFinishingOperationMachineSelectionFolderDetails(String sFolder) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
		Thread.sleep(4000);

		//System.err.println("**************COMMENTING THE MACHINE SELECTION OPTIONS********************************");

		if(_driver.findElements(By.name("machineCategory")).size()>0)
		{
			//System.err.println("error in method EnterFinishingOperationMachineSelectionFolderDetails");
			CommonFunctions.selectDropdownByText(_driver, By.name("machineCategory"), "Folder");
			Thread.sleep(4000);
			CommonFunctions.selectDropdownByText(_driver, By.name("machineType"), "Folder");
			Thread.sleep(4000);
			CommonFunctions.selectDropdownByText(_driver, By.name("folder"), sFolder);

		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Folder)), sFolder);
		}


		Thread.sleep(4000);

	}
	public void EnterFinishingOperationMachineSelectionBinderDetails(String sBinder) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
		Thread.sleep(4000);


		CommonFunctions.selectDropdownByText(_driver, By.name("machineCategory"), "Binder");
		Thread.sleep(4000);
		CommonFunctions.selectDropdownByText(_driver, By.name("binder"), sBinder);
		Thread.sleep(4000);

	}

	public void EnterFinishingOperationMachineSelectionPaddingDetails(String sPadding) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
		Thread.sleep(4000);

		CommonFunctions.selectDropdownByText(_driver, By.name("machineCategory"), "Padding");
		Thread.sleep(4000);
		System.err.println("sPadding is: " +sPadding);
		CommonFunctions.selectDropdownByText(_driver, By.name("padding"), sPadding);
		Thread.sleep(4000);

		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Padding)), sPadding);
		//Thread.sleep(4000);

	}
	public void EnterFinishingOperationMachineSelectionKnifeDetails(String sPadding) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Machine_Selection))).click();
		Thread.sleep(4000);

		CommonFunctions.selectDropdownByText(_driver, By.name("machineCategory"), "ThreeKnife");
		Thread.sleep(4000);
		System.err.println("sPadding is: " +sPadding);
		CommonFunctions.selectDropdownByText(_driver, By.name("threeKnife"), sPadding);
		Thread.sleep(4000);
		//CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Machine_Selection_Three_Knife)), sPadding);
		//Thread.sleep(4000);

	}
	public void EnterFinishingOperationSpecificationDetails(String sSetupMatCost,String sCostPerM,String sMRSpoilage,String sSetupTime,String sQty,String sSetupPerSig,String sRoundingMode,String sCalcMethod) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Specification))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Setup_Material_Cost))).sendKeys(sSetupMatCost);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Material_Cost_PerM))).sendKeys(sCostPerM);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Make_Ready_Spoilage))).sendKeys(sMRSpoilage);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Setup_Time))).sendKeys(sSetupTime);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Quantity))).sendKeys(sQty);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Setup_Per_Signature))).sendKeys(sSetupPerSig);
		Thread.sleep(4000);
		System.err.println("sRoundingMode is: "+sRoundingMode);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Specification_Rounding_Mode)), sRoundingMode);
		Thread.sleep(4000);
		System.err.println("sCalcMethod is: "+sCalcMethod);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Specification_Quantity_Calc_Method)), sCalcMethod);
		//CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Specification_Quantity_Calc_Method)), 1);
		Thread.sleep(4000);

	}

	public void EnterFinishingOperationOtherSettingsDetails(String sStackAmount,String sCoverFoldWidth,String sNumberingStart,boolean sRotateStack) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Finishing_operation_Other_Settings))).click();
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Other_Settings_Stack_Amount))).sendKeys(sStackAmount);
		_driver.findElement(By.name(Locators.getProperty(Locators.Other_Settings_Cover_Fold_Width))).sendKeys(sCoverFoldWidth);
		_driver.findElement(By.name(Locators.getProperty(Locators.Other_Settings_Numbering_Start))).sendKeys(sNumberingStart);
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sRotateStack, By.name(Locators.getProperty(Locators.Other_Settings_Rotate_Stack)));
	}
	public String VerifyFinishingShippingOperation() throws Exception
	{
		String sFinishingOperation="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//div/h4[contains(label,'Shipping Workflow')]/following-sibling::div/a/div")).click();
		Thread.sleep(4000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Shipping and Mailing Workflow")) 
				{

					sFinishingOperation =	 CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='shippingWorkflowFinishingOp']/tbody/tr[1]/td[4]/select"));
					Thread.sleep(4000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
		return sFinishingOperation;	
	}	 

	public String VerifyFinishingOperation() throws Exception
	{
		String sFinishingOperation="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle =_driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//div/h4[contains(label,'Finishing Workflow')]/following-sibling::div/a/div")).click();
		Thread.sleep(4000);
		Set<String> availableWindows1 = _driver.getWindowHandles();
		if (!availableWindows1.isEmpty()) 
		{
			for (String windowId1 : availableWindows1) 
			{	
				if(_driver.switchTo().window(windowId1).getTitle().equals("Finishing Workflow")) 
				{

					sFinishingOperation =	 CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='bindingMethodFinishingOp']/tbody/tr[1]/td[4]/select"));
					Thread.sleep(4000);
					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				}
			}
		}
		else
		{
			_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
		}
		return sFinishingOperation;	
	}	 


	public void EnterCutterSetupDetails(String sDesc,String sMaxHeight,String sMaxWeight,String sSetupHr,String sSetupPerCut,String sLiftTime,String sCutTime, String  sMRSpoilage,String sSpoilagePercent,boolean sMinCuts) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Max_Height))).sendKeys(sMaxHeight);
		_driver.findElement(By.name(Locators.getProperty(Locators.Max_Weight))).sendKeys(sMaxWeight);
		_driver.findElement(By.name(Locators.getProperty(Locators.Base_Setup_hours))).sendKeys(sSetupHr);
		_driver.findElement(By.name(Locators.getProperty(Locators.Additional_Setup_Per_Cut))).sendKeys(sSetupPerCut);
		_driver.findElement(By.name(Locators.getProperty(Locators.Build_Lift_Time))).sendKeys(sLiftTime);
		_driver.findElement(By.name(Locators.getProperty(Locators.Per_Cut_Time))).sendKeys(sCutTime);
		_driver.findElement(By.name(Locators.getProperty(Locators.Specification_Make_Ready_Spoilage))).sendKeys(sMRSpoilage);
		_driver.findElement(By.name(Locators.getProperty(Locators.Run_Spoilage_Percent))).sendKeys(sSpoilagePercent);
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sMinCuts, By.name(Locators.getProperty(Locators.Min_Cuts_Checkbox)));
	}

	public void EnterBinderSetupDetails(String sDesc,String sNote,String sNumPockets,String sPocketSetup,String sBaseMakeReady,String sPocketMakeReady,String sBaseSetup,boolean sCoverFeeder) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Note))).sendKeys(sNote);
		_driver.findElement(By.name(Locators.getProperty(Locators.NumPockets))).sendKeys(sNumPockets);
		_driver.findElement(By.name(Locators.getProperty(Locators.PocketSetup))).sendKeys(sPocketSetup);
		_driver.findElement(By.name(Locators.getProperty(Locators.PocketMakeReady))).sendKeys(sPocketMakeReady);
		_driver.findElement(By.name(Locators.getProperty(Locators.BaseMakeReady))).sendKeys(sBaseMakeReady);
		_driver.findElement(By.name(Locators.getProperty(Locators.BaseSetup))).sendKeys(sBaseSetup);
		PurchasePage PO = new PurchasePage(_driver);
		PO.sSelectCheckBox(sCoverFeeder, By.name(Locators.getProperty(Locators.Cover_Feeder)));
	}


	public void EnterPaddingSetupDetails(String sDesc,String sMinFinalSize,String sMaxFinalSize,String sMRHours,String sPadSpeedPerHour,String sMPad,String sLiftHeight) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Min_Bound_Edge_FinalSize))).sendKeys(sMinFinalSize);
		_driver.findElement(By.name(Locators.getProperty(Locators.Max_Bound_Edge_FinalSize))).sendKeys(sMaxFinalSize);
		_driver.findElement(By.name(Locators.getProperty(Locators.Make_Ready_Hours))).sendKeys(sMRHours);
		_driver.findElement(By.name(Locators.getProperty(Locators.Pad_Speed_Per_Hour))).sendKeys(sPadSpeedPerHour);
		_driver.findElement(By.name(Locators.getProperty(Locators.Material_Cost_Per_MPads))).sendKeys(sMPad);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Lift_Height))).sendKeys(sLiftHeight);

	}
	public void EnterKnifeSetupDetails(String sDesc,String sMinFinalSize,String sLiftHeight,String sMRHours,String sLiftsPerHour) throws Exception
	{
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.name(Locators.getProperty(Locators.Min_Bound_Edge_FinalSize))).sendKeys(sMinFinalSize);
		_driver.findElement(By.name(Locators.getProperty(Locators.Press_Lift_Height))).sendKeys(sLiftHeight);
		_driver.findElement(By.name(Locators.getProperty(Locators.Make_Ready_Hours))).sendKeys(sMRHours);
		_driver.findElement(By.name(Locators.getProperty(Locators.Lifts_Per_Hour))).sendKeys(sLiftsPerHour);

	}

	public boolean PleaseSelectInventoryItem(String sSearch,String sVendor,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle1 = _driver.getWindowHandle();
		System.out.println(originalHandle1);
		String  sWindowTitle1= _driver.getTitle();
		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Please select an Inventory Item")) 
				{
					System.out.println("Search  Iventory Item");
					DC.SearchValue(sVendor,sSearch);
					Thread.sleep(3000);
					_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr[1]/td[2]/a/div")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);	
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);

				}

			}
		}
		return sFlag;
	}

	/*	 
		 public boolean PleaseSelectCorrectInventoryItem(String sSearch,String sInv,String sloc) throws Exception
		 {
		 	boolean sFlag = false;
		 	DCPage DC = new DCPage(_driver);
		 	String  originalHandle = _driver.getWindowHandle();
		 	 System.out.println(originalHandle);
		 	 String  sWindowTitle= _driver.getTitle();
		 	 _driver.findElement(By.xpath(sloc)).click();
		 	 Thread.sleep(3000);
		 	Set<String> availableWindows = _driver.getWindowHandles();
		 	if (!availableWindows.isEmpty()) 
		 	{
		 		for (String windowId : availableWindows) 
		 		{
		 			if(_driver.switchTo().window(windowId).getTitle().contains("Please select an Inventory Item")) 
		 			{
		 				System.out.println("Search  Iventory Item");
		 				DC.SearchValue(sInv,sSearch);
		 				Thread.sleep(3000);

		 				int rowcount = _driver.findElements(By.xpath("//table[@id='activeItems']/tbody/tr")).size();
		 				System.out.println("rowcount  is "+rowcount);
		 			for(int i = 1;i<=rowcount;i++)
		 			{
		 				String sINVentory =_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[3]/div")).getText();
		 				sINVentory=sINVentory.trim();
		 				System.out.println("sINVentory is "+sINVentory);
		 				if(sINVentory.equals(sInv))
		 				{
		 					_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[2]/a/div")).click();
		 					Thread.sleep(4000);
		 				}
		 			}
		 				Thread.sleep(4000);
		 				_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
		 			} 
		 			else 
		 			{
		 				//System.out.println("Not able to find window");
		 				_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

		 			}

		 		}
		 	}
		 return sFlag;
		 }*/

	public boolean PleaseSelectCorrectInventoryItem(String sSearch,String sInv,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();

		//CommonFunctions.selectDropdownByText(_driver, By.xpath("//div/h4[contains(label,'Material Type')]/following-sibling::div/select"), "Inventory Item");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Material_Type)), "Inventory Item");
		Thread.sleep(2000);
		String sInventoryItemIs = CommonFunctions.GetSelectedOption(_driver, By.name("inventoryItem2"));
		sInventoryItemIs=sInventoryItemIs.trim();
		System.out.println("Selected inventory item is "+sInventoryItemIs);
		// _driver.findElement(By.xpath("//div/h4[contains(label,'Inventory Item')]/following-sibling::div/select")).getAttribute("value");

		//		 if(sInventoryItemIs.equals(""))
		//		 {
		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(4000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Please select an Inventory Item")) 
				{
					System.out.println("Search  Iventory Item");
					DC.SearchValue(sInv,sSearch);
					Thread.sleep(4000);

					int rowcount = _driver.findElements(By.xpath("//table[@id='activeItems']/tbody/tr")).size();
					System.out.println("rowcount  is "+rowcount);
					for(int i = 1;i<=rowcount;i++)
					{
						String sINVentory =_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[3]/div")).getText();
						sINVentory=sINVentory.trim();
						System.out.println("sINVentory is "+sINVentory);
						if(sINVentory.equals(sInv))
						{
							_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[2]/a/div")).click();
							Thread.sleep(4000);
						}
					}		 				
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					Thread.sleep(2000);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		//		 }
		//		 else
		//		 {
		//			 System.out.println("inventory item already selected");
		//		 }
		return sFlag;
	}

	public boolean PleaseSelectCorrectInventoryItem1(String sSearch,String sInv,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();

		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Please select an Inventory Item")) 
				{
					System.out.println("Search  Iventory Item");
					DC.SearchValue(sInv,sSearch);
					Thread.sleep(3000);

					int rowcount = _driver.findElements(By.xpath("//table[@id='activeItems']/tbody/tr")).size();
					System.out.println("rowcount  is "+rowcount);
					for(int i = 1;i<=rowcount;i++)
					{
						String sINVentory =_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[3]/div")).getText();
						sINVentory=sINVentory.trim();
						System.out.println("sINVentory is "+sINVentory);
						if(sINVentory.equals(sInv))
						{
							_driver.findElement(By.xpath("//table[@id='activeItems']/tbody/tr["+i+"]/td[2]/a/div")).click();
							Thread.sleep(4000);
						}
					}
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

		return sFlag;
	}
	public boolean PleaseSelectPaperType(String sSearch,String sVendor,String sloc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(sloc)).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Paper Types")) 
				{
					System.out.println("Search  Iventory Item");
					DC.SearchValue(sVendor,sSearch);
					Thread.sleep(4000);
					_driver.findElement(By.xpath("//table[@id='appbox_implicit']/tbody/tr[1]/td[2]/a/div")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);			
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag;
	}



	public void  ImportPartEstimate(String sID,String sEst,String sProduct,boolean sUnforce) throws Exception
	{
		String sPart="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.findElement(By.xpath("//div[@id='contextBar']/span/a[2]")).click();
		WebElement element =	 _driver.findElement(By.xpath("//a[text()='- Import Part']"));
		JavascriptExecutor executor = (JavascriptExecutor)_driver;
		executor.executeScript("arguments[0].click();", element);


		//_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Estimate/importPart/"+sID);	
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Import Estimate Part")) 
				{

					_driver.findElement(By.name("estimate")).sendKeys(sEst);
					Thread.sleep(4000);
					_driver.findElement(By.name("estimatePart")).sendKeys(Keys.TAB);
					CommonFunctions.selectDropdownByText(_driver, By.name("estimatePart"), sProduct);
					Thread.sleep(4000);
					PurchasePage PO = new PurchasePage(_driver);
					PO.sSelectCheckBox(sUnforce, By.name("unforceFieldsBooleanCheck"));
					CommonFunctions.selectDropdownByText(_driver, By.name("estimatePart"), sProduct);
					Thread.sleep(4000);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Import_part))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public void NavigateToAddContact() throws Exception
	{
		sSERVER =  _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Contact/add");	
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Emp_FirstName)));
		assertEquals("Adding Contact", _driver.getTitle());
	}

	public void CreateContact(String sFN,String sLN,String sCompany,String sDept,String sPhone ,String sAdd1,String sAdd2,String sAdd3,String sCity,String sCustomer ) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		NavigateToAddContact();

		CommonFunctions.sSelectCheckBox(_driver, false, By.name("autoUpdateBooleanCheck"));

		_driver.findElement(By.name(Locators.getProperty(Locators.Customer))).sendKeys(sCustomer);		

		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_FirstName))).sendKeys(sFN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_LastName))).sendKeys(sLN);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Company))).sendKeys(sCompany);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Department))).sendKeys(sDept);
		_driver.findElement(By.name(Locators.getProperty(Locators.Contact_Phone_Number))).sendKeys(sPhone);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add1))).sendKeys(sAdd1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add2))).sendKeys(sAdd2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_Add3))).sendKeys(sAdd3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Emp_city))).sendKeys(sCity);

		_driver.findElement(By.xpath("//a[text()='Tax and Shipping Info']")).click();
		Thread.sleep(1000);
		if(_driver.findElements(By.name(Locators.getProperty(Locators.Vendor_Default_Currency))).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Vendor_Default_Currency)), "USD");
			Thread.sleep(1000);
		}
		_driver.findElement(By.name("salesTax")).clear();
		_driver.findElement(By.name("salesTax")).sendKeys("CA0000");

		DC.Add();
	}

	public void CreateCustomer(String sCustID,String sCustName,String sFName,String sLName,String sAdd1,String sAdd2,String sAdd3,String sPhoneNo,String sCity,int sSalePer,int sSaleCat) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Id))).sendKeys(sCustID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Name))).sendKeys(sCustName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_FirstName))).sendKeys(sFName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_LastName))).sendKeys(sLName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add1))).sendKeys(sAdd1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(sAdd2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(sAdd3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Phone))).sendKeys(sPhoneNo);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_City))).sendKeys(sCity);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_SalesPerson)), sSalePer);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Tax_Info))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_Sale_Category)), sSaleCat);
		Thread.sleep(1000);
	}

	public void CreateCustomer(String sCustID,String sCustName,String sFName,String sLName,String sAdd1,String sAdd2,String sAdd3,String sPhoneNo,String sCity,int sSalePer,int sSaleCat,String SalesTaxCode,String TaxableCode) throws Exception
	{

		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Id))).sendKeys(sCustID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Name))).sendKeys(sCustName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_FirstName))).sendKeys(sFName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_LastName))).sendKeys(sLName);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add1))).sendKeys(sAdd1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add2))).sendKeys(sAdd2);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Add3))).sendKeys(sAdd3);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_Phone))).sendKeys(sPhoneNo);
		_driver.findElement(By.name(Locators.getProperty(Locators.Customer_City))).sendKeys(sCity);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_SalesPerson)), sSalePer);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_Tax_Info))).click();
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Customer_Sale_Category)), sSaleCat);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByValue(_driver, By.name("salesTax"), SalesTaxCode);
		CommonFunctions.selectDropdownByValue(_driver, By.name("taxableCode"), TaxableCode);


	}
	public void  SelectCustomer(String sCust) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Customer_PickUp))).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Please select a Customer")) 
				{

					DC.SearchValue(sCust, "id");
					Thread.sleep(4000);
					_driver.findElement(By.xpath("//table[@id='Customer']/tbody/tr/td[2]/a/div")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public boolean VerifyCustomerDetailsInEstimatePage(String sCustID,String sSalesPerson,String sContact,String sName)
	{
		boolean sFlag = false;
		String sCustomerID =  _driver.findElement(By.name("customer")).getAttribute("value");
		sCustomerID=sCustomerID.trim();

		String sGetSelectedSalePerson = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Customer_SalesPerson)));
		sGetSelectedSalePerson=sGetSelectedSalePerson.trim();

		String sGetSelectedContact = CommonFunctions.GetSelectedOption(_driver, By.name("contact"));
		sGetSelectedContact=sGetSelectedContact.trim();

		String sGetName =  _driver.findElement(By.name("prospectName")).getAttribute("value");
		sGetName=sGetName.trim();

		if(sCustomerID.equals(sCustID) && sGetSelectedSalePerson.equals(sSalesPerson) &&  sGetName.equals(sName) && sGetSelectedContact.equals(sContact)  )
		{
			System.out.println("Customer details Verified");
			sFlag = true;
		}
		else
		{
			System.out.println("sCustomerID is "+sCustID+" sSalePerson is "+sSalesPerson+" sContact is "+sContact+" sGetName is"+sName);
			System.out.println("sCustomerID is "+sCustomerID+" sGetSelectedSalePerson is "+sGetSelectedSalePerson+" sGetSelectedContact is "+sGetSelectedContact+" sGetName is"+sGetName);
			//	 Assert.fail("sCustomerID is "+sCustomerID+" sGetSelectedSalePerson is "+sGetSelectedSalePerson+" sGetSelectedContact is "+sGetSelectedContact+" sGetName is"+sGetName);
		}
		return sFlag;
	}

	public void  ModifyEstimateProduct(String sDescription) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//div/h4[contains(label,'Estimate Product')]/following-sibling::div/a/div")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Product")) 
				{
					//_driver.findElement(By.xpath("//table[@id='estimateParts']/tbody/tr/td[4]/input")).click();
					Thread.sleep(4000);

					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDescription);
					Thread.sleep(4000);
					DC.Update();
					_driver.close();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}


	public void  AddOtherPartToEstimateProduct(String sEstProd) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();

		System.out.println("Click on Add other parts tab");
		_driver.findElement(By.xpath("//fieldset[@id='estimateParts_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);


		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Modify Product Parts for Estimate")) 
				{

					System.out.println("Click on Estimate Product");
					String  originalHandle1 = _driver.getWindowHandle();
					System.out.println(originalHandle1);
					String  sWindowTitle1= _driver.getTitle();
					_driver.findElement(By.xpath("//table[@id='estimateParts']/tbody/tr/td[3]/div/a/div")).click();
					Thread.sleep(3000);
					Set<String> availableWindows1 = _driver.getWindowHandles();
					if (!availableWindows1.isEmpty()) 
					{
						for (String windowId1 : availableWindows1) 
						{
							if(_driver.switchTo().window(windowId1).getTitle().equals("Estimate Product")) 
							{
								System.out.println("Select Product");
								CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='estimateParts']/tbody/tr[1]/td[4]/select"), "p1-Description");
								Thread.sleep(4000);
								DC.Update();
								_driver.close();
								Thread.sleep(4000);
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);

							} 
							else 
							{
								//System.out.println("Not able to find window");
								_driver.switchTo().window(originalHandle1).getTitle().equals(sWindowTitle1);

							}

						}
					}
					Thread.sleep(4000);
					DC.Update();
					_driver.close();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		else
		{
			System.out.println("No Window present");
		}

	}
	public String  ModifyPaperInformation() throws Exception
	{
		String sDesc =  "";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePartsInkPaper']/tbody/tr[2]/td[15]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Paper")) 
				{
					PleaseSelectInventoryItem("id","","//table[@id='estimatePapers']/tbody/tr[5]/td/div/div[1]");
					Thread.sleep(4000);
					sDesc = _driver.findElement(By.name("estimatePapers.description")).getAttribute("value");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();

					//	_driver.close();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDesc;
	}
	public String  AddPrintingMaterials(String sMaterialType,String InventoryItem,String sCurrency) throws Exception
	{
		String sDesc =  "";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimatePaper_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Adding Estimate Paper")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name("estimatePapers.materialType"), sMaterialType);
					Thread.sleep(4000);
					PleaseSelectInventoryItem("id",InventoryItem,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");
					//	PleaseSelectCorrectInventoryItem("id",InventoryItem,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");
					if(_driver.findElements(By.name("estimatePapers.altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePapers.altCurrency"), sCurrency);
						Thread.sleep(4000);
					}
					sDesc=	_driver.findElement(By.name("estimatePapers.description")).getAttribute("value");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDesc;
	}
	public void  AddFinishingMaterials(String sQty) throws Exception
	{
		int j=0,k=0;
		String sDesc =  "";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateMaterials_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Adding Estimate Material")) 
				{

					_driver.findElement(By.name("EstimateMaterials.quantity")).sendKeys(sQty);

					int rowcount =_driver.findElements(By.xpath("//table[@id='EstimateMaterials']/tbody/tr")).size();
					System.out.println("rowcount is "+rowcount);
					for(int i =1;i<=rowcount;i++)
					{

						String sLabel = _driver.findElement(By.xpath("//table[@id='EstimateMaterials']/tbody/tr["+i+"]/th/a/span")).getText();
						sLabel=sLabel.trim();
						System.out.println("sLabel is "+sLabel);
						if(sLabel.equals("Inventory Item"))
						{
							j=i;
							break;
						}
					}
					/*
				 				for(int i =1;i<=rowcount;i++)
				 				{

				 					String sLabel = _driver.findElement(By.xpath("//table[@id='EstimateMaterials']/tbody/tr["+i+"]/th/a/span")).getText();
				 					sLabel=sLabel.trim();
				 					System.out.println("sLabel is "+sLabel);
				 					if(sLabel.equals("Paper Type"))
				 					{
				 						k=i;
				 						break;
				 					}
				 				}*/

					PleaseSelectInventoryItem("id","","//table[@id='EstimateMaterials']/tbody/tr["+j+"]/td/div[1]/div[1]");
					//PleaseSelectPaperType("code","","//table[@id='EstimateMaterials']/tbody/tr["+k+"]/td/div[2]/div[1]");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}


	public void  ModifyPrintingMaterials(String sBuySizeW,String sBuySizeH,String sInv) throws Exception
	{

		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePaper']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Paper")) 
				{
					PleaseSelectInventoryItem("id",sInv,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");

					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).sendKeys(sBuySizeW);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).sendKeys(sBuySizeH);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(5000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					CommonFunctions.waitForPageLoaded(_driver);
					System.out.println("Modified the Priting Material");
					PurchasePage PO = new PurchasePage(_driver);
					boolean sAlert = PO.isAlertPresent();
					if(sAlert == true)
					{
						System.err.println("Alert in ModifyPrintingMaterials");
						_driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}	 	
	}

	public void  ModifyPrintingMaterialsAndUpdateNext(String sBuySizeW,String sBuySizeH,String sInv) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePaper']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Paper")) 
				{
					PleaseSelectInventoryItem("id",sInv,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).sendKeys(sBuySizeW);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).sendKeys(sBuySizeH);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_Next))).click();
					boolean sAlert = PO.isAlertPresent();
					if(sAlert == true)
					{
						_driver.switchTo().alert().accept();
					}
					Thread.sleep(3000);
					PleaseSelectInventoryItem("id",sInv,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_W))).sendKeys(sBuySizeW);
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).clear();
					_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Materails_Estimate_Paper_Buy_Size_H))).sendKeys(sBuySizeH);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(5000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					CommonFunctions.waitForPageLoaded(_driver);
					sAlert = PO.isAlertPresent();
					if(sAlert == true)
					{
						System.err.println("Alert in ModifyPrintingMaterialsAndUpdateNext");
						_driver.switchTo().alert().accept();
					}
					Thread.sleep(1000);
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}
				Thread.sleep(4000);
				//System.err.println("**CHECK 1**");
			}
			Thread.sleep(4000);
			//System.err.println("**CHECK 2**");
		}
		Thread.sleep(4000);
		//System.err.println("**CHECK 3**");

	}


	public boolean  DuplicateEstimatePaper() throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePaper']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Paper")) 
				{

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
					Thread.sleep(4000);
					String sDuplicated = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();sDuplicated=sDuplicated.trim();
					String sItemDuplicated = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[2]")).getText();sItemDuplicated=sItemDuplicated.trim();

					_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					CommonFunctions.waitForPageLoaded(_driver);
					if(sDuplicated.equals("Duplicated") && sItemDuplicated.equals("Item duplicated."))
					{
						sFlag= true;	
					}
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}

	public boolean  DuplicateFinishingMaterails(String sQty) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateMaterials']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Material")) 
				{

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Duplicate))).click();
					Thread.sleep(4000);
					String sDuplicated = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();sDuplicated=sDuplicated.trim();
					String sItemDuplicated = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[2]")).getText();sItemDuplicated=sItemDuplicated.trim();
					_driver.findElement(By.name("EstimateMaterials.quantity")).clear();
					_driver.findElement(By.name("EstimateMaterials.quantity")).sendKeys(sQty);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(4000);
					//_driver.close();
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					if(sDuplicated.equals("Duplicated") && sItemDuplicated.equals("Item duplicated."))
					{
						sFlag= true;	
					}

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}
	public boolean  DeleteEstimatePaper(String sInv) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePaper']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Paper")) 
				{
					PleaseSelectInventoryItem("id",sInv,"//tr[th/a/span[contains(text(),'Inventory Item')]]/td/div/div[1]");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_Next))).click();
					Thread.sleep(3000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					CommonFunctions.waitForPageLoaded(_driver);
					String sDeleted = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();sDeleted=sDeleted.trim();
					if(sDeleted.equals("Item deleted."))
					{
						sFlag= true;	
					}
					break;
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}
	public boolean  DeleteFinishingMaterial() throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateMaterials']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Material")) 
				{
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_To_Next_Item))).click();
					Thread.sleep(3000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					String sDeleted = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();sDeleted=sDeleted.trim();
					if(sDeleted.equals("Item deleted."))
					{
						sFlag= true;	
					}
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}

	public String  ModifyInkProductViewDetails() throws Exception
	{
		String sDescription="";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//a[text()='Ink Product View']")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Ink Product View")) 
				{
					CommonFunctions.selectDropdownByIndex(_driver, By.xpath("//table[@id='EstimateInks']/tbody/tr[2]/td[3]/select"), 1);
					Thread.sleep(4000);
					sDescription = CommonFunctions.GetSelectedOption(_driver, By.xpath("//table[@id='EstimateInks']/tbody/tr[2]/td[3]/select"));
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDescription; 	
	}


	public String  AddPrepOperation(String sPrePressItem) throws Exception
	{
		String sDescription="";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimatePrepressOp_fieldset']/div[1]/div[1]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Prepress Operation")) 
				{
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.prepressItem"), sPrePressItem);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDescription; 	
	}

	public String  SelectPrepOperation(String sPrePressItem) throws Exception
	{
		String sDescription="";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimatePrepressOp_fieldset']/div[1]/div[1]/div[2]/a[2]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Add Prepress Operations")) 
				{
					_driver.findElement(By.xpath("//div[@id='operationsEstimatePrepressOp']/div[1]/ul/li/ul/li[1]/a")).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Item))).click();
					Thread.sleep(4000);
					//	CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.prepressItem"), sPrePressItem);
					//	Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_And_Complete))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDescription; 	
	}
	public String  ModifyPrepOperationDetails() throws Exception
	{
		String sDescription="";
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePrepressOp']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Prepress Operation")) 
				{
					_driver.findElement(By.name("EstimatePrepressOperations.quantity")).clear();
					_driver.findElement(By.name("EstimatePrepressOperations.quantity")).sendKeys("10");
					_driver.findElement(By.name("EstimatePrepressOperations.prepressOpSizeW")).clear();
					_driver.findElement(By.name("EstimatePrepressOperations.prepressOpSizeW")).sendKeys("10");
					_driver.findElement(By.name("EstimatePrepressOperations.prepressOpSizeH")).clear();
					_driver.findElement(By.name("EstimatePrepressOperations.prepressOpSizeH")).sendKeys("8");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sDescription; 	
	}
	public boolean  DeletePrepressOperation() throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePrepressOp']/tbody/tr/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Prepress Operation")) 
				{

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					String sDeleted = _driver.findElement(By.xpath("//div[@id='fmessage']/ul/li[1]")).getText();sDeleted=sDeleted.trim();
					if(sDeleted.equals("Item deleted."))
					{
						sFlag= true;	
					}
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}

	public void  ModifyPressDetails(String sRunMethod,String sRunSizeW,String sRunSizeH ) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimatePress']/tbody/tr/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Press")) 
				{

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method))).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Estimate_Press_Run_Method)), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys(sRunSizeW);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys(sRunSizeH);

					}
					else
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("estimatePresses.printRunMethod"), sRunMethod);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys(sRunSizeW);
						_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys(sRunSizeH);
					}
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

					Thread.sleep(4000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize==2)
					{
						_driver.close();
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}


	public boolean  AddEstimatePressDetails() throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimatePress_fieldset']/div/div/div[2]/a[1]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Press")) 
				{

					if(_driver.findElements(By.name(Locators.getProperty(Locators.Estimate_Press_Select_Press))).size()>0)
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Press_Select_Press)), 1);
						Thread.sleep(4000);
						CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Estimate_Estimate_Paper)), 1);
						Thread.sleep(4000);
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Material_Up))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Material_Up))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Width))).sendKeys("26");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Size_Height))).sendKeys("12");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Impression_Per_Hour))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Impression_Per_Hour))).sendKeys("100");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Along))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Across))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Num_Across))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Stagger))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_Stagger))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Press_Sheets_From_Buy))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Press_Sheets_From_Buy))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumWashups))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumWashups))).sendKeys("4");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPlates))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPlates))).sendKeys("8");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPlatesChanges))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPlatesChanges))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPasses))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumPasses))).sendKeys("2");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Make_Ready_Sheet))).sendKeys("115");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunSpoilagePercent))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunSpoilagePercent))).sendKeys("0.5%");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Run_Spoilage_Sheets))).sendKeys("12");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_PressSheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_PressSheets))).sendKeys("11");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunImpression))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunImpression))).sendKeys("264");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunHours))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_RunHours))).sendKeys("2.69");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumberHelpers))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_NumberHelpers))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Make_Ready_Sheet))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Press_Finishing_Spoilage_Sheets))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_MakeReadyHours))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_MakeReadyHours))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_WashupHours))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_WashupHours))).sendKeys("0.56");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_OtherHours))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_OtherHours))).sendKeys("0");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_LiftChange))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_LiftChange))).sendKeys("1");
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_PaperSpeed_Adjustment))).clear();
						_driver.findElement(By.name(Locators.getProperty(Locators.Estimate_Details_PaperSpeed_Adjustment))).sendKeys("0%");


					}
					else
					{
						CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePresses.press"), 1);
						Thread.sleep(4000);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("estimatePresses.estimatePaper"), 1);
						Thread.sleep(4000);
						_driver.findElement(By.name("estimatePresses.materialUp")).clear();
						_driver.findElement(By.name("estimatePresses.materialUp")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.runSizeW")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeW")).sendKeys("26");
						_driver.findElement(By.name("estimatePresses.runSizeH")).clear();
						_driver.findElement(By.name("estimatePresses.runSizeH")).sendKeys("12");
						_driver.findElement(By.name("estimatePresses.impressionsPerHour")).clear();
						_driver.findElement(By.name("estimatePresses.impressionsPerHour")).sendKeys("100");
						_driver.findElement(By.name("estimatePresses.numAlong")).clear();
						_driver.findElement(By.name("estimatePresses.numAlong")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.numAcross")).clear();
						_driver.findElement(By.name("estimatePresses.numAcross")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.numStagger")).clear();
						_driver.findElement(By.name("estimatePresses.numStagger")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.numPressSheetsFromBuy")).clear();
						_driver.findElement(By.name("estimatePresses.numPressSheetsFromBuy")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.numWashups")).clear();
						_driver.findElement(By.name("estimatePresses.numWashups")).sendKeys("4");
						_driver.findElement(By.name("estimatePresses.numPlates")).clear();
						_driver.findElement(By.name("estimatePresses.numPlates")).sendKeys("8");
						_driver.findElement(By.name("estimatePresses.numPlateChanges")).clear();
						_driver.findElement(By.name("estimatePresses.numPlateChanges")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.numPasses")).clear();
						_driver.findElement(By.name("estimatePresses.numPasses")).sendKeys("2");
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.makeReadySheets")).sendKeys("115");
						_driver.findElement(By.name("estimatePresses.runSpoilagePercent")).clear();
						_driver.findElement(By.name("estimatePresses.runSpoilagePercent")).sendKeys("0.5%");
						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.runSpoilageSheets")).sendKeys("12");
						_driver.findElement(By.name("estimatePresses.pressSheets")).clear();
						_driver.findElement(By.name("estimatePresses.pressSheets")).sendKeys("11");
						_driver.findElement(By.name("estimatePresses.runImpressions")).clear();
						_driver.findElement(By.name("estimatePresses.runImpressions")).sendKeys("264");
						_driver.findElement(By.name("estimatePresses.runHours")).clear();
						_driver.findElement(By.name("estimatePresses.runHours")).sendKeys("2.69");
						_driver.findElement(By.name("estimatePresses.numberHelpers")).clear();
						_driver.findElement(By.name("estimatePresses.numberHelpers")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingMakeReadySheets")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).clear();
						_driver.findElement(By.name("estimatePresses.finishingRunSpoilageSheets")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.makeReadyHours")).clear();
						_driver.findElement(By.name("estimatePresses.makeReadyHours")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.washupHours")).clear();
						_driver.findElement(By.name("estimatePresses.washupHours")).sendKeys("0.56");
						_driver.findElement(By.name("estimatePresses.otherHours")).clear();
						_driver.findElement(By.name("estimatePresses.otherHours")).sendKeys("0");
						_driver.findElement(By.name("estimatePresses.liftChange")).clear();
						_driver.findElement(By.name("estimatePresses.liftChange")).sendKeys("1");
						_driver.findElement(By.name("estimatePresses.paperSpeedAdjustment")).clear();
						_driver.findElement(By.name("estimatePresses.paperSpeedAdjustment")).sendKeys("0%");
					}
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFlag; 	
	}

	public void  ModifySpeedFactor(String sKey,By loc) throws Exception
	{
		boolean sFlag = false;
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='All']/tbody/tr/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Speed Factor")) 
				{			
					System.out.println("Enter Key value");
					//_driver.findElement(By.name("key2")).click();
					_driver.findElement(loc).sendKeys(sKey);
					//_driver.findElement(By.xpath("//div/h4[contains(label,'Key2')]/following-sibling::div/input")).sendKeys("223");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					Thread.sleep(3000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public void  AddfinishingOperationToEstimate(String sFinOp) throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div[1]/div[1]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Finishing Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sFinOp);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public void  AddShippingOperationToEstimate(String sShipOp) throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div[1]/div[1]/div[2]/a[2]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Ship/Mail Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sShipOp);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}
	public void  AddShippingOperationUponAddingNewOneToEstimate(String sShipOp,String sShipOp2) throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div[1]/div[1]/div[2]/a[2]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Ship/Mail Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sShipOp);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sShipOp2);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public void  AddfinishingOperationUponAddingNewOneToEstimate(String sFinOp,String sFinOp2) throws Exception
	{

		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div[1]/div[1]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Finishing Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sFinOp);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateFinishingOperations.finishingOperation"), sFinOp2);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public String  ModifyfinishingOperationUpdateAndGoToNext(String sFinOp,String sQty,String sUnitLabel,String sUp) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Finishing Operation")) 
				{			
					System.out.println("Select the Finishing Operation");

					_driver.findElement(By.name("estimateFinishingOperations.quantity")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.units")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.units")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).sendKeys(sUnitLabel);
					_driver.findElement(By.name("estimateFinishingOperations.numUp")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.numUp")).sendKeys(sUp);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_Next))).click();
					Thread.sleep(4000);

					sFinishingOp=CommonFunctions.GetSelectedOption(_driver, By.name("estimateFinishingOperations.finishingOperation"));
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(4000);

					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFinishingOp;
	}

	public String  ModifyShippingOperationUpdateAndGoToNext(String sShipOp,String sQty,String sUnitLabel,String sUp) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Ship/Mail Operation")) 
				{			
					System.out.println("Select the Finishing Operation");

					_driver.findElement(By.name("estimateFinishingOperations.quantity")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.units")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.units")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).sendKeys(sUnitLabel);
					//_driver.findElement(By.name("estimateFinishingOperations.numUp")).clear();
					//_driver.findElement(By.name("estimateFinishingOperations.numUp")).sendKeys(sUp);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_To_Next_Item))).click();
					Thread.sleep(4000);

					sFinishingOp=CommonFunctions.GetSelectedOption(_driver, By.name("estimateFinishingOperations.finishingOperation"));
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(4000);

					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFinishingOp;
	}
	public String  ModifyfinishingOperationInEstimate(String sFinOp,String sQty,String sUnitLabel,String sUp) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Finishing Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOperations.finishingOperation"), 1);
					Thread.sleep(4000);
					sFinishingOp=CommonFunctions.GetSelectedOption(_driver, By.name("estimateFinishingOperations.finishingOperation"));
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.units")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.units")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).sendKeys(sUnitLabel);
					_driver.findElement(By.name("estimateFinishingOperations.numUp")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.numUp")).sendKeys(sUp);

					Thread.sleep(4000);

					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Update))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					}

					Thread.sleep(4000);
					int sSize = _driver.getWindowHandles().size();
					boolean hasQuit =_driver.getWindowHandles().isEmpty();
					System.out.println("sSize is "+sSize+" hasQuit is "+hasQuit);

					if(sSize==2)
					{
						_driver.close();
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFinishingOp;
	}

	public String  ModifyShippingOperationInEstimate(String sShipOp,String sQty,String sUnitLabel,String sUp) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Ship/Mail Operation")) 
				{			
					System.out.println("Select the Finishing Operation");
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateFinishingOperations.finishingOperation"), 1);
					Thread.sleep(4000);
					sFinishingOp=CommonFunctions.GetSelectedOption(_driver, By.name("estimateFinishingOperations.finishingOperation"));
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.quantity")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.units")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.units")).sendKeys(sQty);
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).clear();
					_driver.findElement(By.name("estimateFinishingOperations.unitLabel")).sendKeys(sUnitLabel);
					//	_driver.findElement(By.name("estimateFinishingOperations.numUp")).clear();
					//	_driver.findElement(By.name("estimateFinishingOperations.numUp")).sendKeys(sUp);

					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return sFinishingOp;
	}
	public void  DeletefinishingOperationInEstimate() throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Finishing Operation")) 
				{			
					System.out.println("Delete the Finishing Operation");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public void  DeleteShippingOperationInEstimate() throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateFinishingOpPart']/tbody/tr[2]/td[3]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Ship/Mail Operation")) 
				{			
					System.out.println("Delete the Finishing Operation");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}


	public ArrayList  SelectFinishingOperation() throws Exception
	{
		ArrayList  FinishingOp = new ArrayList();
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div[1]/div[1]/div[2]/a[3]")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Add Finishing Operations")) 
				{		

					int rowcount = _driver.findElements(By.xpath("//div[@id='operationsEstimateFinishingOp']/div[1]/ul[1]/li[2]/ul/li")).size();
					System.out.println("Row Count is "+rowcount);

					for(int i = 1;i<=rowcount;i++)
					{
						String sF1= _driver.findElement(By.xpath("//div[@id='operationsEstimateFinishingOp']/div[1]/ul[1]/li["+rowcount+"]/ul/li["+i+"]/a")).getText();
						System.out.println("Selected operation is "+sF1);
						FinishingOp.add(sF1);
						System.out.println("Select the Finishing Operation");
						_driver.findElement(By.xpath("//a[text()=' "+sF1+"']")).click();
						Thread.sleep(4000);
						if(i==4)
						{
							break;
						}
					}

					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Item))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_And_Go_To_Next))).click();
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_And_Go_To_Next))).click();
					Thread.sleep(4000);
					//_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_And_Go_To_Next))).click();
					//Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_And_Complete))).click();
					Thread.sleep(4000);


					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return FinishingOp;
	}

	public boolean  AddOutSidePurchase(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		boolean AddOutSidePurchase=false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateOutsidePurch_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Outside Purchase")) 
				{			
					System.out.println("Enter the details");
					_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
					_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
					_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 1);
					Thread.sleep(1000);
					if(_driver.findElements(By.name("estimateOutsidePurchases.altCurrency")).size()>0)
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("estimateOutsidePurchases.altCurrency"), "USD");
						Thread.sleep(1000);
					}
					if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh))))
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
						Thread.sleep(2000);
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
						AddOutSidePurchase = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
					}
					else if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_button))))
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						AddOutSidePurchase = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					}			 							 				
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return AddOutSidePurchase;

	}
	public boolean AddMultipleOutSidePurchase(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		boolean AddMultipleOutSidePurchase = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateOutsidePurch_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Outside Purchase")) 
				{			
					System.out.println("Enter the details");
					for(int i =1;i<=4;i++)
					{
						_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc+UniqueNum());
						_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
						_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 1);
						Thread.sleep(1000);
						if(_driver.findElements(By.name("estimateOutsidePurchases.altCurrency")).size()>0)
						{
							CommonFunctions.selectDropdownByText(_driver, By.name("estimateOutsidePurchases.altCurrency"), "USD");
							Thread.sleep(1000);
						}
						if(i==4)
						{
							if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh))))
							{
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
								Thread.sleep(2000);
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
								AddMultipleOutSidePurchase = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
							}
							else if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_button))))
							{
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
								Thread.sleep(2000);
								AddMultipleOutSidePurchase = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
								_driver.close();
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
							}
						}
						else
						{
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
							Thread.sleep(2000);
						}
					}	
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return AddMultipleOutSidePurchase;

	}

	public void  ModifyOutSidePurchase(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateOutsidePurch']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Outside Purchase")) 
				{			
					_driver.findElement(By.name("estimateOutsidePurchases.description")).clear();
					_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
					_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).clear();
					_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
					_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).clear();
					_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 2);
					Thread.sleep(2000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}


	public void  ModifyAllOutSidePurchase(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateOutsidePurch']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Outside Purchase")) 
				{	
					for(int i =1;i<=4;i++)
					{
						_driver.findElement(By.name("estimateOutsidePurchases.description")).clear();
						_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
						_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).clear();
						_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
						_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).clear();
						_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 2);
						Thread.sleep(2000);
						if(i==4)
						{
							_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
							Thread.sleep(2000);
						}
						else
						{
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Update_Go_To_Next_Item))).click();
							Thread.sleep(2000);
						}
					}
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public boolean AddMultipleOutSidePurchaseFromDetailsPopupPage(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		boolean AddMultipleOutSidePurchaseFromDetailsPopupPage = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateOutsidePurch_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Outside Purchase")) 
				{			
					System.out.println("Enter the details");
					System.out.println("Enter the details");
					for(int i =1;i<=4;i++)
					{
						_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
						_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
						_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
						CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 1);
						Thread.sleep(1000);
						CommonFunctions.selectDropdownByText(_driver, By.name("estimateOutsidePurchases.altCurrency"), "USD");
						Thread.sleep(1000);
						if(i==4)
						{
							if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh))))
							{
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
								Thread.sleep(2000);
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
								AddMultipleOutSidePurchaseFromDetailsPopupPage = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
							}
							else if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_button))))
							{
								_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
								Thread.sleep(2000);
								AddMultipleOutSidePurchaseFromDetailsPopupPage = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
								_driver.close();
								_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
							}
						}
						else
						{
							_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
							Thread.sleep(2000);
						}
					}				 				
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return AddMultipleOutSidePurchaseFromDetailsPopupPage;

	}
	public boolean AddOutSidePurchaseFromDetailsPopupPage(String sDesc,String sSetupCost,String sUnitPrice) throws Exception
	{
		boolean AddOutSidePurchaseFromDetailsPopupPage = false;
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//fieldset[@id='estimateOutsidePurch_fieldset']/div[1]/div[1]/div[2]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Estimate Outside Purchase")) 
				{			

					_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
					_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
					_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 1);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateOutsidePurchases.altCurrency"), "USD");
					Thread.sleep(1000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New))).click();
					Thread.sleep(2000);

					_driver.findElement(By.name("estimateOutsidePurchases.description")).sendKeys(sDesc);
					_driver.findElement(By.name("estimateOutsidePurchases.setupCost")).sendKeys(sSetupCost);
					_driver.findElement(By.name("estimateOutsidePurchases.unitPrice")).sendKeys(sUnitPrice);
					CommonFunctions.selectDropdownByIndex(_driver, By.name("estimateOutsidePurchases.uom"), 1);
					Thread.sleep(1000);
					CommonFunctions.selectDropdownByText(_driver, By.name("estimateOutsidePurchases.altCurrency"), "USD");
					Thread.sleep(1000);
					if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh))))
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Refresh))).click();
						Thread.sleep(2000);
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
						AddOutSidePurchaseFromDetailsPopupPage = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
					}
					else if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_button))))
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
						Thread.sleep(2000);
						AddOutSidePurchaseFromDetailsPopupPage = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
						_driver.close();
						_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
					}
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}
		return AddOutSidePurchaseFromDetailsPopupPage;

	}

	public void  DeleteOutSidePurchaseInEstimate() throws Exception
	{
		String sFinishingOp="";
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		_driver.findElement(By.xpath("//table[@id='estimateOutsidePurch']/tbody/tr[1]/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Estimate Outside Purchase")) 
				{			
					System.out.println("Delete the Finishing Operation");
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Delete))).click();
					_driver.findElement(By.xpath("//div[@id='contentDiv']/table/tbody/tr[2]/td/div/input[1]")).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}
	public void EnterEstimateQuoteLetterSetupDEtails(String sUsage,String sDesc,String sName,String sSalution,String sBody,String sHeader,String sClosing,String sComment,String sDisclaimer  ) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name("usage"), sUsage);
		Thread.sleep(4000);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys(sDesc);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PaperSetup_Name))).sendKeys(sName);
		_driver.findElement(By.name("salutation")).sendKeys(sSalution);
		Thread.sleep(4000);
		_driver.findElement(By.name("body")).sendKeys(sBody);
		Thread.sleep(4000);
		_driver.findElement(By.name("quoteLetterNotesHeader")).sendKeys(sHeader);
		Thread.sleep(4000);
		_driver.findElement(By.name("closing")).sendKeys(sClosing);
		Thread.sleep(4000);
		_driver.findElement(By.name("comment")).sendKeys(sComment);
		Thread.sleep(4000);
		_driver.findElement(By.name("disclaimer")).sendKeys(sDisclaimer);
		Thread.sleep(4000);
	}

	public void EnterEstimateLetterConfigurationDetails(String sSequence,String sCat,String sLineType,String QuoteLetterText) throws Exception
	{
		_driver.findElement(By.xpath("//a[text()='Estimate Letter Configuration']")).click();
		Thread.sleep(4000);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String  sWindowTitle= _driver.getTitle();
		System.out.println(sWindowTitle);
		_driver.findElement(By.xpath("//a[text()='Add With Details']")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().equals("Adding Quote Letter Type Text")) 
				{			
					System.out.println("Add quote letter type text");
					_driver.findElement(By.name("sequence")).sendKeys(sSequence);
					_driver.findElement(By.name("category")).sendKeys(sCat);

					CommonFunctions.selectDropdownByIndex(_driver, By.name("section"), 1);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("lineType"), sLineType);
					Thread.sleep(4000);
					CommonFunctions.selectDropdownByText(_driver, By.name("quoteLetterText"), QuoteLetterText);
					Thread.sleep(4000);
					_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
					Thread.sleep(4000);
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				} 
				else 
				{
					//System.out.println("Not able to find window");
					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);

				}

			}
		}

	}

	public boolean VerifyQuoteLetterDetails(String sSequence,String sCat,String sName,String sSalution,String sBody,String sComment,String sClosing) throws Exception
	{
		boolean sFlag=false;
		String sSeq = _driver.findElement(By.xpath("//table[@id='quoteLetterNotes']/tbody/tr[1]/td[3]/input")).getAttribute("value");sSeq=sSeq.trim();
		String sCategory = _driver.findElement(By.xpath("//table[@id='quoteLetterNotes']/tbody/tr[1]/td[4]/input")).getAttribute("value");sCategory=sCategory.trim();
		_driver.findElement(By.xpath("//span[text()='I']")).click();
		Thread.sleep(4000);
		String sLetterType =  CommonFunctions.GetSelectedOption(_driver, By.name("quoteLetterType"));
		String sSal = _driver.findElement(By.name("salutation")).getAttribute("value");sSal=sSal.trim();
		String sBodyText = _driver.findElement(By.name("body")).getText();sBodyText=sBodyText.trim();
		String sCommentText = _driver.findElement(By.name("comment")).getText();sCommentText=sCommentText.trim();
		String sClosingText = _driver.findElement(By.name("closing")).getText();sClosingText=sClosingText.trim();
		if(sSeq.equals(sSequence) && sCategory.equals(sCat)  && sLetterType.equals(sName)  && sBodyText.equals(sBody)  && sCommentText.equals(sComment) && sClosingText.equals(sClosing))
		{
			sFlag=true;
		}
		else
		{
			System.out.println("sSequence  is "+sSequence+" sCat is "+sCat+" sName is "+sName+" sSalution is "+sSalution+" sBody is "+sBody+" sComment is "+sComment+" sClosing is "+sClosing);
			Assert.fail("sSeq is "+sSeq+" sCategory is "+sCategory+" sLetterType is "+sLetterType+" sSal is "+sSal+" sBodyText is "+sBodyText+" sCommentText is "+sCommentText+" sClosingText is "+sClosingText);
		}

		return sFlag;
	}


	public void ChangeOtherHoursInPress(String sOtherHour) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		System.out.println("Enter Gripper Allowance and Color Bar");
		_driver.findElement(By.xpath("//table[@id='estimatePress']/tbody/tr/td[2]/div/a/img")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Estimate Press")) 
				{
					//String sVersion = FetchVersion();
					if(_driver.findElements(By.name("EstimatePresses.otherHours")).size()>0)
					{
						_driver.findElement(By.name("EstimatePresses.otherHours")).clear();
						_driver.findElement(By.name("EstimatePresses.otherHours")).sendKeys(sOtherHour);
						Thread.sleep(4000);
					}
					else
					{
						_driver.findElement(By.name("estimatePresses.otherHours")).clear();
						_driver.findElement(By.name("estimatePresses.otherHours")).sendKeys(sOtherHour);
						Thread.sleep(4000);
					}
					if(_driver.findElements(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).size()>0)
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.UpdateAndRefresh))).click();
						Thread.sleep(4000);
					}
					else
					{
						_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
						Thread.sleep(4000);
					}


					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
			}
		}

	}

	public void EnablePlatecheckboxinPressType(boolean sPlates) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		System.out.println(originalHandle);
		String sWindowTitle = _driver.getTitle();
		System.out.println("Enter Gripper Allowance and Color Bar");
		String sPressType = CommonFunctions.GetSelectedOption(_driver, By.name("pressType"));

		_driver.findElement(By.xpath("//div/h4[contains(label,'Press Type')]/following-sibling::div[1]/a")).click();
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty()) 
		{
			for (String windowId : availableWindows) 
			{
				if(_driver.switchTo().window(windowId).getTitle().contains("Press Type: "+sPressType)) 
				{
					PurchasePage PO = new PurchasePage(_driver);
					PO.sSelectCheckBox(sPlates, By.xpath("//fieldset[1]/div/div/div[1]/div/input[2]"));

					DC.Update();
					_driver.close();

					_driver.switchTo().window(originalHandle).getTitle().equals(sWindowTitle);
				} 
			}
		}

	}

	public void DeleteAllRunMethods () throws Exception
	{
		DCPage DC = new DCPage(_driver);
		System.out.println("Entered Deleting all Run Methods method");
		_driver.findElement(By.xpath("//a[text()='Run Methods']")).click();
		Thread.sleep(4000);
		_driver.findElement(By.xpath("//table[@id='printRunMethod']/thead/tr/th[7]/span/a")).click();
		Thread.sleep(500);
		DC.Update();

	}

	public String UniqueNum6Digit()
	{
		Random random = new Random();
		DateFormat dateFormat = new SimpleDateFormat("hhssmm");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		if(String.valueOf(sUniqueNumber.charAt(0)).equals("0")){sUniqueNumber=sUniqueNumber.replaceFirst("0", String.valueOf(random.nextInt(8)+1));}
		return sUniqueNumber;
	}

	public String UniqueNum4Digit()
	{
		Random random = new Random();
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		if(String.valueOf(sUniqueNumber.charAt(0)).equals("0")){sUniqueNumber=sUniqueNumber.replaceFirst("0", String.valueOf(random.nextInt(8)+1));}
		return sUniqueNumber;
	}

	public String UniqueNum10Digit()
	{
		Random random = new Random();
		DateFormat dateFormat = new SimpleDateFormat("ddMMhhssmm");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		if(String.valueOf(sUniqueNumber.charAt(0)).equals("0")){sUniqueNumber=sUniqueNumber.replaceFirst("0", String.valueOf(random.nextInt(8)+1));}
		return sUniqueNumber;
	}

	public String createSampleEstimate() throws Exception
	{
		DCPage DC = new DCPage(_driver);
		JobPlanningPage JP = new JobPlanningPage(_driver);
		ICPage IC = new ICPage(_driver);
		PurchasePage PO = new PurchasePage(_driver);
		String sEstimateDetails = "";			 

		//inventory variables
		String sInv = "INV"+UniqueNum6Digit(), sItemType ="1 - Paper,Sheets", sPapertype = "CG - Gloss Cover", sInvMixStock = "100000", sInvMaxStock="1000000000";

		//fold pattern variables
		String sFoldPatDesc = "4 Pg Auto" ,sFoldPatternNum=UniqueNum6Digit(),sPage="4",sBindingSide="Left",sJogSide="Right",sJDFType="F4-2 2x1";
		String sSpineEdge="1",sParallelFold="1",sNumberScore="0",sSetupHr="",sUnits="0",sPlates="0",sWidthEdge="2",sFoldPass2="0",sMinStock="",sMaxStock="",sAngleFold="0",sAngleFoldPass2="0",sNumPerfs="0",sSlits="",sGlues="";
		boolean sMustGateFold=false ,sMustDieCut=false,sAskPlies=false;

		//prepress workflow variables
		String sPrepMethod = "Auto"+UniqueNum4Digit(), sMatProvided = "Auto"+UniqueNum4Digit();
		String sPrepressWorkFlow = sMatProvided+" - "+ sPrepMethod;
		String PrepBindery="Normal";
		String sDesc = "PrePress"+UniqueNum6Digit();
		String sCode = "C"+UniqueNum6Digit(),sLabor="000 - NewJob/No Activity",sMaterials="000 - NewJob/No Activity",sPrePressType = "Misc",sPrepressGroup="1",sMinColors="0",sMaxColors="19",sUnitLabel="Disk";

		//finishing workflow variables
		String sFinishingWorkflow = "Fin"+UniqueNum10Digit(), sFinOpDesc = "Fin"+UniqueNum4Digit(), sFinOpCode = "C"+UniqueNum4Digit();
		String sCategory = "Finishing", sOperType="Cutting", sFinishingGroup="Unassigned";
		String sFolder = "Fold"+UniqueNum6Digit(),sMinSizeW="10",sMinSizeH="8",sMaxSizeW="60",sMaxSizeH="50",sDefaultNumup="1";

		//press variables
		String sPressType="Sheetfed", sPressCode = UniqueNum6Digit(), sPressDesc="Auto"+UniqueNum10Digit(), sPress  = sPressCode+" - "+sPressDesc, MRAC= "000 - NewJob/No Activity";

		//product type and category variables
		String sProductTypeID = "PT"+UniqueNum4Digit(), sProductionType="Offset", sProductTypeDesc = "Product type";
		String sBuySizeW="30",sBuySizeH="14",sRunSizeW="28", sRunSizeH="12", sProductType= sProductTypeID+"-"+sProductTypeDesc, sProdcutCategory = "0PrdCat"+UniqueNum6Digit();
		String sEstNum = "Est"+UniqueNum10Digit(), sFinaleSizeW = "12",sFinaleSizeH = "14", sQty1 = "1000",sQty1Desc = "1000";

		//------------------------------create a new Inventory Item----------------------------------------------------------------//
		IC.NavigateToInventorySettingsPage();
		IC.CheckInventoryBin(true);
		IC.UncheckLocationStockLevel(false);
		IC.Update();
		PO.AddNewInventory();
		_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(sInv);
		_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("Letter Paper");
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Addl_Desc))).sendKeys("Letter Paper");
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_Item_Type)), sItemType);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Inventory_UOM)), "M-/Thousand");
		Thread.sleep(1000);
		_driver.findElement(By.name("unitWeight")).sendKeys("1.25");
		_driver.findElement(By.xpath("//span[text()='P']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.name("sizeW")).sendKeys("60");
		_driver.findElement(By.name("sizeH")).sendKeys("70");
		_driver.findElement(By.xpath("//span[text()='P']")).click();
		Thread.sleep(2000);
		if(_driver.findElements(By.name("grainDirection")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("grainDirection"),"Long");
		}
		_driver.findElement(By.name("basis")).sendKeys("80");
		_driver.findElement(By.name("mWeight")).sendKeys("0.55");
		CommonFunctions.selectDropdownByText(_driver, By.name("paperType"), sPapertype);
		Thread.sleep(3000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("paperWeight"),1);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByIndex(_driver, By.name("mediaColorName"),1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath("//a[text()='eService Info']")).click();
		Thread.sleep(1000);
		_driver.findElement(By.name("maxWeightPerBox")).sendKeys("25");
		_driver.findElement(By.name("sellUnitQty")).sendKeys("0.15");
		_driver.findElement(By.name("freightCost")).sendKeys("$20.00");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Inventory_Ordering_Info))).click();
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Min_Stock_Level))).sendKeys(sInvMixStock);	
		_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Ordering_Info_Max_Stock_Level))).sendKeys(sInvMaxStock);	
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		System.err.println("Created new Inventory Item "+sInv);
		sEstimateDetails = sEstimateDetails+"Inventory:"+sInv+"/";

		//-------------------------------Navigate to Fold Pattern and Add new Fold Pattern-------------------------------//
		System.out.println("Navigate to Estimate Fold Pattern");
		NavigateToEstimateFolderPatterns();
		System.out.println("Add Estimate Fold Pattern");
		AddNewFolderPattern(sPage, sFoldPatternNum, sFoldPatDesc, sBindingSide, sJogSide, sJDFType);
		EnterFolderPatternSpecsDetails(sSpineEdge, sParallelFold, sNumberScore, sSetupHr, sUnits, sPlates, sWidthEdge, sFoldPass2, sMinStock, sMaxStock, sAngleFold, sAngleFoldPass2, sNumPerfs, sSlits, sGlues, sMustGateFold, sMustDieCut, sAskPlies);
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		String sFoldPattern = sPage+"/"+sFoldPatternNum+" - "+sFoldPatDesc;
		System.err.println("Created new Fold Pattern "+sFoldPattern);
		sEstimateDetails = sEstimateDetails+"FoldPattern:"+sFoldPattern+"/";


		//-------------------- Navigate to Prepress Item list page and Add new prepress item ----------------------------------//
		System.out.println("Navigate to Prepress item list");
		NavigateToPrepressItem();
		System.out.println("Create new Prepress item");
		AddNewPrepressItem(sCode, sDesc, sLabor, sMaterials, sPrePressType, sPrepressGroup, sUnitLabel, sMinColors, sMaxColors,true);
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		String sPrepressItem = sCode+" - "+sDesc;
		System.err.println("Created new Prepress Item "+sPrepressItem);			
		//-------------------------------Navigate to Materials Provided page and Add new Materials Provided-------------------------------//
		System.out.println("Navigate to Materials Provided page");
		NavigateToMiscellaneousMaterialsProvidedPage();
		System.out.println("Add new Materials Provided");
		AddNewMaterialsProvider(sMatProvided);
		System.err.println("Created new Material provider "+sMatProvided);
		//---------------------------- Navigate to prep method page and Add new Prep Method and Link Material Provided w/ this Prep Method----------------------------//
		System.out.println("Navigate to Materials Provided page");
		NavigateToMiscellaneousPrePMethodPage();
		System.out.println("Add new Prep Methods");
		AddNewPrepMethod(sPrepMethod);
		System.out.println("Link Material Provided w/ this Prep Method");
		AddNewLineMaterails(sMatProvided);
		System.err.println("Created new PrepMethod "+sPrepMethod);
		//----------------------- Navigate to Prepress Workflow page and Add new job difficulty------------------------------//
		System.out.println("Navigate to Workflow Prepress Workflow page");
		NavigateToWorkflowPrepressWorkflowPage();
		AddNewPrePressWorkflow(sPrepMethod, sMatProvided, PrepBindery);
		AddNewPrepressActivity("1", sPrepressItem, "Per Sq Unit - Run Size", "Proof Square Inches", "Press Sheet Size");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
		Thread.sleep(2000);
		System.err.println("Created new Prepressworkflow "+sPrepressWorkFlow);			 
		sEstimateDetails = sEstimateDetails+"PrepressWorkflow:"+sPrepressWorkFlow+"/";


		//---------------------Create new Add new Folder setup------------------------------//
		System.out.println(" Navigate to Folder setup list");
		NavigateToFolderSetup();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(2000);
		EnterFolderSetupGeneralDetails(sFolder, sMinSizeW, sMinSizeH, sMaxSizeW, sMaxSizeH, sDefaultNumup, true);
		EnterFolderSetupSettingsDetails("9", "9", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
		EnterFolderSetupAdjustmentDetails("", "", "", "", "0.1", "1", "1%", "1%", "2", "2", "2%", "2%", "3", "3", "3%", "3%", "4", "4", "4%", "4%");
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));		
		System.err.println("Created new Folder "+sFolder);			
		//--------------------Navigate to Finishing operation list page and Create new finishing operation--------------------//				
		System.out.println("Navigate to Finishing  operation");
		NavigateToFinishingShippingOperation();
		System.out.println("Create new finishing operation");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		Thread.sleep(2000);
		EnterFinishingOperationDetails(sFinOpCode, "", sCategory, sOperType, sFinishingGroup, sFinOpDesc, "", "", "Finishing", "", "000 - NewJob/No Activity", "000 - NewJob/No Activity", "000 - NewJob/No Activity", "000 - NewJob/No Activity", "Signature");
		EnterFinishingOperationMachineSelectionFolderDetails(sFolder);
		EnterFinishingOperationOtherSettingsDetails("8", "", "", false);
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		String sFinishingOperation =sFinOpCode+ " - "+sFinOpDesc;
		System.err.println("Created new Finishing Operation "+sFinishingOperation);			
		//-------------------- Navigate to Finishing workflow page and Create Finishing workflow--------------------//
		System.out.println("Navigate to Finishing workflow");
		NavigateToWorkflowFinishingtWorkflow();
		AddNewPressEventWorkflow(sFinishingWorkflow);			
		_driver.findElement(By.xpath("//fieldset[@id='bindingMethodFinishingOp_fieldset']/div[1]/div[1]/div[2]/input")).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//table[@id='bindingMethodFinishingOp']/tbody/tr[1]/td[3]/input")).sendKeys("1");
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='bindingMethodFinishingOp']/tbody/tr[1]/td[4]/select"), sFinishingOperation);
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='bindingMethodFinishingOp']/tbody/tr[1]/td[5]/select"), "Estimate");
		Thread.sleep(1000);
		DC.Update();
		CommonFunctions.waitForPageLoaded(_driver);
		System.err.println("Created new  Finishing workflow "+sFinishingWorkflow);
		sEstimateDetails = sEstimateDetails+"FinishingWorkflow:"+sFinishingWorkflow+"/";


		//---------------------Create new Press------------------------------//
		NavigateToPressSetup();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
		CommonFunctions.waitForPageLoaded(_driver);			
		EnterPressInfoInPressSetupPage(sPressCode, sPressType,sPressDesc, "", "Charityww", "Agency", "48", "1", "1 1/2", "", "", MRAC, "1", false);
		EnterPressSetupColorInfo("4", "0", "4", "1", "99", "", "2", true);
		EnterPressSetupSizeInfo("8", "10", "396", "396", "396", "396", "0.0016", "2.999", "", true);
		EnterPressSetupMakeReady("000 - NewJob/No Activity", "1", "Plates", "0.17", "0.05", "0.12", "5", "10", "12", "0.05", "0.05", "0.05", "0.1", "25", "25", "25", "20", true);
		EnterPressSetupWashUp("000 - NewJob/No Activity", "0.15", "0.1", "0.6", "0.08", "0.17", "0.16", "0.17", "0.14", "0.33");
		EnterPressSetupDry("000 - NewJob/No Activity", "0.2", "0.6", "0.7", "0.8");
		EnterPressSetupSpoilage("0%", "0.5%", "1%", "1.5%", true);
		EnterPressSetupSpeed("000 - NewJob/No Activity", "0%", "0%", "0%", "-2%", "0.5", "0.05", "10", "");
		EnterPressSetupPlates("1");
		DC.Add();
		System.err.println("Created new Press "+sPress);
		sEstimateDetails = sEstimateDetails+"Press:"+sPress+" | ";

		//-------------------------------Create Product Type and Product Category------------------------------//
		NavigateToEstimateJobProductType();
		CreateJobProductType(sProductTypeID, sProductTypeDesc, "HOUSE", sProductionType, sPage, sFoldPattern);			
		EnterSettingDetailsForJobProductType(sPage, sFoldPattern, sInv, sBuySizeW, sBuySizeH, sPrepressWorkFlow, sFinishingWorkflow, sPress, sRunSizeW, sRunSizeH);
		DC.Add();
		CommonFunctions.waitForPageLoaded(_driver);
		System.err.println("Created new Product Type "+sProductTypeID);
		sEstimateDetails = sEstimateDetails+"ProductType:"+sProductTypeID+"/";
		NavigateToEstimateProductCategory();
		AddNewEstimateProductCategory(sProdcutCategory);
		DC.Update();
		NavigateToEstimateProductCategory();
		AddProductTypeProductCategory(sProdcutCategory, sProductType,"")	;
		System.err.println("Created new Product Category "+sProdcutCategory);
		sEstimateDetails = sEstimateDetails+"ProductCategory:"+sProdcutCategory+"/";

		//	-----------------------------------------create estimate--------------------------------------
		NavigateToEnterNewEstimate();
		JP.SimpleEstimateCreation(sEstNum, "HOUSE", sProdcutCategory, sProductType, sFinaleSizeW, sFinaleSizeH, sQty1, sQty1Desc, sPrepressWorkFlow, sFinishingWorkflow, sPress, sInv);
		Calculate();
		CommonFunctions.waitForPageLoaded(_driver);
		System.err.println("Created new Estimate "+sEstNum);
		sEstimateDetails = sEstimateDetails+"Estimate:"+sEstNum;

		return sEstimateDetails;
	}

	public void gotoEstimateSummaryPage(String EstimateNumber) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		NavigateToEstimate();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));

		DC.SearchValue(EstimateNumber, "estimateNumber");
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/tbody/tr/td[2]/div/a/img"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("estimateDescription"));
		assertEquals("Estimate '"+EstimateNumber+"' Summary", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"gotoEstimate");
	}

	public void AddLinkPrepressItemToPrepressWorkflow(String Sequence, String PrepressItem, String QuanityCalMethod, String EstimateExpression, String SizeCalCMethod) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Prep_Activity_Record)));
		CommonFunctions.SendValue(_driver, By.xpath("//tr[@id='prepActivities_addRow']//input[@name='prepActivities.sequence']"), Sequence);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//tr[@id='prepActivities_addRow']//select[@name='prepActivities.prepressItem']"), PrepressItem);
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//tr[@id='prepActivities_addRow']//select[@name='prepActivities.quantityCalcMethod']"), QuanityCalMethod);
		if(!EstimateExpression.equals("")){CommonFunctions.selectDropdownByText(_driver, By.xpath("//tr[@id='prepActivities_addRow']//select[@name='prepActivities.estimateExpression']"), EstimateExpression);}		
		CommonFunctions.selectDropdownByText(_driver, By.xpath("//tr[@id='prepActivities_addRow']//select[@name='prepActivities.sizeCalcMethod']"), SizeCalCMethod);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}


	public void CreateEstimateRequestVehicleWrap(String Descriptions, String Customer) throws Exception
	{
		_driver.findElement(By.name("description")).sendKeys(Descriptions);	
		_driver.findElement(By.name("customer")).sendKeys(Customer);
		_driver.findElement(By.name("updateForm")).click();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));      
	}

	public void EstimateAddProductVehicleWrap(String Category, String CategoryKeys,String CompositeProd) throws Exception
	{
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Estimate_Add_Property))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(".//label[text()='Category']"));  
		CommonFunctions.selectDropdownByText(_driver, By.name("compCategory"), Category);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdownByText(_driver, By.name("categoryKey1"), CategoryKeys);
		CommonFunctions.selectDropdownByText(_driver, By.name("compProduct"), CompositeProd);

	}

	public void clickOnCreate_EstimateandConvertButton() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Create_EstimateandConvert)));
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Clicked on Create Estimate and Convert Button");

	}

	public void NavigateToEstimateDetail(String EstimateNumber) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		NavigateToEstimate();
		DC.SearchValue(EstimateNumber, "estimateNumber");
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='grid-contents']//table/tbody/tr[1]//img"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("manufacturingLocation"));
	} 


	public void ViewDetails() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Estimate_View_Details)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("currentProduct"));
	} 



}