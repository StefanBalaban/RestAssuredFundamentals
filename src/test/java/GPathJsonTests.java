import static io.restassured.RestAssured.get;

import java.util.List;
import java.util.Map;

import config.FootballApiConfig;
import org.junit.Test;

import io.restassured.response.Response;

public class GPathJsonTests extends FootballApiConfig {
  Response teamsResponse;
  @Test
  public void extractMapOfElementsWithFind() {
    Response response = get("competitions/2021/teams");
    Map<String, ?> allTeamDataForSingleTeam = response
        .path("teams.find {it.name == 'Manchester United FC'}");
    System.out.println("Map of Team Data = " + allTeamDataForSingleTeam);
  }
  @Test
  public void extractSingleValueWithFind() {
    getTeamsInfo();
    String certainPlayer = teamsResponse.path("squad.find {it.shirtNumber == 23}.name");
    System.out.println(certainPlayer);
  }
  @Test
  public void extractMultipleValuesWithFindAll() {
    getTeamsInfo();
    List<String> listOfPlayers = teamsResponse.path("squad.findAll {it.shirtNumber >= 23}.name");
    System.out.println(listOfPlayers);
  }
  @Test
  public void extractMaxValue() {
    getTeamsInfo();
    String playerWithMaxShirtNumber = teamsResponse.path("squad.max {it.shirtNumber}.name");
    System.out.println(playerWithMaxShirtNumber);
  }
  @Test
  public void extractAndSumIdValues() {
    getTeamsInfo();
    int sumOfId = teamsResponse.path("squad.collect {it.id}.sum()");
    System.out.println(sumOfId);
  }
  @Test
  public void extractWithFindAllAndFind() {
    getTeamsInfo();
    String playerOfCertainPosition = teamsResponse
        .path("squad.findAll {it.position = 'Defender'}.find {it.nationality == 'Greece'}.name");
    System.out.println(playerOfCertainPosition);
  }
  @Test
  public void extractFindAndFind() {
    String position = "Defender";
    String nationality = "Greece";
    getTeamsInfo();
    String playerOfCertainPosition = teamsResponse
        .path("squad.find {it.position = '%s' && it.nationality == '%s'}.name",
            position, nationality);
    System.out.println(playerOfCertainPosition);
  }
  private void getTeamsInfo() {
    teamsResponse = get("teams/57");
  }
}
