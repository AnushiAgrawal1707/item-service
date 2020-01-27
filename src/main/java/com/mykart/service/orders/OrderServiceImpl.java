package com.mykart.service.orders;

import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.model.Orders;
import com.mykart.model.User;
import com.mykart.repository.orders.OrderRepository;
import com.mykart.service.cart.CartService;
import com.mykart.service.item.ItemServiceImpl;
import com.mykart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository repository;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemServiceImpl itemService;

    @Override
    public Orders placeOrder(int user_id) {
        User user = userService.getUserById(user_id);
        List<Cart> cartItems = cartService.getAllItems(user_id);
        System.out.println(cartItems);
        Iterator<Cart> iterator = cartItems.iterator();
        Orders orders = new Orders();
        double bill=0;
        while (iterator.hasNext()){
            Cart cart = iterator.next();
            bill= bill+cart.getPrice();
            itemService.updateItem(cart.getItem_id(),cart.getQuantity());
            cart.setIs_ordered(1);
        }
        orders.setCart_id(user.getCart_id());
        orders.setUser_id(user_id);
        orders.setTotal_bill(bill);

        return repository.save(orders);
    }


    @Override
    public List<Orders> getAllOrders(int user_id) {
        List<Orders> ordersList = repository.findAllByUser_id(user_id);

        return ordersList;
    }

    @Override
    public Orders getOrderById(int user_id,int order_id) throws ResourceNotFound {
        Orders orders=repository.findAllByUser_idAndOrder_id(user_id,order_id);
        System.out.println(orders);
        if(orders==null)
            throw new ResourceNotFound();
        return orders;
    }

    @Override
    public String deleteOrder(int user_id, int order_id) {

        repository.deleteById(order_id);
        return "order with order id "+order_id+" is deleted successfully";
    }
}
