package com.multidbd.demo.service;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TenantRegistry {

    private final Map<String, String> tenantToSchema = new ConcurrentHashMap<>();

    public String getSchema(String subdomain) {
        return tenantToSchema.getOrDefault(subdomain, "public");
    }

    public void registerTenant(String subdomain, String schema) {
        tenantToSchema.put(subdomain, schema);
    }

    public Map<String, String> getAllTenants() {
        return tenantToSchema;
    }
}


