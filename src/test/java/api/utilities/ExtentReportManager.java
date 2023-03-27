package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends TestListenerAdapter
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Tile of report
		sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Pet Stores User API");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","veeresh");
		
		
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		test=extent.createTest(tr.getName()); // create new entry in th report
		test.assignCategory(tr.getMethod().getGroups());
		test.createNode(tr.getName());
		test.log(Status.PASS,"Test Passed"); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		test=extent.createTest(tr.getName()); // create new entry in th report
		test.assignCategory(tr.getMethod().getGroups());
		test.createNode(tr.getName());
		test.log(Status.FAIL,"Test Failed");
		test.log(Status.FAIL,tr.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{		test=extent.createTest(tr.getName()); // create new entry in th report
	test.assignCategory(tr.getMethod().getGroups());
	test.createNode(tr.getName());
	test.log(Status.SKIP,"Test Skipped");
	test.log(Status.SKIP,tr.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
