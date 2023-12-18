package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import inheritance.BaseSetupApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiClient;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest extends BaseSetupApi {

    private static String token;
    OrderPage orderPage = new OrderPage();


    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        token = ApiClient.getBearerToken();
    }

    @BeforeEach
    public void setUpUi() {
        open(configuration.getString("ui-url"));
        Selenide.localStorage().setItem("jwt", token);
        Selenide.refresh();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void createOrderWithCorrectCredentials() {
        orderPage.name.sendKeys("TestYanina");
        orderPage.phone.sendKeys("123456");
        orderPage.comment.sendKeys("No Comment");
        orderPage.orderButton.click();
        orderPage.successOrderPopUp.shouldBe(Condition.exist, visible);
    }

    @Test
    public void createOrderWithIncorrectCredentialsAndOrderButtonIsDisabled() {
        orderPage.name.sendKeys("1");
        orderPage.orderButton.shouldBe(disabled);

    }

    @Test
    public void SearchOrderWithIncorrectOrderId() {

        orderPage.statusButton.click();
        orderPage.searchIdField.sendKeys("123");
        orderPage.searchOrderTrackingButton.click();
        orderPage.orderNotFoundPage.shouldBe(Condition.exist, Condition.visible);


    }
}
