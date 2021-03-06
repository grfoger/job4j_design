package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean isPut = false;
        int index;
        if (key != null) {
            index = indexFor(hash(key.hashCode()));
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
                isPut = true;
            }
        }
        return isPut;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        float currentLoad = (float) count / capacity;
        if (currentLoad >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempTable = new MapEntry[capacity * 2];
            capacity = capacity * 2;
            for (MapEntry<K, V> kvMapEntry : table) {
                if (kvMapEntry != null) {
                    int index = indexFor(hash(kvMapEntry.key.hashCode()));
                    tempTable[index] = kvMapEntry;
                }

            }
            table = tempTable;
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null || (key.equals(table[index]))) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean isRemoved = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null || (key.equals(table[index]))) {
            table[index] = null;
            isRemoved = true;
            count--;
            modCount++;
        }
        return isRemoved;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }

        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}