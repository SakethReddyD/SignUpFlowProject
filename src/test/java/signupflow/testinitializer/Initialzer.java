package signupflow.testinitializer;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;

import signupflow.testUtilities.CustomTestAssertions;
import signupflow.testUtilities.CustomTestListeners;
import signupflow.utilities.*;
import signupflow.utilities.Reports.TestStatus;

public class Initialzer {
	public String[] loginData = null;

	public WebDriver driver = null;

	public HelperMethods helper = null;

	public ExtentTest currentRunningTest = null;

	public static Log log = null;

	public static Reports reports = null;

	public ExcelData dataReader = null;

	public ExcelData testDataReader = null;

	public CustomTestAssertions customTestAssertions = null;

	public CustomTestListeners customTestListeners = null;

	public HashMap<String, String> testData = null;

	private String sheetName = "";

	@BeforeSuite
	public void suiteSetup() {
		try {
			if (log == null) {
				log = new Log();
			}

			this.customTestListeners = new CustomTestListeners();
			this.helper = new HelperMethods(log);
			this.driver = this.helper.OpenBrowserAndLaunchApp(
					this.helper.getPropertyValues("projectConfigs", "browser"),
					this.helper.getPropertyValues("projectConfigs", "URL"));
			if (reports == null) {
				reports = new Reports(log, InetAddress.getLocalHost().getHostName(), System.getProperty("os.name"),
						this.helper.getPropertyValues("projectConfigs", "projectName"));
			}

			testDataReader = new ExcelData(
					System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\TestData.xlsx", log);
			sheetName = "Sheet1";
		} catch (Exception e) {
			log.logStatements("Error in suiteSetup method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}
	}

	@AfterSuite
	public void suiteExit() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

	/**
	 * Method to run before each test
	 * 
	 * @param method Running method which will be automatically passed by JAVA
	 */
	@BeforeMethod
	public void setupBeforeTest(Method method) {
		try {

			if (this.driver == null) {
				this.driver = this.helper.OpenBrowserAndLaunchApp(
						this.helper.getPropertyValues("projectConfigs", "browser"),
						this.helper.getPropertyValues("projectConfigs", "URL"));
			}

			testData = testDataReader.getCellValuesForTheSpecifiedFilter(sheetName, method.getName());
			this.currentRunningTest = reports.createTest(method.getName());
			this.customTestAssertions = new CustomTestAssertions(this.driver, reports, log, this.currentRunningTest);
			reports.reportLogger(this.driver, this.currentRunningTest, "Opened an instance of: "
					+ this.helper.getBrowserName() + " version: " + this.helper.getBrowserVer(), TestStatus.Pass);

		} catch (AssertionError e) {
			log.logStatements("Assertion error in setupBeforeTest method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		} catch (Exception e) {
			log.logStatements("Exception in setupBeforeTest method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
			reports.reportLogger(this.driver, this.currentRunningTest,
					"Application was not opened or user did not login.", TestStatus.Fail);
		}
	}

	/**
	 * Method to run after each test
	 * 
	 * @param testResult test result to be passed by TestNG
	 */
	@AfterMethod
	public void tearDownAfterTest(ITestResult testResult) {
		try {
			if (!((Integer) testResult.getStatus()).equals(ITestResult.SKIP)) {
				
			}

			if (this.driver != null) {
				this.driver.quit();
				this.driver = null;
//				driver.navigate().to(this.helper.getPropertyValues("projectConfigs", "URL"));
//				JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
//				jsExecutor.executeScript("return document.readyState").equals("complete");
			}
		} catch (Exception e) {
			log.logStatements("Error in tearDownAfterTest method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
			reports.reportLogger(this.driver, this.currentRunningTest, "User was not logged out", TestStatus.Fail);
		}
	}
}
