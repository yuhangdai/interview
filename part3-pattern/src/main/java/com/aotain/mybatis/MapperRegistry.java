package com.aotain.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author bang
 * @date 2021/01/27
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new MapperProxyFactory<>(type));
                // It's important that the type is added before the parser is run
                // otherwise the binding may automatically be attempted by the
                // mapper parser. If the type is already known, it won't try.
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " );
        }
    }
}
