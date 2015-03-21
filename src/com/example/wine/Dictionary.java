package com.example.wine;

import java.util.HashMap;

/**
 * This is a specialized HashMap with Value defaulting as an empty list
 *
 * Created by Sarvex on 18/03/2015.
 */
public class Dictionary<K, V> extends HashMap<K, V> {

    Class<V> klass;

    public Dictionary(Class klass) {
        this.klass = klass;
    }

    @Override
    public V get(Object key) {
        V result = super.get(key);

        if (result == null) {
            try {
                result = klass.newInstance();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            this.put((K) key, result);
        }

        return result;
    }
}
