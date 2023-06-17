package com.marat.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static com.marat.tests.browserstack.BrowserStackTestBase.*;

public class BrowserstackMobileDriver implements WebDriverProvider {


    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", userName);
        desiredCapabilities.setCapability("browserstack.key", password);

        // Set URL of the application under test
        desiredCapabilities.setCapability("appLocation", getAppLocation);

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("deviceName", getDeviceName);
        desiredCapabilities.setCapability("deviceVersion", getDeviceVersion);

        // Set other BrowserStack mutableCapabilities
        desiredCapabilities.setCapability("projectName", getProjectName);
        desiredCapabilities.setCapability("buildName", getBuildName);
        desiredCapabilities.setCapability("testName", getTestName);


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desiredCapabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(remoteUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

