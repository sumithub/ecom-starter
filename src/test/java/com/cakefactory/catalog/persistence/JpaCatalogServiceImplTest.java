package com.cakefactory.catalog.persistence;

import com.cakefactory.catalog.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaCatalogServiceImplTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CatalogRepository catalogRepository;

    private JpaCatalogServiceImpl jpaCatalogService;

    @BeforeEach
    void setup() {
        this.jpaCatalogService = new JpaCatalogServiceImpl(this.catalogRepository);
    }

    @Test
    @DisplayName("returns data from the database")
    void returnsDataFromDatabase() {
        //given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setSku("cc");
        productEntity.setName("Carrot Cake");
        productEntity.setPrice(3.45);
        entityManager.persistAndFlush(productEntity);
        //when
        Iterable<Product> products = jpaCatalogService.getAllProducts();
        //then
        assertThat(products.iterator().next().getName())
                .isEqualTo(productEntity.getName());
    }
}