package inheritance;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.BeforeAll;
import utils.ApiClient;

public class BaseSetupApi {
    private final static String PATH_TO_CONFIG = "application-template.yaml";
    protected static PropertiesConfiguration configuration;

    @BeforeAll
    public static void setUp() throws ConfigurationException {
        configuration = new PropertiesConfiguration();
        configuration.load(PATH_TO_CONFIG);
        RestAssured.baseURI = configuration.getString("base-url");
    }

    public static RequestSpecification getAuthenticatedRequestSpecification(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Authorization", "Bearer " + ApiClient.getBearerToken() );
        return builder.build();
    }

    public static RequestSpecification getUnauthenticatedRequestSpecification(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        return builder.build();
    }


}
