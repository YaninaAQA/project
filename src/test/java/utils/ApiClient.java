package utils;

import com.google.gson.Gson;
import dto.LoginDto;
import dto.OrderDto;
import inheritance.BaseSetupApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient extends BaseSetupApi {
    public static Response executeGetRequest(RequestSpecification specification){

        return given()
                .spec(specification)
                .log()
                .all()
                .get( )
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response executePostRequestOrder(RequestSpecification specification, OrderDto order){

        return given()
                .spec(specification)
                .basePath("orders")
                .log()
                .all()
                .body(new Gson().toJson( order ))
                .post( )
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static String getBearerToken(){

        return given()
                .spec( getUnauthenticatedRequestSpecification())
                .basePath("login/student")
                .log()
                .all()
                .body( new Gson().toJson( new LoginDto(
                        configuration.getString("username"),
                        configuration.getString("password") )) )
                .post()
                .then()
                .log()
                .all()
                .extract()
                .response()
                .asString();

    }


}
