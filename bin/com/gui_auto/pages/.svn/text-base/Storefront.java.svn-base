package com.gui_auto.pages;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class Storefront implements BaseElement
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

	public  Storefront(WebDriver driver) throws Exception 
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

	public Boolean Search_Product(String sProductName) throws Exception
	{

		String sProductPath, sName;
		if(_driver.findElements(By.xpath(Locators.getProperty(Locators.SearchField))).size() > 0)
		{   
			System.out.println("Able to see search field");	
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).click();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).clear();
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(sProductName);
			_driver.findElement(By.xpath(Locators.getProperty(Locators.SearchField))).sendKeys(Keys.ENTER);
			sProductPath	=	"//h5[contains(text(),'"+sProductName+"')]";
			Thread.sleep(5000);
			_driver.findElement(By.xpath(sProductPath));

			if(_driver.findElement(By.xpath(sProductPath)).isDisplayed())
			{
				sName = _driver.findElement(By.xpath(sProductPath)).getText();

				if (sName.equalsIgnoreCase(sProductName)) 
				{
					System.out.println(sName);
					System.out.println("Product is present");
					return true;
				}
				else
				{
					System.err.println("Product is not present");
					return false;
				}

			}
			else
			{
				System.err.println("Search field is not present");
				return false;
			}
		}

		else
			return false;
	}

	public void Npp_Qty_BuyNow(String sQuantity, String sProductName) throws Exception
	{
		String sProductPath;
		Thread.sleep(5000);
		sProductPath	=	"//h5[contains(text(),'"+sProductName+"')]";
		_driver.findElement(By.xpath(sProductPath)).click();
		/*Actions actions1 = new Actions(_driver);
		 WebElement menuHoverLink1 = _driver.findElement(By.xpath(sProductPath));
		 actions1.moveToElement(menuHoverLink1).perform();*/
		Thread.sleep(5000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.NppQty))).sendKeys(sQuantity);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.BuyNow))).click();
		////_driver.findElement(By.xpath("//h5[contains(text(),'"+sProductName+"')]/following::div/a/span[contains(text(), 'Buy Now')]")).click();
		Thread.sleep(2000);
	}

	public void OtherProduct_BuyNow(String sProductName) throws Exception
	{
		String sProductPath;
		sProductPath	=	"//h5[contains(text(),'"+sProductName+"')]";
		_driver.findElement(By.xpath("//h5[contains(text(),'"+sProductName+"')]/following::div/a/span[contains(text(), 'Buy Now')]")).click();
		Thread.sleep(2000);
	}

	public String Enter_Jobname_Qty(String sQuantity) throws Exception
	{
		// Entering random string for job name, pass only quantity
		String JName = CommonFunctions.getRandomString(10);
		int JQuantity = 1 + (int)(Math.random() * ((20 - 1) + 1));
		String JQua = "" + JQuantity;
		Thread.sleep(6000);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.JobName)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.JobName))).sendKeys(JName);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).clear();
		_driver.findElement(By.xpath(Locators.getProperty(Locators.QuantityJob))).sendKeys(sQuantity);
		return JName;
	}

	public void GoTo_Cart_Checkout() throws Exception
	{	
		Thread.sleep(10000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.MiniCart))).click();
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.ProceedToCheckout)));
		Thread.sleep(10000);
		_driver.findElement(By.xpath(Locators.getProperty(Locators.ProceedToCheckout))).click();


	}

	public void clickon_DeliveryMethodDropDown_Option(String sShipmentMethod) throws Exception
	{
		String shippmentText_Xpath="//option[text()='"+sShipmentMethod+"']";
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)));
		System.out.println("Cliked onb");
		//		CommonFunctions.ClickOn_Element_BasedOnClickable(_driver, By.xpath(shippmentText_Xpath));
		//		System.out.println("Cliked on option");

	}

	public void Choose_ShipmentMethod(String sShipmentMethod) throws Exception
	{
		String shippmentText_Xpath=".//option[text()='"+sShipmentMethod+"']";
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.selectDropdownByText(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)), sShipmentMethod);
	}

	public void choose_ShipmentMethodBasedOnValue(String sShipmentMethod) throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		String shippmentText_Xpath=".//option[text()='"+sShipmentMethod+"']";
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)));

		Select sele=new Select(_driver.findElement(By.xpath(Locators.getProperty(Locators.SelectShipment))));

		String value=CommonFunctions.validateTheDropDownListOptions(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)), sShipmentMethod);

		CommonFunctions.selectDropdown_ByValue(_driver, By.xpath(Locators.getProperty(Locators.SelectShipment)), value);
		System.out.println("shippment method selected as : "+sShipmentMethod);
		Thread.sleep(3000);		
	}

	public void ProceedToPayment() throws Exception
	{
		CommonFunctions.waitForPageLoaded(_driver);
		for(int i=0;i<=3;i++)
		{
			if(_driver.findElements(By.xpath(Locators.getProperty(Locators.ProceedToPayment))).size()>0)
			{
				CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.ProceedToPayment)));
				_driver.findElement(By.xpath(Locators.getProperty(Locators.ProceedToPayment))).click();
				break;
			}
		}
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Cart_Place_Order)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PaymentMethod)));
		Thread.sleep(3000);
	}

	public void ChoosePaymentMethod(String sPaymentMethod) throws Exception
	{
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.PaymentMethod)));
		CommonFunctions.selectRadioButton(_driver, By.xpath(Locators.getProperty(Locators.PaymentMethod)), sPaymentMethod);
	}

	public void PlaceOrder() throws Exception
	{
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.Cart_Place_Order)));
		_driver.findElement(By.xpath(Locators.getProperty(Locators.Cart_Place_Order))).click();
	}

	public String FetchOrderNumber() throws Exception
	{ 
		String sOrderNum;
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.OrderConfirmationText)));
		CommonFunctions.Wait(_driver, By.xpath(Locators.getProperty(Locators.OrderNumber)));
		sOrderNum = _driver.findElement(By.xpath(Locators.getProperty(Locators.OrderNumber))).getText();
		return sOrderNum;
	}

	public Boolean OrderNumberInOrderStatus(String sOrderNumber) throws Exception
	{
		String OrderNumText;
		String sOrderPath = "//td/div[a[text() = '"+sOrderNumber+"']]";
		Thread.sleep(6000);
		Actions actions1 = new Actions(_driver);
		WebElement menuHoverLink1 = _driver.findElement(By.xpath("//*[@class='icon-user-male']"));
		actions1.moveToElement(menuHoverLink1).perform();
		Thread.sleep(2000);
		WebElement subLink = _driver.findElement(By.xpath("//*[contains(text(),'Order History & Status')]"));
		subLink.click();

		CommonFunctions.Wait(_driver, By.xpath(sOrderPath));
		OrderNumText = _driver.findElement(By.xpath(sOrderPath)).getText();
		System.out.println("The order number in order history status is "+OrderNumText);
		int pos = OrderNumText.indexOf(sOrderNumber);
		if (pos > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void navigateToAdministrationPage() throws Exception
	{
		CommonFunctions.ClickElement(_driver, By.xpath(Locators.getProperty(Locators.ClickAdministration)));
		CommonFunctions.waitForPageLoaded(_driver);
		CommonFunctions.Wait(_driver, By.name("ctl00$ctl00$TabNavigatorSFAdministration$QuickMenuSearch"));
	}

	public void changeOrderStatus(String DSFOrderNumber, String ChangeStatus) throws Exception
	{		
		CommonFunctions.ClickElement(_driver, By.id("ctl00_ctl00_C_M_HyperLinkOrderView2"));
		int sOrderNumCnt = CommonFunctions.RowCount(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr"));

		for (int i=2; i<=sOrderNumCnt; i++)
		{
			if (CommonFunctions.isElementPresent(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//a[text()='"+DSFOrderNumber+"']")))
			{
				if (CommonFunctions.Elementdisplayed_Enabled(_driver.findElement(By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//a[contains(@id, 'OrderViewItem_LinkButtonStatus')]"))))
				{
					CommonFunctions.ClickElement(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//a[contains(@id, 'OrderViewItem_LinkButtonStatus')]"));
					CommonFunctions.Wait(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//select"));
					CommonFunctions.selectDropdownByText(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//select"), ChangeStatus);
					CommonFunctions.waitForPageLoaded(_driver);
					String sGetChangedStatus = CommonFunctions.GetText(_driver, By.xpath("//table[contains(@class, 'bg-Bus')]/tbody/tr["+i+"]//a[contains(@id, 'OrderViewItem_LinkButtonStatus')]"));
					assertTrue("The DSF Order status was not changed correctly, Expected = "+ChangeStatus+", Actual = "+sGetChangedStatus, sGetChangedStatus.equals(ChangeStatus));
				}
				else
				{
					System.err.println("The Order "+DSFOrderNumber+" status is not editable");
				}				
			}
		}
	}

	public void reorderFromdsf(String DSFOrderNumber) throws Exception
	{		
		CommonFunctions.ClickElement(_driver, By.xpath("//div[@class='myaccount-label']/span[3]"));
		Thread.sleep(1000);
		CommonFunctions.ClickElement(_driver, By.xpath("//li[text()='Order History & Status']"));
		CommonFunctions.waitForPageLoaded(_driver);
		Thread.sleep(2000);
		if (CommonFunctions.isElementPresent(_driver, By.xpath("//a[contains(text(), '"+DSFOrderNumber+"')]")))
		{
			CommonFunctions.ClickElement(_driver, By.xpath("//a[contains(text(), '"+DSFOrderNumber+"')]"));
			CommonFunctions.waitForDSFPageLoad(_driver);
			CommonFunctions.waitForPageLoaded(_driver);
			CommonFunctions.ClickElement(_driver, By.xpath("//span[contains(text(),'ReOrder')]"));
			CommonFunctions.waitForDSFPageLoad(_driver);
		}
	}
	
}