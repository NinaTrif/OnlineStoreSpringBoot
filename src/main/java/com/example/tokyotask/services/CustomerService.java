package com.example.tokyotask.services;

import com.example.tokyotask.exceptions.BaseException;
import com.example.tokyotask.jpa_repositories.CustomerRepository;
import com.example.tokyotask.models.Customer;
import com.example.tokyotask.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    /**
     * Service Layer for handling Customer operations.
     */
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer addCustomer(Customer newCustomer) {
        return repository.save(newCustomer);
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.INVALID_CUSTOMER));
    }

    public Customer updateCustomer(Customer newCustomer, Long id) {
        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setPhone(newCustomer.getPhone());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}
