package com.crm.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.TestBase.TestBase;

public class HomePage extends TestBase {
	 
	HomePage homepage;
	ContactsPage con= new ContactsPage();
	
	@FindBy(xpath="//td[contains(text(),'User: shanthi jayaraman')]")
	WebElement myname;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contact;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newcontact;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement deals;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasks;
	
	public HomePage()
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public void verifyname()
	{
		driver.switchTo().frame("mainpanel");
		String title=myname.getText();
		Assert.assertEquals("  User: shanthi jayaraman",title);	
		//return true;
		
	}
	
	public ContactsPage clickonContacts()
	{
		//driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		contact.click();
		//newcontact.click();
		return new ContactsPage();
	}
	
	public void clickonNewcontact()
	{
		driver.switchTo().frame("mainpanel");
		Actions act=new Actions(driver);
		act.moveToElement(contact).build().perform();
		//driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		newcontact.click();
	}
	public Dealspage clickonDeals()
	{
		deals.click();
		return new Dealspage();
	}
	public TaskPage clickontask()
	{
		tasks.click();
		return new TaskPage();
	}
}
