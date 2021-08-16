package com.example.tokyotask.jpa_repositories;

import com.example.tokyotask.models.Item;
import com.example.tokyotask.models.Order;
import com.example.tokyotask.models.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
    OrderedItem findByOrderAndItem(Order order, Item item);
}
