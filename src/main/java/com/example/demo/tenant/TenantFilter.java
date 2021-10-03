package com.example.demo.tenant;

import com.example.demo.tenant.TenantStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class TenantFilter implements Filter {
    private static final String TENANT_HEADER_NAME = "X-TENANT-ID";

    @Autowired
    TenantStore tenantStore;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        System.out.printf("Invoking tenant filter ===============");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tenantId = request.getHeader(TENANT_HEADER_NAME);
        try {
            this.tenantStore.setTenantId(tenantId);
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            this.tenantStore.clear();
        }
    }
}
