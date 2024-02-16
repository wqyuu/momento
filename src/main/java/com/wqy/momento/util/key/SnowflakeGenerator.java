package com.wqy.momento.util.key;

public class SnowflakeGenerator implements KeyGenerator {
    public SnowflakeGenerator() {
    }

    public String getValue() {
        Long id = SnowflakeIdWorker.getInstance().nextId(0L, 0L);
        return String.valueOf(id);
    }

    public String getValue(long workId, long datacenterId) {
        return null;
    }

    public static void main(String[] args) {
        SnowflakeGenerator generator = new SnowflakeGenerator();
        System.out.println(generator.getValue());
    }
}