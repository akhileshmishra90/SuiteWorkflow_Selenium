package com.gui_auto.pages;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class eCRMCustomerPages implements BaseElement
{

	
	ReadAndUpdate dbConnection = new ReadAndUpdate();
	ScreenShot TakeScreenShot = new ScreenShot();
	Locators loc = new Locators();
	protected WebDriver _driver;
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
	
	public eCRMCustomerPages(WebDriver driver) throws Exception 
	{
		this._driver = driver;
	}

	public void Login_ecrm(String UN, String PWD) throws InterruptedException
	{
		_driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(UN);
		_driver.findElement(By.name("txtPassword")).sendKeys(PWD);
		_driver.findElement(By.id("Submit1")).click();
		Thread.sleep(10000);
	}
	
	public void Navigate_to_eCrmcustomerpage() throws InterruptedException
	{
		
		
		int SizeVal= _driver.findElements(By.tagName("frame")).size();
		System.out.println(SizeVal);
		
		WebElement element1= _driver.findElement(By.xpath("//frame[@name='menu']"));
		_driver.switchTo().frame(element1);
		
		
		
		
	//	_driver.findElement(By.xpath("//a[@title='Customer Management']")).click();	
	if	(_driver.findElements(By.xpath("//a[@title='Customer Management']/img")).size()>0)
	{
		_driver.findElement(By.xpath("//a[@title='Customer Management']/img")).click();
	}
	_driver.switchTo().defaultContent();
	WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
	_driver.switchTo().frame(element2);
	    _driver.findElement(By.xpath("//span[text()='Customers'][1]")).click();
	 
	      
	 if (_driver.findElements(By.xpath("//span[text()='Customer List']")).size()>0)
	 {
		 System.out.println("Customer List Page is displayed");
	 }
	 else
		 System.err.println("Customer List page is not displayed");
	
	 _driver.findElement(By.xpath("//span[text()='New']")).click();
	 Thread.sleep(2000);
	_driver.findElement(By.xpath("//span[text()='New Customer']")).click();
	
	Thread.sleep(10000);
	if (_driver.findElements(By.xpath("//span[text()='Customer Edit']")).size()>0)
	{
		System.out.println("Customer Edit page is displayed");
		
	}
	else
	{
		System.err.println("Customer edit page is not displayed please investigate");
	}
		_driver.switchTo().defaultContent(); 
	}
	
	public void Navigate_CutomerList_Page()
	{
		int SizeVal= _driver.findElements(By.tagName("frame")).size();
		System.out.println(SizeVal);
		
		WebElement element1= _driver.findElement(By.xpath("//frame[@name='menu']"));
		_driver.switchTo().frame(element1);
		
		
		
		
	//	_driver.findElement(By.xpath("//a[@title='Customer Management']")).click();	
	if	(_driver.findElements(By.xpath("//a[@title='Customer Management']/img")).size()>0)
	{
		_driver.findElement(By.xpath("//a[@title='Customer Management']/img")).click();
	}
	_driver.switchTo().defaultContent();
	WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
	_driver.switchTo().frame(element2);
	    _driver.findElement(By.xpath("//span[text()='Customers'][1]")).click();
	    _driver.switchTo().defaultContent();
	}
	
	public String Create_New_Customer(String CustomerName, String sSalesperson, String saddress1, String sCity, String postalCode) throws InterruptedException
	{
		String Cusid = null;
		WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element2);
		_driver.findElement(By.xpath("//input[contains(@id,'txtCompanyName')]")).sendKeys(CustomerName);
		Select DropdownSalesperson=new Select(_driver.findElement(By.id("ctl00_cphMain_cbxSalesperson")));
		DropdownSalesperson.selectByVisibleText(sSalesperson);
		_driver.findElement(By.id("ctl00_cphMain_txtAddress1")).sendKeys(saddress1);
		_driver.findElement(By.id("ctl00_cphMain_txtTown")).sendKeys(sCity);
		_driver.findElement(By.id("ctl00_cphMain_txtPostCode")).sendKeys(postalCode);
		_driver.findElement(By.xpath("//span[text()='Save']")).click();
		Thread.sleep(5000);
		
		if (_driver.findElements(By.xpath("//span[contains(@id,'cphMain_lblCompanyName')]")).size()>0)
		{
			String CusName=_driver.findElement(By.xpath("//span[contains(@id,'cphMain_lblCompanyName')]")).getText();
			System.out.println("Customer Created is :"+CusName);
			 Cusid=_driver.findElement(By.xpath("//span[contains(@id,'cphMain_lblCompanyId')]")).getText();
		}
		else
		{
			System.err.println("Customer is not created Successfully Please investigate");
			org.junit.Assert.fail("Customer is not created Successfully Please investigate");
		}
		return Cusid;
		
	}
	
	public void Navigate_To_RFQ_Page() throws InterruptedException
	{
		WebElement element1= _driver.findElement(By.xpath("//frame[@name='menu']"));
		_driver.switchTo().frame(element1);
		
//		WebElement element3= _driver.findElement(By.id("Form1"));
//		_driver.switchTo().frame(element3);
		
		_driver.findElement(By.xpath("//*[@id='pbNavigation_p2']/img")).click();
		_driver.switchTo().defaultContent(); 
		
		WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element2);
		_driver.findElement(By.xpath("//*[@id='pbNavigation_p2_p0']")).click();
		Thread.sleep(5000);
		
		
		
		
		if (_driver.findElements(By.xpath("//span[text()='Rfq']")).size()>0)
		{
			System.out.println("RFQ page is Displayed");
		}
		else
		{
			System.err.println("RFQ page is not displayed ..Please investigate");
		}
	
		_driver.switchTo().defaultContent();
	}
	
	public void Select_open_RFQ(String rfqCustomer, String rfqForm) throws InterruptedException
	{
		WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element2);
		_driver.findElement(By.xpath("//span[text()='New']")).click();
		
		
		_driver.findElement(By.xpath("//input[contains(@id,'cphMain_createRfqPopup_C_cbxCustomerId_Input')]")).sendKeys(rfqCustomer);
		String rfqCust="//li[text()='"+rfqCustomer+"']";
		_driver.findElement(By.xpath(rfqCust)).click();
		_driver.findElement(By.xpath("//span[text()='Opportunity:']")).click();
		_driver.findElement(By.id("cbxFormId_Input")).sendKeys(rfqForm);
