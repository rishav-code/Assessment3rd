package com.atmecs.phpassessment.dataprovider;

import org.testng.annotations.DataProvider;

import com.atmecs.phpassessment.constant.FilePath;
import com.atmecs.phpassessment.util.ProvideData;
/**
 * DataProvider class
 * In this class, data is given from the dataprovider
 * @author rishav.kumar
 *
 */
public class Dataprovide {
	@DataProvider(name = "Dpdninput")
	public Object[][] getData() {
		ProvideData provideData = new ProvideData(FilePath.TESTDATA_FILE, 0);
		Object[][] getData = provideData.provideData();
		return getData;
	}


}
