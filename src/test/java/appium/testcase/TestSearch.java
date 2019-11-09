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
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @Description  : 参数化进行
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-09 10:21
 */
@RunWith(Parameterized.class)
public class TestSearch {
    public static SearchPage searchPage;
    @BeforeClass
    public static void beforeAll() throws MalformedURLException {
        App.start();

    }

    @Parameterized.Parameters
/*    public static List<String> data() {
        List<String> stocks = new ArrayList<>();
        stocks.add("xiaomi");
        stocks.add("alibaba");
        stocks.add("jd");
        return stocks;
    }*/
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",8f},
                {"jd",33f},
        });
    }

    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public float price;

    @Before
    public void before(){
        searchPage=App.toSearch();//将查询的页面返回给下面用
    }
    @Test
    public void search(){
      assertThat(searchPage.search(stock).getCurrentPrice(),greaterThanOrEqualTo(price));

    }

    @After
    public void after(){
        searchPage.cancel();
    }
}