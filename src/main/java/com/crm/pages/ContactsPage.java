package com.crm.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.TestBase.TestBase;

public class ContactsPage extends TestBase{

	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contact;
//	@FindBy(xpath="//input[@type='submit'and @value='Save'])")
//	WebElement submit;
	
public ContactsPage()
{
	PageFactory.initElements(driver, this);
}
	public void verifycontactname()
	{
		//driver.switchTo().frame("mainpanel");
		String title=contact.getText();
		Assert.assertEquals("Contacts",title);
		
	}
	
	public void createtitle(String title,String name,String Lname)
	{
		Select drop=new Select(driver.findElement(By.name("title")));
		//driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		drop.selectByVisibleText(title);;
		driver.findElement(By.name("first_name")).sendKeys(name);
		driver.findElement(By.name("surname")).sendKeys(Lname);
	}
	
	public void clickonsubmit()
	{
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		driver.findElement(By.className("button")).click();
	}

}
