package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceBaseResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.baseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testcontext) {

		/*
		 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=
		 * new Date(); //to create date String currentdatetimestamp=df.format(dt);
		 */ // date generated in the form of string

		// instead of above, we can write it as
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// sparkreporter= new
		// ExtentSparkReporter(System.getProperty("user.dir")+"reports/myreports.html");
		sparkreporter.config().setDocumentTitle("Opencart Automation Report");
		sparkreporter.config().setReportName("opencart Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		// getting os and browser name dynamically from xml file
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		
		extent.setSystemInfo("Operating System",os);
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "got succesfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());//creating new  entry for failed test in log 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + "get failed");
		test.info(result.getThrowable().getMessage());//for printing error message

		baseClass bs = new baseClass();
// try  c block is added to find eception img is not loaded or captured
		try {
			String imgpath = new baseClass().capturescreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch (Exception e1) {
			e1.printStackTrace();// printstacktrace method prints the throwable along with other details like the
									// line number and class name where the exception occurred.
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "get skipped");
		test.info(result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();
		
		
		//to display output log immediately without manually opening afer test completion
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport= new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		//to send automated email
		/*try {
		
		URL url= new URL("file:///"+System.getProperty("user.dir")+"\\reports"+repName);
		
		//create email message
		ImageHtmlEmail email= new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("vijayachidanand23@gmail.com", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("vijayachidanand23@gmail.com");//host
		email.setSubject("Test Results");
		email.setMsg("please find the attacehed report");
		email.addTo("viji.vennela@gmail.com");//receiver
		email.attach(url,"extent report", "please check Report....");
		email.send();}
		catch(Exception e) {
			e.printStackTrace();
			
		}*/
		
		
	}
	
}
