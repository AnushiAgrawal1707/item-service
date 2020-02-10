package com.services.itemservice.service;

import com.services.itemservice.exceptions.ResourceAlreadyPresentException;
import com.services.itemservice.exceptions.ResourceNotFoundException;
import com.services.itemservice.model.ItemModel;
import com.services.itemservice.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

  @InjectMocks ItemService itemService;

  @Mock
  ItemRepository itemRepository;



  @Test
  public void getAllItemsTest() throws ResourceNotFoundException {
    List<ItemModel> list = new ArrayList<ItemModel>();
    ItemModel itemModel = mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    list.add(itemModel);
    when(itemRepository.findAll()).thenReturn(list);
    itemService.getAllItems();
    assertEquals(101,itemModel.getItemId());
    verify(itemRepository,times(1)).findAll();
  }

  @Test
  public void getItemByIdTest() throws ResourceNotFoundException {
  ItemModel itemModel = mock(ItemModel.class);
  when(itemModel.getItemId()).thenReturn(101);
  when(itemRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(itemModel));
  itemService.getItemById(101);
  assertEquals(101,itemModel.getItemId());
  verify(itemRepository,times(2)).findById(101);
  }
  
  @Test
  public void addItemTest() throws ResourceAlreadyPresentException {
    ItemModel itemModel=mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    when(itemRepository.save(itemModel)).thenReturn(itemModel);
    itemService.addItem(itemModel);
    assertEquals(101,itemModel.getItemId());
    verify(itemRepository,times(1)).save(itemModel);
    verify(itemRepository,times(1)).findAll();
  }


  @Test
  public void updateItemTest() throws ResourceNotFoundException {
    ItemModel itemModel = mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    when(itemRepository.findById(101)).thenReturn(java.util.Optional.ofNullable(itemModel));
    when(itemRepository.save(itemModel)).thenReturn(itemModel);
    itemService.updateItem(itemModel);
    assertEquals(101,itemModel.getItemId());
    verify(itemRepository,times(1)).save(itemModel);
    verify(itemRepository,times(1)).findById(101);
  }


  @Test
  public void deleteItemTest() throws ResourceNotFoundException {
    ItemModel itemModel = mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    when(itemRepository.findById(101)).thenReturn(java.util.Optional.of(itemModel));
    itemService.deleteItem(101);
    verify(itemRepository,times(1)).deleteById(101);
  }
}