package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:credentials.properties"})
public interface CredentialsConfig extends Config {

    @Key("userName")
    String userName();

    @Key("password")
    String password();

    String remoteUrl();

    String remoteUri();

}
