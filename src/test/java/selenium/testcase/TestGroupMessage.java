/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-03 22:42
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

/**
 * @Description  :  自动化测试企业微信的管理工具中的群发消息
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-03 22:42
 */
public class TestGroupMessage {

    public static App app;
    @BeforeClass
    public static void beforeAll() {
        app = new App();
        app.loginWithCookie();

    }

    @Test
    public void send(){
        String title="新快递来了3";
        List<String> sendMsg=app.toGroupMessage()
                .send("刘江波",title,title+"你有新快递来了", "快递通知","auth")
                .getSendMsg().subList(0,3);//获取前3个
        System.out.println("获取到的所有list内容"+sendMsg);
        assertThat(sendMsg,hasItem(title));//单元测试的断言

    }


    @AfterClass
    //每次执行完case，需要退出
    public static void afterAll() {
        app.quit();

    }
}