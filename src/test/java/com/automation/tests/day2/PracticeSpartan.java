package com.automation.tests.day2;

import com.github.javafaker.Faker;
import groovy.json.JsonOutput;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class PracticeSpartan {

    String baseUrl = "http://3.80.202.201:8000";

    File dilmurod = new File(System.getProperty("user.dir")+"/dilmurodSpartan.json");





    @Test
    @DisplayName("add myself to the spartan list")
    public void addSpartan() {
        given().
                contentType(ContentType.JSON).
                auth().basic("admin", "admin").
                baseUri(baseUrl).body(dilmurod).
                when().
                post("/api/spartans").prettyPeek().
                then().
                statusCode(201);
    }

    @Test
    public void addSpartans(){
        for (int i = 0; i <50 ; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            String num = faker.number().digits(12);
            long randomNum = Long.parseLong(num);

            String body = "{\"gender\": \"Male\", \"name\": \""+name+"\",\"phone\": "+randomNum+"}";

            given().
                    contentType(ContentType.JSON).
                    auth().basic("admin","admin").
                    baseUri(baseUrl).body(body).
            when().
                    post("/api/spartans").prettyPeek().
            then().
                    statusCode(201);
        }


    }


}
