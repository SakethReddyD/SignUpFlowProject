package signupflow.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.SkipException;

import signupflow.pages.homepage.homepage;
import signupflow.pages.loggedinpage.loggedinpage;
import signupflow.pages.signinpage.signinpage;
import signupflow.pages.signuppage.signuppage;
import signupflow.testUtilities.CustomTestListeners;
import signupflow.testinitializer.Initialzer;
import signupflow.utilities.HelperMethods;
import signupflow.utilities.Reports.TestStatus;

@Listeners(CustomTestListeners.class)
public class signupflowtests extends Initialzer{
	HelperMethods helpermethods = new HelperMethods(log);
	public String email = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
	public String firstName = helpermethods.generateRandomWord(8);
	public String lastName = helpermethods.generateRandomWord(6);
	public String fullName = firstName + " " + lastName;
	public String password = helpermethods.generateRandomWord(4) + "@Aa123";
	
	@Test
	public void TC1_CreateAccount() {
		String testCaseID = "TC1";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(email), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
//			String fullname = testData.get("firstName")+" "+testData.get("lastName");
			loggedinpage loggedinpage = new loggedinpage(driver, log);
			this.customTestAssertions.assertTrue(loggedinpage.verifyAccountCreated(fullName), 
					"Account is created successfully");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC2_SignInToAccount() {
		String testCaseID = "TC2";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signinpage signinpage = homepageClass.navigateToSignIn();
			this.customTestAssertions.assertTrue(signinpage.enterMail(email), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signinpage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signinpage.clickSignIn(), 
					"Singned the account");
			String fullname = testData.get("firstName")+" "+testData.get("lastName");
			loggedinpage loggedinpage = new loggedinpage(driver, log);
			this.customTestAssertions.assertTrue(loggedinpage.verifyCustomerLogged(fullName), 
					"Customer logged in successfully");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC3_EnterInvalidNames() {
		String testCaseID = "TC3";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			//Both names are invalid
			this.customTestAssertions.assertTrue(signuppage.enterFirstName("12ss@"), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName("32@dddd"), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyFirstNameNotValidError(), 
					"First Name not valid error is displayed");
			this.customTestAssertions.assertTrue(signuppage.verifyLastNameNotValidError(), 
					"Last Name not valid error is displayed");
			//firstname is invalid
			this.customTestAssertions.assertTrue(signuppage.enterFirstName("12ss@"), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyFirstNameNotValidError(), 
					"First Name not valid error is displayed");
			this.customTestAssertions.assertFalse(signuppage.verifyLastNameNotValidError(), 
					"Last Name not valid error is not displayed");
			//lastname is invalid
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName("32@dddd"), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertFalse(signuppage.verifyFirstNameNotValidError(), 
					"First Name not valid error is not displayed");
			this.customTestAssertions.assertTrue(signuppage.verifyLastNameNotValidError(), 
					"Last Name not valid error is displayed");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC4_NoNamesEntered() {
		String testCaseID = "TC4";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			//firstname is empty
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyRequiredFieldError(), 
					"Required field error is displayed");
			//lastname is empty
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(lastName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(""), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC5_EnterExistingMailID() {
		String testCaseID = "TC5";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			//following user already exists, hence hard coded the value
			this.customTestAssertions.assertTrue(signuppage.enterMail("dedeehtht@vrvrrbr.com"), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyMailExistsError(), 
					"Mail already exists error is displayed");
			
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC6_EnterInvalidMail() {
		String testCaseID = "TC6";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail("dedeehtht#vrvrrbr.com"), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyMailNotValidError(), 
					"Mail invalid error is displayed");
			
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC7_NoMailEntered() {
		String testCaseID = "TC7";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyRequiredFieldError(), 
					"Required field error is displayed");
			
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC8_EnterInvalidPassword() {
		String testCaseID = "TC8";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			//Password of length less than 8 chars is entered
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword("12345ab"), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword("12345ab"), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyPasswordLengthNotValidError(), 
					"Password length is not matched error is displayed");
			//Password of all combinations is not entered
			this.customTestAssertions.assertTrue(signuppage.enterPassword("12345abcd"), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword("12345abcd"), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyPasswordNotValidError(), 
					"Password is not valid error is displayed");
			this.customTestAssertions.assertTrue(signuppage.verifyWeakPasswordError(), 
					"Password is weak error is displayed");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC9_EnterMismatchedPassword() {
		String testCaseID = "TC9";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword("12345ab"), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyPasswordNotMatchedError(), 
					"Password is not matched error is displayed");
			
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC10_PasswordNotEntered() {
		String testCaseID = "TC10";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signuppage signuppage = homepageClass.navigateToCreateAccount();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			this.customTestAssertions.assertTrue(signuppage.verifyNoPasswordError(), 
					"Password is not entered error is displayed");
			
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	@Test
	public void TC11_CreateAccountFromSignInPage() {
		String testCaseID = "TC11";
		try {
			String emailID = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signinpage signinpage = homepageClass.navigateToSignIn();
			signuppage signuppage = signinpage.navigateToCreateAccountFromSignInPage();
			this.customTestAssertions.assertTrue(signuppage.enterFirstName(firstName), 
					"Entered the first name");
			this.customTestAssertions.assertTrue(signuppage.enterLastName(lastName), 
					"Entered the last name");
			this.customTestAssertions.assertTrue(signuppage.enterMail(emailID), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signuppage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signuppage.confirmPassword(password), 
					"Entered the confirm password");
			this.customTestAssertions.assertTrue(signuppage.createAccount(), 
					"Created the account");
			loggedinpage loggedinpage = new loggedinpage(driver, log);
			this.customTestAssertions.assertTrue(loggedinpage.verifyAccountCreated(fullName), 
					"Account is created successfully");
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has passed.",
					TestStatus.Pass);
		} catch (AssertionError e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " has failed.",
					TestStatus.Fail);
			Assert.fail();
		} catch (Exception e) {
			reports.reportLogger(this.driver, this.currentRunningTest, "Test case: " + testCaseID + " was skipped.",
					TestStatus.Skip);
			throw new SkipException("Skipping test case: " + testCaseID);
		}
	}
	
	
	
	
	
}
