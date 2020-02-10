package com.services.itemservice.repository;

import com.services.itemservice.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel,Integer> {

}
