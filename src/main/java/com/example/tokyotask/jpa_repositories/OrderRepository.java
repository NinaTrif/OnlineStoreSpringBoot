package com.example.tokyotask.jpa_repositories;

import com.example.tokyotask.models.Customer;
import com.example.tokyotask.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
}
