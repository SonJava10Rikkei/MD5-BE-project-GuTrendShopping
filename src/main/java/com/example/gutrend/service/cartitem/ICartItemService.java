package com.example.gutrend.service.cartitem;

import com.example.gutrend.model.CartItem;
import com.example.gutrend.model.Category;
import com.example.gutrend.service.IGenericService;

public interface ICartItemService extends IGenericService<CartItem> {
    void deleteCartItem();

}
