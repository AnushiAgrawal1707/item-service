package com.mykart.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mykart.controller.order.OrderController;
import com.mykart.controller.user.UserController;
import com.mykart.exceptionhandler.EmployeeExceptionHandler;
import com.mykart.model.Orders;
import com.mykart.model.User;
import com.mykart.service.orders.OrderService;
import com.mykart.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
public class OrderControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new EmployeeExceptionHandler())
                .build();
    }
    @Test
    public void testPlaceOrder() throws Exception {

         Orders orders=new Orders(100,101,"AGG2",700);
        //User user=new User(101,"anuja","harane","pune","1234567890","anuja@gmail.com","dhgshg232","12we222");
       String inputInJson = this.mapToJson(orders);
       // System.out.println(inputInJson);
        String URI = "/v1/users/101/orders";
        Mockito.when(orderService.placeOrder(101)).thenReturn(orders);

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
