package com.services.itemservice.controller;

import com.services.itemservice.exceptions.ResourceAlreadyPresentException;
import com.services.itemservice.exceptions.ResourceNotFoundException;
import com.services.itemservice.model.ItemModel;
import com.services.itemservice.model.dto.ItemDTO;
import com.services.itemservice.service.ItemService;
import com.services.itemservice.controller.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
  @InjectMocks
  ItemController itemController;

  @Mock
  ItemService itemService;


  @Test
  public void getAllItems() throws Exception {
    List<ItemModel> list = new ArrayList<ItemModel>();
    ItemModel itemModel = mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    list.add(itemModel);
    when(itemService.getAllItems()).thenReturn(list);
    itemController.getAllItems();
    assertEquals(101,itemModel.getItemId());
    verify(itemService,times(1)).getAllItems();
  }
  @Test
  public void getItemById() throws ResourceNotFoundException {
    ItemModel itemModel = mock(ItemModel.class);
    when(itemModel.getItemId()).thenReturn(101);
    when(itemService.getItemById(101)).thenReturn(java.util.Optional.ofNullable(itemModel));
    itemController.getItemById(101);
    assertEquals(101,itemModel.getItemId());
    verify(itemService,times(1)).getItemById(101);
  }

  @Test
  public void createItem() throws ResourceAlreadyPresentException, URISyntaxException {
    ItemModel itemModel = mock(ItemModel.class);
    ItemDTO itemDTO = mock(ItemDTO.class);

    BeanUtils.copyProperties(itemDTO,itemModel);



  }

  @Test
 public void updateItemTest() throws ResourceNotFoundException {
    ItemDTO itemDTO=new ItemDTO();
    itemDTO.setItemId(101);
    itemDTO.setItemName("dairy milk");
    itemDTO.setCategory("chocolate");
    itemDTO.setInStock(100);
    itemDTO.setSellerInfo("cadbury");
    itemDTO.setPrice(10);

    ItemModel itemModel=new ItemModel();

    assertThat(itemModel.getItemId() == 0).as("Id empty").isTrue();
    assertThat(itemModel.getItemName() == null).as("Name empty").isTrue();
    assertThat(itemModel.getCategory() == null).as("Category empty").isTrue();
    assertThat(itemModel.getInStock() == 0).as("Instock empty").isTrue();
    assertThat(itemModel.getSellerInfo() == null).as("Seller info empty").isTrue();
    assertThat(itemModel.getPrice() == 0).as("Price empty").isTrue();

    BeanUtils.copyProperties(itemDTO, itemModel);

    assertThat(itemDTO.getItemId()==itemModel.getItemId()).as("Item id copied").isTrue();
    assertThat(itemDTO.getItemName().equals(itemModel.getItemName())).as("Name copied").isTrue();
    assertThat(itemModel.getCategory().equals(itemDTO.getCategory())).as("Category copied").isTrue();
    assertThat(itemModel.getInStock() == itemDTO.getInStock()).as("Instock copied").isTrue();
    assertThat(itemModel.getSellerInfo().equals(itemDTO.getSellerInfo())).as("Seller info copied").isTrue();
    assertThat(itemModel.getPrice() == itemDTO.getPrice()).as("Price copied").isTrue();

     when(itemService.updateItem(itemModel)).thenReturn(itemModel);
      System.out.println(itemModel);
    BeanUtils.copyProperties(itemModel,itemDTO);
    assertThat(itemDTO.getItemId()==itemModel.getItemId()).as("Item id copied").isTrue();
    assertThat(itemDTO.getItemName().equals(itemModel.getItemName())).as("Name copied").isTrue();
    assertThat(itemModel.getCategory().equals(itemDTO.getCategory())).as("Category copied").isTrue();
    assertThat(itemModel.getInStock() == itemDTO.getInStock()).as("Instock copied").isTrue();
    assertThat(itemModel.getSellerInfo().equals(itemDTO.getSellerInfo())).as("Seller info copied").isTrue();
    assertThat(itemModel.getPrice() == itemDTO.getPrice()).as("Price copied").isTrue();

  //  itemController.updateItem(itemDTO);
//    verify(itemService,times(1)).updateItem(itemModel);
//
//    ItemModel itemModel1=new ItemModel();
//    itemModel1.setItemId(101);
//    itemModel1.setItemName("dairy milk");
//    itemModel1.setCategory("chocolate");
//    itemModel1.setInStock(100);
//    itemModel1.setSellerInfo("cadbury");
//    itemModel1.setPrice(10);
//
//    ItemDTO itemDTO1=new ItemDTO();
//
//    assertThat(itemDTO1.getItemId() == 0).as("Id empty").isTrue();
//    assertThat(itemDTO1.getItemName() == null).as("Name empty").isTrue();
//    assertThat(itemDTO1.getCategory() == null).as("Category empty").isTrue();
//    assertThat(itemDTO1.getInStock() == 0).as("Instock empty").isTrue();
//    assertThat(itemDTO1.getSellerInfo() == null).as("Seller info empty").isTrue();
//    assertThat(itemDTO1.getPrice() == 0).as("Price empty").isTrue();
//
//
//    BeanUtils.copyProperties(itemModel1, itemDTO1);
//    assertThat(itemDTO1.getItemId()==itemModel1.getItemId()).as("Item id copied").isTrue();
//    assertThat(itemDTO1.getItemName().equals(itemModel1.getItemName())).as("Name copied").isTrue();
//    assertThat(itemModel1.getCategory().equals(itemDTO1.getCategory())).as("Category copied").isTrue();
//    assertThat(itemModel1.getInStock() == itemDTO1.getInStock()).as("Instock copied").isTrue();
//    assertThat(itemModel1.getSellerInfo().equals(itemDTO1.getSellerInfo())).as("Seller info copied").isTrue();
//    assertThat(itemModel1.getPrice() == itemDTO1.getPrice()).as("Price copied").isTrue();
//
    //itemController.updateItem(itemDTO1);
//   assertEquals(101,itemModel.getItemId());
//  verify(itemService,times(1)).updateItem(itemModel);
}



  @Test
public  void deleteItem() throws ResourceNotFoundException {


    itemService.deleteItem(101);
    itemController.deleteItem(101);
    verify(itemService,times(2)).deleteItem(101);

  }

}