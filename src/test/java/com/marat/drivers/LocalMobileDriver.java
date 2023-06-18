package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.marat.config.LocalConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalMobileDriver implements WebDriverProvider {

    public static LocalConfig local = ConfigFactory.create(LocalConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL(local.getAppiumUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("localPlatformName", local.getLocalPlatformName());
        desiredCapabilities.setCapability("localDeviceName", local.getLocalDeviceName());
        desiredCapabilities.setCapability("localVersionName", local.getLocalVersionName());
        desiredCapabilities.setCapability("locale", local.getLocale());
        desiredCapabilities.setCapability("language", local.getLanguage());
        desiredCapabilities.setCapability("appPackage", local.getAppPackage());
        desiredCapabilities.setCapability("appActivity", local.getAppActivity());
        desiredCapabilities.setCapability("app", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");
        return file.getAbsolutePath();
    }
}
