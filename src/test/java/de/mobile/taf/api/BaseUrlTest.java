package de.mobile.taf.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BaseUrlTest {
    @DisplayName("Checking the base URL")
    @Test
    public void testBaseUrl() {
        given()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .when().get("https://www.mobile.de/").then().statusCode(200);
    }
}
