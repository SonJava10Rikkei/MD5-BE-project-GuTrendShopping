package com.example.gutrend.repository;

import com.example.gutrend.model.CartItem;
import com.example.gutrend.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(Long id);

    @Query(value = "SELECT sc FROM ShoppingCart as sc join sc.cartItemList cl where cl.id=:id")
    Optional<ShoppingCart> findShopingCartByCartItemId(@Param("id") Long id);

    @Query(value = "select cl from ShoppingCart  sc join sc.cartItemList cl where sc.id = :id")
    List<CartItem> findListCartItemByShoppingCartId(@Param("id") Long id);
}
