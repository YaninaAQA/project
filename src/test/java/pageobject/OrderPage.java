package pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {
    public SelenideElement name = $x("//*[@data-name=\"username-input\"]");
    public SelenideElement phone = $x("//*[@data-name=\"phone-input\"]");
    public SelenideElement comment = $x("//*[@data-name=\"comment-input\"]");
    public SelenideElement orderButton = $x("//*[@data-name=\"createOrder-button\"]");
    public SelenideElement successOrderPopUp = $x("//div[@data-name=\"orderSuccessfullyCreated-popup\"]");
    public SelenideElement statusButton = $x("//button[@data-name=\"openStatusPopup-button\"]");
    public SelenideElement searchIdField = $x("//input[@data-name=\"searchOrder-input\"]");
    public SelenideElement searchOrderTrackingButton = $x("//button[@data-name=\"searchOrder-submitButton\"]");
    public SelenideElement orderNotFoundPage = $x("//main[@data-name=\"orderNotFound-container\"]");

}
