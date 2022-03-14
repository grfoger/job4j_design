package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        cache.put(key, softValue);
    }

    public V get(K key) {
        if (cache.getOrDefault(key, null) == null) {
            load(key);
        }
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        return value;
    }

    protected abstract V load(K key);

}