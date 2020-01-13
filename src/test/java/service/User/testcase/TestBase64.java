/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-13 15:18
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.testcase;


import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * @Description  :  加密解密 JDK1.8 之后的
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-13 15:18
 */
public class TestBase64
{

     @Test
     void testBase64(){

      String str1="nihao123333";
      //加密
      byte[] data=Base64.getEncoder().encode(str1.getBytes());
      String encode=new String(Base64.getEncoder().encode(str1.getBytes()));
      System.out.println("加密后的字符串是："+encode);

      //解密
     String decode=new String(Base64.getDecoder().decode(data));
      System.out.println("解密后的字符串是："+decode);


     }

}