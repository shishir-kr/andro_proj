package android.andro_proj;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class test {

	public static AppiumDriver<MobileElement> driver;
	static List<MobileElement> cardList;

	public static void main(String args[]) throws MalformedURLException, InterruptedException
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		dc.setCapability("deviceName", "Pixel 2 API 28");
		dc.setCapability("udid", "emulator-5554");
		dc.setCapability("app", System.getProperty("user.dir")+"/app/SwipeRecyclerView.apk");
		dc.setCapability("automationName", "uiautomator2");

		dc.setCapability("appPackage", "com.pratap.endlessrecyclerview");
		dc.setCapability("appActivity", "com.pratap.swipe.RecyclerViewExample");

		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		cardList = driver.findElementsById("swipe");

		while(cardList.size()>0)
		{
			MobileElement chosenCard = cardList.get(0);
			org.openqa.selenium.Point centerOfElement = chosenCard.getCenter();
			TouchAction touchAction = new TouchAction(driver);
			touchAction.longPress(point(centerOfElement.x + centerOfElement.x * 50/100, centerOfElement.y)).moveTo(point(centerOfElement.x*20/100, centerOfElement.y)).release().perform();

			driver.findElementById("tvEdit").click();
			driver.findElementById("tvShare").click();
			driver.findElementById("tvDelete").click();
			Thread.sleep(1000);
			cardList = driver.findElementsById("swipe");
		}

		Thread.sleep(10000000);
	}

}
