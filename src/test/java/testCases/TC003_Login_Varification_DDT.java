package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
public class TC003_Login_Varification_DDT extends BaseClass //to run this test case download msexcel in your PC 
{
	@Test(dataProvider="LoginData1",dataProviderClass=DataProviders.class,groups= {"DataDriven","Master"})
	//data provider method LoginData1 is direct method in dataproviders class without using Excel
	//LoginData method is associated with the excel file which is not working in this system
	public void LoginVarificationDDT(String email,String pwd,String exp)
	{
		
		try
		{
			logger.info("****starting TC003_Login_Varification_DDT****");
			
			//Home Page
			HomePage hp=new HomePage(driver);
			hp.ClickOnMyacc(); 
			hp.ClickOnLoginLink();
		
			//Login Page
			LoginPage lp=new LoginPage(driver);
			lp.Setemail(email);
			lp.Setpwd(pwd);
			lp.ClickOnLoginBtn();
			
			
			//My Account Page
			MyAccountPage ap=new MyAccountPage(driver);
			boolean targetPage=ap.IsMyPageExsist();
		
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetPage==true)
				{
					ap.ClickOnLogout();
					Assert.assertTrue(true);
			    }
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetPage==true)
				{
					ap.ClickOnLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****Finished TC003_Login_Varification_DDT****");
		
		
	}

}
