package com.example.gutrend.service.category;

import com.example.gutrend.model.Category;
import com.example.gutrend.service.IGenericService;

public interface ICategoryService extends IGenericService<Category> {
    Boolean existsByName(String name);


}
