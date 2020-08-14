package com.automation.tests.day5POJO;

import com.google.gson.Gson;
import com.pojos.Spartan;
import com.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class PojoPractice {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("SPARTAN_URI");
    }

    @Test
    public void getUser(){
      Response response =  given().contentType(ContentType.JSON).auth().basic("admin","admin").
                when().get("/spartans/{id}",134);

      response.then().statusCode(200);
      //int maxId = response.jsonPath().get("max{it.id}.id");

        Spartan spartan134 = response.as(Spartan.class);
        System.out.println(spartan134);

        Map<String , ?> spartan134asMap = response.as(Map.class);
        System.out.println(spartan134asMap);




    }
    @Test
    public void addUser(){
        Spartan newSpartan = new Spartan("Dilmir","Male",77700077777L);

        Gson gson = new Gson();
       String pojoToJSON =  gson.toJson(newSpartan);
        System.out.println(pojoToJSON);

        Response response = given().contentType(ContentType.JSON).auth().basic("admin","admin").
                body(newSpartan).
                when().post("/spartans").prettyPeek();

       // response.then().statusCode(201);
        int userID = response.jsonPath().getInt("data.id");
        System.out.println(userID);
    }
}
