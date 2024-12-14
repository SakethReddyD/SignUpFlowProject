package signupflow.pages.homepage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import signupflow.pages.signinpage.signinpage;
import signupflow.pages.signuppage.signuppage;
import signupflow.utilities.Log;

public class homepage {
	private WebDriver driver = null;
	private WebDriverWait exWait = null;
	private Log log = null;
	public static String contentCreatedDate = "", contentFiledDate = "";

	/**
	 * Initializes a new instance of homepage class.
	 * 
	 * @param driver - Driver reference to be used for acting on the web elements
	 * @param log    - Log file reference for logging errors to the log file
	 */
	public homepage(WebDriver driver, Log log) {
		try {
			this.driver = driver;
			this.log = log;
			PageFactory.initElements(this.driver, this);
			this.exWait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
		} catch (Exception e) {
			this.log.logStatements("Error in homepage CTOR.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}
	
	@FindBy(xpath = "//span[text()='Home Page']")
	private WebElement homePage;
	
	@FindBy(xpath = "//div[@class='panel header']//a[text()='Create an Account']")
	private WebElement createAccount;
	
	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	private WebElement signIn;
	
	public boolean verifyHomePageLoaded() {
		boolean flag = false;
		try {
			if (homePage.isDisplayed()) {
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in verifyHomePageLoaded method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public signuppage navigateToCreateAccount() {
		signuppage signuppageClass = null;
		try {
			if (createAccount.isDisplayed()) {
				createAccount.click();
				signuppageClass = new signuppage(driver, log);
			}
		} catch (Exception e) {
			this.log.logStatements("Error in navigateToCreateAccount method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return signuppageClass;
	}
	
	public signinpage navigateToSignIn() {
		signinpage signinpageClass = null;
		try {
			if (signIn.isDisplayed()) {
				signIn.click();
				signinpageClass = new signinpage(driver, log);
			}
		} catch (Exception e) {
			this.log.logStatements("Error in navigateToSignIn method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return signinpageClass;
	}
	
	
	
}
