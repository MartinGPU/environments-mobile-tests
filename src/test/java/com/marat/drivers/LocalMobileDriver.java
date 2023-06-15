package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalMobileDriver implements WebDriverProvider {


    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("platformName", "android");
        mutableCapabilities.setCapability("deviceName", "Pixel_4_API_30");
        mutableCapabilities.setCapability("version", "11.0");
        mutableCapabilities.setCapability("locale", "en");
        mutableCapabilities.setCapability("language", "en");
        mutableCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
        mutableCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        mutableCapabilities.setCapability("app", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));

        return new AndroidDriver(getAppiumServerUrl(), capabilities);
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}
