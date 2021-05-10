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
	public static String TEST_DATA_SHEET_PATH = "E:\\Eclipse\\workspace\\ProjectDemo\\src\\main\\java\\com\\shop\\demoqa\\testdata\\BillingDetail.xlsx";
 
	public static ArrayList<ArrayList<String>> getDataTemp(String testcaseName) throws IOException {

		ArrayList<String> a = new ArrayList<String>();
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

		FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("BillingDetails")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
						coloumn = k;
					}
					k++;
				}

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r != null && r.getCell(coloumn) != null
							&& r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == c.CELL_TYPE_STRING) {
								a.add(c.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
						res.add(new ArrayList(a));
						a.clear();
					}
				}
			}
		}

		return res;

	}

}