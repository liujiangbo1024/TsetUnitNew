/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-06 14:38
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @Description  :  素材库界面封装
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-06 14:38
 */
public class MaterialLibraryPage extends BasePage{


    //添加图片
    public MaterialLibraryPage addPicture(String picturepath) {
        findElement(By.linkText("文字"), 5).click();
        findElement(By.linkText("图片"), 5).click();
        findElement(By.linkText("添加图片"), 5).click();

        findElement(By.id("js_upload_input"), 0).sendKeys(picturepath);

            try {
                Thread.sleep(3000);//添加等待就可以上传成功，不然添加不上
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        findElement(By.linkText("完成")).click();
        return this;
    }

        //添加图文
    public MaterialLibraryPage addPicAndWord(String title,String bodyText,String path,String summary,String auth){
        findElement(By.linkText("图文")).click();
        findElement(By.linkText("添加图文")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
        //添加正文内容
        List<WebElement> frame_list = driver.findElements(By.tagName("iframe"));
        String frame_id = frame_list.get(0).getAttribute("id");
        driver.switchTo().frame(frame_id);  // 切换到第一个frame
        findElement(By.cssSelector(".msg_mpNewsEditor_frameBody")).sendKeys(bodyText);
        driver.switchTo().defaultContent();  // 切换回来
        //点击添加封面图
        findElement(By.cssSelector(".msg_infoItem_coverPlaceHolder")).click();
        //下拉
        ((JavascriptExecutor)(driver)).executeScript("window.scroll(0,1600)");
        //添加图片,xpath，failed: waiting for visibility of element located by By.cssSelector: .ww_fileInput报错
        findElement(By.xpath("//*[@class='cropper_mainImgContainer']//input"),0).sendKeys(path);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击确定
        findElement(By.linkText("确定")).click();
        //添加摘要
     /*   findElement(By.cssSelector(".msg_edit_infoItem_textWord")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);*/
        //添加作者
        findElement(By.cssSelector(".js_amrd_author_input")).sendKeys(auth);
        ((JavascriptExecutor)(driver)).executeScript("window.scroll(0,1200)");
        findElement(By.linkText("完成")).click();

        return this;
    }

}