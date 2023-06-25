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
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    @Transient
    private Double totalPrice;
    @NotNull
    private OderStatus status;
    @NotNull
    private Date created;
    @NotNull
    private Date changed;
    @ManyToOne
    private User user;
    @OneToMany
    private List<ShoppingCart> cartSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();
    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "order_shoppingcartlist", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "shoppingcartlist_id"))
//    private List<ShoppingCart>shoppingCartList;
}
