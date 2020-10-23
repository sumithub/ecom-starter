package com.cakefactory;

//import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogService
{
    List<Product> findAll();
}
