package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_RF_001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verifyAccountRegistration()
	{
		logger.info("***** Starting TC_RF_001_AccountRegistrationTest *****");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		logger.info("Providing information in all mandatory fields..");
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		arp.setFirstname(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomNumeric());
		
		String password=randomAlphaNumeric();
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		
		arp.tickPrivacyPolicy();
		arp.clickContinue();
		logger.info("Clicked on Continue");
		
		logger.info("Validating confirmation messaage..");
		String confmsg=arp.getConfirmationMsg();
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		if(confmsg.equals("Your Account Has Been Created!"))
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
		
		logger.info("***** Finished TC_RF_001_AccountRegistrationTest *****");
	
	}
	
}
