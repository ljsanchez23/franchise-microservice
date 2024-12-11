package com.franchise_microservice.adapters.util;

public class AdaptersConstants {
    public static final String USERNAME_FROM_SECRET = "username";
    public static final String PASSWORD_FROM_SECRET = "password";
    public static final String HOST_FROM_SECRET = "host";
    public static final String PORT_FROM_SECRET = "port";
    public static final String DB_NAME_FROM_SECRET = "dbInstanceIdentifier";
    public static final String SECRET_NAME = "aws/secretsmanager/rdscredentials";
    public static final String SPRING_COMPONENT_MODEL = "spring";
    public static final String BRANCH_TABLE_NAME = "branch";
    public static final String FRANCHISE_TABLE_NAME = "franchise";
    public static final String PRODUCT_TABLE_NAME = "product";
    public static final String FRANCHISE_ID_JOIN_COLUMN = "franchise_id";
    public static final String BRANCH_ID_JOIN_COLUMN = "branch_id";
    public static final String UPDATE_BRANCH_NAME_QUERY = "UPDATE BranchEntity f SET f.name = :newName WHERE f.name = :currentName";
    public static final String UPDATE_BRANCH_NAME_QUERY_FIRST_PARAM = "currentName";
    public static final String UPDATE_BRANCH_NAME_QUERY_SECOND_PARAM = "newName";
    public static final String UPDATE_FRANCHISE_NAME_QUERY = "UPDATE FranchiseEntity f SET f.name = :newName WHERE f.name = :currentName";
    public static final String UPDATE_FRANCHISE_NAME_QUERY_FIRST_PARAM = "currentName";
    public static final String UPDATE_FRANCHISE_NAME_QUERY_SECOND_PARAM = "newName";
    public static final String UPDATE_PRODUCT_NAME_QUERY = "UPDATE ProductEntity f SET f.name = :newName WHERE f.name = :currentName";
    public static final String UPDATE_PRODUCT_NAME_QUERY_FIRST_PARAM = "currentName";
    public static final String UPDATE_PRODUCT_NAME_QUERY_SECOND_PARAM = "newName";

    public static final String BRANCH_CONTROLLER_URL = "/branch";
    public static final String FRANCHISE_CONTROLLER_URL = "/franchise";
    public static final String PRODUCT_CONTROLLER_URL = "/product";

    public static final String CREATE_ENDPOINT = "/create";
    public static final String UPDATE_NAME_ENDPOINT = "/update-name";
    public static final String DELETE_PRODUCT_FROM_BRANCH_ENDPOINT = "/delete-product";
    public static final String GET_HIGHEST_STOCK_ENDPOINT = "/highest-stock";
    public static final String UPDATE_STOCK_ENDPOINT = "/update-stock";
    public static final String HEALTH_CONTROLLER_URL = "/health";
}
