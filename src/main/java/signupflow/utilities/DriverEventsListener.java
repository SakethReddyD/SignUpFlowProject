package signupflow.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverEventsListener implements WebDriverListener {

	private Log log = null;

	/**
	 * Initializes a new instance of DriverEventsListener class.
	 * 
	 * @param log Logger object used for added the event logs
	 */
	public DriverEventsListener(Log log) {
		this.log = log;
	}

	@Override
	public void afterClick(WebElement element) {
		this.log.logDriverEvents("Clicked on: " + element.toString() + " element");
	}

	@Override
	public void afterFindElement(WebElement element, By locator, WebElement result) {
		this.log.logDriverEvents("Found element: " + element.toString() + " , by: " + locator.toString());
	}

	@Override
	public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
		this.log.logDriverEvents("Found elements: " + element.toString() + " , by: " + locator.toString());
	}

	@Override
	public void afterIsDisplayed(WebElement element, boolean result) {
		this.log.logDriverEvents(element.toString() + " element display status was: " + result);
	}

	@Override
	public void afterGetText(WebElement element, String result) {
		this.log.logDriverEvents(element.toString() + " element's text was: " + result);
	}

	@Override
	public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
		this.log.logDriverEvents(script + " was executed.");
	}
}