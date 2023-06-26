package com.example.gutrend.controller;


import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.Category;
import com.example.gutrend.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<?> showListOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> detailCategory(@PathVariable Long id) {
//        Optional<Category> category = categoryService.findById(id);
//        if (!category.isPresent()) {
//            return new ResponseEntity<>(new ResponMessage("id_does_not_exist"), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/page")
//    public ResponseEntity<?> pageCategory(@PageableDefault(size = 3) Pageable pageable) {
//        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
//    }
//
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createCategory(@RequestBody Category category) {
//        if (categoryService.existsByName(category.getName())) {
//            return new ResponseEntity<>(new ResponMessage("name_exist"), HttpStatus.OK);
//        }
//        categoryService.save(category);
//        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
//    }


}
