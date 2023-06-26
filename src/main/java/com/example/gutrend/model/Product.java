package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    private float price;
    @NotNull
    private int quantity;
    @Column(columnDefinition = "datetime default (now())")
    private Date date = new Date();
    @NotNull
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/nguyendanhson-9374f.appspot.com/o/f4.jpg?alt=media&token=c30c42a6-b326-40a3-94ff-bfbb7e776521";
    @NotNull
    private String description;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;



    public Product(String name, Category category, float price, int quantity, String avatar, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.avatar = avatar;
        this.description = description;

    }

}
