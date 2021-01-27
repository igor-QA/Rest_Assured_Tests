package tests;

import model.ListUsers;
import model.User;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spec.Request;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.TestUtils.*;

public class ReqresInApiTests {
    /* можно задать одну спецификацию для всех запросов:
    RestAssured.requestSpecification = Request.spec();
     */
    @Test
    @DisplayName("Test for Check list users")
    public void getListUsers() {
        given()
                .spec(Request.spec())
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
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/createUser"))
                .post("users")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue())
                .body("name", is("morpheus"));
    }

    @Test
    @DisplayName("Update user")
    public void shouldUpdateUsers() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/updateUser"))
                .put("users?page=2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", notNullValue())
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Should return user via api")
    void returnUserViaApi() {
        UserData userData = given()
                .spec(Request.spec())
                .when()
                .get("users/" + 10)
                .then()
                .statusCode(200)
                .extract()
                .as(UserData.class);

        assertThat(userData.getData().getId(), is(10));
        assertThat(userData.getData().getEmail(), is(equalTo("byron.fields@reqres.in")));
        assertThat(userData.getData().getLastName(), is("Fields"));
    }

    @Test
    @DisplayName("Should returns all list Users ")
    public void returnListUser() {
       ListUsers listUsers = given()
                .spec(Request.spec())
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract()
                .as(ListUsers.class);

       List<User> data = listUsers.getData();
       assertThat(data.size(), is(6));
    }

    @Test
    @DisplayName("Register succesful")
    public void regUser() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
                given()
                .spec(Request.spec())
                .body(user)
                .when()
                .post("register")
                .then()
                .statusCode(200)
                .body("id", is(4));
    }

    @Test
    @DisplayName("Create valid user")
    void createValidUser() {
        User user = new User();
        user.setLastName("Mark");
        user.setEmail("mark@ya.ru");

                 given()
                .spec(Request.spec())
                .body(user)
                .post("users")
                .then()
                .statusCode(201)
                .body("email", is("mark@ya.ru"));
    }
}