/**
 * @Project Name :  testunitnew
 * @Package Name :  xUnit.param
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-31 14:32
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package xUnit.param;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @Description  :  https://github.com/Pragmatists/JUnitParams   junitparams的例子
 * 参数化常用的一种
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-31 14:32
 */
@RunWith(JUnitParamsRunner.class)
public class TestDataDriver {


    @Test
    @Parameters({"16, false", "22, true"})

    public void personIsAdult(int age, boolean valid) throws Exception {
        assertThat(age>17,equalTo(valid));
    }

}