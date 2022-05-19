package com.automation.interview.page.shoppingPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class selectClothes extends Configaration {

	public selectClothes() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[1]")
	private WebElement WomenButton;
	
	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[2]")
	private WebElement DressButton;
	
	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[3]")
	private WebElement TshirtsButton;

	@FindBy(xpath = "//ul[contains(@class,'submenu-container clearfix first-in-line-xs')]")
	private WebElement dropDownCatagory;
	
	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[2]//a[contains(text(), 'Casual Dresses')]")
	private WebElement CasualDressCatagory;
	
	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[2]//a[contains(text(), 'Evening Dresses')]")
	private WebElement EveningDressesCatagory;
	
	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[2]//a[contains(text(), 'Summer Dresses')]")
	private WebElement SummerDressesCatagory;
	
	@FindBy(xpath = "//span[contains(text(),'There is 1 product.')]")
	private WebElement CasualDressCatagoryAssertion;
	
	@FindBy(xpath = "//img[contains(@title,'Printed Dress')]")
	private WebElement SelectCasualDress;
	
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	private WebElement AddToCratButton;
	
	@FindBy(xpath = "//span[contains(@class,'continue btn btn-default button exclusive-medium')]")
	private WebElement ContinueShoppingButton;
	
	@FindBy(xpath = "//span[contains(@class,'ajax_cart_quantity')]")
	private WebElement VeifyQuantityInCart;
	
	@FindBy(xpath = "//i[contains(@class,'icon-chevron-right right')]")
	private WebElement CheckoutButton;
	
	@FindBy(xpath = "//a[contains(@title,'View my shopping cart')]")
	private WebElement ShoppingCart;
	
	@FindBy(xpath = "//dic[contains(@class,'cart_block block exclusive')]")
	private WebElement ShoppingCartBlock;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
	private WebElement ProceedToCheckOut;
	
	@FindBy(xpath = "//textarea[contains(@class,'form-control')]")
	private WebElement EnterMessage;
	
	@FindBy(xpath = "//input[@id='cgv']")
	private WebElement AcceptTandC;
	
	@FindBy(xpath = "//a[@class='bankwire']")
	private WebElement Payment;
	
	@FindBy(xpath = "//button[@type='submit']/span[contains(text(), 'I confirm my order')]")
	private WebElement ConfirmOrder;
	
	@FindBy(xpath = "//h3[contains(text(),'Bank-wire payment.')]")
	private WebElement ValidatePaymentPage;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order on My Store is complete.')]")
	private WebElement ConformationMessage;
	
	@FindBy(xpath = "//button[contains(@class,'button btn btn-default button-medium')]")
	private WebElement AddressProceedToCheckout;
	
	@FindBy(xpath = "//button[contains(@class,'button btn btn-default standard-checkout button-medium')]")
	private WebElement ShippingProceedToCheckout;
	
	@FindBy(xpath = "//h5[contains(@itemprop,'name')]//a[contains(@class,'product-name')]")
	private WebElement ProductName;
	
	@FindBy(xpath = "//div[contains(@class,'right-block')]//span[contains(@class,'price product-price')]")
	private WebElement ProductPrice;
	

	public void DoPayment() {
		Payment.click();
	}
	
	public void clickAddressProceedToCheckout() {
		AddressProceedToCheckout.click();
	}
	
	public void clickShippingProceedToCheckout() {
		ShippingProceedToCheckout.click();
	}
	
	public void acceptTermsAndConditions() {
		AcceptTandC.click();
	}
	
	public void clickConfirmOrder() {
		ConfirmOrder.click();
	}
	
	public WebElement womenCategoryButton() {
		return AutomationBase.waitForElementPresence(driver, WomenButton, 30);
	}
	
	public WebElement validateConformationMessage() {
		return AutomationBase.waitForElementPresence(driver, ConformationMessage, 30);
	}
	
	public WebElement doValidatePayment() {
		return AutomationBase.waitForElementPresence(driver, ValidatePaymentPage, 30);
	}
	
	public WebElement DressCategoryButton() {
		return AutomationBase.waitForElementPresence(driver, DressButton, 30);
	}
	
	public WebElement tShirtsCategoryButton() {
		return AutomationBase.waitForElementPresence(driver, TshirtsButton, 30);
	}
	
	public WebElement displayDropDown() {
		return AutomationBase.waitForElementPresence(driver, dropDownCatagory, 30);
	}
	
	public WebElement casualDressCatagoryAssertion() {
		return AutomationBase.waitForElementPresence(driver, CasualDressCatagoryAssertion, 30);
	}
	
	public WebElement selectCasualDress() {
		return AutomationBase.waitForElementPresence(driver, SelectCasualDress, 30);
	}
	
	public WebElement selectShoppingCart() {
		return AutomationBase.waitForElementPresence(driver, ShoppingCart, 30);
	}
	
	public WebElement selectShoppingCartBlock() {
		return AutomationBase.waitForElementPresence(driver, ShoppingCartBlock, 30);
	}
	
	public WebElement fetchProductName() {
		return AutomationBase.waitForElementPresence(driver, ProductName, 30);
	}
	
	public WebElement fetchPrice() {
		return AutomationBase.waitForElementPresence(driver, ProductPrice, 30);
	}
	
	public void clickCasualDressCatagory() {
		CasualDressCatagory.click();
	}
	
	public void clickProceedToCheckoutButton() {
		ProceedToCheckOut.click();
	}
	
	public void clickAddToCratButton() {
		AddToCratButton.click();
	}
	
	public void clickContinueShoppingButton() {
		ContinueShoppingButton.click();
	}
	
	public void enterMessage(String msg) {
		EnterMessage.clear();
		EnterMessage.sendKeys(msg);
	}
	
	public void clickEveningDressesCatagory() {
		EveningDressesCatagory.click();
	}
	
	public void clickSummerDressesCatagory() {
		SummerDressesCatagory.click();
	}
	
	public void clickCheckOutButton() {
		CheckoutButton.click();
	}
	
	public WebElement veifyCheckOutButton() {
		return AutomationBase.waitForElementPresence(driver, CheckoutButton, 30);
	}
	
	public WebElement veifyQuantityInCart() {
		return AutomationBase.waitForElementPresence(driver, VeifyQuantityInCart, 30);
	}
	
}
