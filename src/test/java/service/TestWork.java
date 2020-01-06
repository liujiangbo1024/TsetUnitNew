package service;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*接口测试 restassured
*/
public class TestWork {
    static String token;
    static  int  parentDeptID=4;
    @BeforeAll
    //获取token
    public static void getToken() {
        token= given()
                .param("corpid", "ww5cb22ff56fcbfe9f")
                .param("corpsecret", "IGEnLoxWYzVQ8bgKSlQH4kW78Vw9CEOWsd7t6t_igvY")
                .when().log().all()
                       .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                       .body("errcode", equalTo(0))
                .extract().body().path("access_token");
        System.out.println("token是"+token);

    }
   @Test
   //创建部门
   //Query params:	access_token=JzxVuGIhpuu8xxTex0bH0hTiXxpNdj3M_5hWwfwiGDxpZ3mPI7qXLvlP87kQ9vp0E5P0knefLFM3dtfkm9pp6Ka00H64Gv24V1VZCY9HuiDQzxlhXSYo-dbcUDYh8dz9FHJyd5LwVV7c_B5ajlvZInHdNMCBxFfZ0CFdRzIGWBJrhQaI-8FDDQLxOiN2FDwwa1QpQclaGYld-s8EmBGvRw
    public void deptCreate(){
       HashMap<String,Object> data=new HashMap<>();
       data.put("name","测试部门1");
       data.put("parentid",parentDeptID);

       given()
               .queryParam("access_token",token)
               .contentType(ContentType.JSON)
               .body(data)
               .when().log().all()
                       .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
               .then().log().all()
               .body("errcode",equalTo(0));
       //需要list接口娇艳，但是如果便携list请求，会导致代码荣誉带来维护问题，所以引入po思想
   }
   @Test
    public void departList(){
        given()
                .queryParam("access_token",token)
                .queryParam("id",parentDeptID)
                .when().log().all()
                        .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                        .body("errcode",equalTo(0));
   }

}
