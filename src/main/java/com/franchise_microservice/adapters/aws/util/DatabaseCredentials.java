package com.franchise_microservice.adapters.aws.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatabaseCredentials {
    private String username;
    private String password;
    private String host;
    private String port;
    private String dbName;
}
