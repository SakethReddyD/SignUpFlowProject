package signupflow.pages.signinpage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import signupflow.pages.signuppage.signuppage;
import signupflow.utilities.Log;

public class signinpage {
	private WebDriver driver = null;
	private WebDriverWait exWait = null;
	private Log log = null;
	public static String contentCreatedDate = "", contentFiledDate = "";

	/**
	 * Initializes a new instance of signinpage class.
	 * 
	 * @param driver - Driver reference to be used for acting on the web elements
	 * @param log    - Log file reference for logging errors to the log file
	 */
	public signinpage(WebDriver driver, Log log) {
		try {
			this.driver = driver;
			this.log = log;
			PageFactory.initElements(this.driver, this);
			this.exWait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
		} catch (Exception e) {
			this.log.logStatements("Error in signinpage CTOR.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}
	
	@FindBy(xpath = "//a[@class='action create primary']//span[text() = 'Create an Account']")
	private WebElement createAccount;
	
	@FindBy(xpath = "//span[text() = 'Customer Login']")
	private WebElement customerlogin;
	
	@FindBy(xpath = "//input[@id = 'email']")
	private WebElement inputemail;
	
	@FindBy(xpath = "//input[@id = 'pass'][@aria-required='true']")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//span[text()='Sign In']//ancestor::button[@class='action login primary']")
	private WebElement signinButton;
	
	@FindBy(xpath = "//div[contains(text(),'The account sign-in was incorrect')]")
	private WebElement nonExistingUser;
	
	@FindBy(xpath = "//div[contains(text(),'Please enter a valid email address')]")
	private WebElement mailNotValid;
	
	public signuppage navigateToCreateAccountFromSignInPage() {
		signuppage signuppageClass = null;
		try {
			if (createAccount.isDisplayed()) {
				createAccount.click();
				signuppageClass = new signuppage(driver, log);
			}
		} catch (Exception e) {
			this.log.logStatements("Error in navigateToCreateAccountFromSignInPage method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return signuppageClass;
	}
	
	public boolean verifyCustomerloginPageVisibility() {
		boolean flag = false;
		try {
			if (customerlogin.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
			this.log.logStatements("Error in verifyCustomerloginPageVisibility method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterMail(String mail) {
		boolean flag = false;
		try {
			if (inputemail.isDisplayed()) {
				inputemail.sendKeys(mail);
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterMail method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterPassword(String password) {
		boolean flag = false;
		try {
			if (inputPassword.isDisplayed()) {
				inputPassword.sendKeys(password);
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterPassword method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean clickSignIn() {
		boolean flag = false;
		try {
			if (signinButton.isDisplayed()) {
				signinButton.click();
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in clickSignIn method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean verifyMailNotValidError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(mailNotValid));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyMailNotValidError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}
	
	public boolean verifyUserNotExist() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(nonExistingUser));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyUserNotExist method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}
	
	public boolean verifyPasswordInCorrect() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(nonExistingUser));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyPasswordInCorrect method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}
	
	
	
}
