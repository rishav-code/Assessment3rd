package com.atmecs.phpassessment.testbase;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.atmecs.phpassessment.constant.FilePath;
import com.atmecs.phpassessment.util.ReadProp;

public class TestBase {
	Properties baseClass;
	String url;
	public String browser;
	int downloadFile;
	public String normal;
	public WebDriver driver;
	String nodeUrl;
	ReadProp property = new ReadProp();

	//@Parameters({ "browser", "url" })
	//@Parameters("browser")
	@BeforeClass
	public void intitailizeBrowser() throws IOException {

		baseClass = ReadProp.loadProperty(FilePath.CONFIG_FILE);
	//url = baseClass.getProperty("url");
		 browser = baseClass.getProperty("browser");
		normal = baseClass.getProperty("normal");

		if (normal.equalsIgnoreCase("normal")) {
			System.out.println("browser is " + browser);

			if (browser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", FilePath.CHROME_PATH);

				driver = new ChromeDriver();
				//driver.get(url);
			} else if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", FilePath.FIREFOX_PATH);
				driver = new FirefoxDriver();
				//driver.get(url);
			} else if (browser.equalsIgnoreCase("ie")) {

				System.setProperty("webdriver.edge.driver", FilePath.IE_PATH);
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
				driver = new InternetExplorerDriver(ieCaps);
			}
		} else if (normal.equalsIgnoreCase("GRID")) {

			System.out.println("GRID CONNECTION");

			
			WebDriver drv = new Grid().getConnection(driver, browser);
			this.driver = drv;
			driver.get(url);
		}
		

	

//	}	  @AfterTest
//		 public void end() 
//		  { driver.quit();
//		  
//}

}
}
