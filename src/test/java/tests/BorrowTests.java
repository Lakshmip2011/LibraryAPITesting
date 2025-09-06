package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BorrowTests extends BaseTest {

    @Test
    public void testGetBorrowedBooks() {
        given()
                .when()
                .get("/borrow")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testBorrowBook() {
        String borrowBook = "{ \"userId\": 1, \"bookId\": 1, \"status\": \"Borrowed\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(borrowBook)
                .when()
                .post("/borrow")
                .then()
                .statusCode(201)
                .body("status", equalTo("Borrowed"))
                .extract().response();

        System.out.println("Borrowed Book: " + response.asString());
    }

    @Test
    public void testUpdateBorrow() {
        // First create a borrow record
        String borrow = "{ \"userId\": 1, \"bookId\": 2, \"status\": \"Borrowed\" }";
        String borrowId = given()
                .header("Content-Type", "application/json")
                .body(borrow)
                .when()
                .post("/borrow")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now update the borrow record
        String updatedBorrow = "{ \"userId\": 1, \"bookId\": 2, \"status\": \"Returned\" }";

        given()
                .header("Content-Type", "application/json")
                .body(updatedBorrow)
                .when()
                .put("/borrow/" + borrowId)
                .then()
                .statusCode(200)
                .body("status", equalTo("Returned"));

        System.out.println("Updated Borrow Record with ID: " + borrowId);
    }

    @Test
    public void testDeleteBorrow() {
        // First create a borrow record
        String borrow = "{ \"userId\": 1, \"bookId\": 2, \"status\": \"Borrowed\" }";
        String borrowId = given()
                .header("Content-Type", "application/json")
                .body(borrow)
                .when()
                .post("/borrow")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now delete that record
        given()
                .when()
                .delete("/borrow/" + borrowId)
                .then()
                .statusCode(200);

        System.out.println("Deleted Borrow Record with ID: " + borrowId);
    }
}
