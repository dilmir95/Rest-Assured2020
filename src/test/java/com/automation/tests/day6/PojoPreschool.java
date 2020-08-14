package com.automation.tests.day6;

import com.pojos.Student;
import com.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class PojoPreschool {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("PRESCHOOL_URI");
    }

    @Test
    public void getStudents(){
      Response response= given().contentType(ContentType.JSON).
                when().get("/student/{id}",11241).prettyPeek();

        List<Student> students = response.jsonPath().getList("students",Student.class);
        
        Student student11241 = response.jsonPath().getObject("students[0]",Student.class);
        
        System.out.println(students);
        System.out.println("student11241 = " + student11241);
    }
}
