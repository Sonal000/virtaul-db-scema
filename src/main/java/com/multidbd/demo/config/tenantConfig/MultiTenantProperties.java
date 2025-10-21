package com.multidbd.demo.config.tenantConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantProperties {

    @Value("${app.multitenant.enabled:true}")
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }
}