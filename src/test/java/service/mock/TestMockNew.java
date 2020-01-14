package service.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


/*wiremock的 本地创建服务
*
* */
public class TestMockNew {
    static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(options().port(8089)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        configureFor("localhost", 8089);
        System.out.println("mock server start");
    }

    @Test
    void stub() throws InterruptedException {
        stubFor(get(urlEqualTo("/stub")).willReturn(aResponse().withBody("stub demo")));

        given()
                .when().log().all().get("http://127.0.0.1:8089/stub")
                .then().log().all().body(containsString("stub"));

        Thread.sleep(100000);

    }


    @Test
    /*mock 将帖子页面做修改
    * 这个大部分请求透传过去，只有特殊请求mock（/api/v3/topics.json）
    * */
    void mock() throws InterruptedException {
        // Low priority catch-all proxies to otherhost.com by default
        stubFor(get(urlMatching(".*")).atPriority(10)
                .willReturn(aResponse().proxiedFrom("http://106.75.214.88")));


        // High priority stub will send a Service Unavailable response
        // if the specified URL is requested 这个请求拦截，其他请求不拦截
        //https://106.75.214.88/api/v3/topics.json?limit=3 这个没拦截
        stubFor(get(urlEqualTo("/api/v3/topics.json")).atPriority(1)
                .willReturn(aResponse().withStatus(200).withBody("demo")));

        /*http://127.0.0.1:8089/api/v3/topics.json  这个请求拦截*/
        /*http://127.0.0.1:8089/api/v3/topics.json?limit=3  这个请求不拦截*/

        Thread.sleep(100000);
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }
}
