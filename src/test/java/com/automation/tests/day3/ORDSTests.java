package com.automation.tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class ORDSTests {

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:1000/ords/hr";
    }

    @Test
    public void verifyEmployee(){
        Response response = given().contentType(ContentType.JSON).
                when().get("/employees").prettyPeek();

        JsonPath jsonPath = response.jsonPath();
        String nameOfFirstEmployee = jsonPath.getString("items[0].first_name")      ;
        System.out.println("nameOfFirstEmployee = " + nameOfFirstEmployee);
        
        String nameOfLastEmployee = jsonPath.getString("items[-1].first_name") ;
        System.out.println("nameOfLastEmployee = " + nameOfLastEmployee);

        System.out.println(jsonPath.getString("links[0].href"));

        Map<String,?> firstEmployee = jsonPath.get("items[0]") ;

        System.out.println(firstEmployee);



    }

    @Test
    public void getDepartments(){
        Response response = given().contentType(ContentType.JSON).
                when().get("/departments");


        response.then().assertThat().body("items[0].department_id", is(10))     ;

        JsonPath jsonPath = response.jsonPath();


        System.out.println(jsonPath.getString("items[1].department_name"));

        Assertions.assertEquals("Administration",jsonPath.getString("items[0].department_name"));

        System.out.println(jsonPath.getString("links[1].href"));



        

    }
}
