//package com.multidbd.demo.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.multidbd.demo.config.tenantConfig.TenantService;
//
//@RestController
//@RequestMapping("/admin/tenants")
//public class TenantController {
//
//    private final TenantService tenantService;
//
//    public TenantController(TenantService tenantService) {
//        this.tenantService = tenantService;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addTenant(@RequestParam String domain, @RequestParam String schema) {
//        try {
//            tenantService.addTenant(domain, schema);
//            return ResponseEntity.ok("Tenant added");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Failed to add tenant: " + e.getMessage());
//        }
//    }
//}


package com.multidbd.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.multidbd.demo.config.tenantConfig.TenantService;

@RestController
@RequestMapping("/admin/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<?> addTenant(@RequestParam String domain, @RequestParam String schema) {
        try {
            tenantService.addTenant(domain, schema);
            return ResponseEntity.ok("Tenant added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add tenant: " + e.getMessage());
        }
    }
}
