/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-13 10:50
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.restAssured;

import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;

/**
 * @Description  :  restAssured的一些常用方法 Filter
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-13 10:50
 */
public class TestHttp {

    protected Logger logger = Logger.getLogger(getClass());
    /*filter的使用*/
//
    @Test
    void filterDemo(){

        given().filter((req,res,ctx)->{
            logger.info(req.getBaseUri());
            System.out.println(req.getURI());
            Response response=ctx.next(req,res);
            System.out.println(response.getStatusLine());

            logger.info(response.getStatusLine());
            return  response;
        })
                .when().log().all().get("http://192.168.183.29:8000/user.json")
                .then().log().all().statusCode(200);
    }


    @Test
    /*使用filter修改那个请求 request
    * 将请求到http://192.168.183.29:8000/user.json请求
    * 改到http://192.168.183.29:8000/user_error.json
    * */
    void filterAlterRequest(){

        given().filter((req,res,ctx)->{
            req.queryParam("token","XXXX");
            req.baseUri("http://192.168.183.29:8000");
            req.path("/user_error.json");
            System.out.println(req.getURI());
            Response response=ctx.next(req,res);
            System.out.println(response.getStatusLine());
            logger.info(response.getStatusLine());
            return  response;
        })
                .when().log().all().get("http://192.168.183.29:8000/user.json")
                .then().log().all().statusCode(200);
    }

    /*使用filter修改，相应响应修改 将响应的userid改为null*/
    @Test
    void filterAfterResponse(){
        given().filter((req,res,ctx)->{
            Response response=ctx.next(req,res);
            String bodyNew=response.getBody().asString().replace("\"userid\": \"a131\""," \"userid\": null");
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(response);
            responseBuilder.setBody(bodyNew);
            return  responseBuilder.build();
        })
                .when().log().all().get("http://192.168.183.29:8000/user.json")
                .then().log().all().statusCode(200);
    }
    /*使用 DemoFilter，将返回的状态码改为404，将filter封装出来使用*/
    @Test
    void demoFilter() {
        given().filter(new DemoFilter())
                .when().log().all().get("http://192.168.183.29:8000/user.json")
                .then().log().all().statusCode(200);
    }

    @Test
    /*使用filter的解密请求 getMimeDecoder 为啥 乱码*/
    void demoBase64Filter(){
        given().filter((req,res,ctx)->{
            Response responseOrigin=ctx.next(req,res);

            ResponseBuilder responseBuilder=new ResponseBuilder().clone(responseOrigin);
            String responseString=responseOrigin.getBody().toString().trim();//原始的返回值

            String decodeString= null;//解密后的请求
                decodeString = new String(Base64.getDecoder().decode(responseString));


            return  responseBuilder.setBody(decodeString).build();

        }).when().log().all().get("http://192.168.183.29:8000/json.json")
                .then().log().all().statusCode(200);
    }



}