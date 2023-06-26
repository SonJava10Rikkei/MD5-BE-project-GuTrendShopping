package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;

    private OderStatus oderStatus;

    private int totalNumberAll;

    private double totalPriceAll;

    @ManyToOne
    private ShoppingCart shoppingCarts;

    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();

}
