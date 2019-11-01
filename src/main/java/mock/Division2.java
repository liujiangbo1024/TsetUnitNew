/**
 * @Project Name :  testunitnew
 * @Package Name :  mock
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-01 14:38
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package mock;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-01 14:38
 */
public class Division2 {
    public Integer divid2(Integer a,Integer b,Integer c,Division division)
    {

        Integer x=division.divid(a,b);
        if(x>10){
            return 0;
        }else {
            return division.divid(a, b) / c;
        }
    }
}