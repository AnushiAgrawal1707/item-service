package com.mykart.controller.cart;

import com.mykart.dto.CartDTO;
import com.mykart.exception.ItemAlreadyPresentException;
import com.mykart.exception.OutOfStockException;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.service.cart.CartService;
import com.mykart.validators.Identification;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author anuja_harane
 *
 * CartController is responsible for cart related operations
 *
 */
@RestController
@RequestMapping("/v1/users")
@Log4j2
@Validated
@Api(value = "Cart Data Service",
        description = "Operations pertaining to Cart in Cart Data Service")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     *    to get all the items from the cart with given user identifier
     * @param user_id  Identifier of user
     * @return      List of ItemDTO
     */
    @GetMapping("/{user_id}/carts")
    @ApiOperation(value = "Get list of Items available in cart", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public List<Cart> getAllItems(@ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id) {
        log.debug("Executed CartController.getAllItems() to retrieve all Items of  Cart");
        return cartService.getAllItems(user_id);
    }

//    /**
//     *
//     * @param user_id   Identifier of user
//     * @param item_id      get specific Item with given Identifier
//     * @return       ItemDTO
//     * @throws ResourceNotFound   If Item with given identifier not found
//     */
//    @GetMapping("/{user_id}/carts/{item_id}")
//    @ApiOperation(value = "Get Item of cart", response = ItemDTO.class)
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
//            @ApiResponse(code = 401, message = "not authorized!"),
//            @ApiResponse(code = 403, message = "forbidden!!!"),
//            @ApiResponse(code = 404, message = "not found!!!")})
//    public ItemDTO getItems( @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,@ApiParam(value = "item id", required = true) @PathVariable("item_id") int item_id) throws ResourceNotFound {
//        log.debug("Executed CartController.getItems() to retrieve specific Item of  Cart");
//        return cartService.getItemById(user_id,item_id);
//    }

    /**
     *
     * @param user_id    Identifier of user
     * @param cart       Cart object with item_id and quantity of item
     * @return           Cart
     */
    @PostMapping("/{user_id}/carts")
    @ApiOperation(value = "Save Item object into Cart", response = Cart.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public Cart addItemToCart(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,
            @ApiParam(value = "Cart object", required = true) @RequestBody Cart cart) throws OutOfStockException, ItemAlreadyPresentException {
        log.debug("Executed CartController.addItemToCart(cart) to save Item to cart ");
        //System.out.println("arrived at cart controller");
        return cartService.saveItem(user_id,cart);
    }

    /**
     *
     * @param user_id  Identifier of User
     * @param cart     Cart object with item_id and quantity
     * @return      Cart
     */
    @PutMapping("/{user_id}/carts")
    @ApiOperation(value = "Update cart object into the database", response = Cart.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public Cart updateItemInCart(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,
            @ApiParam(value = "Cart object", required = true) @RequestBody Cart cart) throws ResourceNotFound, OutOfStockException {
        log.debug("Executed CartController.updateItemInCart(cart) to update Item to cart ");
      //  System.out.println("arrived at cart controller");
        return cartService.updateItemById(user_id,cart);
    }

    /**
     *
     * @param user_id  Identifier of user
     * @param cart Cart object conating item id to be deleted
     * @return
     * @throws ResourceNotFound
     */
    @DeleteMapping("/{user_id}/carts")
    @ApiOperation(value = "Delete cart object into the database", response = Cart.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public Boolean deleteItemInCart(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,
            @ApiParam(value = "cart", required = true) @RequestBody Cart cart) throws ResourceNotFound {
        log.debug("Executed CartController.updateItemInCart(cart) to update Item to cart ");
      //  System.out.println("arrived at cart controller");
        return cartService.deleteItemById(user_id,cart);
    }
}

