package com.multidbd.demo.controller;

import com.multidbd.demo.service.TenantRegistry;
import com.multidbd.demo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantRegistry tenantRegistry;

    @PostMapping("/register")
    public String registerTenant(@RequestParam String subdomain) {
        tenantService.registerTenant(subdomain);
        return "Tenant registered successfully: " + subdomain;
    }

    @GetMapping
    public Map<String, String> listTenants() {
        return tenantRegistry.getAllTenants();
    }
}
