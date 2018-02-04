package com.gui_auto.pages;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate_Path;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;


public class Users  implements BaseElement
{

	Locators loc = new Locators();
	protected static Locators _Locators = new Locators();
	ReadAndUpdate_Path dbConnection = new ReadAndUpdate_Path();
	private String _pageURL;
	protected WebDriver _driver;
	boolean acceptNextAlert = true;
	ScreenShot TakeScreenShot = new ScreenShot();
	String NewFileNamePath = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();




	public  Users(WebDriver driver) throws Exception 
	{  
		super();  
		PropertyConfigurator.configure("config/log4j.properties");
		this._driver = driver;  

	}  


	public void navigateToPageAndWait() 
	{
		_driver.get(_pageURL);

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


	public boolean isTextPresent(String str)
	{
		WebElement bodyElement = _driver.findElement(By.tagName("body"));
		return bodyElement.getText().contains(str);
	}


	public void NavigateToUsers() throws Exception, IOException
	{



		_driver.findElement(By.xpath(Locators.getProperty(Locators.Users))).click();

		//CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.TaxesLabel)));
		if(_driver.findElements(By.id(Locators.getProperty(Locators.User_Link))).size() > 0)
		{   	

			System.out.println("Navigated to Users Page successfully");
		}

		else
		{
			System.err.println("Not Able To Navigate to Users Page");

		}
	}


	public void EnterUserContactInformation(String Username, String Company) throws Exception
	{


		String UserConfirmation="";

		String sfirstname = Username;
		String sLastName = Username;
		String sAddress1 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS1");
		String sAddress2 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS2");
		String sAddress3 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS3");
		String sCity = dbConnection.ReadFunctionFromExcel("User", "CITY");
		String sState = dbConnection.ReadFunctionFromExcel("User", "USERSTATE");
		String sZipPostalCode = dbConnection.ReadFunctionFromExcel("User", "USERZIPCODE");
		String sCountry = dbConnection.ReadFunctionFromExcel("User", "COUNTRY");
		String sPhoneNumber1 = dbConnection.ReadFunctionFromExcel("User", "PHONENUM1");
		String sPhoneNumber2 = dbConnection.ReadFunctionFromExcel("User", "PHONENUM2");
		String sFaxNumber = dbConnection.ReadFunctionFromExcel("User", "FAXNUMBER");
		String sTitle = dbConnection.ReadFunctionFromExcel("User", "TITLE");
		String sCompany = Company;
		String sDepartment = dbConnection.ReadFunctionFromExcel("User", "DEPARTMENT");
		String sEmailAddress = dbConnection.ReadFunctionFromExcel("User", "EMAIL");

		String sMiddleName = Username+"_mn";



		System.out.println("ENTER THE CONTACT DETAILS"); 


		_driver.findElement(By.id(Locators.getProperty(Locators.UserFname))).sendKeys(sfirstname);

		if (_driver.findElements(By.id(Locators.getProperty(Locators.UserMName))).size()>0)
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserMName))).sendKeys(sMiddleName);
		}

		_driver.findElement(By.id(Locators.getProperty(Locators.UserLName))).sendKeys(sLastName);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineone))).sendKeys(sAddress1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLinetwo))).sendKeys(sAddress2);

		if (_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).sendKeys(sAddress3);
		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);





		WebElement selectCountry = _driver.findElement(By.id(Locators.getProperty(Locators.UserCountry)));
		Select selcountry = new Select(selectCountry);
		String SelectedCountry;
		SelectedCountry = selcountry.getFirstSelectedOption().getText();
		if (SelectedCountry.equalsIgnoreCase(sCountry)) // if country selected is already United States
		{

		}
		else
		{
			selectCountry.sendKeys(sCountry);
		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserZipCode))).sendKeys(sZipPostalCode);




		WebElement dropDownListBox = _driver.findElement(By.id(Locators.getProperty(Locators.UserState))); 
		Select clickThis = new Select(dropDownListBox); 
		String SelectedState;
		SelectedState = clickThis.getFirstSelectedOption().getText();
		if (SelectedState.equalsIgnoreCase(sState)) // no need to select anything
		{

		}
		else
		{
			dropDownListBox.sendKeys(sState);

		}

		CommonFunctions.selectDropdown_ByValue(_driver, By.id(Locators.getProperty(Locators.UserState)), "AZ"); //Added By 

		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneOne))).sendKeys(sPhoneNumber1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneTwo))).sendKeys(sPhoneNumber2);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserFax))).sendKeys(sFaxNumber);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserTitle))).sendKeys(sTitle);

		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).isEnabled())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).sendKeys(sCompany);

		}
		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).sendKeys(sDepartment);
		}

		_driver.findElement(By.id(Locators.getProperty(Locators.UserEmail))).sendKeys(sEmailAddress);


		_driver.findElement(By.id(Locators.getProperty(Locators.SaveUser))).click();

		UserConfirmation =  _driver.findElement(By.id(Locators.getProperty(Locators.UserSuccess))).getText();

		if(UserConfirmation.equalsIgnoreCase("User saved successfully. Would you like to add another user?"))
		{
			System.out.println("User added!!");
		}
		else
		{
			System.err.println("User contact information entry failed");
		}


	}


	// With login bypass unique id
	public void EnterUserContactInformation(String Username, String Company, String UniqueId) throws Exception
	{


		String UserConfirmation="";

		String sfirstname = Username+"_fn";
		String sLastName = Username+"_ln";
		String sAddress1 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS1");
		String sAddress2 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS2");
		String sAddress3 = dbConnection.ReadFunctionFromExcel("User", "ADDRESS3");
		String sCity = dbConnection.ReadFunctionFromExcel("User", "CITY");
		String sState = dbConnection.ReadFunctionFromExcel("User", "USERSTATE");
		String sZipPostalCode = dbConnection.ReadFunctionFromExcel("User", "USERZIPCODE");
		String sCountry = dbConnection.ReadFunctionFromExcel("User", "COUNTRY");
		String sPhoneNumber1 = dbConnection.ReadFunctionFromExcel("User", "PHONENUM1");
		String sPhoneNumber2 = dbConnection.ReadFunctionFromExcel("User", "PHONENUM2");
		String sFaxNumber = dbConnection.ReadFunctionFromExcel("User", "FAXNUMBER");
		String sTitle = dbConnection.ReadFunctionFromExcel("User", "TITLE");
		String sCompany = Company;
		String sDepartment = dbConnection.ReadFunctionFromExcel("User", "DEPARTMENT");
		String sEmailAddress = dbConnection.ReadFunctionFromExcel("User", "EMAIL");

		String sMiddleName = Username+"_mn";



		System.out.println("ENTER THE CONTACT DETAILS"); 


		_driver.findElement(By.id(Locators.getProperty(Locators.UserFname))).sendKeys(sfirstname);

		/* if (_driver.findElement(By.id(Locators.getProperty(Locators.UserMName))).isDisplayed())
    	 {
    	 _driver.findElement(By.id(Locators.getProperty(Locators.UserMName))).sendKeys(sMiddleName);
    	 }*/

		_driver.findElement(By.id(Locators.getProperty(Locators.UserLName))).sendKeys(sLastName);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineone))).sendKeys(sAddress1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLinetwo))).sendKeys(sAddress2);

		if (_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).sendKeys(sAddress3);
		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);





		WebElement selectCountry = _driver.findElement(By.id(Locators.getProperty(Locators.UserCountry)));
		Select selcountry = new Select(selectCountry);
		String SelectedCountry;
		SelectedCountry = selcountry.getFirstSelectedOption().getText();
		if (SelectedCountry.equalsIgnoreCase(sCountry)) // if country selected is already United States
		{

		}
		else
		{
			selectCountry.sendKeys(sCountry);
		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserZipCode))).sendKeys(sZipPostalCode);




		WebElement dropDownListBox = _driver.findElement(By.id(Locators.getProperty(Locators.UserState))); 
		Select clickThis = new Select(dropDownListBox); 
		String SelectedState;
		SelectedState = clickThis.getFirstSelectedOption().getText();
		if (SelectedState.equalsIgnoreCase(sState)) // no need to select anything
		{

		}
		else
		{
			dropDownListBox.sendKeys(sState);

		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneOne))).sendKeys(sPhoneNumber1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneTwo))).sendKeys(sPhoneNumber2);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserFax))).sendKeys(sFaxNumber);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserTitle))).sendKeys(sTitle);

		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).isEnabled())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).sendKeys(sCompany);

		}
		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).sendKeys(sDepartment);
		}

		_driver.findElement(By.id(Locators.getProperty(Locators.UserEmail))).sendKeys(sEmailAddress);

		_driver.findElement(By.id(Locators.getProperty(Locators.LoginBypassUniqueId))).sendKeys(UniqueId);



		_driver.findElement(By.id(Locators.getProperty(Locators.SaveUser))).click();

		UserConfirmation =  _driver.findElement(By.id(Locators.getProperty(Locators.UserSuccess))).getText();

		if(UserConfirmation.equalsIgnoreCase("User saved successfully. Would you like to add another user?"))
		{
			System.out.println("User added!!");
		}
		else
		{
			System.err.println("User contact information entry failed");
		}


	}


	public void Add_User(String Username, String Password,  String Company, String Printshop,String Group) throws Exception
	{



		String UserExistsMsg = "";
		_driver.findElement(By.id(Locators.getProperty(Locators.User_Link))).click();

		// Enter username, password, company, printshop, group
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).sendKeys(Username);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).sendKeys(Password);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).sendKeys(Password);

		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserComp)), Company);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserPrintshop)), Printshop);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserGroups)), Group);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserNext))).click();

		Thread.sleep(1000);

		// Check if user with the same username already exists
		if (_driver.findElements(By.id(Locators.getProperty(Locators.UserAlreadyExists))).size() > 0)
		{
			UserExistsMsg = _driver.findElement(By.id(Locators.getProperty(Locators.UserAlreadyExists))).getText();
			if(UserExistsMsg.contains("Please register using a different User Name"))
			{

			}
		}
		else
		{

			// Enter secret answer
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).sendKeys("1");
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAnswerNext))).click();

			//Enter contact information for user
			EnterUserContactInformation(Username,Company);

		} //end else

	} //end function


	// With login bypass unique Id
	public void Add_User(String Username, String Password,  String Company, String Printshop,String Group, String UniqueId) throws Exception
	{



		String UserExistsMsg = "";
		_driver.findElement(By.id(Locators.getProperty(Locators.User_Link))).click();

		// Enter username, password, company, printshop, group
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).sendKeys(Username);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).sendKeys(Password);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).sendKeys(Password);

		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserComp)), Company);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserPrintshop)), Printshop);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserGroups)), Group);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserNext))).click();

		Thread.sleep(1000);

		// Check if user with the same username already exists
		if (_driver.findElements(By.id(Locators.getProperty(Locators.UserAlreadyExists))).size() > 0)
		{
			UserExistsMsg = _driver.findElement(By.id(Locators.getProperty(Locators.UserAlreadyExists))).getText();
			if(UserExistsMsg.contains("Please register using a different User Name"))
			{

			}
		}
		else
		{

			// Enter secret answer
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).sendKeys("1");
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAnswerNext))).click();

			//Enter contact information for user
			EnterUserContactInformation(Username,Company,UniqueId);

		} //end else

	} //end function

	public void File_Dialog_Enter(String sPath) throws Exception, AWTException
	{

		Thread.sleep(7000);
		setClipboardData(sPath);
		Thread.sleep(7000);			 
		Robot robot = new Robot();
		Thread.sleep(7000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(7000);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(7000);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(7000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(7000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(7000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);		

	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public boolean Buyyer_save_Files(String Filepath) throws Exception, AWTException  
	{

		String filename[] = Filepath.split("/");	
		Filepath = Filepath.replace("/", "\\");
		_driver.manage().window().maximize();

		Actions actions = new Actions(_driver);
		WebElement menuHoverLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.MyAccountResp)));
		actions.moveToElement(menuHoverLink).perform();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	         		         
		WebElement subLink = _driver.findElement(By.xpath(Locators.getProperty(Locators.My_Saved_files)));
		Thread.sleep(1000);
		if (subLink.isDisplayed())
		{
			System.out.println("My Saved files option is displayed");
			subLink.click();

			System.out.println("My Saved files option is Clicked");
		}
		else
		{
			System.err.println("My Saved files option is not displayed");
			return false;
		}

		//To verify that Add File button is present or not.
		WebElement Add_File = _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_File_Button)));
		WebElement Supported_File_Formats = _driver.findElement(By.xpath(Locators.getProperty(Locators.Supported_File_Formats)));

		if (Add_File.isDisplayed() & Add_File.isEnabled())
		{
			System.out.println("Add File button is available and enabled.");
		}

		else
		{
			System.err.println("Add File button is not available. Pls check xpath 'Add_File_Button' from locators");
		}

		if (Supported_File_Formats.isDisplayed() & Supported_File_Formats.isEnabled())
		{
			System.out.println("Supported File Formats button is available and enabled.");
			Add_File.click();
			System.out.println("Add File button is Clicked.");		    	   
		}

		else
		{
			System.err.println("Supported File Formats button is not available. Pls check xpath 'Supported_File_Formats' from locators");
		}

		//  File_Dialog_Enter(Filepath);	//Commented and Introduced AutoIT Script to Upload a file in next step

		//===============AutoIT Script=======================// TO Test From ANT File Execution
		try {

			for(int i=1;i<=3;i++)
			{
				Auto_IT_Upload_File();
				if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Add_more_Files_label))).size()>0)
				{
					System.out.println("File uploaded sucessfully at the"+ i +"th attempt");
					break;
				}
				Thread.sleep(60000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Auto_It");
			e.printStackTrace();

		}
		//==================================================//  


		/*--- +Add more files, Close, Upload button verification---*/
		// 
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.xpath(Locators.getProperty(Locators.Add_more_Files_label)));
		WebElement btnAdd_more_Files_label = _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_more_Files_label)));
		WebElement Upload_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_button)));
		WebElement Upload_Files_Dialog = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_Files_Dialog_Title)));

		if (btnAdd_more_Files_label.isDisplayed() & btnAdd_more_Files_label.isEnabled())
		{
			System.out.println("Add More Files button is available and enabled.");

		}

		else
		{
			System.err.println("Add More Files button is not available. Pls check xpath 'Add_more_Files_label' from locators");
		}

		//For Upload_button button
		if (Upload_button.isDisplayed())
		{
			System.out.println("Upload button is available and enabled.");

		}

		else
		{
			System.err.println("Upload button is not available. Pls check xpath 'Upload_button' from locators");
		}

		//To click on +Add More Files button.
		// btnAdd_more_Files_label.click();	   			      	       

		//To Convert to PDF..
		String PDF_CheckBox_To_Convert = "//tr[td[span[contains(text(),'"+ filename[filename.length-1]+"')]]]//input[contains(@ng-click,'OnChkConvertToPDFClick')]";

		if (_driver.findElements(By.xpath(PDF_CheckBox_To_Convert)).size() > 0 ) 
		{
			System.out.println("Convert to PDF check box is available..");
			CommonFunctions.sSelectCheckBox(_driver, true, By.xpath(PDF_CheckBox_To_Convert));
		}
		else
		{
			System.err.println("Convert to PDF check box is not available..");
		}

		Upload_button.click();
		Thread.sleep(3000);
		//_driver.findElement(By.xpath(Locators.getProperty(Locators.Close_Button)));

		for (int i =0; i<=10;i++)
		{
			Thread.sleep(3000);
			// For Close button
			if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Close_Button))).isDisplayed() & _driver.findElement(By.xpath(Locators.getProperty(Locators.Close_Button))).isEnabled())
			{
				System.out.println("Close button is available and enabled.");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Close_Button))).click();
				break;
			}
			else
			{
				System.out.println("Conversion/file upload is in progress::No. Of Itterations completed::"+i);
			}
		}  




		CommonFunctions.Wait(_driver, By.xpath("//a[text() = '"+ filename[filename.length-1].trim() +"']"));
		boolean blnFile_display = _driver.findElement(By.xpath("//a[text() = '"+ filename[filename.length-1].trim() +"']")).isDisplayed();

		if (blnFile_display)
		{
			System.out.println("Given File is saved in profile");
		}
		else
		{
			System.err.println("Given File is not saved");
		}

		return false;
	}

	public boolean Buyyer_Adhoc_Upload_Multiple_Files(String Filepath) throws Exception, AWTException, IOException  
	{
		//Ensure that filename should contain ;End at last in file string.
		//Eg:- Filepath = "c:/krishna/phani1.doc;c:/krishna/phani2.bmp;end"
		boolean uploadflag = false;
		String[] Filedetails = Filepath.split(";");
		int nooffiles = Filedetails.length-1;
		//=====================================================================================
		//To Click on Add Files button
		//=====================================================================================
		System.out.println("Select a External files by clicking on ADD FILES");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobAddFiles))).click();

		//=====================================================================================
		//To Click on Upload Files button
		//=====================================================================================
		Thread.sleep(5000);
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).size() > 0)
		{
			System.out.println("Adhoc upload files button is available");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).click();
			System.out.println("Upload files button is clicked..");
			Thread.sleep(2000);
		}
		else
		{
			System.err.println("Adhoc - Upload files button is not displayed..");
		}

		for (int j=0;j<=nooffiles;j++)
		{

			if ( Filedetails[j].equalsIgnoreCase("End"))
			{
				break;
			}
			String filename[] = Filedetails[j].split("/");	
			String SubFile = Filedetails[j].replace("/", "\\"); 	//Commented to verify temp	         					     
			// String SubFile = Filedetails[j];//Added Temp To Verify		
			System.out.println("File path uploaded::"+SubFile);

			// File_Dialog_Enter(SubFile); //Commented to check whether the AUTo_IT Muktiple scripts are working or not

			Auto_IT_Upload_MultipleFiles(SubFile);

			Thread.sleep(5000);

			/*--- +Add more files, Close, Upload button verification---*/
			//  		     		      	       


			//To Convert to PDF..
			String PDF_CheckBox_To_Convert = "//tr[td[span[contains(text(),'"+ filename[filename.length-1]+"')]]]//input[contains(@ng-click,'OnChkConvertToPDFClick')]";

			if (_driver.findElements(By.xpath(PDF_CheckBox_To_Convert)).size() > 0 ) 
			{
				System.out.println("Convert to PDF check box is available..");
				CommonFunctions.sSelectCheckBox(_driver, true, By.xpath(PDF_CheckBox_To_Convert));
			}
			else
			{
				System.err.println("Convert to PDF check box is not available..");
			}

			WebElement btnAdd_more_Files_label = _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_more_Files_label)));					       
			WebElement Upload_Files_Dialog = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_Files_Dialog_Title)));

			if (btnAdd_more_Files_label.isDisplayed() & btnAdd_more_Files_label.isEnabled())
			{
				System.out.println("Add More Files button is available and enabled.");
				//To click on +Add More Files button.
				if (j<nooffiles-1)
				{
					btnAdd_more_Files_label.click();					    		
				}
			}

			else
			{
				System.err.println("Add More Files button is not available. Pls check xpath 'Add_more_Files_label' from locators");
			}		       

		} // End for main for loop		

		WebElement Upload_button = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_button)));		 				  
		//For Upload_button button
		if (Upload_button.isDisplayed())
		{
			System.out.println("Upload button is available and enabled.");
			Upload_button.click();
		}

		else
		{
			System.err.println("Upload button is not available. Pls check xpath 'Upload_button' from locators");
		}


		for (int i =0; i<=15;i++)
		{
			Thread.sleep(6000);
			// For Close button
			if (_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).isDisplayed() & _driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).isEnabled())
			{
				System.out.println("Close button is available and enabled.");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files_Done_button))).click();
				uploadflag = true;
				break;
			}		      

		}  					      					      
		Thread.sleep(15000);		//blind wait.. need to improve the code.		      
		String Nooffile_Uploaded = _driver.findElement(By.id("txtGoToPage")).getAttribute("placeholder");

		if (Nooffile_Uploaded.contains("/"))
		{
			String[] Nooffiles = Nooffile_Uploaded.split("/");
			int TotalFiles = Integer.parseInt(Nooffiles[Nooffiles.length-1].trim()) ;
			if (TotalFiles == 6)
			{
				System.out.println("Total 6 files are uploaded correctly.. ");
			}
			else
			{
				System.err.println("files are not uploaded correctly");
				System.err.println("No.Of files expected to be uploaded:6");
				System.err.println("No.Of files Actually uploaded:"+TotalFiles);
			}
		}

		return false;
	}

	public void Auto_IT_Upload_File() throws IOException
	{


		String uploadFilePath = "C:\\Auto_IT\\AutoIT.exe";
		String[] cmd = new String[2];
		cmd[0] = uploadFilePath;
		cmd[1]="c:\\WorkSpace_KK\\ResponsiveUI\\InputTestData\\Data\\Jpeg\\Before_Winter.jpg";
		//cmd[1]="c:/WorkSpace_KK/ResponsiveUI/InputTestData/Data/Jpeg/Before_Winter.jpg";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);


	}

	public void Auto_IT_Upload_MultipleFiles(String subfile) throws IOException
	{
		String uploadFilePath = "C:\\Auto_IT\\UploadFile.exe";
		String[] cmd = new String[2];
		cmd[0] = uploadFilePath;
		cmd[1]=subfile;
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
	}

	public void upload_Multiple_Files(String Filepath) throws Exception, IOException
	{
		//Ensure that filename should contain ;End at last in file string.
		//Eg:- Filepath = "c:/krishna/phani1.doc;c:/krishna/phani2.bmp;end"
		boolean uploadflag = false;
		String[] Filedetails = Filepath.split(";");
		int nooffiles = Filedetails.length-1;
		//=====================================================================================
		//To Click on Add Files button
		//=====================================================================================
		System.out.println("Select a External files by clicking on ADD FILES");
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobAddFiles))).click();

		//=====================================================================================
		//To Click on Upload Files button
		//=====================================================================================

		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).size() > 0)
		{
			System.out.println("Adhoc upload files button is available");
			_driver.findElement(By.xpath(Locators.getProperty(Locators.Adhoc_Upload_Files))).click();
			System.out.println("Upload files button is clicked..");
			Thread.sleep(2000);
		}
		else
		{
			System.err.println("Adhoc - Upload files button is not displayed..");
		}

		for(int i=0;i<6;i++)
		{
			Auto_IT_Upload_File();

			Thread.sleep(5000);

			/*--- +Add more files, Close, Upload button verification---*/
			//  		     		      	       


			WebElement btnAdd_more_Files_label = _driver.findElement(By.xpath(Locators.getProperty(Locators.Add_more_Files_label)));					       
			WebElement Upload_Files_Dialog = _driver.findElement(By.xpath(Locators.getProperty(Locators.Upload_Files_Dialog_Title)));

			if (btnAdd_more_Files_label.isDisplayed() & btnAdd_more_Files_label.isEnabled())
			{
				System.out.println("Add More Files button is available and enabled.");
				//To click on +Add More Files button.
				if (i<nooffiles-1)
				{
					btnAdd_more_Files_label.click();					    		
				}
			}

			else
			{
				System.err.println("Add More Files button is not available. Pls check xpath 'Add_more_Files_label' from locators");
			}		       

		}

	}
	public boolean SeachUser(String name,String FilterType,String username) throws Exception
	{
		CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.User_DropDownFilter_id)));
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.User_DropDownFilter_id)), FilterType);
		_driver.findElement(By.id(Locators.getProperty(Locators.User_SearchTextBox_id))).sendKeys(name);
		_driver.findElement(By.id(Locators.getProperty(Locators.User_FindButton_id))).click();
		String expectedUserRecord="//a[contains(text(),'"+username+"')]";
		CommonFunctions.Wait(_driver, By.xpath(expectedUserRecord));
		if(_driver.findElements(By.xpath(expectedUserRecord)).size()>0)
		{
			System.out.println(username + " User record is present ");
			return true;
		}
		else
		{
			Assert.fail(username + " User record is not present ");
			return false;
		}

	}

	public boolean verifyUserGroupAcces(String username, String GroupNametoCompare) throws Exception 
	{
		//String expectedGroupName_Xpath="//td[text()='"+GroupNametoCompare+"']";
		String expectedGroupName_Xpath ="//*[@id='ctl00_ctl00_C_M_UserDetails1_DataGridUserDetail_ctl02_DataGridGroupDetail']/tbody/tr[2]/td";
		String expectedUserRecord="//a[contains(text(),'"+username+"')]";
		CommonFunctions.Wait(_driver, By.xpath(expectedUserRecord));
		_driver.findElement(By.xpath(expectedUserRecord)).click();
		int va= _driver.findElements(By.xpath(expectedGroupName_Xpath)).size();
		System.out.println("size : "+va);
		// CommonFunctions.Wait(_driver, By.xpath(expectedGroupName_Xpath));
		String actualGroupName=_driver.findElement(By.xpath(expectedGroupName_Xpath)).getAttribute("textContent").trim();
		if(GroupNametoCompare.equalsIgnoreCase(actualGroupName))
		{
			System.out.println(GroupNametoCompare + " Group access is  avilable to user :"+username);
			return true;
		}
		else
		{
			Assert.fail(GroupNametoCompare + " Group access is not avilable to user :"+username);
			return false;
		}

	}

	public void EnterUserContactInformation(String Username, String Company, String UniqueId,String sheetName) throws Exception
	{


		String UserConfirmation="";

		String sfirstname = Username+"_fn";
		String sLastName = Username+"_ln";
		String sAddress1 = dbConnection.ReadFunctionFromExcel(sheetName, "ADDRESS1");
		String sAddress2 = dbConnection.ReadFunctionFromExcel(sheetName, "ADDRESS2");
		String sAddress3 = dbConnection.ReadFunctionFromExcel(sheetName, "ADDRESS3");
		String sCity = dbConnection.ReadFunctionFromExcel(sheetName, "CITY");
		String sState = dbConnection.ReadFunctionFromExcel(sheetName, "USERSTATE");
		String sZipPostalCode = dbConnection.ReadFunctionFromExcel(sheetName, "USERZIPCODE");
		String sCountry = dbConnection.ReadFunctionFromExcel(sheetName, "COUNTRY");
		String sPhoneNumber1 = dbConnection.ReadFunctionFromExcel(sheetName, "PHONENUM1");
		String sPhoneNumber2 = dbConnection.ReadFunctionFromExcel(sheetName, "PHONENUM2");
		String sFaxNumber = dbConnection.ReadFunctionFromExcel(sheetName, "FAXNUMBER");
		String sTitle = dbConnection.ReadFunctionFromExcel(sheetName, "TITLE");
		String sCompany = Company;
		String sDepartment = dbConnection.ReadFunctionFromExcel(sheetName, "DEPARTMENT");
		String sEmailAddress = dbConnection.ReadFunctionFromExcel(sheetName, "EMAIL");

		String sMiddleName = Username+"_mn";



		System.out.println("ENTER THE CONTACT DETAILS"); 


		_driver.findElement(By.id(Locators.getProperty(Locators.UserFname))).sendKeys(sfirstname);

		/* if (_driver.findElement(By.id(Locators.getProperty(Locators.UserMName))).isDisplayed())
    	 {
    	 _driver.findElement(By.id(Locators.getProperty(Locators.UserMName))).sendKeys(sMiddleName);
    	 }*/

		_driver.findElement(By.id(Locators.getProperty(Locators.UserLName))).sendKeys(sLastName);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineone))).sendKeys(sAddress1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLinetwo))).sendKeys(sAddress2);

		if (_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAddLineThree))).sendKeys(sAddress3);
		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);





		WebElement selectCountry = _driver.findElement(By.id(Locators.getProperty(Locators.UserCountry)));
		Select selcountry = new Select(selectCountry);
		String SelectedCountry;
		//SelectedCountry = selcountry.getFirstSelectedOption().getText();
		//selectCountry.sendKeys(sCountry);
		CommonFunctions.selectDropdown_ByValue(_driver, By.id(Locators.getProperty(Locators.UserCountry)), sCountry);
		Thread.sleep(2000);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserZipCode))).sendKeys(sZipPostalCode);
		WebElement dropDownListBox = _driver.findElement(By.id(Locators.getProperty(Locators.UserState))); 
		Select clickThis = new Select(dropDownListBox); 
		String SelectedState;
		SelectedState = clickThis.getFirstSelectedOption().getText();
		if (SelectedState.equalsIgnoreCase(sState)) // no need to select anything
		{

		}
		else
		{
			dropDownListBox.sendKeys(sState);

		}


		_driver.findElement(By.id(Locators.getProperty(Locators.UserCity))).sendKeys(sCity);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneOne))).sendKeys(sPhoneNumber1);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPhoneTwo))).sendKeys(sPhoneNumber2);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserFax))).sendKeys(sFaxNumber);
		_driver.findElement(By.id(Locators.getProperty(Locators.UserTitle))).sendKeys(sTitle);

		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).isEnabled())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserCompany))).sendKeys(sCompany);

		}
		if ( _driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).isDisplayed())
		{
			_driver.findElement(By.id(Locators.getProperty(Locators.UserDepartment))).sendKeys(sDepartment);
		}

		_driver.findElement(By.id(Locators.getProperty(Locators.UserEmail))).sendKeys(sEmailAddress);

		_driver.findElement(By.id(Locators.getProperty(Locators.LoginBypassUniqueId))).sendKeys(UniqueId);



		_driver.findElement(By.id(Locators.getProperty(Locators.SaveUser))).click();

		UserConfirmation =  _driver.findElement(By.id(Locators.getProperty(Locators.UserSuccess))).getText();

		if(UserConfirmation.equalsIgnoreCase("User saved successfully. Would you like to add another user?"))
		{
			System.out.println("User added!!");
		}
		else
		{
			System.err.println("User contact information entry failed");
		}


	}


	public void Add_User_customization(String Username, String Password,  String Company, String Printshop,String Group,String sheetName) throws Exception
	{



		String UserExistsMsg = "";
		_driver.findElement(By.id(Locators.getProperty(Locators.User_Link))).click();

		// Enter username, password, company, printshop, group
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Username))).sendKeys(Username);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserPassword))).sendKeys(Password);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.UserVerifyPwd))).sendKeys(Password);

		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserComp)), Company);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserPrintshop)), Printshop);
		CommonFunctions.selectDropdownByText(_driver, By.id(Locators.getProperty(Locators.UserGroups)), Group);

		_driver.findElement(By.id(Locators.getProperty(Locators.UserNext))).click();

		Thread.sleep(1000);

		// Check if user with the same username already exists
		if (_driver.findElements(By.id(Locators.getProperty(Locators.UserAlreadyExists))).size() > 0)
		{
			UserExistsMsg = _driver.findElement(By.id(Locators.getProperty(Locators.UserAlreadyExists))).getText();
			if(UserExistsMsg.contains("Please register using a different User Name"))
			{

			}
		}
		else
		{

			// Enter secret answer
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.UserSecAnswer))).sendKeys("1");
			_driver.findElement(By.id(Locators.getProperty(Locators.UserAnswerNext))).click();

			//Enter contact information for user
			EnterUserContactInformation(Username,Company,"",sheetName);

		} //end else

	} //end function

}//Class End
