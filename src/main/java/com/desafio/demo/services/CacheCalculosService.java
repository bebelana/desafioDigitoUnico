package com.desafio.demo.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheCalculosService {

    private static final int CACHE_SIZE = 10;
    private final LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>(CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > CACHE_SIZE;
        }
    };

    public String get(String key) {
        return  cache.get(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
