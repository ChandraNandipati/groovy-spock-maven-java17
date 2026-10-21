package com.example

import com.example.config.BaseSpec
import spock.lang.Shared
import spock.lang.Unroll
import groovy.json.JsonOutput

import static io.restassured.RestAssured.given
import io.restassured.http.ContentType

class LoginSpec extends BaseSpec {

    @Shared
    String loginUrl

    // Runs once before all test cases
    def setupSpec() {

        loginUrl = config.getProperty("baseUrl") +
                config.getProperty("loginEndpoint")

        println "Login URL: $loginUrl"
    }

    @Unroll
    def "Verify Login Api - #scenario"() {

        given:
        def requestBody = [
                email   : email,
                password: password
        ]

        when:
        def response = given()
                .contentType(ContentType.JSON)
                .body(JsonOutput.toJson(requestBody))
                .post(loginUrl)

        then:
        response.statusCode() == expectedStatus

        where:
        scenario       | email                 || expectedStatus
        "Valid Login"  | "sneha.abc@demo.com"  || 200
        "Invalid User" | "test@gmail.com"      || 400

        password = scenario == "Valid Login"
                ? validPassword(email)
                : "wrong123"
    }

    static String validPassword(String email) {

        // TODO: Call Vault API and retrieve password
        return System.getenv("TEST_LOGIN_PASSWORD")
    }
}