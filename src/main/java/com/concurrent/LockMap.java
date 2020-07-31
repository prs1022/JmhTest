package com.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LockMap<K, V> {
    Map<K, V> map = new HashMap<>();

    ReentrantLock lock = new ReentrantLock();

    public void put(K k, V v) {
        lock.lock();
        try {
            map.put(k, v);
        } finally {
            lock.unlock();
        }
    }

    public V get(K k) {
        lock.lock();
        try {
            for (int i = 1; i < 1000; i++) {
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    public int size() {
        lock.lock();
        try {
            return map.size();
        } finally {
            lock.unlock();
        }
    }
}

