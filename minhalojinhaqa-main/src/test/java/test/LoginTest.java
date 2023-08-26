package test;

import entidade.Login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @BeforeAll
    public static void setupTest(){
        RestAssured.baseURI = "https://serverest.dev/";
    }

    @Test
    public void realizarLoginSucesso(){
        String json = "{\n"  +
                "  \"email\": \"fulano@qa.com\",\n" +
                "  \"password\": \"teste\"\n" +
                "}";
        Login login = new Login("fulano@qa.com", "teste");
        RestAssured.
                given()
                    .contentType(ContentType.JSON)
                .when()
                    .body(json)
                    .log()
                    .all()
                    .post("login")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @Test
    public void realizarLoginSemSucessoSenhaIncorreta(){

        Login login = new Login("fulano@qa.com", "abcde");
        RestAssured.
                given()
                .contentType(ContentType.JSON)
                .when()
                .body(login)
                .log()
                .all()
                .post("login")
                .then()
                .statusCode(401)
                .log().all();
    }

    @Test
    public void realizarLoginSemSucessoSemInformarSenha(){

        Login login = new Login("fulano@qa.com");
        RestAssured.
                given()
                .contentType(ContentType.JSON)
                .when()
                .body(login)
                .log()
                .all()
                .post("login")
                .then()
                .statusCode(400)
                .log().all();
    }

    @Test
    public void realizarLoginSemSucessoSenhaEmBranco(){

        Login login = new Login("fulano@qa.com", "");
        RestAssured.
                given()
                .contentType(ContentType.JSON)
                .when()
                .body(login)
                .log()
                .all()
                .post("login")
                .then()
                .statusCode(400)
                .log().all();
    }
}
