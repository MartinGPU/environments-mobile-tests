package com.marat.tests.selenoid;

import com.codeborne.selenide.Configuration;
import com.marat.config.RealConfig;
import com.marat.drivers.RealMobileDriver;
import com.marat.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class SelenoidTestBase {

    public static RealConfig real = ConfigFactory.create(RealConfig.class, System.getProperties());

    public static String
            getRealPlatformName,
            getRealDeviceName,
            getRealVersionName,
            getRealLocale,
            getRealLanguage,
            getRealAppPackage,
            getRealAppActivity,
            getRealAppPath;

    @BeforeAll

    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = RealMobileDriver.class.getName();
        //Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        getRealPlatformName = real.getRealPlatformName();
        getRealDeviceName = real.getRealDeviceName();
        getRealVersionName = real.getRealVersionName();
        getRealLocale = real.getRealLocale();
        getRealLanguage = real.getRealLanguage();
        getRealAppPackage = real.getRealAppPackage();
        getRealAppActivity = real.getRealAppActivity();
        getRealAppPath = real.getRealAppPath();
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
