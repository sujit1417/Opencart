package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LF_001_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void LoginTest()
	{
		logger.info("***** Starting TC_LF_001_LoginTest *****");
		
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickLogin();
		logger.info("Clicked on Login link");
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setLoginEmail(pobj.getProperty("username"));
		lp.setLoginPassword(pobj.getProperty("password"));
		lp.clickLogin();
		logger.info("Clicked on Login button");
		
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean headerVisibility=map.isMyAccountPageDisplayed();
		
		if(headerVisibility)
		{
			logger.info("Test passed");
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test failed");
			logger.debug("Debug logs..");;
			Assert.fail();
		}
		logger.info("***** Finished TC_LF_001_LoginTest *****");
	}

}
