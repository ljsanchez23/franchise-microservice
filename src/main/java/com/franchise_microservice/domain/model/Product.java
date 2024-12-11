package com.franchise_microservice.domain.model;

public class Product {
    private String name;
    private Integer stock;
    private String branchName;

    public Product(String name, Integer stock, String branchName) {
        this.name = name;
        this.stock = stock;
        this.branchName = branchName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
