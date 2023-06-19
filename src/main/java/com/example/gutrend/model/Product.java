package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Product {
    @ManyToOne
    User user;
    @ManyToOne
    Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(min = 3, max = 50)
    private String name;
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/nguyendanhson-9374f.appspot.com/o/f4.jpg?alt=media&token=c30c42a6-b326-40a3-94ff-bfbb7e776521";

    private float price;

    private int quantity;
    private int stock;
    private int idCategory;

}
