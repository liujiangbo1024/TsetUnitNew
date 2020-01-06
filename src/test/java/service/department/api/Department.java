package service.department.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.department.Work;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Department {
    public int parentDeptID=4;

    public Response list(int id){
        Response response=given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("id",id)
                .when().log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .body("errcode",equalTo(0))
        .extract().response();
        return response;
    }

    public  Response create(String name,int parentid){
        HashMap<String,Object> data=new HashMap<>();
        data.put("name",name);
        data.put("parentid",parentid);

      return  given()
                .queryParam("access_token",Work.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all()
                .body("errcode",equalTo(0))
        .extract().response();
    }

    public Response delete(int id){
      return   given()
                .queryParam("access_token",Work.getInstance().getToken())
                .queryParam("id",id)
                .when().log().all()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .body("errcode",equalTo(0)).extract().response();

    }

}
