/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-02 9:08
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;


/*登录微信企业号
* 1.登录验证码的解决，拿到cookie，主要是里面的sessionid，当cookie过期的话重新获取一个cookie
* 2.隐式等待
* 3.每个那个测试用例保持独立，case之间没有依赖*/
public class TestWeWork {

    public static App app;

    @BeforeClass
    public static void beforeAll() {
        app = new App();
        app.loginWithCookie();
        //数据清理过程,为了保证添加成功
        String phone="15123334571";
        app.toContact().delete(phone);
    }

    @Test
    //添加员工，保证添加初始化先删除一下
    public void testadd() {
            String phone = "15123334571";
            app.toMemberAdd().add(phone, phone, phone);

    }
    @Test
    //首页进入添加成员页面，添加完，删除该成员
    public void testdelete() {
        String phone = "15123334574";
        app.toMemberAdd().add(phone, phone, phone).delete(phone);
    }

    @Test
    //删除列表页面的的成员，通过复选框删除
    public void testdeleteCurrentPage(){
        try {
            app.toContact().deleteCurrentPage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*    @Test
    public void  importFromFile(){
        app.toContact().ImportFromfile();
    }*/
/*
    @Test
    public void send(){
        app.toGroupMessage().send("公告","刘江波","新快递来了","你有新快递来了","快递通知","auth");
    }*/

    @AfterClass
    //每次执行完case，需要退出
    public static void afterAll() {
        app.quit();

    }
}
