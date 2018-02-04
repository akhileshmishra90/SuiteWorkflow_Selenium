/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright � 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.base_classes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
public class GUI_automation_base {

	public static WebDriver _driver;

	private static String URL = null;
	
	private static String Mobile = null;

	private static String BROWSER = null;
	
	private static String DSFURL = null;
	
	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	private static String DOWNLOADPATH = System.getProperty("user.dir").concat("\\InputTestData\\TempDownload");
	
	DesiredCapabilities capabilities = new DesiredCapabilities();

	/**
	 * JUnit setup should call this via
	 * 
	 * super.setUp()
	 * 
	 * By doing so, the mentioned Driver for WebDriver will be instantiated.
	 * 
	 * @throws Exception
	 */
	public void setUp(final String BROWSER) throws Exception {
		System.out.println(BROWSER);
		if (BROWSER.contentEquals("firefox"))
		{
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			String Path=System.getProperty("user.dir");
//			
//			firefoxProfile.setPreference("browser.download.folderList", 2);
//			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
//			firefoxProfile.setPreference("browser.download.dir", Path+"\\"+"InputTestData\\TempDownload");
//			System.out.println(Path+"\\"+"D:\\SuiteWorkflow_Selenium\\InputTestData\\TempDownload");
//			//firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
//			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/xls");
//			firefoxProfile.setPreference("pdfjs.disabled", true);			
//			firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
//			firefoxProfile.setPreference("plugin.scan.plid.all", false);
//			firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
//
//			JavaScriptError.addExtension(firefoxProfile);	
			
			//System. setProperty("webdriver.gecko.driver", "E:\\ServerCode\\geckodriver.exe");
			_driver = new FirefoxDriver();
			_driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS); 

		}
		else if (BROWSER.contentEquals("internetExplorer"))
		{
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();   
			System.setProperty("webdriver.ie.driver", "InputTestData/IEDriverServer.exe");
			_driver = new InternetExplorerDriver(ieCapabilities);
			
	
		}else if (BROWSER.contentEquals("chrome")){
			System.setProperty("webdriver.chrome.driver","E:\\SuiteWorkflow_Selenium\\chromedriver.exe");
			_driver = new ChromeDriver();
			
//				LoggingPreferences loggingprefs = new LoggingPreferences();
//		        loggingprefs.enable(LogType.BROWSER, Level.ALL);
//		        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
//		        
//		        ChromeOptions chromeOptions = new ChromeOptions();
//		        Map<String, Object> prefs = new HashMap<String, Object>();
//        		prefs.put("download.default_directory", DOWNLOADPATH);
//        		prefs.put("download.prompt_for_download", false);
//        		chromeOptions.setExperimentalOption("prefs", prefs);
//        		chromeOptions.addArguments("--disable-pdf-material-ui");
//        		chromeOptions.addArguments("--disable-out-of-process-pdf");
//        		chromeOptions.addArguments("--start-maximized");
//        		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);        		
//		       
//        		_driver = new ChromeDriver(capabilities);
//        		_driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		}
	else if (BROWSER.contentEquals("safari"))
	{
		//SafariOptions options = new SafariOptions();
		// options.setUseCleanSession(true); //if you wish safari to forget session everytime
		// _driver = new SafariDriver(options); 
	       
		//_driver = new ChromeDriver(capabilities);
		
	}
	else
	{
		System.err.println("Invalid Browser name provided");
	}

		// --- choose one ------------------

		// _driver = new HtmlUnitDriver(true);

		_driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	/**
	 * Build up the URL via the properties file.
	 * 
	 * @return
	 */
	public static String getUrl() {
		// TODO: EY: Should this be done in the constructor instead?
		if (URL == null) {
			try {
				URL = _properties
						.getProperty(GUI_automation_properties.BASEURL);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			assert URL.contains("http"); // should look like a URL
		}
		return URL;
	}
	
	public static String getDSFUrl() {
		// TODO: EY: Should this be done in the constructor instead?
		if (DSFURL == null) {
			try {
				DSFURL = _properties
						.getProperty(GUI_automation_properties.DSFURL);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			assert DSFURL.contains("http"); // should look like a URL
		}
		return DSFURL;
	}
	public static String MobileStatus() {
		// TODO: EY: Should this be done in the constructor instead?
		if (Mobile == null) {
			try {
				Mobile = _properties
						.getProperty(GUI_automation_properties.Mobile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			assert Mobile.contains("false"); // should look like a URL
		}
		return URL;
	}
	/**
	 * Build up the BROWSER via the properties file.
	 * 
	 * @return
	 */
	public static String getBrowser() {
		// TODO: EY: Should this be done in the constructor instead?
		if (BROWSER == null) {
			try {
				BROWSER = _properties
						.getProperty(GUI_automation_properties.BROWSER);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return BROWSER;
	}

}
