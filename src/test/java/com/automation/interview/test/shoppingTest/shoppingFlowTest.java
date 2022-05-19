package com.automation.interview.test.shoppingTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.interview.ReadXML.DataBean;
import com.automation.interview.ReadXML.ReadXmlData;
import com.automation.interview.page.OrderSummary.OrderSummaryAddressPage;
import com.automation.interview.page.OrderSummary.OrderSummarypage;
import com.automation.interview.page.SignIn.SignInPage;
import com.automation.interview.page.registration.RegistrationPage;
import com.automation.interview.page.shoppingPage.selectClothes;
import com.automation.interview.utill.Configaration;

public class shoppingFlowTest extends Configaration {

	private static final String LoginDataFile = "SignInData.xml";
	private static final String RegisterDataFile = "RegisterAUser.xml";
	private DataBean passsword;
	private DataBean lastName;
	private DataBean firstName;
	private DataBean address;
	private DataBean zipCode;
	private DataBean city;
	private DataBean state;
	private Actions action;
	RegistrationPage rp;
	OrderSummarypage orderSummary;
	OrderSummaryAddressPage orderSummaryAddress;
	SignInPage signInPage;
	selectClothes selectClothesPage;
	String selectedDress;
	String selectedDressPrice;
	String totalPrice;

	@BeforeClass
	public void setUp() throws Exception {
		getDriver();
		signInPage = new SignInPage();
		rp = new RegistrationPage();
		orderSummary = new OrderSummarypage();
		action = new Actions(driver);
		selectClothesPage = new selectClothes();
		orderSummaryAddress = new OrderSummaryAddressPage();
		ReadXmlData inputData = new ReadXmlData(LoginDataFile);
		ReadXmlData registerPageInputData = new ReadXmlData(RegisterDataFile);
		lastName = registerPageInputData.getDataBean("CreateAccountForm", "lastName");
		firstName = registerPageInputData.getDataBean("CreateAccountForm", "firstname");
		address = registerPageInputData.getDataBean("CreateAccountForm", "address");
		zipCode = registerPageInputData.getDataBean("CreateAccountForm", "zipcode");
		city = registerPageInputData.getDataBean("CreateAccountForm", "city");
		state = registerPageInputData.getDataBean("CreateAccountForm", "state");
		passsword = inputData.getDataBean("Login", "password");
	}

	@AfterClass
	public void close() throws Exception {
		driver.close();
	}

	@Test(priority = 1)
	public void selectClothesFlow() {
		rp.signInButton();
		signInPage.enterEmail(Configaration.getUserName());
		signInPage.enterPassword(passsword.getValue());
		signInPage.clickLoginButton();
		Assert.assertTrue(signInPage.validateSucessLogin().isDisplayed());
		// Validate options Visbale after hovering on Women category tab
		action.moveToElement(selectClothesPage.womenCategoryButton()).perform();
		Assert.assertTrue(selectClothesPage.displayDropDown().isDisplayed());
		// Validate options Visbale after hovering on dress category tab
		action.moveToElement(selectClothesPage.DressCategoryButton()).perform();
		Assert.assertTrue(selectClothesPage.displayDropDown().isDisplayed());

		action.moveToElement(selectClothesPage.womenCategoryButton()).perform();
		selectClothesPage.clickCasualDressCatagory();
		Assert.assertTrue(selectClothesPage.casualDressCatagoryAssertion().isDisplayed());
		selectedDress = selectClothesPage.fetchProductName().getText();
		selectedDressPrice = selectClothesPage.fetchPrice().getText();
		action.moveToElement(selectClothesPage.selectCasualDress()).perform();
		selectClothesPage.clickAddToCratButton();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		selectClothesPage.clickContinueShoppingButton();
		String quntity = selectClothesPage.veifyQuantityInCart().getText();
		Assert.assertEquals(quntity, "1");

	}
	
	@Test(priority = 2)
	public void validateCart() {
		action.moveToElement(selectClothesPage.selectShoppingCart()).perform();
		Assert.assertTrue(selectClothesPage.veifyCheckOutButton().isDisplayed());
		selectClothesPage.clickCheckOutButton();
	}
	
	@Test(priority = 3)
	public void validateCartSummary() {
		String orderSumamryProductName = orderSummary.fetchProductName().getText();
		Assert.assertEquals(orderSumamryProductName, selectedDress);
		String orderSumamryProductPrice = orderSummary.fetchProductPrice().getText();
		Assert.assertEquals(orderSumamryProductPrice, selectedDressPrice);
		selectClothesPage.clickProceedToCheckoutButton();		
		//totalPrice = orderSummary.fetchTotalPrice().getText();
	}
	
	@Test(priority = 4)
	public void validateOrderDeliveryAddress() {
		String firstNameLastName = orderSummaryAddress.fetchFirstNameLastName().getText();
		Assert.assertEquals(firstNameLastName, firstName.getValue()+" "+lastName.getValue());
		
		String summaryAddress= orderSummaryAddress.fetchHomeAdress().getText();
		Assert.assertEquals(summaryAddress, address.getValue());
		
		String cityName= orderSummaryAddress.fetchCityNameStateName().getText();
		Assert.assertEquals(cityName, city.getValue()+", "+state.getValue()+" "+zipCode.getValue());
		selectClothesPage.enterMessage("Ordering my fav");
		selectClothesPage.clickAddressProceedToCheckout();		
	}
	
	@Test(priority = 5)
	public void validateUncheckedTermsandCondition() throws InterruptedException {
		selectClothesPage.clickShippingProceedToCheckout();
		Assert.assertTrue(orderSummaryAddress.orderSummaryAddress().isDisplayed());
		orderSummaryAddress.clickCloseIcon();
	}
	
	@Test(priority = 6)
	public void validateWithcheckedTermsandCondition() throws InterruptedException {
		selectClothesPage.acceptTermsAndConditions();
		selectClothesPage.clickShippingProceedToCheckout();
	}
	
	@Test(priority = 7)
	public void validatePayment() throws InterruptedException {
		selectClothesPage.DoPayment();
		String summaryPrice = orderSummary.fetchSummaryPrice().getText();
		//Assert.assertEquals(summaryPrice, totalPrice);
		Assert.assertTrue(selectClothesPage.doValidatePayment().isDisplayed());
		selectClothesPage.clickConfirmOrder();
		Assert.assertTrue(selectClothesPage.validateConformationMessage().isDisplayed());
	}
	

	@Test(enabled = false)
	public void processSucessFullCheckout() {
		action.moveToElement(selectClothesPage.selectShoppingCart()).perform();
		selectClothesPage.clickCheckOutButton();
		selectClothesPage.clickProceedToCheckoutButton();
		selectClothesPage.enterMessage("Ordering my fav");
		selectClothesPage.clickAddressProceedToCheckout();
		selectClothesPage.acceptTermsAndConditions();
		selectClothesPage.clickShippingProceedToCheckout();
		selectClothesPage.DoPayment();
		Assert.assertTrue(selectClothesPage.doValidatePayment().isDisplayed());
		selectClothesPage.clickConfirmOrder();
		Assert.assertTrue(selectClothesPage.validateConformationMessage().isDisplayed());
	}
}