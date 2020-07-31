package com.concurrent;

import org.openjdk.jmh.annotations.Threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockMap<K, V> {
//    Map<K, V> map = new HashMap<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(K k, V v) {
        lock.writeLock().lock();
        try {
//            map.put(k, v);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K k) {
        lock.readLock().lock();
        try {
            for (int i = 1; i < 1000; i++) {
            }
        } finally {
            lock.readLock().unlock();
        }
        return null;
    }

    public int size() {
        lock.readLock().lock();
        try {
//            return map.size();
        } finally {
            lock.readLock().unlock();
        }
        return 0;
    }
}

