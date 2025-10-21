//package com.multidbd.demo.interceptor;
//
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.multidbd.demo.config.TenantContext;
//import com.multidbd.demo.config.TenantResolver;
//
//@Component
//public class TenantInterceptor implements HandlerInterceptor {
//
//    private final TenantResolver tenantResolver;
//
//    public TenantInterceptor(TenantResolver tenantResolver) {
//        this.tenantResolver = tenantResolver;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, 
//                           HttpServletResponse response, 
//                           Object handler) {
//        String domain = request.getServerName();
//        String tenantId = tenantResolver.resolveTenantId(domain);
//        System.out.println("DOMAIN NAME "+domain);
//        System.out.println("TENANT ID "+domain);
//        
//        if (tenantId == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return false;
//        }
//        
//        TenantContext.setTenantId(tenantId);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, 
//                               HttpServletResponse response, 
//                               Object handler, 
//                               Exception ex) {
//        TenantContext.clear();
//    }
//}

package com.multidbd.demo.interceptor;

import com.multidbd.demo.config.TenantContext;
import com.multidbd.demo.config.TenantResolver;
import com.multidbd.demo.config.tenantConfig.MultiTenantProperties;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TenantInterceptor implements HandlerInterceptor {

    private final TenantResolver tenantResolver;
    private final MultiTenantProperties multiTenantProperties;

    public TenantInterceptor(TenantResolver tenantResolver, MultiTenantProperties multiTenantProperties) {
        this.tenantResolver = tenantResolver;
        this.multiTenantProperties = multiTenantProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!multiTenantProperties.isEnabled()) {
            // single-tenant mode: use default tenant
            TenantContext.setTenantId("public");
            return true;
        }
        String domain = request.getServerName();
        String tenantId = tenantResolver.resolveTenantId(domain);
        TenantContext.setTenantId(tenantId);
        System.out.println("DOMAIN NAME: " + domain + " | TENANT: " + tenantId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear();
    }
}
