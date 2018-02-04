package com.gui_auto.pages;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

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

public class JobCostingPage implements BaseElement  
{
	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate dbconnection = new ReadAndUpdate();
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	private static  String sSERVER = null;
	private static  String sCOMPANY = null;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;
	protected static GUI_automation_properties _properties = new GUI_automation_properties();
	
	public JobCostingPage(WebDriver driver) throws Exception 
	{
		super();
		this._driver = driver;
	}
	
	@Override
	public boolean onPage() throws NoSuchElementException 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean waitForPage() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public String Date()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        Date date = new Date();
        String suniqueNumber = dateFormat.format(date);        
		return suniqueNumber;
	}
	
	public String UniqueNum()
	{
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}
	
	public String UniqueNum4Digit()
	{
		DateFormat dateFormat = new SimpleDateFormat("mmss");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		return sUniqueNumber;
	}
	
	public void navigateToApproveJobCostsPage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobCost/listApprove");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Approve Job Costs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToApproveJobCostsPage");
		System.out.println("****Approve Job Costs page appears****");
	}
	
	public void navigateToPostJobCostsPage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/process/run?key=JobCost.post");
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PostJobCost_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Posting Job Costs", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToPostJobCostsPage");
		System.out.println("****Post Job Costs page appears****");
	}
	
	public void navigateToEnterJobCostsPage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobCost/addDirect");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		assertEquals("Adding Job Cost", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("naviagteToEnterJobCostsPage");
		System.out.println("****Adding Job Costs page appears****");
	}
	
	public void navigateToNonPlannedReasonMaintenancePage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/NonPlannedReason/list");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Add_New)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Non Planned Reason", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToNonPlannedReasonMaintenancePage");
		System.out.println("****Non Planned Reason list page appears****");
	}
	
	public void navigateToJobCostSettingsPage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobCostSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Job Cost Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToJobCostSettingsPage");
		System.out.println("****Job Cost Setup page appears****");
	}
	
	public void ActvtyCodeTimeChargedDetails (String ChargeBasis, String HrsPerProdUnit) throws Exception
	{
		System.out.println("Entering Time Charged Details for Activity code");
		System.out.println("\nData passed:\nCharge Basis option: "+ChargeBasis+"\nHrs Per Prod Unit : "+HrsPerProdUnit);
		if (!(_driver.findElement(By.name(Locators.getProperty(Locators.AC_TimeCharged_Charge_Basis))).isDisplayed())) 
		{
			_driver.findElement(By.xpath("//legend[text()='"+Locators.getProperty(Locators.AC_TimeChargedLabel)+"']/span")).click();
			Thread.sleep(500);
		}		
		if (!ChargeBasis.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_TimeCharged_Charge_Basis)), ChargeBasis);
		}
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AC_TimeCharged_HrsPerProdUnit)), HrsPerProdUnit);
	}
	
	public void ActvtyCodeProdUnitsChargedDetails (String ProdUnitHr, String AskProdUnit, String ProdUnitDes, String StandardCharge) throws Exception
	{
		System.out.println("Entering Production Units Charged Details for Activity code");
		System.out.println("Data passed:\nStand Prod Units Per Hr: "+ProdUnitHr+"\nAsk Prod Unit : "+AskProdUnit+"\nProduction Units Des : "+ProdUnitDes+"\nStandard Charge : "+StandardCharge);
		if (!(_driver.findElement(By.name(Locators.getProperty(Locators.AC_Production_Units_Charged_StandProdUnits))).isDisplayed()))
		{
			_driver.findElement(By.xpath("//legend[text()='"+Locators.getProperty(Locators.AC_Production_Units_Charged)+"']/span")).click();
			Thread.sleep(500);
		}
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_Charged_StandProdUnits)), ProdUnitHr);
		if (!AskProdUnit.equals("") && CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)))))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_ChargedAskProdUnit)), AskProdUnit);
			Thread.sleep(1000);
		}
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_Charged_ProductionUnitsDes)), ProdUnitDes);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AC_Production_Units_Charged_StandardCharge)), StandardCharge);
	}
	
	public void ActvtyCodeUpdateJobJacketDetails (String ChangeJobStatus, boolean AddJobTracking, boolean UpdateLastAction) throws Exception
	{
		System.out.println("Entering Update Job Jacket Details for Activity code");
		System.out.println("Data Passed:\nChangeJobStatus = "+ChangeJobStatus+"\nAddJobTracking = "+AddJobTracking+"\nUpdateLastAction = "+UpdateLastAction);
		if (!(_driver.findElement(By.name(Locators.getProperty(Locators.AC_UpdateJobJacket_ChanegJobStatus))).isDisplayed())) 
		{
			_driver.findElement(By.xpath("//legend[text()='"+Locators.getProperty(Locators.AC_UpdateJobJacket)+"']/span")).click();
			Thread.sleep(500);
		}
		if (!ChangeJobStatus.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_UpdateJobJacket_ChanegJobStatus)), ChangeJobStatus);
			Thread.sleep(1000);
		}
		CommonFunctions.sSelectCheckBox(_driver, AddJobTracking, By.name(Locators.getProperty(Locators.AC_UpdateJobJacket_AddJobTracking)));
		CommonFunctions.sSelectCheckBox(_driver, UpdateLastAction, By.name(Locators.getProperty(Locators.AC_UpdateJobJacket_UpdateLastAction)));
	}
	
	public void ActvtyCodeOtherSettingsDetails (String AskCounts, String AskNotes, String AskPostage, String AskNonPlannedReason, String PostageActivity, String EstimateResultType, boolean OutsiderPurchase, boolean Paper) throws Exception
	{
		System.out.println("Entering Other Setting Details for Activity code");
		System.out.println("Data Passed:\nAskCounts = "+AskCounts+"\nAskNotes = "+AskNotes+"\nAskPostage = "+AskPostage+"\nAskNonPlannedReason = "+AskNonPlannedReason+"\nPostageActivity = "+PostageActivity+"\nEstimateResultType = "+EstimateResultType+"\nPutsidePurchase = "+OutsiderPurchase+"\nPpaer = "+Paper);
		if (!(_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts))).isDisplayed())) 
		{
			_driver.findElement(By.xpath("//legend[text()='"+Locators.getProperty(Locators.AC_OtherSettingsLabel)+"']/span")).click();
			Thread.sleep(500);
		}
		
		if (!AskCounts.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskCounts)), AskCounts);
			Thread.sleep(1000);
		}
		if (!AskNotes.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Notes)), AskNotes);
			Thread.sleep(1000);
		}
		if (!AskPostage.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_AskPostage)), AskPostage);
			Thread.sleep(1000);
		}
		if (!AskNonPlannedReason.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Ask_Non_Planned_Reason)), AskNonPlannedReason);
			Thread.sleep(1000);
		}
		if (!EstimateResultType.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_EstimateResultType)), EstimateResultType);
			Thread.sleep(1000);
		}
		CommonFunctions.sSelectCheckBox(_driver, OutsiderPurchase, By.name(Locators.getProperty(Locators.AC_OtherSettings_OutsidePurchase)));
		CommonFunctions.sSelectCheckBox(_driver, Paper, By.name(Locators.getProperty(Locators.AC_OtherSettings_Paper)));
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity)), PostageActivity);
		_driver.findElement(By.name(Locators.getProperty(Locators.AC_OtherSettings_Postage_Activity))).sendKeys(Keys.TAB);
	}
	
	public String getActualHoursSpentOnLastActivity() throws Exception
	{
		JobPlanningPage JP = new JobPlanningPage(_driver);
		String sActualHoursSpent = "";
		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.DCL_Completed_Job_Activity))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Completed_Job_Activity)));
			CommonFunctions.waitForPageLoaded(_driver);
			int iRwnCnt = _driver.findElements(By.xpath("//table[@id='JobTransaction_SignedOut']/tbody/tr")).size();
			if (iRwnCnt > 0)
			{
				sActualHoursSpent = JP.GetCellData(1, 12, "//table[@id='JobTransaction_SignedOut']");
			}
			else
			{
				System.err.println("There were no completed Jobs Actviities by this employee. please check");
			}
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.DCL_Return_To_Action_Screen)));
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.err.println("'Completed Job Activities' button was not present. Unable to get the actual hours spent on last Job Activity");
		}
		return sActualHoursSpent;
	}
	
	public boolean JobPartJobCostPage(String sJob,String sPart) throws Exception
	{
		String sJobCostPage = "Job Costs for Job "+sJob+" Part "+sPart;
		sJob = sJob.replace(" ", "%20");
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPart/jobCost/"+sJob+"%3A"+sPart);
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

	public boolean AddJobCost(String JobPlan, String Job, String JobPart, String ActivityCode) throws Exception
	{		
		if (!"Adding Job Cost".equals(_driver.getTitle()))
		{
			navigateToEnterJobCostsPage();
		}
		
		if (!JobPlan.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.DCL_Planned_Activity_ID)), JobPlan);
		}
		else
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), Job);
			_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
			Thread.sleep(1000);
