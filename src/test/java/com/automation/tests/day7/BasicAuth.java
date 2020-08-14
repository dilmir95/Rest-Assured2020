package com.automation.tests.day7;

import com.pojos.Spartan;
import com.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class BasicAuth {


    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("SPARTAN_URI");

    }

    @Test
    public void getSpartans(){
      Response response = given().auth().basic("admin","admin").contentType(ContentType.JSON).
                when().get("/spartans").prettyPeek();

      response.then().statusCode(200);

    }

    @Test
    public void authorizationTest(){
        Spartan Shoxsanam = new Spartan("Shoxsanam","Female",5552332342313L);

       Response response= given().contentType(ContentType.JSON).auth().basic("user","user").body(Shoxsanam).
                when().post("/spartans");

       response.then().statusCode(403);
    }

    @Test
    public void authenticationTest(){
        when().get("/spartans").then().statusCode(401);
    }

    @Test
    public void auth1(){
       Response response =  given().auth().basic("admin","admin").
                when().get("http://practice.cybertekschool.com/basic_auth").prettyPeek();

        response.then().statusCode(200).contentType(ContentType.HTML);

    }
}
