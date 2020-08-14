package com.automation.tests.day9;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class MetaWeatherAPI {

    @BeforeAll
    public static void setup(){
        baseURI = "https://www.metaweather.com/api/";
    }

    @Test
    public void getCity(){
        String city = "New York";

        Response response = given().queryParams("query",city).when().get("location/search").prettyPeek();

        String cityResponse = response.jsonPath().getString("[0].title");
        response.then().body("[0].title",is(city));

        int woeid = response.jsonPath().getInt("[0].woeid");

        Response response1 = get("location/{woeid}",woeid).prettyPeek();

//      String applicableDate =   response1.jsonPath().getString("sources[0].title");
//        System.out.println(applicableDate);


        //Assertions.assertEquals(city,cityResponse);
    }

}
