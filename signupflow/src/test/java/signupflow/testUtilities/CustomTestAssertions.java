package signupflow.testUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import signupflow.utilities.*;
import signupflow.utilities.Reports.TestStatus;

import com.aventstack.extentreports.ExtentTest;

public class CustomTestAssertions extends Assertion{
	private WebDriver driver = null;
	private Reports reportObj = null;
	private Log log = null;
	private ExtentTest currentRunningTest = null;

	/**
	 * Initializes a new instance of CustomTestAssertions class.
	 * 
	 * @param driver             Driver to use
	 * @param reportObj          Reporting object to use for logging statements to
	 *                           report
	 * @param log                Logging object to use for logging statements to log
	 *                           file
	 * @param currentRunningTest Current running test
	 */
	public CustomTestAssertions(WebDriver driver, Reports reportObj, Log log, ExtentTest currentRunningTest) {
		this.driver = driver;
		this.reportObj = reportObj;
		this.log = log;
		this.currentRunningTest = currentRunningTest;
	}

	/**
	 * Method to run on a successful assertion
	 */
	@Override
	public void onAssertSuccess(IAssert<?> a) {
		try {
			this.reportObj.reportLogger(this.driver, this.currentRunningTest, a.getMessage(), TestStatus.Pass);
		} catch (Exception e) {
			this.log.logStatements("Error in onAssertSuccess custom method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}

	/**
	 * Method to run on a assertion failure
	 */
	@Override
	public void onAssertFailure(IAssert<?> a, AssertionError ex) {
		try {
			this.reportObj.reportLogger(this.driver, this.currentRunningTest, "\"" + a.getMessage()
					+ "\" step failed. Expected was: " + a.getExpected() + " Actual is: " + a.getActual(), TestStatus.Fail);
			this.log.logStatements("\"" + a.getMessage() + "\" step failed. Expected was: " + a.getExpected()
					+ " Actual is: " + a.getActual(), ex.getClass().getCanonicalName(), ex.getMessage(),
					ex.getStackTrace());
		} catch (Exception e) {
			this.log.logStatements("Error in onAssertFailure custom method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}
}
