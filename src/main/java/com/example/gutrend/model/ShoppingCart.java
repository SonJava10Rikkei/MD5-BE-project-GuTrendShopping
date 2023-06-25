package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date date;

    @Transient
    private Double totalPrice;

    @Transient
    private int itemsNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CartItem> ItemsCartSet;

    private String tokenSession;

    public Double getTotalPrice() {
        Double sum = 0.0;
        for (CartItem cartItem : this.ItemsCartSet) {
            sum = sum + cartItem.getProduct().getPrice();
        }
        return sum;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemsNumber() {
        return this.ItemsCartSet.size();
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

}
