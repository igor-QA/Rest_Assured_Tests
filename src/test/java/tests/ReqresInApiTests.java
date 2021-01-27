package tests;

import model.ListUsers;
import model.User;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static spec.Specification.requestSpec;
import static utils.TestUtils.*;

public class ReqresInApiTests {

    private static final int USER_ID = 10;

    @Test
    @DisplayName("Test for Check list users")
    public void getListUsers() {
        given()
                .spec(requestSpec())
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
                .spec(requestSpec())
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
                .spec(requestSpec())
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
                .spec(requestSpec())
                .when()
                .get("users/" + USER_ID)
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
                .spec(requestSpec())
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
                .spec(requestSpec())
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
        user.setLastname("Mark");
        user.setEmail("mark@ya.ru");

                 given()
                .spec(requestSpec())
                .body(user)
                .post("users")
                .then()
                .statusCode(201)
                .body("email", is("mark@ya.ru"));

    }
}