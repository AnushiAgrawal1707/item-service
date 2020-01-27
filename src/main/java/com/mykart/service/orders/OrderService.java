package com.mykart.service.orders;

import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    public Orders placeOrder(int user_id);
    public List<Orders> getAllOrders(int user_id);
    public Orders getOrderById(int user_id,int order_id) throws ResourceNotFound;
    public String deleteOrder(int user_id, int order_id);
}
