package com.automation.interview.page.registration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class RegistrationPage extends Configaration {

	public RegistrationPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	private WebElement signInButton;

	@FindBy(xpath = "//input[@id='email_create']")
	private WebElement emaiLTextBox;

	@FindBy(xpath = "//h1[contains(text(),'Authentication')]")
	private WebElement AuthnticationText;

	@FindBy(xpath = "//button[@id='SubmitCreate']")
	private WebElement creatAccountButton;

	@FindBy(xpath = "//li[contains(text(), 'Invalid email address.')]")
	private WebElement inValidEmailErrorMassage;
	
	@FindBy(xpath = "//li[contains(text(), 'An account using this email')]")
	private WebElement exsistingEmailIdErrorMessage;

	@FindBy(xpath = "//div[@id='center_column']")
	private WebElement createAccountForm;
	
	
	public void chekforElementPrecence() {
		AutomationBase.waitForElementPresence(driver, signInButton, 15);
	}

	public void signInButton() {
		signInButton.click();
	}

	public String autheticationText() {
		return AuthnticationText.getText();
	}

	public void emaiLTextBox(String emailID) {
		emaiLTextBox.clear();
		emaiLTextBox.sendKeys(emailID);
	}

	public void createAccountButton() {
		creatAccountButton.click();
	}

	public WebElement inValidEmailErrorMassage() {
		return	AutomationBase.waitForElementPresence(driver, inValidEmailErrorMassage, 15);
		
	}
	
	public WebElement highlatedTextFeild() {
		return	AutomationBase.waitForElementPresence(driver, emaiLTextBox, 15);		
	}
	
	public WebElement exsistingEmailIdErrorMessage() {
		return	AutomationBase.waitForElementPresence(driver, exsistingEmailIdErrorMessage, 15);		
	}
	
	public WebElement displayCreateAnAccountForm() {
		return	AutomationBase.waitForElementPresence(driver, createAccountForm, 15);		
	}
	
}
