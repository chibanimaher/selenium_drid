package jenk;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class SEl {
	
WebDriver driver;
// Create global variable which will be used in all method
ExtentReports extent;
ExtentTest logger;

@BeforeMethod
public void setup()
{
  ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Rapports/learn_automation2.html");
	
  extent = new ExtentReports();
  
  extent.attachReporter(reporter);
  
  logger=extent.createTest("LoginTest");
}    
@AfterMethod
public void tearDown(ITestResult result) throws Exception
{
	
	if(result.getStatus()==ITestResult.FAILURE)
	{
		String temp=Grid.captureScreen(driver);
		logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
	}
	else if(result.getStatus() == ITestResult.SUCCESS) {
        logger.log(Status.INFO, "Google Page opened"+logger.addScreenCaptureFromPath(Grid.captureScreen(driver)));
        String temp=Grid.captureScreen(driver);
    }
    else {
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
        logger.log(Status.PASS, "Google Page opened"+logger.addScreenCaptureFromPath(Grid.captureScreen(driver)));
        String temp=Grid.captureScreen(driver);
    }
	extent.flush();
	driver.quit();
}

@Test
public void test() throws MalformedURLException {

		DesiredCapabilities cap= new DesiredCapabilities();
		//2.ChromeOptions 
		ChromeOptions options=new ChromeOptions();
		options.merge(cap); 
		String hub_Url="http://localhost:4444/wd/hub";
	    driver = new RemoteWebDriver(new URL(hub_Url),options);
		driver.get("http://www.freecrm.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());

	}
}

