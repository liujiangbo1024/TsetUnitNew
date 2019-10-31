/**
 * @Project Name :  testunitnew
 * @Package Name :  xUnit
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-31 13:59
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package xUnit.param;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-31 13:59
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @Description  :  junit4结合hamcrest参数化
 * 参考文档：https://github.com/junit-team/junit4/wiki/Parameterized-tests
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-28 18:04
 */
@RunWith(Parameterized.class)
public class TestParam {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {1,2},
                {3,3},
                {4,5},
                {5,5}
        });
    }
    @Parameterized.Parameter(0)
    public int actual;

    @Parameterized.Parameter(1)
    public int excep;

    @Test
    public void testdemo(){
        assertThat("demo",actual,equalTo(excep));
    }

}