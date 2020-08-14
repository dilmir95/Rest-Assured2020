package com.automation.tests.day9;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class Schema {

//    @BeforeAll
//    public static void setup(){
//        baseURI = "http://3.80.202.201:8000";
//    }

    @Test
    public void getSpartan(){
       Response response = given().auth().basic("admin","admin").
                when().get("/api/spartans/{id}",134).prettyPeek();
    }

    @Test
    public void schemaValidation(){
        Response response = given().auth().basic("admin","admin").
                when().get("/api/spartans/{id}",134).prettyPeek();

        File schema = new File("schema.json");
        response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Test
    public void testORDSSchema(){
        Response response = when().get("http://omdbapi.com//?t=Frozen&apikey=f6a18878").prettyPeek();
        File omdpSchema = new File("omdpSchema.json");

        response.then().body(JsonSchemaValidator.matchesJsonSchema(omdpSchema));
    }
}
