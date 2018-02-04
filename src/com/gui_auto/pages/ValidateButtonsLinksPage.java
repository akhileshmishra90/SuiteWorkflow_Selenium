package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;

public class ValidateButtonsLinksPage implements BaseElement
{
	protected WebDriver _driver;	

	public  ValidateButtonsLinksPage(WebDriver driver) throws Exception
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
	
	public String createExcelFile(String ExcelFilepath) throws Exception
	{
		final String dir = System.getProperty("user.dir");
		File folder = new File(dir.concat("\\ValidateButtonsLinks"));
		File excelFile = new File(dir.concat("\\ValidateButtonsLinks\\"+ExcelFilepath));
		
		//create a folder by name validateButtonslinks (if it doesn't exists) and delete any files inside this folder
		if (folder.exists()) {
			if (excelFile.exists())	{
				excelFile.delete();
			}			
		}
		else	{
			folder.mkdir();
		}
		Thread.sleep(2000);
		
		//create an excel file inside this folder.
		excelFile.createNewFile();
		Thread.sleep(1000);
		if (excelFile.exists())
		{
			System.out.println("new Excel File "+ExcelFilepath+" was created at filepath : "+excelFile);
		}
		else
		{
			System.err.println("new excelfile was not created");
			assertEquals("Excel File should have been created", "Excel File should was NOT created");
		}	 
		return dir.concat("\\ValidateButtonsLinks\\"+ExcelFilepath);
	}
	
	public void CreateExcelSheet(String ExcelFilepath, String ExcelSheetName) throws Exception
	{		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try 
		{
			workbook.createSheet(ExcelSheetName);
			
			FileOutputStream fileOut = new FileOutputStream(ExcelFilepath);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	public void AddColumn(String ExcelFilepath, String ExcelSheetName, String ColoumnName)
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);
			Cell cell = null;
			Row row = null;
			int i = 0;
			
			HSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);			
			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			row = sheet.getRow(0);
			if(row==null) {
				row = sheet.createRow(0);
			}
			i = row.getPhysicalNumberOfCells();
			if(i==0)	{
				cell = row.createCell(0);
			}
			else	{
				cell = row.createCell(i);
			}
			cell.setCellValue(ColoumnName);
			cell.setCellStyle(style);
			
			FileOutputStream fileOut = new FileOutputStream(ExcelFilepath);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void InsertRowData(String ExcelFilepath, String ExcelSheetName, String ColumnName, String Value, boolean enterInNewRow) throws Exception
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			Cell cell = null;
			Row row = null;
			int iRwCnt = 0, iColCnt = 0, iColNum = 0;
			
			row = sheet.getRow(0);
			iColCnt = row.getPhysicalNumberOfCells();
			for (int i=0;i<iColCnt;i++)
			{
				cell = row.getCell(i);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(ColumnName))
				{
					iColNum = i;
				}
			}
			
			iRwCnt = sheet.getPhysicalNumberOfRows();
			if (enterInNewRow)	{
				row = sheet.createRow(iRwCnt);
			}
			else	{
				row = sheet.getRow(iRwCnt-1);
			}			
			cell = row.createCell(iColNum);
			cell.setCellValue(Value);
			
			if (!(cell.getStringCellValue().trim().equals(Value)))
			{
				System.err.println("Value "+Value+" was not entered under "+ColumnName+" column in the table");
			}
			
			FileOutputStream fileOut = new FileOutputStream(ExcelFilepath);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getCellData(String ExcelFilepath, String ExcelSheetName, String ColumnName, int RowNumber)
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			Cell cell = null;
			Row row = null;
			int iColCnt = 0, iColNum = 0;
			
			row = sheet.getRow(0);
			iColCnt = row.getPhysicalNumberOfCells();
			for (int i=0;i<iColCnt;i++)
			{
				cell = row.getCell(i);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(ColumnName))
				{
					iColNum = i;
				}
			}
			row = sheet.getRow(RowNumber);
			cell = row.getCell(iColNum);
			
			return cell.getStringCellValue().trim();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public void AddSheet(String ExcelFilepath, String ExcelSheetName) throws Exception
	{
		CreateExcelSheet(ExcelFilepath, ExcelSheetName);
		AddColumn(ExcelFilepath, ExcelSheetName, "ContextMenu");
		AddColumn(ExcelFilepath, ExcelSheetName, "ContextMenuSection");
		AddColumn(ExcelFilepath, ExcelSheetName, "Links");
		AddColumn(ExcelFilepath, ExcelSheetName, "LinkURL");
		AddColumn(ExcelFilepath, ExcelSheetName, "LinkBroken");	
	}
	
	public int getRowCount(String ExcelFilepath, String ExcelSheetName)
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			return sheet.getPhysicalNumberOfRows();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
		
	public String getttributeValue(WebDriver driver, By locator, String attribute)
	{
		try
		{
			return driver.findElement(locator).getAttribute(attribute);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public void InsertValue(String ExcelFilepath, String ExcelSheetName, int ColumnNumber, String RowValue, String DestinationColumnName, String InsertValue) throws Exception
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			Cell cell = null;
			Row row = null;
			int iRwCnt = 0, iRowNum = 0, iColCnt=0, iColNum = 0;
			
			row = sheet.getRow(0);
			iColCnt = row.getPhysicalNumberOfCells();
			for (int i=0;i<iColCnt;i++)
			{
				cell = row.getCell(i);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(DestinationColumnName))
				{
					iColNum = i;
					break;
				}
			}
			
			iRwCnt = sheet.getPhysicalNumberOfRows();
			for (int i=1;i<iRwCnt;i++)
			{
				row = sheet.getRow(i);
				cell = row.getCell(ColumnNumber-1);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(RowValue))
				{
					iRowNum = i;
					break;
				}
			}
			
			row = sheet.getRow(iRowNum);
			iColCnt = row.getPhysicalNumberOfCells();
			if (iColNum >= iColCnt)	{
				cell = row.createCell(iColNum);
			}
			cell.setCellValue(InsertValue);
			
			if (!(cell.getStringCellValue().trim().equals(InsertValue)))
			{
				System.err.println("Value "+InsertValue+" was not entered under "+DestinationColumnName+" column in the table");
			}
			
			FileOutputStream fileOut = new FileOutputStream(ExcelFilepath);
			workbook.write(fileOut);
			fileOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String ReadValue(String ExcelFilepath, String ExcelSheetName, int ColumnNumber, String RowValue, String DestinationColumnName, String InsertValue) throws Exception
	{
		try 
		{
			FileInputStream FIS = new FileInputStream(ExcelFilepath);
			HSSFWorkbook workbook = new HSSFWorkbook(FIS);			
			HSSFSheet sheet = workbook.getSheet(ExcelSheetName);
			Cell cell = null;
			Row row = null;
			int iRwCnt = 0, iRowNum = 0, iColCnt=0, iColNum = 0;
			
			row = sheet.getRow(0);
			iColCnt = row.getPhysicalNumberOfCells();
			for (int i=0;i<iColCnt;i++)
			{
				cell = row.getCell(i);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(DestinationColumnName))
				{
					iColNum = i;
					break;
				}
			}
			
			iRwCnt = sheet.getPhysicalNumberOfRows();
			for (int i=1;i<iRwCnt;i++)
			{
				row = sheet.getRow(i);
				cell = row.getCell(ColumnNumber-1);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equals(RowValue))
				{
					iRowNum = i;
					break;
				}
			}
			
			row = sheet.getRow(iRowNum);
			cell = row.getCell(iColNum);
			
			return cell.getStringCellValue().trim();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}