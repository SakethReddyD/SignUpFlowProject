package signupflow.pages.signuppage;

import java.time.Duration;

import org.bouncycastle.asn1.eac.Flags;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import signupflow.utilities.Log;

public class signuppage {
	private WebDriver driver = null;
	private WebDriverWait exWait = null;
	private Log log = null;
	public static String contentCreatedDate = "", contentFiledDate = "";

	/**
	 * Initializes a new instance of signuppage class.
	 * 
	 * @param driver - Driver reference to be used for acting on the web elements
	 * @param log    - Log file reference for logging errors to the log file
	 */
	public signuppage(WebDriver driver, Log log) {
		try {
			this.driver = driver;
			this.log = log;
			PageFactory.initElements(this.driver, this);
			this.exWait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
		} catch (Exception e) {
			this.log.logStatements("Error in signuppage CTOR.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
	}
	
	@FindBy(xpath = "//span[text() = 'Create New Customer Account']")
	private WebElement createNewAccountText;
	
	@FindBy(xpath = "//input[@id = 'firstname']")
	private WebElement inputFirstName;
	
	@FindBy(xpath = "//input[@id = 'lastname']")
	private WebElement inputLastName;
	
	@FindBy(xpath = "//input[@id = 'email_address']")
	private WebElement inputEmail;
	
	@FindBy(xpath = "//input[@id = 'password']")
	private WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id = 'password-confirmation']")
	private WebElement inputConfirmPassword;
	
	@FindBy(xpath = "//button[@title = 'Create an Account']")
	private WebElement createAccountButton;
	
	@FindBy(xpath = "//div[contains(text(), 'First Name is not valid!')]")
	private WebElement firstNameNotValid;
	
	@FindBy(xpath = "//div[contains(text(), 'Last Name is not valid!')]")
	private WebElement lastNameNotValid;
	
	@FindBy(xpath = "//div[contains(text(),'Please enter a valid email address')]")
	private WebElement mailNotValid;
	
	@FindBy(xpath = "//div[contains(text(),'equal or greater than 8 symbols')]")
	private WebElement passwordLengthNotValid;
	
	@FindBy(xpath = "//div[text() = 'Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.']")
	private WebElement passwordNotValid;
	
	@FindBy(xpath = "//span[text() = 'Weak']")
	private WebElement weakPassword;
	
	@FindBy(xpath = "//div[text() = 'Please enter the same value again.']")
	private WebElement passwaordNotMatched;
	
	@FindBy(xpath = "//span[text() = 'No Password']")
	private WebElement noPassword;
	
	@FindBy(xpath = "//div[text() = 'This is a required field.']")
	private WebElement requiredField;
	
	@FindBy(xpath = "//div[contains(text(),'There is already an account with this email address')]")
	private WebElement mailIDExists;
	
	
	public boolean verifyCreateNewAccountPageOpened() {
		boolean flag = false;
		try {
			if (createNewAccountText.isDisplayed()) {
				flag = true;
			}
		} catch (Exception e) {
			this.log.logStatements("Error in verifyCreateNewAccountPageOpened method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterFirstName(String firstName) {
		boolean flag = false;
		try {
			this.exWait.until(ExpectedConditions.elementToBeClickable(inputFirstName));
			inputFirstName.clear();
			inputFirstName.sendKeys(firstName);
			flag = true;
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterFirstName method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterLastName(String lastName) {
		boolean flag = false;
		try {
			this.exWait.until(ExpectedConditions.elementToBeClickable(inputLastName));
			inputLastName.clear();
			inputLastName.sendKeys(lastName);
			flag = true;
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterLastName method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterMail(String mail) {
		boolean flag = false;
		try {
			if (inputEmail.isDisplayed()) {
				inputEmail.clear();
				inputEmail.sendKeys(mail);
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterPassword method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean enterPassword(String password) {
		boolean flag = false;
		try {
			if (inputPassword.isDisplayed()) {
				inputPassword.clear();
				inputPassword.sendKeys(password);
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in enterPassword method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean confirmPassword(String password) {
		boolean flag = false;
		try {
			if (inputConfirmPassword.isDisplayed()) {
				inputConfirmPassword.clear();
				inputConfirmPassword.sendKeys(password);
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in confirmPassword method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean createAccount() {
		boolean flag = false;
		try {
			if (createAccountButton.isDisplayed()) {
				createAccountButton.click();
				flag = true;
			}
			
		} catch (Exception e) {
			this.log.logStatements("Error in createAccount method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean verifyFirstNameNotValidError() {
		boolean flag = false;
		try {
			this.exWait.until(ExpectedConditions.visibilityOf(firstNameNotValid));
			flag = true;
		} catch (Exception e) {
			this.log.logStatements("Error in verifyFirstNameNotValidError method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}
		return flag;
	}
	
	public boolean verifyLastNameNotValidError() {
		boolean flag = false;
		try {
			this.exWait.until(ExpectedConditions.visibilityOf(lastNameNotValid));
			flag = true;
		} catch (Exception e) {
			this.log.logStatements("Error in verifyLastNameNotValidError method.", e.getClass().getCanonicalName(),
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

	public boolean verifyPasswordLengthNotValidError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(passwordLengthNotValid));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyPasswordLengthNotValidError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}

	public boolean verifyPasswordNotValidError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(passwordNotValid));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyPasswordNotValidError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}

	public boolean verifyWeakPasswordError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(weakPassword));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyWeakPasswordError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}

	public boolean verifyPasswordNotMatchedError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(passwaordNotMatched));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyPasswordNotMatchedError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}

	public boolean verifyNoPasswordError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(noPassword));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyNoPasswordError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}

	public boolean verifyRequiredFieldError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(requiredField));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyRequiredFieldError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}
	
	public boolean verifyMailExistsError() {
	    boolean flag = false;
	    try {
	        this.exWait.until(ExpectedConditions.visibilityOf(mailIDExists));
	        flag = true;
	    } catch (Exception e) {
	        this.log.logStatements("Error in verifyMailExistsError method.", e.getClass().getCanonicalName(),
	                e.getMessage(), e.getStackTrace());
	    }
	    return flag;
	}
	
	
	
	
}
