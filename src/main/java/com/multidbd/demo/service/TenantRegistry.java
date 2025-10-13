package com.multidbd.demo.service;

package com.royal.toystore.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TenantRegistry {

    private final Map<String, String> tenantMap = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File registryFile = new File("tenant-registry.json");

    public TenantRegistry() {
        loadFromFile();
    }

    public void registerTenant(String subdomain, String schemaName) {
        tenantMap.put(subdomain, schemaName);
        saveToFile();
    }

    public String getSchema(String subdomain) {
        return tenantMap.get(subdomain);
    }

    private void saveToFile() {
        try {
            mapper.writeValue(registryFile, tenantMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        if (!registryFile.exists()) return;
        try {
            Map<String, String> loaded = mapper.readValue(registryFile, new TypeReference<>() {});
            tenantMap.putAll(loaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

