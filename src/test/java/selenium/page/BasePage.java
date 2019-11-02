/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-02 10:30
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Description  :  公共的基础方法
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-02 10:30
 */
public class BasePage {

    public static WebDriver driver;

    //查找元素
    public WebElement findElement(By by) {
        waitClickable(by,10);
        return driver.findElement(by);
    }

    //显示等待
    public void waitClickable(By by, int timeout) {
       //等5s，直到它可以被点击
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    //显示等待 visibilityOfElementLocated 可见\elementToBeClickable可点击，需要看一下详细
    public void waitClickable(By by) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(by));
        //等5s，直到它可以被点击
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(by));
    }

    //每次浏览器进程，每次退出浏览器，20s后退出
    public void quit() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
    /*开了多个浏览器，用chrome driver打开的浏览器都是有标识的*/
}