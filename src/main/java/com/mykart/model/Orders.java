package com.mykart.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name="orders")
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
