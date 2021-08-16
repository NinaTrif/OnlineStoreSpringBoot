package com.example.tokyotask.util;

import com.example.tokyotask.dto.AddItemToOrderRequest;
import com.example.tokyotask.dto.CreateOrderRegisteredRequest;
import com.example.tokyotask.jpa_repositories.CustomerRepository;
import com.example.tokyotask.jpa_repositories.ItemRepository;
import com.example.tokyotask.models.Customer;
import com.example.tokyotask.models.Item;
import com.example.tokyotask.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {
    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepo, ItemRepository itemRepo, OrderService orderService) {
        return args -> {
            /**
             * Used to add some sample data into in-memory H2 database.
             * */
            customerRepo.save(new Customer("Pera", "Peric", "pera.peric@gmail.com", "0601234567"));
            customerRepo.save(new Customer("Jovan", "Jovanovic", "jovan.jovanovic@gmail.com", "0611010101"));
            customerRepo.save(new Customer("Ivana", "Ivanovic", "ivana.ivanovic@gmail.com", "0607654321"));
            customerRepo.save(new Customer("Arsenije", "Arsic", "arsenije.arsic@outlook.com", "064224224"));
            customerRepo.save(new Customer("Paja", "Pajic", "paja.pajic@outlook.com", "0631231231"));

            itemRepo.save(new Item("Nike majica", "Nike zenska majica", "", 2490.00));
            itemRepo.save(new Item("Puma sorc", "Puma zenski sorc", "", 1800.00));
            itemRepo.save(new Item("Nike trenerka", "Nike zenska trenerka donji deo", "", 4300.00));
            itemRepo.save(new Item("Puma patike", "Puma zenske patike", "", 8999.99));
            itemRepo.save(new Item("Nike majica", "Nike muska majica", "", 3200.00));
            itemRepo.save(new Item("Yoga mat", "Prostirka za vezbanje", "", 1200.00));
            itemRepo.save(new Item("Nike patike", "Nike AIR MAX muske patike", "", 17999.99));
            itemRepo.save(new Item("Adidas majica", "Adidas muska majica", "", 3570.00));
            itemRepo.save(new Item("Adidas trenerka", "Adidas muska trenerka donji deo", "", 4999.99));
            itemRepo.save(new Item("Teg", "Teg za vezbanje 1kg", "", 870.00));

            orderService.createOrder(new CreateOrderRegisteredRequest(1l, 5l));
            orderService.addItemToOrder(new AddItemToOrderRequest(1l, 5l));
            orderService.addItemToOrder(new AddItemToOrderRequest(1l, 7l));
            orderService.createOrder(new CreateOrderRegisteredRequest(3l, 2l));
            orderService.addItemToOrder(new AddItemToOrderRequest(2l, 6l));
            orderService.addItemToOrder(new AddItemToOrderRequest(2l, 10l));
            orderService.createOrder(new CreateOrderRegisteredRequest(5l, 8l));
            orderService.placeOrder(3l);
        };
    }
}
