//package com.multidbd.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.multidbd.demo.interceptor.TenantInterceptor;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    private final TenantInterceptor tenantInterceptor;
//
//    public WebConfig(TenantInterceptor tenantInterceptor) {
//        this.tenantInterceptor = tenantInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tenantInterceptor)
//                .addPathPatterns("/api/**");
//    }
//}


package com.multidbd.demo.config;

import com.multidbd.demo.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;

    public WebConfig(TenantResolver tenantResolver) {
        this.tenantInterceptor = new TenantInterceptor(tenantResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/api/**");
    }
}
