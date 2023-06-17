package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:local.properties"})
public interface LocalConfig extends Config {

    @Key("localPlatformName")
    String getLocalPlatformName();

    @Key("localDeviceName")
    String getLocalDeviceName();

    @Key("localVersionName")
    String getLocalVersionName();

    @Key("locale")
    String getLocale();

    @Key("language")
    String getLanguage();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();

    @Key("appPath")
    String getAppPath();
}
