package com.example.gutrend.service.sizecolumn;

import com.example.gutrend.model.SizeColumn;
import com.example.gutrend.service.IGenericService;

public interface ISizeColumnService extends IGenericService<SizeColumn> {
    Boolean existsByName(String name);
}
