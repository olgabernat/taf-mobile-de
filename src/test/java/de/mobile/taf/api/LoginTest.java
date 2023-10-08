package de.mobile.taf.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    @DisplayName("Check login with invalid user")
    @Test
    public void testPostInvalidLoginAndPassword() {
        String bodyValue = "{\n" +
                " \"email\": \"test@gmail.com\",\n" +
                " \"password\": \"1q@W3e4r\",\n" +
                " \"privacy\": true,\n" +
                " \"generalTermsApproved\": true,\n" +
                " \"privacySettings\": [\n" +
                " \"ALLOW_MARKET_RESEARCH\",\n" +
                " \"ALLOW_MARKETING_ACTIVITIES\"\n" +
                " ]\n" +
                "}";
        given()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .header("content-type", "application/json")
                .header("x-mobile-client", "de.mobile.cis")
                .body(bodyValue)
                .when().post("https://www.mobile.de/api/my/account")
                .then().assertThat().statusCode(400)
                .body("[0].field", equalTo("email"))
                .body("[0].code", equalTo("account-exists"))
                .body("[0].message", equalTo("Diese E-Mail-Adresse ist bereits registriert."));
    }

    @DisplayName("Check login with empty email")
    @Test
    public void testPostEmptyEmail() {
        String bodyValue = "{\n" +
                " \"email\": \"\",\n" +
                " \"password\": \"1q@W3e4r\",\n" +
                " \"privacy\": true,\n" +
                " \"generalTermsApproved\": true,\n" +
                " \"privacySettings\": [\n" +
                " \"ALLOW_MARKET_RESEARCH\",\n" +
                " \"ALLOW_MARKETING_ACTIVITIES\"\n" +
                " ]\n" +
                "}";
        given()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .header("Content-Type", "application/json")
                .header("x-mobile-client", "de.mobile.cis")
                .body(bodyValue)
                .when().post("https://www.mobile.de/api/my/account")
                .then().assertThat().statusCode(400)
                .body("[0].field", equalTo("email"))
                .body("[0].code", equalTo("email-empty"))
                .body("[0].message", equalTo("Bitte geben Sie eine E-Mail-Adresse ein."))
                .body("[1].field", equalTo("email"))
                .body("[1].code", equalTo("email-invalid"))
                .body("[1].message", equalTo("Die eingegebene E-Mail-Adresse ist ungültig."));
    }

    @DisplayName("Check login with empty password")
    @Test
    public void testPostEmptyPassword() {
        String bodyValue = "{\n" +
                " \"email\": \"test1234523434@gmail.com\",\n" +
                " \"password\": \"\",\n" +
                " \"privacy\": true,\n" +
                " \"generalTermsApproved\": true,\n" +
                " \"privacySettings\": [\n" +
                " \"ALLOW_MARKET_RESEARCH\",\n" +
                " \"ALLOW_MARKETING_ACTIVITIES\"\n" +
                " ]\n" +
                "}";
        given()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .header("Content-Type", "application/json")
                .header("x-mobile-client", "de.mobile.cis")
                .body(bodyValue)
                .when().post("https://www.mobile.de/api/my/account")
                .then().assertThat().statusCode(400)
                .body("[0].field", equalTo("password"))
                .body("[0].code", equalTo("password-empty"))
                .body("[0].message", equalTo("Bitte gib ein Passtwort ein."));
    }
}
