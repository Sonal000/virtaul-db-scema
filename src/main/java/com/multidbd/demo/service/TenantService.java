//package com.multidbd.demo.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Service;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.sql.Connection;
//import java.sql.Statement;
//
//
//@Service
//public class TenantService {
//
////    @Autowired
////    private TenantRegistry tenantRegistry;
////
////    @PersistenceContext
////    private EntityManager em;
////
////    @Transactional
////    public void registerTenant(String subdomain) {
////        String schemaName = "tenant_" + subdomain.toLowerCase().trim();
////
////        // Create schema if not exists
////        em.createNativeQuery("CREATE SCHEMA IF NOT EXISTS " + schemaName).executeUpdate();
////
////        // Register mapping
////        tenantRegistry.registerTenant(subdomain, schemaName);
////    }
//	
//
//
//		@Autowired
//	    public DataSource dataSource;
//
//
//	    // Step 1: Create schema
//	    public void createSchema(String schemaName) {
//	        try (Connection connection = dataSource.getConnection();
//	             Statement statement = connection.createStatement()) {
//	            String sql = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
//	            statement.execute(sql);
//	            System.out.println("Schema created: " + schemaName);
//	        } catch (Exception e) {
//	            throw new RuntimeException("Failed to create schema: " + schemaName, e);
//	        }
//	    }
//
//	    // Step 2: Create tables in that schema
//	    public void createTablesForTenant(String schema) {
//	        try {
//	            Map<String, Object> settings = new HashMap<>();
//	            settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//	            settings.put("hibernate.hbm2ddl.auto", "update");
//	            settings.put("hibernate.default_schema", schema);
//
//	            // Build a new ServiceRegistry with tenant-specific schema
//	            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//	                    .applySettings(settings)
//	                    .build();
//
//	            // Build Metadata from your entity package
//	            MetadataSources metadataSources = new MetadataSources(registry);
//	            metadataSources.addAnnotatedClass(com.multidbd.demo.entity.User.class);
//	            // Add other entity classes here if needed
//
//	            Metadata metadata = metadataSources.buildMetadata();
//
//	            // Generate or update schema
//	            SchemaManagementToolCoordinator.process(
//	                    metadata,
//	                    registry,
//	                    settings,
//	                    null
//	            );
//
//	            System.out.println("Tables created for schema: " + schema);
//
//	        } catch (Exception e) {
//	            throw new RuntimeException("Error creating tables for schema: " + schema, e);
//	        }
//	    }
//
//	    // Convenience method to add a new tenant
//	    public String addTenant(String tenant) {
//	        createSchema(tenant);
//	        createTablesForTenant(tenant);
//	        return tenant;
//	    }
//	}
//
//	
//	
//
