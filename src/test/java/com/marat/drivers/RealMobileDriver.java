package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.marat.tests.real.RealTestBase.*;

public class RealMobileDriver implements WebDriverProvider {


    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("realPlatformName", getRealPlatformName);
        desiredCapabilities.setCapability("realDeviceName", getRealDeviceName);
        desiredCapabilities.setCapability("realVersionName", getRealVersionName);
        desiredCapabilities.setCapability("realLocale", getRealLocale);
        desiredCapabilities.setCapability("realLanguage", getRealLanguage);
        desiredCapabilities.setCapability("realAppPackage", getRealAppPackage);
        desiredCapabilities.setCapability("realAppActivity", getRealAppActivity);
        desiredCapabilities.setCapability("realAppPath", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));


        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }


    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}
