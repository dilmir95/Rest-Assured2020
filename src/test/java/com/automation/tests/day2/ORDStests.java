package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ORDStests {
    String BASE_URL = "http://3.90.112.152:1000/ords/hr/";



    @Test()
    @DisplayName("get list of all employees") //junit 5 annotation display name
    public void getAllEmployees(){
      Response response  = given().baseUri(BASE_URL).when().get("/employees").prettyPeek();

      /*
      given() - requaest setup
      when() - to specify end point (get,put,post,delete,patch)
      then()- to verify
       */

    }

    @Test
    @DisplayName("Get employee by id ")
    public void getOneEmployee(){
        Response response = given().baseUri(BASE_URL).when().get("/employees/{id}",393).prettyPeek();
    }

    @Test
    @DisplayName("Get employee by id ")
    public void getOneEmployeeId(){

        Response response = given().baseUri(BASE_URL).when().get("/employees/{id}",100).prettyPeek();

        response.then().statusCode(200); // to verify status code is 200

        System.out.println(response.statusCode());

        Assertions.assertEquals(200,response.statusCode());

    }

    @Test
    @DisplayName("get all countries")
    public void getAllCountries(){
//        Response response = given().baseUri(BASE_URL).when().get("/countries").prettyPeek();
//
//        response.then().statusCode(200);
//        or
//        Assertions.assertEquals(200,response.statusCode());

                given().
                        baseUri(BASE_URL).
                when().
                        get("/countries").prettyPeek().
                then().
                        statusCode(200);

    }

    @Test
    public void getJobs(){
        given().baseUri(BASE_URL).when().get("/jobs").prettyPeek().then().statusCode(200);
    }
}
