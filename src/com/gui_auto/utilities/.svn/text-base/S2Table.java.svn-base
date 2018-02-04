package com.gui_auto.utilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.internal.seleniumemulation.AlertOverride;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.gui_auto.base_classes.GUI_automation_base;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.util.Set;
import java.util.List;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class S2Table extends  GUI_automation_base {
	

	public static  int getNumberOfRowsInTable(String tableId,WebDriver driver) {
		
		return driver.findElement(By.id(tableId)).findElements(By.xpath("//tbody/tr")).size();
	}

	public static int getNumberOfRowsInTable(By by,WebDriver driver) {
		return driver.findElement(by).findElements(By.xpath("//tbody/tr")).size();
	}

	public static int getNumberOfColsInTable(By by,WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(0);
		return row.findElements(By.tagName("td")).size();
	}

	public static String getCellText(By by, int rowIndex, int colIndex,WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		return col.getText();
	}

	public static String getCellValue(By by, int rowIndex, int colIndex,WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		return col.getAttribute("value");
	}

	public static String getCellInnerHTML(By by, int rowIndex, int colIndex,WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colIndex);
		System.out.println("HTML: " + col.getAttribute("innerHTML"));
		return col.getAttribute("innerHTML");
	}

	public static int getRowIndexInTable(By by, int colIndex, String query,WebDriver driver) {
		int result = -1;
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		int rowIndex = 0;
		for (WebElement row : rows) {
			if (query.equals(row.findElements(By.tagName("td")).get(colIndex).getText())) {
				result = rowIndex;
				break;
			}
			rowIndex++;
		}

		return result;
	}

	public static WebElement getCellSubElement(By tableBy, int rowIndex, int colIndex, By cellSubElementBy,WebDriver driver) {
		List<WebElement> rows = driver.findElement(tableBy).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		return cols.get(colIndex).findElement(cellSubElementBy);
	}

	public static  void clickCell(By by, int rowIndex, int colIndex,WebDriver driver) {
		List<WebElement> rows = driver.findElement(by).findElements(By.xpath("//tbody/tr"));
		WebElement row = rows.get(rowIndex);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		
		WebElement DemoElement=cols.get(colIndex);
		String xpath=CommonFunctions.getAbsoluteXPath(DemoElement, driver);
		System.out.println(xpath);
		
		try {
			cols.get(colIndex).click();
		} catch (Exception e) {
			
			System.err.println("Unable to click to index element cell is hidden or index out of range");
	
		}
	}
}
