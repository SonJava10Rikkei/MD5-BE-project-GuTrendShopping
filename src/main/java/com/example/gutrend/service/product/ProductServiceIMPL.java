package com.example.gutrend.service.product;

import com.example.gutrend.model.Product;
import com.example.gutrend.model.User;
import com.example.gutrend.repository.IProductRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceIMPL implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        User user = userDetailService.getCurrentUser();
        product.setUser(user);
        productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
