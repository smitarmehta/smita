package snapengageTestNG;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class snapengageLogin {
	WebDriver driver = new ChromeDriver();
  @Test
  public void snapengageTestLogin() throws Exception {
	  	
	    String appUrl = "https://snapengage-qa.appspot.com/signin?to=hub";
	    driver.get(appUrl);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    driver.findElement(By.name("email")).sendKeys("pedroalmodovar@test.com");
	    driver.findElement(By.name("password")).sendKeys("1q2w3e");
	    driver.findElement(By.name("Submit")).click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    String expectedUrl= driver.getCurrentUrl(); 
	    Assert.assertEquals(expectedUrl,"https://snapengage-qa.appspot.com/hub"); 	    
  }
  @AfterTest
  public void teardown() throws Exception{

	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(scrFile, new File("screenshot.png"));
	  driver.close();
	  driver.quit();
  }
}
