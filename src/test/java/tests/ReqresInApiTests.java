package tests;

import model.User;
import model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spec.Request;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static utils.TestUtils.*;

public class ReqresInApiTests extends TestBase{

    private static final int USER_ID = 10;

    @Test
    @DisplayName("Test for Check list users")
    public void getListUsers() {
        given()
                .spec(Request.spec())
        .when()
                .get("/users/2")
        .then()
                .log().body()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));

    }

    @Test
    @DisplayName("Create user")
    void shouldCreateUsers() {
        step("Создание пользователя", () -> {
        steps.shouldCreateUsers();
        });
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
                .get("users/" + USER_ID)
                .then()
                .statusCode(200)
                .extract()
                .as(UserData.class);

        assertThat(userData.getData().getId(), is(10));
        assertThat(userData.getData().getEmail(), is(equalTo("byron.fields@reqres.in")));
        assertThat(userData.getData().getLastName(), is("Fields"));

    }
/*
    @Test
    @DisplayName("Should return Single User")
    public void returnSingleUser() {
        Data data = given()
                .spec(requestSpec())
                .get("user/2")
                .then()
                .statusCode(200)
                .extract()
                .as(Data.class);
        assertThat(data.getEmail(), is("janet.weaver@reqres.in"));
    }
 */

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
        user.setLastname("Mark");
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