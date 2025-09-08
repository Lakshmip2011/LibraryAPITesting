package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTests extends BaseTest {

    @Test
    public void testGetUsers() {
        test = extent.createTest("Get Users Test");

        Response response = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract().response();

        test.log(Status.INFO, "Response: " + response.asString());
        test.log(Status.PASS, "Fetched users successfully with status code 200");
    }

    @Test
    public void testCreateUser() {
        test = extent.createTest("Create User Test");

        String newUser = "{ \"name\": \"Bob\", \"email\": \"bob@test.com\", \"password\": \"secret123\" }";
        test.log(Status.INFO, "Request Body: " + newUser);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(newUser)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Bob"))
                .extract().response();

        test.log(Status.INFO, "Response: " + response.asString());
        test.log(Status.PASS, "User created successfully with name Bob");
    }

    @Test
    public void testUpdateUser() {
        test = extent.createTest("Update User Test");

        // Create user to update
        String user = "{ \"name\": \"Alice\", \"email\": \"alice@test.com\", \"password\": \"12345\" }";
        test.log(Status.INFO, "Creating initial user: " + user);

        String userId = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Update user
        String updatedUser = "{ \"name\": \"Alice Updated\", \"email\": \"alice.updated@test.com\", \"password\": \"99999\" }";
        test.log(Status.INFO, "Updating user with ID " + userId + " → " + updatedUser);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(updatedUser)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Alice Updated"))
                .body("email", equalTo("alice.updated@test.com"))
                .extract().response();

        test.log(Status.INFO, "Response: " + response.asString());
        test.log(Status.PASS, "User updated successfully with ID " + userId);
    }

    @Test
    public void testDeleteUser() {
        test = extent.createTest("Delete User Test");

        // Create user to delete
        String user = "{ \"name\": \"Temp User\", \"email\": \"temp@test.com\", \"password\": \"temp123\" }";
        test.log(Status.INFO, "Creating temp user: " + user);

        String userId = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Delete user
        Response response = given()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Response: " + response.asString());
        test.log(Status.PASS, "User deleted successfully with ID " + userId);
    }
}
