package com.multidbd.demo.config;

//import java.sql.Connection;
//import java.sql.Statement;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Component
public class TenantInitializer implements CommandLineRunner {

    private final DataSource dataSource;
    private final TenantResolver tenantResolver;

    public TenantInitializer(DataSource dataSource, TenantResolver tenantResolver) {
        this.dataSource = dataSource;
        this.tenantResolver = tenantResolver;
    }

    @Override
    public void run(String... args) throws Exception {
        Set<String> tenants = new HashSet<>(tenantResolver.getDomainMappings().values());
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            
            for (String tenant : tenants) {
                String sql = "CREATE SCHEMA IF NOT EXISTS " + tenant;
                statement.execute(sql);
                
                statement.execute("CREATE TABLE IF NOT EXISTS " + tenant + 
                    ".users (id SERIAL PRIMARY KEY, name VARCHAR(255), email VARCHAR(255) " +
                    ")");
                
                System.out.println("Initialized schema: " + tenant);
            }
        }
    }
}


//@Component
//public class TenantInitializer implements CommandLineRunner {
//
//    private final DataSource dataSource;
//    private final TenantResolver tenantResolver;
//
//    public TenantInitializer(DataSource dataSource, TenantResolver tenantResolver) {
//        this.dataSource = dataSource;
//        this.tenantResolver = tenantResolver;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Set<String> tenants = new HashSet<>(tenantResolver.getDomainMappings().values());
//        
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            
//            for (String tenant : tenants) {
//                String sql = "CREATE SCHEMA IF NOT EXISTS " + tenant;
//                statement.execute(sql);
//                System.out.println("Created schema: " + tenant);
//            }
//        }
//    }
//}
