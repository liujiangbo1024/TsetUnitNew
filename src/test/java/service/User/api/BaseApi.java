/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.api
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-07 9:31
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Description  :  获取yaml中的数据
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-07 9:31
 */
public class BaseApi {
    ApiObjectModel model = new ApiObjectModel();

    HashMap<String,Object> params;

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
        //this.params 成员变量
    }

    /*将path中的yaml文件中的内容读取出来转成java对象*/
    public Response parseSteps() {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("method======="+method);
        if (model.methods.entrySet().isEmpty()) {
            System.out.println("pom first load");
            System.out.println("当前的路径===" + this.getClass().getCanonicalName());
            String path = "/" + this.getClass().getCanonicalName().replace(".", "/") + ".yaml";
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            try {

                //将json字符串反序列化为java对象
                model = mapper.readValue(BaseApi.class.getResourceAsStream(path), ApiObjectModel.class);  // 将Json字符串反序列化为Java对象
                // parseSteps(model.methods.get(method));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return model.run(method,params);
    }


}