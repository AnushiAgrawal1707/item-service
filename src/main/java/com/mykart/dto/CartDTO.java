package com.mykart.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mykart.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class CartDTO {

   
    private int id;
    private int item_id;
    private int cart_id;
    private double price;
    private int quantity;
}