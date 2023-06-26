package com.example.gutrend.service.order;

import com.example.gutrend.model.Order;
import com.example.gutrend.model.User;
import com.example.gutrend.repository.ICategoryRepository;
import com.example.gutrend.repository.IOrderRepository;
import com.example.gutrend.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceIPML implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private UserDetailService userDetailService;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
        User user = userDetailService.getCurrentUser();
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

}
