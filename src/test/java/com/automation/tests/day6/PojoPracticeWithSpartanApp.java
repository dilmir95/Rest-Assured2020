package com.automation.tests.day6;

import com.github.javafaker.Faker;
import com.pojos.Spartan;
import com.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class PojoPracticeWithSpartanApp {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("SPARTAN_URI");
        authentication = basic("admin","admin");

    }

    @Test
    public void  addSpartanjan(){
        Map<String,Object> spartanjan = new HashMap<>();

        spartanjan.put("gender","Male");
        spartanjan.put("name","Spartanchik");
        spartanjan.put("phone",21214456543L);


        Response response  = given().
                                        auth().basic("admin","admin").
                                        contentType(ContentType.JSON).body(spartanjan).
                                        accept(ContentType.JSON).
                                when().
                                        post("/spartans").prettyPeek();

        response.then().assertThat().statusCode(201);
        response.then().body("success",is("A Spartan is Born!"));

        System.out.println("=================");
       String msg = response.jsonPath().getString("success");
        System.out.println(msg);

        JsonPath jsonPath = response.jsonPath();

        Spartan spartanchik = jsonPath.getObject("data",Spartan.class);
        System.out.println(spartanchik);
        System.out.println(spartanchik instanceof Spartan);
    }

    @Test
    public void updateSpartan(){
        Spartan updateSpartan = new Spartan("Spartanjan","Male",21214456543L);

        int id = 172;

        Spartan spartanToUpdate = given().accept(ContentType.JSON).auth().basic("admin","admin").
                when().get("/spartans/{id}",id ).as(Spartan.class);

        spartanToUpdate.setName("Spartanxan");


        Response response = given().contentType(ContentType.JSON).auth().basic("admin","admin").
                body(spartanToUpdate).
                when().put("/spartans/{id}",id ).prettyPeek();

        response.then().statusCode(204);
        System.out.println("========================");
        Response   response1 =  given().auth().basic("admin","admin").
        when().get("spartans/{id}",id  ).prettyPeek();

        response1.then().statusCode(200).body("name",is("Spartanxan"));

    }

    @Test
    public void patchSpartan(){

        int id = 172;

        Map<String,String> name = new HashMap<>();
        name.put("name","Hoshmat");

        Response response = given().contentType(ContentType.JSON).body(name).
                when().patch("/spartans/{id}",id).prettyPeek();

        response.then().assertThat().statusCode(204);


        //response.then().assertThat().body("name",is(name.get("name")));
        given().contentType(ContentType.JSON).when().get("/spartans/{id}",id).then().statusCode(200).
                body("name",is(name.get("name")));

    }

    @Test
    public void getListOfSpartans(){
        Response response = given().contentType(ContentType.JSON).when().get("/spartans");

        List<Spartan> allSpartans = response.jsonPath().getList("",Spartan.class);

        System.out.println(allSpartans);


        Faker faker = new Faker();
        int randomNum = faker.number().numberBetween(1,allSpartans.size());

        System.out.println(randomNum);
    }
}
