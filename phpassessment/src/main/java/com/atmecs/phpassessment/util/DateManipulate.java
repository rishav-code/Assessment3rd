package com.atmecs.phpassessment.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.atmecs.phpassessment.constant.FilePath;

public class DateManipulate {
	LocalDate now;
	//ValidateData data;
	ExcelWrite write = new ExcelWrite();
	ExcelReader excelFile = new ExcelReader();
	Scanner scan = new Scanner(System.in);

	public DateManipulate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		now = LocalDate.now();
		System.out.println("Todays date" + "" + dtf.format(now));

	}

	public void getPolicyEffectiveDate(String noOfMonths, String noOfDays) throws NullCellValueException {

		int months = Integer.parseInt(noOfMonths);
		int days = Integer.parseInt(noOfDays);
		now.plusMonths(months);
		System.out.println(" One month later date" + " " + String.valueOf(now.plusMonths(months)));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_current",
				"Months","Policy Effective Increased Month Date:"+" "+ String.valueOf(now.plusMonths(months)));
		now.plusMonths(-months);
		System.out.println(" One month before date" + " " + String.valueOf(now.minusMonths(months)));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_current",
				"MonthsLater","Policy Effective Decreased Month Date:"+" "+ String.valueOf(now.minusMonths(months)));

		now.plusDays(days);
		System.out.println(" Ten days later date" + " " + String.valueOf(now.plusDays(days)));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_current",
				"Days", String.valueOf(now.plusDays(days)));

	}
	public void getPolicyEffectiveDateByUser(String noOfMonths, String noOfDays, String inputYear, String inputMonth,
			String inputDay) throws NullCellValueException {
		int days = Integer.parseInt(noOfDays);
		int months = Integer.parseInt(noOfMonths);
		int year = Integer.parseInt(inputYear);
		int month = Integer.parseInt(inputMonth);
		int day = Integer.parseInt(inputDay);
		LocalDate currentdate = LocalDate.of(year, month, day);
		System.out.println("User input date " + " " + currentdate);
		LocalDate oneMonthBeforeDate = currentdate.plusMonths(months);
		System.out.println(" One month later date" + " " + String.valueOf(oneMonthBeforeDate));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_User_input", "Months",
				"Policy Effective Increased Month Date:" + " " + String.valueOf(oneMonthBeforeDate));
		LocalDate oneMonthAfterDate = currentdate.plusMonths(-months);
		System.out.println(" One month before date" + " " + String.valueOf(oneMonthAfterDate));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_User_input",
				"MonthsLater", "Policy Effective Decreased Month Date:" + " " + String.valueOf(oneMonthAfterDate));
		LocalDate tenDaysLater = currentdate.plusDays(days);
		System.out.println(" Ten days later date" + " " + String.valueOf(tenDaysLater));
		write.setCellDataUnique(FilePath.TESTDATA_FILE, "dateinput", "Policy_Effective_Date_from_User_input", "Days",
				"Policy Effective IncreasedDays Date:" + " " + String.valueOf(tenDaysLater));
	}
	

}
