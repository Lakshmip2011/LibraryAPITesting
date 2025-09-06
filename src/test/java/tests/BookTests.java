package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookTests extends BaseTest {

    @Test
    public void testGetBooks() {
        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testAddBook() {
        String newBook = "{ \"title\": \"Automation Testing\", \"author\": \"Princess QA\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(newBook)
                .when()
                .post("/books")
                .then()
                .statusCode(201)
                .body("title", equalTo("Automation Testing"))
                .extract().response();

        System.out.println("Created Book: " + response.asString());
    }

    @Test
    public void testUpdateBook() {
        // First create a book to update
        String book = "{ \"title\": \"Old Title\", \"author\": \"Anonymous\" }";
        String bookId = given()
                .header("Content-Type", "application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now update that book
        String updatedBook = "{ \"title\": \"Updated Title\", \"author\": \"Princess QA\" }";

        given()
                .header("Content-Type", "application/json")
                .body(updatedBook)
                .when()
                .put("/books/" + bookId)
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("author", equalTo("Princess QA"));

        System.out.println("Updated Book with ID: " + bookId);
    }

    @Test
    public void testDeleteBook() {
        // First create a book to delete
        String book = "{ \"title\": \"Temp Book\", \"author\": \"Anonymous\" }";
        String bookId = given()
                .header("Content-Type", "application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Now delete that book
        given()
                .when()
                .delete("/books/" + bookId)
                .then()
                .statusCode(200);

        System.out.println("Deleted Book with ID: " + bookId);
    }
}
