package com.crm.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.TestBase.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	HomePage homepage;
	ContactsPage contactpage;
	LoginPage loginpage;
	String sheetName="freecrm";
	
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		Initalize();
		loginpage=new LoginPage();
		homepage=new HomePage();	
		contactpage=new ContactsPage();
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		homepage=loginpage.login(prop.getProperty("user"),prop.getProperty("pass"));
		//contactpage=homepage.clickonContacts();
		homepage.clickonNewcontact();
		
	}
	
	@DataProvider
	public Object[][] getcontact()
	{
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(dataProvider="getcontact")
	public void Newcontact(String title,String Firstname,String Lastname)
	{
		contactpage.createtitle(title,Firstname,Lastname);
		contactpage.clickonsubmit();

	}

	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
	
}
