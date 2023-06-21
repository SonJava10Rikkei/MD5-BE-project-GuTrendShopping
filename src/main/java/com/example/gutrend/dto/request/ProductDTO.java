package com.example.gutrend.dto.request;

import com.example.gutrend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private float price;
    @NotNull
    private int quantity;

    @NotNull
    private String avatar;

    @NotNull
    private String description;

    @NotNull
    private Category category;

}
