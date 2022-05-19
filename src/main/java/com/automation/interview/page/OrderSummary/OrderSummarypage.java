package com.automation.interview.page.OrderSummary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class OrderSummarypage extends Configaration {

	public OrderSummarypage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[contains(@class,'cart_description')]//p[contains(@class,'product-name')]")
	private WebElement ProductName;

	@FindBy(xpath = "//td[contains(@class,'cart_unit')]//span[contains(@class,'price')]")
	private WebElement ProductPrice;
	
	@FindBy(xpath = "//td[contains(@id, 'total_price_container')]//span[@id='total_price']")
	private WebElement TotalPrice;
	
	@FindBy(xpath = "//span[@id='amount']")
	private WebElement SummaryPrice;


	public void fetchProductName1() {
		ProductName.click();
	}

	public WebElement fetchProductName() {
		return AutomationBase.waitForElementPresence(driver, ProductName, 30);
	}

	public WebElement fetchProductPrice() {
		return AutomationBase.waitForElementPresence(driver, ProductPrice, 30);
	}
	
	public WebElement fetchTotalPrice() {
		return AutomationBase.waitForElementPresence(driver, TotalPrice, 30);
	}
	
	public WebElement fetchSummaryPrice() {
		return AutomationBase.waitForElementPresence(driver, SummaryPrice, 30);
	}

}
