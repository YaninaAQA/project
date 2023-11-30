package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {
    OrderPage orderPage = new OrderPage();


    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
    }

    @BeforeEach
    public void setUp() {
        open("http://51.250.6.164:3000/signin");
        Selenide.localStorage().setItem("jwt", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5YW5pbmFhcWEiLCJleHAiOjE3MDEzNzY2NjQsImlhdCI6MTcwMTM1ODY2NH0.C-VeKQrEWOFNMiwn8i7GIT0aZATcesXGUARwPIdFT4gJ3d14ar1_ZrZg54PhluBV_hI4xPIcOf1ZZpdgeoJ3FQ");
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
