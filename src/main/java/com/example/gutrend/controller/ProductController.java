package com.example.gutrend.controller;

import com.example.gutrend.dto.request.ProductDTO;
import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.Product;
import com.example.gutrend.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> showListProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist"), HttpStatus.OK);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageProduct(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        if (productService.existsByName(productDTO.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_exist"), HttpStatus.OK);
        } else {
            Product product = new Product(
                    productDTO.getName(), productDTO.getCategory(),
                    productDTO.getPrice(), productDTO.getQuantity(),
                    productDTO.getAvatar(), productDTO.getDescription()
                   );
            productService.save(product);
            return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean checkAvatar = product.getAvatar().equals(product1.get().getAvatar());
        boolean checkDescription = product.getDescription().equals(product1.get().getDescription());
        boolean checkName = product.getName().equals(product1.get().getName());
        boolean checkQuantity = product.getQuantity() == (product1.get().getQuantity());
        boolean checkPrice = product.getPrice() == (product1.get()).getPrice();
        boolean checkCategory = false;
        if (!checkAvatar) {
            product.setId(product1.get().getId());
        }
        if (!checkName) {
            if (productService.existsByName(product.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (product.getCategory().getId().longValue() == product1.get().getCategory().getId().longValue()) {
            checkCategory = true;
        }
        if (checkName
                && checkAvatar
                && checkCategory
                && checkPrice
                && checkQuantity
                && checkDescription

        ) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist "), HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }


}
