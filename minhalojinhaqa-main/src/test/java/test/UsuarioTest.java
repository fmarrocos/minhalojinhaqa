package test;

import com.github.javafaker.Faker;
import entidade.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

    @BeforeAll
    public static void setupTest() {
        RestAssured.baseURI = "https://serverest.dev";
    }

    @Test
    public void criarUsuarioComSucesso(){
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                faker.name().fullName(),faker.internet().emailAddress(),
                "teste123",
                "true");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .body(usuario)
                    .post("usuarios")
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .log().all();
    }

    @Test
    public void criarUsuarioDuplicadoSemSucesso(){
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                faker.name().fullName(),faker.internet().emailAddress(),
                "teste123",
                "true");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                    .body(usuario)
                    .post("usuarios");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .body(usuario)
                    .post("usuarios")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .log().all();
    }

    @Test
    public void BuscarUsuarioPorIdComSucesso() {
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                faker.name().fullName(), faker.internet().emailAddress(),
                "teste123",
                "true");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                .get("usuarios/{_id}", "j3Bf13gp60KDXuLW")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

}