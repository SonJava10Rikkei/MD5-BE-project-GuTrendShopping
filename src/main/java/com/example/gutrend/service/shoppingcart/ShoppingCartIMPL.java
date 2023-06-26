package com.example.gutrend.service.shoppingcart;

import com.example.gutrend.model.CartItem;
import com.example.gutrend.model.ShoppingCart;
import com.example.gutrend.repository.IShoppingCartRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartIMPL implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public Optional<ShoppingCart> findByUserId(Long id) {
        return shoppingCartRepository.findByUserId(id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();

    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Page<ShoppingCart> findAll(Pageable pageable) {
        return shoppingCartRepository.findAll(pageable);
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Optional<ShoppingCart> findShopingCartByCartItemId(Long id) {
        return shoppingCartRepository.findShopingCartByCartItemId(id);
    }

    @Override
    public List<CartItem> findListCartItemByShoppingCartId(Long id) {
        return shoppingCartRepository.findListCartItemByShoppingCartId(id);
    }
}
