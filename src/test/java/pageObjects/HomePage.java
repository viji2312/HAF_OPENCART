package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	 public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//li//a[contains(text(),'Register')]")
	WebElement lnkRegister;
	
	@FindBy(xpath="//li//a[contains(text(),'Login')]")
	WebElement lnkLogin;
	
	
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}
