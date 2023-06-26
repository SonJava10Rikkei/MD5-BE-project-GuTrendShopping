package com.example.gutrend.controller;

import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.CartItem;
import com.example.gutrend.model.ShoppingCart;
import com.example.gutrend.model.User;
import com.example.gutrend.security.userprincal.UserDetailService;
import com.example.gutrend.service.cartitem.ICartItemService;
import com.example.gutrend.service.shoppingcart.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shopping_cart")
@CrossOrigin(origins = "*")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;


    @GetMapping
    public ResponseEntity<?> showListShoppingCart() {
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.findById(id);
        if (!shoppingCart.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist"), HttpStatus.OK);
        }
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageShoppingCart(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(shoppingCartService.findAll(pageable), HttpStatus.OK);
    }


}
