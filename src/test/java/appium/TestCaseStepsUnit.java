/**
 * @Project Name :  testunitnew
 * @Package Name :  xUnit
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-17 18:26
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium;

import appium.page.*;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-17 18:26
 *
 *
 */
public class TestCaseStepsUnit {
    @Test
    //这个是看这个数据格式的
    public void steps() throws JsonProcessingException {
        HashMap<String,PageObjectMethod> testCase=new HashMap<>();
        PageObjectMethod testCaseSteps=new PageObjectMethod();
        List<HashMap<String,String>> steps=new ArrayList<>();
        HashMap<String,String> map=new HashMap<>();
        map.put("id","XXXX");
        HashMap<String,String> map1=new HashMap<>();
        map1.put("id","XXXX");
        map1.put("send","XXXXY");
        steps.add(map);
        steps.add(map1);

        testCaseSteps.setSteps(steps);
        testCase.put("search",testCaseSteps);
        testCase.put("cancel",testCaseSteps);

        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCase));
        System.out.println("=============testCase============"+ JSON.toJSON(testCase));
        System.out.println("==========testCaseSteps===="+JSON.toJSON(testCaseSteps));
        System.out.println("==========steps步骤============"+JSON.toJSONString(steps));
        //{"search":{"steps":[{"send":"XXXXY","id":"XXXX"},{"send":"XXXXY","id":"XXXX"}]}}
    }

    @Test
    public void parseSteps() throws MalformedURLException {
        App.getInstance().start();
        BasePage basePage = new BasePage();
        basePage.parseSteps("search");

    }

@Test
public void demo() {
    Arrays.stream(Thread.currentThread().getStackTrace()).forEach(stack -> {
        System.out.println(stack.getClassName() + ":" + stack.getMethodName());
    });
    //打印出了方法名字
    System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
}


@Test
    public void testPOM() throws JsonProcessingException {
    PageObjectModel model=new PageObjectModel();
    PageObjectElement element=new PageObjectElement();
    List<HashMap<String,String>> elements=new ArrayList<>();

    HashMap<String,String> map2=new HashMap<>();

    map2.put("id","search_input_text");
    map2.put("os","android");

    elements.add(map2);
    element.setElement(elements);
    model.elements.put("search_locator",element);

    PageObjectMethod method=new PageObjectMethod();
    List<HashMap<String,String>> steps=new ArrayList<>();

    HashMap<String,String> map=new HashMap<>();
    map.put("id","XXXX");
    map.put("send","XXXXXX");
    steps.add(map);

    method.setSteps(steps);

    ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model));
}


}