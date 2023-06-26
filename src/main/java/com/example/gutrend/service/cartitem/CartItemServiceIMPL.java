package com.example.gutrend.service.cartitem;

import com.example.gutrend.model.CartItem;
import com.example.gutrend.repository.ICartItemRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceIMPL implements ICartItemService {


    @Autowired
    private ICartItemRepository cartItemRepository;
    @Autowired
    private UserDetailService userDetailService;


    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public Page<CartItem> findAll(Pageable pageable) {
        return cartItemRepository.findAll(pageable);
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void deleteCartItem() {
        cartItemRepository.deleteCartItem();
    }
}
