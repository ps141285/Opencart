package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_Login_Varification extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void Login_Page_Varification()
	{
		try
		{
		logger.info("***TC002_Login_Varification started***");//add logger
		
		HomePage hp=new HomePage(driver);//creating home page object
		hp.ClickOnMyacc();
		hp.ClickOnLoginLink();
		
		LoginPage lp=new LoginPage(driver);//creating login page object
		lp.Setemail(p.getProperty("email"));
		lp.Setpwd(p.getProperty("password"));
		lp.ClickOnLoginBtn();
		
		MyAccountPage myacc=new MyAccountPage(driver);//creating my account page 
		boolean target_page=myacc.IsMyPageExsist();
		//Assert.assertEquals(target_page, true,"Login failed");
		Assert.assertTrue(target_page);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***TC002_Login_Varification Finished***");
		
		
		}

}
