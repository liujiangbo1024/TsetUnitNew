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

        //设置浏览器的属性,page load 加载方式发生变化
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setCapability("PageLoadStrategy","eager");
        driver=new ChromeDriver(chromeOptions);
        //隐式等待5s
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("企业登录")).click();

        System.out.println(driver.manage().getCookies());

        //wwrtx.refid=0206886
        driver.manage().addCookie(new Cookie("wwrtx.refid","0206886"));
        //Cookie: pgv_pvi=4065229824; wwrtx.i18n_lan=zh; _ga=GA1.2.1547429284.1572419437; wwrtx.ref=direct; wwrtx.refid=0206886; wwrtx.ltype=1; wxpay.corpid=1970325014085755; wxpay.vid=1688852932663032; wwrtx.vid=1688852932663032; wwrtx.logined=true; Hm_lvt_9364e629af24cb52acc78b43e8c9f77d=1572419437,1572656608,1572662380; Hm_lpvt_9364e629af24cb52acc78b43e8c9f77d=1572679295; wwrtx.d2st=a6491836; wwrtx.sid=TtNU49l4d2NMXHh3tWbk5CwEZ7xtM-ryQfEvvsXAAutnEgbiVT2XhOO-XjV8B8Es; wwrtx.vst=9xQ5eAMfLZJ5S9SqO35Bs30p_qHePaxhC0MEzKO7kELvfWSBD5K14j96g5i99L-q0BR12-6Ln3_tQVmONIglONCGB-JIMOyHaM489Qj6QxOU8vhlYw9clXHHIgWWYlE_TxaFY54gXhvrFwimqTISbbl-Df0Zy-nvHykGd1RLFfnkCZlXPOZxmeJZk3t8bV2xTdnaFTrMcfYPPmg-w7LKZ96Jxmd31e4NWckHnQ6aeGKdz53V8qA7CvDgGgORFeJzY1oU7hFfYBv1FQGsvOJxLw
        driver.manage().addCookie(new Cookie("wwrtx.sid","TtNU49l4d2NMXHh3tWbk5CwEZ7xtM-ryQfEvvsXAAutnEgbiVT2XhOO-XjV8B8Es"));
        driver.navigate().refresh();
        //返回当前方法
        return this;
    }

//跳转到通讯录列表页面
    public ContactPage toContact(){
        findElement(By.linkText("通讯录"),3).click();
        return new ContactPage();

    }

//跳转到添加成员页面
    public ContactPage toMemberAdd(){
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();

    }
//管理工具页面--消息群发
    public Broadcast toGroupMessage(){
        findElement(By.id("menu_manageTools")).click();
        //css=.manageTools_cnt_item:nth-child(3) > .manageTools_cnt_itemLink
        //<span class="ww_icon ww_icon_AppGroupMessageBig manageTools_cnt_item_icon"></span>
        findElement(By.cssSelector(".ww_icon_AppGroupMessageBig")).click();
        return  new Broadcast();
    }

    //管理工具--素材库
    public MaterialLibraryPage toMaterialLibraryPage(){
        findElement(By.id("menu_manageTools")).click();
        //<span class="ww_icon ww_icon_AppMaterialBig manageTools_cnt_item_icon"></span> 点击素材库
        findElement(By.cssSelector(".ww_icon_AppMaterialBig")).click();
        return new MaterialLibraryPage();
    }
}