package xUnit; /**
 * @Project Name :  Xunit
 * @Package Name :  PACKAGE_NAME
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-21 21:33
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Description  :  套件：是把类集合在一起执行，可以定义执行顺序
 * 套件可以控制单元测试的用例的顺序
测试用例之间的顺序
test fixtures的顺序
继承顺序
套件之间的顺序
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-21 21:33
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestJunit4DemoChild2.class,
        TestJunit4Demo.class,
        TestJunit4DemoChild.class
})
public class TestSuite {
}