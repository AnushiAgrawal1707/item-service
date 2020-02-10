package com.services.itemservice.service;

import com.services.itemservice.exceptions.ResourceAlreadyPresentException;
import com.services.itemservice.exceptions.ResourceNotFoundException;
import com.services.itemservice.model.ItemModel;
import com.services.itemservice.repository.ItemRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Represents the services leveraged by the model Item */
@Service
public class ItemService implements ItemServiceInterface {
  @Autowired private ItemRepository itemRepository;

  private static final String RESOURCE_NOT_FOUND_EXCEPTION_RESPONSE = "No match found";
  private static final String RESOURCE_ALREADY_PRESENT_EXCEPTION_RESPONSE =
      "The item you're trying to add is already available in the warehouse";
  /**
   * Gets all the items available in the warehouse
   *
   * @return list of items in the warehouse
   * @throws ResourceNotFoundException when the list is empty
   */
  public List<ItemModel> getAllItems() throws ResourceNotFoundException {
    List<ItemModel> list = itemRepository.findAll();

    if (list.isEmpty()) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_EXCEPTION_RESPONSE);
    }
    return list;
  }

  /**
   * Gets item with this id
   *
   * @param id This item's unique id
   * @return This item's object containing all the details
   * @throws ResourceNotFoundException when this item is not present in the database
   */
  public Optional<ItemModel> getItemById(int id) throws ResourceNotFoundException {
    if (!itemRepository.findById(id).isPresent()) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_EXCEPTION_RESPONSE);
    }
    return itemRepository.findById(id);
  }

  /**
   * Creates item
   *
   * @param itemModel This item's object containing all the details
   * @return This item's object containing all the details
   * @throws ResourceAlreadyPresentException when the object with same details is already present
   */
  public ItemModel addItem(ItemModel itemModel) throws ResourceAlreadyPresentException {
    if (itemRepository.findAll().contains(itemModel)) {
      throw new ResourceAlreadyPresentException(RESOURCE_ALREADY_PRESENT_EXCEPTION_RESPONSE);
    }
    return itemRepository.save(itemModel);
  }

  /**
   * Updates the data of this item
   *
   * @param itemModel This item's object containing all the details
   * @return This item's updated object containing all the details
   * @throws ResourceNotFoundException when the object with same details is already present
   */
  public ItemModel updateItem(@NotNull ItemModel itemModel) throws ResourceNotFoundException {
    if (!itemRepository.findById(itemModel.getItemId()).isPresent()) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_EXCEPTION_RESPONSE);
    }
    itemRepository.save(itemModel);
    return itemModel;
  }

  /**
   * Deletes this item
   *
   * @param id the id of this item which is to be deleted
   * @return String stating the successful deletion message
   * @throws ResourceNotFoundException when the object with this id is not found
   */
  public String deleteItem(int id) throws ResourceNotFoundException {
    if (!itemRepository.findById(id).isPresent()) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_EXCEPTION_RESPONSE);
    }
    itemRepository.deleteById(id);
    return "item with id = " + id + " is deleted successfully";
  }
}
