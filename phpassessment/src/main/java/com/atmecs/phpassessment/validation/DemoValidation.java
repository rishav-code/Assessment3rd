package com.atmecs.phpassessment.validation;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.atmecs.phpassessment.constant.FilePath;
import com.atmecs.phpassessment.helper.CommonUtlity;
import com.atmecs.phpassessment.util.ReadProp;

public class DemoValidation {
	WebDriver driver;
	Properties loc;
	CommonUtlity WebUtility;

	public DemoValidation (WebDriver driver) {
		this.driver = driver;
		loc = ReadProp.loadProperty(FilePath.LOCATOR_FILE);
		WebUtility = new CommonUtlity(driver);

	}

}
