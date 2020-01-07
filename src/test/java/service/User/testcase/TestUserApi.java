/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-07 10:07
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.testcase;

import org.junit.jupiter.api.Test;
import service.User.api.UserApi;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-07 10:07
 */
public class TestUserApi {


    @Test
    public void get(){
        UserApi userApi=new UserApi();
        userApi.get("servenBurry");
    }

}