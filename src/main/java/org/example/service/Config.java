package org.example.service;

@Config.Sources("file:src/main/resources/testConfig.properties")
public interface Config extends org.aeonbits.owner.Config {

    @Key("baseURI")
    String baseURI();

}
