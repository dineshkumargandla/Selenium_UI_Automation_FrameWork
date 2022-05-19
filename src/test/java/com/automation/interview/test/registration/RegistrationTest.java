package com.automation.interview.test.registration;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.interview.ReadXML.DataBean;
import com.automation.interview.ReadXML.ReadXmlData;
import com.automation.interview.page.registration.CreateAccountPage;
import com.automation.interview.page.registration.RegistrationPage;
import com.automation.interview.utill.Configaration;

public class RegistrationTest extends Configaration {

	private static final String RegisterDataFile = "RegisterAUser.xml";
	private DataBean inValidEmailId;
	private DataBean signPageAssertaion;
	private DataBean registeredEmail;
	private DataBean lastName;
	private DataBean firstName;
	private DataBean password;
	private DataBean address;
	private DataBean zipCodeInvalid;
	private DataBean homePhoneInvalid;
	private DataBean mobilePhoneInvalid;
	private DataBean passwordInvalid;
	private DataBean zipCode;
	private DataBean dobYear;
	private DataBean dobMonth;
	private DataBean dobDay;
	private DataBean city;
	private DataBean state;
	private DataBean mobilePhone;
	private DataBean addressAlias;
	RegistrationPage rp;
	CreateAccountPage createAccount;

	@BeforeClass
	public void setUp() throws Exception {
		getDriver();
		rp = new RegistrationPage();
		createAccount = new CreateAccountPage();
		ReadXmlData inputData = new ReadXmlData(RegisterDataFile);
		signPageAssertaion = inputData.getDataBean("Assertions", "signpageassertaion");
		inValidEmailId = inputData.getDataBean("CreateAccount", "emailid");
		registeredEmail = inputData.getDataBean("CreateAccount", "registeredemail");
		lastName = inputData.getDataBean("CreateAccountForm", "lastName");
		firstName = inputData.getDataBean("CreateAccountForm", "firstname");
		password = inputData.getDataBean("CreateAccountForm", "password");
		address = inputData.getDataBean("CreateAccountForm", "address");
		zipCodeInvalid = inputData.getDataBean("CreateAccountForm", "zipcodeinvalid");
		homePhoneInvalid = inputData.getDataBean("CreateAccountForm", "homephoneinvalid");
		mobilePhoneInvalid = inputData.getDataBean("CreateAccountForm", "mobilephoneinvalid");
		passwordInvalid = inputData.getDataBean("CreateAccountForm", "passwordinvalid");
		zipCode = inputData.getDataBean("CreateAccountForm", "zipcode");
		dobYear = inputData.getDataBean("CreateAccountForm", "dobyear");
		dobMonth = inputData.getDataBean("CreateAccountForm", "dobmonth");
		dobDay = inputData.getDataBean("CreateAccountForm", "dobday");
		city = inputData.getDataBean("CreateAccountForm", "city");
		state = inputData.getDataBean("CreateAccountForm", "state");
		mobilePhone = inputData.getDataBean("CreateAccountForm", "mobilephone");
		addressAlias = inputData.getDataBean("CreateAccountForm", "addressalias");
		rp.chekforElementPrecence();
		rp.signInButton();
		
	}

	@AfterClass
	public void close() throws Exception {
		driver.close();
	}

	@Test(priority = 1)
	private void validateSignInPage() throws InterruptedException {
		String authenticationPage = rp.autheticationText();
		Assert.assertEquals(authenticationPage, signPageAssertaion.getAssertValue());
	}

