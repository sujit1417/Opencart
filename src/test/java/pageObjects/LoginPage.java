package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	//constructor
	
	public LoginPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtLoginEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement tstLoginPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;

	//action methods
	
	public void setLoginEmail(String email)
	{
		txtLoginEmail.sendKeys(email);
	}
	
	public void setLoginPassword(String pass)
	{
		tstLoginPassword.sendKeys(pass);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}

}
