package com.wqy.momento.util.key;

import java.util.UUID;

public class UuidGenerator implements KeyGenerator {
    public UuidGenerator() {
    }

    public String getValue() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getValue(long workId, long datacenterId) {
        return this.getValue();
    }
}