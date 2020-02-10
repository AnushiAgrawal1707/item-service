package com.services.itemservice.service;

import com.services.itemservice.exceptions.ResourceAlreadyPresentException;
import com.services.itemservice.exceptions.ResourceNotFoundException;
import com.services.itemservice.model.ItemModel;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ItemServiceInterface {
    public List<ItemModel> getAllItems() throws ResourceNotFoundException;
    public Optional<ItemModel> getItemById(int id) throws ResourceNotFoundException;
    public ItemModel addItem(ItemModel itemModel) throws ResourceAlreadyPresentException;
    public ItemModel updateItem(@NotNull ItemModel itemModel) throws ResourceNotFoundException;
    public String deleteItem(int id) throws ResourceNotFoundException;
}
