package com.crm.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.TestBase.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.TaskPage;

public class HomePageTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		Initalize();
		loginpage=new LoginPage();
		homepage=new HomePage();
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		homepage=loginpage.login(prop.getProperty("user"),prop.getProperty("pass"));
		
	}
	
	@Test(priority=1)
	public void verifyhometitle()
	{
		homepage.verifyname();
		
	}
	
	@Test(priority=2)
	public void click()
	{
		//driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		homepage.clickonContacts();
		
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
	
	

}
