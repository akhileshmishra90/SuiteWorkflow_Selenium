package com.gui_auto.pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.dao.ReadAndUpdate_Path;
import com.gui_auto.utilities.CommonFunctions;
import com.gui_auto.utilities.Locators;
import com.gui_auto.utilities.ScreenShot;

public class StorefrontCustomizationPage implements BaseElement 
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




	public  StorefrontCustomizationPage(WebDriver driver) throws Exception 
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

	public int GetRowWithCellText(String Valueneedtobe_Searched,WebElement table_element )
	{

		List<WebElement> tr_collection= table_element.findElements(By.tagName("tr"));   
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : tr_collection)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				if(tdElement.getText().equalsIgnoreCase(Valueneedtobe_Searched))
				{

					return row_num;                           
				}
				else
				{
					col_num++;
				}                    
			}
			row_num++;
		}


		return 0;

	}



	public void NavigateToStorefrontCustomization() throws Exception, IOException
	{


		_driver.findElement(By.xpath(Locators.getProperty(Locators.StorefrontCustomization))).click();


		if(_driver.findElements(By.id(Locators.getProperty(Locators.Add_New_Storefront))).size() > 0)
		{   	

			System.out.println("Navigated to Storefront customization Page successfully");
		}

		else
		{
			System.err.println("Not Able To Navigate to Storefront customization Page");

		}
	}

	public void CreateSmartStorefront(String StorefrontName, String Layout, String Style) throws Exception
	{
		String StorefrontPresentAlert = "";
		String ApplyTheme = "//div[@class='content']/h5[starts-with(text(),'"+Layout+"')]/following::input[@value='Apply']";
		_driver.findElement(By.id(Locators.getProperty(Locators.Add_New_Storefront))).click();
		Thread.sleep(30000);
		CommonFunctions.WaitFor_ElementVisiblity(_driver, By.id(Locators.getProperty(Locators.Storefront_Name)));
		_driver.findElement(By.id(Locators.getProperty(Locators.Storefront_Name))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.Storefront_Name))).sendKeys(StorefrontName);

		_driver.findElement(By.id(Locators.getProperty(Locators.AddSmartStore))).click();


		//Check if storefront with the same name already exists
		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.StorefrontExists))).size() > 0)
		{
			StorefrontPresentAlert = _driver.findElement(By.xpath(Locators.getProperty(Locators.StorefrontExists))).getText();
			if(StorefrontPresentAlert.contains("This name already exists"))
			{
				System.out.println("Storefront already exists");
			}
		}
		else
		{

			// choose layout(theme), style(color), apply theme
			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.ChooseTheme)), Layout);
			CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.ChooseColor)), Style);
			_driver.findElement(By.xpath(ApplyTheme)).click();




			// save & close storefront customization page
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SaveSmartStorefront))).click();
			Thread.sleep(5000);

			_driver.findElement(By.xpath(Locators.getProperty(Locators.smart_store_Productsandcategrories))).click();
			Thread.sleep(7000);
			if(_driver.findElement(By.id(Locators.getProperty(Locators.smart_store_default_Products))).isSelected())
			{
				try
				{
					CommonFunctions.sSelectCheckBox(_driver, false, By.id(Locators.getProperty(Locators.smart_store_default_Products))); 
					//_driver.findElement(By.id(Locators.getProperty(Locators.smart_store_default_Products))).click();
					System.out.println("Default products and categories check box is unchecked..");
				}
				catch(ElementNotVisibleException e1)
				{
					CommonFunctions.sSelectCheckBox(_driver, false, By.xpath(Locators.getProperty(Locators.SmartdefaultProduct_cat_check)));
					System.out.println("Default products and categories check box is unchecked..");
				}
			}
			else
			{
				System.out.println("Default products and categories check box is already not selected.."); 
			}

			Thread.sleep(5000);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SaveSmartStorefront))).click();
			Thread.sleep(3000);



			_driver.findElement(By.xpath(Locators.getProperty(Locators.CloseSmartStorefront))).click();

			//To click on yes button when we are trying to close the button.

			if (_driver.findElements(By.xpath(Locators.getProperty(Locators.SF_Confirmation_button))).size() > 0)
			{
				_driver.findElement(By.xpath(Locators.getProperty(Locators.SF_Confirmation_button))).click();
				System.out.println("confirmation button is clicked");
			}
			else
			{
				System.out.println("confirmation pop up is not present");
			}

		}


	}


	public boolean SearchStorefront(String Storefront) throws Exception
	{



		// Search storefront
		System.out.println("Search storefront");
		CommonFunctions.Wait(_driver, By.id(Locators.getProperty(Locators.SearchStorefrontField)));
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefrontField))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefrontField))).sendKeys(Storefront);
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefront))).click();



		String SFXpath = "//a[contains(text(),'"+Storefront+"')]";
		// if storefront is present, click on storefront to edit
		if(_driver.findElements(By.linkText(Storefront)).size() > 0)
		{

			System.out.println("The storefront " +Storefront+" is present");

			return true;
		}
		else if(_driver.findElements(By.xpath(SFXpath)).size() > 0)
		{
			System.out.println(Storefront+"::Storefront is present");
			return true;
		}

		else
		{
			System.out.println("The storefront " +Storefront+" is not present");
			return false;
		}

	}



	public boolean EditStorefront(String Storefront) throws Exception
	{



		// Search storefront
		System.out.println("Search storefront");
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefrontField))).clear();
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefrontField))).sendKeys(Storefront);
		_driver.findElement(By.id(Locators.getProperty(Locators.SearchStorefront))).click();


		String SFXpath = "//a[contains(text(),'"+Storefront+"')]";

		// if storefront is present, click on storefront to edit
		if(_driver.findElements(By.linkText(Storefront)).size() > 0)
		{
			String PSAttr = _driver.findElement(By.linkText(Storefront)).getAttribute("href");
			System.out.println(PSAttr);
			System.out.println("The storefront " +Storefront+" is present");
			_driver.findElement(By.linkText(Storefront)).click();
			return true;
		}
		else if(_driver.findElements(By.xpath(SFXpath)).size()>0)
		{
			System.out.println("The storefront " +Storefront+" is present");
			_driver.findElement(By.xpath(SFXpath)).click();
			return true;
		}

		else
		{
			System.out.println("The storefront " +Storefront+" is not present");
			return false;
		}

	}

	public void AssociateCompanyToStorefront(String StorefrontName, String Company) throws Exception, IOException
	{

		// Choose storefront that needs to be edited
		EditStorefront(StorefrontName);

		// Associate Company
		// String SelectComp = "//div[div[div[div[span[starts-with(text(),'"+Company+"')]]]]]/div[1]/div/div/input";
		String SelectComp = "//div[@class='ng-scope ngRow even']//Input";
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.AssociateCompanies))).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.AssociateComp))).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchComp))).sendKeys(Company);
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchComp))).sendKeys(Keys.ENTER);

		CommonFunctions.sSelectCheckBox(_driver,true,By.xpath(SelectComp));
		Thread.sleep(2000);
		_driver.manage().window().maximize();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SaveCompSF))).click();
		Thread.sleep(2000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.SaveSmartStorefront))).click();
		Thread.sleep(2000);

		// No need to close after associating company, since clicking on Save from Associate Companies page, will close customization page
		// Below line is for future use incase functionality changes

		//_driver.findElement(By.xpath(Locators.getProperty(Locators.CloseSmartStorefront))).click();
		//Thread.sleep(2000);
	}


	// Can be called when control is in storefront customization page
	public void SetDefaultStorefront(String StorefrontName) throws Exception, IOException
	{
		String StorefrontCheck = "//tr[td/a[contains(text(),'"+StorefrontName+"')]]/td/input[@type='checkbox']";
		boolean bStorefrontPresent = SearchStorefront(StorefrontName);

		if (bStorefrontPresent == true)
		{

			_driver.findElement(By.xpath(StorefrontCheck)).click();
			_driver.findElement(By.id(Locators.getProperty(Locators.SetAsDefault))).click();

		}


	}


	//Naviagate to EditPageElement&Style Section
	public void NavigateToEditPageElementStyleSection() throws Exception
	{
		String editPageElementSection_Xpath=".//*[text()='Edit Page Elements & Styles']";
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(editPageElementSection_Xpath));
		System.out.println("Clicked on Edit Page element section");

	}

	public void clickOn_Add_ContinuShopping() throws Exception
	{
		String add_ContinueShopping_Radio=".//*[text()='Add + Continue Shopping']/../input[1]";
		CommonFunctions.waitForPageLoaded(_driver);Thread.sleep(3000);
		String isSelected=CommonFunctions.getAttribute(_driver, By.xpath(add_ContinueShopping_Radio), "value");

		if(isSelected.equals("true"))
		{
			System.out.println("Add continue radio button is already selected");
		}
		else
		{
			CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(add_ContinueShopping_Radio));
		}

		System.out.println("Clicked on AddContinueShopping radio button");
	}

	public boolean Verify_AddContinueRadioButtonStatus(String toBeCompareWith) throws Exception
	{
		String add_ContinueShopping_Radio=".//*[text()='Add + Continue Shopping']/../input[1]";
		String isSelected=CommonFunctions.getAttribute(_driver, By.xpath(add_ContinueShopping_Radio), "value");
		if(isSelected.equals(toBeCompareWith))
		{
			System.out.println("Add Continue button is checked");
			return true;
		}
		else
		{
			System.err.println("Add continue button is not checked");
			return false;
		}
	}



	public void clickOn_EnableFulFillment_Checkbox(boolean flag)
	{
		String FulFillMentCheckbox_Xpath=".//*[text()='Fulfillment Shopping']/../input[1]";
		CommonFunctions.sSelectCheckBox(_driver, flag, By.xpath(FulFillMentCheckbox_Xpath));
		System.out.println("Performed : "+flag +" action on enable fulfillment checkbox");
	}

	public boolean verifyEnableFulFillmentCheckBoxStatus()
	{
		String enableFulFillmentCheckBox_xpath=".//*[text()='Fulfillment Shopping']/../input[1]";
        boolean status=_driver.findElement(By.xpath(enableFulFillmentCheckBox_xpath)).isSelected();
		if(status)
		{
			System.out.println("Enable fulfillment checkbox is checked");
			return true;
		}
		else
		{
			System.out.println("Enable fulfillment checkbox is unchecked");
			return false;
		}
	}

	public void clickOn_EnableListView_checkbox(boolean flag)
	{
		String enableListView_Checkbox_xpath=".//*[text()='Fulfillment Shopping']/../input[2]";
		int i=0;
		CommonFunctions.sSelectCheckBox(_driver, flag, By.xpath(enableListView_Checkbox_xpath));
		System.out.println("Performed : "+flag +" action on enable List View checkbox");
	}

	public boolean verifyEnableListViewCheckBoxStatus()
	{
		String enableListViewCheckBox_xpath=".//*[text()='Fulfillment Shopping']/../input[2]";
		
		boolean status=_driver.findElement(By.xpath(enableListViewCheckBox_xpath)).isSelected();
		if(status)
		{
			System.out.println("Enable List View checkbox is checked");
			return true;
		}
		else
		{
			System.out.println("Enable List View checkbox is unchecked");
			return false;
		}
	}


	public void clickOn_StorefrontSavebutton() throws Exception
	{
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.SaveSmartStorefront)));
		
		try
		{
			Alert alert=_driver.switchTo().alert();
			alert.accept();
		}
		catch(NoAlertPresentException e1)
		{
			System.out.println("No alert present");
		}
		
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.CloseSmartStorefront)));
		System.out.println("Clicked on close button");


		//To click on yes button when we are trying to close the button.

		if (_driver.findElements(By.xpath(Locators.getProperty(Locators.SF_Confirmation_button))).size() > 0)
		{
			CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.SF_Confirmation_button)));
			System.out.println("confirmation button is clicked");
		}
		else
		{
			System.out.println("confirmation pop up is not present");
		}
	}

	public boolean Is_Company_Already_Associated(String sCompany_To_Be_Checked,String StoreFront)
	{
		//Initail Xpaths
		String Mul_Company_Associate_Image= "//tr[td[a[contains(text(),'"+StoreFront+"')]]]//Input[@type='image']";
		String company_Search_xpath = "//tr[td[contains(text(),'"+sCompany_To_Be_Checked+"')]]//Input[@type='checkbox']";

		//To Click on assocaite companies to provided storefront 
		WebElement MulCompanies= _driver.findElement(By.xpath(Mul_Company_Associate_Image));
		//CommonFunctions.Wait(_driver, By.xpath(Mul_Company_Associate_Image));

		if (MulCompanies.isEnabled())
		{
			for(int i=0;i<3;i++)
			{
				MulCompanies.click();
				System.out.println("Clicked on assocaite multiple companies link");
				if(_driver.findElements(By.id(Locators.getProperty(Locators.Comp_Search_box))).size()>0)
				{
					System.out.println("Company Search button is available now");
					break;
				}
				else
				{
					try {
						Thread.sleep(3000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		else
		{
			System.err.println("Assocaite multiple comapnies link is not available. Check the xpath as:"+Mul_Company_Associate_Image.toString());
			return false;
		}


		//Search the company and check whether it is assocaited or not.

		if(_driver.findElement(By.id(Locators.getProperty(Locators.Comp_Search_box))).isDisplayed())
		{
			System.out.println("Company seach box is available..");
			_driver.findElement(By.id(Locators.getProperty(Locators.Comp_Search_box))).clear();
			_driver.findElement(By.id(Locators.getProperty(Locators.Comp_Search_box))).sendKeys(sCompany_To_Be_Checked);
			System.out.println("company is provided for assocaiton");
			_driver.findElement(By.id(Locators.getProperty(Locators.Search_button))).click();
			System.out.println("search button is clicked.");
		}
		else
		{
			System.err.println("company search box is not available.");
			return false;
		}

		if(_driver.findElement(By.xpath(company_Search_xpath)).isDisplayed())
		{
			System.out.println("company"+sCompany_To_Be_Checked+"is displayed");
			if (_driver.findElement(By.xpath(company_Search_xpath)).isSelected())
			{
				System.out.println("company"+sCompany_To_Be_Checked+" is already assocaited..");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Association_button_xpath))).click();
			}
			else
			{
				_driver.findElement(By.xpath(company_Search_xpath)).click();
				System.out.println("company"+sCompany_To_Be_Checked+" is  assocaited by script");
				_driver.findElement(By.xpath(Locators.getProperty(Locators.Association_button_xpath))).click();
			}
			return true;

		}
		else
		{
			System.err.println("company is not available. pls check..");
			return false;
		}		 
	}
}