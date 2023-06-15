package com.marat.tests.browserstack;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.marat.config.CredentialsConfig;
import com.marat.drivers.BrowserstackMobileDriver;
import com.marat.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.marat.helpers.Attach.getSessionId;

public class BrowserStackTestBase {

    public static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    public static String
            userName,
            password,
            remoteUrl;

    @BeforeAll
    static void beforeAll() {
        userName = credentials.userName();
        password = credentials.password();
        remoteUrl = credentials.remoteUrl();
        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();

        Attach.attachVideo(sessionId);
    }
}
