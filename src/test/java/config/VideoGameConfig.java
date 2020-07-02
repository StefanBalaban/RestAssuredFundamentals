package config;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class VideoGameConfig {

  public static RequestSpecification videoGame_requestSpec;
  public static ResponseSpecification videoGame_responseSpec;

  @BeforeClass
  public static void setup() {
    videoGame_requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost/")
        .setBasePath("/app/")
        .setPort(8080)
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .build();
    videoGame_responseSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .build();
    RestAssured.requestSpecification = videoGame_requestSpec;
    RestAssured.responseSpecification = videoGame_responseSpec;
  }

}
