package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class PayrollPage implements BaseElement
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
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	
	public PayrollPage(WebDriver driver) throws Exception {
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
	
	public void navigateToPayrollSettings() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name("payBasis"));
		assertEquals("Payroll Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPayrollSettings");
		System.out.println("****Payroll Setup page appears****");
	}
	
	public void navigateToPayTypes() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollPayType/list");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Pay Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPayTypes");
		System.out.println("****Pay Types list page appears****");
	}
	
	public void navigateToDeductionTypes() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollDeductionType/list");
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Deduction Types", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToDeductionTypes");
		System.out.println("****Deduction Types list page appears****");
	}
	
	public void navigateToCheckTypes() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollCheckType/list");
		CommonFunctions.Wait(_driver, By.name("addGridRow_active"));
		assertEquals("Check Types - Active", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToCheckTypes");
		System.out.println("****Check Types - Active list page appears****");
	}
	
	public void navigateToTaxTables() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollTaxTable/list");
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Add new Record']"));
		assertEquals("Tax Table", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToTaxTables");
		System.out.println("****Tax Tablelist page appears****");
	}
	
	public void navigateToPayrollEmployeeList() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Employee/listPayroll");
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Employees", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPayrollEmployeeList");
		System.out.println("****Employees list page appears****");
	}
	
	public void navigateToEnterNewBatch() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollBatch/add");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Adding Payroll Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToEnterNewBatch");
		System.out.println("****Adding Payroll Batch page appears****");
	}
	
	public void navigateToPostPayroll() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/process/run?key=PayrollBatch.post");
		CommonFunctions.Wait(_driver, By.xpath("//input[@value='Post Payroll Batch']"));
		assertEquals("Post Payroll Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPostPayroll");
		System.out.println("****Post Payroll Batch page appears****");
	}
	
	public void navigateToAPEditApproveBills() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/BillBatch/list?");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Edit/Approve Bill Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToAPEditApproveBills");
		System.out.println("****Edit and Approve Bills page appears****");
	}
	
	public void navigateToGLEditApproveBatch() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/GLBatch/listUnPosted");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Edit & Approve GL Batches", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToGLEditApproveBatch");
		System.out.println("****Edit and Approve GL Batches page appears****");
	}
	
	public void addDeductiontypetoEmployee (String Deductiontype) throws Exception
	{
		String  originalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		Thread.sleep(3000);
		
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty())
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Employee Deduction"))
				{
					CommonFunctions.selectDropdownByText(_driver, By.name("payrollDeductionTypeID"), Deductiontype);
					Thread.sleep(1000);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(2000);
					break;
				}
				else
				{
					_driver.switchTo().window(originalHandle);
				}
			}				
		}
		else
		{
			System.err.println("Adding Employee Deduction window did not appear.");
		}
		_driver.switchTo().window(originalHandle);
	}
	
	public void navigateToPayrollBatchList() throws Exception, IOException
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PayrollBatch/list");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		assertEquals("Payroll Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPayrollEmployeeList");
		System.out.println("****Employees list page appears****");
	}
	
	public String AddPayrollBatch() throws Exception
	{
		String PayrollBatch = "Payroll Batch "+UniqueNum5Digit();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String sAccPeriod = dateFormat.format(date);
		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
			Thread.sleep(1000);
		}
		assertEquals("Adding Payroll Batch", _driver.getTitle());
		CommonFunctions.SendValue(_driver, By.name("beginDate"), "t-");
    	CommonFunctions.SendValue(_driver, By.name("endDate"), "t+");
    	CommonFunctions.SendValue(_driver, By.name("checkDate"), "t");
    	CommonFunctions.SendValue(_driver, By.name("description"), PayrollBatch);
    	if (CommonFunctions.verifyOptionExistsinDropdown(_driver, By.name("glAccountingPeriodID"), sAccPeriod))
    	{
    		CommonFunctions.selectDropdownByText(_driver, By.name("glAccountingPeriodID"), sAccPeriod);
    	}
    	else
    	{
    		int iOptionCnt = CommonFunctions.RowCount(_driver, By.xpath("//select[@name='glAccountingPeriodID']/option"));
    		CommonFunctions.selectDropdownByIndex(_driver, By.name("glAccountingPeriodID"), iOptionCnt-1);
    	}
    	CommonFunctions.selectDropdownByIndex(_driver, By.name("glAccountingPeriodID"), 1);
    	CommonFunctions.selectDropdownByText(_driver, By.name("payPeriod"), "1");
    	CommonFunctions.selectDropdown(_driver, By.name("beginDepartment"), "000");
    	CommonFunctions.selectDropdown(_driver, By.name("endDepartment"), "400");
    	CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
    	CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
    	
    	assertEquals("Payroll Batch "+PayrollBatch, _driver.getTitle());
    	return PayrollBatch;
	}
	
	public boolean CreatePayrollEmployee(String sEmpId,String sFN,String sLN,String Dept,String Add1,String Status,String Phone,String Country,String sState,String zip) throws Exception, IOException
	  {
		  
		boolean sFlag = false;
		
		navigateToPayrollEmployeeList();
		_driver.findElement(By.xpath("//a[contains(text(), 'Add New')]")).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Emp_Add)));
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Employee_ID)), sEmpId);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_FirstName)), sFN);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_LastName)), sLN);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Department)),Dept);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_Add1)), Add1);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Emp_Status)),Status);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_Phone)), Phone);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Country)),Country);
		Thread.sleep(1000);
		CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_StateKey)),sState);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Emp_Zip)), zip);
		  
		CommonFunctions.ClickElement(_driver, By.linkText(Locators.getProperty(Locators.Employee_PayRoll)));
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Employee_PayRoll_PayRate01)));
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Employee_PayRoll_PayRate01)), "10");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Emp_Add)));
		
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Tax Info']"));
		CommonFunctions.selectDropdown(_driver, By.name("location"), "1");
		
		if(CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Employee_Object_added_text))))
		{
			sFlag = true;
		}
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "CreateEmp");
		System.out.println(Date()+ NewFileNamePath);
	    
	    return sFlag;		  
	  }
	
}