package signupflow.utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	private ExtentSparkReporter theReporter = null;

	private ExtentReports extentReports = null;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

	private static String testCaseName = "";

	private Log log = null;

	public enum TestStatus {
		Pass, Fail, Info, Skip
	}

	/**
	 * Initializes a new instance of Reports class.
	 * 
	 * @param machineName Machine name on which tests are supposed to be run
	 * @param osVersion   OS version of the machine on which tests are supposed to
	 *                    be run
	 * @param application Application that is being tested
	 */
	public Reports(Log log, String machineName, String osVersion, String application) {
		try {
			this.log = log;
			theReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\testOutput\\RunOutput_"
					+ dateTimeFormatter.format(LocalDateTime.now()) + ".html");
			this.extentReports = new ExtentReports();
			this.extentReports.setSystemInfo("HostName:   ", machineName);
			this.extentReports.setSystemInfo("OS:   ", osVersion);
			this.extentReports.setSystemInfo("Application:   ", application);
			this.extentReports.attachReporter(this.theReporter);
		} catch (Exception e) {
			log.logStatements("Error in Reports CTOR.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}
	}

	/**
	 * Method to get test case name
	 * 
	 * @return test case name
	 */
	public static String getTestCaseName() {
		return testCaseName;
	}

	/**
	 * Method to set test case name
	 * 
	 * @param test name of the test to set
	 */
	public void setTestCaseName(String test) {
		testCaseName = test;
	}

	/**
	 * Method to make the specified test case as an Extent Test
	 * 
	 * @param testCaseName Test case name to be used
	 * @return Extent test
	 */
	public ExtentTest createTest(String testCaseName) {
		ExtentTest test = null;
		try {
			this.setTestCaseName(testCaseName);
			test = this.extentReports.createTest(testCaseName);
		} catch (Exception e) {
			this.log.logStatements("Error in createTest method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return test;
	}

	/**
	 * Method to add statements into the HTML report
	 * 
	 * @param driver       Driver to use for taking screenshot in case of a failure
	 * @param test         Test of type extent test
	 * @param testStepDesc Step details to be logged in the report
	 * @param testStatus   Status of the test
	 */
	public void reportLogger(WebDriver driver, ExtentTest test, String testStepDesc, TestStatus testStatus) {
		try {
			switch (testStatus) {
			case Pass:
				test.log(Status.PASS, testStepDesc);
				break;
			case Fail:
				test.log(Status.FAIL, testStepDesc);
				String scrPath = this.takeScreenshotAndReturnScrPath(driver);
				test.addScreenCaptureFromPath(scrPath);
				break;
			case Info:
				test.log(Status.INFO, testStepDesc);
				break;
			case Skip:
				test.log(Status.SKIP, testStepDesc);
				break;
			}
		} catch (Exception e) {
			this.log.logStatements("Error in reportLogger method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		} finally {
			this.extentReports.flush();
		}
	}

	/**
	 * Method to take screenshot in case of test failure
	 * 
	 * @param driver Driver to be used for taking the screenshot
	 * @return Path of the screenshot
	 */
	public String takeScreenshotAndReturnScrPath(WebDriver driver) {
		String dateValueString = "", scrPath = "";
		File newFile = null, originalFile = null;
		try {
			final String errScrPath = System.getProperty("user.dir") + "\\testOutput\\errorScreenshots\\";
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			originalFile = scrShot.getScreenshotAs(OutputType.FILE);
			dateValueString = dateTimeFormatter.format(LocalDateTime.now());
			newFile = new File(errScrPath, dateValueString + ".png");
			Thread.sleep(1000);
			FileUtils.moveFile(originalFile, newFile);
			scrPath = errScrPath + dateValueString + ".png";
		} catch (Exception e) {
			this.log.logStatements("Error in takeScreenshotAndReturnScrPath method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}

		return scrPath;
	}
}
