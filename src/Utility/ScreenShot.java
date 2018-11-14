package Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenShot {
	
	public void screenShot(String path,WebDriver driver)
	{
		DateFormat df=new SimpleDateFormat("dd-mm-yyyy hh-mm-ss");
		Date d=new Date();
		String datef=df.format(d);
		//Take screenshots and store it into in variable
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(src, new File(path+datef));
			//FileUtils.copyFile(src, new File("e://SeleniumWork//Screen//PrimusHome.png"+datef));
			Reporter.log("Taking ScreenShots",true);
		}catch(IOException i)
		{
			Reporter.log(i.getMessage(),true);
		}
	}
}
