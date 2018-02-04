package com.gui_auto.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.GUI_automation_properties;
public class ReadAndUpdate 
{              
	public static WebDriver _driver;

	private static String FilePath = null;
	private static String BROWSER = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	Connection conn=null;
	Statement stmt=null;
	String sql="";
	ResultSet rs=null; 
	int rs1 ;
	int iRowCount;
	String sValue = null;
	String sValue1 = null;
	public static String ExcelPath1 = _properties.getProperty(GUI_automation_properties.FILEPATH);
	public static String vbsFile = _properties.getProperty(GUI_automation_properties.VBSFileP);
	///new line
	//final String dir = System.getProperty("user.dir");
	private static String ExcelPath;
	private static String vbsfilepath;

	static 
	{
		try 
		{ 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} 
		catch (Exception e)
		{
			System.err.println(e);
		}
	}

	public static void main(final String[] args)
	{
		final String dir = System.getProperty("user.dir");		
		ExcelPath = dir.concat(ExcelPath1);
		vbsfilepath = dir.concat(vbsFile);
		//   System.out.println("current vbs file path = " + vbsfilepath);
	}

	///new line
	public static void PathsMethod()
	{
		final String dir = System.getProperty("user.dir");	
		ExcelPath = dir.concat(ExcelPath1);
		//  System.out.println("current e file path = " + ExcelPath);
		vbsfilepath = dir.concat(vbsFile);
		//  System.out.println("current vbs file path = " + vbsfilepath);
	}

	public ReadAndUpdate()
	{
		final String dir = System.getProperty("user.dir");
		//  System.out.println("current dir = " + dir);
		ExcelPath = dir.concat(ExcelPath1);
		//  System.out.println("current e file path = " + ExcelPath);
		vbsfilepath = dir.concat(vbsFile);
	}

	public String[] getExcelDataRow(String sheetname,int rno)
	{

		String[] retval=null;
		try{
			Workbook w = Workbook.getWorkbook(new File(ExcelPath));
			Sheet s = w.getSheet(sheetname);
			int cols=s.getColumns();
			retval=new String[cols];
			for(int i=0;i<cols;i++){
				retval[i]=s.getCell(i, rno-1).getContents();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return retval;
	}

	public int getExcelDataRowCount(String sheetname){
		int retval=0;
		try{
			Workbook w = Workbook.getWorkbook(new File(ExcelPath));
			Sheet s = w.getSheet(sheetname);
			int rows = s.getRows();


			return rows;


		}
		catch(Exception e){
			e.printStackTrace();
		}
		return retval;
	}

	public HashMap<String,String> ReadAllDataFunction(String sSheetName,String ScenarioName, String TestCase) throws Exception

	{


		FileInputStream fileIn = new FileInputStream(ExcelPath);		
		POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
		HSSFWorkbook filename = new HSSFWorkbook(fs);		



		HashMap<String,String> TempHasMap=new HashMap<String, String>();
		try
		{
			Integer ColumnCount=0;

			PathsMethod();
			HSSFSheet sheet = filename.getSheet(sSheetName);
			List<String> ColumnNameList=new ArrayList<String>();

			try {

				Row firstRow = sheet.getRow(0);				
				for(Cell celvalue:firstRow)
				{	
					ColumnCount=ColumnCount+1;
					ColumnNameList.add(celvalue.getStringCellValue());
					System.out.println(celvalue.getStringCellValue());

				}
			} catch (java.util.NoSuchElementException e) {
				System.err.println("Exception in reading name");
			}
			//Connect to DB

			//conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();
			for (int i = 0; i < ColumnCount; i++) {


				// Retrieve the cell value 
				sql = "Select "+ColumnNameList.get(i)+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
				System.out.println(sql);
				// Execute Query
				rs=stmt.executeQuery(sql);

				while(rs.next())
				{

					sValue = rs.getString(ColumnNameList.get(i));

					System.out.println(sValue);
					TempHasMap.put(ColumnNameList.get(i), sValue);
				}

			}

		} 
		catch (Exception e){
			e.printStackTrace();
			System.err.println(e);
			return null;
		}   



		finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){}
		}
		return TempHasMap;
	}

	public String ReadFunction(String sSheetName,String ScenarioName, String TestCase,String ColumnName)

	{

		try
		{

			//Connect to DB
			PathsMethod();
			//conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);			
			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			System.out.println(conn);
			stmt=conn.createStatement();

			// Retrieve the cell value 
			sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
			System.out.println(sql);
			// Execute Query
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				sValue = rs.getString(ColumnName);
				System.out.println(sValue);
			}

		} 
		catch (Exception e){
			System.err.println(e);
		}   



		finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){}
		}
		return sValue;
	}

	public HashMap<String,String> ReadAllDataFunctionForRecord(String sSheetName,String ScenarioName, String TestCase) throws Exception

	{


		FileInputStream fileIn = new FileInputStream(ExcelPath);		
		POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
		HSSFWorkbook filename = new HSSFWorkbook(fs);		



		HashMap<String,String> TempHasMap=new HashMap<String, String>();
		try
		{
			Integer ColumnCount=0;

			PathsMethod();
			HSSFSheet sheet = filename.getSheet(sSheetName);
			List<String> ColumnNameList=new ArrayList<String>();

			try {

				Row firstRow = sheet.getRow(0);				
				for(Cell celvalue:firstRow)
				{	
					if(celvalue.getColumnIndex()>6)
					{
						ColumnCount=ColumnCount+1;
						ColumnNameList.add(celvalue.getStringCellValue());
						System.out.println(celvalue.getStringCellValue());
					}

				}
			} catch (java.util.NoSuchElementException e) {

			}
			catch (Exception e) {
				System.err.println("Error in column"+ColumnCount.toString());
			}
			//Connect to DB

			//conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();
			for (int i = 0; i < ColumnCount; i++) {


				// Retrieve the cell value 
				sql = "Select "+ColumnNameList.get(i)+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
				System.out.println(sql);
				// Execute Query
				rs=stmt.executeQuery(sql);

				while(rs.next())
				{
					try {



						sValue = rs.getString(ColumnNameList.get(i));
						if(!((sValue.isEmpty()) || sValue.equals(null)))
						{		
							System.out.println(sValue);
							TempHasMap.put(ColumnNameList.get(i), sValue);
						}


					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}

		} 
		catch (Exception e){

			System.err.println("cell Object not found while reading data ");
			return TempHasMap;
		}   



		finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){}
		}
		return TempHasMap;
	}

	public void  WriteTestCaseData(String sSheetName,String ScenarioName, String TestCase,HashMap<String,String> Testdata) throws Exception
	{

		FileInputStream fileIn = new FileInputStream(ExcelPath);		
		POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
		HSSFWorkbook filename = new HSSFWorkbook(fs);		
		FileOutputStream fileOut=null;
		try
		{

			PathsMethod();
			HSSFSheet sheet = filename.getSheet(sSheetName);

			String newColumn;
			boolean IsColumnPresent;
			try {

				Row firstRow = sheet.getRow(0);
				for (String key : Testdata.keySet()) 
				{
					IsColumnPresent=false;
					for(Cell celvalue:firstRow)
					{
						newColumn=celvalue.getStringCellValue();
						if(newColumn.equals(key))
						{
							IsColumnPresent=true;
							break;
						}

					}
					if(!IsColumnPresent)
					{
						Cell NewCell=firstRow.createCell(firstRow.getPhysicalNumberOfCells());
						NewCell.setCellValue(key);

					}

				}

			} catch (java.util.NoSuchElementException e) {
				System.err.println("Error occur");
			}


			fileIn.close();
			fileIn=null;
			fileOut = new FileOutputStream(ExcelPath);
			filename.write(fileOut);


		} 
		catch (Exception e){
			e.printStackTrace();
			System.err.println(e);


		}   
		finally		
		{
			filename=null;
			fileOut.close();			
			fileOut=null;



		}





	}

	public String UpdateFunction(String sSheetName,String ScenarioName, String TestCase,String ColumnName,String NewValue)
	{
		try
		{

			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();

			//Update cell
			sql = "UPDATE ["+sSheetName+"$] SET  "+ColumnName+" == '"+NewValue+"' where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
			System.out.println(sql);
			//Execute query
			rs1= stmt.executeUpdate(sql);



			conn.commit();			
			conn.close();
			//Runtime.getRuntime().exec("wscript " + vbsfilepath);


		} 
		catch (Exception e)
		{
			System.err.println(e);
		} 

		finally 
		{
			try
			{

				rs.refreshRow();
				rs.close();
				stmt.close();
				if (!(conn.isClosed()))
				{
					conn.commit();
				}
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){}
		}

		return sValue1;  
	}

	public ArrayList<String> GetAllColumnData(String sSheetName,String ColumnId) throws IOException
	{
		ArrayList<String> ColumnList=new ArrayList<String>();
		PathsMethod();
		System.out.println(ExcelPath);
		FileInputStream fileIn = new FileInputStream(ExcelPath);		
		POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
		HSSFWorkbook filename = new HSSFWorkbook(fs);		
		Boolean IsColumnPresent=false;
		Integer ColumnIndex=0;


		try
		{

			HSSFSheet sheet = filename.getSheet(sSheetName);
			Row firstRow = sheet.getRow(0);

			String newColumn;
			for(Cell celvalue:firstRow)
			{
				newColumn=celvalue.getStringCellValue();
				if(newColumn.equals(ColumnId))
				{
					IsColumnPresent=true;
					ColumnIndex=celvalue.getColumnIndex();
					break;
				}

			}
			if(!IsColumnPresent)
			{
				System.err.println("No column found with the given String");
			}

			Integer Rowcount= sheet.getLastRowNum();
			for (int i = 1; i < Rowcount+1; i++) {	

				Row Temprow=sheet.getRow(i);
				Cell cell=Temprow.getCell(ColumnIndex);

				try {


					if(!cell.getStringCellValue().isEmpty())
					{	
						ColumnList.add(cell.getStringCellValue());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if(ColumnList.isEmpty())
			{
				System.err.println("No data found for the given column");
				return null;
			}
			else
			{
				return ColumnList;
			}


		} catch (java.util.NoSuchElementException e) {
			System.err.println("No data found for the given column");
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}


	}

	public String ReadFunctionFromExcel(String sSheetName,String ColumnName)

	{

		try
		{

			//Connect to DB
			//  conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();

			// Retrieve the cell value 
			sql = "Select "+ColumnName+" from ["+sSheetName+"$] " ;
			System.out.println(sql);
			// Execute Query
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				sValue = rs.getString(ColumnName);
				System.out.println(sValue);
			}


		} 
		catch (Exception e){
			System.err.println(e);
		}   



		finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){}
		}
		return sValue;
	}

	public String ReadCellValue(String sSheetName, String KeyColName, String KeyColValue, String ValueColName)
	{	
		try
		{	
			//Connect to DB
			PathsMethod();
			//conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
			conn = DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();

			// Retrieve the cell value 
			sql = "Select "+ValueColName+" from ["+sSheetName+"$] where "+KeyColName+" ='"+KeyColValue+"'" ;

			// Execute Query
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				sValue = rs.getString(ValueColName);
				System.out.println(sValue);
			}	
		} 
		catch (Exception e){System.err.println(e);}

		finally 
		{
			try
			{
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
			}
			catch(Exception e){System.err.println(e);}
		}
		return sValue;
	}

	public String ReadFunction_UsingNumber(String sSheetName,String ScenarioName, String TestCase,String ColumnName)

	{

		try
		{

			//Connect to DB
			conn=DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			stmt=conn.createStatement();

			// Retrieve the cell value 
			sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and Sno='"+TestCase+"'" ;
			// System.out.println(sql);
			// Execute Query
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				sValue = rs.getString(ColumnName);
				System.out.println(sValue);
			}


			/**  while(rs.next())
	          {
	              System.out.println(rs.getString("USERID")+
	                " "+ rs.getString("FIRST_NAME")+" "+ 
	                rs.getString("LAST_NAME"));
	          }**/
		} 


		catch (Exception e){
			System.err.println(e);
		}   

		finally {
			try{
				rs.close();
				stmt.close();
				conn.close();

				rs=null;
				stmt=null;
				conn=null; 
				sql = null;

				System.gc();

			}
			catch(Exception e){}
		}
		return sValue;
	}  

	public String UpdateFunction_SerialNumber(String sSheetName,String ScenarioName, String i,String ColumnName,String NewValue)
	{
		try
		{
			//Connecting to DB
			conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelPath + ";readOnly=false", "", "");
			////conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
			stmt=conn.createStatement();

			//Update cell
			sql = "UPDATE ["+sSheetName+"$] SET  "+ColumnName+" == '"+NewValue+"' where Scenario='"+ScenarioName+"' and Sno='"+i+"'" ;
			//  System.out.println(sql);
			//Execute query
			rs1= stmt.executeUpdate(sql);

			sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and Sno='"+i+"'" ;
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				sValue1 = rs.getString(ColumnName);
				System.out.println(sValue1);
			}
			//Commit
			conn.commit();
			//	          Runtime.getRuntime().exec("wscript C:\\SaveExcel.vbs");


		} 
		catch (Exception e)
		{
			System.err.println(e);
		} 

		finally 
		{
			try
			{

				rs.refreshRow();
				rs.close();
				stmt.close();
				conn.close();
				rs=null;
				stmt=null;
				conn=null; 
				sql= null;

				System.gc();
			}
			catch(Exception e){

			}
		}
		return sValue1;			 

	}

}