//			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Job_Part)), Job+":"+JobPart);
			Thread.sleep(1000);
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), ActivityCode);
			Thread.sleep(1000);
		}
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}
	
	public boolean AddJobCostByIndex(String JobPlan, String Job, String JobPart,int ActivityIndex) throws Exception
	{		
		if (!"Adding Job Cost".equals(_driver.getTitle()))
		{
			navigateToEnterJobCostsPage();
		}
		
		if (!JobPlan.equals(""))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.DCL_Planned_Activity_ID)), JobPlan);
		}
		else
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), Job);
			_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
			Thread.sleep(4000);
			CommonFunctions.selectDropdown(_driver, By.name("jobPartKey"), Job+":"+JobPart);
			Thread.sleep(1000);
			CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.DCL_Activity_Code)), ActivityIndex);
			
		}
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		Thread.sleep(3000);
		CommonFunctions.waitForPageLoaded(_driver);
		return CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Object_added_text)));
	}
	
	public boolean JobPartTrackingPage(String sJob,String sPart) throws Exception
	{
		String sJobPartTrackingPage = "Tracking Information for Job "+sJob+" Part "+sPart;
		sJob = sJob.replace(" ", "%20");
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPart/jobTracking/"+sJob+"%3A"+sPart);
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		String sTitle = _driver.getTitle();
		if(sJobPartTrackingPage.equals(sTitle))
		{
			System.out.println(Date()+": Navigated to Job Tracking page of Job : "+sJob+" and Job Part : "+sPart);			
			return true;
		}
		else
		{
			System.err.println(Date()+": Unable to navigate to Job Tracking page of Job : "+sJob+" and Job Part : "+sPart);
			return false;
		}	 
	}
	
	public boolean JobPartDetailPage(String sJob,String sPart) throws Exception
	{
		String sJobPartDetailPage = "Job "+sJob+" part "+sPart;
		sJob = sJob.replace(" ", "%20");
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/JobPart/detail/"+sJob+"%3A"+sPart);		
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("childObjects"));
		String sTitle = _driver.getTitle();
		if(sJobPartDetailPage.equals(sTitle))
		{
			System.out.println(Date()+": Navigated to Job part Deatil page of Job : "+sJob+" and Job Part : "+sPart);			
			return true;
		}
		else
		{
			System.err.println(Date()+": Unable to navigate to Job part Deatil page of Job : "+sJob+" and Job Part : "+sPart);
			return false;
		}	 
	}
	
	public void ForceSignOutFromDCL (String EmpCode) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Employee/dcActions/"+EmpCode);
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.waitForPageLoaded(_driver);
		DC.SignoutFromDCL();
	}

	public boolean SearchNonPlannedbyCriteria(String SearchCriteria, String SearchValue)
	{		
		System.out.println(Date()+": Searching Non Planned Reason by '"+SearchCriteria+"'");
		try
		{
			JobPlanningPage JP = new JobPlanningPage(_driver);
			if (!CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)))) {
				navigateToNonPlannedReasonMaintenancePage();
			}
			
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), SearchCriteria);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(SearchValue);
			_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
			Thread.sleep(2000);
			CommonFunctions.waitForPageLoaded(_driver);
			if ("Non Planned Reason".equals(_driver.getTitle())) {
				if (JP.launchTableElement(SearchValue, SearchCriteria, "")) {
					return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Schedule_Status)));
				}
				else {
					return false;
				}					
			}
			else {
				return CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.Schedule_Status)));
			}							
		}
		catch (Exception e) {
			e.printStackTrace();  		
			return false;
  		}
	}

	public boolean JobCostAskSettings (boolean askJobPart, boolean allowProcessAll, String ValidateMaterial, String ValidatePlannedQty) throws Exception
	{
		if (!("Job Cost Setup".equals(_driver.getTitle())))
		{
			navigateToJobCostSettingsPage();
		}
		
		CommonFunctions.sSelectCheckBox(_driver, askJobPart, By.name(Locators.getProperty(Locators.JobCostSetup_AskJobPartNumber)));
		CommonFunctions.sSelectCheckBox(_driver, allowProcessAll, By.name(Locators.getProperty(Locators.JobCostSetup_AllowProcessAll)));
		
		if (!ValidateMaterial.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobCostSetup_ValidateMaterial)), ValidateMaterial);
		}
		
		if (!ValidatePlannedQty.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobCostSetup_ValidatePlannedQuantity)), ValidatePlannedQty);
		}
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		return CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}
	
	public boolean JobCostOtherSettings (String StockPullActivity, String OffsetActivity, boolean lastActionFromJobCost, boolean useDirectLabor, String WIPEntryFormat, boolean CheckPlannedWork) throws Exception
	{
		if (!("Job Cost Setup".equals(_driver.getTitle())))
		{
			navigateToJobCostSettingsPage();			
		}
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='"+Locators.getProperty(Locators.Other_Settings)+"']"));
		Thread.sleep(1000);
		
		if (!StockPullActivity.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobCostSetup_StockPullActivityCode)), StockPullActivity);
		}
		
		if (!OffsetActivity.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobCostSetup_OffsetActivityCode)), OffsetActivity);
		}		
		
		if (!WIPEntryFormat.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobCostSetup_WIPEntryFormat)), WIPEntryFormat);
		}

		CommonFunctions.sSelectCheckBox(_driver, lastActionFromJobCost, By.name(Locators.getProperty(Locators.JobCostSetup_LastActionFromJobCost)));
		CommonFunctions.sSelectCheckBox(_driver, useDirectLabor, By.name(Locators.getProperty(Locators.JobCostSetup_UseDirectLabor)));
		CommonFunctions.sSelectCheckBox(_driver, CheckPlannedWork, By.name(Locators.getProperty(Locators.JobCostSetup_CheckPlannedWork)));
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		return CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}

	public void ChangeJobStatus (String JobID, String Status) throws Exception
	{
		sSERVER =_properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+sSERVER+"/epace/company:"+sCOMPANY+"/object/Job/detail/"+JobID);
		Thread.sleep(6000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Status)), Status);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(6000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		CommonFunctions.getPopupMessage(_driver);
		CommonFunctions.waitForPageLoaded(_driver);	
	}

	public boolean JobStatusCodeSettings (boolean AdmintoProd, boolean Admin, boolean Produciton, boolean OpenJob, boolean SchedOK, boolean ReadyForDevice, boolean Editable, boolean BillingOK, boolean JobChargesOK, boolean AutoChangeable, String AskComments, String AskReason) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Settings']"));
		Thread.sleep(1000);
		
		CommonFunctions.sSelectCheckBox(_driver, AdmintoProd, By.name(Locators.getProperty(Locators.JobStatus_AdminToProduction)));
		CommonFunctions.sSelectCheckBox(_driver, Admin, By.name(Locators.getProperty(Locators.JobStatus_Admin)));
		CommonFunctions.sSelectCheckBox(_driver, Produciton, By.name(Locators.getProperty(Locators.JobStatus_Production)));
		CommonFunctions.sSelectCheckBox(_driver, OpenJob, By.name(Locators.getProperty(Locators.JobStatus_OpenJob)));
		CommonFunctions.sSelectCheckBox(_driver, SchedOK, By.name(Locators.getProperty(Locators.JobStatus_SchedOK)));
		CommonFunctions.sSelectCheckBox(_driver, ReadyForDevice, By.name(Locators.getProperty(Locators.JobStatus_ReadyForDeviceSubmission)));
		CommonFunctions.sSelectCheckBox(_driver, Editable, By.name(Locators.getProperty(Locators.JobStatus_Editable)));
		CommonFunctions.sSelectCheckBox(_driver, BillingOK, By.name(Locators.getProperty(Locators.JobStatus_BillingOK)));
		CommonFunctions.sSelectCheckBox(_driver, JobChargesOK, By.name(Locators.getProperty(Locators.JobStatus_JobChargesOK)));
		CommonFunctions.sSelectCheckBox(_driver, AutoChangeable, By.name(Locators.getProperty(Locators.JobStatus_AutoChangeable)));
		
		if (!AskReason.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobStatus_AskReason)), AskReason);
		}
		
		if (!AskComments.equals(""))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.JobStatus_AskCommnets)), AskComments);
		}
				
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
		Thread.sleep(2000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		return CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Updated_text)));
	}

	public boolean ApproveJobCostTransaction(String JobID) throws Exception
	{
		boolean bApproved = false;
		
		System.out.println("Searching for Job "+JobID);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)), "Job");
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Search_TextField))).sendKeys(JobID);
		_driver.findElement(By.name(Locators.getProperty(Locators.Employee_Find))).click();
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		
		int iNoOfTransac = _driver.findElements(By.xpath("//table[@id='All']/tbody/tr")).size();
		
		if (iNoOfTransac > 0)
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='(all)']"));
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
			CommonFunctions.waitForPageLoaded(_driver);
			
			if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Updated_text))))
			{
				System.out.println("The Page refreshed and Message 'Updated' was displayed, & the transactions were approved");
				bApproved = true;
			}
			else
			{
				System.err.println("The transactions were NOT approved");
			}			
		}	
		else
		{
			System.err.println("No Job Cost transactions were found for Job "+JobID);
		}
		return bApproved;
	}

	public void PostJobCostTransaction (String TransactionType, String CutoffDate, String SingleJobCost, String WIPAccPeriod, boolean PostUnapproved, String Department, String CostCenter, String JobType) throws Exception
	{
		if (!("Posting Job Costs".equals(_driver.getTitle())))
		{
			navigateToPostJobCostsPage();
		}
		
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PostJobCost_TransactionType)), TransactionType);
		
		if (CommonFunctions.isElementPresent(_driver, By.name(Locators.getProperty(Locators.PostJobCost_CutoffDate))))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.PostJobCost_CutoffDate)), CutoffDate);
		}		
		
		if (SingleJobCost != "" && TransactionType.equals("Single Type"))
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PostJobCost_SingleJobCostTypetoPost)), SingleJobCost);
		}
		
		if (WIPAccPeriod != "")
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.PostJobCost_WIPAccountingPeriod)), WIPAccPeriod);
		}
		
		if (Department != "")
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.Emp_Department)), Department);
		}
		
		if (CostCenter != "")
		{
			CommonFunctions.selectDropdown(_driver, By.name(Locators.getProperty(Locators.NonChargeableType_CostCenter)), CostCenter);
		}
		
		if (JobType != "")
		{
			CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Job_Type)), JobType);
		}
		
		CommonFunctions.sSelectCheckBox(_driver, PostUnapproved, By.name(Locators.getProperty(Locators.PostJobCost_PostunapprovedJobCosts)));
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.PostJobCost_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(13000);
	}
	
	public void ViewPostedInvoice() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/Invoice/listPosted");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Employee_Search_Dropdown)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Invoice", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("ViewPostedInvoice");
		System.out.println("****Invoices page appears****");
	}
	
	public void ReverseInvoice(String InvNumber) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		ViewPostedInvoice();
		DC.SearchValue(InvNumber, "Invoice Num");
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Reverse']"));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickElement(_driver, By.xpath("//a[@value='Reverse Invoice' and @name='updateForm']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(5000);
	}
	
	public void AddCostingRatestoActivityCode (String Rate, String MarkupList, String LaborCost, String LaborOverhead, String MachineCost, String GeneralOA, String CostMarkup) throws Exception
	{		
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.AC_CostingRates))))
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.AC_CostingRates)));
			CommonFunctions.ClickElement(_driver, By.xpath("//input[@name='addGridRow_ActivityRate']"));
			
			CommonFunctions.selectDropdown(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_Rate)), Rate);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_MarkupList)), MarkupList);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_LaborCost)), LaborCost);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_LaborOverhead)), LaborOverhead);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_MachineCost)), MachineCost);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_GeneralOA)), GeneralOA);
			CommonFunctions.SendValue(_driver, By.xpath("//table[@id='ActivityRate']/tbody/tr[1]"+Locators.getProperty(Locators.ACCostingRates_CostMarkup)), CostMarkup);
		}
		else
		{
			System.err.println("'Costing Rates' tab was not found in the page. Please ensure activity deatil page is displayed");
			Assert.fail("'Costing Rates' tab was not found in the page. Please ensure activity deatil page is displayed");
		}
	}
	
	public boolean JobPartPurchaseOrders (String JobID, String JobPart) throws Exception
	{
		JobID = JobID.replace(" ", "%20");
		
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/PurchaseOrderLine/listJobPart/"+JobID+"%3A"+JobPart);
		Thread.sleep(5000);
		CommonFunctions.waitForPageLoaded(_driver);
		String sTitle = _driver.getTitle();
		if(sTitle.equals("Purchase Orders"))
		{
			System.out.println(Date()+": Navigated to Purchase Order List page of Job : "+JobID+" and Job Part : "+JobPart);
			return true;
		}
		else
		{
			System.err.println(Date()+": Unable to navigate to Purchase Order List page of Job : "+JobID+" and Job Part : "+JobPart);
			return false;
		}	 
	}

	public void AssignJobToPOLineItem(String sJob, String InvItem) throws Exception 
	{		
		JobPlanningPage JP = new JobPlanningPage(_driver);
		String  originalHandle = _driver.getWindowHandle();
		
		if(_driver.findElements(By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr")).size()>0)
		{
			int iInvRw = JP.SearchTable4Value("Inventory Item", "//table[@id='PurchaseOrderLinePaper']", InvItem);			
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='PurchaseOrderLinePaper']/tbody/tr["+iInvRw+"]/td[2]/div/a/img"));
		}
		else
		{
			int iInvRw = JP.SearchTable4Value("Inventory Item", "//table[@id='PurchaseOrderLine']", InvItem);			
			CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='PurchaseOrderLine']/tbody/tr["+iInvRw+"]/td[2]/div/a/img"));
		}		
		
		Thread.sleep(5000);
		Set<String> availableWindows = _driver.getWindowHandles();
		if (!availableWindows.isEmpty())
		{
			for (String windowId : availableWindows) 
			{
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().contains("Purchase order line"))
				{
					CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Job)), sJob);
					_driver.findElement(By.name(Locators.getProperty(Locators.Job))).sendKeys(Keys.TAB);
					Thread.sleep(3000);
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Update)));
					Thread.sleep(2000);
					_driver.switchTo().window(originalHandle);
					CommonFunctions.waitForPageLoaded(_driver);
					break;
				} 
				else 
				{
					_driver.switchTo().window(originalHandle);
		
				}		
			}
		}		
	}

	public void AddNewBillBatch (String Description) throws Exception
	{
		APPage AP = new APPage(_driver);		
		AP.NavigateToAddNewBillBatch();
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Bill_Batch_Description)), Description);
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.GL_AccountingPeriod)), "2014-06");
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.MIS_Invoice_Date)), "06/30/2014");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("Bill Batch", _driver.getTitle());
		System.out.println("Added a New Bill Batch");
	}
	
	public String AddNewBill (String PONumber) throws Exception
	{
		String BillNumber = "QAPOBill"+UniqueNum();
		
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		CommonFunctions.waitForPageLoaded(_driver);		
		assertEquals("Adding Bill", _driver.getTitle());
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AddBill_PONumber)), PONumber);
		_driver.findElement(By.name(Locators.getProperty(Locators.AddBill_PONumber))).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.AddBill_BillNumber)), BillNumber);
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
		CommonFunctions.waitForPageLoaded(_driver);
		
		System.out.println("Created a New Bill : "+BillNumber);
		return CommonFunctions.GetValue(_driver, By.name(Locators.getProperty(Locators.AddBill_PONumber)));		
	}
	
	public void PickPOReceipts() throws Exception
	{
		if (CommonFunctions.isElementPresent(_driver, By.xpath(Locators.getProperty(Locators.Bill_PickPOReciepts))))
		{
			String  originalHandle = _driver.getWindowHandle();
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Bill_PickPOReciepts)));
			Thread.sleep(5000);
			Set<String> availableWindows = _driver.getWindowHandles();
			if (!availableWindows.isEmpty())
			{
				for (String windowId : availableWindows) 
				{
					_driver.switchTo().window(windowId);
					CommonFunctions.waitForPageLoaded(_driver);
					if(_driver.getTitle().contains("Adding Purchase Order Receipts"))
					{
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='attributes']/div/ul/li[1]"));
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='attributes']/div/ul/li[1]/ul[1]/li[1]"));
						CommonFunctions.ClickElement(_driver, By.xpath("//div[@id='attributes']/div/ul/li[1]/ul[1]/li[1]/ul[1]/li[1]/a"));
						CommonFunctions.ClickElement(_driver, By.name(Locators.getProperty(Locators.Bill_AddPurchaseOrderReceipts)));
						Thread.sleep(3000);
						_driver.switchTo().window(originalHandle);
						CommonFunctions.waitForPageLoaded(_driver);
						break;
					} 
					else 
					{
						_driver.switchTo().window(originalHandle);			
					}		
				}
			}
		}
		else
		{
			System.err.println("Pick PO Receipts button was not found");
			Assert.fail("Pick PO Receipts button was not found");
		}
	}	
	
	public void ApproveBillBatch (String BillDescription) throws Exception
	{
		APPage AP = new APPage(_driver);
		JobPlanningPage JP = new JobPlanningPage(_driver);
		DCPage DC = new DCPage(_driver);
		
		AP.NavigateToBillBatchList();
		CommonFunctions.waitForPageLoaded(_driver);
		
		//uncheck all the approve checkboxes
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/thead/tr/th[3]/span/a"));
		CommonFunctions.ClickElement(_driver, By.xpath("//table[@id='appbox_implicit']/thead/tr/th[3]/span/a"));
		
		int BillBatchRw = JP.SearchTable4Value("Description", "", BillDescription);
		
		if (BillBatchRw > 0)
		{
			CommonFunctions.sSelectCheckBox(_driver, true, By.xpath("//table[@id='appbox_implicit']/tbody/tr["+BillBatchRw+"]/td[3]/input[@name='appbox_implicit.approvedBooleanCheck']"));
			DC.Update();
			CommonFunctions.waitForPageLoaded(_driver);
		}
		else
		{
			System.err.println("Bill Batch with description '"+BillDescription+"' was not found");
			Assert.fail("Bill Batch with description '"+BillDescription+"' was not found");
		}	
	}
	
	public void PostApprovedBillBatches () throws Exception
	{
		APPage AP = new APPage(_driver);
		
		AP.NavigateToPostBillBatch();
		CommonFunctions.waitForPageLoaded(_driver);
				
		CommonFunctions.ClickElement(_driver, By.name(Locators.getProperty(Locators.ApprovedOnly)));
		System.out.println("'Approved Only Batches' checkbox is set to true");
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Post_Bill_Batches)));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(12000);
		
		assertEquals("Bill Post Successful!", _driver.getTitle());
		System.out.println(_driver.findElement(By.xpath("//[@id='fmessage']/ul")).getAttribute("text"));			
	}
		
	public void enterJobPODetails (String POType, String PONumber, String Vendor, String LineType, String Inventory, String Description, String UnitPrice, String Quantity) throws Exception
	{
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Type)), POType);
		Thread.sleep(1000);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_PO_Num)), PONumber);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Enter_PO_Num_Vendor)), Vendor);	
		CommonFunctions.selectDropdownByText(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_LineType)), LineType);
		Thread.sleep(1000);
		
		if (LineType.equals("Inventory"))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Inventory_Item)), Inventory);
			_driver.findElement(By.name(Locators.getProperty(Locators.Inventory_Item))).sendKeys(Keys.TAB);
			Thread.sleep(3000);
		}					
		else if (LineType.equals("Manual"))
		{
			CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Description)), Description);
		}
		
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Qty_Ordered)), Quantity);
		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.CONTROL + "a");				
		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(Keys.DELETE);
		_driver.findElement(By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Unit_Price))).sendKeys(UnitPrice);
		CommonFunctions.SendValue(_driver, By.name(Locators.getProperty(Locators.Adding_PO_Line_Item_Date_Required)), "t");
		CommonFunctions.selectDropdownByIndex(_driver, By.name(Locators.getProperty(Locators.PO_Automatic_PO_Creation_Default_GL_Account)), 1);
	}
	
	public String AddPOToJob (String JobID, String JobPart, String POType, String Vendor, String LineType, String Inventory, String Description, String UnitPrice, String Quantity) throws Exception
	{
		String sPON= "QAPO"+UniqueNum4Digit();
		boolean AddPO = false;
		
		System.out.println("Navigate to Job Part Purchase Orders detail page");
		JobPartPurchaseOrders(JobID, JobPart);
		
		System.out.println("Click Add new button and enter the details and add a PO.");	
		String sOriginalHandle = _driver.getWindowHandle();
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_New_Record)));
		Thread.sleep(3000);
		Set<String> availableWindows = _driver.getWindowHandles();
		 
		if (!availableWindows.isEmpty()) 
		{	
			for (String windowId : availableWindows) 
			{ 
				_driver.switchTo().window(windowId);
				CommonFunctions.waitForPageLoaded(_driver);
				if(_driver.getTitle().equals("Adding Purchase Order"))
				{
					enterJobPODetails(POType, sPON, Vendor, LineType, Inventory, Description, UnitPrice, Quantity);					
					CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Add_button)));
					Thread.sleep(3000);
					_driver.switchTo().window(sOriginalHandle);
					CommonFunctions.waitForPageLoaded(_driver);					
					AddPO = CommonFunctions.isElementPresent(_driver, By.xpath("//div[@id='fmessage']/ul/li[text()='PurchaseOrder ("+sPON+")']"));
					break;
				} 
				else 
				{
					_driver.switchTo().window(sOriginalHandle);										
				}
			}
		}
		else
		{
			System.err.println("Add PO window was not displayed. Unable to add PO to Job "+JobID);
			Assert.fail("Unable to Add PO");
		}
		
		System.out.println("Add PO = "+AddPO);		
		
		if (AddPO)		{
			return sPON;		}
		else		{
			return "";		}
	}
	
	public void ReceivePO (String PONumber, String Quanity, String ReceiveNote) throws Exception
	{
		PurchasePage PO = new PurchasePage(_driver);
		DCPage DC = new DCPage(_driver);
		
		System.out.println("Navigate to Receive PO Line Item Type");
		PO.NavigateToReceivePO();
		DC.SearchValue(PONumber, "PO Number");
		CommonFunctions.waitForPageLoaded(_driver);
		
		CommonFunctions.SendValue(_driver, By.xpath("//table[contains(@id, 'PurchaseOrderLine')]/tbody/tr[1]//input[contains(@name, 'quantityToReceive')]"), Quanity);
		CommonFunctions.SendValue(_driver, By.xpath("//table[contains(@id, 'PurchaseOrderLine')]/tbody/tr[1]//input[contains(@name, 'receivingNote')]"), ReceiveNote);
				
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.Receive_PO_Update_And_Receive_Button)));
		CommonFunctions.waitForPageLoaded(_driver);
	}	
	
	public String verifyJobpartJobCostTrasaction (String JobID, String JobPart, boolean JobCostPosted, String ColumnName, String Value) throws Exception
	{
		JobPlanningPage JP = new JobPlanningPage(_driver);
		String TransactionType = "", TablePath = "";
		int RwCnt = 0;
		System.out.println("Navigate to the Job cost page for the Job Part.");
		JobPartJobCostPage(JobID, JobPart);
		
		if (!JobCostPosted)
		{
			CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.JobCost_UnpostedJobCosts)));
			TablePath = "//table[@id='JobCost_N10022']";
		}
		else
		{
			TablePath = "//table[@id='JobCost_N10051']";
		}
		
		RwCnt = _driver.findElements(By.xpath(TablePath+"/tbody/tr")).size();
		if (RwCnt > 0)
		{
			String sOriginalHandle = _driver.getWindowHandle();
			
			if (ColumnName != "" && Value != "")
			{	RwCnt = JP.SearchTable4Value(ColumnName, TablePath, Value);	}
			else
			{	RwCnt = 1;	}
			
			CommonFunctions.ClickElement(_driver, By.xpath(TablePath+"/tbody/tr["+RwCnt+"]/td[2]/div/a/img"));
			Thread.sleep(2000);				
			Set<String> availableWindows = _driver.getWindowHandles();				 
			if (!availableWindows.isEmpty()) 
			{	
				for (String windowId : availableWindows) 
				{ 
					_driver.switchTo().window(windowId);
					CommonFunctions.waitForPageLoaded(_driver);
					if(_driver.getTitle().equals("Job Cost")) 
					{ 
						TransactionType = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Job_Cost_TransactionType)));												
						_driver.close();	
						Thread.sleep(2000);
						_driver.switchTo().window(sOriginalHandle);
						break;
					}
				}
			}
			else
			{
				sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
				String sGetURL = "http://"+sSERVER+_driver.findElement(By.xpath(TablePath+"/tbody/tr[1]/td[2]/div/a")).getAttribute("href");
				_driver.get(sGetURL);
				CommonFunctions.waitForPageLoaded(_driver);
				Thread.sleep(2000);
				TransactionType = CommonFunctions.GetSelectedOption(_driver, By.name(Locators.getProperty(Locators.Job_Cost_TransactionType)));
			}
		}
		else
		{
			System.err.println("Job has Zero Job Costs");
		}			
		return TransactionType;
	}
	
	public void DCPullMaterials(String sJob, String Quantity) throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Pull Materials']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(1000);
		_driver.findElement(By.name(Locators.getProperty(Locators.DCL_Job_Pick_Name))).sendKeys(sJob);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		System.out.println(Date()+" Job selected");
		CommonFunctions.selectDropdownByIndex(_driver, By.name("sourceMaterialSelect"), 2);
		System.out.println(Date()+" Inventory Item "+CommonFunctions.GetSelectedOption(_driver, By.name("sourceMaterialSelect"))+" selected");
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Go_To_Action_Screen))).sendKeys(Keys.TAB);
		CommonFunctions.SendValue(_driver, By.name("quantity"), Quantity);
		CommonFunctions.SendValue(_driver, By.name("notes"), "Pulled materials (quantity = "+Quantity+") for job "+sJob);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.DCL_Add_Transaction))).click();
		Thread.sleep(2000);
	}

	public void changePostingMethod(String ModuleName, String PostingMethod) throws Exception
	{
		DCPage DC = new DCPage(_driver);
		
		navigateToJobCostSettingsPage();
		CommonFunctions.ClickElement(_driver, By.xpath("//a[text()='Posting Method']"));
		
		int iRwCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[@id='JobCostType']/tbody/tr"));
		for (int i=1; i<iRwCnt; i++)
		{
			String sDescritpion = CommonFunctions.GetText(_driver, By.xpath("//table[@id='JobCostType']/tbody/tr["+i+"]/td[3]/div"));
			
			if (sDescritpion.equals(ModuleName))
			{
				CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[@id='JobCostType']/tbody/tr["+i+"]//select[@name='JobCostType.postingMethod']"), PostingMethod);
				DC.Update();
				break;
			}
		}
	}
	
	public void navigateToGeneralLedgerSettingsPage() throws Exception
	{
		sSERVER = _properties.getProperty(GUI_automation_properties.SERVER);
		sCOMPANY = _properties.getProperty(GUI_automation_properties.COMPANY);
		_driver.get("http://"+ sSERVER +"/epace/company:"+ sCOMPANY +"/object/GLSetup/detail/1");
		CommonFunctions.Wait(_driver, By.name(Locators.getProperty(Locators.Update)));
		CommonFunctions.waitForPageLoaded(_driver);
		assertEquals("General Ledger Setup", _driver.getTitle());
		NewFileNamePath = TakeScreenShot.getDestinationFile("navigateToGeneralLedgerSettingsPage");
		System.out.println("****General Ledger Setup page appears****");
	}
	
}