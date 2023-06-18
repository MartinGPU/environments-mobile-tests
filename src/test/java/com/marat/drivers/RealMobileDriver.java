package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.marat.config.RealConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealMobileDriver implements WebDriverProvider {

    public static RealConfig real = ConfigFactory.create(RealConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL(real.getRealAppiumUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("realPlatformName", real.getRealPlatformName());
        desiredCapabilities.setCapability("realDeviceName", real.getRealDeviceName());
        desiredCapabilities.setCapability("realVersionName", real.getRealVersionName());
        desiredCapabilities.setCapability("realLocale", real.getRealLocale());
        desiredCapabilities.setCapability("realLanguage", real.getRealLanguage());
        desiredCapabilities.setCapability("realAppPackage", real.getRealAppPackage());
        desiredCapabilities.setCapability("realAppActivity", real.getRealAppActivity());
        desiredCapabilities.setCapability("realAppPath", getAbsolutePath("src/test/resources/app-alpha-universal-release.apk"));


        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }


    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}
