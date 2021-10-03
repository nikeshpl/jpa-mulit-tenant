package com.example.demo.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("proxiedThreadLocalTargetSource")
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

    @Autowired
    private TenantStore tenantStore;
    private static final String defaultTenant = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String t = tenantStore.getTenantId();
        if (t != null) {
            return t;
        } else {
            return defaultTenant;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}