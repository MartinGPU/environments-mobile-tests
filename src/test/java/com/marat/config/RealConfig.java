package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})
public interface RealConfig extends Config {

    @Key("realPlatformName")
    String getRealPlatformName();

    @Key("realDeviceName")
    String getRealDeviceName();

    @Key("realVersionName")
    String getRealVersionName();

    @Key("realLocale")
    String getRealLocale();

    @Key("realLanguage")
    String getRealLanguage();

    @Key("realAppPackage")
    String getRealAppPackage();

    @Key("realAppActivity")
    String getRealAppActivity();

    @Key("realAppiumUrl")
    String getRealAppiumUrl();
}
