package com.multidbd.demo.config;


import com.multidbd.demo.config.MultiTenantContext;
import com.multidbd.demo.service.TenantRegistry;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {

    private final TenantRegistry tenantRegistry;

    public TenantFilter(TenantRegistry tenantRegistry) {
        this.tenantRegistry = tenantRegistry;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String host = httpRequest.getServerName(); // e.g. tenant1.app.com
        
        
        String subdomain = host.split("\\.")[0]; // tenant1
        String schema = tenantRegistry.getSchema(subdomain);
        
        System.out.println("HOST : "+host);
        System.out.println("subdomain : "+subdomain);
        System.out.println("schema : "+schema);

        MultiTenantContext.setCurrentTenant(schema);
        try {
            chain.doFilter(request, response);
        } finally {
            MultiTenantContext.clear();
        }
    }

}