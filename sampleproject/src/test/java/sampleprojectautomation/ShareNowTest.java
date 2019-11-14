package sampleprojectautomation;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ShareNowTest {
	public static URL url;
	  public static DesiredCapabilities capabilities;
	  public static AndroidDriver<MobileElement> driver;
	  
	  
	  @BeforeTest
	  public void setupAppium() throws MalformedURLException {
	    
	    final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
	    url = new URL(URL_STRING);
	
	    capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
	    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability("deviceName","8e2e6b1b");//Please provide your device name here
	    capabilities.setCapability("platformVersion", "9");
	    capabilities.setCapability("platformName","Android");
	    capabilities.setCapability("locationServicesAuthorized", true);
	    capabilities.setCapability("appPackage", "com.car2go");
	    capabilities.setCapability("appActivity", ".activity.MainActivity");
	    driver = new AndroidDriver<MobileElement>(url, capabilities);
	    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    
	
	  }
	  //5
	  @AfterTest
	  public void uninstallApp() throws InterruptedException {
		//driver.findElement(By.id("com.car2go:id/account_logout")).click();
		driver.closeApp();
	    driver.quit();
	  }
	  //6
	  //Verifies all acceptance criteria of US2
	  @Test (enabled=true) public void atestLoginSuccessful() throws InterruptedException {
		 if (driver.findElementsById("com.android.packageinstaller:id/permission_allow_button").size()>0)
	     driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
	     Thread.sleep(1000);
	     driver.findElement(By.id("com.car2go:id/menuItem")).click();
	     Thread.sleep(1000);
	     String buttonStatus=driver.findElement(By.id("com.car2go:id/loginRegisterButton")).getAttribute("enabled");
	     System.out.println("status of Button "+buttonStatus);
	     Assert.assertEquals(buttonStatus, "true");
	     driver.findElement(By.id("com.car2go:id/loginRegisterButton")).click();
	     Assert.assertEquals(driver.findElement(By.id("com.car2go:id/loginTextView")).getAttribute("enabled"), "true");
	     driver.findElement(By.id("com.car2go:id/loginTextView")).click();
	     Thread.sleep(1000);
	     Set<String> contextSet = driver.getContextHandles();
 		for(String contextName: contextSet) {
 			System.out.println("context Name " + contextName);
 			driver.context(contextName); //Changing the context
 		}
	     System.out.println("Size of android.widget.EditText "+driver.findElementsByClassName("android.widget.EditText").size());
	     Assert.assertEquals(driver.findElements(By.className("android.widget.EditText")).get(0).getAttribute("enabled"), "true");
	     Assert.assertEquals(driver.findElements(By.className("android.widget.EditText")).get(1).getAttribute("enabled"), "true");
	     driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys("nataliya.lashchyk+testchallenge@gmail.com");
	     driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("Aa123456");
	     Assert.assertEquals(driver.findElements(By.className("android.widget.EditText")).get(1).getText(), "••••••••");
	     Assert.assertEquals(driver.findElement(By.className("android.widget.Button")).getAttribute("enabled"), "true");
	     driver.findElement(By.className("android.widget.Button")).click();
	     Assert.assertEquals(driver.findElement(By.id("com.car2go:id/profile")).getText(), "Nataliia Lashchyk");
	     driver.findElement(By.id("com.car2go:id/profile")).click();
//	    
	  }
	  //Verifies User Profile details from US2
	  @Test (enabled=true) public void btestLoggedInUserProfile() throws InterruptedException {
//		  driver.findElement(By.id("com.car2go:id/menuItem")).click();
//		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/profile")).getText(), "Nataliia Lashchyk");
//		  driver.findElement(By.id("com.car2go:id/profile")).click();
		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/accountUserName")).getText(), "Nataliia Lashchyk");
		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/accountUserAddress")).getText(), "Münchenerstr 12, 80539 München");
		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/accountUserEmail")).getText(), "nataliya.lashchyk+testchallenge@gmail.com");
		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/accountUserPhone")).getText(), "+*************6734");
		  
	}
	//Verifies Request Pin from US2
	  @Test (enabled=true) public void ctestEditUserInfo() throws InterruptedException {
//		  driver.findElement(By.id("com.car2go:id/menuItem")).click();
//		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/profile")).getText(), "Nataliia Lashchyk");
//		  driver.findElement(By.id("com.car2go:id/profile")).click();
		  driver.findElement(By.id("com.car2go:id/editAccountInformation")).click();
		  Thread.sleep(3000);
		  int viewSize=driver.findElements(By.className("android.view.View")).size();
		  System.out.println("Size of android.view.View is "+viewSize);
		  for (int i=0;i<viewSize;i++) {
			  if (driver.findElements(By.className("android.view.View")).get(i).getText().equals("nataliya.lashchyk+testchallenge1@gmail.com")) {
			  	driver.findElements(By.className("android.view.View")).get(i).click();
			  	break;
			  }
		  }
		  driver.findElement(By.className("android.widget.EditText")).sendKeys("nataliya.lashchyk+testchallenge1@gmail.com");
		  driver.findElement(By.className("android.widget.Button")).click();
	}
	//Verifies Invooices for November 2019 from US5
	  @Test (enabled=true) public void dtestInvoices() throws InterruptedException {
//		  driver.findElement(By.id("com.car2go:id/menuItem")).click();
//		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/profile")).getText(), "Nataliia Lashchyk");
		  Thread.sleep(3000);
		  driver.findElement(By.className("android.widget.ImageButton")).click();
		  driver.findElement(By.className("android.widget.ImageButton")).click();
		  driver.findElement(By.id("com.car2go:id/rentals")).click();
		  Assert.assertEquals(driver.findElement(By.id("com.car2go:id/group_rentals_title")).getText(), "November 2019");
		  Assert.assertEquals(driver.findElements(By.className("android.widget.TextView")).get(2).getText(), "No trips");
	}
	   
		
}
