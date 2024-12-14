
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
public class signinflowtests extends Initialzer{
	HelperMethods helpermethods = new HelperMethods(log);
	public String email = helpermethods.generateRandomWord(6)+"@"+helpermethods.generateRandomWord(4)+".com";
	public String password = helpermethods.generateRandomWord(4) + "@Aa123";
	
	
	@Test
	public void TC12_EnterNonExistingUser() {
		String testCaseID = "TC12";
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
			this.customTestAssertions.assertTrue(signinpage.verifyUserNotExist(), 
					"User not exist error is displayed");
			
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
	public void TC13_EnterInValidMailInSignInPage() {
		String testCaseID = "TC13";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signinpage signinpage = homepageClass.navigateToSignIn();
			this.customTestAssertions.assertTrue(signinpage.enterMail("aazzaa#gg.com"), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signinpage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signinpage.clickSignIn(), 
					"Singned the account");
			this.customTestAssertions.assertTrue(signinpage.verifyMailNotValidError(), 
					"Mail is not valid error is displayed");
			
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
	public void TC14_EnterWrongPasswordInSignInPage() {
		String testCaseID = "TC14";
		try {
			homepage homepageClass = new homepage(driver, log);
			homepageClass.verifyHomePageLoaded();
			signinpage signinpage = homepageClass.navigateToSignIn();
			//Account is already created with user: asia@india.com
			this.customTestAssertions.assertTrue(signinpage.enterMail("asia@india.com"), 
					"Entered the username");
			this.customTestAssertions.assertTrue(signinpage.enterPassword(password), 
					"Entered the password");
			this.customTestAssertions.assertTrue(signinpage.clickSignIn(), 
					"Singned the account");
			this.customTestAssertions.assertTrue(signinpage.verifyPasswordInCorrect(), 
					"Password is not valid error is displayed");
			
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
