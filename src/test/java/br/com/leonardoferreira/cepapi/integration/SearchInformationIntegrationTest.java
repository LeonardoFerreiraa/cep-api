package br.com.leonardoferreira.cepapi.integration;

import br.com.leonardoferreira.cepapi.base.BaseIntegrationTest;
import io.restassured.RestAssured;
import java.util.stream.Stream;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchInformationIntegrationTest extends BaseIntegrationTest {

    @ParameterizedTest
    @MethodSource("zipCodesWithSuccess")
    public void searchWithSuccess(final String zipCode, final String address, final String neighborhood,
                           final String city, final String federationUnity) {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/zip-codes/{zipCode}", zipCode)
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("address", Matchers.is(address))
                    .body("zipCode", Matchers.is(zipCode))
                    .body("neighborhood", Matchers.is(neighborhood))
                    .body("city", Matchers.is(city))
                    .body("federationUnity", Matchers.is(federationUnity));
        // @formatter:on
    }

    @Test
    public void searchNotFoundZipCode() {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/zip-codes/99999-999")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .body("message", Matchers.is("ZipCode Not Found [99999-999]"));
        // @formatter:on
    }

    public static Stream<Arguments> zipCodesWithSuccess() {
        return Stream.of(
                Arguments.of("01330-000", "Rua Rocha", "Bela Vista", "São Paulo", "SP"),
                Arguments.of("85100-000", "", "Jordão", "Guarapuava", "PR"),
                Arguments.of("75064-590", "Rua A", "Vila Jaiara", "Anápolis", "GO"),
                Arguments.of("12245-230", "Avenida Tivoli", "Vila Betânia", "São José dos Campos", "SP"),
                Arguments.of("69908-768", "Rodovia BR-364 (Rio Branco-Porto Velho)", "Loteamento Santa Helena", "Rio Branco", "AC"),
                Arguments.of("14801-210", "Avenida São Geraldo", "Centro", "Araraquara", "SP"),
                Arguments.of("14802-510", "Rua Domingos Barbieri", "Vila Harmonia", "Araraquara", "SP"),
                Arguments.of("13580-000", "", "", "Ribeirão Bonito", "SP"),
                Arguments.of("14806-143", "Avenida Doutor Carlos Chagas", "Jardim Adalgisa", "Araraquara", "SP"));
    }

}
