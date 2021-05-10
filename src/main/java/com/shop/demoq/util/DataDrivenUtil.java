package com.shop.demoq.util;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenUtil {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static String TEST_DATA_SHEET_PATH = "BillingDetails.xlsx";

	public static ArrayList<ArrayList<String>> getDataTemp(String testcaseName) throws IOException {

		ArrayList<String> listOfDetails = new ArrayList<String>();
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

		FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("BillingDetails")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();

				int coloumn = 0;

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r != null && r.getCell(coloumn) != null
							&& r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						boolean shouldTakeRow = true;
						int colIndex = 0;
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == c.CELL_TYPE_STRING) {
								if (c.getStringCellValue().equalsIgnoreCase("N") && colIndex==8) {
									shouldTakeRow = false;
								} else if (c.getStringCellValue().equalsIgnoreCase("Y")) {
								} 
								
								else {
									listOfDetails.add(c.getStringCellValue());
								}
							} else {
								listOfDetails.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							colIndex++;
						}
						if (shouldTakeRow) {
						
							res.add(new ArrayList(listOfDetails));
							listOfDetails.clear();
						} else {
							listOfDetails.clear();
						}
					}
				}
			}
		}

		return res;

	}

}