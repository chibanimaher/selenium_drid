import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import jdk.jfr.Configuration;
import jdk.nashorn.internal.runtime.Context;

public class Grid {
	public static WebDriver driver;
	public static void main(String[] args) throws  InterruptedException, IOException, ParseException {
		//1.define desired cap 
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WIN10);
		//2.ChromeOptions 
		ChromeOptions options=new ChromeOptions();
		options.merge(cap); 
		String hub_Url="http://localhost:4444/wd/hub";
		WebDriver driver = new RemoteWebDriver(new URL(hub_Url),options);
		driver.get("http://www.freecrm.com"); System.out.println(driver.getTitle());

	}

}
