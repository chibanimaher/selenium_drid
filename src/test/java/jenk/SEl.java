package jenk;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class SEl {

@Test
public void test() throws MalformedURLException {

		DesiredCapabilities cap= new DesiredCapabilities();
		//2.ChromeOptions 
		ChromeOptions options=new ChromeOptions();
		options.merge(cap); 
		String hub_Url="http://localhost:4444/wd/hub";
		WebDriver driver = new RemoteWebDriver(new URL(hub_Url),options);
		driver.get("http://www.freecrm.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());

	}

}
