package org.trelloAPI.services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Properties;

import static org.trelloAPI.utils.PROPERTIESReader.getConfigs;

public class APIManager {

    public static RequestSpecification userReqSpecs;
    public Properties config;
    @BeforeMethod
    public void extractConfigData() {
        try
        {
            config = getConfigs("src/main/java/Config.properties");
            userReqSpecs = RestAssured.given()
                    .baseUri(config.getProperty("baseURL"))
                    .basePath(config.getProperty("basePath"))
                    .queryParam("key", config.getProperty("APIKey"))
                    .queryParam("token", config.getProperty("APIToken"));

        }
        catch(IOException e){
            System.out.println("Config File not found!!!");
        }
    }
}
