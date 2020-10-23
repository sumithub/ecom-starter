package com.cakefactory;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Override
    public List<Product> findAll() {
        return Arrays.asList(
                new Product("abcr", "All Butter Croissant", 0.75),
                new Product("ccr", "Chocolate Croissant", 0.95),
                new Product("b", "Fresh Baguette", 1.60),
                new Product("rv", "Red Velvet", 3.95),
                new Product("vs", "Victoria Sponge", 5.45),
                new Product("cc", "Carrot Cake", 3.45));
    }
}
