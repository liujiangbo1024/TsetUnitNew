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
import io.restassured.specification.RequestSpecification;
import service.department.Work;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @Description  :  发送请求拼接过程。API中方法定义
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-07 9:27
 */
public class ApiObjectMethodModel {
    public  HashMap<String,Object> params;
    public HashMap<String,Object> query;
    public HashMap<String,Object> header;
    public HashMap<String,Object> postBody;
    public String postBodyRaw;
    public String method="get";
    public String url="";

    public Response run(){
        RequestSpecification request=given();
        request.queryParam("access_token", Work.getInstance().getToken());

        if(query!=null){
            query.entrySet().forEach(entry->{

                request.queryParam(entry.getKey(),replace(entry.getValue().toString()));
            });
        }

        if(header!=null){
            header.entrySet().forEach(entry->{
                request.queryParam(entry.getKey(),replace(entry.getValue().toString()));
            });
        }

        if(postBody!=null){
            request.body(postBody);
        }
        /*文本格式的body这样写*/
        if(postBodyRaw!=null){
            request.body(postBodyRaw);
        }

        return request
                .when().log().all().request(method,url)
                .then().log().all().extract().response();
    }


    public Response run(HashMap<String, Object> params) {
        this.params=params;
        return run();

    }

    /*替换yaml 文件中变量的值raw，替换为传入的params的值*/
    public String replace(String raw){

        for(Map.Entry<String,Object> kv:params.entrySet()){
            String  mather="${"+kv.getKey()+"}";
            if(raw.contains(mather)){
                System.out.println("替换的mathr=="+mather);
               raw= raw.replace(mather,kv.getValue().toString());
            }

        }
        return  raw;
    }
}