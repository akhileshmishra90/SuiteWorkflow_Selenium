  package com.gui_auto.dao;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;

import org.openqa.selenium.WebDriver;


import com.gui_auto.base_classes.GUI_automation_properties;
public class ReadAndUpdate_Path 
{	
	public static WebDriver _driver;

	private static String FilePath = null;
	private static String BROWSER = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	
	
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
  Connection conn=null;
  Statement stmt=null;
  String sql="";
  ResultSet rs=null; 
  int rs1 ;
  int iRowCount;
  String sValue = null;
  String sValue1 = null;
  String dir = System.getProperty("user.dir");		
  String ExcelPath = dir.concat("\\InputTestData\\DSF_ResponsiveUI.xls");
  //public InputStream ExcelPath1 = ReadAndUpdate.class.getResourceAsStream("/DSF_ResponsiveUI.xls");
 // public String ExcelPath = IOUtils.toString(ExcelPath1, "UTF-8");
  
  
 //   StringWriter writer = new StringWriter();
  //IOUtils.copy(ExcelPath1,writer,encoding);
 // String theString = writer.toString();
  
  // Foo.class.getResourceAsStream("/test.csv")
  
  public String GetPrintOptionImageID(String sSheetName,String Menu, String SubMenu,String SubSubMenu,String ColumnName)
	
	{

		try
	      {
			
			//Connect to DB
			conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath+"");
	         
	          stmt=conn.createStatement();
	     
	         // Retrieve the cell value 
	          sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Menu='"+Menu+"' and SubMenu='"+SubMenu+"'and SubSubMenu='"+SubSubMenu+"'" ;
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
	          }*/
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
  
 public String ReadFunction(String sSheetName,String ScenarioName, String TestCase,String ColumnName)
		
		{
	 
			try
		      {
				
				//Connect to DB
		          conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath+"");
		          stmt=conn.createStatement();
		     
		         // Retrieve the cell value 
		          sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
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
		
 public String ReadFunction_UsingNumber(String sSheetName,String ScenarioName, String TestCase,String ColumnName)
	
	{

		try
	      {
			
			//Connect to DB
	          conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath+"");
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
		
		
		
  public String UpdateFunction(String sSheetName,String ScenarioName, String TestCase,String ColumnName,String NewValue)
		{
			 try
		      {
				 //Connecting to DB
				 conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath+";ReadOnly=False;");
		          ////conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
		          stmt=conn.createStatement();
		          
		          //Update cell
		         sql = "UPDATE ["+sSheetName+"$] SET  "+ColumnName+" == '"+NewValue+"' where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
		       //  System.out.println(sql);
		        //Execute query
		          rs1= stmt.executeUpdate(sql);
		          
		          sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and TestCase='"+TestCase+"'" ;
		          rs=stmt.executeQuery(sql);
		          while(rs.next())
		          {
		          sValue1 = rs.getString(ColumnName);
		          System.out.println(sValue1);
		          }
		         //Commit
		          conn.commit();
//		          Runtime.getRuntime().exec("wscript C:\\SaveExcel.vbs");
              

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
  
  public String UpdateFunction_SerialNumber(String sSheetName,String ScenarioName, String Sno,String ColumnName,String NewValue)
	{
		 try
	      {
			 //Connecting to DB
			 conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath+";ReadOnly=False;");
	          ////conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
	          stmt=conn.createStatement();
	          
	          //Update cell
	         sql = "UPDATE ["+sSheetName+"$] SET  "+ColumnName+" == '"+NewValue+"' where Scenario='"+ScenarioName+"' and Sno='"+Sno+"'" ;
	       //  System.out.println(sql);
	        //Execute query
	          rs1= stmt.executeUpdate(sql);
	          
	          sql = "Select "+ColumnName+" from ["+sSheetName+"$] where Scenario='"+ScenarioName+"' and Sno='"+Sno+"'" ;
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



public String ReadFunctionFromExcel(String sSheetName,String ColumnName)

{

	try
      {
		
		//Connect to DB
         // conn=DriverManager.getConnection("jdbc:odbc:excel","excel",ExcelPath);
          conn=DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ExcelPath);
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
                  	          
        /**  while(rs.next())
          {
              System.out.println(rs.getString("USERID")+
                " "+ rs.getString("FIRST_NAME")+" "+ 
                rs.getString("LAST_NAME"));
          }*/
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

 public String updateColumnValueFromDB(String DBUrl,String JdbcUrl,String username,String Password,String tableName,String columnNametoUpdate,String updateValue,String WhereColumnName,int WherecolumnValue) 
 {
	

	 try
	 {
		 DriverManager.getConnection(DBUrl, username, Password);
		 stmt = conn.createStatement();
		String sql="Update '"+tableName+"' set '"+columnNametoUpdate+"'='"+updateValue+"' Where  '"+ WhereColumnName+"'='"+WherecolumnValue+"'";
		System.out.println("updated query"+sql);
		stmt.executeUpdate(sql);
		 // Retrieve the cell value 
	    sql = "Select "+columnNametoUpdate+" from ["+tableName+"]  Where  '"+ WhereColumnName+"'='"+WherecolumnValue+"'" ;
	    ResultSet rs = stmt.executeQuery(sql);
	 }
	 catch(SQLException se)
	 {
		//Handle errors for JDBC
	      se.printStackTrace();
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

}//class End

