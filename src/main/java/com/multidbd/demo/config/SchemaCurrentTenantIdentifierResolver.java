//package com.multidbd.demo.config;
//
//import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SchemaCurrentTenantIdentifierResolver 
//    implements CurrentTenantIdentifierResolver<String> {
//
//    @Override
//    public String resolveCurrentTenantIdentifier() {
//        String tenantId = TenantContext.getTenantId();
//        System.out.println("CURRENT TENANT"+ tenantId);
////        return tenantId != null ? tenantId : "public";
//        return tenantId != null ? tenantId : "public";
//    }
//
//    @Override
//    public boolean validateExistingCurrentSessions() {
//        return true;
//    }
//}


package com.multidbd.demo.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class SchemaCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getTenantId();
        System.out.println("CURRENT TENANT: " + tenantId);
        return tenantId != null ? tenantId : "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
