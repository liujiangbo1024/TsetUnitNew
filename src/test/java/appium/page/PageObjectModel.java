/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-23 11:15
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import java.util.HashMap;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-23 11:15
 */
public class PageObjectModel {
    public HashMap<String,PageObjectElement> elements=new HashMap<>();//元素 所有的元素
    public HashMap<String,PageObjectMethod> methods=new HashMap<>();//方法 元素下的所有方法

}