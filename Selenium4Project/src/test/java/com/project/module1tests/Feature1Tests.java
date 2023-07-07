package com.project.module1tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.project.pages.BaseClass;
import com.project.pages.LoginPage;
import com.project.utilities.ExcelHandling;

public class Feature1Tests extends BaseClass {

	String path = System.getProperty("user.dir")+"/testdata/testdata_module1.xlsx";
	String sheetName = "Suite1";

	ExcelHandling objExcel = new ExcelHandling();
	XSSFSheet objSheet = objExcel.getSheetData(path, sheetName);
	LoginPage objLoginPage = null; 
	
	@Test
	public void loginTest() {
	
		objLoginPage = new LoginPage(driver);
		String userName = objExcel.getCellData(objSheet,1,0);
		String pwd = objExcel.getCellData(objSheet,1,1);
		
		objLoginPage.login(userName, pwd);
	}
}
