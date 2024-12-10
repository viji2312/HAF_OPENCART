package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
 
 
public class baseClass {
	public static WebDriver driver;
	public Logger logger;
	Properties prop;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	@SuppressWarnings("deprecation")
	public void setup( String os,String browser) throws IOException, InterruptedException {
		
		//loading config.properties
		//filereader is an alternative for fileinputstream.
		
		FileReader file= new FileReader("./src//test//resources//config.properties");
		prop= new Properties();
		prop.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		//this.getclass() get classname dynamically at the run time  & gets logger for class and stores in a variable logger
	
		if(prop.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities capabilities= new DesiredCapabilities();
			
					//capabilities.setPlatform(Platform.WIN10);
					//capabilities.setBrowserName("chrome");
			
			//to get platform and browsername from xmlfile and launch on grid 
			//for getting os 
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
				
			}
			else if(os.equalsIgnoreCase("linux")){
				capabilities.setPlatform(Platform.LINUX);
				
			}
			else{
				System.out.println("no matching os");
				return;//to exit out of conditional statement
				
			}
			
			//for getting browsername from xml  and application on grid
			
			switch(browser.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			//MicrosoftEdge is keyword for edge browser
			default:
				System.out.println("no matching browser");
				return;
			}
			String hubUrl = "http://192.168.2.15:4444/wd/hub";
			//to launch url in remote env
			driver= new RemoteWebDriver(new URL(hubUrl),capabilities);
			
			
		}
		
			//if execution environment is local this is used below one
		if(prop.getProperty("execution_env").equals("local")) 
		{
		switch(browser.toLowerCase())
		{
		case"chrome":driver= new ChromeDriver();break;
		case"edge":driver= new EdgeDriver();break;
		case"firefox":driver= new FirefoxDriver();break;
		default: System.out.println("Invalid browser");return;
		
		}
			
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	

	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	public String randomstring() {
		@SuppressWarnings("deprecation")
		String generatedstring=RandomStringUtils.randomAlphanumeric(5);
		return generatedstring;
	}
	
	
	public String randomnumber() {
		String generatednumber=RandomStringUtils.randomAlphanumeric(10);
		return generatednumber;
	}
	
	public String randomalphanumeric() {
		String generatedstring=RandomStringUtils.randomAlphanumeric(5);
		String generatednumber=RandomStringUtils.randomAlphanumeric(5);
		return(generatedstring+generatednumber);
		//return(generatedstring+"@"+generatednumber);// added special character with concatination 
		
	}
	//tname= testname
	public String capturescreen(String tname) {
		String timestamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	TakesScreenshot ts= (TakesScreenshot) driver;
	 File sourcefile=ts.getScreenshotAs(OutputType.FILE);
	 String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
	 File targetFile= new File(targetFilePath);
	 sourcefile.renameTo(targetFile);
		
		return targetFilePath ;
		
		//return tname;
		
	}
}
