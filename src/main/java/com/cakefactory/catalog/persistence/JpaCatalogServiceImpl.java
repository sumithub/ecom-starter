package com.cakefactory.catalog.persistence;

import com.cakefactory.catalog.CatalogService;
import com.cakefactory.catalog.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public JpaCatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Iterable<ProductEntity> productEntities = catalogRepository.findAll();
        for (ProductEntity pe :productEntities) {
            Product product = new Product();
            product.setName(pe.getName());
            product.setPrice(pe.getPrice());
            products.add(product);
        }
        return products;
    }
}
