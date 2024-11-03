package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage 
{
	//constructor
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	//find XPATH
	@FindBy(xpath=("//a[@title='My Account']"))
	WebElement linkMyAcc;
	
	@FindBy(xpath=("//a[normalize-space()='Register']"))
	WebElement linkReg;
	
	@FindBy(xpath=("//a[normalize-space()='Login']"))
	WebElement linklogin;
	
	//Action Method
	public void ClickOnMyacc()
	{
		linkMyAcc.click();
	}
	public void ClickOnRegister()
	{
		linkReg.click();
	}
	public void ClickOnLoginLink()
	{
		linklogin.click();
	}
	

}
