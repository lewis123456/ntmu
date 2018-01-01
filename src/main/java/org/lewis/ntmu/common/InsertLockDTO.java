package org.lewis.ntmu.common;

import java.util.Date;

public class InsertLockDTO {
    private String method;
    private String ipThreadId;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIpThreadId() {
        return ipThreadId;
    }

    public void setIpThreadId(String ipThreadId) {
        this.ipThreadId = ipThreadId;
    }
}
