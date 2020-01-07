/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.api
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-07 9:27
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.api;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-07 9:27
 */
public class ApiObjectModel {

    public HashMap<String, ApiObjectMethodModel> methods = new HashMap<>();

    public ApiObjectMethodModel getMethod(String method) {
        return methods.get(method);
    }
    public Response run(String method){
        System.out.println("请求的URL为=="+methods.get(method).url);
        return getMethod(method).run();

    }

    public Response run(String method, HashMap<String, Object> params) {

        return getMethod(method).run(params);
    }
}