package com.cakefactory.catalog.persistence;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<ProductEntity, String> {
}
