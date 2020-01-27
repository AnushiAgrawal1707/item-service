package com.mykart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Table(name="orders")
@Entity
@ToString
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "cart_id")
    private String cart_id;
    @Column(name = "total_bill")
    private double total_bill;
}
