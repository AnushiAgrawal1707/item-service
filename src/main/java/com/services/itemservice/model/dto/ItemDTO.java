package com.services.itemservice.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(description = "Details of Item data transfer object")
public class ItemDTO {
    /** The unique identification number of this item This is the primary key */
    @Id
    @Column(name = "item_id")
    private int itemId;

    /** The name of this item */
    @NotBlank
    @Column(name = "item_name")
    private String itemName;

    /** The name of the seller who sells this item */
    @NotBlank
    @Column(name = "seller_info")
    private String sellerInfo;

    /** The category in which this item falls */
    @NotBlank
    @Column(name = "category")
    private String category;

    /** The quantity of this item available in the warehouse */
    @NotNull
    @Column(name = "instock")
    private int inStock;

    /** The price of this item */
    @NotNull
    @Column(name = "price")
    private double price;

}
