package com.wqy.momento.util.key;


public class SnowflakeIdWorker {
    private final long twepoch;
    private final long workerIdBits;
    private final long datacenterIdBits;
    private final long maxWorkerId;
    private final long maxDatacenterId;
    private final long sequenceBits;
    private final long workerIdShift;
    private final long datacenterIdShift;
    private final long timestampLeftShift;
    private final long sequenceMask;
    private long sequence;
    private long lastTimestamp;

    private SnowflakeIdWorker() {
        this.twepoch = 1420041600000L;
        this.workerIdBits = 5L;
        this.datacenterIdBits = 5L;
        this.maxWorkerId = 31L;
        this.maxDatacenterId = 31L;
        this.sequenceBits = 12L;
        this.workerIdShift = 12L;
        this.datacenterIdShift = 17L;
        this.timestampLeftShift = 22L;
        this.sequenceMask = 4095L;
        this.sequence = 0L;
        this.lastTimestamp = -1L;
    }

    public static SnowflakeIdWorker getInstance() {
        return SnowflakeIdWorker.SnowflakeIdWorkerHolder.SNOWFLAKEID_WORKER;
    }

    public synchronized long nextId(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                long timestamp = this.timeGen();
                if (timestamp < this.lastTimestamp) {
                    throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
                } else {
                    if (this.lastTimestamp == timestamp) {
                        this.sequence = this.sequence + 1L & 4095L;
                        if (this.sequence == 0L) {
                            timestamp = this.tilNextMillis(this.lastTimestamp);
                        }
                    } else {
                        this.sequence = 0L;
                    }

                    this.lastTimestamp = timestamp;
                    return timestamp - 1420041600000L << 22 | datacenterId << 17 | workerId << 12 | this.sequence;
                }
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    protected long timeGen() {
        return SystemClock.now();
    }

    public static class SnowflakeIdWorkerHolder {
        private static final SnowflakeIdWorker SNOWFLAKEID_WORKER = new SnowflakeIdWorker();

        public SnowflakeIdWorkerHolder() {
        }
    }
}