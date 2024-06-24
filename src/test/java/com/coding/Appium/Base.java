package com.coding.Appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException{
	
	//Run Appium server
	 service = new 	AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\DELL\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
	 
	 service.start();
	
	//Create capabilities 
	UiAutomator2Options options = new UiAutomator2Options();
	options.setDeviceName("My Pixel 2 API 24");
	//options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\ApiDemos-debug.apk");
	options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
	
	//Create object for AndroidDriver/IOS Driver
	driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
	
	}
	
	 //Scroll to end of the app
	 public void scrollToEnd() {
    	 boolean canScrollMore;
 		do {
 		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
 			    "left", 100, "top", 100, "width", 200, "height", 200,
 			    "direction", "down",
 			    "percent", 3.0
 			));
 		
 		} while(canScrollMore);
     }
     
	 //Scroll to specific element
     public void scrollToElement(String ele) {
    	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ele\"));"));
     }
     
     
   //perform swipe action
     public void swipeAction(WebElement ele, String swipeDirection) {
    	
 		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
 				"elementId", ((RemoteWebElement) ele).getId(),
 			    "direction", swipeDirection,
 			    "percent", 0.75
 			));	
     }
	
	@AfterClass
	public void TearDown() {
		driver.quit();
		service.stop();
	}
}
