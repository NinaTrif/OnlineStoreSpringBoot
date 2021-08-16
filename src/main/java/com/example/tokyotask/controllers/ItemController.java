package com.example.tokyotask.controllers;

import com.example.tokyotask.models.Item;
import com.example.tokyotask.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {
    /**
     * Rest Controller used for creating, deleting and updating items, as well other item manipulations.
     * Item API Layer.
     */
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping("/addItem")
    Item addItem(@RequestBody Item newItem) {
        return itemService.addItem(newItem);
    }

    @GetMapping("/items/{id}")
    Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/updateItem/{id}")
    Item updateItem(@RequestBody Item newItem, @PathVariable Long id) {
        return itemService.updateItem(newItem, id);
    }

    @DeleteMapping("/deleteItem/{id}")
    void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
