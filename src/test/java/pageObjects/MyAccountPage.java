package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//constructor
	
	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeader;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	//action methods
	public boolean isMyAccountPageDisplayed()
	{
		try
		{
		return (msgHeader.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}

}
