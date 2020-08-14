package com.automation.tests.day7;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class ApiKey {

    private  final String key = "f6a18878";

    @BeforeAll
    public static void setup(){
        baseURI = "http://omdbapi.com/";
    }

    @Test
    public void getMovie(){
        String itemToSearch = "Narcos";

       Response response = given().queryParam("t",itemToSearch).
                queryParam("apikey",key).
                when().get().prettyPeek();

       response.then().statusCode(200).body("Title",containsString(itemToSearch));
        System.out.println(response.jsonPath().get("Ratings").toString());
    }
}
