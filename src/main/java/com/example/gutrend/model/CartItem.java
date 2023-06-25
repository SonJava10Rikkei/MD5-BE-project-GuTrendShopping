package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cartitem")
@Data
@AllArgsConstructor
@NoArgsConstructor()
public class CartItem {
    @ManyToOne
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int quantity;

    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Product product;
}
