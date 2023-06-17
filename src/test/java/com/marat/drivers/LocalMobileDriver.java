package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static com.marat.tests.local.LocalTestBase.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalMobileDriver implements WebDriverProvider {


    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("localPlatformName", getLocalPlatformName);
        desiredCapabilities.setCapability("localDeviceName", getLocalDeviceName);
        desiredCapabilities.setCapability("localVersionName", getLocalVersionName);
        desiredCapabilities.setCapability("locale", getLocale);
        desiredCapabilities.setCapability("language", getLanguage);
        desiredCapabilities.setCapability("appPackage", getAppPackage);
        desiredCapabilities.setCapability("appActivity", getAppActivity);
        desiredCapabilities.setCapability("app", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}
