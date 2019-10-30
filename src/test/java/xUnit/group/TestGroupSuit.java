package xUnit.group; /**
 * @Project Name :  Xunit
 * @Package Name :  PACKAGE_NAME
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-28 16:03
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Description  :  按照分组标签执行
 * 标签 分组区分不同的测试用例，执行TestGroups中的标签为slow的（slow中排除fast的）
 * @Creation Date:  2019-10-28 16:03
 */

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowGroup.class)
@Categories.ExcludeCategory(FastGroup.class)
@Suite.SuiteClasses({
     TestGroups.class
//demo2 4 5为执行结果
})
public class TestGroupSuit {
}