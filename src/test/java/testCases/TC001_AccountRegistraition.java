package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistraition extends BaseClass 
{

	
	@Test(groups={"Regression","Master"})
	public void Reg_test() throws InterruptedException
	{
		try
		{
		logger.info("******Starting TC001_AccountRegistraition******");
		HomePage hp=new HomePage(driver);
		
		hp.ClickOnMyacc();
		logger.info("Clicked on my account element");
		hp.ClickOnRegister();
		logger.info("Clicked on register element");
		
		logger.info("Providing user data for registration form");
		RegistrationPage regp=new RegistrationPage(driver);
		regp.SetFirstName(RandomData());
		regp.SetLastName(RandomData());
		regp.SetEmail(RandomData()+"@gmail.com");
		regp.SetPhoneNo(RandomNumberData());
		regp.Setpwd(PWD);
		regp.Setconfrmpwd(PWD);
		regp.ClickOnCheck();
		regp.ClickOnContinue();
		
		logger.info("validating the confirmation messege");
		String msgcnfrm=regp.GetConfirmationMsg();
		Assert.assertEquals(msgcnfrm, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			//logger.debug("Debug logs..");
			logger.error("Test failed..");
			Assert.fail();
			
		}
		logger.info("****** Ended TC001_AccountRegistraition ******");
	}
	
	
	
	
	
	

}
