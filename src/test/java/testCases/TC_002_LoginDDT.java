package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


//dataprovider class helps in avoiding loops

public class TC_002_LoginDDT extends baseClass {
	
	@Test(dataProvider="LoginData" ,dataProviderClass=DataProviders.class,groups="Datadriven")
	//getting dataprovider from different class
	public void verify_LoginDDT(String email, String pwd, String exp) {
		
		logger.info("******Starting TC_002_LoginDDT*******");
		try {
	HomePage hp= new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	
	
	LoginPage lp= new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	MyAccountPage macc= new MyAccountPage(driver);
	 boolean targetpage= macc.isMyAccountPageExists();
	 
	//case1: valid credentials- login succesfull-test passed- logout
	//case2: valid credentials- login unsuccesfull-test failed
	//case3: invalid credentials- login succesfull-testfailed-logout
	//case4: invalid credentials- login unsuccesfull-test passed

	 
	 if(exp.equalsIgnoreCase("valid")) {
		 if(targetpage==true) {
			 Assert.assertTrue(true);
			 macc.clickLogout();
		 }
		 else {
			 Assert.assertTrue(false);
		 }
	 }
	 
	 if(exp.equalsIgnoreCase("invalid")) {
		 if(targetpage==false) {
			 Assert.assertTrue(true);
		 }
		 else 
		 {
			 macc.clickLogout();
			Assert.assertTrue(false);
		 }
	 }}
		catch(Exception e) {
			Assert.fail();
		}
	 
	 logger.info("******finished TC_002_LoginDDT*******");
	}
	
	

}
