package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @BeforeAll
    public static void setupTest(){
        RestAssured.baseURI = "http://localhost:3000/";
    }

    @Test
    public void realizarLoginSucesso(){
        String json = "{\n"  +
                "  \"email\": \"fulano@qa.com\",\n" +
                "  \"password\": \"teste\"\n" +
                "}";

        RestAssured.given().contentType(ContentType.JSON)
                .when().body(json).log().all().post("login")
                .then().statusCode(200).log();
    }
}
