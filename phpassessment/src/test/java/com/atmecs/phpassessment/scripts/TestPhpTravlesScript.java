package com.atmecs.phpassessment.scripts;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.phpassessment.constant.FilePath;
import com.atmecs.phpassessment.constant.TimeOut;
import com.atmecs.phpassessment.pages.PhpFuncationality;
import com.atmecs.phpassessment.reports.LogReport;
import com.atmecs.phpassessment.testbase.TestBase;
import com.atmecs.phpassessment.util.ExcelReader;
import com.atmecs.phpassessment.util.NullCellValueException;
import com.atmecs.phpassessment.util.ReadProp;

public class TestPhpTravlesScript extends TestBase {
	Properties baseClass;
	ReadProp property;
	String url;
	PhpFuncationality funcationality;
	ExcelReader excelFile;
	LogReport log;

	@BeforeClass
	public void urlqw() {
		baseClass = ReadProp.loadProperty(FilePath.CONFIG_FILE);
		url = baseClass.getProperty("url1");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TimeOut.TIMEOUT_INSECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TimeOut.IMPLICIT_TIMEOUT_INSECONDS, TimeUnit.SECONDS);

	}

	@Test
	public void phpTravlesintegration() throws NullCellValueException {
		log = new LogReport();
		excelFile = new ExcelReader();
		funcationality = new PhpFuncationality(driver);
		String noOfAdults = excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "No_Of_adults",
				"Test_Id1");
		String noOfChilds = excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "No_Of_Childs",
				"Test_Id1");
		String noOfDaysCheckout = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "input", "Dayss");
		String day = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Months");
		String month = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Days");
		String year = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Dayss");

		String noOfMonths = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "input", "Months");
		String noOfDays = excelFile.getCellData(FilePath.TESTDATA_FILE, "dateinput", "input", "Days");

		log.info("Step1:Searching for hotel");
		funcationality.hotelSearch();
		log.info("Step2:Calculating effective booking check in date");
		funcationality.getPolicyEffectiveDate(noOfMonths, noOfDays);
		log.info("Step2:Calculating effective booking check out date");
        funcationality.getPolicyEffectiveDateByUser(noOfDaysCheckout, day, month, year);
        log.info("Step 3:Selecting no of adults and childs");
		funcationality.addAdults(noOfAdults, noOfChilds);
		log.info("Step4:valaditing booking Details");
		funcationality.validateModifytab();
		log.info("Step5:Sorting Star rating");
		funcationality.sortRating();

	}
}