package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ItemAlreadyPresentException;
import com.mykart.exception.OutOfStockException;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getAllItems(int user_id);
//    public ItemDTO getItemById(int user_id,int item_id) throws ResourceNotFound;
    public Cart saveItem(int user_id,Cart cart) throws OutOfStockException, ItemAlreadyPresentException;
    public Cart updateItemById(int user_id,Cart cart) throws ResourceNotFound, OutOfStockException;
    public Boolean deleteItemById(int user_id,Cart cart) throws ResourceNotFound;
   
}
