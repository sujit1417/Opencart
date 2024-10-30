package testBase;

import java.io.File;
import java.io.FileInputStream;
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

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;		//log4j
public Properties pobj;
	

	@BeforeClass(alwaysRun=true)
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//loading config.properties
		FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
		pobj=new Properties();
		pobj.load(file);
		
		//logs generation
		logger=LogManager.getLogger(this.getClass());		//this keyword will ensure that logs will be generated for current class
		
		//remote execution
		if(pobj.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			switch(os.toLowerCase())
			{
				case "windows": capabilities.setPlatform(Platform.WIN11);
				break;
				case "mac": capabilities.setPlatform(Platform.MAC);
				break;
				case "linux": capabilities.setPlatform(Platform.LINUX);
				break;
				default: System.out.println("No matching operating system..");
			}
			
			//browser
			switch(br.toLowerCase())
			{
				case "chrome": capabilities.setBrowserName("chrome");
				break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge");
				break;
				case "firefox": capabilities.setBrowserName("firefox");
				break;
				default: System.out.println("No matching browser..");
				return;
			}
			
			//initiating browser driver
			@SuppressWarnings("deprecation")
			URL url=new URL("http://192.168.0.195:4444/wd/hub");	//converting string to URL type
			driver=new RemoteWebDriver(url,capabilities);
			
		}
		
		//local execution
		if(pobj.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome": driver=new ChromeDriver();
				break;
				case "edge": driver=new EdgeDriver();
				break;
				case "firefox": driver=new FirefoxDriver();
				break;
				default: System.out.println("No matching browser..");
				return;
			}
		}
		
		//driver operations
		driver.manage().deleteAllCookies();
		driver.get(pobj.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		

	}
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumeric()
	{
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphaNumeric()
	{
		return RandomStringUtils.randomAlphanumeric(6);
	}


	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		
		//copying sourcefile to targetfile
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
	

}
