package br.com.coopersystem;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CotacaoResourceTest {

    @Test
    public void testCotacaoDolarDiaEndpoint() {
        given()
        .queryParam("dataCotacao", "'10-14-2021'")
          .when().get("/cotacao/cotacaoDolarDia")
          .then()
             .statusCode(200)
             .body("cotacaoCompra", is(5.4982F));
    }

    @Test
    public void testDiaNaoUtilEndpoint() {
        given()
        .queryParam("dataCotacao", "'10-17-2021'")
          .when().get("/cotacao/cotacaoDolarDia")
          .then()
             .statusCode(400)
             .body("message", is("Informe um dia útil"));
    }

}