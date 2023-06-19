package com.example.gutrend.service.category;

import com.example.gutrend.model.Category;
import com.example.gutrend.model.User;
import com.example.gutrend.repository.ICategoryRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private UserDetailService userDetailService;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        User user = userDetailService.getCurrentUser();
        category.setUser(user);
        categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
