/**
 * @Project Name :  testunitnew
 * @Package Name :  service.User.testcase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2020-01-13 14:01
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package service.User.testcase;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

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

}