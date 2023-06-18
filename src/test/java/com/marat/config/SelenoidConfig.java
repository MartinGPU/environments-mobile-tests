package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})
public interface SelenoidConfig extends Config{

    @Config.Key("selenoidPlatformName")
    String getSelenoidPlatformName();

    @Config.Key("selenoidDeviceName")
    String getSelenoidDeviceName();

    @Config.Key("selenoidVersion")
    String getSelenoidVersionName();

    @Config.Key("selenoidLocale")
    String getSelenoidLocale();

    @Config.Key("selenoidLanguage")
    String getSelenoidLanguage();

    @Config.Key("selenoidAppPackage")
    String getSelenoidAppPackage();

    @Config.Key("selenoidAppActivity")
    String getSelenoidAppActivity();

    @Config.Key("selenoidAppiumUrl")
    String getSelenoidAppiumUrl();

}
