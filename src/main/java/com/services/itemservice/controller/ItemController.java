package com.services.itemservice.controller;

import com.services.itemservice.exceptions.ResourceAlreadyPresentException;
import com.services.itemservice.exceptions.ResourceNotFoundException;
import com.services.itemservice.model.ItemModel;
import com.services.itemservice.model.dto.ItemDTO;
import com.services.itemservice.service.ItemService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *Represents the controller of the item service
 */
@RestController
@RequestMapping("/v1")
@Api(
    value = "Items Warehouse Service")
@Slf4j
public class ItemController {

  @Autowired
  private ItemService itemService;

    /**
     * GET method to retrieve all the items available in the warehouse with /warehouse mapping
     * @return list of items in the warehouse
     */
    @ApiOperation(
            value = "Returns all items details",
            notes = "Returns a complete list of items with details.",
            response = List.class)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful retrieval of items details",
                            response = List.class),
                    @ApiResponse(code = 404, message = "No data available"),
                    @ApiResponse(code = 401, message = "not authorized!"),
                    @ApiResponse(code = 403, message = "Access forbidden"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    @GetMapping(value = "/warehouse")
    public ResponseEntity<List<ItemModel>> getAllItems() {
        List<ItemModel> list = new ArrayList<>();
        try {
            list = itemService.getAllItems();
            log.debug("Executed ItemController.getAllItems() to retrive all items .");
            return ResponseEntity.ok(list);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * GET method to retrieve item with this id with/warehouse/{item_id} mapping
     * @param itemId This item's unique id
     * @return This item's object containing all the details
     */
    @ApiOperation(
            value = "Returns specific item details",
            notes = "Returns item's details with given id",
            response = ItemModel.class)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful retrieval of item details",
                            response = ItemModel.class),
                    @ApiResponse(code = 404, message = "No data available"),
                    @ApiResponse(code = 401, message = "not authorized!"),
                    @ApiResponse(code = 403, message = "Access forbidden"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    @GetMapping(value = "/warehouse/{item_id}")
    public ResponseEntity<Optional<ItemModel>> getItemById(
            @ApiParam(value = "item id", required = true) @PathVariable("item_id") int itemId) {
        try {
            Optional<ItemModel> itemModel = itemService.getItemById(itemId);
            log.debug("Executed ItemController.getItemById(id) to retrive an item object with id "+itemId);
            return ResponseEntity.ok(itemModel);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * POST method to create item in the database with /warehouse mapping
     * @param itemDTO This item's object containing all the details
     * @return This item's object containing all the details
     * @throws URISyntaxException when the given string can't be parsed as a URI reference
     */
    @ApiOperation(
            value = "insert item in the database",
            notes = "create item in the database",
            response = ItemModel.class)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful insertion of item details",
                            response = ItemModel.class),
                    @ApiResponse(code = 401, message = "not authorized!"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    @PostMapping("/warehouse")
    public ResponseEntity<ItemDTO> createItem(
            @ApiParam("Item information for a new item to be created") @Valid @RequestBody ItemDTO itemDTO)
            throws URISyntaxException {
        try {
            ItemModel itemModel = new ItemModel();
            BeanUtils.copyProperties(itemDTO,itemModel);
            itemModel=itemService.addItem(itemModel);
            BeanUtils.copyProperties(itemModel,itemDTO);
            log.debug("Executed ItemController.createItem(itemDTO) to create an Item object");
            return ResponseEntity.created(new URI("/id=" + itemDTO.getItemId())).body(itemDTO);
        } catch (ResourceAlreadyPresentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     *PUT method to update this item in the database with /warehouse mapping
     * @param itemDTO This item's object containing all the details
     * @return This item's updated object containing all the details
     */
    @ApiOperation(
            value = "update item in the database",
            notes = "update item in the database",
            response = ItemModel.class)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful updation of item details",
                            response = ItemModel.class),
                    @ApiResponse(code = 401, message = "not authorized!"),
                    @ApiResponse(code = 403, message = "Access forbidden"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    @PutMapping("/warehouse")
    public ResponseEntity<ItemDTO> updateItem(
            @ApiParam("item information of an existing item to be updated") @Valid @RequestBody
                    ItemDTO itemDTO) {
        try {
            ItemModel itemModel=new ItemModel();
            BeanUtils.copyProperties(itemDTO,itemModel);
            itemModel=itemService.updateItem(itemModel);
            BeanUtils.copyProperties(itemModel,itemDTO);
            log.debug("Executed ItemController.updateItem(itemDTO) to update Item object with id"
                    + itemDTO.getItemId());
            return ResponseEntity.ok(itemDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE method to delete this item from the database with /warehouse/{item_id} mapping
     * @param itemId the id of this item which is to be deleted
     * @return String stating the successful deletion message
     */
    @ApiOperation(
            value = "delete item from the database",
            notes = "delete item from the database",
            response = ItemModel.class)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful deletion of item",
                            response = String.class),
                    @ApiResponse(code = 404, message = "No data available"),
                    @ApiResponse(code = 401, message = "not authorized!"),
                    @ApiResponse(code = 403, message = "Access forbidden"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    @DeleteMapping("/warehouse/{item_id}")
    public ResponseEntity<String> deleteItem(
            @ApiParam(value = "Item id", required = true) @PathVariable("item_id") int itemId) {
        try {
            log.debug(
                    "Executed ItemController.deleteItem(id) to delete Item object with id " + itemId);

            return ResponseEntity.ok(itemService.deleteItem(itemId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
