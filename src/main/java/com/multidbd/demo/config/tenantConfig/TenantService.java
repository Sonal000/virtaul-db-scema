package com.multidbd.demo.config.tenantConfig;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final DataSource dataSource;
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public TenantService(TenantRepository tenantRepository, DataSource dataSource) {
        this.tenantRepository = tenantRepository;
        this.dataSource = dataSource;
        refreshCache();
    }

    @Scheduled(fixedDelayString = "${tenants.refresh-ms:30000}")
    public void scheduledRefresh() {
        refreshCache();
    }

    public void refreshCache() {
        Map<String, String> map = tenantRepository.findAllDomainMappings();
        cache.clear();
        cache.putAll(map);
    }

    public String resolveTenantByDomain(String domain) {
        String t = cache.get(domain);
        if (t == null && domain.contains(":")) {
            t = cache.get(domain.substring(0, domain.indexOf(":")));
        }
        return t;
    }

    public void ensureSchemaCreated(String schema) throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + schema);
            stmt.execute("CREATE TABLE IF NOT EXISTS " + schema +
                ".users (id SERIAL PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))");
        }
    }

    public void addTenant(String domain, String schema) throws Exception {
        tenantRepository.insertTenant(domain, schema);
        ensureSchemaCreated(schema);
        cache.put(domain, schema);
    }
}

