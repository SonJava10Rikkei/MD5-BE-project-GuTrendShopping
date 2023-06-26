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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartitem")
@CrossOrigin(origins = "*")
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<?> showListCartitem() {
        return new ResponseEntity<>(cartItemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCartitem(@PathVariable Long id) {
        Optional<CartItem> cartItem = cartItemService.findById(id);
        if (!cartItem.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist"), HttpStatus.OK);
        }
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageCartitem(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(cartItemService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCartitem(@Valid @RequestBody CartItem cartItem) {
        User user = userDetailService.getCurrentUser();
        ShoppingCart shoppingCart;
        Optional<ShoppingCart> findShoppingCart = shoppingCartService.findByUserId(user.getId());
        if (!findShoppingCart.isPresent()) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            List<CartItem> cartItemList = new ArrayList<>();
            cartItemList.add(cartItem);
            shoppingCart.setCartItemList(cartItemList);
        } else {
            shoppingCart = findShoppingCart.get();
            updateShoppingCartInfo(cartItem, shoppingCart);
        }
        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCartitem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        User user = userDetailService.getCurrentUser();
        Optional<CartItem> cartItem1 = cartItemService.findById(id);
        Optional<ShoppingCart> findShoppingCart = shoppingCartService.findShopingCartByCartItemId(id);

        if (!cartItem1.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("cart_item_not_exist"), HttpStatus.OK);
        }
        if (!findShoppingCart.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("shopping_cart_include_not_exist"), HttpStatus.OK);
        }
        if (findShoppingCart.get().getUser().getId().longValue() != user.getId().longValue()) {
            return new ResponseEntity<>(new ResponMessage("shopping_cart_include_not_own"), HttpStatus.OK);
        }

        ShoppingCart shoppingCart = findShoppingCart.get();
        updateShoppingCartInfo(cartItem, shoppingCart);

        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long id) {
        User user = userDetailService.getCurrentUser();
        Optional<CartItem> cartItem1 = cartItemService.findById(id);
        Optional<ShoppingCart> findShoppingCart = shoppingCartService.findShopingCartByCartItemId(id);

        if (!cartItem1.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("cart_item_not_exist"), HttpStatus.OK);
        }
        if (!findShoppingCart.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("shopping_cart_include_not_exist"), HttpStatus.OK);
        }
        if (findShoppingCart.get().getUser().getId().longValue() != user.getId().longValue()) {
            return new ResponseEntity<>(new ResponMessage("shopping_cart_include_not_own"), HttpStatus.OK);
        }
        ShoppingCart shoppingCart = findShoppingCart.get();
        for (int i = 0; i < shoppingCart.getCartItemList().size(); i++) {
            if (shoppingCart.getCartItemList().get(i).getId() == id) {
                shoppingCart.getCartItemList().remove(i);
                break;
            }
        }
        cartItemService.deleteById(id);
        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }

    private static void updateShoppingCartInfo(CartItem cartItem, ShoppingCart shoppingCart) {
        boolean flag = false;
        int index = -1;
        List<CartItem> cartItemList = shoppingCart.getCartItemList();
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).getProduct().getId().longValue() ==
                    cartItem.getProduct().getId().longValue()
            ) {
                flag = true;
                index = i;
                break;
            }
        }
        if (flag) {
            cartItemList.get(index).setQuantity(cartItemList.get(index).getQuantity() + cartItem.getQuantity());
        } else {
            cartItemList.add(cartItem);
        }
    }


}
