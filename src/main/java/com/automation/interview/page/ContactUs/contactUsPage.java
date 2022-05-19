package com.automation.interview.page.ContactUs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automation.interview.utill.AutomationBase;
import com.automation.interview.utill.Configaration;

public class contactUsPage extends Configaration{

	public contactUsPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//a[contains(text(),'Contact us')]")
	private WebElement ContactUsButton;
	
	@FindBy(xpath = "//select[@id='id_contact']")
	private WebElement SubjectHeading;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement Email;
	
	@FindBy(xpath = "//input[@id='id_order']")
	private WebElement OrderReference;
	
	@FindBy(xpath = "//div[contains(@class,'submit')]/button[contains(@id,'submitMessage')]")
	private WebElement SubmitButton;
	
	@FindBy(xpath = "//textarea[@id='message']")
	private WebElement Message;
	
	@FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
	private WebElement InvalidEmailErrorMessage;
	
	@FindBy(xpath = "//li[contains(text(),'The message cannot be blank.')]")
	private WebElement EmptyMessageFeildErrorMessage;

	@FindBy(xpath = "//li[contains(text(),'Please select a subject from the list provided.')]")
	private WebElement EmptySubjectErrorMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Your message has been successfully sent to our tea')]")
	private WebElement SucessMessage;


	public void clickContactUsButton() {
		ContactUsButton.click();
		
	}
	
	public void selectSubjectHeading(String subjectHeading) {
		Select se = new Select(SubjectHeading);
		se.selectByVisibleText(subjectHeading);
	}
	
	public void enterEmail(String email) {
		Email.clear();
		Email.sendKeys(email);
	}
	
	public void enterOrderReference(String orderReference) {
		OrderReference.clear();
		OrderReference.sendKeys(orderReference);
	}
	
	public void enterMessage(String message) {
		Message.clear();
		Message.sendKeys(message);
	}
	
	public void clickSubmitButton() {
		SubmitButton.click();
		
	}
	public WebElement checkInvalidErrorMessage() {
		return AutomationBase.waitForElementPresence(driver, InvalidEmailErrorMessage, 30);
	}
	
	public WebElement checkEmptyMessageErrorMessage() {
		return AutomationBase.waitForElementPresence(driver, EmptyMessageFeildErrorMessage, 30);
	}
	
	public WebElement checkEmptySubjectErrorMessage() {
		return AutomationBase.waitForElementPresence(driver, EmptySubjectErrorMessage, 30);
	}
	
	public WebElement checkSucessMessage() {
		return AutomationBase.waitForElementPresence(driver, SucessMessage, 30);
	}
}
