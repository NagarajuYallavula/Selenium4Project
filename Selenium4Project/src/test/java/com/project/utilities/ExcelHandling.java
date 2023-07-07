package com.project.utilities;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandling {

	public String projectPath;
	public XSSFWorkbook xlworkbook=null;
	public XSSFSheet sheet=null;
	public int currentRow = 1;

	public XSSFSheet getSheetData(String xlPath, String sheetName) {
		
		try {
			xlworkbook = new XSSFWorkbook(xlPath);
			sheet = xlworkbook.getSheet(sheetName);			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sheet;
	}
	
	public int getRowCount(XSSFSheet sheetObj) {

		int rowCount = sheetObj.getPhysicalNumberOfRows();
		System.out.println("### Sheet Row Count: " + rowCount);
		
		return rowCount;
	}

	public String getCellData(XSSFSheet sheetObj, int row, int col) {

		getRowCount(sheetObj);  //rowcount check
		String cellData = sheetObj.getRow(row).getCell(col).getStringCellValue();
		System.out.println("Excel Cell Value: " + cellData);
		
		return cellData;
	}
}