package com.franchise_microservice.domain.model;

import java.util.List;

public class Franchise {
    private String name;
    private List<Branch> branches;

    public Franchise(String name, List<Branch> branches) {
        this.name = name;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void addBranch(Branch branch){
        this.branches.add(branch);
    }
}
