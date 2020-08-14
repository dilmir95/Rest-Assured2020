package com.automation.tests.day8;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class BearerAuth {

    @BeforeAll
    public static void setup(){
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";

    }
    public static String getToken(){
        Response response = given().queryParam("email","teacherva5@gmail.com").
                queryParam("password","maxpayne").
                get("/sign");


        return response.jsonPath().getString("accessToken");
    }

    @Test
    public void loginTest(){
       Response response = given().queryParam("email","teacherva5@gmail.com").
                queryParam("password","maxpayne").
                get("/sign");


         String token = response.jsonPath().getString("accessToken");
        System.out.println(token);
    }

    @Test
    public void getAllRooms(){



        String token = getToken();
        given().auth().oauth2(token).
                get("/api/rooms").prettyPeek();
    }
}
