package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id=\"input-email\"]")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement txt_pwd;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	WebElement btn_login;
	
	
	public void Setemail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void Setpwd(String pwd)
	{
		txt_pwd.sendKeys(pwd);
	}
	public void ClickOnLoginBtn()
	{
		btn_login.click();
	}
	

	

}
