package com.example.gutrend.repository;

import com.example.gutrend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from CartItem ci where ci.id not in (SELECT cl.id from ShoppingCart sc JOIN sc.cartItemList as cl )")
    void deleteCartItem();


}
