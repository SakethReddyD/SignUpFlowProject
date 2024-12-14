package signupflow.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
	final String dirPathForLog = System.getProperty("user.dir") + "\\testOutput\\testlogs\\";

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private String logFileFullPath = "";

	/**
	 * Initializes a new instance of Log class.
	 */
	public Log() {
		try {
			String logFileName = "Test_Run_Logs.txt";
			File logDir = new File(this.dirPathForLog);
			if (!logDir.exists()) {
				logDir.mkdir();
			}

			File logFile = new File(this.dirPathForLog + logFileName);
			logFileFullPath = logFile.getAbsolutePath();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method to log exception messages to the text file in case of an exception
	 * 
	 * @param errMsgToPrintInLog Custom exception message to log
	 * @param exType             Type of exception
	 * @param exMsg              Exception message from the exception object
	 * @param stacktrace         Stacktrace of the exception
	 */
	public void logStatements(String errMsgToPrintInLog, String exType, String exMsg, StackTraceElement[] stacktrace) {
		StringBuilder allStacktraceContent = new StringBuilder();
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			for (StackTraceElement stackTraceElement : stacktrace) {
				allStacktraceContent.append(stackTraceElement.toString());
				allStacktraceContent.append(System.lineSeparator());
			}

			fileWriter = new FileWriter(logFileFullPath, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(MessageFormat.format("{0} | {1}", dateTimeFormatter.format(LocalDateTime.now()),
					errMsgToPrintInLog));
			bufferedWriter.write(System.lineSeparator());
			bufferedWriter.write(MessageFormat.format("Exception type: {0}", exType));
			bufferedWriter.write(System.lineSeparator());
			bufferedWriter.write(MessageFormat.format("Exception message: {0}", exMsg));
			bufferedWriter.write(System.lineSeparator());
			bufferedWriter.write(MessageFormat.format("Exception Stacktrace: {0}", allStacktraceContent.toString()));
			bufferedWriter.write(System.lineSeparator());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to log custom messages when there are no exceptions
	 * 
	 * @param messageToLog Custom message to log
	 */
	public void logDriverEvents(String messageToLog) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(logFileFullPath, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(
					MessageFormat.format("{0} | {1}", dateTimeFormatter.format(LocalDateTime.now()), messageToLog));
			bufferedWriter.write(System.lineSeparator());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
