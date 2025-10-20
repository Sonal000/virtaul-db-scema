package com.multidbd.demo.config.tenantConfig;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TenantRepository {
    private final JdbcTemplate jdbc;

    public TenantRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Map<String, String> findAllDomainMappings() {
        String sql = "SELECT domain_name, schema_name FROM config.tenants";
        List<Map<String,Object>> rows = jdbc.queryForList(sql);
        return rows.stream().collect(Collectors.toMap(
            r -> (String) r.get("domain_name"),
            r -> (String) r.get("schema_name")
        ));
    }

    public int insertTenant(String domain, String schema) {
        return jdbc.update("INSERT INTO config.tenants(domain_name, schema_name) VALUES (?, ?)", domain, schema);
    }
}

