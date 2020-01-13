/**
 * @Project Name :  testunitnew
 * @Package Name :  appium
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-14 15:32
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium;

import appium.page.PageObjectMethod;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Description  :  从yaml文件外面读取内容
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-14 15:32
 */
public class TestResources {

    public  String name;
    public  int age;

    @Test
    //读取文件的路径和文件中的内容
    public void readFile() throws IOException {
        /*输出结果：path
        * file:/E:/javascripts/study/testunitnew/target/test-classes/
          file:/E:/javascripts/study/testunitnew/target/classes/appium/TestSearch.yaml*/
        System.out.println(this.getClass().getCanonicalName());//appium.TestResources
        String path="/"+this.getClass().getCanonicalName().replace(".","/")+".yaml";//获取到这个配置文件 通过标准化的路径
        System.out.println("path========="+path);
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/appium/testcase/TestSearch.yaml"));

        //读取输入流
        /*//读文件 读取那个TestStock.yaml中的文件
//static String:readFileToString(File file, String encoding)
System.out.println(FileUtils.readFileToString(new File("D:/a/b/cxyapi.txt"), "UTF-8"));

//static List<String>:readLines(File file, String encoding)
System.out.println(FileUtils.readLines(new File("D:/a/b/cxyapi.txt"), "UTF-8")); //返回一个list*/

        System.out.println(FileUtils.readFileToString(new File(this.getClass().getResource("/appium/testcase/TestSearch.yaml").getPath()),"UTF-8"));

    }
    @Test
    //向文件中写json
    public void writeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("demo.json"),this);
    }

    @Test
    public void readJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            TestResources demo=mapper.readValue(new File("demo.json"),this.getClass());
            System.out.println(demo);
            System.out.println(demo.name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    //在resources目录下：能读出数据就行，jackjson序列化 objectmapper.readValue (将Json字符串反序列化为Java对象)
    public void readYaml() throws IOException {
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
       // HashMap<Object, Object> demo=mapper.readValue(this.getClass().getResourceAsStream("appium/TestSearch.yaml"), new TypeReference<HashMap<Object,Object>>(){});
        Object[][] demo=mapper.readValue(this.getClass().getResourceAsStream("/appium/testcase/TestSearch.yaml"),Object[][].class);
        System.out.println(JSON.toJSONString(demo));
        System.out.println(demo.length);
        System.out.println(demo[0].toString());
        System.out.println(demo[1].length);
        System.out.println(demo[2].toString());
        System.out.println(demo[2][0]);//jd
        System.out.println(demo[2][1]);//33



    }

    @Test
    public void readYamle() throws IOException {
        String path="/appium/page/searchPage.yaml";
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, PageObjectMethod>> typeRef
                = new TypeReference<HashMap<String, PageObjectMethod>>() {};
        HashMap<String, PageObjectMethod> steps=mapper.readValue(this.getClass().getResourceAsStream(path),typeRef);

        //sout
        System.out.println(JSON.toJSON(steps));



       // parseSteps(path,"search");
    }


}