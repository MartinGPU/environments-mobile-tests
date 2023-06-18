package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.marat.config.SelenoidConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidMobileDriver implements WebDriverProvider {

    public static SelenoidConfig selenoid = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL(selenoid.getSelenoidAppiumUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("platformName", selenoid.getSelenoidPlatformName());
        desiredCapabilities.setCapability("deviceName", selenoid.getSelenoidDeviceName());
        desiredCapabilities.setCapability("version", selenoid.getSelenoidVersionName());
        desiredCapabilities.setCapability("locale", selenoid.getSelenoidLocale());
        desiredCapabilities.setCapability("language", selenoid.getSelenoidLanguage());
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", true);
        desiredCapabilities.setCapability("appPackage", selenoid.getSelenoidAppPackage());
        desiredCapabilities.setCapability("appActivity", selenoid.getSelenoidAppActivity());
        desiredCapabilities.setCapability("app", apkUrl());

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }


    private URL apkUrl() {
        try {
            return new URL("https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/app-alpha-universal-release.apk");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
