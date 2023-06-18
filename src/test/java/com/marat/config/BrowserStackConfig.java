package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${deviceHost}.properties"})
public interface BrowserStackConfig extends Config {

    @Key("appLocation")
    String getAppLocation();

    @Key("deviceName")
    String getDeviceName();

    @Key("deviceVersion")
    String getDeviceVersion();

    @Key("projectName")
    String getProjectName();

    @Key("buildName")
    String getBuildName();

    @Key("testName")
    String getTestName();

    @Key("remoteWebUrl")
    String getRemoteWebUrl();
}
