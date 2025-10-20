//package com.multidbd.demo.config;


//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@ConfigurationProperties(prefix = "tenants")
//public class TenantResolver {
//
//    private Map<String, String> domainMappings = new HashMap<>();
//
//    public String resolveTenantId(String domain) {
//        // Try exact match first
//        String tenantId = domainMappings.get(domain);
//        
//        // If no exact match, try matching without port
//        if (tenantId == null && domain.contains(":")) {
//            String domainWithoutPort = domain.substring(0, domain.indexOf(":"));
//            tenantId = domainMappings.get(domainWithoutPort);
//        }
//        
//        return tenantId;
//    }
//
//    public Map<String, String> getDomainMappings() {
//        return domainMappings;
//    }
//
//    public void setDomainMappings(Map<String, String> domainMappings) {
//        this.domainMappings = domainMappings;
//    }
//}


package com.multidbd.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.multidbd.demo.config.tenantConfig.TenantService;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tenants")
public class TenantResolver {

    private Map<String, String> domainMappings = new HashMap<>();
    private final TenantService tenantService;

    public TenantResolver(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    public String resolveTenantId(String domain) {
        String tenantId = tenantService.resolveTenantByDomain(domain);
        if (tenantId != null) return tenantId;

        tenantId = domainMappings.get(domain);
        if (tenantId == null && domain.contains(":")) {
            String domainWithoutPort = domain.substring(0, domain.indexOf(":"));
            tenantId = domainMappings.get(domainWithoutPort);
        }
        return tenantId;
    }

    public Map<String, String> getDomainMappings() {
        return domainMappings;
    }

    public void setDomainMappings(Map<String, String> domainMappings) {
        this.domainMappings = domainMappings;
    }
}

