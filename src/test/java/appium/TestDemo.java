/**
 * @Project Name :  testunitnew
 * @Package Name :  appium
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-08 15:26
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDemo{

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //先指定隐士等待，否则无法运行成功
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_open");//开启权限
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/image_cancel");//更新版本
        el4.click();
        Thread.sleep(10000);
        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el5.click();
        MobileElement el6 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el6.click();
        el6.sendKeys("alibaba");
        MobileElement el7 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el7.click();
        MobileElement el8 = (MobileElement) driver.findElementById("com.xueqiu.android:id/current_price");
        el8.click();
    }

 /*   @After
    public void tearDown() {
        driver.quit();
    }*/
}
