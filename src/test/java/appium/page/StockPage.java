/**
 * @Project Name :  testunitnew
 * @Package Name :  appium.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-10 13:52
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package appium.page;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  : 自选--先删除所有股票--在添加自选股票
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-10 13:52
 */
public class StockPage extends BasePage{

    //删除既有的的股票
    public StockPage deleteAll(){
/*      findElementAndClick(By.id("com.xueqiu.android:id/edit_group"));//点击
        findElementAndClick(By.id("com.xueqiu.android:id/check_all"));//全选
        findElementAndClick(By.id("com.xueqiu.android:id/cancel_follow"));//取消关注
        findElementAndClick(By.id("com.xueqiu.android:id/md_buttonDefaultPositive"));//确定
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));//完成*/

        parseSteps("deleteAll");
        return this;
    }
    //获取所有股票
    public List<String> getAllStocks() {
        handleAlter();
        List<String> stocks = new ArrayList<>();
        driver.findElements(By.id("com.xueqiu.android:id/portfolio_stockName")).forEach(element -> {
                    stocks.add(element.getText());
                }
        );
        System.out.println("所有的股票"+stocks);
        return stocks;
    }

    //加入自选股票
    public StockPage addDefaultSelectedStocks(){
        findElementAndClick(By.id("com.xueqiu.android:id/add_to_portfolio_stock"));
        return this;
    }
    //在自选股票页面--点击搜索框
    public SearchPage toSearch(){
        findElementAndClick(By.id("com.xueqiu.android:id/action_search"));
        return new SearchPage();
    }

}