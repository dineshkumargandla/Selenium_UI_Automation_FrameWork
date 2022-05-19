package com.ui.automation.test.ContactUs;

import com.ui.automation.page.ContactUs.contactUsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ui.automation.ReadXML.DataBean;
import com.ui.automation.ReadXML.ReadXmlData;
import com.ui.automation.utill.Configaration;

public class ContactUsTest extends Configaration{
	private static final String ContactUsDataFile = "ContactUs.xml";
	private DataBean emailId;
	private DataBean orderReference;
	private DataBean message;
	contactUsPage contactUs;
	
	@BeforeClass
	public void setUp() throws Exception {
		getDriver();
		contactUs = new contactUsPage();
		ReadXmlData inputData = new ReadXmlData(ContactUsDataFile);
		emailId = inputData.getDataBean("contactUs", "email");
		orderReference = inputData.getDataBean("contactUs", "orderreference");
		message = inputData.getDataBean("contactUs", "message");
		contactUs.clickContactUsButton();
		}
	
	@AfterClass
	public void close() throws Exception {
		driver.close();
	}
	
	@Test(priority = 1)
	private void validateWithEmptyFeilds() throws InterruptedException {
		contactUs.clickSubmitButton();
		Assert.assertTrue(contactUs.checkInvalidErrorMessage().isDisplayed());
	}
	
	@Test(priority = 2)
	private void validateWithEmptyMessage() throws InterruptedException {
		
		contactUs.selectSubjectHeading("Customer service");
		contactUs.enterEmail(emailId.getValue());
		contactUs.enterOrderReference(orderReference.getValue());
		contactUs.clickSubmitButton();
		Assert.assertTrue(contactUs.checkEmptyMessageErrorMessage().isDisplayed());
	}
	
	@Test(priority = 3)
	private void validateWithEmptySubject() throws InterruptedException {
		contactUs.selectSubjectHeading("-- Choose --");
		contactUs.enterEmail(emailId.getValue());
		contactUs.enterOrderReference(orderReference.getValue());
		contactUs.enterMessage(message.getValue());
		contactUs.clickSubmitButton();
		Assert.assertTrue(contactUs.checkEmptySubjectErrorMessage().isDisplayed());
	}
	@Test(priority = 4)
	private void validateContactUsSucessSenario() throws InterruptedException {
		
		contactUs.clickContactUsButton();
		contactUs.selectSubjectHeading("Customer service");
		contactUs.enterEmail(emailId.getValue());
		contactUs.enterOrderReference(orderReference.getValue());
		contactUs.enterMessage(message.getValue());
		contactUs.clickSubmitButton();
		Assert.assertTrue(contactUs.checkSucessMessage().isDisplayed());
	}

	
}
