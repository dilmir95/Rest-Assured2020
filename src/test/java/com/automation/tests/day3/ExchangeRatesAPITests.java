package com.automation.tests.day3;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;


public class ExchangeRatesAPITests {

    String BASE_URL = "https://api.openrates.io";

    @BeforeAll
    static void setup(){
        baseURI = "https://api.openrates.io";

    }

    @Test
    @DisplayName("Get latest rates")
    public void getForexRates(){

                get("/latest").prettyPeek().
        then().
                statusCode(200);
    }

    @Test
    public void changeBaseCurrency(){
        Response  response = given().
                queryParam("base","USD").
        get("/latest").
                prettyPeek();

        response.then().statusCode(200);
        response.then().assertThat().body("base",is("USD"));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        response.then().assertThat().body("date",containsStringIgnoringCase(date));
        //to use without static import
        //response.then().assertThat().body("base",Matchers.is("USD"));


    }
    @Test
    public void getHistoryOfRates(){
        Response response = given().queryParam("base","USD").
                when().get("/2008-01-02").prettyPeek();

        //response.then().statusCode(200);


        response.then().assertThat().
                statusCode(200).
                and(). //syntax sugar doesn't do anything
                body("date",is("2008-01-02"));

        response.then().assertThat().body("rates.USD",is(1.0F));

        Float param = response.jsonPath().get("rates.EUR");
        //to retrieve or parse from json response we use jsonPath();
        //to view of response body
        System.out.println(param);

        Headers headers = response.headers();
        System.out.println(headers);

    }
}
