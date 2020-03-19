package com.calamp.colt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SetData {
	//set data to data.xlsx file
	public void toExcel(String fileName, String sheetName, int rIndex, int cIndex,String value) {
		try {
			File f = new File("./test-data"+fileName+".xlsx");
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rIndex);
			Cell c = r.getCell(cIndex);
			c.setCellValue(value);									//set cell value
			FileOutputStream fos = new FileOutputStream(f);			//save the file
			wb.write(fos);											//save workbook
		} catch(Exception e) {	
		}
	}
	//set data to data.properties file
	public void toProperties(String fileName, String key, String value, String comment) {
		try {
			File f = new File("./test-config/"+fileName+".properties");
			FileInputStream fis = new FileInputStream(f);
			Properties prop = new Properties();
			prop.load(fis);
			prop.put(key, value);
			FileOutputStream fos = new FileOutputStream(f);
			prop.store(fos, comment);	
		} catch(Exception e) {
			
		}
	}
}
