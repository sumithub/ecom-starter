package com.cakefactory.service;

import com.cakefactory.domain.Product;

public interface CatalogService
{
    Iterable<Product> getAllProducts();
}
