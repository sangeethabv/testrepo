package com.calamp.colt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetData {
	//read data from data.excel
	public static String fromExcel(String fileName, String sheetName, int rIndex, int cIndex) {
		String data = null;
		try {
			File f = new File("./test-data"+fileName+".xlsx");	//open file
			FileInputStream fis = new FileInputStream(f);		// create virtual image of file
			Workbook wb = WorkbookFactory.create(fis);			// Get total workbook excel
			Sheet st = wb.getSheet(sheetName);					// Get the sheet name in excel
			Row r = st.getRow(rIndex);							// Get row index of sheet
			Cell c = r.getCell(cIndex);							// Get the value of cell
			data = c.toString();								// convert to string
			
		} catch(Exception e) {
		}
		return data;
	}
	
	//read data from data.properties file
	public static String fromProperties(String fileName,String key) {
		String value = null;
		try {
			File f = new File("./test-config/"+fileName+".properties");	//Read file
			FileInputStream fis = new FileInputStream(f);				//Create virtual file
			Properties prop = new Properties();							//Create obj of properties class
			prop.load(fis);												//load property file
			value = (String) prop.get(key);								// get the value for requested key
		} catch(Exception e) {
		}
		return value;
	}
}
