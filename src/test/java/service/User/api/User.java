package service.User.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.department.Work;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
/*关于成员的增删改查 */
public class User {

    //读取成员
    public Response get(String userid){
        return   given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("userid",userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
    }
    //修改成员
    public Response update(String userid, HashMap<String, Object> data) {
        data.put("userid",userid);
       return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .body(data)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/update")
                .then().log().all()
                .extract().response();
    }

    //删除成员
    public Response delete(String userid) {
        return   given()
                .queryParam("access_token", Work.getInstance().getToken())
                .queryParam("userid",userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then().log().all().extract().response();
    }
    //新增成员
    public Response create(String userid, HashMap<String, Object> data) {
        data.put("userid",userid);
        return given()
                .queryParam("access_token", Work.getInstance().getToken())
                .body(data)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all()
                .extract().response();
    }

 //根据模版创建成员
 public Response cloneUser(String userid, HashMap<String, Object> data) {
     data.put("userid",userid);
    String body= template("service/User/api/user.json",data);//调用template 处理模版
     return given()
             .queryParam("access_token", Work.getInstance().getToken())
             .contentType(ContentType.JSON)
             .body(body)
             .when().log().all().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
             .then().log().all()
             .extract().response();
 }

 //将模版数据处理成 String（MustacheFactory）
 public static String template(String templatePath,HashMap<String,Object> data){
     Writer writer = new StringWriter();
     MustacheFactory mf = new DefaultMustacheFactory();
     Mustache mustache = mf.compile(templatePath);
     mustache.execute(writer, data);
     try {
         writer.flush();
     } catch (IOException e) {
         e.printStackTrace();
     }
     System.out.println(writer.toString());
     return  writer.toString();
 }



}
