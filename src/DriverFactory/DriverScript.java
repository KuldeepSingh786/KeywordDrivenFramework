package DriverFactory;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.Functionlibaries;
import Constants.PrimusBankConstants;
import Utility.ExcelUtilMethods;
public class DriverScript  extends PrimusBankConstants 
{
	public static Logger log;
	public static ExtentReports report;
	public static ExtentTest logger;
	
	//storing xl path
	String inputpath="./Input/Controller.xlsx";
	String outpath="./Output/KeywordResults.xlsx";
	String xtendPath="./Reports/keywordExtentReport.html";
	String tcsheet="TestCases";
	String tssheet="TestSteps";
	
	//creating reference object for all function library to call methods
	Functionlibaries pb=new Functionlibaries();
	
	@Test
	public void keywordDrivenTest() throws IOException, InterruptedException
	{
	//creating reference object for XlReader class
	ExcelUtilMethods xl=new ExcelUtilMethods(inputpath);
	
	//give path to store extents report.html
	report=new ExtentReports(xtendPath);	
	logger=report.startTest("keywordDrivenTest","Verify Primusbnak testcases");
	
	//creating object for logger class
	log=Logger.getLogger("DriverScript");
	
	//loading log4j property file
	PropertyConfigurator.configure("E:\\SeleniumWork\\KeywordDrivenFramework\\src\\config\\Log4j.properties");	
	String Execute,TestCase_Id,Teststep_Id,keyword;
	boolean res=false;
	String tcres="";
	
	//counting rows in test case sheet
	int tccount=xl.getRowCount(tcsheet);
	log.info("counting no of rows in "+tcsheet+" sheet::"+tccount);
	logger.log(LogStatus.INFO,"counting no of rows in "+tcsheet+" sheet"+tccount );
	
	//counting rows in test steps sheet
	int tscount=xl.getRowCount(tssheet);
	log.info("Counting no of rows in "+tssheet+" sheet::"+tscount);
	logger.log(LogStatus.INFO,"Counting no of rows in "+tssheet+" sheet"+tccount );
	
	//iterate all rows in testcase sheet
	for (int i = 1; i <= tccount; i++) 
	{
		log.info("iterate "+tcsheet+" sheet rows");
		Execute=xl.getcellData(tcsheet, i, 2);
		log.info("Getting 3rd column from "+tcsheet+" sheet");
		logger.log(LogStatus.INFO,"Getting 3rd column from "+tcsheet+" sheet::"+tccount );
		
		if (Execute.equalsIgnoreCase("Y")) 
		{
			log.info("Ignoring case sensitive");
			
			//reading TestCase Id column from testcase sheet
			TestCase_Id=xl.getcellData(tcsheet, i, 0);
			log.info("Getting 1st sheet from sheet1::"+TestCase_Id);
			logger.log(LogStatus.INFO,"Getting 1st sheet from sheet1::"+TestCase_Id );
			
			//iterate all rows in sheet 2(TestSteps)
			for (int j = 1; j <=tscount ; j++) 
			{
					log.info("iterate "+tssheet+" sheet rows");
					Teststep_Id=xl.getcellData(tssheet, j, 0);
					if (TestCase_Id.equalsIgnoreCase(Teststep_Id)) 
						{
						//reading keyword column from TestStep sheet
						keyword=xl.getcellData(tssheet, j, 3);
						log.info("Getting keword sheet from "+tssheet+"sheet");
						switch (keyword.toUpperCase()) 
						{
							case "ADMINLOGIN":
								res=pb.Verify_adminLogin("Admin", "Admin");
								log.info("Calling admin login method:::"+res);
								logger.log(LogStatus.PASS,"Calling admin login method:::"+res );
								break;
								
							case "NEWBRANCHCREATION":
								pb.navigateToBranchDetailsPage();			
								res=pb.Verify_BranchCreation("Ameerpet45567", "Hyderabad", "Ameerpet", "12345", "INDIA", "Andhra Pradesh", "Hyderabad");
								log.info("Calling branchcreation method::"+res);
								logger.log(LogStatus.PASS,"Calling branch creation method method:::"+res );
								break;
								
							case"UPDATEBRANCH":
								pb.navigateToBranchDetailsPage();
								res=pb.verify_Branchupdation("Testing1234", "Ameerpet");
								log.info("Calling branch updation method::"+res);
								logger.log(LogStatus.PASS, "Calling branch updation method::"+res);
								break;
								
							case "ADMINLOGOUT":
								res=pb.Verify_adminLogout();
								log.info("Calling admin logout method");
								break;
						}
						String tsres=null;
						if (res) 
						{
							tsres="Pass";
							xl.setCellDatanewfile(tssheet, j, 4, tsres, outpath);
							log.info("writing results into second sheet 4 column::"+tsres);
							logger.log(LogStatus.PASS,"writing into results column in sheet two::"+tsres);
							xl.fillGreenColor(tssheet, j, 4, outpath);
						} 
						else 
						{
							tsres="Fail";							
							xl.setCellDatanewfile(tssheet, j, 4, tsres, outpath);
							log.info("writing results into second sheet 4 column:::"+tsres);
							logger.log(LogStatus.FAIL,"writing into results column in sheet two::"+tsres);
							xl.fillRedColor(tssheet, j, 4, outpath);
						}
						if (!tcres.equalsIgnoreCase("FAIL")) 
						{
							//assign teststep results to testcase results
							tcres=tsres;
						}
					}//End of If
			}//End of for loop
			xl.setCellDatanewfile(tcsheet, i, 3, tcres, outpath);
			logger.log(LogStatus.FAIL,"writing into results column in sheet one::"+tcres);
			if (tcres.equalsIgnoreCase("PASS")) 
			{
				xl.fillGreenColor(tcsheet, i, 3, outpath);
			}
			else 
				xl.fillRedColor(tcsheet, i, 3, outpath);
			}//End of if
	else 
	{
		xl.setCellDatanewfile(tcsheet, i, 3, "Blocked", outpath);
		log.info("writing blocked into first sheet 4 column");
		logger.log(LogStatus.UNKNOWN,"writing into results column in sheet one");
	}
}
	report.endTest(logger);
	report.flush();
  }
}









