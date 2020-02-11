package com.mykart.unit.service;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ItemAlreadyPresentException;
import com.mykart.exception.OutOfStockException;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.repository.cart.CartRepository;
import com.mykart.repository.orders.OrderRepository;
import com.mykart.service.cart.CartService;
import com.mykart.service.cart.CartServiceImpl;
import com.mykart.service.item.ItemService;
import com.mykart.service.orders.OrderServiceImpl;
import com.mykart.service.user.UserService;
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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceTest {

    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserService userService;
    @Mock
    private ItemService itemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testGetAllItems() {
        User user = Mockito.mock(User.class);
        Mockito.when(userService.getUserById(101)).thenReturn(user);
        List<Cart> cartItems =new ArrayList();
        cartItems.add(Mockito.mock(Cart.class));
        cartItems.add(Mockito.mock(Cart.class));
        cartItems.add(Mockito.mock(Cart.class));
        Mockito.when(cartRepository.findByCartIdAndOrdered(user.getCart_id(), 0)).thenReturn(cartItems);
        System.out.println(cartItems);
        List<Cart> cartList=cartService.getAllItems(101);
        assertEquals(3, cartList.size());
    }
    /*
    @Test
    public void testDeleteItemById() throws ResourceNotFound {
        User user = Mockito.mock(User.class);
        Mockito.when(userService.getUserById(101)).thenReturn(user);
        Cart cart=Mockito.mock(Cart.class);
        Mockito.when(cartRepository.findByCartIdAndItemIdAndIsOrdered(user.getCart_id(), 101,0)).thenReturn(cart);
        Mockito.doNothing().when(cartRepository).deleteById(cart.getId());
        cartService.deleteItemById(101,cart);
        verify(cartRepository, times(1)).deleteById(cart.getId());
    }  */
    @Test
    public void testUpdateItemById() throws ResourceNotFound, OutOfStockException {
        User user=Mockito.mock(User.class);
        when(userService.getUserById(101)).thenReturn(user);
        Cart cart =Mockito.mock(Cart.class);
   Mockito.when(cartRepository.findByCartIdAndItemIdAndIsOrdered(user.getCart_id(), cart.getItem_id(), 0)).thenReturn(cart);
        ItemDTO itemDTO=Mockito.mock(ItemDTO.class);
        Mockito.when(itemService.getItem(cart.getItem_id())).thenReturn(itemDTO);
        Cart newCart =cartService.updateItemById(101,cart);
        Mockito.when(cartRepository.save(newCart)).thenReturn(cart);
        assertEquals(newCart, cart);
    }
    @Test
    public void testSaveItem()
            throws OutOfStockException, ItemAlreadyPresentException {
        User user=Mockito.mock(User.class);
        when(userService.getUserById(101)).thenReturn(user);
        ItemDTO itemDTO=Mockito.mock(ItemDTO.class);
        Cart cart =Mockito.mock(Cart.class);
        Mockito.when(itemService.getItem(cart.getItem_id())).thenReturn(itemDTO);
        Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(cart);
        Cart newCart =cartService.saveItem(101,cart);
        assertEquals(newCart, cart);
    }

}
