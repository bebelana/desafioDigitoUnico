package com.desafio.demo.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheCalculosService {
    private static final int MAX_ENTRIES = 10;
    private Map<String, Integer> cache;

    public CacheCalculosService() {
        this.cache = new LinkedHashMap<String, Integer>(MAX_ENTRIES + 1, 1.0f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }
    public Integer get(String key) {
        return  cache.get(key);
    }

    public void put(String key, Integer value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
