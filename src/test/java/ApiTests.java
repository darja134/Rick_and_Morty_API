import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {

    private final static String baseURI = "https://rickandmortyapi.com";
    private final static String endpoint = "/api/character/?name=%s";

    @Test
    @Description("Verify if response status code 200")
    public void statusCodeTest() {
        given().
            filter(new AllureRestAssured()).
            baseUri(baseURI).
        when().
            get(String.format(endpoint, "Rick Sanchez")).
        then().
            assertThat().statusCode(SC_OK);
    }

    @Test
    @Description("Verify if 'count' field value is '4'")
    public void responseFieldTest() {
        given().
            filter(new AllureRestAssured()).
            baseUri(baseURI).
        when().
            get(String.format(endpoint, "Rick Sanchez")).
        then().
            assertThat().body("info.count", equalTo(4));
    }
}
