import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ReqresTestsHW16 {
    @Test
    void listOfUsers() {
        given()
                .contentType(JSON)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", equalTo(12));
    }


    @Test
    void createUser() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"leader\"}")
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus")
                        ,"job", is("leader"));
    }


    @Test
    void updateUser() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"zion resident\"}")
                .put("https://reqres.in/api/users2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus")
                        ,"job", is("zion resident"));
    }
    @Test
    void updateUserWithPatch() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"zion resident\"}")
                .when()
                .patch("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("updatedAt", containsString("2021"));
    }


    @Test
    void deleteUser() {
        given()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}