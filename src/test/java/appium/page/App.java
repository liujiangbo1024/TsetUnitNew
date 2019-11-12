/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-09 10:17
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:17
 */
public class App  extends  BasePage{

    //使用static
    public static void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        //desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        //127.0.0.1:7555
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");//app的入口
        desiredCapabilities.setCapability("noReset",false);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        /* noreset：以下2种方式都可以 避免权限弹框
       1. noReset：true数据缓存，弹框也会缓存，不重置APP的状态
       2. noReset：false：权限，弹框的也会清空+autoGrantPermissions：true也可以  */
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//不添加隐式等待这个雪球APP进不去

      //  handleAlter();//处理那些乱七八糟额弹框
       //处理进入页面前的等待:显示等待
        new WebDriverWait(driver,30).until(x->{
            System.out.println(System.currentTimeMillis());
            String xml=driver.getPageSource();
            Boolean exist=xml.contains("home_search")||xml.contains("image_cancel");
            System.out.println(exist);
            return exist;

        });

    /*    new WebDriverWait(driver,30).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("com.xueqiu.android:id/home_search")));*/
       /*弹广告和升级的弹框处理方法如下：
        By adsLocater=By.id("xxx");
        List<WebElement> ads= driver.findElements(adsLocater);
        if(ads.size()>=1){
            ads.get(0).click();
        }*/

    }

    //点击搜索框
    public static SearchPage toSearch() {
         findElementAndClick(By.id("com.xueqiu.android:id/home_search"));
        return new SearchPage();

    }

    //自选股票
    public static  StockPage toStock(){
        findElementAndClick(By.xpath("//*[contains(@resource-id, 'tab_name') and @text='自选']"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new StockPage();
    }
}