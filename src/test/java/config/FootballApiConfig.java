package config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FootballApiConfig {
  public static RequestSpecification football_requestSpec;
  public static ResponseSpecification football_responseSpec;

  @BeforeClass
  public static void setup() {
    football_requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://api.football-data.org")
        .setBasePath("/v2/")
        .addHeader("X-Auth-Token", "e158aa147632423394a0876e2658f39c")
        .addHeader("X-Response-Control", "minified")
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .build();
    football_responseSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .build();
    RestAssured.requestSpecification = football_requestSpec;
    RestAssured.responseSpecification = football_responseSpec;
  }

}
