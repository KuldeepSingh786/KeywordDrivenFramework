package CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import Constants.PrimusBankConstants;
import Utility.Taking_Screenshot;;
/*
 * Project Name: PRimus bank
 * Module Name: Admin Login
 * 
 */
public class Functionlibaries extends PrimusBankConstants 
{
		public boolean Verify_adminLogin(String uid,String pwd) throws InterruptedException
		{
		driver.findElement(By.xpath(p.getProperty("objusername"))).sendKeys(uid);
		Thread.sleep(5000);
		driver.findElement(By.xpath(p.getProperty("objpassword"))).sendKeys(pwd);
		driver.findElement(By.xpath(p.getProperty("objloginbtn"))).click();
		String expurl,acturl;
		expurl="adminflow";
		acturl=driver.getCurrentUrl();
		if(acturl.toLowerCase().contains(expurl.toLowerCase())) 
		{
		//calling screen shot class method
		Taking_Screenshot.takescreenshot(driver, "Loginpage");
		return true;
		} else 
		Taking_Screenshot.takescreenshot(driver, "Loginpage");
		{
		return false;
		}
		}
		/*
		 * Project Name: PRimus bank
		 * Module Name: Branch Creation
		 * 
		 */
	public boolean Verify_BranchCreation(String branchname,String add1,String add2,
		String zipcode,String country,String state,String city) throws InterruptedException
		{
			driver.findElement(By.xpath(p.getProperty("objnewbranch"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(p.getProperty("objbname"))).sendKeys(branchname);
			driver.findElement(By.xpath(p.getProperty("objadd1"))).sendKeys(add1);
			driver.findElement(By.xpath(p.getProperty("objadd2"))).sendKeys(add2);
			driver.findElement(By.xpath(p.getProperty("objzcode"))).sendKeys(zipcode);
			WebElement countryelement;
			countryelement=driver.findElement(By.xpath(p.getProperty("objcountry")));
			Select countrylist=new Select(countryelement);
				try 
				{
					countrylist.selectByVisibleText(country);
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
					throw new SkipException("given Country not present in country listbox");
				}
					Thread.sleep(5000);
				WebElement stateelement;
				stateelement=driver.findElement(By.xpath(p.getProperty("objstate")));
				Select statelist=new Select(stateelement);
					try 
					{
						statelist.selectByVisibleText(state);
					}
					catch (Throwable e) 
					{
						System.out.println(e.getMessage());
						throw new SkipException("given State not Present in the List");
					}
					Thread.sleep(5000);
					WebElement cityelement;
					cityelement=driver.findElement(By.xpath(p.getProperty("objcity")));
					Select citylist=new Select(cityelement);
						try 
						{
							citylist.selectByVisibleText(city);
						} 
						catch (Exception e) 
						{
							System.out.println(e.getMessage());
							throw new SkipException("given City is not Present in the List");
						}		
					driver.findElement(By.xpath(p.getProperty("objsubmit"))).click();
					Thread.sleep(5000);
					String alertmsg=driver.switchTo().alert().getText();
					System.out.println(alertmsg);
					Thread.sleep(4000);
					driver.switchTo().alert().accept();
					//calling screenshot method
					Taking_Screenshot.takescreenshot(driver, "branchcreation");
						if (alertmsg.toLowerCase().contains("new branch with")) 
						{
						return true;
						} else 
						{
						return false;
						}
		}
		/*
		 * Project Name: PRimus bank
		 * Module Name: Admin Logout
		 * 
		 */
		public boolean Verify_adminLogout() throws InterruptedException
		{
			driver.findElement(By.xpath(p.getProperty("objlogout"))).click();
			Thread.sleep(4000);
			Taking_Screenshot.takescreenshot(driver, "Logout");
				if (driver.findElement(By.id("login")).isDisplayed()) 
					{
				return true;
					} else 
					{
				return false;
					}
		}
		/*
		 * Project Name: PRimus bank
		 * Module Name: Navigate to branch details page
		 * 
		 */
		public void navigateToBranchDetailsPage() throws InterruptedException
		{
			driver.findElement(By.xpath(p.getProperty("objBranches"))).click();
			Thread.sleep(4000);
		}
		/*
		 * Project Name: PRimus bank
		 * Module Name: Branch Updation
		 * 
		 * 
		 */
		public boolean verify_Branchupdation( String bname,String add1) throws InterruptedException
		{
				driver.findElement(By.xpath(p.getProperty("Obj_Click_Branches"))).click();
					driver.findElement(By.xpath(p.getProperty("Obj_Click_Edit"))).click();
					driver.findElement(By.xpath(p.getProperty("Obj_Update_Bname"))).clear();
					Thread.sleep(3000);
					driver.findElement(By.xpath(p.getProperty("Obj_Update_Bname"))).sendKeys(bname);
					driver.findElement(By.xpath(p.getProperty("Obj_Update_Add1"))).clear();
					Thread.sleep(3000);
					driver.findElement(By.xpath(p.getProperty("Obj_Update_Add1"))).sendKeys(add1);
					driver.findElement(By.xpath(p.getProperty("Obj_Click_Update"))).click();
					Thread.sleep(5000);
				String alertmsg=driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();
				System.out.println(alertmsg);
					if(alertmsg.toLowerCase().contains("branch updated"))
					{
						return true;
						}
					else{
						return false;
					}
		}
}






