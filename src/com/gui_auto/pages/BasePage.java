/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.pages;

import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.GUI_automation_base;



public class BasePage {

	protected WebDriver 	_driver;



	public BasePage() {
		_driver = GUI_automation_base._driver;
		assert(_driver != null);


	}	
}
