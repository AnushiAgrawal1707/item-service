package com.services.itemservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** Represents an item stored in the warehouse */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class ItemModel {
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
