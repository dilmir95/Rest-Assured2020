package com.automation.tests.day6;
import com.pojos.Employee;
import com.pojos.Link;
import com.utils.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class ORDSpracticePOJO {
    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("URI");

    }

    @Test
    public void getEmployeeTest(){
        Response response = when().get("/employees/{id}",103).prettyPeek();

        Employee employee103 = response.as(Employee.class);
        System.out.println(employee103);

        List<Link> links = employee103.getLinks();
        System.out.println(links);



    }

}
