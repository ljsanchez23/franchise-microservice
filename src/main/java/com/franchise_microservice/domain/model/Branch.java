package com.franchise_microservice.domain.model;

import java.util.List;

public class Branch {
    private String name;
    private List<Product> products;

    public Branch(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public boolean removeProductByName(String productName) {
        return products.removeIf(product -> product.getName().equals(productName));
    }

}
