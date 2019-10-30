package xUnit; /**
 * @Project Name :  Xunit
 * @Package Name :  PACKAGE_NAME
 * @Description :  junit4的例子
 *
 测试用例之间的顺序
test fixtures的顺序
继承顺序
套件之间的顺序
 * @author :  liujiangbo
 * @Creation Date:  2019-10-21 20:21
 * --------  ---------  --------------------------
 */

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

/**
 * @Description  :  这个Unit4的案例
 * 单元测试中的方法就是测试用例，尽量不要有顺序的依赖
 * @FixMethodOrder(MethodSorters.NAME_ASCENDING) 这个测试用例按照asc码排序
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-21 20:21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJunit4Demo {
    @BeforeClass
    public static void beforeAlltestclass(){
        System.out.println("beforeAllClass");
    }

    @AfterClass
    public static void afterAlltestclass(){
        System.out.println("AfterAllClass");
    }
    @Before
    public void beforeTestcase(){
        System.out.println("I am @before");
    }

    @After
    public void AfterTestcase(){
        System.out.println("I am @After");
    }


    @Test
    public void testdemo3 (){
        System.out.println("testdemo3");
        assertTrue(false);
    }
    @Test
    public void testdemo1(){
        System.out.println("testdemo1");
        assertTrue(true);
    }

    @Test
    public void testdemo2(){
        System.out.println("testdemo2");
        assertTrue(false);
    }
}