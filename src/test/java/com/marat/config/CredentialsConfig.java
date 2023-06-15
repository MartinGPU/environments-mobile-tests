package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {

    String userName();

    String password();

    String remoteUrl();

}
