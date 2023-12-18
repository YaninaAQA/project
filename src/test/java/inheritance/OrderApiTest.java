package inheritance;

import dto.OrderDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ApiClient;

public class OrderApiTest extends BaseSetupApi {
    @Test
    void createOrderWithCorrectTokenAndVerifyResponseBody() {

        Response response = ApiClient.executePostRequestOrder(
                getAuthenticatedRequestSpecification(),
                new OrderDto("name", "55446677", "comment"));

        Assertions.assertAll("Body Information",
                () -> Assertions.assertTrue(response.jsonPath().getInt("id") > 0, "id"),
                () -> Assertions.assertEquals("OPEN", response.jsonPath().getString("status")));
    }


    @Test
    void createOrderWithIncorrectTokenShouldReturnUnauthorized() {

        Response response = ApiClient.executePostRequestOrder(
                getUnauthenticatedRequestSpecification(),
                new OrderDto("name", "55446677", "comment"));

        Assertions.assertAll("Body Information",
                () -> Assertions.assertEquals( HttpStatus.SC_UNAUTHORIZED, response.getStatusCode() ),
                () -> Assertions.assertEquals("", response.getBody().asString() ));

    }


}
