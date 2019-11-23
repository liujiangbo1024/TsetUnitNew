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

import org.openqa.selenium.By;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:27
 */
public class SearchPage extends BasePage {

    private By inputBox=By.id("com.xueqiu.android:id/search_input_text");

    public SearchPage search(String keyWord) throws IOException {
        HashMap<String,Object> data=new HashMap<>();
        data.put("keyword",keyWord);
        setParams(data);
        parseSteps("search");
       // App.driver.findElement(inputBox).sendKeys(keyWord);
        //findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]")).click();
        //findElementAndClick(By.id("com.xueqiu.android:id/name"));//找到多个，点击第一个

        return this;
    }

    public Float getCurrentPrice() {
       // MobileElement el5 = (MobileElement) findElement(By.id("com.xueqiu.android:id/current_price"));
        //el5.click();
        //return Float.valueOf(el5.getText());
        parseSteps("getCurrentPrice");
        return Float.valueOf(getResults().get("price").toString());
    }

    //输入的那个内容每次输入后，清除
    public App cancel(){
        //findElementAndClick(By.id("com.xueqiu.android:id/action_close"));
        //parseSteps("cancel");
        parseSteps(Thread.currentThread().getStackTrace()[1].getMethodName());

        return new App();
    }
    //点击“加自选”
    public SearchPage select(){
        //findElementAndClick(By.id("com.xueqiu.android:id/follow_btn"));
        parseSteps("select");
        return new SearchPage();

    }

}