package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ItemAlreadyPresentException;
import com.mykart.exception.OutOfStockException;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.repository.cart.CartRepository;
import com.mykart.service.item.ItemService;
import com.mykart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
  @Autowired private CartRepository cartRepository;
  @Autowired private ItemService itemService;
  @Autowired private UserService userService;

  @Override
  public List<Cart> getAllItems(int user_id) {
    List<ItemDTO> itemDTOList = new ArrayList<>();
    User user = userService.getUserById(user_id);
    String cart_id = user.getCart_id();
    List<Cart> cartList = cartRepository.findByCartIdAndOrdered(cart_id, 0);
    System.out.println(cartList);
    Iterator<Cart> iterator = cartList.iterator();
    while (iterator.hasNext()) {
      itemDTOList.add(itemService.getItem(iterator.next().getItem_id()));
    }

    System.out.println(itemDTOList);

    return cartList;
  }

  @Override
  public Cart saveItem(int user_id, Cart cart)
      throws OutOfStockException, ItemAlreadyPresentException {
    User user = userService.getUserById(user_id);
    System.out.println("arrived at cart service");
    String cart_id = user.getCart_id();
    ItemDTO item = itemService.getItem(cart.getItem_id());
    Iterator<Cart> iterator = cartRepository.findByCartIdAndOrdered(cart_id, 0).iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getItem_id() == item.getItemId()) {
        throw new ItemAlreadyPresentException(
            item.getItemName() + " is already added to your cart");
      }
    }
    int inStock = item.getInStock();
    if (inStock < cart.getQuantity())
      throw new OutOfStockException(item.getItemName() + " is currently unavailable");
    cart.setItem_id(item.getItemId());
    cart.setCart_id(user.getCart_id());
    cart.setPrice(item.getPrice() * cart.getQuantity());
    System.out.println(cart);
    return cartRepository.save(cart);
  }

  @Override
  public Boolean deleteItemById(int user_id, Cart cart) throws ResourceNotFound {
    User user = userService.getUserById(user_id);
    String cart_id = user.getCart_id();
    Cart c = cartRepository.findByCartIdAndItemIdAndIsOrdered(cart_id, cart.getItem_id(), 0);
    if (c == null) throw new ResourceNotFound();
    System.out.println(c);
    cartRepository.deleteById(c.getId());
    return true;
  }

  @Override
  public Cart updateItemById(int user_id, Cart cart) throws ResourceNotFound, OutOfStockException {
    User user = userService.getUserById(user_id);
    String cart_id = user.getCart_id();
    int item_id = cart.getItem_id();
    Cart newCart = cartRepository.findByCartIdAndItemIdAndIsOrdered(cart_id, item_id, 0);
    if (newCart == null) throw new ResourceNotFound("resource not found");

    ItemDTO item = itemService.getItem(cart.getItem_id());
    int inStock = item.getInStock();

    if (inStock < cart.getQuantity())
      throw new OutOfStockException(item.getItemName() + " is currently unavailable");

    newCart.setQuantity(cart.getQuantity());
    cartRepository.save(newCart);
    return newCart;
  }
}
