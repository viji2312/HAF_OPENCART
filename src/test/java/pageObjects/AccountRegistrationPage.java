package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	//The super keyword refers to superclass (parent) objects.
			//It is used to call superclass methods, and to access the superclass constructor.

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div//h1[contains(text(),'Your Account Has Been Created!')]")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	

	public void setTelephone(String telephone) {
		 txtTelephone.sendKeys(telephone);
	}
	
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	} 
	
	
	public void setConfirmPassword(String pwd) {
		 txtConfirmPassword.sendKeys(pwd);
	}
	
	
	public void setPrivacyPolicy() {
		chkdPolicy.click();
	}
	
	
	public void clickContinue() {
		//solution 1
		 btnContinue.click(); 
		 
		//solution 2
		 //btnContinue.submit();
		 
		 //solution3 using actions class
		  /*Actions act = new Actions(driver);
		  act.moveToElement(btnContinue).build().perform();*/
		 
		 //solution3
		/* JavascriptExecutor js= (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();",btnContinue);*/
		 
		 //solution4 using keyboard actions
		// btnContinue.sendKeys(Keys.RETURN);
		 
		 //solution 5 using explicit wait
		  /*WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();*/}
		 
	
	
	//here we are getting only msg, not validating since validation will be done only in testcases
		 public String getConfirmationMsg() {
			try{return(msgConfirmation.getText());}
			catch(Exception e) {
				return(e.getMessage());
			}
			
		}
		 
		 
	
	
	
	
}
