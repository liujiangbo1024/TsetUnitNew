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

/**
 * @Description  : 公告群发功能
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-02 14:48
 */
public class Broadcast extends BasePage {

    //公告群发功能
    public Broadcast send(String app,String range,String title,String body,String summary,String auther){
        findElement(By.linkText("选择需要发消息的应用")).click();
        findElement(By.cssSelector(".ww_icon_AppNotice")).click();
        findElement(By.linkText("确定")).click();
        findElement(By.linkText("选择发送范围")).click();
        System.out.println(driver.getWindowHandles());
        System.out.println(driver.getPageSource());

        //1. js执行时间超长，使用page load策略
        findElement(By.id("memberSearchInput")).sendKeys(range);
        //加载很慢，load模式可以配置,延迟，是否有frame
        findElement(By.cssSelector("ww_searchResult_title_peopleName")).click();
        findElement(By.linkText("确认")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
        //2. 切换成frame
        driver.switchTo().frame(0);
        //<body class="view mpnews msg_noticeEditor_frameBody" contenteditable="true" spellcheck="false"><p><br></p></body>
        findElement(By.cssSelector(".msg_noticeEditor_frameBody")).sendKeys(body);
        Object[] windows=driver.getWindowHandles().toArray();
        for(int i=0;i<windows.length;i++){
            System.out.println(windows[i]);
        }

        driver.switchTo().window(windows[0].toString());
        System.out.println(driver.getPageSource());
        //3. 从frame切换回来
       // driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
        //切换前后的pagesource
        System.out.println(driver.getPageSource());


        //4. 点击后摘要的窗体发生变化 滚屏的问题，可视化的问题
        //依赖于上面的一次点击，才出现元素
        //<div class="msg_edit_infoItem_textTitle">摘要:</div>
        findElement(By.cssSelector("msg_edit_infoItem_textTitle")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);
        findElement(By.cssSelector(".js_amrd_sendName")).sendKeys(auther);

        //5. 看看有几个这个发送 的元素，2个发送按钮，
        //解决方法，点击y轴的时候，y轴小的那个发送，那个浮动在上面的点击
        //或者 活动到最下方，点击最下面的发送
        driver.findElements(By.linkText("发送")).forEach(element->{

            System.out.println(element.isDisplayed());
        } );
        findElement(By.linkText("发送")).click();

        return this;

    }
}