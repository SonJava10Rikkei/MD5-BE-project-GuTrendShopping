package com.example.gutrend.service.shoppingcart;

import com.example.gutrend.model.CartItem;
import com.example.gutrend.model.ShoppingCart;
import com.example.gutrend.service.IGenericService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IShoppingCartService extends IGenericService<ShoppingCart> {
    Optional<ShoppingCart> findByUserId(Long id);
    void save (ShoppingCart shoppingCart);
    Optional<ShoppingCart> findShopingCartByCartItemId( Long id);
    List<CartItem> findListCartItemByShoppingCartId(Long id);
}
