package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void Setup(String os,String br) throws IOException 
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());//Including logs file for test cases
		
		//for remote machine execution (virtual machine setup)
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capability=new DesiredCapabilities();
		//OS
			if(os.equalsIgnoreCase("windows"))
			{
				capability.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capability.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capability.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matchin os");
				return;
			}
		//browser
			switch(br.toLowerCase())
			{
			case "chrome":capability.setBrowserName("chrome");break;
			case "edge":capability.setBrowserName("edge");break;
			case "firefox":capability.setBrowserName("firefox");break;
			default:System.out.println("No matching browser....");return;
			}
			
			URL remoteurl=new URL("http://localhost:4444/wd/hub");
			driver=new RemoteWebDriver(remoteurl,capability);
			
		}
		//for local machine execution
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		      switch(br.toLowerCase())
		       {
		         case "chrome":driver=new ChromeDriver();break;
		         case "edge":driver=new EdgeDriver();break;
		         case "firefox":new FirefoxDriver();break;
		         default:System.out.println("Invalid Browser....");return;
		       }
		}
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl")); //reading  url from properties file  
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void TearDown()
	{
		driver.quit();
	}
	 
	public String PWD=RandomAlphaNumeric();
	public String  RandomData()
	{
		String getstringdata=RandomStringUtils.randomAlphabetic(7);
		return getstringdata;
	}
	public String  RandomNumberData()
	{
		String getstringdata=RandomStringUtils.randomNumeric(10);
		return getstringdata;
	}
	public String RandomAlphaNumeric()
	{
		String getstringdata=RandomStringUtils.randomAlphabetic(4);
		String getstringnumericdata=RandomStringUtils.randomNumeric(3);
		return (getstringdata+getstringnumericdata);
	}
	public String CaptureScreen(String tname)
	{
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File srcfile=takescreenshot.getScreenshotAs(OutputType.FILE);//simply edit file location of screenshot 
		
		String targetfilepath=System.getProperty("user.dir")+"\\screenshot\\"+ tname + "_" + timestamp + ".PNG";
		File targetfile=new File(targetfilepath);
		
		srcfile.renameTo(targetfile);
		
		return targetfilepath;
	}

}
