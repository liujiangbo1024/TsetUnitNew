package xUnit; /**
 * @Project Name :  Xunit
 * @Package Name :  PACKAGE_NAME
 * @Description :  junit4的例子
 * @author :  liujiangbo
 * @Creation Date:  2019-10-21 20:21
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

/**
 * @Description  :  这个Unit4的案例
 * 单元测试中的方法就是测试用例，尽量不要有顺序的依赖
 * 测试的方法的执行顺序要注意，测试套件的执行顺序
 * 父类也都执行一遍
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-21 20:21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJunit4DemoChild2 extends TestJunit4Demo {
    @BeforeClass
    public static void beforeAlltestclassChild2(){
        System.out.println("TestJunit4DemoChild2： beforeAllClass");
    }

    @AfterClass
    public static void afterAlltestclassChild2(){
        System.out.println(" TestJunit4DemoChild2： AfterAllClass");
    }
    @Before
    public void beforeTestcaseChild2(){
        System.out.println("TestJunit4DemoChild2：I am @before");
    }

    @After
    public void AfterTestcaseChild2(){
        System.out.println("TestJunit4DemoChild2：I am @After");
    }


    @Test
    public void testdemo3Child2 (){
        System.out.println("TestJunit4DemoChild2：testdemo3");
        assertTrue(false);
    }
    @Test
    public void testdemo1Child2(){
        System.out.println("TestJunit4DemoChild2：testdemo1");
        assertTrue(true);
    }

    @Test
    public void testdemo2Child2(){
        System.out.println("TestJunit4DemoChild2：testdemo2");
        assertTrue(false);
    }
}