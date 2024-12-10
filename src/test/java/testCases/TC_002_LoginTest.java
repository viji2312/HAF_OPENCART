package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTest extends baseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		
		logger.info("**** Starting TC_002_LoginTest**** ");
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc= new MyAccountPage(driver);
		 boolean targetpage= macc.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true, "Login failed");
		Assert.assertTrue(targetpage);}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("**** finished  TC_002_LoginTest**** ");
	}
	
	
	
}
