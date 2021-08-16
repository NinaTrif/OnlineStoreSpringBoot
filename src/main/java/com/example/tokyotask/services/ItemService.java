package com.example.tokyotask.services;

import com.example.tokyotask.exceptions.BaseException;
import com.example.tokyotask.jpa_repositories.ItemRepository;
import com.example.tokyotask.models.Item;
import com.example.tokyotask.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    /**
     * Service Layer for handling Item operations.
     */
    private final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItems() {
        return repository.findAll();
    }

    public Item addItem(Item newItem) {
        return repository.save(newItem);
    }

    public Item getItemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.INVALID_ITEM));
    }

    public Item updateItem(Item newItem, Long id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setImageURL(newItem.getImageURL());
                    item.setPrice(newItem.getPrice());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
