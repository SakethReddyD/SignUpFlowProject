package signupflow.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class HelperMethods {
	private Log log = null;
	private DriverEventsListener driverEventsListener = null;
	private String browserVer = "", browserName = "";
	private Reports reportObj = null;

	/**
	 * Initializes a new instance of HelperMethods class.
	 * 
	 * @param log logging object to use
	 */
	public HelperMethods(Log log) {
		try {
			this.log = log;
			this.driverEventsListener = new DriverEventsListener(log);
		} catch (Exception e) {
			this.log.logStatements("Error in HelperMethods method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}
	}

	/**
	 * Method to get the report object which will be used for writing statements to
	 * the HTML report
	 * 
	 * @return
	 */
	public Reports getReportObj() {
		return reportObj;
	}

	/**
	 * Method to set the report object which will be used for writing statements to
	 * the HTML report
	 * 
	 * @param reportObj reporting object to set
	 */
	public void setReportObj(Reports reportObj) {
		this.reportObj = reportObj;
	}

	/**
	 * Method to open a browser
	 * 
	 * @param browserName Browser to open
	 * @return specified driver
	 */
	public WebDriver OpenBrowser(String browserName) {
		WebDriver driver = null;
		WebDriver driverWithEventHandlers = null;
		try {
			switch (browserName) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", this.driverPath() + "\\chromedriver.exe");
				System.setProperty("webdriver.http.factory", "jdk-http-client");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("safebrowsing.enabled", true);
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				break;
			case "edgechromium":
				System.setProperty("webdriver.edge.driver", this.driverPath() + "\\msedgedriver.exe");
				EdgeOptions edgeOption = new EdgeOptions();
				edgeOption.addArguments("-inprivate");
				edgeOption.addArguments("--remote-allow-origins=*");
				driver = new EdgeDriver(edgeOption);
				break;
			}

			if (driver != null) {
				Capabilities browserCapabilities = ((RemoteWebDriver) driver).getCapabilities();
				this.setBrowserVer(browserCapabilities.getBrowserVersion());
				this.setBrowserName(browserCapabilities.getBrowserName());
				driverWithEventHandlers = new EventFiringDecorator(this.driverEventsListener).decorate(driver);
				driverWithEventHandlers.manage().window().maximize();
			}
		} catch (Exception e) {
			this.log.logStatements("Error in OpenBrowser method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
			throw e;
		}

		return driverWithEventHandlers;
	}

	/**
	 * Method to open browser and launch the required application
	 * 
	 * @param browserName Browser to open the application on
	 * @return driver object after successful initialization and launch
	 */
	public WebDriver OpenBrowserAndLaunchApp(String browserName, String url) {
		WebDriver driver = null;
		try {
			driver = this.OpenBrowser(browserName);
			if (driver != null) {
				driver.navigate().to(url);
				JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
				jsExecutor.executeScript("return document.readyState").equals("complete");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			this.log.logStatements("Error in OpenBrowserAndLaunchApp method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}

		return driver;
	}

	/**
	 * Method to get property values from the config file with extension of
	 * .properties
	 * 
	 * @param configFileName   Config file name containing the properties
	 * @param propertyToSearch Property key to search for
	 * @return property value after successful search operation
	 */
	public String getPropertyValues(String configFileName, String propertyToSearch) {
		Properties properties = null;
		String propValueString = "";
		try {
			String userDirString = System.getProperty("user.dir") + "\\configs\\" + configFileName + ".properties";
			FileInputStream thePropFile = new FileInputStream(userDirString);
			properties = new Properties();
			properties.load(thePropFile);
			propValueString = properties.getProperty(propertyToSearch);
		} catch (Exception e) {
			this.log.logStatements("Error in getPropertyValues method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}

		return propValueString;
	}

	/**
	 * Method to get current machine's web browser version
	 * 
	 * @return browser version
	 */
	public String getBrowserVer() {
		return browserVer;
	}

	/**
	 * Method to set current machine's web browser version
	 * 
	 * @param browserVer Browser version to set
	 */
	public void setBrowserVer(String browserVer) {
		this.browserVer = browserVer;
	}

	/**
	 * Method to get current machine's web browser name
	 * 
	 * @return browser name
	 */
	public String getBrowserName() {
		return browserName;
	}

	/**
	 * Method to set current machine's web browser name
	 * 
	 * @param browserName Browser name to set
	 */
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}


	/**
	 * Method to get the path where drivers are stored.
	 * 
	 * @return Path to all drivers
	 */
	public String driverPath() {
		String driverPath = "";
		try {
			File driverDir = new File(
					new File(System.getProperty("user.dir")) +"\\src\\test\\resources\\ComponentLib\\Drivers");
			driverPath = driverDir.getAbsolutePath();
		} catch (Exception e) {
			this.log.logStatements("Error in driverPath method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return driverPath;
	}

	
	/**
	 * Method to generate random word
	 * 
	 * @param lengthOfString - Length of random word generated
	 * @return Returns the random word of specified length
	 */
	public String generateRandomWord(int lengthOfString) {
		String containerString = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder stringBuilder = new StringBuilder(lengthOfString);
		try {
			for (int i = 0; i < lengthOfString; i++) {
				int index = (int) (containerString.length() * Math.random());
				stringBuilder.append(containerString.charAt(index));
			}
		} catch (Exception e) {
			this.log.logStatements("Error in generateRandomWord method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}

		return stringBuilder.toString();
	}
	
}