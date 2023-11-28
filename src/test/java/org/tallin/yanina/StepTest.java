package org.tallin.yanina;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$x;

public class StepTest {

//https://selenide.org/javadoc/current/com/codeborne/selenide/Condition.html

    @Test
    public void insertingIncorrectCredentials() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;

        Selenide.open("http://51.250.6.164:3000/signin");
        $x("//input[@data-name=\"username-input\"]").setValue("AnyName");
        $x("//input[@data-name=\"password-input\"]").setValue("AnyPassword");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//div[@data-name=\"authorizationError-popup\"]").shouldBe(Condition.exist, Condition.visible);



    }

    @Test
    public void insertingCorrectCredentials() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;

        Selenide.open("http://51.250.6.164:3000/signin");
        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("p5Twdy789");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//button[@data-name=\"createOrder-button\"]").shouldBe(Condition.exist, Condition.visible);

    }

    @Test
    public void insertingIncorrectCredentialsAndReLogin() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;

        Selenide.open("http://51.250.6.164:3000/signin");
        $x("//input[@data-name=\"username-input\"]").setValue("AnyName");
        $x("//input[@data-name=\"password-input\"]").setValue("AnyPassword");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//div[@data-name=\"authorizationError-popup\"]").shouldBe(Condition.exist, Condition.visible);
        $x("//button[@data-name=\"authorizationError-popup-close-button\"]").click();

        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("p5Twdy789");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//button[@data-name=\"createOrder-button\"]").shouldBe(Condition.exist, Condition.visible);


    }

    @Test
    public void errorCheckingWithOneCharacterInLoginField() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;

        Selenide.open("http://51.250.6.164:3000/signin");
        $x("//input[@data-name=\"username-input\"]").setValue("W");


        $x("//*[contains(@class, \"form-error form-error_active\")]").shouldBe(Condition.exist, Condition.visible);
//$x("//*[text()=\"The field must contain  at least of characters: 2\"]").shouldBe(Condition.visible);

    }

    @Test
    public void errorCheckingWithOneCharacterInPasswordField() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;

        Selenide.open("http://51.250.6.164:3000/signin");
        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("1");


        $x("//*[contains(@class, \"form-error form-error_active\")]").shouldBe(Condition.exist, Condition.visible);


    }


}
