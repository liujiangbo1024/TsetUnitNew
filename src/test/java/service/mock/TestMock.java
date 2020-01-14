/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-13 18:57
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

/**
 * @Description  :  wireMock
 * @author       :  liujiangbo
 * @Creation Date:  2020-01-13 18:57
 */
public class TestMock {
    static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(options().port(8088)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        configureFor("localhost", 8088);
        System.out.println("mock server start");
    }

    @Test
    void stub() throws InterruptedException {
        stubFor(get(urlEqualTo("/stub")).willReturn(aResponse().withBody("stub demo")));

        given()
                .when().log().all().get("http://127.0.0.1:8088/stub")
                .then().log().all().body(containsString("stub"));

        Thread.sleep(100000);

    }


}