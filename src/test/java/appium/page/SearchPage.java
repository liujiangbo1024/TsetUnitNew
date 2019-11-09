/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-09 10:27
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:27
 */
public class SearchPage extends BasePage {

    private By inputBox=By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord){

        App.driver.findElement(inputBox).sendKeys(keyWord);
        findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")).click();
        return this;
    }

    public Float getCurrentPrice() {
        MobileElement el5 = (MobileElement) findElement(By.id("com.xueqiu.android:id/current_price"));
        //el5.click();
        return Float.valueOf(el5.getText());
    }

    //输入的那个内容每次输入后，清除
    public App cancel(){
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));
        return new App();
    }
}