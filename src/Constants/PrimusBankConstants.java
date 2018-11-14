package Constants;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
public class PrimusBankConstants 
{
public static Properties p;
public static FileInputStream fi;
public static WebDriver driver;
  @BeforeMethod
public static void launchApp() throws IOException
 {
 // System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
  driver=new ChromeDriver();
  p=new Properties();
  //accessing property file path
fi=new FileInputStream("E:\\SeleniumWork\\KeywordDrivenFramework\\src\\config\\Repository.properties");
	  p.load(fi);
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 driver.get(p.getProperty("objurl"));
	 driver.manage().window().maximize();
 }
  @AfterMethod
 public static void closeApp()
 {
 driver.quit();
 }
  }





