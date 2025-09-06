package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTests extends BaseTest {

    @Test
    public void testGetUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreateUser() {
        String newUser = "{ \"name\": \"Bob\", \"email\": \"bob@test.com\", \"password\": \"secret123\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(newUser)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Bob"))
                .extract().response();

        System.out.println("Created User: " + response.asString());
    }

    @Test
    public void testUpdateUser() {
        // First create a user to update
        String user = "{ \"name\": \"Alice\", \"email\": \"alice@test.com\", \"password\": \"12345\" }";

        String userId = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now update the user
        String updatedUser = "{ \"name\": \"Alice Updated\", \"email\": \"alice.updated@test.com\", \"password\": \"99999\" }";

        given()
                .header("Content-Type", "application/json")
                .body(updatedUser)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Alice Updated"))
                .body("email", equalTo("alice.updated@test.com"));

        System.out.println("Updated User with ID: " + userId);
    }

    @Test
    public void testDeleteUser() {
        // First create a user to delete
        String user = "{ \"name\": \"Temp User\", \"email\": \"temp@test.com\", \"password\": \"temp123\" }";
        String userId = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");  // Store as String

        // Now delete that user
        given()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(200);

        System.out.println("Deleted User with ID: " + userId);
    }
}
