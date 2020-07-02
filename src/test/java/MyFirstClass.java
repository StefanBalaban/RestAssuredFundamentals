import static io.restassured.RestAssured.*;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

public class MyFirstClass extends VideoGameConfig {
  @Test
  public void testMethod() {
    given()
        .log().all()
    .when()
        .get("videogames")
    .then()
        .log().all();
  }
  @Test
  public void testWithEndpoint() {
    get(VideoGameEndpoints.ALL_VIDEOGAMES)
        .then().log().all();
  }
}
