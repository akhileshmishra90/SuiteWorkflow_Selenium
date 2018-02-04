package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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


public class ReportPage implements BaseElement
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

	public  ReportPage(WebDriver driver) throws Exception 
	{  
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
	
	
	public HashMap<String, String> GetReportDetail(String ReportID) throws Exception
	{	
		HashMap<String, String> TestCaseData =new HashMap<String, String>();
		NavigateToReport(ReportID);	
		
		
		try {
			
		
		String Name=getNextElementValue("Name");
		TestCaseData.put("Name", Name);
		String Category=getNextElementValue("Category");
		TestCaseData.put("Category", Category);			
		String Module=getNextElementValue("Module");
		TestCaseData.put("ModuleData", Module);			
		String DisplayName=getNextElementValue("Display Name");			
		TestCaseData.put("DisplayName", DisplayName);
		String Description=getNextElementValue("Description");			
		TestCaseData.put("Description", Description);
		String ExportType=getNextElementValue("Export Type");			
		TestCaseData.put("ExportType", ExportType);
		String CrystalClearVersion=getNextElementValue("Crystal Clear Version");
		TestCaseData.put("CrystalClearVersion", CrystalClearVersion);
		String BaseObject=getNextElementValue("Base Object");
		TestCaseData.put("BaseObject", BaseObject);
		
		} catch (Exception e) {
			
		}
		
		return TestCaseData;
		
		
		
	}


	public Boolean OpenRecord(String Id) throws Exception
	{
		NavigateToReportList();

		JobPlanningPage JP=new JobPlanningPage(_driver);

		String TotalRecord=CommonFunctions.GetText(_driver, By.xpath(".//*[@id='grid-contents']/div/div[1]/div[1]/strong"));
		if(!(Integer.parseInt(TotalRecord)>1))
		{
			return false;
		}
		Boolean IsElementClick=JP.ClickTable4Value("ID","//table[@id='appbox_implicit']",Id,"//input[@name='testReportDefault_button']");
		if(IsElementClick)
		{
			System.out.println("element is found successfully");
			return true;
		}
		else
		{
			System.err.println("Element is not found");
			return false;
		}


	}
	public void TestRecord(HashMap<String, String> HasMapobj)throws Exception			
	{
		String InputDateXpath=".//label[text()='Enter Beginning Date']/../../div/input";
		String InputEndDateXpath=".//label[text()='Enter Ending Date']/../../div/input";
		String InputBeginVendorXpath=".//label[text()='Enter Beginning Vendor']/../../div/input";
		String InputEndingVendorXpath=".//label[text()='Enter Ending Vendor']/../../div/input";
		String SelectDetailSummary=".//label[text()='Detail or Summary']/../../div/select";
		String SelectPrintSetupXpath=".//label[text()='Print All or Only those setup to print']/../../div/select";
		String SelectDetailExportXpath=".//label[text()='Detail or Export']/../../div/select";
		String SelectSortNameOrIdXpath=".//label[text()='Sort by Vendor Name or Vendor ID']/../../div/select";

		String sUN = dbConnection.ReadFunction("Global", "Epace_Login", "Epace_Login", "Username");

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).size() > 0)
		{ 
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_Another_Instance))).click();
			Thread.sleep(2000);
			String sLoggedinUserpath = "//div[@id='welcomeMessage']/a[text()='"+sUN+"']";
			boolean sFlag= CommonFunctions.isElementPresent(_driver,By.xpath(sLoggedinUserpath)); 
			if(sFlag == true)
			{


				NewFileNamePath =  TakeScreenShot.getDestinationFile("Home");
				File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(NewFileNamePath));
				System.out.println(NewFileNamePath);
				// assertEquals("Employee Login for Workstation: "+sWorkspace, _driver.getTitle());
			}
			else
			{
				System.out.println("Login Failed");		
			}
		}

		try {
			if (HasMapobj.containsKey("BeginDate"))
			{
				CommonFunctions.SendValue(_driver, By.xpath(InputDateXpath), HasMapobj.get("BeginDate"),"Begindate");
			}

		} catch (Exception e) {

		}
		try {
			if (HasMapobj.containsKey("EndDate"))
			{
				CommonFunctions.SendValue(_driver, By.xpath(InputEndDateXpath), HasMapobj.get("EndDate"),"EndDate");
			}
		} catch (Exception e) {

		}


		try {
			if (HasMapobj.containsKey("BeginVendor"))
			{
				CommonFunctions.SendValue(_driver, By.xpath(InputBeginVendorXpath), HasMapobj.get("BeginVendor"),"BeginVendor");
			}
		} catch (Exception e) {

		}


		try {

			if (HasMapobj.containsKey("EndVendor"))
			{
				CommonFunctions.SendValue(_driver, By.xpath(InputEndingVendorXpath), HasMapobj.get("EndVendor"),"EndVendor");
			}
		} catch (Exception e) {

		}

		try {
			if (HasMapobj.containsKey("DetailSummary"))
			{				
				CommonFunctions.selectDropdownByText(_driver, By.xpath(SelectDetailSummary), HasMapobj.get("DetailSummary"));
			}

		} catch (Exception e) {

		}

		try {

			if (HasMapobj.containsKey("PrintSetup"))
			{				
				CommonFunctions.selectDropdownByText(_driver, By.xpath(SelectPrintSetupXpath), HasMapobj.get("PrintSetup"));
			}

		} catch (Exception e) {
			System.err.println("Unable to value from printsetup  ");
		}

		try {


			if (HasMapobj.containsKey("DetailExport"))
			{				
				CommonFunctions.selectDropdownByText(_driver, By.xpath(SelectDetailExportXpath), HasMapobj.get("DetailExport"));
			}
		} catch (Exception e) {
			System.err.println("Unable to value from printsetup  ");
		}


		try {


			if (HasMapobj.containsKey("SortNameID"))
			{				
				CommonFunctions.selectDropdownByText(_driver, By.xpath(SelectSortNameOrIdXpath), HasMapobj.get("SortNameID"));
			}

		} catch (Exception e) {
			System.err.println("Unable to Select Sort by id or name ");
		}


	}



	public void NavigateToReportList() throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Report/list");
		CommonFunctions.Wait(_driver,
				By.name(Locators.getProperty(Locators.Search_TextField)));
		assertEquals("Reports", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,
				"NavigateToReportList");
	}

	public void NavigateToReport(String ReportID) throws Exception,
	IOException {
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://" + sSERVER + "/epace/company:" + sCOMPANY
				+ "/object/Report/detail/"+ReportID);
		CommonFunctions.Wait(_driver,
				By.name("testReportDefault_button"));
		
		String ID=CommonFunctions.GetText(_driver, By.xpath(".//label[text()='ID']/../../div/div"));
		if(!(ID.contains(ReportID)))
		{
			/*Assert.fail("Failed to find the report");*/
			
			System.err.println("Failed to find the report"+ReportID);
		}

	}

	public String getNextElementValue(String Element)
	{
		String Value;
		List<WebElement> Elementlist=_driver.findElements(By.xpath("//label[contains(text(),'"+Element+"')]/../../div/*"));
		for(WebElement Ele:Elementlist)
		{	
			if(Ele.getTagName().contains("input") && Ele.getAttribute("name").equalsIgnoreCase(Element) && Ele.getAttribute("type").equals("text"))					
			{
				Value=Ele.getAttribute("value");
				if(Value.equals(null))
				{
					Value=Ele.getText();
				}
				return Value;
			}
			else if(Ele.getTagName().contains("input") && Ele.getAttribute("type").equals("text"))
			{
				Value=Ele.getAttribute("value");
				if(Value.equals(null))
				{
					Value=Ele.getText();
				}
				return Value;
			}
			else if(Ele.getTagName().contains("div"))
			{

				Value=Ele.getText();

				return Value;
			}
			else if(Ele.getTagName().contains("textarea"))
			{

				Value=Ele.getText();

				return Value;
			}

			else if(Ele.getTagName().contains("select"))
			{
				Select sele = new Select(Ele);
				String sOption = sele.getFirstSelectedOption().getText();

				return sOption;
			}


		}
		return null;
	}

	public HashMap<String, String> GetAllParameters()			
	{	
		HashMap<String, String>  MapData = new HashMap<String, String>();

		String Value;
		int IntSize=_driver.findElements(By.xpath("//fieldset[1]/legend[text()='Parameters  ']/../div/*")).size();

		for(int i=1;i<IntSize+1;i++)						
		{
			try {

				List<WebElement> Elementlist=_driver.findElements(By.xpath(".//fieldset[1]/legend[text()='Parameters  ']/../div/div["+i+"]/div/div/..//label[@class='required']/../../div/*"));
				String Label=_driver.findElement(By.xpath("//fieldset[1]/legend[text()='Parameters  ']/../div/div["+i+"]/div/div/..//label[@class='required']")).getText();
				Label=Label.replaceAll(" ", "_");
				Label=Label.replaceAll("#", "");				
				Label=Label.replaceAll("\\W", "");
				for(WebElement Ele:Elementlist)
				{	
					if(Ele.getTagName().contains("input")  && Ele.getAttribute("type").equals("text") && Ele.getAttribute("name").contains("prompt"))					
					{
						Value=Ele.getAttribute("value");
						if(Value.equals(null))
						{
							Value=Ele.getText();
						}
						MapData.put(Label, Value);
						break;

					}
					else if(Ele.getTagName().contains("input") && Ele.getAttribute("type").equals("text") && Ele.getAttribute("name").contains("prompt"))
					{
						Value=Ele.getAttribute("value");
						if(Value.equals(null))
						{
							Value=Ele.getText();
						}
						MapData.put(Label, Value);
						break;
					}
					else if(Ele.getTagName().contains("div") && Ele.getAttribute("name").contains("prompt"))
					{

						Value=Ele.getText();

						MapData.put(Label, Value);
						break;
					}
					else if(Ele.getTagName().contains("textarea") && Ele.getAttribute("name").contains("prompt"))
					{

						Value=Ele.getText();

						MapData.put(Label, Value);
						break;
					}

					else if(Ele.getTagName().contains("select") && Ele.getAttribute("name").contains("prompt"))
					{
						Select sele = new Select(Ele);
						String sOption = sele.getFirstSelectedOption().getText();

						MapData.put(Label, sOption);
						break;
					}


				}
			} catch (NoSuchElementException e) {
				System.err.println("Unable to find  the element for record "+i);
			}
		}

		return MapData;

	}


	public  void  EditParameters(HashMap<String, String> MapData)			
	{	

		String Value;
		int IntSize=_driver.findElements(By.xpath("//fieldset[1]/legend[text()='Parameters  ']/../div/*")).size();


		for(String key:MapData.keySet())
		{
			for(int i=1;i<IntSize+1;i++)						
			{
				try {

					List<WebElement> Elementlist=_driver.findElements(By.xpath(".//fieldset[1]/legend[text()='Parameters  ']/../div/div["+i+"]/div/div/..//label[@class='required']/../../div/*"));
					String Label=_driver.findElement(By.xpath("//fieldset[1]/legend[text()='Parameters  ']/../div/div["+i+"]/div/div/..//label[@class='required']")).getText();
					Label=Label.replaceAll(" ", "_");
					Label=Label.replaceAll("#", "");				
					Label=Label.replaceAll("\\W", "");


					if(key.equals(Label))

					{
						for(WebElement Ele:Elementlist)
						{	
							if(Ele.getTagName().contains("input")  && Ele.getAttribute("type").equals("text") && Ele.getAttribute("name").contains("prompt"))					
							{
								Value=Ele.getAttribute("value");
								if(Value.equals(null))
								{
									Value=Ele.getText();
								}

								if(Value.equals(MapData.get(key)))
								{
									break;
								}
								else
								{
									Ele.clear();
									Ele.sendKeys(MapData.get(key));

								}


							}
							else if(Ele.getTagName().contains("input") && Ele.getAttribute("type").equals("text") && Ele.getAttribute("name").contains("prompt"))
							{
								Value=Ele.getAttribute("value");
								if(Value.equals(null))
								{
									Value=Ele.getText();
								}
								if(Value.equals(MapData.get(key)))
								{
									break;
								}
								else
								{
									Ele.clear();
									Ele.sendKeys(MapData.get(key));

								}
							}
							else if(Ele.getTagName().contains("div") && Ele.getAttribute("name").contains("prompt"))
							{

								Value=Ele.getText();

								if(Value.equals(MapData.get(key)))
								{
									break;
								}
								else
								{
									Ele.clear();
									Ele.sendKeys(MapData.get(key));

								}


							}
							else if(Ele.getTagName().contains("textarea") && Ele.getAttribute("name").contains("prompt"))
							{

								Value=Ele.getText();
								if(Value.equals(MapData.get(key)))
								{
									break;
								}
								else
								{
									Ele.clear();
									Ele.sendKeys(MapData.get(key));

								}

							}

							else if(Ele.getTagName().contains("select") && Ele.getAttribute("name").contains("prompt"))
							{
								Select sele = new Select(Ele);
								String sOption = sele.getFirstSelectedOption().getText();

								if(sOption.equals(MapData.get(key)))
								{
									break;
								}
								else
								{
									sele.selectByVisibleText(MapData.get(key));
									break;
								}
							}


						}

					} 
				}catch (NoSuchElementException e) {
					System.err.println("Unable to find  the element for record "+i);
				}
			}



		}


	}

}
