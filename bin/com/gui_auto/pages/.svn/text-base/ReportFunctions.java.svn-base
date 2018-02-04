package com.gui_auto.pages;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.ss.usermodel.Workbook;
import autoitx4java.AutoItX;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;

public class ReportFunctions implements BaseElement
{
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
    public String sPrintout = null;
    private static  String sSERVER = null;
	private static  String sCOMPANY = null;
    protected WebDriver _driver;
	
    public  ReportFunctions(WebDriver driver) throws Exception
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
	
	public String UniqueNumber()
	{
		SimpleDateFormat DF = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = new Date();
		return DF.format(date);		
	}
	
	public String getReportID(WebDriver driver, By by) throws Exception
	{
		String reportID = "";
		if (CommonFunctions.isElementPresent(driver, by))
		{
			String href = driver.findElement(by).getAttribute("href");
			String[] arr1 = href.split("report=");
			String[] arr2 = arr1[1].split("&");
			reportID = arr2[0].trim();
		}
		else
		{
			System.err.println("Report button was not present. Unable to get report ID");
		}
		return reportID;
	}
	
	public void changeReportExportType(String reportID, String reportType) throws Exception
	{
		WebElement link = _driver.findElement(By.xpath("//a[@id='firstContextItem']"));
		Actions newTab = new Actions(_driver);
		newTab.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).click(link).keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();
		Thread.sleep(5000);
		 
		//handle windows change
		String base = _driver.getWindowHandle();
		Set<String> set = _driver.getWindowHandles();
		 
		set.remove(base);
		_driver.switchTo().window((String) set.toArray()[0]);
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Report/detail/"+reportID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("exportType"));
		
		CommonFunctions.selectDropdownByText(_driver, By.name("exportType"), reportType);
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
		
