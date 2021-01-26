package steps;

import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.TestUtils.readFromFile;

public class Steps {
    public void shouldCreateUsers() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/createUser"))
                .post("users")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("name", is("morpheus"));
    }
}
