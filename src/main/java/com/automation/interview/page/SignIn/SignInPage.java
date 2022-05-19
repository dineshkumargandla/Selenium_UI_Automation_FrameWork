package com.automation.interview.page.SignIn;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class SignInPage  extends Configaration {

	public SignInPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement EmailAddress;
	
	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement Password;
	
	@FindBy(xpath = "//button[@id='SubmitLogin']")
	private WebElement LoginButton;

	@FindBy(xpath = "//li[contains(text(),'An email address required.')]")
	private WebElement EmailAddressRequriedErrorMessage;
	
	@FindBy(xpath = "//li[contains(text(),'Password is required.')]")
	private WebElement PasswordRequriedErrorMessage;
	
	@FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
	private WebElement InvalidEmailErrorMessage;
	
	@FindBy(xpath = "//li[contains(text(),'Invalid password.')]")
	private WebElement InvalidPassword;
	
	@FindBy(xpath = "//li[contains(text(),'Authentication failed.')]")
	private WebElement AuthenticationFailed;
	
	@FindBy(xpath = "//a[contains(text(),'Contact us')]")
	private WebElement ContactUs;
	
	@FindBy(xpath = "//span[contains(text(),'Order history and details')]")
	private WebElement DashboardPage;
	
	
	public void enterEmail(String email) {
		EmailAddress.clear();
		EmailAddress.sendKeys(email);
	}

	public void enterPassword(String password) {
		Password.clear();
		Password.sendKeys(password);
	}
	
	public void clickLoginButton() {
		LoginButton.click();
	}
	
	public void clickContactUSButton() {
		ContactUs.click();
	}
	
	public WebElement emailRequriedErrorMassage() {
		return	AutomationBase.waitForElementPresence(driver, EmailAddressRequriedErrorMessage, 30);
		
	}
	
	public WebElement passwordRequriedErrorMassage() {
		return	AutomationBase.waitForElementPresence(driver, PasswordRequriedErrorMessage, 30);
	}
	
	public WebElement invalidEmailErrorMassage() {
		return	AutomationBase.waitForElementPresence(driver, InvalidEmailErrorMessage, 30);
	}
	
	public WebElement authenticationFailedErrorMassage() {
		return	AutomationBase.waitForElementPresence(driver, AuthenticationFailed, 30);
	}
	
	public WebElement validateSucessLogin() {
		return	AutomationBase.waitForElementPresence(driver, DashboardPage, 30);
	}
	
	
}
