package com.inetbanking.testCases;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass{
	
	ReadConfig readconfig = new ReadConfig();

	//define the common variables here...
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUser();
	public String password = readconfig.getPassword();
	public String homepageTitle = readconfig.getTitle();
	
	public static WebDriver driver;
	public static Logger logger;
	public static LoginPage lp;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{	
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties"); 		
		//System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		//above is commented out since chromedriver is placed in /usr/local/bin/, making it "native"

		if (br.equals("chrome"))
		{
			//suppress "Chrome is being Controlled by Automated test Software" message in Chrome
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			
			driver = new ChromeDriver(options);	
			
			//first-off open the URL in a browser
			driver.get(baseURL);
			
			//LoginPage lp = new LoginPage(driver);
			lp = new LoginPage(driver);
			//deal with the Chrome warnings pre-login page
			lp.clickAdvancedButton();
			lp.clickURLLink();	
		}
		
		else if (br.equals("safari"))
		{	
			//since Safari is natively supported on Mac this is all we need to do
			driver = new SafariDriver();
			//first-off open the URL in a browser
			driver.get(baseURL);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomStringGenerator()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
		
	}
	
	public static String randomNumberGenerator()
	{
		String generatednumber = RandomStringUtils.randomNumeric(4);
		return generatednumber;
	}
}