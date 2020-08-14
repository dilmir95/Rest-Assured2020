package com.automation.tests.day2;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class SpartanTest {
        String BASE_URL = "http://3.80.202.201:8000";


        /*
        //URI (Uniform Resource Identifier) = URL + URN = http://www.google.com/index.html
        //URL (Uniform Resource Locator)    = http://www.google.com
        //URN (Uniform Resource Name)       = /index.html
         */

    @Test
    @DisplayName("get all spartans")
    public void getAllSpartans(){
            given().
                        auth().basic("admin","admin"). //to provide credentials
                        baseUri(BASE_URL).
            when().
                        get("/api/spartans").prettyPeek().
            then().
                        statusCode(200);


    }

    @Test
    @DisplayName("Add new Spartan")
    public void addNewSpartan(){
       // String body = "{\"gender\": \"Male\", \"name\": \"Random user\",\"phone\": 999999988766}";
        //or
        File body = new File(System.getProperty("user.dir")+"/newSpartan.json");



        given().
                contentType(ContentType.JSON).
                auth().basic("admin","admin").
                baseUri(BASE_URL).
                body(body).
        when().
                post("/api/spartans").prettyPeek().
        then().statusCode(201);

    }

    @Test
    public void deleteSpartan(){
        given().contentType(ContentType.JSON).
                auth().basic("admin","admin").
                baseUri(BASE_URL).
        when().delete("/api/spartans/{id}",861).prettyPeek().
        then().statusCode(204);
    }

    @Test
    public void getName(){
      Response response = given().contentType(ContentType.JSON).auth().basic("admin","admin").baseUri(BASE_URL).when().get("/api/spartans/search?nameContains=Mariana").prettyPeek();

        JsonPath jsonPath = response.jsonPath();

        jsonPath.getString("content[0].id");

        response.then().assertThat().body("content[0].id", Matchers.is(116));


    }
}
