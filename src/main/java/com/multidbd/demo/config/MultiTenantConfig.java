package com.multidbd.demo.config;

//import org.hibernate.MultiTenancyStrategy;
//import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MultiTenantConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private SchemaMultiTenantConnectionProvider multiTenantConnectionProvider;
//
//    @Autowired
//    private CurrentTenantIdentifierResolver tenantIdentifierResolver;
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setPackagesToScan("com.royal.toystore.entity"); // your entities package
//        emf.setJpaVendorAdapter(jpaVendorAdapter);
//
//        Map<String, Object> jpaProperties = new HashMap<>();
//        jpaProperties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
//        jpaProperties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
//        jpaProperties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
//
//        emf.setJpaPropertyMap(jpaProperties);
//        return emf;
//    }
//}

