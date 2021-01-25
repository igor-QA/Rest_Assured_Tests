package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spec.Specification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.TestUtils.*;

public class ReqresInApiTests {
/*
    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = "https://reqres.in/api";
     }
 */

    @Test
    @DisplayName("Test for Check list users")
    public void getListUsers() {
        given()
                .spec(Specification.requestSpec())
                .get("/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));

    }

    @Test
    @DisplayName("Create user")
    public void shouldCreateUsers() {
        given()
                .spec(Specification.requestSpec())
                .body(readFromFile("src/test/resources/createUser"))
                .post("users")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("createdAt", notNullValue());

    }
    @Test
    @DisplayName("Update user")
    public void shouldUpdateUsers() {
        given()
                .spec(Specification.requestSpec())
                .body(readFromFile("src/test/resources/updateUser"))
                .put("users?page=2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", notNullValue())
                .body("updatedAt", notNullValue());

    }
}