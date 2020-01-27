package com.mykart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Table(name="cart")
@Entity
@ToString
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "cart_id")
    private String cart_id;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_ordered")
    private int is_ordered;
}
