package com.multidbd.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TenantService {

    @Autowired
    private TenantRegistry tenantRegistry;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void registerTenant(String subdomain) {
        String schemaName = "tenant_" + subdomain.toLowerCase().trim();

        // Create schema if not exists
        em.createNativeQuery("CREATE SCHEMA IF NOT EXISTS " + schemaName).executeUpdate();

        // Register mapping
        tenantRegistry.registerTenant(subdomain, schemaName);
    }
}

