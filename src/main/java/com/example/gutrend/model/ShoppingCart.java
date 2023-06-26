package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopingcart")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ShoppingCart {
    @OneToOne
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private int totalNumberItems;
    @Transient
    private Double totalPriceItems;

    @Transient
    private Order order;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "cartItemOfShopingCart",joinColumns = @JoinColumn(name = "shoppingCartId"),inverseJoinColumns = @JoinColumn(name = "CartItemId"))
    private List<CartItem> cartItemList;


}
