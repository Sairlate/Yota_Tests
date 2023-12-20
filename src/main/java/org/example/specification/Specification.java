package org.example.specification;

import org.example.service.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    static Config config = org.aeonbits.owner.ConfigFactory.create(Config.class);
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(config.baseURI())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(statusCode)
                .build();
    }
}
