import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import config.FootballApiConfig;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class FootballApiTests extends FootballApiConfig {
  @Test
  public void getDetailsOfOneArea() {
    given()
        .queryParam("areas", 2072)
    .when()
        .get("areas")
    .then();
    }
  @Test
  public  void getDateFounded() {
    given()
    .when()
        .get("teams/57")
    .then()
        .body("founded", equalTo(1886));
  }
  @Test
  public  void getFirstTeamsName() {
    given()
        .when()
        .get("competitions/2021/teams")
        .then()
        .body("teams[1].name", equalTo("Aston Villa FC"));
  }
  @Test
  public void extractReponseBody() {
    String responseBody = get("teams/57").asString();
    System.out.println(responseBody);
  }
  @Test
  public void extractReponseBodyCheckFirst() {
    Response response =
        given()
        .when()
          .get("teams/57")
        .then()
          .contentType(ContentType.JSON)
          .extract().response();
    String jsonResponseAsString = response.asString();
    System.out.println(jsonResponseAsString);
  }
  @Test
  public void extractHeaders() {
    Response response =
        given()
            .when()
            .get("teams/57")
            .then()
            .contentType(ContentType.JSON)
            .extract().response();
    Headers headers = response.getHeaders();
    String contentTypeHeader = response.header("Content-Type");
  }
  @Test
  public  void extractFirstTeamName(){
    String firstName =
        get("competitions/2021/teams")
          .jsonPath()
          .getString("teams[0].name");
    System.out.println(firstName);
  }
  @Test
  public void extractTeamNames(){
    Response response =
        given()
        .when()
          .get("competitions/2021/teams")
        .then()
          .extract()
          .response();
    List<String> listOfNames = response.path("teams.name");

    for(String teamName: listOfNames)
      System.out.println(teamName);
  }
}
