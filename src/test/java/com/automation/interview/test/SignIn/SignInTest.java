package com.automation.interview.test.SignIn;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.interview.ReadXML.DataBean;
import com.automation.interview.ReadXML.ReadXmlData;
import com.automation.interview.page.SignIn.SignInPage;
import com.automation.interview.page.registration.CreateAccountPage;
import com.automation.interview.page.registration.RegistrationPage;
import com.automation.interview.utill.Configaration;

public class SignInTest {

	public class RegistrationTest extends Configaration {

		private static final String LoginDataFile = "SignInData.xml";
		private DataBean passsword;
		private DataBean invalidEmail;
		private DataBean invalidPassword;
		private DataBean unRegisteredUser;
		RegistrationPage rp;
		CreateAccountPage createAccount;
		SignInPage signInPage;

		@BeforeClass
		public void setUp() throws Exception {
			getDriver();
			rp = new RegistrationPage();
			signInPage = new SignInPage();
			createAccount = new CreateAccountPage();
			ReadXmlData inputData = new ReadXmlData(LoginDataFile);
			passsword = inputData.getDataBean("Login", "password");
			invalidEmail = inputData.getDataBean("Login", "invalidemail");
			invalidPassword = inputData.getDataBean("Login", "invalidpassword");
			unRegisteredUser = inputData.getDataBean("Login", "unregisterdsuser");
		}

		@AfterClass
		public void close() throws Exception {
			driver.close();
		}

		@Test(priority = 1)
		private void validateSignInPageWithEmptyInput() throws InterruptedException {
			rp.chekforElementPrecence();
			rp.signInButton();

			// Click With empty input text
			signInPage.clickLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertTrue(signInPage.emailRequriedErrorMassage().isDisplayed());

		}

		@Test(priority = 2)
		private void validateSignInPageWithEmptyPasswordInput() throws InterruptedException {

			// Click with password is Empty

			signInPage.enterEmail(Configaration.getUserName());
			signInPage.clickLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertTrue(signInPage.passwordRequriedErrorMassage().isDisplayed());
		}

		@Test(priority = 3)
		private void validateSignInPageWithInvalidEmailID() throws InterruptedException {

			// Enter Invalid EmailID

			signInPage.enterEmail(invalidEmail.getValue());
			signInPage.enterPassword(passsword.getValue());
			signInPage.clickLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertTrue(signInPage.invalidEmailErrorMassage().isDisplayed());
		}

		@Test(priority = 4)
		private void validateSignInPageWithWrongPassword() throws InterruptedException {

			// Enter Invalid Password

			signInPage.enterEmail(Configaration.getUserName());
			signInPage.enterPassword(invalidPassword.getValue());
			signInPage.clickLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertTrue(signInPage.authenticationFailedErrorMassage().isDisplayed());
		}

		@Test(priority = 5)
		private void validateSignInPageWithUnregisteredUser() throws InterruptedException {

			// Enter unRegisteredUser

			signInPage.enterEmail(unRegisteredUser.getValue());
			signInPage.enterPassword(passsword.getValue());
			signInPage.clickLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertTrue(signInPage.authenticationFailedErrorMassage().isDisplayed());
		}

		@Test(priority = 6)
		private void validateSignInPageWithValidCredentails() throws InterruptedException {
			signInPage.enterEmail(Configaration.getUserName());
			signInPage.enterPassword(passsword.getValue());
			signInPage.clickLoginButton();
			Assert.assertTrue(signInPage.validateSucessLogin().isDisplayed());
		}
	}
}
