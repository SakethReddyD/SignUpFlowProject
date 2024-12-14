package signupflow.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	private Log log = null;

	
	public ExcelData(String xlFilePath, Log log) {
		try {
			this.log = log;
			File src = new File(xlFilePath);
			fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			this.log.logStatements("Error in ExcelData CTOR.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}
	}

	
	public String getCellData(String[] excelData, String ParamName) {
		String Dataval = "";
		try {
			for (int i = 1; i < excelData.length; i++) {
				if (excelData[i].startsWith(ParamName)) {
					String[] dataval = excelData[i].split(":");
					Dataval = dataval[1];
					break;
				}
			}
		} catch (Exception e) {
			this.log.logStatements("Error in getCellData method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return Dataval;
	}

	
	private int getRowCount(String sheetName) {
		int rowCount = -1;
		try {
			sheet = workbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			this.log.logStatements("Error in getRowCount method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return rowCount;
	}

	
	private int getColumnCount(String sheetName) {
		int colCount = -1;
		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			colCount = row.getLastCellNum();
		} catch (Exception e) {
			this.log.logStatements("Error in getColumnCount method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return colCount;
	}

	
	private int getColumnNumber(String sheetName, String columnName) throws Exception {
		int columnNumber = 0;
		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
					columnNumber = i;
					break;
				}
			}

			if (columnNumber == 0) {
				throw new Exception("No column with title '" + columnName + "' found");
			}
		} catch (Exception e) {
			this.log.logStatements("Error in getColumnNumber method.", e.getClass().getCanonicalName(), e.getMessage(),
					e.getStackTrace());
		}

		return columnNumber;
	}

	
	public List<String[]> getAllRowsFromDataSheet(String sheetName) {
		List<String[]> rowCollection = new ArrayList<>();
		try {

			this.sheet = this.workbook.getSheet(sheetName);
			int rows = this.getRowCount(sheetName);
			int columns = this.getColumnCount(sheetName);
			String[] cellValues = null;
			for (int i = 1; i < rows; i++) {
				cellValues = new String[columns];
				for (int j = 0; j < columns; j++) {
					Cell cellValue = sheet.getRow(i).getCell(j);
					if (cellValue.getCellType() == CellType.NUMERIC) {
						cellValues[j] = String.valueOf((int) cellValue.getNumericCellValue());
					} else {
						cellValues[j] = cellValue.getStringCellValue();
					}
				}

				rowCollection.add(cellValues);
			}
		} catch (Exception e) {
			this.log.logStatements("Error in getAllRowsFromDataSheet method.", e.getClass().getCanonicalName(),
					e.getMessage(), e.getStackTrace());
		}

		return rowCollection;
	}

	
	public HashMap<String, String> getCellValuesForTheSpecifiedFilter(String sheetName, String filter)
			throws Exception {
		HashMap<String, String> testData = new HashMap<String, String>();
		try {
			int testrow = 0;
			sheet = workbook.getSheet(sheetName);
			int rows = getRowCount(sheetName);
			for (int i = 1; i <= rows; i++) {
				String celldata = sheet.getRow(i).getCell(0).getStringCellValue();
				if (celldata.equalsIgnoreCase(filter)) {
					testrow = i;
					break;
				}
			}

			if (testrow == 0) {
				throw new Exception("No row matching filter '" + filter + "' found");
			}

			int columns = sheet.getRow(testrow).getLastCellNum();
			for (int j = 1; j < (columns); j++) {
				String cellheader = sheet.getRow(0).getCell(j).getStringCellValue();
				Cell celldata1 = sheet.getRow(testrow).getCell(j);
				if (celldata1 == null || celldata1.getCellType() == CellType.BLANK) {
					continue;
				} else if (celldata1.getCellType() == CellType.NUMERIC) {
					testData.put(cellheader, String.valueOf((int) celldata1.getNumericCellValue()));
				} else if (celldata1.getCellType() == CellType.BOOLEAN) {
					testData.put(cellheader, String.valueOf((Boolean) celldata1.getBooleanCellValue()));
				} else {
					testData.put(cellheader, celldata1.getStringCellValue());
				}
			}
		} catch (Exception e) {
			this.log.logStatements("Error in getCellValuesForTheSpecifiedFilter method.",
					e.getClass().getCanonicalName(), e.getMessage(), e.getStackTrace());
		}

		return testData;
	}

	
	public String getCellValueForTheSpecifiedColumn(String sheetName, String filter, String columnName)
			throws Exception {
		String data = "";
		int columnNumber = 0;
		try {
			int testrow = 0;

			sheet = workbook.getSheet(sheetName);
			int rows = getRowCount(sheetName);
			columnNumber = getColumnNumber(sheetName, columnName);
			for (int i = 0; i < rows; i++) {
				String celldata = sheet.getRow(i).getCell(0).getStringCellValue();
				if (celldata.equalsIgnoreCase(filter)) {
					testrow = i;
					break;
				}
			}

			data = sheet.getRow(testrow).getCell(columnNumber).getStringCellValue();

		} catch (Exception e) {
			this.log.logStatements("Error in getCellValueForTheSpecifiedColumn method.",
					e.getClass().getCanonicalName(), e.getMessage(), e.getStackTrace());
		}

		return data;
	}
}
