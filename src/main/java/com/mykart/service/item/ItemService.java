package com.mykart.service.item;

import com.mykart.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    public ItemDTO getItem(int item_id);
    public ItemDTO updateItem(int item_id, int quantity);
}