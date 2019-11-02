package mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    Division2 division2=new Division2();
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

/* when(mockDivision.divid(0,0)).thenReturn(11); mock*/
   @Test
    public void divid2(){

       assertThat(division2.divid2(100,10,5,division),equalTo(2));

       Division mockDivision=mock(Division.class);
      /* when(mockDivision.divid(0,0)).thenReturn(11);
       assertThat(division2.divid2(0,0,5,mockDivision),equalTo(0));*/

      /*mock方法mockDivision后，无论入参是什么，返回值都是 11,为了覆盖方法中的if代码*/
       when(mockDivision.divid(anyInt(),anyInt())).thenReturn(11);
       assertThat(division2.divid2(1,2,5,mockDivision),equalTo(0));
   }

    //部分mock，divid这个mock，divid3仍走正常逻辑，thenCallRealMethod
   @Test
    public void divid3(){
       Division2 mockDivision2=mock(Division2.class);

       when(mockDivision2.divid(anyInt(),anyInt())).thenReturn(10);
       //部分mock
       when(mockDivision2.divid3(anyInt(),anyInt(),anyInt())).thenCallRealMethod();
       assertThat(mockDivision2.divid3(0,0,5),equalTo(2));

       when(mockDivision2.divid(anyInt(),anyInt())).thenReturn(100);
       assertThat(mockDivision2.divid3(0,0,5),equalTo(0));

   }
}