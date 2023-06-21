package com.example.gutrend.controller;

import com.example.gutrend.dto.request.ProductDTO;
import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.Product;
import com.example.gutrend.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> showListProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageProduct(Pageable pageable) {
        return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(), productDTO.getCategory(),
                productDTO.getPrice(), productDTO.getQuantity(),
                productDTO.getAvatar(), productDTO.getDescription());
        productService.save(product);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }
}