//		String rfqFormxpath="//li[text()='"+rfqForm+"']";
//		_driver.findElement(By.xpath(rfqFormxpath)).click();
		_driver.findElement(By.xpath("//span[text()='Opportunity:']")).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath("//input[contains(@id,'cphMain_createRfqPopup_C_btnPopupProceed_input')]")).click();
		
		if (_driver.findElements(By.xpath("//u[text()='Header Details']")).size()>0)
		{
			System.out.println("Navigation to form SuccessFull");
		}
		else
		{
			System.err.println("Navigation to form not successfull please investigate");
		}
		_driver.switchTo().defaultContent();
	}
	
	public String CreateRFQ(String repUsername, String userContact, String assigne, String rfqStatus, String refDescription, String Quantity) throws Exception
	{
		//_driver.findElement(By.xpath("//select[contains(@name,'repusername')]"));
		WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element2);
		WebElement element3= _driver.findElement(By.xpath("//iframe[@id='rfqFrame']"));
		_driver.switchTo().frame(element3);
		
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[contains(@name,'repusername')]"), repUsername);
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[contains(@name,'contactName')]"), userContact);
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[contains(@name,'assignee')]"), assigne);
		CommonFunctions.selectDropdownByText(_driver,By.xpath("//select[contains(@name,'rfqstatus')]"), rfqStatus);
		_driver.findElement(By.xpath("//input[(contains(@name,'reference') and (@value='Carton Product -'))]")).clear();
		_driver.findElement(By.xpath("//input[(contains(@name,'reference') and (@value='Carton Product -'))]")).sendKeys(refDescription);
		_driver.findElement(By.xpath("//input[contains(@name,'Quantity1')]")).clear();
		_driver.findElement(By.xpath("//input[contains(@name,'Quantity1')]")).sendKeys(Quantity);
	  
		_driver.switchTo().defaultContent();
		WebElement element4= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element4);
		_driver.findElement(By.xpath("//span[text()='Submit Rfq']")).click();
	    
	    Alert alert = _driver.switchTo().alert();
	    String AlertText= _driver.switchTo().alert().getText();
	    System.out.println(AlertText);
	    alert.accept();
	    
	    String[] id=AlertText.split("\\s");
	    String idval=id[1];
	    String[] rfq=idval.split("-");
	    String rfqid=rfq[0];
	    System.out.println("RFQ Id Creadted is : "+rfqid);
		return rfqid;
	    
	}
	
	public void eCRM_Validate_Customer(String sCustomerName, String sCustDesignation) throws InterruptedException
	{
		WebElement element2= _driver.findElement(By.xpath("//frame[@name='master']"));
		_driver.switchTo().frame(element2);
		
		_driver.findElement(By.xpath(".//input[contains(@id,'cphMain_CustomerList1_tbarSearch_i0_txtSearch')]")).sendKeys(sCustomerName);
		_driver.findElement(By.xpath("//span[text()='Search']")).click();
		Thread.sleep(5000);
		if (_driver.findElement(By.xpath("//table[@id='ctl00_cphMain_CustomerList1_grdCustomers_ctl00']/tbody")).isDisplayed())
		{
			  System.out.println("Customer Data grid is displayed");
			  int value=_driver.findElements(By.xpath("//table[@id='ctl00_cphMain_CustomerList1_grdCustomers_ctl00']/tbody/tr")).size();
		      System.out.println(value);
			  for (int i=1;i<=value;i++)
			  {
				  String xpathval="//table[@id='ctl00_cphMain_CustomerList1_grdCustomers_ctl00']/tbody/tr["+i+"]/td[5]";
				  String Custname=_driver.findElement(By.xpath(xpathval)).getText();
				  System.out.println(Custname);
				   if (Custname.equals(sCustomerName))
				   {
					   System.out.println("Customer is displayed, Now check the Designation");
					   String Desigxpath="//table[@id='ctl00_cphMain_CustomerList1_grdCustomers_ctl00']/tbody/tr["+i+"]/td[6]";
					   String Designationval=_driver.findElement(By.xpath(Desigxpath)).getText();
					   if (sCustDesignation.equals(Designationval))
					   {
						   System.out.println("After edit in radius the designation of the Customer in ecrm is : "+Designationval);
					   }
					   else
					   {
						   System.err.println("After edit in radius the designation of the Customer in ecrm is : "+Designationval);
						   Assert.fail("After edit in radius the designation of the Customer in ecrm is : "+Designationval);;
					   }
				   }
			  }
		
		}
		else
		  {
			  System.err.println("Customer Data grid is not displayed");
		  }
	
	
	
	
	
	
	
	}
}

