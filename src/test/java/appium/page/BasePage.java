/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-09 10:51
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:51
 */
public class BasePage {
    public static AndroidDriver<WebElement> driver;

    //传入的数据存储
    private static HashMap<String,Object> params=new HashMap<>();
    //保存返回的值
    private static HashMap<String,Object> results=new HashMap<>();

    private  PageObjectModel model=new PageObjectModel();

    //测试结果的读取
    public static HashMap<String, Object> getResults() {
        return results;
    }

    public static HashMap<String, Object> getParams() {
        return params;
    }

    //测试步骤参数化
    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }



    public static  WebElement findElement(By by) {
    //todo:递归的更好
        System.out.println("元素为："+by);
        try {
            return  driver.findElement(by);
        } catch (Exception e) {
            handleAlter();

            //再次回到
            return driver.findElement(by);
        }
    }


    public static  void  findElementAndClick(By by) {
        //todo:递归的更好
        //todo :如果定位的元素是动态变化的
        System.out.println("点击元素为："+by);
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlter();//处理所有的弹框
            //再次回到
             driver.findElement(by).click();
        }
    }


    public static List<WebElement> findElemets(By by){
        return driver.findElements(by);


    }
     static void handleAlter() {
       By tips=By.id("com.xueqiu.android:id/snb_tip_text");
         List<By> alterBoxs = new ArrayList<>();
         //todo:不需要所有的都判断是否存在
         alterBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));//升级框出现
         //alterBoxs.add(By.id("com.xueqiu.android:id/update_id_ok"));//后台更新
         alterBoxs.add(tips);//自选股票页面手势框,找到这个后不能直接点击，需要点击任意地方
         alterBoxs.add(By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));//App的评价页面，需要点击下次再说
         //adb shell pm clear com.xueqiu.android 清理android的缓存
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

         alterBoxs.forEach(alter -> {
             List<WebElement> ads = driver.findElements(alter);
             if (alter.equals(tips)) {

                 System.out.println("snb_tip found ");
                 Dimension size = driver.manage().window().getSize();
                 try {
                     if (driver.findElements(tips).size() >= 1) {
                         new TouchAction(driver).tap(PointOption.point(size.width / 2, size.height / 2)).perform();
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 } finally {
                     System.out.println("snb_tip clicked");
                 }
             } else if (ads.size() >= 1) {
                 ads.get(0).click();
             }
         });

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    public void parseSteps(){
        String method=Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(method);
        parseSteps(method);
    }


    //解析步骤 search
    public void parseSteps(String method) {

        //HashMap<String,List<HashMap<String,String>>>可以取消steps等多余关键字
        System.out.println("当前的路径==="+this.getClass().getCanonicalName());
        String path="/"+this.getClass().getCanonicalName().replace(".","/")+".yaml";
        parseSteps(path,method);

    }
    
    public void parseSteps(String path, String method){
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());

        //String jsonInput = "{\"key\": \"value\"}";
        //TypeReference<HashMap<String, PageObjectMethod>> typeRef = new TypeReference<HashMap<String, PageObjectMethod>>() {};
        // Map<String, String> map = mapper.readValue(jsonInput, typeRef);
        System.out.println(path);
        // Object[][] demo=mapper.readValue(TestSearch.class.getResourceAsStream("/appium/testcase/TestSearch.yaml"),Object[][].class);
        try {
            
            //将json字符串反序列化为java对象
             model = mapper.readValue(BasePage.class.getResourceAsStream(path),PageObjectModel.class);  // 将Json字符串反序列化为Java对象
            parseSteps(model.methods.get(method));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void parseSteps(PageObjectMethod steps){
        steps.getSteps().forEach(step-> {
            System.out.println("=====step========="+ JSON.toJSONString(step));
                    WebElement element = null;
                    String id = step.get("id");
                    if (id != null) {
                            element = findElement(By.id(id));

                    }
                    else if (step.get("aid") != null) {
                        element = findElement(MobileBy.AccessibilityId(step.get("aid")));
                    }else if (step.get("xpath") != null) {
                        element = (WebElement) driver.findElement(By.xpath(step.get("xpath")));
                    }else if(step.get("element")!=null){
                        model.elements.get(step.get("element")).getLocator();
                    }

                    String send=step.get("send");
                    if(send!=null){
                        //参数化驱动
                        for(Map.Entry<String,Object> kv:params.entrySet()){
                        String matcher="${"+kv.getKey()+"}";
                        if(send.contains(matcher)){
                            System.out.println("kv==="+kv);
                            send=send.replace(matcher,kv.getValue().toString());
                        }
                        }
                        element.sendKeys(send);

                    }else if(step.get("get")!=null){
                       String attribute= element.getAttribute(step.get("get"));//获取属性
                        results.put(step.get("dump"),attribute);
                        //results.put(price,text);
                    }else {
                        element.click();//不符合上述的就click
                    }

                }
        );

    }

}