package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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

public class JobPlanningPage implements BaseElement
{

	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbconnection = new ReadAndUpdate();
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	private static  String sSERVER = null;
	private static  String sCOMPANY = null;
	private static String DownloadFolder = System.getProperty("user.dir").concat("\\InputTestData\\TempDownload");
	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public JobPlanningPage(WebDriver driver) throws Exception {
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

	public String  Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		Date date = new Date();
		String suniqueNumber = dateFormat.format(date);        
		return suniqueNumber;
	}

	public String UniqueNum3Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String sUniqueNumber = Integer.toString(random.nextInt(10))+chars[random.nextInt(chars.length)]+Integer.toString(random.nextInt(10));
		return sUniqueNumber;
	}

	public String UniqueNum4Digit()
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}

	public String UniqueNum5Digit()
	{
		Random random = new Random();
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();

		String sUniqueNumber = dateFormat.format(date) + chars[random.nextInt(chars.length)];
		return sUniqueNumber;
	}	

	public String isAlertPresent(String Accept)
	{ 
		boolean alertPresent = false;
		try { 
			Alert alert = _driver.switchTo().alert();
			alertPresent = true;
			return alert.getText();
		} catch (NoAlertPresentException Ex) { 
			return ""; 
		} finally {
			if (alertPresent && Accept.equalsIgnoreCase("accept"))
			{
				_driver.switchTo().alert().accept();
			}
		}
	}

	public void navigateToNewJobPlanPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPlan/addDirect");
		CommonFunctions.Wait(_driver, By.xpath("//*[@id=\"tabBar\"]/div/span/a"));
		assertEquals("Adding Job Plan", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToNewJobPlanPage");
		System.out.println("****Enter New Job Plan page appears****");
	}

	public void navigateToFindJobPlansPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPlan/list");
		CommonFunctions.Wait(_driver, By.xpath("//*[@id='buttonbox']/a"));
		assertEquals("Job Plans - All Statuses", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToFindJobPlansPage");
		System.out.println("****Find Job Plans page appears****");
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click();
		Thread.sleep(5000);
	}

	public void navigateToAddCostCenterPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/CostCenter/addNoDepartment");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add)));
		assertEquals("Adding Cost Center", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToAddCostCenterPage");
		System.out.println("****Adding Cost Center page appears****");
	}

	public void navigateToActivityCodeDetailPage(String ActivityCodeid) throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/ActivityCode/detail/"+ActivityCodeid+"?tab=0");


		System.out.println("****Naviageted to Activty code detail page****");
	}

	public void navigateToCostCenterMaintainance() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/CostCenter/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add)));
		assertEquals("Cost Centers", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToCostCenterMaintainancePage");
		System.out.println("****Naviagated to Cost Center Maintainance page****");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(1000);
	}

	public void navigateToManufacturingLocationMaintainance() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/ManufacturingLocation/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add)));
		assertEquals("Manufacturing Locations", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToManufacturingLocationMaintainancePage");
		System.out.println("****Naviagated to Manufacturing Location Maintainance page****");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(1000);
	}

	public void navigateTojobTypeListpage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobType/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add)));
		assertEquals("Job Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateTojobTypeListpage");
		System.out.println("****Naviagated to Job Types page****");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(1000);
	}

	public void navigateToAddNewManufacturingLocation() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/ManufacturingLocation/add");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Active_CheckBox)));
		assertEquals("Adding Manufacturing Location", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToAddNewManufacturingLocation");
		System.out.println("****Naviagated to Adding Manufacturing Location page****");
	}

	public void navigateToJobPlanSetupPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPlanSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.JobPlanSetup_AllowJobPlanRefresh)));
		assertEquals("Job Plan Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobPlanSetupPage");
		System.out.println("****Navigated to Job Plan Settings page****");
	}

	public void navigateToJobPlanScanningPage() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPlan/scan/001");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Job_plan_Scan_UpdatePlanRecords)));
		assertEquals("Job Plan Scanning", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobPlanScanningPage");
		System.out.println("****Job Plan Scanning page appears****");
	}

	public static class MyComparator implements Comparator<String>
	{
		private int maxLen;
		private static final String REGEX = "[0-9]+";
		public MyComparator(int maxLen) {
			this.maxLen = maxLen;
		}

		@Override
		public int compare(String obj1, String obj2) 
		{
			String o1 = obj1;
			String o2 = obj2;

			// both numbers
			if (o1.matches("[1-9]+") && o2.matches("[1-9]+")) {
				Integer integer1 = Integer.valueOf(o1);
				Integer integer2 = Integer.valueOf(o2);
				return integer1.compareTo(integer2);
			}

			// both string
			if (o1.matches("[a-zA-Z]+") && o2.matches("[a-zA-Z]+")) {
				return o1.compareTo(o2);
			}

			// alphanumeric, mix-case
			Pattern p = Pattern.compile(REGEX);
			Matcher m1 = p.matcher(o1);
			Matcher m2 = p.matcher(o2);

			List<String> list = new ArrayList<String>();
			while (m1.find()) {
				list.add(m1.group());
			}
			for (String string : list) 
			{
				o1.replaceFirst(string, leftPad(string, "0", maxLen));
			}

			list.clear();

			while (m2.find()) 
			{
				list.add(m2.group());
			}
			for (String string : list) 
			{
				o2.replaceFirst(string, leftPad(string, "0", maxLen));
			}

			return o1.toLowerCase().compareTo(o2.toLowerCase());
		}
	}

	public static String leftPad(String stringToPad, String padder, Integer size)
	{
		final StringBuilder strb = new StringBuilder(size.intValue());
		final StringCharacterIterator sci = new StringCharacterIterator(padder);
		while (strb.length() < (size.intValue() - stringToPad.length())) 
		{
			for (char ch = sci.first(); ch != CharacterIterator.DONE; ch = sci.next()) 
			{
				if (strb.length() < (size.intValue() - stringToPad.length())) 
				{
					strb.insert(strb.length(), String.valueOf(ch));
				}
			}
		}
		return strb.append(stringToPad).toString();
	}

	public String GetCellData(int rowNum, int colNum, String tableXPath)
	{
		String sObjData = "";
		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		try
		{
			List<WebElement> sFetchV = _driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/*"));
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
						String sCName =	 _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getAttribute("class");
						if(sCName.equals("wrappingLabel") || sCName.equals("label") || sCName.equals("notesWrap"))
						{
							sObjData =  _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div")).getText().trim();
							count++;

						}	
						else if(sCName.equals("viewLinkContainerGrid"))
						{
							sObjData =  _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div/a")).getText().trim();
							count++;
						}
					}
					else if(nodeName.equals("INPUT"))
					{
						String sType =	 _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).getAttribute("type");
						if(sType.equals(null) || sType.equals("") || sType.equals("text"))
						{
							sObjData =  _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input")).getAttribute("value").trim();
							count++;
						}
						else if(sType.equals("checkbox"))
						{
							boolean sFetchValue1=  _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input["+ChBxInd+"]")).isSelected();
							sObjData = "" +sFetchValue1;
							sObjData = sObjData.trim();
							count++;
						}
					}
					else if(nodeName.equals("SELECT"))
					{							
						sObjData =  CommonFunctions.GetSelectedOptionValue(_driver, By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/select")).trim();
						count++;
					}
				}
			}
		}  
		catch (Exception e) {
			e.printStackTrace();  		
			return sObjData;
		}
		return sObjData;
	}

	public List<String> GetColumnData (String sColumnName, String tableXPath)
	{
		String sFetchValue = "";
		int rowcount = 0, ColCount = 0;
		List <String> SortedList = new ArrayList<String>();
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();
			if (_driver.findElements(By.xpath("//select[@class='pageSelect']")).size() > 0)
			{
				Select dropDown = new Select(_driver.findElement(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")));
				java.util.List<WebElement> options = dropDown.getOptions();
				int iPages  = options.size();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);

				rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
				String sTotalRecord =_driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
				int sTotalRecords = Integer.parseInt(sTotalRecord);

				//fetch the values of the 
				for(int h = 3;h<=ColCount;h++)
				{
					String sCName1 = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();				 
					if(sCName1.equals(sColumnName))
					{
						if(rowcount>0 && sTotalRecords<1000)
						{
							for(int c = 1 ;c<=iPages;c++)
							{
								rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
								for(int j = 1 ;j<=rowcount;j++)
								{
									sFetchValue = GetCellData(j, h, tableXPath);
									SortedList.add(sFetchValue);					
								}							
								int d = c+1;
								String e = String.valueOf(d);
								if(iPages!=c)
								{
									CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), e);
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
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);
			}
			else
			{
				//fetch the values
				for(int h = 3;h<=ColCount;h++)
				{
					rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
					String sCName1 = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();				 
					if(sCName1.equals(sColumnName))
					{
						if(rowcount>0)
						{
							for(int j = 1 ;j<=rowcount;j++)
							{
								sFetchValue = GetCellData(j, h, tableXPath);							
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
			return null;
		}
	}

	public boolean removeSort (String sColumnName, String tableXPath)
	{
		int ColCount = 0;
		String sCName = "";
		boolean bRemoveSort = true;
		try
		{			
			//**********Remove Sort for the columns specified*******************
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			removeSorting(sColumnName, tableXPath);

			//verify the sort is removed
			if (!("ALL".equals(sColumnName.toUpperCase().trim())))
			{
				for(int i =3;i<=ColCount;i++)
				{	
					sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
					if(sCName.equals(sColumnName))
					{		
						if(_driver.findElements(By.xpath(tableXPath+"/tr/th["+i+"]/img[1][@alt='sorted ascending']")).size()>0 || _driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[1][@alt='sorted descending']")).size()>0)
						{					
							bRemoveSort = false;					
						}
						break;
					}
				}
			}			
			return bRemoveSort;
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
		}		
	}

	public void removeSorting (String ColumnName, String tableXPath)
	{
		int ColCount = 0;
		String sCName = "";
		try
		{			
			//**********Remove Sort for the columns specified*******************
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();

			if("ALL".equals(ColumnName.toUpperCase().trim()))
			{
				for (int i=1; i<ColCount+1; i++)
				{
					if (CommonFunctions.isElementPresent(_driver, By.xpath(tableXPath+"/thead/tr/th["+i+"]/a/span")))
					{
						_driver.findElement(By.xpath(tableXPath+"/thead/tr/th["+i+"]/a/span")).click();
						Thread.sleep(1000);
						break;
					}
				}
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[7]/a")).click();
			}
			else
			{
				for(int i =3;i<=ColCount;i++)
				{	
					sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
					if(sCName.equals(ColumnName))
					{		
						if(_driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[1][@alt='sorted ascending']")).size()>0 || _driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[1][@alt='sorted descending']")).size()>0)
						{					
							_driver.findElement(By.xpath(tableXPath+"/thead/tr/th["+i+"]/a/span")).click();
							Thread.sleep(1000);
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[5]/a")).click();
							Thread.sleep(2000);
						}
					}
				}
			}
			CommonFunctions.waitForPageLoaded(_driver);
			Thread.sleep(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void applySort (String sColumnName, String sSortType, String tableXPath)
	{
		try
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			if (_driver.findElements(By.xpath("//select[@class='pageSelect']")).size() > 0) {
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@class='pageSelect']"), "1"); 
			}

			Thread.sleep(1000);
			int ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();
			for(int i =3;i<=ColCount;i++)
			{	
				String sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();				
				if(sCName.equals(sColumnName))
				{
					String  ifColumnSorted = "";
					if (CommonFunctions.isElementPresent(_driver, By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/img")))
					{
						ifColumnSorted = CommonFunctions.getAttribute(_driver, By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/img"), "alt");
					}

					if(!ifColumnSorted.contains(sSortType))
					{
						_driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).click();
						Thread.sleep(2000);
						if("ascend".equals(sSortType.toLowerCase()))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[1]/a[text()='Sort Ascending']")).click();
							Thread.sleep(2000);
						}
						else if("descend".equals(sSortType.toLowerCase()))
						{
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[3]/a[text()='Sort Descending']")).click();
							Thread.sleep(2000);
						}
						break;
					}
					else
					{
						System.out.println("Column is already sorted in specified manner");
						break;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean SortAndVerify (String sSortType, String sColumnName, String tableXPath)
	{
		boolean result = true;
		int maxLen = 0;
		List <String> SortedList = new ArrayList<String>();
		List <String> SortedList1 = new ArrayList<String>();

		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}	

			removeSort("all", tableXPath);

			//****************Fetch the values and sort it as specified and compare*******************
			System.out.println("Fetch the Values from table before sorting");		
			SortedList = GetColumnData(sColumnName, tableXPath);

			//sort the list
			for (String string : SortedList) {
				if (string.length() > maxLen) {
					maxLen = string.length();
				}
			}
			if("ascend".equals(sSortType.toLowerCase()))
			{
				//sort the list in ascending order
				System.out.println(sColumnName+" values before sort "+SortedList);								
				Collections.sort(SortedList, new MyComparator(maxLen));
				System.out.println(sColumnName+" values after sort "+SortedList);
			}				 
			else if("descend".equals(sSortType.toLowerCase()))
			{
				//sort the list in descending order
				System.out.println(sColumnName+" values before sort "+SortedList);
				Collections.sort(SortedList, new MyComparator(maxLen));
				Collections.reverse(SortedList);
				System.out.println(sColumnName+" values after sort "+SortedList);
			}

			//sort the table
			System.out.println("Sort the Table");			
			applySort(sColumnName, sSortType, tableXPath);

			//fetch values after sorting
			System.out.println("Fetch the Values from table after sorting");
			SortedList1 = GetColumnData(sColumnName, tableXPath);
			System.out.println("After sorting "+SortedList1);

			//compare the lists
			System.out.println("Compare two array list");
			for(int k = 0;k<SortedList1.size();k++)
			{
				if (!(SortedList1.get(k).equals(SortedList.get(k))))
				{	
					result = false;
					System.out.println(SortedList1.get(k)+" is not equal to "+SortedList.get(k));					
				}
			}
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	public int SearchTable4Value (String ColumnName, String tableXPath, String Value)
	{
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			List<String> lColumnData  = GetColumnData (ColumnName, tableXPath);

			if (lColumnData.indexOf(Value) < 0)
			{
				return lColumnData.indexOf(Value);
			}
			else
			{
				return lColumnData.indexOf(Value)+1;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}		
	}

	public boolean SearchCostCenterbyCriteria(String SearchCriteria, String SearchValue)
	{
		System.out.println(Date()+": Searching Cost Center by '"+SearchCriteria+"'");	
		boolean result = false;
		try
		{
			if (!CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)))) {
				navigateToCostCenterMaintainance();
			}
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), SearchCriteria);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(SearchValue);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Cost_Center_Type)));

			if ("Cost Centers".equals(_driver.getTitle())) {
				if (launchTableElement(SearchValue, SearchCriteria, "")) {
					result = true;
				}
				else {
					result = false;
				}					
			}
			else {
				if (SearchCriteria.equals("ID")) 
				{
					String sTitle = "Cost Center "+SearchValue;
					result = (sTitle.equals(_driver.getTitle()));
				}
				else if (SearchCriteria.equals("Description")) 
				{
					String sDescription = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.Description)));
					result = (sDescription.equals(SearchValue));
				}
			}
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return result;
		}
	}  

	public boolean removeFilter (String sColumnName, String tableXPath)
	{
		int ColCount = 0;
		String sCName = "";
		boolean bRemoveSort = true;
		try
		{			
			//**********Remove Sort for the columns specified*******************
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();

			if("ALL".equals(sColumnName.toUpperCase().trim()))
			{
				for(int i =3;i<=ColCount;i++)
				{	
					if(_driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[@alt='filtered']")).size() > 0)
					{					
						_driver.findElement(By.xpath(tableXPath+"/thead/tr/th["+i+"]/a/span")).click();
						Thread.sleep(1000);
						_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[11]/a")).click();
						Thread.sleep(2000);
					}
				}
			}
			else
			{
				for(int i =3;i<=ColCount;i++)
				{	
					sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
					if(sCName.equals(sColumnName))
					{		
						if(_driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[@alt='filtered']")).size() > 0)
						{					
							_driver.findElement(By.xpath(tableXPath+"/thead/tr/th["+i+"]/a/span")).click();
							Thread.sleep(1000);
							_driver.findElement(By.xpath("//div[@id='gridSortControllerID']/ul/li[11]/a")).click();
							Thread.sleep(2000);
						}
						break;
					}
				}
			}
			CommonFunctions.waitForPageLoaded(_driver);

			//verify the filter is removed
			if("ALL".equals(sColumnName.toUpperCase().trim()))
			{
				for(int i =3;i<=ColCount;i++)
				{	
					if(_driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[@alt='filtered']")).size() > 0)
					{					
						bRemoveSort = false;
						break;
					}
				}
			}
			else
			{
				for(int i =3;i<=ColCount;i++)
				{	
					sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
					if(sCName.equals(sColumnName))
					{		
						if(_driver.findElements(By.xpath(tableXPath+"/thead/tr/th["+i+"]/img[@alt='filtered']")).size() > 0)
						{					
							bRemoveSort = false;					
						}
						break;
					}
				}
			}
			return bRemoveSort;
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
		}		
	}

	public int getColumnNum(String ColumnName, String tableXPath)
	{

		int ColCount = 0, colNum = 0;
		String sCName = "";
		try
		{			
			//**********Remove Sort for the columns specified*******************
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();			
			for(int i =3;i<=ColCount;i++)
			{	
				if(CommonFunctions.isElementPresent(_driver, By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")))
				{
					sCName = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+i+"]/a/span")).getText();
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

	public void applyFilter (int rowNum, int colNum, int filterOption, String value, String tableXPath)
	{
		Actions action = new Actions(_driver);
		WebElement wCellElement = null;

		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable);
		}

		try
		{	
			//mark the show grid filter checkbox as checked
			if (!_driver.findElement(By.xpath(Locators.getProperty(Locators.Show_Grid_Filter))).isSelected()) {
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Show_Grid_Filter))).click();
			}

			List<WebElement> sFetchV = _driver.findElements(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/*"));
			for(WebElement obj1 : sFetchV)
			{
				String nodeName = obj1.getAttribute("nodeName");  			
				if(nodeName.equals("DIV"))	{
					wCellElement = _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/div"));
				}										 
				else if(nodeName.equals("INPUT"))	{
					wCellElement = _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/input"));
				}
				else if(nodeName.equals("SELECT"))	{
					wCellElement = _driver.findElement(By.xpath(tableXPath+"/tbody/tr["+rowNum+"]/td["+colNum+"]/select"));
				}				
			}

			//hover on specified cell and select specified option
			action.moveToElement(wCellElement);
			action.perform();
			Thread.sleep(500);

			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Grid_Filter))))
			{
				switch (filterOption) {
				case 1 :						
					action.moveToElement(_driver.findElement(By.xpath("//a[@class='filterBySelection']")));
					action.click();
					action.perform();
					break;
				case 2 :
					action.moveToElement(_driver.findElement(By.xpath("//a[@class='filterExcludingSelection']")));
					action.click();
					action.perform();
					break;
				case 3 :
					action.moveToElement(_driver.findElement(By.xpath("//input[@id='filterFor_box']")));
					action.sendKeys(_driver.findElement(By.xpath("//input[@id='filterFor_box']")), value);
					Thread.sleep(1000);
					action.moveToElement(_driver.findElement(By.xpath("//a[@class='filterFor']")));
					action.click();
					action.perform();
					break;
				case 4 :
					action.moveToElement(_driver.findElement(By.xpath("//input[@id='filterLike_box']")));
					action.sendKeys(_driver.findElement(By.xpath("//input[@id='filterLike_box']")), value);
					Thread.sleep(1000);
					action.moveToElement(_driver.findElement(By.xpath("//a[@class='filterLike']")));
					action.click();
					action.perform();
					break;
				}
				Thread.sleep(2000);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public boolean filterExistforColumn (int colNum)
	{
		Actions action = new Actions(_driver);
		try 
		{			
			if (!_driver.findElement(By.xpath(Locators.getProperty(Locators.Show_Grid_Filter))).isSelected()) {
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Show_Grid_Filter))).click();
			}

			WebElement wCellElement = _driver.findElement(By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr[1]/td["+colNum+"]"));

			//hover on first cell
			action.moveToElement(wCellElement);
			action.perform();
			Thread.sleep(500);

			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Grid_Filter)))) {
				return true;
			}
			else {
				return false;
			}						
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	public boolean AddnewCostCenter (String CCId, String CCType, String DeptId, String PFClass, String ManufacturingLocation)
	{
		try
		{			
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
			CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Active_CheckBox)));
			assertEquals("Adding Cost Center", _driver.getTitle());

			_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(CCId);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Cost_Center_Type)), CCType);
			Thread.sleep(500);
			_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("CstCenter "+CCId+" description");
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Department)), DeptId);
			Thread.sleep(500);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Cost_Center_PrintFlow_Class)), PFClass);
			Thread.sleep(500);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Cost_Center_Manufacturing_Location)), ManufacturingLocation);
			Thread.sleep(500);

			if(!_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).isSelected())
			{
				_driver.findElement(By.name(Locators.getProperty(Locators.Active_CheckBox))).click(); 
			}
			_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
			Thread.sleep(3000);
			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text))))
			{
				String sTitle  = "Cost Center "+CCId;
				assertEquals(sTitle, _driver.getTitle());
				System.out.println("Created new Cost Center '"+CCId+"'");
				return true;
			}
			else
			{
				return false;
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to create new Cost Center");
			return false;
		}
	}

	public boolean DeleteCostCenter (String CostCenterID)
	{
		boolean sFlag = false;

		try
		{			
			String sCostCenterPage = "Cost Center "+CostCenterID ;				
			String sTitle = _driver.getTitle();	

			if(sCostCenterPage.equals(sTitle))
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
				Thread.sleep(1000);

				_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
				Thread.sleep(2000);
				sFlag =  CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));	  
			}
			else
			{
				sFlag = false;
				System.out.println(Date()+"Not Able to see Cost Center"); 
			}

			return sFlag;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to delete Cost Center");
			return sFlag;
		}
	}

	public boolean SearchActivityCodebyCriteria(String SearchCriteria, String SearchValue)
	{
		System.out.println(Date()+": Searching Activity Code by '"+SearchCriteria+"'");
		try
		{
			DCPage DC = new DCPage(_driver);

			if (!CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)))) {
				DC.NavigateToActivityCodeMaintainance();
			}

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), SearchCriteria);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(SearchValue);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			CommonFunctions.waitForPageLoaded(_driver);
			if ("Activity Codes".equals(_driver.getTitle())) {
				if (launchTableElement(SearchValue, SearchCriteria, "")) {
					return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.NonChargeableType_CostCenter)));
				}
				else {
					return false;
				}					
			}
			else {
				return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.NonChargeableType_CostCenter)));
			}							
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
		}
	}

	public boolean AddNewActivityCode(String CostCenter, String ActivityCode) throws Exception
	{
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_New_Record))).size()>0) 
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
			Thread.sleep(1000);
		}		

		if(_driver.getTitle().equals("Adding Activity Code"))
		{				
			_driver.findElement(By.name(Locators.getProperty(Locators.ID))).sendKeys(ActivityCode);
			_driver.findElement(By.name(Locators.getProperty(Locators.Description))).sendKeys("ActvtyCode "+ActivityCode);
			_driver.findElement(By.xpath("//input[contains(@name,'costCenter')]")).sendKeys(CostCenter);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.AC_CostingRates))).click();
			Thread.sleep(500);
			if (CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Cost_Center_CostMarkupCategory))))
			{
				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Cost_Center_CostMarkupCategory)), 1);
			}
			Thread.sleep(1000);
			_driver.findElement(By.xpath("//a[text()='Settings']")).click();
			Thread.sleep(500);
			_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
			Thread.sleep(5000);
			String sTitle = "Activity code "+ActivityCode;
			assertEquals(sTitle, _driver.getTitle());

			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
			{	
				System.out.println(Date()+"Activity Code Created");	
				return true;
			}
			else
			{
				System.err.println("Not able to Create Activity Code");
				return false;
			}		
		} 
		else 
		{				
			System.err.println("System did not navigate to Adding Activity Code page");	
			return false;
		}		
	}	

	public boolean launchTableElement (String value, String columnName, String tableXPath) throws Exception
	{
		String sOldTitle = _driver.getTitle();
		if (tableXPath.equals("")) {
			tableXPath = Locators.getProperty(Locators.Common_Webtable); 
		}

		int searchValueIndex = SearchTable4Value(columnName, tableXPath, value);
		if (searchValueIndex > 0)
		{
			if (searchValueIndex > 20) 
			{
				if (_driver.findElements(By.xpath("//select[@class='pageSelect']")).size() > 0)
				{
					String pageIndex = String.valueOf(searchValueIndex%20);
					String valueRowNum = String.valueOf(searchValueIndex/20);

					CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@class='pageSelect']"), pageIndex);
					Thread.sleep(1000);

					//click magnifying glass next to record
					_driver.findElement(By.xpath(tableXPath+"//tbody/tr["+valueRowNum+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);
				}
				else
				{
					//click magnifying glass next to record
					_driver.findElement(By.xpath(tableXPath+"//tbody/tr["+searchValueIndex+"]/td[2]/div/a/img")).click();
					Thread.sleep(2000);	
				}
			}
			else
			{
				//click magnifying glass next to record
				_driver.findElement(By.xpath(tableXPath+"//tbody/tr["+searchValueIndex+"]/td[2]/div/a/img")).click();
				Thread.sleep(2000);	
			}
		}
		else
		{
			System.err.println("The given entry '"+value+"' was not found in '"+columnName+"' column");
		}

		CommonFunctions.waitForPageLoaded(_driver);
		String sNewTitle = _driver.getTitle();
		if (sNewTitle.equals(sOldTitle))
		{
			return false;
		}
		else
		{
			return true;	
		}
	}

	public void expandJobPlanningSettings () throws Exception
	{
		if (!(_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).isDisplayed())) 
		{
			_driver.findElement(By.xpath("//legend[text()='"+Locators.getProperty(Locators.AC_Job_Planning_Setting)+"']/span")).click();
			Thread.sleep(500);
		}
	}

	public boolean editJobPlanningSettings (String planningIntegration, String jobPlanLevel, String planningTimeCalculation, String otherCheckbox) throws Exception
	{
		expandJobPlanningSettings();

		if (!planningIntegration.equals(""))	{
			if (planningIntegration.toLowerCase().trim().equals("planning")) {
				_driver.findElement(By.xpath("//input[@value='2' and @name='"+Locators.getProperty(Locators.AC_Job_Planning_Setting_PlanningIntegration)+"']")).click();
			}
			else if (planningIntegration.toLowerCase().trim().equals("scheduling")) {
				_driver.findElement(By.xpath("//input[@value='1' and @name='"+Locators.getProperty(Locators.AC_Job_Planning_Setting_PlanningIntegration)+"']")).click();
			}
		}

		if (!jobPlanLevel.equals(""))	{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_JobPlanLevel)), jobPlanLevel);
		}

		if (!planningTimeCalculation.equals(""))	{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_PlanningTimeCalculation)), planningTimeCalculation);
		}

		if (!otherCheckbox.equals(""))	{
			String[] CheckBoxes =  otherCheckbox.split(";");			
			for (String checkbox : CheckBoxes)	{
				if (checkbox.equals("Update Job Part Location"))	{
					if (!_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_UpdateJobPartLocation))).isSelected())	{
						_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_UpdateJobPartLocation))).click();
					}
				}
				else if (checkbox.equals("Ask If Complete"))	{
					if (!_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).isSelected())	{
						_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_Ask_If_Complete))).click();
					}
				}
				else if (checkbox.equals("Plan By Pass"))	{
					if (!_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_PlanByPass))).isSelected())	{
						_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_PlanByPass))).click();
					}
				}
				else if (checkbox.equals("Update Planning"))	{
					if (!_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_UpdatePlanning))).isSelected())	{
						_driver.findElement(By.name(Locators.getProperty(Locators.AC_Job_Planning_Setting_UpdatePlanning))).click();
					}
				}
			}
		}

		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Update))).click();
		Thread.sleep(1000);		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Department_Updated_text))))	{
			return true;
		}
		else {
			return true;
		}		
	}

	public boolean DeleteActivtyCode (String ActivityCode)
	{
		boolean sFlag = false;

		try
		{			
			String sActivityCodePage = "Activity code "+ActivityCode;
			String sTitle = _driver.getTitle();	

			if(sActivityCodePage.equals(sTitle))
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
				Thread.sleep(1000);

				_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
				Thread.sleep(2000);
				sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));
			}
			else
			{
				sFlag = false;
				System.out.println(Date()+"Not Able to see Activity Code"); 
			}

			return sFlag;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to delete Activity Code");
			return sFlag;
		}
	}

	public boolean SearchManufacturingLocationbyCriteria(String SearchCriteria, String SearchValue)
	{
		System.out.println(Date()+": Searching Manufacturing Location by '"+SearchCriteria+"'");
		try
		{
			if (!CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)))) {
				navigateToManufacturingLocationMaintainance();
			}

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), SearchCriteria);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(SearchValue);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			if ("Manufacturing Locations".equals(_driver.getTitle())) {
				if (launchTableElement(SearchValue, SearchCriteria, "")) {
					return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Manufacturing_LocationCode)));
				}
				else {
					return false;
				}					
			}
			else {
				return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Manufacturing_LocationCode)));
			}							
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
		}
	}

	public boolean verifyNewBlankManufacturingLocationRecord ()
	{
		int iRwCnt = _driver.findElements(By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr")).size();	

		String ML_Code = GetCellData(iRwCnt, 3, "");
		String ML_SLT = GetCellData(iRwCnt, 4, "");
		String ML_Desc = GetCellData(iRwCnt, 5, "");
		String ML_SI = GetCellData(iRwCnt, 6, "");
		String ML_MLIU = GetCellData(iRwCnt, 7, "");		

		if (ML_Code.equals("") && ML_SLT.equals("") && ML_Desc.equals("") && ML_SI.equals("1") && ML_MLIU.equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}		
	}

	public boolean AddNewManufacturingLocationinGrid (String Code, String SiteLicenseType, String SchedulerInstance, String MaxLoggedInUsers) throws Exception
	{		
		navigateToManufacturingLocationMaintainance();		
		_driver.findElement(By.name(Locators.getProperty(Locators.Job_Component_Add_In_Grid))).click();
		Thread.sleep(500);		

		if (verifyNewBlankManufacturingLocationRecord())
		{
			int iRwCnt = _driver.findElements(By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr")).size();
			CommonFunctions.SendValue(_driver, By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr["+iRwCnt+"]/td[3]/input"), Code);
			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr["+iRwCnt+"]/td[4]/select"), SiteLicenseType);
			CommonFunctions.SendValue(_driver, By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr["+iRwCnt+"]/td[5]/input"), Code+" added by automation");
			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr["+iRwCnt+"]/td[6]/select"), SchedulerInstance);
			CommonFunctions.SendValue(_driver, By.xpath(Locators.getProperty(Locators.Common_Webtable)+"/tbody/tr["+iRwCnt+"]/td[7]/input"), MaxLoggedInUsers);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Update))).click();
			Thread.sleep(2000);
			return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		}
		else
		{
			System.err.println("No new blank record was displayed");
			return false;
		}		
	}

	public boolean AddNewManufacturingLocation (String Code, String SiteLicenseType, String SchedulerInstance, String MaxLoggedInUsers) throws Exception
	{	
		if (!("Adding Manufacturing Location".equals(_driver.getTitle())))	{
			navigateToAddNewManufacturingLocation();	}

		if ("Adding Manufacturing Location".equals(_driver.getTitle()))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Manufacturing_LocationCode)), Code);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location_SiteLicenseType)), SiteLicenseType);
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Description)), Code+" added by automation");
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location_SchedulerInstance)), SchedulerInstance);
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location_MaximumLoggedInUsers)), MaxLoggedInUsers);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
			Thread.sleep(2000);
			return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		}
		else
		{
			System.err.println("Add New Manufacturing Location Page was not displayed");
			return false;
		}
	}

	public boolean DeleteManufacturingLocation (String ManufacturingLocation)
	{
		boolean sFlag = false;

		try
		{			
			if(CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Delete))))
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
				Thread.sleep(1000);

				_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
				Thread.sleep(2000);
				sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));	  
			}
			else
			{
				sFlag = false;
				System.out.println(Date()+"Not Able to see Manufacturing Location"); 
			}

			return sFlag;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to delete Manufacturing Location");
			return sFlag;
		}
	}

	public boolean AddNewJobType(String Description, String JacketType, String JobNumberSequence) throws Exception
	{		
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_New_Record))).size()>0) 
		{
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_New_Record))).click();
			Thread.sleep(1000);
		}

		if(_driver.getTitle().equals("Adding Job Type")) 
		{				
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Description)), Description);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Type_JacketType)), JacketType);
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Type_JobNumberSequence)), JobNumberSequence);
			_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
			Thread.sleep(3000);
			String sTitle = "JobType: "+Description;
			assertEquals(sTitle, _driver.getTitle());

			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
			{	
				System.out.println(Date()+"Job Type Created");	
				return true;
			}
			else
			{
				System.err.println("Not able to Job Type");
				return false;
			}		
		} 
		else 
		{				
			System.err.println("System did not navigate to Adding Job Type page");	
			return false;
		}		
	}	

	public boolean SearchJobTypebyCriteria(String SearchCriteria, String SearchValue)
	{
		System.out.println(Date()+": Searching Job Type by '"+SearchCriteria+"'");
		try
		{
			if (!CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)))) {
				navigateTojobTypeListpage();
			}

			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), SearchCriteria);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(SearchValue);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			CommonFunctions.waitForPageLoaded(_driver);
			if ("Job Types".equals(_driver.getTitle())) {
				if (launchTableElement(SearchValue, SearchCriteria, "")) {
					return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Description)));
				}
				else {
					return false;
				}					
			}
			else {
				return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Description)));
			}							
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
		}
	}

	public boolean DeleteJobType (String jobTypeDesc)
	{
		boolean sFlag = false;

		try
		{			
			String sJobTypePage = "JobType: "+jobTypeDesc ;				
			String sTitle = _driver.getTitle();	

			if(sJobTypePage.equals(sTitle))
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
				Thread.sleep(1000);

				_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
				Thread.sleep(2000);
				sFlag = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));	  
			}
			else
			{
				sFlag = false;
				System.out.println(Date()+"Not Able to see Job Type detail page"); 
			}

			return sFlag;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to delete Job Type");
			return sFlag;
		}
	}

	public String getUniqueNumDigit(String Item, int Length) throws Exception
	{
		String sUniqueNumber = "", sURL = "";

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

	public static boolean URLExists(String URLName)
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

	public boolean SearchJobPart(String sJob,String sPart) throws Exception
	{
		String sJobPartPage = "Job "+sJob+" part "+sPart;
		System.out.println(Date()+": Job Part Page  is " + sJobPartPage);
		sCOMPANY =  _properties.getProperty(GUI_automation_properties.COMPANY);
		String sXpath = "//a[@href='/epace/company:"+sCOMPANY+"/object/JobPart/detail/"+sJob+"%3A"+sPart+"']/img";
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(sJob);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);

		_driver.findElement(By.xpath(sXpath)).click();
		CommonFunctions.Wait(_driver,By.xpath(Locators.getProperty(Locators.Job_Materials_Link)));
		String sTitle = _driver.getTitle();
		System.out.println(Date()+"sTitle " + sTitle);

		if(sJobPartPage.equals(sTitle))
		{
			assertEquals(sJobPartPage, _driver.getTitle());
			System.out.println(Date()+": Able to see Job Part");			
			return true;
		}
		else
		{
			System.out.println(Date()+": Not Able to see Job Part");				
			return false;
		}		
	}

	public boolean AddNewJobPlan(String Job, String JobPart, String Activity, String ManufacturingLoc, String PlannedHours, String Priority, String Status) throws Exception
	{		
		if (!"Adding Job Plan".equals(_driver.getTitle()))	{
			navigateToNewJobPlanPage();
		}

		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), Job);
		_driver.findElement(By.name(Locators.getProperty(Locators.Planned_Hours))).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Job_Part)), Job+":"+JobPart);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Manufacturing_Location)), ManufacturingLoc);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.JobPlanning_ActivityCode)), Activity);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Emp_Status)), Status);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Planned_Hours)), PlannedHours);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Priority)), Priority);

		_driver.findElement(By.name(Locators.getProperty(Locators.Add))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		assertEquals("Job Plan", _driver.getTitle());

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Object_added_text))).size()>0)
		{	
			System.out.println(Date()+"Job Plan for Job "+Job+" with activity code "+Activity+" was Created successfully");	
			return true;
		}
		else
		{
			System.err.println(Date()+"Job Plan was not created");
			return false;
		}	
	}

	public boolean SearchJobPlan(String sJob,String sActivityCode) throws Exception
	{
		int iRowNum = 0;
		if (!("Job Plans - All Statuses".equals(_driver.getTitle())))	{
			navigateToFindJobPlansPage();
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(2000);

		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(sJob);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);

		if ("Job Plans - All Statuses".equals(_driver.getTitle())) 	{
			List<String> lColumnData  = GetColumnData("Planned Activity", "//table[@id='AllStatus']");
			for(int k = 0;k<lColumnData.size();k++)	{
				if (lColumnData.get(k).contains(sActivityCode))	{	
					iRowNum = k+1;		
					break;
				}
			}

			if (iRowNum > 0)	{
				if (iRowNum > 20)	{				
					if (_driver.findElements(By.xpath("//select[@class='pageSelect']")).size() > 0)	{
						String pageIndex = String.valueOf(iRowNum%20);
						iRowNum = iRowNum/20;

						CommonFunctions.selectDropdownByText(_driver, By.xpath("//select[@class='pageSelect']"), pageIndex);
						Thread.sleep(1000);
					}
				}				
				_driver.findElement(By.xpath("//table[@id='AllStatus']/tbody/tr["+iRowNum+"]/td[2]/div/div")).click();
				Thread.sleep(1000);
				_driver.findElement(By.xpath("//a[text()='- Detail Direct']")).click();
				Thread.sleep(3000);				
				CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Planned_Hours)));
			}			
		}

		if("Job Plan".equals(_driver.getTitle()))	{
			System.out.println(Date()+": Job Plan deatil Direct page is launched");			
			return true;
		}
		else	{
			System.err.println(Date()+": Job Plan deatil Direct page was not displayed");				
			return false;
		}
	}

	public boolean DeleteJobPlan()
	{
		boolean sFlag = false;

		try
		{			
			String sJobPlanDetailDirectPage = "Job Plan";
			String sTitle = _driver.getTitle();	

			if(sJobPlanDetailDirectPage.equals(sTitle))
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Employee_Delete))).click();
				Thread.sleep(1000);

				_driver.findElement(By.cssSelector(Locators.getProperty(Locators.Employee_Delete_Popup))).click();
				Thread.sleep(2000);
				sFlag =  CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Deleted_text)));	  
			}
			else
			{
				sFlag = false;
				System.out.println(Date()+"Not Able to see Job Plan"); 
			}

			return sFlag;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to delete Job Plan");
			return sFlag;
		}
	}

	public void EnterJobPlanScanDetails(String StartDate, String ActivityCode, String sPriority, String JobPlanID, String Job, String JobPart, String PlannedHours, String Status, String AssignedTo, String ControlScan, boolean Update) throws Exception
	{
		if (!("Job Plan Scanning".equals(_driver.getTitle())))	{
			navigateToJobPlanScanningPage();
		}

		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Start_Date)), StartDate);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.JobPlanning_ActivityCode)), ActivityCode);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Priority)), sPriority);

		if (!JobPlanID.equals(""))		{
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_ID)+"']"), JobPlanID);
		}

		if (!Job.equals("") && !JobPart.equals(""))		{
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_Job)+"']"), Job);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_Part)+"']"), JobPart);			
		}

		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_PlannedHours)+"']"), PlannedHours);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_Status)+"']"), Status);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_AssignedTo)+"']"), AssignedTo);
		CommonFunctions.SendValue(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]//input[@name='"+Locators.getProperty(Locators.Job_Plan_Scan_ControlScan)+"']"), ControlScan);

		if (Update)	{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Job_plan_Scan_UpdatePlanRecords)));
			Thread.sleep(1000);
			CommonFunctions.waitForPageLoaded(_driver);
		}		
	}

	public boolean VerifyEmailRequiredFields () throws Exception
	{
		String sEmail = "qaautomation@efi.com", sSubject = "", sReplyToName = "", sReqFieldErrMsg = "";
		boolean VerifiedEmailFields = true;

		//Required Fields - Recipient
		System.out.println("\nOn the Email Details Page - Fill in all the details except the Recipient field");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)), sEmail);
		sSubject = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).getAttribute("value");
		sReplyToName = _driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name))).getAttribute("value");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);

		boolean bErrMsg = CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Error_Message)));
		if (bErrMsg)
		{
			assertEquals("Check Again\nAt least one recipient with a valid email address must be selected.", _driver.findElement(By.xpath(Locators.getProperty(Locators.Error_Message))).getText());
			System.out.println("The folllowing error message was displayed : "+_driver.findElement(By.xpath(Locators.getProperty(Locators.Error_Message))).getText());
		}
		else
		{
			System.err.println("No error message was displayed for blank 'Recipient' field");
			VerifiedEmailFields = false;
		}

		//Required Fields - Reply To address		
		System.out.println("\nOn the Email Details Page - Fill in all the details - for the Reply To address Leave it blank & Click");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img))).click();
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.PO_EMail_TO_TextField)), sEmail);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);			

		sReqFieldErrMsg = isAlertPresent("accept");
		if (!sReqFieldErrMsg.equals("") && sReqFieldErrMsg.equals("The following error(s) occurred:\n - The Reply To field is required."))
		{
			System.out.println("The folllowing error message was displayed : "+sReqFieldErrMsg);
		}
		else
		{
			System.err.println("No error message was displayed for blank 'Reply To' field");
			VerifiedEmailFields = false;
		}	

		//Required Fields - Reply To address
		System.out.println("\nOn the Email Details Page - Fill in all the details - For the Reply To address 'Send Email' enter an invalid email address format & Click 'Send Email' button");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)), "invalid.com");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);			

		sReqFieldErrMsg = isAlertPresent("accept");
		if (!sReqFieldErrMsg.equals("") && sReqFieldErrMsg.equals("Invalid Reply To address. An e-mail address must contain the @ symbol."))
		{
			System.out.println("The folllowing error message was displayed : "+sReqFieldErrMsg);				
		}
		else
		{
			System.err.println("No error message was displayed for invalid 'Reply To' field");
			VerifiedEmailFields = false;
		}

		//Required Fields - Reply To Name
		System.out.println("\nOn the Email Details Page - Fill in all the details - For the Reply To Name, leave it blank & Click 'Send Email' button");
		Thread.sleep(2000);
		isAlertPresent("accept");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).sendKeys("1");
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).sendKeys(Keys.CONTROL + "a");			
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).sendKeys(Keys.DELETE);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)), sEmail);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).sendKeys(Keys.TAB);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To))).sendKeys(Keys.TAB);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)), sEmail);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);

		sReqFieldErrMsg = isAlertPresent("accept");
		if (!sReqFieldErrMsg.equals("") && sReqFieldErrMsg.equals("The following error(s) occurred:\n - The Reply To Name field is required."))
		{
			System.out.println("The folllowing error message was displayed : "+sReqFieldErrMsg);			
		}
		else
		{
			System.err.println("No error message was displayed for blank 'Reply To Name' field");
			VerifiedEmailFields = false;
		}

		//Required Fields - Subject
		System.out.println("\nOn the Email Details Page - Fill in all the details - For the Subject, leave it blank & Click 'Send Email' button");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To_Name)), sReplyToName);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Subject))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);

		sReqFieldErrMsg = isAlertPresent("accept");
		if (!sReqFieldErrMsg.equals("") && sReqFieldErrMsg.equals("The following error(s) occurred:\n - The Subject field is required."))
		{
			System.out.println("The folllowing error message was displayed : "+sReqFieldErrMsg);			
		}
		else
		{
			System.err.println("No error message was displayed for blank 'Subject' field");
			VerifiedEmailFields = false;
		}

		//Required Fields - Body	
		System.out.println("\nOn the Email Details Page - Fill in all the details - For the Body, leave it blank & Click 'Send Email' button");
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Subject)), sSubject);
		_driver.findElement(By.name(Locators.getProperty(Locators.PO_Email_Body))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(1000);

		sReqFieldErrMsg = isAlertPresent("accept");
		if (!sReqFieldErrMsg.equals("") && sReqFieldErrMsg.equals("The following error(s) occurred:\n - The Body field is required."))
		{
			System.out.println("The folllowing error message was displayed : "+sReqFieldErrMsg);
		}
		else
		{
			System.err.println("No error message was displayed for blank 'Body' field");
			VerifiedEmailFields = false;
		}

		return VerifiedEmailFields;
	}

	public void deleteFileinDownloadFolder (String fileName)
	{
		String ExcelFilePath = DownloadFolder+"\\"+fileName;
		System.out.println(ExcelFilePath);
		File ExcelFile = new File(ExcelFilePath);
		if (ExcelFile.exists())
		{
			ExcelFile.delete();
		}
	}

	public void navigateToCompanySetup () throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Company/detail/001");
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Company Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToCompanySetup");
		System.out.println("****Company Setup page appears****");
	}

	public boolean SendEmail (String emailAddress) throws Exception
	{
		System.out.println("Fill in the details on the 'Company Email' page & Click 'Send Email' button & verify the email");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.PO_EMail_TO_img))).click();
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.id(Locators.getProperty(Locators.PO_EMail_TO_TextField)), emailAddress);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PO_Email_Reply_To)), emailAddress);		
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SendEmail))).click();
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Success_Message)));
	}

	public String getJobIDfromURL ()
	{
		String sJobID = "";

		String sURL = _driver.getCurrentUrl();
		String[] aURL = sURL.split("detail/");
		sJobID = aURL[1].replace("?tab=0", "").trim();

		return sJobID;
	}

	public void SimpleEstimateCreation(String sEstNum,String sCust,String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String sPrePress,String sFinish,String sPress, String sInv) throws Exception
	{
		EstimationPage EP = new EstimationPage(_driver);
		if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "DigitalPrint");
			if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			{
				CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Digital");
				Thread.sleep(2000);
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
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		if (sPrePress.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), sPrePress);
		}			
		Thread.sleep(1000);
		if (sFinish.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Finishing)), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Finishing)), sFinish);
		}
		Thread.sleep(1000);
		if (sPress.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Press)), 1);
		}
		else
		{
			//				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Press)), sPress);
		}
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		Thread.sleep(1000);

		if (!sInv.equals(""))
		{
			EP.PleaseSelectCorrectInventoryItem("id",sInv,"//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]");
		}

		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys("4");
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys("4");
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys("4");
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Front)), "3");
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Back)), "3");
	}


	public void SimpleEstimateCreation_WF3(String sEstNum,String sCust,String sCat,String sProd,String sFinaleSizeW,String sFinaleSizeH,String sQty1,String sQty1Desc,String sPrePress,String sFinish,String sPress, String sInv) throws Exception
	{
		EstimationPage EP = new EstimationPage(_driver);
		if(_driver.findElements(By.name("productionType")).size()>0)
		{
			CommonFunctions.selectDropdownByText(_driver, By.name("productionType"), "Offset");
			//				if(_driver.findElements(By.name("userInterfaceSet")).size()>0)
			//				{
			//					CommonFunctions.selectDropdownByText(_driver, By.name("userInterfaceSet"), "Offset");
			//					Thread.sleep(2000);
			//				}
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
		//			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Category)), sCat);
		//			Thread.sleep(2000);
		//			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Estimate_Product)), sProd);
		//			Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name("compositeProduct"), sProd);
		Thread.sleep(2000);
		_driver.findElement(By.name("compositePages")).sendKeys("64");

		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_W))).sendKeys(sFinaleSizeW);
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_FinalSize_H))).sendKeys(sFinaleSizeH);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).clear();
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_1))).sendKeys(sQty1);
		_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Qty_Desc))).sendKeys(sQty1Desc);
		if (sPrePress.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_PrePress)), sPrePress);
		}			
		Thread.sleep(1000);
		if (sFinish.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Finishing)), 1);
		}
		else
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Finishing)), sFinish);
		}
		Thread.sleep(1000);
		if (sPress.equals(""))
		{
			//				CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.Est_Press)), 1);
		}
		else
		{
			//				CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Est_Press)), sPress);
		}
		Thread.sleep(1000);
		//			_driver.findElement(By.xpath(Locators.getProperty(Locators.Est_Material_Tab))).click();
		//			Thread.sleep(1000);

		if (!sInv.equals(""))
		{
			EP.PleaseSelectCorrectInventoryItem("id",sInv,"//div/h4[contains(label,'Inventory Item')]/following-sibling::div/div[1]");
		}

		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_S1))).sendKeys("4");
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_S2))).sendKeys("4");
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).clear();
		//			_driver.findElement(By.name(Locators.getProperty(Locators.Est_Total))).sendKeys("4");
		//			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Front)), "3");
		//			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Est_Ink_Coverage_Back)), "3");
	}


	public void modifyEstPrepressOp(int RwNum, String PrepressItem, String Quantity, String Hours) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='e']"));
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='estimatePrepressOp']/tbody/tr["+RwNum+"]/td[2]/div/a/img"));
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();				 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Estimate Prepress Operation")) 
				{ 
					if (!(PrepressItem.equals("")))
					{
						CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.prepressItem"), PrepressItem);
						Thread.sleep(1000);
					}
					if (!(Quantity.equals("")))
					{
						CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.quantity"), Quantity);
					}
					if (!(Hours.equals("")))
					{
						CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.hours"), Hours);
					}
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.UpdateAndRefresh)));
					Thread.sleep(2000);
					_driver.switchTo().window(sOriginalHandle);
					CommonFunctions.waitForPageLoaded(_driver);
					break;
				}
			}
		}
		else
		{
			System.err.println("prepress operation window was not displayed");
		}	
	}

	public void AddEstPrepressOp(String PrepressItem, String Quantity, String Hours) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='e']"));
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset [@id='estimatePrepressOp_fieldset']/div/div[1]//a[text()='Add']"));
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();				 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Estimate Prepress Operation"))
				{ 
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.prepressItem"), PrepressItem);
					Thread.sleep(1000);
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.quantity"), Quantity);					
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.hours"), Hours);					
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh)));
					Thread.sleep(2000);
					_driver.switchTo().window(sOriginalHandle);
					CommonFunctions.waitForPageLoaded(_driver);
					break;
				}
			}
		}
		else
		{
			System.err.println("prepress operation window was not displayed");
		}	
	}

	public void AddEstFinishingOp(String FinishOp, String Quantity, String Hours) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='F']"));
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div/div[1]//a[text()='Add Finishing Operation']"));
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();				 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Estimate Finishing Operation"))
				{ 
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.finishingOperation"), FinishOp);
					Thread.sleep(1000);
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.quantity"), Quantity);					
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.hours"), Hours);					
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh)));
					Thread.sleep(2000);
					_driver.switchTo().window(sOriginalHandle);
					CommonFunctions.waitForPageLoaded(_driver);
					break;
				}
			}
		}
		else
		{
			System.err.println("Finishing operation window was not displayed");
		}	
	}

	public void AddEstShipingOp(String ShippingOp, String Quantity, String Hours) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//span[text()='F']"));
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath("//fieldset[@id='estimateFinishingOpPart_fieldset']/div/div[1]//a[text()='Add Shipping Operation']"));
		Thread.sleep(2000);
		Set<String> availableWindows = _driver.getWindowHandles();				 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Estimate Ship/Mail	 Operation"))
				{ 
					CommonFunctions.selectDropdownByText(_driver, By.name("EstimatePrepressOperations.finishingOperation"), ShippingOp);
					Thread.sleep(1000);
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.quantity"), Quantity);					
					CommonFunctions.SendValue(_driver, By.name("EstimatePrepressOperations.hours"), Hours);					
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_Refresh)));
					Thread.sleep(2000);
					_driver.switchTo().window(sOriginalHandle);
					CommonFunctions.waitForPageLoaded(_driver);
					break;
				}
			}
		}
		else
		{
			System.err.println("Shipping operation window was not displayed");
		}	
	}

	public Boolean ClickTable4Value (String ColumnName, String tableXPath, String Value,String ElementToWait)
	{
		Boolean IsElementClick=false;
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}  			  		
			IsElementClick= GetClickColumnData (ColumnName, tableXPath,Value,ElementToWait);
			return IsElementClick;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	public Boolean GetClickColumnData (String sColumnName, String tableXPath,String Value,String xpath )
	{
		String sFetchValue = "";
		int rowcount = 0, ColCount = 0;
		List <String> SortedList = new ArrayList<String>();
		Boolean RecordFound=false;
		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable); 
			}

			ColCount = _driver.findElements(By.xpath(tableXPath+"/thead/tr[1]/th")).size();
			if (_driver.findElements(By.xpath("//select[@class='pageSelect']")).size() > 0)
			{
				Select dropDown = new Select(_driver.findElement(By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")));
				java.util.List<WebElement> options = dropDown.getOptions();
				int iPages  = options.size();
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);

				rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
				String sTotalRecord =_driver.findElement(By.xpath("//div[@id='scrollableContent']/div[@id='grid-contents']/div/div[1]/div[1]/strong")).getText();
				int sTotalRecords = Integer.parseInt(sTotalRecord);


				for(int h =3;h<=ColCount;h++)
				{	String sCName1 ;
				try {								
					sCName1 = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();
				} catch (NoSuchElementException e) {
					sCName1="";
				}

				if(sCName1.equals(sColumnName))
				{
					if(rowcount>0 && sTotalRecords<1000)
					{
						for(int c = 1 ;c<=iPages;c++)
						{
							rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
							for(int j = 1 ;j<=rowcount;j++)
							{
								sFetchValue = GetCellData(j, h, tableXPath);
								if(sFetchValue.equals(Value))	
								{  

									_driver.findElement(By.xpath(tableXPath+"/tbody/tr["+j+"]/td[3]/div/a/img")).click();										
									CommonFunctions.Wait(_driver, By.xpath(xpath));
									Thread.sleep(2000);

									if(CommonFunctions.isElementPresent(_driver, By.xpath(xpath)))
									{	
										return true;
									}
									else
									{
										return false;
									}


								}
							}							
							int d = c+1;
							String e = String.valueOf(d);
							if(iPages!=c)
							{
								CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), e);
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
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']"), "1");
				Thread.sleep(1000);
			}
			else
			{
				//fetch the values
				for(int h = 3;h<=ColCount;h++)
				{
					rowcount = _driver.findElements(By.xpath(tableXPath+"/tbody/tr")).size();
					String sCName1 = _driver.findElement(By.xpath(tableXPath+"/thead/tr[1]/th["+h+"]/a/span")).getText().trim();				 
					if(sCName1.equals(sColumnName))
					{
						if(rowcount>0)
						{
							for(int j = 1 ;j<=rowcount;j++)
							{
								sFetchValue = GetCellData(j, h, tableXPath);							
								if(sFetchValue.equals(Value))	
								{	
									RecordFound=true;
									_driver.findElement(By.xpath(tableXPath+"/tbody/tr["+j+"]/td[3]/div/a/img")).click();

									CommonFunctions.Wait(_driver, By.xpath(xpath));
									Thread.sleep(2000);
									if(CommonFunctions.isElementPresent(_driver, By.xpath(xpath)))
									{	
										return true;
									}
									else
									{
										return false;
									}
								}			
							}					
						}
						else
						{
							Assert.fail("RowCount is less then 0"+rowcount);
						}
					}					
				}	
			}
			return RecordFound;
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return null;
		}
	}

	public String getJobParts_Validation(String Quantity, String Description, String ShipContact)
	{
		String Flag=null;
	 String qtyVal=	_driver.findElement(By.name("qtyOrdered")).getText();
		if (Quantity.equals(qtyVal))
		{
			Flag="True";
		}
		else
		{
			System.err.println("Part Quantity displayed is not correct");
			Flag= "False";
		}
		
	String Desc=_driver.findElement(By.xpath(".//input[@name='description']")).getText();
		if 	(Description.equals(Desc))
		{
			Flag="True";
		}
		else
		{
			Flag= "False";
			System.err.println("Part Description is not correct");
		}
	String sContact= _driver.findElement(By.name("shipToContact")).getText();	
		if(ShipContact.equals(sContact))
		{
			Flag="True";
		}
		else
		{
			Flag= "False";
			System.err.println("Part Shipping contact is not correct");
		}
	  
		return Flag;
		
	}
	
	public String getJobPart_Inventory_Order_item(String ItemDescription)
	{
		String Flag=null;
		String idesc= _driver.findElement(By.xpath(".//table[contains(@id, 'JobMaterial')]/tbody/tr/td[5]/div")).getText();
		if (ItemDescription.equals(idesc))
		{
			Flag= "True";
		}
		else
		{
			System.err.println("Item Description is not displayed correctly");
		}
		
		
		
		return NewFileNamePath;
		
	}
	
	public String getJobPlans() throws Exception
	{
		String sJobPlans = "";

		int iJobPlanCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr"));
		int iPlanIDCol = getColumnNum("ID", "//table[@id='JobPlan']");
		for (int i=1; i<=iJobPlanCnt; i++)
		{
			if(i==1)
			{
				sJobPlans = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr["+i+"]/td["+iPlanIDCol+"]/div"));
			}
			else
			{
				sJobPlans = sJobPlans+";"+CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr["+i+"]/td["+iPlanIDCol+"]/div"));
			}
		}

		return sJobPlans.trim();
	}

	public String getJPPFIDs() throws Exception
	{
		String sJPPFIDs = "";
		int iCount = 0;

		int iJobPlanCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr"));
		int iJPPFCol = getColumnNum("Job Part Press Form", "//table[@id='JobPlan']");
		for (int i=1; i<=iJobPlanCnt; i++)
		{
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr["+i+"]/td["+iJPPFCol+"]/div/a")))
			{
				if(iCount==0)
				{
					sJPPFIDs = (String) ((JavascriptExecutor) _driver).executeScript("return arguments[0].innerHTML", _driver.findElement(By.xpath("//table[@id='JobPlan']/tbody/tr["+(i+1)+"]/td["+iJPPFCol+"]/span")));
					iCount = 1;
				}
				else
				{
					sJPPFIDs = sJPPFIDs+";"+(String) ((JavascriptExecutor) _driver).executeScript("return arguments[0].innerHTML", _driver.findElement(By.xpath("//table[@id='JobPlan']/tbody/tr["+(i+1)+"]/td["+iJPPFCol+"]/span")));
				}
			}
			else
			{
				if(iCount==0)
				{
					sJPPFIDs = ";";
					iCount = 1;
				}
				else
				{
					sJPPFIDs = sJPPFIDs+";";
				}
			}
		}

		return sJPPFIDs.trim();
	}

	public void UpdateLinksJobPlan() throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//input[@value='Update Links' and @name='updateForm']")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Update Links' and @name='updateForm']"));
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		}
	}

	public boolean verifyJobPlansScheduledCheckBox(int TimeOut) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		boolean IsScheduledBoxChecked = false;
		int loopCount = 0;

		String sPFForm = CommonFunctions.GetText(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]/td[3]/div"));
		if (sPFForm == null || sPFForm.equals(""))
		{
			UpdateLinksJobPlan();
			DC.Update();
		}

		IsScheduledBoxChecked = CommonFunctions.VerifyChecked(_driver, By.name("scheduled"));
		while (!IsScheduledBoxChecked && loopCount < TimeOut)
		{			
			Thread.sleep(5000);
			DC.Update();
			IsScheduledBoxChecked = CommonFunctions.VerifyChecked(_driver, By.name("scheduled"));
			loopCount++;
		} 

		return IsScheduledBoxChecked;
	}

	public void navigateToProductionPlanPage(String JobID) throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Job/plan/"+JobID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Update)));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToProductionPlanPage");
	}

	public int getRowNum (String ColumnName, String ColumnValue, String tableXPath) throws Exception
	{
		int iRowNumber = -1;

		try 
		{
			if (tableXPath.equals("")) {
				tableXPath = Locators.getProperty(Locators.Common_Webtable);
			}

			List<String> lColumnData  = GetColumnData(ColumnName, tableXPath);

			if (lColumnData.indexOf(ColumnValue) < 0)
			{
				System.err.println("Row Number = "+lColumnData.indexOf(ColumnValue));
			}
			else
			{ 
				iRowNumber = lColumnData.indexOf(ColumnValue)+1;
				if (CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='grid-contents']//select[@class='pageSelect']")))
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
	
	public String GetCellDataByKey(String KeyColumnName, String KeyColumnValue, String GetColumnName, String tableXPath) throws Exception
    {
           String sObjData = "";
           if (tableXPath.equals("")) {
                  tableXPath = Locators.getProperty(Locators.Common_Webtable);
           }

           int iRowNum = getRowNum(KeyColumnName, KeyColumnValue, tableXPath);
           int iCcolNum = getColumnNum(GetColumnName, tableXPath);

           sObjData = GetCellData(iRowNum, iCcolNum, tableXPath);
           
           return sObjData;
    }

	public String[][] GetPlanLinkDetails(String JobPartNum) throws Exception
	{
		int iJobPartJobPlanCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPlan']/tbody//a[starts-with(text(),'P"+JobPartNum+"')]"));
		String[][] aTaskLinkDetails = new String[iJobPartJobPlanCnt][4];
		
		int iJobPlanCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr"));
		int iJobPartColNum = getColumnNum("Job Part", "//table[@id='JobPlan']/tbody/tr");
		int iPlannedActColNum = getColumnNum("Planned Activity", "//table[@id='JobPlan']/tbody/tr");
		int iIDColNum = getColumnNum("ID", "//table[@id='JobPlan']/tbody/tr");
		int iPrevTaskColNum = getColumnNum("Previous Task", "//table[@id='JobPlan']/tbody/tr");
		int iNextTaskColNum = getColumnNum("Next Task", "//table[@id='JobPlan']/tbody/tr");
		
		int iArrIndex = 0;
		for (int i=1; i<=iJobPlanCnt; i++)
		{
			String sJobPart = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr["+i+"]/td["+iJobPartColNum+"]/div/a"));
			if (sJobPart.contains("P"+JobPartNum+""))
			{
				String sPlannedAct = GetCellData(i, iPlannedActColNum, "//table[@id='JobPlan']");
				String sPlanID = GetCellData(i, iIDColNum, "//table[@id='JobPlan']");
				String sPrevTask = GetCellData(i, iPrevTaskColNum, "//table[@id='JobPlan']");
				String sNextTask = GetCellData(i, iNextTaskColNum, "//table[@id='JobPlan']");
				
				aTaskLinkDetails[iArrIndex][0] = sPlannedAct;
				aTaskLinkDetails[iArrIndex][1] = sPlanID;
				aTaskLinkDetails[iArrIndex][2] = sPrevTask;
				aTaskLinkDetails[iArrIndex][3] = sNextTask;
				
				iArrIndex++;
			}
		}
		
		return aTaskLinkDetails;
	}
	
	public int find2DRowIndex(Object[][] array, Object search) 
	{
	    if (search == null || search.equals("")  || array == null) return -1;

	    for (int rowIndex = 0; rowIndex < array.length; rowIndex++ )
	    {
	       Object[] row = array[rowIndex];
	       if (row != null)
	       {
	          for (int columnIndex = 0; columnIndex < row.length; columnIndex++)
	          {
	             if (search.equals(row[columnIndex]))
	             {
	                 return rowIndex;
	             }
	          }
	       }
	    }
	    return -1; // value not found in array
	 }
	
}