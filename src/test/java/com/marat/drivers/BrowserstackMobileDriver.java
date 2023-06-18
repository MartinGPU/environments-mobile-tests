package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.marat.config.BrowserStackConfig;
import com.marat.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    public static BrowserStackConfig browserStack = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("userName", credentials.userName());
        desiredCapabilities.setCapability("password", credentials.password());

        desiredCapabilities.setCapability("appLocation", browserStack.getAppLocation());
        desiredCapabilities.setCapability("deviceName", browserStack.getDeviceName());
        desiredCapabilities.setCapability("deviceVersion", browserStack.getDeviceVersion());
        desiredCapabilities.setCapability("projectName", browserStack.getProjectName());
        desiredCapabilities.setCapability("buildName", browserStack.getBuildName());
        desiredCapabilities.setCapability("testName", browserStack.getTestName());

        try {
            return new RemoteWebDriver(
                    new URL(browserStack.getRemoteWebUrl()), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

