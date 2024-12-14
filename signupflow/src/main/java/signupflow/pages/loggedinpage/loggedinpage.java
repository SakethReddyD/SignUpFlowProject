package signupflow.pages.loggedinpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import signupflow.utilities.Log;

public class loggedinpage {
	private WebDriver driver = null;
	private WebDriverWait exWait = null;
	private Log log = null;
	public static String contentCreatedDate = "", contentFiledDate = "";

	/**
	 * Initializes a new instance of loggedinpage class.
	 * 
	 * @param driver - Driver reference to be used for acting on the web elements
	 * @param log    - Log file reference for logging errors to the log file
	 */
	public loggedinpage(WebDriver driver, Log log) {
		try {
			this.driver = driver;
			this.log = log;
			PageFactory.initElements(this.driver, this);
			this.exWait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
		} catch (Exception e) {
			this.log.logStatements("Error in loggedinpage CTOR.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}
	
	
	public boolean verifyAccountCreated(String name) {
		boolean flag = false;
		try {
			String pathTextString = "//p[contains(text(),'"+ name +"')]";
			WebElement customerName = driver.findElement(By.xpath(pathTextString));
			if (customerName.isDisplayed()) {
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in verifyAccountCreated method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean verifyCustomerLogged(String name) {
		boolean flag = false;
		try {
			Thread.sleep(5000);
			String pathTextString = "//div[@class='panel header']//span[contains(text(),'"+ name +"')]";
			WebElement customerName = driver.findElement(By.xpath(pathTextString));
			if (customerName.isDisplayed()) {
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in verifyCustomerLogged method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	
}
