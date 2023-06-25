package com.example.gutrend.controller;

import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.Category;
import com.example.gutrend.model.SizeColumn;
import com.example.gutrend.service.sizecolumn.ISizeColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sizecolumn")
public class SizeColumnController {
    @Autowired
    private ISizeColumnService sizeColumnService;


    @GetMapping()
    public ResponseEntity<?> listSizeColumn() {
        return new ResponseEntity<>(sizeColumnService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailSizeColumn(@PathVariable Long id) {
        Optional<SizeColumn> sizeColumn = sizeColumnService.findById(id);
        if (!sizeColumn.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist"), HttpStatus.OK);
        }
        return new ResponseEntity<>(sizeColumn, HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?> pageSizeColumn(@PageableDefault(size = 3)Pageable pageable) {
        return new ResponseEntity<>(sizeColumnService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSizeColumn(@Valid @RequestBody SizeColumn sizeColumn) {
        if (sizeColumnService.existsByName(sizeColumn.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_exist"), HttpStatus.OK);
        }
        sizeColumnService.save(sizeColumn);
        return new ResponseEntity<>(new ResponMessage("create_success"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSizeColumn(@PathVariable Long id, @RequestBody SizeColumn sizeColumn) {
        Optional<SizeColumn> sizeColumn1 = sizeColumnService.findById(id);
        if (!sizeColumn1.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist "),HttpStatus.NOT_FOUND);
        }
        if (!sizeColumn.getName().equals(sizeColumn1.get().getName())) {
            if (sizeColumnService.existsByName(sizeColumn.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (sizeColumn.getName().equals(sizeColumn1.get().getName())) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        sizeColumn.setId(sizeColumn1.get().getId());
        sizeColumnService.save(sizeColumn);
        return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSizeColumn(@PathVariable Long id) {
        Optional<SizeColumn> sizeColumn = sizeColumnService.findById(id);
        if (!sizeColumn.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("id_does_not_exist "),HttpStatus.NOT_FOUND);
        }
        sizeColumnService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }





}
