package org.tallin.yanina;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class StepTest {

//https://selenide.org/javadoc/current/com/codeborne/selenide/Condition.html

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
        Configuration.headless = true;
        open("http://51.250.6.164:3000/signin");
    }

    @Test
    public void insertingIncorrectCredentials() {


        $x("//input[@data-name=\"username-input\"]").setValue("AnyName");
        $x("//input[@data-name=\"password-input\"]").setValue("AnyPassword");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//div[@data-name=\"authorizationError-popup\"]").shouldBe(Condition.exist, Condition.visible);
        $x("//button[@data-name=\"authorizationError-popup-close-button\"]").click();


    }

    @Test
    public void errorCheckingWithOneCharacterInLoginField() {


        $x("//input[@data-name=\"username-input\"]").setValue("W");


        $x("//*[@data-name=\"username-input\"]/following-sibling::span[@data-name=\"username-input-error\"]").shouldBe(Condition.exist, Condition.visible);

        $x("//input[@data-name=\"username-input\"]").clear();


    }

    @Test
    public void errorCheckingWithOneCharacterInPasswordField() {


        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("1");


        $x("//*[@data-name=\"password-input\"]/following-sibling::span[@data-name=\"username-input-error\"]").shouldBe(Condition.exist, Condition.visible);

        $x("//input[@data-name=\"password-input\"]").clear();


    }


    @Test
    public void insertingIncorrectCredentialsAndReLogin() {


        $x("//input[@data-name=\"username-input\"]").setValue("AnyName");
        $x("//input[@data-name=\"password-input\"]").setValue("AnyPassword");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//div[@data-name=\"authorizationError-popup\"]").shouldBe(Condition.exist, Condition.visible);
        $x("//button[@data-name=\"authorizationError-popup-close-button\"]").click();

        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("p5Twdy789");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//button[@data-name=\"createOrder-button\"]").shouldBe(Condition.exist, Condition.visible);

        $x("//a[@data-name=\"logout-button\"]").click();
        $x("//button[@data-name=\"signIn-button\"]").shouldBe(Condition.exist, Condition.visible);


    }

    @Test
    public void insertingCorrectCredentials() {


        $x("//input[@data-name=\"username-input\"]").setValue("yaninaaqa");
        $x("//input[@data-name=\"password-input\"]").setValue("p5Twdy789");
        $x("//button[@data-name=\"signIn-button\"]").click();

        $x("//button[@data-name=\"createOrder-button\"]").shouldBe(Condition.exist, Condition.visible);

        $x("//a[@data-name=\"logout-button\"]").click();

    }


}
