package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class WorkflowIntegrationPage implements BaseElement 
{
	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbconnection = new ReadAndUpdate();
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public WorkflowIntegrationPage(WebDriver driver) throws Exception 
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

	public void navigateToPaceConnectsPage(String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/PaceConnect/list");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("searchField"));
		assertEquals("Pace Connects", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPaceConnectsPage");
		System.out.println("****Pace Connects page appears****");
	}

	public void navigateToPaceConnectDetailPage(String PaceConnectID, String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/PaceConnect/detail/"+PaceConnectID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("activeBooleanCheck"));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToPaceConnectDetailPage");
		System.out.println("****Pace Connects detail page appears****");
	}

	public void navigateToPaceConnectDetailPageByName(String PaceConnectName, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		navigateToPaceConnectsPage(Server, Company);
		DC.SearchValue(PaceConnectName, "name");
		CommonFunctions.waitForPageLoaded(_driver);
	}

	public boolean verifyJobPlansScheduledCheckBox(int TimeOut) throws Exception
	{
		JobPlanningPage JP = new JobPlanningPage(_driver);
		DCPage DC = new DCPage(_driver);

		boolean IsScheduledBoxChecked = false;
		int loopCount = 0;

		String sPFForm = CommonFunctions.GetText(_driver, By.xpath("//table[@id='JobPlan']/tbody/tr[1]/td[3]/div"));
		if (sPFForm == null || sPFForm.equals(""))
		{
			JP.UpdateLinksJobPlan();
			DC.Update();
		}

		IsScheduledBoxChecked = CommonFunctions.VerifyChecked(_driver, By.name("scheduled"));
		while (!IsScheduledBoxChecked && loopCount < TimeOut)
		{
			Thread.sleep(5000);
			_driver.navigate().refresh();
			CommonFunctions.waitForPageLoaded(_driver);
			IsScheduledBoxChecked = CommonFunctions.VerifyChecked(_driver, By.name("scheduled"));
			loopCount++;
		}

		return IsScheduledBoxChecked;

	}

	public void DisableEnablePaceConnects(String Type, String UpdateAction, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		navigateToPaceConnectsPage(Server, Company);
		DC.SearchValue(Type, "type");
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//a[text() = '(all)']"))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text() = '(all)']"));
			if(UpdateAction.toLowerCase().equals("disable"))
			{
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text() = '(all)']"));
			}
		}
		else if (CommonFunctions.isElementPresent(_driver, By.name("activeBooleanCheck")))
		{
			if(UpdateAction.toLowerCase().equals("disable"))
			{
				CommonFunctions.sSelectCheckBox(_driver, false, By.name("activeBooleanCheck"));
			}
			else
			{
				CommonFunctions.sSelectCheckBox(_driver, true, By.name("activeBooleanCheck"));
			}
		}

		DC.Update();
	}

	public void ModifyPaceConnect(String paceConnectID, String UpdateAction, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		navigateToPaceConnectDetailPage(paceConnectID, Server, Company);
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.isElementPresent(_driver, By.name("activeBooleanCheck")))
		{			
			if(UpdateAction.toLowerCase().equals("disable"))
			{
				CommonFunctions.sSelectCheckBox(_driver, false, By.name("activeBooleanCheck"));
			}
			else
			{
				CommonFunctions.sSelectCheckBox(_driver, true, By.name("activeBooleanCheck"));
			}
		}

		DC.Update();
	}

	public void DisableEnablePaceConnectsByName(String PaceConnectName, String UpdateAction, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		navigateToPaceConnectsPage(Server, Company);
		DC.SearchValue(PaceConnectName, "name");
		CommonFunctions.waitForPageLoaded(_driver);

		if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//a[text() = '(all)']"))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text() = '(all)']"));
			if(UpdateAction.toLowerCase().equals("disable"))
			{
				CommonFunctions.ClickElement(_driver, By.xpath("//a[text() = '(all)']"));
			}
		}
		else if (CommonFunctions.isElementPresent(_driver, By.name("activeBooleanCheck")))
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.name("activeBooleanCheck"));
			if(UpdateAction.toLowerCase().equals("disable"))
			{
				CommonFunctions.sSelectCheckBox(_driver, false, By.name("activeBooleanCheck"));
			}
		}

		DC.Update();
	}

	public void enableInputLocation(String Name) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Inputs']"));

		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='Inputs']/tbody/tr"));
		for (int i=1; i<=iRwCnt; i++)
		{
			String sInputLocationName = CommonFunctions.getText(_driver, By.xpath("//table[@id='Inputs']/tbody/tr["+i+"]/td[3]/div"));
			if (sInputLocationName.equals(Name))
			{
				CommonFunctions.sSelectCheckBox(_driver, true, By.xpath("//table[@id='Inputs']/tbody/tr["+i+"]//input[@name='Inputs.activeBooleanCheck']"));
			}
			else
			{
				CommonFunctions.sSelectCheckBox(_driver, false, By.xpath("//table[@id='Inputs']/tbody/tr["+i+"]//input[@name='Inputs.activeBooleanCheck']"));
			}
		}

		DC.Update();
	}

	public void NavigateToJob(String Job, String Server, String Company) throws Exception
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Job/detail/"+Job);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Description)));
		assertEquals("Job "+Job, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJob");
	}

	public void NavigateToJobPart(String Job, String JobPart, String Server, String Company) throws Exception
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/JobPart/detail/"+Job+"%3A"+JobPart);	
		CommonFunctions.Wait(_driver, By.name("description_label"));
		assertEquals("Job "+Job+" part "+JobPart, _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"NavigateToJobPart");
	}

	public boolean JobPartJobCostPage(String sJob,String sPart, String Server, String Company) throws Exception
	{
		String sJobCostPage = "Job Costs for Job "+sJob+" Part "+sPart;
		sJob = sJob.replace(" ", "%20");

		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/JobPart/jobCost/"+sJob+"%3A"+sPart);
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		String sTitle = _driver.getTitle();
		if(sJobCostPage.equals(sTitle))
		{
			System.out.println(Date()+": Navigated to Job Cost page of Job : "+sJob+" and Job Part : "+sPart);			
			return true;
		}
		else
		{
			System.err.println(Date()+": Unable to navigate to Job Cost page of Job : "+sJob+" and Job Part : "+sPart);
			return false;
		}	 
	}

	public void navigateToProductionPlanPage(String JobID, String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/Job/plan/"+JobID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToProductionPlanPage");
	}

	public void Click_on_updatebutton()
	{
		_driver.findElement(By.xpath("//div[@id='buttonbox']/input[ @value='Update']")).click();
		CommonFunctions.waitForPageLoaded(_driver);

		if (_driver.findElements(By.xpath("//div[@id='fmessage']/ul/li[text()='Updated ']")).size()>0)
		{
			System.out.println("Update is Successfull..");
		}
		else
		{
			System.err.println("update is not Successfull...");
		}
	}

	public String FetchJobNumber(String dsfOrderID, String Server, String Company) throws Exception
	{
		String sJobNumber ="";
		String sJobNumber_1 ="";
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Job/list");
		Thread.sleep(5000);
		System.out.println("**Job List page appears**");
		CommonFunctions.selectDropdownByText(_driver, By.name("list"), "Open Jobs");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "DSF Order ID");
		Thread.sleep(1000);
		System.out.println("Select DSF Order ID from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(dsfOrderID);
		System.out.println("Entering the dsf order is as: "+dsfOrderID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		Thread.sleep(5000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Jobs - Open Jobs"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		}
		else if(sTitle.equals("Jobs-ps - Open Jobs"))
		{
			sJobNumber_1 = _driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[3]/div")).getText();
			System.out.println("Job Number is: " +sJobNumber_1);
			_driver.get("http://"+Server+"/epace/company:public/object/Job/detail/"+sJobNumber_1);
		}

		_driver.findElement(By.xpath("//a[text()='Pace Connect']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		String sOrderNum = 	_driver.findElement(By.xpath("//div/h4[contains(label,'DSF Order ID')]/following-sibling::div/div")).getText();

		sOrderNum=sOrderNum.replace(",", "");
		sOrderNum=sOrderNum.trim();
		System.out.println("sOrderNum is "+sOrderNum);

		if(sOrderNum.equals(dsfOrderID))
		{
			sJobNumber= _driver.findElement(By.xpath("//div[@id='scrollableContent']/fieldset/div[1]/div[1]/div[1]/div/div")).getText();
			sJobNumber=sJobNumber.trim();
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Info']"));

		return sJobNumber;
	}

	public String FetchJobNumber_Printstream(String dsfOrderID, String Server, String Company) throws Exception
	{
		String sJobNumber ="";
		String sJobNumber_1 ="";

		String JobDescription="	PS Order # "+dsfOrderID+" - Fulfillment";
		System.out.println(JobDescription);
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Job/list");
		Thread.sleep(5000);
		System.out.println("**Job List page appears**");
		CommonFunctions.selectDropdownByText(_driver, By.name("list"), "Open Jobs");
		Thread.sleep(1000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Search_Dropdown)), "Description");
		Thread.sleep(1000);
		System.out.println("Select Description from drowdown");
		_driver.findElement(By.name(Locators.getProperty(Locators.Search_TextField))).sendKeys(JobDescription);
		System.out.println("Entering the dsf order is as: "+dsfOrderID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Find))).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);

		//If the Job doesn't open directly, click the magnifying glass to open it
		Thread.sleep(5000);

		String sTitle = _driver.getTitle();
		System.out.println("Title of the page is "+sTitle);

		if(sTitle.equals("Jobs - Open Jobs"))
		{
			System.out.println("Click the magnifying glass to go to the detail page");
			_driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[2]/div/a/img")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		}
		else if(sTitle.equals("Jobs-ps - Open Jobs"))
		{
			sJobNumber_1 = _driver.findElement(By.xpath("//*[@id='openJobs']/tbody/tr[1]/td[3]/div")).getText();
			System.out.println("Job Number is: " +sJobNumber_1);
			_driver.get("http://"+Server+"/epace/company:public/object/Job/detail/"+sJobNumber_1);
		}

		_driver.findElement(By.xpath("//a[text()='Pace Connect']")).click(); CommonFunctions.waitForPageLoaded(_driver); Thread.sleep(1000);
		Thread.sleep(2000);
		String sOrderNum = 	_driver.findElement(By.xpath("//div/h4[contains(label,'PrintStream Order ID')]/following-sibling::div/div")).getText();

		sOrderNum=sOrderNum.replace(",", "");
		sOrderNum=sOrderNum.trim();
		System.out.println("sOrderNum is "+sOrderNum);

		if(sOrderNum.equals(dsfOrderID))
		{
			sJobNumber= _driver.findElement(By.xpath("//div[@id='scrollableContent']/fieldset/div[1]/div[1]/div[1]/div/div")).getText();
			sJobNumber=sJobNumber.trim();
			System.out.println("Job Number is "+sJobNumber);
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Info']"));

		return sJobNumber;
	}

	public void NavigateToDataCollectionLogin(String Server, String Company) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);;
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Employee/lookup");	
		Thread.sleep(2000);
		boolean sAlert = PO.isAlertPresent();
		if(sAlert == true)
		{
			_driver.switchTo().alert().accept();
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.DCL_Login)));
		assertEquals("Data Collection Login", _driver.getTitle());
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"DataCollectionLogin");
	}

	public void navigateToApproveJobCostsPage(String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/JobCost/listApprove");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)));		
		assertEquals("Approve Job Costs - All", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToApproveJobCostsPage");
		System.out.println("****Approve Job Costs page appears****");
	}

	public void navigateToPostJobCostsPage(String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/process/run?key=JobCost.post");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PostJobCost_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Posting Job Costs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToPostJobCostsPage");
		System.out.println("****Post Job Costs page appears****");
	}

	public void NavigateToEnterNewBatch(String Server, String Company) throws Exception 
	{
		_driver.get("http://" + Server + "/epace/company:" + Company + "/object/InvoiceBatch/add");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,"NavigateToEnterNewBatch");
	}

	public void NavigateToJobPart(String Server, String Company, int Jobid ,int partnumber) throws Exception
	{
		_driver.get("http://" + Server + "/epace/company:" + Company + "/object/JobPart/detail/"+Jobid+"%3A0"+partnumber);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals("Adding Batch", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,"NavigateToEnterNewBatch");

	}

	public void NavigateToEnterNewInvoicePage(String Server, String Company) throws Exception
	{
		_driver.get("http://" + Server + "/epace/company:" + Company + "/object/Invoice/add");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.InvoiceType)));
		assertEquals("Adding Invoice Transaction", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.ScreenShotWindow(_driver,"NavigateToEnterNewInvoicePage");
	}

	public void AddNewInvoice(String sJobId, String BatchId) throws Exception
	{
		System.out.println("Navigate to enter new Invoice Page ");
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(sJobId);
		_driver.findElement(By.xpath("//input[contains(@name,'job')]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.InvoiceType)), "Invoice");
		Thread.sleep(1000);

		if (_driver.findElements(By.name("invoiceBatch2")).size() > 0) 
		{
			CommonFunctions.selectDropdownByValue(_driver, By.name("invoiceBatch2"), BatchId);
			Thread.sleep(1000);
		}
		else
		{
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.InvoiceType)), 1);
			Thread.sleep(1000);
		}
		CommonFunctions.selectDropdownByIndex(_driver,By.name(Locators.getProperty(Locators.SaleDistributionMethod)),1);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);

		if (_driver.getTitle().equals("Adding Invoice Transaction"))
		{
			if (CommonFunctions.GetText(_driver, By.xpath(Locators.getProperty(Locators.WarningMessageAtTop))).equals("Must distribute tax when using job shipment zip sales tax basis: Invoice[distributeTax=false][distributeTax]"))
			{
				CommonFunctions.selectDropdown(_driver,By.name("taxDistributionMethod"), "1");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Add_button))).click();
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
			}
		}

		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text))))			
		{
			System.out.println("Invoice is Created successfully InvoiceNum");
		}
		else
		{
			Assert.fail("Failed to create the invoice");
		}
	}

	public void ApprovedInvoiceBatch(String BatchId, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		_driver.get("http://" + Server + "/epace/company:" + Company + "/object/InvoiceBatch/detail/"+BatchId+"?tab=0");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("approvedBooleanCheck"));
		CommonFunctions.ClickElement(_driver, By.name("approvedBooleanCheck"));
		DC.Update();		
	}

	public void navigateToJobShipmentListpage(String Job, String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/JobShipment/listJob/"+Job);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//a[text()='Add Detailed Record']"));
		assertEquals("Job Shipments", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobPartShipmentListpage");
		System.out.println("****Job Shipments List page appears****");
	}

	public void navigateToJobShipmentDetailpage(String JobShipmentID, String Server, String Company) throws Exception
	{
		_driver.get("http://"+ Server +"/epace/company:"+ Company +"/object/JobShipment/detail/"+JobShipmentID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("shipmentType"));
		assertEquals("Job Shipment", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobShipmentDetailpage");
		System.out.println("****Job Shipments Detail page appears****");
	}

	public void CallPythonScript(String jobName, String PythonScriptPath) throws Exception
	{
		String jobname1 = "\""+jobName+"\"";
		String pythonScriptPath = PythonScriptPath;
		String[] cmd = new String[3];
		cmd[0] = "C:\\Python26\\python.exe";
		cmd[1] = pythonScriptPath;
		cmd[2] = jobname1;

		//create runtime to execute external command
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);

		//retrieve output from python script
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while((line = bfr.readLine()) != null) 
		{
			// display each output line form python script
			System.out.println(line);
		}
	}

	public void NavigateToCustomerReceivablesPage(String sCustomer, String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Receivable/list/"+sCustomer);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//table[@id='OpenInvoices']"));
		assertEquals(_driver.getTitle(), "Receivables - Open Invoices");
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "NavigateToCustomerReceivablesPage");
	}

	public boolean VerifyInvoiceDetailsInCustReceivables(String sCustomer, String sInvoiceNumber, String sInvoiceAmount, String Server, String Company) throws Exception, IOException
	{
		boolean bInvoiceExists = false, bInvoiceDetailMatches = false;

		NavigateToCustomerReceivablesPage(sCustomer, Server, Company);

		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='OpenInvoices']/tbody/tr"));
		for (int i=1; i<=iRwCnt; i++)
		{
			String sInvNum = CommonFunctions.getText(_driver, By.xpath("//table[@id='OpenInvoices']/tbody/tr["+i+"]/td[3]/div"));
			if (sInvNum.equals(sInvoiceNumber))
			{
				bInvoiceExists = true;
				String sInvAmount = CommonFunctions.getText(_driver, By.xpath("//table[@id='OpenInvoices']/tbody/tr["+i+"]/td[11]/div"));

				if (sInvAmount.equals(sInvoiceAmount))
				{
					System.out.println("Invoice exists and Invoice amount macthes");
					bInvoiceDetailMatches = true;
				}
				else
				{
					System.err.println("Invoice Amount, Expected = "+sInvoiceAmount+", Actual = "+sInvAmount);
				}
				break;
			}
		}

		if (!bInvoiceExists) {System.err.println("Invoice doesnot exists in customer receivables page");}

		return bInvoiceDetailMatches;
	}

	public boolean VerifyPaymentDetailsInCustPayables(String sCustomer, String sInvoiceAmount, String Server, String Company) throws Exception, IOException
	{
		boolean bPaymentRecordMatches = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String sDate = sdf.format(new Date());

		NavigateToCustomerPaymentsPage(sCustomer, Server, Company);

		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='YTD']/tbody/tr"));
		for (int i=1; i<=iRwCnt; i++)
		{
			String sDateDisplayed = CommonFunctions.getText(_driver, By.xpath("//table[@id='YTD']/tbody/tr["+i+"]/td[3]/div"));			
			if (sDateDisplayed.equals(sDate))
			{
				String sAmount = CommonFunctions.getText(_driver, By.xpath("//table[@id='YTD']/tbody/tr["+i+"]/td[6]/div"));

				if (sAmount.equals(sInvoiceAmount))
				{
					System.out.println("Payment record exists and Invoice amount matches");
					bPaymentRecordMatches = true;
					break;
				}			
			}
		}

		if (!bPaymentRecordMatches) 
		{
			TakeScreenShot.ScreenShotWindow(_driver, "Error_CustomerPayables_");
			System.err.println("Payment record doesnot exists in customer Payables page");
		}

		return bPaymentRecordMatches;
	}

	public void NavigateToAddPaymentBatchPage(String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/PaymentBatch/add");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals(_driver.getTitle(), "Adding Batch");
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "NavigateToAddPaymentBatchPage");
	}

	public void AddPayment(String Customer, String InvoiceNumber, String InvoiceAmount) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.waitForPageLoaded(_driver);

		CommonFunctions.SendValue(_driver, By.name("customer"), Customer);
		CommonFunctions.SendValue(_driver, By.name("amount"), InvoiceAmount);
		CommonFunctions.selectDropdownByText(_driver, By.name("receivableSelectionMethod"), "Invoice Number");
		DC.Add();

		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Pick Receivables For Payment']"));
		Thread.sleep(2000);
		CommonFunctions.SwitchToWindow(_driver, "Adding items to Payment Transaction");
		CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(), '"+InvoiceNumber+"')]"));
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Add Items']"));

		CommonFunctions.ReturnToOriginalWindow(_driver);
		CommonFunctions.waitForPageLoaded(_driver);

		if (!CommonFunctions.isTextPresent(_driver, InvoiceNumber))
		{
			Assert.fail("Payment is not added");
		}
	}

	public void NavigateToPaymentBatchDetailPage(String PaymentBatchID, String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/PaymentBatch/detail/"+PaymentBatchID);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("glAccountingPeriod"));
		assertEquals(_driver.getTitle(), "Batch");
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "NavigateToPaymentBatchDetailPage");
	}

	public void NavigateToPostPaymentsPage(String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/process/run?key=PaymentBatch.post");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("postSingleBatch"));
		assertEquals(_driver.getTitle(), "Post Customer Payments");
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "NavigateToPostPaymentsPage");
	}

	public boolean ApproveAndPostPaymentBatch(String PaymentBatchID, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);

		NavigateToPaymentBatchDetailPage(PaymentBatchID, Server, Company);
		CommonFunctions.sSelectCheckBox(_driver, true, By.name("approvedBooleanCheck"));
		DC.Update();

		NavigateToPostPaymentsPage(Server, Company);
		CommonFunctions.selectDropdown(_driver, By.name("postSingleBatch"), PaymentBatchID);
		CommonFunctions.ClickElement(_driver, By.xpath("//input[@value='Post Customer Payments']"));
		Thread.sleep(15000);
		CommonFunctions.waitForPageLoaded(_driver);

		if ("Payment Post Successful!".equals(_driver.getTitle()))
		{
			System.out.println("payment Batch "+PaymentBatchID+" is approved and posted successfully");
			return true;
		}
		else
		{
			System.err.println("payment Batch "+PaymentBatchID+" is NOT posted successfully");
			return false;
		}
	}

	public void gotoCustomerDetailPage(String Customer, String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Customer/detail/"+Customer.toUpperCase());
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("customerStatus"));
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver,"gotoCustomerDetailPage");
	}

	public void NavigateToCustomerPaymentsPage(String sCustomer, String Server, String Company) throws Exception, IOException
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Payment/listCustomer/"+sCustomer);
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath("//table[@id='OpenInvoices']"));
		//		assertEquals(_driver.getTitle(), "Payments - YTD");
		NewFileNamePath =  TakeScreenShot.ScreenShotWindow(_driver, "NavigateToCustomerPaymentsPage");
	}

	public void NavigateToEnterNewEstimate(String Server, String Company) throws Exception
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Estimate/add");	
		CommonFunctions.waitForPageLoaded(_driver);
		//		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimate_Number)));
	}

	public void getJobPartDetails(String Sheet, String Scenario, String Testcase, String JobPart) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Part Details']"));
		String sFinishSize = CommonFunctions.GetValue(_driver, By.name("finalSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("finalSizeH"));
		String sFlatSize = CommonFunctions.GetValue(_driver, By.name("flatSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("flatSizeH"));
		String sPages = CommonFunctions.GetSelectedOption(_driver, By.name("numPages"));
		String sFoldPattern = CommonFunctions.GetSelectedOption(_driver, By.name("foldPattern"));

		String sContentFile = "";
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobPartContentFile']/tbody/tr")))
		{
			sContentFile = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartContentFile']/tbody/tr[1]/td[3]/div/a"));
		}

		String sNumUps = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[2]/div"));
		String sNumAlong = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[3]/div"));
		String sNumAcross = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[4]/div"));
		String sPrinter = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[5]/div"));
		String sOrientation = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[6]/div"));
		String sPrintJobMat = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[7]/div"));
		String sPrintJobMatDesc = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[8]/div"));
		String sPageRange = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[9]/div"));
		String sPageCount = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[10]/div"));
		String sS1 = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[11]/div"));
		String sS2 = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartPressFormMixedMedia']/tr[3]/td[12]/div"));

		int iFinishRwCnt1 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr"));
		int iFinishRwCnt2 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr"));
		String sFinishingType = "", sFinishingOperation = "";

		if (iFinishRwCnt1 > 0)
		{
			sFinishingType = "Inline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}
		else if (iFinishRwCnt2 > 0)
		{
			sFinishingType = "Offline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}

		String sJobmaterials = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr")));
		String sJobMat = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[3]/div"));
		String sJobMatDesc = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[5]/div"));
		String sJobMatBS = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[6]/div"));
		String sJobMatRS = "";
		if(CommonFunctions.isElementPresent(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[7]/div/span[3]")))
		{
			sJobMatRS = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[7]/div/span[1]")) + " x " + CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[7]/div/span[3]"));
		}
		String sJobMatPQ = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1018D']/tbody/tr[1]/td[8]/div"));

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='G']"));
		String sItemTem = CommonFunctions.getText(_driver, By.xpath("//label[text()='Item Template']/../../div/div[1]/a"));
		String sJobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct"));

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='D']"));
		String sPromiseDate = CommonFunctions.GetValue(_driver, By.name("promiseDate"));
		String sDueDate = CommonFunctions.GetValue(_driver, By.name("dueDate"));

		String sNotes = "";
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Notes']"));
		if (CommonFunctions.isElementPresent(_driver, By.name("JobNote_N101CE.note")))
		{
			sNotes = CommonFunctions.GetValue(_driver, By.name("JobNote_N101CE.note"));
		}

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
		String sJobPartItems = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartItem_N10327']/tbody/tr")));

		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinalSize", sFinishSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FlatSize", sFlatSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Pages", sPages);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FoldPattern", sFoldPattern);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"ContentFiles", sContentFile);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumUps", sNumUps);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumAlong", sNumAlong);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumAcross", sNumAcross);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Printer", sPrinter);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Orientation", sOrientation);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMaterial", sPrintJobMat);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Description", sPrintJobMatDesc);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PageRange", sPageRange);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PageCount", sPageCount);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"S1", sS1);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"S2", sS2);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingType", sFinishingType);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingOperation", sFinishingOperation);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumMaterials", sJobmaterials);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMat", sJobMat);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatDesc", sJobMatDesc);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatBuySize", sJobMatBS);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatRunSize", sJobMatRS);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatPlanQty", sJobMatPQ);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobProduct", sJobProduct);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PromiseDate", sPromiseDate);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"DueDate", sDueDate);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"ItemTemplate", sItemTem);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Notes", sNotes);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobPartItems", sJobPartItems);

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Part Details']"));
	}

	public void getJobPartDetails1(String Sheet, String Scenario, String Testcase, String JobPart) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='G']"));
		String sItemTem = CommonFunctions.getText(_driver, By.xpath("//label[text()='Item Template']/../../div/div[1]/a"));
		String sJobProduct = CommonFunctions.GetSelectedOption(_driver, By.name("jobProduct"));

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='D']"));
		String sPromiseDate = CommonFunctions.GetValue(_driver, By.name("promiseDate"));
		String sDueDate = CommonFunctions.GetValue(_driver, By.name("dueDate"));

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='o']"));
		String sFinishSize = CommonFunctions.GetValue(_driver, By.name("finalSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("finalSizeH"));
		String sFlatSize = CommonFunctions.GetValue(_driver, By.name("flatSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("flatSizeH"));
		String sPages = CommonFunctions.GetSelectedOption(_driver, By.name("numPages"));
		String sFoldPattern = CommonFunctions.GetSelectedOption(_driver, By.name("foldPattern"));

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='r']"));
		CommonFunctions.SetOriginalWindowHandle(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobPartPressForm_N1018D']/tbody/tr[1]/td[2]//img"));
		Thread.sleep(3000);
		CommonFunctions.SwitchToWindow(_driver, "Job Part Press Form");
		String sNumUps = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.PressForm_Up)));
		String sNumAlong = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.PressForm_NumAlong)));
		String sNumAcross = CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.PressForm_NumAcross)));
		String sPrinter = CommonFunctions.GetSelectedOption(_driver, By.xpath("//label[text()='Printer']/../../div/select"));
		String sOrientation = CommonFunctions.GetSelectedOption(_driver, By.name("orientation"));
		String sPrintJobMat = CommonFunctions.GetSelectedOption(_driver, By.xpath("//label[text()='Job Material']/../../div/select"));
		String sPageRange = CommonFunctions.GetValue(_driver, By.name("pageRange"));
		if(sPageRange == null){sPageRange = "";}
		String sPageCount = CommonFunctions.getText(_driver, By.xpath("//label[text()='Page Count']/../../div/div"));
		String sS1 = CommonFunctions.getText(_driver, By.name(Locators.getProperty(Locators.PressForm_S1)));
		String sS2 = CommonFunctions.getText(_driver, By.name(Locators.getProperty(Locators.PressForm_S2)));
		_driver.close();
		CommonFunctions.ReturnToOriginalWindow(_driver);

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='F']"));
		int iFinishRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartFinishingOp']/tbody/tr"));
		String sFinishingType = "", sFinishingOperation = "";

		if (iFinishRwCnt > 0)
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='JobPartFinishingOp']/tbody/tr[3]/td[4]//img"));
			Thread.sleep(3000);
			CommonFunctions.SwitchToWindow(_driver, "Job Part Finishing Operation");
			boolean sInline = CommonFunctions.VerifyChecked(_driver, By.name("inLineBooleanCheck"));

			if (sInline)
			{
				sFinishingType = "Inline";
			}
			else
			{
				sFinishingType = "Offline";
			}			
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartFinishingOp']/tbody/tr[3]/td[7]/div/div[2]/div/a"));
		}

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='M']"));
		String sJobmaterials = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr")));
		String sJobMat = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[3]/div"));
		String sJobMatDesc = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[5]/div"));
		String sJobMatBS = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[7]/div"));
		String sJobMatRS = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[8]/div/span[1]")) + " x " + CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[8]/div/span[3]"));
		String sJobMatPQ = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobMaterial_N1010A']/tbody/tr[1]/td[11]/div"));

		String sNotes = "";
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Notes']"));
		if (CommonFunctions.isElementPresent(_driver, By.name("JobNote_N101CE.note")))
		{
			sNotes = CommonFunctions.GetValue(_driver, By.name("JobNote_N101CE.note"));
		}

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
		String sJobPartItems = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartItem_N10327']/tbody/tr")));

		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinalSize", sFinishSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FlatSize", sFlatSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Pages", sPages);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FoldPattern", sFoldPattern);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumUps", sNumUps);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumAlong", sNumAlong);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumAcross", sNumAcross);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Printer", sPrinter);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Orientation", sOrientation);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMaterial", sPrintJobMat);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PageRange", sPageRange);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PageCount", sPageCount);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"S1", sS1);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"S2", sS2);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingType", sFinishingType);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingOperation", sFinishingOperation);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"NumMaterials", sJobmaterials);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMat", sJobMat);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatDesc", sJobMatDesc);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatBuySize", sJobMatBS);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatRunSize", sJobMatRS);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobMatPlanQty", sJobMatPQ);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobProduct", sJobProduct);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"PromiseDate", sPromiseDate);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"DueDate", sDueDate);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"ItemTemplate", sItemTem);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"Notes", sNotes);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"JobPartItems", sJobPartItems);
	}

	public void getJobPartDetails_Latest(String Sheet, String JobPart) throws Exception
	{
		//Clicks on job part details tab
		this.NavigateToJobPartDetailsTab();

		//*********Fetch Details Section**********************//
		this.fetchDetailsSectionInfo_JobPart(Sheet, "Details", "JobPart");

		//Fetch Content File Section
		this.FetchContentFile_JobPartPage(Sheet, "Content", "JobPart1ContentFiles");

		//Fetch Printer Forms Section
		//********** Fetching the 1st Printer Form Values**********//
		String tableName ="gridRow_2";
		this.fetchPrinterFormTableValues(Sheet, "PrinterForm1", tableName, 6);

		//********** Fetching the 2nd Printer Form Values**********//
		tableName ="gridRow_4";
		this.fetchPrinterFormTableValues(Sheet, "PrinterForm2", tableName, 4);

		//********** Fetching the 3rd Printer Form Values**********//
		tableName ="gridRow_6";
		this.fetchPrinterFormTableValues(Sheet, "PrinterForm3", tableName, 3);

		//Fetch Finishing Row Details
		this.fetchFinishDetailsFromJobPartPage(Sheet, "Finish", "JobPart", JobPart);

		//Start of Fetch Material Table Values
		tableName="JobMaterial";
		this.fetchJobMaterialTableValues(Sheet, "Material", "JobPart", tableName, 6);

		//Clicks on job part details tab
		this.NavigateToJobPartDetailsTab();
	}

	public void FetchContentFile_JobPartPage(String Sheet, String Scanario, String column)
	{
		String totalNumber_Of_ContentFile="//table[@id='JobPartContentFile']/tbody/tr";


		int totalContenetFullCount=_driver.findElements(By.xpath(totalNumber_Of_ContentFile)).size();
		String sContentFile = "";
		for(int c=1; c<=totalContenetFullCount;c++)
		{
			sContentFile = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartContentFile']/tbody/tr["+c+"]/td[3]/div/a"));
			String Sno=Integer.toString(c);
			System.out.println("Sno is :"+ Sno);
			//Writing the content file name in to excel
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scanario, Sno, column, sContentFile);
			c=Integer.parseInt(Sno);
		}

	}

	public boolean validateContentFile_JobPartPage(String Sheet,String Scanario,String column)
	{
		String totalNumber_Of_ContentFile="//table[@id='JobPartContentFile']/tbody/tr";

		boolean sflag=false;
		int totalContenetFullCount=_driver.findElements(By.xpath(totalNumber_Of_ContentFile)).size();
		String sContentFile = "";
		for(int c=1; c<=totalContenetFullCount;c++)
		{
			sContentFile = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartContentFile']/tbody/tr["+c+"]/td[3]/div/a"));
			String Sno=Integer.toString(c);
			System.out.println("Sno is :"+ Sno);
			//Writing the content file name in to excel
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scanario, Sno, column,sContentFile);

			String contentFile_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scanario, Sno, column);

			if(contentFile_Exp.equals(sContentFile))
			{
				System.out.println("content file "+c+" is matching");
				sflag=true;
			}
			else
			{
				System.err.println("content file "+c+" is matching : Expected "+contentFile_Exp+" and Actual is : "+sContentFile+"");
				sflag=false;
			}

			c=Integer.parseInt(Sno);
		}

		if(sflag == true)
		{
			return true;

		}
		else
		{
			return false;
		}
	}

	public void fetchPrinterFormTableValues(String Sheet, String Scenario, String tableName, int tillLastRow)
	{
		for(int i=3;i<=tillLastRow;i++)//Where 3 is first row
		{
			String sNumUps = CommonFunctions.getText(_driver, By.xpath("//*[ @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[2]/div"));
			String sNumAlong = CommonFunctions.getText(_driver, By.xpath("//*[@id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[3]/div"));
			String sNumAcross = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[4]/div"));
			String sPrinter = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[5]/div"));
			String sOrientation = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[6]/div"));
			String sPrintJobMat = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[7]/div"));
			String sPrintJobMatDesc = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[8]/div"));
			String sPageRange = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[9]/div"));
			String sPageCount = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[10]/div"));
			String sS1 = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[11]/div"));
			String sS2 = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[12]/div"));

			String Sno=Integer.toString(i);
			System.out.println("Sno is :"+ Sno);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1NumUps",sNumUps);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1NumAlong",sNumAlong);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1NumAcross",sNumAcross);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1Printer",sPrinter);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1Orientation",sOrientation);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMaterial",sPrintJobMat);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1Description",sPrintJobMatDesc);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1PageRange",sPageRange);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1PageCount",sPageCount);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1S1",sS1);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1S2",sS2);

			i=Integer.parseInt(Sno);
		}
	}

	public void fetchJobMaterialTableValues(String Sheet, String Scenario, String Testcase, String tableName, int tillLastRow) throws Exception
	{
		String sJobNumOfmaterials = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath(".//table[contains(@id,'JobMaterial')]/tbody//tr")));

		for(int i=1;i<=tillLastRow;i++)
		{
			String sJobMat = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[3]/div"));
			String sJobMatDesc = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[5]/div"));
			String sJobMatBS = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[6]/div"));
			String sJobMatRS = "";
			if(CommonFunctions.isElementPresent(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[3]")))
			{
				sJobMatRS = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[1]")) + " x " + CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[3]"));
			}

			String sJobMatPQ = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[8]/div"));

			String Sno=Integer.toString(i);
			System.out.println("Sno is :"+ Sno);

			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMat",sJobMat);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMatDesc",sJobMatDesc);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMatBuySize",sJobMatBS);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMatRunSize",sJobMatRS);
			dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "JobPart1JobMatPlanQty",sJobMatPQ);

			i=Integer.parseInt(Sno);

		}

		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1NumMaterials", sJobNumOfmaterials);

		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='G']"));
		String sItemTem = CommonFunctions.getText(_driver, By.xpath("//label[text()='Item Template']/../../div/div[1]/a"));

		String sNotes = "";
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Notes']"));
		if (CommonFunctions.isElementPresent(_driver, By.name("JobNote_N101CE.note")))
		{
			sNotes = CommonFunctions.GetValue(_driver, By.name("JobNote_N101CE.note"));
		}

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
		String sJobPartItems = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartItem_N10327']/tbody/tr")));

		//dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "NumberOFMaterial",sJobmaterials);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1ItemTemplate", sItemTem);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1Notes", sNotes);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1JobPartItems", sJobPartItems);

	}

	public boolean ValidateJobMaterialTableValues(String Sheet, String Scenario,String Testcase,String tableName,int tillLastRow) throws Exception
	{
		String MaterialTableXpath=".//table[contains(@id,'JobMaterial')]/tbody//tr";
		String sJobNumOfmaterials = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath(".//table[contains(@id,'JobMaterial')]/tbody//tr")));

		boolean sflag=false;
		boolean sflag1=false;
		boolean sflag2=false;
		for(int i=1;i<=tillLastRow;i++)
		{


			String sJobMat = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[3]/div"));
			String sJobMatDesc = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[5]/div"));
			String sJobMatBS = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[6]/div"));
			String sJobMatRS = "";
			if(CommonFunctions.isElementPresent(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[3]")))
			{
				sJobMatRS = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[1]")) + " x " + CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[7]/div/span[3]"));
			}

			String sJobMatPQ = CommonFunctions.getText(_driver, By.xpath(".//table[contains(@id,'"+tableName+"')]/tbody//tr["+i+"]/td[8]/div"));

			String Sno=Integer.toString(i);
			System.out.println("Sno is :"+ Sno);

			String sJobMat_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMat");
			String sJobMatDesc_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMatDesc");
			String sJobMatBS_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMatBuySize");
			String sJobMatRS_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMatRunSize");
			String sJobMatPQ_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMatPlanQty");




			if(sJobMat_Exp.equals(sJobMat) && sJobMatDesc_Exp.equals(sJobMatDesc) && sJobMatBS_Exp.equals(sJobMatBS) && sJobMatRS_Exp.equals(sJobMatRS) && sJobMatPQ_Exp.equals(sJobMatPQ))
			{
				System.out.println("Material "+i+" Details are Matching");
				sflag=true;
			}
			else
			{
				System.err.println("Job Material, Expected = "+sJobMat_Exp+", Actual = " + sJobMat +
						"\nJob Material Description, Expected = "+sJobMatDesc_Exp+", Actual = " + sJobMatDesc +
						"\nBuy Size, Expected ="+sJobMatBS_Exp+", Actual = " + sJobMatBS +
						"\nRun Size, Expected = "+sJobMatRS_Exp+", Actual = " + sJobMatRS +
						"\nPlanned Quantity, Expected = "+sJobMatPQ_Exp+", Actual = " + sJobMatPQ );
				sflag=false;
			}



			i=Integer.parseInt(Sno);

		}

		//Reading total number of materials from Excel
		String sJobNumOfmaterials_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1NumMaterials");

		if(sJobNumOfmaterials_Exp.equals(sJobNumOfmaterials))
		{
			System.out.println("Total Number of material is matching");
			sflag1=true;
		}
		else
		{
			System.err.println("Job Materials, Expected = "+sJobNumOfmaterials_Exp+", Actual = " + sJobNumOfmaterials +"\n");
			sflag1=false;
		}


		CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='tabBar']//span[text()='G']"));
		String sItemTem = CommonFunctions.getText(_driver, By.xpath("//label[text()='Item Template']/../../div/div[1]/a"));

		String sNotes = "";
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Notes']"));
		if (CommonFunctions.isElementPresent(_driver, By.name("JobNote_N101CE.note")))
		{
			sNotes = CommonFunctions.GetValue(_driver, By.name("JobNote_N101CE.note"));
		}

		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Job Part Items']"));
		String sJobPartItems = String.valueOf(CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartItem_N10327']/tbody/tr")));

		//dbconnection.UpdateFunction_SerialNumber(Sheet, Scenario, Sno, "NumberOFMaterial",sJobmaterials);

		String sItemTem_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1ItemTemplate");
		String sNotes_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1Notes");
		String sJobPartItems_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1JobPartItems");

		if(sItemTem_Exp.equals(sItemTem) && sNotes_Exp.equals(sNotes) && sJobPartItems_Exp.equals(sJobPartItems))
		{
			System.out.println("Item Template - Notes - PartItems Details Are Matching");
			sflag2=true;

		}
		else
		{
			System.err.println("Item Template, Expected = "+sItemTem_Exp+", Actual = " + sItemTem +
					"\nJob Part Items, Expected = "+sJobPartItems_Exp+", Actual = " + sJobPartItems +
					"\n Notes Expetced ="+ sNotes_Exp+",ACtual = "+sNotes);
			sflag2=false;
		}


		if(sflag==true && sflag1==true &&sflag2 ==true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public void fetchDetailsSectionInfo_JobPart(String Sheet, String Scenario, String Testcase ) throws Exception
	{
		String sFinishSize = CommonFunctions.GetValue(_driver, By.name("finalSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("finalSizeH"));
		String sFlatSize = CommonFunctions.GetValue(_driver, By.name("flatSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("flatSizeH"));
		String sPages = CommonFunctions.GetSelectedOption(_driver, By.name("numPages"));
		String sFoldPattern = CommonFunctions.GetSelectedOption(_driver, By.name("foldPattern"));

		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FinalSize", sFinishSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FlatSize", sFlatSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1Pages", sPages);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FoldPattern", sFoldPattern);
	}

	public Boolean ValidateDetailsSectionInfo_JobPart(String Sheet,String Scenario,String Testcase ) throws Exception
	{

		boolean sflag=false;

		String sFinishSize = CommonFunctions.GetValue(_driver, By.name("finalSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("finalSizeH"));
		String sFlatSize = CommonFunctions.GetValue(_driver, By.name("flatSizeW")) + " x " + CommonFunctions.GetValue(_driver, By.name("flatSizeH"));
		String sPages = CommonFunctions.GetSelectedOption(_driver, By.name("numPages"));
		String sFoldPattern = CommonFunctions.GetSelectedOption(_driver, By.name("foldPattern"));

		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FinalSize", sFinishSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FlatSize", sFlatSize);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1Pages", sPages);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart1FoldPattern", sFoldPattern);

		String sFinishSize_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1FinalSize");
		String sFlatSize_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1FlatSize");
		String sPages_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1Pages");
		String sFoldPattern_Exp=dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart1FoldPattern");

		if(sFinishSize_Exp.equals(sFinishSize) && sFlatSize_Exp.equals(sFlatSize) && sPages_Exp.equals(sPages) && sFoldPattern_Exp.equals(sFoldPattern) )
		{
			System.out.println("Job part Details Values Are Matching");
			sflag=true;
		}
		else
		{
			System.err.println("Finish Size, Expected = "+sFinishSize_Exp+", Actual = " + sFinishSize +
					"\nFlat Size, Expected = "+sFlatSize_Exp+", Actual = " + sFlatSize +
					"\nPages, Expected = "+sPages_Exp+", Actual = " + sPages +
					"\nFold Pattern, Expected = "+sFoldPattern_Exp+", Actual = " + sFoldPattern +"");
			sflag=false;
		}

		if(sflag == true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public void NavigateToJobPartDetailsTab() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Part Details']"));
		CommonFunctions.waitForPageLoaded(_driver);
		System.out.println("Navigated to Job Part Details Page");
	}

	public void fetchFinishDetailsFromJobPartPage(String Sheet, String Scenario, String Testcase, String JobPart)
	{
		//Fetch Finishing Row Details

		int iFinishRwCnt1 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr"));
		int iFinishRwCnt2 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr"));
		String sFinishingType = "", sFinishingOperation = "";

		if (iFinishRwCnt1 > 0)
		{
			sFinishingType = "Inline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}
		else if (iFinishRwCnt2 > 0)
		{
			sFinishingType = "Offline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}


		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingType", sFinishingType);
		dbconnection.UpdateFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingOperation", sFinishingOperation);
	}

	public boolean ValidateFinishDetailsFromJobPartPage(String Sheet,String Scenario,String Testcase,String JobPart)
	{
		//Fetch Finishing Row Details
		boolean sflag=false;

		int iFinishRwCnt1 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr"));
		int iFinishRwCnt2 = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr"));
		String sFinishingType = "", sFinishingOperation = "";

		if (iFinishRwCnt1 > 0)
		{
			sFinishingType = "Inline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='pressEvents']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}
		else if (iFinishRwCnt2 > 0)
		{
			sFinishingType = "Offline";
			sFinishingOperation = CommonFunctions.getText(_driver, By.xpath("//table[@id='JobPartFinishingOp_fieryGrid']/tbody/tr[1]/td[4]/div/div[2]/div/a"));
		}


		String sFinishingType_exp=	dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingType");
		String sFinishingOperation_exp=	dbconnection.ReadFunction(Sheet, Scenario, Testcase, "JobPart"+JobPart+"FinishingOperation");

		if(sFinishingType_exp.equals(sFinishingType) && sFinishingOperation_exp.equals(sFinishingOperation) )
		{
			System.out.println("Finishing Type and Operations Are Matching");
			sflag=true;
			return sflag;
		}
		else
		{
			System.err.println("Finish Type, Expected = "+sFinishingType_exp+", Actual = " + sFinishingType +
					"\nFinishing Operation, Expected = "+sFinishingOperation+", Actual = " + sFinishingOperation);
			sflag=false;
			return sflag;
		}


	}

	public boolean ValidatePrinterFormTableValues(String Sheet,String Scenario,String tableName,int tillLastRow)
	{

		//String tableName ="gridRow_2";
		//int j =3;

		boolean sflag=false;

		for(int i=3;i<=tillLastRow;i++)//Where 3 is first row
		{

			String sNumUps = CommonFunctions.getText(_driver, By.xpath("//*[ @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[2]/div"));
			String sNumAlong = CommonFunctions.getText(_driver, By.xpath("//*[@id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[3]/div"));
			String sNumAcross = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[4]/div"));
			String sPrinter = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[5]/div"));
			String sOrientation = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[6]/div"));
			String sPrintJobMat = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[7]/div"));
			String sPrintJobMatDesc = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[8]/div"));
			String sPageRange = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[9]/div"));
			String sPageCount = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[10]/div"));
			String sS1 = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[11]/div"));
			String sS2 = CommonFunctions.getText(_driver, By.xpath("//*[  @id='"+tableName+"']//*[@id='JobPartPressFormMixedMedia']//tr["+i+"]/td[12]/div"));

			String Sno=Integer.toString(i);
			System.out.println("Sno is :"+ Sno);

			String sNumUps_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1NumUps");
			String sNumAlong_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1NumAlong");
			String sNumAcross_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1NumAcross");
			String sPrinter_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1Printer");
			String sOrientation_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1Orientation");
			String sPrintJobMat_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1JobMaterial");
			String sPrintJobMatDesc_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1Description");
			String sPageRange_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1PageRange");
			String sPageCount_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1PageCount");
			String sS1_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1S1");
			String sS2_Exp = dbconnection.ReadFunction_UsingNumber(Sheet, Scenario, Sno, "JobPart1S2");


			if(sNumUps_Exp.equals(sNumUps) && sNumAlong.equals(sNumAlong_Exp) && sNumAcross.equals(sNumAcross_Exp) && sPrinter.equals(sPrinter_Exp) && sOrientation.equals(sOrientation_Exp)
					&& sPrintJobMat.equals(sPrintJobMat_Exp) && sPrintJobMatDesc.equals(sPrintJobMatDesc_Exp) && sPageRange.equals(sPageRange_Exp) && sPageCount.equals(sPageCount_Exp) 
					&& sS1.equals(sS1_Exp) && sS2.equals(sS2_Exp))
			{
				System.out.println("Printer Form Table Values Are Matching");
				sflag=true;
			}
			else
			{
				System.err.println(" Num Ups, Expected ="+ sNumUps_Exp+ ", Actual = " + sNumUps +
						"\nNum Along, Expected ="+sNumAlong_Exp+", Actual = " + sNumAlong +
						"\nNum Across, Expected = "+sNumAcross_Exp+", Actual = " + sNumAcross +								
						"\nPrint, Expected = "+sPrinter_Exp+", Actual = " + sPrinter +
						"\nOrientation, Expected = "+sOrientation_Exp+", Actual = " + sOrientation +
						"\nJob Material, Expected = "+sPrintJobMat_Exp+", Actual = " + sPrintJobMat +
						"\nDescription, Expected = "+sPrintJobMatDesc_Exp+",+ Actual = " + sPrintJobMatDesc +
						"\nPage range, Expected ="+sPageRange_Exp+", Actual = " + sPageRange +
						"\nPage Count, Expected ="+sPageCount_Exp+", Actual = " + sPageCount +
						"\nS1, Expected = "+sS1_Exp+", Actual = " + sS1 +
						"\nS2, Expected = "+sS2_Exp+", Actual = " + sS2 );
				sflag=false;
			}

			i=Integer.parseInt(Sno);
		}

		if(sflag == true)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	public void NavigateToEstimate(String Server, String Company) throws Exception
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/Estimate/list");	
		CommonFunctions.waitForPageLoaded(_driver);
		//assertEquals("Search for Estimates", _driver.getTitle());
	}

	public void NavigateToEstimateDetail(String EstimateNumber, String Server, String Company) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		NavigateToEstimate(Server, Company);
		DC.SearchValue(EstimateNumber, "estimateNumber");
		CommonFunctions.waitForPageLoaded(_driver);
		if(CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='grid-contents']//table/tbody/tr[1]//img")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='grid-contents']//table/tbody/tr[1]//img"));
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("manufacturingLocation"));
	}

	public void NavigateToGangListGangPart(String Server, String Company) throws Exception
	{
		//http://pacepf-suite50/epace/company:public/object/JobPart/gangingList
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/JobPart/gangingList");	
		CommonFunctions.waitForPageLoaded(_driver);
		//	CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Estimate_Number)));
	}

	public void NavigateToRequestNewEstimate(String Server, String Company) throws Exception
	{
		_driver.get("http://"+Server+"/epace/company:"+Company+"/object/EstimateRequest/add");
		CommonFunctions.waitForPageLoaded(_driver);
		//assertEquals("Adding Estimate", _driver.getTitle());
	}


}