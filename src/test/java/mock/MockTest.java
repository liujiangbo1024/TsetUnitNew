/**
 * @Project Name :  testunitnew
 * @Package Name :  mock
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-01 11:01
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package mock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-01 11:01
 */
public class MockTest {
    @Test
    public void testDemo(){
        List<String> list=new ArrayList<String>();
        list.add("lily");
        list.add("Lucy");
        list.add("coco");
        assertThat(list.size(),equalTo(2));
    }
    /*不想去不断假数据这个list，mock一个长度5*/
    @Test
    public void testMockDemo(){
        List<String> list1=mock(ArrayList.class);
        list1.add("lily1");
        list1.add("Lucy1");
        list1.add("coco1");

        when(list1.size()).thenReturn(5);
        int a=2;
        assertThat(list1.size(),equalTo(5));
    }

}