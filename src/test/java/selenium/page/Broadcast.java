/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-02 14:48
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  : 管理工具--消息群发页面封装--公告群发功能,消息群发的例子，加断言
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-02 14:48
 */
public class Broadcast extends BasePage {

    //公告群发功能
    public Broadcast send(String range,String title,String body,String summary,String auther){

        findElement(By.linkText("选择需要发消息的应用")).click();
        findElement(By.cssSelector(".ww_icon_AppNotice")).click();//默认选择公告
        findElement(By.linkText("确定")).click();
        findElement(By.linkText("选择发送范围")).click();


        //1. js执行时间超长，使用page load策略
        findElement(By.id("memberSearchInput")).sendKeys(range);//选择输入框，并输入内容
        //加载很慢，load模式可以配置,延迟，是否有frame
        findElement(By.cssSelector(".ww_searchResult_title_peopleName"),10).click();//点击查询出的人员名字
        findElement(By.linkText("确认"),5).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);//输入标题

        System.out.println("输出windowshanles1"+driver.getWindowHandles());
        //2. 切换成frame
        driver.switchTo().frame(0);
        //<body class="view mpnews msg_noticeEditor_frameBody" contenteditable="true" spellcheck="false"><p><br></p></body>
        findElement(By.cssSelector(".msg_noticeEditor_frameBody")).sendKeys(body);//输入正文

        //.out.println("输出windowshanles2=="+driver.getWindowHandles());
        // System.out.println("输出pagesource=="+driver.getPageSource());

        Object[] windows=driver.getWindowHandles().toArray();
        for(int i=0;i<windows.length;i++){
            System.out.println(windows[i]);
        }

        driver.switchTo().window(windows[0].toString());

        //System.out.println(driver.getPageSource());
        //3. 从frame切换回来
       // driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        //切换前后的pagesource
       // System.out.println("切换后的pagesource==="+driver.getPageSource());


        //4. 点击后摘要的窗体发生变化 滚屏的问题，可视化的问题
        //依赖于上面的一次点击，才出现元素,
        //点击摘要点不住waiting for element to be clickable: By.cssSelector: msg_edit_infoItem_textTitle (tried for 6 second(s) with 500 milliseconds interval)
        //<div class="msg_edit_infoItem_textTitle">摘要:</div>

        ((JavascriptExecutor)(driver)).executeScript("window.scroll(0,800)");
        findElement(By.cssSelector(".msg_edit_infoItem_textWord")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);//摘要
        findElement(By.cssSelector(".js_amrd_sendName")).sendKeys(auther);

        //5. 2个发送按钮，看看有几个这个发送 的元素
        //解决方法，点击y轴的时候，y轴小的那个发送，那个浮动在上面的点击
        //或者 活动到最下方，点击最下面的发送
        ((JavascriptExecutor)(driver)).executeScript("window.scroll(0,1200)");
        driver.findElements(By.linkText("发送")).forEach(element->{
            System.out.println("发送对象=="+element);
            System.out.println("发送的对象=="+element.isDisplayed());
            System.out.println("发送的对象坐标=="+element.getLocation());
        } );
        findElement(By.linkText("发送")).click();
        findElement(By.linkText("确定")).click();
        return this;

    }

    //<td class="msg_history_msgList_td">   新快递来了   </td> 断言要用的方法，获取发送的信息是否对
    public List<String> getSendMsg() {
        //findElement(By.linkText("发消息")).click();
        findElement(By.linkText("已发送")).click();
        List<String> msg = new ArrayList<>();
        driver.findElements(By.cssSelector(".msg_history_msgList_td")).forEach(element -> {
            msg.add(element.getText());
        });
        System.out.println("msg======"+msg);
        return msg;
    }
}