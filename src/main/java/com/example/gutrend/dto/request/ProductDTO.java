package com.example.gutrend.dto.request;

import com.example.gutrend.model.Category;
import com.example.gutrend.model.SizeColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull
    @NotBlank
    private String name;
    @NotNull

    private float price;
    @NotNull

    private int quantity;

    @NotNull
    @NotBlank
    private String avatar;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Category category;
    @NotNull
    private List<SizeColumn> sizeColumnList = new ArrayList<>();

}
