package com.mykart.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mykart.controller.user.UserController;
import com.mykart.model.User;
import com.mykart.repository.user.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        //RestAssuredMockMvc.standaloneSetup(new UserController());
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    @Test
    public void testSaveUser() {
        User user=new User(103,"anuja","harane","pune","1234567890","anuja@gmail.com","dhgshg232","12we222");
        String inputInJson = this.mapToJson(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
       // RestAssured.baseURI = "http://localhost:"+port+"/v1/users/";
        //System.out.println(RestAssured.baseURI);
        MockMvcResponse response = null;

        try {
            response = RestAssuredMockMvc.given()
                    .contentType(ContentType.JSON)
                    .body(inputInJson)
                    .post("/v1/users/register");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());


    }
    @Test
    public void testSaveUser_InvalidData() {
        User user=new User(103,null,"harane","pune","1234567890","anujagmail.com","dhgshg232","12we222");
        String inputInJson = this.mapToJson(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        // RestAssured.baseURI = "http://localhost:"+port+"/v1/users/";
        //System.out.println(RestAssured.baseURI);
        MockMvcResponse response = null;

        try {
            response = RestAssuredMockMvc.given()
                    .contentType(ContentType.JSON)
                    .body(inputInJson)
                    .post("/v1/users/register");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());


    }
    @Test
    public void testUpdateUser() {
        User user=new User(101,"anuja","harane","pune","1234567890","anuja@gmail.com","dhgshg232","12we222");
       String token= " dshkjar837590347turekhgjkdfghdfjkgherui7569837968tuu";
        String inputInJson = this.mapToJson(user);
        Mockito.when(userRepository.findById(101)).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        MockMvcResponse response = null;

        try {
            response = RestAssuredMockMvc.given()
                    .header("Authorization", "Token " + token)
                    .contentType(ContentType.JSON)
                    .body(inputInJson)
                    .put("/v1/users/update/{user_id}",101);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(response.asString()).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());


    }
    @Test
    public void testDeleteUser() {
        User user=new User(101,"anuja","harane","pune","1234567890","anuja@gmail.com","dhgshg232","12we222");
        String token= " dshkjar837590347turekhgjkdfghdfjkgherui7569837968tuu";
        String inputInJson = this.mapToJson(user);
        Mockito.when(userRepository.findById(101)).thenReturn(user);
        Mockito.doNothing().when(userRepository).deleteById(101);
        MockMvcResponse response = null;

        try {
            response = RestAssuredMockMvc.given()
                    .header("Authorization", "Token " + token)
                    .contentType(ContentType.JSON)
                    .delete("/v1/users/delete/{user_id}",101);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());


    }

    /** Maps an Object into a JSON String. Uses a Jackson ObjectMapper. */
    private String mapToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.writeValueAsString(object);
            // System.out.println(value);
        } catch (Exception a) {
            a.printStackTrace();
            return null;
        }

    }
   
}
