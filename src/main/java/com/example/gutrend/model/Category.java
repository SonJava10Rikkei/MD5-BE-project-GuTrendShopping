package com.example.gutrend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Category {
    @ManyToOne
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String name;
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/nguyendanhson-9374f.appspot.com/o/Category_default.png?alt=media&token=32429ae4-ae06-43e9-a50d-5d797eb46932";

}
