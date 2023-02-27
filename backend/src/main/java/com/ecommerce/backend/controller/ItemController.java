package com.ecommerce.backend.controller;

import com.ecommerce.backend.Repository.ItemRepository;
import com.ecommerce.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems()
    {
        List<Item> items = itemRepository.findAll();
        return items;
    }

}