/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.api
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-07 10:08
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.api;

import io.restassured.response.Response;

import java.util.HashMap;


/**
 * @Description  :  po的代码
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-07 10:08
 */
public class UserApi extends  BaseApi {
    public Response get(String userid) {
        HashMap<String,Object> params=new HashMap<>();
        params.put("userid",userid);
        setParams(params);
        return parseSteps();
    }
}