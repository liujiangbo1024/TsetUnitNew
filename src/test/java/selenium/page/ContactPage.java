/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-02 9:44
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @Description  :  通讯录页面封装--关于通讯录页面的，添加 ，删除操作，批量导入
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-02 9:44
 */
public class ContactPage extends BasePage {

    //1-添加成员
    public ContactPage add(String username, String id, String phone) {
        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }

    //2-查询出成员，删除成员 动态加载，删除或者确认没找到
    //找不到，可以添加延迟，
    public ContactPage delete(String keyword) {
        //每次输入前清理一下，再次搜索
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(keyword);
        try {
            //抽取“编辑”，因为在列表页和详情页面  都有这个“删除”，只有详情页面有 “编辑”，所以用【编辑】判断找到搜索的成员
            waitClickable(By.linkText("编辑"), 5);
        } catch (Exception e) {
            System.out.println("not Fond");
            return this;
        }

        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        return this;//删除后，仍在通讯录详情页面
    }
    //3-通过复选框删除成员，点击了复选框会页面会发生变化
    //css=.member_colRight_memberTable_tr_Hover .ww_checkbox
    public ContactPage deleteCurrentPage() throws InterruptedException {

      /*  Select select=new Select(findElement(By.cssSelector(".ww_checkbox")));
        select.getFirstSelectedOption().click();
        select.selectByIndex(0);*/

        //先确认可以点击的，因为样式发生变化，要WebDriverWait等待元素出来可点击,有个瞬时的动态变化
        Thread.sleep(3000);
        waitClickable(By.cssSelector(".ww_checkbox"), 3);
        List<WebElement> elements = driver.findElements(By.cssSelector(".ww_checkbox"));
        System.out.println("列表长度"+elements.size());
        for (int i = 1; i < 3; i++) {
            System.out.println(i);
            elements.get(i).click();//选出其中一个点击
            try {
                //点击的动态效果，所以每次都等待500ms
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        return this;
    }
    // css=.ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left
    //failed: waiting for visibility of element located by By.partialLinkText:ww_btn_PartDropdown_left (tried for 5 second(s) with 500 milliseconds interval)
    //4-批量导入
    public void ImportFromFile(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //<div class="ww_btn_PartDropdown_left">批量导入/导出</div> partialLinkText这个是啥
        findElement(By.partialLinkText("批量导入/导出")).click();
        findElement(By.linkText("文件导入")).click();
        //上传的时候不能有wait时间
        findElement(By.id("js_upload_file_input"),0).sendKeys("D:\\software\\yidongduan\\selenium\\通讯录批量导入模板.xlsx");
        //id=submit_csv 点击导入按钮
        findElement(By.id("submit_csv")).click();
        findElement(By.linkText("完成")).click();

    }


    public void list(){

    }
}