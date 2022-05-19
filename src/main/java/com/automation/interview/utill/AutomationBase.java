package com.automation.interview.utill;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class AutomationBase {

	public static WebElement waitForElementPresence(WebDriver driver, WebElement webElement, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval))
				.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}
	
	

}
