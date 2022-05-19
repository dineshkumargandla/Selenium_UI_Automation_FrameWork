package com.automation.interview.page.registration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class CreateAccountPage extends Configaration {

	public CreateAccountPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[contains(@id,'customer_firstname')]")
	private WebElement CustomerFirstName;

	@FindBy(xpath = "//input[contains(@id,'customer_lastname')]")
	private WebElement CustomerLastName;

	@FindBy(xpath = "//input[contains(@id,'passwd')]")
	private WebElement Password;

	@FindBy(xpath = "//input[contains(@id,'address1')]")
	private WebElement Address;

	@FindBy(xpath = "//input[contains(@id,'email')]")
	private WebElement EmailInputFeild;

	@FindBy(xpath = "//input[contains(@id,'postcode')]")
	private WebElement ZipCodeInputFeild;

	@FindBy(xpath = "//input[contains(@id,'phone')]")
	private WebElement HomePhoneInputFeild;

	@FindBy(xpath = "//input[contains(@id,'phone_mobile')]")
	private WebElement MobilePhoneInputFeild;

	@FindBy(xpath = "//button[@id='submitAccount']")
	private WebElement RegisterButton;

	@FindBy(xpath = "//p[contains(text(),'There are 8 errors')]")
	private WebElement ReturingAllErrorMessages;

	@FindBy(xpath = "//li[contains(text(), ' is invalid.')]/b[contains(text(), 'email')]")
	private WebElement InvalidEmailErrorMessages;

	@FindBy(xpath = "//li[contains(text(), ' is invalid.')]/b[contains(text(), 'passwd')]")
	private WebElement InvalidPasswordErrorMessages;

	@FindBy(xpath = "//li[contains(text(), ' is invalid.')]/b[contains(text(), 'phone')]")
	private WebElement InvalidHomePhoneErrorMessages;

	@FindBy(xpath = "//li[contains(text(), ' is invalid.')]/b[contains(text(), 'phone_mobile')]")
	private WebElement InvalidMobilePhoneErrorMessages;

	@FindBy(xpath = "//li[contains(text(), 'The Zip/Postal code you')]")
	private WebElement InvalidZipCodeErrorMessages;

	@FindBy(xpath = "//select[@id='days']")
	private WebElement DateDropDown;

	@FindBy(xpath = "//select[@id='months']")
	private WebElement MonthDropDown;

	@FindBy(xpath = "//select[@id='years']")
	private WebElement YearDropDown;

	@FindBy(xpath = "//input[@id='city']")
	private WebElement City;

	@FindBy(xpath = "//select[@id='id_state']")
	private WebElement State;

	@FindBy(xpath = "//input[@id='alias']")
	private WebElement AddressAlias;

	public void enterFirstName(String firstName) {
		CustomerFirstName.clear();
		CustomerFirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		CustomerLastName.clear();
		CustomerLastName.sendKeys(lastName);
	}

	public void enterPassword(String password) {
		Password.clear();
		Password.sendKeys(password);
	}

	public void enterZipCode(String zipCode) {
		ZipCodeInputFeild.clear();
		ZipCodeInputFeild.sendKeys(zipCode);
	}

	public void enterAddress(String address) {
		Address.clear();
		Address.sendKeys(address);
	}

	public void enterEmail(String email) {
		EmailInputFeild.clear();
		EmailInputFeild.sendKeys(email);
	}

	public void enterHomePhone(String homePhone) {
		HomePhoneInputFeild.clear();
		HomePhoneInputFeild.sendKeys(homePhone);
	}

	public void enterMobilePhone(String mobilePhone) {
		MobilePhoneInputFeild.clear();
		MobilePhoneInputFeild.sendKeys(mobilePhone);
	}

	public void enterPasswordName(String firstName) {
		CustomerFirstName.sendKeys(firstName);
	}

	public void clickRegisterButton() {
		AutomationBase.waitForElementPresence(driver, RegisterButton, 15);
		RegisterButton.click();
	}

	public void selectDoBDate(String date) {
		Select se = new Select(DateDropDown);
		se.selectByVisibleText(date);
	}

	public void selectDoBMonth(String month) {
		Select se = new Select(MonthDropDown);
		se.selectByVisibleText(month);
	}

	public void selectDoBYear(String year) {
		Select se = new Select(YearDropDown);
		se.selectByVisibleText(year);
	}

	public void enterCity(String city) {
		City.sendKeys(city);
	}

	public void enterAddressAlias(String addressAlias) {
		AddressAlias.sendKeys(addressAlias);
	}

	public void selectState(String state) {
		Select se = new Select(State);
		se.selectByVisibleText(state);
	}

	public WebElement behaviorOfFirstNameTextBox() {
		return AutomationBase.waitForElementPresence(driver, CustomerFirstName, 30);
	}

	public WebElement behaviorOfLastNameTextBox() {
		return AutomationBase.waitForElementPresence(driver, CustomerLastName, 30);
	}

	public WebElement behaviorOfPasswordTextBox() {
		return AutomationBase.waitForElementPresence(driver, Password, 30);
	}

	public WebElement returingAllErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, ReturingAllErrorMessages, 30);
	}

	public WebElement returingInvalidEmailErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, InvalidEmailErrorMessages, 30);
	}

	public WebElement returingInvalidPasswordErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, InvalidPasswordErrorMessages, 30);
	}

	public WebElement returingInvalidHomePhoneErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, InvalidHomePhoneErrorMessages, 30);
	}

	public WebElement returingInvalidMobilePhoneErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, InvalidMobilePhoneErrorMessages, 30);
	}

	public WebElement returingInvalidZipCodeErrorMessages() {
		return AutomationBase.waitForElementPresence(driver, InvalidZipCodeErrorMessages, 30);
	}

}
