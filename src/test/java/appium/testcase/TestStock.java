/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-10 12:45
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.testcase;

import appium.page.App;
import appium.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @Description  :  junit5参数化Appium
 * @order
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-10 12:45
 */
public class TestStock {
    private  static StockPage stockPage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        App.getInstance().start();
        stockPage= App.getInstance().toStock();
    }

    @BeforeEach
    public void beforeEach() {

    }
    @Order(100)
    @Test
    public void addDefaultSelectedStocks(){
        if(stockPage.getAllStocks().size()>1){
            stockPage.deleteAll();
        }

        assertThat(stockPage.addDefaultSelectedStocks().getAllStocks().size(),greaterThanOrEqualTo(6));
    }

    //显示控制用例的执行顺序
    @Order(200)
    @ParameterizedTest
    @MethodSource("data")
    public void addStock(String code, String name) throws IOException {

        stockPage.toSearch().search(code).select().cancel();//有页面的跳转--从股票页面--跳转到查询页面--添加到自选-取消回到股票列表页面
        assertThat(stockPage.getAllStocks(), hasItem(name));//判断是否有刚添加的股票
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                arguments("pdd", "拼多多"),
                arguments("jd", "京东")
        );
    }
}