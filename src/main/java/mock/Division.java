/**
 * @Project Name :  testunitnew
 * @Package Name :  mock
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-10-31 14:56
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package mock;

/**
 * @Description  :  测试驱动开发
 * @author       :  liujiangbo
 * @Creation Date:  2019-10-31 14:56
 */
public class Division {

    public Integer divid(Integer a,Integer b){
        if(b==0){
            return null;

        }else if(a>1000){
            return null;

        }else if(a<b){
            return 0;

        }else {
            return a / b;
        }
    }
}