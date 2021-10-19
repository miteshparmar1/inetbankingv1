package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	//first and foremost create a WebDriver object
	WebDriver ldriver;
	
	//constructor needs to be public since it's being accessed outside the package
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	//deal with the Warning page before the main page loads
	@FindBy(id="details-button")
	@CacheLookup
	WebElement clickButton;
	
	@FindBy(id="proceed-link")
	@CacheLookup
	WebElement clickLink;
	
	//add the three elements that are present in the login page
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnkLogout;
	
	
	
	public void clickAdvancedButton()
	{
		clickButton.click();
	}
	
	public void clickURLLink()
	{
		clickLink.click();
	}
	
	
	public void setUserName(String uname)
	{	
		txtUserName.sendKeys(uname);	
	}
	
	public void setPassword(String pwd)
	{	
		txtPassword.sendKeys(pwd);	
	}
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
}