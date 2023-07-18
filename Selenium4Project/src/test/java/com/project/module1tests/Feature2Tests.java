package com.project.module1tests;

import static org.testng.Assert.assertEquals;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.project.pages.BaseClass;
import com.project.pages.LoginPage;
import com.project.utilities.ExcelHandling;

public class Feature2Tests extends BaseClass {

	String path = System.getProperty("user.dir")+"/testdata/testdata_module1.xlsx";
	String sheetName = "Suite1";
	
	ExcelHandling objExcel = new ExcelHandling();
	XSSFSheet objSheet = objExcel.getSheetData(path, sheetName);
	LoginPage objLoginPage = null; 
	
	
	@Test
	public void loginTest() {
			
		logger = extent.createTest("TC_001-LoginTest");
		
		objLoginPage = new LoginPage(driver);
		String userName = objExcel.getCellData(objSheet,1,0);
		String pwd = objExcel.getCellData(objSheet,1,1);
		
		objLoginPage.login(userName, pwd);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test1 Passed", ExtentColor.GREEN));
	}
	
	@Test(groups= {"reg"})
	public void searchStoreTest() {
		
		logger = extent.createTest("TC_001-LoginTest");
		
		String strSearch = objExcel.getCellData(objSheet,1,9);
		objLoginPage.searchStore(strSearch);
		boolean testResult = objLoginPage.verifySearchResults(strSearch);
		
		assertEquals(testResult,true);
		logger.log(Status.FAIL, MarkupHelper.createLabel("Test2 Failed", ExtentColor.RED));
	}
}