/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-09 10:51
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:51
 */
public class BasePage {
    public static AndroidDriver driver;


    public static  WebElement findElement(By by) {
    //todo:递归的更好
        try {
            return  driver.findElement(by);
        } catch (Exception e) {
            handleAlter();

            //再次回到
            return driver.findElement(by);
        }
    }


    public static  void  findElementAndClick(By by) {
        //todo:递归的更好
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlter();
            //再次回到
             driver.findElement(by).click();
        }
    }

    private static void handleAlter() {
        List<By> alterBoxs= new ArrayList<>();
        //todo:不需要所有的都判断是否存在
        alterBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));
        alterBoxs.add(By.id("com.xueqiu.android:id/update_id_ok"));//后台更新
       // alterBoxs.add(By.xpath("yyy"));

        alterBoxs.forEach(alter->{
            By adsLocator=alter;
            List<WebElement> ads = driver.findElements(adsLocator);
            if (ads.size() >= 1) {
                ads.get(0).click();
            }
        });
    }


}