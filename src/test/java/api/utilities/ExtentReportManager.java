package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;  // for look and feel
	public ExtentReports extent; // to specify common info
	public ExtentTest test; //  to specify log entries about Tests
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
	    /*  SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt= new Date();
		  String currentdatetimestamp= df.format(dt);
   instead of this below line will fetch time stamp */ 
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		
		repName= "Test-Report-"+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssured Automation Project Report"); // Title of Report
		sparkReporter.config().setReportName("Pet Store Users API");  // name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("User", "Bindu");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));

		extent.setSystemInfo("Browser", System.getProperty("os.browser"));
			
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName()); // to include testcase class name in report 
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report	
		test.createNode(result.getName());
		test.log(Status.PASS, result.getName()+" got successfully executed");
				
	}
	
	 public void onTestFailure(ITestResult result) {
		 
		    test = extent.createTest(result.getTestClass().getName());
		    test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());	
			
			test.log(Status.FAIL, result.getName()+" got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			 }
	 
	 public void onTestSkipped(ITestResult result) {
		 
	    test = extent.createTest(result.getTestClass().getName());
	    test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());	
			
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());		 	 
	 }
	 
	 public void onFinish(ITestContext testContext) {
		 
		 extent.flush();
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try {
			Desktop.getDesktop().browse(extentReport.toURI());			
		     } 
		 catch (IOException e) {
		       e.printStackTrace();
		}
		
	/* 
	   To send the report to your team automatically through email...
	 
		 try { 
			   @SuppressWarnings("deprecation")
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	     // Create the email message
		   ImageHtmlEmail email1 = new ImageHtmlEmail();
	        email1.setDataSourceResolver (new DataSourceUrlResolver(url));
	        email1.setHostName("smtp.googlemail.com");  
		    email1.setSmtpPort(465);
		    email1.setAuthenticator(new DefaultAuthenticator("abcd@gmail.com","password"));
		    email1.setSSLOnConnect(true);
		    email1.setFrom("abcd@gmail.com"); // sender
		    email1.setSubject("Test Results");
		    email1.setMsg("Please find Attached Report...");
		    email1.addTo("XYZ@gmail.com"); // Receiver
		    email1.attach(url, "extent report", "Please check report...");
		    email.send(); // send the email
		    } 
		    catch(Exception e)
		     { e.printStackTrace();
		     }
	  */
	 }
	
	
}
