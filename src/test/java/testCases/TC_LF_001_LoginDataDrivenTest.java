package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_LF_001_LoginDataDrivenTest extends BaseClass{
	
	/*Data is valid  - login success - test pass  - logout
	  Data is valid  - login failed - test fail

	  Data is invalid - login success - test fail  - logout
	  Data is invalid - login failed - test pass
	 */
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"Datadriven","Master"})
	public void LoginDDT(String username, String password, String expresult)
	{
		logger.info("***** Starting TC_LF_001_LoginDataDrivenTest *****");
		
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setLoginEmail(username);
		lp.setLoginPassword(password);
		lp.clickLogin();
		logger.info("Clicked on Login button");
		
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean headerVisibility=map.isMyAccountPageDisplayed();
		
		if(expresult.equalsIgnoreCase("Valid"))
		{
			if(headerVisibility)		//login success
			{
				logger.info("Test passed");
				map.clickLogout();
				Assert.assertTrue(true);
			}
			
			else						//login fail
			{
				logger.error("Test failed");
				logger.debug("Debug logs..");;
				Assert.fail();
			}
		}
		
		if(expresult.equalsIgnoreCase("Invalid"))
		{
			if(headerVisibility)		//login success
			{
				logger.error("Test failed");
				logger.debug("Debug logs..");
				map.clickLogout();
				Assert.fail();		
			}
			
			else						//login fail
			{
				logger.info("Test passed");
				Assert.assertTrue(true);
			}
		}

		logger.info("***** Finished TC_LF_001_LoginDataDrivenTest *****");
	}
	
}
