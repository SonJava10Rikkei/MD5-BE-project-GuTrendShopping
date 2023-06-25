package com.example.gutrend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sizecolumn")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class SizeColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    private int quantitySize;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String name;

    @ManyToOne
    private User user;
}
