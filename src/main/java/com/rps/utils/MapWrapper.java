package com.rps.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for building a HashMap in one-line code.
 */
public final class MapWrapper<KEY, VALUE> {
    private Map<KEY, VALUE> map;
    
    private MapWrapper() {
        map = new HashMap<KEY, VALUE>();
    }
    
    public static <KEY, VALUE> MapWrapper<KEY, VALUE> instance() {
        return new MapWrapper();   
    }
    
    public MapWrapper put(KEY key, VALUE value) {
        map.put(key, value);
        return this;
    }
    
    public Map<KEY, VALUE> get() {
        return map;
    }
}