package com.mykart.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mykart.controller.authentication.AuthenticationController;
import com.mykart.controller.user.UserController;
import com.mykart.exceptionhandler.EmployeeExceptionHandler;
import com.mykart.model.User;
import com.mykart.security.JwtRequest;
import com.mykart.security.JwtResponse;
import com.mykart.security.JwtTokenUtil;
import com.mykart.security.JwtUserDetailsService;
import com.mykart.service.authentication.AuthenticationService;
import com.mykart.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private JwtUserDetailsService userDetailsService;
    @Mock
    private AuthenticationService authenticationService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .setControllerAdvice(new EmployeeExceptionHandler())
                .build();
    }
    @Test
    public void testCreateAuthenticationTokenForUser() throws Exception {
        JwtRequest request=new JwtRequest("anuja","admin");
        JwtResponse jwtResponse=new JwtResponse("hsadjdt436825efgsdhg743hgjdqsghkwergtuwryqo5y43");
        String inputInJson = this.mapToJson(request);
        System.out.println(inputInJson);
        String jwtToken = this.mapToJson(jwtResponse);
        String URI = "/v1/users/login";
        UserDetails userDetails=Mockito.mock(UserDetails.class);
        //Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())));
        Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(userDetails);
        Mockito.when(jwtTokenUtil.generateToken(userDetails)).thenReturn(jwtResponse.getToken());
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(inputInJson);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
        assertThat(outputInJson).isEqualTo(jwtToken);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void testCreateAuthenticationTokenForUser_InvalidCredentials() throws Exception {
        JwtRequest request=new JwtRequest("anuja","admin");
        String inputInJson = this.mapToJson(request);
        System.out.println(inputInJson);
        String URI = "/v1/users/login";
        UserDetails userDetails=Mockito.mock(UserDetails.class);
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))).thenThrow(BadCredentialsException.class);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(inputInJson);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
    @Test
    public void testCreateAuthenticationTokenForUser_UserDisabled() throws Exception {
        JwtRequest request=new JwtRequest("anuja","admin");
        String inputInJson = this.mapToJson(request);
        System.out.println(inputInJson);
        String URI = "/v1/users/login";
        UserDetails userDetails=Mockito.mock(UserDetails.class);
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))).thenThrow(DisabledException.class);
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON).content(inputInJson);

        MvcResult result =
                mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        System.out.println(outputInJson);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
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
