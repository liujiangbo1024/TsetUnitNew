package xUnit.group; /**
 * @Project Name :  Xunit
 * @Package Name :  PACKAGE_NAME
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-28 15:49
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

/**
 * @Description  :  基于hamcrest的断言，建议是使用这个 assertThat断言，assertThat断言比较灵活
 * -- @Category 分类标签，一个方法可以从属于多个分类
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-28 15:49
 */
public class TestGroups {
    @Category({SlowGroup.class,FastGroup.class})
    @Test
    public void testdemo1(){
        assertTrue(false);
    }

    /*分组*/
    @Category(SlowGroup.class)
    @Test
    public void testdemo2(){
        assertEquals("diff two values:",100,10);
        assertTrue(false);
    }

    @Category(FastGroup.class)
    @Test
    public void testdemo3(){
        /*基于hamcrest的断言*/
        assertThat("actual value",11,equalTo(10));
        assertTrue(false);
    }

    @Category(SlowGroup.class)
    @Test
    public void testdemo4(){
        assertThat("actual value is close to 10",
                10.1,
        closeTo(10.0,0.1));

       /*范围在9.9到10.1 之间的可以通过
       * 宽松断言*/
    }
    @Category(SlowGroup.class)
    @Test
    public void testdemo5(){
        assertThat("actual value is close to 10",
                9.88,
               anyOf(closeTo(10.0,0.1),equalTo(9.88)) );

        /*范围在9.9到10.1 之间的可以通过
         * 宽松断言 anyOf 可以匹配任何一个断言 这个也可以匹配9.9 --10.1，还有9.88 也可以*/
    }


}