package com.multidbd.demo.config;

//import com.multidbd.demo.service.TenantRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MultiTenantDataSource extends AbstractRoutingDataSource {
//
//    @Autowired
//    private TenantRegistry tenantRegistry;
//
//    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();
//
//    public static void setCurrentTenant(String tenant) {
//        currentTenant.set(tenant);
//    }
//
//    public static void clear() {
//        currentTenant.remove();
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return currentTenant.get();
//    }
//
//    public String determineSchema() {
//        String subdomain = currentTenant.get();
//        return tenantRegistry.getSchema(subdomain);
//    }
//}
//
