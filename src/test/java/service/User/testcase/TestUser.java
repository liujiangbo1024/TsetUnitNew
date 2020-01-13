package service.User.testcase;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import service.User.api.User;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
    /*参数化，测试用例的csv*/
    @ParameterizedTest
    @CsvSource({
            "abc,abc",
            "mn,mn",
            "111,222"
    })
    public void deleteByParams(String name,String userid){
        String nameNew = name;
        if(userid.isEmpty()) {
            userid = "a131_" + System.currentTimeMillis();
        }

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

    /*外部数据源,测试用例的参数化 csv 文件 resources放在resource是下和我的类同级*/
    @ParameterizedTest
    @CsvFileSource(resources = "TestUser.csv")
    public void deleteByParamsA(String name,String userid){
        String nameNew = name;
        if(userid.isEmpty()) {
            userid = "a131_" + System.currentTimeMillis();
        }

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

    /*比较复杂的数据使用MethodSource,数据格式不受限制*/
    @ParameterizedTest
    @MethodSource("deleteByParamsFromYamlData")
    public void deleteByParamsFromYaml(String name,String userid,List<Integer> departs){
        String nameNew = name;
        if(userid.isEmpty()) {
            userid = "a131_" + System.currentTimeMillis();
        }
        if(departs==null){
            departs= Arrays.asList(4);
        }

        HashMap<String,Object> data=new HashMap<>();
        data.put("name",nameNew);
        data.put("department",departs);
        data.put("mobile",String.valueOf(System.currentTimeMillis()).substring(0,11));
        //data.put("emial","123456@qq.com");

        User user=new User();
        user.cloneUser(userid,data).then().body("errcode",equalTo(0));
        user.delete(userid).then().body("errcode",equalTo(0));
        user.get(userid).then().body("errcode",not(equalTo(0)));
    }

    /*https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
     junit5 的参数化参考*/
    static Stream<Arguments> deleteByParamsFromYamlData() {

        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());

        TypeReference<List<HashMap<String, Object>>> typeRef = new TypeReference<List<HashMap<String, Object>>>() {};
        List<HashMap<String, Object>> data;
        try {

            //将yaml中的文件转化成   List<HashMap<String, Object>>  对象
           data= mapper.readValue(TestUser.class.getResourceAsStream("/service/User/testcase/TestUser.yaml"), typeRef);
            ArrayList<Arguments> results=new ArrayList<>();
            data.forEach(map->{
                results.add(arguments(
                        map.get("name").toString(),
                        map.get("userid").toString(),
                        map.get("departs")));
            });
            return results.stream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Stream.of();
    }
}
