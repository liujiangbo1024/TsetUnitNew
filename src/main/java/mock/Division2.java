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
 * @Description  :  mock和部分mock
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-01 14:38
 */
public class Division2 {
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
    public Integer divid2(Integer a,Integer b,Integer c,Division division)
    {

        Integer x=division.divid(a,b);
        if(x>10){
            return 0;
        }else {
            return division.divid(a, b) / c;
        }
    }

    public Integer divid3(Integer a,Integer b,Integer c)
    {

        Integer x=divid(a,b);
        if(x>10){
            return 0;
        }else {
            return divid(a, b) / c;
        }
    }


}