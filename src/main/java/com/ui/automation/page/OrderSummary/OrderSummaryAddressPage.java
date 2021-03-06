package com.ui.automation.page.OrderSummary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ui.automation.utill.AutomationBase;
import com.ui.automation.utill.Configuration;

public class OrderSummaryAddressPage extends Configuration {
	
	public OrderSummaryAddressPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[contains(@id,'address_delivery')]//li[contains(@class,'address_firstname address_lastname')]")
	private WebElement FiestNameLastName;

	@FindBy(xpath = "//ul[contains(@id,'address_delivery')]//li[contains(@class,'address_address1 address_address2')]")
	private WebElement HomeAdress;

	
	@FindBy(xpath = "//ul[contains(@id,'address_delivery')]//li[contains(@class,'address_city address_state_name address_postcode')]")
	private WebElement CityNameStateName;
	
	@FindBy(xpath = "//p[contains(text(),'You must agree to the terms of service before cont')]")
	private WebElement TermsandConditionsErrorMessage;

	@FindBy(xpath = "//a[contains(@class,'fancybox-item fancybox-close')]")
	private WebElement CloseIcon;

	
	public WebElement fetchFirstNameLastName() {
		return AutomationBase.waitForElementPresence(driver, FiestNameLastName, 30);
	}

	public WebElement fetchHomeAdress() {
		return AutomationBase.waitForElementPresence(driver, HomeAdress, 30);
	}
	
	public WebElement fetchCityNameStateName() {
		return AutomationBase.waitForElementPresence(driver, CityNameStateName, 30);
	}
	
	public WebElement orderSummaryAddress() {
		return AutomationBase.waitForElementPresence(driver, TermsandConditionsErrorMessage, 30);
	}

	public void clickCloseIcon() {
		CloseIcon.click();
	}

}
