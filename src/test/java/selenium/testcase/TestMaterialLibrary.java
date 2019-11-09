/**
 * @Project Name :  testunitnew
 * @Package Name :  selenium.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-06 14:50
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package selenium.testcase;

import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-06 14:50
 */
public class TestMaterialLibrary {
    public static App app;
    @BeforeClass
    public static void beforeAll() {
        app = new App();
        app.loginWithCookie();

    }

    @Test
    //添加图片
    public void testLibrary(){
        String picturePath="C:\\Users\\liujiangbo\\Desktop\\timg.jpg";
        app.toMaterialLibraryPage().addPicture(picturePath);
    }
    //添加图文
    @Test
    public void testLibiaryAddPicWord(){
        String picturePath="C:\\Users\\liujiangbo\\Desktop\\timg.jpg";
        app.toMaterialLibraryPage().addPicAndWord("图文主题",
                "图文内容",picturePath,"核心摘要","auth");

    }
}