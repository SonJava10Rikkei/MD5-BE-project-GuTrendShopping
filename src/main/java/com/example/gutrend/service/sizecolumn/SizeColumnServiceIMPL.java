package com.example.gutrend.service.sizecolumn;

import com.example.gutrend.model.SizeColumn;
import com.example.gutrend.model.User;
import com.example.gutrend.repository.ISizeColumnRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeColumnServiceIMPL implements ISizeColumnService {
    @Autowired
    private ISizeColumnRepository sizeColumnRepository;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public List<SizeColumn> findAll() {
        return sizeColumnRepository.findAll();
    }

    @Override
    public void save(SizeColumn sizeColumn) {
        User user = userDetailService.getCurrentUser();
        sizeColumn.setUser(user);
        sizeColumnRepository.save(sizeColumn);

    }

    @Override
    public Page<SizeColumn> findAll(Pageable pageable) {
        return sizeColumnRepository.findAll(pageable);
    }

    @Override
    public Optional<SizeColumn> findById(Long id) {
        return sizeColumnRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        sizeColumnRepository.deleteById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return sizeColumnRepository.existsByName(name);

    }
}
