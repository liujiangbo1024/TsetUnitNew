/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-02 9:42
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-02 9:42
 */
public class App extends BasePage{

//登录企业微信公众号
    public App loginWithCookie(){
        String url="https://work.weixin.qq.com/";
        driver = new ChromeDriver();
        ChromeOptions chromeOptions=new ChromeOptions();
        //chromeOptions.setCapability("pageLoad");
        //隐式等待5s
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("企业登录")).click();

        System.out.println(driver.manage().getCookies());

        driver.manage().addCookie(new Cookie("wwrtx.refid","1688852932663032"));
        driver.manage().addCookie(new Cookie("wwrtx.sid","TtNU49l4d2NMXHh3tWbk5BpEVURJrawnPvctTt94RSi15AVfoWTeJZMmQlvFyKhS"));
        driver.navigate().refresh();
        //返回当前方法
        return this;
    }

//跳转到通讯录列表页面
    public ContactPage toContact(){
        findElement(By.linkText("通讯录")).click();
        return new ContactPage();

    }

//跳转到添加成员页面
    public ContactPage toMemberAdd(){
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();

    }
//管理工具页面
    public Broadcast toGroupMessage(){
        findElement(By.id("menu_manageTools")).click();
        //<span class="ww_icon ww_icon_AppGroupMessageBig manageTools_cnt_item_icon"></span>
        //css=.manageTools_cnt_item:nth-child(3) > .manageTools_cnt_itemLin
        findElement(By.cssSelector(".ww_icon ww_icon_AppGroupMessageBig")).click();
        return  new Broadcast();
    }
}