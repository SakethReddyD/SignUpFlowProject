package signupflow.testUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import signupflow.testinitializer.Initialzer;


public class CustomTestListeners implements ITestListener{
	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	public void onStart(ITestContext context) {				
		Initialzer.log.logDriverEvents("********* Test Suite: " + context.getSuite().getName() + " started. *********");
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	public void onFinish(ITestContext context) {
		Initialzer.log.logDriverEvents("********* Test Suite: " + context.getSuite().getName() + " completed. *********");
	}

	/**
	 * Invoked each time before a test will be invoked. The <code>ITestResult</code>
	 * is only partially filled with the references to class, method, start millis
	 * and status.
	 *
	 * @param result the partially filled <code>ITestResult</code>
	 * @see ITestResult#STARTED
	 */
	public void onTestStart(ITestResult result) {
		Initialzer.log.logDriverEvents("********* Test case: " + result.getName() + " started. *********");
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {
		Initialzer.log.logDriverEvents("********* Test case: " + result.getName() + " passed. *********");
	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result) {
		Initialzer.log.logDriverEvents("********* Test case: " + result.getName() + " failed. *********");
	}
}
