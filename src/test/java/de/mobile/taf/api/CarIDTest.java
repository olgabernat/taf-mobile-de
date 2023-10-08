package de.mobile.taf.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CarIDTest {
    @DisplayName("Checking the car ID")
    @Test
    public void testPostCarId() {
        String headerValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaWQiOiJmZDM5MWZjMS00YmI3LTRkZjQtYTUxMS04YWIxNTJkOTRiZDIiLCJpYXQiOjE2Nzg4OTg3OTh9.4i0QiqIXw9FZOhuJQNs38I2pUL99pQgmEaOjwkCO4NY";
        String bodyValue = "{\n" +
                " \"adId\": \"376872435\",\n" +
                " \"ref\": null,\n" +
                " \"refId\": null\n" +
                "}";
        given()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .header("x-mobile-vi", headerValue)
                .body(bodyValue)
                .when().post("https://suchen.mobile.de/fahrzeuge/svc/my/parkings/376872435")
                .then().assertThat().statusCode(201)
                .body("adId", equalTo(376872435));
    }
}
