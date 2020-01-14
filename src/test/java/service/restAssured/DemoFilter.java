/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-13 14:01
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.restAssured;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * @Description  :  TODO
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-13 14:01
 */
public class DemoFilter implements Filter{
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
       Response responseOrigin=ctx.next(requestSpec,responseSpec);
        return   new ResponseBuilder().clone(responseOrigin).setStatusCode(404).build();

    }

    /**
     * @Description  :  加密解密 JDK1.8 之后的
     * @author       :  liujiangbo
     * @Creation Date:  2020-01-13 15:18
     */
    public static class TestBase64
    {

         @Test
         void testBase64(){

          String str1="nihao123333";
          //加密
          byte[] data= Base64.getEncoder().encode(str1.getBytes());
          String encode=new String(Base64.getEncoder().encode(str1.getBytes()));
          System.out.println("加密后的字符串是："+encode);

          //解密
         String decode=new String(Base64.getDecoder().decode(data));
          System.out.println("解密后的字符串是："+decode);


         }

    }
}