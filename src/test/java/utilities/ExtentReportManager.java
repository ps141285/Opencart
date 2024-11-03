package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext textcontext)
	{
		//method 1 to create timestamp
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String timestamp=df.format(dt);
		
		//method 2
		//String timestamp1=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report"+timestamp+".html";
		sparkreporter=new ExtentSparkReporter("\\reports\\"+repName);
		
		sparkreporter.config().setDocumentTitle("Opencart Automation Report");
		sparkreporter.config().setReportName("OpenCart Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Moduel", "Admin");
		extent.setSystemInfo("Sub-Module", "Customer");
		extent.setSystemInfo("User",System.getProperty("user.name"));
		extent.setSystemInfo("Enviornment","QA");
		
		
		String os=textcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=textcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includegroups=textcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includegroups.toString());
		}
		
	}
	public void onSuccsess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+": got succsessfully executed");
	}
	public void onFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getMethod()+": got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try
		{
			BaseClass bc=new BaseClass();
			String imgpath=bc.CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	public void onSkipped(ITestResult result )
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP,result.getMethod()+": got Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	public void onFinished(ITestContext testcontext)
	{
		extent.flush();
		
		
		  String
		  pathofextentreport=System.getProperty("user.dir")+"\\reports\\"+repName; File
		  extentReport=new File(pathofextentreport); 
		  try {
		  Desktop.getDesktop().browse(extentReport.toURI()); } 
		  catch(IOException e) 
		  {
		  e.printStackTrace(); 
		  }
		 
	}
	
	


}
