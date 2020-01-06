package service.User.testcase;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import service.User.api.User;
import service.department.Work;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TestUser {


    @Test
    //读取成员
    public void info(){
    User user=new User();
    user.get("a131").then().body("name",equalTo("a131"));
    }
    @Test
    //获取更新
    public void update(){
        User user=new User();
        String userid = "a131";
        String nameNew = "test for new";
        HashMap<String,Object> data=new HashMap<>();
        data.put("name",nameNew);
        data.put("address","address for new");
        user.update(userid,data);
        user.get(userid).then().body("name",equalTo(nameNew));
    }

    //
    @Test
    public void create(){
        String nameNew = "test for new";
        String userid = "a131_"+System.currentTimeMillis();
        HashMap<String,Object> data=new HashMap<>();
        data.put("name",nameNew);
        data.put("address","address for new");
        data.put("department",new int[]{4});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        //data.put("emial","123456@qq.com");

        User user=new User();
        user.create(userid,data).then().body("errcode",equalTo(0));
        user.get(userid).then().body("name",equalTo(nameNew));

    }
    @Test
    //测试创建用户的方法：使用模版创建用户
    public void cloneUser(){
       // User user=new User();
       // user.get("a1237");

        String nameNew = "test for new";
        String userid = "a131_"+System.currentTimeMillis();

        HashMap<String,Object> data=new HashMap<>();
        data.put("name",nameNew);
        data.put("department",new int[]{4});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        //data.put("emial","123456@qq.com");

        User user=new User();
        user.cloneUser(userid,data).then().body("errcode",equalTo(0));
        user.get(userid).then().body("name",equalTo(nameNew));

    }


    @Test
    //测试Mustache模版，将json 或者其他变成模版 参考https://github.com/spullara/mustache.java
    public void template() throws IOException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Mustache");
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        data.put("userid",System.currentTimeMillis());

        //Writer writer = new OutputStreamWriter(System.out);
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("service/User/api/user.json");
        mustache.execute(writer, data);
        writer.flush();
        System.out.println(writer.toString());
    }

    @Test
    public void delete(){
        String nameNew = "test for new";
        String userid = "a131_"+System.currentTimeMillis();

        HashMap<String,Object> data=new HashMap<>();
        data.put("name",nameNew);
        data.put("department",new int[]{4});
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        //data.put("emial","123456@qq.com");

        User user=new User();
        user.cloneUser(userid,data).then().body("errcode",equalTo(0));
        user.delete(userid).then().body("errcode",equalTo(0));
        user.get(userid).then().body("errcode",not(equalTo(0)));
    }
}
