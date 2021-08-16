package com.example.tokyotask.jpa_repositories;

import com.example.tokyotask.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
