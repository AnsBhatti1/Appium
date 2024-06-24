package com.coding.Appium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SetWifii extends Base {
	
	@Test
	public void setwifi() {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click(); //uses the 2nd tagname of xpath
		driver.findElement(By.id("android:id/edit")).sendKeys("12345");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();//for common class name we use index number method get(1)
		
	}
}
