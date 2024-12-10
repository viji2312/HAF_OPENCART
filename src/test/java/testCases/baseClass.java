package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
 
 
public class baseClass {
	public static WebDriver driver;
	public Logger logger;
	Properties p;
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup( String os,String br) throws IOException {
		
		//loading config.properties
		//filereader is an alternative for fileinputstream.
		
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		//this.getclass() get classname dynamically at the run time  & gets logger for class and stores in a variable logger
		switch(br.toLowerCase()){
		case"chrome":driver= new ChromeDriver();break;
		case"edge":driver= new EdgeDriver();break;
		case"firefox":driver= new FirefoxDriver();break;
		default: System.out.println("Invalid browser");return;
		
	
			
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	private void Switch() {
		// TODO Auto-generated method stub
		
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
