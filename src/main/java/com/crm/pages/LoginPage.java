package com.crm.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.TestBase.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
    WebElement loginbtn;
	
	@FindBy(xpath="//button[@type='button']")
	WebElement signupbtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmlogo;
	
	public LoginPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCrmLogo()
	{
		return crmlogo.isDisplayed();
	}
	public HomePage login(String un,String pd)
	{
		username.sendKeys(un);
		password.sendKeys(pd);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginbtn);
		//loginbtn.click();
		return new HomePage();
		
	}
	
}
