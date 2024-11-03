package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage
{
	//constructor

	public RegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//find XPATH
	
	@FindBy(xpath=("//input[@id=\"input-firstname\"]"))
	WebElement txt_fname;
	
	@FindBy(xpath=("//input[@id=\"input-lastname\"]"))
	WebElement txt_lname;
	
	@FindBy(xpath=("//input[@id=\"input-email\"]"))
	WebElement txt_email;
	
	@FindBy(xpath=("//input[@id=\"input-telephone\"]"))
	WebElement txt_phoneno;
	
	@FindBy(xpath=("//input[@id=\"input-password\"]"))
	WebElement txt_pwd;
	
	@FindBy(xpath=("//input[@id=\"input-confirm\"]"))
	WebElement txt_confrmpwd;
	
	@FindBy(xpath=("//input[@name=\"agree\"]"))
	WebElement chk_agree;
	
	@FindBy(xpath=("//input[@value=\"Continue\"]"))
	WebElement btn_continue;
	
	@FindBy(xpath=("//h1[normalize-space()=\"Your Account Has Been Created!\"]"))
	WebElement get_confrm_msg;
	
	//Action Method 
	public void  SetFirstName(String fname)
	{
		txt_fname.sendKeys(fname);
	}
	public void SetLastName(String lname)
	{
		txt_lname.sendKeys(lname);
	}
	public void SetEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void SetPhoneNo(String phone)
	{
		txt_phoneno.sendKeys(phone);
	}
	public void Setpwd(String pwd)
	{
		txt_pwd.sendKeys(pwd);
	}
	public void Setconfrmpwd(String cnfpwd)
	{
		txt_confrmpwd.sendKeys(cnfpwd);
	}
	public void ClickOnCheck()
	{
		chk_agree.click();
	}
	public void ClickOnContinue()
	{
		btn_continue.click();
	}
	public String GetConfirmationMsg()
	{
		try
		{
		String Msg_confirmation=get_confrm_msg.getText();
		return(Msg_confirmation);
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	

}
