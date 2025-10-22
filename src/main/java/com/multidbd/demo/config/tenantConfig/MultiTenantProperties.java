package com.multidbd.demo.config.tenantConfig;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantProperties {

    @Value("${app.multitenant.enabled:false}")
    private boolean enabled;

    @Value("${app.multitenant.default-schema:public}")
    private String defaultSchema;
    
    @Value("${app.multitenant.base-domains:}")
    private String baseDomains;

    public boolean isEnabled() {
        return enabled;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }
    
    public List<String> getBaseDomains() {
        if (baseDomains == null || baseDomains.isBlank()) {
            return List.of();
        }
        return Arrays.stream(baseDomains.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}