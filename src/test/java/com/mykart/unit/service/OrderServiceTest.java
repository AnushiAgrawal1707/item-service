package com.mykart.unit.service;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Authentication;
import com.mykart.model.Cart;
import com.mykart.model.Orders;
import com.mykart.model.User;
import com.mykart.repository.orders.OrderRepository;
import com.mykart.repository.user.UserRepository;
import com.mykart.service.authentication.AuthenticationService;
import com.mykart.service.cart.CartService;
import com.mykart.service.item.ItemService;
import com.mykart.service.orders.OrderService;
import com.mykart.service.orders.OrderServiceImpl;
import com.mykart.service.user.UserService;
import com.mykart.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserService userService;
    @Mock
    private CartService cartService;
    @Mock
    private ItemService itemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }/*
    @Test
    public void testPlaceOrder() {
           User user=Mockito.mock(User.class);
            Mockito.when(userService.getUserById(101)).thenReturn(user);
            ItemDTO itemDTO=Mockito.mock(ItemDTO.class);
          // Mockito.when(itemService.updateItem(Mockito.anyInt(),Mockito.anyInt())).thenReturn(itemDTO);
        List<Cart> cartItems =new ArrayList();
        cartItems.add(Mockito.mock(Cart.class));
        cartItems.add(Mockito.mock(Cart.class));
        cartItems.add(Mockito.mock(Cart.class));
         Mockito.when(cartService.getAllItems(101)).thenReturn(cartItems);
            System.out.println(cartItems);
        Orders orders =Mockito.mock(Orders.class);
          Mockito.when(orderRepository.save(Mockito.any(Orders.class))).thenReturn(orders);
          Orders result=orderService.placeOrder(101);
        } */
    @Test
    public void testGetAllOrders() {
        User user=Mockito.mock(User.class);
        Mockito.when(userService.getUserById(user.getUser_id())).thenReturn(user);
        List<Orders> orders =new ArrayList();
        orders.add(Mockito.mock(Orders.class));
        orders.add(Mockito.mock(Orders.class));
        orders.add(Mockito.mock(Orders.class));
        Mockito.when(orderRepository.findAllByUser_id(101)).thenReturn(orders);
        System.out.println(orders);
        List<Orders> result=orderService.getAllOrders(101);
        assertEquals(3, result.size());
    }

    @Test
    public void testGetOrderById() throws ResourceNotFound {
       Orders orders =Mockito.mock(Orders.class);
        Mockito.when(orderRepository.findAllByUser_idAndOrder_id(101,234)).thenReturn(orders);
        System.out.println(orders);
        Orders result=orderService.getOrderById(101,234);
        assertEquals(result, orders);
    }
    @Test(expected = ResourceNotFound.class)
    public void testGetOrderById_ResourceNotFound() throws ResourceNotFound {
        Orders orders =Mockito.mock(Orders.class);
        Mockito.when(orderRepository.findAllByUser_idAndOrder_id(101,234)).thenReturn(null);
        System.out.println(orders);
        Orders result=orderService.getOrderById(101,234);
        assertEquals(result, null);
    }
    @Test
    public void testDeleteOrderById() throws ResourceNotFound {

        Mockito.doNothing().when(orderRepository).deleteById(234);
        orderService.deleteOrder(101,234);
        verify(orderRepository, times(1)).deleteById(234);
       
    }

}
