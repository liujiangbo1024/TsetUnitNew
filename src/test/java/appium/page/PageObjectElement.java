/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-23 11:03
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;

/**
 * @Description  :  获取元素
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-23 11:03
 */
public class PageObjectElement {
    public List<HashMap<String,String>> element;

    public List<HashMap<String, String>> getElement() {
        return element;
    }

    public void setElement(List<HashMap<String, String>> element) {
        this.element = element;
    }

    public By getLocator(){
        //todo: xxxx

//        String osOrigin=BasePage.driver.getCapabilities().getPlatform().toString().toLowerCase();
        return By.id("xxx");
    }

    public By getLocator(String os,String version){
        for(HashMap<String, String> map : element){
            if(map.get("os")==os && map.get("version")==version){
                if(map.get("id")!=null){
                    return By.id(map.get("id"));
                }else if(map.get("xpath")!=null){
                    return By.xpath((map.get("xpath")));
                }
                break;
            }
        }
        return null;

    }
}