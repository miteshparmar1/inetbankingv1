package com.inetbanking.testCases;

import java.io.IOException;


import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
	
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("password is provided");
		lp.clickSubmit();
		
		//wait for 3 seconds
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details...");
		addcust.custName("Mitesh");
		addcust.custgender("male");
		addcust.custdob("12", "15", "1979");
		Thread.sleep(3000);
		addcust.custaddress("Bird Lane");
		addcust.custcity("Leicester");
		addcust.custstate("Leicestershire");
		addcust.custpinno("1234567");
		addcust.custtelephoneno("07790992246");
		
		String email = randomStringGenerator() + "@gmail.com";
		
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		Thread.sleep(3000);
		
		logger.info("validation started...");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully");
		
		if (res==true)
		{
			Assert.assertTrue(true);
			logger.info("test Case has passed...");
		}
		else
		{
			logger.info("test case has failed...");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	
}
