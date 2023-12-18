package remote;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.LoginPage;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class LoginRemoteBrowserTest {
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUpAll() {

        Configuration.browserSize = "1280x800";
        Configuration.remote = "http://34.68.42.160:4444" + "/wd/hub";
        ;
        Configuration.browser = "Firefox";
        Configuration.browserVersion = "115.0";
        Configuration.browserSize = "1920x1080";

        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
    }

    @BeforeEach
    public void setUp() {
        open("http://51.250.6.164:3000/signin");
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
        Selenide.refresh();
    }

    @Test
    public void incorrectCredentials() {
        loginPage.login.sendKeys("login");
        loginPage.password.sendKeys("password");
        loginPage.signInButton.click();
        loginPage.errorIncorrectCredentials.shouldBe(visible);
    }


}

//http://34.68.42.160:8080/