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
public class TestJunit4DemoChild extends TestJunit4Demo {
    @BeforeClass
    public static void beforeAlltestclassChild(){
        System.out.println("TestJunit4DemoChild： beforeAllClass");
    }

    @AfterClass
    public static void afterAlltestclassChild(){
        System.out.println(" TestJunit4DemoChild： AfterAllClass");
    }
    @Before
    public void beforeTestcaseChild(){
        System.out.println("TestJunit4DemoChild：I am @before");
    }

    @After
    public void AfterTestcaseChild(){
        System.out.println("TestJunit4DemoChild：I am @After");
    }


    @Test
    public void testdemo3Child (){
        System.out.println("TestJunit4DemoChild：testdemo3");
        assertTrue(false);
    }
    @Test
    public void testdemo1Child(){
        System.out.println("TestJunit4DemoChild：testdemo1");
        assertTrue(true);
    }

    @Test
    public void testdemo2Child(){
        System.out.println("TestJunit4DemoChild：testdemo2");
        assertTrue(false);
    }
}