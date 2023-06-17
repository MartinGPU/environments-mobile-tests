package com.marat.tests.local;

import com.codeborne.selenide.Configuration;
import com.marat.config.LocalConfig;
import com.marat.drivers.LocalMobileDriver;
import com.marat.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class LocalTestBase {

    public static LocalConfig local = ConfigFactory.create(LocalConfig.class, System.getProperties());

    public static String getLocalPlatformName,
            getLocalDeviceName,
            getLocalVersionName,
            getLocale,
            getLanguage,
            getAppPackage,
            getAppActivity,
            getAppPath;

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = LocalMobileDriver.class.getName();
        //Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        getLocalPlatformName = local.getLocalPlatformName();
        getLocalDeviceName = local.getLocalDeviceName();
        getLocalVersionName = local.getLocalVersionName();
        getLocale = local.getLocale();
        getLanguage = local.getLanguage();
        getAppPackage = local.getAppPackage();
        getAppActivity = local.getAppActivity();
        getAppPath = local.getAppPath();
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();
    }
}
