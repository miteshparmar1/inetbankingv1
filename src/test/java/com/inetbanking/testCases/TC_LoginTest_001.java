/*AUT is https://demo.guru99.com/v4/index.php
 * Full tutorial is on YouTube: https://www.youtube.com/watch?v=M4Ye3SKT46g&t=4489s
 * 
 */

package com.inetbanking.testCases;

import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

public class TC_LoginTest_001 extends BaseClass
{
	
	//only a single test method needs creating...
	@Test
	public void loginTest() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		logger.info("URL is opened successfully");

		LoginPage lp = new LoginPage(driver);
		
		//deal with the Chrome warnings pre-login page
		//lp.clickAdvancedButton();
		//lp.clickURLLink();
				
		//deal with the login page
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		//click the login button
		lp.clickSubmit();
		
		if (driver.getTitle().equals(homepageTitle))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Login test failed since title of home page is " + driver.getTitle() + " instead of expected value: " + homepageTitle);
			captureScreen(driver, "loginTest");
		}
	}	
}