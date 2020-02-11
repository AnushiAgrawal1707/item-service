package com.mykart.unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.mykart.MykartUserServiceApplication;
import com.mykart.config.WebSecurityConfig;
import com.mykart.controller.user.UserController;
import com.mykart.exception.ResourceNotFound;
import com.mykart.exceptionhandler.EmployeeExceptionHandler;
import com.mykart.exceptionhandler.ErrorResponse;
import com.mykart.model.User;
import com.mykart.repository.user.UserRepository;
import com.mykart.security.JwtAuthenticationEntryPoint;
import com.mykart.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    
    private MockMvc mockMvc;
    
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService service;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new EmployeeExceptionHandler())
                .build();
    }


    @Test
    public void testCreateUser() throws Exception {

        User user=new User(101,"anuja","harane","pune","1234567890","anuja@gmail.com","dhgshg232","12we222");
        String inputInJson = this.mapToJson(user);
        System.out.println(inputInJson);
        String URI = "/v1/users/register";

        Mockito.when(service.saveUser(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(inputInJson);

    MvcResult result =
        mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
       assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());   
    }
    @Test
    public void testUpdateUser() throws Exception {

        User user=new User(101,"anuja","harane","pune","1234567890","anujagmail.com","dhgshg232","12we222");
        String inputInJson = this.mapToJson(user);
        System.out.println(inputInJson);
        String URI = "/v1/users/update/101";
        Mockito.when(service.getUserById(101)).thenReturn(user);
        Mockito.when(service.saveUser(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(inputInJson);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void testUpdateUser_ResourceNotFound() throws Exception {


       User user=new User(101,"anuja","harane","pune","1234567890","anujagmail.com","dhgshg232","12we222");
        ErrorResponse error=new ErrorResponse();
        error.setMessage("Resource Not Found");
        error.setStatusCode(404);
        String userInput = this.mapToJson(user);
        String inputInJson = this.mapToJson(error);
        System.out.println(inputInJson);
        String URI = "/v1/users/update/101";
        Mockito.when(service.getUserById(101)).thenReturn(null);
       // Mockito.when(service.saveUser(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(userInput);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
       assertThat(outputInJson).isEqualTo(inputInJson);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
    @Test
    public void testDeleteUser() throws Exception {

        User user=new User(101,"anuja","harane","pune","1234567890","anujagmail.com","dhgshg232","12we222");
        String inputInJson = this.mapToJson(user);
        System.out.println(inputInJson);
        String URI = "/v1/users/delete/101";
        Mockito.when(service.getUserById(101)).thenReturn(user);
       Mockito.doNothing().when(service).deleteUser(Mockito.any(User.class));

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
       // assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void testDeleteUser_ResourceNowtFound() throws Exception {


       // User user=new User(101,"anuja","harane","pune","1234567890","anujagmail.com","dhgshg232","12we222");
        ErrorResponse error=new ErrorResponse();
        error.setMessage("Resource Not Found");
        error.setStatusCode(404);
       // String userInput = this.mapToJson(user);
        String inputInJson = this.mapToJson(error);
        System.out.println(inputInJson);
        String URI = "/v1/users/delete/101";
        Mockito.when(service.getUserById(101)).thenReturn(null);
        // Mockito.when(service.saveUser(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        // assertThat(outputInJson).isEqualTo(inputInJson);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
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
