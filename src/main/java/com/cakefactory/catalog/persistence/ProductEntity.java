package com.cakefactory.catalog.persistence;

import javax.persistence.*;

@Entity
@Table(name = "catalog")
class ProductEntity {

    private String sku;
    private String name;
    private double price;

    @Id
    public String getSku() {
        return sku;
    }

    public void setSku(String id) {
        this.sku = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
