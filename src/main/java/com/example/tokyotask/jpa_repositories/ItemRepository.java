package com.example.tokyotask.jpa_repositories;

import com.example.tokyotask.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
