package sampleprojectautomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppTest {
  public static URL url;
  public static DesiredCapabilities capabilities;
  public static AndroidDriver<MobileElement> driver;
  //1
  @BeforeSuite
  public void setupAppium() throws MalformedURLException {
    //2
    final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
    url = new URL(URL_STRING);
//3
    capabilities = new DesiredCapabilities();
    //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
    //capabilities.setCapability(MobileCapabilityType.APP, "https://github.com/afollestad/material-dialogs/raw/master/sample/sample.apk");
    //capabilities.setCapability(MobileCapabilityType.APP,"");
    capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
    capabilities.setCapability("deviceName","8e2e6b1b");
    capabilities.setCapability("platformVersion", "9");
    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("locationServicesAuthorized", true);
    
    capabilities.setCapability("appPackage", "com.car2go");
    capabilities.setCapability("appActivity", ".activity.MainActivity");
    driver = new AndroidDriver<MobileElement>(url, capabilities);
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    
//   driver.resetApp();
  }
  //5
  @AfterSuite
  public void uninstallApp() throws InterruptedException {
    driver.removeApp("com.example.android.contactmanager");
  }
  //6
  @Test (enabled=true) public void myFirstTest() throws InterruptedException {
    driver.resetApp();
  }
  /*
   * Test Name: testBasicNoTitle()
   * Given: Application is installed and launched
   * And:   User is on the home page
   * When:  User selects button "BASIC (NO TITLE)"
   * Then:  User should see popup with text "This app wants to access your location", "DISAGREE" and "AGREE"
   * When:   User selects "AGREE"
   * Then:   Popup should be dismissed
   */
  @Test (enabled=true) public void testBasicNoTitle() throws InterruptedException {
    // Find the button BASIC (NO TITLE) and click it
  //driver.findElementById("com.afollestad.materialdialogssample:id/basic").click();
	  driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();  
    // Assert the presence of the popup title, AGREE and DISAGREE buttons
    Boolean isTitlePresent = !driver.findElementsById("com.afollestad.materialdialogssample:id/md_content").isEmpty();
    Boolean isDisagreePresent = !driver.findElementsById("com.afollestad.materialdialogssample:id/md_buttonDefaultNegative").isEmpty();
    Boolean isAgreePresent = !driver.findElementsById("com.afollestad.materialdialogssample:id/md_buttonDefaultPositive").isEmpty();
    Assert.assertTrue(isTitlePresent && isDisagreePresent && isAgreePresent);
    // Assert the contents of the popup title, AGREE and DISAGREE button
    String popupTitle = driver.findElementById("com.afollestad.materialdialogssample:id/md_content").getText();
    Assert.assertEquals(popupTitle, "This app wants to access your location.");
    String disagreeText = driver.findElementById("com.afollestad.materialdialogssample:id/md_buttonDefaultNegative").getText();
    Assert.assertEquals(disagreeText, "DISAGREE");
    String agreeText = driver.findElementById("com.afollestad.materialdialogssample:id/md_buttonDefaultPositive").getText();
    Assert.assertEquals(agreeText, "AGREE");
  // Click on the AGREE button
   driver.findElementById("com.afollestad.materialdialogssample:id/md_buttonDefaultPositive").click();
  // Assert that the popup is no longer visible
    Boolean isTitleStillPresent = !driver.findElementsById("com.afollestad.materialdialogssample:id/md_content").isEmpty();
    Assert.assertFalse(isTitleStillPresent);
  }
}