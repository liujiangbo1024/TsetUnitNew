package service.department;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Work {
    private static Work work;
    String token;
    public  static Work getInstance(){
        if(work==null){
            work=new Work();
        }
        return work;
    }

    public  String  getToken(){
        if(token==null) {
            token = given()
                    .param("corpid", "ww5cb22ff56fcbfe9f")
                    .param("corpsecret", "IGEnLoxWYzVQ8bgKSlQH4kW78Vw9CEOWsd7t6t_igvY")
                    .when().log().all()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then().log().all()
                    .body("errcode", equalTo(0))
                    .extract().body().path("access_token");
            System.out.println("token是"+token);
        }

        return token;

    }
}
