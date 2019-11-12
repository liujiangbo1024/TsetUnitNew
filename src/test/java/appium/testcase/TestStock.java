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

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;

/**
 * @Description  :  使用junit5加Appium作App的自动化,junit的数据驱动
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-10 12:45
 */
public class TestStock {
    private  static StockPage stockPage;
    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        App.start();
        stockPage= App.toStock();
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
    @Order(200)
    @Test
    public void addStock() {

        stockPage.toSearch().search("pdd").select().cancel();
        assertThat(stockPage.getAllStocks(), hasItem("拼多多"));
    }

}