import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import io.restassured.response.Response;

public class VideoGameDbTests extends VideoGameConfig {
  @Test
  public  void getAllGames() {
    given()
    .when()
        .get(VideoGameEndpoints.ALL_VIDEOGAMES)
    .then();
  }
  @Test
  public void createNewGameByJson() {
    String gameBodyJson = "{\n" +
        "  \"id\": 0,\n" +
        "  \"name\": \"string\",\n" +
        "  \"releaseDate\": \"2020-06-13T17:16:24.993Z\",\n" +
        "  \"reviewScore\": 0,\n" +
        "  \"category\": \"string\",\n" +
        "  \"rating\": \"string\"\n" +
        "}";
    given()
        .body(gameBodyJson)
    .when()
        .post(VideoGameEndpoints.ALL_VIDEOGAMES)
    .then();
  }
  @Test
  public void createNewGameByXML() {
    String gameBodyXML = "<videoGame category=\"string\" rating=\"string\">\n" +
        "    <id>13</id>\n" +
        "    <name>string</name>\n" +
        "    <releaseDate>2020-06-13T00:00:00+02:00</releaseDate>\n" +
        "    <reviewScore>0</reviewScore>\n" +
        "  </videoGame>";
    given()
        .body(gameBodyXML)
        .header("Content-Type", "application/xml")
        .header("Accept", "application/xml")
    .when()
        .post(VideoGameEndpoints.ALL_VIDEOGAMES)
    .then();
  }
  @Test
  public void updateGame() {
    String gameBodyJson = "{\n" +
        "  \"id\": 1,\n" +
        "  \"name\": \"Novi\",\n" +
        "  \"releaseDate\": \"2020-06-13T17:16:24.994Z\",\n" +
        "  \"reviewScore\": 0,\n" +
        "  \"category\": \"string\",\n" +
        "  \"rating\": \"string\"\n" +
        "}";
    given()
        .body(gameBodyJson)
    .when()
        .put("videogames/1")
    .then();
  }
  @Test
  public void deleteGame() {
    given()
    .when()
        .delete("videogames/1")
    .then();
  }
  @Test
  public  void getSingleGame() {
    given()
        .pathParam("videoGameId", 5)
    .when()
        .get(VideoGameEndpoints.SINGLE_VIDEOGAME)
    .then();
  }
  @Test
  public void testVideoGameSerializationByJson() {
    VideoGame videoGame = new VideoGame("99", "2018-04-04", "Game", "Mature", 15, "Shoter");
    given()
        .body(videoGame)
    .when()
        .post(VideoGameEndpoints.ALL_VIDEOGAMES)
    .then();
  }
  @Test
  public void validateJsonSchema() {
    given()
        .pathParam("videoGameId", 5)
    .when()
        .get(VideoGameEndpoints.SINGLE_VIDEOGAME)
    .then()
        .body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));

  }
  @Test
  public void convertJsonToPojo() {
    Response response =
        given().pathParam("videoGameId", 5)
        .when().get(VideoGameEndpoints.SINGLE_VIDEOGAME);
    VideoGame videoGame = response.getBody().as(VideoGame.class);
    System.out.println(videoGame);
  }
  @Test
  public void extractReponseTime() {
    long responseTime = get(VideoGameEndpoints.ALL_VIDEOGAMES).time();
    System.out.println(responseTime);
  }
  @Test
  public void assertResponseTime() {
    when().get(VideoGameEndpoints.ALL_VIDEOGAMES)
    .then().time(lessThan(1050L));
  }
}
