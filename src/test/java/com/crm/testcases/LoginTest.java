package com.crm.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.TestBase.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class LoginTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		Initalize();
		 loginpage=new LoginPage();
		 homepage =new HomePage();
	}
	@Test
	public void loginpagetitletest()
	{
		String title=loginpage.validateLoginPageTitle();
		Assert.assertEquals(title,"Free CRM software in the cloud powers sales and customer service");
	}
	
	@Test
	public void crmlogotest()
	{
		boolean logo=loginpage.validateCrmLogo();
		Assert.assertTrue(logo);
	}
	@Test
	public void loginpagetest()
	{
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		homepage=loginpage.login(prop.getProperty("user"),prop.getProperty("pass"));
		System.out.println(prop.getProperty("user"));
		//return homepage;
	}
   @AfterMethod
   public void quit()
   {
	   driver.quit();
   }
   
}
