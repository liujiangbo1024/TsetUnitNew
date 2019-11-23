/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-17 18:15
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import java.util.HashMap;
import java.util.List;

/**
 * @Description  :  解析出以下格式
 * search:
- id: home_search
- id: search_input_text
=======如下：List<HashMap<String,String>>
[{"id":"home_search"},{"send":"PDD","get":"text","id":"search_input_text"}]

代表每个方法的若干步骤
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-17 18:15
 */
public class PageObjectMethod {

    private List<HashMap<String,String>> steps;

    public  List<HashMap<String, String>> getSteps() {
        return steps;
    }

    public void setSteps(List<HashMap<String, String>> steps) {
        this.steps = steps;
    }
}