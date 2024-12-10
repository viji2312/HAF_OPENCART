package testCases;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001_AccountRegistrationTest extends baseClass {


	
	@Test(groups= {"Regession","Master"})//master means all tests should be run
	public void verify_account_registration() {
		logger.info("*****starting TC_001_AccountRegistrationTest****** ");
	try {	
	HomePage hp= new HomePage(driver);
	hp.clickMyAccount();
	logger.info("clicked on myAccount link");
	
	hp.clickRegister();
	logger.info("clicked on Register link");
	
	AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
	logger.info("Providing customer details....");
	regpage.setFirstName(randomstring().toUpperCase());
	regpage.setLastName(randomstring().toUpperCase());
	regpage.setEmail(randomstring()+"@gmail.com");
	
	String password=randomalphanumeric();
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	regpage.setTelephone(randomnumber());
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	
	logger.info("validating expected results....");
	String confmsg=regpage.getConfirmationMsg();
	if(confmsg.equals("Your Account Has Been Created!")) {
		Assert.assertTrue(true);
	}
	else {
		
		logger.error("test is failed");
		logger.debug("Debug logs....");
		Assert.assertTrue(false);
	}
	
	
	
	//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	catch(Exception e) {
		
		Assert.fail();
	}
	
	logger.info("*****finished TC_001_AccountRegistrationTest****** ");
	}
	
}
