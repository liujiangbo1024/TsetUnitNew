/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-09 10:21
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.testcase;

import appium.page.App;
import appium.page.SearchPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @Description  :
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:21
 */
@RunWith(Parameterized.class)
public class TestSearch {
    public static SearchPage searchPage;
    @BeforeClass
    public static void beforeAll() throws MalformedURLException {
        App.getInstance().start();

    }

    @Parameterized.Parameters
/*    public static List<String> data() {
        List<String> stocks = new ArrayList<>();
        stocks.add("xiaomi");
        stocks.add("alibaba");
        stocks.add("jd");
        return stocks;
    }*/
//参数化
  /*  public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",8f},
                {"jd",33f},
        });
    }*/

  //参数化
    public static Collection<Object[]> data() throws IOException {
       /* return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",8f},
                {"jd",33f},
        });*/
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        String path="/"+TestSearch.class.getCanonicalName().replace(".","/")+".yaml";
        System.out.println(path);
       // Object[][] demo=mapper.readValue(TestSearch.class.getResourceAsStream("/appium/testcase/TestSearch.yaml"),Object[][].class);
        Object[][] demo=mapper.readValue(TestSearch.class.getResourceAsStream(path),Object[][].class);   // 将Json字符串反序列化为Java对象
        System.out.println("入参==="+demo);
        return Arrays.asList(demo);
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public float price;

    @Before
    public void before(){
        searchPage=App.getInstance().toSearch();
    }
    @Test
    public void search() throws IOException {
      assertThat(searchPage.search(stock).getCurrentPrice(),greaterThanOrEqualTo(price));

    }

    @After
    public void after(){
        searchPage.cancel();
    }
}