		//close the window and sitch back to the base tab
		_driver.close();
		_driver.switchTo().window(base);
		CommonFunctions.waitForPageLoaded(_driver);
	}
	
	public void switchtoReportWindow() throws Exception
	{
		String parentWindow = _driver.getWindowHandle();
		Iterator<String> windowIterator = _driver.getWindowHandles().iterator();
		while(windowIterator.hasNext()) {
		    _driver.switchTo().window(windowIterator.next());
		    if (_driver.getTitle().contains("Report")) {
		    	CommonFunctions.waitForPageLoaded(_driver);
		      break; }
		    else{_driver.switchTo().window(parentWindow);}
		}
	}
	
	public String getReportNameFromRepotWindow() throws Exception
	{
		String parentWindow = _driver.getWindowHandle();
		switchtoReportWindow();
		String sCurrentURL = _driver.getCurrentUrl();
		String[] arr1 = sCurrentURL.split(".xls");
		String[] arr2 = arr1[0].split("/");
		_driver.switchTo().window(parentWindow);
		return arr2[arr2.length-1].trim()+".xls";
	}
	
	public String MoveReportTo(String fileName, String filePath, String DestFolder) throws Exception
	{
		if (filePath.equals(""))
		{
			filePath = System.getProperty("user.dir").concat(_properties.getProperty(GUI_automation_properties.DownloadPath));
		}
		
		File srcFile = new File(filePath+"\\"+fileName);
		String sDestFilePath = DestFolder+"\\"+UniqueNumber()+fileName;
		File destFile = new File(sDestFilePath);
		
		int loop = 0;
		while (!srcFile.exists() && loop < 5)
		{
			Thread.sleep(5000);
			loop++;
		}
		
		//move report to specified folder
		if (srcFile.exists())
		{
			FileUtils.moveFile(srcFile, destFile);
			System.out.println("New Report file path = "+destFile.getCanonicalPath());
			return sDestFilePath;
		}
		else
		{
			System.err.println("File doesnot exists");
			return "";
		}
	}
	
	//get the date/time vale form excel
	public static String getDateTimeValue(Date date)
	{
		String cellValue = "";

        //set up formatters that will be used below
        SimpleDateFormat formatTime = new SimpleDateFormat("h:mm:ss a");
        SimpleDateFormat formatYearOnly = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        /*get date year.
        *"Time-only" values have date set to 31-Dec-1899 so if year is "1899"
        * you can assume it is a "time-only" value 
        */
        String dateStamp = formatYearOnly.format(date);

        if (dateStamp.equals("1899")){
            //Return "Time-only" value as String H:mm:ss AM/PM
            cellValue = formatTime.format(date);
        } else {
            //here you may have a date-only or date-time value

            //get time as String HH:mm:ss 
            String timeStamp =formatTime.format(date);

            if (timeStamp.equals("12:00:00 AM")){
                //if time is 12:00:00 AM you can assume it is a date only value (but it could be midnight)
                //returning MM/dd/yyyy in case of a date value
                cellValue = dateFormat.format(date);
            } else {
                //return date-time value as "MM/dd/yyyy H:mm:ss AM/PM"
                cellValue = dateFormat.format(date)+" "+timeStamp;
            }
        }        
        return cellValue;
	}
	
	//get date/time vale form excel in MM/dd/yy format
	public static String getDateTimeValue1(Date date)
	{
		String cellValue = "";

        //set up formatters that will be used below
        SimpleDateFormat formatTime = new SimpleDateFormat("h:mm:ss a");
        SimpleDateFormat formatYearOnly = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        /*get date year.
        *"Time-only" values have date set to 31-Dec-1899 so if year is "1899"
        * you can assume it is a "time-only" value 
        */
        String dateStamp = formatYearOnly.format(date);

        if (dateStamp.equals("1899")){
            //Return "Time-only" value as String H:mm:ss AM/PM
            cellValue = formatTime.format(date);
        } else {
            //here you may have a date-only or date-time value

            //get time as String HH:mm:ss 
            String timeStamp =formatTime.format(date);

            if (timeStamp.equals("12:00:00 AM")){
                //if time is 12:00:00 AM you can assume it is a date only value (but it could be midnight)
                //returning MM/dd/yyyy in case of a date value
                cellValue = dateFormat.format(date);
            } else {
                //return date-time value as "MM/dd/yyyy H:mm:ss AM/PM"
                cellValue = dateFormat.format(date)+" "+timeStamp;
            }
        }        
        return cellValue;
	}
	
	//get cell value from excel
	public static String getCellValue (Cell cell)
	{
		String cellValue = "";	
	
		switch (cell.getCellType()) 
		{
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					cellValue = getDateTimeValue(date);
				} else {
					cellValue = new DataFormatter().formatCellValue(cell);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getRichStringCellValue().getString().trim();
				break;
		}
		return cellValue;
	}
	
	//this function verify if the data passed exists (keys and values are in the same row or not)
	public boolean verifyReport(HashMap<String, String> Data, String filePath) throws Exception
	{
        String cellContent1 = "";
        String cellContent2 = "";
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        for (String Key : Data.keySet())
        {
        	cellContent1 = Key;
        	cellContent2 = Data.get(Key);
            
            if(!verifyData(workbook, sheet, cellContent1, cellContent2))
            {
            	fileMatches = false;
            }
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent, String cellContent1) 
	{
		int cellRw = -1, cellCol = 0, cellRwMatch = 0;
		String cellValue = "";
		boolean sValuesExists = false;
		
		for (Row row : sheet) 
		{
			cellRw = -1;
			cellCol = -1;
			for (Cell cell : row) 
			{
				if (cell!=null) {
					cellValue = getCellValue(cell);				    
				    if (cellValue.equals(cellContent))
					{
						cellRw = row.getRowNum();
						if (cellRwMatch > 0)
						{
							System.err.println("One more Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						else
						{
							System.out.println("Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						cellRwMatch++;
						break;
					}				    
				}
			}		
		
			if (cellRw >= 0)
			{
				row = sheet.getRow(cellRw);
				for (Cell cell : row) 
				{
					if (cell!=null && cell.getColumnIndex() > cellCol) {						
						cellValue = getCellValue(cell);					    
					    if (cellValue.equals(cellContent1))
						{
					    	cellCol = cell.getColumnIndex();
					    	sValuesExists = true;
							break;
						}				    
					}
				}
				
				if (sValuesExists) {break;}
				else{System.err.println("Value : '"+cellContent+"' exists : "+(cellRw > 0)+
						"\nValue : '"+cellContent1+"' exists : "+sValuesExists);}
			}
		}
		
		if (cellRw >= 0 && sValuesExists)
		{
			System.out.println(cellContent+" is "+cellContent1);
		}
		else if (cellRwMatch == 0)
		{
			System.err.println("Row with key data '"+cellContent+"' does not exists");
		}
		return sValuesExists;
	}
	
	//this function verify if the data passed exists (key in a row and its value in the immediate next row, same coloumn)
	public boolean verifyReport1(HashMap<String, String> Data, String filePath) throws Exception
	{
        String cellContent1 = "";
        String cellContent2 = "";
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        for (String Key : Data.keySet())
        {
        	cellContent1 = Key;
        	cellContent2 = Data.get(Key);
            
            if(!verifyData1(workbook, sheet, cellContent1, cellContent2))
            {
            	fileMatches = false;
            }
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData1(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent, String cellContent1) 
	{
		int cellRw = -1, cellCol = 0;
		String cellValue = "";
		boolean sValuesExists = false;
		
		for (Row row : sheet) 
		{
			cellRw = -1;
			for (Cell cell : row) 
			{
				if (cell!=null) {
					cellValue = getCellValue(cell);				    
				    if (cellValue.equals(cellContent))
					{
						cellRw = row.getRowNum();
						System.out.println("Match for '"+cellContent+"' found in Row number: "+cellRw);
						cellCol = cell.getColumnIndex();
						break;
					}				    
				}
			}		
			
			if (cellRw >= 0)
			{
				row = sheet.getRow(cellRw+1);
				Cell cell = row.getCell(cellCol);
				cellValue = getCellValue(cell);   
			    
			    if (cellValue.equals(cellContent1))
				{
			    	sValuesExists = true;
					break;
				}
			    else{System.err.println("'"+cellContent1+"' is NOT present");}
			}
		}
		
		if (cellRw >= 0 && sValuesExists)
		{
			System.out.println(cellContent+" is "+cellContent1);
		}	
		return sValuesExists;
	}
	
	//this function verify if the data passed exists (key in a row and its value in the immediate next row)
	public boolean verifyReport2(HashMap<String, String> Data, String filePath) throws Exception
	{
        String cellContent1 = "";
        String cellContent2 = "";
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        for (String Key : Data.keySet())
        {
        	cellContent1 = Key;
        	cellContent2 = Data.get(Key);
            
            if(!verifyData2(workbook, sheet, cellContent1, cellContent2))
            {
            	fileMatches = false;
            }
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData2(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent, String cellContent1) 
	{
		int cellRw = -1;
		String cellValue = "";
		boolean sValuesExists = false;
		
		for (Row row : sheet) 
		{
			cellRw = -1;
			for (Cell cell : row) 
			{
				if (cell!=null) {
					cellValue = getCellValue(cell);				    
				    if (cellValue.equals(cellContent))
					{
						cellRw = row.getRowNum();
						System.out.println("Match found in Row number: "+cellRw);
						break;
					}				    
				}
			}		
			
			if (cellRw >= 0)
			{
				row = sheet.getRow(cellRw+1);
				for (Cell cell : row) 
				{
					if (cell!=null) {
						cellValue = getCellValue(cell);						
						if (cellValue.equals(cellContent1))
						{
					    	sValuesExists = true;
							break;
						}
					}
				}
				
				if (sValuesExists) {break;}
				else{System.err.println(cellContent1+" is NOT present");}
			}
		}
		
		if (cellRw >= 0 && sValuesExists)
		{
			System.out.println(cellContent+" is "+cellContent1);
		}
		else
		{
			System.err.println("Value : "+cellContent+" exists : "+(cellRw > 0)+
								"\nValue : "+cellContent1+" exists : "+sValuesExists);
		}		
		return sValuesExists;
	}

	//this function verify if the data passed exists (key and multiple values in the same row)
	public boolean verifyReport3(String key, String[] Data, String filePath) throws Exception
	{
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
            
        if(!verifyData3(workbook, sheet, key, Data))
        {
        	fileMatches = false;
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData3(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent, String[] cellContent1)
	{
		int cellRw = -1, cellmatches = 0, cellCol = -1, cellRwMatch = 0;
		String cellValue = "";
		boolean sValuesExists = false;
		
		for (Row row : sheet) 
		{
			cellRw = -1; cellmatches = 0; cellCol = -1;
			for (Cell cell : row) 
			{
				if (cell!=null) {
					cellValue = getCellValue(cell);
				    if (cellValue.equals(cellContent))
					{				    	
						cellRw = row.getRowNum();
						if (cellRwMatch > 0)
						{
							System.err.println("One more Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						else
						{
							System.out.println("Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						cellRwMatch++;
						break;
					}				    
				}
			}		
			
			if (cellRw >= 0)
			{
				row = sheet.getRow(cellRw);
				for (int i=0; i<=cellContent1.length-1; i++)
				{
					boolean valueExists = false;
					for (Cell cell : row) 
					{
						if (cell!=null && cell.getColumnIndex() > cellCol) {
							cellValue = getCellValue(cell);						    
						    if (cellValue.equals(cellContent1[i]))
							{
						    	cellCol = cell.getColumnIndex();
						    	cellmatches++;
						    	valueExists = true;
								break;
							}
						}
					}
					if(!valueExists){System.err.println(cellContent1[i]+" is NOT present");}
				}
				
				if (cellmatches==cellContent1.length) {sValuesExists=true;break;}
			}
		}
		
		if (cellRw >= 0 && sValuesExists)
		{
			System.out.println(cellContent+" and its details "+Arrays.toString(cellContent1)+" were displayed correctly");
		}
		else
		{
			System.err.println(cellContent+" and its details "+Arrays.toString(cellContent1)+" were NOT displayed");
		}		
		return sValuesExists;
	}
	
	//this function verify if the data passed exists in the excelsheet (data equals)
	public int verifyReport4(String FindValue,  String filePath) throws Exception
	{
        int fileMatches = 0;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        fileMatches = verifyData4(workbook, sheet, FindValue);
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static int verifyData4(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent)
	{
		int cellRw = -1;
		String cellValue = "";
		
		for (Row row : sheet)
		{
			for (Cell cell : row) 
			{
				if (cell!=null) {					
					cellValue = getCellValue(cell);
				    if (cellValue.equals(cellContent))
					{
				    	cellRw = row.getRowNum();
				    	System.out.println("Match for '["+cellContent+"]' found in Row "+cellRw);
						break;
					}				    
				}
			}
			if (cellRw >= 0) {break;}
		}
		
		if (cellRw >= 0)
		{
			System.out.println(cellContent+" was displayed correctly");
		}
		else
		{
			System.err.println(cellContent+" was NOT displayed");
		}
		return cellRw;
	}
		
	//this function verify if the multiple data passed exists in the excelsheet
	public boolean verifyReport5(List <String> dataList, String filePath) throws Exception
	{
        int fileMatches = 0;
        boolean allDataFound = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        for (String searchValue : dataList)
        {
        	fileMatches = verifyData4(workbook, sheet, searchValue);
        	if (fileMatches < 0)
        	{
        		allDataFound = false;
        	}
        }
        
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return allDataFound;
	}	
	
	//this function verify if the data passed exists in the particular row specified (multiple values in the specified row)
	public boolean verifyReport6(int RowNumber, String[] Data, String filePath) throws Exception
	{
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
            
        if(!verifyData6(workbook, sheet, RowNumber, Data))
        {
        	fileMatches = false;
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData6(HSSFWorkbook workbook, HSSFSheet sheet, int cellContentsRowNum, String[] cellContent1)
	{
		int cellmatches = 0, cellCol = -1;
		String cellValue = "";
		boolean sValuesExists = false;
		
		Row row = sheet.getRow(cellContentsRowNum);
		for (int i=0; i<=cellContent1.length-1; i++)
		{
			boolean valueExists = false;
			for (Cell cell : row) 
			{
				if (cell!=null && cell.getColumnIndex() > cellCol) {
					cellValue = getCellValue(cell);						    
				    if (cellValue.equals(cellContent1[i]))
					{
				    	cellCol = cell.getColumnIndex();
				    	cellmatches++;
				    	valueExists = true;
						break;
					}
				}
			}
			if(!valueExists){System.err.println(cellContent1[i]+" is NOT present");}
		}
		
		if (cellmatches==cellContent1.length)
		{
			System.out.println("Details "+Arrays.toString(cellContent1)+" were displayed correctly");
			sValuesExists = true;
		}
		else
		{
			System.err.println("Details "+Arrays.toString(cellContent1)+" were NOT displayed");
		}		
		return sValuesExists;
	}
	
	//this function verify if the data passed exists (key and value are in the same row or not)
	public boolean verifyReport7(String Key, String Data, String filePath) throws Exception
	{
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        fileMatches = verifyData(workbook, sheet, Key, Data);           
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	
	//this function verify if the data passed exists (data contains)
	public int verifyReport8(String FindValue,  String filePath) throws Exception
	{
        int fileMatches = 0;
        

        FileInputStream input = new FileInputStream(filePath);
      
        //XSSFWorkbook workbook = new XSSFWorkbook(input);
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        fileMatches = verifyDataContains(workbook, sheet, FindValue);
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static int verifyDataContains(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent)
	{
		int cellRw = -1;
		String cellValue = "";
		
		for (Row row : sheet)
		{
			for (Cell cell : row) 
			{
				if (cell!=null) {					
					cellValue = getCellValue(cell);
				    if (cellValue.contains(cellContent))
					{
				    	cellRw = row.getRowNum();
				    	System.out.println("Match for '["+cellContent+"]' found in Row "+cellRw);
						break;
					}				    
				}
			}
			if (cellRw >= 0) {break;}
		}
		
		if (cellRw >= 0)
		{
			System.out.println(cellContent+" was displayed correctly");
		}
		else
		{
			System.err.println(cellContent+" was NOT displayed");
		}
		return cellRw;
	}		
	
	//this function gets the cell value from the report
	public String getValueFromReport(int RowNum, int ColNum, String filePath) throws Exception
	{
		String sCellValue = "";

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
            
        sCellValue = verifyData8(workbook, sheet, RowNum, ColNum);
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		
		return sCellValue;
	}
	private static String verifyData8(HSSFWorkbook workbook, HSSFSheet sheet, int RowNum, int ColNum)
	{		
		Row row = sheet.getRow(RowNum);
		Cell cell = row.getCell(ColNum);
		String cellValue = getCellValue(cell);
		if(cellValue == null)
		{
			cellValue = "";
		}
		return cellValue;
	}
	
	//this function verify if the data passed exists (key and multiple values in the immediate row)
	public boolean verifyReport9(String key, String[] Data, String filePath) throws Exception
	{
        boolean fileMatches = true;

        FileInputStream input = new FileInputStream(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(0);
            
        if(!verifyData9(workbook, sheet, key, Data))
        {
        	fileMatches = false;
        }
               
        input.close();
        sheet = null;
        workbook = null;
        input = null;
		return fileMatches;
	}
	private static boolean verifyData9(HSSFWorkbook workbook, HSSFSheet sheet, String cellContent, String[] cellContent1)
	{
		int cellRw = -1, cellmatches = 0, cellCol = -1, cellRwMatch = 0;
		String cellValue = "";
		boolean sValuesExists = false;
		
		for (Row row : sheet) 
		{
			cellRw = -1; cellmatches = 0; cellCol = -1;
			for (Cell cell : row) 
			{
				if (cell!=null) {
					cellValue = getCellValue(cell);
				    if (cellValue.equals(cellContent))
					{				    	
						cellRw = row.getRowNum();
						if (cellRwMatch > 0)
						{
							System.err.println("One more Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						else
						{
							System.out.println("Match for '"+cellContent+"' found in Row number: "+cellRw);
						}
						cellRwMatch++;
						break;
					}				    
				}
			}		
			
			if (cellRw >= 0)
			{
				row = sheet.getRow(cellRw+1);
				for (int i=0; i<=cellContent1.length-1; i++)
				{
					boolean valueExists = false;
					for (Cell cell : row) 
					{
						if (cell!=null && cell.getColumnIndex() > cellCol) {
							cellValue = getCellValue(cell);						    
						    if (cellValue.equals(cellContent1[i]))
							{
						    	cellCol = cell.getColumnIndex();
						    	cellmatches++;
						    	valueExists = true;
								break;
							}
						}
					}
					if(!valueExists){System.err.println(cellContent1[i]+" is NOT present");}
				}
				
				if (cellmatches==cellContent1.length) {sValuesExists=true;break;}
			}
		}
		
		if (cellRw >= 0 && sValuesExists)
		{
			System.out.println(cellContent+" and its details "+Arrays.toString(cellContent1)+" were displayed correctly");
		}
		else
		{
			System.err.println(cellContent+" and its details "+Arrays.toString(cellContent1)+" were NOT displayed");
		}		
		return sValuesExists;
	}
	
	//verify Print dialog box
	public boolean verifyPrintDialogBox() throws Exception
	{
		boolean verifyPrintDialog = false;
		Robot robot = new Robot();
		AutoItX auto = new AutoItX();
		int browserID = 0;
		
		switchtoReportWindow();
		CommonFunctions.waitForPageLoaded(_driver);
//		String sWindowTitle = _driver.getTitle();
		auto.winActivate("Running Report...Late+Jobs+Plan+Report.pdf - Google Chrome");
		auto.winWaitActive("Running Report...Late+Jobs+Plan+Report.pdf - Google Chrome");
		auto.send("{CTRLDOWN}p{CTRLUP}");
		Thread.sleep(2000);
//		robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_P);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_P);
        
		String browser = _properties.getProperty(GUI_automation_properties.BROWSER);
		if (browser.equals("firefox"))	{
			browserID = 1; 
		}
		else if (browser.equals("chrome"))	{
			browserID = 2;
		}
		
		switch (browserID) {
		case 1:
			verifyPrintDialog = auto.winExists("Print");
			if (verifyPrintDialog) {
				auto.winActivate("Print");
				auto.winWaitActive("Print");
				auto.controlClick("Print", "", "2");
				Thread.sleep(1000);
			}
			break;
		case 2:		
			verifyPrintDialog = auto.winExists("Chrome Legacy Window");
			if (verifyPrintDialog) {
				auto.winActivate("Chrome Legacy Window");
				auto.winWaitActive("Chrome Legacy Window");
				auto.send("{ESC}");
//				robot.keyPress(KeyEvent.VK_ESCAPE);
//				robot.keyRelease(KeyEvent.VK_ESCAPE);
				Thread.sleep(1000);
			}
			break;
		}
		return verifyPrintDialog;
	}

	//verifyHoverMessage
	public boolean CheckHoverMessage(WebDriver driver, By by, String HoverMessage) throws Exception
	{
		Actions ToolTip1 = new Actions(driver);
		WebElement element = driver.findElement(by);
		ToolTip1.clickAndHold(element).perform(); // Perform mouse hover action using 'clickAndHold' method
		String ToolTipText = element.getAttribute("title"); // Get the value of Tooltip by getAttribute command
		if (ToolTipText.equals(HoverMessage))		{
			System.out.println("Hover Message value : " + ToolTipText);
			return true;
		}
		else		{
			System.err.println("Hover Message. Expected = " + HoverMessage + "\nActual = "+ToolTipText);
			return false;
		}
	}
	
	//modify month and day in Date in M/d/yy format
	public String modifyDate(String date) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("M/d/yy");

		return myFormat.format(fromUser.parse(date));
	}
	
	//modify month and day in Date in MM/dd/yy format
	public String modifyDate1(String date) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yy");

		return myFormat.format(fromUser.parse(date));
	}
	
	//modify time in h:mm:ss a format
	public String modifyTime(String time) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat("h:mm a");
		SimpleDateFormat myFormat = new SimpleDateFormat("h:mm:ss a");

		try
		{
			return myFormat.format(fromUser.parse(time));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return time;
		}
	}
	
	//modify time in h:mm a format
	public String modifyTime1(String time) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat("h:mm:ss a");
		SimpleDateFormat myFormat = new SimpleDateFormat("h:mm a");

		return myFormat.format(fromUser.parse(time));
	}
	
	//verify printer error message
	public boolean VerifyPrinterErrorMessage() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		
		CommonFunctions.selectDropdownByIndex(_driver, By.name("printer"), 0);
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Print_Report)));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);
		
		String sErrorMessage = CommonFunctions.GetText(_driver, By.xpath(Locators.getProperty(Locators.Error_Message)));
		String sErrorMessage1 = CommonFunctions.GetText(_driver, By.xpath("//span[@class='error']"));
		
		if (sErrorMessage.equals("Check Again\nPrinter: Must select a printer to print the report") && sErrorMessage1.equals("Must select a printer to print the report"))
		{return true;}
		else
		{return false;}	
	}
	
	//modify date/time format as required
	public String changeFormat(String Date_Time, String currentFormat, String desiredFormat) throws Exception
	{
		SimpleDateFormat fromUser = new SimpleDateFormat(currentFormat);
		SimpleDateFormat myFormat = new SimpleDateFormat(desiredFormat);

		return myFormat.format(fromUser.parse(Date_Time));
	}	
	
	//get future date
	public String GetFutureDate(String format, int NumberOfDays)
	{
		Date date = new Date();
		SimpleDateFormat myFormat = new SimpleDateFormat(format);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, NumberOfDays);
		date = c.getTime();	

		return myFormat.format(date);	
	}
	
}