	// Passing the Invalid Email Id
	// Testing the CreateAccount Account Section
	@Test(priority = 2)
	private void createAnAccount() throws InterruptedException {
		// Passing incorrect Email
		rp.emaiLTextBox(inValidEmailId.getValue());
		rp.createAccountButton();
		String brColorError = rp.highlatedTextFeild().getCssValue("background-color");
		Assert.assertEquals(brColorError, "rgba(255, 241, 242, 1)");
		Assert.assertTrue(rp.inValidEmailErrorMassage().isDisplayed());
		// Passing existing Email ID
		rp.emaiLTextBox(registeredEmail.getValue());
		rp.createAccountButton();
		String brColor = rp.highlatedTextFeild().getCssValue("background-color");
		Assert.assertEquals(brColor, "rgba(221, 249, 225, 1)");
		Assert.assertTrue(rp.exsistingEmailIdErrorMessage().isDisplayed());
		// Passing a new email ID
		rp.emaiLTextBox(Configaration.getUserName());
		rp.createAccountButton();
		Assert.assertTrue(rp.displayCreateAnAccountForm().isDisplayed());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority = 3)
	private void createAccountWithEmptyFeilds() throws InterruptedException {
		createAccount.enterFirstName("");
		createAccount.enterLastName("");
		createAccount.enterPassword("");
		rp.displayCreateAnAccountForm().click();
		String bgColorFirstNameError = createAccount.behaviorOfFirstNameTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorFirstNameError, "rgba(255, 241, 242, 1)");
		String bgColorLastNameError = createAccount.behaviorOfFirstNameTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorLastNameError, "rgba(255, 241, 242, 1)");
		String bgColorPasswordError = createAccount.behaviorOfPasswordTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorPasswordError, "rgba(255, 241, 242, 1)");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@Test(priority = 4)
	private void verifyingFormateOfTextFeilds() throws InterruptedException {
		createAccount.enterEmail(inValidEmailId.getValue());
		createAccount.enterZipCode(zipCodeInvalid.getValue());
		createAccount.enterHomePhone(homePhoneInvalid.getValue());
		createAccount.enterMobilePhone(mobilePhoneInvalid.getValue());
		createAccount.enterPassword(passwordInvalid.getValue());
		createAccount.clickRegisterButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Assert.assertTrue(createAccount.returingInvalidEmailErrorMessages().isDisplayed());
		Assert.assertTrue(createAccount.returingInvalidPasswordErrorMessages().isDisplayed());
		Assert.assertTrue(createAccount.returingInvalidHomePhoneErrorMessages().isDisplayed());
		Assert.assertTrue(createAccount.returingInvalidMobilePhoneErrorMessages().isDisplayed());
		Assert.assertTrue(createAccount.returingInvalidZipCodeErrorMessages().isDisplayed());
		
	}
	
	@Test(priority = 5)
	private void verifyingTextFeildsWithBasicValidData() throws InterruptedException {
		createAccount.enterFirstName(firstName.getValue());
		createAccount.enterLastName(lastName.getValue());
		createAccount.enterPassword(password.getValue());
		rp.displayCreateAnAccountForm().click();
		String bgColorFirstName = createAccount.behaviorOfFirstNameTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorFirstName, "rgba(221, 249, 225, 1)");
		String bgColorLastName = createAccount.behaviorOfFirstNameTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorLastName, "rgba(221, 249, 225, 1)");
		String bgColorPassword = createAccount.behaviorOfPasswordTextBox().getCssValue("background-color");
		Assert.assertEquals(bgColorPassword, "rgba(221, 249, 225, 1)");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	
	@Test(priority = 6)
	private void createAccountForm() throws InterruptedException {
		createAccount.enterFirstName(firstName.getValue());
		createAccount.enterLastName(lastName.getValue());
		createAccount.enterEmail(Configaration.getUserName());
		createAccount.enterPassword(password.getValue());
		createAccount.selectDoBDate(dobDay.getValue());
		createAccount.selectDoBMonth(dobMonth.getValue());
		createAccount.selectDoBYear(dobYear.getValue());
		createAccount.enterAddress(address.getValue());
		createAccount.enterCity(city.getValue());
		createAccount.selectState(state.getValue());
		createAccount.enterZipCode(zipCode.getValue());
		createAccount.enterMobilePhone(mobilePhone.getValue());
		createAccount.enterHomePhone("");
		createAccount.enterAddressAlias(addressAlias.getValue());
		createAccount.clickRegisterButton();

	}

}
