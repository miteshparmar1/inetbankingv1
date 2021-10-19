package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	//write the test method
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException 
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name is provided");
		lp.setPassword(pwd);
		logger.info("user password is provided");
		lp.clickSubmit(); 
	
		Thread.sleep(3000);
		
		//this is a failure case
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept(); //close the alert window
			driver.switchTo().defaultContent(); //get to the login screen
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			//test passed
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout(); //click on the log out click 
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); //close the logout alert
			driver.switchTo().defaultContent();	
		}
	}
	
	
	public boolean isAlertPresent() //user defined method to check if alert box is present or not
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	//write a DataProvider method
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		//String path = "Users/Mitz/eclipse-workspace/inetBankingV1/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
	
		int rownum  = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i,j);
			}
		}
		return loginData;
	}
}