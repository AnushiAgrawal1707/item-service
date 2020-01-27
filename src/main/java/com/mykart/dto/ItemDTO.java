package com.mykart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDTO {

    private int itemId;
    private String itemName;
    private String sellerInfo;
    private String category;
    private int inStock;
    private double price;
}
