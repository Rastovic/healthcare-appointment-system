package com.main.app.Config;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private final static AtomicInteger atomicInt = new AtomicInteger();

    public static Long getNewId() {
        long time = System.currentTimeMillis();
        int num = atomicInt.getAndIncrement() % 1000000;
        return time * 1000000 + num;
    }
}
