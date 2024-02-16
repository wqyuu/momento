package com.wqy.momento.util.key;

public interface KeyGenerator {
    String getValue();

    String getValue(long workId, long datacenterId);
}