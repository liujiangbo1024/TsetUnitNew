package mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author :  liujiangbo
 * @Project Name :  testunitnew
 * @Package Name :  mock
 * @Description : 测试驱动开发，当开发的方法还没有写好时，用单测驱动开发
 * @Creation Date:  2019-10-31 15:07
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
public class DivisionTest1 {

    Division division=new Division();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divid() {
        assertThat(division.divid(10,5),equalTo(2));

    }

    @Test
    public void dividReturnWithZero() {
        assertThat(division.divid(1,2),equalTo(0));
    }


    //测试驱动开发，除数是0的情况未考虑
    @Test
    public void dividByZero() {
        assertThat(division.divid(100,0),equalTo(null));
    }

/*    @Test
    public void dividwitAG100() {
        assertThat(division.divid(2000,2),equalTo(null));
    }*/
}