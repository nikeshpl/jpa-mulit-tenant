package com.example.demo.tenant;

import lombok.Data;

@Data
public class TenantStore {

    private String tenantId;

    public void clear() {
        this.tenantId = null;
    }
}