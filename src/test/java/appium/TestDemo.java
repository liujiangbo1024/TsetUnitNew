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

import appium.page.App;
import io.appium.java_client.MobileElement;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

public class TestDemo{



    @Before
    public void setUp() throws MalformedURLException {

    }

    @Test
    public void sampleTest() {
        MobileElement el2 = (MobileElement) App.driver.findElementById("com.xueqiu.android:id/home_search");
        el2.click();
        MobileElement el3 = (MobileElement) App.driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.click();
        el3.sendKeys("alibaba");
        MobileElement el4 = (MobileElement) App.driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el4.click();
        MobileElement el5 = (MobileElement) App.driver.findElementById("com.xueqiu.android:id/current_price");
        el5.click();
    }

 /*   @After
    public void tearDown() {
        driver.quit();
    }*/
}
