package com.atmecs.phpassessment.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.atmecs.phpassessment.constant.FilePath;
import com.atmecs.phpassessment.constant.Findloc;
import com.atmecs.phpassessment.constant.TimeOut;
import com.atmecs.phpassessment.helper.CommonUtlity;
import com.atmecs.phpassessment.helper.Waits;
import com.atmecs.phpassessment.reports.LogReport;
import com.atmecs.phpassessment.util.DateManipulate;
import com.atmecs.phpassessment.util.ExcelReader;
import com.atmecs.phpassessment.util.ExcelWrite;
import com.atmecs.phpassessment.util.NullCellValueException;

public class PhpFuncationality {
	WebDriver driver;
	CommonUtlity commonUtlity;
	ExcelReader excelFile;
	DateManipulate date;
	LocalDate now;
	ExcelWrite write;
	LogReport log;
	Waits wait;

	Findloc loc;

	public PhpFuncationality(WebDriver driver) {
		this.driver = driver;
		commonUtlity = new CommonUtlity(driver);
		loc = new Findloc();
		excelFile = new ExcelReader();
		date = new DateManipulate();
		write = new ExcelWrite();
		log = new LogReport();
		wait = new Waits();
	}

	public void hotelSearch() throws NullCellValueException {
		log.info("Validation for Home page rediraction starts");
		commonUtlity.pageRedirection(
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "PageTitle", "Test_Id1"));
		log.info("Validation for Home page rediraction passed and ends");
		log.info("Selecting the Booking city");
		commonUtlity.clickElement(loc.getlocator("loc.hotellocation.search"));
		commonUtlity.clickAndSendText(loc.getlocator("loc.inputcity.text"), TimeOut.TIMEOUT_INSECONDS,
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "Destination", "Test_Id1"));
		commonUtlity.scrollDownPage(TimeOut.IMPLICIT_SCROLL);
		commonUtlity.clickElement(loc.getlocator("loc.clickininput.click"));
		log.info("Selecting check in date");
		commonUtlity.clickElement(loc.getlocator("loc.checkindate.click"));

	}

	public void getPolicyEffectiveDate(String noOfMonths, String noOfDays) throws NullCellValueException {
		log.info("calculating The Check in Date and inserting");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		now = LocalDate.now();
		int days = Integer.parseInt(noOfDays);
		now.plusDays(days);
		String newdate = dtf.format(now.plusDays(days));
		String[] datesplit = newdate.split("/", 3);

		System.out.println(" Ten days later date" + " " + String.valueOf(now.plusDays(days)));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_current", "Days",
				newdate);
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Months", datesplit[0]);
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Days", datesplit[1]);
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "SplittedDate", "Dayss", datesplit[2]);
		commonUtlity.clickAndSendText(loc.getlocator("loc.checkindate.click"), TimeOut.TIMEOUT_INSECONDS, excelFile
				.getCellData(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_current", "Days"));

	}

	public void getPolicyEffectiveDateByUser(String noOfDays, String inputDay, String inputMonth, String inputYear)
			throws NullCellValueException {
		log.info("calculating the checkout date and inserting");
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int days = Integer.parseInt(noOfDays);
		int year = Integer.parseInt(inputYear);
		int month = Integer.parseInt(inputMonth);
		int day = Integer.parseInt(inputDay);
		LocalDate currentdate = LocalDate.of(year, month, day);
		System.out.println("User input date " + " " + currentdate);
		LocalDate checkout = currentdate.plusDays(days);
		dateformat.format(checkout);
		System.out.println(" Ten days later date" + " " + String.valueOf(checkout));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_User_input", "Days",
				dateformat.format(checkout));
		commonUtlity.clickAndSendText(loc.getlocator("loc.checkout.click"), TimeOut.TIMEOUT_INSECONDS, excelFile
				.getCellData(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_User_input", "Days"));
	}

	public void addAdults(String adult, String child) throws NullCellValueException {
		log.info("Taking input for no of adults and no of childs");

		double noofadults = Double.parseDouble(adult);
		double fianlAdults = noofadults - 2.0;
		for (int index = 1; index <= fianlAdults; index++) {
			commonUtlity.clickElement(loc.getlocator("loc.add.button.adult"));
			boolean status = commonUtlity.isElementVisible(loc.getlocator("loc.add.button.adult"));
			Assert.assertEquals(status, true);

		}
		commonUtlity.clickElement(loc.getlocator("loc.destination.click"));
		double noOfChild = Double.parseDouble(child);
		System.out.println(noOfChild);
		for (int index = 1; index <= noOfChild; index++) {
			commonUtlity.clickElement(loc.getlocator("loc.add.button.child"));

		}
		commonUtlity.clickElement(loc.getlocator("loc.search.button"));
		log.info("Hotel page rediraction assertion starts");
		commonUtlity.pageRedirection(
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "PageTitle", "Test_Id2"));
		log.info("Hotel page rediraction assertion completed");
	}

	public void validateModifytab() throws NullCellValueException {

		commonUtlity.clickElement(loc.getlocator("loc.modify.click"));
		log.info("checkin Date Validation starts");
		wait.hardWait(TimeOut.WAIT_TIMEOUT_INSECONDS);
		String actualCheckinDate = commonUtlity.getElement(loc.getlocator("loc.checkindate.click"))
				.getAttribute("value");
		commonUtlity.assertion(actualCheckinDate,
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "Expected_CheckInDate", "Test_Id1"));
		log.info("Check in date validation completed");
		log.info("checkout Date Validation starts");
		String actualCheckOutDate = commonUtlity.getElement(loc.getlocator("loc.checkout.click")).getAttribute("value");
		commonUtlity.assertion(actualCheckOutDate,
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "Expected_CheckOutDate", "Test_Id1"));
		log.info("Check out date validation completed");
		log.info("No of adults Validation");
		wait.hardWait(TimeOut.WAIT_TIMEOUT_INSECONDS);
		String actualNoOfAdults = commonUtlity.getElement(loc.getlocator("loc.adultinput.text")).getAttribute("value");
		commonUtlity.assertion(actualNoOfAdults,
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "No_Of_adults", "Test_Id1"));
		log.info("No of adults validation completed");
		log.info("No of childs Validation");
		String actualNoOfChilds = commonUtlity.getElement(loc.getlocator("loc.child.text")).getAttribute("value");
		commonUtlity.assertion(actualNoOfChilds,
				excelFile.getCellData(FilePath.TESTDATA_FILE, "phptravlesinput", "No_Of_Childs", "Test_Id2"));
		log.info("No of childs validation completed");

	}
	public void sortRating() {
		log.info("sorting started for rating");
		List<WebElement> ratingList = commonUtlity.getElementsList(driver, loc.getlocator("loc.starrating.text"));
		System.out.println(ratingList.size());
		List<Integer> intrating = new ArrayList<Integer>();
		for (int index = 0; index < ratingList.size(); index++) {
			String rating = ratingList.get(index).getText();
			rating.split("/", 2);
			intrating.add(Integer.parseInt(rating));
					
		}

		Collections.sort(intrating);
		System.out.println(intrating.get(0));
		
}
	
}