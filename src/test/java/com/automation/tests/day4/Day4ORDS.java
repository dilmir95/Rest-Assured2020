package com.automation.tests.day4;

import com.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class Day4ORDS {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("URI");
    }

    @Test
    public void getEmp(){
       Response response =  given().accept(ContentType.JSON).
                when().get("/employees");

       response.then().statusCode(200).time(lessThan(3000L)).contentType(ContentType.JSON);

    }

    @Test
    public void getCountry(){
      Response response =  given().accept(ContentType.JSON).queryParam("q","{\"country_id\":\"US\"}").
                when().get("/countries").prettyPeek();

      response.then().contentType(ContentType.JSON).statusCode(200).
              body("items[0].country_name",is("United States of America"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("items[0].country_name"),"United States of America");

        String countryName = jsonPath.getString("items.find{it.country_id == 'US'}.country_name");
        System.out.println(countryName);

        //to save result in map
        Map<String , Object> countryUS = jsonPath.get("items.find{it.country_id = 'US'}");

        System.out.println(countryUS);
        //iterate over map
        for(Map.Entry<String,Object> entry: countryUS.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }


        System.out.println("===================================");

        List<String> countryNames = jsonPath.getList("items.findAll{it.region_id == 2}.country_name");
        System.out.println(countryNames);
    }

    @Test
    public void getEmpTest() {
        Response response = given().contentType(ContentType.JSON).
                when().get("/employees").prettyPeek();

        Map<String, ?> bestPaid = response.jsonPath().get("items.max{it.salary}");
        System.out.println(bestPaid);

        List<Integer> allSalaries = response.jsonPath().getList("items.collect{it.salary}");

        Integer sumOfSalaries = response.jsonPath().get("items.collect{it.salary}.sum()");

        System.out.println(allSalaries);
        System.out.println("sumOfSalaries = " + sumOfSalaries);

    }
    @Test
    @DisplayName("Verify that every employee has positive salary")
    public void testSalary(){
        when().
                get("/employees").
                then().assertThat().
                statusCode(200).
                body("items.salary", everyItem(greaterThan(0))).
                log().ifError();
    }

    @Test
    public void getSalaries(){
        when().get("/salaries").then().
                assertThat().statusCode(200).body("items.salary",everyItem(greaterThan(1000)));


    }
    @Test
    public void getAllProgrammers(){
      Response response =   when().get("/employees");
      List<Map<String,Object >> allProgrammers = response.jsonPath().
                get("items.findAll{it.job_id == 'IT_PROG'}");


        System.out.println(allProgrammers);

        response.then().
                body("items.findAll{it.job_id == 'IT_PROG'}.department_id",everyItem(is(60))).
                log().ifError();

        

        }
        @Test
        public void verifyPhoneNumber(){
           Response response = when().get("/employees/{id}",101).prettyPeek();

           response.then().statusCode(200);
           response.then().body("phone_number",is("515.123.4568"));

        }
    }

